package Presentacion.InventarioValorizado;

import Servicios.CuentasxCobrar.Sv_Impresion;
import Servicios.Servicio_Control;
import Servicios.Servicio_Equipos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_Resumen extends javax.swing.JFrame {

    DefaultTableModel modelo;
    DefaultTableModel tab;
    Servicio_Equipos sl;
    
    String tipoValorizacion;
    String totalNeto;

    public IU_Resumen(DefaultTableModel modelo,String t0,String t1,String t2,String t3,String t4,
                      String tipoValorizacion, String totalNeto) {
        initComponents();
        setLocationRelativeTo(null);
        tab = (DefaultTableModel) tablaLineas.getModel();
        
        sl = new Servicio_Equipos(null);
        this.modelo = new DefaultTableModel();
        this.modelo = modelo;
        texto0.setText(t0);
        texto1.setText(t1);
        texto2.setText(t2);
        texto3.setText(t3);
        texto4.setText(t4);
        
        this.tipoValorizacion = tipoValorizacion;
        this.totalNeto = totalNeto;
        txtsumatotal.setText(totalNeto);
        
        listar_Resumen();
        alinearDerecha();
    }
    
    private void alinearDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaLineas.getColumnModel().getColumn(2).setCellRenderer(tcr);
    }

    public void listar_Resumen() { 
        
        Object[] fi = {"x","xx","xx","x","x","x","x","x"};
        modelo.addRow(fi);
        int n = modelo.getRowCount();        
        BigDecimal total = new BigDecimal(0);
//        BigDecimal a = new BigDecimal(2);
        int colIdLinea = 7;
        int colTotal = 6;
        
        for ( int i = 0; i < n; i++ ) {
            if ( i != n-1 ){
//                if ( modelo.getValueAt(i, 0).equals(modelo.getValueAt(i + 1, 0)) ) {
                if ( modelo.getValueAt(i, colIdLinea).equals(modelo.getValueAt(i + 1, colIdLinea)) ) {
                    total = total.add(BigDecimal.valueOf(Double.parseDouble(modelo.getValueAt(i, colTotal).toString())));
                    
                } else {
                    total = total.add(BigDecimal.valueOf(Double.parseDouble(modelo.getValueAt(i, colTotal).toString())));
//                    String desc = sl.getEquipos_por_codigo(Integer.parseInt(modelo.getValueAt(i, 0).toString())).getDescripcion();
                    String desc = sl.getEquipos_por_codigo(Integer.parseInt(modelo.getValueAt(i, colIdLinea).toString())).getDescripcion();
//                    Object[] fila = {modelo.getValueAt(i, 0), desc, total};
                    Object[] fila = {modelo.getValueAt(i, colIdLinea), desc, total};
                    total = new BigDecimal(0);
                    tab.addRow(fila);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLineas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnImprimir = new javax.swing.JButton();
        texto0 = new javax.swing.JLabel();
        texto1 = new javax.swing.JLabel();
        texto2 = new javax.swing.JLabel();
        texto3 = new javax.swing.JLabel();
        texto4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsumatotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("INVENTARIO VALORIZADO - RESUMEN ");

        tablaLineas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDLINEA", "LINEA", "COSTO TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLineas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaLineas);
        if (tablaLineas.getColumnModel().getColumnCount() > 0) {
            tablaLineas.getColumnModel().getColumn(0).setResizable(false);
            tablaLineas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaLineas.getColumnModel().getColumn(1).setResizable(false);
            tablaLineas.getColumnModel().getColumn(1).setPreferredWidth(160);
            tablaLineas.getColumnModel().getColumn(2).setResizable(false);
        }

        btnImprimir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        texto0.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        texto1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        texto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        texto3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        texto4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setText("S/.");

        txtsumatotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsumatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(texto0, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(texto4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsumatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        HashMap parametros = SetParametros();
        ArrayList listado = SetLista();
        
        Servicios.Importaciones.Servicio_Control sc = new Servicios.Importaciones.Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();
        String url1 = rutaReportes + "\\consultasReportes\\Inventario_Valorizado_Resumen.jrxml";
        String url2 = rutaReportes + "\\consultasReportes\\Inventario_Valorizado_Resumen.jasper";
//        Sv_Impresion.exporta(1, lista, parametros, "reportes/Inventario_Valorizado_Resumen.pdf");
//        Sv_Impresion.exporta(3, lista, parametros, "reportes/Inventario_Valorizado_Resumen.pdf");
        try {
            convertPedidoToPDF(url1, url2, parametros, listado);

        } catch ( Exception ex ) {
            System.out.println("Error(Inventario_Valorizado_Resumen):" + ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    public void convertPedidoToPDF(String url1, String url2,
                                   HashMap parametros,
                                   ArrayList listado) throws Exception{
        
        JasperCompileManager.compileReportToFile(url1,url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
                                                               parametros,
                                                               new JRBeanCollectionDataSource(listado));
        
        JasperViewer.viewReport(jasperPrint, false, Locale.getDefault());
    }
    
    private ArrayList SetLista() {
        
        modelo = (DefaultTableModel) tablaLineas.getModel();
//        ArrayList<InventarioAlmacen> lista = new ArrayList<>();
        ArrayList<InventarioResumen> lista = new ArrayList<>();
        
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
            String numLinea = modelo.getValueAt(i, 0).toString();
            String linea = modelo.getValueAt(i, 1).toString();
            String total = modelo.getValueAt(i, 2).toString();
            
            lista.add(new InventarioResumen(numLinea, linea, total));
//            String numLinea = modelo.getValueAt(i, 0).toString();
//            String linea = modelo.getValueAt(i, 0).toString();
//            String codigo = modelo.getValueAt(i, 1).toString();
//            String codigosec = "";
//            if(modelo.getValueAt(i, 2)!=null){
//                codigosec = modelo.getValueAt(i, 2).toString();
//            }            
//            String descripcion = modelo.getValueAt(i, 3).toString();
//            String stock = modelo.getValueAt(i, 5).toString();
//            String precio = modelo.getValueAt(i, 6).toString();
//            String total = modelo.getValueAt(i, 7).toString();
//            lista.add(new InventarioAlmacen(linea, codigo, codigosec, descripcion, stock, precio, total));
        }
        return lista;
    }

    private HashMap SetParametros() {
        Servicio_Control sc = new Servicio_Control();       
        HashMap mapa = new HashMap<>();
        String empresa = sc.listar_control().get(0).getNombrempresa();
        String valorizacion = this.tipoValorizacion;
//        String valorizacion = txtvalorizacion.getText();
        String t1 = texto0.getText();
        String t2 = texto1.getText();
        String t3 = texto2.getText();
        String t4 = texto3.getText();
        String t5 = texto4.getText();
//        String totalneto = txtsumatotal.getText();
        mapa.put("empresa", empresa);
        mapa.put("valorizacion",valorizacion);
        mapa.put("t1", t1);
        mapa.put("t2", t2);
        mapa.put("t3", t3);
        mapa.put("t4", t4);
        mapa.put("t5", t5);
//        mapa.put("totalneto", totalneto);
        String nombre = "";
        if ( valorizacion.equals("Precio Lista") ) {
            nombre = "PRECIO LISTA";
        }
        if ( valorizacion.equals("Ultimo Costo") ) {
            nombre = "ULTIMO COSTO";
        }
        if ( valorizacion.equals("Costo Promedio") ) {
            nombre = "COSTO PROMEDIO";
        }
//        if ( tipvalor.equals("Precio Lista") ) {
//            nombre = "PRECIO LISTA";
//        }
//        if ( tipvalor.equals("Ultimo Costo") ) {
//            nombre = "ULTIMO COSTO";
//        }
//        if ( tipvalor.equals("Costo Promedio") ) {
//            nombre = "COSTO PROMEDIO";
//        }
        mapa.put("nombre", nombre);
        mapa.put("totalneto", totalNeto);
        return mapa;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaLineas;
    private javax.swing.JLabel texto0;
    private javax.swing.JLabel texto1;
    private javax.swing.JLabel texto2;
    private javax.swing.JLabel texto3;
    private javax.swing.JLabel texto4;
    private javax.swing.JLabel txtsumatotal;
    // End of variables declaration//GEN-END:variables
}
