/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

public class PantallaResumenNovedadesVino extends javax.swing.JFrame {
    private List<HashMap<String, Object>> resumenNovedadesVinos;
    private PantallaImportarBodega pantallaImportarBodega;
    
    public PantallaResumenNovedadesVino() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\resources\\images\\vino.png");
        this.setIconImage(icon);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panVinosResumen = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNovedadesVino = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        comBodegas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resumen de actualizaciones");
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                volverAPantallaImportarBodega(evt);
            }
        });

        panVinosResumen.setPreferredSize(new java.awt.Dimension(750, 664));

        lblTitulo2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo2.setText("Resumen de Actualizaciones");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Novedades de Vinos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 12))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 311));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        tblNovedadesVino.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        tblNovedadesVino.setFocusable(false);
        tblNovedadesVino.setShowGrid(false);
        tblNovedadesVino.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblNovedadesVino);

        jPanel1.add(jScrollPane3);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodegas Actualizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 12))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(650, 123));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        comBodegas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        comBodegas.setName(""); // NOI18N
        comBodegas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comBodegasActionPerformed(evt);
            }
        });
        jPanel3.add(comBodegas);

        javax.swing.GroupLayout panVinosResumenLayout = new javax.swing.GroupLayout(panVinosResumen);
        panVinosResumen.setLayout(panVinosResumenLayout);
        panVinosResumenLayout.setHorizontalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)))
                    .addGroup(panVinosResumenLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblTitulo2)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panVinosResumenLayout.setVerticalGroup(
            panVinosResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panVinosResumenLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo2)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVinosResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panVinosResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverAPantallaImportarBodega(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_volverAPantallaImportarBodega
        JOptionPane.showMessageDialog(null, "¡Se han enviado las notificaciones!");
        pantallaImportarBodega.getPantallaNotificacionPush().setVisible(true);
    }//GEN-LAST:event_volverAPantallaImportarBodega

    private void comBodegasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comBodegasActionPerformed
        String bodegaSeleccionada = (String) comBodegas.getSelectedItem();
        List<HashMap<String,Object>> resumenNovedadesBodegaSeleccionada = filtrarVinosDeBodegaSeleccionada(bodegaSeleccionada);
        cargarResumenVinos(resumenNovedadesBodegaSeleccionada);
    }//GEN-LAST:event_comBodegasActionPerformed

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
    private javax.swing.JComboBox<String> comBodegas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel panVinosResumen;
    private javax.swing.JTable tblNovedadesVino;
    // End of variables declaration//GEN-END:variables
}
