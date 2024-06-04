using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.RequestRegistrarDietaSvcIn;

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

        public List<ComidaSugeridaData> ComidasSugeridas { get; set; }

        public double ConsumoDeAgua { get; set; }
        public bool Activa { get; set; }
    }
}
