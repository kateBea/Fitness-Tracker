using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetDietaDeUsuario : BaseRequest
    {
        public string Id { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
    }
}
