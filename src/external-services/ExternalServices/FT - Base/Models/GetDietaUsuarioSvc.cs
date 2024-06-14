using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener detalles de una dieta de usuario en el servicio.
    /// </summary>
    public class RequestGetDietaUsuarioSvc
    {
        /// <summary>
        /// Obtiene o establece el correo electrónico del usuario.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el identificador único de la dieta.
        /// </summary>
        [JsonProperty("dieta_id")]
        public string Id { get; set; } = string.Empty;
    }

    /// <summary>
    /// Modelo de respuesta para obtener detalles de una dieta de usuario desde el servicio.
    /// </summary>
    public class ResponseGetDietaUsuarioSvc : BaseResponseSvc
    {
        /// <summary>
        /// Obtiene o establece los datos de la dieta de usuario.
        /// </summary>
        [JsonProperty("data")]
        public ResponseGetDietaUsuarioData? Data { get; set; }
    }

    /// <summary>
    /// Clase de datos para representar la información de una dieta de usuario.
    /// </summary>
    public class ResponseGetDietaUsuarioData
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la dieta.
        /// </summary>
        [JsonProperty("id")]
        public string Id { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece las calorías objetivo de la dieta.
        /// </summary>
        [JsonProperty("calorias_target")]
        public float CaloriasTarget { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha de inicio de la dieta.
        /// </summary>
        [JsonProperty("fecha_inicio")]
        public DateTime? FechaInicio { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha de fin de la dieta.
        /// </summary>
        [JsonProperty("fecha_fin")]
        public DateTime? FechaFin { get; set; }

        /// <summary>
        /// Obtiene o establece la lista de comidas sugeridas para la dieta.
        /// </summary>
        [JsonProperty("comidas_sugeridas")]
        public List<ResponseGetDietaUsuarioDataComida> ComidasSugeridas { get; set; } = [];

        /// <summary>
        /// Obtiene o establece el consumo de agua recomendado para la dieta.
        /// </summary>
        [JsonProperty("consumo_agua")]
        public float ConsumoDeAgua { get; set; }

        /// <summary>
        /// Obtiene o establece si la dieta está activa.
        /// </summary>
        [JsonProperty("activa")]
        public bool Activa { get; set; }

        /// <summary>
        /// Obtiene o establece la fecha de registro de la dieta.
        /// </summary>
        [JsonProperty("fecha_registro")]
        public DateTime? FechaRegistro { get; set; }

        /// <summary>
        /// Obtiene o establece la última fecha de modificación de la dieta.
        /// </summary>
        [JsonProperty("ultima_modificacion")]
        public DateTime? FechaUltimaModificacion { get; set; }
    }

    /// <summary>
    /// Clase de datos para representar información detallada de una comida asociada a una dieta de usuario.
    /// </summary>
    public class ResponseGetDietaUsuarioDataComida
    {
        /// <summary>
        /// Obtiene o establece el identificador único de la comida.
        /// </summary>
        [JsonProperty("id")]
        public string Id { get; set; } = string.Empty;

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
        /// Obtiene o establece la cantidad de proteínas en la comida.
        /// </summary>
        [JsonProperty("proteinas")]
        public double Proteinas { get; set; }

        /// <summary>
        /// Obtiene o establece la cantidad de calorías en la comida.
        /// </summary>
        [JsonProperty("calorias")]
        public float Calorias { get; set; }

        /// <summary>
        /// Obtiene o establece la cantidad de carbohidratos en la comida.
        /// </summary>
        [JsonProperty("carbohidratos")]
        public float Carbohidratos { get; set; }

        /// <summary>
        /// Obtiene o establece la lista de vitaminas presentes en la comida.
        /// </summary>
        [JsonProperty("vitaminas")]
        public List<string> Vitaminas { get; set; } = [];

        /// <summary>
        /// Obtiene o establece la cantidad de grasas en la comida.
        /// </summary>
        [JsonProperty("grasas")]
        public double Grasas { get; set; } = 0f;

        /// <summary>
        /// Obtiene o establece el tipo de comida.
        /// </summary>
        [JsonProperty("tipo")]
        public string Tipo { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el orden de la comida en la dieta.
        /// </summary>
        [JsonProperty("orden")]
        public string Orden { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la fecha de registro de la comida.
        /// </summary>
        [JsonProperty("fecha_registro")]
        public DateTime? FechaRegistro { get; set; }
    }
}
