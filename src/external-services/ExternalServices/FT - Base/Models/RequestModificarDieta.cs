﻿using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestModificarDieta : BaseRequest
    {
        public string Id { get; set; }

        public DateTime FechaInicio { get; set; }

        public DateTime FechaFin { get; set; }

        public bool Activa { get; set; }
    }
}
