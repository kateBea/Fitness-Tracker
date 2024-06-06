using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Utilities
{
    /// <summary>
    /// Base response
    /// </summary>
    public class BaseResponse
    {
        /// <summary>
        /// Wether the operation was successfull or not
        /// </summary>
        public bool Success { get; set; } = true;

        /// <summary>
        /// Response summary
        /// </summary>
        public string ResponseDescription { get; set; } = string.Empty;

        /// <summary>
        /// Operation errors
        /// </summary>
        public List<string> Errors { get; set; } = [];
    }
}
