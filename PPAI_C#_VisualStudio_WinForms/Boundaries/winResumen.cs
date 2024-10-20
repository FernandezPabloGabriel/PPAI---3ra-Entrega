using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace PPAI.Boundaries
{
    public partial class winResumen : Form
    {
        public winResumen()
        {
            InitializeComponent();
        }

        private void winResumen_Load(object sender, EventArgs e)
        {

        }

        private void btnVolver_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        public string RtxtResumen
        {
            get { return rtxtResumen.Text; }
            set { rtxtResumen.Text = value; }
        }
    }
}
