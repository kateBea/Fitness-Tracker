using AutoMapper;
using FTAlimentos.ModelsSvc.SearchByCriteria;
using FTAlimentos.ModelsSvc.SearchRecipe;
using FTAlimentos.ViewModels;

namespace FTAlimentos.Mappeing
{
    public class RecetasMapperProfiles : Profile
    {
        public RecetasMapperProfiles()
        {
            CreateMap<RecipeSearchResponse, SearchRecipeIDVM>();
            CreateMap<RecipeSearchResponse, SearchRecipeUriVM>();
            CreateMap<RecipeSearchResponse, SearchRecipeVM>();
        }
    }
}
