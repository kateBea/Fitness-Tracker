using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    /// <summary>
    /// 
    /// </summary>
    public class CambiarPasswordValidator : BaseValidator<RequestCambiarPassword>
    {
        /// <summary>
        /// 
        /// </summary>
        public CambiarPasswordValidator()
        {
            RuleFor(data => data.Email).NotEmpty().WithMessage("El campo de correo electrónico es obligatorio.");
            RuleFor(data => data.NewPassword).NotEmpty().WithMessage("El campo de nueva contraseña es obligatorio.");
            RuleFor(data => data.OldPassword).NotEmpty().WithMessage("El campo de antigua contraseña es obligatorio.");
            RuleFor(data => data.AttemtPasswordChangeDate).NotEmpty().WithMessage("El campo de intento de cambio es obligatorio.");
        }
    }
}
