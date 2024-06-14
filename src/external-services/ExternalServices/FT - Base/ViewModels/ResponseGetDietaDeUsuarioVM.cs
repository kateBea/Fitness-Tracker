using FTBase.Models;
using Newtonsoft.Json;
using Shared.Utilities;
using static FTBase.Models.ResponseGetDietaUsuarioSvc;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta para obtener la dieta de un usuario.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseGetDietaDeUsuarioVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece los datos de la dieta del usuario.
        /// Puede ser nulo si el usuario no tiene una dieta registrada.
        /// </summary>
        public ResponseGetDietaUsuarioData? Data { get; set; } = new();
    }
}

