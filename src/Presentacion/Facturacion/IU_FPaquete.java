
package Presentacion.Facturacion;

import Entidades.Paquetes;
import Entidades.PaquetesRepuestos;
import Entidades.Usuarios;
import Servicios.Servicio_Maestros;
import Servicios.Servicio_Paquetes;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

public class IU_FPaquete extends javax.swing.JFrame {
    Servicios.Servicio_Paquetes sp ;
    Servicios.Servicio_Maestros sm;
    ArrayList<PaquetesRepuestos> rep;
    DefaultTableModel t;
    Usuarios u;

    public IU_FPaquete(Usuarios u) {
        sm= new Servicio_Maestros(null);
        rep=new ArrayList<>();
        sp=new Servicio_Paquetes();
        initComponents();
        t=(DefaultTableModel)tb_paq.getModel();
        ListarPaquetes();
        this.u=u;
    }
    
    private void ListarPaquetes(){
        Iterator it = sp.GetList().iterator();
        while(it.hasNext()){
            Paquetes p = (Paquetes)it.next();
            Object [] row = new Object[2];
            row[0]=p.getIdpaquete();
            row[1]=p.getDescripcion();
            t.addRow(row);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_paq = new javax.swing.JTable();
        bt_agregar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_paq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_paq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tb_paqKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tb_paq);
        tb_paq.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb_paq.getColumnModel().getColumn(1).setPreferredWidth(300);

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        int filat = tb_paq.getSelectedRow();
        if(filat>=0){
            rep = (ArrayList<PaquetesRepuestos>) sp.obtenerRepuestos_Paq(Integer.parseInt(String.valueOf(t.getValueAt(filat, 0))));        
            IU_Facturacion f= new IU_Facturacion(rep);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            System.out.println(u.getNombre());
            f.setUsuario(u);
            dispose();
        }
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void tb_paqKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_paqKeyTyped
        if(tb_paq.getSelectedRow()>=0){
            if(evt.getKeyChar() == 10){
                bt_agregar.doClick();
            }
        }
    }//GEN-LAST:event_tb_paqKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_paq;
    // End of variables declaration//GEN-END:variables


}
