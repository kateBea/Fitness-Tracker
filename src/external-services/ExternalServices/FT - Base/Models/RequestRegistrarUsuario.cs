using Shared.Utilities;

namespace FTBase.Models
{
    /// <summary>
    /// Register user request model
    /// </summary>
    public class RequestRegistrarUsuario : BaseRequest
    {
        /// <summary>
        /// User's email
        /// </summary>
        public string Email { get; set; } = string.Empty;

        /// <summary>
        /// User's nickname
        /// </summary>
        public string Username { get; set; } = string.Empty;

        /// <summary>
        /// User's password
        /// </summary>
        public string Password { get; set; } = string.Empty;

        /// <summary>
        /// User's name. Not required yet
        /// </summary>
        public string? Name { get; set; } = string.Empty;

        /// <summary>
        /// User's first surname. Not required yet
        /// </summary>
        public string? FirstSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's second surname. Not required yet
        /// </summary>
        public string? SecondSurname { get; set; } = string.Empty;

        /// <summary>
        /// User's birthday. Not required yet
        /// </summary>
        public DateOnly? Birthday { get; set; } = DateOnly.FromDateTime(DateTime.Now.AddYears(-18));

        /// <summary>
        /// User's height. Not required yet
        /// </summary>
        public float? Height { get; set; } = 0.0f;

        /// <summary>
        /// User's weight. Not required yet
        /// </summary>
        public float? Weight { get; set; } = 0.0f;

        /// <summary>
        /// User's sex. Not required yet
        /// </summary>
        public string? Sex { get; set; } = string.Empty;
    }
}
