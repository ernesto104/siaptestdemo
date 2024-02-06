package Presentacion.EmisionPlanillas;

import Entidades.Vendedores;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;
import Servicios.Servicio_Vendedor;
import java.util.Iterator;

/**
 *
 * @author maverick225
 */
public class UI_FiltrarPlanillas extends javax.swing.JFrame {

    private Servicio_GestionarPlanillas gestionar;
    private int seleccion;
    public boolean aceptar;

    public UI_FiltrarPlanillas(Servicio_GestionarPlanillas ui) {
        this();
        gestionar = ui;
        aceptar = false;
        HabilitarBotones();
    }

    public UI_FiltrarPlanillas() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
//        ListarVendedores();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rb_Clientes = new javax.swing.JRadioButton();
        rb_Facturas = new javax.swing.JRadioButton();
        rb_Boletas = new javax.swing.JRadioButton();
        rb_NotasCredito = new javax.swing.JRadioButton();
        rb_NotasDebito = new javax.swing.JRadioButton();
        btn_Aceptar = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        rb_Clientes.setText("Mostrar Todo");
        rb_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ClientesActionPerformed(evt);
            }
        });

        rb_Facturas.setText("Facturas");

        rb_Boletas.setText("Boletas");
        rb_Boletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_BoletasActionPerformed(evt);
            }
        });

        rb_NotasCredito.setText("Notas de crédito");

        rb_NotasDebito.setText("Notas de débito");

        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });

        btn_Cancelar.setText("Salir");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_NotasDebito)
                            .addComponent(rb_NotasCredito)
                            .addComponent(rb_Boletas)
                            .addComponent(rb_Facturas)
                            .addComponent(rb_Clientes)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(rb_Clientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_Facturas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_Boletas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_NotasCredito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_NotasDebito)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar = true;
        dispose();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void rb_BoletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_BoletasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_BoletasActionPerformed

    private void rb_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_ClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JRadioButton rb_Boletas;
    public javax.swing.JRadioButton rb_Clientes;
    public javax.swing.JRadioButton rb_Facturas;
    public javax.swing.JRadioButton rb_NotasCredito;
    public javax.swing.JRadioButton rb_NotasDebito;
    // End of variables declaration//GEN-END:variables

    private void HabilitarBotones() {
        if (gestionar.listaCabecSalvar) {
            rb_Facturas.setEnabled(false);
            rb_Boletas.setEnabled(false);
            rb_NotasCredito.setEnabled(false);
            rb_NotasDebito.setEnabled(false);
//            rb_Vendedor.setEnabled(false);
        }
    }
    
//    private void ListarVendedores(){
//        Iterator it = new Servicio_Vendedor(null).Listar_vendedores().iterator();
//        while(it.hasNext()){
//            cb_vendedor.addItem(((Vendedores)it.next()).getNombre());
//        }        
//    }
//    public String Sel_Vendedor(){
//        return String.valueOf(cb_vendedor.getSelectedItem());
//    }    

    
}
