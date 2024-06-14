using Microsoft.AspNetCore.Http;

namespace Shared.Contexts
{
    /// <summary>
    /// Primary constructor
    /// </summary>
    /// <param name="httpContextAccessor"></param>
    public class DataHttpContext(IHttpContextAccessor httpContextAccessor) : IDataHttpContext
    {
        #region Properties

        /// <summary>
        /// HTTP Data accessor
        /// </summary>
        private readonly IHttpContextAccessor _httpContextAccessor = httpContextAccessor;

        #endregion

        /// <summary>
        /// Obtiene el valor de un encabezado HTTP especificado por su nombre.
        /// </summary>
        /// <param name="name">El nombre del encabezado HTTP a obtener.</param>
        /// <returns>El valor del encabezado HTTP especificado.</returns>
        public string GetHeaderByName(string name)
        {
            return _httpContextAccessor.HttpContext.Request.Headers[name];
        }
    }
}
