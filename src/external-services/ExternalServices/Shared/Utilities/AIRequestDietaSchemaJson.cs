using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// 
    /// </summary>
    public class AIRequestDietaSchemaJson
    {
        [JsonProperty("edad")]
        public int Edad { get; set; }

        [JsonProperty("calorias_objetivo")]
        public float TargetCalories { get; set; } = 0.0f;

        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; } = DateTime.Now;

        /// <summary>
        /// Consuma de agua diario en litros.
        /// </summary>
        [JsonProperty("consumo_diario_agua")]
        public float ConsumoAgua = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        [JsonProperty("listado_comidas")]
        public List<ComidaDieta> ComidasSugeridas { get; set; } = [];
    }

    /// <summary>
    /// 
    /// </summary>
    public class ComidaDieta
    {
        [JsonProperty("comida_id")]
        public string Id { get; set; } = string.Empty;

        [JsonProperty("nombre_comida")]
        public string? Nombre { get; set; } = string.Empty;

        [JsonProperty("descripcion")]
        public string? Descripcion { get; set; } = string.Empty;

        [JsonProperty("orden_comida")]
        public string? OrdenComida { get; set; } = string.Empty; // primer plato, etc

        [JsonProperty("tipo_comida")]
        public string? TipoComida { get; set; } = string.Empty; // almuerzo, merienda

        [JsonProperty("unidades")]
        public int Unidades { get; set; } = 0;

        [JsonProperty("calorias_consumidas")]
        public float CaloiasGeneradas { get; set; } = 0.0f;

        [JsonProperty("carbohidratos")]
        public float Carbohidratos { get; set; } = 0.0f;

        [JsonProperty("grasas")]
        public float Grasas { get; set; } = 0.0f;

        [JsonProperty("vitaminas")]
        public List<string> Vitaminas { get; set; } = [];
    }
}
