package Presentacion.EmisionPlanillas;

import Entidades.Cabeces;
import Entidades.Cabecsalvar;
import Entidades.Tipocambio;
import Entidades.Vendedores;
import Mantenimiento.VendedorDAO;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_TipoCambio;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Documentos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 */
public class UI_ModificarPlanilla extends javax.swing.JFrame {

    TipoMensaje tm;
    private Cabeces cabeces;
    private Cabecsalvar cabsal;
    private Servicio_GestionarPlanillas gestionar;
    
    private JTable tabla;
    private int filaSeleccionada;
    private String rol;

    public UI_ModificarPlanilla(Object o, Servicio_GestionarPlanillas gestionar,
                                JTable tabla, Date inicio, Date fin, int tipo,
                                UI_GestionarPlanillas padre, int filaSeleccionada) {
        this();
        tm = new TipoMensaje();
        
        this.tabla = tabla;
        this.filaSeleccionada = filaSeleccionada;
        limpiarTabla();
        Servicio_GestionarPlanillas servicioGP = new Servicio_GestionarPlanillas(padre, inicio, fin, tipo);
        
        Cabeces cab = (Cabeces) o;
        listarVendedores(cab);
        SetCabec(o);
        this.gestionar = gestionar;
        
//        dc_Fecha.setDate(new Date());
        String fechaTabla = String.valueOf(tabla.getValueAt(filaSeleccionada, 4));
        dc_Fecha.setDate(stringToDate(fechaTabla));
    }
    
    private Date stringToDate(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/M/yyyy");
        Date date = null;
        try {
             date = formato.parse(fecha);
        } catch( ParseException pe ) {
            System.out.println("Excepcion :" + pe.getMessage());
        }
        return date;
    }
    
    
    private void limpiarTabla(){
//        System.out.println("limpiando tabla...");
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
       for (int i = 0; i < tabla.getRowCount(); i++) {
           model.removeRow(i);
           i-=1;
       }
    }
    
    
    private void listarVendedores(Cabeces cab) {
        Iterator<Vendedores> it = new Servicio_Documentos().getVendedoresOrdenados().iterator();
        while (it.hasNext()) {
            Vendedores v = it.next();
            cbVendedor.addItem(v.getNombre());
        }
    }

    private void SetCabec(Object o) {
        if (o instanceof Cabeces) {
            cabeces = (Cabeces) o;
            lb_tipoDoc.setText(TipoDoc(cabeces.getId().getTipodoc()));
            jl_nroserie.setText(cabeces.getId().getNrorserie());
            lb_documento.setText(cabeces.getId().getNrodocumento());
            lb_total.setText((cabeces.getMoneda().equals("1") ? "S/. " : "$ ") + String.valueOf(cabeces.getTotal()));
            dc_Fecha.setDate(cabeces.getTipocambio().getFecha());
            cb_Forma.setSelectedIndex(Integer.parseInt(cabeces.getTipoperacion()));
            
            if(cabeces.getTxtgen()== 0) {
                cbGenTxt.setSelectedIndex(0);//
            } else {
                cbGenTxt.setSelectedIndex(1);
            }
            
            
            if ( filaSeleccionada == -1 ) {
                cbVendedor.setSelectedItem(cabeces.getVendedores().getNombre());
                
            } else {
                String nombreVendedor = String.valueOf(this.tabla.getValueAt(filaSeleccionada, 12));
                cbVendedor.setSelectedItem(nombreVendedor);
            }

        } else {
            cabsal = (Cabecsalvar) o;
            cb_Forma.setEnabled(false);
            lb_tipoDoc.setText("SALIDAS VARIAS");
            lb_documento.setText(cabsal.getCodigosalida());
            lb_total.setText(String.valueOf("$ " + cabsal.getTotal()));
            dc_Fecha.setDate(cabsal.getFecha());
            
            if(cabeces.getTxtgen()== 0) {
                cbGenTxt.setSelectedIndex(0);//
            } else {
                cbGenTxt.setSelectedIndex(1);
            }
            
            if ( filaSeleccionada == -1 ) {
                cbVendedor.setSelectedItem(cabeces.getVendedores().getNombre());
                
            } else {
                String nombreVendedor = String.valueOf(this.tabla.getValueAt(filaSeleccionada, 12));
                cbVendedor.setSelectedItem(nombreVendedor);
            }
        }
    }

    public UI_ModificarPlanilla() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        dc_Fecha = new com.toedter.calendar.JDateChooser();
        cb_Forma = new javax.swing.JComboBox();
        lb_tipoDoc = new javax.swing.JLabel();
        lb_documento = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jl_nroserie = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbVendedor = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbGenTxt = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Datos del documento");

        jLabel2.setText("Tipo documento:");

        jLabel3.setText("Nro documento:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Total:");

        jLabel6.setText("Forma de pago:");

        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        cb_Forma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "CONTADO", "CREDITO" }));

        lb_tipoDoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_documento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setText("Nro. Serie : ");

        jl_nroserie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setText("Vendedor:");

        jLabel9.setText("Enviado a Sunat:");

        cbGenTxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Enviado", "Enviado" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGenTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(dc_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lb_documento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(lb_tipoDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jl_nroserie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_Forma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lb_tipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jl_nroserie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lb_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dc_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_Forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbGenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        String validacion = validar();
        if (validacion.equals(tm.VALIDO)) {
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (opcion == tm.SI) {
                if (cabeces != null) {
                    cabeces.setTipoperacion(String.valueOf(cb_Forma.getSelectedIndex()));
                    Tipocambio t = new Servicio_TipoCambio().obtenerTipoCambio(dc_Fecha.getDate());
                    cabeces.setTipocambio(t);
                    System.out.println(cbGenTxt.getSelectedItem().toString());
                    if(cbGenTxt.getSelectedIndex()==0) {
                        
                            cabeces.setTxtgen(0);
                    } else {
                        cabeces.setTxtgen(1);
                    }
                    
                    String nombreVend = String.valueOf(cbVendedor.getSelectedItem());
                    Vendedores vendedor = new VendedorDAO().Obtener_Vendedor_PorNombre(nombreVend);
                    if (new Servicio_Cabeces().actualizarDoc(cabeces, vendedor)) {
                        tm.Mensaje("SE GUARDARON LOS CAMBIOS");
                        System.out.println(cabeces.getTxtgen());
                        dispose();
                    } else {
                        tm.Error("ERROR AL MODIFICAR EL REGISTRO");
                    }                    
                } else if (cabsal != null) {
                    cabsal.setFecha(dc_Fecha.getDate());
                    if(cbGenTxt.getSelectedIndex()==0) {
                            cabeces.setTxtgen(0);
                    } else {
                        cabeces.setTxtgen(1);
                    }
                    if (new Servicio_Documentos().Actualizar_SK(cabsal)) {
                        tm.Mensaje("SE GUARDARON LOS CAMBIOS");
                        dispose();
                    } else {
                        tm.Error("ERROR AL MODIFICAR EL REGISTRO");
                    }
                }
            }
        } else {
            tm.Error(validacion);
        }
    }//GEN-LAST:event_btn_AceptarActionPerformed
    private String validar() {
        String error = "ERROR";
        if (dc_Fecha.getDate() == null) {
            error += "\n- INDIQUE UNA FECHA";
        }
        if (cb_Forma.isEnabled() && cb_Forma.getSelectedIndex() == 0) {
            error += "\n- SELECCIONE UNA FORMA DE PAGO";
        }
        if (Calendar.getInstance().getTime().compareTo(dc_Fecha.getDate()) < 0) {
            error += "\n- SELECCIONE UNA FECHA ANTERIOR A LA DE HOY";
        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    private String TipoDoc(String TipoDoc) {
        switch (TipoDoc) {
            case "01":
                return "FACTURA";
            case "02":
                return "BOLETA";
            case "03":
                return "NOTA DE CREDITO";
            case "04":
                return "NOTA DE DEBITO";
            default:
                return "";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JComboBox cbGenTxt;
    private javax.swing.JComboBox cbVendedor;
    private javax.swing.JComboBox cb_Forma;
    private com.toedter.calendar.JDateChooser dc_Fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jl_nroserie;
    private javax.swing.JLabel lb_documento;
    private javax.swing.JLabel lb_tipoDoc;
    private javax.swing.JLabel lb_total;
    // End of variables declaration//GEN-END:variables
}
