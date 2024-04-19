using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Interfaces;
using AppFitnessTrackerAI.ViewModels;
using FluentValidation;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FitnessTrackerAI.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class AssistanceController : ControllerBase
    {
        #region Properties
        private readonly IAssistanceService _assistanceService;
        private readonly IValidator<ModelDebug> _validatorModelDebug;
        #endregion

        public AssistanceController(IAssistanceService assistanceService, IValidator<ModelDebug> validatorModelDebug)
        {
            _assistanceService = assistanceService;
            _validatorModelDebug = validatorModelDebug;
        }

        [HttpPost("DebugEndpoint")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ModelDebugVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ModelDebugVM>> DebugEndpoint([FromBody] ModelDebug model)
        {
            // Validate entry model
            var result = _validatorModelDebug.Validate(model);
            if (result == null || !result.IsValid) 
            {
                return BadRequest(result?.Errors);
            }

            // manage exceptions and other stuff
            var res = await _assistanceService.Get(model.Message);

            // return result
            return Ok(res);
        }
    }
}
