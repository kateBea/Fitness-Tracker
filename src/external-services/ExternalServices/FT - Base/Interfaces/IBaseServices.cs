using FTBase.Models;
using FTBase.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace FTBase.Interfaces
{
    /// <summary>
    /// Interfaz para los servicios base que proporciona funcionalidades comunes a la aplicación.
    /// </summary>
    public interface IBaseServices
    {
        /// <summary>
        /// Inicia sesión un usuario con las credenciales proporcionadas.
        /// </summary>
        /// <param name="model">Solicitud que contiene las credenciales de inicio de sesión.</param>
        /// <returns>Respuesta que contiene el resultado del inicio de sesión.</returns>
        public Task<ResponseLoginVM> Login(RequestLogin model);

        /// <summary>
        /// Registra un nuevo usuario con la información proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información del usuario a registrar.</param>
        /// <returns>Respuesta que contiene el resultado del registro de usuario.</returns>
        public Task<ResponseRegistrarUsuarioVM> RegistrarUsuario(RequestRegistrarUsuario model);

        /// <summary>
        /// Cambia la contraseña de un usuario.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información necesaria para cambiar la contraseña.</param>
        /// <returns>Respuesta que contiene el resultado del cambio de contraseña.</returns>
        public Task<ResponseCambiarPasswordVM> CambiarPassword(RequestCambiarPassword model);

        /// <summary>
        /// Registra una nueva dieta con la información proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la dieta a registrar.</param>
        /// <returns>Respuesta que contiene el resultado del registro de la dieta.</returns>
        public Task<ResponseRegistrarDietaVM> RegistrarDieta(RequestRegistrarDieta model);

        /// <summary>
        /// Modifica una dieta existente con la información proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la dieta a modificar.</param>
        /// <returns>Respuesta que contiene el resultado de la modificación de la dieta.</returns>
        public Task<ResponseModificarDietaVM> ModificarDieta(RequestModificarDieta model);

        /// <summary>
        /// Obtiene los datos de un usuario específico.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información del usuario para obtener sus datos.</param>
        /// <returns>Respuesta que contiene los datos del usuario.</returns>
        public Task<ResponseGetDatosUsuarioVM> GetDatosUsuario(RequestGetDatosUsuario model);

        /// <summary>
        /// Modifica los datos de un usuario existente.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información del usuario a modificar.</param>
        /// <returns>Respuesta que contiene el resultado de la modificación de los datos del usuario.</returns>
        public Task<ResponseModificarDatosUsuarioVM> ModificarDatosUsuario(RequestModificarDatosUsuario model);

        /// <summary>
        /// Obtiene la lista de dietas de un usuario específico.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información del usuario para obtener sus dietas.</param>
        /// <returns>Respuesta que contiene la lista de dietas del usuario.</returns>
        public Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model);

        /// <summary>
        /// Obtiene una dieta específica de un usuario.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la dieta a obtener.</param>
        /// <returns>Respuesta que contiene la dieta del usuario.</returns>
        public Task<ResponseGetDietaDeUsuarioVM> GetDietaUsuario(RequestGetDietaDeUsuario model);

        /// <summary>
        /// Obtiene una rutina específica por su ID.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la rutina a obtener.</param>
        /// <returns>Respuesta que contiene la rutina solicitada.</returns>
        public Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model);

        /// <summary>
        /// Modifica una rutina existente con la información proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la rutina a modificar.</param>
        /// <returns>Respuesta que contiene el resultado de la modificación de la rutina.</returns>
        public Task<ResponseModificarRutinaVM> ModificarRutina(RequestModificarRutina model);

        /// <summary>
        /// Obtiene la lista de rutinas de un usuario específico.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información del usuario para obtener sus rutinas.</param>
        /// <returns>Respuesta que contiene la lista de rutinas del usuario.</returns>
        public Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model);

        /// <summary>
        /// Obtiene la lista de alimentos basados en la solicitud proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene los criterios para obtener los alimentos.</param>
        /// <returns>Respuesta que contiene la lista de alimentos.</returns>
        public Task<ResponseGetAlimentosVM> GetAlimentos(RequestGetAlimentos model);

        /// <summary>
        /// Registra una nueva rutina con la información proporcionada.
        /// </summary>
        /// <param name="model">Solicitud que contiene la información de la rutina a registrar.</param>
        /// <returns>Respuesta que contiene el resultado del registro de la rutina.</returns>
        public Task<ResponseRegistrarRutinaVM> RegistrarRutina(RequestRegistrarRutina model);
    }
}
