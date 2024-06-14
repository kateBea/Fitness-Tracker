using FTAI.Models;
using Shared.Utilities;

namespace FTAI.ViewModels
{
    /// <summary>
    /// Respuesta chat
    /// </summary>
    public class ResponseStartNewChatAssistanceVM : BaseResponse
    {
        /// <summary>
        /// última respuesta
        /// </summary>
        public string RespuestaActual { get; set; } = string.Empty;

        /// <summary>
        /// Histórico de mensajes
        /// </summary>
        public List<Message> Mensajes { get; set; } = [];
    }

    /// <summary>
    /// Modelo de mensaje
    /// </summary>
    public class Message
    {
        /// <summary>
        /// Contenido
        /// </summary>
        public string Content { get; set; } = string.Empty;

        /// <summary>
        /// Emisor
        /// </summary>
        public MessageEmisor MessageEmisor { get; set; } = MessageEmisor.USER;
    }

    /// <summary>
    /// Tipos de emisor
    /// </summary>
    public enum MessageEmisor
    {
        /// <summary>
        /// Mensaje del usuario en la conversación
        /// </summary>
        USER,

        /// <summary>
        /// Mensaje de chat GPT en la conversación
        /// </summary>
        ASSISTANT,
    }
}
