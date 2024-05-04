using FTAlimentos.ViewModels;

namespace FTAlimentos.Interfaces
{
    public interface IRecetasService
    {
        Task<SearchRecipeVM> GetRecipeByCriteria(string criteria);
        Task<SearchRecipeIDVM> GetRecipeByID(string criteria);
        Task<SearchRecipeUriVM> GetRecipeByUri(string criteria);
    }
}
