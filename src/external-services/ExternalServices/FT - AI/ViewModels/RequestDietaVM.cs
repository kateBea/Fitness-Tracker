using FTAI.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.ViewModels
{
    public class RequestDietaVM : BaseResponse
    {
        [JsonProperty("gasto_calorias")]
        public float GastoCalorias { get; set; } = 0.0f;

        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(1);

        [JsonProperty("consumo_agua")]
        public float ConsumoAgua { get; set; } = 0.0f;

        [JsonProperty("comidas")]
        public List<ComidaVM> Comidas { get; set; } = [];
    }

    public class ComidaVM
    {
        [JsonProperty("nombre")]
        public string? Nombre { get; set; } = string.Empty;

        [JsonProperty("descripcion")]
        public string? Descripcion { get; set; } = string.Empty;

        [JsonProperty("unidades")]
        public int Unidades { get; set; } = 0;

        [JsonProperty("calorias_generadas")]
        public float CaloiasGeneradas { get; set; } = 0.0f;

        [JsonProperty("vitaminas")]
        public List<string> Vitaminas { get; set; } = [];
    }
}
