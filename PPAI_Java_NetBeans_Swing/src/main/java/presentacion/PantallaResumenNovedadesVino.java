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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNovedadesVino = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        comBodegas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setPreferredSize(new java.awt.Dimension(750, 520));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                volverAPantallaImportarBodega(evt);
            }
        });

        panVinosResumen.setPreferredSize(new java.awt.Dimension(750, 664));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo2.setText("Resumen de Actualizaciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Novedades de Vinos"));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 311));

        tblNovedadesVino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vino", "Nota de Cata", "Precio ARS", "Etiqueta", "Estado", "Nro Varietales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblNovedadesVino);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bodegas Actualizadas"));
        jPanel3.setPreferredSize(new java.awt.Dimension(650, 123));

        comBodegas.setName(""); // NOI18N
        comBodegas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comBodegasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comBodegas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panVinosResumenLayout = new javax.swing.GroupLayout(panVinosResumen);
        panVinosResumen.setLayout(panVinosResumenLayout);
        panVinosResumenLayout.setHorizontalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
                .addGap(101, 101, 101)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lblTitulo2))
        );
        panVinosResumenLayout.setVerticalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblTitulo2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVinosResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panVinosResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverAPantallaImportarBodega(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_volverAPantallaImportarBodega
        pantallaImportarBodega.getPantallaNotificacionPush().setVisible(true);
    }//GEN-LAST:event_volverAPantallaImportarBodega

    private void comBodegasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comBodegasActionPerformed
        String bodegaSeleccionada = (String) comBodegas.getSelectedItem();
        List<HashMap<String,Object>> resumenNovedadesBodegaSeleccionada = filtrarVinosDeBodegaSeleccionada(bodegaSeleccionada);
        cargarResumenVinos(resumenNovedadesBodegaSeleccionada);
    }//GEN-LAST:event_comBodegasActionPerformed

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
        
        //Mostramos por defecto los vinos de la primer bodega en la pantalla
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
            int cant = ((List<List<String>>) novedadVino.get("varietales")).size();
            Object cantVarietales = "-";
            if(cant > 0 && ((String) novedadVino.get("tipo")).equalsIgnoreCase("Creado")){
                cantVarietales = cant;
            }
            mt.addRow(new Object[]{
                novedadVino.get("nombre"),
                novedadVino.get("notaDeCataBodega"),
                novedadVino.get("precioArs"),
                novedadVino.get("imagenEtiqueta"),
                novedadVino.get("tipo"),
                cantVarietales
            });
        }
    }
    
    public void setPantallaImportarBodega(PantallaImportarBodega pantallaImportarBodega) {
        this.pantallaImportarBodega = pantallaImportarBodega;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBodegas;
    private javax.swing.JComboBox<String> cmbBodegas1;
    private javax.swing.JComboBox<String> cmbBodegas10;
    private javax.swing.JComboBox<String> cmbBodegas11;
    private javax.swing.JComboBox<String> cmbBodegas12;
    private javax.swing.JComboBox<String> cmbBodegas13;
    private javax.swing.JComboBox<String> cmbBodegas14;
    private javax.swing.JComboBox<String> cmbBodegas15;
    private javax.swing.JComboBox<String> cmbBodegas16;
    private javax.swing.JComboBox<String> cmbBodegas17;
    private javax.swing.JComboBox<String> cmbBodegas18;
    private javax.swing.JComboBox<String> cmbBodegas19;
    private javax.swing.JComboBox<String> cmbBodegas2;
    private javax.swing.JComboBox<String> cmbBodegas20;
    private javax.swing.JComboBox<String> cmbBodegas21;
    private javax.swing.JComboBox<String> cmbBodegas22;
    private javax.swing.JComboBox<String> cmbBodegas23;
    private javax.swing.JComboBox<String> cmbBodegas24;
    private javax.swing.JComboBox<String> cmbBodegas25;
    private javax.swing.JComboBox<String> cmbBodegas26;
    private javax.swing.JComboBox<String> cmbBodegas27;
    private javax.swing.JComboBox<String> cmbBodegas28;
    private javax.swing.JComboBox<String> cmbBodegas29;
    private javax.swing.JComboBox<String> cmbBodegas3;
    private javax.swing.JComboBox<String> cmbBodegas30;
    private javax.swing.JComboBox<String> cmbBodegas31;
    private javax.swing.JComboBox<String> cmbBodegas32;
    private javax.swing.JComboBox<String> cmbBodegas33;
    private javax.swing.JComboBox<String> cmbBodegas34;
    private javax.swing.JComboBox<String> cmbBodegas4;
    private javax.swing.JComboBox<String> cmbBodegas5;
    private javax.swing.JComboBox<String> cmbBodegas6;
    private javax.swing.JComboBox<String> cmbBodegas7;
    private javax.swing.JComboBox<String> cmbBodegas8;
    private javax.swing.JComboBox<String> cmbBodegas9;
    private javax.swing.JComboBox<String> comBodegas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel panVinosResumen;
    private javax.swing.JTable tblNovedadesVino;
    // End of variables declaration//GEN-END:variables
    private List<HashMap<String, Object>> resumenNovedadesVinos;
    private PantallaImportarBodega pantallaImportarBodega;
}
