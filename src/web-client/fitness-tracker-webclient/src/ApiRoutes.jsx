// API Routes

export const API_ROUTES = {
    // API Fitness Tracker AI ==================================================
    Greeting: "https://localhost:7298/api/assistance/Greeting",
    MessageDebug: "https://localhost:7298/api/assistance/MessageDebug",
    GenerarDieta: "https://localhost:7298/api/assistance/GenerarDieta",
    ChatAssistance: "https://localhost:7298/api/assistance/ChatAssistance",
    MessageDebugLimit: "https://localhost:7298/api/assistance/MessageDebugLimitTokens",
    
    // API Fitness Tracker Alimentos ===========================================
    Autocomplete: "https://localhost:7298/api/alimentos/Autocomplete",
    GetNutrients: "https://localhost:7298/api/alimentos/BuscarPorDescripcion",
    BuscarPorDescripcion: "https://localhost:7298/api/alimentos/BuscarPorDescripcion",
    
    // API Fitness Tracker Recetas =============================================
    GetRecipeById: "https://localhost:7298/api/recetas/GetRecipeById",
    GetRecipeByUri: "https://localhost:7298/api/recetas/GetRecipeByUri",
    GetRecipeByCriteria: "https://localhost:7298/api/recetas/GetRecipeByCriteria",
    
    // API Fitness Tracker Client ==============================================
    CambiarPassword: "https://localhost:7298/api/client/CambiarPassword",
    GetDatosUsuario: "https://localhost:7298/api/client/GetDatosUsuario",
    GetDietaUsuario: "https://localhost:7298/api/client/GetDietaDeUsuario",
    GetListDietasUsuario: "https://localhost:7298/api/client/GetListDietasDeUsuario",
    GetRutinaUsuario: "https://localhost:7298/api/client/GetRutinaPorId",
    GetListRutinasUsuario: "https://localhost:7298/api/client/GetListRutinasUsuario",
    Login: "https://localhost:7298/api/client/Login",
    ModificarDatos: "https://localhost:7298/api/client/ModificarDatosUsuario",
    ModificarDieta: "https://localhost:7298/api/client/ModificarDieta",
    ModificarRutina: "https://localhost:7298/api/client/ModificarRutina",
    RegistrarUsuario: "https://localhost:7298/api/client/RegistrarUsuario",
    RegistrarDieta: "https://localhost:7298/api/client/RegistrarDieta",
};
