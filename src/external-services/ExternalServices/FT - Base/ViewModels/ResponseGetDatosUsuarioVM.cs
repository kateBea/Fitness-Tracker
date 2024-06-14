using FTBase.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Get datos usuario response model
    /// </summary>
    public class ResponseGetDatosUsuarioVM : BaseResponse
    {
        /// <summary>
        /// Datos usuario view model data
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDatosUsuarioVMData? Data { get; set; } = new();

        /// <summary>
        /// Get datos usuario view model data
        /// </summary>
        public class ResponseGetDatosUsuarioVMData
        {
            /// <summary>
            /// Nickname del usuario
            /// </summary>
            public string NombreUsuario { get; set; } = string.Empty;

            /// <summary>
            /// Nombre del usuario
            /// </summary>
            public string Nombre { get; set; } = string.Empty;

            /// <summary>
            /// Imagen de perfil del usuario
            /// </summary>
            public string Imagen { get; set; } = string.Empty;

            /// <summary>
            /// Peso objetivo
            /// </summary>
            public double ObjetivoPeso { get; set; } = 0.0;

            /// <summary>
            /// Primer apellido del usuario
            /// </summary>
            public string PrimerApellido { get; set; } = string.Empty;

            /// <summary>
            /// Segundo apellido del usuario
            /// </summary>
            public string SegundoApellido { get; set; } = string.Empty;

            /// <summary>
            /// Fecha de nacimiento del usuario
            /// </summary>
            public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now);

            /// <summary>
            /// Fecha de alta del usuario
            /// </summary>
            public DateTime FechaRegistro { get; set; } = DateTime.Now;

            /// <summary>
            /// Altura en centímetros
            /// </summary>
            public double Altura { get; set; } = 0.0;

            /// <summary>
            /// Peso en kilogramos
            /// </summary>
            public double Peso { get; set; } = 0.0;

            /// <summary>
            /// Sexo del usuario
            /// </summary>
            public string Sexo { get; set; } = string.Empty;
        }
    }
}
