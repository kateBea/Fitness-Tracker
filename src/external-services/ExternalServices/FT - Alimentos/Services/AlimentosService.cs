using AutoMapper;
using FTAlimentos.Interfaces;
using FTAlimentos.ModelsSvc.Autocomplete;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ViewModels;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using Shared.Contexts;
using System.Net.Http.Headers;
using System.Text;
using System.Web;

namespace FTAlimentos.Services
{
    /// <summary>
    /// 
    /// </summary>
    public class AlimentosService : IAlimentosService
    {
        #region Properties
        private string? _applicationId = string.Empty;
        private string? _applicationKey = string.Empty;

        private KeyValuePair<string?, string> _foodRequestParse = new();
        private KeyValuePair<string?, string> _foodRequestNutrients = new();
        private KeyValuePair<string?, string> _foodRequestAutocomplete = new();

        private readonly IConfiguration _configuration;
        private readonly HttpClient _httpClient;
        private readonly IMapper _mapper;
        private readonly IDataHttpContext _dataHttpContext;
        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <param name="configuration"></param>
        /// <param name="mapper"></param>
        public AlimentosService(IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext)
        {
            _dataHttpContext = dataHttpContext;
            _configuration = configuration;

            _mapper = mapper;

            _applicationId = _configuration.GetSection("ApplicationFoodId").Value;
            _applicationKey = _configuration.GetSection("ApplicationFoodKey").Value;

            _foodRequestParse = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestParse")?.Value, string.Empty);
            _foodRequestNutrients = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestNutrients")?.Value, string.Empty);
            _foodRequestAutocomplete = KeyValuePair.Create(_configuration.GetSection("FoodEndpoints:FoodRequestAutocomplete")?.Value, string.Empty);

            _httpClient = new HttpClient();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="prompt"></param>
        /// <param name="health"></param>
        /// <param name="category"></param>
        /// <returns></returns>
        public async Task<ResponseFoodParseVM> Parse(string? prompt, List<string>? health = null, string? category = null)
        {
            var uriBuilder = _SetBaseParams(_foodRequestParse.Key ?? string.Empty);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["ingr"] = prompt;
            query["nutrition-type"] = "cooking";
            query["category"] = category ?? "generic-foods";

            uriBuilder.Query = query.ToString();

            string finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseFoodParser>(json);
                return new ResponseFoodParseVM() { Result = parsed };
            }

            return new ResponseFoodParseVM();
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="foodId"></param>
        /// <returns></returns>
        public async Task<ResponseNutrientsVM> GetNutrients(string? foodId)
        {
            string finalUrl = _SetBaseParams(_foodRequestNutrients.Key ?? string.Empty).ToString();

            var obj = new RequestNutrients();
            obj.Ingredients.Add(new IngredientInfo() { FoodId = foodId ?? string.Empty, MeasureURI = "http://www.edamam.com/ontologies/edamam.owl#Measure_gram", Quantity = 1 });


            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseNutrients>(json);
                return new ResponseNutrientsVM() { Result = parsed };
            }

            return new ResponseNutrientsVM();
        }

        public async Task<ResponseAutocompleteVM> Autocomplete(string? prompt)
        {
            var uriBuilder = _SetBaseParams(_foodRequestAutocomplete.Key ?? string.Empty);

            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["q"] = prompt;

            uriBuilder.Query = query.ToString();
            string finalUrl = uriBuilder.ToString();

            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<List<string>>(json) ?? [];
                return new ResponseAutocompleteVM() { Result = new ResponseAutocomplete() { Coincidencias = parsed } };
            }

            return new ResponseAutocompleteVM();
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
