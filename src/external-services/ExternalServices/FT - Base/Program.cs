using FluentValidation;
using FTBase.Interfaces;
using FTBase.Models;
using FTBase.Services;
using FTBase.Validators;
using Security.Authentication;
using System.Reflection;
using Microsoft.OpenApi.Models;
using Shared.Contexts;
using FT___Base.Exceptions;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddCustomJwtAuthentication();
builder.Services.AddHttpContextAccessor();
builder.Services.AddEndpointsApiExplorer();

builder.Services
    .AddProblemDetails(options =>
        options.CustomizeProblemDetails = ctx =>
        {
            ctx.ProblemDetails.Extensions.Add("trace-id", ctx.HttpContext.TraceIdentifier);
            ctx.ProblemDetails.Extensions.Add("instance", $"{ctx.HttpContext.Request.Method} {ctx.HttpContext.Request.Path}");
        });

builder.Services.AddExceptionHandler<ExceptionToProblemDetailsHandler>();

builder.Services.AddSwaggerGen(c =>
{
    c.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        In = ParameterLocation.Header,
        Description = "Por favor, introduce un Token válido",
        Name = "Autorización",
        Type = SecuritySchemeType.Http,
        BearerFormat = "JWT",
        Scheme = "Bearer"
    });

    c.OperationFilter<BasicAuthOperationsFilter>();

    // Set the comments path for the Swagger JSON and UI.
    var xmlFile = $"{Assembly.GetExecutingAssembly().GetName().Name}.xml";
    var xmlPath = Path.Combine(AppContext.BaseDirectory, xmlFile);
    c.IncludeXmlComments(xmlPath);
});


// Add services
builder.Services.AddScoped<IBaseServices, BaseServices>();
builder.Services.AddScoped<IDataHttpContext, DataHttpContext>();

// Add validators
builder.Services.AddScoped<IValidator<RequestLogin>, RequestLoginValidator>();
builder.Services.AddScoped<IValidator<RequestRegistrarUsuario>, RegisterUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestCambiarPassword>, CambiarPasswordValidator>();
builder.Services.AddScoped<IValidator<RequestRegistrarRutina>, RequestRegistrarRutinaValidator>();
builder.Services.AddScoped<IValidator<RequestRegistrarDieta>, RequestRegistrarDietaValidator>();
builder.Services.AddScoped<IValidator<RequestModificarDieta>, RequestModificarDietaValidator>();
builder.Services.AddScoped<IValidator<RequestGetDatosUsuario>, RequestGetDatosUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestModificarDatosUsuario>, RequestModificarDatosUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetListDietasDeUsuario>, RequestGetListDietasDeUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetDietaDeUsuario>, RequestGetDietaDeUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetRutinaPorId>, RequestGetRutinaPorIdValidator>();
builder.Services.AddScoped<IValidator<RequestModificarRutina>, RequestModificarRutinaValidator>();
builder.Services.AddScoped<IValidator<RequestGetListRutinasUsuario>, RequestGetListRutinasUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetAlimentos>, RequestGetAlimentosValidator>();

// Mappings
builder.Services.AddAutoMapper(typeof(BaseServices));   

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseStatusCodePages();

//app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();

app.Run();
