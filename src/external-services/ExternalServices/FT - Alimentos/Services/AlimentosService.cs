using AutoMapper;
using FTAlimentos.Interfaces;
using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ViewModels;
using Newtonsoft.Json;
using Shared.Contexts;
using System.Net.Http.Headers;
using System.Text;
using System.Web;

namespace FTAlimentos.Services
{
    /// <summary>
    /// Alimentos service
    /// </summary>
    public class AlimentosService : IAlimentosService
    {
        #region Properties
        /// <summary>
        /// Identificador de la aplicación, utilizado para autenticación.
        /// </summary>
        private string? _applicationId = string.Empty;

        /// <summary>
        /// Clave de la aplicación, utilizada para autenticación.
        /// </summary>
        private string? _applicationKey = string.Empty;

        /// <summary>
        /// Par clave-valor para la solicitud de análisis de alimentos.
        /// La clave es una cadena opcional y el valor es una cadena.
        /// </summary>
        private KeyValuePair<string?, string> _foodRequestParse = new();

        /// <summary>
        /// Par clave-valor para la solicitud de nutrientes.
        /// La clave es una cadena opcional y el valor es una cadena.
        /// </summary>
        private KeyValuePair<string?, string> _foodRequestNutrients = new();

        /// <summary>
        /// Par clave-valor para la solicitud de autocompletado.
        /// La clave es una cadena opcional y el valor es una cadena.
        /// </summary>
        private KeyValuePair<string?, string> _foodRequestAutocomplete = new();

        /// <summary>
        /// Interfaz para la configuración de la aplicación.
        /// </summary>
        private readonly IConfiguration _configuration;

        /// <summary>
        /// Cliente HTTP utilizado para realizar solicitudes HTTP.
        /// </summary>
        private readonly HttpClient _httpClient;

        /// <summary>
        /// Objeto de mapeo para convertir entre diferentes tipos de modelos.
        /// </summary>
        private readonly IMapper _mapper;

        /// <summary>
        /// Contexto de datos HTTP.
        /// </summary>
        private readonly IDataHttpContext _dataHttpContext;
        #endregion


        /// <summary>
        /// Constructor predeterminado para la clase AlimentosService.
        /// Inicializa las dependencias necesarias y configura los parámetros de la aplicación.
        /// </summary>
        /// <param name="configuration">Interfaz para acceder a la configuración de la aplicación.</param>
        /// <param name="mapper">Objeto de mapeo para convertir entre diferentes tipos de modelos.</param>
        /// <param name="dataHttpContext">Contexto de datos HTTP.</param>
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
        /// Realiza el análisis de alimentos basado en el prompt proporcionado, 
        /// con opciones adicionales para etiquetas de salud y categoría.
        /// </summary>
        /// <param name="prompt">El texto de entrada que describe el alimento a analizar.</param>
        /// <param name="health">Lista opcional de etiquetas de salud para filtrar los resultados.</param>
        /// <param name="category">Categoría opcional del alimento para especificar el tipo de búsqueda.</param>
        /// <returns>Una tarea que representa la operación asincrónica. La tarea resultante contiene un objeto de tipo <see cref="ResponseFoodParseVM"/> con los resultados del análisis.</returns>
        public async Task<ResponseFoodParseVM> Parse(string? prompt, List<string>? health = null, string? category = null)
        {
            var uriBuilder = SetBaseParams(_foodRequestParse.Key ?? string.Empty);
            var query = HttpUtility.ParseQueryString(uriBuilder.Query);

            query["ingr"] = prompt;
            query["nutrition-type"] = "cooking";
            query["category"] = category ?? "generic-foods";
            uriBuilder.Query = query.ToString();

            var finalUrl = uriBuilder.ToString();
            var result = await _httpClient.GetAsync(finalUrl);

            if (result.IsSuccessStatusCode)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseFoodParser>(json);

                return new ResponseFoodParseVM() { Result = parsed };
            }

            return new();
        }


        /// <summary>
        /// Obtiene la información nutricional de un alimento especificado por su ID.
        /// </summary>
        /// <param name="foodId">El identificador del alimento del cual se desea obtener la información nutricional.</param>
        /// <returns>Una tarea que representa la operación asincrónica. La tarea resultante contiene un objeto de tipo <see cref="ResponseNutrientsVM"/> con los resultados nutricionales.</returns>
        public async Task<ResponseNutrientsVM> GetNutrients(string? foodId)
        {
            string finalUrl = SetBaseParams(_foodRequestNutrients.Key ?? string.Empty).ToString();

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

        /// <summary>
        /// Realiza la autocompletación de un término de búsqueda dado.
        /// </summary>
        /// <param name="prompt">El término de búsqueda para el cual se desea obtener sugerencias de autocompletación.</param>
        /// <returns>Una tarea que representa la operación asincrónica. La tarea resultante contiene un objeto de tipo <see cref="ResponseAutocompleteVM"/> con las coincidencias sugeridas.</returns>
        public async Task<ResponseAutocompleteVM> Autocomplete(string? prompt)
        {
            var uriBuilder = SetBaseParams(_foodRequestAutocomplete.Key ?? string.Empty);

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

        /// <summary>
        /// Establece los parámetros base para una URL.
        /// </summary>
        /// <param name="baseUrl">La URL base a la cual se le agregarán los parámetros.</param>
        /// <returns>Un objeto <see cref="UriBuilder"/> con los parámetros base establecidos.</returns>
        private UriBuilder SetBaseParams(string baseUrl)
        {
            UriBuilder result = new(baseUrl)
            {
                Port = -1
            };

            var query = HttpUtility.ParseQueryString(result.Query);

            query["app_id"] = _applicationId;
            query["app_key"] = _applicationKey;

            result.Query = query.ToString();

            return result;
        }

        #endregion
    }
}
