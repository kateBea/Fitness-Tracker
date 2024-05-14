using FTAI.Models;
using FTAI.Interfaces;
using FTAI.ViewModels;
using FluentValidation;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FTAI.Controllers.v1
{
    /// <summary>
    /// Default constructor.
    /// </summary>
    /// <param name="assistanceService"></param>
    /// <param name="validatorModelDebug"></param>
    /// <param name="validatorRequestDieta"></param>
    /// <param name="validatorRequestAssistantIn"></param>
    [ApiController]
    [Route("[controller]")]
    public class AssistanceController(IAssistanceService assistanceService,
        IValidator<ModelDebug> validatorModelDebug,
        IValidator<RequestDietaIn> validatorRequestDieta,
        IValidator<RequestChatAssistantIn> validatorRequestAssistantIn) : ControllerBase
    {
        #region Properties
        private readonly IAssistanceService _assistanceService = assistanceService;
        private readonly IValidator<ModelDebug> _validatorModelDebug = validatorModelDebug;
        private readonly IValidator<RequestDietaIn> _validatorRequestDieta = validatorRequestDieta;
        private readonly IValidator<RequestChatAssistantIn> _validatorRequestAssistantIn = validatorRequestAssistantIn;

        #endregion

        /// <summary>
        /// For debug purposes.
        /// </summary>
        /// <returns><see cref="string"/></returns>
        [HttpGet("Greeting")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(string))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ModelDebugVM>> DebugEndpoint()
        {
            return Ok("Hello there :)");
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="message">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ModelDebugVM"/></returns>
        [HttpGet("RequestAnswer")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ModelDebugVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ModelDebugVM>> RequestAnswer([FromQuery] string message)
        {
            // manage exceptions and other stuff
            var res = await _assistanceService.Get(message);

            // return result
            return Ok(res);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Is a basic completion that uses 100 tokens at most.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ModelDebugVM"/>.</returns>
        [HttpGet("BasicCompletion")]
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
            var res = await _assistanceService.BasicCompletion(model);

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

        /// <summary>
        /// Petición para iniciar una nueva conversación con el assietnte virtual.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="RequestDietaVM"/>.</returns>
        [HttpPost("StartNewChatAssistance")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(AssistantChatVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<AssistantChatVM>> StartNewChatAssistance([FromBody] RequestChatAssistantIn model)
        {
            // Validate entry model
            var result = _validatorRequestAssistantIn.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            // manage exceptions and other stuff
            var res = await _assistanceService.StartNewChat(model);

            // return result
            return Ok(res);
        }

        /// <summary>
        /// Petición para iniciar una nueva conversación con el assietnte virtual.
        /// </summary>
        /// <param name="model">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="AssistantChatVM"/>.</returns>
        [HttpPut("ContinueOngoingChatAssitance")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(AssistantChatVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<AssistantChatVM>> ContinueOngoingChatAssitance([FromBody] RequestChatAssistantIn model)
        {
            // Validate entry model
            var result = _validatorRequestAssistantIn.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            // manage exceptions and other stuff
            var res = await _assistanceService.ContinueOngoingConversation(model);

            // return result
            return Ok(res);
        }
    }
}
