using Newtonsoft.Json;
using Shared.Utilities;
using System.Text.Json.Serialization;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener una rutina por su identificador en el servicio.
    /// </summary>
    public class RequestGetRutinaPorIdSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico asociado a la solicitud de rutina.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el identificador único de la rutina.
        /// </summary>
        [JsonProperty("rutina_id")]
        public string Id { get; set; } = string.Empty;
    }

    /// <summary>
    /// Clase de respuesta para obtener los detalles de una rutina por su identificador en el servicio.
    /// Hereda de BaseResponseSvc.
    /// </summary>
    public class ResponseGetRutinaPorIdSvc : BaseResponseSvc
    {
        /// <summary>
        /// Obtiene o establece los datos de la rutina solicitada.
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetRutinaData? Data { get; set; } = new();

        /// <summary>
        /// Clase interna que define los datos específicos de una rutina.
        /// </summary>
        public class ResponseGetRutinaData
        {
            /// <summary>
            /// Obtiene o establece el identificador único de la rutina.
            /// </summary>
            [JsonProperty("id")]
            public string Id { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el tiempo de sueño registrado durante la rutina.
            /// </summary>
            [JsonProperty("tiempo_suenio")]
            public double TiempoDeSuenio { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece las calorías quemadas durante la rutina.
            /// </summary>
            [JsonProperty("calorias_quemadas")]
            public double CaloriasQuemadas { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece la cantidad de pasos realizados durante la rutina.
            /// </summary>
            [JsonProperty("pasos_realizados")]
            public int PasosRealizados { get; set; } = 0;

            /// <summary>
            /// Obtiene o establece la frecuencia cardíaca registrada durante la rutina.
            /// </summary>
            [JsonProperty("frecuencia_cardiaca")]
            public double FrecuenciaCardiaca { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece el nivel de oxígeno en sangre registrado durante la rutina.
            /// </summary>
            [JsonProperty("nivel_oxigeno_sangre")]
            public double NivelOxigenoSangre { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece la fecha de seguimiento de la rutina.
            /// </summary>
            [JsonProperty("fecha_seguimiento")]
            public DateOnly? FechaSeguimiento { get; set; }

            /// <summary>
            /// Obtiene o establece la última fecha de modificación de la rutina.
            /// </summary>
            [JsonProperty("ultima_modificacion")]
            public DateTime? FechaUltimaModificacion { get; set; }

            /// <summary>
            /// Obtiene o establece la lista de comidas consumidas durante la rutina.
            /// </summary>
            [JsonProperty("comidas_consumidas")]
            public List<AlimentoInfo> ComidasConsumidas { get; set; } = [];
        }

        /// <summary>
        /// Clase que define la información de un alimento consumido durante la rutina.
        /// </summary>
        public class AlimentoInfo
        {
            /// <summary>
            /// Obtiene o establece el identificador del alimento consumido.
            /// </summary>
            [JsonProperty("comida_id")]
            public string ComidaId { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el nombre del alimento consumido.
            /// </summary>
            [JsonProperty("nombre")]
            public string Nombre { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el tipo de alimento consumido.
            /// </summary>
            [JsonProperty("tipo")]
            public string Tipo { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece la hora de consumo del alimento.
            /// </summary>
            [JsonProperty("hora_consumo")]
            public DateTime? HoraConsumo { get; set; }

            /// <summary>
            /// Obtiene o establece el orden del alimento consumido.
            /// </summary>
            [JsonProperty("orden")]
            public string Orden { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece la descripción del alimento consumido.
            /// </summary>
            [JsonProperty("descripcion")]
            public string Descripcion { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece las calorías del alimento consumido.
            /// </summary>
            [JsonProperty("calorias")]
            public double Calorias { get; set; } = 0;

            /// <summary>
            /// Obtiene o establece las proteínas del alimento consumido.
            /// </summary>
            [JsonProperty("proteinas")]
            public double Proteinas { get; set; } = 0;

            /// <summary>
            /// Obtiene o establece las grasas del alimento consumido.
            /// </summary>
            [JsonProperty("grasas")]
            public double Grasas { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece los carbohidratos del alimento consumido.
            /// </summary>
            [JsonProperty("carbohidratos")]
            public double Carbohidratos { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece la lista de vitaminas del alimento consumido.
            /// </summary>
            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; } = [];
        }
    }
}
