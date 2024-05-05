using FTAI.Models;
using FTAI.Interfaces;
using FTAI.ViewModels;
using FluentValidation;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FTAI.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class AssistanceController : ControllerBase
    {
        #region Properties
        private readonly IAssistanceService _assistanceService;
        private readonly IValidator<ModelDebug> _validatorModelDebug;
        private readonly IValidator<RequestDietaIn> _validatorRequestDieta;
        #endregion

        /// <summary>
        /// Default constructor.
        /// </summary>
        /// <param name="assistanceService"></param>
        /// <param name="validatorModelDebug"></param>
        public AssistanceController(IAssistanceService assistanceService, 
            IValidator<ModelDebug> validatorModelDebug,
            IValidator<RequestDietaIn> validatorRequestDieta)
        {
            _assistanceService = assistanceService;
            _validatorModelDebug = validatorModelDebug;
            _validatorRequestDieta = validatorRequestDieta;
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ModelDebugVM"/></returns>
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

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Is a basic completion that uses 100 tokens at most.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ModelDebugVM"/>.</returns>
        [HttpPost("BasicCompletion")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ModelDebugVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ModelDebugVM>> BasicChatCompletion([FromBody] ModelDebug model)
        {
            // Validate entry model
            var result = _validatorModelDebug.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            // manage exceptions and other stuff
            var res = await _assistanceService.BasicCompletion(model.Message);

            // return result
            return Ok(res);
        }

        /// <summary>
        /// Petición para elaborar una dieta acorde a los requisitos del usuario.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="RequestDietaVM"/>.</returns>
        [HttpPost("RequestDieta")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(RequestDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<RequestDietaVM>> RequestDieta([FromBody] RequestDietaIn model)
        {
            // Validate entry model
            var result = _validatorRequestDieta.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            // manage exceptions and other stuff
            var res = await _assistanceService.RequestDieta(model);

            // return result
            return Ok(res);
        }
    }
}
