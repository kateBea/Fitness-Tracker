using FT___Base.Interfaces;
using FT___Base.Models;
using FT___Base.ViewModels;
using System.Web;
using System;
using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using FTAlimentos.Models;
using System.Net.Http.Headers;
using System.Net;
using AutoMapper;
using static FT___Base.ViewModels.ResponseLoginVM;
using Security.Authentication;
using Shared.Contexts;
using Shared.Constants;
using System.Security.Claims;
using System.Security.Cryptography;
using Shared.Utilities;

namespace FT___Base.Services
{
    /// <summary>
    /// 
    /// </summary>
    public class BaseServices : IBaseServices
    {
        #region Properties

        private readonly string _loginEndpoint;
        private readonly string _registerEndpoint;
        private readonly string _cambiarPasswordEndpoint;
        private readonly string _registrarDietaEndpoint;
        private readonly string _modificarDietaEndpoint;
        private readonly string _modificarRutinaEndpoint;
        private readonly string _getDatosUsuarioEndpoint;
        private readonly string _modificarDatosUsuarioEndpoint;
        private readonly string _getListDietasUsuarioEndpoint;
        private readonly string _getDietaUsuarioEndpoint;
        private readonly string _getRutinaEndpoint;
        private readonly string _getListRutinasEndpoint;
        private readonly string _registrarRutinaEndpoint;

        private readonly HttpClient _httpClient;
        private readonly IMapper _mapper;

        private readonly IDataHttpContext _dataHttpContext;

        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <param name="configuration"></param>
        /// <param name="mapper"></param>
        public BaseServices(IConfiguration configuration, IMapper mapper, IDataHttpContext dataHttpContext)
        {
            _mapper = mapper;
            _dataHttpContext = dataHttpContext;

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

            _httpClient = new HttpClient();
            _httpClient.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseCambiarPasswordVM> CambiarPassword(RequestCambiarPassword model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseCambiarPasswordVM();
            string finalUrl = _SetBaseParams(_cambiarPasswordEndpoint).ToString();

            var obj = _mapper.Map<CambiarPasswordSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<CambiarPasswordSvcOut>(json);

                resultVm = _mapper.Map<ResponseCambiarPasswordVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseGetDatosUsuarioVM> GetDatosUsuario(RequestGetDatosUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetDatosUsuarioVM();
            string finalUrl = _SetBaseParams(_getDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDatosUsuarioSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.Found || result.StatusCode == HttpStatusCode.NotFound)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetDatosUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseGetDatosUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseGetDietaDeUsuarioVM> GetDietaUsuario(RequestGetDietaDeUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetDietaDeUsuarioVM();
            string finalUrl = _SetBaseParams(_getDietaUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDietaUsuarioInSvc>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.NotFound)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetDietaUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseGetDietaDeUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRequestGetListDietasDeUsuarioVM();
            string finalUrl = _SetBaseParams(_getListDietasUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListDietasDeUsuarioSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetListDietasDeUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseRequestGetListDietasDeUsuarioVM>(parsed);
            }

            return resultVm;
        }


        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetListRutinasUsuarioVM();
            string finalUrl = _SetBaseParams(_getListRutinasEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListRutinasUsuarioSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.NotFound)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetListRutinasUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseGetListRutinasUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseGetRutinaPorIdVM();
            string finalUrl = _SetBaseParams(_getRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestGetRutinaPorIdSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.NotFound)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseGetRutinaPorIdSvcOut>(json);

                resultVm = _mapper.Map<ResponseGetRutinaPorIdVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseLoginVM> Login(RequestLogin model)
        {
            var resultVm = new ResponseLoginVM();
            string finalUrl = _SetBaseParams(_loginEndpoint).ToString();

            var obj = _mapper.Map<RequestLoginSvcIn>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<RequestLoginSvcOut>(json);

                resultVm.ResponseDescription = parsed?.ResponseDescription ?? string.Empty;
                resultVm.Success = parsed!.Success;

                resultVm.Data = parsed!.Success ? _mapper.Map<ResponseLoginVMData>(parsed) : null;

                if (resultVm.Success)
                {
                    // token logic
                    // el username es el email, es de esta forma que identifcamos a los
                    // usuarios, para evitar exponerlo en los claims del token se encripta
                    var encriptedEmail = EncodeUserEmail(model.Email);

                    var input = new GenerateJwtTokenIn()
                    {
                        Username = encriptedEmail,
                    };

                    var output = JwtTokenHandler.GenerateJwt(input);

                    resultVm.Data.Token = output.Token;
                    resultVm.Data.TokenExpirationDate = output.TokenExpireDate;
                    resultVm.Data.TokenDuration = output.TokenExpireTime;
                }

            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseModifcarDietaVM> ModificarDieta(RequestModificarDieta model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModifcarDietaVM();
            string finalUrl = _SetBaseParams(_modificarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestModifcarDietaSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModifcarDietaSvcOut>(json);

                resultVm = _mapper.Map<ResponseModifcarDietaVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseModificarDatosUsuarioVM> ModificarDatosUsuario(RequestModificarDatosUsuario model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModificarDatosUsuarioVM();
            string finalUrl = _SetBaseParams(_modificarDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarDatosUsuarioSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.NotFound)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModificarDatosUsuarioSvcOut>(json);

                resultVm = _mapper.Map<ResponseModificarDatosUsuarioVM>(parsed);
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseModificarRutinaVM> ModificarRutina(RequestModificarRutina model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseModificarRutinaVM();
            string finalUrl = _SetBaseParams(_modificarRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarRutinaSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PutAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseModificarRutinaSvcOut>(json);

                resultVm = _mapper.Map<ResponseModificarRutinaVM>(parsed);
            }

            return resultVm;
        }

        public async Task<ResponseRegistrarRutinaVM> RegistrarRutina(RequestRegistrarRutina model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRegistrarRutinaVM();
            string finalUrl = _SetBaseParams(_registrarRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestRegistrarRutinaIn>(model);
            obj.Email = emailFromToken;

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
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseRegistrarUsuarioVM> RegistrarUsuario(RequestRegistrarUsuario model)
        {
            var resultVm = new ResponseRegistrarUsuarioVM();
            string finalUrl = _SetBaseParams(_registerEndpoint).ToString();

            var obj = _mapper.Map<RequestRegisterSvcIn>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<bool>(json);

                resultVm.Success = parsed;
                resultVm.ResponseDescription = parsed!? "Usuario registrado" : "Error en el registro";
            }

            return resultVm;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseRegistrarDietaVM> RegistrarDieta(RequestRegistrarDieta model)
        {
            string token = _dataHttpContext.GetHeaderByName(HttpConstants.AuthorizationHeader).Split(" ").Last();
            string emailFromToken = JwtTokenHandler.GetClaimFromJwt(token, "email");

            var resultVm = new ResponseRegistrarDietaVM();
            string finalUrl = _SetBaseParams(_registrarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestRegistrarDietaSvcIn>(model);
            obj.Email = emailFromToken;

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseRegistrarDietaOut>(json);

                resultVm = _mapper.Map<ResponseRegistrarDietaVM>(parsed);
            }

            return resultVm;
        }

        #region Private Methods

        /// <summary>
        /// Returns encripted email in 64 base
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private string EncodeUserEmail(string email)
        {
            byte[] encrypted;

            // Create a new instance of the Aes
            // class.  This generates a new key and initialization
            // vector (IV).
            using (Aes myAes = Aes.Create())
            {

                // Encrypt the string to an array of bytes.
                encrypted = Cryptography.EncryptStringToBytes_Aes(email, myAes.Key, myAes.IV);
            }

            return Convert.ToBase64String(encrypted);
        }

        /// <summary>
        /// returns decripted email
        /// </summary>
        /// <param name="email">64 base encripted email</param>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private string DencodeUserEmail(string email)
        {
            string roundtrip = string.Empty;
            // Create a new instance of the Aes
            // class.  This generates a new key and initialization
            // vector (IV).
            using (Aes myAes = Aes.Create())
            {
                // Decrypt the bytes to a string.
                roundtrip = Cryptography.DecryptStringFromBytes_Aes(Convert.FromBase64String(email), myAes.Key, myAes.IV);
            }

            return roundtrip;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="baseUrl"></param>
        /// <param name="port"></param>
        /// <returns></returns>
        private UriBuilder _SetBaseParams(string baseUrl, int port = 8080)
        {
            UriBuilder result = new UriBuilder(baseUrl);

            result.Port = port;
            var query = HttpUtility.ParseQueryString(result.Query);

            result.Query = query.ToString();

            return result;
        }

        #endregion
    }
}
