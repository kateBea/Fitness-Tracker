using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestModifcarDietaSvcIn
    {
        [JsonProperty("dieta_id")]
        public string IdDieta { get; set; }

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
        public float ConsumoDeAgua { get; set; }

        [JsonProperty("activa")]
        public bool Activa { get; set; }
    }

    public class ResponseModifcarDietaSvcOut : BaseResponseSvc
    {
        [JsonProperty("created_at")]
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
