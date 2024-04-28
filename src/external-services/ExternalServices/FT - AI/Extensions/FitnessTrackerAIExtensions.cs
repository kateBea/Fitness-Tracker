using FTAI.Classes;
using FTAI.Interfaces;
using FTAI.Services;
using FTAI.Validator;
using FluentValidation;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Extensions
{
    public static class FTAIExtensions
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
