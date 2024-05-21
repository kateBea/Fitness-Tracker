using FT___Base.Models;
using FT___Base.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace FT___Base.Interfaces
{
    public interface IBaseServices
    {
        public Task<ResponseLoginVM> Login(RequestLogin model);
        public Task<ResponseRegistrarUsuarioVM> RegistrarUsuario(RequestRegistrarUsuario model);
        public Task<ResponseCambiarPasswordVM> CambiarPassword(RequestCambiarPassword model);
        public Task<ResponseRegistrarDietaVM> RegistrarDieta(RequestRegistrarDieta model);
        public Task<ResponseModifcarDietaVM> ModificarDieta(RequestModificarDieta model);
        public Task<ResponseGetDatosUsuarioVM> GetDatosUsuario(RequestGetDatosUsuario model);
        public Task<ResponseModificarDatosUsuarioVM> ModificarDatosUsuario(RequestModificarDatosUsuario model);
        public Task<ResponseRequestGetListDietasDeUsuarioVM> GetListDietasDeUsuario(RequestGetListDietasDeUsuario model);
        public Task<ResponseGetDietaDeUsuarioVM> GetDietaUsuario(RequestGetDietaDeUsuario model);
        public Task<ResponseGetRutinaPorIdVM> GetRutinaPorId(RequestGetRutinaPorId model);
        public Task<ResponseModificarRutinaVM> ModificarRutina(RequestModificarRutina model);
        public Task<ResponseGetListRutinasUsuarioVM> GetListRutinasUsuario(RequestGetListRutinasUsuario model);
        public Task<ResponseRegistrarRutinaVM> RegistrarRutina(RequestRegistrarRutina model);
    }
}
