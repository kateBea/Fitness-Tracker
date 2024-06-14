using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.RequestRegistrarDietaSvcIn;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de datos para registrar una dieta.
    /// Contiene diferentes parámetros necesarios para definir una dieta.
    /// </summary>
    public class RequestRegistrarDieta : BaseRequest
    {
        /// <summary>
        /// Obtiene o establece el objetivo de calorías diarias de la dieta.
        /// </summary>
        public double CaloriasTarget { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la fecha de inicio de la dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de finalización de la dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(7);

        /// <summary>
        /// Obtiene o establece el consumo diario de agua en litros.
        /// </summary>
        public double ConsumoDeAgua { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la lista de comidas sugeridas para la dieta.
        /// </summary>
        public List<ComidaSugeridaData> ComidasSugeridas { get; set; } = [];
    }
}

