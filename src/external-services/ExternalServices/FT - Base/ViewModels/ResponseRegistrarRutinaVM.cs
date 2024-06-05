using Newtonsoft.Json;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseRegistrarRutinaVM
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public DateTime? CreatedAt { get; set; }
    }
}
