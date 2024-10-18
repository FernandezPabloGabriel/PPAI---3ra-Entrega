using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PPAI.Entidades
{
    public class Usuario
    {
        private string nombre;
        private string password;
        private bool esPremium;

        public Usuario(string nombre, string password, bool esPremium) 
        { 
            this.nombre = nombre;
            this.password = password;
            this.esPremium = esPremium;
        }
        
        public string Nombre
        {
            get => nombre;
            set => nombre = value;
        }

        public string Password
        {
            get => password;
            set => password = value;
        }

        public bool EsPremium
        {
            get => esPremium;
            set => esPremium = value;
        }
        // Falta agregar el metodo mostrarnombre()
    }
}
