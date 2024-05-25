using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetDietaDeUsuarioValidator : BaseValidator<RequestGetDietaDeUsuario>
    {
        public RequestGetDietaDeUsuarioValidator()
        {
            RuleFor(x => x.Email).NotEmpty();
            RuleFor(x => x.IdDieta).NotEmpty();
        }
    }

}
