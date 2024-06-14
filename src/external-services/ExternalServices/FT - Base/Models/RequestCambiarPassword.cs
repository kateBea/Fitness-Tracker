using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Request change password model
    /// </summary>
    public class RequestCambiarPassword : BaseRequest
    {
        /// <summary>
        /// Old passowrd
        /// </summary>
        public string OldPassword { get; set; } = string.Empty;

        /// <summary>
        /// New password
        /// </summary>
        public string NewPassword { get; set; } = string.Empty;
    }
}
