namespace FT___Base.ViewModels
{
    public class ResponseLoginVM
    {
        public string Email { get; set; } = string.Empty;
        public string Username { get; set; } = string.Empty;
        public string Token { get; set; } = string.Empty;
        public DateTime LoginDate { get; set; } = DateTime.Now;
        public int TokenDuration { get; set; } = 0;
        
    }
}
