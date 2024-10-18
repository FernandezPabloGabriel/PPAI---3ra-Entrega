using PPAI.Entidades;
using PPAI;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Net.Mime.MediaTypeNames;

namespace PPAI
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Usuario usu = new Usuario(txtUsuario.Text, txtContraseña.Text, true);

            string usuCorrecto = "a";
            string passCorrecta = "a";
            bool esPremium = true;

            if (txtUsuario.Text.Equals(usuCorrecto) && txtContraseña.Text.Equals(passCorrecta) && esPremium is true)
            {   
                // Nombre de usuario y contraseña correctos
                PantallaImportarActualizacionBodegas ventana = new PantallaImportarActualizacionBodegas();
                ventana.Show();
                this.Hide();
            }
            else
            {
                // Nombre de usuario o contraseña incorrectos
                MessageBox.Show("Usuario o contraseña incorrecto");
            }
        }
    }
}
