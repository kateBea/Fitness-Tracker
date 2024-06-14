using Newtonsoft.Json;
using Shared.Utilities;
using System.Text.Json.Serialization;
using static FTBase.Models.ResponseGetDietaUsuarioSvc;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener la lista de dietas de un usuario en el servicio.
    /// </summary>
    public class RequestGetListDietasDeUsuarioSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;
    }

    /// <summary>
    /// Clase de respuesta para obtener la lista de dietas de un usuario en el servicio.
    /// Extiende de BaseResponse para incluir atributos comunes de respuesta.
    /// </summary>
    public class ResponseGetListDietasDeUsuarioSvcOut : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la lista de datos de dietas del usuario.
        /// </summary>
        [JsonProperty("data")]
        public List<ResponseGetDietaUsuarioData>? Data { get; set; } = [];
    }
}
