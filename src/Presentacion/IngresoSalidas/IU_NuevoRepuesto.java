package Presentacion.IngresoSalidas;

import Entidades.Equipos;
import Entidades.Estratificacion;
import Entidades.Marcas;
import Entidades.Modelos;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import Presentacion.Facturacion.IU_FMaestro;
import Servicios.Servicio_Equipos;
import Servicios.Servicio_Estratificacion;
import Servicios.Servicio_Maestros;
import Servicios.Servicio_Marcas;
import Servicios.Servicio_Modelos;
import Servicios.TipoMensaje;
import Servicios.UserSession;
import Servicios.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class IU_NuevoRepuesto extends javax.swing.JFrame {
    Servicio_Maestros sm;
    TipoMensaje tm;
    
    IU_FMaestro iuMaestro;
    DefaultComboBoxModel modelMarcCombo;

    public IU_NuevoRepuesto(IU_FMaestro iuMaestro) {
        tm = new TipoMensaje();
        sm = new Servicio_Maestros(null);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        this.iuMaestro = iuMaestro;
        tx_idrep.setText(String.valueOf(sm.nextId()));
        ListarEquipos();
        String equipoDescrItem = String.valueOf(cb_linea.getSelectedItem());
        String equipoIdItem = equipoDescrItem.split("-")[0];
        ListarMarcas(Integer.parseInt(equipoIdItem));
        
        System.out.println("\n Usuario guardado: "+ UserSession.USER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_idrep = new javax.swing.JTextField();
        tx_precioLista = new javax.swing.JTextField();
        tx_MarginUtil = new javax.swing.JTextField();
        cb_linea = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_descripcion = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNroParte = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_Marcas = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cb_Modelos = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        tx_descLista = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tx_descrModelo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        bt_agregar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Producto"));

        jLabel1.setText("ID Producto");

        jLabel2.setText("Descripcion");

        jLabel3.setText("Precio Lista");

        jLabel4.setText("Equipo");

        jLabel5.setText("Margen Util");

        tx_idrep.setEnabled(false);

        cb_linea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lineaItemStateChanged(evt);
            }
        });

        ta_descripcion.setColumns(20);
        ta_descripcion.setRows(3);
        ta_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ta_descripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(ta_descripcion);

        jLabel7.setText("(*)");

        jLabel8.setText("(*)");

        jLabel9.setText("(*)");

        jLabel10.setText("N° Serie");

        txtNroParte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroParteKeyTyped(evt);
            }
        });

        jLabel11.setText("(*)");

        jLabel12.setText("Marca");

        cb_Marcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_MarcasItemStateChanged(evt);
            }
        });

        jLabel13.setText("Modelo");

        jLabel14.setText("Anotacion 1");

        jLabel16.setText("Aplicacion 1");

        jLabel15.setText("(*)");

        jLabel17.setText("(*)");

        jLabel18.setText("(*)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tx_idrep, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNroParte)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_Marcas, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cb_Modelos, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_precioLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_MarginUtil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tx_descrModelo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tx_descLista, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_idrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Marcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Modelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_descLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_descrModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_precioLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_MarginUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NUEVO PRODUCTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
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

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        String validacion = validar();
//        System.out.println("validacion:" + validacion);
        
        if( validacion.equals(tm.VALIDO) ) {
            Repuestos nuevo = new Repuestos();
            
            String id_linea = String.valueOf(cb_linea.getSelectedItem());
            String[] linea = id_linea.split("-");
            
            String id_marca = String.valueOf(cb_Marcas.getSelectedItem());
            String[] marca = id_marca.split("-");
            
            String id_modelo = String.valueOf(cb_Modelos.getSelectedItem());
            String[] modelo = id_modelo.split("-");

            
            Servicio_Estratificacion es = new Servicio_Estratificacion();
            Estratificacion estr = es.getEstratificacion_por_nombre("N");
            
            
            int idLinea = Integer.parseInt(linea[0]);
            int idMarca = Integer.parseInt(marca[0]);
            int idModelo = Integer.parseInt(modelo[0]);
            String marcaDescr = marca[1];
            
            Servicio_Equipos li = new Servicio_Equipos(null);
            Equipos equipos = li.getEquipos_por_codigo(Integer.valueOf(idLinea));
            
            Servicio_Marcas ma = new Servicio_Marcas(null);
            Marcas marcas = ma.getMarcas_por_codigo(Integer.valueOf(idMarca));
            
            Servicio_Modelos mo = new Servicio_Modelos(null);
            Modelos modelos = mo.getModelos_por_codigo(Integer.valueOf(idModelo));
//            Equipos l = (Equipos) cb_linea.getSelectedItem();
            RepuestosId id = new RepuestosId();
//            nuevo.setId(new RepuestosId(Integer.parseInt(tx_idrep.getText()), l.getIdlinea()));
            nuevo.setId(new RepuestosId(Integer.parseInt(tx_idrep.getText()), idLinea,idMarca, idModelo));
            
            nuevo.setEquipos(equipos);
            nuevo.setMarcas(marcas);
            nuevo.setModelos(modelos);
            nuevo.setEstratificacion(estr);
            
            
            nuevo.setMarca(marcaDescr);
            nuevo.setDescripcion(ta_descripcion.getText());
            nuevo.setPreciolista(Double.parseDouble(tx_precioLista.getText()));
            nuevo.setMargenutil(Double.parseDouble(tx_MarginUtil.getText()));
            nuevo.setStock(0);
            nuevo.setStockminimo(0);
            nuevo.setCodrepuesto(txtNroParte.getText());
            nuevo.setCostopromedio(0.00);
            nuevo.setDesclista(tx_descLista.getText());
            nuevo.setDescrmodelo(tx_descrModelo.getText());
            nuevo.setFecharegistro(fechasistemaDate());
            nuevo.setUsuario(UserSession.USER);
            
            if ( sm.addRepuestos(nuevo) ) {
                tm.Mensaje(tm.EXITO_AGREGAR);
//                System.out.println("recarga maestro...");
                recargarMaestro();
                this.iuMaestro.repaint();
                dispose();
                
            } else {
                tm.Mensaje(tm.ERROR_AGREGAR);
            }
            
        } else {
            tm.manejarMensajes(validacion);
        }
    }//GEN-LAST:event_bt_agregarActionPerformed

    
    public Date fechasistemaDate() {
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
        System.out.println(today);
        //Date ahora = today.getTime();
        
        Date ahora = new Date();
        System.out.println(ahora);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return ahora;
    }
        
        
    public void recargarMaestro() {
        System.out.println("recargar Maestro(IU_NuevoRepuesto)");
        BorrarTabla();
        List lst = sm.getList();
        if ( !lst.isEmpty() ) {
            System.out.println("tamaño de lista(IU_NuevoRepuesto):" + lst.size());
        }
        ListarRep(lst.iterator());
    }
    
    private void BorrarTabla() {
        DefaultTableModel t = (DefaultTableModel) iuMaestro.tb_rep.getModel();
        int numRows = t.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            t.removeRow(0);
        }
    }
    
    public void ListarRep(Iterator it) {
        while ( it.hasNext() ) {
            Object[] result = (Object[]) it.next();
            AgregarFila(result);
        }
    }
    
    public void AgregarFila(Object[] repuesto) {        
        Object[] row = new Object[8];
        row[0] = Integer.parseInt(String.valueOf(repuesto[0])); // idLinea
        row[1] = String.valueOf(repuesto[1]); // codRepuesto o NroParte
//        System.out.println("cod repuesto:" + row[1]);
        row[2] = String.valueOf(repuesto[2]); // descripcion
        row[3] = ( repuesto[3] == null ) ? "" : String.valueOf(repuesto[3]); // descrmodelo (aplicación)
        
        DefaultTableModel t = (DefaultTableModel) iuMaestro.tb_rep.getModel();
        Util util = new Util();
        
        if ( iuMaestro.f != null ) { // Proviene de IU_Facturacion
            row[4] = util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[4]))); // preciolista
            row[5] = ( repuesto[6] == null ) ? "" : String.valueOf(repuesto[6]); // stock
            row[6] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta
//                    util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            
        } else if ( iuMaestro.det != null ) { // Proviene de IU_DetalleIngSal
            //row[4] = ( repuesto[5] == null ) ? "" : util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            row[4] = ( repuesto[4] == null ) ? "" : String.valueOf(repuesto[4]);
            row[5] = ( repuesto[6] == null ) ? "" : String.valueOf(repuesto[5]); //Descripcion
            //row[5] = Integer.parseInt(String.valueOf(repuesto[6])); // stock
            row[6] = ( repuesto[6] == null ) ? "" :  Integer.parseInt(String.valueOf(repuesto[6]));
        }
        row[7] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta
        t.addRow(row);
    }
    
    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void txtNroParteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroParteKeyTyped
        char c = evt.getKeyChar();//sacamos el caracter que se ha presionado
        if ( c >= 'a' && c <= 'z' ) { // Sólo teclas de la a-z
            evt.setKeyChar((char) (c - 32)); // Convertir a mayúsculas
        }
    }//GEN-LAST:event_txtNroParteKeyTyped

    private void ta_descripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ta_descripcionKeyTyped
        char c = evt.getKeyChar();//sacamos el caracter que se ha presionado
        if ( c >= 'a' && c <= 'z' ) { // Sólo teclas de la a-z
            evt.setKeyChar((char) (c - 32)); // Convertir a mayúsculas
        }
    }//GEN-LAST:event_ta_descripcionKeyTyped

    private void cb_lineaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lineaItemStateChanged
        // TODO add your handling code here:
        
        String equipoDescrElement = String.valueOf(cb_linea.getSelectedItem());
        
        String equipoId ="0";
        String marcaId="0";
        //Marcas marcaselec = new Marcas();
        
        //if (cb_linea.getSelectedIndex() != 0) {
            equipoId = equipoDescrElement.split("-")[0];
            //marcaId = marcaDescrElement.split("-")[0];
            //visibilidadElementosMarca(true);
        //}
            //Servicio_Equipos servequip = new Servicio_Equipos(null);
            //Equipos equipselec = servequip.getEquipos_por_codigo(Integer.parseInt(equipoId));
            //llenar_marcasXequipo(equipselec);
        
            ListarMarcas(Integer.parseInt(equipoId));
            
            
            String marcaDescrElement = String.valueOf(cb_Marcas.getSelectedItem());
            marcaId = marcaDescrElement.split("-")[0];
            
            if ("Seleccionar Marca".equals(marcaId)) {
                marcaId="0";
            }
            ListarModelos(Integer.parseInt(marcaId));
        
            
            cb_Marcas.setSelectedIndex(0);
            cb_Modelos.setSelectedIndex(0);
        
    }//GEN-LAST:event_cb_lineaItemStateChanged

    private void cb_MarcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_MarcasItemStateChanged
        // TODO add your handling code here:
  
        String marcaDescrElement = String.valueOf(cb_Marcas.getSelectedItem());

        String marcaId="0";
        //Marcas marcaselec = new Marcas();
        
        //if (cb_linea.getSelectedIndex() != 0) {
;
            marcaId = marcaDescrElement.split("-")[0];
            //visibilidadElementosMarca(true);
        //}
            //Servicio_Equipos servequip = new Servicio_Equipos(null);
            //Equipos equipselec = servequip.getEquipos_por_codigo(Integer.parseInt(equipoId));
            //llenar_marcasXequipo(equipselec);
        

            ListarModelos(Integer.parseInt(marcaId));
        
            
            cb_Modelos.setSelectedIndex(0);
    }//GEN-LAST:event_cb_MarcasItemStateChanged
  
    private void ListarEquipos() {
        ArrayList<Equipos> lista = (new Servicio_Equipos(null).listar_Equipos());
        
        for ( Equipos equipo : lista ) {
            cb_linea.addItem(equipo.getIdequipo() + "-" + equipo.getDescripcion());
        }
    }
    
    
    private void ListarMarcas(int idEquipo) {
        List<Marcas> lista = (new Servicio_Marcas(null).buscarMarcasx_CodigoEquipo(idEquipo));
        
        modelMarcCombo = new DefaultComboBoxModel();
        if(!lista.isEmpty()) {
            for ( Marcas marca : lista ) {
                modelMarcCombo.addElement(marca.getIdmarca()+ "-" + marca.getDescripcion());

                }
        } else {
            modelMarcCombo.addElement("Seleccionar Marca");
        }
 
        cb_Marcas.setModel(modelMarcCombo);

        
    }
    
    private void ListarModelos(int idMarca) {
        List<Modelos> lista = (new Servicio_Modelos(null).buscarModelosx_CodigoMarca(idMarca));
        
        modelMarcCombo = new DefaultComboBoxModel();
        
        if(!lista.isEmpty()) {
            for ( Modelos modelo : lista ) {
                modelMarcCombo.addElement(modelo.getIdmodelo() + "-" + modelo.getDescripcion());
            }
        } else {
            modelMarcCombo.addElement("Seleccionar Modelo");
        }
        //System.out.println(lista);
        cb_Modelos.setModel(modelMarcCombo);
    }
    
    private String validar(){
        if ( txtNroParte.getText().equals("")
                || tx_precioLista.getText().equals("") 
                || ta_descripcion.getText().equals("")
                || tx_MarginUtil.getText().equals("")
                || cb_Marcas.getSelectedItem().toString().equals("Seleccionar Marca")
                || cb_Modelos.getSelectedItem().toString().equals("Seleccionar Modelo")) {
            return tm.CAMPOS_INCOMPLETOS_;
        }
        
        if (sm.GetRepuesto_Codigo(txtNroParte.getText()) != null) {
            return "El numero de serie ya existe";
        }
        try {
            double prec;
            prec = Double.parseDouble(tx_precioLista.getText());
            prec = Double.parseDouble(tx_MarginUtil.getText());
            
        } catch ( NumberFormatException e ) {
            return tm.ERROR_NUMERO;
        }
        return tm.VALIDO;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_salir;
    private javax.swing.JComboBox cb_Marcas;
    private javax.swing.JComboBox cb_Modelos;
    private javax.swing.JComboBox cb_linea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_descripcion;
    private javax.swing.JTextField tx_MarginUtil;
    private javax.swing.JTextField tx_descLista;
    private javax.swing.JTextField tx_descrModelo;
    private javax.swing.JTextField tx_idrep;
    private javax.swing.JTextField tx_precioLista;
    private javax.swing.JTextField txtNroParte;
    // End of variables declaration//GEN-END:variables
}