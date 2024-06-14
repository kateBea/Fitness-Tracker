using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestGetRutinaPorId
    /// </summary>
    public class RequestGetRutinaPorIdValidator : BaseValidator<RequestGetRutinaPorId>
    {
        /// <summary>
        /// Constrcutor
        /// </summary>
        public RequestGetRutinaPorIdValidator()
        {
            RuleFor(x => x.Id).NotEmpty().WithMessage("El campo ID es obligatorio.");
        }
    }
}
