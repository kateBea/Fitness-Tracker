using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetListRutinasUsuarioValidator : BaseValidator<RequestGetListRutinasUsuario>
    {
        public RequestGetListRutinasUsuarioValidator()
        {
            RuleFor(x => x.FetchAll).NotEmpty().DependentRules( () =>
            {
                When(data => data.FetchAll == false, () =>
                {
                    RuleFor(x => x.FechaInicio).NotEmpty();
                    RuleFor(x => x.FechaFin).NotEmpty().LessThanOrEqualTo(x => x.FechaInicio);
                });
            });

        }
    }
}
