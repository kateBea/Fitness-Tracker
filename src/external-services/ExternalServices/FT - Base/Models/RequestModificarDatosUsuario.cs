using Newtonsoft.Json;

namespace FT___Base.Models
{
    public class RequestModificarDatosUsuario
    {
        public string Email { get; set; } = string.Empty;

        public string NombreUsuario { get; set; } = string.Empty;


        public string Nombre { get; set; } = string.Empty;


        public string PrimerApellido { get; set; } = string.Empty;


        public string SegundoApellido { get; set; } = string.Empty;


        public DateTime FechaDeNacimiento { get; set; } = DateTime.Now;

        // En centímetros
        public double Altura { get; set; } = 0.0;

        // En kilogramos
        public double Peso { get; set; } = 0.0;

        public string Sexo { get; set; } = string.Empty;
    }
}
