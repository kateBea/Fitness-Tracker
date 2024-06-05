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

        [JsonProperty("comidas_sugeridas")]
        public List<ComidaSugeridaData> ComidasSugeridas { get; set; }

        [JsonProperty("consumo_agua")]
        public double ConsumoDeAgua { get; set; }

        public class ComidaSugeridaData
        {
            [JsonProperty("id")]
            public string IdComida { get; set; }

            [JsonProperty("orden")]
            public string Orden { get; set; }

            [JsonProperty("tipo")]
            public string Tipo { get; set; }

            [JsonProperty("nombre")]
            public string Nombre { get; set; }

            [JsonProperty("descripcion")]
            public string Descripcion { get; set; }

            [JsonProperty("calorias")]
            public string Tip { get; set; }

            [JsonProperty("proteinas")]
            public float Proteinas { get; set; }

            [JsonProperty("grasas")]
            public float Grasas { get; set; }

            [JsonProperty("carbohidratos")]
            public float Carbohidratos { get; set; }

            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; }
        }
    }

    public class ResponseRegistrarDietaOut : BaseResponseSvc
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
