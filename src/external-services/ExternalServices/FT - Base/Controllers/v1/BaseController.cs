using FT___Base.Interfaces;
using FT___Base.Models;
using FT___Base.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FT___Base.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class BaseController : ControllerBase
    {
        #region Properties

        private readonly IBaseServices _baseServices;

        #endregion

        public BaseController(IBaseServices baseServices)
        {
            _baseServices = baseServices;
        }

        /// <summary>
        /// Endpoint para solicitar iniciar sesión.
        /// </summary>
        /// <param name="model">Información de inicio de sesión solicitada</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseLoginVM"/></returns>
        [HttpPost("Login")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseLoginVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseLoginVM>> Login([FromBody] RequestLogin model)
        {
            var result = await _baseServices.Login(model);
            return Ok(result);
        }

        /// <summary>
        /// Endpoint para registrar un nuevo usuario.
        /// </summary>
        /// <param name="model">Información de registro solicitada</param>
        /// <returns>Respuesta del modelo de vista de registro. Ver: <see cref="ResponseRegisterVM"/></returns>
        [HttpPost("Register")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegisterVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegisterVM>> Register([FromBody] RequestRegister model)
        {
            var result = await _baseServices.Register(model);
            return Ok(result);
        }

        /// <summary>
        /// Endpoint para cambiar la contraseña de un usuario.
        /// </summary>
        /// <param name="model">Información para cambiar la contraseña solicitada</param>
        /// <returns>Respuesta del modelo de vista de cambio de contraseña. Ver: <see cref="ResponseCambiasPasswordVM"/></returns>
        [HttpPut("CambiarPassword")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseCambiasPasswordVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseCambiasPasswordVM>> CambiarPassword([FromBody] RequestCambiasPassword model)
        {
            var result = await _baseServices.CambiarPassword(model);
            return Ok(result);
        }

        /// <summary>
        /// Registra una nueva dieta.
        /// </summary>
        /// <param name="model">Los detalles de la dieta a registrar.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRegistrarDietaVM"/></returns>
        [HttpPost("RegistrarDieta")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegistrarDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegistrarDietaVM>> RegistrarDieta([FromBody] RquestRegistrarDieta model)
        {
            var result = await _baseServices.RegistrarDieta(model);
            return Ok(result);
        }

        /// <summary>
        /// Obtiene los datos del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener los datos del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetDatosUsuario"/></returns>
        [HttpGet("GetDatosUsuario")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetDatosUsuario))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetDatosUsuario>> GetDatosUsuario([FromBody] RequestGetDatosUsuario model)
        {
            var result = await _baseServices.GetDatosUsuario(model);
            return Ok(result);
        }

        /// <summary>
        /// Obtiene la lista de dietas del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener la lista de dietas del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRequestGetListDietasDeUsuarioVM"/></returns>
        [HttpGet("GetListDietasDeUsuario")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRequestGetListDietasDeUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRequestGetListDietasDeUsuarioVM>> GetListDietasDeUsuario([FromBody] RequestGetListDietasDeUsuario model)
        {
            var result = await _baseServices.GetListDietasDeUsuario(model);
            return Ok(result);
        }

        /// <summary>
        /// Obtiene la dieta por ID de un usuario específico.
        /// </summary>
        /// <param name="model">La solicitud para obtener la dieta del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetDietaDeUsuarioVM"/></returns>
        [HttpGet("GetDietaDeUsuario")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetDietaDeUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetDietaDeUsuarioVM>> GetDietaDeUsuario([FromQuery] RequestGetDietaDeUsuario model)
        {
            var result = await _baseServices.GetDietaDeUsuario(model);
            return Ok(result);
        }

        /// <summary>
        /// Obtiene los datos de la rutina de actividad física y alimentación del usuario identificada por su ID.
        /// </summary>
        /// <param name="model">Datos de solicitud para obtener la rutina por su ID.</param>
        /// <returns>Respuesta del modelo de vista que contiene los detalles de la rutina. Ver: <see cref="ResponseGetRutinaPorIdVM"/></returns>
        [HttpGet("GetRutinaPorId")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(bool))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetRutinaPorIdVM>> GetRutinaPorId([FromBody] RequestGetRutinaPorId model)
        {
            var result = await _baseServices.GetRutinaPorId(model);
            return Ok(result);
        }

        /// <summary>
        /// Obtiene las rutinas de actividad física y alimentación del usuario a lo largo de un período de tiempo.
        /// </summary>
        /// <remarks>
        /// Se pueden solicitar las rutinas en un rango de fechas, si no el comportamiento por defecto
        /// es retornar todas las rutinas. Si se decide en un rango de fechas por defecto se recogen las de los últimos cinco días.
        /// </remarks>
        /// <param name="model">Datos de solicitud para obtener las rutinas del usuario.</param>
        /// <returns>Respuesta del modelo de vista que contiene las rutinas del usuario. Ver: <see cref="ResponseGetListRutinasUsuarioVM"/></returns>
        [HttpGet("GetListRutinasUsuario")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetListRutinasUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetListRutinasUsuarioVM>> GetListRutinasUsuario([FromBody] RequestGetListRutinasUsuario model)
        {

            var result = await _baseServices.GetListRutinasUsuario(model);
            
            return Ok(result);
        }
    }
}
