using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetListRutinasUsuarioValidator : BaseValidator<RequestGetListRutinasUsuario>
    {
        public RequestGetListRutinasUsuarioValidator()
        {
            RuleFor(x => x.StartDate).NotEmpty();
            RuleFor(x => x.EndDate).NotEmpty().LessThanOrEqualTo(x => x.StartDate);
            RuleFor(x => x.RetrieveAll).NotEmpty();
        }
    }
}
