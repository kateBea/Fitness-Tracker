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
        public DateTime FechaNacimiento { get; set; } = DateTime.Now;

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
        /// 
        /// </summary>
        public double TargetCalories { get; set; } = 0.0f;

        /// <summary>
        /// Probablemente se cambiará a una lista de strings, conteniendo las restricciones alimenticias:
        /// Vegetariano / Vegano / Sin Gluten / Sin Lácteos / Sin Frutos Secos
        /// </summary>
        public string? RestricionesAlimenticias { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public string? HabilidadCulinaria { get; set; } = string.Empty;

        /// <summary>
        /// 
        /// </summary>
        public string? ComentariosAdicionales { get; set; } = string.Empty;

        /// <summary>
        /// Fecha de inicio de la dieta.
        /// </summary>
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Fecha de finalización de la dieta.
        /// </summary>
        public DateTime FechaFin { get; set; } = DateTime.Now;

        /// <summary>
        /// Consuma de agua diario en litros.
        /// </summary>
        public double ConsumoAgua = 0.0f;

        /// <summary>
        /// Comidas preferidas para esta dieta.
        /// </summary>
        //public List<ComidaDieta> PreferenciasAlimenticias { get; set; } = [];
    }
}
