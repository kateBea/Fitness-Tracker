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
using Newtonsoft.Json.Schema;
using Newtonsoft.Json.Schema.Generation;
using Shared.Contexts;

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
        public AssistanceService(ILogger<AssistanceService> logger, IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext)
        {
            _logger = logger;
            _authKey = configuration.GetValue<string>("OpenAIAuthKey");
            _mapper = mapper;
            _dataHttpContext = dataHttpContext;

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
            var message = BuildDietaQuery(dieta);
            var api = new OpenAI_API.OpenAIAPI();

            // Chat coompletion
            JSchemaGenerator generator = new()
            {
                // change contract resolver so property names are camel case
                ContractResolver = new CamelCasePropertyNamesContractResolver()
            };

            JSchema schema = generator.Generate(typeof(AIResponseSchemaJson));
            FileLogging.LogToFile($"JSON Schema of PlanDieta (request) ===================\n{message}");
            FileLogging.LogToFile($"JSON Schema of PlanDieta (response) ===================\n{schema}");

            var assistantResponseStr = await api.Chat.CreateChatCompletionAsync(
                $"Generar dieta con las siguientes especificaciones:\n{message}.\n" +
                $"Usa únicamente las comidas que hay en el Array preferenciasAlimenticias para esta dieta\n" +
                $"los posibles valores para orden_comida son: PRIMER_PLATO, SEGUNDO_PLATO, TERCER_PLATO, PLATO_UNICO\n" +
                $"los posibles valores para tipo_comida son: DESAYUNO, MERIENDA_MATUTINA, ALMUERZO, MERIENDA_VESPERTINA, CENA\n" +
                $"Responde solo con un JSON siguiendo el siguiente JSON Schema:\n{schema}\n");

            var assistantResponseObject = GetAssistantObjectResponseFromStr(assistantResponseStr.ToString());

            // File Log
            FileLogging.LogToFile($"Assistant answer ======================\n{assistantResponseStr}");

            return _mapper.Map<ResponseGenerarDietaVM>(assistantResponseObject);
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
            var schema = _mapper.Map<AIRequestDietaSchemaJson>(details);
            var json = JsonConvert.SerializeObject(schema, Formatting.Indented);

            result += json;

            // Results
            FileLogging.LogToFile($"AI JSON Schema Result ================================\n{result}");

            return result;
        }
    }
}
