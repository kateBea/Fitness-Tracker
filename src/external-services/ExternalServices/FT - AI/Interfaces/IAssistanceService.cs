using FTAI.Models;
using FTAI.ViewModels;

namespace FTAI.Interfaces
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

        /// <summary>
        /// Request dieta.
        /// </summary>
        /// <param name="dieta">The user message.</param>
        /// <returns>View model response. See: <see cref="RequestDietaVM"/></returns>
        Task<RequestDietaVM> RequestDieta(RequestDietaIn dieta);
    }
}
