package Presentacion.Importaciones;

import Entidades.Control;
import Entidades.Importaciones.FormatoOrdenCompra;
import Entidades.Importaciones.Ordenacion;
import Entidades.Importadores;
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
import Servicios.Importaciones.Servicio_Excel;
import Servicios.Importaciones.Servicio_Importadores;
import java.util.Locale;
import net.sf.jasperreports.view.JasperViewer;

public class IU_ImpPedido extends javax.swing.JFrame {
    static FREP025 menu;
    static private int idCabSug;
    Servicio_CabSugerido scs;
    
    public IU_ImpPedido(FREP025 m, int idcabsug) {
        initComponents();
        Ordenacion ord = new Ordenacion();
        ord.eventoTabla(this);
        menu = m;
        idCabSug = idcabsug;
        cargarIdSugerido();
        cargarImportadores();
        dcFecha.setDate(new Date());
        Servicio_DetSugerido sds = new Servicio_DetSugerido(this);
        sds.cargaTablaAImprimirSugerido(idCabSug,2);
        alinearCeldasADerecha();
        Servicio_Control sc = new Servicio_Control();
        Control c = sc.getControlUnico();
        lblEmpresa.setText(c.getNombrempresa());
        scs = new Servicio_CabSugerido();
        asignarMoneda(c);
    }
    
    private void asignarMoneda(Control c) {
        String moneda = Monetario.asignarMoneda(c.getMonedarepuestos());
        lblMoneda.setText(moneda);
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbResultados.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tbResultados.getColumnModel().getColumn(6).setCellRenderer(tcr);
    }
    
    public void vaciarImportadores(){
        cbImportador.removeAllItems();
    }
    
    public void cargarImportadores(){
        Servicio_Importadores si = new Servicio_Importadores();
        si.listarImportadores(cbImportador);
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        Importadores imp = scs.getCabSugerido(idCabSug).getImportadores();
        
        if ( imp != null ) {
            String importador = imp.getNombre();
            int indice = -1;
            
            for ( int i = 0; i < cbImportador.getItemCount(); i++ ) {
                if ( cbImportador.getItemAt(i).equals(importador) ) {
                    indice = i;
                    break;
                }
            }
            cbImportador.setSelectedIndex(indice);
        }
    }
    
    public int cargarImportadores(String nombreImportador){
        Servicio_Importadores si = new Servicio_Importadores();
        return si.listarImportadores(cbImportador,nombreImportador);
    }
    
    private void cargarIdSugerido() {
        txtNumPedido.setText(String.valueOf(idCabSug));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTotalFob = new javax.swing.JTextField();
        txtNumPedido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        cbImportador = new javax.swing.JComboBox();
        btnNuevo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResultados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};
            lblEmpresa = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            btnImprimir = new javax.swing.JButton();
            btnSalir = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            lblMoneda = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            setTitle("IMPRESIÓN DE PEDIDO OFICIAL DE IMPORTACIÓN");
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel3.setText("N° DE PEDIDO:");

            txtTotalFob.setEditable(false);
            txtTotalFob.setBackground(new java.awt.Color(255, 255, 255));
            txtTotalFob.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

            txtNumPedido.setEditable(false);
            txtNumPedido.setBackground(new java.awt.Color(255, 255, 255));

            jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel9.setText("TOTAL FOB:");

            dcFecha.setBackground(new java.awt.Color(255, 255, 255));
            dcFecha.setEnabled(false);

            btnNuevo.setText("NUEVO");
            btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNuevoActionPerformed(evt);
                }
            });

            jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel4.setText("IMPORTADOR:");

            btnExportar.setText("EXPORTAR");
            btnExportar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnExportarActionPerformed(evt);
                }
            });

            tbResultados.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "PART. ARANC.", "COD. FABRICACIÓN", "N° PARTE ▲▼", "DESCRIPCIÓN ▲▼", "CANTIDAD", "FOB", "FOB TOTAL"
                }
            ));
            jScrollPane1.setViewportView(tbResultados);

            lblEmpresa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            lblEmpresa.setText("[Nombre de la Empresa]");

            jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            jLabel7.setText("FECHA:");

            btnImprimir.setText("IMPRIMIR");
            btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnImprimirActionPerformed(evt);
                }
            });

            btnSalir.setText("SALIR");
            btnSalir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSalirActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
            jLabel2.setText("ORDEN DE COMPRA");

            lblMoneda.setText("S/.");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 473, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotalFob, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)))
                            .addGap(24, 24, 24))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cbImportador, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnNuevo))
                                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(16, 16, 16)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNumPedido))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txtTotalFob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMoneda))
                    .addGap(20, 20, 20))
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
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        frmiNuevoImportador iuImpor = new frmiNuevoImportador(this,1);
        iuImpor.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed
    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        Servicio_Excel se = new Servicio_Excel(tbResultados, this);
        se.Exportar_Excel("Pedido Oficial");
    }//GEN-LAST:event_btnExportarActionPerformed
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int numPed = Integer.parseInt(txtNumPedido.getText());
        Object importNew = cbImportador.getSelectedItem();
        
        if ( cbImportador.getSelectedItem().equals("") ) {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Por favor, seleccione un importador", "AVISO", JOptionPane.OK_OPTION);
            
        } else {
            if ( scs.esSugerido(numPed) ) {
                if ( JOptionPane.showConfirmDialog(this,"¿Está seguro de registrar la ORDEN DE COMPRA de importación?",
                    "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                    actualizarRegistroCabecSugerido(1);
                    System.out.println("Orden de Compra registrada");
                    
                    try {
                        convertPedidoToPDF(importNew);
                    } catch ( Exception ex ) {
                        System.out.println("Error:"+ex.getMessage());
                    }
                }
            } else {//Si pedido es OFICIAL, y se imprime por segunda vez
                Object importAnt = scs.getCabSugerido(numPed).getImportadores().getNombre();
                Object importador = importAnt;
                
                if ( !importAnt.equals(importNew) ) {
                    if ( JOptionPane.showConfirmDialog(this,"¿Está seguro de modificar la ORDEN DE COMPRA de importación?",
                        "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                        
                        actualizarRegistroCabecSugerido(2);
                        importador = importNew;
                        System.out.println("Reactualizando orden de compra");
                        
                    } else {
                        System.out.println("Orden de compra NO MODIFICADA");
                    }
                } else {
                    System.out.println("Solo muestra pdf, no actualiza registros");
                }
                
                try {
                    convertPedidoToPDF(importador);
                } catch ( Exception ex ) {
                    System.out.println("Error(botón Imprimir):" + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    
    public String getCabeceraPedido(){
        //String importador=String.valueOf(cbImportador.getSelectedItem());
        String importador = "[Importador]";
        String ln = System.getProperty("line.separator");
        return "FRANCO SAC\t\t\t\t\t\t\t\t\t\t\t\tPágina: 1" + ln + ln
                +"ORDEN DE COMPRA\t\t\t\t\t\t\t\t\t\t\t\t(FREP030.PRG)" + ln + ln
                +"NRO.DE PEDIDO: 17\t\t\t\t\t\t\t\t\t\t\tFECHA: 13/05/2013" + ln + ln
                +"IMPORTADOR:" + importador + ln + ln
                +"-------------------------------------------------------------------------------------------------------------------------"+ln
                +"NRO. DE PARTE	COD.	*------------DESCRIPCION-------------*	CANT.		F.O.B.		FOB"+ln
		+"\t\tSEC.						PEDIDA 				TOTAL"+ln
                +"-------------------------------------------------------------------------------------------------------------------------"+ln;
    }
    
    public void actualizarRegistroCabecSugerido(int opc){
        JOptionPane jop = new JOptionPane();
        
        // Código encontrado comentado (algo raro k haya estado comentado, pq lo dejé operativo)
        if ( cbImportador.getSelectedIndex() != -1 ) {
            int idsugerido = Integer.parseInt(txtNumPedido.getText());
            String nameImport = String.valueOf(cbImportador.getSelectedItem());
            
            if ( scs.actualizaFechaPedido(idsugerido,nameImport) && opc == 1 ) {
                jop.showMessageDialog(null, "Registro del Pedido Oficial Exitoso","EXITO", 
                                        JOptionPane.INFORMATION_MESSAGE);
                
            } else if ( scs.actualizaFechaPedido(idsugerido,nameImport) && opc == 2 ) {
                jop.showMessageDialog(null, "Actualización del Pedido Oficial Exitoso","EXITO", 
                                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                jop.showMessageDialog(null, "No se pudo registrar el Pedido Oficial en la BD","ERROR", 
                                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            jop.showMessageDialog(null, "Por favor, seleccione un importador","AVISO", 
                                        JOptionPane.OK_OPTION);
        }
    }
    
    public void convertPedidoToPDF(Object importador) throws Exception{
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tbResultados.getModel();
        int numRow = dtm.getRowCount();
        
        List<FormatoOrdenCompra> listado = new ArrayList<FormatoOrdenCompra>();
        for ( int i = 0; i < numRow; i++ ) { 
            FormatoOrdenCompra foc = new FormatoOrdenCompra();
            foc.setNumItem(String.valueOf(i+1));
            foc.setPartidaarancelaria(String.valueOf(dtm.getValueAt(i,0)));
            foc.setIdLinea(String.valueOf(dtm.getValueAt(i,1)));
            foc.setNumparte(String.valueOf(dtm.getValueAt(i,2)));
            foc.setDescripcion(String.valueOf(dtm.getValueAt(i,3)));
            foc.setCantpedida(String.valueOf(dtm.getValueAt(i,4)));
            foc.setFob(String.valueOf(dtm.getValueAt(i,5)));
            foc.setFobtotal(String.valueOf(dtm.getValueAt(i,6)));
            listado.add(foc);
        }
//        String url1 = "src/Presentacion/Importaciones/OrdenCompra.jrxml";
//        String url2 = "src/Presentacion/Importaciones/OrdenCompra.jasper";
        
        Servicio_Control sc = new Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();

        String url1 = rutaReportes + "\\OrdenCompra.jrxml";
        String url2 = rutaReportes + "\\OrdenCompra.jasper";
        
        JasperCompileManager.compileReportToFile(url1,url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        
        //PARAMETROS
        HashMap parametros = new HashMap();
        parametros.put("empresa", lblEmpresa.getText());
        parametros.put("numPedido",txtNumPedido.getText());
        
        Date hoy = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String cadFecha = df.format(hoy);
        parametros.put("fecha",cadFecha);
        parametros.put("importador",String.valueOf(importador));
        parametros.put("totalImp",txtTotalFob.getText());
        parametros.put("moneda", lblMoneda.getText());
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros,new JRBeanCollectionDataSource(listado));// port(reporte, parametros,datasource);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
        
        String pdf = rutaReportes + "\\OrdenCompra" + txtNumPedido.getText() + ".pdf";
        
//        JasperViewer.viewReport(jasperPrint, false, Locale.getDefault());
        
        // El directorio raiz no tiene pq existir -- mkdirs    --
        File directorio2 = new File(rutaReportes);
        if ( directorio2.mkdirs() )
            System.out.println("Se ha creado directorio");
        
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    public javax.swing.JComboBox cbImportador;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblMoneda;
    public javax.swing.JTable tbResultados;
    private javax.swing.JTextField txtNumPedido;
    public javax.swing.JTextField txtTotalFob;
    // End of variables declaration//GEN-END:variables
}