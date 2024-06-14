using Newtonsoft.Json;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de datos para registrar una rutina.
    /// Contiene diferentes parámetros que pueden ser registrados durante una rutina de ejercicio.
    /// </summary>
    public class RequestRegistrarRutina
    {
        /// <summary>
        /// Obtiene o establece el tiempo de sueño durante la rutina, en horas.
        /// </summary>
        public double TiempoDeSuenio { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la cantidad de calorías quemadas durante la rutina.
        /// </summary>
        public double CaloriasQuemadas { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la cantidad de pasos realizados durante la rutina.
        /// </summary>
        public int PasosRealizados { get; set; } = 0;

        /// <summary>
        /// Obtiene o establece la frecuencia cardíaca durante la rutina.
        /// </summary>
        public double FrecuenciaCardiaca { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece el nivel de oxígeno en sangre durante la rutina.
        /// </summary>
        public double NivelOxigenoSangre { get; set; } = 0.0;
    }
}

