using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestRegistrarUsuario : BaseRequest
    {
        public string Email { get; set; } = string.Empty;
        public string Username { get; set; } = string.Empty;
        public string Password { get; set; } = string.Empty;

        // temporalmente opcionales
        public string? Name { get; set; } = string.Empty;
        public string? FirstSurname { get; set; } = string.Empty;
        public string? SecondSurname { get; set; } = string.Empty;
        public DateOnly? Birthday { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));
        public float? Height { get; set; } = 0.0f;
        public float? Weight { get; set; } = 0.0f;
        public string? Sex { get; set; } = string.Empty;
    }
}
