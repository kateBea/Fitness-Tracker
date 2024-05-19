using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetDietaUsuarioSvcOut;

namespace FT___Base.ViewModels
{
    public class ResponseRequestGetListDietasDeUsuarioVM : BaseResponse
    {
        [JsonProperty("data")]
        public List<ResponseGetDietaUsuarioData>? Data { get; set; } = [];
    }
}
