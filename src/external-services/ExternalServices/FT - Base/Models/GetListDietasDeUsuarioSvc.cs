using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetDietaUsuarioSvcOut;

namespace FT___Base.Models
{
    public class RequestGetListDietasDeUsuarioSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; }
    }

    public class ResponseGetListDietasDeUsuarioSvcOut : BaseResponse
    {
        [JsonProperty("data")]
        public List<ResponseGetDietaUsuarioData>? Data { get; set; } = [];
    }
}
