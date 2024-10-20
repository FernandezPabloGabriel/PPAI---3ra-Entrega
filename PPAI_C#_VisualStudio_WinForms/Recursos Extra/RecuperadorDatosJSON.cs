using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PPAI.Recursos_Extra
{
    public class RecuperadorDatosJSON
    {
        public static string ObtenerJSON(string _ruta)
        {
            string datosJSON;
            //Forma estandar de leer un archivo de disco
            using (var reader = new StreamReader(_ruta))
            {
                datosJSON = reader.ReadToEnd();
            }
            return datosJSON;
        }
    }
}
