using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using PPAI.Entidades;
using PPAI.Boundaries;
using System.Reflection;
using PPAI.Recursos_Extra;


namespace PPAI.Gestor
{
    public class GestorImportarActualizacionBodegas
    {
        public static List<Bodega>? ListaBodegas { get; set; } //Lista de bodegas que se cargan al principio del programa
        public static List<Maridaje>? ListaMaridajes { get; set; } //Lista de maridajes que se cargan al principio del programa
        public static List<TipoDeUva>? ListaTiposUva { get; set; } //Lista de tipos de uva que se cargan al principio del programa
        public static List<Usuario>? ListaUsuarios { get; set; }
        public static string resumenDeImportes = "";
        public static List<Bodega> listaBodegasActualizables = new List<Bodega>();

        private static string _rutaAPIsDeLasBodegas = @"..\..\..\Jsons\APIsDeLasBodegas\";



        //3°) 
        public static List<Bodega> OpcionImportarActualizacionVinosBodega()
        {
            List<Bodega> listaBodegasActualizables = BuscarBodegaParaActualizar(); //Lista de bodegas para actualizar
            return listaBodegasActualizables;
        }


        //4°) Devuelve la lista de bodegas disponibles para actualizar
        private static List<Bodega> BuscarBodegaParaActualizar()
        {
            listaBodegasActualizables.Clear();
            foreach (Bodega bodega in ListaBodegas)
            {
                //Utilización de 5°) y 6°)
                if (bodega.ExisteActualizacionDisponible())
                {
                    listaBodegasActualizables.Add(bodega);
                }
            }
            return listaBodegasActualizables;
        }


        //9°) Tomamos todos los vinos de la API "externa" de la bodega seleccionada
        public static bool TomarBodega(string nombreBodega)
        {
            resumenDeImportes += $"BODEGA: '{nombreBodega}'\n";
            Bodega bodegaSeleccionada = BuscarBodegaSeleccionadaPorNombre(nombreBodega);
            bool hayActualizacion = ObtenerActualizacionDeBodegas(bodegaSeleccionada);

            //ModificarFechaActualizacionDeBodega(bodegaSeleccionada); 
            if (hayActualizacion is false)
            {
                resumenDeImportes += $"No hay actualizaciones a realizar ¡La bodega está al día!\n";
            }
            resumenDeImportes += "\n****************************************************************************************************************\n\n";
            return hayActualizacion;
        }


        //10°)
        private static bool ObtenerActualizacionDeBodegas(Bodega bodegaSeleccionada)
        {
            bool hayActualizacion = false;
            //Bodega bodegaSeleccionada = BuscarBodegaSeleccionadaPorNombre(nombreBodega);
            List<Vino> vinosParaActualizar = [];
            List<Vino> vinosParaCrear = [];


            //El nombre de la bodega en este caso es exactamente igual al de la bodega que importamos, ya que lo usamos como valor de comparación.
            string listaVinosImportadosJSON = InterfazAPIsBodegas.ObtenerActualizacion(bodegaSeleccionada.Nombre, _rutaAPIsDeLasBodegas); //Nos comunicamos con la interfaz para recuperar la ruta de sus datos
            //string listaVinosImportadosJSON = RecuperadorDatosJSON.ObtenerJSON(_rutaAPIBodega);
            var listaVinosImportados = JsonConvert.DeserializeObject<List<Vino>>(listaVinosImportadosJSON);

            (vinosParaActualizar, vinosParaCrear) = CompararVinos_Sistema_Importados(listaVinosImportados, bodegaSeleccionada.ListaVinos, vinosParaActualizar, vinosParaCrear);


            if (vinosParaActualizar.Count > 0)
            {
                hayActualizacion = true;
                ActualizarDatosDeVinosDeBodega(vinosParaActualizar, bodegaSeleccionada);
            }
            if (vinosParaCrear.Count > 0)
            {
                hayActualizacion = true;
                CrearNuevosVinos(vinosParaCrear, bodegaSeleccionada);
            }
            ModificarFechaActualizacionDeBodega(bodegaSeleccionada);
            return hayActualizacion;
        }


        //12°)
        private static void ActualizarDatosDeVinosDeBodega(List<Vino> vinosParaActualizar, Bodega bodegaSeleccionada)
        {
            resumenDeImportes += "*** Vinos Actualizados ***\n" + bodegaSeleccionada.ActualizarVinos(vinosParaActualizar);
            ActualizarListaBodega(bodegaSeleccionada);
        }


        //18°) 
        private static void CrearNuevosVinos(List<Vino> vinosParaCrear, Bodega bodegaSeleccionada)
        {
            resumenDeImportes += "*** Vinos Añadidos ***\n";
            foreach (Vino vinoParaCrear in vinosParaCrear)
            {
                List<Maridaje> maridajesParaActualizacion = BuscarMaridaje(vinoParaCrear);
                List<TipoDeUva> tiposUvaParaActualizacion = BuscarTipoUva(vinoParaCrear);

                Vino vinoCreado = new Vino(vinoParaCrear.Nombre,
                                           vinoParaCrear.ImagenEtiqueta,
                                           vinoParaCrear.PrecioArs,
                                           vinoParaCrear.Aniada,
                                           vinoParaCrear.NotaDeCataBodega,
                                           vinoParaCrear.FechaActualizacion,
                                           maridajesParaActualizacion,
                                           tiposUvaParaActualizacion,
                                           vinoParaCrear.Varietales);
                bodegaSeleccionada.ListaVinos.Add(vinoCreado);

                resumenDeImportes += $"(+) {vinoCreado.ToString()}";
                resumenDeImportes += "\n+ Maridajes:";
                foreach (Maridaje maridaje in vinoCreado.Maridajes)
                {
                    resumenDeImportes += "\n- " + maridaje.ToString();
                }
                resumenDeImportes += "\n+ Varietales:";
                foreach (Varietal varietal in vinoCreado.Varietales)
                {
                    resumenDeImportes += "\n- " + varietal.ToString();
                }
                resumenDeImportes += "\n-----------------------------------------------\n";
            }
            ActualizarListaBodega(bodegaSeleccionada);
        }


        //Almacenará objetos tipo Bodega en su respectiva lista
        public static void RecuperarBodegas(List<Bodega> bodegas)
        {
            ListaBodegas = bodegas;
        }


        //Almacenará objetos tipo TiposUva en su respectiva lista
        public static void RecuperarTiposUva(List<TipoDeUva> tipoUvas)
        {
            ListaTiposUva = tipoUvas;
        }


        //Almacenará objetos tipo Maridajes en su respectiva lista
        public static void RecuperarMaridajes(List<Maridaje> maridajes)
        {
            ListaMaridajes = maridajes;
        }


        //19°) Se añadirá a una lista todos los maridajes del vino importado de la API encontrados en nuestro sistema
        private static List<Maridaje> BuscarMaridaje(Vino vinoParaCrear)
        {
            List<Maridaje> maridajesParaActualizacion = [];
            foreach (Maridaje maridajeImportado in vinoParaCrear.Maridajes)
            {
                foreach (Maridaje maridaje in ListaMaridajes)
                {
                    if (maridaje.MaridaConVino(maridajeImportado))
                    {
                        maridajesParaActualizacion.Add(maridaje);
                    }
                }
            }
            return maridajesParaActualizacion;
        }


        //21°) Verificamos si existen en el sistema los tipos de uva de los varietales de los vinos a crear correspondientes
        private static List<TipoDeUva> BuscarTipoUva(Vino vinoParaCrear)
        {
            List<TipoDeUva> tiposUvaParaActualizacion = [];
            foreach (Varietal varietalImportado in vinoParaCrear.Varietales)
            {
                foreach (TipoDeUva tipoUva in ListaTiposUva)
                {
                    if (tipoUva.EsTipoUva(varietalImportado.TipoDeUva))
                    {
                        tiposUvaParaActualizacion.Add(tipoUva);
                    }
                }
            }
            return tiposUvaParaActualizacion;
        }


        /*-----METODOS AUXILIARES-----*/
        private static Bodega BuscarBodegaSeleccionadaPorNombre(string nombreBodega)
        {
            foreach (Bodega bodega in ListaBodegas)
            {
                if (bodega.Nombre == nombreBodega)
                {
                    return bodega;
                }
            }
            return null;
        }


        //El equivalente al Opt "Actualizar o crear vino"
        private static Tuple<List<Vino>, List<Vino>> CompararVinos_Sistema_Importados(List<Vino> vinosImportados, List<Vino> vinosSistema, List<Vino> vinosParaActualizar, List<Vino> vinosParaCrear)
        {
            bool existeVino = false;

            //Comparar lista de vinos de la bodega seleccionada con la lista importada de vinos de la bodega
            foreach (Vino vinoImportado in vinosImportados)
            {
                foreach (Vino vinoSistema in vinosSistema)
                {
                    if (vinoImportado.Nombre == vinoSistema.Nombre) //Si tienen el mismo nombre entonces hay existencia del vino en el sistema
                    {
                        if (CompararAtributos(vinoSistema, vinoImportado) is false) //Si son distintos en algún atributo que no sea el nombre lo añadimos a la lista
                        {
                            vinosParaActualizar.Add(vinoImportado);
                        }
                        existeVino = true;
                    }
                }
                if (existeVino is false) //Si no hay existencia del vino en el sistema lo añadimos a lista para crear
                {
                    vinosParaCrear.Add(vinoImportado);
                }
                existeVino = false;
            }
            return new Tuple<List<Vino>, List<Vino>>(vinosParaActualizar, vinosParaCrear);
        }


        private static bool CompararAtributos(Vino vinoSistema, Vino vinoImportado)
        {
            if (vinoSistema.ImagenEtiqueta != vinoImportado.ImagenEtiqueta)
            {
                return false;
            }
            if (vinoSistema.PrecioArs != vinoImportado.PrecioArs)
            {
                return false;
            }
            if (vinoSistema.NotaDeCataBodega != vinoSistema.NotaDeCataBodega)
            {
                return false;
            }
            return true;
        }


        //Modificamos la fecha de actualización de la bodega
        private static void ModificarFechaActualizacionDeBodega(Bodega bodegaSeleccionada)
        {
            foreach (Bodega bodega in ListaBodegas)
            {
                if (bodega.Nombre == bodegaSeleccionada.Nombre)
                {
                    bodega.FechaUltimaActualizacion = DateTime.Now;
                }
            }
        }


        private static void ActualizarListaBodega(Bodega bodegaSeleccionada)
        {
            for (int i = 0; i < ListaBodegas.Count; i++)
            {
                if (bodegaSeleccionada.Nombre == ListaBodegas[i].Nombre)
                {
                    ListaBodegas[i] = bodegaSeleccionada;
                    //ListaBodegas[i].FechaUltimaActualizacion = DateTime.Now; 
                }
            }
        }
    }
}
