/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class PantallaResumenNovedadesVino extends javax.swing.JFrame {
    public PantallaResumenNovedadesVino() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panVinosResumen = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNovedadesVino = new javax.swing.JTable();
        comBodegas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setPreferredSize(new java.awt.Dimension(600, 492));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                volverAPantallaImportarBodega(evt);
            }
        });

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo2.setText("Resumen de Actualizaciones");

        tblNovedadesVino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vino", "Nota de Cata", "Precio ARS", "Etiqueta", "Estado"
            }
        ));
        jScrollPane3.setViewportView(tblNovedadesVino);

        comBodegas.setName(""); // NOI18N
        comBodegas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comBodegasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panVinosResumenLayout = new javax.swing.GroupLayout(panVinosResumen);
        panVinosResumen.setLayout(panVinosResumenLayout);
        panVinosResumenLayout.setHorizontalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(comBodegas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(lblTitulo2)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panVinosResumenLayout.setVerticalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo2)
                .addGap(33, 33, 33)
                .addComponent(comBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVinosResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVinosResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comBodegasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comBodegasActionPerformed
        String bodegaSeleccionada = (String) comBodegas.getSelectedItem();
        List<HashMap<String,Object>> resumenNovedadesBodegaSeleccionada = filtrarVinosDeBodegaSeleccionada(bodegaSeleccionada);
        cargarResumenVinos(resumenNovedadesBodegaSeleccionada);
    }//GEN-LAST:event_comBodegasActionPerformed

    private void volverAPantallaImportarBodega(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_volverAPantallaImportarBodega
        pantallaImportarBodega.setVisible(true);
    }//GEN-LAST:event_volverAPantallaImportarBodega

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PantallaResumenNovedadesVino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PantallaResumenNovedadesVino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PantallaResumenNovedadesVino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PantallaResumenNovedadesVino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PantallaResumenNovedadesVino().setVisible(true);
//            }
//        });
//    }
    
    public void mostrarResumenVinosImportados(List<HashMap<String,Object>> resumenNovedadesVinosPar){
        resumenNovedadesVinos = resumenNovedadesVinosPar;
        String primerBodega = (String) resumenNovedadesVinos.get(0).get("bodega");
        
        //Añadir las bodegas actualizadas al combo box
        List<String> bodegasActualizadas = 
                resumenNovedadesVinos
                    .stream()
                    .map(vino->(String) vino.get("bodega"))
                    .distinct()
                    .collect(Collectors.toList());
        cargarBodegas(bodegasActualizadas);
        
        //Cargamos el primer vino de la lista por defecto
        List<HashMap<String,Object>> resumenNovedadesPrimerBodega = filtrarVinosDeBodegaSeleccionada(primerBodega);
        cargarResumenVinos(resumenNovedadesPrimerBodega);
    }
    
    public void cargarBodegas(List<String> bodegasActualizadas){
        bodegasActualizadas.forEach(bode->comBodegas.addItem(bode));
    }
    
    public List<HashMap<String,Object>> filtrarVinosDeBodegaSeleccionada(String bodegaSeleccionada){
        return resumenNovedadesVinos
                .stream()
                .filter(vino->((String) vino.get("bodega")).equalsIgnoreCase(bodegaSeleccionada))
                .collect(Collectors.toList());
    }
    
    public void cargarResumenVinos(List<HashMap<String,Object>> resumenNovedadesBodegaSeleccionada){
        DefaultTableModel mt = (DefaultTableModel) tblNovedadesVino.getModel();
        
        for(int i = mt.getRowCount()-1; i >= 0; i--){
            mt.removeRow(i);
        }
        
        for(HashMap<String,Object> novedadVino: resumenNovedadesBodegaSeleccionada){
            mt.addRow(new Object[]{
                novedadVino.get("nombre"),
                novedadVino.get("notaDeCataBodega"),
                novedadVino.get("precioArs"),
                novedadVino.get("imagenEtiqueta"),
                novedadVino.get("tipo")
            });
        }
    }

    public void setPantallaImportarBodega(PantallaImportarBodega pantallaImportarBodega) {
        this.pantallaImportarBodega = pantallaImportarBodega;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comBodegas;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel panVinosResumen;
    private javax.swing.JTable tblNovedadesVino;
    // End of variables declaration//GEN-END:variables
    private List<HashMap<String, Object>> resumenNovedadesVinos;
    private PantallaImportarBodega pantallaImportarBodega;
}
