package Presentacion.Importaciones;

import Entidades.Control;
import Entidades.Importaciones.FormatoImpSugerido;
import Entidades.Importaciones.Ordenacion;
import Entidades.Otros.Monetario;
import java.awt.Desktop;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Importaciones.Servicio_DetSugerido;

public class IU_ImpSugerido extends javax.swing.JFrame {
    static FREP025 menu;
    static private int idCabSug;
    
    public IU_ImpSugerido(FREP025 m, int idcabsug) {
        initComponents();
        Ordenacion ord = new Ordenacion();
        ord.eventoTabla(this);
        menu = m;
        idCabSug = idcabsug;
        cargarIdSugerido();
        dcFecha.setDate(new Date());
        Servicio_DetSugerido sds = new Servicio_DetSugerido(this);
        sds.cargaTablaAImprimirSugerido(idCabSug,1);
        alinearCeldasADerecha();
        Servicio_Control sc = new Servicio_Control();
        Control c = sc.getControlUnico();
        lblEmpresa.setText(c.getNombrempresa());
        asignarMoneda(c);
    }
    
    private void asignarMoneda(Control c) {
        String moneda = Monetario.asignarMoneda(c.getMonedarepuestos());
        lblMonedaTotCos.setText(moneda);
        lblMonedaTotFob.setText(moneda);
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbResultados.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(8).setCellRenderer(tcr);
    }
    
    private String getNumImpresion(int idcabsug){
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        String numguia = scs.getActualNumImpresion(idcabsug);
        return String.valueOf(Integer.parseInt(numguia) + 1);
    }
    
    private void cargarIdSugerido(){
        txtNumPedido.setText(String.valueOf(idCabSug));
    }
    
    public void convertSugeridoToPDF() throws Exception {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tbResultados.getModel();
        int numRow = dtm.getRowCount();
        List<FormatoImpSugerido> listado = new ArrayList<FormatoImpSugerido>();
        
        for ( int i = 0; i < numRow; i++ ) { 
            FormatoImpSugerido fis = new FormatoImpSugerido();
            fis.setNumItem(String.valueOf(i + 1));
            fis.setCodLinea(String.valueOf(dtm.getValueAt(i, 0)));
            fis.setNumParte(String.valueOf(dtm.getValueAt(i, 1)));
            fis.setCodSec(String.valueOf(dtm.getValueAt(i, 2)));
            fis.setDescripcion(String.valueOf(dtm.getValueAt(i, 3)));
            fis.setCantPedida(String.valueOf(dtm.getValueAt(i, 4)));
            fis.setPrecioCosto(String.valueOf(dtm.getValueAt(i, 5)));
            fis.setCostoTotal(String.valueOf(dtm.getValueAt(i, 6)));
            fis.setFob(String.valueOf(dtm.getValueAt(i, 7)));
            fis.setFobTotal(String.valueOf(dtm.getValueAt(i, 8)));
            listado.add(fis);
        }
//        String url1 = "src/Presentacion/Importaciones/Sugerido.jrxml";
//        String url2 = "src/Presentacion/Importaciones/Sugerido.jasper";
        Servicio_Control sc = new Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();
        
        String url1 = rutaReportes + "\\Sugerido.jrxml";
        String url2 = rutaReportes + "\\Sugerido.jasper";
                
        JasperCompileManager.compileReportToFile(url1, url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        
        //PARAMETROS
        HashMap parametros = new HashMap();
        //Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("empresa", lblEmpresa.getText());
        parametros.put("numPedido",txtNumPedido.getText());
        
        Date hoy = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String cadFecha = df.format(hoy);
        parametros.put("fecha", cadFecha);
        parametros.put("totalCosto", txtTotalCosto.getText());
        parametros.put("totalFob", txtTotalFOB.getText());
        parametros.put("monedaTC", lblMonedaTotCos.getText());
        parametros.put("monedaFob", lblMonedaTotFob.getText());
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listado));// port(reporte, parametros,datasource);
        JRExporter exporter = new JRPdfExporter();  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
        
        String pdf = rutaReportes + "\\Sugerido" + txtNumPedido.getText() + ".pdf";
        System.out.println("pdf:" + pdf);
        
        // El directorio raiz no tiene pq existir -- mkdirs    --
        File directorio2 = new File(rutaReportes);
        if ( directorio2.mkdirs() )
            System.out.println("Se ha creado directorio");
//        else
//            System.out.println("No se ha podido crear el directorio"); 
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(pdf)); 
        exporter.exportReport(); 
        Desktop d = Desktop.getDesktop();
        
        if ( d.isDesktopSupported() ) {
           File path = new File (pdf);
           d.open(path);
           
        } else {
            System.out.println("El sistema no soporta los procedimientos.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTotalCosto = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        dcFecha = new com.toedter.calendar.JDateChooser();
        txtNumPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTotalFOB = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResultados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};
            jLabel1 = new javax.swing.JLabel();
            lblMonedaTotCos = new javax.swing.JLabel();
            lblMonedaTotFob = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            setTitle("IMPRESIÓN DE SUGERIDO");
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel4.setText("TOTAL COSTO:");

            txtTotalCosto.setEditable(false);
            txtTotalCosto.setBackground(new java.awt.Color(255, 255, 255));
            txtTotalCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

            btnSalir.setText("SALIR");
            btnSalir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSalirActionPerformed(evt);
                }
            });

            dcFecha.setBackground(new java.awt.Color(255, 255, 255));
            dcFecha.setDateFormatString("dd-MM-yyyy");
            dcFecha.setEnabled(false);

            txtNumPedido.setEditable(false);
            txtNumPedido.setBackground(new java.awt.Color(255, 255, 255));

            jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel7.setText("FECHA:");

            jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel3.setText("N° DE PEDIDO:");

            lblEmpresa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            lblEmpresa.setText("[Nombre de la Empresa]");

            btnImprimir.setText("IMPRIMIR");
            btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnImprimirActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
            jLabel2.setText("SUGERIDO DE COMPRA");

            txtTotalFOB.setEditable(false);
            txtTotalFOB.setBackground(new java.awt.Color(255, 255, 255));
            txtTotalFOB.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

            tbResultados.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "COD. LINEA ▲▼", "N° PARTE ▲▼", "COD. SEC.", "DESCRIPCION", "CANTIDAD", "PRECIO COSTO", "COSTO TOTAL", "FOB", "FOB TOTAL"
                }
            ));
            jScrollPane1.setViewportView(tbResultados);

            jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel1.setText("TOTAL FOB:");

            lblMonedaTotCos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblMonedaTotCos.setText("S/.");

            lblMonedaTotFob.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblMonedaTotFob.setText("S/.");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblMonedaTotCos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(lblMonedaTotFob, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTotalFOB, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1115, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(20, 20, 20))))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblEmpresa)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(35, 35, 35)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(txtTotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalFOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMonedaTotCos)
                        .addComponent(lblMonedaTotFob))
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if ( JOptionPane.showConfirmDialog(this, "¿Está seguro de imprimir el sugerido?",    
                                           "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
            try {
                convertSugeridoToPDF();
                
            } catch ( Exception ex ) {
                System.out.println("Error:" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblMonedaTotCos;
    private javax.swing.JLabel lblMonedaTotFob;
    public javax.swing.JTable tbResultados;
    private javax.swing.JTextField txtNumPedido;
    public javax.swing.JTextField txtTotalCosto;
    public javax.swing.JTextField txtTotalFOB;
    // End of variables declaration//GEN-END:variables
}