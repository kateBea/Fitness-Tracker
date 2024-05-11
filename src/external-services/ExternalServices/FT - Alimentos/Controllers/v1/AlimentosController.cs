using FTAlimentos.Interfaces;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.Services;
using FTAlimentos.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FTAlimentos.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class AlimentosController : ControllerBase
    {
        private readonly IAlimentosService _alimentosService;

        public AlimentosController(IAlimentosService alimentosService)
        {
            _alimentosService = alimentosService;
        }

        #region Edadam Responses
        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="prompt">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="FoodParseVM"/></returns>
        [HttpGet("ParseSearch")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(FoodParseVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<FoodParseVM>> ParseSearch([FromQuery] string prompt)
        {
            var parseResult = await _alimentosService.Parse(prompt);

            return Ok(parseResult);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="ingredient">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="NutrientsVM"/></returns>
        [HttpPost("Nutrients")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(NutrientsVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<NutrientsVM>> Nutrients([FromQuery] string foodId)
        {
            var parseResult = await _alimentosService.Nutrients(null);

            return Ok(parseResult);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="prompt">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="AutocompleteVM"/></returns>
        [HttpGet("Autocomplete")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(AutocompleteVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<AutocompleteVM>> Autocomplete([FromQuery] string prompt)
        {
            var parseResult = await _alimentosService.Autocomplete(prompt);

            return Ok(parseResult);
        }
        #endregion
    }
}
