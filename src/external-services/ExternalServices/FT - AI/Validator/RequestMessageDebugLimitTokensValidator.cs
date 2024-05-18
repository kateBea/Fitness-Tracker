using FTAI.Models;
using FluentValidation;

namespace FTAI.Validator
{
    /// <summary>
    /// Validador para el modelo de entrada de datos RequestMessageDebugLimitTokens.
    /// </summary>
    public class RequestMessageDebugLimitTokensValidator : BaseValidator<RequestMessageDebugLimitTokens>
    {
        /// <summary>
        /// Default constructor
        /// </summary>
        public RequestMessageDebugLimitTokensValidator()
        {
            RuleFor(obj =>  obj.Message).NotEmpty().WithMessage("El campo Message es obligatorio.");
            RuleFor(obj =>  obj.MaxTokens).LessThanOrEqualTo(1000).WithMessage("El límite de tokens es 1000 (inclusive).");
        }
    }
}
