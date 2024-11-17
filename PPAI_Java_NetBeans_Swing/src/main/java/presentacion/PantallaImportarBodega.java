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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBodegasActualizables = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnMarcarTodo = new javax.swing.JButton();
        btnActualizarSelecciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Importar Actualizaciones");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bodegas Disponibles"));

        tblBodegasActualizables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nombre"
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
            tblBodegasActualizables.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblBodegasActualizables.getColumnModel().getColumn(1).setPreferredWidth(250);
        }

        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(100, 23));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirtomarSeleccionBodega(evt);
            }
        });

        btnMarcarTodo.setText("Marcar todo");
        btnMarcarTodo.setPreferredSize(new java.awt.Dimension(100, 23));
        btnMarcarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodotomarSeleccionBodega(evt);
            }
        });

        btnActualizarSelecciones.setText("Actualizar");
        btnActualizarSelecciones.setPreferredSize(new java.awt.Dimension(100, 23));
        btnActualizarSelecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarSeleccionBodega(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizarSelecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarSelecciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                JOptionPane.showMessageDialog(null, "Por favor seleccione una bodega");
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
        this.setPantallaResumenNovedadesVino(new PantallaResumenNovedadesVino());
        pantallaResumenNovedadesVino.setPantallaImportarBodega(this);
        pantallaResumenNovedadesVino.setLocationRelativeTo(null);
        pantallaResumenNovedadesVino.setVisible(true);
        pantallaResumenNovedadesVino.mostrarResumenVinosImportados(resumenNovedadesVino);
    }
    
    public void mostrarResumenNotificaciones(List<List<List<String>>> resumenNotificaciones){
        this.setPantallaNotificacionPush(new PantallaNotificacionPush());
        pantallaNotificacionPush.setPantallaImportarBodega(this);
        pantallaNotificacionPush.setLocationRelativeTo(null);
        pantallaNotificacionPush.mostrarResumenNotificaciones(resumenNotificaciones);
    }

    public void setPantallaResumenNovedadesVino(PantallaResumenNovedadesVino pantallaResumenNovedadesVino) {
        this.pantallaResumenNovedadesVino = pantallaResumenNovedadesVino;
    }

    public void setPantallaNotificacionPush(PantallaNotificacionPush pantallaNotificacionPush) {
        this.pantallaNotificacionPush = pantallaNotificacionPush;
    }

    public PantallaNotificacionPush getPantallaNotificacionPush() {
        return pantallaNotificacionPush;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarSelecciones;
    private javax.swing.JButton btnMarcarTodo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblBodegasActualizables;
    // End of variables declaration//GEN-END:variables
    private PantallaResumenNovedadesVino pantallaResumenNovedadesVino;
    private PantallaNotificacionPush pantallaNotificacionPush;
}
