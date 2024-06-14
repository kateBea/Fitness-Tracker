using Newtonsoft.Json;
using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de solicitud para obtener alimentos por servicio.
    /// </summary>
    public class RequestGetAlimentosSvc : BaseRequest
    {
        /// <summary>
        /// Dirección de correo electrónico asociada al usuario.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;
    }

    /// <summary>
    /// Modelo de respuesta para obtener alimentos por servicio.
    /// </summary>
    public class ResponseGetAlimentosSvc : BaseResponseSvc
    {
        /// <summary>
        /// Lista de alimentos obtenidos como resultado de la solicitud.
        /// </summary>
        [JsonProperty("data")]
        public List<GetAlimentosListItem>? Data { get; set; } = [];

        /// <summary>
        /// Representa un elemento de la lista de alimentos obtenidos.
        /// </summary>
        public class GetAlimentosListItem
        {
            /// <summary>
            /// Identificador único del alimento.
            /// </summary>
            [JsonProperty("id")]
            public string Id { get; set; } = string.Empty;

            /// <summary>
            /// Nombre del alimento.
            /// </summary>
            [JsonProperty("nombre")]
            public string Nombre { get; set; } = string.Empty;

            /// <summary>
            /// Descripción del alimento.
            /// </summary>
            [JsonProperty("descripcion")]
            public string Descripcion { get; set; } = string.Empty;

            /// <summary>
            /// Cantidad de calorías por porción del alimento.
            /// </summary>
            [JsonProperty("calorias")]
            public double Calorias { get; set; } = 0f;

            /// <summary>
            /// Contenido de proteínas por porción del alimento.
            /// </summary>
            [JsonProperty("proteinas")]
            public double Proteinas { get; set; } = 0f;

            /// <summary>
            /// Contenido de grasas por porción del alimento.
            /// </summary>
            [JsonProperty("grasas")]
            public double Grasas { get; set; } = 0f;

            /// <summary>
            /// Contenido de carbohidratos por porción del alimento.
            /// </summary>
            [JsonProperty("carbohidratos")]
            public double Carbohidratos { get; set; } = 0f;

            /// <summary>
            /// Lista de vitaminas contenidas en el alimento.
            /// </summary>
            [JsonProperty("vitaminas")]
            public List<string> Vitaminas { get; set; } = [];

            /// <summary>
            /// Fecha de registro del alimento en el sistema.
            /// </summary>
            [JsonProperty("fecha_registro")]
            public DateTime FechaRegistro { get; set; } = DateTime.Now;

            /// <summary>
            /// Fecha de última modificación del alimento en el sistema.
            /// </summary>
            [JsonProperty("ultima_modificacion")]
            public DateTime FechaUltimaModificacion { get; set; } = DateTime.Now;
        }
    }
}
