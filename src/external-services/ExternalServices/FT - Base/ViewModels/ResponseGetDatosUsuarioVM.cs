using FT___Base.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseGetDatosUsuarioVM : BaseResponse
    {
        /// <summary>
        /// 
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDatosUsuarioSvcOutData? Data { get; set; } = new();
    }
}
