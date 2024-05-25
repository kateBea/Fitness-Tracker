using FluentValidation;
using FTAI.Interfaces;
using FTAI.Models;
using FTAI.ViewModels;
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
        IValidator<RequestMessageDebugLimitTokens> validatorModelDebug,
        IValidator<RequestGenerarDieta> validatorRequestDieta,
        IValidator<RequestStartNewChatAssistance> validatorRequestAssistantIn) : ControllerBase
    {
        #region Properties
        private readonly IAssistanceService _assistanceService = assistanceService;
        private readonly IValidator<RequestMessageDebugLimitTokens> _validatorModelDebug = validatorModelDebug;
        private readonly IValidator<RequestGenerarDieta> _validatorRequestDieta = validatorRequestDieta;
        private readonly IValidator<RequestStartNewChatAssistance> _validatorRequestAssistantIn = validatorRequestAssistantIn;

        #endregion

        /// <summary>
        /// Responde con el string "Hello there :)". Utilizado principalmente
        /// para depuración, confirmar que las peticiones llegand a esta API.
        /// </summary>
        /// <returns><see cref="string"/></returns>
        [HttpGet("Greeting")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(string))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
#pragma warning disable CS1998 // Async method lacks 'await' operators and will run synchronously
        public async Task<ActionResult<ResponseMessageDebugVM>> DebugEndpoint()
#pragma warning restore CS1998 // Async method lacks 'await' operators and will run synchronously
        {
            return Ok("Hello there :)");
        }

        /// <summary>
        /// Enviar un mensaje de texto al LLM GPT4 Vision. Sin límite
        /// de tokens, usar con precaución para evitar un consumo exagerado de
        /// la cuato de uso. Utilizado principalmente para depuración.
        /// </summary>
        /// <param name="message">El mensaje a ser enviado</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseMessageDebugVM"/></returns>
        [HttpGet("MessageDebug")]
        [Authorize] // Autorize with role admin
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseMessageDebugVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseMessageDebugVM>> MessageDebug([FromQuery] string message)
        {
            if (string.IsNullOrEmpty(message))
            {
                return BadRequest("El mensaje no puede ser vacío");
            }

            var res = await _assistanceService.Ask(message);

            return Ok(res);
        }

        /// <summary>
        /// Enviar un mensaje de texto al LLM GPT4 Vision. Con límite
        /// de tokens (300 por defecto), igualmente, usar con precaución para evitar un consumo 
        /// exagerado de la cuato de uso. Utilizado principalmente para depuración.
        /// </summary>
        /// <param name="model">Mensaje a serv enviado.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseMessageDebugVM"/>.</returns>
        [HttpGet("MessageDebugLimitTokens")]
        [Authorize] // authorize with role admin
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseMessageDebugVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseMessageDebugVM>> MessageDebugLimitTokens([FromQuery] RequestMessageDebugLimitTokens model)
        {
            var result = _validatorModelDebug.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var res = await _assistanceService.AskWithTokenLimit(model);

            return Ok(res);
        }

        /// <summary>
        /// Petición para elaborar una dieta acorde a los requisitos del usuario.
        /// Las comidas de la dieta se obtienen de los servicios de alimentos o recetas.
        /// </summary>
        /// <param name="model">Requisitos de la dieta</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGenerarDietaVM"/>.</returns>
        [HttpPost("GenerarDieta")]
        [Authorize] // authorize
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGenerarDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGenerarDietaVM>> GenerarDieta([FromBody] RequestGenerarDieta model)
        {
            var result = _validatorRequestDieta.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var res = await _assistanceService.GenerarDieta(model);

            return Ok(res);
        }

        /// <summary>
        /// Petición para iniciar una nueva conversación con el assiente virtual.
        /// </summary>
        /// <param name="model">Datos de la nueva conversación.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseStartNewChatAssistanceVM"/>.</returns>
        [HttpPut("ChatAssistance")]
        [Authorize] // authorize
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseStartNewChatAssistanceVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseStartNewChatAssistanceVM>> ChatAssistance([FromBody] RequestStartNewChatAssistance model)
        {
            var result = _validatorRequestAssistantIn.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var res = await _assistanceService.StartNewChat(model);

            return Ok(res);
        }
    }
}
