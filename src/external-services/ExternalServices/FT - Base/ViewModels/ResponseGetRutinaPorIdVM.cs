using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.ResponseGetRutinaPorIdSvc;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta para obtener una rutina por su ID.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseGetRutinaPorIdVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece los datos de la rutina obtenida por su ID.
        /// Puede ser nulo si no se encontró ninguna rutina con el ID especificado.
        /// </summary>
        public ResponseGetRutinaData? Data { get; set; } = new();
    }
}
