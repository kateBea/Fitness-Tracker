using Newtonsoft.Json;

namespace FTAI.Models
{
    public class RequestDietaIn : BaseRequest
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
        [JsonProperty("consumo_agua")]
        public float ConsumoAgua = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        [JsonProperty("comidas")]
        public List<ComidaIn> ComidasSugeridas { get; set; } = [];
    }

    public class ComidaIn
    {
        [JsonProperty("nombre")]
        public string? Nombre { get; set; } = string.Empty;

        [JsonProperty("descripcion")]
        public string? Descripcion { get; set; } = string.Empty;

        [JsonProperty("unidades")]
        public int Unidades { get; set; } = 0;

        [JsonProperty("calorias_generadas")]
        public float CaloiasGeneradas { get; set; } = 0.0f;

        [JsonProperty("vitaminas")]
        public List<string> Vitaminas { get; set; } = [];
    }
}
