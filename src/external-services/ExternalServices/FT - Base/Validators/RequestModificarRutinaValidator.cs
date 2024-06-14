using FluentValidation;
using FTBase.Models;
using FTAI.Validator;

namespace FTBase.Validators
{
    /// <summary>
    /// Validador para RequestModificarRutina
    /// </summary>
    public class RequestModificarRutinaValidator : BaseValidator<RequestModificarRutina>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public RequestModificarRutinaValidator()
        {
            RuleFor(data => data.Id).NotEmpty();
        }
    }

}
