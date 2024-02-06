package Presentacion.Reportes;

import Servicios.Servicio_Excel;
import Servicios.Reportes.Servicio_SalesReport;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ledis Rivera
 */
public class UI_FlujoComparativo extends javax.swing.JFrame {

    private DefaultTableModel modeloIzq;
    private DefaultTableModel modeloDer;
    Servicio_SalesReport servicioSalesReport;
    private Servicio_Excel servicio_Excel;
    private int anio;

    public UI_FlujoComparativo(String anio) {
        initComponents(); 
        setLocationRelativeTo(null);
        setVisible(true);
        
        this.anio = Integer.parseInt(anio);
        modeloIzq = (DefaultTableModel) tblReporteIzq.getModel();
        ((JLabel) tblReporteIzq.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        modeloDer = (DefaultTableModel) tblReporteDer.getModel();
        ((JLabel) tblReporteDer.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        servicioSalesReport = new Servicio_SalesReport(tblReporteIzq, tblReporteDer, this.anio);
        servicio_Excel = new Servicio_Excel(tblReporteIzq, tblReporteDer,this);
        
        lblAnyoAnt.setText("" + (this.anio-1));
        lblAnyoSel.setText("" + anio);
        
        llenarReporte();
        alinearColumnaDerecha();
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblReporteIzq.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tblReporteIzq.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tblReporteDer.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tblReporteDer.getColumnModel().getColumn(1).setCellRenderer(tcr);
    }
    
    private void llenarReporte() {
        servicioSalesReport.listarVentas(modeloIzq, modeloDer);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelClientes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteIzq = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReporteDer = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        lblAnyoAnt = new javax.swing.JLabel();
        lblAnyoSel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FLUJO COMPARATIVO DE VENTAS");

        tblReporteIzq.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblReporteIzq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mes", "TOTAL SOLES", "TOTAL DOLARES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReporteIzq);
        if (tblReporteIzq.getColumnModel().getColumnCount() > 0) {
            tblReporteIzq.getColumnModel().getColumn(0).setMinWidth(100);
            tblReporteIzq.getColumnModel().getColumn(0).setMaxWidth(100);
            tblReporteIzq.getColumnModel().getColumn(1).setMinWidth(125);
            tblReporteIzq.getColumnModel().getColumn(1).setMaxWidth(125);
            tblReporteIzq.getColumnModel().getColumn(2).setMinWidth(125);
            tblReporteIzq.getColumnModel().getColumn(2).setMaxWidth(125);
        }

        tblReporteDer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblReporteDer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOTAL SOLES", "TOTAL DOLARES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblReporteDer);
        if (tblReporteDer.getColumnModel().getColumnCount() > 0) {
            tblReporteDer.getColumnModel().getColumn(0).setMinWidth(125);
            tblReporteDer.getColumnModel().getColumn(0).setMaxWidth(125);
            tblReporteDer.getColumnModel().getColumn(1).setMinWidth(125);
            tblReporteDer.getColumnModel().getColumn(1).setMaxWidth(125);
        }

        jButton1.setText("Exportar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        lblAnyoAnt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAnyoAnt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblAnyoSel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAnyoSel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelClientesLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(lblAnyoAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAnyoSel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAnyoAnt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAnyoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        servicio_Excel.Exportar_Excel_2_Tables(anio);
    }//GEN-LAST:event_jButton1ActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnsalir;
    public javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblAnyoAnt;
    public javax.swing.JLabel lblAnyoSel;
    public javax.swing.JPanel panelClientes;
    public javax.swing.JTable tblReporteDer;
    public javax.swing.JTable tblReporteIzq;
    // End of variables declaration//GEN-END:variables
}
