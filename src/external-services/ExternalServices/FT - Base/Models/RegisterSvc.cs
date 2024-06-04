using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestRegisterSvcIn
    {
        [JsonProperty("correo_electronico")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("nombre_usuario")]
        public string? Username { get; set; } = string.Empty;

        [JsonProperty("password")]
        public string Password { get; set; } = string.Empty;


        // Temporalmente opcionales
        [JsonProperty("nombre")]
        public string? Name { get; set; } = string.Empty;

        [JsonProperty("primer_apellido")]
        public string? FirstSurname { get; set; } = string.Empty;

        [JsonProperty("segundo_apellido")]
        public string? SecondSurname { get; set; } = string.Empty;

        [JsonProperty("fecha_nacimiento")]
        public DateOnly? Birthday { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        [JsonProperty("altura")]
        public float? Height { get; set; } = 0.0f;

        [JsonProperty("peso")]
        public float? Weight { get; set; } = 0.0f;

        [JsonProperty("sexo")]
        public string? Sex { get; set; } = string.Empty;
    }

    public class ResponseRegisterSvcOut : BaseResponseSvc
    {
    }
}
