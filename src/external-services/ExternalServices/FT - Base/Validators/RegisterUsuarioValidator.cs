using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RegisterUsuarioValidator : BaseValidator<RequestRegistrarUsuario>
    {
        public RegisterUsuarioValidator()
        {
            RuleFor(x => x.Email).NotEmpty().EmailAddress();
            RuleFor(x => x.Username).NotEmpty().Length(3, 150);
            RuleFor(x => x.Password).NotEmpty().Length(3, 150);
        }
    }
}
