using Newtonsoft.Json;
using Shared.Utilities;
using System.Text.Json.Serialization;
using static FTBase.Models.ResponseGetRutinaPorIdSvc;

namespace FTBase.Models
{
    /// <summary>
    /// Clase de solicitud para obtener la lista de rutinas de un usuario en el servicio.
    /// Utilizada para enviar parámetros al servicio para recuperar las rutinas de un usuario específico.
    /// </summary>
    public class RequestGetListRutinasUsuarioSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario para el cual se solicitan las rutinas.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Indica si se deben recuperar todas las rutinas del usuario (true) o filtrar por fechas (false).
        /// </summary>
        [JsonProperty("fetch_all")]
        public bool FetchAll { get; set; } = true;

        /// <summary>
        /// Obtiene o establece la fecha de inicio para el filtro de rutinas.
        /// Es requerido si FetchAll es false.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateOnly? FechaInicio { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha de fin para el filtro de rutinas.
        /// Es requerido si FetchAll es false.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateOnly? FechaFin { get; set; }
    }

    /// <summary>
    /// Clase de respuesta para obtener la lista de rutinas de un usuario en el servicio.
    /// Extiende de BaseResponseSvc para incluir datos de respuesta comunes.
    /// </summary>
    public class ResponseGetListRutinasUsuarioSvc : BaseResponseSvc
    {
        /// <summary>
        /// Obtiene o establece la lista de datos de rutinas del usuario.
        /// </summary>
        [JsonProperty("data")]
        public List<ResponseGetRutinaData> Data { get; set; } = [];
    }
}
