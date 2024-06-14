using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Representa la respuesta a la solicitud de modificar datos de usuario.
    /// Extiende la clase base de respuestas.
    /// </summary>
    public class ResponseModificarDatosUsuarioVM : BaseResponse
    {
        /// <summary>
        /// Obtiene o establece la fecha y hora en que se modificaron los datos del usuario.
        /// Por defecto, se establece como la fecha y hora actual.
        /// </summary>
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}