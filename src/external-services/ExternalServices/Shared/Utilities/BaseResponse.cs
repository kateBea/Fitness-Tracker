using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// Base response
    /// </summary>
    public class BaseResponse
    {
        /// <summary>
        /// Wether the operation was successfull or not
        /// </summary>
        [JsonProperty("success")]
        public bool Success { get; set; } = true;

        /// <summary>
        /// Response summary
        /// </summary>
        [JsonProperty("response_description")]
        public string ResponseDescription { get; set; } = string.Empty;

        /// <summary>
        /// Operation errors
        /// </summary>
        public List<string> Errors { get; set; } = [];
    }
}
