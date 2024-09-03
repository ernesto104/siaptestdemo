package Presentacion.PlanillaElectronica;


import Entidades.Cabeces;
import Servicios.TipoMensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maverick225
 * @author modified : Ledis Rivera Ch.
 */
public class FREP033_ extends javax.swing.JFrame {
    TipoMensaje tm ;
    
    public FREP033_() {
        initComponents();
        seteaFechas();
        tm = new TipoMensaje();
    }

    private void seteaFechas() {
        Date ahora = new Date();
        dc_FechaDesde.setDate(ahora);
        dc_FechaHasta.setDate(ahora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPlanillaElectronica = new javax.swing.JPanel();
        btn_Continuar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dc_FechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dc_FechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btn_Continuar.setText("Continuar");
        btn_Continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ContinuarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
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
        jLabel1.setText("PLANILLA ELECTRONICA");

        javax.swing.GroupLayout panelPlanillaElectronicaLayout = new javax.swing.GroupLayout(panelPlanillaElectronica);
        panelPlanillaElectronica.setLayout(panelPlanillaElectronicaLayout);
        panelPlanillaElectronicaLayout.setHorizontalGroup(
            panelPlanillaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlanillaElectronicaLayout.createSequentialGroup()
                .addGroup(panelPlanillaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlanillaElectronicaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPlanillaElectronicaLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPlanillaElectronicaLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btn_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        panelPlanillaElectronicaLayout.setVerticalGroup(
            panelPlanillaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPlanillaElectronicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(panelPlanillaElectronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPlanillaElectronica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPlanillaElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_ContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ContinuarActionPerformed
        Date desde = dc_FechaDesde.getDate();
        Date hasta = dc_FechaHasta.getDate();
        List<Cabeces> lista = null;
        String validacion = validar();
        
        if ( validacion.equals(tm.VALIDO) ) {
            /*0 VENTAS*/
            UI_GestionarPlanillas ui = new UI_GestionarPlanillas(desde, hasta,5);
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
    public javax.swing.JButton btn_Continuar;
    public javax.swing.JButton btn_Salir;
    public com.toedter.calendar.JDateChooser dc_FechaDesde;
    public com.toedter.calendar.JDateChooser dc_FechaHasta;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel panelPlanillaElectronica;
    // End of variables declaration//GEN-END:variables
}