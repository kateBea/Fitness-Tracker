using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// Request login external service model
    /// </summary>
    public class RequestRegisterSvcIn
    {
        /// <summary>
        /// User's email
        /// </summary>
        [JsonProperty("correo_electronico")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// User's nickname
        /// </summary>
        [JsonProperty("nombre_usuario")]
        public string? Username { get; set; } = string.Empty;

        /// <summary>
        /// User's password
        /// </summary>
        [JsonProperty("password")]
        public string Password { get; set; } = string.Empty;

        /// <summary>
        /// User's name
        /// </summary>
        [JsonProperty("nombre")]
        public string? Name { get; set; } = string.Empty;

        /// <summary>
        /// User's first surname
        /// </summary>
        [JsonProperty("primer_apellido")]
        public string? FirstSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's second surname
        /// </summary>
        [JsonProperty("segundo_apellido")]
        public string? SecondSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's birth date
        /// </summary>
        [JsonProperty("fecha_nacimiento")]
        public DateOnly? Birthday { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// User's height
        /// </summary>
        [JsonProperty("altura")]
        public float? Height { get; set; } = 0.0f;

        /// <summary>
        /// User's weight
        /// </summary>
        [JsonProperty("peso")]
        public float? Weight { get; set; } = 0.0f;

        /// <summary>
        /// User's sex
        /// </summary>
        [JsonProperty("sexo")]
        public string? Sex { get; set; } = string.Empty;
    }

    /// <summary>
    /// Response register external service model
    /// </summary>
    public class ResponseRegisterSvcOut : BaseResponseSvc
    {
    }
}
