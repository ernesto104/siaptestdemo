package Presentacion.Importaciones;

import Entidades.Importaciones.FormatoValorizacion;
import Entidades.Cabecsugerido;
import Entidades.Control;
import Entidades.Importadores;
import Entidades.Otros.Monetario;
import Entidades.Tramites;
import Mantenimiento.Importaciones.TramitesDAO;
import static Presentacion.Importaciones.IU_ValorizacionPI.iuvaloriza;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
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
import Servicios.Importaciones.FullSelectorListener;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Importaciones.Servicio_Importadores;
import Servicios.Importaciones.Servicio_Tramites;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class IU_Valorizacion extends javax.swing.JFrame {
    static FREP026 pediofi;
    Servicio_CabSugerido scs;
    Cabecsugerido cs;
    private Servicio_Tramites st;
    private frmiIngresarImpuesto frmInputTax;
    private int idCabSugerido;
    static private frmiNuevoImportador nuevoImportador;
    private String numFactImpBD;
    private String nameImportadorBD;
    private String numGuiaBD;
    private Date fechaEmbarqueBD;
    
    private Double total;
    
    public IU_Valorizacion(FREP026 pedOf) {
        initComponents();
        
        if ( pedOf.cboPedOficial.getItemCount() > 0 ) {
            scs = new Servicio_CabSugerido();
            cs = new Cabecsugerido();
            
            pediofi = pedOf;
            double tot = 0.00;
            
            // Obtener sugerido
            String idCabSug = String.valueOf(pedOf.cboPedOficial.getSelectedItem());
            txtNumPedido.setText(idCabSug);
            idCabSugerido = Integer.parseInt(idCabSug);
            System.out.println("idCabSugerido:" + idCabSugerido);
            cs = scs.getCabSugerido(idCabSugerido);
            
            // Asignar cabecera de Valorización
            numFactImpBD = cs.getNrofacimportacion();
            txtNumFactImportacion.setText(numFactImpBD);
            numGuiaBD = cs.getNroguia();
            txtNumGuia.setText(numGuiaBD);
            fechaEmbarqueBD = cs.getFechaembarque();
            jdcFechaEmbarque.setDate(fechaEmbarqueBD);
            
            // Asignar proveedores
            nameImportadorBD = cs.getImportadores().getNombre();
            asignarProveedor(true);
            
            // Calcular totales de Valorización
            total = cs.getTotal();
            System.out.println("total:" + total);
            
            if ( total == null ) {//Pedido no ingresado
                System.out.println("1.Pedido no ingresado(Valorizar)");
                tot = redondearA2Decimales(scs.getFobTotal(idCabSugerido));
                habilitarElementos(true);
                
            } else {//Pedido ya ingresado
                System.out.println("2.Pedido ya ingresado(ya se valorizó)");
                tot = total;
                txtFactorCosto.setText(String.valueOf(cs.getFactorCosto()));
                habilitarElementos(false);
                
                if ( cs.getFactorCosto() == null ) { // Se valorizó por impuestos
                    txtFactorCosto.setText("");
                    btnAceptar.setEnabled(false);
                    
                } else { // Se valorizó por factor de costo
                    txtTotalImportacion.setText(String.valueOf(total));
                    btnAceptar.setEnabled(false);
                }
                btnAceptar.setEnabled(false);
            }
            
            System.out.println("tot:" + tot);
            if ( tot != -1 ) {
                txtValorFobImp.setText(String.valueOf(tot));
                
            } else {
                txtValorFobImp.setText("0.00");
                txtTotalImportacion.setText("0.00");
            }
            
            // Asignar browse de trámites para Valorización:
            // 1.Por Factor de Costo ó 
            // 2.Gastos Administrativos)}
            st = new Servicio_Tramites(this);
            limpiarTabla();
            boolean hayTramites = st.cargarTramitesATabla(tbImpuestos);
            rbGastosAdmin.setSelected(hayTramites);
            tbImpuestos.setEnabled(true);
            formatearDecimalDerechaEnTextField(txtValorFobImp, tot);
            
//            double producto = iniciarDatosValorizacion(cs, hayTramites);
//            if ( producto != 0.00 ) {
//                formatearDecimalDerechaEnTextField(txtTotalImportacion, producto);
//            }
//            if ( tbImpuestos.getRowCount() == 0 || cs.getTotal() != null ) {
//                btnValorizar.setEnabled(false);
//            } else {
//                btnValorizar.setEnabled(true);
//            }
        }
        alinearCeldasADerecha();
        asignarMoneda();
        rbFactorCosto.setSelected(true);
    }
    
    private void habilitarElementos(boolean habilitar) {
        btnIngresar.setEnabled(habilitar);
        btnModificar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
        btnValorizar.setEnabled(habilitar);
        
        txtNumFactImportacion.setEnabled(habilitar);
        txtNumGuia.setEnabled(habilitar);
        jdcFechaEmbarque.setEnabled(habilitar);
        
        cbImportador.setEnabled(habilitar);
        btnNuevoImportador.setEnabled(habilitar);
        
        rbFactorCosto.setEnabled(habilitar);
        txtFactorCosto.setEnabled(habilitar);
        btnAceptar.setEnabled(habilitar);
        
        rbGastosAdmin.setEnabled(habilitar);
        tbImpuestos.setEnabled(habilitar);
    }
    
    private void asignarMoneda() {
        Servicio_Control sc = new Servicio_Control();
        Control c = sc.getControlUnico();
        String moneda = Monetario.asignarMoneda(c.getMonedarepuestos());
        lblMonedaVFI.setText(moneda);
        lblMonedaTI.setText(moneda);
    }
    
    private double iniciarDatosValorizacion(Cabecsugerido cs, boolean hayTramites) {
//        System.out.println("factor costo inicial:" + cs.getFactorCosto());
        double producto = 0.00;
//        double 
//        System.out.println("cs.getFactorCosto():" + cs.getFactorCosto());
//        System.out.println("igual ? " + (cs.getFactorCosto() == null));
        
        if ( cs.getFactorCosto() == null  ) { 
            if ( !hayTramites ) { // Inicio de Valorización, aún no se ha guardado Modalidad de Valorizar
                System.out.println("caso 0");
                rbFactorCosto.setSelected(true);
                inicializarModoGastosAdmValorizar(false);
                
            } else { // Cuando ya se ingreso la valorización, y se está volviendo por 2° vez
                System.out.println("caso 1");
                rbGastosAdmin.setSelected(true);
                inicializarModoGastosAdmValorizar(true);
            }

        } else if ( cs.getFactorCosto() == 0.00 ) { // Modalidad 2 de Valorizar: Por gastos administrativos
            System.out.println("caso 2");
            rbGastosAdmin.setSelected(true);
            System.out.println("total:" + total);
//            if ( total == null ) {
//                System.out.println("total igual a null, sugerido a valorizar");
//                inicializarModoGastosAdmValorizar(true);
//            } else {
//                System.out.println("total no es null, nota de ingreso, no valorizar");
//                inicializarModoGastosAdmValorizar(false);
//                txtFactorCosto.setText("");
//            }
                inicializarModoGastosAdmValorizar(true);

        } else { // Modalidad 1 de Valorizar: Por factor de costo
            System.out.println("caso 3");
            rbFactorCosto.setSelected(true);
            txtFactorCosto.setText(String.valueOf(cs.getFactorCosto()));
            producto = setTotalImportacion();
            inicializarModoGastosAdmValorizar(false);
        }
        return producto;
    }
    
    private void inicializarModoGastosAdmValorizar(boolean mostrar) {
        rbFactorCosto.setSelected(!mostrar);
        txtFactorCosto.setEnabled(!mostrar);
        btnIngresar.setEnabled(mostrar);
        btnModificar.setEnabled(mostrar);
        btnEliminar.setEnabled(mostrar);
        
        if ( mostrar ){
            txtFactorCosto.setText("");
        } else {
            txtFactorCosto.grabFocus();
        }
    }
    
    public void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
//        System.out.println("moneyCad:" + moneyCad);
        txt.setText(moneyCad);
//        System.out.println("txt.getText:" + txt.getText());
    }
    
    public int getIdCabSugerido(){
        return idCabSugerido;
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbImpuestos.getColumnModel().getColumn(1).setCellRenderer(tcr);
    }
    
    private double redondearA2Decimales(double numero){
        double newNumber = Math.round(numero * 100.00) / 100.00;
        return newNumber;
    }
    
    public IU_Valorizacion(frmiIngresarImpuesto ingImpuesto){
        initComponents();
        frmInputTax = ingImpuesto;
    }
    
    public IU_Valorizacion(frmiNuevoImportador nuevoImp){
        initComponents();
        nuevoImportador = nuevoImp;
    }
    
    private void asignarProveedor(boolean activar){
        cbImportador.setVisible(activar);
        cbImportador.removeAllItems();
        if ( activar ) {
            cargarImportadores();
        }
        
        if ( this.nameImportadorBD != null ) {
            cbImportador.setSelectedItem(nameImportadorBD);
        }
    }
    
    public String getNumPedido(){
        return txtNumPedido.getText();
    }
    
    public int getFilaSeleccionadaTabla(){
        return tbImpuestos.getSelectedRow();
    }
    
    public void actualizarTotalImportacion() {
        double totImportacion = 0.00;
        for ( int i = 0; i < tbImpuestos.getRowCount(); i++ ) {
            double importe;
            if ( tbImpuestos.getValueAt(i,1) == null ) {
                importe = 0.00;
                
            } else {
                importe = Double.parseDouble(quitaComa(String.valueOf(tbImpuestos.getValueAt(i,1))));
            }
            totImportacion += importe;
        }
        totImportacion = redondearA2Decimales(totImportacion);
        totImportacion += Double.parseDouble(quitaComa(txtValorFobImp.getText()));
        formatearDecimalDerechaEnTextField(txtTotalImportacion, totImportacion);
    }
    
    @SuppressWarnings("unchecked")
    public String quitaComa(String numero){
        int c = numero.length();
        String cad = "";
        
        for ( int contador = 0; contador < c; contador++ ) {
            if ( numero.charAt(contador) == ',' ) {
                numero.replace(',' , ' ');
                
            } else {
                cad += numero.charAt(contador);
            }
        }
        return cad;
    }
    
    public void cargarImportadores(){
        Servicio_Importadores si = new Servicio_Importadores();
        si.listarImportadores(cbImportador);
    }
    
    public int cargarImportadores(String nombreImportador){
        Servicio_Importadores si = new Servicio_Importadores();
        return si.listarImportadores(cbImportador,nombreImportador);
    }
    
    public void vaciarImportadores(){
        cbImportador.removeAllItems();
    }
    
    public void convertValorizacionToPDF() throws Exception {
        System.out.println("convertValorizacionToPDF...");
        
        //PARAMETROS
        //Map<String, String> parametros = new HashMap<String, String>();
        HashMap parametros = new HashMap();
        Date hoy = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String cadFecha = df.format(hoy);
        parametros.put("fecha", cadFecha);
        parametros.put("importador", String.valueOf(cbImportador.getSelectedItem()));
        parametros.put("fobImp", txtValorFobImp.getText());
        parametros.put("numPedido", txtNumPedido.getText());
        parametros.put("numFacImp", txtNumFactImportacion.getText());
        parametros.put("numGuia", txtNumGuia.getText());
        parametros.put("totalImp", txtTotalImportacion.getText());
        parametros.put("monedaVFI", lblMonedaVFI.getText());
        parametros.put("monedaTI", lblMonedaTI.getText());
        
        Date fechaEmbarque = jdcFechaEmbarque.getDate();
        cadFecha = df.format(fechaEmbarque);
        parametros.put("fechaEmbarque", cadFecha);
        
        // Configurar jasper:
        String url1 = "", url2 = "";
        JasperPrint jasperPrint = null;
        List<FormatoValorizacion> listado = new ArrayList<FormatoValorizacion>();
        
        if ( rbGastosAdmin.isSelected() ) {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm = (DefaultTableModel) tbImpuestos.getModel();
            int numRow = dtm.getRowCount();
            
            for ( int i = 0; i < numRow; i++ ) {
                FormatoValorizacion fv = new FormatoValorizacion();
                fv.setGlosa(String.valueOf(dtm.getValueAt(i, 0)));
                fv.setMonto(String.valueOf(dtm.getValueAt(i, 1)));
                listado.add(fv);
            }
            
        } else if ( rbFactorCosto.isSelected() ) {
            
            String tiString = txtTotalImportacion.getText();
            String vfiString = txtValorFobImp.getText();
            double ti = Double.parseDouble(quitaComa(tiString));
            double vfi = Double.parseDouble(quitaComa(vfiString));
            double sumaImpuestos = ti - vfi;
            String si = Monetario.formatearMonetario(sumaImpuestos, 2);
//            System.out.println("sumaImpuestos:" + si);
//            parametros.put("totImpuestos", si);
            
            FormatoValorizacion fv = new FormatoValorizacion();
            fv.setGlosa("Total Impuestos");
            fv.setMonto(si);
            listado.add(fv);
        }
        
//        url1 = "src/Presentacion/Importaciones/Valorizacion.jrxml";
//        url2 = "src/Presentacion/Importaciones/Valorizacion.jasper";
        
        Servicio_Control sc = new Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();

        url1 = rutaReportes + "\\Valorizacion.jrxml";
        url2 = rutaReportes + "\\Valorizacion.jasper";

        JasperCompileManager.compileReportToFile(url1, url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);
        jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listado));
            
        JRExporter exporter = new JRPdfExporter();  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
        
        //String pdf="src/Presentacion/Importaciones/Valorizacion"+txtNumPedido.getText()+".pdf";
        
        // El directorio raiz no tiene pq existir -- mkdirs    --
        File directorio2 = new File(rutaReportes);
//        if ( directorio2.mkdirs() )
//            System.out.println("Se ha creado directorio");
//        else
//            System.out.println("No se ha podido crear el directorio"); 
        
        String pdf = rutaReportes + "/ValorizacionFC" + txtNumPedido.getText() + ".pdf";
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgModoValorizar = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblImportador = new javax.swing.JLabel();
        cbImportador = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNumPedido = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbImpuestos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        txtValorFobImp = new javax.swing.JTextField();
        lblNumFactImport = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnNuevoImportador = new javax.swing.JButton();
        txtNumFactImportacion = new javax.swing.JTextField();
        txtNumPedido = new javax.swing.JTextField();
        lblNumGuia = new javax.swing.JLabel();
        txtNumGuia = new javax.swing.JTextField();
        jdcFechaEmbarque = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnValorizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbFactorCosto = new javax.swing.JRadioButton();
        txtFactorCosto = new javax.swing.JTextField();
        rbGastosAdmin = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAceptar = new javax.swing.JButton();
        txtTotalImportacion = new javax.swing.JTextField();
        lblMonedaTI = new javax.swing.JLabel();
        lblMonedaVFI = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("VALORIZACION DE PEDIDOS DE IMPORTACION + RESUMEN + INGRESOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblImportador.setText("IMPORTADOR:");

        cbImportador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbImportadorKeyPressed(evt);
            }
        });

        jLabel7.setText("VALOR FOB IMPORTACIÓN:");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("VALORIZACIÓN DE PEDIDO DE IMPORTACIÓN");

        jLabel9.setText("TOTAL DE IMPORTACIÓN:");

        lblNumPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumPedido.setText("N° PEDIDO:");

        tbImpuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GLOSA IMPUESTO", "MONTO"
            }
        ));
        tbImpuestos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(tbImpuestos);

        txtValorFobImp.setEditable(false);
        txtValorFobImp.setBackground(new java.awt.Color(255, 255, 255));
        txtValorFobImp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblNumFactImport.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumFactImport.setText("N° FACTURA DE IMPORTACION:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("ORDEN DE IMPORTACION DE REPUESTOS");

        btnNuevoImportador.setText("NUEVO");
        btnNuevoImportador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoImportadorActionPerformed(evt);
            }
        });

        txtNumFactImportacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumFactImportacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFactImportacionKeyTyped(evt);
            }
        });

        txtNumPedido.setEditable(false);
        txtNumPedido.setBackground(new java.awt.Color(255, 255, 255));

        lblNumGuia.setText("N° GUIA:");

        txtNumGuia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumGuiaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumGuiaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("FECHA DE EMBARQUE:");

        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnValorizar.setText("VALORIZAR");
        btnValorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValorizarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("(*)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("(*)");

        bgModoValorizar.add(rbFactorCosto);
        rbFactorCosto.setText("Por Factor de Costo");
        rbFactorCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbFactorCostoItemStateChanged(evt);
            }
        });

        bgModoValorizar.add(rbGastosAdmin);
        rbGastosAdmin.setText("Por Gastos Administrativos");
        rbGastosAdmin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbGastosAdminItemStateChanged(evt);
            }
        });

        btnAceptar.setText("Actualizar Total de Importación");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtTotalImportacion.setEditable(false);
        txtTotalImportacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalImportacion.setEnabled(false);

        lblMonedaTI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMonedaTI.setText("S/.");

        lblMonedaVFI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMonedaVFI.setText("S/.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 401, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNumGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumFactImport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumFactImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rbFactorCosto)
                                        .addComponent(rbGastosAdmin))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtFactorCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnAceptar))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblMonedaTI, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(txtTotalImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblImportador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblMonedaVFI, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtValorFobImp, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbImportador, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNuevoImportador, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnValorizar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumPedido)
                    .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblNumFactImport))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumFactImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumGuia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdcFechaEmbarque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImportador)
                    .addComponent(cbImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoImportador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValorFobImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(lblMonedaVFI))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFactorCosto)
                    .addComponent(txtFactorCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbGastosAdmin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonedaTI))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnValorizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        JOptionPane jop = new JOptionPane();
        Servicio_CabSugerido sc = new Servicio_CabSugerido();
        Cabecsugerido cs = new Cabecsugerido();
        cs = sc.getCabSugerido(Integer.parseInt(txtNumPedido.getText()));
        fechaEmbarqueBD = cs.getFechaembarque();
        
        if ( rbGastosAdmin.isSelected() ) {
            if ( tbImpuestos.getModel().getRowCount() == 0 ) {
                jop.showMessageDialog(null, "Ingrese al menos un impuesto,\n para poder imprimir",
                                      "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                btnIngresar.grabFocus();

            } else if ( btnSalir.getActionCommand().equals("SALIR") ) {
                preparandoValorizacion(jop, cs);
            }
        }
        if ( rbFactorCosto.isSelected() ) {
            String factorCosto = txtFactorCosto.getText();
            if ( "".equals(factorCosto) ) {
                msgRequiereFactorCosto(jop);
                
            } else {
                double fc = Double.parseDouble(factorCosto);
                if ( fc < 1 || fc >= 2 ) {
                    msgRequiereFactorCosto(jop);
                    
                } else {
                    if ( totalImportacionActualizado(txtValorFobImp, txtFactorCosto, txtTotalImportacion) ) {
                        if ( btnSalir.getActionCommand().equals("SALIR") ) {
                            System.out.println("entraaa");
                            preparandoValorizacion(jop, cs);
                            setBotonValorizar();
                        }

                    } else {
                        jop.showMessageDialog(null, "Presione el botón \"Actualizar Total de Importación\" para Valorizar",
                                              "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        if ( tbImpuestos.getRowCount() == 0 ) {
            btnValorizar.setEnabled(false);
            
        } else {
            btnValorizar.setEnabled(true);
        }
        frmiIngresarImpuesto iuImpuesto = new frmiIngresarImpuesto(this,1);
        iuImpuesto.setVisible(true);
    }//GEN-LAST:event_btnIngresarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if ( tbImpuestos.getRowCount() == 0 ) {
            btnValorizar.setEnabled(false);
            
        } else {
            btnValorizar.setEnabled(true);
        }
        
        int filaSeleccionada = tbImpuestos.getSelectedRow();
        if ( filaSeleccionada != - 1 ) {
            JOptionPane j = new JOptionPane();
            
            if ( JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el impuesto?", 
                                               "CONFIRMACION", JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION){
                Servicio_CabSugerido scs = new Servicio_CabSugerido();
                DefaultTableModel modelo =(DefaultTableModel)tbImpuestos.getModel();
                String glosa = String.valueOf(tbImpuestos.getValueAt(filaSeleccionada,0));
                int idTramite = st.getIdTramiteXGlosa(idCabSugerido,glosa);
                
                if ( idTramite != -1 ) {
                    TramitesDAO tramDao = new TramitesDAO();
                    Tramites tramiteAnt = new Tramites();
                    tramiteAnt = tramDao.Obtener_Objeto(idTramite);
                    Cabecsugerido cs = new Cabecsugerido();
                    tramiteAnt.setCabecsugerido(cs);
                    
                    if ( st.eliminarImpuesto(idTramite) ) {
                        modelo.removeRow(filaSeleccionada);
                        actualizarTotalImportacion();
                        j.showMessageDialog(null, "El Impuesto se ha eliminado del Pedido Oficial","EXITO", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Seleccione un impuesto de la tabla","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnValorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValorizarActionPerformed
        //si nro factura importacion <> null -> PEDIDO OFICIAL, sino -> ORDEN DE PEDIDO
        JOptionPane jop = new JOptionPane();

        if ( rbGastosAdmin.isSelected() ) {
            if ( tbImpuestos.getModel().getRowCount() != 0 ) {
                preparandoValorizacionPI(jop);

            } else {
                jop.showMessageDialog(null, "Ingrese al menos un impuesto,\n para poder valorizar",
                                      "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                btnIngresar.grabFocus();
            }
        }

        if ( rbFactorCosto.isSelected() ) {
            String factorCosto = txtFactorCosto.getText();
            if ( "".equals(factorCosto) ) {
                msgRequiereFactorCosto(jop);
                
            } else {
                double fc = Double.parseDouble(factorCosto);
                
                if ( fc < 1 || fc >= 2 ) {
                    msgRequiereFactorCosto(jop);
                    
                } else {
                    if ( totalImportacionActualizado(txtValorFobImp, txtFactorCosto, txtTotalImportacion) ) {
                        preparandoValorizacionPI(jop);

                    } else {
                        jop.showMessageDialog(null, "Presione el botón \"Actualizar Total de Importación\" para Valorizar",
                                              "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnValorizarActionPerformed
    private void btnNuevoImportadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoImportadorActionPerformed
        frmiNuevoImportador_1 importador = new frmiNuevoImportador_1(this);
        importador.setVisible(true);
    }//GEN-LAST:event_btnNuevoImportadorActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        String textBtn = btnSalir.getActionCommand();
        if ( textBtn.equals("SALIR") ) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed
    private void txtNumFactImportacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFactImportacionKeyTyped
        char caracter = evt.getKeyChar();
        if( ((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '-') ) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumFactImportacionKeyTyped
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tbImpuestos.getSelectedRow();
        if ( filaSeleccionada != -1 ) {
            frmiIngresarImpuesto modificar = new frmiIngresarImpuesto(this,2);
            modificar.setVisible(true);
            actualizarTotalImportacion();
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Seleccione un impuesto de la tabla", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed
    private void txtNumFactImportacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFactImportacionKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            txtNumGuia.grabFocus();
        }
    }//GEN-LAST:event_txtNumFactImportacionKeyPressed
    private void txtNumGuiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumGuiaKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            cbImportador.grabFocus();
        }
    }//GEN-LAST:event_txtNumGuiaKeyPressed
    private void cbImportadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbImportadorKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            btnImprimir.grabFocus();
        }
    }//GEN-LAST:event_cbImportadorKeyPressed
    private void txtNumGuiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumGuiaKeyTyped
        char caracter = evt.getKeyChar();
        if( ((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '-') ) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumGuiaKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        double fc = Double.parseDouble(txtFactorCosto.getText());
        
        if ( fc < 1 || fc >= 2) {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Ingrese un valor mayor o igual que 1 y menor que 2 para el Factor de Costo",
                                  "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            btnValorizar.setEnabled(false);
            
        } else {
            double producto = setTotalImportacion();
            formatearDecimalDerechaEnTextField(txtTotalImportacion, producto);
            btnValorizar.setEnabled(true);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void setBotonValorizar() {
        if ( "*".equals(cs.getEstado()) ) { // Aún no se ha valorizado
            btnValorizar.setEnabled(true);
        } else { // Ya se valorizó
            btnValorizar.setEnabled(false);
        }
        System.out.println("btnValorizar.setEnabled:" + btnValorizar.isEnabled());
    }
    
    private double setTotalImportacion() {
        double fc = Double.parseDouble(txtFactorCosto.getText());
        String vfiSinComa = quitaComa(txtValorFobImp.getText());
        double vfi = Double.parseDouble(vfiSinComa);
        return Double.parseDouble(Monetario.formatearMonetario(vfi * fc, 2));
    }
    
    private void inicializarModGastosAdministrativos(boolean mostrar) {
        txtFactorCosto.setEnabled(!mostrar);
        btnAceptar.setEnabled(!mostrar);
        btnIngresar.setEnabled(mostrar);
        btnModificar.setEnabled(mostrar);
        btnEliminar.setEnabled(mostrar);
        
//        btnValorizar.setEnabled(!mostrar);
        
        if ( mostrar ){
            txtFactorCosto.setText("");
        }
        txtTotalImportacion.setText(txtValorFobImp.getText());
    }
    
    private void rbGastosAdminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbGastosAdminItemStateChanged

        if ( total == null ) { // Es un sugerido aún y debe valorizarse
            btnIngresar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            txtFactorCosto.setEnabled(false);
            btnAceptar.setEnabled(false);
            txtFactorCosto.setText("");
        }
//            inicializarModGastosAdministrativos(true);
//        } else { // Es una nota de ingreso, y ya no se valoriza
//            inicializarModGastosAdministrativos(false);
//        }
        st.cargarTramitesATabla(tbImpuestos);
        actualizarTotalImportacion();
    }//GEN-LAST:event_rbGastosAdminItemStateChanged

    private void rbFactorCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbFactorCostoItemStateChanged
        
        btnIngresar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        String vfi = txtValorFobImp.getText();
        txtTotalImportacion.setText(vfi);
        
        if ( "*".equals(this.cs.getEstado()) ) { // Aún no se ha valorizado
            txtFactorCosto.setEnabled(true);
            btnAceptar.setEnabled(true);
            
        } else { // Ya se valorizó (se tiene Nota de Ingreso)
            txtFactorCosto.setEnabled(false);
            btnAceptar.setEnabled(false);
            System.out.println("btnValorizar deshabilitar");
            btnValorizar.setEnabled(false);
        }
        Double fc = cs.getFactorCosto();
        if ( cs == null ) {
            txtFactorCosto.setText("");
            
        } else if ( cs.getFactorCosto() == null ) {
            txtFactorCosto.setText("");
            
        } else {
            txtFactorCosto.setText(String.valueOf(fc));
            vfi = quitaComa(vfi);
            double vfiDouble = Double.parseDouble(vfi);
            double producto = fc * vfiDouble;
//            txtTotalImportacion.setText(String.valueOf(producto));
            formatearDecimalDerechaEnTextField(txtTotalImportacion, producto);
        }        
        limpiarTabla();
    }//GEN-LAST:event_rbFactorCostoItemStateChanged

    public void limpiarTabla(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) tbImpuestos.getModel();
            int filas = tbImpuestos.getRowCount();
            for ( int i = 0; filas > i; i++ ) {
                modelo.removeRow(0);
            }
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgModoValorizar;
    private javax.swing.JButton btnAceptar;
    public javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImprimir;
    public javax.swing.JButton btnIngresar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevoImportador;
    private javax.swing.JButton btnSalir;
    public javax.swing.JButton btnValorizar;
    public javax.swing.JComboBox cbImportador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public com.toedter.calendar.JDateChooser jdcFechaEmbarque;
    private javax.swing.JLabel lblImportador;
    private javax.swing.JLabel lblMonedaTI;
    private javax.swing.JLabel lblMonedaVFI;
    private javax.swing.JLabel lblNumFactImport;
    private javax.swing.JLabel lblNumGuia;
    private javax.swing.JLabel lblNumPedido;
    public javax.swing.JRadioButton rbFactorCosto;
    public javax.swing.JRadioButton rbGastosAdmin;
    public javax.swing.JTable tbImpuestos;
    private javax.swing.JTextField txtFactorCosto;
    public javax.swing.JTextField txtNumFactImportacion;
    public javax.swing.JTextField txtNumGuia;
    public javax.swing.JTextField txtNumPedido;
    public javax.swing.JTextField txtTotalImportacion;
    public javax.swing.JTextField txtValorFobImp;
    // End of variables declaration//GEN-END:variables
    
    private void preparandoValorizacion(JOptionPane jop, Cabecsugerido cs) {
        txtNumFactImportacion.setVisible(true);
        txtNumFactImportacion.grabFocus();
        txtNumFactImportacion.addFocusListener(new FullSelectorListener());
        asignarProveedor(true);

        if ( this.nameImportadorBD != null ) {
            cbImportador.setSelectedItem(nameImportadorBD);
        }
        jdcFechaEmbarque.grabFocus();

        if ( txtNumFactImportacion.getText().equals("") ) {
            jop.showMessageDialog(null, "Ingrese el número de facturación,\n para poder imprimir",
                                  "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            txtNumFactImportacion.grabFocus();
        }/*else if(txtNumGuia.getText().equals("")){
         jop.showMessageDialog(null, "Ingrese el número de guía,\n para poder imprimir",
         "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
         txtNumGuia.grabFocus();
         }*/ else if ( jdcFechaEmbarque.getDate() == null ) {
            jop.showMessageDialog(null, "Ingrese la fecha de embarque,\n para poder imprimir",
                                  "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
            jdcFechaEmbarque.getDateEditor().getUiComponent().requestFocusInWindow();

        } else {
            String numFactImpForm = txtNumFactImportacion.getText();
            String nameImpForm = String.valueOf(cbImportador.getSelectedItem());
            String numGuiaForm = txtNumGuia.getText();
            Date fechaEmbarqueForm = jdcFechaEmbarque.getDate();
            /*System.out.println("fechaEmbarqueBD:"+fechaEmbarqueBD);
             System.out.println("FechaEmbarqueForm:"+fechaEmbarqueForm);*/

            if ( numFactImpForm.equals(numFactImpBD) && nameImpForm.equals(nameImportadorBD)
                    && numGuiaForm.equals(numGuiaBD) && fechaEmbarqueForm.equals(fechaEmbarqueBD) ) {
                System.out.println("No guardar, son iguales en N° fac,importador, N° Guia y fecha de embarque");

            } else {
                System.out.println("Guardar en bd");
                cs = new Cabecsugerido();
                int idCabSug = Integer.parseInt(txtNumPedido.getText());
                Servicio_Importadores si = new Servicio_Importadores();
                Importadores imp = new Importadores();
                imp = si.getImportador(String.valueOf(cbImportador.getSelectedItem()));
                
                double factorCosto = 0.00;
                if ( !"".equals(txtFactorCosto.getText()) ) {
                    factorCosto = Double.parseDouble(txtFactorCosto.getText());
                }
                scs.actualizarCabSug(idCabSug, numFactImpForm, numGuiaForm, imp, jdcFechaEmbarque.getDate(), factorCosto);

                if ( !numFactImpForm.equals(numFactImpBD) ) {
                    System.out.println("Desea guardar el nuevo N° Factura de Importacion");
                }
                if ( !nameImpForm.equals(nameImportadorBD) ) {
                    System.out.println("Desea guardar el nuevo importador");
                }
                if ( !numGuiaForm.equals(numGuiaBD) ) {
                    System.out.println("Desea guardar el nuevo N° guía");
                }
                //UPDATE EN BD CABSUGERIDO
                this.numFactImpBD = numFactImpForm;
                this.nameImportadorBD = imp.getNombre();
                this.numGuiaBD = numGuiaForm;
            }
            if ( JOptionPane.showConfirmDialog(this, "¿Está seguro de imprimir la valorización?",
                                               "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                System.out.println("Si imprimir");

                try {
                    convertValorizacionToPDF();
                } catch ( Exception ex ) {
                    System.out.println("Error:" + ex.getMessage());
                }
            }
        }
    }
    
    private void preparandoValorizacionPI(JOptionPane jop) {
        if ( !txtNumFactImportacion.getText().equals("") && 
             jdcFechaEmbarque.getDate() != null && 
             this.nameImportadorBD != null ) {

            IU_ValorizacionPI iuValPI = new IU_ValorizacionPI(this);
            iuValPI.setVisible(true);
            //iuValPI.verDiferencia();
            System.out.println("Guardar en bd");
            int idCabSug = Integer.parseInt(txtNumPedido.getText());
            Servicio_CabSugerido scs = new Servicio_CabSugerido();
            Cabecsugerido cs = new Cabecsugerido();
            cs = scs.getCabSugerido(idCabSug);

//            System.out.println("N° Fact Imp:" + cs.getNrofacimportacion());
//            System.out.println("Fecha embarque:" + cs.getFechaembarque());
            
//            if ( cs.getNrofacimportacion() == null || cs.getFechaembarque() == null ) {
//                System.out.println("entraaaaa");
                Servicio_Importadores si = new Servicio_Importadores();
                Importadores imp = new Importadores();
                imp = si.getImportador(String.valueOf(cbImportador.getSelectedItem()));

                double factorCosto = 0.00;
                if ( !"".equals(txtFactorCosto.getText()) ) {
                    factorCosto = Double.parseDouble(txtFactorCosto.getText());
                }
//                System.out.println("factorCosto:" + factorCosto);
                
                if ( !scs.actualizarCabSug(idCabSug, txtNumFactImportacion.getText(), txtNumGuia.getText(),
                                           imp, jdcFechaEmbarque.getDate(), factorCosto) ) {
                    System.out.println("Error al actualizar cabSugerido");
                }
                System.out.println("caso 1");
//            }
        } else {
            if ( rbGastosAdmin.isSelected() ) {
                if ( tbImpuestos.getModel().getRowCount() == 0 ) {
                    jop.showMessageDialog(null, "Ingrese al menos un impuesto,\n para poder valorizar",
                                          "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                    btnIngresar.grabFocus();

                } else {
                    System.out.println("caso 2");
                    validarCamposValorizar(jop);
                }
            }
            if ( rbFactorCosto.isSelected() ) {
                if ( "".equals(txtFactorCosto.getText()) ) {
                    jop.showMessageDialog(null, "Ingrese el factor de costo con un valor entre 1 y 2,\n para poder imprimir",
                                          "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("caso 3");
                    validarCamposValorizar(jop);
                }
            }
        }
    }
    
    private void validarCamposValorizar(JOptionPane jop) {
        String mensaje = "";

        if ( txtNumFactImportacion.getText().equals("")
                && this.nameImportadorBD == null
                && this.jdcFechaEmbarque.getDate() == null ) {
            mensaje = "Seleccione un importador, N° factura de importación y\n"
                    + "fecha de embarque para poder valorizar";
            txtNumFactImportacion.grabFocus();

        } else if ( this.nameImportadorBD == null ) {
            mensaje = "Seleccione un importador,\npara poder valorizar";
            cbImportador.grabFocus();

        } else if ( txtNumFactImportacion.getText().equals("") ) {
            mensaje = "Ingrese el N° factura de importación,\npara poder valorizar";
            txtNumFactImportacion.grabFocus();

        } else if ( jdcFechaEmbarque.getDate() == null ) {
            mensaje = "Seleccione fecha de embarque,\npara poder valorizar";
            jdcFechaEmbarque.grabFocus();
        }
        jop.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean totalImportacionActualizado(JTextField txtVfi, JTextField txtFc, JTextField txtTi) {
        boolean actualizado = false;
        double vfi = Double.parseDouble(quitaComa(txtVfi.getText()));
        double fc = Double.parseDouble(quitaComa(txtFc.getText()));
        double ti = Double.parseDouble(quitaComa(txtTi.getText()));
//        System.out.println("vfi:" + vfi);
//        System.out.println("fc:" + fc);
//        System.out.println("ti:" + ti);
        
        Double producto = Double.parseDouble(Monetario.formatearMonetario(vfi * fc, 2));
//        System.out.println("producto:" + producto);
//        System.out.println("iguales?" + (producto == ti));
        if ( producto == ti ) {
            actualizado = true;
        }
        return actualizado;
    }
    
    private void msgRequiereFactorCosto(JOptionPane jop) {
        jop.showMessageDialog(null, "Ingrese el factor de costo con un valor entre 1 y 2,\n para poder imprimir",
                                      "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
    }
}