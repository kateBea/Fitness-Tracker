﻿using FluentValidation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppFitnessTrackerAI.Validator
{
    public class BaseValidator<T> : AbstractValidator<T>
    {
        public BaseValidator()
        {
            
        }
    }
}