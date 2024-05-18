﻿using Shared.Utilities;

namespace FT___Base.ViewModels
{
    /// <summary>
    /// 
    /// </summary>
    public class ResponseRegisterVM : BaseResponse
    {
        public string Email { get; set; } = string.Empty;
        public string Username { get; set; } = string.Empty;
        public string Name { get; set; } = string.Empty;
        public string FirstSurname { get; set; } = string.Empty;
        public string SecondSurname { get; set; } = string.Empty;
        public string Password { get; set; } = string.Empty;
        public DateTime Birthday { get; set; } = DateTime.Now.AddYears(-18);
        public DateTime CreateDate { get; set; } = DateTime.Now;
        public float Height { get; set; } = 0.0f;
        public float Weight { get; set; } = 0.0f;
        public string Sex { get; set; } = string.Empty;
    }
}
