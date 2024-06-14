using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.Models
{
    /// <summary>
    /// Clase que representa una solicitud para generar una dieta.
    /// </summary>
    public class RequestGenerarDieta : BaseRequest
    {
        /// <summary>
        /// Fecha de nacimiento del usuario.
        /// </summary>
        public DateOnly FechaNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// Sexo del usuario.
        /// </summary>
        public string Sexo { get; set; } = string.Empty;

        /// <summary>
        /// Altura del usuario en centímetros.
        /// </summary>
        public int Altura { get; set; }

        /// <summary>
        /// Nivel de actividad física del usuario.
        /// </summary>
        public string NivelActividadFisica { get; set; } = string.Empty;

        /// <summary>
        /// Objetivo principal de la dieta.
        /// </summary>
        public string? ObjetivoPrincipal { get; set; } = string.Empty;

        /// <summary>
        /// Restricciones alimenticias del usuario, por ejemplo:
        /// Vegetariano / Vegano / Sin Gluten / Sin Lácteos / Sin Frutos Secos.
        /// Probablemente se cambiará a una lista de strings.
        /// </summary>
        public string? RestricionesAlimenticias { get; set; } = string.Empty;

        /// <summary>
        /// Comentarios adicionales del usuario sobre la dieta.
        /// </summary>
        public string? ComentariosAdicionales { get; set; } = string.Empty;

        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        public DateOnly FechaInicio { get; set; } = DateOnly.FromDateTime(DateTime.Now);

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        public DateOnly FechaFin { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddDays(7));

        /// <summary>
        /// Lista de comidas preferidas para esta dieta.
        /// </summary>
        [JsonIgnore]
        public List<ComidaDieta> PreferenciasAlimenticias { get; set; } = [];
    }
}
