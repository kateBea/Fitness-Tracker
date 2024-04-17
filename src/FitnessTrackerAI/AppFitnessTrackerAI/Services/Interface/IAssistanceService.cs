using AppFitnessTrackerAI.Classes;

namespace AppFitnessTrackerAI.Services.Interface
{
    public interface IAssistanceService
    {
        /// <summary>
        /// For debug purposes only.
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        ModelDebug Get(int id);
    }
}
