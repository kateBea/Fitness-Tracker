using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
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
