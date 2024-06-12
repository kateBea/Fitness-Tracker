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
        public int Edad { get; set; }

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
        /// Consuma de agua diario en litros.
        /// </summary>
        public double ConsumoAgua = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        public List<ComidaDieta> ComidasSugeridas { get; set; } = [];
    }

    /// <summary>
    /// 
    /// </summary>
    public class ComidaDieta
    {
        public string Id { get; set; } = string.Empty;

        public string? Nombre { get; set; } = string.Empty;

        public string? Descripcion { get; set; } = string.Empty;

        public double Proteinas { get; set; } = 0;

        public double Caloias { get; set; } = 0.0f;

        public double Carbohidratos { get; set; } = 0.0f;

        public double Grasas { get; set; } = 0.0f;

        public List<string> Vitaminas { get; set; } = [];
    }
}
