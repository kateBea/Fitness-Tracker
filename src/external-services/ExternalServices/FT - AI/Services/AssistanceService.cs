using FTAI.Models;
using FTAI.Interfaces;
using FTAI.ViewModels;
using FTAI.Logging;
using OpenAI_API.Chat;
using OpenAI_API.Models;
using OpenAI_API.Moderation;
using AutoMapper;
using Shared.Utilities;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using Shared.Contexts;
using Microsoft.AspNetCore.Mvc.RazorPages;
using FTAI.Controllers.v1;
using FTAlimentos.Interfaces;
using System;
using NJsonSchema;

namespace FTAI.Services
{
    public class UserConversation
    {
        public string UserName { get; set; } = string.Empty;
        public Conversation? Conversation { get; set; }
    }

    public class AssistanceService : IAssistanceService
    {
        #region Properties
        /// <summary>
        /// Logger object for this service
        /// </summary>
        private readonly ILogger _logger;
        private readonly IMapper _mapper;
        private readonly IAlimentosService _alimentosService;

        /// <summary>
        /// 
        /// </summary>
        private readonly string? _authKey = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        private static List<UserConversation> _activeConversation = [];

        private readonly IDataHttpContext _dataHttpContext;

        #endregion

        /// <summary>
        /// Default constructor.
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="configuration"></param>
        public AssistanceService(ILogger<AssistanceService> logger, IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext, IAlimentosService alimentosService)
        {
            _logger = logger;
            _authKey = configuration.GetValue<string>("OpenAIAuthKey");
            _mapper = mapper;
            _dataHttpContext = dataHttpContext;
            _alimentosService = alimentosService;

            // Init OpenAI lib
            Model.DefaultChatModel = Model.GPT4_Vision;
            OpenAI_API.APIAuthentication.Default = new OpenAI_API.APIAuthentication(_authKey);
        }


        public async Task<ResponseMessageDebugVM> AskWithTokenLimit(RequestMessageDebugLimitTokens data)
        {
            // Setup
            var api = new OpenAI_API.OpenAIAPI();

            var model = Model.GPT4_Vision;  // model type
            var temperature = 0.1;          // model risks

            // Query
            var results = await api.Chat.CreateChatCompletionAsync(new ChatRequest()
            {
                Model = model,
                Temperature = temperature,
                MaxTokens = data.MaxTokens,
                Messages = new List<ChatMessage> {
                    new ChatMessage(ChatMessageRole.User, data.Message)
                }
            });

            // Results debug
            _logger.LogDebug($"Got response: {results}");
            _logger.LogDebug($"For message: {data.Message}");
            _logger.LogDebug($"From model: {results.Model}");
            _logger.LogDebug($"With max tokens: {data.MaxTokens}");

            // File Log
            FileLogging.LogToFile(results.ToString());

            return new ResponseMessageDebugVM() { Result = results.ToString() };
        }

        public async Task<ResponseMessageDebugVM> Ask(string message)
        {
            // Create api
            var api = new OpenAI_API.OpenAIAPI();

            // Chat coompletion
            var result = await api.Chat.CreateChatCompletionAsync(message);

            // File Log
            FileLogging.LogToFile(result.ToString());

            // Results
            _logger.LogDebug($"Got response {result}");
            return new ResponseMessageDebugVM() { Result = result.ToString() };
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="chatInfo"></param>
        /// <returns></returns>
        public async Task<ResponseStartNewChatAssistanceVM> StartNewChat(RequestStartNewChatAssistance chatInfo)
        {
            var result = new ResponseStartNewChatAssistanceVM();
            var api = new OpenAI_API.OpenAIAPI();

            var conversation = api.Chat.CreateConversation();

            conversation.AppendUserInput(chatInfo.Mensaje);
            var respuesta = await conversation.GetResponseFromChatbotAsync();

            var messages = conversation.Messages.ToList();

            return result;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dieta"></param>
        /// <returns></returns>
        public async Task<ResponseGenerarDietaVM> GenerarDieta(RequestGenerarDieta dieta)
        {
            // Setup
            var api = new OpenAI_API.OpenAIAPI();

            // Determinar categoría
            List<string> health = await GetHealthLabels(dieta, api);

            // Asignar categoría
            string categoria = "generic-meals";

            // Obtener las comidas
            dieta.PreferenciasAlimenticias = await GetComidas(health, categoria);

            // Cosntruimos la query de la dieta
            var message = BuildDietaQuery(dieta);

            // Chat coompletion
            var requestAttempts = 5;
            var requestSuccess = false;
            var schema = JsonSchema.FromType<AIResponseSchemaJson>().ToJson();
            var ejemplo = JsonConvert.SerializeObject(GetEjemplo(), Formatting.Indented);
            FileLogging.LogToFile($"JSON Schema of PlanDieta (request) ===================\n{message}");
            FileLogging.LogToFile($"JSON Schema of PlanDieta (response) ===================\n{schema}");

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
                        $"Establece el consumo de agua diario que consideres necesario para este usuario\n"+
                        $"Responde solo con un JSON siguiendo el siguiente JSON como ejemplo, no uses los datos del ejemplo, sólo utiliza el mismo formato:\n{ejemplo}\n");

                    assistantResponseObject = GetAssistantObjectResponseFromStr(assistantResponseStr.ToString());

                    // File Log
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

            return result;
        }

        private AIResponseSchemaJson GetEjemplo()
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
            new MenuDiario
            {
                ComidaId = "foodid1",
                NombreComida = "Food 1",
                HoraConsumo = new DateTime(2024, 6, 10, 8, 0, 0),
                CaloriasConsumidas = 190.10363961529697,
                OrdenComida = "PRIMER_PLATO",
                TipoComida = "DESAYUNO"
            },
            new MenuDiario
            {
                ComidaId = "foodId2",
                NombreComida = "Food 2",
                HoraConsumo = new DateTime(2024, 6, 10, 13, 0, 0),
                CaloriasConsumidas = 108.95621144933602,
                OrdenComida = "PRIMER_PLATO",
                TipoComida = "ALMUERZO"
            },
            // Añadir más comidas según sea necesario
        }
                }
            };


            return ejemploDieta;
        }

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
            var schemaJson = schema.ToJson();
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

        private AIResponseSchemaJson? GetAssistantObjectResponseFromStr(string assistantResponseStr)
        {
            var result = assistantResponseStr.Substring(
                assistantResponseStr.IndexOf('{'), assistantResponseStr.LastIndexOf('}') + 1);

            return JsonConvert.DeserializeObject<AIResponseSchemaJson>(result);
        }

        private string BuildDietaQuery(RequestGenerarDieta details)
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
