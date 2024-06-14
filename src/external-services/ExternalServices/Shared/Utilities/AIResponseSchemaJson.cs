using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace Shared.Utilities
{
    /// <summary>
    /// Represents a JSON schema for AI response data.
    /// </summary>
    public class AIResponseSchemaJson
    {
        /// <summary>
        /// Gets or sets the diet plan associated with the AI response.
        /// </summary>
        public PlanDieta PlanDieta { get; set; } = new();
    }

    /// <summary>
    /// Representa un plan de dieta con objetivos diarios y menús planificados.
    /// </summary>
    public class PlanDieta
    {
        /// <summary>
        /// Obtiene o establece las calorías objetivo diarias para la dieta.
        /// </summary>
        public double CaloriasObjetivoDiario { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la fecha de inicio del plan de dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de finalización del plan de dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(7);

        /// <summary>
        /// Obtiene o establece la lista de menús diarios planificados para la dieta.
        /// </summary>
        public List<MenuDiario> MenuDiario { get; set; } = new List<MenuDiario>();

        /// <summary>
        /// Obtiene o establece el consumo diario de agua en litros para la dieta.
        /// </summary>
        public double ConsumoDiarioAguaLitros { get; set; } = 0.0;
    }

    /// <summary>
    /// Representa un menú diario dentro de un plan de dieta.
    /// </summary>
    public class MenuDiario
    {
        /// <summary>
        /// Obtiene o establece el identificador de la comida en el menú.
        /// </summary>
        public string ComidaId { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el nombre de la comida en el menú.
        /// </summary>
        public string NombreComida { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la hora de consumo de la comida.
        /// </summary>
        public DateTime HoraConsumo { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece el tipo de comida (primer plato, almuerzo, etc.).
        /// </summary>
        public string TipoComida { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el orden de la comida en el menú (primer plato, postre, etc.).
        /// </summary>
        public string OrdenComida { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece las calorías consumidas en esta comida del menú.
        /// </summary>
        public double CaloriasConsumidas { get; set; } = 0.0;
    }
}
