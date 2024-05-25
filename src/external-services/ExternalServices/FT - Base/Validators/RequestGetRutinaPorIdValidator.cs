using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestGetRutinaPorIdValidator : BaseValidator<RequestGetRutinaPorId>
    {
        public RequestGetRutinaPorIdValidator()
        {
            RuleFor(x => x.Email).NotEmpty();
            RuleFor(x => x.IdRutina).NotEmpty();
        }
    }
}
