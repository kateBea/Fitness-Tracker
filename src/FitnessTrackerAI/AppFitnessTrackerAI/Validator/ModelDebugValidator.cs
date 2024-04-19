using AppFitnessTrackerAI.Classes;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppFitnessTrackerAI.Validator
{
    public class ModelDebugValidator : BaseValidator<ModelDebug>
    {
        public ModelDebugValidator()
        {
            RuleFor(obj =>  obj.Name).NotEmpty().WithMessage("The field name is required.");
        }
    }
}
