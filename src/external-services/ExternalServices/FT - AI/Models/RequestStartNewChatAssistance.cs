using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.Models
{
    public class RequestStartNewChatAssistance : BaseRequest
    {
        public string Mensaje { get; set; } = string.Empty;
        public string Nombre { get; set; } = string.Empty;
    }
}
