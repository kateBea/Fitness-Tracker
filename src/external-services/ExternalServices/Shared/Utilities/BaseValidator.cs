using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FTAI.Validator
{
    /// <summary>
    /// Validador base
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class BaseValidator<T> : AbstractValidator<T>
    {
        /// <summary>
        /// Constructor
        /// </summary>
        public BaseValidator()
        {
            
        }
    }
}
