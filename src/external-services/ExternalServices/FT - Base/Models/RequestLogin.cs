using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestLogin : BaseRequest
    {
        /// <summary>
        /// 
        /// </summary>
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public string Password { get; set; } = string.Empty;
    }
}
