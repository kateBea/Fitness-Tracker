using FTAI.Models;
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
using FTAI.Mappings;

namespace FTAI.Extensions
{
    public static class FTAIExtensions
    {
        public static IServiceCollection AddFitnessTrackerDependencies(this IServiceCollection services)
        {

           services.AddScoped<IAssistanceService, AssistanceService>();
           services.AddAutoMapper(typeof(MapperProfiles));

            return services;
        }

        public static IServiceCollection AddFitnessTrackerModelValidators(this IServiceCollection services)
        {
            services.AddScoped<IValidator<ModelDebug>, ModelDebugValidator>();
            services.AddScoped<IValidator<RequestDietaIn>, RequestDietaValidator>();
            services.AddScoped<IValidator<RequestChatAssistantIn>, RequestChatAssistantInValidator>();

            return services;
        }
    }
}
