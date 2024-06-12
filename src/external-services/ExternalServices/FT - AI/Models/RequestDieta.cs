using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAI.Models
{
    /// <summary>
    /// 
    /// </summary>
    public class RequestGenerarDieta : BaseRequest
    {
        /// <summary>
        /// 
        /// </summary>
        public DateOnly FechaNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// 
        /// </summary>
        public string Sexo { get; set; } = string.Empty;

        /// <summary>
        /// Altura en centímetros.
        /// </summary>
        public int Altura { get; set; }

        /// <summary>
        /// Nivel de actividad física.
        /// </summary>
        public string NivelActividadFisica { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public string? ObjetivoPrincipal { get; set; } = string.Empty;

        /// <summary>
        /// Probablemente se cambiará a una lista de strings, conteniendo las restricciones alimenticias:
        /// Vegetariano / Vegano / Sin Gluten / Sin Lácteos / Sin Frutos Secos
        /// </summary>
        public string? RestricionesAlimenticias { get; set; } = string.Empty;

        /// <summary>
        /// 
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
        /// Comidas preferidas para esta dieta.
        /// </summary>
        [JsonIgnore]
        public List<ComidaDieta> PreferenciasAlimenticias { get; set; } = [];
    }
}
