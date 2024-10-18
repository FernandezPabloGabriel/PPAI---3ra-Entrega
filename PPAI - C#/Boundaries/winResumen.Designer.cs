namespace PPAI.Boundaries
{
    partial class winResumen
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
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
            lblTitulo = new Label();
            rtxtResumen = new RichTextBox();
            btnVolver = new Button();
            btnExportar = new Button();
            SuspendLayout();
            // 
            // lblTitulo
            // 
            lblTitulo.AutoSize = true;
            lblTitulo.BackColor = Color.Transparent;
            lblTitulo.Font = new Font("Segoe UI Black", 24F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblTitulo.Location = new Point(114, 27);
            lblTitulo.Name = "lblTitulo";
            lblTitulo.Size = new Size(631, 54);
            lblTitulo.TabIndex = 5;
            lblTitulo.Text = "RESUMEN DE IMPORTACIONES";
            lblTitulo.TextAlign = ContentAlignment.TopCenter;
            // 
            // rtxtResumen
            // 
            rtxtResumen.Cursor = Cursors.IBeam;
            rtxtResumen.Location = new Point(81, 113);
            rtxtResumen.Name = "rtxtResumen";
            rtxtResumen.ReadOnly = true;
            rtxtResumen.Size = new Size(701, 391);
            rtxtResumen.TabIndex = 7;
            rtxtResumen.Text = "";
            // 
            // btnVolver
            // 
            btnVolver.BackColor = SystemColors.ControlDark;
            btnVolver.FlatStyle = FlatStyle.System;
            btnVolver.Font = new Font("Segoe UI", 10.2F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnVolver.Location = new Point(626, 511);
            btnVolver.Margin = new Padding(3, 4, 3, 4);
            btnVolver.Name = "btnVolver";
            btnVolver.Size = new Size(156, 38);
            btnVolver.TabIndex = 8;
            btnVolver.Text = "Volver";
            btnVolver.UseVisualStyleBackColor = false;
            btnVolver.Click += btnVolver_Click;
            // 
            // btnExportar
            // 
            btnExportar.BackColor = SystemColors.ControlDark;
            btnExportar.Enabled = false;
            btnExportar.FlatStyle = FlatStyle.System;
            btnExportar.Font = new Font("Segoe UI", 10.2F, FontStyle.Regular, GraphicsUnit.Point, 0);
            btnExportar.Location = new Point(464, 511);
            btnExportar.Margin = new Padding(3, 4, 3, 4);
            btnExportar.Name = "btnExportar";
            btnExportar.Size = new Size(156, 38);
            btnExportar.TabIndex = 9;
            btnExportar.Text = "Exportar";
            btnExportar.UseVisualStyleBackColor = false;
            btnExportar.Visible = false;
            // 
            // winResumen
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackgroundImage = Properties.Resources.fondoCorcho2;
            BackgroundImageLayout = ImageLayout.Stretch;
            ClientSize = new Size(866, 591);
            Controls.Add(btnExportar);
            Controls.Add(btnVolver);
            Controls.Add(rtxtResumen);
            Controls.Add(lblTitulo);
            Name = "winResumen";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Resumen de Importaciones";
            Load += winResumen_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label lblTitulo;
        private RichTextBox rtxtResumen;
        private Button btnVolver;
        private Button btnExportar;
    }
}