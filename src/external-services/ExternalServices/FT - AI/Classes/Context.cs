using FTAI.Interfaces;
using Microsoft.Extensions.Logging;

namespace FTAI.Classes
{
    public static class Context
    {
        /// <summary>
        /// Initializes the OpenAI-API-dotnet library, setting up the API KEY and
        /// the default language model.
        /// </summary>
        public static void Init()
        {
            OpenAI_API.APIAuthentication.Default = 
                new OpenAI_API.APIAuthentication("sk-proj-lWb2nIAe3QCgghCSA8ZTT3BlbkFJp87Ob5xOGnk67uDJUHMl");
            OpenAI_API.Models.Model.DefaultChatModel = OpenAI_API.Models.Model.GPT4_Vision;
        }
    }
}
