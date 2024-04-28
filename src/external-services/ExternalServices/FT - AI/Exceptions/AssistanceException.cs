using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Exceptions
{
    /// <summary>
    /// 
    /// </summary>
    public class AssistanceException : Exception
    {
        #region Properties
        /// <summary>
        /// 
        /// </summary>
        public string Message { get; set; } = string.Empty;
        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <param name="message"></param>
        public AssistanceException(string message)
        {
            Message = message;
        }
    }
}
