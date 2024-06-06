using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    public class ResponseModificarRutinaVM : BaseResponse
    {
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
