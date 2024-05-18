using FTAlimentos.ViewModels;

namespace FTAlimentos.Interfaces
{
    public interface IAlimentosService
    {
        Task<ResponseFoodParseVM> Parse(string? prompt);
        Task<ResponseNutrientsVM> GetNutrients(string? foodId);
        Task<ResponseAutocompleteVM> Autocomplete(string? prompt);
    }
}
