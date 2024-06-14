using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para el inicio de sesión.
    /// Contiene los parámetros necesarios para autenticar a un usuario.
    /// </summary>
    public class RequestLogin : BaseRequest
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario.
        /// </summary>
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la contraseña del usuario.
        /// </summary>
        public string Password { get; set; } = string.Empty;
    }
}

