using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;
using System.Text.Json.Serialization;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener datos de usuario desde un servicio externo.
    /// </summary>
    public class RequestGetDatosUsuarioSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;
    }

    /// <summary>
    /// Modelo de respuesta para obtener datos de usuario desde un servicio externo.
    /// </summary>
    public class ResponseGetDatosUsuarioSvc : BaseResponseSvc
    {
        /// <summary>
        /// Datos del usuario.
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDatosUsuarioSvcOutData Data { get; set; } = new();
    }

    /// <summary>
    /// Datos de usuario obtenidos desde un servicio externo.
    /// </summary>
    public class ResponseGetDatosUsuarioSvcOutData
    {
        /// <summary>
        /// Correo electrónico del usuario.
        /// </summary>
        [JsonProperty("correo_electronico")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Nombre de usuario.
        /// </summary>
        [JsonProperty("nombre_usuario")]
        public string NombreUsuario { get; set; } = string.Empty;

        /// <summary>
        /// Nombre del usuario.
        /// </summary>
        [JsonProperty("nombre")]
        public string Nombre { get; set; } = string.Empty;

        /// <summary>
        /// URL de la imagen del usuario.
        /// </summary>
        [JsonProperty("imagen")]
        public string Imagen { get; set; } = string.Empty;

        /// <summary>
        /// Objetivo de peso del usuario.
        /// </summary>
        [JsonProperty("objetivo_peso")]
        public double ObjetivoPeso { get; set; } = 0.0;

        /// <summary>
        /// Primer apellido del usuario.
        /// </summary>
        [JsonProperty("primer_apellido")]
        public string PrimerApellido { get; set; } = string.Empty;

        /// <summary>
        /// Segundo apellido del usuario.
        /// </summary>
        [JsonProperty("segundo_apellido")]
        public string SegundoApellido { get; set; } = string.Empty;

        /// <summary>
        /// Fecha de nacimiento del usuario.
        /// </summary>
        [JsonProperty("fecha_nacimiento")]
        public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now);

        /// <summary>
        /// Fecha de registro del usuario.
        /// </summary>
        [JsonProperty("fecha_alta")]
        public DateTime FechaRegistro { get; set; } = DateTime.Now;

        /// <summary>
        /// Altura del usuario en centímetros.
        /// </summary>
        [JsonProperty("altura")]
        public double Altura { get; set; } = 0.0;

        /// <summary>
        /// Peso del usuario en kilogramos.
        /// </summary>
        [JsonProperty("peso")]
        public double Peso { get; set; } = 0.0;

        /// <summary>
        /// Sexo del usuario.
        /// </summary>
        [JsonProperty("sexo")]
        public string Sexo { get; set; } = string.Empty;
    }
}
