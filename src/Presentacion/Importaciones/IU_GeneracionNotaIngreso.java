package Presentacion.Importaciones;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Importaciones.FormatoNotaIngreso;
import Entidades.Cabecsugerido;
import Entidades.Control;
import Entidades.Detallees;
import Entidades.Detallesugerido;
import Entidades.Importadores;
import Entidades.Operaciones;
import Entidades.Otros.Monetario;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import Mantenimiento.Importaciones.CabSugDAO;
import Mantenimiento.Importaciones.DetalleSugDAO;
import Mantenimiento.Importaciones.RepuestosDAO;
import Mantenimiento.OperacionesDAO;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Importaciones.Servicio_DetSugerido;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import Servicios.Importaciones.Servicio_DetalleES;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class IU_GeneracionNotaIngreso extends javax.swing.JFrame {
    static private IU_ValorizacionPI iuvalorizapi;
    private boolean registro = false;
    private int existenDetallees;
    static private int opcion;
    private double[] costNews;
    private double[] costoProm;
    
    public IU_GeneracionNotaIngreso(IU_ValorizacionPI iuvalpi, int opc) {
        initComponents();
        iuvalorizapi = iuvalpi;
        costNews = iuvalpi.getCostosNuevos();
        existenDetallees = inicializarCabecera();
        double costoTot = 0.00;
        costoTot = cargarParaNotaIngreso(tbDetalleNotaIngreso);
        txtTotal.setText(String.valueOf(costoTot));
        alinearCeldasADerecha();
        formatearDecimalDerechaEnTextField(txtTotal,costoTot);
        Servicio_DetalleES sdet = new Servicio_DetalleES();
        
        if ( sdet.seRegistro(iuvalorizapi.getIdSugerido(), 
           Integer.parseInt(txtNotaIngreso.getText()))){
            registro = true;
        }
        asignarMoneda();
    }
    
    private void asignarMoneda() {
        Servicio_Control sc = new Servicio_Control();
        Control c = sc.getControlUnico();
        String moneda = Monetario.asignarMoneda(c.getMonedarepuestos());
        lblMoneda.setText(moneda);
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbDetalleNotaIngreso.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tbDetalleNotaIngreso.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbDetalleNotaIngreso.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }
    
    private int inicializarCabecera(){
        int numDetallees =- 1;
        int idSugerido = iuvalorizapi.getIdSugerido();
        Servicio_DetalleES sd = new Servicio_DetalleES();
        String numIngreso = "1";
        
        if ( opcion == 1 ) {//REGISTRO            
            numIngreso = String.valueOf(sd.getNumIngresoNext());
        }
        txtNotaIngreso.setText(numIngreso);
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        Cabecsugerido cabsugerido = new Cabecsugerido();
        cabsugerido = scs.getCabSugerido(idSugerido);
        
        Importadores importador = new Importadores();
        importador = cabsugerido.getImportadores();
        txtImportador.setText(importador.getNombre());
        txtNumFactImpor.setText(cabsugerido.getNrofacimportacion());
        jdcFecha.setDate(new Date());
        txtMotivo.setText("INGRESO DE FACTURA DE IMPORTACION");
        txtMotivo.grabFocus();
        return numDetallees;
    }
    
    private void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        txt.setText(moneyCad);
    }
    
    private int getValida(int num){
        if ( String.valueOf(num).equals("null") ) {
            return 0;
        } else {
            return num;
        }
    }
    
    private double getValida(double num){
        if ( String.valueOf(num).equals("null") ) {
            return 0.0;
        } else {
            return num;
        }
    }
    
    public void setCostosPromedios(List lista,double[] costoNuevo,double[] costoUnitario){
        Iterator iteDet = lista.iterator();
        int i = 0;
        
        while ( iteDet.hasNext() ) {
            Object [] objRep = (Object []) iteDet.next();
            int idrepuesto = Integer.parseInt(String.valueOf(objRep[0]));
            int idlinea = Integer.parseInt(String.valueOf(objRep[4]));
            
            Servicio_Repuestos sr = new Servicio_Repuestos();
            Repuestos repAnt = sr.getRepuesto(idlinea,idrepuesto);
            
            int stock = getValida(repAnt.getStock());
            double costoPromedio = getValida(repAnt.getCostopromedio());
            int cantPedida = getValida(Integer.parseInt(String.valueOf(objRep[2])));
            
            double costoPromNew = getCostoPromedio(stock,costoPromedio,cantPedida,costoNuevo[i]);
            i++;
        }
    }
    
    private double getCostoPromedio(int stock, double costoPromedio, int cantPedida, double newCost){
        double costProm = 0.0;
        costProm = ( (stock * costoPromedio) + (cantPedida * newCost) ) / (stock + cantPedida);
        costProm = redondearA2Decimales(costProm);
        return costProm;
    }
    
    public double cargarParaNotaIngreso(JTable tbDetalleNotaIngreso) {
        DefaultTableModel table = (DefaultTableModel)tbDetalleNotaIngreso.getModel();
        DetalleSugDAO dsDao = new DetalleSugDAO();
        List listaDetSug = new ArrayList();
        listaDetSug = dsDao.getATablaNotaIngreso(iuvalorizapi.getIdSugerido());
        double factor = iuvalorizapi.getFactorValorizacion();
        Iterator iteDetSug = listaDetSug.iterator();
        double costTot = 0.00;
        int j = 0;
        Object row[] = new Object[6];
        double costonuevo = 0.00;
        costoProm = new double[costNews.length];
        
        while ( iteDetSug.hasNext() ) {
            Object [] objDetSug = (Object []) iteDetSug.next();
            row[0] = validaString(objDetSug[0]);
            row[1] = validaString(objDetSug[1]);
            row[2] = validaString(objDetSug[2]);
            row[3] = redondearA2Decimales(costNews[j]);
            int cant = validaInteger(objDetSug[4]);
            row[4] = getComaMillar(cant);
            Servicio_DetalleES sdes = new Servicio_DetalleES();
            int contador = sdes.contarDetallesConNI(iuvalorizapi.getIdSugerido());
            
            //Hallando costo promedio en jtable:
            if ( contador == 0 ) {
                Servicio_Repuestos sr = new Servicio_Repuestos();
                int idrepuesto = Integer.parseInt(String.valueOf(objDetSug[5]));
                int idlinea = Integer.parseInt(String.valueOf(objDetSug[7]));
                Repuestos repAnt = new Repuestos();
                repAnt = sr.getRepuesto(idlinea,idrepuesto);
                int stock = 0;
                
                if ( repAnt.getStock() != null ) {
                    stock = getValida(repAnt.getStock());
                }
                double costoPromedio = 0.00;
                if ( repAnt.getCostopromedio() != null ) {
                    costoPromedio = getValida(repAnt.getCostopromedio());
                }
                int cantPedida = getValida(Integer.parseInt(String.valueOf(objDetSug[4])));
                //System.out.println("IdRep-Stock-CostoProm-NuevoCost-CantPedida");
                costonuevo = factor * Double.parseDouble(String.valueOf(objDetSug[6]));
                //System.out.println(idrepuesto+"-"+stock+"-"+costoPromedio+"-"+costonuevo+"-"+cantPedida);
                
                double costProm = getCostoPromedio(stock,costoPromedio,cantPedida,costonuevo);
                costoProm[j] = costProm;
                
            }
            row[5] = Double.parseDouble(String.valueOf(redondearA4Decimales(costNews[j]))) * Double.parseDouble(String.valueOf(cant));
            costTot += redondearA2Decimales(Double.parseDouble(String.valueOf(row[5])));
            table.addRow(row);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[3])),j,3);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[5])),j,5);
            j++;
        }
        return costTot;
    }
    
    private double redondearA4Decimales(double numero){
        double newNumber = Math.round(numero * 10000.00) / 10000.00;
        return newNumber;
    }
    
    private String getComaMillar(Object valor){
        String cantStr = String.valueOf(valor);
        int cantInt = Integer.parseInt(cantStr);
        int cifMillar, cifResto;
        
        if ( cantStr.length() > 3 && Integer.parseInt(cantStr) > 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            
            if ( cifResto == 0 && cifMillar != 0 ) {
                cantStr = cifMillar + ",000";
                
            } else if ( cifResto / 100 != 0 ) {
                cantStr = cifMillar + "," + cifResto;
                
            } else if ( cifResto / 10 != 0 ) {
                cantStr = cifMillar + ",0" + cifResto;
                
            } else {
                cantStr = cifMillar + ",00" + cifResto;
            }
        } else if ( cantStr.length() > 4 && Integer.parseInt(cantStr) < 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            
            if ( cifResto == 0 && cifMillar != 0 ) {
                cantStr = cifMillar + ",000";
                
            } else if ( cifResto / 100 != 0 ) {
                cantStr = cifMillar + "," + Math.abs(cifResto);
                
            } else if ( cifResto / 10 != 0 ) {
                cantStr = cifMillar + ",0" + Math.abs(cifResto);
                
            } else {
                cantStr = cifMillar + ",00" + Math.abs(cifResto);
            }
        }
        return cantStr;
    }
    
    private int validaInteger(Object obj){
        if ( String.valueOf(obj).equals("null") ) {
            return 0;
            
        } else {
            return Integer.parseInt(String.valueOf(obj));
        }
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        tbDetalleNotaIngreso.setValueAt(moneyCad,fila,columna);        
    }
    
    private double redondearA2Decimales(double numero){
        double newNumber = Math.round(numero * 100.00) / 100.00;
        return newNumber;
    }
    
    private double validaDouble(Object obj){
        if ( String.valueOf(obj).equals("null") ) {
            return 0.0;
        } else {
            return Double.parseDouble(String.valueOf(obj));
        }
    }
    
    private String validaString(Object obj){
        if ( String.valueOf(obj).equals("null") ) {
            return "";
        } else {
            return String.valueOf(obj);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalleNotaIngreso = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};
            btnImprimir = new javax.swing.JButton();
            btnSalir = new javax.swing.JButton();
            txtImportador = new javax.swing.JTextField();
            jdcFecha = new com.toedter.calendar.JDateChooser();
            jLabel3 = new javax.swing.JLabel();
            txtTotal = new javax.swing.JTextField();
            txtNumFactImpor = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtMotivo = new javax.swing.JTextField();
            txtNotaIngreso = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            lblMoneda = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            setTitle("GENERACION DE NOTA DE INGRESO DE PEDIDO DE IMPORTACION");
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            jLabel5.setText("N° FACTURA DE IMPORTACIÓN:");

            tbDetalleNotaIngreso.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "COD. REPUESTO", "N° LÍNEA", "DESCRIPCIÓN", "PRECIO COSTO", "CANTIDAD", "COSTO TOTAL"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jScrollPane1.setViewportView(tbDetalleNotaIngreso);

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

            txtImportador.setEditable(false);
            txtImportador.setBackground(new java.awt.Color(255, 255, 255));

            jdcFecha.setBackground(new java.awt.Color(255, 255, 255));
            jdcFecha.setEnabled(false);

            jLabel3.setText("N° NOTA DE INGRESO:");

            txtTotal.setEditable(false);
            txtTotal.setBackground(new java.awt.Color(255, 255, 255));
            txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

            txtNumFactImpor.setEditable(false);
            txtNumFactImpor.setBackground(new java.awt.Color(255, 255, 255));

            jLabel2.setText("FECHA:");

            jLabel1.setText("NOMBRE DEL IMPORTADOR:");

            jLabel4.setText("MOTIVO:");

            txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtMotivoKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtMotivoKeyTyped(evt);
                }
            });

            txtNotaIngreso.setEditable(false);
            txtNotaIngreso.setBackground(new java.awt.Color(255, 255, 255));

            jLabel6.setText("TOTAL:");

            lblMoneda.setText("S/.");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtNotaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtImportador, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(txtNumFactImpor))))
                    .addGap(21, 21, 21))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNotaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNumFactImpor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMoneda))
                    .addContainerGap())
            );

            btnImprimir.getAccessibleContext().setAccessibleName("");
            btnSalir.getAccessibleContext().setAccessibleName("");

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
        iuvalorizapi.getIU_Valorizacion().setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        JOptionPane jop = new JOptionPane();
        double total = Double.parseDouble(quitaComa(iuvalorizapi.getTotalCostos()));
        
        if ( txtMotivo.getText().equals("") ) {
            jop.showMessageDialog(null, "Por favor, ingrese el MOTIVO\n de la Nota de Ingreso", "FALTA", JOptionPane.ERROR_MESSAGE);
            txtMotivo.grabFocus();
            
        } else {
            if ( registro == false ) {
                
                if ( JOptionPane.showConfirmDialog(this, "¿Está seguro de imprimir la nota de\ningreso, sólo se registra por UNICA VEZ?", "CONFIRMACION",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
                {
//                    iuvalorizapi.setCostosPromedios(iuvalorizapi.getLista(), iuvalorizapi.getCostosNuevos(), iuvalorizapi.getValoresFob());
                    iuvalorizapi.setListaCostosPromedios(iuvalorizapi.getLista(), iuvalorizapi.getCostosNuevos(), iuvalorizapi.getValoresFob());
                    Servicio_CabSugerido scs = new Servicio_CabSugerido();
                    
                    // Actualizar Cabecera de Sugerido
                    if ( scs.actualizaEstadoTerminado(iuvalorizapi.getIdSugerido()) ) {
                        System.out.println("actualizar Estado Terminado(acabó ok)");
                    }
                    
                    // Se registra en Detallees el "detalle del pedido" (k inicialmente estaba en detallesugerido
                    // k se tiene k traspasar todos los registros entre tablas(detallesugerido, detallees) para no perder la data, 
                    // puesto que se eliminarán todos los registros del detallesugerido cuando se haya generado la "Nota de Ingreso"
                    if ( registrar() && actualizar() ) {
                        registro = true;
                        jop.showMessageDialog(null, "Registro de Nota de Ingreso Exitosa", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        jop.showMessageDialog(null, "Error al registrar Nota de ingreso \n" + "y actualizar stock","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    //Eliminando registros de detallesugerido
                    Servicio_DetSugerido sds = new Servicio_DetSugerido();
                    int idsugerido = iuvalorizapi.getIdSugerido();
                    
//                    System.out.println("idsugerido:" + idsugerido);
                    if ( sds.eliminarDetSugerido(idsugerido) ) {
                        System.out.println("eliminados todos ok");
                    }
                    try {
                        convertNotaIngresoToPDF();
                    } catch ( Exception ex ) {
                        System.out.println("Error:"+ex.getMessage());
                    }
                }
            } else {  
                try {
                    convertNotaIngresoToPDF();
                } catch ( Exception ex ) {
                    System.out.println("Error:"+ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        iuvalorizapi.getIU_Valorizacion().setEnabled(true);
        this.dispose();
        
        if ( registro ) {
            iuvalorizapi.getIU_Valorizacion().btnValorizar.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().btnIngresar.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().btnModificar.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().btnEliminar.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().txtNumFactImportacion.setEditable(false);
            iuvalorizapi.getIU_Valorizacion().txtNumGuia.setEditable(false);
            iuvalorizapi.getIU_Valorizacion().btnNuevoImportador.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().cbImportador.setEnabled(false);
            iuvalorizapi.getIU_Valorizacion().jdcFechaEmbarque.setEnabled(false);
        }
    }//GEN-LAST:event_btnSalirActionPerformed
    private void txtMotivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            btnImprimir.grabFocus();
        }
    }//GEN-LAST:event_txtMotivoKeyPressed
    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
        char c = evt.getKeyChar();
        if ( c >= 'a' && c <= 'z' ) {
            evt.setKeyChar((char) (c - 32));
        }
    }//GEN-LAST:event_txtMotivoKeyTyped
    
    public void convertNotaIngresoToPDF() throws Exception{
        DefaultTableModel dtm = new DefaultTableModel();
        dtm = (DefaultTableModel) tbDetalleNotaIngreso.getModel();
        int numRow = dtm.getRowCount();
        
        List<FormatoNotaIngreso> listado = new ArrayList<FormatoNotaIngreso>();
        for ( int i = 0; i < numRow; i++ ) {
            FormatoNotaIngreso fni = new FormatoNotaIngreso();
            fni.setNumItem(String.valueOf(i + 1));
            fni.setCodRepuesto(String.valueOf(dtm.getValueAt(i,0)));
            fni.setNumLinea(String.valueOf(dtm.getValueAt(i,1)));
            fni.setDescripcion(String.valueOf(dtm.getValueAt(i,2)));
            fni.setPrecioCosto(String.valueOf(dtm.getValueAt(i,3)));
            fni.setCantidad(String.valueOf(dtm.getValueAt(i,4)));
            fni.setCostoTotal(String.valueOf(dtm.getValueAt(i,5)));
            listado.add(fni);
        }
        
//        String url1 = "src/Presentacion/Importaciones/NotaIngreso.jrxml";
//        String url2 = "src/Presentacion/Importaciones/NotaIngreso.jasper";
        
        Servicio_Control sc = new Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();

        String url1 = rutaReportes + "\\NotaIngreso.jrxml";
        String url2 = rutaReportes + "\\NotaIngreso.jasper";
        
        JasperCompileManager.compileReportToFile(url1, url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        //PARAMETROS
        HashMap parametros = new HashMap();
        parametros.put("importador", txtImportador.getText());
        parametros.put("notaIngreso", txtNotaIngreso.getText());
        parametros.put("motivo", txtMotivo.getText());
        
        Date hoy = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String cadFecha = df.format(hoy);
        parametros.put("fecha", cadFecha);
        parametros.put("numFactImp", txtNumFactImpor.getText());
        parametros.put("total", txtTotal.getText());
        parametros.put("moneda", lblMoneda.getText());
        
        JasperPrint jasperPrint=JasperFillManager.fillReport(reporte,parametros,new JRBeanCollectionDataSource(listado));// port(reporte, parametros,datasource);
        
        JRExporter exporter = new JRPdfExporter();  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
        
        String pdf = rutaReportes + "/NotaIngreso" + txtNotaIngreso.getText() + ".pdf";
        
        // El directorio raiz no tiene pq existir -- mkdirs    --
//        File directorio2 = new File(rutaReportes);
//        if ( directorio2.mkdirs() )
//            System.out.println("Se ha creado directorio");
//        else
//            System.out.println("No se ha podido crear el directorio"); 
        
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(pdf)); 
        exporter.exportReport(); 
        
        Desktop d = Desktop.getDesktop();
        if ( d.isDesktopSupported() ) {
           File path = new File (pdf);
           d.open(path);
        } else{
            System.out.println("El sistema no soporta los procedimientos.");
        }
    }
    
    private boolean actualizar(){
        System.out.println("ACTUALIZAR...");
        boolean val = true;
        DetalleSugDAO dsd = new DetalleSugDAO();
        List<Detallesugerido> lista = new ArrayList<Detallesugerido>();
        lista = dsd.getDetallesSug(iuvalorizapi.getIdSugerido());
        RepuestosDAO rd = new RepuestosDAO();
        Servicio_Repuestos sr = new Servicio_Repuestos();
        
        List<Repuestos> listaRepStockNuevo = new ArrayList<Repuestos>();
        
        for ( int i = 0; i < lista.size(); i++ ) {
            int idRep = lista.get(i).getRepuestos().getId().getIdrepuesto();
            int linea = lista.get(i).getRepuestos().getId().getIdlinea();
            Repuestos rep = rd.getRepuesto(linea, idRep);
            int cantPedida = lista.get(i).getCantpedida();
            int stock = 0;
            
            if ( rep.getStock() != null ) {
                stock = rep.getStock();
            }
            rep.setCostopromedio(this.costoProm[i]);
//            System.out.println(idRep + "-" + linea + "->" + stock + "-" + cantPedida);
            int newStock = stock + cantPedida;
        
            // Actualizar stock en Repuestos
            rep.setStock(newStock);
//            if ( !sr.actualizarStock(rep, newStock) ) {
//                val = false;
//                break;
//            }
            listaRepStockNuevo.add(rep);
        }
        if ( !listaRepStockNuevo.isEmpty() ) {
            System.out.println("Tamaño de listaRepStockNuevo:" + listaRepStockNuevo.size());
        }
        if ( !sr.actualizarListaStock(listaRepStockNuevo) ) {
            val = false;
        }
        
        Servicio_CabSugerido scs = new Servicio_CabSugerido();
        Cabecsugerido cs = new Cabecsugerido();
        cs = scs.getCabSugerido(iuvalorizapi.getIdSugerido());
        double total = Double.parseDouble(quitaComa(iuvalorizapi.getTotalCostos()));
//        System.out.println("Total:"+total);
        cs.setTotal(total);
        
        // Actualizar fecha de ingreso del stock en Cabecera de Sugerido
        if ( !scs.actualizarFechaIngresoStock(cs) ) {
            val = false;
        }
        return val;
    }
    
    public String quitaComa(String Nombre1){
        int c = Nombre1.length();
        String cad = "";
        
        for ( int contador = 0; contador < c; contador++ ) {
            if ( Nombre1.charAt(contador) == ',' ) {
                Nombre1.replace(',',' ');
            } else {
                cad += Nombre1.charAt(contador);
            }
        }
        return cad;
    }
    
    private boolean registrar(){
        System.out.println("REGISTRAR DetalleSugerido en Detallees...");
        boolean val = true;
        List<Detallesugerido> lista = new ArrayList<Detallesugerido>();
        DetalleSugDAO dsd = new DetalleSugDAO();
//        System.out.println("iuvalorizapi:" + iuvalorizapi.getIdSugerido());
        lista = dsd.getDetallesSug(iuvalorizapi.getIdSugerido());
        Servicio_DetalleES sde = new Servicio_DetalleES();
        
        RepuestosDAO rd = new RepuestosDAO();
        CabSugDAO csd = new CabSugDAO();
        Cabecsugerido cs = new Cabecsugerido();
        List<Detallees> listaDetalles = new ArrayList<Detallees>();
        
        
        for ( int i = 0; i < lista.size(); i++ ) {
            int idRep = lista.get(i).getRepuestos().getId().getIdrepuesto();
            int linea = lista.get(i).getRepuestos().getId().getIdlinea();
//            System.out.println("linea:" + linea);
//            System.out.println("idRep:" + idRep);
            
//            Repuestos rep = rd.getRepuesto(linea, idRep);
            RepuestosId repId = new RepuestosId();
            repId.setIdlinea(linea);
            repId.setIdrepuesto(idRep);
            
            Repuestos rep = new Repuestos();
            rep.setId(repId);
            
            Detallees detEs = new Detallees(); // MOVIDO DENTRO DEL FOR AHORA
            detEs.setRepuestos(rep);
            cs = csd.getCabSug(iuvalorizapi.getIdSugerido());
            detEs.setCabecsugerido(cs);
            detEs.setFecha(new Date());
            detEs.setNroingreso(Integer.parseInt(txtNotaIngreso.getText()));
            detEs.setMotivo(txtMotivo.getText());
            
            OperacionesDAO od = new OperacionesDAO();
            Operaciones op = od.getOperacion("IB");
            detEs.setOperaciones(op);
            int cantEntregada = lista.get(i).getCantentregada();
            detEs.setCantentregada(cantEntregada);
            detEs.setCantpedida(cantEntregada);
            
            Servicio_Repuestos sr = new Servicio_Repuestos();
            detEs.setPreciolista(sr.getRepuesto(linea, idRep).getPreciolista());//VERIFICAR
            detEs.setCostopromedio(costoProm[i]);////VERIFICAR
            detEs.setUltimocosto(lista.get(i).getValorcosto());
            
//            CabecesId cabId = new CabecesId();
//            cabId.setTipotra("C");
//            cabId.setTipodoc("09");
//            cabId.setNrorserie("1");
//            cabId.setNrodocumento(txtNumFactImpor.getText());
//            
//            Cabeces cab = new Cabeces();
//            cab.setId(cabId);
//            detEs.setCabeces(cab);
            
//            if ( !sde.registrarDetallees(detEs) ) {
//                val = false;
//                break;
//            }
            listaDetalles.add(detEs);
        }
        if ( !sde.registrarListaDetallees(listaDetalles) ) {
            val = false;
        }
        return val;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JTable tbDetalleNotaIngreso;
    private javax.swing.JTextField txtImportador;
    public javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNotaIngreso;
    private javax.swing.JTextField txtNumFactImpor;
    public javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}