using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de registrar una rutina.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseRegistrarRutinaVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la rutina registrada.
        /// </summary>
        public string Id { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha y hora en que se creó la rutina.
        /// </summary>
        public DateTime? CreatedAt { get; set; }
    }
}