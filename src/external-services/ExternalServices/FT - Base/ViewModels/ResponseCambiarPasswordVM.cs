using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// Change password response view model
    /// </summary>
    public class ResponseCambiarPasswordVM : BaseResponse
    {
        /// <summary>
        /// Password change date, not null if the operation is successfull
        /// </summary>
        public DateTime? PasswordChangeDate { get; set; }
    }
}
