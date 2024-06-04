using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestRegistrarRutinaIn : BaseRequest
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("tiempo_suenio")]
        public double TiempoDeSuenio { get; set; } = 0;

        [JsonProperty("calorias_quemadas")]
        public double CaloriasQuemadas { get; set; }

        [JsonProperty("pasos_realizados")]
        public int PasosRealizados { get; set; }

        [JsonProperty("frecuencia_cardiaca")]
        public double FrecuenciaCardiaca { get; set; }

        [JsonProperty("nivel_oxigeno_sangre")]
        public double NivelOxigenoSangre { get; set; }
    }

    /// <summary>
    /// 
    /// </summary>
    public class RequestRegistrarRutinaOut : BaseResponseSvc
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
