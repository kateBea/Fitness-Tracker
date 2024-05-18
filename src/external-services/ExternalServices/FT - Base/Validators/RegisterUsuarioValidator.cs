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
            RuleFor(x => x.Name).NotEmpty().Length(1, 150);
            RuleFor(x => x.FirstSurname).NotEmpty().Length(1, 1250);
            RuleFor(x => x.SecondSurname).Length(0, 150);
            RuleFor(x => x.Birthday).NotEmpty().LessThan(DateTime.Now);
            RuleFor(x => x.Height).GreaterThan(0);
            RuleFor(x => x.Weight).GreaterThan(0);
            RuleFor(x => x.Sex).NotEmpty();
        }
    }
}
