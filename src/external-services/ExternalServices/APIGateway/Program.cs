using Ocelot.DependencyInjection;
using Ocelot.Middleware;
using System.Reflection;

var builder = WebApplication.CreateBuilder(args);
var baseePath = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location) ?? string.Empty;

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddHttpContextAccessor();

// Setup Ocelot Api Gateway
builder.Configuration
        .SetBasePath(baseePath)
        .AddJsonFile("appsettings.json", optional: false, reloadOnChange: true)
        .AddJsonFile("ocelot.json", optional: false, reloadOnChange: true)
        .AddEnvironmentVariables();

builder.Services.AddSwaggerForOcelot(builder.Configuration);
builder.Services.AddOcelot(builder.Configuration);

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.UseCors(builder => builder
    .AllowAnyOrigin()
    .AllowAnyMethod()
    .AllowAnyHeader());

app.UseSwaggerForOcelotUI(opt =>
{
    opt.PathToSwaggerGenerator = "/swagger/docs";
});

//app.UseHttpsRedirection();

app.UseOcelot().Wait();
app.UseAuthorization();
app.MapControllers();


app.Run();
