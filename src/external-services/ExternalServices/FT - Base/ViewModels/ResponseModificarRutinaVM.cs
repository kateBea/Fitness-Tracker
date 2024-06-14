using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de modificar una rutina.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseModificarRutinaVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la fecha y hora en que se modificó la rutina.
        /// Por defecto, se establece como la fecha y hora actual.
        /// </summary>
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}

