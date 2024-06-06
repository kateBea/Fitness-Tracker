using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestModificarDatosUsuarioSvcIn
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
        public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        // En centímetros
        [JsonProperty("altura")]
        public double Altura { get; set; } = 0.0;

        // En kilogramos
        [JsonProperty("peso")]
        public double Peso { get; set; } = 0.0;

        [JsonProperty("sexo")]
        public string Sexo { get; set; } = string.Empty;
    }

    /// <summary>
    /// 
    /// </summary>
    public class ResponseModificarDatosUsuarioSvcOut : BaseResponseSvc
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("modified_at")]    
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
