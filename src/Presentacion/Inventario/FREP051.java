
package Presentacion.Inventario;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FREP051 extends javax.swing.JFrame {

    Reportes reporte;
    String orden;
    String valorizacion;
    String date;
    
    public FREP051() {
        initComponents();
        this.setLocationRelativeTo(null);
        Date d = new Date();
        txtFechaInvent.setDate(d);
        reporte = new Reportes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        panelDiferenciasInvent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        combBoxPrecLista = new javax.swing.JRadioButton();
        combBoxCostPromed = new javax.swing.JRadioButton();
        combBoxUltimCosto = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        combBoxCodfabric = new javax.swing.JRadioButton();
        combBoxNroParte = new javax.swing.JRadioButton();
        combBoxDescrip = new javax.swing.JRadioButton();
        combBoxUbicAlm = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        combBoxTodo = new javax.swing.JRadioButton();
        combBoxSoloDiferen = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaInvent = new com.toedter.calendar.JDateChooser();
        btnGenerarReport = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DIFERENCIAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Valorización"));

        buttonGroup2.add(combBoxPrecLista);
        combBoxPrecLista.setSelected(true);
        combBoxPrecLista.setText("Precio Lista");

        buttonGroup2.add(combBoxCostPromed);
        combBoxCostPromed.setText("Costo Promedio");

        buttonGroup2.add(combBoxUltimCosto);
        combBoxUltimCosto.setText("Ultimo Costo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combBoxUltimCosto)
                    .addComponent(combBoxPrecLista)
                    .addComponent(combBoxCostPromed))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(combBoxPrecLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxCostPromed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxUltimCosto)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Orden de Presentación del Inventario"));

        buttonGroup1.add(combBoxCodfabric);
        combBoxCodfabric.setSelected(true);
        combBoxCodfabric.setText("Código de Fabricación");

        buttonGroup1.add(combBoxNroParte);
        combBoxNroParte.setText("Nro. Parte");

        buttonGroup1.add(combBoxDescrip);
        combBoxDescrip.setText("Descripción");

        buttonGroup1.add(combBoxUbicAlm);
        combBoxUbicAlm.setText("Ubicación en el Almacén");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combBoxUbicAlm)
                    .addComponent(combBoxDescrip)
                    .addComponent(combBoxCodfabric)
                    .addComponent(combBoxNroParte))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(combBoxCodfabric)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxNroParte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxDescrip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxUbicAlm)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Impresión"));

        buttonGroup3.add(combBoxTodo);
        combBoxTodo.setSelected(true);
        combBoxTodo.setText("Todo");

        buttonGroup3.add(combBoxSoloDiferen);
        combBoxSoloDiferen.setText("Solo Diferencia");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combBoxTodo)
                    .addComponent(combBoxSoloDiferen))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(combBoxTodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combBoxSoloDiferen)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Inventario"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setText("Inventario al:");

        txtFechaInvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaInventKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(txtFechaInvent, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaInvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        btnGenerarReport.setText("Generar Reporte");
        btnGenerarReport.setNextFocusableComponent(btnSalir);
        btnGenerarReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReportActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(btnGenerarReport);

        javax.swing.GroupLayout panelDiferenciasInventLayout = new javax.swing.GroupLayout(panelDiferenciasInvent);
        panelDiferenciasInvent.setLayout(panelDiferenciasInventLayout);
        panelDiferenciasInventLayout.setHorizontalGroup(
            panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
            .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDiferenciasInventLayout.createSequentialGroup()
                        .addComponent(btnGenerarReport, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 171, Short.MAX_VALUE))
            .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
        );
        panelDiferenciasInventLayout.setVerticalGroup(
            panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarReport, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(panelDiferenciasInventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDiferenciasInventLayout.createSequentialGroup()
                    .addGap(108, 108, 108)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(226, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDiferenciasInvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDiferenciasInvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReportActionPerformed
        
        String url = "src/Presentacion/Inventario/DiferenciasInventario.jrxml";
        int todo = 0;
        
        if(combBoxCodfabric.isSelected()){
            orden = "Código de Fabricación";
        }
        if(combBoxNroParte.isSelected()){
            orden = "Nro. Parte";
        }
        if(combBoxDescrip.isSelected()){
            orden = "Descripción";
        }
        if(combBoxUbicAlm.isSelected()){
            orden = "Ubicación en el Almacén";
        }
        if(combBoxPrecLista.isSelected()){
            valorizacion = "Precio Lista";
        }
        if(combBoxCostPromed.isSelected()){
            valorizacion = "Costo Promedio";
        }
        if(combBoxUltimCosto.isSelected()){
            valorizacion = "Ultimo costo";
        }
        if(combBoxTodo.isSelected()){
            todo = 1;
        }
        
        date =  txtFechaInvent.getDate().getDate()+"/"+
                (txtFechaInvent.getDate().getMonth()+1)+"/"+
                (txtFechaInvent.getDate().getYear()+1900)+"";
        try {
            reporte.mostrarReporteParametros(url, orden, valorizacion, date, todo);
        } catch (SQLException ex) {
            Logger.getLogger(FREP051.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarReportActionPerformed

    private void txtFechaInventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaInventKeyTyped
        int cod = (int) evt.getKeyChar();
        if (cod == 10) {
            btnGenerarReport.doClick();
        }
    }//GEN-LAST:event_txtFechaInventKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReport;
    public javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton combBoxCodfabric;
    private javax.swing.JRadioButton combBoxCostPromed;
    private javax.swing.JRadioButton combBoxDescrip;
    private javax.swing.JRadioButton combBoxNroParte;
    private javax.swing.JRadioButton combBoxPrecLista;
    private javax.swing.JRadioButton combBoxSoloDiferen;
    private javax.swing.JRadioButton combBoxTodo;
    private javax.swing.JRadioButton combBoxUbicAlm;
    private javax.swing.JRadioButton combBoxUltimCosto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JPanel panelDiferenciasInvent;
    private com.toedter.calendar.JDateChooser txtFechaInvent;
    // End of variables declaration//GEN-END:variables
}
