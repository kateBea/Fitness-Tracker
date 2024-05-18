using FluentValidation;
using FT___Base.Models;
using FTAI.Validator;

namespace FT___Base.Validators
{
    public class RequestModificarRutinaValidator : BaseValidator<RequestModificarRutina>
    {
        public RequestModificarRutinaValidator()
        {
            RuleFor(data => data.Id).NotEmpty();
            RuleFor(data => data.Email).NotEmpty();
        }
    }

}
