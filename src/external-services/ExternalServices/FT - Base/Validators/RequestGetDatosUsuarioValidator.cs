using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetDatosUsuarioValidator : BaseValidator<RequestGetDatosUsuario>
    {
        public RequestGetDatosUsuarioValidator()
        {
            RuleFor(x => x.Id).NotEmpty();
        }
    }
}
