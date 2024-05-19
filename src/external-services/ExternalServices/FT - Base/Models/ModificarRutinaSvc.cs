using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestModificarRutinaSvcIn
    {
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
        public List<AlimentoInfo> AlimentoInfos { get; set; }

        public class AlimentoInfo
        {
            [JsonProperty("comida_id")]
            public string ComidaId { get; set; }

            [JsonProperty("tipo")]
            public string Tipo { get; set; }

            [JsonProperty("hora_consumo")]
            public DateTime HoraConsumo { get; set; }

            [JsonProperty("orden")]
            public string Orden { get; set; }
        }
    }

    public class ResponseModificarRutinaSvcOut : BaseResponseSvc
    {
        [JsonProperty("modified_at")]
        public DateTime? MyProperty { get; set; } = DateTime.Now;
    }
}
