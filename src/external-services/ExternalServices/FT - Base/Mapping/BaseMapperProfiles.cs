using AutoMapper;
using FT___Base.Models;
using FT___Base.ViewModels;
using FTAlimentos.Models;
using Shared.Utilities;
using static FT___Base.ViewModels.ResponseLoginVM;

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
            CreateMap<ResponseGetDatosUsuarioSvcOut, ResponseGetDatosUsuarioVM>();

            CreateMap<RequestGetDietaDeUsuario, RequestGetDietaUsuarioInSvc>();
            CreateMap<ResponseGetDietaUsuarioSvcOut, ResponseGetDietaDeUsuarioVM>();

            CreateMap<RequestRegistrarRutina, RequestRegistrarRutinaIn>();
            CreateMap<RequestRegistrarRutinaOut, ResponseRegistrarRutinaVM>();

            CreateMap<RequestGetListDietasDeUsuario, RequestGetListDietasDeUsuarioSvcIn>();
            CreateMap<ResponseGetListDietasDeUsuarioSvcOut, ResponseRequestGetListDietasDeUsuarioVM>();

            CreateMap<RequestGetListRutinasUsuario, RequestGetListRutinasUsuarioSvcIn>();
            CreateMap<ResponseGetListRutinasUsuarioSvcOut, ResponseGetListRutinasUsuarioVM>();

            CreateMap<RequestGetRutinaPorId, RequestGetRutinaPorIdSvcIn>();
            CreateMap<ResponseGetRutinaPorIdSvcOut, ResponseGetRutinaPorIdVM>();

            CreateMap<RequestLogin, RequestLoginSvcIn>();
            CreateMap<RequestLoginSvcOut, ResponseLoginVMData>();


            CreateMap<RequestModificarDieta, RequestModifcarDietaSvcIn>();
            CreateMap<ResponseModifcarDietaSvcOut, ResponseModifcarDietaVM>();

            CreateMap<RequestModificarDatosUsuario, RequestModificarDatosUsuarioSvcIn>();
            CreateMap<ResponseModificarDatosUsuarioSvcOut, ResponseModificarDatosUsuarioVM>();

            CreateMap<RequestModificarRutina, RequestModificarRutinaSvcIn>();
            CreateMap<ResponseModificarRutinaSvcOut, ResponseModificarRutinaVM>();

            CreateMap<RequestRegistrarUsuario, RequestRegisterSvcIn>();
            CreateMap<ResponseRegisterSvcOut, ResponseRegistrarUsuarioVM>();

            CreateMap<RequestRegistrarDieta, RequestRegistrarDietaSvcIn>();
            CreateMap<ResponseRegistrarDietaOut, ResponseRegistrarDietaVM>();
        }
    }


}
