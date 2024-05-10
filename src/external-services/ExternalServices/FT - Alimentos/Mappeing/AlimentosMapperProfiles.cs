using AutoMapper;
using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ViewModels;

namespace FTAlimentos.Mappeing
{
    public class AlimentosMapperProfiles : Profile
    {
        public AlimentosMapperProfiles()
        {
            CreateMap<ResponseNutrients, NutrientsVM >();
            CreateMap<ResponseFoodParser, FoodParseVM >();
            CreateMap<ResponseAutocomplete, AutocompleteVM>();
        }
    }
}
