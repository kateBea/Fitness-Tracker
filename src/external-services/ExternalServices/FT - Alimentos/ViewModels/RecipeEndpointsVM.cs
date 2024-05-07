using FTAlimentos.ModelsSvc.SearchByCriteria;
using FTAlimentos.ModelsSvc.SearchRecipe;
using Newtonsoft.Json;

namespace FTAlimentos.ViewModels
{
    public class SearchRecipeVM
    {
        [JsonProperty("result_SearchRecipeVM")]
        public RecipeSetResponse? Result { get; set; }
    }

    public class SearchRecipeIDVM
    {
        [JsonProperty("result_SearchRecipeIDVM")]
        public RecipeSearchResponse? Result { get; set; }

    }

    public class SearchRecipeUriVM
    {
        [JsonProperty("result_SearchRecipeUriVM")]
        public RecipeSearchResponse? Result { get; set; }
    }
}
