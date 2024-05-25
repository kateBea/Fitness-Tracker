using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseGetListRutinasUsuarioVM : BaseResponse
    {
        public List<ResponseGetRutinaData>? Data { get; set; } = [];
    }
}
