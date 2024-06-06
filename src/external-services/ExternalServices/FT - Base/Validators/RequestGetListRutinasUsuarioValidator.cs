using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetListRutinasUsuarioValidator : BaseValidator<RequestGetListRutinasUsuario>
    {
        public RequestGetListRutinasUsuarioValidator()
        {
            RuleFor(x => x.FetchAll).NotEmpty()
                .DependentRules( () =>
                {
                    When(data => data.FetchAll == false, () =>
                    {
                        RuleFor(x => x.FechaFin).NotEmpty().WithMessage("La fecha de fin no puede estar vacía si se quiere rutinas en un rango de fechas");
                        RuleFor(x => x.FechaInicio).NotEmpty().WithMessage("La fecha de inicio no puede estar vacía si se quiere rutinas en un rango de fechas")
                            .LessThanOrEqualTo(x => x.FechaFin).WithMessage("La fecha de fin debe ser superior a la de inicio");
                    });
                });

        }
    }
}
