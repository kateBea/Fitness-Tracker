using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestLoginValidator : BaseValidator<RequestLogin>
    {
        /// <summary>
        /// 
        /// </summary>
        public RequestLoginValidator()
        {
            RuleFor(data => data.Email).NotNull().WithMessage("El campo de email es obligatorio.");
            RuleFor(data => data.Password).NotNull().WithMessage("El campo de contraseña es obligatorio.");
        }
    }
}
