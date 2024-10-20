namespace PPAI.Recursos_Extra
{
    partial class BarraDeCarga
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
            components = new System.ComponentModel.Container();
            barraCarga = new ProgressBar();
            lblCargando = new Label();
            timerBarraCarga = new System.Windows.Forms.Timer(components);
            SuspendLayout();
            // 
            // barraCarga
            // 
            barraCarga.ForeColor = Color.Lime;
            barraCarga.Location = new Point(113, 56);
            barraCarga.Name = "barraCarga";
            barraCarga.Size = new Size(565, 29);
            barraCarga.TabIndex = 9;
            // 
            // lblCargando
            // 
            lblCargando.AutoSize = true;
            lblCargando.Location = new Point(346, 22);
            lblCargando.Name = "lblCargando";
            lblCargando.Size = new Size(103, 20);
            lblCargando.TabIndex = 10;
            lblCargando.Text = "Cargando...0%";
            // 
            // timerBarraCarga
            // 
            timerBarraCarga.Interval = 1;
            timerBarraCarga.Tick += timerBarraCarga_Tick;
            // 
            // BarraDeCarga
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 117);
            Controls.Add(lblCargando);
            Controls.Add(barraCarga);
            Name = "BarraDeCarga";
            ShowIcon = false;
            StartPosition = FormStartPosition.CenterScreen;
            Text = "BarraDeCarga";
            TopMost = true;
            Load += BarraDeCarga_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private ProgressBar barraCarga;
        private Label lblCargando;
        private System.Windows.Forms.Timer timerBarraCarga;
    }
}