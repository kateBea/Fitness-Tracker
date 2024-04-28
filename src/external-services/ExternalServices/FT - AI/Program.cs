// Inclusions
using FTAI.Extensions;
using FTAI.Utilities;

// Create builder
var builder = WebApplication.CreateBuilder(args);

// Configure dependecnies
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
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
