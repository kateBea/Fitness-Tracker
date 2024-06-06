using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestRegistrarRutina
    {

        public double TiempoDeSuenio { get; set; } = 0.0;

        public double CaloriasQuemadas { get; set; } = 0.0;

        public int PasosRealizados { get; set; } = 0;

        public double FrecuenciaCardiaca { get; set; } = 0.0;

        public double NivelOxigenoSangre { get; set; } = 0.0;

    }
}
