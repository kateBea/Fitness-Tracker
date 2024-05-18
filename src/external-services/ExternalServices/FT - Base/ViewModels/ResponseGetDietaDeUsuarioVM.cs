using FT___Base.Models;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    public class ResponseGetDietaDeUsuarioVM : BaseResponse
    {
        public string Id { get; set; } = string.Empty;

        public float TargetCalories { get; set; } = 0.0f;

        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now;

        /// <summary>
        /// Consuma de agua diario en litros.
        /// </summary>
        public float ConsumoAgua = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        public List<ComidaDieta> ComidasSugeridas { get; set; } = [];
    }
}
