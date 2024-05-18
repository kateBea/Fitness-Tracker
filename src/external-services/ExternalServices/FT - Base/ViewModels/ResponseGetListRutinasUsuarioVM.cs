using Newtonsoft.Json;
using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseGetListRutinasUsuarioVM : BaseResponse
    {
        [JsonProperty("rutinas")]
        public List<Rutina> Rutinas { get; set; } = [];

    }

    public class Rutina
    {
        [JsonProperty("_id")]
        public string Id { get; set; }

        [JsonProperty("tiempo_suenio")]
        public float TiempoSuenio { get; set; }

        [JsonProperty("calorias_quemadas")]
        public float CaloriasQuemadas { get; set; }

        [JsonProperty("pasos_realizados")]
        public int PasosRealizados { get; set; }

        [JsonProperty("frecuencia_cardiaca")]
        public float FrecuenciaCardiaca { get; set; }

        [JsonProperty("nivel_oxigeno_sangre")]
        public float NivelOxigenoSangre { get; set; }

        [JsonProperty("presion_arterial")]
        public float PresionArterial { get; set; }

        [JsonProperty("fecha_seguimiento")]
        public DateTime FechaSeguimiento { get; set; }

        [JsonProperty("comidas_consumidas")]
        public List<AlimentoConsumido> ComidasConsumidas { get; set; }
    }

    public class AlimentoConsumido
    {
        [JsonProperty("_id")]
        public string Id { get; set; }

        [JsonProperty("comida")]
        public Comida Comida { get; set; }

        [JsonProperty("tipo")]
        public string Tipo { get; set; }

        [JsonProperty("hora_consumo")]
        public DateTime HoraConsumo { get; set; }

        [JsonProperty("orden")]
        public string Orden { get; set; }
    }

    public class Comida
    {
        [JsonProperty("_id")]
        public string Id { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; }

        [JsonProperty("descripcion")]
        public string Descripcion { get; set; }

        [JsonProperty("unidades")]
        public int Unidades { get; set; }

        [JsonProperty("calorias")]
        public float Calorias { get; set; }

        [JsonProperty("carbohidratos")]
        public float Carbohidratos { get; set; }

        [JsonProperty("vitaminas")]
        public List<string> Vitaminas { get; set; }

        [JsonProperty("grasas")]
        public float Grasas { get; set; }
    }
}
