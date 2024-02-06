package Presentacion.Importaciones;

import javax.swing.JOptionPane;
import Servicios.Importaciones.Servicio_CabSugerido;

public class FREP025 extends javax.swing.JFrame {
    Servicio_CabSugerido scs;
    
    public FREP025() {
        initComponents();
        scs = new Servicio_CabSugerido();
        btnPrintSugerido.setVisible(true);
//      btnPrintSugerido.setVisible(false);        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrintImportaciones = new javax.swing.JPanel();
        btnPrintSugerido = new javax.swing.JButton();
        btnPrintPedidoOf = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("IMPRESIÓN DE IMPORTACIONES");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnPrintSugerido.setText("Imprimir Sugerido");
        btnPrintSugerido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintSugeridoActionPerformed(evt);
            }
        });

        btnPrintPedidoOf.setText("Imprimir Pedido Oficial");
        btnPrintPedidoOf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPedidoOfActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPRESIÓN DE PEDIDOS");

        javax.swing.GroupLayout panelPrintImportacionesLayout = new javax.swing.GroupLayout(panelPrintImportaciones);
        panelPrintImportaciones.setLayout(panelPrintImportacionesLayout);
        panelPrintImportacionesLayout.setHorizontalGroup(
            panelPrintImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrintImportacionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPrintImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrintSugerido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrintPedidoOf, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(133, 133, 133))
            .addGroup(panelPrintImportacionesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelPrintImportacionesLayout.setVerticalGroup(
            panelPrintImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrintImportacionesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnPrintSugerido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrintPedidoOf, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrintImportaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrintImportaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.panelPrintImportaciones.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnPrintSugeridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintSugeridoActionPerformed

        System.out.println("Hay o no sugeridos : " + scs.contarSugeridos());

        if ( scs.contarSugeridos() != 0 ) {
            IU_MenuImpSugerido menuSugerido = new IU_MenuImpSugerido(this);
            menuSugerido.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "No existen sugeridos registrados en la BD,"
                                  + "\nPor favor, primero cree un sugerido","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPrintSugeridoActionPerformed
    private void btnPrintPedidoOfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPedidoOfActionPerformed
        if ( scs.contarSugeridosYOrdenesCompra() != 0 ) {
            IU_ElegirSugerido dialogoImp = new IU_ElegirSugerido(this, 5);
            dialogoImp.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "No existen sugeridos registrados en la BD,"
                                  + "\nPor favor, primero cree un sugerido","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPrintPedidoOfActionPerformed
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.panelPrintImportaciones.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnPrintPedidoOf;
    public javax.swing.JButton btnPrintSugerido;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel panelPrintImportaciones;
    // End of variables declaration//GEN-END:variables
}