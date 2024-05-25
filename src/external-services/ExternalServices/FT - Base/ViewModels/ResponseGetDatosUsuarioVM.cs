using FT___Base.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseGetDatosUsuarioVM : BaseResponse
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDatosUsuarioVMData? Data { get; set; } = new();

        /// <summary>
        /// 
        /// </summary>
        public class ResponseGetDatosUsuarioVMData
        {

            public string NombreUsuario { get; set; } = string.Empty;

            public string Nombre { get; set; } = string.Empty;

            public string PrimerApellido { get; set; } = string.Empty;

            public string SegundoApellido { get; set; } = string.Empty;

            public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now);

            public DateTime FechaRegistro { get; set; } = DateTime.Now;

            // En centímetros
            public double Altura { get; set; } = 0.0;

            // En kilogramos
            public double Peso { get; set; } = 0.0;

            public string Sexo { get; set; } = string.Empty;
        }
    }
}
