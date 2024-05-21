using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestRegistrarRutina
    {
        public string Email { get; set; } = string.Empty;

        public double TiempoDeSuenio { get; set; } = 0;

        public double CaloriasQuemadas { get; set; }

        public int PasosRealizados { get; set; }

        public double FrecuenciaCardiaca { get; set; }

        public double NivelOxigenoSangre { get; set; }

        public double PresionArterial { get; set; }
    }
}
