using FTAlimentos.Interfaces;
using FTAlimentos.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FTAlimentos.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class RecetasController : Controller
    {
        #region Properties

        private readonly IRecetasService _recetasService;

        #endregion

        public RecetasController(IRecetasService recetasService)
        {
            _recetasService = recetasService;
        }

        #region Edadam Responses

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="criteria">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="SearchRecipeVM"/></returns>
        [HttpGet("GetRecipeByCriteria")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(SearchRecipeVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<SearchRecipeVM>> GetRecipeByCriteria([FromQuery] string criteria)
        {
            var result = await _recetasService.GetRecipeByCriteria(criteria);
            return Ok(result);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="id">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="SearchRecipeIDVM"/></returns>
        [HttpGet("GetRecipeById")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(SearchRecipeIDVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<SearchRecipeIDVM>> GetRecipeById([FromQuery] string id)
        {
            var result = await _recetasService.GetRecipeByID(id);
            return Ok(result);
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="uri">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="SearchRecipeUriVM"/></returns>
        [HttpGet("GetRecipeByUri")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(SearchRecipeVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<SearchRecipeUriVM>> GetRecipeByUri([FromQuery] string uri)
        {
            var result = await _recetasService.GetRecipeByUri(uri);
            return Ok(result);
        }

        #endregion
    }
}
