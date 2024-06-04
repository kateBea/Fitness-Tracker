using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestLogin : BaseRequest
    {
        public string Email { get; set; }
        /// <summary>
        /// 
        /// </summary>
        public string Password { get; set; } = string.Empty;
    }
}
