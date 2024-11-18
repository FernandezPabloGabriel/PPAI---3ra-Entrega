/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.otros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author USUARIO
 */
public class PantallaCarga extends javax.swing.JFrame {

    public PantallaCarga() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panBackground = new javax.swing.JPanel();
        pgbBarraDeCarga = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cargando bodegas");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        panBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pgbBarraDeCarga.setStringPainted(true);
        panBackground.add(pgbBarraDeCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 340, 40));

        jLabel1.setText("Este proceso puede tardar unos minutos...");
        panBackground.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarBarra(){
//        Thread t1 = new Thread(){
//            public void run(){
//                while(pgbBarraDeCarga.getValue() <= 100){
//                    pgbBarraDeCarga.setValue(pgbBarraDeCarga.getValue()+10);
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(PantallaCarga.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//;
//                }
//            }
//        };
//        t1.start();

// Iniciar barra de progreso y tarea en paralelo
        new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simular carga de datos desde la base de datos
                System.out.println("Cargando datos desde la base de datos...");
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(500); // Simula tiempo de procesamiento
                    publish(i * 10); // Actualiza el progreso
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Actualizar la barra de progreso en el EDT
                for (Integer progreso : chunks) {
                    pgbBarraDeCarga.setValue(progreso);
                }
            }

            @Override
            protected void done() {
                // Acción una vez completada la carga
                System.out.println("Carga completada");
                
            }
        }.execute();
    }
    
        public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaCarga::new);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panBackground;
    private javax.swing.JProgressBar pgbBarraDeCarga;
    // End of variables declaration//GEN-END:variables
}
