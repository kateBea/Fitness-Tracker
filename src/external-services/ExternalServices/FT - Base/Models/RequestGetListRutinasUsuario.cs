namespace FT___Base.Models
{
    public class RequestGetListRutinasUsuario
    {
        /// <summary>
        /// Si es cierto, se recogen todas las rutinas, si no, se recogen
        /// las rutinas en el rango de fechas especificado.
        /// </summary>
        public bool RetrieveAll { get; set; } = true;

        /// <summary>
        /// 
        /// </summary>
        public DateTime EndDate { get; set; } = DateTime.Now.AddDays(-5);

        /// <summary>
        /// 
        /// </summary>
        public DateTime StartDate { get; set; } = DateTime.Now;
    }
}
