// API Routes

export const API_ROUTES = {
    // API Fitness Tracker AI ==================================================
    Greeting: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/assistance/Greeting",
    MessageDebug: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/assistance/MessageDebug",
    GenerarDieta: "http://localhost:8081/api/assistance/GenerarDieta",
    ChatAssistance: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/assistance/ChatAssistance",
    MessageDebugLimit: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/assistance/MessageDebugLimitTokens",
    
    // API Fitness Tracker Alimentos ===========================================
    Autocomplete: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/alimentos/Autocomplete",
    GetNutrients: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/alimentos/BuscarPorDescripcion",
    BuscarPorDescripcion: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/alimentos/BuscarPorDescripcion",
    
    // API Fitness Tracker Recetas =============================================
    GetRecipeById: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/recetas/GetRecipeById",
    GetRecipeByUri: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/recetas/GetRecipeByUri",
    GetRecipeByCriteria: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/recetas/GetRecipeByCriteria",
    
    // API Fitness Tracker Client ==============================================
    CambiarPassword: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/CambiarPassword",
    GetDatosUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/GetDatosUsuario",
    GetDietaUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/GetDietaDeUsuario",
    GetListDietasUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/GetListDietasDeUsuario",
    GetRutinaUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/GetRutinaPorId",
    GetListRutinasUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/GetListRutinasUsuario",
    Login: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/Login",
    ModificarDatos: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/ModificarDatosUsuario",
    ModificarDieta: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/ModificarDieta",
    ModificarRutina: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/ModificarRutina",
    RegistrarUsuario: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/RegistrarUsuario",
    RegistrarDieta: "http://ec2-3-220-31-228.compute-1.amazonaws.com:8081/api/client/RegistrarDieta",
};
