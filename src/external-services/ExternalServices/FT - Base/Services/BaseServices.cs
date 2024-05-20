﻿using FT___Base.Interfaces;
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

        private readonly HttpClient _httpClient;
        private readonly IMapper _mapper;

        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <param name="configuration"></param>
        /// <param name="mapper"></param>
        public BaseServices(IConfiguration configuration, IMapper mapper)
        {
            _mapper = mapper;

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
            var resultVm = new ResponseCambiarPasswordVM();
            string finalUrl = _SetBaseParams(_cambiarPasswordEndpoint).ToString();

            var obj = _mapper.Map<CambiarPasswordSvcIn>(model);

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
            var resultVm = new ResponseGetDatosUsuarioVM();
            string finalUrl = _SetBaseParams(_getDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDatosUsuarioSvcIn>(model);

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
            var resultVm = new ResponseGetDietaDeUsuarioVM();
            string finalUrl = _SetBaseParams(_getDietaUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetDietaUsuarioInSvc>(model);

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
            var resultVm = new ResponseRequestGetListDietasDeUsuarioVM();
            string finalUrl = _SetBaseParams(_getListDietasUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListDietasDeUsuarioSvcIn>(model);

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
            var resultVm = new ResponseGetListRutinasUsuarioVM();
            string finalUrl = _SetBaseParams(_getListRutinasEndpoint).ToString();

            var obj = _mapper.Map<RequestGetListRutinasUsuarioSvcIn>(model);

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
            var resultVm = new ResponseGetRutinaPorIdVM();
            string finalUrl = _SetBaseParams(_getRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestGetRutinaPorIdSvcIn>(model);

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

                resultVm = _mapper.Map<ResponseLoginVM>(parsed);
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
            var resultVm = new ResponseModifcarDietaVM();
            string finalUrl = _SetBaseParams(_modificarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestModifcarDietaSvcIn>(model);

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
            var resultVm = new ResponseModificarDatosUsuarioVM();
            string finalUrl = _SetBaseParams(_modificarDatosUsuarioEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarDatosUsuarioSvcIn>(model);

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
            var resultVm = new ResponseModificarRutinaVM();
            string finalUrl = _SetBaseParams(_modificarRutinaEndpoint).ToString();

            var obj = _mapper.Map<RequestModificarRutinaSvcIn>(model);

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

        /// <summary>
        /// 
        /// </summary>
        /// <param name="model"></param>
        /// <returns></returns>
        public async Task<ResponseRegisterVM> Register(RequestRegistrarUsuario model)
        {
            var resultVm = new ResponseRegisterVM();
            string finalUrl = _SetBaseParams(_registerEndpoint).ToString();

            var obj = _mapper.Map<RequestRegisterSvcIn>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseRegisterSvcOut>(json);

                resultVm = _mapper.Map<ResponseRegisterVM>(parsed);
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
            var resultVm = new ResponseRegistrarDietaVM();
            string finalUrl = _SetBaseParams(_registrarDietaEndpoint).ToString();

            var obj = _mapper.Map<RequestRegistrarDietaSvcIn>(model);

            var requestJson = JsonConvert.SerializeObject(obj, Formatting.Indented);
            var result = await _httpClient.PostAsync(finalUrl, new StringContent(requestJson, Encoding.UTF8, "application/json"));

            if (result.StatusCode == HttpStatusCode.OK || result.StatusCode == HttpStatusCode.BadRequest)
            {
                var json = await result.Content.ReadAsStringAsync();
                var parsed = JsonConvert.DeserializeObject<ResponseRegistrarDietaSvcOut>(json);

                resultVm = _mapper.Map<ResponseRegistrarDietaVM>(parsed);
            }

            return resultVm;
        }

        #region Private Methods

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
