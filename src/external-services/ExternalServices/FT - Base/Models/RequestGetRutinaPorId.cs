namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener una rutina por su identificador único.
    /// Contiene el parámetro necesario para recuperar los detalles de una rutina específica.
    /// </summary>
    public class RequestGetRutinaPorId
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la rutina.
        /// </summary>
        public string Id { get; set; } = string.Empty;
    }
}
