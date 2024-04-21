using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.ViewModels;

namespace AppFitnessTrackerAI.Interfaces
{
    public interface IAssistanceService
    {
        /// <summary>
        /// For debug purposes only. Uses default settings to 
        /// request respponses to the OpenAI endpoints.
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        Task<ModelDebugVM> Get(string message);

        /// <summary>
        /// Basic chat completion, for debug purposes. Uses 100 tokens at most,
        /// so the answer might get cut if its longer. Model used is GPT 4 Turbo Vision.
        /// </summary>
        /// <param name="message">The user message.</param>
        /// <returns>View model response. See: <see cref="ModelDebugVM"/></returns>
        Task<ModelDebugVM> BasicCompletion(string message);
    }
}
