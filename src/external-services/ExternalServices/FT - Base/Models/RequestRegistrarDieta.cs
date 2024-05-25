using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestRegistrarDieta : BaseRequest
    {

        public float CaloriasTarget { get; set; }

        public DateTime FechaInicio { get; set; }

        public DateTime FechaFin { get; set; }

        public List<string> ComidasSugeridas { get; set; }

        public double ConsumoDeAgua { get; set; }
    }
}
