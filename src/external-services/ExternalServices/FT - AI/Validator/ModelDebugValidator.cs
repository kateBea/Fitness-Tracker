using FTAI.Classes;
using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Validator
{
    public class ModelDebugValidator : BaseValidator<ModelDebug>
    {
        public ModelDebugValidator()
        {
            RuleFor(obj =>  obj.Message).NotEmpty().WithMessage("The field name is required.");
        }
    }
}
