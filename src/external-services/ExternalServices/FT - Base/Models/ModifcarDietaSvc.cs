using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestModifcarDietaSvcIn
    {
        [JsonProperty("dieta_id")]
        public string Id { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; }

        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; }

        [JsonProperty("activa")]
        public bool Activa { get; set; }
    }

    public class ResponseModifcarDietaSvcOut : BaseResponseSvc
    {
        [JsonProperty("created_at")]
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
