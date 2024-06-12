using FTAlimentos.ViewModels;

namespace FTAlimentos.Interfaces
{
    /// <summary>
    /// 
    /// </summary>
    public interface IAlimentosService
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="prompt"></param>
        /// <param name="health"></param>
        /// <param name="category"></param>
        /// <returns></returns>
        Task<ResponseFoodParseVM> Parse(string? prompt, List<string>? health = null, string? category = null);
        
        /// <summary>
        /// 
        /// </summary>
        /// <param name="foodId"></param>
        /// <returns></returns>
        Task<ResponseNutrientsVM> GetNutrients(string? foodId);
        
        /// <summary>
        /// 
        /// </summary>
        /// <param name="prompt"></param>
        /// <returns></returns>
        Task<ResponseAutocompleteVM> Autocomplete(string? prompt);
    }
}
