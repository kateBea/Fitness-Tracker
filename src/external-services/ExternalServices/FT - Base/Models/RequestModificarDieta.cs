using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestModificarDieta : BaseRequest
    {
        public string Email { get; set; }

        public float CaloriasTarget { get; set; }

        public DateTime FechaInicio { get; set; }

        public DateTime FechaFin { get; set; }

        public List<string> ComidasSugeridas { get; set; }

        public float ConsumoDeAgua { get; set; }

        public bool Activa { get; set; }
    }
}
