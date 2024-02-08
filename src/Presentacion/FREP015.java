package Presentacion;

import Entidades.Roles;
import Entidades.Usuarios;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Roles;
import Servicios.Servicio_Usuarios;
import Servicios.TipoMensaje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrador
 */
public class FREP015 extends javax.swing.JFrame {

    private DefaultTableModel table;
    private DefaultTableCellRenderer render;
    private Servicio_Usuarios su;
    TipoMensaje tm;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    JTextField[] componentes;
    boolean modificar;
    RowSorter<TableModel> sorter;

    public FREP015() {
        initComponents();
        setLocationRelativeTo(null);
        tm = new TipoMensaje();
        table = (DefaultTableModel) tb_usuario.getModel();

        sorter = new TableRowSorter<TableModel>(table);

        tb_usuario.setRowSorter(sorter);

        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        su = new Servicio_Usuarios(this);
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_salir);
        asignarEvento();
        su.listar();
        llenar_roles();
        centrarDatos();
        modificar = false;
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_usuario.getTableHeader().getDefaultRenderer();
        tb_usuario.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayComponente() {
        componentes = new JTextField[2];
        componentes[0] = tx_nombre;
        componentes[1] = tx_clave;
    }

    private void crearArrayNumMax() {
        numMaximo.add(35);
        numMaximo.add(10);
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

    private void llenar_roles() {
        Servicio_Roles sr = new Servicio_Roles(null);
        Iterator it = sr.getList().iterator();

        while (it.hasNext()) {
            Roles r = (Roles) it.next();
            cb_rol.addItem(r.getNombre());
        }
    }

    private void centrarDatos() {
        render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tb_usuario.getColumnCount(); i++) {
            if (i >= 2) {
                tb_usuario.getColumnModel().getColumn(i).setCellRenderer(render);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUsuarios = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox();
        cb_rol = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dt_fecha = new com.toedter.calendar.JDateChooser();
        tx_clave = new javax.swing.JPasswordField();
        btn_visualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_usuario = new javax.swing.JTable();
        bt_salir = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Usuarios");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Usuario"));

        jLabel2.setText("Nombre");

        jLabel3.setText("Clave");

        jLabel4.setText("Rol");

        jLabel5.setText("Estado");

        cb_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Alta", "Baja" }));
        cb_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_estadoActionPerformed(evt);
            }
        });

        cb_rol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cb_rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_rolActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha de Expiracion");

        jLabel7.setText("(*)");

        jLabel8.setText("(*)");

        jLabel9.setText("(*)");

        jLabel10.setText("(*)");

        jLabel11.setText("(*)");

        btn_visualizar.setText("Visualizar");
        btn_visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_visualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_visualizar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(106, 106, 106)
                                        .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_visualizar)))
                        .addGap(8, 8, 8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tb_usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Rol", "F. Expiracion", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_usuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_usuario.getTableHeader().setReorderingAllowed(false);
        tb_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_usuarioMouseReleased(evt);
            }
        });
        tb_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_usuarioKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_usuario);
        if (tb_usuario.getColumnModel().getColumnCount() > 0) {
            tb_usuario.getColumnModel().getColumn(0).setPreferredWidth(130);
            tb_usuario.getColumnModel().getColumn(2).setPreferredWidth(30);
            tb_usuario.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("USUARIOS");

        javax.swing.GroupLayout panelUsuariosLayout = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(panelUsuariosLayout);
        panelUsuariosLayout.setHorizontalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUsuariosLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelUsuariosLayout.setVerticalGroup(
            panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                String clave = tx_clave.getText();
                Date fec = dt_fecha.getDate();
                String rol = String.valueOf(cb_rol.getSelectedItem());
                int estado = cb_estado.getSelectedIndex();

                Servicio_Roles sr = new Servicio_Roles(null);
                Roles temp = sr.getRol_por_nombre(rol);
                Usuarios us = new Usuarios();
                us.setNombre(nombre);
                us.setClave(clave);
                us.setFechaExp(fec);
                us.setRoles(temp);
                us.setEstado(estado);
                if (su.addUsuarios(us)) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    Object[] row = {nombre,
                        rol,
                        new SimpleDateFormat("dd/MM/yyyy").format(fec),
                        estado};
                    table.addRow(row);
                    tx_nombre.requestFocus();
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
        if (dt_fecha.getDate() != null) {
            int fila = tb_usuario.getSelectedRow();
            String validacion = validarEntradas();
            if (validacion.equals(tm.VALIDO)) {
                if (fila >= 0) {
                    int verif;
                    verif = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                    if (verif == tm.SI) {
                        String nombre = tx_nombre.getText();
                        String clave = tx_clave.getText();
                        Date fec = dt_fecha.getDate();
                        String rol = String.valueOf(cb_rol.getSelectedItem());
                        int estado = cb_estado.getSelectedIndex();
                        Servicio_Roles sr = new Servicio_Roles(null);
                        Roles temp = sr.getRol_por_nombre(rol);
                        Usuarios user = su.getUsuario_Nombre(String.valueOf(table.getValueAt(fila, 0)));
                        user.setNombre(nombre);
                        user.setClave(clave);
                        user.setFechaExp(fec);
                        user.setRoles(temp);
                        user.setEstado(estado);

                        if (su.actualizarUsuarios(user)) {
                            tm.Mensaje(tm.EXITO_MODIFICAR);
                            actualizarFila(fila);
                            clean();
                        } else {
                            tm.Error(tm.ERROR_MODIFICAR);
                        }
                    } else {
                        tm.Error(tm.SELECCION_REGISTRO);
                    }
                }
            } else {
                tm.Error(validacion);
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA FECHA", "FECHA VACIA", JOptionPane.ERROR_MESSAGE);
        }
        modificar = false;
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        clean();
        cb_estado.setSelectedIndex(0);
        cb_rol.setSelectedIndex(0);
        tb_usuario.clearSelection();
        dt_fecha.setDate(null);
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_usuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_usuarioMouseReleased
        Informacion_Usuario();
    }//GEN-LAST:event_tb_usuarioMouseReleased

    private void tb_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_usuarioKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_Usuario();
        }
    }//GEN-LAST:event_tb_usuarioKeyReleased

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void btn_visualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_visualizarActionPerformed
        JOptionPane.showMessageDialog(null, "La clave es :" + tx_clave.getText(), "Clave", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_visualizarActionPerformed

    private void cb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_estadoActionPerformed

    private void cb_rolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_rolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_rolActionPerformed
    private void Informacion_Usuario() {
        int fila = tb_usuario.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        Usuarios user = su.getUsuario_Nombre(String.valueOf(table.getValueAt(fila, 0)));
        tx_nombre.setText(user.getNombre());
        tx_clave.setText(user.getClave());
        dt_fecha.setDate(user.getFechaExp());
        cb_rol.setSelectedItem(user.getRoles().getNombre());
        cb_estado.setSelectedIndex(user.getEstado());
    }

    private void clean() {
        for (JTextField j : componentes) {
            j.setText("");
        }
        tb_usuario.clearSelection();
        cb_estado.setSelectedIndex(0);
        cb_rol.setSelectedIndex(0);
        dt_fecha.setDate(null);
    }

    private String validarEntradas() {
        String error = "ERROR";
        boolean a = true;
        if (tx_nombre.getText().equals("")) {
            error += "\n- COMPLETE CAMPO NOMBRE";
            a = false;
        }
        if (tx_clave.getText().equals("")) {
            error += "\n- COMPLETE CAMPO CLAVE";
        }

        if (cb_rol.getSelectedIndex() == 0) {
            error += "\n- SELECCIONE UN ROL";
        }
        if (cb_estado.getSelectedIndex() == 0) {
            error += "\n- SELECCIONE UN ESTADO PARA EL USUARIO";
        }
        if (dt_fecha.getDate() == null || dt_fecha.getDate().compareTo(Calendar.getInstance().getTime()) < 0) {
            error += "\n- SELECCIONE UNA CORRECTA FECHA DE EXPIRACION";
        }
        if (a && !modificar) {
            if (su.getUsuario_Nombre(tx_nombre.getText()) != null) {
                error += "\n- YA EXISTE UN USUARIO CON ESE NOMBRE";
            }
        }

        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[5];
        row[0] = tx_nombre.getText();
        row[1] = String.valueOf(cb_rol.getSelectedItem());
        row[2] = new SimpleDateFormat("dd/MM/yyyy").format(dt_fecha.getDate());
        row[3] = cb_estado.getSelectedItem();

        table.removeRow(fila);
        table.insertRow(fila, row);

        tb_usuario.clearSelection();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_agregar;
    public javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_modificar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JButton btn_visualizar;
    public javax.swing.JComboBox cb_estado;
    public javax.swing.JComboBox cb_rol;
    public com.toedter.calendar.JDateChooser dt_fecha;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
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
    public javax.swing.JPanel panelUsuarios;
    public javax.swing.JTable tb_usuario;
    public javax.swing.JPasswordField tx_clave;
    public javax.swing.JTextField tx_nombre;
    // End of variables declaration//GEN-END:variables
}
