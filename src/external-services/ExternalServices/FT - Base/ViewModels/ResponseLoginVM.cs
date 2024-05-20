using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseLoginVM : BaseResponse
    {
        public string Email { get; set; } = string.Empty;
        public string Name { get; set; } = string.Empty;
        public string FirstSurname { get; set; } = string.Empty;
        public string? SecondSurname { get; set; } = string.Empty;
        public string Token { get; set; } = string.Empty;
        public DateTime LoginDate { get; set; } = DateTime.Now;
        public DateTime TokenExpirationDate { get; set; } = DateTime.Now;
        public int TokenDuration { get; set; } = 0;
        
    }
}
