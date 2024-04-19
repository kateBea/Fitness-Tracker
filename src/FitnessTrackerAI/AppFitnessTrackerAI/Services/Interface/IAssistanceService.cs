using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.ViewModels;

namespace AppFitnessTrackerAI.Services.Interface
{
    public interface IAssistanceService
    {
        /// <summary>
        /// For debug purposes only.
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        Task<ModelDebugVM> Get(int id);
    }
}
