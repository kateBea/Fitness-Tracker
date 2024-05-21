using Newtonsoft.Json;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.Models
{
    public class RequestModificarRutina
    {
        [JsonProperty("rutina_id")]
        public string IdRutina { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("rutina_id")]
        public string RutinaId { get; set; }

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

        [JsonProperty("presion_arterial")]
        public float PresionArterial { get; set; }

        [JsonProperty("alimentos")]
        public List<AlimentoInfo> AlimentoInfos { get; set; } = [];
    }
}
