using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestRegistrarDietaValidator : BaseValidator<RequestRegistrarDieta>
    {
        public RequestRegistrarDietaValidator()
        {
            RuleFor(x => x.CaloriasTarget).GreaterThan(0);
            RuleFor(x => x.FechaInicio).NotEmpty().LessThanOrEqualTo(x => x.FechaFin);
            RuleFor(x => x.FechaFin).NotEmpty();
            RuleFor(x => x.ComidasSugeridas).NotEmpty();
            RuleFor(x => x.ConsumoDeAgua).GreaterThan(0);
        }
    }
}
