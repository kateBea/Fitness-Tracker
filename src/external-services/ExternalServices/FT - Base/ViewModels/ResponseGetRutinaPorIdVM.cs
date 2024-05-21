using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetRutinaPorIdSvcOut;

namespace FT___Base.ViewModels
{
    public class ResponseGetRutinaPorIdVM : BaseResponse
    {
        public ResponseGetRutinaData? Data { get; set; }
    }
}
