using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace Security.Authentication
{
    public static class JwtTokenHandler
    {
        public static GenerateJwtTokenOut? GenerateJwt(GenerateJwtTokenIn data)
        {
            var tokenKey = Encoding.ASCII.GetBytes(AuthConstants.JWT_SECURITY_KEY);
            var tokenExpiryTimeStamp = DateTime.Now.AddSeconds(AuthConstants.JWT_TOKEN_VALIDITY_TIME);

            var claimsIdentity = new ClaimsIdentity(new List<Claim>
            {
                new Claim("email", data.Username),
                new Claim(ClaimTypes.Role, data.Role)
            });

            var signingCredentials = 
                new SigningCredentials(new SymmetricSecurityKey(tokenKey), SecurityAlgorithms.HmacSha256Signature);

            var securityTokenDescriptor = new SecurityTokenDescriptor()
            {
                Subject = claimsIdentity,
                Expires = tokenExpiryTimeStamp,
                SigningCredentials = signingCredentials,
            };

            var jwtSecurityTokenHandler = new JwtSecurityTokenHandler();
            var securityToken = jwtSecurityTokenHandler.CreateToken(securityTokenDescriptor);
            var token = jwtSecurityTokenHandler.WriteToken(securityToken);

            return new GenerateJwtTokenOut()
            {
                Token = token,
                TokenExpireTime = (int)tokenExpiryTimeStamp.Subtract(DateTime.Now).TotalSeconds,
                TokenExpireDate = tokenExpiryTimeStamp,
            };
        }

        public static string GetClaimFromJwt(string jwtToken, string claimType)
        {
            var handler = new JwtSecurityTokenHandler();
            var jsonToken = handler.ReadToken(jwtToken) as JwtSecurityToken;

            if (jsonToken == null)
            {
                throw new ArgumentException("Invalid JWT token");
            }

            var claim = jsonToken.Claims.FirstOrDefault(c => c.Type == claimType);

            return claim?.Value;
        }

        public static IServiceCollection AddCustomJwtAuthentication(this IServiceCollection service)
        {
            service.AddAuthentication(options =>
            {
                options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
            }).AddJwtBearer(options =>
            {
                options.Events = new JwtBearerEvents
                {
                    OnChallenge = async context =>
                    {
                        // Call this to skip the default logic and avoid using the default response
                        context.HandleResponse();

                        // Write to the response in any way you wish
                        context.Response.StatusCode = 401;
                        context.Response.Headers.Append("my-custom-header", "custom-value");
                        await context.Response.WriteAsync(@"{ \ }");
                    }
                };

                options.RequireHttpsMetadata = false;
                options.SaveToken = true;
                options.TokenValidationParameters = new TokenValidationParameters()
                {
                    ValidateIssuerSigningKey = true,
                    ValidateIssuer = false,
                    ValidateAudience = false,
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(AuthConstants.JWT_SECURITY_KEY))
                };
            });

            service.AddProblemDetails(options =>
            {

            });

            return service;
        }
    }

    public class GenerateJwtTokenIn
    {
        public string Username { get; set; }
        public string Role { get; set; } = "User"; // default role is user
    }

    public class GenerateJwtTokenOut
    {
        public string Token { get; set; }
        public int TokenExpireTime { get; set; } // in seconds
        public DateTime TokenExpireDate { get; set; }
    }
}
