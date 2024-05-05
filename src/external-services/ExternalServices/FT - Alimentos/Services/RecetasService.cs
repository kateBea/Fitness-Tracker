using FTAlimentos.Interfaces;
using FTAlimentos.ViewModels;

namespace FTAlimentos.Services
{
    public class RecetasService : IRecetasService
    {
        public RecetasService()
        {
            
        }

        public Task<SearchRecipeVM> GetRecipeByCriteria(string criteria)
        {
            throw new NotImplementedException();
        }

        public Task<SearchRecipeIDVM> GetRecipeByID(string criteria)
        {
            throw new NotImplementedException();
        }

        public Task<SearchRecipeUriVM> GetRecipeByUri(string criteria)
        {
            throw new NotImplementedException();
        }
    }
}
