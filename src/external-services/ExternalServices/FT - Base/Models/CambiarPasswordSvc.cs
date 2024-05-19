﻿using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class CambiarPasswordSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("old_password")]
        public string OldPassword { get; set; } = string.Empty;

        [JsonProperty("new_password")]
        public string NewPassword { get; set; } = string.Empty;

        [JsonProperty("attempt_change_date")]
        public DateTime AttemtPasswordChangeDate { get; set; } = DateTime.Now;
    }

    /// <summary>
    /// 
    /// </summary>
    public class CambiarPasswordSvcOut : BaseResponseSvc
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("change_date")]
        public DateTime PasswordChangeDate { get; set; } = DateTime.Now;
    }
}
