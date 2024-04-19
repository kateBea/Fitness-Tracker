using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Services.Implementation;
using AppFitnessTrackerAI.Services.Interface;
using AppFitnessTrackerAI.Validator;
using FluentValidation;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppFitnessTrackerAI.Extensions
{
    public static class FitnessTrackerAIExtensions
    {
        public static IServiceCollection AddFitnessTrackerDependencies(this IServiceCollection services)
        {

            var result = services.AddScoped<IAssistanceService, AssistanceService>();

            return services;
        }

        public static IServiceCollection AddFitnessTrackerModelValidators(this IServiceCollection services)
        {
            var result = services.AddScoped<IValidator<ModelDebug>, ModelDebugValidator>();

            return services;
        }
    }
}
