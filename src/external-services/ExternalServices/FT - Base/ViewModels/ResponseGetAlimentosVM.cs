using Shared.Utilities;
using static FT___Base.Models.ResponseGetAlimentosSvc;

namespace FT___Base.ViewModels
{
    public class ResponseGetAlimentosVM : BaseResponse
    {
        public List<GetAlimentosListItem>? Data { get; set; } = [];
    }
}
