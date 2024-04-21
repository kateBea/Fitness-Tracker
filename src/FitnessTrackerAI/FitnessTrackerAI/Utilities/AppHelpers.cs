using AppFitnessTrackerAI.Classes;
using Microsoft.AspNetCore.Mvc;

namespace FitnessTrackerAI.Utilities
{
    public static class AppHelpers
    {
        public static WebApplication SetupEnvironment(this WebApplication webApplication)
        {
            // Configure the HTTP request pipeline.
            if (webApplication.Environment.IsDevelopment())
            {
                webApplication.UseSwagger();
                webApplication.UseSwaggerUI();
            }

            // Init OpenAI API Context
            Context.Init();

            return webApplication;
        }
    }
}
