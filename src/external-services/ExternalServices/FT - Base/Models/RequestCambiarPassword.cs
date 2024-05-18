using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestCambiarPassword : BaseRequest
    {
        public string Email { get; set; } = string.Empty;
        public string OldPassword { get; set; } = string.Empty;
        public string NewPassword { get; set; } = string.Empty;
        public DateTime AttemtPasswordChangeDate { get; set; } = DateTime.Now;
    }
}
