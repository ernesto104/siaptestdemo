package Presentacion.Facturacion;

import static Entidades.Otros.Constante.FACTURA_NORMAL;
import Entidades.Roles;
import Entidades.Usuarios;

/**
 *
 * @author CRS
 */
public class FREP019 extends javax.swing.JFrame {
    
    Usuarios usuario;
    String valorVenta;
    
    public FREP019(Usuarios usuario, String valorVenta) {
        initComponents();
        this.usuario = usuario;
        this.valorVenta = valorVenta;
        bt_web.setVisible(false);
        //bt_guiaR.setVisible(false);
//        System.out.println("Roles : " + roles);
        Roles Querol = usuario.getRoles();
        System.out.println("Al comienzo Rol : " + Querol);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFact = new javax.swing.JPanel();
        bt_Nueva = new javax.swing.JButton();
        bt_xPaq = new javax.swing.JButton();
        bt_xPro = new javax.swing.JButton();
        bt_sk = new javax.swing.JButton();
        bt_web = new javax.swing.JButton();
        bt_guiaR = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bt_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bt_Nueva.setText("Nueva Factura");
        bt_Nueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_NuevaActionPerformed(evt);
            }
        });

        bt_xPaq.setText("Por Paquete");
        bt_xPaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xPaqActionPerformed(evt);
            }
        });

        bt_xPro.setText("Por Proforma");
        bt_xPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xProActionPerformed(evt);
            }
        });

        bt_sk.setText("Canje de Salidas Varias");
        bt_sk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_skActionPerformed(evt);
            }
        });

        bt_web.setText("Pedido Web");
        bt_web.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_webActionPerformed(evt);
            }
        });

        bt_guiaR.setText("Guias de Remision");
        bt_guiaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guiaRActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENÚ DE FACTURACIÓN");

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFactLayout = new javax.swing.GroupLayout(panelFact);
        panelFact.setLayout(panelFactLayout);
        panelFactLayout.setHorizontalGroup(
            panelFactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFactLayout.createSequentialGroup()
                .addGroup(panelFactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFactLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(panelFactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_Nueva, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_xPaq, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_xPro, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_sk, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_web, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_guiaR, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelFactLayout.setVerticalGroup(
            panelFactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFactLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_Nueva, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(bt_xPaq, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(bt_xPro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(bt_sk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(bt_web, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_guiaR, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(panelFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guiaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guiaRActionPerformed
        IU_BuscarGR guia = new IU_BuscarGR(usuario);
        guia.setLocationRelativeTo(null);
        guia.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_guiaRActionPerformed

    private void bt_xPaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xPaqActionPerformed
        IU_FPaquete p = new IU_FPaquete(usuario);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_xPaqActionPerformed

    private void bt_NuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_NuevaActionPerformed
        IU_Facturacion f = new IU_Facturacion(valorVenta, FACTURA_NORMAL, null, null, usuario);
//        f.setUsuario(usuario);
        System.out.println("Usuario : " + usuario);
        
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_NuevaActionPerformed

    private void bt_xProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xProActionPerformed
        IU_FProforma iup = new IU_FProforma(usuario, valorVenta);
        iup.setLocationRelativeTo(null);
        iup.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_xProActionPerformed

    private void bt_webActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_webActionPerformed
        IU_PedidosWeb ipw = new IU_PedidosWeb(usuario);
        ipw.setLocationRelativeTo(null);
        ipw.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_webActionPerformed

    private void bt_skActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_skActionPerformed
        IU_FSalidasVarias iu = new IU_FSalidasVarias(usuario);
        iu.setLocationRelativeTo(null);
        iu.setVisible(true);
        dispose();
    }//GEN-LAST:event_bt_skActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_Nueva;
    public javax.swing.JButton bt_guiaR;
    public javax.swing.JButton bt_salir;
    public javax.swing.JButton bt_sk;
    public javax.swing.JButton bt_web;
    public javax.swing.JButton bt_xPaq;
    public javax.swing.JButton bt_xPro;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel panelFact;
    // End of variables declaration//GEN-END:variables
}
