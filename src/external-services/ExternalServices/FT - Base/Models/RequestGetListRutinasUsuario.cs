using Newtonsoft.Json;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener la lista de rutinas de un usuario.
    /// Contiene los parámetros opcionales para filtrar las rutinas según diferentes criterios.
    /// </summary>
    public class RequestGetListRutinasUsuario
    {
        /// <summary>
        /// Obtiene o establece un valor que indica si se deben recuperar todas las rutinas del usuario.
        /// Si es verdadero, se ignoran los campos de fecha (FechaInicio y FechaFin).
        /// Si es falso, se recuperan las rutinas entre las fechas especificadas (FechaInicio y FechaFin).
        /// </summary>
        public bool? FetchAll { get; set; } = false;

        /// <summary>
        /// Obtiene o establece la fecha de inicio para filtrar las rutinas.
        /// Debe ser no nula si FetchAll es falso.
        /// </summary>
        public DateOnly? FechaInicio { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha de fin (no inclusiva) para filtrar las rutinas.
        /// Debe ser no nula si FetchAll es falso.
        /// </summary>
        public DateOnly? FechaFin { get; set; }
    }
}
