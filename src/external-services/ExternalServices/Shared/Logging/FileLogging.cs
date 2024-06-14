namespace FTAI.Logging
{
    /// <summary>
    /// Clase estática para gestionar el registro de logs en archivos.
    /// </summary>
    public static class FileLogging
    {
        /// <summary>
        /// Registra el contenido proporcionado en un archivo de log.
        /// </summary>
        /// <param name="contents">El contenido a registrar en el archivo de log.</param>
        /// <param name="path">La ruta del archivo de log. Por defecto es "logs/output.log".</param>
        /// <returns>Devuelve true si el registro se realizó con éxito.</returns>
        public static bool LogToFile(string contents, string path = "logs/output.log")
        {
            // Reemplazar el nombre del log para que tenga el formato año-mes-dia-log.log
            var exists = Directory.Exists("logs");

            if (!exists)
            {
                Directory.CreateDirectory("logs");
            }

            // Escribe el array de cadenas en un nuevo archivo llamado "WriteLines.txt".
            using StreamWriter outputFile = (File.Exists(path)) ? File.AppendText(path) : File.CreateText(path);
            outputFile.WriteLine($"{DateTime.Now} ===========================================================");
            outputFile.WriteLine(contents);
            outputFile.WriteLine($"==========================================================================\n");

            return true;
        }
    }
}
