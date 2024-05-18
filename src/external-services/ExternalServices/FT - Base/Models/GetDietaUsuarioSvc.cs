using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetDietaUsuarioSvc
    {

    }

    public class ResponseGetDietaUsuarioSvcOut : BaseResponseSvc
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("nombre_usuario")]
        public string NombreUsuario { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; }

        [JsonProperty("primer_apellido")]
        public string PrimerApellido { get; set; }

        [JsonProperty("segundo_apellido")]
        public string SegundoApellido { get; set; }

        [JsonProperty("fecha_nacimiento")]
        public DateTime FechaNacimiento { get; set; }

        [JsonProperty("altura")]
        public double Altura { get; set; }

        [JsonProperty("peso")]
        public double Peso { get; set; }

        [JsonProperty("sexo")]
        public string Sexo { get; set; }
    }
}
