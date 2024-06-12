using AutoMapper;
using FTAI.Models;
using FTAI.ViewModels;
using Shared.Utilities;

namespace FTAI.Mappings
{
    /// <summary>
    /// 
    /// </summary>
    public class MapperProfiles : Profile
    {
        /// <summary>
        /// 
        /// </summary>
        public MapperProfiles()
        {
            CreateMap<RequestGenerarDieta, AIRequestDietaSchemaJson>().ReverseMap();

            CreateMap<AIResponseSchemaJson, ResponseGenerarDietaVM>()
                .ForMember(dest => dest.Dieta, opt => opt.MapFrom<CustomResolverAIResponseSchemaJson>());
        }
    }

    /// <summary>
    /// 
    /// </summary>
    public class CustomResolverAIResponseSchemaJson : IValueResolver<AIResponseSchemaJson, ResponseGenerarDietaVM, Plan>
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="source"></param>
        /// <param name="destination"></param>
        /// <param name="destMember"></param>
        /// <param name="context"></param>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        public Plan Resolve(AIResponseSchemaJson source, ResponseGenerarDietaVM destination, Plan destMember, ResolutionContext context)
        {
            destMember.FechaFin = source.PlanDieta.FechaFin;
            destMember.FechaInicio = source.PlanDieta.FechaInicio;
            destMember.ConsumoAgua = (float)source.PlanDieta.ConsumoDiarioAguaLitros;
            destMember.GastoCalorias = (float)source.PlanDieta.CaloriasObjetivoDiario;

            foreach (var item in source.PlanDieta.MenuDiario)
            {
                destMember.Comidas.Add(new ComidaVM()
                {
                    Nombre = item.NombreComida,
                    Id = item.ComidaId,
                    HoraConsumo = item.HoraConsumo,
                    OrdenComida = item.OrdenComida,
                    TipoComida = item.TipoComida,
                    CaloriasConsumidas = item.CaloriasConsumidas,
                });
            }

            return destMember;
        }
    }
}
