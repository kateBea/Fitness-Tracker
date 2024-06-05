using FT___Base.Models;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseRegistrarDietaVM : BaseResponse
    {
        public string Id { get; set; }
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
