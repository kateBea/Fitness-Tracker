using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.Models
{
    public class RequestModificarRutinaSvcIn
    {
        [JsonProperty("rutina_id")]
        public string Id { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("tiempo_suenio")]
        public float TiempoDeSuenio { get; set; }

        [JsonProperty("calorias_quemadas")]
        public float CaloriasQuemadas { get; set; }

        [JsonProperty("pasos_realizados")]
        public int PasosRealizados { get; set; }

        [JsonProperty("frecuencia_cardiaca")]
        public float FrecuenciaCardiaca { get; set; }

        [JsonProperty("nivel_oxigeno_sangre")]
        public float NivelOxigenoSangre { get; set; }

        [JsonProperty("alimentos")]
        public List<AlimentoInfo> ComidasConsumidas { get; set; }
    }

    public class ResponseModificarRutinaSvcOut : BaseResponseSvc
    {
        [JsonProperty("modified_at")]
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
