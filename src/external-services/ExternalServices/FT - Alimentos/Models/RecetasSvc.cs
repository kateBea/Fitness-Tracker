using FTAlimentos.ModelsSvc.Nutrients;
using FTAlimentos.ModelsSvc.Parse;
using FTAlimentos.ModelsSvc.SearchByCriteria;
using FTAlimentos.ModelsSvc.SearchRecipe;
using Newtonsoft.Json;

namespace FTAlimentos.ModelsSvc
{
    namespace SearchByCriteria
    {
        public class Thumbnail
        {
            [JsonProperty("url")]
            public string Url { get; set; }

            [JsonProperty("width")]
            public int Width { get; set; }

            [JsonProperty("height")]
            public int Height { get; set; }
        }

        public class Images
        {
            [JsonProperty("THUMBNAIL")]
            public Thumbnail Thumbnail { get; set; }

            [JsonProperty("SMALL")]
            public Thumbnail Small { get; set; }

            [JsonProperty("REGULAR")]
            public Thumbnail Regular { get; set; }

            [JsonProperty("LARGE")]
            public Thumbnail Large { get; set; }
        }


            /**
        public class TotalNutrients
        {
            // Aquí puedes definir las propiedades para los nutrientes adicionales según tu necesidad, ejemplo de respuesta abajo

             "totalNutrients": {
          "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 2021.021511246522,
            "unit": "kcal"
          },
          "FAT": {
            "label": "Fat",
            "quantity": 115.07297042005193,
            "unit": "g"
          },
          "FASAT": {
            "label": "Saturated",
            "quantity": 20.262049155891972,
            "unit": "g"
          },
          "FATRN": {
            "label": "Trans",
            "quantity": 0.0126359237,
            "unit": "g"
          },
          "FAMS": {
            "label": "Monounsaturated",
            "quantity": 70.60146489247734,
            "unit": "g"
          },
          "FAPU": {
            "label": "Polyunsaturated",
            "quantity": 17.295825700446024,
            "unit": "g"
          },
          "CHOCDF": {
            "label": "Carbs",
            "quantity": 210.8944367120563,
            "unit": "g"
          },
          "CHOCDF.net": {
            "label": "Carbohydrates (net)",
            "quantity": 173.24194449231848,
            "unit": "g"
          },
          "FIBTG": {
            "label": "Fiber",
            "quantity": 37.65249221973781,
            "unit": "g"
          },
          "SUGAR": {
            "label": "Sugars",
            "quantity": 28.005651835492756,
            "unit": "g"
          },
          "PROCNT": {
            "label": "Protein",
            "quantity": 66.01439097682751,
            "unit": "g"
          },
          "CHOLE": {
            "label": "Cholesterol",
            "quantity": 44.225256075000004,
            "unit": "mg"
          },
          "NA": {
            "label": "Sodium",
            "quantity": 2694.516587818478,
            "unit": "mg"
          },
          "CA": {
            "label": "Calcium",
            "quantity": 809.8437916235594,
            "unit": "mg"
          },
          "MG": {
            "label": "Magnesium",
            "quantity": 588.7329258663539,
            "unit": "mg"
          },
          "K": {
            "label": "Potassium",
            "quantity": 3355.842977548936,
            "unit": "mg"
          },
          "FE": {
            "label": "Iron",
            "quantity": 14.295687080269701,
            "unit": "mg"
          },
          "ZN": {
            "label": "Zinc",
            "quantity": 11.792062792402021,
            "unit": "mg"
          },
          "P": {
            "label": "Phosphorus",
            "quantity": 1638.3272598520189,
            "unit": "mg"
          },
          "VITA_RAE": {
            "label": "Vitamin A",
            "quantity": 110.64274810999689,
            "unit": "µg"
          },
          "VITC": {
            "label": "Vitamin C",
            "quantity": 166.309551345,
            "unit": "mg"
          },
          "THIA": {
            "label": "Thiamin (B1)",
            "quantity": 1.3930316252743626,
            "unit": "mg"
          },
          "RIBF": {
            "label": "Riboflavin (B2)",
            "quantity": 1.9299913070770627,
            "unit": "mg"
          },
          "NIA": {
            "label": "Niacin (B3)",
            "quantity": 24.88839790740369,
            "unit": "mg"
          },
          "VITB6A": {
            "label": "Vitamin B6",
            "quantity": 1.938329544969897,
            "unit": "mg"
          },
          "FOLDFE": {
            "label": "Folate equivalent (total)",
            "quantity": 356.3515373807156,
            "unit": "µg"
          },
          "FOLFD": {
            "label": "Folate (food)",
            "quantity": 356.3515373807156,
            "unit": "µg"
          },
          "FOLAC": {
            "label": "Folic acid",
            "quantity": 0,
            "unit": "µg"
          },
          "VITB12": {
            "label": "Vitamin B12",
            "quantity": 0.4762719885000001,
            "unit": "µg"
          },
          "VITD": {
            "label": "Vitamin D",
            "quantity": 0.2126214234375,
            "unit": "µg"
          },
          "TOCPHA": {
            "label": "Vitamin E",
            "quantity": 25.663764293351505,
            "unit": "mg"
          },
          "VITK1": {
            "label": "Vitamin K",
            "quantity": 79.71553711958751,
            "unit": "µg"
          },
          "WATER": {
            "label": "Water",
            "quantity": 754.3555563107618,
            "unit": "g"
          }
        },

        }
             */

        public class TotalNutrientItem
        {
            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("quantity")]
            public double Quantity { get; set; }

            [JsonProperty("unit")]
            public string Unit { get; set; }
        }

        public class TotalDaily
        {
            // Aquí puedes definir las propiedades para las cantidades diarias de nutrientes adicionales según la necesidad, ejemplo abajo.

            /**
             * 
             * "totalDaily": {
          "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 101.0510755623261,
            "unit": "%"
          },
          "FAT": {
            "label": "Fat",
            "quantity": 177.03533910777222,
            "unit": "%"
          },
          "FASAT": {
            "label": "Saturated",
            "quantity": 101.31024577945986,
            "unit": "%"
          },
          "CHOCDF": {
            "label": "Carbs",
            "quantity": 70.29814557068543,
            "unit": "%"
          },
          "FIBTG": {
            "label": "Fiber",
            "quantity": 150.60996887895124,
            "unit": "%"
          },
          "PROCNT": {
            "label": "Protein",
            "quantity": 132.02878195365503,
            "unit": "%"
          },
          "CHOLE": {
            "label": "Cholesterol",
            "quantity": 14.741752025000002,
            "unit": "%"
          },
          "NA": {
            "label": "Sodium",
            "quantity": 112.27152449243658,
            "unit": "%"
          },
          "CA": {
            "label": "Calcium",
            "quantity": 80.98437916235594,
            "unit": "%"
          },
          "MG": {
            "label": "Magnesium",
            "quantity": 140.17450615865567,
            "unit": "%"
          },
          "K": {
            "label": "Potassium",
            "quantity": 71.4009144159348,
            "unit": "%"
          },
          "FE": {
            "label": "Iron",
            "quantity": 79.42048377927613,
            "unit": "%"
          },
          "ZN": {
            "label": "Zinc",
            "quantity": 107.20057084001837,
            "unit": "%"
          },
          "P": {
            "label": "Phosphorus",
            "quantity": 234.04675140743126,
            "unit": "%"
          },
          "VITA_RAE": {
            "label": "Vitamin A",
            "quantity": 12.293638678888543,
            "unit": "%"
          },
          "VITC": {
            "label": "Vitamin C",
            "quantity": 184.78839038333334,
            "unit": "%"
          },
          "THIA": {
            "label": "Thiamin (B1)",
            "quantity": 116.08596877286355,
            "unit": "%"
          },
          "RIBF": {
            "label": "Riboflavin (B2)",
            "quantity": 148.46086977515864,
            "unit": "%"
          },
          "NIA": {
            "label": "Niacin (B3)",
            "quantity": 155.55248692127307,
            "unit": "%"
          },
          "VITB6A": {
            "label": "Vitamin B6",
            "quantity": 149.10227268999205,
            "unit": "%"
          },
          "FOLDFE": {
            "label": "Folate equivalent (total)",
            "quantity": 89.0878843451789,
            "unit": "%"
          },
          "VITB12": {
            "label": "Vitamin B12",
            "quantity": 19.844666187500003,
            "unit": "%"
          },
          "VITD": {
            "label": "Vitamin D",
            "quantity": 1.41747615625,
            "unit": "%"
          },
          "TOCPHA": {
            "label": "Vitamin E",
            "quantity": 171.0917619556767,
            "unit": "%"
          },
          "VITK1": {
            "label": "Vitamin K",
            "quantity": 66.42961426632293,
            "unit": "%"
          }
        }
             */
        }

        public class TotalDailyItem
        {
            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("quantity")]
            public double Quantity { get; set; }

            [JsonProperty("unit")]
            public string Unit { get; set; }
        }

        public class DigestObject
        {
            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("tag")]
            public string Tag { get; set; }

            [JsonProperty("schemaOrgTag")]
            public string SchemaOrgTag { get; set; }

            [JsonProperty("total")]
            public double Total { get; set; }

            [JsonProperty("hasRDI")]
            public bool HasRDI { get; set; }

            [JsonProperty("daily")]
            public double Daily { get; set; }

            [JsonProperty("unit")]
            public string Unit { get; set; }
        }

        public class DigestItem
        {
            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("tag")]
            public string Tag { get; set; }

            [JsonProperty("schemaOrgTag")]
            public string SchemaOrgTag { get; set; }

            [JsonProperty("total")]
            public double Total { get; set; }

            [JsonProperty("hasRDI")]
            public bool HasRDI { get; set; }

            [JsonProperty("daily")]
            public double Daily { get; set; }

            [JsonProperty("unit")]
            public string Unit { get; set; }

            [JsonProperty("sub")]
            public List<DigestObject> Sub { get; set; }
        }

        public class Digest
        {
            [JsonProperty("digest")]
            public List<DigestItem> DigestItems { get; set; }
        }

        public class RecipeHit
        {
            [JsonProperty("recipe")]
            public Recipe Recipe { get; set; }

            [JsonProperty("_links")]
            public Links Links { get; set; }
        }

        public class Self
        {
            [JsonProperty("href")]
            public string Href { get; set; }

            [JsonProperty("title")]
            public string Title { get; set; }
        }

        public class Next
        {
            [JsonProperty("href")]
            public string Href { get; set; }

            [JsonProperty("title")]
            public string Title { get; set; }
        }

        public class RecipeSetResponse
        {
            [JsonProperty("from")]
            public int From { get; set; }

            [JsonProperty("to")]
            public int To { get; set; }

            [JsonProperty("count")]
            public int Count { get; set; }

            [JsonProperty("_links")]
            public Links Links { get; set; }

            [JsonProperty("hits")]
            public List<RecipeHit> Hits { get; set; }
        }

        public class Recipe
        {
            [JsonProperty("uri")]
            public string Uri { get; set; }

            [JsonProperty("label")]
            public string Label { get; set; }

            [JsonProperty("image")]
            public string Image { get; set; }

            [JsonProperty("images")]
            public Images Images { get; set; }

            [JsonProperty("source")]
            public string Source { get; set; }

            [JsonProperty("url")]
            public string Url { get; set; }

            [JsonProperty("shareAs")]
            public string ShareAs { get; set; }

            [JsonProperty("yield")]
            public double Yield { get; set; }

            [JsonProperty("dietLabels")]
            public List<string> DietLabels { get; set; }

            [JsonProperty("healthLabels")]
            public List<string> HealthLabels { get; set; }

            [JsonProperty("cautions")]
            public List<string> Cautions { get; set; }

            [JsonProperty("ingredientLines")]
            public List<string> IngredientLines { get; set; }

            [JsonProperty("ingredients")]
            public List<Ingredient> Ingredients { get; set; }

            [JsonProperty("calories")]
            public double Calories { get; set; }

            [JsonProperty("glycemicIndex")]
            public double GlycemicIndex { get; set; }

            [JsonProperty("inflammatoryIndex")]
            public double InflammatoryIndex { get; set; }

            [JsonProperty("totalCO2Emissions")]
            public double TotalCO2Emissions { get; set; }

            [JsonProperty("co2EmissionsClass")]
            public string CO2EmissionsClass { get; set; }

            [JsonProperty("totalWeight")]
            public double TotalWeight { get; set; }

            [JsonProperty("cuisineType")]
            public List<string> CuisineType { get; set; }

            [JsonProperty("mealType")]
            public List<string> MealType { get; set; }

            [JsonProperty("dishType")]
            public List<string> DishType { get; set; }

            [JsonProperty("instructions")]
            public List<string> Instructions { get; set; }

            [JsonProperty("tags")]
            public List<string> Tags { get; set; }

            [JsonProperty("externalId")]
            public string ExternalId { get; set; }

            [JsonProperty("totalNutrients")]
            public TotalNutrients TotalNutrients { get; set; }

            [JsonProperty("totalDaily")]
            public TotalDaily TotalDaily { get; set; }

            [JsonProperty("digest")]
            public List<Digest> Digest { get; set; }
        }
    }
    
    namespace SearchRecipe
    {
        public class ImageSizes
        {
            public Thumbnail THUMBNAIL { get; set; }
            public Thumbnail SMALL { get; set; }
            public Thumbnail REGULAR { get; set; }
        }

        public class TotalNutrients
        {
            public Enerc_KCAL ENERC_KCAL { get; set; }
            public FAT FAT { get; set; }
            public FASAT FASAT { get; set; }
            public FATRN FATRN { get; set; }
            public FAMS FAMS { get; set; }
            public FAPU FAPU { get; set; }
            public CHOCDF CHOCDF { get; set; }
            public FIBTG FIBTG { get; set; }
            public SUGAR SUGAR { get; set; }
            public PROCNT PROCNT { get; set; }
        }

        public class Enerc_KCAL
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FAT
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FASAT
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FATRN
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FAMS
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FAPU
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class CHOCDF
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class FIBTG
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class SUGAR
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class PROCNT
        {
            public string label { get; set; }
            public double quantity { get; set; }
            public string unit { get; set; }
        }

        public class RecipeSearchResponse
        {
            public string uri { get; set; }
            public string label { get; set; }
            public string image { get; set; }
            public ImageSizes images { get; set; }
            public string source { get; set; }
            public string url { get; set; }
            public string shareAs { get; set; }
            public int yield { get; set; }
            public List<string> dietLabels { get; set; }
            public List<string> healthLabels { get; set; }
            public List<string> cautions { get; set; }
            public List<string> ingredientLines { get; set; }
            public List<Ingredient> ingredients { get; set; }
            public double calories { get; set; }
            public double totalCO2Emissions { get; set; }
            public string co2EmissionsClass { get; set; }
            public double totalWeight { get; set; }
            public int totalTime { get; set; }
            public List<string> cuisineType { get; set; }
            public List<string> mealType { get; set; }
            public List<string> dishType { get; set; }
            public TotalNutrients totalNutrients { get; set; }
        }

    }

}
