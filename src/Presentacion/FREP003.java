package Presentacion;

import Entidades.Equipos;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Equipos;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily
 */
public class FREP003 extends javax.swing.JFrame {

    int numeroequipos;
    Servicio_Equipos sli;
    int fila;
    private boolean seleccionada;
    public JTextField[] componentes;
    DefaultTableModel modelo;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    TipoMensaje tm;
    private Servicio_Equipos sl;
    private DefaultTableModel table;
    int ultimo_id;
    int num_lin;

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }

    public FREP003() {

        initComponents();
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) tablaCodigoEquipos.getModel();
        table = (DefaultTableModel) tablaCodigoEquipos.getModel();
        sli = new Servicio_Equipos(this);
        tm = new TipoMensaje();

        txtid.setText(String.valueOf(sli.nextId()));
        Listar_Equipos();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnagregar);
        asignarEvento();

        sl = new Servicio_Equipos(this);
    }

    private void crearArrayComponente() {
        componentes = new JTextField[5];
        componentes[0] = txtdescripcion;
        componentes[1] = txtdescuento;
        componentes[2] = txtdescuento2;
        componentes[3] = txtdescuento3;
        componentes[4] = txtdescuento4;
    }

    private void crearArrayNumMax() {
        numMaximo.add(40);
        numMaximo.add(13);
        numMaximo.add(13);
        numMaximo.add(13);
        numMaximo.add(13);
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("D");
        tipoDato.add("D");
        tipoDato.add("D");
        tipoDato.add("D");
    }

    private void Listar_Equipos() {
        sli.Listar_equipos();
    }

    private String validarEntradas() {
        String mensaje = "";
        if (txtdescripcion.getText().equals("")) {
            mensaje = mensaje + "Ingrese descripción de Línea";
        }
        return mensaje;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelCodigoEquipos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtdescuento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdescuento2 = new javax.swing.JTextField();
        txtdescuento3 = new javax.swing.JTextField();
        txtdescuento4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCodigoEquipos = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnexportar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Linea", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jLabel1.setText("Código de Equipo");

        jLabel2.setText("Marca");

        txtid.setEnabled(false);

        jLabel4.setText("Equipo");

        jLabel3.setText("( * )");

        jLabel5.setText("Modelo");

        jLabel7.setText("Descuento 3");

        jLabel8.setText("Descuento 4 ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtdescuento4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addComponent(txtdescuento3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtdescuento2, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 285, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdescuento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescuento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescuento4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Equipos");

        tablaCodigoEquipos.setAutoCreateRowSorter(true);
        tablaCodigoEquipos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tablaCodigoEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Producto", "Equipo", "Marca", "Modelo", "Descuento 3", "Descuento 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCodigoEquipos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaCodigoEquipos.getTableHeader().setReorderingAllowed(false);
        tablaCodigoEquipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCodigoEquiposMouseReleased(evt);
            }
        });
        tablaCodigoEquipos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaCodigoEquiposKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCodigoEquipos);
        if (tablaCodigoEquipos.getColumnModel().getColumnCount() > 0) {
            tablaCodigoEquipos.getColumnModel().getColumn(0).setResizable(false);
            tablaCodigoEquipos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaCodigoEquipos.getColumnModel().getColumn(1).setResizable(false);
            tablaCodigoEquipos.getColumnModel().getColumn(1).setPreferredWidth(300);
            tablaCodigoEquipos.getColumnModel().getColumn(2).setResizable(false);
            tablaCodigoEquipos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaCodigoEquipos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tablaCodigoEquipos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tablaCodigoEquipos.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        btnmodificar.setText("Modificar");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnexportar.setText("Exportar");
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCodigoEquiposLayout = new javax.swing.GroupLayout(panelCodigoEquipos);
        panelCodigoEquipos.setLayout(panelCodigoEquiposLayout);
        panelCodigoEquiposLayout.setHorizontalGroup(
            panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                .addGroup(panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                        .addGroup(panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoEquiposLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCodigoEquiposLayout.setVerticalGroup(
            panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoEquiposLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCodigoEquipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCodigoEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed

        if (seleccionada == false) {
            String validacion = validarEntradas();
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    double descuento = validarDoble(txtdescuento);
                    double descuento2 = validarDoble(txtdescuento2);
                    double descuento3 = validarDoble(txtdescuento3);
                    double descuento4 = validarDoble(txtdescuento4);

                    Equipos li = new Equipos();
                    li.setIdequipo(id);
                    li.setDescripcion(descripcion);
                    li.setDescuento1(validarDoble(txtdescuento));
                    li.setDescuento2(validarDoble(txtdescuento2));
                    li.setDescuento3(validarDoble(txtdescuento3));
                    li.setDescuento4(validarDoble(txtdescuento4));

                    if (sli.addEquipos(li)) {
                        JOptionPane.showMessageDialog(null, "Operacion exitosa");
                        Object[] row = {id, descripcion, descuento, descuento2, descuento3, descuento4};
                        table.addRow(row);
                        clean();
                        txtid.setText(String.valueOf(sli.nextId()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ESTA LINEA YA SE ENCUENTRA REGISTRADA", "Error al agregar", 0);
        }
    }//GEN-LAST:event_btnagregarActionPerformed

    private double validarDoble(JTextField txt) {
        if (txt.getText().equals("")) {
            return 0.0;
        } else {
            return Double.parseDouble(txt.getText());
        }
    }

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

        if (seleccionada == true) {
            if (JOptionPane.showConfirmDialog(this, "¿ Desea continuar con la Operacion ?", "Confirmacion", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                if (sli.borrarEquipos(sli.getEquipos(Integer.parseInt(txtid.getText())))) {
                    JOptionPane.showMessageDialog(null, "Operacion exitosa");
                    table.removeRow(fila);
                    tablaCodigoEquipos.clearSelection();
                    seleccionada = false;
                    clean();
                    txtid.setText(String.valueOf(sli.nextId()));
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operacion");
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_LINEAS);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    public boolean actualizarEquipos(Equipos l) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(l);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            return false;
        }

    }

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        String validacion = validarEntradas();
        if (seleccionada == true) {
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {

                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    double descuento = validarDoble(txtdescuento);
                    double descuento2 = validarDoble(txtdescuento2);
                    double descuento3 = validarDoble(txtdescuento3);
                    double descuento4 = validarDoble(txtdescuento4);

                    Equipos l = new Equipos();
                    l.setIdequipo(id);
                    l.setDescripcion(descripcion);
                    l.setDescuento1(descuento);
                    l.setDescuento2(descuento2);
                    l.setDescuento3(descuento3);
                    l.setDescuento4(descuento4);

                    if (actualizarEquipos(l)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaCodigoEquipos.getModel();
                        m.setValueAt(id, fila, 0);
                        m.setValueAt(descripcion, fila, 1);
                        m.setValueAt(descuento, fila, 2);
                        m.setValueAt(descuento2, fila, 3);
                        m.setValueAt(descuento3, fila, 4);
                        m.setValueAt(descuento4, fila, 5);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_LINEAS);
        }


    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        limpiar();
        tablaCodigoEquipos.clearSelection();
        seleccionada = false;
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        Servicio_Excel servicio_excel;
        servicio_excel = new Servicio_Excel(tablaCodigoEquipos, this);
        servicio_excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnexportarActionPerformed

    private void tablaCodigoEquiposKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaCodigoEquiposKeyReleased
        fila = tablaCodigoEquipos.getSelectedRow();
        String id = tablaCodigoEquipos.getValueAt(fila, 0).toString();
        String descripcion = tablaCodigoEquipos.getValueAt(fila, 1).toString();
        double descuento = (double) tablaCodigoEquipos.getValueAt(fila, 2);
        double descuento2 = (double) tablaCodigoEquipos.getValueAt(fila, 3);
        double descuento3 = (double) tablaCodigoEquipos.getValueAt(fila, 4);
        double descuento4 = (double) tablaCodigoEquipos.getValueAt(fila, 5);

        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        txtdescuento.setText(String.valueOf(descuento));
        txtdescuento2.setText(String.valueOf(descuento2));
        txtdescuento3.setText(String.valueOf(descuento3));
        txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoEquiposKeyReleased

    private void tablaCodigoEquiposMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCodigoEquiposMouseReleased
        fila = tablaCodigoEquipos.getSelectedRow();
        String id = tablaCodigoEquipos.getValueAt(fila, 0).toString();
        String descripcion = tablaCodigoEquipos.getValueAt(fila, 1).toString();
        double descuento = (double) tablaCodigoEquipos.getValueAt(fila, 2);
        double descuento2 = (double) tablaCodigoEquipos.getValueAt(fila, 3);
        double descuento3 = (double) tablaCodigoEquipos.getValueAt(fila, 4);
        double descuento4 = (double) tablaCodigoEquipos.getValueAt(fila, 5);

        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        txtdescuento.setText(String.valueOf(descuento));
        txtdescuento2.setText(String.valueOf(descuento2));
        txtdescuento3.setText(String.valueOf(descuento3));
        txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoEquiposMouseReleased

    public void limpiar() {

        txtdescripcion.setText("");
        txtdescuento.setText("");
        txtdescuento2.setText("");
        txtdescuento3.setText("");
        txtdescuento4.setText("");
        if (table.getRowCount() != 0) {
            int num = Integer.parseInt(table.getValueAt(table.getRowCount() - 1, 0).toString()) + 1;
            txtid.setText(String.valueOf(num));
        } else {
            txtid.setText("1");
        }

    }

    private void clean() {
        for (JTextField j : componentes) {
            j.setText("");
        }
        tablaCodigoEquipos.clearSelection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnagregar;
    public javax.swing.JButton btneliminar;
    public javax.swing.JButton btnexportar;
    public javax.swing.JButton btnlimpiar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JButton btnsalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JPanel panelCodigoEquipos;
    public javax.swing.JTable tablaCodigoEquipos;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtdescuento;
    public javax.swing.JTextField txtdescuento2;
    public javax.swing.JTextField txtdescuento3;
    public javax.swing.JTextField txtdescuento4;
    public javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
