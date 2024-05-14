namespace FTAI.Models
{
    /// <summary>
    /// This model is only used for debug purposes.
    /// </summary>
    public class ModelDebug
    {
        /// <summary>
        /// Message to send.
        /// </summary>
        public string Message { get; set; } = string.Empty;

        /// <summary>
        /// Maximum amount of tokens. Default is 300
        /// </summary>
        public int MaxTokens { get; set; } = 300;
    }
}
