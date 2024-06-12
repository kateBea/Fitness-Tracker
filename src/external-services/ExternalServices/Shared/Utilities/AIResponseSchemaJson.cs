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
        public PlanDieta PlanDieta { get; set; } = new();
    }

    public class PlanDieta
    {
        public double CaloriasObjetivoDiario { get; set; } = 0.0;
        
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(7);

        public List<MenuDiario> MenuDiario { get; set; } = [];

        public double ConsumoDiarioAguaLitros { get; set; } = 0.0f;
    }

    public class MenuDiario
    {
        public string ComidaId { get; set; } = string.Empty;

        public string NombreComida { get; set; } = string.Empty;    

        public DateTime HoraConsumo { get; set; } = DateTime.Now;

        public double CaloriasConsumidas { get; set; } = 0.0;

        /// <summary>
        /// primer plato, etc
        /// </summary>
        public string OrdenComida { get; set; } = string.Empty;

        /// <summary>
        /// almuerzo, merienda
        /// </summary>
        public string TipoComida { get; set; } = string.Empty;
    }
}
