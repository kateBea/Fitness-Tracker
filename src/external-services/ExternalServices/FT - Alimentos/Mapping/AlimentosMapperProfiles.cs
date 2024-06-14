using AutoMapper;
using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ViewModels;

namespace FTAlimentos.Mappeing
{
    /// <summary>
    /// Alimentos mapper profiles
    /// </summary>
    public class AlimentosMapperProfiles : Profile
    {
        /// <summary>
        /// Default constructor
        /// </summary>
        public AlimentosMapperProfiles()
        {
            CreateMap<ResponseNutrients, ResponseNutrientsVM >();
            CreateMap<ResponseFoodParser, ResponseFoodParseVM >();
            CreateMap<ResponseAutocomplete, ResponseAutocompleteVM>();
        }
    }
}
