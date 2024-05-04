using FTAlimentos.Interfaces;
using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ViewModels;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System.Net.Http.Headers;
using System.Text;
using System.Web;

namespace FTAlimentos.Services
{
    public class AlimentosService : IAlimentosService
    {
        #region Properties
        private string? _applicationId = string.Empty;
        private string? _applicationKey = string.Empty;

        private KeyValuePair<string, string>? _foodRequestParse;
        private KeyValuePair<string, string>? _foodRequestNutrients;
        private KeyValuePair<string, string>? _foodRequestAutocomplete;

        private readonly IConfiguration _configuration;
        private readonly HttpClient _httpClient; 
        #endregion

        public AlimentosService(IConfiguration configuration)
        {

            _configuration = configuration;

            _applicationId = _configuration.GetSection("ApplicationFoodId").Value;
            _applicationKey = _configuration.GetSection("ApplicationFoodKey").Value;

            _foodRequestParse = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestParse")?.Value, string.Empty);
            _foodRequestNutrients = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestNutrients")?.Value, string.Empty);
            _foodRequestAutocomplete = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestAutocomplete")?.Value, string.Empty);

            _httpClient = new HttpClient();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        public async Task<FoodParseVM> Parse(string? prompt)
        {
            var uriBuilder = _SetBaseParams(_foodRequestParse.GetValueOrDefault().Key);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["ingr"] = prompt;
            query["nutrition-type"] = "cooking";
            query["category"] = "generic-foods";

            uriBuilder.Query = query.ToString();

            string finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseFoodParser>(json);
                return new FoodParseVM() { Result = parsed };
            }

            return new FoodParseVM();
        }
       
        public async Task<NutrientsVM> Nutrients(string? prompt)
        {
            string finalUrl = _SetBaseParams(_foodRequestNutrients.GetValueOrDefault().Key).ToString();

            var obj = new RequestNutrients();
            obj.Ingredients.Add(new IngredientInfo() { FoodId = "food_a6k79rrahp8fe2b26zussa3wtkqh" });


            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseNutrients>(json);
                return new NutrientsVM() { Result = parsed };
            }

            return new NutrientsVM();
        }

        public async Task<AutocompleteVM> Autocomplete(string? prompt)
        {
            var uriBuilder = _SetBaseParams(_foodRequestAutocomplete.GetValueOrDefault().Key);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["q"] = prompt;

            uriBuilder.Query = query.ToString();
            string finalUrl = uriBuilder.ToString();

            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<List<string>>(json);
                return new AutocompleteVM() { Result = new ResponseAutocomplete() { Results = parsed } };
            }

            return new AutocompleteVM();
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
