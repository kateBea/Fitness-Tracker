using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAlimentos.Models
{
    public class RequestLoginSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("password")]
        public string Password { get; set; } = string.Empty;
    }

    /// <summary>
    /// 
    /// </summary>
    public class RequestLoginSvcOut: BaseResponseSvc
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("name")]
        public string Name { get; set; } = string.Empty;

        [JsonProperty("first_name")]
        public string FirstSurname { get; set; } = string.Empty;

        [JsonProperty("second_name")]
        public string? SecondSurname { get; set; } = string.Empty;

        [JsonProperty("token")]
        public string? Token { get; set; } = string.Empty;

        [JsonProperty("logged_at")]
        public DateTime? LoginDate { get; set; } = DateTime.Now;

        [JsonProperty("token_expiration_date")]
        public DateTime? TokenExpirationDate { get; set; } = DateTime.Now;

        [JsonProperty("token_duration")]
        public int? TokenDuration { get; set; } = 0;
    }
}
