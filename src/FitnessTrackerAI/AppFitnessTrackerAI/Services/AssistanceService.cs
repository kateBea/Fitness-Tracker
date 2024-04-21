using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Interfaces;
using AppFitnessTrackerAI.ViewModels;
using Microsoft.Extensions.Logging;
using OpenAI_API.Chat;
using OpenAI_API.Models;
using OpenAI_API.Moderation;

namespace AppFitnessTrackerAI.Services
{
    public class AssistanceService : IAssistanceService
    {
        #region Properties
        /// <summary>
        /// Logger object for this service
        /// </summary>
        private readonly ILogger _logger;

        #endregion

        /// <summary>
        /// Default constructor.
        /// </summary>
        /// <param name="logger"></param>
        public AssistanceService(ILogger<AssistanceService> logger)
        {
            _logger = logger;
        }


        public async Task<ModelDebugVM> BasicCompletion(string message)
        {
            // Setup
            var api = new OpenAI_API.OpenAIAPI();

            var model = Model.GPT4_Vision;  // model type
            var temperature = 0.1;          // model risks
            var tokensLimit = 20;          // words limit

            // Query
            var results = await api.Chat.CreateChatCompletionAsync(new ChatRequest()
            {
                Model = model,
                Temperature = temperature,
                MaxTokens = tokensLimit,
                Messages = new List<ChatMessage> {
                    new ChatMessage(ChatMessageRole.User, message)
                }
            });

            // Results debug
            _logger.LogDebug($"Got response: {results}");
            _logger.LogDebug($"For message: {message}");
            _logger.LogDebug($"From model: {results.Model}");
            _logger.LogDebug($"With max tokens: {tokensLimit}");

            return new ModelDebugVM() { Result = results.ToString() };
        }

        public async Task<ModelDebugVM> Get(string message)
        {
            // Create api
            var api = new OpenAI_API.OpenAIAPI();

            // Chat coompletion
            var result = await api.Chat.CreateChatCompletionAsync(message);

            // Results
            _logger.LogDebug($"Got response {result}");
            return new ModelDebugVM() { Result = result.ToString() };
        }
    }
}
