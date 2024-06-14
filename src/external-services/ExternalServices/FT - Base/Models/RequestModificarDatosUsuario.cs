using Newtonsoft.Json;

namespace FTBase.Models
{
    /// <summary>
    /// Modelo de datos para modificar los datos de un usuario existente.
    /// Contiene los parámetros necesarios para actualizar la información personal de un usuario.
    /// </summary>
    public class RequestModificarDatosUsuario
    {
        /// <summary>
        /// Obtiene o establece el nombre de usuario.
        /// </summary>
        public string NombreUsuario { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el nombre del usuario.
        /// </summary>
        public string Nombre { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el primer apellido del usuario.
        /// </summary>
        public string PrimerApellido { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el segundo apellido del usuario.
        /// </summary>
        public string SegundoApellido { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece la URL de la imagen del usuario.
        /// </summary>
        public string Imagen { get; set; } = string.Empty;

        /// <summary>
        /// Obtiene o establece el objetivo de peso del usuario en kilogramos.
        /// </summary>
        public double ObjetivoPeso { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece la fecha de nacimiento del usuario.
        /// </summary>
        public DateOnly FechaDeNacimiento { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// Obtiene o establece la altura del usuario en centímetros.
        /// </summary>
        public double Altura { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece el peso actual del usuario en kilogramos.
        /// </summary>
        public double Peso { get; set; } = 0.0;

        /// <summary>
        /// Obtiene o establece el sexo del usuario.
        /// </summary>
        public string Sexo { get; set; } = string.Empty;
    }
}

