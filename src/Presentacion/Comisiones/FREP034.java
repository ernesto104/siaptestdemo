
package Presentacion.Comisiones;

import Entidades.Cabeces;
import Mantenimiento.ControlDAO;
import Servicios.Comision.Tabla_Comisiones;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Constantes;
import static Servicios.Constantes.GLOSA_DOLARES;
import Servicios.Servicio_Cabeces;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Impresion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author CRS
 */
public class FREP034 extends javax.swing.JFrame implements Constantes {

    Servicio_Cabeces servicio_cab;
    double pagar;
    int TipoComision;
    String vendedor;
    List comisiones_no_pagadas, comisiones_pagadas, actual, todo;
    UI_MostrarDetalles detalles = null;
    IU_ModificarCabecera mod_cab;
    TipoMensaje tm;
    boolean check;
    List<Cabeces> seleccionados;
    Tabla_Comisiones tab_comision;

    public FREP034() {
        initComponents();
        tab_comision = new Tabla_Comisiones(this);
        tb_comision.setModel(tab_comision);
        seleccionados = new ArrayList<>();
        setLocationRelativeTo(null);
        setAnchoColumnas();
        tm = new TipoMensaje();
        pagar = 0;
        servicio_cab = new Servicio_Cabeces();
        comisiones_no_pagadas = servicio_cab.Obtener_Comisiones_Pendientes("");
        actual = comisiones_no_pagadas;
        Listar(actual);
        tx_cliente.setDocument(new Validar_Mayusculas(tx_cliente, 20));
        tx_nroDoc.setDocument(new Validar_Mayusculas(tx_cliente, 20));
        lb_Apagar.setText("0.00");
        
    }

    private void Listar(List resultado) {
        tab_comision.Agregar(resultado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelComisiones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_comision = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_valorVenta = new javax.swing.JLabel();
        lb_comisiones = new javax.swing.JLabel();
        lb_Apagar = new javax.swing.JLabel();
        bt_pagarComision = new javax.swing.JButton();
        bt_masDatos = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_filtrar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tx_nroDoc = new javax.swing.JTextField();
        tx_cliente = new javax.swing.JTextField();
        bt_limpiarFiltro = new javax.swing.JButton();
        lb_vendedor = new javax.swing.JLabel();
        lb_valorVenta1 = new javax.swing.JLabel();
        lb_valorVenta2 = new javax.swing.JLabel();
        lb_valorVenta3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_comision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "TIPO", "NRO", "FECHA", "VENDEDOR", "CLIENTE / PROVEEDOR", " %", "VALOR VENTA", "COMISION", "PAGADO", "REFERENCIA", "SEL", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_comision.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_comision.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_comision);

        jLabel1.setText("Valor Venta");

        jLabel2.setText("Comisiones");

        jLabel3.setText("A pagar");

        lb_valorVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_valorVenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_comisiones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_comisiones.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_Apagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_Apagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        bt_pagarComision.setText("Paga e Imprime Comision");
        bt_pagarComision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_pagarComisionActionPerformed(evt);
            }
        });

        bt_masDatos.setText("Mas Datos");
        bt_masDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_masDatosActionPerformed(evt);
            }
        });

        bt_modificar.setText("Modificar");
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });

        bt_filtrar.setText("Filtrar");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GESTIONAR COMISIONES");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSQUEDA"));

        jLabel8.setText("Nro Doc");

        jLabel9.setText("Cliente/Proveedor");

        tx_nroDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltrarPor(evt);
            }
        });

        tx_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FiltrarPor(evt);
            }
        });

        bt_limpiarFiltro.setText("Limpiar Filtro");
        bt_limpiarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarFiltroActionPerformed(evt);
            }
        });

        lb_vendedor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_vendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(bt_limpiarFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lb_valorVenta1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_valorVenta1.setText("$");

        lb_valorVenta2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_valorVenta2.setText("$");

        lb_valorVenta3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_valorVenta3.setText("$");

        javax.swing.GroupLayout panelComisionesLayout = new javax.swing.GroupLayout(panelComisiones);
        panelComisiones.setLayout(panelComisionesLayout);
        panelComisionesLayout.setHorizontalGroup(
            panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComisionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComisionesLayout.createSequentialGroup()
                        .addComponent(bt_masDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_pagarComision, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_salir, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(47, 47, 47)
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_valorVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_valorVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_valorVenta3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lb_comisiones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(lb_valorVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lb_Apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(panelComisionesLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelComisionesLayout.setVerticalGroup(
            panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComisionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComisionesLayout.createSequentialGroup()
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_valorVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_valorVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelComisionesLayout.createSequentialGroup()
                                .addComponent(lb_valorVenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_valorVenta3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelComisionesLayout.createSequentialGroup()
                                .addComponent(lb_comisiones, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb_Apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelComisionesLayout.createSequentialGroup()
                        .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelComisionesLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelComisionesLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bt_masDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_pagarComision, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelComisiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelComisiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed
        final IU_Filtrar filtro = new IU_Filtrar();
        filtro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (filtro.isAceptar()) {
                    TipoComision = filtro.tipoComision();
                    vendedor = filtro.Sel_Vendedor();
                    lb_vendedor.setText(vendedor);
                    Filtrar();
                }
            }
        });
    }//GEN-LAST:event_bt_filtrarActionPerformed

    private void bt_limpiarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarFiltroActionPerformed
        tx_cliente.setText("");
        tx_nroDoc.setText("");
        comisiones_no_pagadas = servicio_cab.Obtener_Comisiones_Pendientes("");
        comisiones_pagadas = todo = null;
        actual = comisiones_no_pagadas;
        Listar(actual);
        vendedor = "";
        TipoComision = 0;
        lb_vendedor.setText(vendedor);
    }//GEN-LAST:event_bt_limpiarFiltroActionPerformed

    private void FiltrarPor(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FiltrarPor
        if (evt.getKeyChar() == 10) {
            String cliente = tx_cliente.getText();
            String nro = tx_nroDoc.getText();

            ArrayList<Cabeces> filtrado = new ArrayList<>();

            for (Object o : actual) {
                Cabeces c = (Cabeces) o;
                boolean cli = false;
                boolean doc = false;
                if (c.getClientes().getNombre().indexOf(cliente) != -1) {
                    cli = true;
                }
                if (c.getId().getNrodocumento().indexOf(nro) != -1) {
                    doc = true;
                }
                if (cli && doc) {
                    filtrado.add(c);
                }
            }
            Listar(filtrado);
        }


    }//GEN-LAST:event_FiltrarPor

    private void bt_masDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_masDatosActionPerformed
        int fila = tb_comision.getSelectedRow();
        if (fila >= 0) {
            if (detalles == null) {
                Cabeces c = tab_comision.GetCabecera(fila);
                detalles = new UI_MostrarDetalles(c);
            } else {
                detalles.dispose();
                detalles = null;
                bt_masDatos.doClick();
            }
        }
    }//GEN-LAST:event_bt_masDatosActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_pagarComisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_pagarComisionActionPerformed
        if (!lb_Apagar.getText().equals("0.0")) {
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (opcion == tm.SI) {
                ArrayList<Cabeces> selec = tab_comision.getSeleccionados();
                for (int i = 0; i < selec.size(); i++) {
                    Cabeces c = selec.get(i);
                    c.setComision("*");
                    selec.set(i, c);
                }
                if (servicio_cab.pagarComisiones(selec)) {
                    tm.Mensaje("OPERACION EXITOSA");
                    
                    imprimir(true);
                    tab_comision.BorrarSeleccion();                    
                    Filtrar();
                } else {
                    tm.Error("ERROR AL PAGAR LAS COMISIONES");
                }
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_pagarComisionActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        final int fila = tb_comision.getSelectedRow();
        if (fila >= 0) {
            if (mod_cab == null) {
                Cabeces cab = tab_comision.GetCabecera(fila);
                mod_cab = new IU_ModificarCabecera(cab);
                mod_cab.setServ(servicio_cab);
                mod_cab.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (mod_cab.isExito()) {
                            tab_comision.setValueAt("*", fila, 9);
                            tab_comision.fireTableDataChanged();
                        }
                    }
                });
            } else {
                mod_cab.dispose();
                mod_cab = null;
                bt_modificar.doClick();
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void imprimir(boolean tipo) {
        int opcion =0;
        if (tipo == false) {
            opcion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        }
        if (tipo == true || (tipo == false && opcion == JOptionPane.YES_OPTION)) {
            ArrayList lista = SetLista(tipo);//true : solo los seleccionados, false todos los de la tabla
            Map<String, Object> parametros = SetParametros();
            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
            Servicio_Impresion.exporta(9, lista, parametros, "plantillas/Planillas.pdf");
        }
    }

    private ArrayList SetLista(boolean sel) {
        ArrayList<FilaComision> lista = new ArrayList<>();
        for (int i = 0; i < tab_comision.getRowCount(); i++) {

            if (sel == true) {
                if ((boolean) tab_comision.getValueAt(i, 11) == true) {
                    String tipo = String.valueOf(tab_comision.getValueAt(i, 1));
                    String nroDoc = String.valueOf(tab_comision.getValueAt(i, 2));
                    String fecha = String.valueOf(tab_comision.getValueAt(i, 3));
                    String vend = String.valueOf(tab_comision.getValueAt(i, 4));
                    String cliente_proveedor = String.valueOf(tab_comision.getValueAt(i, 5));
                    String valorVenta = GLOSA_DOLARES + " " + String.valueOf(tab_comision.getValueAt(i, 7));
                    String comision = GLOSA_DOLARES + " " + String.valueOf(tab_comision.getValueAt(i, 8));
                    lista.add(new FilaComision(tipo, nroDoc, fecha, vend, cliente_proveedor, valorVenta, comision));
                }
            } else {
                String tipo = String.valueOf(tab_comision.getValueAt(i, 1));
                String nroDoc = String.valueOf(tab_comision.getValueAt(i, 2));
                String fecha = String.valueOf(tab_comision.getValueAt(i, 3));
                String vend = String.valueOf(tab_comision.getValueAt(i, 4));
                String cliente_proveedor = String.valueOf(tab_comision.getValueAt(i, 5));
                String valorVenta = GLOSA_DOLARES + " " + String.valueOf(tab_comision.getValueAt(i, 7));
                String comision = GLOSA_DOLARES + " " + String.valueOf(tab_comision.getValueAt(i, 8));
                lista.add(new FilaComision(tipo, nroDoc, fecha, vend, cliente_proveedor, valorVenta, comision));
            }
        }
        return lista;
    }

    private Map SetParametros() {
        Map<String, Object> mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        mapa.put("totalcomisiones", GLOSA_DOLARES + " " + lb_Apagar.getText());
        mapa.put("vendedor", lb_vendedor.getText());
        return mapa;
    }

    private void Filtrar() {
        tx_cliente.setText("");
        tx_nroDoc.setText("");
        switch (TipoComision) {
            case 1:
                comisiones_pagadas = servicio_cab.Obtener_Comisiones_Pagadas(vendedor);
                comisiones_no_pagadas = todo = null;
                actual = comisiones_pagadas;
                break;
            case 2:
                comisiones_no_pagadas = servicio_cab.Obtener_Comisiones_Pendientes(vendedor);
                comisiones_pagadas = todo = null;
                actual = comisiones_no_pagadas;
                break;
            case 3:
                todo = servicio_cab.Obtener_Comisiones(vendedor);
                actual = todo;
                comisiones_no_pagadas = comisiones_pagadas = null;
                break;
            case 4:
                actual = tab_comision.getSeleccionados();
                comisiones_no_pagadas = comisiones_pagadas = todo = null;
                break;
        }
        Listar(actual);
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_comision.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_comision.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_comision.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 8:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 9:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 10:
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 11:
                    anchoColumna = (5 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_filtrar;
    public javax.swing.JButton bt_limpiarFiltro;
    public javax.swing.JButton bt_masDatos;
    public javax.swing.JButton bt_modificar;
    public javax.swing.JButton bt_pagarComision;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lb_Apagar;
    public javax.swing.JLabel lb_comisiones;
    public javax.swing.JLabel lb_valorVenta;
    public javax.swing.JLabel lb_valorVenta1;
    public javax.swing.JLabel lb_valorVenta2;
    public javax.swing.JLabel lb_valorVenta3;
    public javax.swing.JLabel lb_vendedor;
    public javax.swing.JPanel panelComisiones;
    public javax.swing.JTable tb_comision;
    public javax.swing.JTextField tx_cliente;
    public javax.swing.JTextField tx_nroDoc;
    // End of variables declaration//GEN-END:variables
}
