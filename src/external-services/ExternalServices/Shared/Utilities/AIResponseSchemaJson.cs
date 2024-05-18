using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace Shared.Utilities
{
    public class AIResponseSchemaJson
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("plan_dieta")]
        public PlanDieta PlanDieta { get; set; } = new();
    }

    public class PlanDieta
    {
        [JsonProperty("calorias_objetivo_diario")]
        public double CaloriasObjetivoDiario { get; set; }
        
        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; }

        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; }

        [JsonProperty("menu_diario")]
        public List<MenuDiario> MenuDiario { get; set; } = [];
        
        [JsonProperty("consumo_diario_agua_litros")]
        public double ConsumoDiarioAguaLitros { get; set; }
    }

    public class MenuDiario
    {
        [JsonProperty("comida_id")]
        public string ComidaId { get; set; } = string.Empty;

        [JsonProperty("nombre_comida")]
        public string NombreComida { get; set; } = string.Empty;    

        [JsonProperty("hora_consumo")]
        public DateTime HoraConsumo { get; set; }

        [JsonProperty("calorias_consumidas")]
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
