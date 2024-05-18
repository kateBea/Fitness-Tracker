using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    public class BaseResponseSvc
    {
        [JsonProperty("response_description")]
        public string ResponseDescription { get; set; }

        // success prop
    }
}
