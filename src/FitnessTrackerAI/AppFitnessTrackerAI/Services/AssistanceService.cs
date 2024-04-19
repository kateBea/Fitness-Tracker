using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Interfaces;
using AppFitnessTrackerAI.ViewModels;
using Microsoft.Extensions.Logging;

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
        /// 
        /// </summary>
        /// <param name="logger"></param>
        public AssistanceService(ILogger<AssistanceService> logger)
        {
            _logger = logger;
        }

        public async Task<ModelDebugVM> Get(string message)
        {
            var api = new OpenAI_API.OpenAIAPI();
            var result = await api.Chat.CreateChatCompletionAsync(message);

            _logger.LogDebug($"Got response {result}");
            return new ModelDebugVM()
            {
                Result = result.ToString(),
            };
        }
    }
}
