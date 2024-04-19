using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Services.Interface;
using AppFitnessTrackerAI.ViewModels;
using Microsoft.Extensions.Logging;
using OpenAI_API;
using OpenAI_API.Models;

namespace AppFitnessTrackerAI.Services.Implementation
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
        /// Default constructor for this service.
        /// </summary>
        /// <param name="logger"></param>
        public AssistanceService(ILogger logger)
        {
            _logger = logger;
        }

        public async Task<ModelDebugVM> Get(string message)
        {
            var api = new OpenAIAPI("sk-proj-lWb2nIAe3QCgghCSA8ZTT3BlbkFJp87Ob5xOGnk67uDJUHMl");
            var result = await api.Chat.CreateChatCompletionAsync(message);

            _logger.LogDebug($"Got response {result}");
            return new ModelDebugVM()
            {
                Result = result.ToString(),
            };
        }
    }
}
