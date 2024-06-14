using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para registrar una rutina.
    /// </summary>
    public class RequestRegistrarRutinaIn : BaseRequest
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico asociado a la rutina.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el tiempo de sueño de la rutina.
        /// </summary>
        [JsonProperty("tiempo_suenio")]
        public double TiempoDeSuenio { get; set; } = 0;

        /// <summary>
        /// Obtiene o establece las calorías quemadas durante la rutina.
        /// </summary>
        [JsonProperty("calorias_quemadas")]
        public double CaloriasQuemadas { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la cantidad de pasos realizados durante la rutina.
        /// </summary>
        [JsonProperty("pasos_realizados")]
        public int PasosRealizados { get; set; } = 0;

        /// <summary>
        /// Obtiene o establece la frecuencia cardíaca durante la rutina.
        /// </summary>
        [JsonProperty("frecuencia_cardiaca")]
        public double FrecuenciaCardiaca { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece el nivel de oxígeno en sangre durante la rutina.
        /// </summary>
        [JsonProperty("nivel_oxigeno_sangre")]
        public double NivelOxigenoSangre { get; set; } = 0f;
    }

    /// <summary>
    /// Modelo de petición de listado de rutinas
    /// </summary>
    public class RequestRegistrarRutinaOut : BaseResponseSvc
    {
        /// <summary>
        /// Id de la rutina
        /// </summary>
        [JsonProperty("id")]
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Fecha de creación
        /// </summary>
        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
