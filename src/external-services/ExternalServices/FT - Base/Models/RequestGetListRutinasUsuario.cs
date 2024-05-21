using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestGetListRutinasUsuario
    {
        public string Email { get; set; }

        // Si es cierto se recogen todas las rutinas del usuario,
        // si no recogen entre las fechas [fechaInicio, fechaFin),
        // estas por ello deben ser no nulas para este caso
        public bool FetchAll { get; set; }

        public DateTime? FechaInicio { get; set; }

        public DateTime? FechaFin { get; set; }
    }
}
