package Presentacion.IngresoSalidas;

import Entidades.Clientes;
import Servicios.IngresoSalidas.Servicio_IngresoSalida;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class IU_SeleccionProveedor extends javax.swing.JFrame {
    Clientes seleccionado;
    Servicio_IngresoSalida servicio;
    boolean continuar;
    DefaultTableModel tabla ;

    public IU_SeleccionProveedor() {
        initComponents();
        seleccionado = null;
        servicio = new Servicio_IngresoSalida();
        tabla = (DefaultTableModel) tb_SelProv.getModel();
        ListarProveedores();
        setLocationRelativeTo(null);
        setVisible(true);
        setAnchoColumnas();
        continuar = false;
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_SelProv.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_SelProv.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_SelProv.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (15 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_SelProv = new javax.swing.JTable();
        bt_continuar = new javax.swing.JButton();
        bt_agregarProv = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_SelProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id ", "Nombres", "Direccion", "RUC", "Telefono", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_SelProv);

        bt_continuar.setText("Continuar");
        bt_continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_continuarActionPerformed(evt);
            }
        });

        bt_agregarProv.setText("Agregar Proveedor");
        bt_agregarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarProvActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECCION DE PROVEEDOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(bt_agregarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_agregarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_continuarActionPerformed
        int fila = tb_SelProv.getSelectedRow();
        if( fila >= 0 ) {
            seleccionado = servicio.GetCliente(Integer.parseInt(String.valueOf(tabla.getValueAt(fila,0))));
            if(seleccionado!=null){
                continuar=true;
                IU_IngresoCompraLocal icl = new IU_IngresoCompraLocal(seleccionado);            
                dispose();
            } else{
                JOptionPane.showMessageDialog(null, "Primero Seleccione un Proveedor","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Proveedor","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_continuarActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_agregarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarProvActionPerformed
        final IU_NuevoProv np = new IU_NuevoProv();

        np.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                if(np.prov!=null)
                    ListarProveedores();
            }       
        });
        this.setVisible(false);
    }//GEN-LAST:event_bt_agregarProvActionPerformed
    
    private void ListarProveedores(){
        borrarTabla();
        ArrayList<Clientes> prov = (ArrayList<Clientes>) servicio.ObtenerProveedor();
        for(Clientes proveedor : prov){
            Object [] row = {proveedor.getIdcliente(),
                             proveedor.getNombre(),
                             proveedor.getDireccion(),
                             proveedor.getRuc(),
                             proveedor.getTelefonofijo(),
                             proveedor.getEmail()};
        tabla.addRow(row);
        }
    }
    private void borrarTabla(){
        int numr = tabla.getRowCount();
        for(int i=0;i<numr;i++)
            tabla.removeRow(0);
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregarProv;
    private javax.swing.JButton bt_continuar;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_SelProv;
    // End of variables declaration//GEN-END:variables
}
