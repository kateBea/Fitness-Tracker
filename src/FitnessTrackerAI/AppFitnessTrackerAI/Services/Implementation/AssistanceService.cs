using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Services.Interface;

namespace AppFitnessTrackerAI.Services.Implementation
{
    public class AssistanceService : IAssistanceService
    {
        public ModelDebug Get(int id)
        {
            return new ModelDebug()
            {
                Name = "Yes"
            };
        }
    }
}
