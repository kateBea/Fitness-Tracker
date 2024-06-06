using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestGetListRutinasUsuario
    {

        // Si es cierto se recogen todas las rutinas del usuario y se ignoran los campos de las fechas,
        // si no, recogen entre las fechas [fechaInicio, fechaFin),
        // estas por ello deben ser no nulas para este caso
        public bool? FetchAll { get; set; } = false;

        public DateOnly? FechaInicio { get; set; }

        public DateOnly? FechaFin { get; set; }
    }
}
