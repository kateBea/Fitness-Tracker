using FTAI.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.ViewModels
{
    /// <summary>
    /// Representa la respuesta para la generación de una dieta.
    /// </summary>
    public class ResponseGenerarDietaVM : BaseResponse
    {
        /// <summary>
        /// Plan de dieta generado.
        /// </summary>
        public Plan Dieta { get; set; } = new();
    }

    /// <summary>
    /// Representa un plan de dieta.
    /// </summary>
    public class Plan
    {
        /// <summary>
        /// Gasto calórico estimado.
        /// </summary>
        public float GastoCalorias { get; set; } = 0.0f;

        /// <summary>
        /// Fecha de inicio del plan de dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Fecha de finalización del plan de dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(1);

        /// <summary>
        /// Consumo de agua recomendado.
        /// </summary>
        public float ConsumoAgua { get; set; } = 0.0f;

        /// <summary>
        /// Lista de comidas incluidas en el plan de dieta.
        /// </summary>
        public List<ComidaVM> Comidas { get; set; } = new();
    }

    /// <summary>
    /// Representa una comida en el plan de dieta.
    /// </summary>
    public class ComidaVM
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
        /// Hora de consumo de la comida.
        /// </summary>
        public DateTime HoraConsumo { get; set; } = DateTime.Now;

        /// <summary>
        /// Calorías consumidas en esta comida.
        /// </summary>
        public double CaloriasConsumidas { get; set; }

        /// <summary>
        /// Orden de la comida, por ejemplo, primer plato.
        /// </summary>
        public string? OrdenComida { get; set; } = string.Empty;

        /// <summary>
        /// Tipo de comida, por ejemplo, almuerzo o merienda.
        /// </summary>
        public string? TipoComida { get; set; } = string.Empty;

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

