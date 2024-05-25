using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseModifcarDietaVM : BaseResponse
    {
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
