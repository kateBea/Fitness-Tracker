using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de modificar una dieta.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseModificarDietaVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la fecha y hora en que se modificó la dieta.
        /// Por defecto, se establece como la fecha y hora actual.
        /// </summary>
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
