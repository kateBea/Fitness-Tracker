using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    public class BaseResponse
    {
        /// <summary>
        /// 
        /// </summary>
        public bool Success { get; set; } = true;

        /// <summary>
        /// 
        /// </summary>
        public string ResponseCode { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public string ResponseDescription { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public List<string> Errors { get; set; } = [];
    }
}
