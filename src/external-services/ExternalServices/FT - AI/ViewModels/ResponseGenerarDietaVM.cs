using FTAI.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseGenerarDietaVM : BaseResponse
    {
        public Plan Dieta { get; set; } = new();
    }

    public class Plan
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
        public string Id { get; set; } = string.Empty;
        
        public string? Nombre { get; set; } = string.Empty;

        public DateTime HoraConsumo { get; set; } = DateTime.Now;

        public double CaloriasConsumidas { get; set; }

        /// <summary>
        /// primer plato, etc
        /// </summary>
        [JsonProperty("orden_comida")]
        public string? OrdenComida { get; set; } = string.Empty;

        /// <summary>
        /// almuerzo, merienda
        /// </summary>
        [JsonProperty("tipo_comida")]
        public string? TipoComida { get; set; } = string.Empty;
    }
}
