using FT___Base.Models;
using Newtonsoft.Json;
using Shared.Utilities;
using static FT___Base.Models.ResponseGetDietaUsuarioSvcOut;

namespace FT___Base.ViewModels
{
    public class ResponseGetDietaDeUsuarioVM : BaseResponse
    {
        public ResponseGetDietaUsuarioData? Data { get; set; }
    }
}
