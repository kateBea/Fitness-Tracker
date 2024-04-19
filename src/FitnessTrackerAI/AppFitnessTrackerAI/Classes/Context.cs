using AppFitnessTrackerAI.Interfaces;
using Microsoft.Extensions.Logging;

namespace AppFitnessTrackerAI.Classes
{
    public static class Context
    {
        public static void Init()
        {
            OpenAI_API.APIAuthentication.Default = 
                new OpenAI_API.APIAuthentication("sk-proj-lWb2nIAe3QCgghCSA8ZTT3BlbkFJp87Ob5xOGnk67uDJUHMl");
            OpenAI_API.Models.Model.DefaultChatModel = OpenAI_API.Models.Model.GPT4_Vision;
        }
    }
}
