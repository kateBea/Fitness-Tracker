using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shared.Contexts
{
    public interface IDataHttpContext
    {
        public string GetHeaderByName(string name);
    }
}
