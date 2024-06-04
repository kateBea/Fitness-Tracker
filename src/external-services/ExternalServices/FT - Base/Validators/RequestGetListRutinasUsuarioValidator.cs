using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetListRutinasUsuarioValidator : BaseValidator<RequestGetListRutinasUsuario>
    {
        public RequestGetListRutinasUsuarioValidator()
        {
            RuleFor(x => x.FetchAll).NotNull().DependentRules( () =>
            {
                When(data => data.FetchAll == false, () =>
                {
                    RuleFor(x => x.FechaInicio).NotEmpty();
                    RuleFor(x => x.FechaInicio).NotEmpty().LessThanOrEqualTo(x => x.FechaFin);
                });
            });

        }
    }
}
