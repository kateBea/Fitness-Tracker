﻿using FluentValidation;
using FTBase.Interfaces;
using FTBase.Models;
using FTBase.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace FTBase.Controllers.v1
{
    /// <summary>
    /// Controlador base que maneja las operaciones comunes para las solicitudes de la aplicación.
    /// </summary>
    /// <param name="baseServices">Servicios base utilizados en este controlador.</param>
    /// <param name="validatorRegistrarUsuario">Validador para las solicitudes de registro de usuario.</param>
    /// <param name="validatorCambiarPassword">Validador para las solicitudes de cambio de contraseña.</param>
    /// <param name="validatorRegistrarDieta">Validador para las solicitudes de registro de dieta.</param>
    /// <param name="validatorModificarDieta">Validador para las solicitudes de modificación de dieta.</param>
    /// <param name="validatorGetDatosUsuario">Validador para las solicitudes de obtención de datos de usuario.</param>
    /// <param name="validatorModificarDatosUsuario">Validador para las solicitudes de modificación de datos de usuario.</param>
    /// <param name="validatorGetListDietasDeUsuario">Validador para las solicitudes de obtención de la lista de dietas del usuario.</param>
    /// <param name="validatorGetDietaDeUsuario">Validador para las solicitudes de obtención de una dieta específica del usuario.</param>
    /// <param name="validatorGetRutinaPorId">Validador para las solicitudes de obtención de una rutina por su ID.</param>
    /// <param name="validatorModificarRutina">Validador para las solicitudes de modificación de una rutina.</param>
    /// <param name="validatorGetListRutinasUsuario">Validador para las solicitudes de obtención de la lista de rutinas del usuario.</param>
    /// <param name="validatorRegistrarRutina">Validador para las solicitudes de registro de una rutina.</param>
    /// <param name="validatorGetAlimentos">Validador para las solicitudes de obtención de alimentos.</param>
    /// <param name="validatorLogin">Validador para las solicitudes de inicio de sesión.</param>
    [ApiController]
    [Route("[controller]")]
    public class BaseController(IBaseServices baseServices,
        IValidator<RequestRegistrarUsuario> validatorRegistrarUsuario,
        IValidator<RequestCambiarPassword> validatorCambiarPassword,
        IValidator<RequestRegistrarDieta> validatorRegistrarDieta,
        IValidator<RequestModificarDieta> validatorModificarDieta,
        IValidator<RequestGetDatosUsuario> validatorGetDatosUsuario,
        IValidator<RequestModificarDatosUsuario> validatorModificarDatosUsuario,
        IValidator<RequestGetListDietasDeUsuario> validatorGetListDietasDeUsuario,
        IValidator<RequestGetDietaDeUsuario> validatorGetDietaDeUsuario,
        IValidator<RequestGetRutinaPorId> validatorGetRutinaPorId,
        IValidator<RequestModificarRutina> validatorModificarRutina,
        IValidator<RequestGetListRutinasUsuario> validatorGetListRutinasUsuario,
        IValidator<RequestRegistrarRutina> validatorRegistrarRutina,
        IValidator<RequestGetAlimentos> validatorGetAlimentos,
        IValidator<RequestLogin> validatorLogin) : ControllerBase
    {
        #region Properties

        /// <summary>
        /// Validador para la solicitud de registrar usuario.
        /// </summary>
        private readonly IValidator<RequestRegistrarUsuario> _validatorRegistrarUsuario = validatorRegistrarUsuario;

        /// <summary>
        /// Validador para la solicitud de cambiar contraseña.
        /// </summary>
        private readonly IValidator<RequestCambiarPassword> _validatorCambiarPassword = validatorCambiarPassword;

        /// <summary>
        /// Validador para la solicitud de obtener alimentos.
        /// </summary>
        private readonly IValidator<RequestGetAlimentos> _validatorGetAlimentos = validatorGetAlimentos;

        /// <summary>
        /// Validador para la solicitud de registrar dieta.
        /// </summary>
        private readonly IValidator<RequestRegistrarDieta> _validatorRegistrarDieta = validatorRegistrarDieta;

        /// <summary>
        /// Validador para la solicitud de modificar dieta.
        /// </summary>
        private readonly IValidator<RequestModificarDieta> _validatorModificarDieta = validatorModificarDieta;

        /// <summary>
        /// Validador para la solicitud de obtener datos de usuario.
        /// </summary>
        private readonly IValidator<RequestGetDatosUsuario> _validatorGetDatosUsuario = validatorGetDatosUsuario;

        /// <summary>
        /// Validador para la solicitud de modificar datos de usuario.
        /// </summary>
        private readonly IValidator<RequestModificarDatosUsuario> _validatorModificarDatosUsuario = validatorModificarDatosUsuario;

        /// <summary>
        /// Validador para la solicitud de obtener la lista de dietas de un usuario.
        /// </summary>
        private readonly IValidator<RequestGetListDietasDeUsuario> _validatorGetListDietasDeUsuario = validatorGetListDietasDeUsuario;

        /// <summary>
        /// Validador para la solicitud de obtener una dieta de un usuario.
        /// </summary>
        private readonly IValidator<RequestGetDietaDeUsuario> _validatorGetDietaDeUsuario = validatorGetDietaDeUsuario;

        /// <summary>
        /// Validador para la solicitud de obtener una rutina por ID.
        /// </summary>
        private readonly IValidator<RequestGetRutinaPorId> _validatorGetRutinaPorId = validatorGetRutinaPorId;

        /// <summary>
        /// Validador para la solicitud de modificar una rutina.
        /// </summary>
        private readonly IValidator<RequestModificarRutina> _validatorModificarRutina = validatorModificarRutina;

        /// <summary>
        /// Validador para la solicitud de obtener la lista de rutinas de un usuario.
        /// </summary>
        private readonly IValidator<RequestGetListRutinasUsuario> _validatorGetListRutinasUsuario = validatorGetListRutinasUsuario;

        /// <summary>
        /// Validador para la solicitud de inicio de sesión.
        /// </summary>
        private readonly IValidator<RequestLogin> _validatorLogin = validatorLogin;

        /// <summary>
        /// Validador para la solicitud de registrar una rutina.
        /// </summary>
        private readonly IValidator<RequestRegistrarRutina> _validatorRegistrarRutina = validatorRegistrarRutina;

        /// <summary>
        /// Servicios base utilizados en esta clase.
        /// </summary>
        private readonly IBaseServices _baseServices = baseServices;

        #endregion


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
            var result = _validatorLogin.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.Login(model);
            return Ok(response);
        }

        /// <summary>
        /// Endpoint para registrar un nuevo usuario.
        /// </summary>
        /// <param name="model">Información de registro solicitada</param>
        /// <returns>Respuesta del modelo de vista de registro. Ver: <see cref="ResponseRegistrarUsuarioVM"/></returns>
        [HttpPost("RegistrarUsuario")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegistrarUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegistrarUsuarioVM>> RegistrarUsuario([FromBody] RequestRegistrarUsuario model)
        {
            var result = _validatorRegistrarUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.RegistrarUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Cambiar la contraseña de un usuario.
        /// </summary>
        /// <param name="model">Información para cambiar la contraseña solicitada</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseCambiarPasswordVM"/></returns>
        [HttpPut("CambiarPassword")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseCambiarPasswordVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseCambiarPasswordVM>> CambiarPassword([FromBody] RequestCambiarPassword model)
        {
            var result = _validatorCambiarPassword.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.CambiarPassword(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene los datos del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener los datos del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetDatosUsuarioVM"/></returns>
        [HttpGet("GetDatosUsuario")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetDatosUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetDatosUsuarioVM>> GetDatosUsuario([FromQuery] RequestGetDatosUsuario model)
        {
            var result = _validatorGetDatosUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetDatosUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene la dieta por ID de un usuario específico.
        /// </summary>
        /// <param name="model">La solicitud para obtener la dieta del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetDietaDeUsuarioVM"/></returns>
        [HttpGet("GetDietaDeUsuario")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetDietaDeUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetDietaDeUsuarioVM>> GetDietaDeUsuario([FromQuery] RequestGetDietaDeUsuario model)
        {
            var result = _validatorGetDietaDeUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetDietaUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene la lista de dietas del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener la lista de dietas del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRequestGetListDietasDeUsuarioVM"/></returns>
        [HttpGet("GetListDietasDeUsuario")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRequestGetListDietasDeUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRequestGetListDietasDeUsuarioVM>> GetListDietasDeUsuario([FromQuery] RequestGetListDietasDeUsuario model)
        {
            var result = _validatorGetListDietasDeUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetListDietasDeUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene los datos de la rutina de actividad física y alimentación del usuario identificada por su ID.
        /// </summary>
        /// <param name="model">Datos de solicitud para obtener la rutina.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetRutinaPorIdVM"/></returns>
        [HttpGet("GetRutinaPorId")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetRutinaPorIdVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetRutinaPorIdVM>> GetRutinaPorId([FromQuery] RequestGetRutinaPorId model)
        {
            var result = _validatorGetRutinaPorId.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetRutinaPorId(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene las rutinas de actividad física y alimentación del usuario a lo largo de un período de tiempo.
        /// </summary>
        /// <remarks>
        /// Se pueden solicitar las rutinas en un rango de fechas, si no el comportamiento por defecto
        /// es retornar todas las rutinas. Si se decide en un rango de fechas por defecto se recogen las de los últimos cinco días.
        /// </remarks>
        /// <param name="model">Datos de solicitud para obtener las rutinas del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetListRutinasUsuarioVM"/></returns>
        [HttpGet("GetListRutinasUsuario")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetListRutinasUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetListRutinasUsuarioVM>> GetListRutinasUsuario([FromQuery] RequestGetListRutinasUsuario model)
        {
            var result = _validatorGetListRutinasUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetListRutinasUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene el listado de alimentos registrados del usuario.
        /// </summary>
        /// <remarks>
        /// Retorna una lista con los alimentos que el usuario tiene registrado hasta la fecha.
        /// </remarks>
        /// <param name="model">Datos de solicitud para obtener los alimentos.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetAlimentosVM"/></returns>
        [HttpGet("GetAlimentos")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseGetAlimentosVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseGetAlimentosVM>> GetAlimentos([FromQuery] RequestGetAlimentos model)
        {
            var result = _validatorGetAlimentos.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetAlimentos(model);
            return Ok(response);
        }

        /// <summary>
        /// Registra una nueva dieta.
        /// </summary>
        /// <param name="model">Los detalles de la dieta a registrar.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRegistrarDietaVM"/></returns>
        [HttpPost("RegistrarDieta")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegistrarDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegistrarDietaVM>> RegistrarDieta([FromBody] RequestRegistrarDieta model)
        {
            var result = _validatorRegistrarDieta.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.RegistrarDieta(model);
            return Ok(response);
        }

        /// <summary>
        /// Registra una rutina de actividad física y alimentación del usuario.
        /// </summary>
        /// <param name="model">Datos de solicitud para registrar la rutina.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRegistrarRutinaVM"/></returns>
        [HttpPost("RegistrarRutina")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegistrarRutinaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegistrarRutinaVM>> RegistrarRutina([FromBody] RequestRegistrarRutina model)
        {
            var result = _validatorRegistrarRutina.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.RegistrarRutina(model);
            return Ok(response);
        }

        /// <summary>
        /// Modifica los datos del usuario.
        /// </summary>
        /// <param name="model">La solicitud para modificar los datos del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModificarDatosUsuarioVM"/></returns>
        [HttpPut("ModificarDatosUsuario")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseModificarDatosUsuarioVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseModificarDatosUsuarioVM>> ModificarDatosUsuario([FromBody] RequestModificarDatosUsuario model)
        {
            var result = _validatorModificarDatosUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.ModificarDatosUsuario(model);
            return Ok(response);
        }

        /// <summary>
        /// Modifica una nueva dieta.
        /// </summary>
        /// <param name="model">Los detalles de la dieta a modificar.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModificarDietaVM"/></returns>
        [HttpPut("ModificarDieta")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseModificarDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseModificarDietaVM>> ModificarDieta([FromBody] RequestModificarDieta model)
        {
            var result = _validatorModificarDieta.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.ModificarDieta(model);
            return Ok(response);
        }

        /// <summary>
        /// Actualiza los datos de la rutina de actividad física y alimentación del usuario identificada por su ID.
        /// </summary>
        /// <param name="model">Datos de solicitud para modificar la rutina.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModificarRutinaVM"/></returns>
        [HttpPut("ModificarRutina")]
        [Authorize]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseModificarRutinaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseModificarRutinaVM>> ModificarRutina([FromBody] RequestModificarRutina model)
        {
            var result = _validatorModificarRutina.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.ModificarRutina(model);
            return Ok(response);
        }
    }
}
