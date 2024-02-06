package Presentacion.Importaciones;

import Entidades.Importaciones.BarraProgresoNewSug;
import javax.swing.JOptionPane;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Repuestos;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;

public class FREP024 extends javax.swing.JFrame {
    IU_Sugerido iuSugerido;
    IU_ActualizarSugerido iuActSugerido;
    BarraProgresoNewSug pane;
    
    public FREP024() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpMenuImportaciones = new javax.swing.JPanel();
        btnEliminarSug = new javax.swing.JButton();
        btnNuevoSug = new javax.swing.JButton();
        btnActualizarSug = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MENU DE IMPORTACIONES");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jpMenuImportaciones.setMinimumSize(new java.awt.Dimension(486, 308));

        btnEliminarSug.setText("Eliminar Pedido de Reposición");
        btnEliminarSug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSugActionPerformed(evt);
            }
        });

        btnNuevoSug.setText("Nuevo Pedido de Reposición");
        btnNuevoSug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoSugActionPerformed(evt);
            }
        });

        btnActualizarSug.setText("Actualizar Existente");
        btnActualizarSug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarSugActionPerformed(evt);
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
        jLabel1.setText("PEDIDOS DE IMPORTACIÓN");

        javax.swing.GroupLayout jpMenuImportacionesLayout = new javax.swing.GroupLayout(jpMenuImportaciones);
        jpMenuImportaciones.setLayout(jpMenuImportacionesLayout);
        jpMenuImportacionesLayout.setHorizontalGroup(
            jpMenuImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuImportacionesLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jpMenuImportacionesLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jpMenuImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnActualizarSug, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarSug, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoSug, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpMenuImportacionesLayout.setVerticalGroup(
            jpMenuImportacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMenuImportacionesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnNuevoSug, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizarSug, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarSug, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMenuImportaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpMenuImportaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.jpMenuImportaciones.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed
    
    public int getNumRegASugerido(){
        int numReg = 0;
        Servicio_Repuestos sr = new Servicio_Repuestos();
        List listaRep = sr.getRepuestosASugerido();
        
        if ( listaRep != null ) {
            numReg = listaRep.size();
        }
        return numReg;
    }
    private void btnNuevoSugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoSugActionPerformed
        JFrame frame = new JFrame( "NUEVO SUGERIDO EN PROGRESO" );
        pane = new BarraProgresoNewSug(frame, this);
        frame.setUndecorated(true);
        frame.getContentPane().add(pane,BorderLayout.CENTER );
        frame.setSize( 400,150 );
        frame.setLocationRelativeTo(null);
        frame.setVisible( true );
        frame.setDefaultCloseOperation(0);
    }//GEN-LAST:event_btnNuevoSugActionPerformed
    private void btnActualizarSugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarSugActionPerformed
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        int countSugeridosYPedidos = scs.contarSugeridosYOrdenesCompra();
        
        if ( countSugeridosYPedidos != 0 ) {
            IU_ElegirSugerido dialogo = new IU_ElegirSugerido(this,2);
            dialogo.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "No existen sugeridos ni pedidos de\nimportación en la Base de Datos","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarSugActionPerformed
    private void btnEliminarSugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSugActionPerformed
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        int countSugeridos = scs.contarSugeridos();
        
        if ( countSugeridos != 0 ) {
            IU_ElegirSugerido dialogo = new IU_ElegirSugerido(this,3);
            dialogo.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "No existen sugeridos de pedidos en la BD","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarSugActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarSug;
    public javax.swing.JButton btnEliminarSug;
    public javax.swing.JButton btnNuevoSug;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jpMenuImportaciones;
    // End of variables declaration//GEN-END:variables
}