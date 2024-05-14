using FTAI.Models;
using FTAI.Interfaces;
using FTAI.ViewModels;
using FTAI.Logging;
using OpenAI_API.Chat;
using OpenAI_API.Models;
using OpenAI_API.Moderation;

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

        /// <summary>
        /// 
        /// </summary>
        private readonly string? _authKey = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        private static List<UserConversation> _activeConversation = [];

        #endregion

        /// <summary>
        /// Default constructor.
        /// </summary>
        /// <param name="logger"></param>
        public AssistanceService(ILogger<AssistanceService> logger, IConfiguration configuration)
        {
            _logger = logger;
            _authKey = configuration.GetValue<string>("openAIAuthKey");

            // Init OpenAI lib
            Model.DefaultChatModel = Model.GPT4_Vision;
            OpenAI_API.APIAuthentication.Default = new OpenAI_API.APIAuthentication(_authKey);
        }


        public async Task<ModelDebugVM> BasicCompletion(ModelDebug data)
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

            return new ModelDebugVM() { Result = results.ToString() };
        }

        public async Task<ModelDebugVM> Get(string message)
        {
            // Create api
            var api = new OpenAI_API.OpenAIAPI();

            // Chat coompletion
            var result = await api.Chat.CreateChatCompletionAsync(message);

            // File Log
            FileLogging.LogToFile(result.ToString());

            // Results
            _logger.LogDebug($"Got response {result}");
            return new ModelDebugVM() { Result = result.ToString() };
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="chatInfo"></param>
        /// <returns></returns>
        public async Task<AssistantChatVM> StartNewChat(RequestChatAssistantIn chatInfo)
        {
            var result = new AssistantChatVM();
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
        /// <param name="chatInfo"></param>
        /// <returns></returns>
        public async Task<AssistantChatVM> ContinueOngoingConversation(RequestChatAssistantIn chatInfo)
        {
            var result = new AssistantChatVM();
            var api = new OpenAI_API.OpenAIAPI();

            // Comprobar primero si ya existe un chat con el usuario
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
        public async Task<RequestDietaVM> RequestDieta(RequestDietaIn dieta)
        {
            // Setup
            var message = string.Empty;

            var dietaOut = new RequestDietaVM();
            var api = new OpenAI_API.OpenAIAPI();

            // Chat coompletion
            // GPT Responde con un JSON en el formato del RequestDietaVM
            var result = await api.Chat.CreateChatCompletionAsync(message);

            // File Log
            FileLogging.LogToFile(result.ToString());

            // Results
            _logger.LogDebug($"Got response {result}");
            return new RequestDietaVM() {  };
        }
    }
}
