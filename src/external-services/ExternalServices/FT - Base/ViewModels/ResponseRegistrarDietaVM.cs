using FTBase.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de registrar una dieta.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseRegistrarDietaVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta registrada.
        /// </summary>
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la fecha y hora en que se creó la dieta.
        /// Por defecto, se establece como la fecha y hora actual.
        /// </summary>
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
