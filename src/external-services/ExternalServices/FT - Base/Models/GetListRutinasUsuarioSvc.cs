using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.Models
{
    public class RequestGetListRutinasUsuarioSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        // Si es cierto se recogen todas las rutinas del usuario,
        // si no recogen entre las fechas [fechaInicio, fechaFin),
        // estas por ello deben ser no nulas para este caso
        [JsonProperty("fetch_all")]
        public bool FetchAll { get; set; }

        [JsonProperty("fecha_inicio")]
        public DateTime? FechaInicio { get; set; }

        [JsonProperty("fecha_fin")]
        public DateTime? FechaFin { get; set; }
    }

    public class ResponseGetListRutinasUsuarioSvcOut : BaseResponseSvc
    {
        [JsonProperty("data")]
        public List<ResponseGetRutinaData>? Data { get; set; } = [];
    }
}
