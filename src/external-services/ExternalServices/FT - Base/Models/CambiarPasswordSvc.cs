using Shared.Utilities;

namespace FT___Base.Models
{
    public class CambiarPasswordSvcIn
    {
        public string Email { get; set; } = string.Empty;
        public string OldPassword { get; set; } = string.Empty;
        public string NewPassword { get; set; } = string.Empty;
        public DateTime AttemtPasswordChangeDate { get; set; } = DateTime.Now;
    }

    public class CambiarPasswordSvcOut : BaseResponseSvc
    {
        public DateTime PasswordChangeDate { get; set; } = DateTime.Now;
    }
}
