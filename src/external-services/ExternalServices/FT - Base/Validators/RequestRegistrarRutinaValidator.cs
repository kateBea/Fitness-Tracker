using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestRegistrarRutinaValidator : BaseValidator<RequestRegistrarRutina>
    {
        /// <summary>
        /// 
        /// </summary>
        public RequestRegistrarRutinaValidator()
        {
            RuleFor(data => data.Email).NotEmpty().WithMessage("El campo de email no puede estar nulo o vació.");
        }
    }
}
