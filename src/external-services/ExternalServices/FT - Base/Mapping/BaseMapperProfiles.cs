using AutoMapper;
using FTBase.Models;
using FTBase.ViewModels;
using FTAlimentos.Models;
using Shared.Utilities;
using static FTBase.ViewModels.ResponseGetDatosUsuarioVM;
using static FTBase.ViewModels.ResponseLoginVM;
using static FTBase.Models.ResponseGetAlimentosSvc;

namespace FTBase.Mapping
{
    /// <summary>
    /// Base mapping profiles
    /// </summary>
    public class BaseMapperProfiles : Profile
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public BaseMapperProfiles()
        {
            CreateMap<RequestCambiarPassword, RequestCambiarPasswordSvc>();
            CreateMap<ResponseCambiarPasswordSvc, ResponseCambiarPasswordVM>();

            CreateMap<RequestGetAlimentos, RequestGetAlimentosSvc>();
            CreateMap<ResponseGetAlimentosSvc, ResponseGetAlimentosVM>();

            CreateMap<RequestGetDatosUsuario, RequestGetDatosUsuarioSvc>();
            CreateMap<ResponseGetDatosUsuarioSvcOutData, ResponseGetDatosUsuarioVMData>();

            CreateMap<RequestGetDietaDeUsuario, RequestGetDietaUsuarioSvc>();
            CreateMap<ResponseGetDietaUsuarioSvc, ResponseGetDietaDeUsuarioVM>();

            CreateMap<RequestRegistrarRutina, RequestRegistrarRutinaIn>();
            CreateMap<RequestRegistrarRutinaOut, ResponseRegistrarRutinaVM>();

            CreateMap<RequestGetListDietasDeUsuario, RequestGetListDietasDeUsuarioSvc>();
            CreateMap<ResponseGetListDietasDeUsuarioSvcOut, ResponseRequestGetListDietasDeUsuarioVM>();

            CreateMap<RequestGetListRutinasUsuario, RequestGetListRutinasUsuarioSvc>();
            CreateMap<ResponseGetListRutinasUsuarioSvc, ResponseGetListRutinasUsuarioVM>();

            CreateMap<RequestGetRutinaPorId, RequestGetRutinaPorIdSvc>();
            CreateMap<ResponseGetRutinaPorIdSvc, ResponseGetRutinaPorIdVM>();

            CreateMap<RequestLogin, RequestLoginSvc>();
            CreateMap<ResponseLoginSvc, ResponseLoginVMData>();


            CreateMap<RequestModificarDieta, RequestModifcarDietaSvc>();
            CreateMap<ResponseModifcarDietaSvcOut, ResponseModificarDietaVM>();

            CreateMap<RequestModificarDatosUsuario, RequestModificarDatosUsuarioSvc>();
            CreateMap<ResponseModificarDatosUsuarioSvc, ResponseModificarDatosUsuarioVM>();

            CreateMap<RequestModificarRutina, RequestModificarRutinaSvc>();
            CreateMap<ResponseModificarRutinaSvc, ResponseModificarRutinaVM>();

            CreateMap<RequestRegistrarUsuario, RequestRegisterSvc>();
            CreateMap<ResponseRegisterSvc, ResponseRegistrarUsuarioVM>();

            CreateMap<RequestRegistrarDieta, RequestRegistrarDietaSvcIn>();
            CreateMap<ResponseRegistrarDietaOut, ResponseRegistrarDietaVM>();

            CreateMap<GetAlimentosListItem, GetAlimentosListItemVM>();
        }
    }


}
