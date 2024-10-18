namespace PPAI
{
    partial class PantallaImportarActualizacionBodegas
    {
        private System.ComponentModel.IContainer components = null;
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(PantallaImportarActualizacionBodegas));
            btnImportarActu = new Button();
            btnActualizar = new Button();
            lblTitulo = new Label();
            gridBodegasActualizar = new DataGridView();
            Bodega = new DataGridViewTextBoxColumn();
            Seleccionar = new DataGridViewCheckBoxColumn();
            lblActualizar = new Label();
            ((System.ComponentModel.ISupportInitialize)gridBodegasActualizar).BeginInit();
            SuspendLayout();
            // 
            // btnImportarActu
            // 
            btnImportarActu.BackColor = SystemColors.ButtonHighlight;
            btnImportarActu.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnImportarActu.Location = new Point(314, 141);
            btnImportarActu.Margin = new Padding(3, 4, 3, 4);
            btnImportarActu.Name = "btnImportarActu";
            btnImportarActu.Size = new Size(237, 57);
            btnImportarActu.TabIndex = 0;
            btnImportarActu.Text = "Importar Actualizaciones";
            btnImportarActu.UseVisualStyleBackColor = false;
            btnImportarActu.Click += OpcionImportarActualizacionVinosBodega;
            // 
            // btnActualizar
            // 
            btnActualizar.BackColor = SystemColors.ControlLightLight;
            btnActualizar.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnActualizar.Location = new Point(314, 141);
            btnActualizar.Margin = new Padding(3, 4, 3, 4);
            btnActualizar.Name = "btnActualizar";
            btnActualizar.Size = new Size(237, 57);
            btnActualizar.TabIndex = 3;
            btnActualizar.Text = "Actualizar Bodegas";
            btnActualizar.UseVisualStyleBackColor = false;
            btnActualizar.Visible = false;
            btnActualizar.Click += TomarBodega;
            // 
            // lblTitulo
            // 
            lblTitulo.AutoSize = true;
            lblTitulo.BackColor = Color.Transparent;
            lblTitulo.Font = new Font("Segoe UI Black", 24F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblTitulo.Location = new Point(182, 45);
            lblTitulo.Name = "lblTitulo";
            lblTitulo.Size = new Size(499, 54);
            lblTitulo.TabIndex = 4;
            lblTitulo.Text = "Bienvenido a BONVINO!";
            lblTitulo.TextAlign = ContentAlignment.TopCenter;
            // 
            // gridBodegasActualizar
            // 
            gridBodegasActualizar.AllowUserToAddRows = false;
            gridBodegasActualizar.AllowUserToDeleteRows = false;
            gridBodegasActualizar.AllowUserToResizeColumns = false;
            gridBodegasActualizar.AllowUserToResizeRows = false;
            gridBodegasActualizar.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            gridBodegasActualizar.Columns.AddRange(new DataGridViewColumn[] { Bodega, Seleccionar });
            gridBodegasActualizar.Cursor = Cursors.No;
            gridBodegasActualizar.Location = new Point(165, 251);
            gridBodegasActualizar.Name = "gridBodegasActualizar";
            gridBodegasActualizar.RowHeadersWidth = 51;
            gridBodegasActualizar.ScrollBars = ScrollBars.Vertical;
            gridBodegasActualizar.Size = new Size(539, 222);
            gridBodegasActualizar.TabIndex = 6;
            // 
            // Bodega
            // 
            Bodega.HeaderText = "Bodega";
            Bodega.MinimumWidth = 6;
            Bodega.Name = "Bodega";
            Bodega.ReadOnly = true;
            Bodega.Width = 300;
            // 
            // Seleccionar
            // 
            Seleccionar.HeaderText = "Seleccionar";
            Seleccionar.MinimumWidth = 6;
            Seleccionar.Name = "Seleccionar";
            Seleccionar.Width = 200;
            // 
            // lblActualizar
            // 
            lblActualizar.AutoSize = true;
            lblActualizar.BackColor = Color.Transparent;
            lblActualizar.Font = new Font("Segoe UI Black", 24F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblActualizar.Location = new Point(107, 45);
            lblActualizar.Name = "lblActualizar";
            lblActualizar.Size = new Size(655, 54);
            lblActualizar.TabIndex = 7;
            lblActualizar.Text = "Seleccione los vinos a actualizar:";
            lblActualizar.TextAlign = ContentAlignment.TopCenter;
            lblActualizar.Visible = false;
            // 
            // PantallaImportarActualizacionBodegas
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackgroundImage = Properties.Resources.fondoCorcho2;
            BackgroundImageLayout = ImageLayout.Stretch;
            ClientSize = new Size(866, 591);
            Controls.Add(gridBodegasActualizar);
            Controls.Add(lblTitulo);
            Controls.Add(btnActualizar);
            Controls.Add(btnImportarActu);
            Controls.Add(lblActualizar);
            Icon = (Icon)resources.GetObject("$this.Icon");
            Margin = new Padding(3, 4, 3, 4);
            MaximizeBox = false;
            Name = "PantallaImportarActualizacionBodegas";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Importar actualización vinos de bodega";
            Load += PantallaImportarActualizacionBodegas_Load;
            ((System.ComponentModel.ISupportInitialize)gridBodegasActualizar).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnImportarActu;
        private Button btnActualizar;
        private Label lblTitulo;
        private DataGridView gridBodegasActualizar;
        private DataGridViewTextBoxColumn Bodega;
        private DataGridViewCheckBoxColumn Seleccionar;
        private Label lblActualizar;
    }
}