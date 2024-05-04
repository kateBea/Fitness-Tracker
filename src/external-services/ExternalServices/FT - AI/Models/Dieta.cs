using Newtonsoft.Json;

namespace FTAI.Models
{
    public class Dieta : BaseRequest
    {
        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; } = DateTime.Now;

        /// <summary>
        /// Consuma de agua diario en litros.
        /// </summary>
        [JsonProperty("consumo_diario_agua")]
        public float ConsumoAgua = 0.0f;
    }
}
