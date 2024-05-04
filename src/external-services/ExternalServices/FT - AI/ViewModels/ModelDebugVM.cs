using FTAI.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.ViewModels
{
    public class ModelDebugVM : BaseResponse
    {
        [JsonProperty("result")]
        public string? Result { get; set; } = string.Empty;
    }
}
