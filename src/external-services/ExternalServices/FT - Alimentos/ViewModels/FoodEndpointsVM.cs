using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using Newtonsoft.Json;
using Shared.Utilities;

namespace FTAlimentos.ViewModels
{
    /// <summary>
    /// Respuesta del análisis de alimentos.
    /// </summary>
    public class ResponseFoodParseVM : BaseResponse
    {
        /// <summary>
        /// Resultado del análisis de alimentos.
        /// </summary>
        public ResponseFoodParser? Result { get; set; }
    }

    /// <summary>
    /// Respuesta de los nutrientes.
    /// </summary>
    public class ResponseNutrientsVM : BaseResponse
    {
        /// <summary>
        /// Resultado de los nutrientes.
        /// </summary>
        public ResponseNutrients? Result { get; set; }
    }

    /// <summary>
    /// Respuesta del autocompletado.
    /// </summary>
    public class ResponseAutocompleteVM : BaseResponse
    {
        /// <summary>
        /// Resultado del autocompletado.
        /// </summary>
        public ResponseAutocomplete? Result { get; set; }
    }

    /// <summary>
    /// Respuesta del autocompletado.
    /// </summary>
    public class ResponseAutocomplete
    {
        /// <summary>
        /// Lista de coincidencias encontradas.
        /// </summary>
        public List<string> Coincidencias { get; set; } = [];
    }
}
