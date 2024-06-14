using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.ResponseGetRutinaPorIdSvc;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta para obtener la lista de rutinas de un usuario.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseGetListRutinasUsuarioVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la lista de datos de rutinas del usuario.
        /// Puede ser una lista vacía si el usuario no tiene rutinas registradas.
        /// </summary>
        public List<ResponseGetRutinaData>? Data { get; set; } = new List<ResponseGetRutinaData>();
    }
}