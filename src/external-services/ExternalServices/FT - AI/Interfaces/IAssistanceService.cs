using FTAI.Models;
using FTAI.ViewModels;

namespace FTAI.Interfaces
{
    /// <summary>
    /// Interfaz del asistente virtual.
    /// </summary>
    public interface IAssistanceService
    {
        /// <summary>
        /// Solo para fines de depuración. Utiliza configuraciones predeterminadas para solicitar respuestas a los endpoints de OpenAI.
        /// </summary>
        /// <param name="message">El mensaje del usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. El resultado de la tarea contiene una respuesta del modelo de vista <see cref="ResponseMessageDebugVM"/>.</returns>
        Task<ResponseMessageDebugVM> Ask(string message);

        /// <summary>
        /// Completa el chat básico para fines de depuración. Utiliza un máximo de 100 tokens, por lo que la respuesta puede cortarse si es más larga. El modelo utilizado es GPT-4 Turbo Vision.
        /// </summary>
        /// <param name="model">El modelo de solicitud del usuario que contiene el mensaje.</param>
        /// <returns>Una tarea que representa la operación asincrónica. El resultado de la tarea contiene una respuesta del modelo de vista <see cref="ResponseMessageDebugVM"/>.</returns>
        Task<ResponseMessageDebugVM> AskWithTokenLimit(RequestMessageDebugLimitTokens model);

        /// <summary>
        /// Genera un plan de dieta basado en la solicitud proporcionada.
        /// </summary>
        /// <param name="dieta">El modelo de solicitud que contiene los parámetros para la generación de la dieta.</param>
        /// <returns>Una tarea que representa la operación asincrónica. El resultado de la tarea contiene una respuesta del modelo de vista <see cref="ResponseGenerarDietaVM"/>.</returns>
        Task<ResponseGenerarDietaVM> GenerarDieta(RequestGenerarDieta dieta);

        /// <summary>
        /// Inicia una nueva sesión de chat con el asietnte virtual.
        /// </summary>
        /// <param name="chatInfo">El modelo de solicitud que contiene los parámetros para iniciar el chat.</param>
        /// <returns>Una tarea que representa la operación asincrónica. El resultado de la tarea contiene una respuesta del modelo de vista <see cref="ResponseStartNewChatAssistanceVM"/>.</returns>
        Task<ResponseStartNewChatAssistanceVM> StartNewChat(RequestStartNewChatAssistance chatInfo);
    }
}

