using AppFitnessTrackerAI.Classes;
using AppFitnessTrackerAI.Services.Interface;
using Microsoft.AspNetCore.Mvc;

namespace FitnessTrackerAI.Controllers.v1
{
    [ApiController]
    [Route("[controller]")]
    public class AssistanceController : ControllerBase
    {
        private readonly IAssistanceService _assistanceService;

        /// <summary>
        /// 
        /// </summary>
        public AssistanceController(IAssistanceService assistanceService)
        {
            _assistanceService = assistanceService;
        }

        [HttpGet]
        public ActionResult<ModelDebug> Index()
        {
            // Validate entry model

            // manage exceptions and other stuff

            // return result

            return _assistanceService.Get(1);
        }
    }
}
