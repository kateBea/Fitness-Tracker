using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    /// <summary>
    /// Loging request model validator
    /// </summary>
    public class RequestLoginValidator : BaseValidator<RequestLogin>
    {
        /// <summary>
        /// Default constructor
        /// </summary>
        public RequestLoginValidator()
        {
            RuleFor(data => data.Email).NotEmpty().WithMessage("El campo de email es obligatorio.");
            RuleFor(data => data.Password).NotEmpty().WithMessage("El campo de contraseña es obligatorio.");
        }
    }
}
