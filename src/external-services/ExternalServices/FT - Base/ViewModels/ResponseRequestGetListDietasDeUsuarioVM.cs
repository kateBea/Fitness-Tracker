using FTBase.Models;
using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.ResponseGetDietaUsuarioSvc;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de obtener la lista de dietas de un usuario.
    /// </summary>
    public class ResponseRequestGetListDietasDeUsuarioVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece los datos de las dietas del usuario.
        /// </summary>
        [JsonProperty("data")]
        public List<ResponseGetDietaUsuarioData>? Data { get; set; } = [];
    }
}
