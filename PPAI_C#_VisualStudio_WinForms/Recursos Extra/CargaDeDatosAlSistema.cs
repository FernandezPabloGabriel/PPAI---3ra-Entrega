using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using PPAI.Entidades;
using PPAI.Gestor;
using System.Reflection;
using PPAI.Boundaries;

namespace PPAI.Recursos_Extra
{
    public class CargaDeDatosAlSistema
    {
        // Por defecto la ruta relativa hacia nuestra carpeta "Jsons" va a estar precedida por "\bin\Debug\net8.0-windows", por lo tanto utilizamos ..\..\..\ para acceder a ella
        private static string _rutaBodegasSistema = @"..\..\..\Jsons\DatosDelSistema\Bodegas.json";
        private static string _rutaTiposUvaSistema = @"..\..\..\Jsons\DatosDelSistema\TiposUva.json";
        private static string _rutaMaridajesSistema = @"..\..\..\Jsons\DatosDelSistema\Maridajes.json";


        public static void RecuperarDatosDeJSONs()
        {
            //Bodegas
            AgregarBodegas(_rutaBodegasSistema);

            //Tipos de Uva
            AgregarTiposUva(_rutaTiposUvaSistema);

            //Maridajes
            AgregarMaridajes(_rutaMaridajesSistema);
        }

        private static void AgregarBodegas(string ruta)
        {
            string vinosDeJson;
            using (var reader = new StreamReader(ruta))
            {
                vinosDeJson = reader.ReadToEnd();
            }
            var vinos = JsonConvert.DeserializeObject<List<Bodega>>(vinosDeJson);

            //List<Bodega> listaBodegas = Deserializador<Bodega>.DeserializarJSON(listaBodegasJSON);
            GestorImportarActualizacionBodegas.RecuperarBodegas(vinos);
        }

        private static void AgregarTiposUva(string ruta)
        {
            var listaTiposUvaJSON = RecuperadorDatosJSON.ObtenerJSON(ruta);
            List<TipoDeUva> listaTiposUva = Deserializador<TipoDeUva>.DeserializarJSON(listaTiposUvaJSON);
            GestorImportarActualizacionBodegas.RecuperarTiposUva(listaTiposUva);
        }

        private static void AgregarMaridajes(string ruta)
        {
            var listaMaridajesJSON = RecuperadorDatosJSON.ObtenerJSON(ruta);
            List<Maridaje> listaMaridajes = Deserializador<Maridaje>.DeserializarJSON(listaMaridajesJSON);
            GestorImportarActualizacionBodegas.RecuperarMaridajes(listaMaridajes);
        }
    }
}
