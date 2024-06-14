using AutoMapper;
using FTAI.Models;
using FTAI.ViewModels;
using Shared.Utilities;

namespace FTAI.Mappings
{
    /// <summary>
    /// Clase de perfiles de mapeo de AutoMapper.
    /// </summary>
    public class MapperProfiles : Profile
    {
        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="MapperProfiles"/> y configura los mapeos.
        /// </summary>
        public MapperProfiles()
        {
            CreateMap<RequestGenerarDieta, AIRequestDietaSchemaJson>().ReverseMap();

            CreateMap<AIResponseSchemaJson, ResponseGenerarDietaVM>()
                .ForMember(dest => dest.Dieta, opt => opt.MapFrom<CustomResolverAIResponseSchemaJson>());
        }
    }

    /// <summary>
    /// Clase personalizada para resolver el mapeo de AIResponseSchemaJson a Plan.
    /// </summary>
    public class CustomResolverAIResponseSchemaJson : IValueResolver<AIResponseSchemaJson, ResponseGenerarDietaVM, Plan>
    {
        /// <summary>
        /// Resuelve el mapeo de AIResponseSchemaJson a Plan.
        /// </summary>
        /// <param name="source">La instancia de AIResponseSchemaJson que se está mapeando.</param>
        /// <param name="destination">La instancia de destino de ResponseGenerarDietaVM.</param>
        /// <param name="destMember">El miembro de destino Plan.</param>
        /// <param name="context">El contexto de resolución.</param>
        /// <returns>El objeto Plan mapeado.</returns>
        /// <exception cref="NotImplementedException">Lanzada si el método no está implementado.</exception>
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

