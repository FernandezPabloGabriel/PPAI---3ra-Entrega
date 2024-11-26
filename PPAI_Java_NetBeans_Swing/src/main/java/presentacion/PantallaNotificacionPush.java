/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PantallaNotificacionPush extends javax.swing.JFrame{
    private List<HashMap<String,Object>> resumenNotificaciones;
    private PantallaImportarBodega pantallaImportarBodega;
    
    public PantallaNotificacionPush() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\resources\\images\\vino.png");
        this.setIconImage(icon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblInformeNotificaciones = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cmbBodegas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informe notificaciones");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                volverAPantallaImportarBodega(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInformeNotificaciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblInformeNotificaciones.setText("Informe Notificaciones Push");
        jPanel1.add(lblInformeNotificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 44));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios notificados"));
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 174));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUsuario);
        if (tblUsuario.getColumnModel().getColumnCount() > 0) {
            tblUsuario.getColumnModel().getColumn(0).setResizable(false);
            tblUsuario.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        jPanel2.add(jScrollPane1);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 380, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Bodega Actualizada"));
        jPanel4.setPreferredSize(new java.awt.Dimension(450, 100));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        cmbBodegas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBodegasActionPerformed(evt);
            }
        });
        jPanel4.add(cmbBodegas);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 380, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbBodegasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBodegasActionPerformed
        String bodegaSeleccionada = (String) cmbBodegas.getSelectedItem();
        for(HashMap<String,Object> datosNotificacionPorBodega: resumenNotificaciones){
            String bodega = (String) datosNotificacionPorBodega.get("bodega");
            if(bodega.equalsIgnoreCase(bodegaSeleccionada)){
                cargarResumenVinos(datosNotificacionPorBodega);
            }
        }
    }//GEN-LAST:event_cmbBodegasActionPerformed

    private void volverAPantallaImportarBodega(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_volverAPantallaImportarBodega
        this.pantallaImportarBodega.setVisible(true);
        this.pantallaImportarBodega.corroborarBodegasRestantes();
    }//GEN-LAST:event_volverAPantallaImportarBodega

    public void mostrarResumenNotificaciones(List<HashMap<String,Object>> resumenNotificacionesParam) {
        resumenNotificaciones = resumenNotificacionesParam;
        cargarBodegas();
        cargarResumenVinos(resumenNotificaciones.get(0));
    }
    
    public void cargarBodegas(){
        for(HashMap<String,Object> datosNotificacionPorBodega: resumenNotificaciones){
            cmbBodegas.addItem((String) datosNotificacionPorBodega.get("bodega"));
        }
    }
    
    public void cargarResumenVinos(HashMap<String,Object> datosNotificacionPorBodega){
        DefaultTableModel tmUsuario = (DefaultTableModel) tblUsuario.getModel();
        
        for (int i = tmUsuario.getRowCount()-1; i >= 0; i--) {
            tmUsuario.removeRow(i);
        }
        
        List<String> usuariosPorBodega = (List<String>) datosNotificacionPorBodega.get("usuarios");
        for(String usuarios: usuariosPorBodega){
            tmUsuario.addRow(new Object[]{usuarios});
        }
    }

    public void setPantallaImportarBodega(PantallaImportarBodega pantallaImportarBodega) {
        this.pantallaImportarBodega = pantallaImportarBodega;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBodegas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInformeNotificaciones;
    private javax.swing.JTable tblUsuario;
    // End of variables declaration//GEN-END:variables
}
