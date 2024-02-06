package Presentacion.EmisionPlanillas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Mantenimiento.Navegacion;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_TipoCambio;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Documentos;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author maverick225
 * @author modified : Ledis Rivera Ch.
 */
public class UI_IngresarAnulado extends javax.swing.JFrame {
    int tipo;
    TipoMensaje tm;
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    Sistema sis;
    Servicio_Documentos sf;
    
    UI_GestionarPlanillas iuGp;
    Date inicio;
    Date fin;
    private String rol;

    public UI_IngresarAnulado(UI_GestionarPlanillas iuGp, Date inicio, Date fin, int tipo) {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        tm = new TipoMensaje();
        dc_Fecha.setDate(new Date());
        this.tipo = tipo;
        sf = new Servicio_Documentos();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btn_Aceptar);
        asignarEvento();
        this.iuGp = iuGp;
        this.inicio = inicio;
        this.fin = fin;
    }

    private void crearArrayComponente() {
        componentes = new JTextField[2];
        componentes[0] = txtserie;
        componentes[1] = txt_Numero;
    }

    private void crearArrayNumMax() {
        numMaximo.add(3);
        numMaximo.add(8);
    }

    private void asignarEvento() {
        for ( int i = 0; i < componentes.length; i++ ) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayTipoDato() {
        tipoDato.add("I");
        tipoDato.add("I");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_Tipo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txt_Numero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dc_Fecha = new com.toedter.calendar.JDateChooser();
        btn_Aceptar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        txtserie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo de documento:");

        cb_Tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "FACTURA", "BOLETA", "NOTA DE CREDITO", "NOTA DE DEBITO" }));

        jLabel2.setText("Nro de documento:");

        txt_Numero.setToolTipText("Nro. Documento");

        jLabel3.setText("Fecha:");

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

        txtserie.setToolTipText("Serie");

        jLabel4.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtserie, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(cb_Tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dc_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dc_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    public String numero_serie(String serie) {
        String cadena = "";
        int tamaño = serie.length();

        switch ( tamaño ) {
            case 1:
                cadena = serie;
                return cadena;
                
            case 3:
                cadena = serie.substring(2, 3);
                return cadena;
        }
        return cadena;
    }

    public String tipo_doc(int tipo) {
        String cadena;
        switch ( tipo ) {
            case 1:
                cadena = "V";
                return cadena;
                
            case 2:
                cadena = "C";
                return cadena;
                
            default:
                cadena = "V";
                break;
        }
        return cadena;
    }

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        String validacion = validar();
        if ( validacion.equals(tm.VALIDO) ) {
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            
            if ( opcion == tm.SI ) {
                String serie = numero_serie(txtserie.getText());
                String tipotra = tipo_doc(tipo);
                Cabeces cab = new Cabeces();
                CabecesId id = new CabecesId();
                System.out.println("cbtipo:" + cb_Tipo.getSelectedIndex());
                id.setTipodoc("0" + cb_Tipo.getSelectedIndex());
                id.setNrodocumento(txt_Numero.getText());
                id.setNrorserie(serie);
                id.setTipotra(tipotra);
                
                Servicio_Cabeces sc = new Servicio_Cabeces();
                cab = sc.obtenerCabecera_id(id);
                //obtenerCabecera(String tipoTra, String tipoDoc, String nroSerie, String nroDocumento)
                
                cab.setEstado("ANULADO");
//                Tipocambio t = new Servicio_TipoCambio().getTipoCambio(dc_Fecha.getDate());
////                Tipocambio t = new Servicio_TipoCambio().obtenerTipoCambio(dc_Fecha.getDate());
//                System.out.println("tipoCambio:" + t);
//                
//                if ( t == null ) {
//                    t = new Tipocambio(dc_Fecha.getDate());
//                }
//                cab.setTipocambio(t);
                
//                if ( new Servicio_Cabeces().GuardarDocumentoAnulado(cab) ) {
                if ( sc.registrarFactAnulada(cab) ) {
                    System.out.println("N°:" + txt_Numero.getText());
                    sis = sf.getSis(String.valueOf(cb_Tipo.getSelectedItem()));
                    sis.setUltimonumero(Integer.parseInt(txt_Numero.getText()));
                    sf.actualizarSis(sis);
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    
                    // Refrescar browse con el ingreso de la nueva factura anulada
                    BorrarTabla();
                    Servicio_GestionarPlanillas servicioGP = new Servicio_GestionarPlanillas(this.iuGp, inicio, fin, tipo);
                    dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "DOCUMENTO YA EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            tm.Error(validacion);
        }
    }//GEN-LAST:event_btn_AceptarActionPerformed
    
    private void BorrarTabla() {
        System.out.println("borrar tabla");
        DefaultTableModel modelo = (DefaultTableModel) iuGp.tab_Planillas.getModel();
        int numRows = modelo.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            modelo.removeRow(0);
        }
    }
    
    public String validar() {
        String error = "ERROR";
        
        if ( cb_Tipo.getSelectedIndex() == 0 ) {
            error += "\n- SELECCIONE UN TIPO DE DOCUMENTO";
        }
        
        if ( txtserie.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO NUMERO DE SERIE";
        }
        
        if ( txtserie.getText().length() == 2 ) {
            error += "\n- EL CAMPO SERIE (SON 3 DIGITOS)";
        }
        
        if ( txt_Numero.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO NUMERO DE DOCUMENTO";
            
        } else {
            try {
                int d = Integer.parseInt(txt_Numero.getText());
            } catch ( NumberFormatException e ) {
                error += "\n- INGRESE UN NUMERO DE DOCUMENTO VALIDO";
            }
        }
        if ( dc_Fecha.getDate() == null ) {
            error += "\n- SELECCIONE UNA FECHA";
        }

        if ( error.equals("ERROR") ) {
            return tm.VALIDO;
            
        } else {
            return error;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JComboBox cb_Tipo;
    private com.toedter.calendar.JDateChooser dc_Fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_Numero;
    private javax.swing.JTextField txtserie;
    // End of variables declaration//GEN-END:variables
}