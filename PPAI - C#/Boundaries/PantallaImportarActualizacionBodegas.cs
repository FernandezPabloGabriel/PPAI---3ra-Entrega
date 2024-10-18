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
using System.IO;
using Newtonsoft.Json;
using PPAI.Gestor;
using System.Reflection.Emit;
using PPAI.Recursos_Extra;
using PPAI.Boundaries;


namespace PPAI
{
    public partial class PantallaImportarActualizacionBodegas : Form
    {
        public PantallaImportarActualizacionBodegas() //La función que habilita los componentes de la pantalla
        {
            InitializeComponent();
        }


        //Lo que hará la pantalla al cargarse
        private void PantallaImportarActualizacionBodegas_Load(object sender, EventArgs e)
        {
        }


        //1°) Método que hace referencia a la presión del botón de mismo nombre que nos habilitará el grid
        private void OpcionImportarActualizacionVinosBodega(object sender, EventArgs e)
        {
            habilitarPantalla();
            RecargarPantalla();
        }


        private void RecargarPantalla()
        {
            //Llamar método del gestor, guardamos en una nueva lista de bodegas a aquellas que están en periodo de actualización
            List<Bodega> listaBodegasActualizables = GestorImportarActualizacionBodegas.OpcionImportarActualizacionVinosBodega();
            gridBodegasActualizar.Rows.Clear();

            //Validamos si hay actualizaciones disponibles, si las hay llamamos al método "presentarBodegasParaActualizar" de la interfaz 
            if (listaBodegasActualizables.Count == 0)
            {
                MessageBox.Show("No hay bodegas para actualizar, Estas al día!!!");
            }
            else
            {
                for (int i = 0; i < listaBodegasActualizables.Count; i++)
                {
                    PresentarBodegasParaActualizar(listaBodegasActualizables[i].Nombre);
                }
            }
        }


        //2°)
        private void habilitarPantalla()
        {
            //Llamamos a la barra de carga
            CargarBarraDeCarga("Cargando");

            //Cambiamos algunos valores de los componentes para que se visualice posteriormente las bodegas a actualizar
            lblTitulo.Visible = false;
            lblActualizar.Visible = true;
            btnActualizar.Visible = true;
            btnImportarActu.Visible = false;
            gridBodegasActualizar.Cursor = DefaultCursor;
        }


        //7°)
        private void PresentarBodegasParaActualizar(String nombreBodega)
        {
            DataGridViewRow fila = new DataGridViewRow(); //Creamos una nueva fila del dataGridView
            fila.Cells.Add(new DataGridViewTextBoxCell() { Value = nombreBodega }); //Añadimos celda con el valor del nombre de la bodega
            gridBodegasActualizar.Rows.Add(fila); //Añadimos la fila al dataGridView
        }


        //8°) Le indicamos al gestor que tome todos los vinos de la API de la bodega seleccionada
        private void TomarBodega(object sender, EventArgs e)
        {
            bool haySeleccion = false;
            //Llamar método del gestor
            if (gridBodegasActualizar.Rows.Count == 0)
            {
                MessageBox.Show("No hay bodegas a seleccionar ¡Estas al día!");
            }
            else
            {
                bool hayActualizacion = false;
                for (int i = 0; i < gridBodegasActualizar.Rows.Count; i++)
                {
                    bool seleccionadaBodega = Convert.ToBoolean(gridBodegasActualizar.Rows[i].Cells["Seleccionar"].Value); //Obtenemos valor de verdad del checkbox de la fila
                    if (seleccionadaBodega)
                    {
                        string nombreBodega = Convert.ToString(gridBodegasActualizar.Rows[i].Cells["Bodega"].Value); //Obtenemos texto que guarda la celda de la columna "Bodega"
                        hayActualizacion = GestorImportarActualizacionBodegas.TomarBodega(nombreBodega);
                        haySeleccion = true;
                    }
                }
                if (haySeleccion is false)
                {
                    MessageBox.Show("Por favor selecciona una bodega para actualizar");
                }
                else
                {
                    winResumen resumen = new winResumen();
                    resumen.RtxtResumen = GestorImportarActualizacionBodegas.resumenDeImportes;
                    CargarBarraDeCarga("Generando resumen");
                    resumen.ShowDialog();
                    GestorImportarActualizacionBodegas.resumenDeImportes = "";
                }

                if (hayActualizacion)
                {
                    EnviarNovedades();
                }

                RecargarPantalla();
            }
        }


        private void EnviarNovedades()
        {
            CargarBarraDeCarga("Enviando Novedades");
            MessageBox.Show("¡Novedades enviadas a los seguidores!");
        }


        private void CargarBarraDeCarga(string modo)
        {
            BarraDeCarga barraDeCarga = new BarraDeCarga();
            barraDeCarga.Modo = modo;
            barraDeCarga.ShowDialog();    
        }
    }
}
