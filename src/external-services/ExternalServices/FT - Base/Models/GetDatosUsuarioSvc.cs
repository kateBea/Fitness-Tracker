using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// Get datos usuario external service request model
    /// </summary>
    public class RequestGetDatosUsuarioSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;
    }

    /// <summary>
    /// Get datos usuario external service response model
    /// </summary>
    public class ResponseGetDatosUsuarioSvcOut : BaseResponseSvc
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDatosUsuarioSvcOutData Data { get; set; } = new();
    }

    /// <summary>
    /// 
    /// </summary>
    public class ResponseGetDatosUsuarioSvcOutData
    {
        [JsonProperty("correo_electronico")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("nombre_usuario")]
        public string NombreUsuario { get; set; } = string.Empty;

        [JsonProperty("nombre")]
        public string Nombre { get; set; } = string.Empty;

        [JsonProperty("primer_apellido")]
        public string PrimerApellido { get; set; } = string.Empty;

        [JsonProperty("segundo_apellido")]
        public string SegundoApellido { get; set; } = string.Empty;

        [JsonProperty("fecha_nacimiento")]
        public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now);

        [JsonProperty("fecha_alta")]
        public DateTime FechaRegistro { get; set; } = DateTime.Now;

        // En centímetros
        [JsonProperty("altura")]
        public double Altura { get; set; } = 0.0;

        // En kilogramos
        [JsonProperty("peso")]
        public double Peso { get; set; } = 0.0;

        [JsonProperty("sexo")]
        public string Sexo { get; set; } = string.Empty;
    }
}
