using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Classes
{
    public class BaseResponse
    {
        [JsonProperty("response_code")]
        public string ResponseCode { get; set; } = string.Empty;
        [JsonProperty("response_description")]
        public string ResponseDescription { get; set; } = string.Empty;
    }
}
