package Presentacion.Anulacion;

import Entidades.Cabeces;
import Entidades.Control;
import Entidades.Detallees;
import Entidades.Operaciones;
import Entidades.Otros.ImpresionExcel;
import Entidades.Otros.Monetario;
import Entidades.Pagos;
import Entidades.Sistema;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Presentacion.Anulacion.DetalleAnulacion;
import Servicios.Servicio_Banco;
import Servicios.Servicio_DetalleEs;
import Servicios.Servicio_Documentos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Pagos;
import Servicios.Servicio_Sistema;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Impresion;
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

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Anulacion_Imprimir extends javax.swing.JFrame {

    Cabeces cabeces;
    boolean movimient;
    boolean aument;
    String tipo;
    List<Detallees> lista;
    
    Usuarios usuario;
    TipoMensaje tm;
    JTable tabDet;
    int nroIngreso;
    
    String glosa;
    IU_Anulacion_Documento iuad;

    public IU_Anulacion_Imprimir(Cabeces c, List<Detallees> list, boolean mov, boolean a, String t,
                                 Usuarios usuario, JTable tabDet, String glosa, IU_Anulacion_Documento iuad) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.usuario = usuario;
        this.cabeces = c;
        this.movimient = mov;
        this.tipo = t;
        this.aument = a;
        this.lista = list;
        this.tabDet = tabDet;
        this.glosa = glosa;
        this.iuad = iuad;
        
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

        if ( cabeces != null ) {
            String id = Monetario.formatearMonetario(String.valueOf(cabeces.getTotal()), 2);
            txtImporteDocument.setText(id);
            txtNroFacturaRelac.setText("" + cabeces.getId().getNrodocumento());

            if ( cabeces.getId().getTipodoc().equals("01") ) {
                txtTipoDocument.setText("Factura");
                
            } else {
                if ( cabeces.getId().getTipodoc().equals("02") ) {
                    txtTipoDocument.setText("Boleta");
                    
                } else {
                    if ( cabeces.getId().getTipodoc().equals("03") ) {
                        txtTipoDocument.setText("Nota de Crédito");
                        
                    } else {
                        if ( cabeces.getId().getTipodoc().equals("05") ) {
                            txtTipoDocument.setText("Guía de Remisión");   
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        jLabel1.setText("IMPRIMIR DOCUMENTO");

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

        btnImprimir.setText("<html>Anular &<br/>Imprimir</html>");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    public String validar() {
        if ( txtAreaMotivo.equals("") ) {
            //JOptionPane.showMessageDialog(null, "Ingrese el motivo", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
            return "Ingrese el motivo de Anulación";
        } else {
            return tm.VALIDO;
        }
    }
    
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        String validacion = validar();
        boolean band = false;
        
        if ( validacion.equals(tm.VALIDO) ) {
            Servicio_Sistema ss = new Servicio_Sistema();
            Sistema sis = ss.Obtener_Sistema_TipoDoc("10"); // nro documento de ing. x anulacion (o DEVOLUCIÓN) en tabla Sistema
            nroIngreso = sis.getUltimonumero() + 1;
//        if (txtAreaMotivo.equals("")) {
//            JOptionPane.showMessageDialog(null, "Ingrese el motivo", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//        } else {
            if ( cabeces != null ) {
                Servicio_DetalleEs servDe = new Servicio_DetalleEs();
                ArrayList<Detallees> lista = servDe.Listar_DetalleEs_PorCabec(cabeces.getId());
                cabeces.setEstado("ANULADO");

                for ( int i = 0; i < lista.size(); i++ ) {
                    Detallees d = lista.get(i);
                    d.setMotivo(txtAreaMotivo.getText());
                    Date fecha = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    
                    try {
                        d.setNroingreso(nroIngreso);
                        fecha = sdf.parse(sdf.format(fecha));
                        
                    } catch (ParseException ex) {
                        Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    d.setFecha(fecha);
                    Operaciones op = lista.get(i).getOperaciones();
                    op.setIdoperacion(2);
                    d.setOperaciones(op);
                    lista.set(i, d);
                }

                Servicio_Documentos serv = new Servicio_Documentos();
                if ( serv.anularFactura(cabeces, lista, movimient, aument) ) {
                    sis.setUltimonumero(nroIngreso);
                    ss.actualizarSistemas(sis);
                    
                    if ( !cabeces.getTipoperacion().equals("1") ) {
                        Servicio_Pagos sp = new Servicio_Pagos();
                        Servicio_Banco sb = new Servicio_Banco(null);
                        Pagos pag = new Pagos();
                        pag.setCabeces(cabeces);
                        pag.setFecha(new Date());
                        pag.setImporte(cabeces.getTotal());
                        pag.setForma("E");
                        pag.setUsuarios(usuario);
                        pag.setBancos(sb.getBanco_Nombre("ANULACION"));
                        pag.setNrocheque("A");
                        sp.GuardarPagos(pag);
                    }
                    JOptionPane.showMessageDialog(null, "Se anuló el documento", "ANULACIÓN DE DOCUMENTO", JOptionPane.INFORMATION_MESSAGE);
                    band = true;
                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se anuló el documento", "ANULACIÓN DE DOCUMENTO", JOptionPane.ERROR);
                }
                
            } else {
                Servicio_DetalleEs servDe = new Servicio_DetalleEs();

                for ( int i = 0; i < lista.size(); i++ ) {
                    Detallees d = lista.get(i);
                    d.setMotivo(txtAreaMotivo.getText());
                    d.setNroingreso(nroIngreso);
                    Date fecha = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    
                    try {
                        fecha = sdf.parse(sdf.format(fecha));
                    } catch (ParseException ex) {
                        Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    d.setFecha(fecha);
                    lista.set(i, d);
                }
                Servicio_Documentos serv = new Servicio_Documentos();
                
                if ( serv.anularFactura(cabeces, (ArrayList<Detallees>) lista, movimient, aument) ) {
                    JOptionPane.showMessageDialog(null, "Se anulo el documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    band = true;
                    sis.setUltimonumero(nroIngreso);
                    ss.actualizarSistemas(sis);
                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error: No se anuló el documento", "ANULACIÓN DE DOCUMENTO", JOptionPane.ERROR);
                }
            }

            if ( band ) {
                try {
                    System.out.println("anular & imprimir Ing. x anulacion");
                    Imprimir();
                } catch (BiffException ex) {
                    Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (IOException ex) {
                    Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (WriteException ex) {
                    Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (ParseException ex) {
                    Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (FontFormatException ex) {
                    Logger.getLogger(IU_Anulacion_Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }       
        } else {
            tm.Error(validacion);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    public void Imprimir() throws BiffException, IOException, FileNotFoundException, WriteException, ParseException, FontFormatException {
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        String rutaBDIngXAnulacion = control.getRutaprogramas();
        String rutaExcel = rutaBDIngXAnulacion.replace("/", "\\");
        
        ArrayList lista = SetLista();
        Map<String, Object> parametros = SetParametros(control);
        System.out.println("NUEVO");
//        String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//        Servicio_Impresion.exporta(20, lista, parametros, "plantillas/Ingresos_Y_Salidas.pdf");
        
        String excelIngXAnulacion = new Servicio_Excel(iuad.tblDetalle, this).generarExcelIngXAnulacion(parametros, lista);
//        String excelIngXAnulacion = new Servicio_Excel(tabDet, this).generarExcelIngXAnulacion(parametros, lista);
        excelIngXAnulacion = rutaExcel + "\\" + excelIngXAnulacion;
        new ImpresionExcel().imprimirDesdeExcel(excelIngXAnulacion);
        dispose();
    }
    
    private ArrayList SetLista() {
        ArrayList<DetalleAnulacion> lista = new ArrayList<>();
        for ( int i = 0; i < tabDet.getRowCount(); i++ ) {
            String linea = String.valueOf(tabDet.getValueAt(i, 0));
            String nroParte = String.valueOf(tabDet.getValueAt(i, 1));
            
            String descripcion = String.valueOf(tabDet.getValueAt(i, 2));
            String cantidad = String.valueOf(tabDet.getValueAt(i, 3));
            String precioLista = String.valueOf(tabDet.getValueAt(i, 4));
            String total = String.valueOf(tabDet.getValueAt(i, 5));
            String modelo = String.valueOf(tabDet.getValueAt(i, 6));
            lista.add(new DetalleAnulacion(linea, nroParte, descripcion, cantidad, precioLista, total, modelo));
        }
        return lista;
    }

    private Map SetParametros(Control control) {
        Map<String, Object> mapa = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        mapa.put("tipoDoc", txtTipoDocument.getText());
        mapa.put("titulo", "Ingresos al Almacén");
        mapa.put("transaccion", "Anulación de Documento");
        mapa.put("nroIngreso", nroIngreso + "");
        mapa.put("rutaProgramas", control.getRutaprogramas());
        mapa.put("empresa", control.getNombrempresa());
        mapa.put("glosa", glosa);
        
        if ( cabeces != null ) {
//            mapa.put("femision", sdf.format(cabeces.getTipocambio().getFecha()));
            Date fechaHora = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = formatoFecha.format(fechaHora);
//            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
//            String hora = formatoHora.format(fechaHora);
//            String fechayHora = fecha + " - " + hora;
            mapa.put("femision", fecha);
            
            mapa.put("nroDoc", cabeces.getId().getNrodocumento());
            
        } else {
            mapa.put("femision", "");
            mapa.put("nroDoc", "");
        }
        mapa.put("motivo", txtAreaMotivo.getText());
        mapa.put("total", txtImporteDocument.getText());
        return mapa;
    }
    
    private void txtAreaMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaMotivoKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            btnImprimir.requestFocus();
        }
    }//GEN-LAST:event_txtAreaMotivoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTextArea txtAreaMotivo;
    private javax.swing.JTextField txtImporteDocument;
    private javax.swing.JTextField txtNroFacturaRelac;
    private javax.swing.JTextField txtTipoDocument;
    // End of variables declaration//GEN-END:variables
}