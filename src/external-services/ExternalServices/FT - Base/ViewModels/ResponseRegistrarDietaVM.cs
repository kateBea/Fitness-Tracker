using FT___Base.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseRegistrarDietaVM : BaseResponse
    {
        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
