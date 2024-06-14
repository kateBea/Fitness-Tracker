using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.Models
{
    /// <summary>
    /// Clase que representa una solicitud para iniciar un nuevo chat de asistencia.
    /// </summary>
    public class RequestStartNewChatAssistance : BaseRequest
    {
        /// <summary>
        /// Mensaje inicial del chat.
        /// </summary>
        public string Mensaje { get; set; } = string.Empty;

        /// <summary>
        /// Nombre del usuario que inicia el chat.
        /// </summary>
        public string Nombre { get; set; } = string.Empty;
    }
}
