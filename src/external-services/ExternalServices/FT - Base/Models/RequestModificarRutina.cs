using Newtonsoft.Json;
using static FTBase.Models.ResponseGetRutinaPorIdSvc;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de datos para modificar una rutina existente.
    /// Contiene los parámetros necesarios para actualizar la información de una rutina.
    /// </summary>
    public class RequestModificarRutina
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la rutina a modificar.
        /// </summary>
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el tiempo de sueño registrado durante la rutina, en horas.
        /// </summary>
        public double TiempoDeSuenio { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece las calorías quemadas durante la rutina.
        /// </summary>
        public double CaloriasQuemadas { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece el número de pasos realizados durante la rutina.
        /// </summary>
        public int PasosRealizados { get; set; } = 0;

        /// <summary>
        /// Obtiene o establece la frecuencia cardíaca registrada durante la rutina.
        /// </summary>
        public double FrecuenciaCardiaca { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece el nivel de oxígeno en sangre registrado durante la rutina.
        /// </summary>
        public double NivelOxigenoSangre { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la lista de alimentos consumidos durante la rutina.
        /// </summary>
        public List<AlimentoInfo> ComidasConsumidas { get; set; } = [];
    }
}
