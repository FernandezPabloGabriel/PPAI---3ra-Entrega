using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace PPAI.Entidades
{
    public class Varietal
    {
        private string descripcion;
        private float porcentajeComposicion;
        private TipoDeUva tipoDeUva;

        public Varietal(string descripcion, float porcentajeComposicion, TipoDeUva tipoDeUva)
        {
            this.descripcion = descripcion;
            this.porcentajeComposicion = porcentajeComposicion;
            this.tipoDeUva = tipoDeUva;
        }

        public string Descripcion
        {
            get => descripcion;
            set => descripcion = value;
        }

        public float PorcentajeComposicion
        {
            get => porcentajeComposicion;
            set => porcentajeComposicion = value;
        }

        public TipoDeUva TipoDeUva
        {
            get => tipoDeUva;
            set => tipoDeUva = value;
        }

        public override string ToString()
        {
            return $"Descripcion: {Descripcion} | PorcentajeComposicion: {PorcentajeComposicion} | Tipo de Uva: {TipoDeUva.Nombre}";
        }
    }
}
