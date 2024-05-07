using FTAlimentos.Interfaces;
using FTAlimentos.ViewModels;
using Microsoft.Extensions.Configuration;
using System.Net.Http.Headers;
using System.Net.Http;
using System;
using System.Web;
using Newtonsoft.Json;
using FTAlimentos.ModelsSvc.SearchByCriteria;
using FTAlimentos.ModelsSvc.SearchRecipe;

namespace FTAlimentos.Services
{
    public class RecetasService : IRecetasService
    {
        #region Properties
        private string? _applicationId = string.Empty;
        private string? _applicationKey = string.Empty;

        private KeyValuePair<string, string>? _recipeSearch;
        private KeyValuePair<string, string>? _searchRecipeById;
        private KeyValuePair<string, string>? _searchRecipeByUri;

        private readonly IConfiguration _configuration;
        private readonly HttpClient _httpClient;
        #endregion

        public RecetasService(IConfiguration configuration)
        {
            _configuration = configuration;

            _applicationId = _configuration.GetSection("ApplicationRecipeId").Value;
            _applicationKey = _configuration.GetSection("ApplicationRecipeKey").Value;

            _recipeSearch = KeyValuePair.Create(_configuration.GetSection("RecipeEndpoints:RecipeSearch")?.Value, string.Empty);
            _searchRecipeById = KeyValuePair.Create(_configuration.GetSection("RecipeEndpoints:SpecificRecipeInfo")?.Value, string.Empty);
            _searchRecipeByUri = KeyValuePair.Create(_configuration.GetSection("RecipeEndpoints:LookupByUri")?.Value, string.Empty);

            _httpClient = new HttpClient();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task<SearchRecipeVM> GetRecipeByCriteria(string criteria)
        {
            var uriBuilder = _SetBaseParams(_recipeSearch.GetValueOrDefault().Key);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["type"] = "public";
            query["q"] = criteria;

            uriBuilder.Query = query.ToString();

            string finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<RecipeSetResponse>(json);
                return new SearchRecipeVM() { Result = parsed };
            }

            return new SearchRecipeVM();
        }

        public async Task<SearchRecipeIDVM> GetRecipeByID(string id)
        {
            var uriBuilder = _SetBaseParams(_recipeSearch.GetValueOrDefault().Key);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["type"] = "public";
            query["id"] = id;

            uriBuilder.Query = query.ToString();

            string finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<RecipeSearchResponse>(json);
                return new SearchRecipeIDVM() { Result = parsed };
            }

            return new SearchRecipeIDVM();
        }

        public async Task<SearchRecipeUriVM> GetRecipeByUri(string uri)
        {
            var uriBuilder = _SetBaseParams(_recipeSearch.GetValueOrDefault().Key);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["type"] = "public";
            query["uri"] = uri;

            uriBuilder.Query = query.ToString();

            string finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<RecipeSearchResponse>(json);
                return new SearchRecipeUriVM() { Result = parsed };
            }

            return new SearchRecipeUriVM();
        }

        #region Private Methods
        private UriBuilder _SetBaseParams(string baseUrl)
        {
            UriBuilder result = new UriBuilder(baseUrl);

            result.Port = -1;
            var query = HttpUtility.ParseQueryString(result.Query);

            query["app_id"] = _applicationId;
            query["app_key"] = _applicationKey;

            result.Query = query.ToString();

            return result;
        }
        #endregion
    }
}
