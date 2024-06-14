using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para registrar una nueva dieta mediante el servicio.
    /// </summary>
    public class RequestRegistrarDietaSvcIn
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario para el cual se registra la dieta.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece las calorías objetivo diarias para la dieta.
        /// </summary>
        [JsonProperty("calorias_target")]
        public double CaloriasTarget { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece la fecha de inicio de la dieta.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; } = DateTime.Now;

        /// <summary>
        /// Obtiene o establece la fecha de fin de la dieta.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; } = DateTime.Now.AddDays(2);

        /// <summary>
        /// Obtiene o establece la lista de comidas sugeridas para la dieta.
        /// </summary>
        [JsonProperty("comidas_sugeridas")]
        public List<ComidaSugeridaData> ComidasSugeridas { get; set; } = [];

        /// <summary>
        /// Obtiene o establece el consumo diario de agua en litros para la dieta.
        /// </summary>
        [JsonProperty("consumo_agua")]
        public double ConsumoDeAgua { get; set; }

        /// <summary>
        /// Clase interna que representa los datos de una comida sugerida en la dieta.
        /// </summary>
        public class ComidaSugeridaData
        {
            /// <summary>
            /// Obtiene o establece el identificador único de la comida sugerida.
            /// </summary>
            [JsonProperty("id")]
            public string IdComida { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el orden de la comida en la dieta.
            /// </summary>
            [JsonProperty("orden")]
            public string Orden { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el tipo de comida.
            /// </summary>
            [JsonProperty("tipo")]
            public string Tipo { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece el nombre de la comida.
            /// </summary>
            [JsonProperty("nombre")]
            public string Nombre { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece la descripción de la comida.
            /// </summary>
            [JsonProperty("descripcion")]
            public string Descripcion { get; set; } = string.Empty;

            /// <summary>
            /// Obtiene o establece las calorías de la comida.
            /// </summary>
            [JsonProperty("calorias")]
            public double Calorias { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece la cantidad de proteínas en la comida.
            /// </summary>
            [JsonProperty("proteinas")]
            public double Proteinas { get; set; }

            /// <summary>
            /// Obtiene o establece la cantidad de grasas en la comida.
            /// </summary>
            [JsonProperty("grasas")]
            public double Grasas { get; set; } = 0f;
             
            /// <summary>
            /// Obtiene o establece la cantidad de carbohidratos en la comida.
            /// </summary>
            [JsonProperty("carbohidratos")]
            public double Carbohidratos { get; set; } = 0f;

            /// <summary>
            /// Obtiene o establece la lista de vitaminas presentes en la comida.
            /// </summary>
            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; } = [];
        }
    }

    /// <summary>
    /// Modelo de respuesta para la operación de registro de dieta mediante el servicio.
    /// </summary>
    public class ResponseRegistrarDietaOut : BaseResponseSvc
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta registrada.
        /// </summary>
        [JsonProperty("id")]
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la fecha y hora de creación de la dieta.
        /// </summary>
        [JsonProperty("created_at")]
        public DateTime? CreatedAt { get; set; } = DateTime.Now;
    }
}
