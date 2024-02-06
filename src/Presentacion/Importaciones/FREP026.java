package Presentacion.Importaciones;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Servicios.Importaciones.Servicio_CabSugerido;

public class FREP026 extends javax.swing.JFrame {
    static IU_Valorizacion iuvaloriza;
    static Servicio_CabSugerido scs;
    
    public FREP026() {
        initComponents();
        scs = new Servicio_CabSugerido();
        int countPedOf = scs.cuentaOrdenesCompra();
//        System.out.println("Cantidad ordenes de compra: " + countPedOf);
        
        if ( countPedOf != 0 ) {
            scs.listarOrdenesPedidos(cboPedOficial);
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "No existen pedidos oficiales en la BD","ADVERTENCIA",JOptionPane.INFORMATION_MESSAGE);
            this.panelPedidoAValorizar.setVisible(false);
        }
    }
    
    public FREP026(IU_Valorizacion iuva) {
        initComponents();
        iuvaloriza = iuva;
        scs = new Servicio_CabSugerido();
        int numPedOf = scs.cuentaOrdenesCompra();
        
        if ( numPedOf != 0 ) {
            scs.listarOrdenesPedidos(cboPedOficial);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPedidoAValorizar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        cboPedOficial = new javax.swing.JComboBox();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ELEGIR PEDIDO OFICIAL");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("N° PEDIDO OFICIAL:");

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        cboPedOficial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboPedOficialKeyPressed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPedidoAValorizarLayout = new javax.swing.GroupLayout(panelPedidoAValorizar);
        panelPedidoAValorizar.setLayout(panelPedidoAValorizarLayout);
        panelPedidoAValorizarLayout.setHorizontalGroup(
            panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoAValorizarLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPedOficial, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelPedidoAValorizarLayout.setVerticalGroup(
            panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPedidoAValorizarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPedOficial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelPedidoAValorizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPedidoAValorizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPedidoAValorizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.panelPedidoAValorizar.setVisible(false);
    }//GEN-LAST:event_formWindowClosing
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        mostrarPedidoOficial();
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void cboPedOficialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboPedOficialKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            mostrarPedidoOficial();
        }
    }//GEN-LAST:event_cboPedOficialKeyPressed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.panelPedidoAValorizar.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed
    private void mostrarPedidoOficial(){
        if ( cboPedOficial.getSelectedIndex() != -1 ) {
            iuvaloriza = new IU_Valorizacion(this);
            iuvaloriza.setVisible(true);
            this.dispose();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    public javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox cboPedOficial;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel panelPedidoAValorizar;
    // End of variables declaration//GEN-END:variables
}