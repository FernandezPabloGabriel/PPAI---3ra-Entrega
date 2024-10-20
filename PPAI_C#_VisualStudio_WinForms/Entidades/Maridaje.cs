using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PPAI.Entidades
{
    public class Maridaje
    {

        private string nombre;
        private string descripcion;

        public Maridaje(string nombre, string descripcion)
        {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public string Nombre
        {
            get => nombre;
            set => nombre = value;
        }

        public string Descripcion
        {
            get => descripcion;
            set => descripcion = value;
        }


        //20°)
        public bool MaridaConVino(Maridaje maridajeImportado)
        {
            if (maridajeImportado.Nombre == this.Nombre)
            {
                return true;
            }
            return false;
        }


        public override string ToString()
        {
            return $"Nombre: {Nombre} | Descripcion: {Descripcion}";
        }
    }
}
