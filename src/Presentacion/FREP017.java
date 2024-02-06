package Presentacion;

import Entidades.Roles;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Roles;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FREP017 extends javax.swing.JFrame {

    private DefaultTableModel table;
    private DefaultTableCellRenderer render;
    private Servicio_Roles sr;
    private Navegacion nav;
    TipoMensaje tm;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    private Servicio_Excel se;
    JTextField[] componentes;
    boolean modificar;
    RowSorter<TableModel> sorter;

    public FREP017() {
        initComponents();
        tm = new TipoMensaje();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        sr = new Servicio_Roles(this);
        se = new Servicio_Excel(tb_roles, this);
        centrarDatos();
        table = (DefaultTableModel) tb_roles.getModel();

        sorter = new TableRowSorter<TableModel>(table);

        tb_roles.setRowSorter(sorter);

        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_agregar);
        asignarEvento();
        sr.listar();
        modificar = false;
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_roles.getTableHeader().getDefaultRenderer();
        tb_roles.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayComponente() {
        componentes = new JTextField[2];
        componentes[0] = tx_codigo;
        componentes[1] = tx_nombre;
    }

    private void crearArrayNumMax() {
        numMaximo.add(10);
        numMaximo.add(40);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void centrarDatos() {
        render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tb_roles.getColumnCount(); i++) {
            tb_roles.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRol = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tx_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_roles = new javax.swing.JTable();
        bt_salir = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Roles");
        setBackground(new java.awt.Color(255, 51, 204));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Rol"));

        jLabel2.setText("Nombre");

        jLabel4.setText("(*)");

        jLabel3.setText("Codigo");

        jLabel5.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tb_roles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_roles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_roles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_rolesMouseReleased(evt);
            }
        });
        tb_roles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_rolesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_roles);
        tb_roles.getColumnModel().getColumn(0).setPreferredWidth(10);
        tb_roles.getColumnModel().getColumn(1).setPreferredWidth(300);

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_modificar.setText("Modificar");
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ROLES");

        javax.swing.GroupLayout panelRolLayout = new javax.swing.GroupLayout(panelRol);
        panelRol.setLayout(panelRolLayout);
        panelRolLayout.setHorizontalGroup(
            panelRolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRolLayout.createSequentialGroup()
                .addGroup(panelRolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRolLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRolLayout.createSequentialGroup()
                        .addGroup(panelRolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRolLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRolLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRolLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRolLayout.setVerticalGroup(
            panelRolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRolLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed

        String validacion = validarEntradas();
        if (validacion.equals(tm.VALIDO)) {
            int verif;
            verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (verif == tm.SI) {
                String nombre = tx_nombre.getText();
                String codigo = tx_codigo.getText();

                Roles rol = new Roles();
                rol.setNombre(nombre);
                rol.setCodigorol(codigo);
                if (sr.addRol(rol)) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    Object[] row = {codigo, nombre};
                    table.addRow(row);

                    clean();
                } else {
                    tm.Error(tm.ERROR_AGREGAR);
                }
            }
        } else {
            tm.Error(validacion);
        }

    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        modificar = true;
        int fila = tb_roles.getSelectedRow();
        if (fila >= 0) {
            String validacion = validarEntradas();
            if (validacion.equals(tm.VALIDO)) {
                int verif;
                verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                if (verif == tm.SI) {
                    String nombre = tx_nombre.getText();
                    String codrol = tx_codigo.getText();
                    Roles t = sr.getRol_por_nombre(String.valueOf(table.getValueAt(fila, 1)));
                    t.setNombre(nombre);
                    t.setCodigorol(codrol);
                    if (sr.actualizarRol(t)) {
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
        int fila = tb_roles.getSelectedRow();
        if (fila >= 0) {
            int verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            if (verif == tm.SI) {
                if (sr.borrarRol(sr.getRol_por_nombre(String.valueOf(table.getValueAt(fila, 1))))) {
                    tm.Mensaje(tm.EXITO_ELIMINAR);
                    table.removeRow(fila);
                    tb_roles.clearSelection();
                    clean();
                    tx_nombre.requestFocus();
                } else {
                    tm.Error(tm.ERROR_ELIMINAR + "\n-  ROL ASOCIADO A USUARIO Y/O ACCESOS Y/O CONTROL");
                }
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        clean();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_rolesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_rolesMouseReleased
        Informacion_Rol();
    }//GEN-LAST:event_tb_rolesMouseReleased

    private void tb_rolesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_rolesKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_Rol();
        }
    }//GEN-LAST:event_tb_rolesKeyReleased

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed
    private void Informacion_Rol() {
        int fila = tb_roles.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        if (fila >= 0) {
            Roles r = sr.getRol_por_nombre(String.valueOf(table.getValueAt(fila, 1)));
            tx_codigo.setText(r.getCodigorol());
            tx_nombre.setText(r.getNombre());
        }
    }

    private void clean() {
        for (JTextField j : componentes) {
            j.setText("");
        }
        tb_roles.clearSelection();
    }

    private String validarEntradas() {
        String error = "ERROR";
        boolean a = true;
        if (tx_codigo.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO CODIGO";
            a = false;
        }
        if (tx_nombre.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO NOMBRE";
        }
        if (a && !modificar) {
            if (sr.getRol_por_codigo(tx_codigo.getText()) != null) {
                error += "\n- EL ROL YA SE ENCUENTRA REGISTRADO, INGRESE OTRO";
            }
        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[2];
        row[0] = tx_codigo.getText();
        row[1] = tx_nombre.getText();

        table.removeRow(fila);
        table.insertRow(fila, row);
        tb_roles.clearSelection();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_agregar;
    public javax.swing.JButton bt_eliminar;
    public javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_modificar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelRol;
    public javax.swing.JTable tb_roles;
    public javax.swing.JTextField tx_codigo;
    public javax.swing.JTextField tx_nombre;
    // End of variables declaration//GEN-END:variables
}
