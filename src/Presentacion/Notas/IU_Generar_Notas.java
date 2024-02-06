package Presentacion.Notas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import Entidades.Control;
import Entidades.Detallenota;
import static Entidades.Otros.Constante.NOTA_CREDITO;
import static Entidades.Otros.Constante.NOTA_DEBITO;
import static Entidades.Otros.Constante.NUM_CARACT_DIR_TRUNCA;
import Entidades.Otros.DatosNC;
import Entidades.Otros.DatosND;
import Entidades.Otros.Fecha;
import Entidades.Otros.ImpresionExcel;
import Entidades.Otros.Monetario;
import Entidades.Repuestos;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Entidades.Usuarios;
import Mantenimiento.ClienteDAO;
import Mantenimiento.ControlDAO;
import Mantenimiento.ExcelDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Documentos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Repuesto;
import Servicios.Servicio_Sistema;
import Servicios.Servicio_TipoCambio;
import Servicios.Util;
import Servicios.facturacion.Billete;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Generar_Notas extends javax.swing.JFrame {

    Clientes cliente;
    Cabeces cabeces;
    DefaultTableModel modelo;
    ControlDAO controlDAO;
    double igv;
    double valorVentaNota;
    double igvNota;
    double totalNota;
    double tipoCambioVenta;
    private DateFormat df;
    Servicio_Documentos servicioDocumentos;
    Tipocambio tipoCambio;
    TablaNotas2Columnas tabla;
    TablaNotas4Columnas tabla4;
    int numFilaNota;
    Sistema sistema;
    String nota;
    double subTotal;
    double igvv;
    double total;
    Usuarios usuario;
    boolean bandera_mon;
    Util util;
    
    Servicio_Excel servicio_Excel;
    String tipoDocumento;
    String nroFactura;
    
    String concepto;
    String completarNotaDe;
    
    int modalidadNC;
    private ArrayList<Detallenota> listaDetalleNota;

    public IU_Generar_Notas(Usuarios u, String tipoNota, Clientes cliente, Cabeces cabeces, TablaNotas2Columnas tabla, TablaNotas4Columnas tabla4, 
                            boolean bandera, String fechaEmision,
                            String tipoDocumento, String nroFactura,
                            String concepto,
                            int modalidadNC) throws ParseException {

        this.modalidadNC = modalidadNC;
        this.concepto = concepto;
        initComponents();
        bandera_mon = bandera;
        cambiar();
        this.tipoDocumento = tipoDocumento;
        this.nroFactura = nroFactura;
        
        if ( tabla == null ) {
            this.tabla4 = tabla4;
            this.tabla = null;
            tblDescripcion.setModel(tabla4);
            tabla4.SetEditable(false);
            numFilaNota = this.tabla4.lN.size();
            
        } else {
            this.tabla = tabla;
            this.tabla4 = null;
            tblDescripcion.setModel(tabla);
            tabla.SetEditable(false);
            numFilaNota = this.tabla.getlN().size();
        }
        this.setLocationRelativeTo(null);
        this.nota = tipoNota; // NC o ND
        if ( NOTA_CREDITO.equals(tipoNota) ) {
            completarNotaDe = "de Crédito";
            
        } else {
            completarNotaDe = "de Débito";
        }
        
        lblTitulo.setText("GENERAR NOTA " + completarNotaDe.toUpperCase());
        this.cliente = cliente;
        this.cabeces = cabeces;
        df = new SimpleDateFormat("dd/MM/yyyy");

        iniciar();
        servicioDocumentos = new Servicio_Documentos();
        usuario = u;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        alinearColumnaDerecha();
//        setFormatoDecimalesVV();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        txtFechaEmision.setText(fechaEmision);
    }
    
//    private void setFormatoDecimalesVV() {
//        DefaultTableModel model = (DefaultTableModel) tblDescripcion.getModel();
//        for (int fila = 0; fila < model.getRowCount(); fila++) {
//            String valorVenta = String.valueOf(model.getValueAt(fila, 3));
//            String cadenaVV = Monetario.formatearMonetario(valorVenta, 2);
//            model.setValueAt(cadenaVV, fila, 3);
//            System.out.println("cadenaVV:" + cadenaVV);
//        }
//    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblDescripcion.getColumnModel().getColumn(3).setCellRenderer(tcr); // Valor de Venta
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void iniciar() throws ParseException {
//        System.out.println("iniciar()...");
        txtDireccionCliente.setEditable(false);
        txtFechaEmision.setEditable(false);

        txtRUC.setEditable(false);
        txtIGV1.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtSubTotal1.setEditable(false);

        txtTotal1.setEditable(false);

        if ( cliente != null ) {
            txtDireccionCliente.setText(cliente.getDireccion());
            txtNombreCliente.setText(cliente.getNombre());
            txtRUC.setText(cliente.getRuc());
        }

        Servicio_Sistema ss = new Servicio_Sistema();

        if ( nota.equals(NOTA_CREDITO) ) {
            sistema = ss.getSistemas_por_descripcion("Nota de Credito");
            
        } else {
            sistema = ss.getSistemas_por_descripcion("Nota de Debito");

        }
//        System.out.println("ULTIMO NUMERO DE la nota " + sistema + ":"  + sistema.getUltimonumero());
        txtNumeroNota.setText("" + (sistema.getUltimonumero() + 1));

        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtFechaEmision.setText(sdf.format(fecha));
        txtFechaImpresion.setDate(fecha);

        Servicio_TipoCambio s = new Servicio_TipoCambio();
        tipoCambio = new Tipocambio();
        tipoCambio = s.obtenerTipoCambio(df.parse(txtFechaEmision.getText()));
        tipoCambioVenta = tipoCambio.getValorventa();
        controlDAO = new ControlDAO(this);
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        igv = control.getImpuestoigv();
                
        if ( tabla4 != null ) {
            this.total = tabla4.TotalBruto();
            this.igvv = tabla4.Redondear(total * igv / (100 + igv));
            this.subTotal = total - igvv;
            
            String subTotalString = tabla4.RedondearConCerosDecimales(subTotal, 2);
            txtSubTotal1.setText(subTotalString);
            txtIGV1.setText("" + igvv);
            
            String tot = String.valueOf(new Util().Redondear2Decimales(total));
            txtTotal1.setText(Monetario.formatearMonetario(tot, 2));
            
        } else {
            this.total = tabla.TotalBruto();
            this.igvv = tabla.Redondear(total * igv / (100 + igv));
            this.subTotal = total - igvv;
            
            String subTotalString = tabla.RedondearConCeros(subTotal, 2);
            txtSubTotal1.setText(subTotalString);
            txtIGV1.setText("" + igvv);
            
            String tot = String.valueOf(new Util().Redondear2Decimales(total));
            txtTotal1.setText(Monetario.formatearMonetario(tot, 2));
        }
        
//        txtSubTotal1.setText(subTotalString);
//        txtIGV1.setText(tabla4.RedondearConCeros(igvv, 2));
//        txtTotal1.setText(tabla4.RedondearConCeros(total, 2));
    }

    public void cambiar() {
        if ( bandera_mon == true ) {
            txtlab1.setText("(US$):");
            txtlab2.setText("(US$):");
            txtlab3.setText("(US$):");
            
        } else {
            txtlab1.setText("(S/.):");
            txtlab2.setText("(S/.):");
            txtlab3.setText("(S/.):");
        }
    }

    private void Calcular_SubTotalDolares() {
        double subt = 0;
        
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            
            if ( tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1)) != null ) {
                Double s = (Double) tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1));
                subt = subt + s;
            }
        }

        valorVentaNota = subt;
        valorVentaNota = Math.round(valorVentaNota);
        txtSubTotal1.setText("" + valorVentaNota);

        igvNota = igv * 0.01 * valorVentaNota;
        igvNota = Math.round(igvNota);
        txtIGV1.setText("" + igvNota);
        totalNota = valorVentaNota + igvNota;
        totalNota = Math.round(totalNota);
        txtTotal1.setText("" + totalNota);
    }

    private void cambiar_Soles() {
        double subt = 0;
        
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            if ( tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1)) != null ) {
                Double s = (Double) tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1));
                subt = subt + s;
                subt = subt * tipoCambioVenta;
                subt = Math.round(subt);
                tblDescripcion.setValueAt(subt, i, (tblDescripcion.getColumnCount() - 1));
            }
        }
        Calcular_SubTotalDolares();
    }

    private void cambiar_Dolares() {
        double subt = 0;
        
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            if ( tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1)) != null ) {
                Double s = (Double) tblDescripcion.getValueAt(i, (tblDescripcion.getColumnCount() - 1));
                subt = subt + s;
                subt = subt / tipoCambioVenta;
                subt = Math.round(subt);
                tblDescripcion.setValueAt(subt, i, (tblDescripcion.getColumnCount() - 1));
            }
        }
        Calcular_SubTotalDolares();
    }

    public boolean TablaVacia() {
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            for ( int j = 0; j < tblDescripcion.getColumnCount(); j++ ) {
                if ( tblDescripcion.getValueAt(i, j) != null ) {
                    return false;
                }
            }
        }
        return true;
    }

    private Date CambiarStringToDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        strFecha = txtFechaEmision.getText();
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            
        } catch ( ParseException ex ) {
            ex.printStackTrace();
        }
        return fecha;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSubTotal1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtIGV1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDescripcion = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        radBtnContado = new javax.swing.JRadioButton();
        radBtnCredito = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtFechaImpresion = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtNumeroNota = new javax.swing.JTextField();
        txtlab1 = new javax.swing.JLabel();
        txtlab2 = new javax.swing.JLabel();
        txtlab3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("GENERAR NOTA ");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Documento"));

        jLabel6.setText("Nombre del Cliente:");

        jLabel7.setText("Dirección del Cliente:");

        jLabel8.setText("Fecha de Emisión:");

        jLabel9.setText("Número de R.U.C:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombreCliente)
                    .addComponent(txtDireccionCliente))
                .addGap(33, 33, 33))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción"));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Sub-Total");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("I.G.V ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Total");

        tblDescripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDescripcion);

        jLabel16.setText("Tipo de Pago:");

        buttonGroup2.add(radBtnContado);
        radBtnContado.setSelected(true);
        radBtnContado.setText("Contado");

        buttonGroup2.add(radBtnCredito);
        radBtnCredito.setText("Crédito");

        jLabel3.setText("Fecha de Impresión:");

        jLabel17.setText("Número de Nota:");

        txtlab1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab1.setText("(US$) : ");

        txtlab2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab2.setText("(US$) : ");

        txtlab3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab3.setText("(US$) : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumeroNota, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFechaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(radBtnCredito)
                                            .addComponent(radBtnContado))
                                        .addGap(284, 284, 284))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(469, 469, 469)))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtlab3)
                            .addComponent(txtlab2)
                            .addComponent(txtlab1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSubTotal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIGV1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlab1)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtIGV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlab2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlab3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radBtnContado)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radBtnCredito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Imprimir");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
//        System.out.println("tabla:" + tabla);
        
        if ( tabla != null ) {
            tabla.SetEditable(true);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private boolean nroDocumentoNotaExiste(String tipoTransaccion, String tipoDoc, String numSerie, String numDoc) {
        boolean existe = true;
        Cabeces cab = new Servicio_Cabeces().obtenerCabecera(tipoTransaccion, tipoDoc, numSerie, numDoc);
        
        if ( cab == null ) {
            existe = false;
            System.out.println("NO EXISTE");
        }
        return existe;
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if ( TablaVacia() == true ) {
            JOptionPane.showMessageDialog(null, "La tabla del Detalle de la Nota " + completarNotaDe + " esta vacía", 
                                         "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
//            int nroDocNotaCoD = Integer.parseInt(txtNumeroNota.getText());
            int nroDocumento = Integer.parseInt(txtNumeroNota.getText());
            String tipoTransaccion = "V";
            String numSerie = sistema.getSerie() + "";
            String numDocumento = String.valueOf(nroDocumento);
            
            String tipoDoc = "";
//            System.out.println("TIPO NOTA:" + this.nota);
            
            if ( NOTA_CREDITO.equals(this.nota) ) {
                tipoDoc = "03";
                
            } else if ( NOTA_DEBITO.equals(this.nota) ) {
                tipoDoc = "04";
            }
//            System.out.println("TIPO DOCUMENTO:" + tipoDocumento);
//            System.out.println("tipoTransaccion:" + tipoTransaccion);
//            System.out.println("tipoDocumento:" + tipoDoc);
//            System.out.println("numSerie:" + numSerie);
//            System.out.println("numDocumento:" + numDocumento);
            
            if ( !nroDocumentoNotaExiste(tipoTransaccion, tipoDoc, numSerie, numDocumento) ) {
//            if ( nroDocNotaCoD >= (sistema.getUltimonumero() + 1) ) {
                CabecesId cabecesId = new CabecesId();
                Cabeces c = new Cabeces();
                
                //Es nota de Credito
                if ( cabeces != null ) {
                    cabecesId.setTipotra(tipoTransaccion);
                    cabecesId.setTipodoc(tipoDoc);
                    cabecesId.setNrorserie(numSerie);
//                    cabecesId.setNrodocumento((sistema.getUltimonumero() + 1) + "");
                    cabecesId.setNrodocumento(nroDocumento + "");
                    
                    c.setNcFacbol(cabeces.getId().getNrodocumento());
                    c.setNcFacbolImporte(cabeces.getTotal());
                    c.setClientes(cabeces.getClientes());
                    c.setVendedores(cabeces.getVendedores());
                    c.setNcFacbol(nroFactura + "");
                    c.setNcFacbolImporte(this.total);
                    c.setEstado("");

                } //Es nota de débito
                else {
                    cabecesId.setTipotra(tipoTransaccion);
                    cabecesId.setTipodoc(tipoDoc);
                    cabecesId.setNrorserie(numSerie);
//                    cabecesId.setNrodocumento((sistema.getUltimonumero() + 1) + "");
                    cabecesId.setNrodocumento(nroDocumento + "");
                    c.setClientes(this.cliente);
                    c.setEstado("");
                    
                }
                c.setId(cabecesId);
                c.setTipocambio(tipoCambio);
                c.setTipocambio_1(tipoCambioVenta);

                if ( radBtnContado.isSelected() ) {
                    c.setTipoperacion("2");
                    
                } else {
                    c.setTipoperacion("1");
                }
                c.setValorventa(this.subTotal);
                c.setIgv(this.igvv);
                c.setTotal(this.total);
                c.setUsuarios(usuario);

                if ( bandera_mon == true ) {
                    c.setMoneda("2");
                    
                } else {
                    c.setMoneda("1");
                }

                Servicio_Documentos s = new Servicio_Documentos();
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea generar la nota " + completarNotaDe + "?", "CONFIRMACIÓN", 0);
                
                if ( verif == 0 ) {
                    ArrayList<Detallenota> listaDetalle = new ArrayList<>();
                    
                    for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
                        Detallenota d = new Detallenota();
                        d.setCabeces(c);
                        
                        if ( !tblDescripcion.getValueAt(i, 1).equals("") ) {
                            if ( tabla == null ) {
                                System.out.println("tabla es NULA");
                                String mm = String.valueOf(tblDescripcion.getValueAt(i, 1));
                                int cantidad = Integer.parseInt(mm);
                                
                                
                                System.out.println("Cantidad : " + cantidad);
                                
                                d.setCantidad(cantidad);

                                String idRep = (String) tblDescripcion.getValueAt(i, 0);
                                Servicio_Repuesto r = new Servicio_Repuesto();
                                Repuestos rp = r.getRepuesto(idRep);
                                d.setRepuestos(rp);

                                String valor = String.valueOf(tblDescripcion.getValueAt(i, 3));
                                d.setValor(Double.parseDouble(valor));

                                String descrip = (String) tblDescripcion.getValueAt(i, 2);
                                d.setDescripcion(descrip);
                                
                            } else {
                                System.out.println("tabla NO ES NULA");
                                d.setCantidad(0);
                                String ss = (String) tblDescripcion.getValueAt(i, 1);
                                d.setValor(Double.valueOf(ss));

                                String descrip = (String) tblDescripcion.getValueAt(i, 0);
                                d.setDescripcion(descrip);
                            }
                            listaDetalle.add(d);
                        }
                    }
                    
                    
                    int numDocActualEnBD = sistema.getUltimonumero();
                    boolean actualizarSistema = false;
//                    System.out.println("ultimo N° INGRESADO POR USUARIO:" + nroDocumento);
                    
                    if( nroDocumento >= numDocActualEnBD + 1 ) { // CORRELATIVO
                        numDocActualEnBD = nroDocumento; // ACTUALIZAR ultimo número en Sistema
                        sistema.setUltimonumero(numDocActualEnBD); // NUEVO N° DE DOCUMENTO(ÚLTIMO NÚMERO) A ACTUALIZAR EN SISTEMA
                        actualizarSistema = true;
                    }

                    String condicionPago = "";
                    if ( radBtnContado.isSelected() ) {
                        condicionPago = "CONTADO";
                        
                    } else if ( radBtnCredito.isSelected() ) {
                        condicionPago = "CREDITO";
                    }
                        
                    boolean registro = s.GuardarNotas(c, listaDetalle, sistema, actualizarSistema);
                    
//                    sistema.setUltimonumero(sistema.getUltimonumero() + 1);
                    if ( registro == true ) {
                        try {
                            JOptionPane.showMessageDialog(null, "Insertó un nueva nota " + completarNotaDe);
                            
                            String ST = txtSubTotal1.getText();
                            
                            Double IGV = Double.parseDouble(txtIGV1.getText());
                            Double TOT = Double.parseDouble(txtTotal1.getText());
                            Date fechaImpresion = txtFechaImpresion.getDate();
                            String fechaEmision = txtFechaEmision.getText();
                            
                            // Inicio de impresión de NC por Excel
                            /*
                            // Impresión por Jasper (1° Versión de M&D)
                            // Anterior impresión desde jasper para imprimir Nota de Crédito
                            Impresion impresion = new Impresion(controlDAO.Obtener_Objeto(1).getNrolineanc(),
                                                            ST, IGV, TOT, c.getId().getNrodocumento(),
                                                            fechaImpresion, fechaEmision);
                            
                            System.out.println("N° lista:" + listaDetalle.size());
                            if ( tabla == null ) {
                                impresion.imprime(1, c, listaDetalle);
                                
                            } else {
                                impresion.imprime(0, c, listaDetalle);
                            }
                            // Fin de impresión de NC por Excel
                            */
                            
                            
                            // Impresión de Nota de Crédito generando Excel
//                            System.out.println("TIPO DE NOTA:" + this.nota);
                            
                            // Se puede usar c.getId().getNrodocumento(),  en vez de nrodocumento en el 4° parámetro, pero como cadena, pq es entero.
                            
                            // Impresión por Excel (2° Versión de C&S)
                            if ( NOTA_CREDITO.equals(nota) ) {
                                
                                String nombreExcelNotaCredito = generarExcelNotaCredito(c, listaDetalle, ST, 
//                                                                                        IGV, TOT,
                                                                                        String.valueOf(nroDocumento),
                                                                                        fechaImpresion, fechaEmision,
                                                                                        this.tipoDocumento,
                                                                                        this.nroFactura,
                                                                                        this.concepto,
                                                                                        condicionPago,
                                                                                        modalidadNC);

                                Control control = new ControlDAO().Obtener_Objeto(1);
                                String rutaBDNotaCredito = control.getRutaprogramas();
                                String rutaExcelNotaCredito = rutaBDNotaCredito.replace("/", "\\");
                                String nombreExcelNC = rutaExcelNotaCredito + "\\" + nombreExcelNotaCredito;
                                new ImpresionExcel().imprimirDesdeExcel(nombreExcelNC);

                                // NUEVA FUNCIONALIDAD (04.07.2021) --> Generar Nota de Ingreso
                                // falta actualizar para imprimir nota de ingreso y actualizar los stocks
                                // List listaDetalles = d.getListaDetalleEs(c.getId());
                                
                                String glosa = "S/.";
                                if (c.getMoneda().equalsIgnoreCase("2")) {
                                    glosa = "US$";
                                }
                                System.out.println("glosa:" + glosa);
                                
                                System.out.println("this.cabeces ::: " + this.cabeces);
                                IU_Generacion_Nota_Ingreso_Imprimir generacionNotaIngreso = new IU_Generacion_Nota_Ingreso_Imprimir(
                                        c, listaDetalle, true, true, null, usuario, tblDescripcion, glosa, this, this.cabeces);
                                generacionNotaIngreso.setVisible(true);
                                
                            } else {
                                System.out.println("nota de debito, nroDocND:" + c.getId().getNrodocumento());
                                System.out.println("IMPRIMIR NOTA DE DÉBITO...");
//                                 Se puede usar c.getId().getNrodocumento(), en vez de nrodocumento en el 4° parámetro, pero como cadena, pq es entero.
                                
                                String nombreExcelNotaDebito = generarExcelNotaDebito(c, listaDetalle, ST, 
//                                                                                      IGV, TOT, 
//                                                                                      c.getId().getNrodocumento(),
                                                                                      String.valueOf(nroDocumento),
                                                                                      fechaImpresion, fechaEmision,
                                                                                      this.tipoDocumento,
                                                                                      this.nroFactura
//                                                                                      ,this.concepto
                                                                                      );

                                Control control = new ControlDAO().Obtener_Objeto(1);
                                String rutaBDNotaDebito = control.getRutaprogramas();
                                String rutaExcelNotaDebito = rutaBDNotaDebito.replace("/", "\\");
                                String nombreExcelND = rutaExcelNotaDebito + "\\" + nombreExcelNotaDebito;
                                new ImpresionExcel().imprimirDesdeExcel(nombreExcelND);
                            }
                            this.setVisible(false);
                            
                        } catch ( FileNotFoundException ex ) {
                            Logger.getLogger(IU_Generar_Notas.class.getName()).log(Level.SEVERE, null, ex);
                            
                        } catch ( BiffException ex ) {
                            Logger.getLogger(IU_Generar_Notas.class.getName()).log(Level.SEVERE, null, ex);
                            
                        } catch ( ParseException ex ) {
                            Logger.getLogger(IU_Generar_Notas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error no insertó nota " + completarNotaDe);
                        dispose();
                    }
                }
//            } else {
//                System.out.println("Ha cambiado el número de la nota " + completarNotaDe + "...");
//            }
            } else {
//                JOptionPane.showMessageDialog(null, "El número de nota " + completarNotaDe + " con " + nroDocumento + " ya se encuentra registrado");
                JOptionPane.showMessageDialog(null, "El número de nota " + completarNotaDe + " con " + nroDocumento + " ya se encuentra registrado", "Existente", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private String generarExcelNotaCredito(Cabeces c, ArrayList<Detallenota> detalle, String subTotal, 
//                                           double igv, double total,
                                           String nroDocumento, Date fechaImpresion, String fechaEmision,
                                           String tipoDocumento, String nroFactura, String concepto,
                                           String condicionPago,
                                           int modalidadNC)
                                           throws FileNotFoundException, BiffException, ParseException {
        servicio_Excel = new Servicio_Excel(tblDescripcion, this);
        DatosNC nota = new DatosNC();
        nota = llenarDatosNotaCredito(c, nota, subTotal, 
//                                      igv, total, 
                                      nroDocumento, fechaImpresion, fechaEmision, tipoDocumento, nroFactura, concepto,
                                      condicionPago,
                                      modalidadNC);
        servicio_Excel.Exportar_Excel_CabecDet_NC(nota, detalle);
        return nota.getNombreExcelNotaCredito();
    }
    
    private String generarExcelNotaDebito(Cabeces c, ArrayList<Detallenota> detalle, String subTotal, 
//                                          double igv, double total,
                                          String nroDocND, Date fechaImpresion, String fechaEmision,
                                          String tipoDocumento, String nroFactura
//                                          , String concepto
                                          )
                                          throws FileNotFoundException, BiffException, ParseException {
        servicio_Excel = new Servicio_Excel(tblDescripcion, this);
        DatosND nota = new DatosND();
        nota = llenarDatosNotaDebito(c, nota, subTotal, 
//                                     igv, total, 
                                     nroDocND, fechaImpresion, fechaEmision, tipoDocumento, nroFactura);
        servicio_Excel.Exportar_Excel_CabecDet_ND(nota, detalle);
        return nota.getNombreExcelNotaDebito();
    }
    
    private static String formatearNotaCredito(String numSerie, String numCorrelativo) {
        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, 8);
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    private DatosNC llenarDatosNotaCredito(Cabeces c, DatosNC nota, String subTotal, 
//                                           double igv, double total,
                                           String nroDocumento, Date fechaImpresion, String fechaEmision,
                                           String tipoDocumento, String nroFactura, String concepto,
                                           String condicionPago,
                                           int modalidadNC) {
        
        Sistema sistema = new SistemaDAO().obtener_por_nombre("NOTA DE CREDITO");
        String nroSerie = String.valueOf(sistema.getSerie());
        
        if ( !"null".equals(nroDocumento) ) {
            String numeroNC = formatearNotaCredito(nroSerie, nroDocumento);
            nota.setNumeroNC(numeroNC);
        }
        String nombreExcelNC = "\\NotaCredito_" + nroSerie + "_" + nroDocumento + ".xls";
        
        nota.setModalidadNC(modalidadNC);
        nota.setCondicionPago(condicionPago);
        
        nota.setConcepto(concepto);
        nota.setNroSerie(nroSerie);
        nota.setNroDocumento(nroDocumento);
        nota.setNombreExcelNotaCredito(nombreExcelNC);
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        nota.setRutaProgramas(control.getRutaprogramas());
        
        nota.setTipoDocumento(tipoDocumento);
        nroFactura = formatearNotaCredito(nroSerie, nroFactura);
        nota.setNroFactura(nroFactura);
        
        nota.setFechaEmision(fechaEmision);
        String nombreEmpresa = c.getClientes().getNombre();
        nota.setClienteNombre(nombreEmpresa);
        nota.setClienteRUC(c.getClientes().getRuc());
        String direccion = c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion();
//        nota.setClienteDireccion(truncarDireccion(nombreEmpresa, direccion));
        nota.setClienteDireccion(truncarDireccion(nombreEmpresa, direccion));
        
        String fechaNotaCredito = Fecha.getDia(fechaImpresion) + "/" + 
                                  Fecha.getMesNumeros(fechaImpresion) + "/" + 
                                  Fecha.getAnio(fechaImpresion);
        nota.setFechaNC(fechaNotaCredito);
        
        String moneda = ( "2".equals(c.getMoneda()) ) ? "US$" : "S/.";
        nota.setMoneda(moneda);
        nota.setSubtotal(subTotal);
        nota.setImpuesto(String.valueOf(control.getImpuestoigv()));
        nota.setIgv(txtIGV1.getText());
        
        String totalEnLetras = Billete.BilleteX(c.getTotal(), c.getMoneda().equals("2") ? " DOLARES AMERICANOS" : " NUEVOS SOLES"); // SON:
        nota.setTotalEnLetras(totalEnLetras);
        
        Monetario.formatearMonetario(nroSerie, numFilaNota);
      
        String tot = String.valueOf(new Util().Redondear2Decimales(c.getTotal()));
        nota.setTotal(Monetario.formatearMonetario(tot, 2));
        String dia, mesLetras, año2DigUlt, año4DigUlt;
        
        if ( fechaEmision == null ) {
            dia = Fecha.getDia(c.getTipocambio().getFecha());
            mesLetras = Fecha.getMes(c.getTipocambio().getFecha());
            año2DigUlt = Fecha.getAnio(c.getTipocambio().getFecha()).substring(2, 4);
            año4DigUlt = Fecha.getAnio(c.getTipocambio().getFecha()).substring(0, 4);

        } else {
//            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//            fechaCadena = formateador.format(fechaEmision);
//            Date fechaE = new Date(fechaCadena);
//            System.out.println("fechaEmision:" + fechaEmision);
            String fe[] = fechaEmision.split("/");
            dia = fe[0];
            mesLetras = Fecha.getMesLetras(Integer.parseInt(fe[1]));
            año2DigUlt = fe[2].substring(2, 4);
            año4DigUlt = fe[2].substring(0, 4);
//            dia = Fecha.getDia(fechaE);
//            mesLetras = Fecha.getMes(fechaE);
//            año2DigUlt = Fecha.getAnio(fechaE).substring(2, 4);
        }
        nota.setDia(dia);
        nota.setMesLetras(mesLetras);
        nota.setAño2DigUlt(año2DigUlt);
        nota.setAño4DigUlt(año4DigUlt);
        return nota;
    }
    
    private String truncarDireccion(String nombreEmpresa, String direccion) {
        Clientes cliente = new ClienteDAO().getxNombreDireccion(nombreEmpresa, direccion);
        
        if ( !"null".equals(direccion) ) {
            if ( direccion.length() > NUM_CARACT_DIR_TRUNCA ) {
                direccion = direccion.substring(0,NUM_CARACT_DIR_TRUNCA);
            }
        }
//        direccion = direccion + " - " + cliente.getPlaza();
        return direccion;
    }
    
    private DatosND llenarDatosNotaDebito(Cabeces c, DatosND nota, String subTotal, 
//                                          double igv, double total,
                                          String nroDocND, Date fechaImpresion, String fechaEmision,
                                          String tipoDocumento, String nroFactura) {
        
        Sistema sistema = new SistemaDAO().obtener_por_nombre("NOTA DE DEBITO");
        String nroSerieND = String.valueOf(sistema.getSerie());
        System.out.println("nroSerieND:" + nroSerieND);
        System.out.println("nroDocND:" + nroDocND);
        
        if ( !"null".equals(nroDocND) ) {
            String numeroND = formatearNotaCredito(nroSerieND, nroDocND);
            nota.setNumeroND(numeroND);
        }
        String nombreExcelND = "\\NotaDebito_" + nroSerieND + "_" + nroDocND + ".xls";
        
        nota.setNroSerie(nroSerieND);
        nota.setNumeroND(nroDocND);
        nota.setNombreExcelNotaDebito(nombreExcelND);
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        nota.setRutaProgramas(control.getRutaprogramas());
        
        nota.setTipoDocumento(tipoDocumento);
        nroFactura = formatearNotaCredito(nroSerieND, nroFactura);
        nota.setNroDocFactura(nroFactura);
        
        nota.setFechaEmision(fechaEmision);
        String nombreEmpresa = c.getClientes().getNombre();
        nota.setClienteNombre(nombreEmpresa);
        nota.setClienteRUC(c.getClientes().getRuc());
        String direccion = c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion();
//        nota.setClienteDireccion(truncarDireccion(nombreEmpresa, direccion));
        nota.setClienteDireccion(truncarDireccion(nombreEmpresa, direccion));
        
        String fechaNotaDebito = Fecha.getDia(fechaImpresion) + "/" + 
                                 Fecha.getMesNumeros(fechaImpresion) + "/" + 
                                 Fecha.getAnio(fechaImpresion);
        nota.setFechaEmision(fechaNotaDebito);
        
        String moneda = ( "2".equals(c.getMoneda()) ) ? "US$" : "S/.";
        nota.setMoneda(moneda);
        nota.setSubtotal(subTotal);
        nota.setImpuesto(String.valueOf(control.getImpuestoigv()));
        nota.setIgv(txtIGV1.getText());
        
        String totalEnLetras = Billete.BilleteX(c.getTotal(), c.getMoneda().equals("2") ? " DOLARES AMERICANOS" : " NUEVOS SOLES"); // SON:
        nota.setTotalEnLetras(totalEnLetras);
        
        Monetario.formatearMonetario(nroSerieND, numFilaNota);
      
        String tot = String.valueOf(new Util().Redondear2Decimales(c.getTotal()));
        nota.setTotal(Monetario.formatearMonetario(tot, 2));
        String dia, mesLetras, año1DigUlt; //, año2DigUlt, año4DigUlt;
        
        if ( fechaEmision == null ) {
            dia = Fecha.getDia(c.getTipocambio().getFecha());
            mesLetras = Fecha.getMes(c.getTipocambio().getFecha());
            año1DigUlt = Fecha.getAnio(c.getTipocambio().getFecha()).substring(3, 4);
//            año2DigUlt = Fecha.getAnio(c.getTipocambio().getFecha()).substring(2, 4);
//            año4DigUlt = Fecha.getAnio(c.getTipocambio().getFecha()).substring(0, 4);

        } else {
//            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//            fechaCadena = formateador.format(fechaEmision);
//            Date fechaE = new Date(fechaCadena);
//            System.out.println("fechaEmision:" + fechaEmision);
            String fe[] = fechaEmision.split("/");
            dia = fe[0];
            mesLetras = Fecha.getMesLetras(Integer.parseInt(fe[1]));
            año1DigUlt = fe[2].substring(3, 4);
//            año2DigUlt = fe[2].substring(2, 4);
//            año4DigUlt = fe[2].substring(0, 4);
//            dia = Fecha.getDia(fechaE);
//            mesLetras = Fecha.getMes(fechaE);
//            año2DigUlt = Fecha.getAnio(fechaE).substring(2, 4);
        }
        nota.setDia(dia);
        nota.setMesLetras(mesLetras);
        nota.setAño1DigUlt(año1DigUlt);
        return nota;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton radBtnContado;
    private javax.swing.JRadioButton radBtnCredito;
    private javax.swing.JTable tblDescripcion;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtFechaEmision;
    public com.toedter.calendar.JDateChooser txtFechaImpresion;
    private javax.swing.JTextField txtIGV1;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroNota;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtSubTotal1;
    private javax.swing.JTextField txtTotal1;
    private javax.swing.JLabel txtlab1;
    private javax.swing.JLabel txtlab2;
    private javax.swing.JLabel txtlab3;
    // End of variables declaration//GEN-END:variables
}