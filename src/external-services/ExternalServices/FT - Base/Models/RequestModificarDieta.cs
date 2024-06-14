using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de datos para modificar una dieta existente.
    /// Contiene los parámetros necesarios para actualizar la información de una dieta.
    /// </summary>
    public class RequestModificarDieta : BaseRequest
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta a modificar.
        /// </summary>
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la fecha de inicio de la dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de fin de la dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece un valor que indica si la dieta está activa o no.
        /// </summary>
        public bool Activa { get; set; } = false;
    }
}
