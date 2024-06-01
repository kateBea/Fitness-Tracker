using Newtonsoft.Json;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.Models
{
    public class RequestModificarRutina
    {
        public string IdRutina { get; set; }

        public float TiempoDeSuenio { get; set; }

        public float CaloriasQuemadas { get; set; }

        public int PasosRealizados { get; set; }

        public float FrecuenciaCardiaca { get; set; }

        public float NivelOxigenoSangre { get; set; }

        public List<AlimentoInfo> AlimentoInfos { get; set; } = [];
    }
}
