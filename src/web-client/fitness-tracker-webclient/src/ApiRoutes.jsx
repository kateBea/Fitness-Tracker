// API Routes

export const API_ROUTES = {
    // API Fitness Tracker AI ==================================================
    Greeting: "http://localhost:8081/api/assistance/Greeting",
    MessageDebug: "http://localhost:8081/api/assistance/MessageDebug",
    GenerarDieta: "http://localhost:8081/api/assistance/GenerarDieta",
    ChatAssistance: "http://localhost:8081/api/assistance/ChatAssistance",
    MessageDebugLimit: "http://localhost:8081/api/assistance/MessageDebugLimitTokens",
    
    // API Fitness Tracker Alimentos ===========================================
    Autocomplete: "http://localhost:8081/api/alimentos/Autocomplete",
    GetNutrients: "http://localhost:8081/api/alimentos/BuscarPorDescripcion",
    BuscarPorDescripcion: "http://localhost:8081/api/alimentos/BuscarPorDescripcion",
    
    // API Fitness Tracker Recetas =============================================
    GetRecipeById: "http://localhost:8081/api/recetas/GetRecipeById",
    GetRecipeByUri: "http://localhost:8081/api/recetas/GetRecipeByUri",
    GetRecipeByCriteria: "http://localhost:8081/api/recetas/GetRecipeByCriteria",
    
    // API Fitness Tracker Client ==============================================
    CambiarPassword: "http://localhost:8081/api/client/CambiarPassword",
    GetDatosUsuario: "http://localhost:8081/api/client/GetDatosUsuario",
    GetDietaUsuario: "http://localhost:8081/api/client/GetDietaDeUsuario",
    GetListDietasUsuario: "http://localhost:8081/api/client/GetListDietasDeUsuario",
    GetRutinaUsuario: "http://localhost:8081/api/client/GetRutinaPorId",
    GetListRutinasUsuario: "http://localhost:8081/api/client/GetListRutinasUsuario",
    Login: "http://localhost:8081/api/client/Login",
    ModificarDatos: "http://localhost:8081/api/client/ModificarDatosUsuario",
    ModificarDieta: "http://localhost:8081/api/client/ModificarDieta",
    ModificarRutina: "http://localhost:8081/api/client/ModificarRutina",
    RegistrarUsuario: "http://localhost:8081/api/client/RegistrarUsuario",
    RegistrarDieta: "http://localhost:8081/api/client/RegistrarDieta",
    GetComida:"http://localhost:8081/api/alimentos/BuscarPorDescripcion"
};
