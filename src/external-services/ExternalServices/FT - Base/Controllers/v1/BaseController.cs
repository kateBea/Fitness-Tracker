using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FT___Base.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class BaseController : ControllerBase
    {
        #region Properties

        #endregion

        public BaseController()
        {
            
        }

        /// <summary>
        /// Request a text message response to the GPT 4 Vision model.
        /// Has no token limit, use carfully if you do not want a large response.
        /// </summary>
        /// <param name="id">The message to be sent.</param>
        /// <returns>View model response. See: <see cref="bool"/></returns>
        [HttpGet("IsValidUser")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(bool))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<bool>> IsValidUser([FromQuery] string id)
        {

            return Ok(true);
        }
    }
}
