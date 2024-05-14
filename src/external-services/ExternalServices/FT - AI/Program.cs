// Inclusions
using FTAI.Extensions;
using FTAI.Utilities;
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
builder.Services.AddFitnessTrackerDependencies();
builder.Services.AddFitnessTrackerModelValidators();


// Build app
var app = builder.Build();

// Setup add
app.SetupEnvironment();
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

// Run app
app.Run();
