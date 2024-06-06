using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseRegistrarRutinaVM : BaseResponse
    {
        public string Id { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public DateTime? CreatedAt { get; set; }
    }
}
