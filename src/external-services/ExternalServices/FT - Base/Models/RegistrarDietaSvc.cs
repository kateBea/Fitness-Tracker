using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestRegistrarDietaSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("calorias_target")]
        public float CaloriasTarget { get; set; }

        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; }

        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; }

        [JsonProperty("id_comidas_sugeridas")]
        public List<string> ComidasSugeridas { get; set; }

        [JsonProperty("consumo_agua")]
        public double ConsumoDeAgua { get; set; }
    }

    public class ResponseRegistrarDietaSvcOut : BaseResponseSvc
    {
        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
