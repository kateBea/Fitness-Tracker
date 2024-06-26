﻿using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Request change password validator
    /// </summary>
    public class CambiarPasswordValidator : BaseValidator<RequestCambiarPassword>
    {
        /// <summary>
        /// Default constructor
        /// </summary>
        public CambiarPasswordValidator()
        {
            RuleFor(data => data.NewPassword).NotEmpty().WithMessage("El campo de nueva contraseña es obligatorio.");
            RuleFor(data => data.OldPassword).NotEmpty().WithMessage("El campo de antigua contraseña es obligatorio.");
        }
    }
}
