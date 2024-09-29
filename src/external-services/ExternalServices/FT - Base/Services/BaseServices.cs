using AutoMapper;
using FTBase.Interfaces;
using FTBase.Models;
using FTBase.ViewModels;
using FTAlimentos.Models;
using Newtonsoft.Json;
using Security.Authentication;
using Shared.Constants;
using Shared.Contexts;
using Shared.Utilities;
using System.Net;
using System.Net.Http.Headers;
using System.Text;
using System.Web;
using static FTBase.ViewModels.ResponseGetDatosUsuarioVM;
using static FTBase.ViewModels.ResponseLoginVM;

namespace FTBase.Services
{
    /// <summary>
    /// Base service
    /// </summary>
    public class BaseServices : IBaseServices
    {
        #region Properties

        /// <summary>
        /// Punto final para la operación de inicio de sesión.
        /// </summary>
        private readonly string _loginEndpoint;

        /// <summary>
        /// Punto final para la operación de registro de usuario.
        /// </summary>
        private readonly string _registerEndpoint;

        /// <summary>
        /// Punto final para la operación de cambio de contraseña.
        /// </summary>
        private readonly string _cambiarPasswordEndpoint;

        /// <summary>
        /// Punto final para la operación de registro de dieta.
        /// </summary>
        private readonly string _registrarDietaEndpoint;

        /// <summary>
        /// Punto final para la operación de modificación de dieta.
        /// </summary>
        private readonly string _modificarDietaEndpoint;

        /// <summary>
        /// Punto final para la operación de modificación de rutina.
        /// </summary>
        private readonly string _modificarRutinaEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de datos de usuario.
        /// </summary>
        private readonly string _getDatosUsuarioEndpoint;

        /// <summary>
        /// Punto final para la operación de modificación de datos de usuario.
        /// </summary>
        private readonly string _modificarDatosUsuarioEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de la lista de dietas de usuario.
        /// </summary>
        private readonly string _getListDietasUsuarioEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de dieta de usuario.
        /// </summary>
        private readonly string _getDietaUsuarioEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de rutina por ID.
        /// </summary>
        private readonly string _getRutinaEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de la lista de rutinas de usuario.
        /// </summary>
        private readonly string _getListRutinasEndpoint;

        /// <summary>
        /// Punto final para la operación de registro de rutina.
        /// </summary>
        private readonly string _registrarRutinaEndpoint;

        /// <summary>
        /// Punto final para la operación de obtención de alimentos.
        /// </summary>
        private readonly string _getAlimentosEndpoint;

        /// <summary>
        /// Cliente HTTP para realizar solicitudes HTTP.
        /// </summary>
        private readonly HttpClient _httpClient;

        /// <summary>
        /// Objeto para mapear entre diferentes tipos de objetos.
        /// </summary>
        private readonly IMapper _mapper;

        /// <summary>
        /// Contexto de datos HTTP para acceder a la información de la solicitud HTTP actual.
        /// </summary>
        private readonly IDataHttpContext _dataHttpContext;

        #endregion


        /// <summary>
        /// Constructor de la clase BaseServices.
        /// </summary>
        /// <param name="configuration">Configuración de la aplicación proporcionada por el entorno.</param>
        /// <param name="mapper">Objeto utilizado para mapear entre diferentes tipos de objetos.</param>
        /// <param name="dataHttpContext">Contexto de datos HTTP para acceder a la información de la solicitud HTTP actual.</param>
        public BaseServices(IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext)
        {
            _mapper = mapper;
            _dataHttpContext = dataHttpContext;
            
#if !DEBUG
            _loginEndpoint = configuration.GetSection("ExternalServices:Login").Value!;
            _registerEndpoint = configuration.GetSection("ExternalServices:RegisterEndpoint").Value!;
            _cambiarPasswordEndpoint = configuration.GetSection("ExternalServices:CambiarPasswordEndpoint").Value!;
            _registrarDietaEndpoint = configuration.GetSection("ExternalServices:RegistrarDietaEndpoint").Value!;
            _modificarDietaEndpoint = configuration.GetSection("ExternalServices:ModificarDietaEndpoint").Value!;
            _modificarRutinaEndpoint = configuration.GetSection("ExternalServices:ModificarRutinaEndpoint").Value!;
            _getDatosUsuarioEndpoint = configuration.GetSection("ExternalServices:GetDatosUsuarioEndpoint").Value!;
            _modificarDatosUsuarioEndpoint = configuration.GetSection("ExternalServices:ModificarDatosUsuarioEndpoint").Value!;
            _getDietaUsuarioEndpoint = configuration.GetSection("ExternalServices:GetDietaUsuarioEndpoint").Value!;
            _getListDietasUsuarioEndpoint = configuration.GetSection("ExternalServices:GetDietasUsuarioEndpoint").Value!;
            _getRutinaEndpoint = configuration.GetSection("ExternalServices:GetRutinaEndpoint").Value!;
            _getListRutinasEndpoint = configuration.GetSection("ExternalServices:GetRutinasEndpoint").Value!;
            _registrarRutinaEndpoint = configuration.GetSection("ExternalServices:RegistrarRutinaEndpoint").Value!;
            _getAlimentosEndpoint = configuration.GetSection("ExternalServices:GetAlimentosEndpoint").Value!;
#else
            _loginEndpoint = configuration.GetSection("ExternalServicesDebug:Login").Value!;
            _registerEndpoint = configuration.GetSection("ExternalServicesDebug:RegisterEndpoint").Value!;
            _cambiarPasswordEndpoint = configuration.GetSection("ExternalServicesDebug:CambiarPasswordEndpoint").Value!;
            _registrarDietaEndpoint = configuration.GetSection("ExternalServicesDebug:RegistrarDietaEndpoint").Value!;
            _modificarDietaEndpoint = configuration.GetSection("ExternalServicesDebug:ModificarDietaEndpoint").Value!;
            _modificarRutinaEndpoint = configuration.GetSection("ExternalServicesDebug:ModificarRutinaEndpoint").Value!;
            _getDatosUsuarioEndpoint = configuration.GetSection("ExternalServicesDebug:GetDatosUsuarioEndpoint").Value!;
            _modificarDatosUsuarioEndpoint = configuration.GetSection("ExternalServicesDebug:ModificarDatosUsuarioEndpoint").Value!;
            _getDietaUsuarioEndpoint = configuration.GetSection("ExternalServicesDebug:GetDietaUsuarioEndpoint").Value!;
            _getListDietasUsuarioEndpoint = configuration.GetSection("ExternalServicesDebug:GetDietasUsuarioEndpoint").Value!;
            _getRutinaEndpoint = configuration.GetSection("ExternalServicesDebug:GetRutinaEndpoint").Value!;
            _getListRutinasEndpoint = configuration.GetSection("ExternalServicesDebug:GetRutinasEndpoint").Value!;
            _registrarRutinaEndpoint = configuration.GetSection("ExternalServicesDebug:RegistrarRutinaEndpoint").Value!;
            _getAlimentosEndpoint = configuration.GetSection("ExternalServicesDebug:GetAlimentosEndpoint").Value!;
#endif

            _httpClient = new();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        /// <summary>
        /// Cambia la contraseña del usuario según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para cambiar la contraseña.</param>
        /// <returns>Una tarea que representa la operación asincrónica. Devuelve un objeto 
        /// <see cref="ResponseCambiarPasswordVM"/> como resultado de la operación.</returns>
        public async Task<ResponseCambiarPasswordVM> CambiarPassword(RequestCambiarPassword model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseCambiarPasswordVM();
            string finalUrl = SetBaseParams(_cambiarPasswordEndpoint).ToString();

            var obj = _mapper.Map<RequestCambiarPasswordSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseCambiarPasswordSvc>(json);

                resultVm = _mapper.Map<ResponseCambiarPasswordVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Obtiene los datos del usuario según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener los datos del usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseGetDatosUsuarioVM"/> con los datos del usuario.</returns>
        public async Task<ResponseGetDatosUsuarioVM> GetDatosUsuario(RequestGetDatosUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetDatosUsuarioVM();
            string finalUrl = SetBaseParams(_getDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDatosUsuarioSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetDatosUsuarioSvc>(json);

                resultVm.Data = _mapper.Map<ResponseGetDatosUsuarioVMData>(parsed?.Data);
                resultVm.ResponseDescription = parsed?.ResponseDescription;
            } 
            else if (result.StatusCode == HttpStatusCode.InternalServerError)
            {
                // TODO:
            }

            return resultVm;
        }

        /// <summary>
        /// Obtiene la dieta del usuario según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener la dieta del usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseGetDietaDeUsuarioVM"/> con los detalles de la dieta del usuario.</returns>
        public async Task<ResponseGetDietaDeUsuarioVM> GetDietaUsuario(RequestGetDietaDeUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetDietaDeUsuarioVM();
            string finalUrl = SetBaseParams(_getDietaUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDietaUsuarioSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetDietaUsuarioSvc>(json);

                resultVm = _mapper.Map<ResponseGetDietaDeUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Obtiene la lista de dietas de usuario según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener la lista de dietas de usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseRequestGetListDietasDeUsuarioVM"/> con la lista de dietas de usuario.</returns>
        public async Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRequestGetListDietasDeUsuarioVM();
            string finalUrl = SetBaseParams(_getListDietasUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListDietasDeUsuarioSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetListDietasDeUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseRequestGetListDietasDeUsuarioVM>(parsed);
            }

            return resultVm;
        }


        /// <summary>
        /// Obtiene la lista de rutinas de usuario según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener la lista de rutinas de usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseGetListRutinasUsuarioVM"/> con la lista de rutinas de usuario.</returns>
        public async Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetListRutinasUsuarioVM();
            string finalUrl = SetBaseParams(_getListRutinasEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListRutinasUsuarioSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetListRutinasUsuarioSvc>(json);

                resultVm = _mapper.Map<ResponseGetListRutinasUsuarioVM>(parsed);

                foreach (var item in resultVm?.Data)
                {
                    // Evitar mandar una lista nula al cliente si está vacía
                    item.ComidasConsumidas ??= [];
                }
            }

            return resultVm;
        }

        /// <summary>
        /// Obtiene la lista de alimentos según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener la lista de alimentos.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseGetAlimentosVM"/> con la lista de alimentos.</returns>
        public async Task<ResponseGetAlimentosVM> GetAlimentos(RequestGetAlimentos model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetAlimentosVM();
            string finalUrl = SetBaseParams(_getAlimentosEndpoint).ToString();

            var obj = _mapper.Map<RequestGetAlimentosSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetAlimentosSvc>(json);

                resultVm = _mapper.Map<ResponseGetAlimentosVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Obtiene los detalles de una rutina según el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para obtener los detalles de la rutina.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseGetRutinaPorIdVM"/> con los detalles de la rutina.</returns>
        public async Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetRutinaPorIdVM();
            string finalUrl = SetBaseParams(_getRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestGetRutinaPorIdSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetRutinaPorIdSvc>(json);

                resultVm = _mapper.Map<ResponseGetRutinaPorIdVM>(parsed);

                // Evitar mandar al cliente una lista vacía
                if (resultVm?.Data.ComidasConsumidas == null)
                {
                    resultVm.Data.ComidasConsumidas = [];
                }
            }

            return resultVm;
        }

        /// <summary>
        /// Realiza una solicitud de inicio de sesión utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para iniciar sesión.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseLoginVM"/> con los resultados del inicio de sesión.</returns>
        public async Task<ResponseLoginVM> Login(RequestLogin model)
        {
            var resultVm = new ResponseLoginVM();
            string finalUrl = SetBaseParams(_loginEndpoint).ToString();

            var obj = _mapper.Map<RequestLoginSvc>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseLoginSvc>(json);

                resultVm.ResponseDescription = parsed?.ResponseDescription ?? string.Empty;
                resultVm.Success = parsed!.Success;

                resultVm.Data = parsed!.Success ? _mapper.Map<ResponseLoginVMData>(parsed) : null;

                if (resultVm.Success)
                {
                    // token logic
                    // el username es el email, es de esta forma que identifcamos a los usuarios
                    var encriptedEmail = EncodeUserEmail(model.Email);

                    var input = new GenerateJwtTokenIn()
                    {
                        Username = encriptedEmail,
                    };

                    var output = JwtTokenHandler.GenerateJwt(input);

                    resultVm.Data.Token = output.Token;
                    resultVm.Data.TokenExpirationDate = output.TokenExpireDate;
                    resultVm.Data.TokenDuration = output.TokenExpireTime;

#if DEBUG
                    var token = JwtTokenHandler.GetClaimFromJwt(output.Token, "email");
                    var decrypt = DecodeUserEmail(token);
                    var e = decrypt;
#endif
                }

            }

            return resultVm;
        }

        /// <summary>
        /// Modifica una dieta utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para modificar la dieta.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseModificarDietaVM"/> con los resultados de la modificación de la dieta.</returns>
        public async Task<ResponseModificarDietaVM> ModificarDieta(RequestModificarDieta model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModificarDietaVM();
            string finalUrl = SetBaseParams(_modificarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestModifcarDietaSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModifcarDietaSvcOut>(json);

                resultVm = _mapper.Map<ResponseModificarDietaVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Modifica los datos de un usuario utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para modificar los datos del usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseModificarDatosUsuarioVM"/> con los resultados de la modificación de los datos del usuario.</returns>
        public async Task<ResponseModificarDatosUsuarioVM> ModificarDatosUsuario(RequestModificarDatosUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModificarDatosUsuarioVM();
            string finalUrl = SetBaseParams(_modificarDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarDatosUsuarioSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModificarDatosUsuarioSvc>(json);

                resultVm = _mapper.Map<ResponseModificarDatosUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Modifica una rutina utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para modificar la rutina.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseModificarRutinaVM"/> con los resultados de la modificación de la rutina.</returns>
        public async Task<ResponseModificarRutinaVM> ModificarRutina(RequestModificarRutina model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModificarRutinaVM();
            string finalUrl = SetBaseParams(_modificarRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarRutinaSvc>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModificarRutinaSvc>(json);

                resultVm = _mapper.Map<ResponseModificarRutinaVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Registra una nueva rutina utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para registrar la rutina.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseRegistrarRutinaVM"/> con los resultados del registro de la rutina.</returns>
        public async Task<ResponseRegistrarRutinaVM> RegistrarRutina(RequestRegistrarRutina model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRegistrarRutinaVM();
            string finalUrl = SetBaseParams(_registrarRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestRegistrarRutinaIn>(model);
            obj.Email = DecodeUserEmail(emailFromToken);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<RequestRegistrarRutinaOut>(json);

                resultVm = _mapper.Map<ResponseRegistrarRutinaVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// Registra un nuevo usuario utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para registrar el usuario.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseRegistrarUsuarioVM"/> con los resultados del registro del usuario.</returns>
        public async Task<ResponseRegistrarUsuarioVM> RegistrarUsuario(RequestRegistrarUsuario model)
        {
            var resultVm = new ResponseRegistrarUsuarioVM();
            string finalUrl = SetBaseParams(_registerEndpoint).ToString();

            var obj = _mapper.Map<RequestRegisterSvc>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.InternalServerError)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<bool>(json);

                resultVm.Success = parsed;
                resultVm.ResponseDescription = resultVm.Success ? "Usuario registrado" : "Error en el registro";
            }

            return resultVm;
        }

        /// <summary>
        /// Registra una nueva dieta utilizando el modelo proporcionado.
        /// </summary>
        /// <param name="model">Modelo de solicitud para registrar la dieta.</param>
        /// <returns>Una tarea que representa la operación asincrónica. 
        /// Devuelve un objeto <see cref="ResponseRegistrarDietaVM"/> con los resultados del registro de la dieta.</returns>
        public async Task<ResponseRegistrarDietaVM> RegistrarDieta(RequestRegistrarDieta model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRegistrarDietaVM();
            string finalUrl = SetBaseParams(_registrarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestRegistrarDietaSvcIn>(model);
            obj.Email = DecodeUserEmail(emailFromToken)    ;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseRegistrarDietaOut>(json);

                resultVm = _mapper.Map<ResponseRegistrarDietaVM>(parsed);
            }

            return resultVm;
        }

        #region Private Methods

        /// <summary>
        /// Devuelve el correo electrónico encriptado en base 64.
        /// </summary>
        /// <param name="email">Correo electrónico a encriptar.</param>
        /// <returns>El correo electrónico encriptado en formato base 64.</returns>
        private static string EncodeUserEmail(string email)
        {
            byte[] encrypted;

            // Create a new instance of the Aes
            // class.  This generates a new key and initialization
            // vector (IV).
            {
                // Encrypt the string to an array of bytes.
                encrypted = Cryptography.EncryptStringToBytes_Aes(email);
            }

            return Convert.ToBase64String(encrypted);
        }

        /// <summary>
        /// Email desencriptado
        /// </summary>
        /// <param name="email">64 base encripted email</param>
        /// <returns></returns>
        private static string DecodeUserEmail(string email)
        {
            string roundtrip;
            // Create a new instance of the Aes
            // class.  This generates a new key and initialization
            // vector (IV).
            {
                // Decrypt the bytes to a string.
                roundtrip = Cryptography.DecryptStringFromBytes_Aes(Convert.FromBase64String(email));
            }

            return roundtrip;
        }

        /// <summary>
        /// Configura los parámetros base para construir una URI.
        /// </summary>
        /// <param name="baseUrl">URL base.</param>
        /// <param name="port">Puerto opcional (por defecto es 8080).</param>
        /// <returns>Objeto UriBuilder configurado con los parámetros base especificados.</returns>
        private static UriBuilder SetBaseParams(string baseUrl, int port = 8080)
        {
            UriBuilder result = new(baseUrl)
            {
                Port = port
            };

            var query = HttpUtility.ParseQueryString(result.Query);

            result.Query = query.ToString();

            return result;
        }

        #endregion
    }
}
