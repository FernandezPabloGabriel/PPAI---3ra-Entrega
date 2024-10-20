using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using PPAI.Entidades;

namespace PPAI.Recursos_Extra
{
    public class Deserializador<T>
    {
        //public List<T> RecuperarJson(string _ruta)
        //{
        //    var datosJSON = ObtenerDatosJSON(_ruta);
        //    var datos = DeserializarJSON(datosJSON);
        //    return datos;
        //}

        //public static string ObtenerDatosJSON(string _ruta)
        //{
        //    string datosJSON;
        //    //Forma estandar de leer un archivo de disco
        //    using (var reader = new StreamReader(_ruta))
        //    {
        //        datosJSON = reader.ReadToEnd();
        //    }
        //    return datosJSON;
        //}
        public static List<T> DeserializarJSON(string datosJSON)
        {
            var datos = JsonConvert.DeserializeObject<List<T>>(datosJSON);
            return datos;
        }
    }
}
