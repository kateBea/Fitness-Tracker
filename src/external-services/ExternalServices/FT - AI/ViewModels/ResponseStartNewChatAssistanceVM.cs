using FTAI.Models;
using Shared.Utilities;

namespace FTAI.ViewModels
{
    public class ResponseStartNewChatAssistanceVM : BaseResponse
    {
        public string RespuestaActual { get; set; } = string.Empty;
        public List<Message> Mensajes { get; set; } = [];
    }

    public class Message
    {
        public string Content { get; set; } = string.Empty;
        public MessageEmisor MessageEmisor { get; set; } = MessageEmisor.USER;
    }

    public enum MessageEmisor
    {
        USER,       // Mensaje del usuario en la conversación
        ASSISTANT,  // Mensaje de chat GPT en la conversación
    }
}
