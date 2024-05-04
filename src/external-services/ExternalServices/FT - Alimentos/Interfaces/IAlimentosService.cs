using FTAlimentos.ViewModels;

namespace FTAlimentos.Interfaces
{
    public interface IAlimentosService
    {
        Task<FoodParseVM> Parse(string? prompt);
        Task<NutrientsVM> Nutrients(string? prompt);
        Task<AutocompleteVM> Autocomplete(string? prompt);
    }
}
