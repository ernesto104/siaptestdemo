package Presentacion.Reimpresion;

import Entidades.Clientes;
import Entidades.Control;
import static Entidades.Otros.Constante.COMPRA_LOCAL;
import static Entidades.Otros.Constante.TIT_ICLyIR;
import static Entidades.Otros.Constante.TIT_SR;
import static Entidades.Otros.Constante.INGRESO_POR_AJUSTES;
import static Entidades.Otros.Constante.SALIDA_POR_AJUSTES;
import static Entidades.Otros.Constante.TIPODOC_ING_COMPRA_LOCAL;
import static Entidades.Otros.Constante.TIPODOC_ING_REGULARIZACION;
import static Entidades.Otros.Constante.TIPODOC_SAL_REGULARIZACION;
import static Entidades.Otros.Constante.NOTA_ING_AJUSTES;
import static Entidades.Otros.Constante.NOTA_ING_COMPRA_LOCAL;
import static Entidades.Otros.Constante.NOTA_SALIDA;
import static Entidades.Otros.Constante.REIMP_INGSAL_COL_CODSEC;
import static Entidades.Otros.Constante.REIMP_INGSAL_COL_MODELO;
import static Entidades.Otros.Constante.REIMP_INGSAL_COL_PRECLISTA;
import Entidades.Otros.Monetario;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Mantenimiento.ControlDAO;
import Presentacion.IngresoSalidas.DetalleIngSal;
import Servicios.Servicio_DetalleEs;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import Servicios.facturacion.Servicio_Impresion;
import java.util.HashMap;
import Servicios.Servicio_Sistema;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Reimpresion_Ingreso_Salida extends javax.swing.JFrame {

    DefaultTableModel modelo;
    ControlDAO controlDAO;
    double igv;
    double valorVentaNota;
    double igvNota;
    double totalNota;
    double tipoCambioVenta;
    Tipocambio tipoCambio;
    int nroDocum;
    String tipo;
    
    Servicio_Sistema ser_sis;
    Clientes proveedor;
    String nroDocProveedor;
    String motivoCompraLocal;

    public IU_Reimpresion_Ingreso_Salida(int nroDocumen, String tip, Clientes proveedor, String nroDocProveedor, String motivoCompraLocal)
                                        throws ParseException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nroDocum = nroDocumen;
        this.tipo = tip;
        iniciar();
        btnReimprimir.requestFocus();
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        
        this.proveedor = proveedor;
        this.nroDocProveedor = nroDocProveedor;
        this.motivoCompraLocal = motivoCompraLocal;
        
        ocultarColumnas();
        alinearColumnasDerecha();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
    }
    
    private void alinearColumnasDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblDescripcion.getColumnModel().getColumn(2).setCellRenderer(tcr);      // Cantidad
        tblDescripcion.getColumnModel().getColumn(4).setCellRenderer(tcr);      // Valor(Total por item)
    }
    
    private void ocultarColumnas() {
        ocultarColumna(REIMP_INGSAL_COL_CODSEC);
        ocultarColumna(REIMP_INGSAL_COL_PRECLISTA);
//        ocultarColumna(REIMP_INGSAL_COL_MODELO);
    }
    
    private void ocultarColumna(int columna) {
        tblDescripcion.getColumnModel().getColumn(columna).setMaxWidth(0);
        tblDescripcion.getColumnModel().getColumn(columna).setMinWidth(0);
        tblDescripcion.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }

    public void iniciar() throws ParseException {
        txtTipoDocumento.setEditable(false);
        txtNumeroDocumento.setEditable(false);
        txtIGV1.setEditable(false);
        txtSubTotal1.setEditable(false);
        txtTotal1.setEditable(false);
        String tipoDoc = "";
        
        switch( this.tipo ) {
            case NOTA_ING_COMPRA_LOCAL:
                tipoDoc = COMPRA_LOCAL;
                break;
                
            case NOTA_ING_AJUSTES:
                tipoDoc = INGRESO_POR_AJUSTES;
                break;
                
            case NOTA_SALIDA:
                tipoDoc = SALIDA_POR_AJUSTES;
                break;
        }
        txtTipoDocumento.setText(tipoDoc);
        txtNumeroDocumento.setText(" " + nroDocum);

        modelo = (DefaultTableModel) tblDescripcion.getModel();
        ((JLabel) tblDescripcion.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        controlDAO = new ControlDAO(this);
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        igv = control.getImpuestoigv();
        
        Servicio_DetalleEs s = new Servicio_DetalleEs(tblDescripcion);
        
        if ( NOTA_SALIDA.equals(tipo) ) {
            s.Listar_Detalle_Salida(nroDocum, modelo);
            
        } else {
            s.Listar_Detalle_Ingreso(nroDocum, modelo, tipo);
        }
        Calcular_SubTotal();
    }

    private void Calcular_SubTotal() {
        double tot = 0;
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            Double s = Double.parseDouble(String.valueOf(tblDescripcion.getValueAt(i,4)));
            tot = tot + s;
        }
        totalNota = tot;
        String _totalNota = Monetario.formatearMonetario(tot, 2);
        txtTotal1.setText(_totalNota);

        System.out.println("igv:" + igv); // 18.0
        double factorIgv = igv * 0.01;
        
        valorVentaNota = tot / (factorIgv + 1);
        String subTotal1 = Monetario.formatearMonetario(valorVentaNota, 2);
        txtSubTotal1.setText(subTotal1);
        
        igvNota = factorIgv * totalNota;
        String _igvNota = Monetario.formatearMonetario(igvNota, 2);
        txtIGV1.setText(_igvNota);
        /*
        valorVentaNota = subt;
        String subTotal1 = Monetario.formatearMonetario(subt, 2);
        txtSubTotal1.setText(subTotal1);

        igvNota = igv * 0.01 * valorVentaNota;
        String _igvNota = Monetario.formatearMonetario(igvNota, 2);
        txtIGV1.setText(_igvNota);
        
        totalNota = valorVentaNota + igvNota;
        String _totalNota = Monetario.formatearMonetario(totalNota, 2);
        txtTotal1.setText(_totalNota);*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSubTotal1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtIGV1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDescripcion = new javax.swing.JTable();
        btnReimprimir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTipoDocumento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REIMPRESIÓN DE NOTA");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción"));

        jLabel13.setText("Sub-Total :");

        txtSubTotal1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel14.setText("I.G.V :");

        txtIGV1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel15.setText("Total :");

        txtTotal1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tblDescripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Nro. Parte", "Cantidad", "Descripcion", "Total", "CodSecundario", "PrecioLista", "Modelo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDescripcion.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblDescripcion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 786, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIGV1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtIGV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnReimprimir.setText("Reimprimir");
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        jButton2.setText("Excel");

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo de Documento:");

        jLabel5.setText("Número de Documento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(btnReimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        btnReimprimir.getAccessibleContext().setAccessibleName("");
        jButton2.getAccessibleContext().setAccessibleName("");
        jButton3.getAccessibleContext().setAccessibleName("");

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReimprimirActionPerformed
        imprimir();
    }//GEN-LAST:event_btnReimprimirActionPerformed

    private void imprimir() {
        ArrayList lista = SetLista();
        String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
        Map<String, Object> parametros;
        
        switch ( tipo ) {
            case NOTA_ING_COMPRA_LOCAL:
                parametros = SetParametros();
                Servicio_Impresion.exporta(28, lista, parametros, "plantillas/Ingresos_Por_Compra_Local.pdf");
                break;
                
            case NOTA_ING_AJUSTES:
                parametros = SetParametros();
                lista = SetListaIng();
                Servicio_Impresion.exporta(4, lista, parametros, "plantillas/Ingresos_Y_Salidas_Por_Ajustes.pdf");
                break;
                    
            case NOTA_SALIDA:
                parametros = SetParametros();
                Servicio_Impresion.exporta(4, lista, parametros, "plantillas/Ingresos_Y_Salidas_Por_Ajustes.pdf");
                break;
        }
    }
    
    private ArrayList SetLista() {
        ArrayList<DetalleIngSal> lista = new ArrayList<>();
        
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            String linea = String.valueOf(tblDescripcion.getValueAt(i, 0));
            String nroParte = String.valueOf(tblDescripcion.getValueAt(i, 1));
//            String codSec = (tblDescripcion.getValueAt(i, 2) == null ) ? "" : String.valueOf(tblDescripcion.getValueAt(i, 2));
            String descripcion = String.valueOf(tblDescripcion.getValueAt(i, 3));
            String cantidad = String.valueOf(tblDescripcion.getValueAt(i, 2));

//            String precioLista = String.valueOf(tabla.getValueAt(i, 5));
            String total = String.valueOf(tblDescripcion.getValueAt(i, 4));
            
            String codSec = String.valueOf(tblDescripcion.getValueAt(i, 5));
//            codSec = "null".equals(codSec) ? "" : codSec;
            String precioLista = String.valueOf(tblDescripcion.getValueAt(i, 6));
            String descrModelo = String.valueOf(tblDescripcion.getValueAt(i, 7));
//            lista.add(new DetalleIngSal(nroParte, codSec, descripcion, cantidad, precioLista, total));
            lista.add(new DetalleIngSal(linea, 
                                        nroParte, 
                                        descripcion, cantidad, precioLista, total, descrModelo));
        }
        return lista;
    }
    
    private ArrayList SetListaIng() {
        System.out.println("SetListaIng....");
        ArrayList<DetalleIngSal> lista = new ArrayList<>();
        
        for ( int i = 0; i < tblDescripcion.getRowCount(); i++ ) {
            String linea = String.valueOf(tblDescripcion.getValueAt(i, 0));
            String nroParte = String.valueOf(tblDescripcion.getValueAt(i, 1));
//            String codSec = (tblDescripcion.getValueAt(i, 2) == null ) ? "" : String.valueOf(tblDescripcion.getValueAt(i, 2));
            String descripcion = String.valueOf(tblDescripcion.getValueAt(i, 3));
            String cantidad = String.valueOf(tblDescripcion.getValueAt(i, 2));

//            String precioLista = String.valueOf(tabla.getValueAt(i, 5));
            String total = String.valueOf(tblDescripcion.getValueAt(i, 4));
            
            String codSec = String.valueOf(tblDescripcion.getValueAt(i, 5));
//            codSec = "null".equals(codSec) ? "" : codSec;
            String precioLista = String.valueOf(tblDescripcion.getValueAt(i, 6));
            String descrModelo = String.valueOf(tblDescripcion.getValueAt(i, 7));
            
            descripcion = descripcion + " " + descrModelo;
            
//            lista.add(new DetalleIngSal(nroParte, codSec, descripcion, cantidad, precioLista, total));
//            lista.add(new DetalleIngSal(linea, nroParte, descripcion, cantidad, precioLista, total, descrModelo));
            lista.add(new DetalleIngSal(nroParte,
                                        codSec,
                                        descripcion, cantidad, precioLista, total, descrModelo));
        }
        return lista;
    }
    
    private Map SetParametros() {
        Map<String, Object> mapa = new HashMap<>();
        String ultnumero = "";
        ser_sis = new Servicio_Sistema();
        
        switch ( tipo ) {
            case NOTA_ING_COMPRA_LOCAL:
                Sistema sis = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_COMPRA_LOCAL);
//                ultnumero = String.valueOf(sis.getUltimonumero() + 1);
                String nroSerie = String.valueOf(sis.getSerie());
                mapa.put("nroDoc", ""); // NO DEBERÍA EXISTIR NROINGRESO PARA COMPRA LOCAL, PQ NO SE SETEA EN DETALLEES, solo NroDocumento
//                mapa.put("nroDoc", ultnumero);
                
                // Proveedor
                mapa.put("nroserie", formatearCeros(nroSerie, 3)); // N° Serie
                System.out.println("nroDocProveedor:::" + nroDocProveedor);
                mapa.put("nroDocProv", nroDocProveedor); // N° Documento
                mapa.put("proveedor", proveedor == null ? " " : proveedor.getNombre()); // Nombre
                break;
                
            case NOTA_ING_AJUSTES:
                ultnumero = String.valueOf(ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_REGULARIZACION).getUltimonumero());
                mapa.put("nroDoc", ultnumero);
                break;
            
            case NOTA_SALIDA:
                ultnumero = String.valueOf(ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_SAL_REGULARIZACION).getUltimonumero());
                mapa.put("nroDoc", ultnumero);
                break;
        }
        
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("titulo", NOTA_SALIDA.equals(tipo) ? TIT_SR : TIT_ICLyIR);
        mapa.put("empresa", empresa);
//        mapa.put("transaccion", lb_tipotrans.getText());
        mapa.put("transaccion", NOTA_ING_COMPRA_LOCAL.equals(tipo) ? COMPRA_LOCAL : (NOTA_ING_AJUSTES.equals(tipo) ? INGRESO_POR_AJUSTES : SALIDA_POR_AJUSTES));
        mapa.put("motivo", motivoCompraLocal);
        mapa.put("total", txtTotal1.getText());
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReimprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDescripcion;
    private javax.swing.JTextField txtIGV1;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtSubTotal1;
    private javax.swing.JTextField txtTipoDocumento;
    private javax.swing.JTextField txtTotal1;
    // End of variables declaration//GEN-END:variables
}