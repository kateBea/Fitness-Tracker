using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAlimentos.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseFoodParseVM : BaseResponse
    {
        public ResponseFoodParser? Result { get; set; }
    }

    /// <summary>
    /// 
    /// </summary>
    public class ResponseNutrientsVM : BaseResponse
    {
        public ResponseNutrients? Result { get; set; }
    }

    /// <summary>
    /// 
    /// </summary>
    public class ResponseAutocompleteVM : BaseResponse
    {
        /// <summary>
        /// 
        /// </summary>
        public ResponseAutocomplete? Result { get; set; }
    }

    /// <summary>
    /// 
    /// </summary>
    public class ResponseAutocomplete
    {
        /// <summary>
        /// 
        /// </summary>
        public List<string> Coincidencias { get; set; } = [];
    }
}
