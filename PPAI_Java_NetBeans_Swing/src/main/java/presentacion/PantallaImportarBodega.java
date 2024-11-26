package presentacion;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logica_de_negocio.controlador.GestorImportadorBodega;

public class PantallaImportarBodega extends javax.swing.JFrame {
    private GestorImportadorBodega gestorImportadorBodega;
    private PantallaResumenNovedadesVino pantallaResumenNovedadesVino;
    private PantallaNotificacionPush pantallaNotificacionPush;
    private boolean marcadosTodos = true;

    public PantallaImportarBodega() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\resources\\images\\vino.png");
        this.setIconImage(icon);
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
        setTitle("Importar actualizaciones");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Importar Actualizaciones");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bodegas Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 12))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        tblBodegasActualizables.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
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
        tblBodegasActualizables.setShowGrid(false);
        tblBodegasActualizables.getTableHeader().setResizingAllowed(false);
        tblBodegasActualizables.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblBodegasActualizables);
        if (tblBodegasActualizables.getColumnModel().getColumnCount() > 0) {
            tblBodegasActualizables.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblBodegasActualizables.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.add(jScrollPane1);

        btnSalir.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSalir.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirtomarSeleccionBodega(evt);
            }
        });

        btnMarcarTodo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnMarcarTodo.setText("Marcar todo");
        btnMarcarTodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnMarcarTodo.setPreferredSize(new java.awt.Dimension(100, 35));
        btnMarcarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcarTodotomarSeleccionBodega(evt);
            }
        });

        btnActualizarSelecciones.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        btnActualizarSelecciones.setText("Actualizar");
        btnActualizarSelecciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnActualizarSelecciones.setPreferredSize(new java.awt.Dimension(100, 35));
        btnActualizarSelecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarSeleccionBodega(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarSelecciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMarcarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnActualizarSelecciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    
    public void corroborarBodegasRestantes(){
        DefaultTableModel mt = (DefaultTableModel) tblBodegasActualizables.getModel();
        int cantidadBodegas = mt.getRowCount();
        
        if(!(cantidadBodegas > 0)){
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
    }
    
    private void tomarSeleccionBodega(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarSeleccionBodega
        corroborarBodegasRestantes();
        List<String> bodegasSeleccionadas = new ArrayList<>();
        DefaultTableModel mt = (DefaultTableModel) tblBodegasActualizables.getModel();
        int cantidadBodegas = mt.getRowCount();
        boolean haySeleccion = false;
        
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
            tm.setValueAt(marcadosTodos, i, 0);
        }
        
        marcadosTodos = !marcadosTodos;
    }//GEN-LAST:event_btnMarcarTodotomarSeleccionBodega

    private void habilitarPantalla(){
        this.setLocationRelativeTo(null);
        //pantallaCarga.dispose();
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
    
    public void mostrarResumenNotificaciones(List<HashMap<String,Object>> resumenNotificaciones){
        this.setPantallaNotificacionPush(new PantallaNotificacionPush());
        pantallaNotificacionPush.setPantallaImportarBodega(this);
        pantallaNotificacionPush.setLocationRelativeTo(null);
        pantallaNotificacionPush.mostrarResumenNotificaciones(resumenNotificaciones);
    }

    //Seteo de pantallas para navegar entre "Resumen", "Notificaciones" e "Importaciones"
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

}
