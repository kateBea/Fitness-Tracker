using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Exceptions
{
    /// <summary>
    /// Representa una excepción personalizada utilizada para el manejo de errores específicos de asistencia.
    /// </summary>
    /// <remarks>
    /// Inicializa una nueva instancia de la clase <see cref="AssistanceException"/> con el mensaje de error especificado.
    /// </remarks>
    /// <param name="message">El mensaje que describe el error.</param>
    public class AssistanceException(string message) : Exception(message) {}
}

