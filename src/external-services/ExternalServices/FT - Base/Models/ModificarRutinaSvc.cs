using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.ResponseGetRutinaPorIdSvc;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para modificar una rutina en el servicio.
    /// </summary>
    public class RequestModificarRutinaSvc
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la rutina.
        /// </summary>
        [JsonProperty("rutina_id")]
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el correo electrónico asociado a la rutina.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el tiempo de sueño registrado durante la rutina.
        /// </summary>
        [JsonProperty("tiempo_suenio")]
        public double TiempoDeSuenio { get; set; } = 0f;

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
        /// Obtiene o establece la frecuencia cardíaca registrada durante la rutina.
        /// </summary>
        [JsonProperty("frecuencia_cardiaca")]
        public double FrecuenciaCardiaca { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece el nivel de oxígeno en sangre durante la rutina.
        /// </summary>
        [JsonProperty("nivel_oxigeno_sangre")]
        public double NivelOxigenoSangre { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la lista de alimentos consumidos durante la rutina.
        /// </summary>
        [JsonProperty("alimentos")]
        public List<AlimentoInfo> ComidasConsumidas { get; set; } = [];
    }

    /// <summary>
    /// Modelo de respuesta de modificar rutina
    /// </summary>
    public class ResponseModificarRutinaSvc : BaseResponseSvc
    {
        /// <summary>
        /// Fecha de modificación
        /// </summary>
        [JsonProperty("modified_at")]
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
