using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetRutinaPorIdSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("rutina_id")]
        public string IdRutina { get; set; }
    }

    public class ResponseGetRutinaPorIdSvcOut : BaseResponseSvc
    {
        [JsonProperty("data")]
        public ResponseGetRutinaData? Data { get; set; }

        public class ResponseGetRutinaData
        {
            [JsonProperty("id")]
            public string Id { get; set; }

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

            [JsonProperty("fecha_seguimiento")]
            public DateTime? FechaSeguimiento { get; set; }

            [JsonProperty("ultima_modificacion")]
            public DateTime? FechaUltimaModificacion { get; set; }

            [JsonProperty("comidas_consumidas")]
            public List<AlimentoInfo> ComidasConsumidas { get; set; }
        }

        public class AlimentoInfo
        {
            [JsonProperty("comida_id")]
            public string ComidaId { get; set; }

            [JsonProperty("nombre")]
            public string Nombre { get; set; }

            [JsonProperty("tipo")]
            public string Tipo { get; set; }

            [JsonProperty("hora_consumo")]
            public DateTime? HoraConsumo { get; set; }

            [JsonProperty("orden")]
            public string Orden { get; set; }
        }
    }
}
