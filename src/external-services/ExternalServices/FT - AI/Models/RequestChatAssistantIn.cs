﻿using Newtonsoft.Json;

namespace FTAI.Models
{
    public class RequestChatAssistantIn : BaseRequest
    {
        public string Mensaje { get; set; } = string.Empty;
        public string Nombre { get; set; } = string.Empty;
    }
}
