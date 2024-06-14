using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestModificarDatosUsuario
    /// </summary>
    public class RequestModificarDatosUsuarioValidator : BaseValidator<RequestModificarDatosUsuario>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestModificarDatosUsuarioValidator()
        {
            RuleFor(x => x.NombreUsuario).NotEmpty().Length(3, 50);
            RuleFor(x => x.Nombre).NotEmpty().Length(1, 50);
            RuleFor(x => x.PrimerApellido).NotEmpty().Length(1, 50);
            RuleFor(x => x.SegundoApellido).Length(0, 50);
            RuleFor(x => x.FechaDeNacimiento).NotEmpty().LessThan(DateOnly.FromDateTime(DateTime.Now));
            RuleFor(x => x.Altura).GreaterThan(0);
            RuleFor(x => x.Peso).GreaterThan(0);
            RuleFor(x => x.Sexo).NotEmpty();
        }
    }

}
