// Inclusions
using FluentValidation;
using FTAI.Interfaces;
using FTAI.Mappings;
using FTAI.Models;
using FTAI.Services;
using FTAI.Validator;
using System.Reflection;

// Create builder
var builder = WebApplication.CreateBuilder(args);

// Configure dependecnies
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    // Set the comments path for the Swagger JSON and UI.
    var xmlFile = $"{Assembly.GetExecutingAssembly().GetName().Name}.xml";
    var xmlPath = Path.Combine(AppContext.BaseDirectory, xmlFile);
    c.IncludeXmlComments(xmlPath);
});

builder.Services.AddScoped<IAssistanceService, AssistanceService>();
builder.Services.AddAutoMapper(typeof(MapperProfiles));

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

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

// Run app
app.Run();
