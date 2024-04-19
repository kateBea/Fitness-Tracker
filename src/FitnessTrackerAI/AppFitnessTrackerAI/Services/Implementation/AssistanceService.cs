using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Services.Interface;
using AppFitnessTrackerAI.ViewModels;

namespace AppFitnessTrackerAI.Services.Implementation
{
    public class AssistanceService : IAssistanceService
    {
        public async Task<ModelDebugVM> Get(int id)
        {
            return new ModelDebugVM()
            {
                Result = "Yes"
            };
        }
    }
}
