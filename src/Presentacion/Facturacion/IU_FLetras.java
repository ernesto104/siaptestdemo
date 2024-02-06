package Presentacion.Facturacion;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecproformas;
import Entidades.Cabecsalvar;
import Entidades.Detallees;
import Entidades.Otros.NroLetras;
import Entidades.Pagos;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Servicios.Constantes;
import Servicios.Editor_Celdas;
import Servicios.Servicio_TipoCambio;
import Servicios.TipoMensaje;
import Servicios.Util;
import Servicios.facturacion.Editor;
import Servicios.facturacion.Render_Letra;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.Tabla_letras;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author CRS
 */
public class IU_FLetras extends javax.swing.JFrame implements Constantes{

    Tabla_letras tbl;
    private boolean guardado;
    Editor edit;
    Render_Letra rend;
    Cabeces cab;
    ArrayList<Detallees> det;
    TipoMensaje tm;
    private Servicio_Documentos ServicioDoc;
    private Sistema sisFac;
    private Sistema sisLet;
    private Object cab_actualizar;
    Util util ;
    Editor_Celdas editor;
    JTextField edit_field;

    public IU_FLetras(Cabeces cab, ArrayList<Detallees> dt) {
        this.cab = cab;
        this.det = dt;
        util = new Util();
        tbl = new Tabla_letras(cab.getTotal(), Calendar.getInstance().getTime(),util);
        initComponents();
        initOtherComponents();
        
    }

    private void initOtherComponents() {
        tm = new TipoMensaje();
        edit = new Editor();
        rend = new Render_Letra();
        tb_letras.setDefaultEditor(JDateChooser.class, edit);
        tb_letras.setDefaultRenderer(JDateChooser.class, rend);
        setLocationRelativeTo(null);
        setVisible(true);
        if(cab.getMoneda().equals(DOLARES)){
            lb_total.setText(GLOSA_DOLARES+util.DosDecimales(cab.getTotal()));
        }else{
            lb_total.setText(GLOSA_SOLES+util.DosDecimales(cab.getTotal()));
        }
        
        setAnchoColumnas();
        cab_actualizar = null; 
        tb_letras.setCellSelectionEnabled(true);
        guardado = false;
        SetEditor();
    }
    private void SetEditor(){
        edit_field = new JTextField();
        editor = new Editor_Celdas(edit_field);
        editor.setClickCountToStart(2);
        for(int i=0;i<tbl.getColumnCount()-1;i++){
            tb_letras.getColumnModel().getColumn(i).setCellEditor(editor);
        }
    }
    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_letras.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_letras.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_letras.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (25 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_letras = new javax.swing.JTable();
        bt_aceptar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lb_total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tx_letras = new javax.swing.JTextField();
        dt_fecha = new JDateChooser(Calendar.getInstance().getTime());
        bt_asignar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lb_tipoOperacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Canje por Letras");

        tb_letras.setModel(tbl);
        jScrollPane1.setViewportView(tb_letras);

        bt_aceptar.setText("Aceptar");
        bt_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aceptarActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setText("Tipo de Operacion");

        jLabel5.setText("Fecha de Emision");

        jLabel4.setText("Cantidad de Letras");

        tx_letras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_letrasKeyTyped(evt);
            }
        });

        dt_fecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dt_fechaPropertyChange(evt);
            }
        });

        bt_asignar.setText("Asignar");
        bt_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_asignarActionPerformed(evt);
            }
        });

        jLabel6.setText("Total ");

        lb_tipoOperacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_tipoOperacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tipoOperacion.setText("CANJE POR LETRAS");
        lb_tipoOperacion.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lb_tipoOperacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(93, 93, 93))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tx_letras, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(bt_asignar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_tipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_letras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_asignar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CANJE POR LETRAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_asignarActionPerformed
        if (!tx_letras.getText().equals("")) {
            try {
                int numLetras = Integer.parseInt(tx_letras.getText());
                BorrarTabla();
                tbl.asignarLetras(numLetras);
            } catch (NumberFormatException e) {
                Toolkit.getDefaultToolkit().beep();
                tm.Error("INGRESE UN NUMERO CORRECTO");
            }
        }
    }//GEN-LAST:event_bt_asignarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void tx_letrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_letrasKeyTyped
        if (evt.getKeyChar() == 10) {
            bt_asignar.doClick();
        }
    }//GEN-LAST:event_tx_letrasKeyTyped

    private void bt_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aceptarActionPerformed
        tb_letras.clearSelection();
        sisLet = ServicioDoc.getSis("Número de Letra");
        Cabecproformas cabp;
        Cabecsalvar cabsal;
        if( tbl.Valida()){
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (opcion == tm.SI) {
                ArrayList<NroLetras> letras = tbl.getLt();
                Pagos pago = new Pagos();
                pago.setCabeces(cab);
                pago.setFecha(cab.getTipocambio().getFecha());
                pago.setImporte(cab.getTotal());
                pago.setForma(EFECTIVO);

                if (cab_actualizar != null) {
                    if (cab_actualizar instanceof Cabecproformas) {
                        cabp = (Cabecproformas) cab_actualizar;
                        cabp.setCabeces(cab);
                        cab_actualizar = cabp;
                    } else if (cab_actualizar instanceof Cabecsalvar) {
                        cabsal = (Cabecsalvar) cab_actualizar;
                        cabsal.setCabeces(cab);
                        cab_actualizar = cabsal;
                    }
                }
                

                if (ServicioDoc.GuardarLetras_actualizarDoc(cab, det, SetCabecLetras(cab, letras), pago, cab_actualizar)) {
                    guardado = true;
                    ServicioDoc.actualizarSis(sisFac);
                    ServicioDoc.actualizarSis(sisLet);
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    dispose();//por mientras

                } else {
                    tm.Error(tm.ERROR_GUARDAR);
                }

            }
        } else {
            tm.Error("ERROR \n-  CAMPOS VACIOS O INVALIDOS");
        }
    }//GEN-LAST:event_bt_aceptarActionPerformed

    private void dt_fechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dt_fechaPropertyChange
        if (evt.getPropertyName().compareTo("date") == 0) {
            tbl.setFechaInicio(dt_fecha.getDate());
        }

    }//GEN-LAST:event_dt_fechaPropertyChange

    private void BorrarTabla() {
        int numRows = tbl.getRowCount();
        for (int i = 0; i < numRows; i++) {
            tbl.eliminar(0);
        }
    }

    public ArrayList<Cabeces> SetCabecLetras(Cabeces cab, ArrayList<NroLetras> nletras) {
        ArrayList<Cabeces> cabLetra = new ArrayList<>();
        int ulNum = sisLet.getUltimonumero()+1;
        int serie = sisLet.getSerie();
        int i = 0;
        Tipocambio cambio;
        try {
            cambio = ServicioDoc.getTipoCambio(dt_fecha.getDate());
            System.out.println(cambio);
        } catch (Exception e) {
            cambio = new Tipocambio();
            cambio.setFecha(dt_fecha.getDate());
            new Servicio_TipoCambio().Insertar(cambio);
        }

        for (NroLetras n : nletras) {
            Cabeces cabec = new Cabeces();
            cabec.setTipoperacion(LETRAS);
            cabec.setClientes(cab.getClientes());
            cabec.setTipocambio(cambio);
            cabec.setTipocambio_1(cab.getTipocambio().getValorventa());
            cabec.setId(new CabecesId(VENTA, sisLet.getTipodoc(), String.valueOf(serie), String.valueOf(ulNum)));
            cabec.setTotal(n.getImporte());
            cabec.setLetraFacbol(String.valueOf(cab.getId().getNrodocumento()));
            cabec.setMoneda(cab.getMoneda());
            cabec.setUsuarios(cab.getUsuarios());
            JDateChooser chooser = (JDateChooser) tbl.getValueAt(i++, 3);
            cabec.setFechavencimiento(chooser.getDate());
            cabLetra.add(cabec);

            ulNum++;
        }
        sisLet.setUltimonumero(ulNum-1);
        return cabLetra;
    }

    public void setServicioDoc(Servicio_Documentos ServicioDoc) {
        this.ServicioDoc = ServicioDoc;
    }

    public void setSisFac(Sistema sis) {
        this.sisFac = sis;
    }

    public void setCab_act(Object cab) {
        this.cab_actualizar = cab;
    }

    public boolean isGuardado() {
        return guardado;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_aceptar;
    private javax.swing.JButton bt_asignar;
    private javax.swing.JButton bt_cancelar;
    private com.toedter.calendar.JDateChooser dt_fecha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_tipoOperacion;
    private javax.swing.JLabel lb_total;
    private javax.swing.JTable tb_letras;
    private javax.swing.JTextField tx_letras;
    // End of variables declaration//GEN-END:variables
}

