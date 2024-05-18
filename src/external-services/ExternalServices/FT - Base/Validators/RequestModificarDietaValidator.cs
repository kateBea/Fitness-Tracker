using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestModificarDietaValidator : BaseValidator<RequestModificarDieta>
    {
        public RequestModificarDietaValidator()
        {
            RuleFor(x => x.Id).NotEmpty();
            RuleFor(x => x.TargetCalories).GreaterThan(0);
            RuleFor(x => x.FechaInicio).NotEmpty().LessThanOrEqualTo(x => x.FechaFin);
            RuleFor(x => x.FechaFin).NotEmpty();
            RuleFor(x => x.ComidasSugeridas).NotEmpty();
            RuleFor(x => x.ConsumoAgua).GreaterThan(0);
        }
    }
}
