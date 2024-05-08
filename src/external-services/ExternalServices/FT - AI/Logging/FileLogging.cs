namespace FTAI.Logging
{
    public static class FileLogging
    {
        public static bool LogToFile(string contents, string path = "logs/output.log")
        {
            // Replace log name to format year-mnth-day-log.log

            // Write the string array to a new file named "WriteLines.txt".
            using StreamWriter outputFile = File.AppendText(path);
            outputFile.WriteLine($"{DateTime.Now} ===========================================================");
            outputFile.WriteLine(contents);
            outputFile.WriteLine($"==========================================================================\n");

            return true;
        }
    }
}
