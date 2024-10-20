using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPAI.Entidades
{
    public class Siguiendo
    {
        private string fechaInicio;
        private string fechaFin;
        private Bodega bodega;

        public Siguiendo(string fechaInicio, string fechaFin, Bodega bodega)
        {
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.bodega = bodega;

        }
        public string FechaInicio
        {
            get => fechaInicio;
            set => fechaInicio = value;
        }
        public string FechaFin
        {
            get => fechaFin;
            set => fechaFin = value;
        }
        public Bodega Bodega
        {
            get => bodega; 
            set => bodega = value;
        }
    }
}

// Faltaría agregar método sosDeBodega()