using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Contexts
{
    public class DataHttpContext : IDataHttpContext
    {
        #region Properties

        private readonly IHttpContextAccessor _httpContextAccessor;

        #endregion

        public DataHttpContext(IHttpContextAccessor httpContextAccessor)
        {
            _httpContextAccessor = httpContextAccessor;
        }

        public string GetHeaderByName(string name)
        {
            return _httpContextAccessor.HttpContext.Request.Headers[name];
        }
    }
}
