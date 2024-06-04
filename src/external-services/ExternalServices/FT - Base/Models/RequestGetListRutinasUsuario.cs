using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestGetListRutinasUsuario
    {

        // Si es cierto se recogen todas las rutinas del usuario,
        // si no recogen entre las fechas [fechaInicio, fechaFin),
        // estas por ello deben ser no nulas para este caso
        public bool? FetchAll { get; set; } = false;

        public DateTime? FechaInicio { get; set; }

        public DateTime? FechaFin { get; set; }
    }
}
