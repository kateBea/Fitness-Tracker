using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Password change external service request model
    /// </summary>
    public class RequestCambiarPasswordSvc
    {
        /// <summary>
        /// User's email
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// User's old password
        /// </summary>
        [JsonProperty("old_password")]
        public string OldPassword { get; set; } = string.Empty;

        /// <summary>
        /// User's new password
        /// </summary>
        [JsonProperty("new_password")]
        public string NewPassword { get; set; } = string.Empty;
    }

    /// <summary>
    /// Password change external service response model
    /// </summary>
    public class ResponseCambiarPasswordSvc : BaseResponseSvc
    {
        /// <summary>
        /// Change date and time
        /// </summary>
        [JsonProperty("change_date")]
        public DateTime? PasswordChangeDate { get; set; } = DateTime.Now;
    }
}
