using FT___Base.Models;
using FT___Base.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace FT___Base.Interfaces
{
    public interface IBaseServices
    {
        public Task<ResponseLoginVM> Login(RequestLogin model);
        public Task<ResponseRegisterVM> Register(RequestRegister model);
        public Task<ResponseCambiasPasswordVM> CambiarPassword(RequestCambiasPassword model);
        public Task<ResponseRegistrarDietaVM> RegistrarDieta(RquestRegistrarDieta model);
        public Task<ResponseGetDatosUsuario> GetDatosUsuario(RequestGetDatosUsuario model);
        public Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model);
        public Task<ResponseGetDietaDeUsuarioVM> GetDietaDeUsuario(RequestGetDietaDeUsuario model);
        public Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model);
        public Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model);
    }
}
