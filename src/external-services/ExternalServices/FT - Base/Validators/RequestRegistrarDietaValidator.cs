using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestRegistrarDieta
    /// </summary>
    public class RequestRegistrarDietaValidator : BaseValidator<RequestRegistrarDieta>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestRegistrarDietaValidator()
        {
            RuleFor(x => x.CaloriasTarget).GreaterThan(0);
            RuleFor(x => x.FechaInicio).NotEmpty().LessThanOrEqualTo(x => x.FechaFin);
            RuleFor(x => x.FechaFin).NotEmpty();
            RuleFor(x => x.ConsumoDeAgua).GreaterThan(0);
        }
    }
}
