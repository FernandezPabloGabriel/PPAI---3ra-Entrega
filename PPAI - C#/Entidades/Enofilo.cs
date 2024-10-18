using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace PPAI.Entidades
{
    public class Enofilo
    {
        private string nombre;
        private string apellido;
        private bool imagenPerfil;
        private List<Siguiendo> suscripciones;
        private Usuario usuario;

        public Enofilo(string nombre, string apellido, bool imagenPerfil, List<Siguiendo> siguiendo, Usuario usuario)
        {
            this.nombre = nombre;
            this.apellido = apellido;
            this.imagenPerfil = imagenPerfil;
            this.suscripciones = siguiendo;
            this.usuario = usuario;
        }
        public string Nombre
        {
            get => nombre;
            set => nombre = value;
        }
        public string Apellido
        {
            get => apellido;
            set => apellido = value;
        }
        public bool ImagenPerfil
        {
            get => imagenPerfil;
            set => imagenPerfil = value;
        }
        public List<Siguiendo> Suscripciones
        {
            get => suscripciones;
            set => suscripciones = value;  
        }
        public Usuario Usuario
        {
            get => usuario;
            set => usuario = value;
        }
    }
}

// Falta agregar métodos esSeguidorDeBodega() y obtenerNombreUsuario()