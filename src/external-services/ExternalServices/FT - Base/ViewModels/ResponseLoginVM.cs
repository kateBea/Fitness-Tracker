using Shared.Utilities;

namespace FTBase.ViewModels
{
    /// <summary>
    /// Login response view model
    /// </summary>
    public class ResponseLoginVM : BaseResponse
    {
        /// <summary>
        /// Response data
        /// </summary>
        public ResponseLoginVMData? Data { get; set; } = new();

        /// <summary>
        /// Response login data model
        /// </summary>
        public class ResponseLoginVMData
        {
            /// <summary>
            /// User's name
            /// </summary>
            public string Name { get; set; } = string.Empty;

            /// <summary>
            /// User's nickname
            /// </summary>
            public string Username { get; set; } = string.Empty;

            /// <summary>
            /// Users first surname
            /// </summary>
            public string FirstSurname { get; set; } = string.Empty;

            /// <summary>
            /// User's second surname
            /// </summary>
            public string? SecondSurname { get; set; } = string.Empty;

            /// <summary>
            /// User's login token
            /// </summary>
            public string Token { get; set; } = string.Empty;

            /// <summary>
            /// Login date and time
            /// </summary>
            public DateTime LoginDate { get; set; } = DateTime.Now;

            /// <summary>
            /// Token expiration date and time
            /// </summary>
            public DateTime TokenExpirationDate { get; set; } = DateTime.Now;

            /// <summary>
            /// Token duration in seconds
            /// </summary>
            public int TokenDuration { get; set; } = 0;
        }
    }
}
