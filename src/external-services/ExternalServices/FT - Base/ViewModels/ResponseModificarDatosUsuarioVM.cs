using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseModificarDatosUsuarioVM : BaseResponse
    {
        /// <summary>
        /// 
        /// </summary>
        public DateTime? ModifiedAt { get; set; } = DateTime.Now;
    }
}
