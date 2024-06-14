using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para modificar datos de usuario en el servicio.
    /// </summary>
    public class RequestModificarDatosUsuarioSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario.
        /// </summary>
        [JsonProperty("correo_electronico")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el nombre de usuario.
        /// </summary>
        [JsonProperty("nombre_usuario")]
        public string NombreUsuario { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el primer nombre del usuario.
        /// </summary>
        [JsonProperty("nombre")]
        public string Nombre { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el primer apellido del usuario.
        /// </summary>
        [JsonProperty("primer_apellido")]
        public string PrimerApellido { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el segundo apellido del usuario.
        /// </summary>
        [JsonProperty("segundo_apellido")]
        public string SegundoApellido { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la URL de la imagen del usuario.
        /// </summary>
        [JsonProperty("imagen")]
        public string Imagen { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el objetivo de peso del usuario.
        /// </summary>
        [JsonProperty("objetivo_peso")]
        public double ObjetivoPeso { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la fecha de nacimiento del usuario.
        /// </summary>
        [JsonProperty("fecha_nacimiento")]
        public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// Obtiene o establece la altura del usuario en centímetros.
        /// </summary>
        [JsonProperty("altura")]
        public double Altura { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece el peso del usuario en kilogramos.
        /// </summary>
        [JsonProperty("peso")]
        public double Peso { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece el sexo del usuario.
        /// </summary>
        [JsonProperty("sexo")]
        public string Sexo { get; set; } = string.Empty;
    }


    /// <summary>
    /// Modelo de respuesta modificar datos usuario
    /// </summary>
    public class ResponseModificarDatosUsuarioSvc : BaseResponseSvc
    {
        /// <summary>
        /// Fecha de modificación
        /// </summary>
        [JsonProperty("modified_at")]    
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
