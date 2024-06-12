// Inclusions
using FluentValidation;
using FTAI.Interfaces;
using FTAI.Mappings;
using FTAI.Models;
using FTAI.Services;
using FTAI.Validator;
using System.Reflection;
using Security.Authentication;
using Microsoft.OpenApi.Models;
using Microsoft.Extensions.Options;
using Swashbuckle.AspNetCore.Filters;
using Shared.Contexts;
using Microsoft.Extensions.DependencyInjection;
using FTAlimentos.Interfaces;
using FTAlimentos.Services;

// Create builder
var builder = WebApplication.CreateBuilder(args);

// Configure dependecnies
builder.Services.AddControllers();
builder.Services.AddCustomJwtAuthentication();
builder.Services.AddHttpContextAccessor();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        In = ParameterLocation.Header,
        Description = "Please enter a valid token",
        Name = "Authorization",
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

builder.Services.AddScoped<IAssistanceService, AssistanceService>();
builder.Services.AddScoped<IAlimentosService, AlimentosService>();

builder.Services.AddAutoMapper(typeof(MapperProfiles));

builder.Services.AddScoped<IDataHttpContext, DataHttpContext>();

builder.Services.AddScoped<IValidator<RequestMessageDebugLimitTokens>, RequestMessageDebugLimitTokensValidator>();
builder.Services.AddScoped<IValidator<RequestGenerarDieta>, RequestGenerarDietaValidator>();
builder.Services.AddScoped<IValidator<RequestStartNewChatAssistance>, RequestChatAssistantInValidator>();


// Build app
var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

// Run app
app.Run();
