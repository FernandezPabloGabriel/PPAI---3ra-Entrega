package presentacion;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logica_de_negocio.controlador.GestorImportadorBodega;

public class PantallaImportarBodega extends javax.swing.JFrame {
    GestorImportadorBodega gestorImportadorBodega;

    public PantallaImportarBodega() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBodegasActualizables = new javax.swing.JTable();
        btnActualizarSelecciones = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnMarcarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("¡Bonvino! Proximamente...");

        tblBodegasActualizables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Actualizar", "Bodegas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBodegasActualizables);
        if (tblBodegasActualizables.getColumnModel().getColumnCount() > 0) {
            tblBodegasActualizables.getColumnModel().getColumn(0).setPreferredWidth(35);
        }

        btnActualizarSelecciones.setText("Actualizar");
        btnActualizarSelecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarSeleccionBodega(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirtomarSeleccionBodega(evt);
            }
        });

        btnMarcarTodo.setText("Marcar todo");
        btnMarcarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodotomarSeleccionBodega(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarSelecciones)))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarSelecciones)
                    .addComponent(btnMarcarTodo)
                    .addComponent(btnSalir))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setGestorImportadorBodega(GestorImportadorBodega gestorImportadorBodega) {
        this.gestorImportadorBodega = gestorImportadorBodega;
    }

    public void opcionImportarActualizacionVino(){
        habilitarPantalla();
        this.gestorImportadorBodega.opcionImportarActualizacionVino();
    }
    
    private void tomarSeleccionBodega(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarSeleccionBodega
        Set<String> bodegasSeleccionadas = new HashSet<>();
        DefaultTableModel mt = (DefaultTableModel) tblBodegasActualizables.getModel();
        int cantidadBodegas = mt.getRowCount();
        boolean haySeleccion = false;
        
        if(cantidadBodegas > 0){
            for(int i = cantidadBodegas - 1; i >= 0; i--){
                Boolean esSeleccionada = (Boolean) mt.getValueAt(i, 0);
                if(esSeleccionada){
                    String nombreBodega = (String) mt.getValueAt(i, 1);
                    bodegasSeleccionadas.add(nombreBodega);
                    mt.removeRow(i);
                    haySeleccion = true;
                }
            }
            if(haySeleccion){
                gestorImportadorBodega.tomarSeleccionBodega(bodegasSeleccionadas);
            } else{
                String[] opcion = {"Aceptar"};
                JOptionPane.showOptionDialog(
                null, 
                "Por favor seleccione una bodega.", 
                "Sin selección",
                JOptionPane.YES_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcion,
                opcion[0]);
            }
            
        } else{
            String[] opcion = {"Aceptar"};
            JOptionPane.showOptionDialog(
                    null, 
                    "No hay mas bodegas que actualizar, saliendo del programa...", 
                    "Finalizando...",
                    JOptionPane.YES_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcion,
                    opcion[0]);
            System.exit(0);
        }
        
    }//GEN-LAST:event_tomarSeleccionBodega

    private void btnSalirtomarSeleccionBodega(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirtomarSeleccionBodega
        String[] opciones = {"Aceptar", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(
                null, 
                "¿Estás seguro de que deseas salir?", 
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[1]);
        if(opcion == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirtomarSeleccionBodega

    private void btnMarcarTodotomarSeleccionBodega(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcarTodotomarSeleccionBodega
        DefaultTableModel tm = (DefaultTableModel) tblBodegasActualizables.getModel();
        int cantBodega = tm.getRowCount();
        
        for (int i = 0; i < cantBodega; i++) {
            tm.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_btnMarcarTodotomarSeleccionBodega

    private void habilitarPantalla(){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void mostrarBodegaParaActualizar(Set<String> nombresBodegasSet){
        DefaultTableModel mt = (DefaultTableModel) tblBodegasActualizables.getModel();
        for(String bodegaNombre:nombresBodegasSet){
            mt.addRow(new Object[]{false, bodegaNombre});
        }
    }
    
    public void mostrarResumenVinosImportados(List<HashMap<String,Object>> resumenNovedadesVino){
        this.setVisible(false);
        PantallaResumenNovedadesVino pantallaResumenVinosImportados = new PantallaResumenNovedadesVino();
        pantallaResumenVinosImportados.setPantallaImportarBodega(this);
        pantallaResumenVinosImportados.setLocationRelativeTo(null);
        pantallaResumenVinosImportados.setVisible(true);
        pantallaResumenVinosImportados.mostrarResumenVinosImportados(resumenNovedadesVino);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarSelecciones;
    private javax.swing.JButton btnMarcarTodo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblBodegasActualizables;
    // End of variables declaration//GEN-END:variables
}
