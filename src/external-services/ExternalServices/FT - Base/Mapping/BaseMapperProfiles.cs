using AutoMapper;
using FT___Base.Models;
using FT___Base.ViewModels;
using FTAlimentos.Models;
using Shared.Utilities;

namespace FT___Base.Mapping
{
    /// <summary>
    /// 
    /// </summary>
    public class BaseMapperProfiles : Profile
    {
        /// <summary>
        /// 
        /// </summary>
        public BaseMapperProfiles()
        {
            CreateMap<RequestCambiarPassword, CambiarPasswordSvcIn>();
            CreateMap<CambiarPasswordSvcOut, ResponseCambiarPasswordVM>();

            CreateMap<RequestGetDatosUsuario, RequestGetDatosUsuarioSvcIn>();
            CreateMap<ResponseGetDietaUsuarioSvcOut, ResponseGetDatosUsuarioVM>();

            CreateMap<RequestGetDietaDeUsuario, RequestGetDatosUsuarioSvcIn>();
            CreateMap<ResponseGetDietaUsuarioSvcOut, ResponseGetDietaDeUsuarioVM>();

            CreateMap<RequestGetListDietasDeUsuario, RequestGetListDietasDeUsuarioSvcIn>();
            CreateMap<ResponseGetListDietasDeUsuarioSvcOut, ResponseRequestGetListDietasDeUsuarioVM>();

            CreateMap<RequestGetListRutinasUsuario, RequestGetListRutinasUsuarioSvcIn>();
            CreateMap<ResponseGetListRutinasUsuarioSvcOut, ResponseGetListRutinasUsuarioVM>();

            CreateMap<RequestGetRutinaPorId, RequestGetRutinaPorIdSvcIn>();
            CreateMap<ResponseGetRutinaPorIdSvcOut, ResponseGetRutinaPorIdVM>();

            CreateMap<RequestLogin, RequestLoginSvcIn>();
            CreateMap<RequestLoginSvcOut, ResponseLoginVM>();

            CreateMap<RequestModificarDieta, RequestModifcarDietaSvcIn>();
            CreateMap<ResponseModifcarDietaSvcOut, ResponseModifcarDietaVM>();

            CreateMap<RequestModificarDatosUsuario, RequestModificarDatosUsuarioSvcIn>();
            CreateMap<ResponseModificarDatosUsuarioSvcOut, ResponseModificarDatosUsuarioVM>();

            CreateMap<RequestModificarRutina, RequestModificarRutinaSvcIn>();
            CreateMap<ResponseModificarRutinaSvcOut, ResponseModificarRutinaVM>();

            CreateMap<RequestRegistrarUsuario, RequestRegisterSvcIn>();
            CreateMap<ResponseRegisterSvcOut, ResponseRegisterVM>();

            CreateMap<RequestRegistrarDieta, RequestRegistrarDietaSvcIn>();
            CreateMap<ResponseRegistrarDietaSvcOut, ResponseRegistrarDietaVM>();
        }
    }

    
}
