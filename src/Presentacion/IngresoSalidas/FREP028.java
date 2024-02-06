package Presentacion.IngresoSalidas;

import static Entidades.Otros.Constante.ING_IMPORTACION;
import static Entidades.Otros.Constante.ING_REGULARIZACION;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;

/**
 *
 * @author CRS
 */
public class FREP028 extends javax.swing.JFrame {

    public FREP028() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIngSal = new javax.swing.JPanel();
        bt_compraLocal = new javax.swing.JButton();
        bt_ingresoImportacion = new javax.swing.JButton();
        bt_salidaVarias = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bt_ingresoVarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_compraLocal.setText("Ingreso por compra Local");
        bt_compraLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_compraLocalActionPerformed(evt);
            }
        });

        bt_ingresoImportacion.setText("Ingresos por Importación");
        bt_ingresoImportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ingresoImportacionActionPerformed(evt);
            }
        });

        bt_salidaVarias.setText("Salidas por Ajustes");
        bt_salidaVarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salidaVariasActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU DE INGRESOS Y SALIDAS");

        bt_ingresoVarios.setText("Ingresos por Ajustes");
        bt_ingresoVarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ingresoVariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIngSalLayout = new javax.swing.GroupLayout(panelIngSal);
        panelIngSal.setLayout(panelIngSalLayout);
        panelIngSalLayout.setHorizontalGroup(
            panelIngSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngSalLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(panelIngSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_compraLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_ingresoImportacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_salidaVarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_ingresoVarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(149, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelIngSalLayout.setVerticalGroup(
            panelIngSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIngSalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_compraLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_ingresoVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_salidaVarias, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_ingresoImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelIngSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIngSal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_compraLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_compraLocalActionPerformed
        IU_SeleccionProveedor isp = new IU_SeleccionProveedor();
        dispose();
    }//GEN-LAST:event_bt_compraLocalActionPerformed

    private void bt_ingresoImportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ingresoImportacionActionPerformed
        IU_IngresosSalidasV iv = new IU_IngresosSalidasV(ING_IMPORTACION);
        iv.setTipoOperacion(ING_IMPORTACION); 
        dispose();
    }//GEN-LAST:event_bt_ingresoImportacionActionPerformed

    private void bt_salidaVariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salidaVariasActionPerformed
        IU_IngresosSalidasV iv = new IU_IngresosSalidasV(SAL_REGULARIZACION);
        iv.setTipoOperacion(SAL_REGULARIZACION); //salidas
        dispose();
    }//GEN-LAST:event_bt_salidaVariasActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_ingresoVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ingresoVariosActionPerformed
        IU_IngresosSalidasV iv = new IU_IngresosSalidasV(ING_REGULARIZACION);
        iv.setTipoOperacion(ING_REGULARIZACION); 
        dispose();        
    }//GEN-LAST:event_bt_ingresoVariosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_cancelar;
    public javax.swing.JButton bt_compraLocal;
    public javax.swing.JButton bt_ingresoImportacion;
    public javax.swing.JButton bt_ingresoVarios;
    public javax.swing.JButton bt_salidaVarias;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel panelIngSal;
    // End of variables declaration//GEN-END:variables
}
