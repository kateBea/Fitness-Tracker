using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestModificarDatosUsuarioValidator : BaseValidator<RequestModificarDatosUsuario>
    {
        public RequestModificarDatosUsuarioValidator()
        {
            RuleFor(x => x.Email).NotEmpty().EmailAddress();
            RuleFor(x => x.NombreUsuario).NotEmpty().Length(3, 50);
            RuleFor(x => x.Nombre).NotEmpty().Length(1, 50);
            RuleFor(x => x.PrimerApellido).NotEmpty().Length(1, 50);
            RuleFor(x => x.SegundoApellido).Length(0, 50);
            RuleFor(x => x.FechaNacimiento).NotEmpty().LessThan(DateTime.Now);
            RuleFor(x => x.Altura).GreaterThan(0);
            RuleFor(x => x.Peso).GreaterThan(0);
            RuleFor(x => x.Sexo).NotEmpty();
        }
    }

}
