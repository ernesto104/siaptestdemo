package Presentacion.PlanillaLetras;

import Entidades.Cabeces;
import Entidades.Usuarios;
import Servicios.TipoMensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ledis Rivera Changra
 */
public class FREP080 extends javax.swing.JFrame {
    TipoMensaje tm ;
    Usuarios usuario;
    String rol;
    
    public FREP080(Usuarios usuario, String rol) {
        initComponents();
        seteaFechas();
        tm = new TipoMensaje();
        this.usuario = usuario;
        this.rol = rol;
    }

    private void seteaFechas() {
        Date ahora = new Date();
        dc_FechaDesde.setDate(ahora);
        dc_FechaHasta.setDate(ahora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPlanillaLet = new javax.swing.JPanel();
        btn_Continuar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dc_FechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dc_FechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btn_Continuar.setText("Continuar");
        btn_Continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ContinuarActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHAS"));

        jLabel4.setText("Desde");

        jLabel5.setText("Hasta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dc_FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dc_FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dc_FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(dc_FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLANILLA LETRAS PARA BANCO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>Favor indicar el rango de <b>Fecha de Emisión</b></html>");

        javax.swing.GroupLayout panelPlanillaLetLayout = new javax.swing.GroupLayout(panelPlanillaLet);
        panelPlanillaLet.setLayout(panelPlanillaLetLayout);
        panelPlanillaLetLayout.setHorizontalGroup(
            panelPlanillaLetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3)
            .addGroup(panelPlanillaLetLayout.createSequentialGroup()
                .addGroup(panelPlanillaLetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlanillaLetLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPlanillaLetLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btn_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPlanillaLetLayout.setVerticalGroup(
            panelPlanillaLetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPlanillaLetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panelPlanillaLetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPlanillaLet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPlanillaLet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void btn_ContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ContinuarActionPerformed
        Date desde = dc_FechaDesde.getDate();
        Date hasta = dc_FechaHasta.getDate();
        List<Cabeces> lista = null;
        String validacion = validar();
        
        if ( validacion.equals(tm.VALIDO) ) {
            IU_PlanillaLetras uipl = new IU_PlanillaLetras(usuario, rol, desde, hasta);
            dispose();
            
        } else {
            tm.Error(validacion);
        }
    }//GEN-LAST:event_btn_ContinuarActionPerformed
    
    private String validar(){
        String error = "ERROR";
        if ( dc_FechaDesde.getDate() == null || dc_FechaHasta.getDate() == null ) {
            error += "\n- SELECCIONE FECHA DE INICIAL Y FECHA FINAL";
            
        } else {
            if ( dc_FechaDesde.getDate().compareTo(dc_FechaHasta.getDate()) > 0 ) {
                error += "\n-LA FECHA INICIAL DEBE SER MENOR O IGUAL QUE LA FINAL";
            }
        }
        if ( error.equals("ERROR") )
            return tm.VALIDO;
        else 
            return error;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonSalir;
    public javax.swing.JButton btn_Continuar;
    public com.toedter.calendar.JDateChooser dc_FechaDesde;
    public com.toedter.calendar.JDateChooser dc_FechaHasta;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel panelPlanillaLet;
    // End of variables declaration//GEN-END:variables
}