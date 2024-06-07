using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetAlimentosSvc : BaseRequest
    {
        [JsonProperty("email")]
        public string Email { get; set; }
    }

    public class ResponseGetAlimentosSvc : BaseResponseSvc
    {
        [JsonProperty("data")]
        public List<GetAlimentosListItem>? Data { get; set; } = [];

        public class GetAlimentosListItem
        {
            [JsonProperty("id")]
            public string Id { get; set; }

            [JsonProperty("nombre")]
            public string Nombre { get; set; }

            [JsonProperty("descripcion")]
            public string Descripcion { get; set; }

            [JsonProperty("calorias")]
            public double Calorias { get; set; }

            [JsonProperty("proteinas")]
            public double Proteinas { get; set; }

            [JsonProperty("grasas")]
            public double Grasas { get; set; }

            [JsonProperty("carbohidratos")]
            public double Carbohidratos { get; set; }

            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; }

            [JsonProperty("fecha_registro")]
            public DateTime FechaRegistro { get; set; }

            [JsonProperty("ultima_modificacion")]
            public DateTime FechaUltimaModificacion { get; set; }
        }
    }
}
