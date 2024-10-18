using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PPAI.Recursos_Extra
{
    public partial class BarraDeCarga : Form
    {
        private string modo;
        public string Modo
        {
            get { return modo; }
            set { modo = value; }
        }

        public BarraDeCarga()
        {
            InitializeComponent();
        }


        private void BarraDeCarga_Load(object sender, EventArgs e)
        {
            timerBarraCarga.Start();
        }


        private void timerBarraCarga_Tick(object sender, EventArgs e)
        {
            if (barraCarga.Value < 100)
            {
                barraCarga.Value += 2;
                lblCargando.Text = Modo + "..." + barraCarga.Value.ToString() + "%";
            }
            else {
                timerBarraCarga.Stop();
                this.Hide();
            }
        }


    }
}
