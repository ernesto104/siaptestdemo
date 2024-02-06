
package Presentacion;

import Entidades.Transportistas;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Transportista;
import Servicios.TipoMensaje;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowSorter;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Cristian
 */
public class FREP005 extends javax.swing.JFrame {

    private DefaultTableModel table;
    private DefaultTableCellRenderer render;
    private Servicio_Transportista st;
    private Navegacion nav;
    TipoMensaje tm;
    boolean modificar;

    private JTableHeader header;
    private JPopupMenu renamePopup;
    private JTextField text;
    private TableColumn column;

    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    private Servicio_Excel se;
    RowSorter<TableModel> sorter;

    public FREP005() {
        initComponents();
        st = new Servicio_Transportista(this);
        se = new Servicio_Excel(tb_transp, this);

        table = (DefaultTableModel) tb_transp.getModel();


        sorter = new TableRowSorter<TableModel>(table);

        tb_transp.setRowSorter(sorter);


        this.setLocationRelativeTo(null);
        crearArrayComponente();
        tm = new TipoMensaje();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        //centrarDatos();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_agregar);
        st.listar();
        asignarEvento();
        setAnchoColumnas();
        SetParametrosTableHeader();
        modificar = false;
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_transp.getTableHeader().getDefaultRenderer();
        tb_transp.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayComponente() {
        componentes = new JTextField[5];
        componentes[0] = tx_ruc;
        componentes[1] = tx_nombre;
        componentes[2] = tx_direc;
        componentes[3] = tx_tel1;
        componentes[4] = tx_tel2;
    }

    private void crearArrayNumMax() {
        numMaximo.add(11);
        numMaximo.add(50);
        numMaximo.add(50);
        numMaximo.add(20);
        numMaximo.add(20);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("I");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTransportista = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tx_nombre = new javax.swing.JTextField();
        tx_ruc = new javax.swing.JTextField();
        tx_tel1 = new javax.swing.JTextField();
        tx_tel2 = new javax.swing.JTextField();
        tx_direc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_transp = new javax.swing.JTable();
        bt_modificar = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_exportar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Transportistas");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Transportista"));

        jLabel2.setText("Nombre");

        jLabel3.setText("RUC");

        jLabel4.setText("Telefono 1");

        jLabel5.setText("Telefono 2");

        jLabel6.setText("Direccion");

        tx_nombre.setNextFocusableComponent(tx_direc);

        tx_ruc.setNextFocusableComponent(tx_nombre);

        tx_tel1.setNextFocusableComponent(tx_tel2);

        tx_tel2.setNextFocusableComponent(bt_agregar);

        tx_direc.setNextFocusableComponent(tx_tel1);

        jLabel7.setText("(*)");

        jLabel8.setText("(*)");

        jLabel9.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_direc, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_tel1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(tx_tel2))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tx_tel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_tel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_direc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tb_transp.setAutoCreateRowSorter(true);
        tb_transp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE (BUSCAR)", "RUC", "DIRECCION", "TELEFONO 1", "TELEFONO 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tb_transp.setToolTipText("");
        tb_transp.setRowSelectionAllowed(false);
        tb_transp.setRowSorter(null);
        tb_transp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_transp.getTableHeader().setReorderingAllowed(false);
        tb_transp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_transpMouseReleased(evt);
            }
        });
        tb_transp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_transpKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_transp);
        if (tb_transp.getColumnModel().getColumnCount() > 0) {
            tb_transp.getColumnModel().getColumn(0).setPreferredWidth(60);
            tb_transp.getColumnModel().getColumn(1).setPreferredWidth(60);
            tb_transp.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_transp.getColumnModel().getColumn(3).setMinWidth(100);
            tb_transp.getColumnModel().getColumn(3).setPreferredWidth(10);
            tb_transp.getColumnModel().getColumn(3).setMaxWidth(100);
            tb_transp.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        bt_modificar.setMnemonic('M');
        bt_modificar.setText("Modificar");
        bt_modificar.setToolTipText("Modifica los datos de un Transportista");
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });

        bt_agregar.setMnemonic('A');
        bt_agregar.setText("Agregar");
        bt_agregar.setToolTipText("Agrega Nuevo Transportista");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_eliminar.setMnemonic('E');
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setToolTipText("Elimina un Transportista");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_exportar.setMnemonic('x');
        bt_exportar.setText("Exportar");
        bt_exportar.setToolTipText("Pasar los datos de la tabla a una hoja Excel");
        bt_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exportarActionPerformed(evt);
            }
        });

        bt_salir.setMnemonic('S');
        bt_salir.setText("Salir");
        bt_salir.setToolTipText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        bt_limpiar.setMnemonic('S');
        bt_limpiar.setText("Limpiar");
        bt_limpiar.setToolTipText("Salir");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("TRANSPORTISTAS");

        javax.swing.GroupLayout panelTransportistaLayout = new javax.swing.GroupLayout(panelTransportista);
        panelTransportista.setLayout(panelTransportistaLayout);
        panelTransportistaLayout.setHorizontalGroup(
            panelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransportistaLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelTransportistaLayout.createSequentialGroup()
                .addGroup(panelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTransportistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(panelTransportistaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTransportistaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelTransportistaLayout.setVerticalGroup(
            panelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransportistaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTransportista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        //     if (tb_transp.getSelectedRow() >= 0 ) {
        String validacion = validarEntradas();
        if ( validacion.equals(tm.VALIDO) ) {
            int verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if ( verif == tm.SI ) {
                String nombre = tx_nombre.getText();
                String direc = tx_direc.getText();
                String ruc = tx_ruc.getText();
                String tel1 = tx_tel1.getText();
                String tel2 = tx_tel2.getText();

                Transportistas t = new Transportistas();
                t.setNombre(nombre);
                t.setDireccion(direc);
                t.setRuc(ruc);
                t.setTelefono(tel1);
                t.setTelefono2(tel2);

                if ( st.addTransportista(t) ) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    Object[] row = {nombre, ruc, direc, tel1, tel2};
                    table.addRow(row);
                    tx_nombre.requestFocus();
                    clean();
                    
                } else {
                    tm.Error(tm.ERROR_GUARDAR);
                }

            }
        } else {
            tm.Error(validacion);
        }
        //  }
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        modificar = true;
        int fila = tb_transp.getSelectedRow();
        if (fila >= 0) {
            String validacion = validarEntradas();
            if (validacion.equals(tm.VALIDO)) {
                int verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                if (verif == tm.SI) {

                    String nombre = tx_nombre.getText();
                    String direc = tx_direc.getText();
                    String ruc = tx_ruc.getText();
                    String tel1 = tx_tel1.getText();
                    String tel2 = tx_tel2.getText();

                    Transportistas t = st.Obtener_Transportista_Por_Ruc(String.valueOf(table.getValueAt(fila, 1)));
                    t.setNombre(nombre);
                    t.setDireccion(direc);
                    t.setRuc(ruc);
                    t.setTelefono(tel1);
                    t.setTelefono2(tel2);
                    if (st.actualizarTransportista(t)) {
                        tm.Mensaje(tm.EXITO_MODIFICAR);
                        actualizarFila(fila);
                        clean();
                    } else {
                        tm.Error(tm.ERROR_MODIFICAR);
                    }
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
        int fila = tb_transp.getSelectedRow();
        int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
        if (fila >= 0) {
            if (opcion == tm.SI) {
                if (st.borrarTransportista(st.Obtener_Transportista_Por_Ruc(String.valueOf(table.getValueAt(fila, 1))))) {
                    tm.Mensaje(tm.EXITO_ELIMINAR);
                    table.removeRow(fila);
                    tb_transp.clearSelection();
                    clean();
                } else {
                    tm.Error(tm.ERROR_ELIMINAR + "\n- TRANSPORTISTA ASOCIADO A CLIENTE Y/0 FACTURA");
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
        tb_transp.clearSelection();
        tx_nombre.requestFocus();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_transpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_transpMouseReleased
        Informacion_Transportista();
    }//GEN-LAST:event_tb_transpMouseReleased

    private void tb_transpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_transpKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_Transportista();
        }
    }//GEN-LAST:event_tb_transpKeyReleased

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed
    private void Informacion_Transportista() {
        int fila = tb_transp.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        if (fila >= 0) {
            Transportistas temp = st.Obtener_Transportista_Por_Ruc(String.valueOf(table.getValueAt(fila, 1)));
            tx_nombre.setText(String.valueOf(temp.getNombre()));
            tx_ruc.setText(String.valueOf(temp.getRuc()));
            tx_direc.setText(String.valueOf(temp.getDireccion()));
            tx_tel1.setText(temp.getTelefono() != null ? String.valueOf(temp.getTelefono()) : "");
            tx_tel2.setText(temp.getTelefono2() != null ? String.valueOf(temp.getTelefono2()) : "");

        }
    }

    private String validarEntradas() {
        String error = "ERROR";
        boolean a = true, b = true, c = true;
        if (!esNumero(tx_ruc.getText())) {
            error += "\n- NUMERO DE RUC INCORRECTO";
            a = false;
        }
        if (tx_ruc.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO RUC";
            b = false;
        }
        if (!tx_ruc.getText().equals("") && tx_ruc.getText().length() != 11) {
            error += "\n- LONGITUD INCORRECTA DEL NUMERO DE RUC";
            c = false;
        }
        if (tx_nombre.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO NOMBRE";
        }
        if (tx_direc.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO DIRECCION";
        }
        if (!"".equals(tx_tel1.getText()) && tx_tel1.getText().length() < 7) {
            error += "\n- LONGITUD INCORRECTA DEL TELEFONO 1";
        }
        if (!"".equals(tx_tel2.getText()) && tx_tel2.getText().length() < 7) {
            error += "\n- LONGITUD INCORRECTA DEL TELEFONO 2";
        }
        if (a && b && c && !modificar) {
            if (st.RucRepetido(tx_ruc.getText())) {
                error += "\n- EL NUMERO DE RUC YA ESTA REGISTRADO, DIGITE OTRO";
            }
        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    private void clean() {
        for (JTextField tx : componentes) {
            tx.setText("");
        }
    }

    private boolean esNumero(String c) {
        int lc = c.length();
        for (int i = 0; i < lc; i++) {
            if (!Character.isDigit(c.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[5];
        row[0] = tx_nombre.getText();
        row[1] = tx_ruc.getText();
        row[2] = tx_direc.getText();
        row[3] = tx_tel1.getText();
        row[4] = tx_tel2.getText();

        table.removeRow(fila);
        table.insertRow(fila, row);

        tb_transp.clearSelection();

    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_transp.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_transp.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_transp.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (15 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    private void BorrarTabla() {
        int numRows = table.getRowCount();
        for (int i = 0; i < numRows; i++) {
            table.removeRow(0);
        }
    }

    private void SetParametrosTableHeader() {
        header = tb_transp.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editColumnAt(event.getPoint());
                }
            }

            private void editColumnAt(Point point) {
                int columnIndex = header.columnAtPoint(point);

                if (columnIndex == 0) {
                    column = header.getColumnModel().getColumn(columnIndex);
                    Rectangle columnRectangle = header.getHeaderRect(columnIndex);

                    text.setText(column.getHeaderValue().toString());
                    renamePopup.setPreferredSize(
                            new Dimension(columnRectangle.width, columnRectangle.height - 1));
                    renamePopup.show(header, columnRectangle.x, 0);

                    text.requestFocusInWindow();
                    text.selectAll();
                }
            }
        });

        text = new JTextField();
        text.setBorder(null);
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxNombre();
            }

            private void BuscarxNombre() {
                String nombre = text.getText();
                if (text.getText().equals("")) {
                    text.setText("NOMBRE (BUSCAR)");
                }
                column.setHeaderValue(text.getText());
                renamePopup.setVisible(false);
                header.repaint();
                BorrarTabla();
                st.ListarxNombre(nombre);
            }
        });

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_agregar;
    public javax.swing.JButton bt_eliminar;
    public javax.swing.JButton bt_exportar;
    public javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_modificar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelTransportista;
    public javax.swing.JTable tb_transp;
    public javax.swing.JTextField tx_direc;
    public javax.swing.JTextField tx_nombre;
    public javax.swing.JTextField tx_ruc;
    public javax.swing.JTextField tx_tel1;
    public javax.swing.JTextField tx_tel2;
    // End of variables declaration//GEN-END:variables
    public JTextField[] componentes;

}
