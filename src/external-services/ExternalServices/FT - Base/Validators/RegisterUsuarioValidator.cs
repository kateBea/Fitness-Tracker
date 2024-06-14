using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Register user model validator
    /// </summary>
    public class RegisterUsuarioValidator : BaseValidator<RequestRegistrarUsuario>
    {
        /// <summary>
        /// Default constructor
        /// </summary>
        public RegisterUsuarioValidator()
        {
            RuleFor(x => x.Email)
                .NotEmpty().WithMessage("El campo email es obligatorio")
                .EmailAddress().WithMessage("El email introducido no es un email válido");

            RuleFor(x => x.Username)
                .NotEmpty().WithMessage("El campo de nombre de usuario es obligatorio")
                .Length(3, 151).WithMessage("El nombre de usuario debe tener entre 3 y 150 caracteres (ambos inclusos)");

            RuleFor(x => x.Password)
                .NotEmpty().WithMessage("El campo de nombre de usuario es obligatorio")
                .Length(3, 21).WithMessage("La contraseña debe tener entre 3 y 20 caracteres (ambos inclusos)");
        }
    }
}
