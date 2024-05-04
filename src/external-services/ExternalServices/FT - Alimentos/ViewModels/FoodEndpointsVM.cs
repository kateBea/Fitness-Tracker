using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using Newtonsoft.Json;

namespace FTAlimentos.ViewModels
{
    public class FoodParseVM
    {
        [JsonProperty("Result")]
        public ResponseFoodParser? Result { get; set; }
    }

    public class NutrientsVM
    {
        [JsonProperty("Result")]
        public ResponseNutrients? Result { get; set; }
    }

    public class AutocompleteVM
    {
        [JsonProperty("Result")]
        public ResponseAutocomplete? Result { get; set; }
    }
}
