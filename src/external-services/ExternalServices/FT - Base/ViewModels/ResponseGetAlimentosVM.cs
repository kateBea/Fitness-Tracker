using Shared.Utilities;
using static FTBase.Models.ResponseGetAlimentosSvc;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta para obtener la lista de alimentos.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseGetAlimentosVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la lista de alimentos obtenidos.
        /// Puede ser nulo si no se encontraron alimentos o no hay datos disponibles.
        /// </summary>
        public List<GetAlimentosListItemVM>? Data { get; set; } = new List<GetAlimentosListItemVM>();
    }

    /// <summary>
    /// Clase interna que representa un elemento de la lista de alimentos.
    /// Contiene detalles específicos de cada alimento.
    /// </summary>
    public class GetAlimentosListItemVM
    {
        /// <summary>
        /// Obtiene o establece el identificador único del alimento.
        /// </summary>
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el nombre del alimento.
        /// </summary>
        public string Nombre { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la descripción del alimento.
        /// </summary>
        public string Descripcion { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la cantidad de calorías del alimento.
        /// </summary>
        public double Calorias { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la cantidad de proteínas del alimento.
        /// </summary>
        public double Proteinas { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la cantidad de grasas del alimento.
        /// </summary>
        public double Grasas { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la cantidad de carbohidratos del alimento.
        /// </summary>
        public double Carbohidratos { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la lista de vitaminas presentes en el alimento.
        /// </summary>
        public List<string> Vitaminas { get; set; } = [];

        /// <summary>
        /// Obtiene o establece la fecha de registro del alimento en el sistema.
        /// </summary>
        public DateTime FechaRegistro { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de la última modificación del alimento en el sistema.
        /// </summary>
        public DateTime FechaUltimaModificacion { get; set; } = DateTime.Now;
    }
}
