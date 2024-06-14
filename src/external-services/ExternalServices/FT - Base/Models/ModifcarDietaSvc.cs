using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para modificar una dieta en el servicio.
    /// </summary>
    public class RequestModifcarDietaSvc
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta a modificar.
        /// </summary>
        [JsonProperty("dieta_id")]
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el correo electrónico asociado a la dieta.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la fecha de inicio de la dieta.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de fin de la dieta.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(1);

        /// <summary>
        /// Obtiene o establece un valor que indica si la dieta está activa o no.
        /// </summary>
        [JsonProperty("activa")]
        public bool Activa { get; set; } = false;
    }

    /// <summary>
    /// Modelo de respuesta para modificar una dieta en el servicio.
    /// </summary>
    public class ResponseModifcarDietaSvcOut : BaseResponseSvc
    {
        /// <summary>
        /// Obtiene o establece la fecha de modificación de la dieta.
        /// </summary>
        [JsonProperty("created_at")]
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
