using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAlimentos.Models
{
    /// <summary>
    /// Login request external service model
    /// </summary>
    public class RequestLoginSvc
    {
        /// <summary>
        /// User's email
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// User's password
        /// </summary>
        [JsonProperty("password")]
        public string Password { get; set; } = string.Empty;
    }

    /// <summary>
    /// Login response external service model
    /// </summary>
    public class ResponseLoginSvc: BaseResponseSvc
    {
        /// <summary>
        /// User's email
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// User's nickname
        /// </summary>
        [JsonProperty("username")]
        public string Username { get; set; } = string.Empty;

        /// <summary>
        /// User's name
        /// </summary>
        [JsonProperty("name")]
        public string Name { get; set; } = string.Empty;

        /// <summary>
        /// User's first surname
        /// </summary>
        [JsonProperty("first_name")]
        public string FirstSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's second surname
        /// </summary>
        [JsonProperty("second_name")]
        public string? SecondSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's login date and time
        /// </summary>
        [JsonProperty("logged_at")]
        public DateTime? LoginDate { get; set; } = DateTime.Now;
    }
}
