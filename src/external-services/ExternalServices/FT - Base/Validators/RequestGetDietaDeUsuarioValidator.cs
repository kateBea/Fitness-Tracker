using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestGetDietaDeUsuario
    /// </summary>
    public class RequestGetDietaDeUsuarioValidator : BaseValidator<RequestGetDietaDeUsuario>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestGetDietaDeUsuarioValidator()
        {
            RuleFor(x => x.Id).NotEmpty();
        }
    }

}
