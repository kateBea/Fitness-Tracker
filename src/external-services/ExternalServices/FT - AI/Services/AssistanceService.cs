using FTAI.Models;
using FTAI.Interfaces;
using FTAI.ViewModels;
using FTAI.Logging;
using OpenAI_API.Chat;
using OpenAI_API.Models;
using AutoMapper;
using Shared.Utilities;
using Newtonsoft.Json;
using Shared.Contexts;
using FTAlimentos.Interfaces;
using NJsonSchema;

namespace FTAI.Services
{
    /// <summary>
    /// Clase que representa una conversación de usuario.
    /// </summary>
    public class UserConversation
    {
        /// <summary>
        /// Nombre del usuario.
        /// </summary>
        public string UserName { get; set; } = string.Empty;

        /// <summary>
        /// Objeto que representa la conversación del usuario.
        /// </summary>
        public Conversation? Conversation { get; set; }
    }

    /// <summary>
    /// Asssitance service
    /// </summary>
    public class AssistanceService : IAssistanceService
    {
        #region Properties
        /// <summary>
        /// Objeto logger para este servicio.
        /// </summary>
        private readonly ILogger _logger;

        /// <summary>
        /// Objeto para mapear entre tipos.
        /// </summary>
        private readonly IMapper _mapper;

        /// <summary>
        /// Servicio de alimentos para la dieta.
        /// </summary>
        private readonly IAlimentosService _alimentosService;

        /// <summary>
        /// Clave de autenticación opcional para servicios externos.
        /// </summary>
        private readonly string? _authKey = string.Empty;

        /// <summary>
        /// Conversaciones activas de los usuarios.
        /// </summary>
        private static List<UserConversation> _activeConversation = [];

        /// <summary>
        /// Contexto de datos HTTP.
        /// </summary>
        private readonly IDataHttpContext _dataHttpContext;

        #endregion

        /// <summary>
        /// Constructor por defecto de la clase AssistanceService.
        /// </summary>
        /// <param name="logger">Objeto logger para registrar información.</param>
        /// <param name="configuration">Configuración de la aplicación.</param>
        /// <param name="mapper">Objeto para mapear entre tipos.</param>
        /// <param name="dataHttpContext">Contexto de datos HTTP.</param>
        /// <param name="alimentosService">Servicio de alimentos para la dieta.</param>
        public AssistanceService(ILogger<AssistanceService> logger, IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext, IAlimentosService alimentosService)
        {
            _logger = logger;
            _authKey = configuration.GetValue<string>("OpenAIAuthKey");
            _mapper = mapper;
            _dataHttpContext = dataHttpContext;
            _alimentosService = alimentosService;

            // Inicialización de la librería OpenAI
            Model.DefaultChatModel = Model.GPT4_Vision;
            OpenAI_API.APIAuthentication.Default = new OpenAI_API.APIAuthentication(_authKey);
        }

        /// <summary>
        /// Realiza una solicitud de chat limitada por tokens para depuración.
        /// Utiliza un modelo GPT-4 Turbo Vision con un límite máximo de tokens.
        /// </summary>
        /// <param name="data">Datos de solicitud que incluyen el mensaje del usuario y el límite de tokens.</param>
        /// <returns>ViewModel de respuesta con el resultado de la solicitud.</returns>
        public async Task<ResponseMessageDebugVM> AskWithTokenLimit(RequestMessageDebugLimitTokens data)
        {
            // Configuración
            var api = new OpenAI_API.OpenAIAPI();
            var model = Model.GPT4_Vision;  // Tipo de modelo
            var temperature = 0.1;          // Riesgos del modelo

            // Consulta
            var results = await api.Chat.CreateChatCompletionAsync(new ChatRequest()
            {
                Model = model,
                Temperature = temperature,
                MaxTokens = data.MaxTokens,
                Messages =
                [
                    new ChatMessage(ChatMessageRole.User, data.Message)
                ]
            });

            // Depuración de resultados
            _logger.LogDebug($"Respuesta recibida: {results}");
            _logger.LogDebug($"Para el mensaje: {data.Message}");
            _logger.LogDebug($"Desde el modelo: {results.Model}");
            _logger.LogDebug($"Con máximo de tokens: {data.MaxTokens}");

            // Registro en archivo
            FileLogging.LogToFile(results.ToString());

            // Retorna el ViewModel de respuesta
            return new ResponseMessageDebugVM() { Result = results.ToString() };
        }


        /// <summary>
        /// Realiza una solicitud de chat utilizando los ajustes predeterminados para obtener respuestas de los endpoints de OpenAI.
        /// </summary>
        /// <param name="message">El mensaje del usuario para el cual se solicita la respuesta.</param>
        /// <returns>ViewModel de respuesta con el resultado de la solicitud.</returns>
        public async Task<ResponseMessageDebugVM> Ask(string message)
        {
            var api = new OpenAI_API.OpenAIAPI();
            var result = await api.Chat.CreateChatCompletionAsync(message);

            FileLogging.LogToFile(result.ToString());
            return new ResponseMessageDebugVM() { Result = result.ToString() };
        }


        /// <summary>
        /// Inicia un nuevo chat de asistencia basado en la solicitud proporcionada por el usuario.
        /// </summary>
        /// <param name="chatInfo">La información del chat proporcionada por el usuario.</param>
        /// <returns>ViewModel de respuesta que contiene los mensajes intercambiados en el chat.</returns>
        public async Task<ResponseStartNewChatAssistanceVM> StartNewChat(RequestStartNewChatAssistance chatInfo)
        {
            var api = new OpenAI_API.OpenAIAPI();
            var result = new ResponseStartNewChatAssistanceVM();

            var conversation = api.Chat.CreateConversation();
            conversation.AppendUserInput(chatInfo.Mensaje);
            result.RespuestaActual = await conversation.GetResponseFromChatbotAsync();

            var messages = conversation.Messages.ToList();

            foreach (var message in messages)
            {
                result.Mensajes.Add(new()
                {
                    Content = message.TextContent,
                    MessageEmisor = message.Role == 
                        ChatMessageRole.Assistant ? MessageEmisor.ASSISTANT : MessageEmisor.USER,
                });
            }

            return result;
        }


        /// <summary>
        /// Genera una dieta personalizada basada en la solicitud proporcionada por el usuario.
        /// </summary>
        /// <param name="dieta">La solicitud de generación de dieta proporcionada por el usuario.</param>
        /// <returns>ViewModel de respuesta que contiene la información de la dieta generada.</returns>
        public async Task<ResponseGenerarDietaVM> GenerarDieta(RequestGenerarDieta dieta)
        {
            // Setup
            var categoria = "generic-meals";
            var api = new OpenAI_API.OpenAIAPI();
            var health = await GetHealthLabels(dieta, api);
            var detallesComidas = await GetComidas(health, categoria);
            dieta.PreferenciasAlimenticias = detallesComidas;

            var message = BuildDietaQuery(dieta);

            var requestAttempts = 5;
            var requestSuccess = false;
            var ejemplo = JsonConvert.SerializeObject(GetEjemplo(), Formatting.Indented);
            FileLogging.LogToFile($"JSON Schema of PlanDieta (request) ===================\n{message}");
            FileLogging.LogToFile($"JSON Schema of PlanDieta (response) ===================\n{ejemplo}");

            var assistantResponseObject = new AIResponseSchemaJson();

            while (requestAttempts > 0 && !requestSuccess)
            {
                try
                {
                    var assistantResponseStr = await api.Chat.CreateChatCompletionAsync(
                        $"Generar dieta con las siguientes especificaciones:\n{message}.\n" +
                        $"Usa únicamente las comidas que hay en el Array preferenciasAlimenticias para esta dieta\n" +
                        $"los posibles valores para orden_comida son: PRIMER_PLATO, SEGUNDO_PLATO, TERCER_PLATO, PLATO_UNICO\n" +
                        $"los posibles valores para tipo_comida son: DESAYUNO, MERIENDA_MATUTINA, ALMUERZO, MERIENDA_VESPERTINA, CENA\n" +
                        $"Establece el consumo de agua diario que consideres necesario para este usuario\n" +
                        $"Responde solo con un JSON siguiendo el siguiente JSON como ejemplo, no uses los datos del ejemplo, sólo utiliza el mismo formato:\n{ejemplo}\n");

                    assistantResponseObject = GetAssistantObjectResponseFromStr(assistantResponseStr.ToString());

                    FileLogging.LogToFile($"Assistant answer ======================\n{assistantResponseStr}");
                    requestSuccess = true;
                }
                catch
                {
                    --requestAttempts;
                }
            }

            var result = requestSuccess ? _mapper.Map<ResponseGenerarDietaVM>(assistantResponseObject) : new();
            result.ResponseDescription = requestSuccess ? "Dieta generada con éxito" : "No se ha podido generar la dieta";

            if (requestSuccess)
            {
                // Cargar detalles comidas
                foreach (var item in result?.Dieta.Comidas)
                {
                    var comida = detallesComidas.Find(c => c.Id == item.Id);
                    if (comida != null)
                    {
                        item.Descripcion = comida.Descripcion;
                        item.Proteinas = comida.Proteinas;
                        item.Carbohidratos = comida.Carbohidratos;
                        item.Grasas = comida.Grasas;
                        item.Caloias = comida.Caloias;
                    }
                }
            }

            return result;
        }

        /// <summary>
        /// Genera un AIResponseSchemaJson. Útil para utilizar como
        /// muestra de datos a seguir para el asistente.
        /// </summary>
        /// <returns>Objeto AIResponseSchemaJson</returns>
        private static AIResponseSchemaJson GetEjemplo()
        {
            // Ejemplo de uso
            var ejemploDieta = new AIResponseSchemaJson
            {
                PlanDieta = new PlanDieta
                {
                    CaloriasObjetivoDiario = 1800.0,
                    FechaInicio = new DateTime(2024, 6, 10),
                    FechaFin = new DateTime(2024, 9, 10),
                    ConsumoDiarioAguaLitros = 2.0,
                    MenuDiario = new List<MenuDiario>
                {
                new()
                {
                    ComidaId = "foodid1",
                    NombreComida = "Food 1",
                    HoraConsumo = new DateTime(2024, 6, 10, 8, 0, 0),
                    CaloriasConsumidas = 190.10363961529697,
                    OrdenComida = "PRIMER_PLATO",
                    TipoComida = "DESAYUNO"
                },
            new() {
                ComidaId = "foodId2",
                NombreComida = "Food 2",
                HoraConsumo = new DateTime(2024, 6, 10, 13, 0, 0),
                CaloriasConsumidas = 108.95621144933602,
                OrdenComida = "PRIMER_PLATO",
                TipoComida = "ALMUERZO"
            },
        }
                }
            };


            return ejemploDieta;
        }

        /// <summary>
        /// Obtiene una lista de comidas basadas en las restricciones de salud y la categoría especificada.
        /// </summary>
        /// <param name="health">Lista de restricciones de salud que deben cumplir las comidas.</param>
        /// <param name="categoria">Categoría de las comidas a buscar.</param>
        /// <returns>Una lista de objetos ComidaDieta que cumplen con los criterios especificados.</returns>
        private async Task<List<ComidaDieta>> GetComidas(List<string> health, string categoria)
        {
            var result = await _alimentosService.Parse("meal", health, categoria);
            List<ComidaDieta> comidaDietas = [];


            foreach (var item in result?.Result?.Hints)
            {
                comidaDietas.Add(new ComidaDieta
                {
                    Id = item.Food.FoodId,
                    Nombre = item.Food.Label,
                    Descripcion = item.Food.KnownAs,
                    Proteinas = item.Food.Nutrients.Protein,
                    Carbohidratos = item.Food.Nutrients.Carbohydrates,
                    Grasas = item.Food.Nutrients.Fat,
                    Caloias = item.Food.Nutrients.Calories,
                });
            }

            return comidaDietas;
        }

        /// <summary>
        /// Obtiene las etiquetas de salud recomendadas basadas en el modelo de solicitud y la API de OpenAI proporcionada.
        /// </summary>
        /// <param name="model">Modelo de solicitud que contiene la información para determinar las etiquetas de salud.</param>
        /// <param name="api">Instancia de la API de OpenAI utilizada para obtener las etiquetas de salud.</param>
        /// <returns>Una lista de etiquetas de salud recomendadas.</returns>
        private async Task<List<string>> GetHealthLabels(RequestGenerarDieta model, OpenAI_API.OpenAIAPI api)
        {
            List<string> result = new();

            List<string> healthParameters = new List<string>
            {
                "alcohol-free",
                "celery-free",
                "crustacean-free",
                "dairy-free",
                "egg-free",
                "fish-free",
                "fodmap-free",
                "gluten-free",
                "immuno-supportive",
                "keto-friendly",
                "kidney-friendly",
                "kosher",
                "low-fat-abs",
                "low-potassium",
                "low-sugar",
                "lupine-free",
                "mustard-free",
                "no-oil-added",
                "paleo",
                "peanut-free",
                "pescatarian",
                "pork-free",
                "red-meat-free",
                "sesame-free",
                "shellfish-free",
                "soy-free",
                "sugar-conscious",
                "tree-nut-free",
                "vegan",
                "vegetarian",
                "wheat-free"
            };

            var schema = JsonSchema.FromType<List<string>>();
            var prompt = $"Para una persona que quiere seguir una dieta con las siguientes características:\n{JsonConvert.SerializeObject(model, Formatting.Indented)}\n" +
                $"\nCual de las siguinetes categorías de comida recomiendas:\n{String.Join(",", healthParameters)}\n" +
                $"Necesito una respuesta en JSON que se adhiera al siguiente esquema JSON. Por favor, proporciona solo la respuesta" +
                $" en JSON y asegúrate de que cumpla con el formato proporcionado.\n Formato JSON:\n [\"string\", \"string\", ...]";
            var requestAttempts = 10;
            var responseSuccess = false;

            while (requestAttempts > 0 && !responseSuccess)
            {
                try
                {
                    var assistantResponse = await api.Chat.CreateChatCompletionAsync(prompt);

                    var assistantResponseStr = assistantResponse.ToString();

                    var a = assistantResponseStr.IndexOf('[');
                    var b = assistantResponseStr.LastIndexOf(']');

                    var assistantResponseStrSubStr = assistantResponseStr.Substring(
                        assistantResponseStr.IndexOf('['), assistantResponseStr.LastIndexOf(']') + 1);
                    result = JsonConvert.DeserializeObject<List<string>>(assistantResponseStrSubStr.Trim()) ?? [];
                    responseSuccess = true;

                }
                catch
                {
                    --requestAttempts;
                }
            }


            return result;
        }

        /// <summary>
        /// Convierte una cadena JSON en un objeto AIResponseSchemaJson opcional.
        /// </summary>
        /// <param name="assistantResponseStr">Cadena JSON que representa la respuesta del asistente.</param>
        /// <returns>Objeto AIResponseSchemaJson si la conversión es exitosa; de lo contrario, null.</returns>
        private static AIResponseSchemaJson? GetAssistantObjectResponseFromStr(string assistantResponseStr)

        {
            var result = assistantResponseStr.Substring(
                assistantResponseStr.IndexOf('{'), assistantResponseStr.LastIndexOf('}') + 1);

            return JsonConvert.DeserializeObject<AIResponseSchemaJson>(result);
        }

        /// <summary>
        /// Construye una consulta de dieta basada en los detalles proporcionados en el modelo RequestGenerarDieta.
        /// </summary>
        /// <param name="details">Detalles de la dieta especificados en el modelo RequestGenerarDieta.</param>
        /// <returns>Consulta de dieta construida como una cadena.</returns>
        private static string BuildDietaQuery(RequestGenerarDieta details)
        {
            var result = string.Empty;
            AIRequestDietaSchemaJson schema = new()
            {
                Edad = DateTime.Now.Year - details.FechaNacimiento.Year,
                FechaInicio = details.FechaInicio,
                FechaFin = details.FechaFin,
                ComidasSugeridas = details.PreferenciasAlimenticias,
            };


            var json = JsonConvert.SerializeObject(schema, Formatting.Indented);

            result += json;

            // Results
            FileLogging.LogToFile($"AI JSON Schema Result ================================\n{result}");

            return result;
        }
    }
}
