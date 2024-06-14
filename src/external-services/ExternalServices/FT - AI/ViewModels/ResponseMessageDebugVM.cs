using FTAI.Models;
using Newtonsoft.Json;
using Shared.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.ViewModels
{
    /// <summary>
    /// Respuesta mensaje de depuración
    /// </summary>
    public class ResponseMessageDebugVM : BaseResponse
    {
        /// <summary>
        /// Contenido de la respuesta
        /// </summary>
        public string? Result { get; set; } = string.Empty;
    }
}
