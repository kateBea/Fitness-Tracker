using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetDietaUsuarioInSvc
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("dieta_id")]
        public string IdDieta { get; set; } = string.Empty;
    }

    public class ResponseGetDietaUsuarioSvcOut : BaseResponseSvc
    {
        [JsonProperty("data")]
        public ResponseGetDietaUsuarioData? Data { get; set; }

        public class ResponseGetDietaUsuarioData
        {
            [JsonProperty("id")]
            public string Id { get; set; }

            [JsonProperty("calorias_target")]
            public float CaloriasTarget { get; set; }

            [JsonProperty("fecha_inicio")]
            public DateTime? FechaInicio { get; set; }

            [JsonProperty("fecha_fin")]
            public DateTime? FechaFin { get; set; }

            [JsonProperty("comidas_sugeridas")]
            public List<ResponseGetDietaUsuarioDataComida> ComidasSugeridas { get; set; }

            [JsonProperty("consumo_agua")]
            public float ConsumoDeAgua { get; set; }

            [JsonProperty("fecha_registro")]
            public DateTime? FechaRegistro { get; set; }

            [JsonProperty("ultima_modificacion")]
            public DateTime? FechaUltimaModificacion { get; set; }
        }

        public class ResponseGetDietaUsuarioDataComida
        {
            [JsonProperty("id")]
            public string Id { get; set; }

            [JsonProperty("nombre")]
            public string Nombre { get; set; }

            [JsonProperty("descripcion")]
            public string Descripcion { get; set; }

            [JsonProperty("unidades")]
            public int Unidades { get; set; }

            [JsonProperty("calorias")]
            public float Calorias { get; set; }

            [JsonProperty("carbohidratos")]
            public float Carbohidratos { get; set; }

            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; }

            [JsonProperty("grasas")]
            public float Grasas { get; set; }

            [JsonProperty("fecha_registro")]
            public DateTime? FechaRegistro { get; set; }

            [JsonProperty("ultima_modificacion")]
            public DateTime? FechaUltimaModificacion { get; set; }
        }
    }
}
