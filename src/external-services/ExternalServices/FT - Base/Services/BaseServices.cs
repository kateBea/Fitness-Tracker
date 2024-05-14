using FT___Base.Interfaces;
using FT___Base.Models;
using FT___Base.ViewModels;

namespace FT___Base.Services
{
    public class BaseServices : IBaseServices
    {
        public async Task<ResponseCambiasPasswordVM> CambiarPassword(RequestCambiasPassword model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseGetDatosUsuario> GetDatosUsuario(RequestGetDatosUsuario model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseGetDietaDeUsuarioVM> GetDietaDeUsuario(RequestGetDietaDeUsuario model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseLoginVM> Login(RequestLogin model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseRegisterVM> Register(RequestRegister model)
        {
            throw new NotImplementedException();
        }

        public async Task<ResponseRegistrarDietaVM> RegistrarDieta(RquestRegistrarDieta model)
        {
            throw new NotImplementedException();
        }
    }
}
