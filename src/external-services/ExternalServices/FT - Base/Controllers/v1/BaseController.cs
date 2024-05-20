﻿using FluentValidation;
using FT___Base.Interfaces;
using FT___Base.Models;
using FT___Base.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace FT___Base.Controllers.v1
{
    /// <summary>
    /// 
    /// </summary>
    /// <param name="baseServices"></param>
    /// <param name="validatorRegistrarUsuario"></param>
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
        IValidator<RequestLogin> validatorLogin) : ControllerBase
    {
        #region Properties
        private readonly IValidator<RequestRegistrarUsuario> _validatorRegistrarUsuario = validatorRegistrarUsuario;
        private readonly IValidator<RequestCambiarPassword> _validatorCambiarPassword = validatorCambiarPassword;

        private readonly IValidator<RequestRegistrarDieta> _validatorRegistrarDieta = validatorRegistrarDieta;
        private readonly IValidator<RequestModificarDieta> _validatorModificarDieta = validatorModificarDieta;
        private readonly IValidator<RequestGetDatosUsuario> _validatorGetDatosUsuario = validatorGetDatosUsuario;
        private readonly IValidator<RequestModificarDatosUsuario> _validatorModificarDatosUsuario = validatorModificarDatosUsuario;
        private readonly IValidator<RequestGetListDietasDeUsuario> _validatorGetListDietasDeUsuario = validatorGetListDietasDeUsuario;
        private readonly IValidator<RequestGetDietaDeUsuario> _validatorGetDietaDeUsuario = validatorGetDietaDeUsuario;
        private readonly IValidator<RequestGetRutinaPorId> _validatorGetRutinaPorId = validatorGetRutinaPorId;
        private readonly IValidator<RequestModificarRutina> _validatorModificarRutina = validatorModificarRutina;
        private readonly IValidator<RequestGetListRutinasUsuario> _validatorGetListRutinasUsuario = validatorGetListRutinasUsuario;
        private readonly IValidator<RequestLogin> _validatorLogin = validatorLogin;



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
        /// <returns>Respuesta del modelo de vista de registro. Ver: <see cref="ResponseRegisterVM"/></returns>
        [HttpPost("Register")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseRegisterVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseRegisterVM>> Register([FromBody] RequestRegistrarUsuario model)
        {
            var result = _validatorRegistrarUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.Register(model);
            return Ok(response);
        }

        /// <summary>
        /// Cambiar la contraseña de un usuario.
        /// </summary>
        /// <param name="model">Información para cambiar la contraseña solicitada</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseCambiarPasswordVM"/></returns>
        [HttpPut("CambiarPassword")]
        [AllowAnonymous]
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
        /// Registra una nueva dieta.
        /// </summary>
        /// <param name="model">Los detalles de la dieta a registrar.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRegistrarDietaVM"/></returns>
        [HttpPost("RegistrarDieta")]
        [AllowAnonymous]
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
        /// Modifica una nueva dieta.
        /// </summary>
        /// <param name="model">Los detalles de la dieta a modificar.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModifcarDietaVM"/></returns>
        [HttpPut("ModificarDieta")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseModifcarDietaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseModifcarDietaVM>> ModificarDieta([FromBody] RequestModificarDieta model)
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
        /// Obtiene los datos del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener los datos del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetDatosUsuarioVM"/></returns>
        [HttpGet("GetDatosUsuario")]
        [AllowAnonymous]
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
        /// Modifica los datos del usuario.
        /// </summary>
        /// <param name="model">La solicitud para modificar los datos del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModificarDatosUsuarioVM"/></returns>
        [HttpPut("ModificarDatosUsuario")]
        [AllowAnonymous]
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
        /// Obtiene la lista de dietas del usuario.
        /// </summary>
        /// <param name="model">La solicitud para obtener la lista de dietas del usuario.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseRequestGetListDietasDeUsuarioVM"/></returns>
        [HttpGet("GetListDietasDeUsuario")]
        [AllowAnonymous]
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
            var result = _validatorGetDietaDeUsuario.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.GetDietaUsuario(model);
            return Ok(response);
        }


        /// <summary>
        /// Actualiza los datos de la rutina de actividad física y alimentación del usuario identificada por su ID.
        /// </summary>
        /// <param name="model">Datos de solicitud para modificar la rutina.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseModificarRutinaVM"/></returns>
        [HttpGet("ModificarRutina")]
        [AllowAnonymous]
        [ProducesResponseType(StatusCodes.Status200OK, Type = typeof(ResponseModificarRutinaVM))]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public async Task<ActionResult<ResponseModificarRutinaVM>> ModificarRutina([FromQuery] RequestModificarRutina model)
        {
            var result = _validatorModificarRutina.Validate(model);
            if (result == null || !result.IsValid)
            {
                return BadRequest(result?.Errors);
            }

            var response = await _baseServices.ModificarRutina(model);
            return Ok(response);
        }

        /// <summary>
        /// Obtiene los datos de la rutina de actividad física y alimentación del usuario identificada por su ID.
        /// </summary>
        /// <param name="model">Datos de solicitud para obtener la rutina.</param>
        /// <returns>Respuesta del modelo de vista. Ver: <see cref="ResponseGetRutinaPorIdVM"/></returns>
        [HttpGet("GetRutinaPorId")]
        [AllowAnonymous]
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
        [AllowAnonymous]
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
    }
}
