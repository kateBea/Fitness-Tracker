using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// Base response for external services
    /// </summary>
    public class BaseResponseSvc
    {
        /// <summary>
        /// Response description
        /// </summary>
        [JsonProperty("response_description")]
        public string ResponseDescription { get; set; } = string.Empty;

        /// <summary>
        /// Wether the requested operation succeded or not
        /// </summary>
        [JsonProperty("success")]
        public bool Success { get; set; } = true;
    }
}
