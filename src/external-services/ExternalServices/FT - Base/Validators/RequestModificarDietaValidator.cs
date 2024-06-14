using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestModificarDieta
    /// </summary>
    public class RequestModificarDietaValidator : BaseValidator<RequestModificarDieta>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestModificarDietaValidator()
        {
            RuleFor(x => x.FechaInicio).NotEmpty().LessThanOrEqualTo(x => x.FechaFin);
            RuleFor(x => x.FechaFin).NotEmpty();
        }
    }
}
