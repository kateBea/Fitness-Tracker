using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// Representa el esquema de una solicitud de dieta para la IA.
    /// </summary>
    public class AIRequestDietaSchemaJson
    {
        /// <summary>
        /// Edad de la persona que solicita la dieta.
        /// </summary>
        public int Edad { get; set; }

        /// <summary>
        /// Calorías objetivo diarias para la dieta.
        /// </summary>
        public double TargetCalories { get; set; } = 0.0f;

        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        public DateOnly FechaInicio { get; set; } = DateOnly.FromDateTime(DateTime.Now);

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        public DateOnly FechaFin { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddDays(7));

        /// <summary>
        /// Consumo de agua diario en litros.
        /// </summary>
        public double ConsumoAgua { get; set; } = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        public List<ComidaDieta> ComidasSugeridas { get; set; } = new();
    }

    /// <summary>
    /// Representa una comida sugerida en la dieta.
    /// </summary>
    public class ComidaDieta
    {
        /// <summary>
        /// Identificador de la comida.
        /// </summary>
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Nombre de la comida.
        /// </summary>
        public string? Nombre { get; set; } = string.Empty;

        /// <summary>
        /// Descripción de la comida.
        /// </summary>
        public string? Descripcion { get; set; } = string.Empty;

        /// <summary>
        /// Cantidad de proteínas en la comida.
        /// </summary>
        public double Proteinas { get; set; } = 0;

        /// <summary>
        /// Calorías de la comida.
        /// </summary>
        public double Caloias { get; set; } = 0.0f;

        /// <summary>
        /// Cantidad de carbohidratos en la comida.
        /// </summary>
        public double Carbohidratos { get; set; } = 0.0f;

        /// <summary>
        /// Cantidad de grasas en la comida.
        /// </summary>
        public double Grasas { get; set; } = 0.0f;

        /// <summary>
        /// Lista de vitaminas incluidas en la comida.
        /// </summary>
        public List<string> Vitaminas { get; set; } = new();
    }
}

