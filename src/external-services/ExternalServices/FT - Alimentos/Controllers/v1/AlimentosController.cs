using FTAlimentos.Interfaces;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.Services;
using FTAlimentos.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FTAlimentos.Controllers.v1
{
    /// <summary>
    /// 
    /// </summary>
    /// <param name="alimentosService"></param>
    [ApiController]
    [Route("[controller]")]
    public class AlimentosController(IAlimentosService alimentosService) : ControllerBase
    {
        #region Properties

        private readonly IAlimentosService _alimentosService = alimentosService;

        #endregion

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="prompt">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ResponseFoodParseVM"/></returns>
        [HttpGet("BuscarPorDescripcion")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseFoodParseVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseFoodParseVM>> BuscarPorDescripcion([FromQuery] string prompt)
        {
            var parseResult = await _alimentosService.Parse(prompt);

            return Ok(parseResult);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="foodId">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ResponseNutrientsVM"/></returns>
        [HttpGet("GetNutrients")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseNutrientsVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseNutrientsVM>> GetNutrients([FromQuery] string foodId)
        {
            var parseResult = await _alimentosService.GetNutrients(foodId);

            return Ok(parseResult);
        }

        /// <summary>
        ///Funcionalidad de autocompletado que se puede utilizar en conjunto con la
        ///búsqueda por descripción para localizar alimentos.
        /// </summary>
        /// <param name="prompt">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="ResponseAutocompleteVM"/></returns>
        [HttpGet("Autocomplete")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseAutocompleteVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseAutocompleteVM>> Autocomplete([FromQuery] string prompt)
        {
            if (string.IsNullOrEmpty(prompt))
            {
                return BadRequest("Este campo es obligatorio.");
            }

            var parseResult = await _alimentosService.Autocomplete(prompt);

            return Ok(parseResult);
        }
    }
}
