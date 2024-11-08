package presentacion;

import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logica_De_Negocio.controlador.GestorImportadorBodega;

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

        btnActualizarSelecciones.setText("Actualizar Selecciones");
        btnActualizarSelecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarSeleccionBodega(evt);
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
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnActualizarSelecciones)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnActualizarSelecciones)
                .addContainerGap(37, Short.MAX_VALUE))
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
        
        for(int i = cantidadBodegas - 1; i >= 0; i--){
            Boolean esSeleccionada = (Boolean) mt.getValueAt(i, 0);
            if(esSeleccionada){
                String nombreBodega = (String) mt.getValueAt(i, 1);
                bodegasSeleccionadas.add(nombreBodega);
                mt.removeRow(i);
            }
        }
        
        gestorImportadorBodega.tomarSeleccionBodega(bodegasSeleccionadas);
    }//GEN-LAST:event_tomarSeleccionBodega

    private void habilitarPantalla(){
        this.setVisible(true);
    }
    
    public void mostrarBodegaParaActualizar(Set<String> nombresBodegasSet){
        DefaultTableModel mt = (DefaultTableModel) tblBodegasActualizables.getModel();
        for(String bodegaNombre:nombresBodegasSet){
            mt.addRow(new Object[]{false, bodegaNombre});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarSelecciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblBodegasActualizables;
    // End of variables declaration//GEN-END:variables
}
