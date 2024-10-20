using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PPAI.Entidades
{
    public class Vino
    {
        private string nombre;
        private bool imagenEtiqueta;
        private float precioARS;
        private int aniada;
        private string notaDeCataBodega;
        private DateTime fechaActualizacion;
        private List<Maridaje> maridajes;
        private List<Varietal> varietales;


        public Vino(string nombre, bool imagenEtiqueta, float precioARS, int aniada, string notaDeCataBodega, DateTime fechaActualizacion, List<Maridaje> maridajes, List<TipoDeUva> tiposDeUva, List<Varietal> varietales)
        {
            this.nombre = nombre;
            this.imagenEtiqueta = imagenEtiqueta;
            this.precioARS = precioARS;
            this.aniada = aniada;
            this.notaDeCataBodega = notaDeCataBodega;
            this.fechaActualizacion = fechaActualizacion;
            this.maridajes = maridajes;
            if (tiposDeUva != null)
            {
                this.varietales = CrearVarietales(varietales, tiposDeUva);
            }
            else
            {
                this.varietales = varietales;
            }
        }

        public string Nombre
        {
            get => nombre;
            set => nombre = value;
        }

        //15°)
        public bool ImagenEtiqueta
        {
            get => imagenEtiqueta;
            set => imagenEtiqueta = value;
        }

        //16°)
        public float PrecioArs
        {
            get => precioARS;
            set => precioARS = value;
        }

        public int Aniada
        {
            get => aniada;
            set => aniada = value;
        }

        //17°)
        public string NotaDeCataBodega
        {
            get => notaDeCataBodega;
            set => notaDeCataBodega = value;
        }

        //14°)
        public DateTime FechaActualizacion
        {
            get => fechaActualizacion;
            set => fechaActualizacion = value;
        }

        public List<Maridaje> Maridajes
        {
            get => maridajes;
            set => maridajes = value;
        }

        public List<Varietal> Varietales
        {
            get => varietales; 
            set => varietales = value;
        }


        public List<Varietal> CrearVarietales(List<Varietal> varietalesParaActualizacion, List<TipoDeUva> tiposUvaParaActualizacion)
        {
            List<Varietal> varietales = new List<Varietal>();
            foreach (Varietal varietal in varietalesParaActualizacion)
            {
                foreach (TipoDeUva tipoUva in tiposUvaParaActualizacion)
                    if (varietal.TipoDeUva.Nombre == tipoUva.Nombre)
                    {
                        varietales.Add(varietal); //Se añade el varietal a la lista de varietales del Vino cuando este encuentra su tipo de uva correspondiente en la actualización.
                    }
            }
            return varietales;
        }


        public override string ToString()
        {
            return $"Nombre: {Nombre} | Precio(ARS): {precioARS} | Nota de Cata: {NotaDeCataBodega} | Tiene Etiqueta: {ImagenEtiqueta}";
        }
    }
}
