using Newtonsoft.Json;

namespace FTAlimentos.ModelsSvc
{
    namespace Parse
    {
        /// <summary>
        /// Representa los nutrientes de un alimento.
        /// </summary>
        public class FoodNutrient
        {
            /// <summary>
            /// Calorías del alimento.
            /// </summary>
            [JsonProperty("ENERC_KCAL")]
            public double Calories { get; set; } = 0.0;

            /// <summary>
            /// Proteína del alimento.
            /// </summary>
            [JsonProperty("PROCNT")]
            public double Protein { get; set; } = 0.0;

            /// <summary>
            /// Grasa del alimento.
            /// </summary>
            [JsonProperty("FAT")]
            public double Fat { get; set; } = 0.0;

            /// <summary>
            /// Carbohidratos del alimento.
            /// </summary>
            [JsonProperty("CHOCDF")]
            public double Carbohydrates { get; set; } = 0.0;

            /// <summary>
            /// Fibra del alimento.
            /// </summary>
            [JsonProperty("FIBTG")]
            public double Fiber { get; set; } = 0.0;
        }

        /// <summary>
        /// Envuelve un objeto Food.
        /// </summary>
        public class FoodWrapper
        {
            /// <summary>
            /// El alimento envuelto.
            /// </summary>
            [JsonProperty("food")]
            public Food Wrapper { get; set; } = new Food();
        }

        /// <summary>
        /// Representa un alimento.
        /// </summary>
        public class Food
        {
            /// <summary>
            /// ID del alimento.
            /// </summary>
            [JsonProperty("foodId")]
            public string FoodId { get; set; } = string.Empty;

            /// <summary>
            /// Etiqueta del alimento.
            /// </summary>
            [JsonProperty("label")]
            public string Label { get; set; } = string.Empty;

            /// <summary>
            /// Nombre conocido del alimento.
            /// </summary>
            [JsonProperty("knownAs")]
            public string KnownAs { get; set; } = string.Empty;

            /// <summary>
            /// Nutrientes del alimento.
            /// </summary>
            [JsonProperty("nutrients")]
            public FoodNutrient Nutrients { get; set; } = new FoodNutrient();

            /// <summary>
            /// Categoría del alimento.
            /// </summary>
            [JsonProperty("category")]
            public string Category { get; set; } = string.Empty;

            /// <summary>
            /// Etiqueta de la categoría del alimento.
            /// </summary>
            [JsonProperty("categoryLabel")]
            public string CategoryLabel { get; set; } = string.Empty;

            /// <summary>
            /// Imagen del alimento.
            /// </summary>
            [JsonProperty("image")]
            public string Image { get; set; } = string.Empty;
        }

        /// <summary>
        /// Representa una medida de un alimento.
        /// </summary>
        public class Measure
        {
            /// <summary>
            /// URI de la medida.
            /// </summary>
            [JsonProperty("uri")]
            public string Uri { get; set; } = string.Empty;

            /// <summary>
            /// Etiqueta de la medida.
            /// </summary>
            [JsonProperty("label")]
            public string Label { get; set; } = string.Empty;

            /// <summary>
            /// Peso de la medida.
            /// </summary>
            [JsonProperty("weight")]
            public double Weight { get; set; } = 0.0;

            /// <summary>
            /// Medidas cualificadas.
            /// </summary>
            [JsonProperty("qualified")]
            public List<QualifiedMeasure> Qualified { get; set; } = new List<QualifiedMeasure>();
        }

        /// <summary>
        /// Representa una medida cualificada de un alimento.
        /// </summary>
        public class QualifiedMeasure
        {
            /// <summary>
            /// Calificadores de la medida.
            /// </summary>
            [JsonProperty("qualifiers")]
            public List<Qualifier> Qualifiers { get; set; } = new List<Qualifier>();

            /// <summary>
            /// Peso de la medida.
            /// </summary>
            [JsonProperty("weight")]
            public double Weight { get; set; } = 0.0;
        }

        /// <summary>
        /// Representa un calificador de una medida.
        /// </summary>
        public class Qualifier
        {
            /// <summary>
            /// URI del calificador.
            /// </summary>
            [JsonProperty("uri")]
            public string Uri { get; set; } = string.Empty;

            /// <summary>
            /// Etiqueta del calificador.
            /// </summary>
            [JsonProperty("label")]
            public string Label { get; set; } = string.Empty;
        }

        /// <summary>
        /// Respuesta del análisis de alimentos.
        /// </summary>
        public class ResponseFoodParser
        {
            /// <summary>
            /// Texto analizado.
            /// </summary>
            [JsonProperty("text")]
            public string Text { get; set; } = string.Empty;

            /// <summary>
            /// Alimentos analizados.
            /// </summary>
            [JsonProperty("parsed")]
            public List<FoodWrapper> Parsed { get; set; } = new List<FoodWrapper>();

            /// <summary>
            /// Sugerencias de alimentos.
            /// </summary>
            [JsonProperty("hints")]
            public List<FoodHint> Hints { get; set; } = new List<FoodHint>();

            /// <summary>
            /// Enlaces relacionados.
            /// </summary>
            [JsonProperty("_links")]
            public Links Links { get; set; } = new Links();
        }

        /// <summary>
        /// Representa una sugerencia de alimento.
        /// </summary>
        public class FoodHint
        {
            /// <summary>
            /// El alimento sugerido.
            /// </summary>
            [JsonProperty("food")]
            public Food Food { get; set; } = new Food();

            /// <summary>
            /// Las medidas del alimento sugerido.
            /// </summary>
            [JsonProperty("measures")]
            public List<Measure> Measures { get; set; } = new List<Measure>();
        }

        /// <summary>
        /// Representa los enlaces relacionados en la respuesta.
        /// </summary>
        public class Links
        {
            /// <summary>
            /// Enlace a la página actual.
            /// </summary>
            [JsonProperty("self")]
            public Self Self { get; set; } = new Self();

            /// <summary>
            /// Enlace a la siguiente página.
            /// </summary>
            [JsonProperty("next")]
            public Next Next { get; set; } = new Next();
        }

        /// <summary>
        /// Self
        /// </summary>
        public class Self
        {
            /// <summary>
            /// Href
            /// </summary>
            [JsonProperty("href")]
            public string Href { get; set; } = string.Empty;

            /// <summary>
            /// Title
            /// </summary>
            [JsonProperty("title")]
            public string Title { get; set; } = string.Empty;
        }

        /// <summary>
        /// Next
        /// </summary>
        public class Next
        {
            /// <summary>
            /// Href
            /// </summary>
            [JsonProperty("href")]
            public string Href { get; set; } = string.Empty;

            /// <summary>
            /// Title
            /// </summary>
            [JsonProperty("title")]
            public string Title { get; set; } = string.Empty;
        }

        /// <summary>
        /// Representa un enlace.
        /// </summary>
        public class NextLink
        {
            /// <summary>
            /// Título del enlace.
            /// </summary>
            [JsonProperty("title")]
            public string Title { get; set; } = string.Empty;

            /// <summary>
            /// Href del enlace.
            /// </summary>
            [JsonProperty("href")]
            public string Href { get; set; } = string.Empty;
        }
    }

    namespace Nutrients
    {
        /// <summary>
        /// Solicitud de nutrientes de un alimento.
        /// </summary>
        public class RequestNutrients
        {
            /// <summary>
            /// Lista de ingredientes.
            /// </summary>
            [JsonProperty("ingredients")]
            public List<IngredientInfo> Ingredients { get; set; } = new List<IngredientInfo>();
        }

        /// <summary>
        /// Información de un ingrediente.
        /// </summary>
        public class IngredientInfo
        {
            /// <summary>
            /// Cantidad del ingrediente.
            /// </summary>
            [JsonProperty("quantity")]
            public double Quantity { get; set; } = 0.0;

            /// <summary>
            /// URI de la medida del ingrediente.
            /// </summary>
            [JsonProperty("measureURI")]
            public string MeasureURI { get; set; } = string.Empty;

            /// <summary>
            /// Calificadores del ingrediente.
            /// </summary>
            [JsonProperty("qualifiers")]
            public List<string> Qualifiers { get; set; } = new List<string>();

            /// <summary>
            /// ID del alimento del ingrediente.
            /// </summary>
            [JsonProperty("foodId")]
            public string FoodId { get; set; } = string.Empty;
        }

        /// <summary>
        /// Respuesta de nutrientes de un alimento.
        /// </summary>
        public class ResponseNutrients
        {
            /// <summary>
            /// URI de la respuesta.
            /// </summary>
            [JsonProperty("uri")]
            public string Uri { get; set; } = string.Empty;

            /// <summary>
            /// Calorías totales.
            /// </summary>
            [JsonProperty("calories")]
            public double Calories { get; set; } = 0.0;

            /// <summary>
            /// Peso total.
            /// </summary>
            [JsonProperty("totalWeight")]
            public double TotalWeight { get; set; } = 0.0;

            /// <summary>
            /// Etiquetas de la dieta.
            /// </summary>
            [JsonProperty("dietLabels")]
            public List<string> DietLabels { get; set; } = new List<string>();

            /// <summary>
            /// Etiquetas de salud.
            /// </summary>
            [JsonProperty("healthLabels")]
            public List<string> HealthLabels { get; set; } = new List<string>();

            /// <summary>
            /// Advertencias.
            /// </summary>
            [JsonProperty("cautions")]
            public List<string> Cautions { get; set; } = new List<string>();

            /// <summary>
            /// Nutrientes totales.
            /// </summary>
            [JsonProperty("totalNutrients")]
            public Dictionary<string, object> TotalNutrients { get; set; } = new Dictionary<string, object>();

            /// <summary>
            /// Valores diarios totales.
            /// </summary>
            [JsonProperty("totalDaily")]
            public Dictionary<string, object> TotalDaily { get; set; } = new Dictionary<string, object>();

            /// <summary>
            /// Ingredientes de la respuesta.
            /// </summary>
            [JsonProperty("ingredients")]
            public List<Ingredient> Ingredients { get; set; } = new List<Ingredient>();
        }

        /// <summary>
        /// Representa un ingrediente en la respuesta.
        /// </summary>
        public class Ingredient
        {
            /// <summary>
            /// Ingredientes analizados.
            /// </summary>
            [JsonProperty("parsed")]
            public List<ParsedIngredient> Parsed { get; set; } = new List<ParsedIngredient>();
        }

        /// <summary>
        /// Representa un ingrediente analizado.
        /// </summary>
        public class ParsedIngredient
        {
            /// <summary>
            /// Texto del ingrediente.
            /// </summary>
            [JsonProperty("text")]
            public string Text { get; set; } = string.Empty;

            /// <summary>
            /// Medida del ingrediente.
            /// </summary>
            [JsonProperty("measure")]
            public string Measure { get; set; } = string.Empty;

            /// <summary>
            /// Cantidad del ingrediente.
            /// </summary>
            [JsonProperty("quantity")]
            public double Quantity { get; set; } = 0.0;

            /// <summary>
            /// Nombre del ingrediente.
            /// </summary>
            [JsonProperty("food")]
            public string Food { get; set; } = string.Empty;

            /// <summary>
            /// ID del alimento del ingrediente.
            /// </summary>
            [JsonProperty("foodId")]
            public string FoodId { get; set; } = string.Empty;

            /// <summary>
            /// Peso del ingrediente.
            /// </summary>
            [JsonProperty("weight")]
            public double Weight { get; set; } = 0.0;

            /// <summary>
            /// Peso retenido del ingrediente.
            /// </summary>
            [JsonProperty("retainedWeight")]
            public double RetainedWeight { get; set; } = 0.0;

            /// <summary>
            /// Estado del ingrediente.
            /// </summary>
            [JsonProperty("status")]
            public string Status { get; set; } = string.Empty;
        }
    }

    namespace Autocomplete
    {
        // Agregar modelos aquí según sea necesario
    }

    namespace Errors
    {
        /// <summary>
        /// Representa una respuesta de error.
        /// </summary>
        public class ErrorResponse
        {
            /// <summary>
            /// Código de error.
            /// </summary>
            [JsonProperty("errorCode")]
            public string ErrorCode { get; set; } = string.Empty;

            /// <summary>
            /// Mensaje de error.
            /// </summary>
            [JsonProperty("message")]
            public string Message { get; set; } = string.Empty;

            /// <summary>
            /// Parámetros relacionados con el error.
            /// </summary>
            [JsonProperty("params")]
            public List<string> Params { get; set; } = [];
        }
    }
}

