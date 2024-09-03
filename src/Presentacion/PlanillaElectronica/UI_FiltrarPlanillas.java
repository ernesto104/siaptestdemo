package Presentacion.PlanillaElectronica;

import Presentacion.EmisionPlanillas.*;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;

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

        rb_Facturas.setText("Facturas");

        rb_Boletas.setText("Boletas");

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
                .addGap(45, 45, 45)
                .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rb_NotasDebito)
                    .addComponent(rb_NotasCredito)
                    .addComponent(rb_Boletas)
                    .addComponent(rb_Clientes)
                    .addComponent(rb_Facturas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(rb_Clientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_Facturas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_Boletas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_NotasCredito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_NotasDebito)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        }
    }
}
