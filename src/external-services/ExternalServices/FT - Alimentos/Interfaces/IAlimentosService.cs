using FTAlimentos.ViewModels;

namespace FTAlimentos.Interfaces
{
    /// <summary>
    /// Interfaz que define los métodos relacionados con el manejo de alimentos,
    /// incluyendo el análisis de alimentos, la obtención de nutrientes y la
    /// autocompletación de términos de búsqueda.
    /// </summary>
    public interface IAlimentosService
    {
        /// <summary>
        /// Analiza un texto proporcionado para extraer información sobre alimentos.
        /// </summary>
        /// <param name="prompt">El texto que se analizará para extraer información de alimentos.</param>
        /// <param name="health">Una lista opcional de etiquetas de salud para filtrar los alimentos.</param>
        /// <param name="category">Una categoría opcional para filtrar los alimentos.</param>
        /// <returns>Una tarea que representa el resultado del análisis de alimentos,
        /// retornando un objeto <see cref="ResponseFoodParseVM"/> con la información obtenida.</returns>
        Task<ResponseFoodParseVM> Parse(string? prompt, List<string>? health = null, string? category = null);

        /// <summary>
        /// Obtiene los nutrientes de un alimento específico dado su ID.
        /// </summary>
        /// <param name="foodId">El ID del alimento para el cual se desean obtener los nutrientes.</param>
        /// <returns>Una tarea que representa el resultado de la obtención de nutrientes,
        /// retornando un objeto <see cref="ResponseNutrientsVM"/> con la información nutricional.</returns>
        Task<ResponseNutrientsVM> GetNutrients(string? foodId);

        /// <summary>
        /// Devuelve sugerencias de autocompletado para el término de búsqueda proporcionado.
        /// </summary>
        /// <param name="prompt">El término de búsqueda para el cual se desean obtener sugerencias.</param>
        /// <returns>Una tarea que representa el resultado de la autocompletación,
        /// retornando un objeto <see cref="ResponseAutocompleteVM"/> con las sugerencias de términos.</returns>
        Task<ResponseAutocompleteVM> Autocomplete(string? prompt);
    }
}
