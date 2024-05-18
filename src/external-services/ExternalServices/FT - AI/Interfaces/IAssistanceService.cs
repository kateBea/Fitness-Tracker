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
        /// <param name="message"></param>
        /// <returns></returns>
        Task<ResponseMessageDebugVM> Ask(string message);

        /// <summary>
        /// Basic chat completion, for debug purposes. Uses 100 tokens at most,
        /// so the answer might get cut if its longer. Model used is GPT 4 Turbo Vision.
        /// </summary>
        /// <param name="model">The user message.</param>
        /// <returns>View model response. See: <see cref="ResponseMessageDebugVM"/></returns>
        Task<ResponseMessageDebugVM> AskWithTokenLimit(RequestMessageDebugLimitTokens model);

        /// <summary>
        /// Request dieta.
        /// </summary>
        /// <param name="dieta">The user message.</param>
        /// <returns>View model response. See: <see cref="ResponseGenerarDietaVM"/></returns>
        Task<ResponseGenerarDietaVM> GenerarDieta(RequestGenerarDieta dieta);

        /// <summary>
        /// 
        /// </summary>
        /// <param name="chatInfo"></param>
        /// <returns></returns>
        Task<ResponseStartNewChatAssistanceVM> StartNewChat(RequestStartNewChatAssistance chatInfo);

    }
}
