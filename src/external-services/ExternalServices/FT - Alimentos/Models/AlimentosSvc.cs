using FTAlimentos.ModelsSvc.SearchByCriteria;
using Newtonsoft.Json;

namespace FTAlimentos.ModelsSvc
{
    namespace Parse
    {
        public class FoodNutrient
        {
            [JsonProperty("ENERC_KCAL")]
            public double Calories { get; set; }

            [JsonProperty("PROCNT")]
            public double Protein { get; set; }

            [JsonProperty("FAT")]
            public double Fat { get; set; }

            [JsonProperty("CHOCDF")]
            public double Carbohydrates { get; set; }

            [JsonProperty("FIBTG")]
            public double Fiber { get; set; }
        }

        public class FoodWrapper
        {
            [JsonProperty("food")]
            public Food Wrapper { get; set; }
        }

        public class Food
        {
            [JsonProperty("foodId")]
            public string FoodId { get; set; }

            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("knownAs")]
            public string KnownAs { get; set; }

            [JsonProperty("nutrients")]
            public FoodNutrient Nutrients { get; set; }

            [JsonProperty("category")]
            public string Category { get; set; }

            [JsonProperty("categoryLabel")]
            public string CategoryLabel { get; set; }

            [JsonProperty("image")]
            public string Image { get; set; }
        }

        public class Measure
        {
            [JsonProperty("uri")]
            public string Uri { get; set; }

            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("weight")]
            public double Weight { get; set; }

            [JsonProperty("qualified")]
            public List<QualifiedMeasure> Qualified { get; set; }
        }

        public class QualifiedMeasure
        {
            [JsonProperty("qualifiers")]
            public List<Qualifier> Qualifiers { get; set; }

            [JsonProperty("weight")]
            public double Weight { get; set; }
        }

        public class Qualifier
        {
            [JsonProperty("uri")]
            public string Uri { get; set; }

            [JsonProperty("label")]
            public string Label { get; set; }
        }

        public class ResponseFoodParser
        {
            [JsonProperty("text")]
            public string Text { get; set; }

            [JsonProperty("parsed")]
            public List<FoodWrapper> Parsed { get; set; }

            [JsonProperty("hints")]
            public List<FoodHint> Hints { get; set; }

            [JsonProperty("_links")]
            public Links Links { get; set; }
        }

        public class FoodHint
        {
            [JsonProperty("food")]
            public Food Food { get; set; }

            [JsonProperty("measures")]
            public List<Measure> Measures { get; set; }
        }

        public class Links
        {
            [JsonProperty("self")]
            public Self Self { get; set; }

            [JsonProperty("next")]
            public Next Next { get; set; }
        }

        public class NextLink
        {
            [JsonProperty("title")]
            public string Title { get; set; }

            [JsonProperty("href")]
            public string Href { get; set; }
        }
    }

    namespace Nutrients
    {
        public class RequestNutrients
        {
            [JsonProperty("ingredients")]
            public List<IngredientInfo> Ingredients { get; set; } = new List<IngredientInfo>();
        }

        public class IngredientInfo
        {
            [JsonProperty("quantity")]
            public double Quantity { get; set; } = 0.0;

            [JsonProperty("measureURI")]
            public string MeasureURI { get; set; } = string.Empty;

            [JsonProperty("qualifiers")]
            public List<string> Qualifiers { get; set; } = new List<string>();

            [JsonProperty("foodId")]
            public string FoodId { get; set; } = string.Empty;
        }


        public class ResponseNutrients
        {
            [JsonProperty("uri")]
            public string Uri { get; set; }

            [JsonProperty("calories")]
            public double Calories { get; set; }

            [JsonProperty("totalWeight")]
            public double TotalWeight { get; set; }

            [JsonProperty("dietLabels")]
            public List<string> DietLabels { get; set; }

            [JsonProperty("healthLabels")]
            public List<string> HealthLabels { get; set; }

            [JsonProperty("cautions")]
            public List<string> Cautions { get; set; }

            [JsonProperty("totalNutrients")]
            public Dictionary<string, object> TotalNutrients { get; set; }

            [JsonProperty("totalDaily")]
            public Dictionary<string, object> TotalDaily { get; set; }

            [JsonProperty("ingredients")]
            public List<Ingredient> Ingredients { get; set; }
        }

        public class Ingredient
        {
            [JsonProperty("parsed")]
            public List<ParsedIngredient> Parsed { get; set; }
        }

        public class ParsedIngredient
        {
            [JsonProperty("text")]
            public string Text { get; set; }

            [JsonProperty("measure")]
            public string Measure { get; set; }

            [JsonProperty("quantity")]
            public double Quantity { get; set; }

            [JsonProperty("food")]
            public string Food { get; set; }

            [JsonProperty("foodId")]
            public string FoodId { get; set; }

            [JsonProperty("weight")]
            public double Weight { get; set; }

            [JsonProperty("retainedWeight")]
            public double RetainedWeight { get; set; }

            [JsonProperty("status")]
            public string Status { get; set; }
        }
    }

    namespace Autocomplete
    {
        
    }

    namespace Errors
    {
        public class ErrorResponse
        {
            [JsonProperty("errorCode")]
            public string ErrorCode { get; set; }

            [JsonProperty("message")]
            public string Message { get; set; }

            [JsonProperty("params")]
            public List<string> Params { get; set; }
        }
    }
}
