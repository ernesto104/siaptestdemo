package Presentacion.Notas;

import Entidades.Cabeces;
import Entidades.Control;
import Entidades.Detallees;
import Entidades.Detallenota;
import Entidades.Operaciones;
//import static Entidades.Otros.Constante.ING_DEVOLUCION;
import static Entidades.Otros.Constante.ING_REGULARIZACION;
//import static Entidades.Otros.Constante.TIPODOC_ING_DEVOLUCION;

//import static Entidades.Otros.Constante.ING_REGULARIZACION;
import static Entidades.Otros.Constante.TIPODOC_ING_REGULARIZACION;

import Entidades.Otros.ImpresionExcel;
import Entidades.Otros.Monetario;
import Entidades.Repuestos;
import Entidades.Sistema;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Mantenimiento.DetallesDAO;
import Presentacion.Anulacion.DetalleAnulacion;
import Presentacion.IngresoSalidas.DetalleIngSal;
import Servicios.IngresoSalidas.Servicio_IngresoSalida;
import Servicios.Servicio_Sistema;
import Servicios.TipoMensaje;
import java.awt.FontFormatException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import Servicios.Servicio_Documentos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Operaciones;
import Servicios.Servicio_Repuesto;
import Servicios.Util;

public class IU_Generacion_Nota_Ingreso_Imprimir extends javax.swing.JFrame {

    Cabeces cabecesNotaCredito;
    Cabeces cabecesFactura;
    boolean movimient;
    boolean aument;
    String tipo;
    List<Detallenota> detallesNotaCredito;
    
    Usuarios usuario;
    TipoMensaje tm;
    JTable tabDet;
    int nroIngreso;
    int nroSalida;    
    
    String glosa;
    IU_Generar_Notas iugenNotaIngreso;
    Servicio_Sistema ss;
    Servicio_Sistema ser_sis;
    
    Util util;

    public IU_Generacion_Nota_Ingreso_Imprimir(Cabeces cabNotaCredito, List<Detallenota> detallesNotaCredito, boolean mov, boolean a, 
                                                String t, Usuarios usuario, JTable tabDet, String glosa, IU_Generar_Notas iugenNotaIngreso, 
                                                Cabeces cabecesFactura) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuario = usuario;
        this.cabecesNotaCredito = cabNotaCredito;
        this.cabecesFactura = cabecesFactura;
        this.movimient = mov;
        this.tipo = t;
        this.aument = a;
        this.detallesNotaCredito = detallesNotaCredito;
        this.tabDet = tabDet;
        this.glosa = glosa;
        this.iugenNotaIngreso = iugenNotaIngreso;
        util = new Util();
        
        iniciar();        
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
    }

    public void iniciar() {
        tm = new TipoMensaje();
        txtImporteDocument.setEditable(false);
        txtNroFacturaRelac.setEditable(false);
        txtTipoDocument.setEditable(false);

        if ( cabecesFactura != null ) {
            String id = Monetario.formatearMonetario(String.valueOf(cabecesFactura.getTotal()), 2);
            
            txtImporteDocument.setText(id);
            txtNroFacturaRelac.setText("" + cabecesFactura.getId().getNrodocumento());
/*
            if ( cabeces.getId().getTipodoc().equals("01") ) {*/
                txtTipoDocument.setText("Factura");
                /*
            } else {
                if ( cabeces.getId().getTipodoc().equals("02") ) {
                    txtTipoDocument.setText("Boleta");
                    
                } else {
                    if ( cabeces.getId().getTipodoc().equals("03") ) {*/
                        //txtTipoDocument.setText("Nota de Crédito");
                    /*    
                    } else {
                        if ( cabeces.getId().getTipodoc().equals("05") ) {
                            txtTipoDocument.setText("Guía de Remisión");   
                        }
                    }
                }
            }*/
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMotivo = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTipoDocument = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNroFacturaRelac = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtImporteDocument = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPRIMIR NOTA DE INGRESO");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Motivo de Devolución"));

        jLabel2.setText("Ingrese motivo:");

        txtAreaMotivo.setColumns(20);
        txtAreaMotivo.setRows(5);
        txtAreaMotivo.setNextFocusableComponent(btnImprimir);
        txtAreaMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaMotivoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtAreaMotivo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));

        jLabel3.setText("Tipo de Documento:");

        jLabel4.setText("Número de factura relacionada:");

        jLabel5.setText("Importe de Documento:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtImporteDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTipoDocument, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtNroFacturaRelac))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTipoDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNroFacturaRelac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtImporteDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnImprimir.setText("Imprimir");
        btnImprimir.setNextFocusableComponent(btnSalir);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(txtAreaMotivo);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>                        

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.setVisible(false);
    }                                        

    public String validar() {
        if ( txtAreaMotivo.equals("") ) {
            //JOptionPane.showMessageDialog(null, "Ingrese el motivo", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
            return "Ingrese el motivo de la Nota de Ingreso";
        } else {
            return tm.VALIDO;
        }
    }
    
    private Detallees filtrarSoloDetalleesConNotaCredito(Detallenota detNota, List<Detallees> listaDetalleEs) {
        System.out.println("detNota:::idRepuesto:::" + detNota.getRepuestos().getId().getIdrepuesto());
        for (Detallees det : listaDetalleEs) {
            System.out.println("DET.idRepuesto:" + det.getRepuestos().getId().getIdrepuesto());
            if (det.getRepuestos().getId().getIdrepuesto() == detNota.getRepuestos().getId().getIdrepuesto()) {
                return det;
            }
        }
        return null;
    }
    
    
    
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String validacion = validar();
        boolean band = false;
        
        if ( validacion.equals(tm.VALIDO) ) {
            ss = new Servicio_Sistema();
         // Sistema sis = ss.Obtener_Sistema_TipoDoc("10"); // nro documento de ing. x anulacion (o DEVOLUCIÓN) en tabla Sistema
            Sistema sis = ss.Obtener_Sistema_TipoDoc("11"); // nro documento de ing. x anulacion (o DEVOLUCIÓN) en tabla Sistema
            nroIngreso = sis.getUltimonumero() + 1;
            
            ArrayList<Detallees> listaDetalles = null;
            DetallesDAO detalleEsDAO = new DetallesDAO();
            List<Detallees> listaDetalleEs = detalleEsDAO.getListaDetalleEs(cabecesFactura.getId());
            
            if ( cabecesFactura != null ) {
                Servicio_Operaciones servOper = new Servicio_Operaciones();
                Operaciones op = servOper.obtenerOperacion(9);
                
                listaDetalles = new ArrayList<Detallees>();
                
                for (Detallenota detNota : detallesNotaCredito) {
                    String idRep = String.valueOf(detNota.getRepuestos().getId().getIdrepuesto());
                    System.out.println("idRep >>" + idRep);
                    Detallees detFactura = filtrarSoloDetalleesConNotaCredito(detNota, listaDetalleEs);
                    
                    if (detFactura != null) {
                        Detallees detNotaIngreso = new Detallees();
                        detNotaIngreso.setRepuestos(detNota.getRepuestos());
                        detNotaIngreso.setCantpedida(detNota.getCantidad());
                        detNotaIngreso.setCantentregada(detNota.getCantidad());
                        
                        Double precioLista = (Double) (detNota.getValor()/ detNota.getCantidad());
                        String plString = formatearCeros(String.valueOf(util.Redondear2Decimales(precioLista)), 2);
                        detNotaIngreso.setPreciolista(Double.parseDouble(plString));
                        
                        detNotaIngreso.setDescuento1(0.00);
                        detNotaIngreso.setDescuento2(0.00);
                        detNotaIngreso.setDescuento3(0.00);
                        detNotaIngreso.setDescuento4(0.00);
                        detNotaIngreso.setOperaciones(op);
                        
                        detNotaIngreso.setMotivo("(DEV)" + txtAreaMotivo.getText());
                        Date fecha = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        try {
                            detNotaIngreso.setNroingreso(nroIngreso);
                            fecha = sdf.parse(sdf.format(fecha));

                        } catch (ParseException ex) {
                            Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        detNotaIngreso.setFecha(fecha);
                        
                        listaDetalles.add(detNotaIngreso);
                    }
                }
                
                System.out.println(">> listaDetalles:" + listaDetalles);
                
                if (listaDetalles != null) {
                    System.out.println("listaDetalles nro elementos:" + listaDetalles.size());
                }

                Servicio_IngresoSalida serv = new Servicio_IngresoSalida();
                if (serv.guardarIngresoPorNCDevolucion(listaDetalles /*, detallesNotaCredito*/)) {
                    try {
                        System.out.println("nroIngreso ULTIMO a actualizar en BD:" + nroIngreso);
                        sis.setUltimonumero(nroIngreso);
                        ss.actualizarSistemas(sis);
                        JOptionPane.showMessageDialog(null, "Se generó nota de ingreso", "GENERACIÓN DE NOTA DE INGRESO", JOptionPane.INFORMATION_MESSAGE);
                        imprimirReporte(detallesNotaCredito);
                        dispose();
                        
                    } catch (BiffException ex) {
                        Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (WriteException ex) {
                        Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FontFormatException ex) {
                        Logger.getLogger(IU_Generacion_Nota_Ingreso_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No generó nota de ingreso", "GENERACIÓN DE NOTA DE INGRESO", JOptionPane.ERROR);
                }
            }
        } else {
            tm.Error(validacion);
        }
    }                                           

    // Nueva forma de Imprimir Solo NOTA DE INGRESO
    private void imprimirReporte(List<Detallenota> detallesNotaCredito) throws BiffException, IOException, FileNotFoundException, WriteException, ParseException, FontFormatException {
        Control control = new ControlDAO().Obtener_Objeto(1);
        String rutaBDIng = control.getRutaprogramas();
        
        String rutaExcel = rutaBDIng.replace("/", "\\");
        String excelIngReg = "", excelIngCompraLocal = "";
        
        Map<String, Object> parametros = SetParametros(control);
        ArrayList<DetalleIngSal> lista = SetLista(detallesNotaCredito);
        
        // ING_REGULARIZACION : 2
        excelIngReg = new Servicio_Excel(tabDet, this).generarExcelDocumento(parametros, lista, ING_REGULARIZACION);
        //excelIngReg = new Servicio_Excel().generarExcelDocumento(parametros, lista, ING_REGULARIZACION);
        excelIngReg = rutaExcel + "\\" + excelIngReg;
        new ImpresionExcel().imprimirDesdeExcel(excelIngReg);
        dispose();
    }
    
    private Map SetParametros(Control control) {
        Map<String, Object> mapa = new HashMap<>();
        String ultnumero = "";
        ser_sis = new Servicio_Sistema();
        
//        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        String empresa = control.getNombrempresa();
        mapa.put("titulo", "INGRESOS AL ALMACEN");
        mapa.put("empresa", empresa);
        mapa.put("transaccion", "INGRESO POR AJUSTE");
        mapa.put("rutaProgramas", control.getRutaprogramas());
        mapa.put("rutaTemplate", control.getRutareportes());

        ser_sis = new Servicio_Sistema();
        Sistema sistema = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_REGULARIZACION);
        ultnumero = String.valueOf(sistema.getUltimonumero());
        System.out.println("ultimo num:" + ultnumero);
        mapa.put("nroDoc", ultnumero);
        mapa.put("motivo", txtAreaMotivo.getText());
        mapa.put("glosa", glosa);
        mapa.put("total", formatearCeros(String.valueOf(util.Redondear2Decimales(cabecesNotaCredito.getTotal())), 2));
        return mapa;
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    private ArrayList SetLista(List<Detallenota> detallesNotaCredito) {
        ArrayList<DetalleIngSal> lista = new ArrayList<>();
        
        for (Detallenota detNota : detallesNotaCredito) {
            String nroParte = String.valueOf(detNota.getRepuestos().getCodrepuesto());
            
            String codSecundario = detNota.getRepuestos().getCodigoseg();
            String codSec = ( "null".equals(codSecundario) ? "" : codSecundario);
            String descripcion = detNota.getRepuestos().getDescripcion();
            String cantidad = String.valueOf(detNota.getCantidad());

            String precioLista = formatearCeros(String.valueOf(util.Redondear2Decimales(detNota.getValor()/detNota.getCantidad())), 2); // redondear a 2 decimales 
            String total = String.valueOf(detNota.getValor());
            
            String modelo = detNota.getRepuestos().getDescrmodelo();
            String descrModelo = ( "null".equals(modelo) ? "" : modelo);

            lista.add(new DetalleIngSal(nroParte, codSec, descripcion, cantidad, precioLista, total, descrModelo));
        }
        return lista;
    }
    
    // Anterior Impresion (por Jasper)
    public void Imprimir() throws BiffException, IOException, FileNotFoundException, WriteException, ParseException, FontFormatException {
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        String rutaBDSalXAnulacion = control.getRutaprogramas();
        String rutaExcel = rutaBDSalXAnulacion.replace("/", "\\");
        
        /*
        ArrayList lista = SetLista();
        Map<String, Object> parametros = SetParametros(control);
        */
        /// FALTA AJUSTAR PARA QUE FUNCIONE IMPRESION DE NOTA DE INGRESO POR NOTA DE CREDITO (DEVOLUCION): LEDIS
        /*
        String excelSalXAnulacion = new Servicio_Excel(iuad.tblDetalle, this).generarExcelSalXAnulacion(parametros, lista);
        excelSalXAnulacion = rutaExcel + "\\" + excelSalXAnulacion;
        new ImpresionExcel().imprimirDesdeExcel(excelSalXAnulacion);
        dispose();
                */
    }
    
    private ArrayList SetLista() {
        System.out.println("SetLista.......................");
        ArrayList<DetalleAnulacion> lista = new ArrayList<>();
        for ( int i = 0; i < tabDet.getRowCount(); i++ ) {
            String numeroParte = String.valueOf(tabDet.getValueAt(i, 0));
            
            Servicio_Repuesto r = new Servicio_Repuesto();
            Repuestos repuesto = r.getRepuesto(numeroParte);
            
            String linea = repuesto.getEquipos().getDescripcion();
            String descripcion = String.valueOf(tabDet.getValueAt(i, 2));
            String cantidad = String.valueOf(tabDet.getValueAt(i, 1));
            String precioLista = String.valueOf(repuesto.getPreciolista());
            String total = String.valueOf(tabDet.getValueAt(i, 3));
            String modelo = repuesto.getDescrmodelo();
            //String linea = String.valueOf(tabDet.getValueAt(i, 0));
            //String nroParte = String.valueOf(tabDet.getValueAt(i, 1));
            //String descripcion = String.valueOf(tabDet.getValueAt(i, 2));
            //String cantidad = String.valueOf(tabDet.getValueAt(i, 3));
            //String precioLista = String.valueOf(tabDet.getValueAt(i, 4));
            //String total = String.valueOf(tabDet.getValueAt(i, 5));
            //String modelo = String.valueOf(tabDet.getValueAt(i, 6));
            lista.add(new DetalleAnulacion(linea, numeroParte, descripcion, cantidad, precioLista, total, modelo));
        }
        return lista;
    }

    /*
    private Map SetParametros(Control control) {
        System.out.println("SetParametros........");
        Map<String, Object> mapa = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        mapa.put("tipoDoc", txtTipoDocument.getText());
        mapa.put("titulo", "Ingreso al Almacén");
        mapa.put("transaccion", "Nota de Ingreso"); // Anulación de Documento
        mapa.put("nroIngreso", nroSalida + "");
        mapa.put("rutaProgramas", control.getRutaprogramas());
        mapa.put("empresa", control.getNombrempresa());
        mapa.put("glosa", glosa);
        
        if ( cabecesFactura != null ) {
//            mapa.put("femision", sdf.format(cabeces.getTipocambio().getFecha()));
            Date fechaHora = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formatoFecha.format(fechaHora);
//            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
//            String hora = formatoHora.format(fechaHora);
//            String fechayHora = fecha + " - " + hora;
            mapa.put("femision", fecha);
            mapa.put("nroDoc", cabecesFactura.getId().getNrodocumento());
            
        } else {
            mapa.put("femision", "");
            mapa.put("nroDoc", "");
        }
        mapa.put("motivo", txtAreaMotivo.getText());
        mapa.put("total", txtImporteDocument.getText());
        return mapa;
    }*/
    
    private void txtAreaMotivoKeyTyped(java.awt.event.KeyEvent evt) {                                       
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            btnImprimir.requestFocus();
        }
    }                                      

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea txtAreaMotivo;
    private javax.swing.JTextField txtImporteDocument;
    private javax.swing.JTextField txtNroFacturaRelac;
    private javax.swing.JTextField txtTipoDocument;
    // End of variables declaration                   
}