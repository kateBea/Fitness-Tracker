using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestGetDatosUsuario
    /// </summary>
    public class RequestGetDatosUsuarioValidator : BaseValidator<RequestGetDatosUsuario>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestGetDatosUsuarioValidator()
        {
        }
    }
}
