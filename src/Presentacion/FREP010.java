package Presentacion;

import Entidades.Estratificacion;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Estratificacion;
import Servicios.Servicio_Excel;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fabrica2
 */
public class FREP010 extends javax.swing.JFrame {

    private DefaultTableModel table;
    private DefaultTableCellRenderer render;
    private Servicio_Estratificacion set;
    private Navegacion nav;
    TipoMensaje tm;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    private Servicio_Excel se;
    JTextField[] componentes;
    boolean modificar;
    RowSorter<TableModel> sorter;

    public FREP010() {
        initComponents();
        tm = new TipoMensaje();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        set = new Servicio_Estratificacion(this);
        se = new Servicio_Excel(tb_estr, this);
        table = (DefaultTableModel) tb_estr.getModel();
        sorter = new TableRowSorter<TableModel>(table);
        tb_estr.setRowSorter(sorter);
        setLocationRelativeTo(null);
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_agregar);
        asignarEvento();
        setAnchoColumnas();
        centrarDatos();
        set.listar();
        modificar = false;
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_estr.getTableHeader().getDefaultRenderer();
        tb_estr.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayComponente() {
        componentes = new JTextField[5];
        componentes[0] = tx_codigo;
        componentes[1] = tx_desc;
        componentes[2] = tx_ctdesde;
        componentes[3] = tx_cthasta;
        componentes[4] = tx_meses;
    }

    private void crearArrayNumMax() {
        numMaximo.add(1);
        numMaximo.add(50);
        numMaximo.add(6);
        numMaximo.add(6);
        numMaximo.add(2);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("I");
        tipoDato.add("I");
        tipoDato.add("I");
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void centrarDatos() {
        render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tb_estr.getColumnCount(); i++) {
            if (i != 1) {
                tb_estr.getColumnModel().getColumn(i).setCellRenderer(render);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEstratificacion = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_codigo = new javax.swing.JTextField();
        tx_ctdesde = new javax.swing.JTextField();
        tx_cthasta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_desc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx_meses = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_estr = new javax.swing.JTable();
        bt_agregar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_exportar = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Estratificacion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Estratificacion"));
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel1.setText("Cantidad desde ");

        jLabel2.setText("Codigo ");

        jLabel3.setText("Cantidad hasta");

        jLabel4.setText("Descripcion ");

        jLabel5.setText("N° de meses");

        jLabel6.setText("(*)");

        jLabel7.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(tx_ctdesde, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(tx_cthasta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_meses, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_ctdesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_cthasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tb_estr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cant. desde", "Cant. hasta", "Meses"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_estr.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_estr.getTableHeader().setReorderingAllowed(false);
        tb_estr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_estrMouseReleased(evt);
            }
        });
        tb_estr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_estrKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_estr);

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_modificar.setText("Modificar");
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });

        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_exportar.setText("Exportar");
        bt_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exportarActionPerformed(evt);
            }
        });

        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ESTRATIFICACION");

        javax.swing.GroupLayout panelEstratificacionLayout = new javax.swing.GroupLayout(panelEstratificacion);
        panelEstratificacion.setLayout(panelEstratificacionLayout);
        panelEstratificacionLayout.setHorizontalGroup(
            panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstratificacionLayout.createSequentialGroup()
                .addGroup(panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEstratificacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEstratificacionLayout.createSequentialGroup()
                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelEstratificacionLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelEstratificacionLayout.setVerticalGroup(
            panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstratificacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEstratificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEstratificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed

        String validacion = validarEntradas();
        if (validacion.equals(tm.VALIDO)) {

            int a, b;

            if (!tx_ctdesde.getText().equalsIgnoreCase("") && !tx_cthasta.getText().equalsIgnoreCase("")) {
                a = Integer.parseInt(tx_ctdesde.getText());
                b = Integer.parseInt(tx_cthasta.getText());
            } else {
                a = 0;
                b = 0;
            }

            if (a <= b) {

                int verif;
                int cantdesde = 0;
                int canthasta = 0;
                int meses = 0;
                verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                if (verif == tm.SI) {
                    String codigo = tx_codigo.getText();
                    String descripcion = tx_desc.getText();
                    if (!tx_ctdesde.getText().equals("")) {
                        cantdesde = Integer.parseInt(tx_ctdesde.getText());
                    }
                    if (!tx_cthasta.getText().equals("")) {
                        canthasta = Integer.parseInt(tx_cthasta.getText());
                    }
                    if (!tx_meses.getText().equals("")) {
                        meses = Integer.parseInt(tx_meses.getText());
                    }

                    Estratificacion estr = new Estratificacion();
                    estr.setCodigoestratificacion(codigo);
                    estr.setDescripcion(descripcion);
                    estr.setVentasdesde(cantdesde);
                    estr.setVentashasta(canthasta);
                    estr.setNromeses(meses);

                    if (set.addEstratificacion(estr)) {
                        tm.Mensaje(tm.EXITO_AGREGAR);
                        Object[] row = {codigo, descripcion, cantdesde, canthasta, meses};
                        table.addRow(row);
                        tx_codigo.requestFocus();
                        clean();
                    } else {
                        tm.Error(tm.ERROR_AGREGAR);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "La cantidad hasta debe ser mayor o igual que desde","Validacion",JOptionPane.ERROR_MESSAGE);
            }

        } else {
            tm.Error(validacion);
        }


    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed

        modificar = true;

        int fila = tb_estr.getSelectedRow();
        if (fila >= 0) {
            String validacion = validarEntradas();
            if (validacion.equals(tm.VALIDO)) {

                int a, b;

                if (!tx_ctdesde.getText().equalsIgnoreCase("") && !tx_cthasta.getText().equalsIgnoreCase("")) {
                    a = Integer.parseInt(tx_ctdesde.getText());
                    b = Integer.parseInt(tx_cthasta.getText());
                } else {
                    a = 0;
                    b = 0;
                }

                if (a <= b) {

                    int verif;
                    verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                    if (verif == tm.SI) {
                        int cantdesde = 0;
                        int canthasta = 0;
                        int meses = 0;
                        String codigo = tx_codigo.getText();
                        String descripcion = tx_desc.getText();
                        if (!tx_ctdesde.getText().equals("")) {
                            cantdesde = Integer.parseInt(tx_ctdesde.getText());
                        }
                        if (!tx_cthasta.getText().equals("")) {
                            canthasta = Integer.parseInt(tx_cthasta.getText());
                        }
                        if (!tx_meses.getText().equals("")) {
                            meses = Integer.parseInt(tx_meses.getText());
                        }

                        Estratificacion estr = set.getEstratificacion_por_nombre(String.valueOf(table.getValueAt(fila, 0)));
                        estr.setCodigoestratificacion(codigo);
                        estr.setDescripcion(descripcion);
                        estr.setVentasdesde(cantdesde);
                        estr.setVentashasta(canthasta);
                        estr.setNromeses(meses);

                        if (set.actualizarEstratificacion(estr)) {
                            tm.Mensaje(tm.EXITO_MODIFICAR);
                            actualizarFila(fila);
                            clean();
                        } else {
                            tm.Error(tm.ERROR_MODIFICAR);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null,"La cantidad hasta debe ser mayor o igual que desde", "Validacion",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                tm.Error(validacion);
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }

        modificar = false;
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = tb_estr.getSelectedRow();
        if (fila >= 0) {
            int verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (verif == tm.SI) {
                if (set.borrarEstratificacion(set.getEstratificacion_por_nombre(String.valueOf(table.getValueAt(fila, 0))))) {
                    tm.Mensaje(tm.EXITO_ELIMINAR);
                    table.removeRow(fila);
                    tb_estr.clearSelection();
                    clean();
                } else {
                    tm.Error(tm.ERROR_ELIMINAR + "\n-  ESTRATIFICACION ASOCIADA A REPUESTOS");
                }
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportarActionPerformed
        se.Exportar_Excel(1);
    }//GEN-LAST:event_bt_exportarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        clean();
        tx_codigo.requestFocus();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_estrMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_estrMouseReleased
        Informacion_Estratificacion();
    }//GEN-LAST:event_tb_estrMouseReleased

    private void tb_estrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_estrKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_Estratificacion();
        }
    }//GEN-LAST:event_tb_estrKeyReleased

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void Informacion_Estratificacion() {
        int fila = tb_estr.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        if (fila >= 0) {
            Estratificacion estratificacion = set.getEstratificacion_por_nombre(String.valueOf(table.getValueAt(fila, 0)));
            tx_codigo.setText(estratificacion.getCodigoestratificacion());
            tx_desc.setText(estratificacion.getDescripcion());
            tx_ctdesde.setText(String.valueOf(estratificacion.getVentasdesde()));
            tx_cthasta.setText(String.valueOf(estratificacion.getVentashasta()));
            tx_meses.setText(String.valueOf(estratificacion.getNromeses()));
        }
    }

    private String validarEntradas() {
        String error = "ERROR";
        boolean a = true;
        if (tx_codigo.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO CODIGO";
            a = false;
        }
        if (tx_desc.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO DESCRIPCION";
        }

        if (a && !modificar) {
            if (set.getEstratificacion_por_nombre(tx_codigo.getText()) != null) {
                error += "\n- EL CODIGO ESTA REPETIDO, INGRESE OTRO";
            }
        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    private void clean() {
        for (JTextField j : componentes) {
            j.setText("");
        }
        tb_estr.clearSelection();
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[5];
        row[0] = tx_codigo.getText();
        row[1] = tx_desc.getText();
        row[2] = !tx_ctdesde.getText().equals("") ? tx_ctdesde.getText() : 0;
        row[3] = !tx_cthasta.getText().equals("") ? tx_cthasta.getText() : 0;
        row[4] = !tx_meses.getText().equals("") ? tx_meses.getText() : 0;

        table.removeRow(fila);
        table.insertRow(fila, row);

        tb_estr.clearSelection();

    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_estr.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_estr.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_estr.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (10 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_agregar;
    public javax.swing.JButton bt_eliminar;
    public javax.swing.JButton bt_exportar;
    public javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_modificar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelEstratificacion;
    public javax.swing.JTable tb_estr;
    public javax.swing.JTextField tx_codigo;
    public javax.swing.JTextField tx_ctdesde;
    public javax.swing.JTextField tx_cthasta;
    public javax.swing.JTextField tx_desc;
    public javax.swing.JTextField tx_meses;
    // End of variables declaration//GEN-END:variables
}
