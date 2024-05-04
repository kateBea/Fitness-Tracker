using Newtonsoft.Json;

namespace FTAI.Models
{
    public class RequestDietaIn
    {
        [JsonProperty("requisitos_dieta")]
        public Dieta DetallesDieta { get; set; } = new Dieta();
    }
}
