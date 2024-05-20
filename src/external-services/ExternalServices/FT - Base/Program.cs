using FluentValidation;
using FT___Base.Interfaces;
using FT___Base.Models;
using FT___Base.Services;
using FT___Base.Validators;
using System.Reflection;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    // Set the comments path for the Swagger JSON and UI.
    var xmlFile = $"{Assembly.GetExecutingAssembly().GetName().Name}.xml";
    var xmlPath = Path.Combine(AppContext.BaseDirectory, xmlFile);
    c.IncludeXmlComments(xmlPath);
});

// Add services
builder.Services.AddScoped<IBaseServices, BaseServices>();

// Add validators
builder.Services.AddScoped<IValidator<RequestLogin>, RequestLoginValidator>();
builder.Services.AddScoped<IValidator<RequestRegistrarUsuario>, RegisterUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestCambiarPassword>, CambiarPasswordValidator>();

builder.Services.AddScoped<IValidator<RequestRegistrarDieta>, RequestRegistrarDietaValidator>();
builder.Services.AddScoped<IValidator<RequestModificarDieta>, RequestModificarDietaValidator>();
builder.Services.AddScoped<IValidator<RequestGetDatosUsuario>, RequestGetDatosUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestModificarDatosUsuario>, RequestModificarDatosUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetListDietasDeUsuario>, RequestGetListDietasDeUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetDietaDeUsuario>, RequestGetDietaDeUsuarioValidator>();
builder.Services.AddScoped<IValidator<RequestGetRutinaPorId>, RequestGetRutinaPorIdValidator>();
builder.Services.AddScoped<IValidator<RequestModificarRutina>, RequestModificarRutinaValidator>();
builder.Services.AddScoped<IValidator<RequestGetListRutinasUsuario>, RequestGetListRutinasUsuarioValidator>();

// Mappings
builder.Services.AddAutoMapper(typeof(BaseServices));   

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
