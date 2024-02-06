package Presentacion.historialMov;

/**
 *
 * @author CRS
 */
public class FREP035 extends javax.swing.JFrame {

    public FREP035() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHistorialMov = new javax.swing.JPanel();
        bt_VistaTodo = new javax.swing.JButton();
        bt_VistaFecha = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_VistaTodo.setText("Visualizar Todo");
        bt_VistaTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_VistaTodoActionPerformed(evt);
            }
        });

        bt_VistaFecha.setText("Visualizar Por Fecha");
        bt_VistaFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_VistaFechaActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HISTORIAL DE MOVIMIENTO DE REPUESTO");

        javax.swing.GroupLayout panelHistorialMovLayout = new javax.swing.GroupLayout(panelHistorialMov);
        panelHistorialMov.setLayout(panelHistorialMovLayout);
        panelHistorialMovLayout.setHorizontalGroup(
            panelHistorialMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistorialMovLayout.createSequentialGroup()
                .addGroup(panelHistorialMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHistorialMovLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8))
                    .addGroup(panelHistorialMovLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(panelHistorialMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_VistaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_VistaTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelHistorialMovLayout.setVerticalGroup(
            panelHistorialMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHistorialMovLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(bt_VistaTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_VistaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHistorialMov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHistorialMov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_VistaFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_VistaFechaActionPerformed
        IU_FiltrarFecha fecha = new IU_FiltrarFecha();
        fecha.setLocationRelativeTo(null);
        fecha.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_VistaFechaActionPerformed

    private void bt_VistaTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_VistaTodoActionPerformed
        IU_SeleccionRepuesto selRep = new IU_SeleccionRepuesto();
        selRep.setLocationRelativeTo(null);
        selRep.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_VistaTodoActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_VistaFecha;
    public javax.swing.JButton bt_VistaTodo;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JPanel panelHistorialMov;
    // End of variables declaration//GEN-END:variables
}
