using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    public class RequestGetDietaDeUsuario : BaseRequest
    {
        public string Id { get; set; } = string.Empty;
    }
}
