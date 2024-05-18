using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAlimentos.Models
{
    public class RequestLoginSvcIn
    {
        [JsonProperty("email")]
        public string Email { get; set; } = string.Empty;

        [JsonProperty("password")]
        public string Password { get; set; } = string.Empty;
    }

    /// <summary>
    /// 
    /// </summary>
    public class RequestLoginSvcOut: BaseResponseSvc
    {
        [JsonProperty("data")]
        public Cliente Data { get; set; }

        [JsonProperty("success")]
        public bool Success { get; set; }
    }

    public class Cliente
    {
        [JsonProperty("correo_electronico")]
        public string CorreoElectronico { get; set; }

        [JsonProperty("nombre_usuario")]
        public string NombreUsuario { get; set; }

        [JsonProperty("nombre")]
        public string Nombre { get; set; }

        [JsonProperty("primer_apellido")]
        public string PrimerApellido { get; set; }

        [JsonProperty("segundo_apellido")]
        public string SegundoApellido { get; set; }

        [JsonProperty("contrasenia")]
        public string Contrasenia { get; set; }

        [JsonProperty("fecha_nacimiento")]
        public DateTime FechaNacimiento { get; set; }

        [JsonProperty("fecha_registro")]
        public DateTime FechaRegistro { get; set; }

        [JsonProperty("altura")]
        public float Altura { get; set; }

        [JsonProperty("peso")]
        public float Peso { get; set; }

        [JsonProperty("sexo")]
        public string Sexo { get; set; }

        [JsonProperty("dietas")]
        public List<Dieta> Dietas { get; set; }

        [JsonProperty("rutinas")]
        public List<Rutina> Rutinas { get; set; }
    }

    public class Dieta
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("calorias_target")]
        public float CaloriasTarget { get; set; }

        [JsonProperty("fecha_inicio")]
        public DateTime FechaInicio { get; set; }

        [JsonProperty("fecha_fin")]
        public DateTime FechaFin { get; set; }

        [JsonProperty("comidas_sugeridas")]
        public List<Comida> ComidasSugeridas { get; set; }

        [JsonProperty("consumo_agua")]
        public float ConsumoAgua { get; set; }
    }

    public class Comida
    {
        [JsonProperty("id")]
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

    public class Rutina
    {
        [JsonProperty("id")]
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
        public List<Alimento> ComidasConsumidas { get; set; }
    }

    public class Alimento
    {
        [JsonProperty("id")]
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
}
