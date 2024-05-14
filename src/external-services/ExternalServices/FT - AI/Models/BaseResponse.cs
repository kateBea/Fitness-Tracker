using Newtonsoft.Json;

namespace FTAI.Models
{
    public class BaseResponse
    {
        [JsonProperty("response_code")]
        public string ResponseCode { get; set; } = string.Empty;
        [JsonProperty("response_description")]
        public string ResponseDescription { get; set; } = string.Empty;
    }
}
