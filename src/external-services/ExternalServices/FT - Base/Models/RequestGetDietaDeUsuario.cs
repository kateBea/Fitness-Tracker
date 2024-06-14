using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener la dieta de un usuario por su identificador.
    /// </summary>
    public class RequestGetDietaDeUsuario : BaseRequest
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta.
        /// </summary>
        public string Id { get; set; } = string.Empty;
    }
}
