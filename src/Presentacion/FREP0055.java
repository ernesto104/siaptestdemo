package Presentacion;

import Entidades.Equipos;
import Entidades.Marcas;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.Servicio_Equipos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Marcas;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily
 */
public class FREP0055 extends javax.swing.JFrame {

    int numeroequipos;
    Servicio_Marcas smarc;
    int fila;
    private boolean seleccionada;
    public JTextField[] componentes;
    DefaultTableModel modelo;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    TipoMensaje tm;
    private Servicio_Marcas sl;
    private DefaultTableModel table;
    int ultimo_id;
    int num_lin;

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }

    public FREP0055() {

        initComponents();
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) tablaCodigoMarcas.getModel();
        table = (DefaultTableModel) tablaCodigoMarcas.getModel();
        smarc = new Servicio_Marcas(this);
        tm = new TipoMensaje();

        txtid.setText(String.valueOf(smarc.nextId()));
        //Listar_Marcas();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        llenar_equipos();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnagregar);
        asignarEvento();

        sl = new Servicio_Marcas(this);
        
        //para ocultar botones (se desplazo a otra vista el boton Eliminar)
        btneliminar.setVisible(false);
        jLabel5.setVisible(false);
        jLabel7.setVisible(false);
        txtdescuento2.setVisible(false);
        txtdescuento3.setVisible(false);
        btndesactivar.setVisible(false);
        
        
        //se ocultan elementos hasta seleccionar un equipo
        seleccionarVisibilidadElementosMarca(false);
              
    }
    
    private void seleccionarVisibilidadElementosMarca (boolean estado) {
        labeltextid.setVisible(estado);
        txtid.setVisible(estado);
        labeltextdescripcion.setVisible(estado);
        txtdescripcion.setVisible(estado);
        jLabel3 .setVisible(estado);       
        labelestado.setVisible(estado);
        comboEstado.setVisible(estado);
    }

    private void crearArrayComponente() {
        componentes = new JTextField[3];
        componentes[0] = txtdescripcion;
        //componentes[1] = txtdescuento;
        componentes[1] = txtdescuento2;
        componentes[2] = txtdescuento3;
        //componentes[3] = txtdescuento4;
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
    //generar contenido de tabla
    private void Listar_Marcas() {
        smarc.Listar_marcas();
    }
    
    private void Listar_MarcasxEquipo(Equipos equipo) {
        BorrarTabla();
        smarc.ListarMarcasx_Equipo(equipo);
    }

    
    private void BorrarTabla() {
        int numRows = table.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            table.removeRow(0);
        }
    }
    
    
    private String validarEntradas() {
        String mensaje = "ERROR";
        
        if (comboEquipo.getSelectedIndex() == 0) {
            mensaje += "\n- Seleccione un equipo encasillar la marca";
        }
        
        if (txtdescripcion.getText().equals("")) {
            mensaje = mensaje + "\n- Ingrese descripción de Marca";
        }
        
        
        if (comboEstado.getSelectedIndex() == 0) {
            mensaje += "\n- Seleccione un estado para el equipo";
        }
        
        
       /* if (mensaje.equals("")) {
            return tm.VALIDO;
        } else {*/
            return mensaje;
        //}
   
    }
    
   private void llenar_equipos() {
       Servicio_Equipos se = new Servicio_Equipos(null);
       Iterator it = se.getList().iterator();

       while (it.hasNext()) {
           Equipos e = (Equipos) it.next();
           comboEquipo.addItem(e.getDescripcion());
       }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btneliminar = new javax.swing.JButton();
        panelCodigoMarcas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labeltextid = new javax.swing.JLabel();
        labelestado = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        labeltextdescripcion = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdescuento2 = new javax.swing.JTextField();
        txtdescuento3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        comboEquipo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        textidequipo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCodigoMarcas = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnexportar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btndesactivar = new javax.swing.JButton();

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

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Marca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        labeltextid.setText("Código de Marcas");

        labelestado.setText("Estado");

        txtid.setEnabled(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        labeltextdescripcion.setText("Descripcion");

        jLabel3.setText("( * )");

        jLabel5.setText("Modelo");

        txtdescuento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescuento2ActionPerformed(evt);
            }
        });

        txtdescuento3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescuento3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Descuento 3");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Activado", "Desactivado" }));

        jLabel9.setText("Equipo");

        comboEquipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        comboEquipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEquipoItemStateChanged(evt);
            }
        });

        jLabel10.setText("( * )");

        textidequipo.setEnabled(false);
        textidequipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textidequipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labeltextid)
                    .addComponent(labeltextdescripcion)
                    .addComponent(labelestado)
                    .addComponent(jLabel9))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textidequipo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(285, 285, 285))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtdescuento3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdescuento2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textidequipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeltextid)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labeltextdescripcion)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelestado)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtdescuento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdescuento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Marcas");

        tablaCodigoMarcas.setAutoCreateRowSorter(true);
        tablaCodigoMarcas.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tablaCodigoMarcas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Equipo", "Equipo", "Cod. Marca", "Descripcion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCodigoMarcas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaCodigoMarcas.getTableHeader().setReorderingAllowed(false);
        tablaCodigoMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCodigoMarcasMouseReleased(evt);
            }
        });
        tablaCodigoMarcas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaCodigoMarcasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCodigoMarcas);
        if (tablaCodigoMarcas.getColumnModel().getColumnCount() > 0) {
            tablaCodigoMarcas.getColumnModel().getColumn(0).setPreferredWidth(125);
            tablaCodigoMarcas.getColumnModel().getColumn(1).setPreferredWidth(260);
            tablaCodigoMarcas.getColumnModel().getColumn(2).setPreferredWidth(125);
            tablaCodigoMarcas.getColumnModel().getColumn(3).setPreferredWidth(260);
            tablaCodigoMarcas.getColumnModel().getColumn(4).setPreferredWidth(150);
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

        btndesactivar.setText("Des/Activar");
        btndesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndesactivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCodigoMarcasLayout = new javax.swing.GroupLayout(panelCodigoMarcas);
        panelCodigoMarcas.setLayout(panelCodigoMarcasLayout);
        panelCodigoMarcasLayout.setHorizontalGroup(
            panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoMarcasLayout.createSequentialGroup()
                .addGroup(panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCodigoMarcasLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel6))
                    .addGroup(panelCodigoMarcasLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(btndesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoMarcasLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoMarcasLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoMarcasLayout.createSequentialGroup()
                        .addGroup(panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(panelCodigoMarcasLayout.createSequentialGroup()
                                .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        panelCodigoMarcasLayout.setVerticalGroup(
            panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoMarcasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCodigoMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btndesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCodigoMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCodigoMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed

        if (seleccionada == false) {
            String validacion = validarEntradas();
            
            if (!validacion.equals("ERROR")) {
                //tm.Error(validacion);
                JOptionPane.showMessageDialog(null, validacion, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    //int equipoId2 = Integer.parseInt(comboEquipo.getSelectedItem().toString()); 
                    int equipoId = Integer.parseInt(textidequipo.getText());
                    String equipodescr = comboEquipo.getSelectedItem().toString();
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    String estado = (String) comboEstado.getSelectedItem();
                    //double descuento = validarDoble(txtdescuento);
                    double descuento2 = validarDoble(txtdescuento2);
                    double descuento3 = validarDoble(txtdescuento3);
                    //double descuento4 = validarDoble(txtdescuento4);
                    
                    
                    Servicio_Equipos sequip= new Servicio_Equipos(null);
                    Equipos equip = sequip.getEquipos_por_codigo(equipoId);
                    
                    Marcas ma = new Marcas();
                    ma.setEquipo(equip);
                    ma.setIdmarca(id);
                    ma.setDescripcion(descripcion);
                    ma.setEstado(estado);
                    //li.setDescuento1(validarDoble(txtdescuento));
                    //li.setDescuento2(validarDoble(txtdescuento2));
                    //li.setDescuento3(validarDoble(txtdescuento3));
                   // ma.setDescuento4(validarDoble(txtdescuento4));

                    if (smarc.addMarcas(ma)) {
                        JOptionPane.showMessageDialog(null, "Operacion exitosa");
                        Object[] row = {equipoId, equipodescr, id,/*descuento,*/ descripcion, estado};
                        table.addRow(row);
                        clean();
                        txtid.setText(String.valueOf(smarc.nextId()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ESTA MARCA YA SE ENCUENTRA REGISTRADA\nUTILICE LA OPCION 'LIMPIAR' PARA COMENZAR A AGREGAR UNA NUEVO MARCA", "Error al agregar", 0);
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
                if (smarc.borrarMarcas(smarc.getMarcas(Integer.parseInt(txtid.getText())))) {
                    JOptionPane.showMessageDialog(null, "Operacion exitosa");
                    table.removeRow(fila);
                    tablaCodigoMarcas.clearSelection();
                    seleccionada = false;
                    clean();
                    txtid.setText(String.valueOf(smarc.nextId()));
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operacion");
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_MARCA);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    public boolean actualizarMarcas(Marcas l) {
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
            if (!validacion.equals("ERROR")) {
                JOptionPane.showMessageDialog(null, validacion, "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    int equipoId = Integer.parseInt(textidequipo.getText());
                    String equipodescr = comboEquipo.getSelectedItem().toString();
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    //double descuento = validarDoble(txtdescuento);
                    String estado = (String) comboEstado.getSelectedItem();
                    //double descuento2 = validarDoble(txtdescuento2);
                   /* double descuento3 = validarDoble(txtdescuento3);
                    double descuento4 = validarDoble(txtdescuento4);*/

                    
                    Servicio_Equipos sequip= new Servicio_Equipos(null);
                    Equipos eq = sequip.getEquipos_por_codigo(equipoId);

                    Marcas marc = new Marcas();
                    marc.setIdmarca(id);
                    marc.setEquipo(eq);
                    marc.setDescripcion(descripcion);
                    marc.setEstado(estado);
                   // l.setDescuento1(descuento);
                    //m.setDescuento2(descuento2);
                    /*l.setDescuento3(descuento3);
                    l.setDescuento4(descuento4);*/

                    if (actualizarMarcas(marc)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaCodigoMarcas.getModel();
                        m.setValueAt(equipoId, fila, 0);
                        m.setValueAt(equipodescr, fila, 1);
                        m.setValueAt(id, fila, 2);
                        //m.setValueAt(descuento, fila, 2);
                        m.setValueAt(descripcion, fila, 3);
                        m.setValueAt(estado, fila, 4);
                       /* m.setValueAt(descuento3, fila, 4);
                        m.setValueAt(descuento4, fila, 5);*/

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_MARCA);
        }


    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        limpiar();
        tablaCodigoMarcas.clearSelection();
        seleccionada = false;
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        Servicio_Excel servicio_excel;
        servicio_excel = new Servicio_Excel(tablaCodigoMarcas, this);
        servicio_excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnexportarActionPerformed

    //selecciona item cuando libera tecla presionada
    private void tablaCodigoMarcasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaCodigoMarcasKeyReleased
        fila = tablaCodigoMarcas.getSelectedRow();
        String equipoId = tablaCodigoMarcas.getValueAt(fila, 0).toString();
        String equipoDescr = tablaCodigoMarcas.getValueAt(fila, 1).toString();
        String id = tablaCodigoMarcas.getValueAt(fila, 2).toString();
        String descripcion = tablaCodigoMarcas.getValueAt(fila, 3).toString();
        String estado = (String) tablaCodigoMarcas.getValueAt(fila, 4);
        
        /*double descuento2 = (double) tablaCodigoMarcas.getValueAt(fila, 5);
        double descuento3 = (double) tablaCodigoMarcas.getValueAt(fila, 6);
        double descuento4 = (double) tablaCodigoMarcas.getValueAt(fila, 7);*/

        textidequipo.setText(equipoId);
        comboEquipo.setSelectedItem(String.valueOf(equipoDescr));
        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        comboEstado.setSelectedItem(String.valueOf(estado));
       // txtdescuento.setText(String.valueOf(descuento));
        /*txtdescuento2.setText(String.valueOf(descuento2));
        txtdescuento3.setText(String.valueOf(descuento3));*/
        //txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoMarcasKeyReleased

     //selecciona item cuando libera el click del mouse
    private void tablaCodigoMarcasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCodigoMarcasMouseReleased
        // TODO add your handling code here:
        //Para rellenar formulario al seleccionar 1 fila de tabla
        fila = tablaCodigoMarcas.getSelectedRow();
        String equipoId = tablaCodigoMarcas.getValueAt(fila, 0).toString();
        String equipoDescr = tablaCodigoMarcas.getValueAt(fila, 1).toString();
        String id = tablaCodigoMarcas.getValueAt(fila, 2).toString();
        String descripcion = tablaCodigoMarcas.getValueAt(fila, 3).toString();
        String estado = (String) tablaCodigoMarcas.getValueAt(fila, 4);
        //double descuento2 = (double) tablaCodigoEquipos.getValueAt(fila, 3);
       // double descuento3 = (double) tablaCodigoEquipos.getValueAt(fila, 4);
      //  double descuento4 = (double) tablaCodigoEquipos.getValueAt(fila, 5);

        textidequipo.setText(equipoId);
        comboEquipo.setSelectedItem(String.valueOf(equipoDescr));
        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        comboEstado.setSelectedItem(String.valueOf(estado));
       // txtdescuento2.setText(String.valueOf(descuento2));
       // txtdescuento3.setText(String.valueOf(descuento3));
        //txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoMarcasMouseReleased

    private void btndesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndesactivarActionPerformed
        // TODO add your handling code here:
        String validacion = validarEntradas();
        if (seleccionada == true) { 
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    //int id = Integer.parseInt(txtid.getText());
                    //String descripcion = txtdescripcion.getText();
                    //double descuento = validarDoble(txtdescuento);
                    
                    //probando acceso a data de fila de manera directa
                    fila = tablaCodigoMarcas.getSelectedRow();
                    int idEquipo = (int) tablaCodigoMarcas.getValueAt(fila, 0);
                    String descrEquipo = tablaCodigoMarcas.getValueAt(fila, 1).toString();
                    int id = (int) tablaCodigoMarcas.getValueAt(fila, 2);
                    String descripcion = tablaCodigoMarcas.getValueAt(fila, 3).toString();
                    String estado = (String) tablaCodigoMarcas.getValueAt(fila, 4);
                    
                    Servicio_Equipos sequip= new Servicio_Equipos(null);
                    Equipos eq = sequip.getEquipos_por_codigo(idEquipo);
                    
                    /*double descuento2 = validarDoble(txtdescuento2);*/
                   /* double descuento3 = validarDoble(txtdescuento3);
                    double descuento4 = validarDoble(txtdescuento4);*/
                    if(estado.equals("Activado")){
                        estado="Desactivado";
                    }else{
                        estado="Activado";
                    }
                    
                    
                    Marcas masc = new Marcas();
                    masc.setIdmarca(id);
                    masc.setEquipo(eq);
                    masc.setDescripcion(descripcion);
                    masc.setEstado(estado);
                   // l.setDescuento1(descuento);
                   /* l.setDescuento2(descuento2);*/
                    /*l.setDescuento3(descuento3);
                    l.setDescuento4(descuento4);*/

                    if (actualizarMarcas(masc)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaCodigoMarcas.getModel();
                        m.setValueAt(idEquipo, fila, 0);
                        m.setValueAt(descrEquipo, fila, 1);
                        m.setValueAt(id, fila, 2);
                        m.setValueAt(descripcion, fila, 3);
                        m.setValueAt(estado, fila, 4);
                        //m.setValueAt(descuento, fila, 2);
                       /* m.setValueAt(descuento2, fila, 3);*/
                       /* m.setValueAt(descuento3, fila, 4);
                        m.setValueAt(descuento4, fila, 5);*/
                        
                        comboEstado.setSelectedItem(String.valueOf(estado));

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_MARCA);
        }
    }//GEN-LAST:event_btndesactivarActionPerformed

    private void txtdescuento3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescuento3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescuento3ActionPerformed

    private void txtdescuento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescuento2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescuento2ActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void textidequipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textidequipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textidequipoActionPerformed

    private void comboEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEquipoItemStateChanged
        // TODO add your handling code here:
        
        String equipoDescr = String.valueOf(comboEquipo.getSelectedItem());

        Servicio_Equipos servequip = new Servicio_Equipos(null);
        Equipos equipselec = servequip.buscarEquiposx_Nombre(equipoDescr);
        //System.out.println(equipselec);
        //Listar_Provincias(dpto);
        if(equipselec == null){
            textidequipo.setText("");
            seleccionarVisibilidadElementosMarca(false);
        } else {
            seleccionarVisibilidadElementosMarca(true);
            textidequipo.setText(Integer.toString(equipselec.getIdequipo()));
        }
        Listar_MarcasxEquipo(equipselec);
        
    }//GEN-LAST:event_comboEquipoItemStateChanged

    public void limpiar() {

        textidequipo.setText("");
        comboEquipo.setSelectedIndex(0);
        txtdescripcion.setText("");
       // txtdescuento.setText("");
        comboEstado.setSelectedIndex(0);
        txtdescuento2.setText("");
        txtdescuento3.setText("");

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
        tablaCodigoMarcas.clearSelection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnagregar;
    public javax.swing.JButton btndesactivar;
    public javax.swing.JButton btneliminar;
    public javax.swing.JButton btnexportar;
    public javax.swing.JButton btnlimpiar;
    public javax.swing.JButton btnmodificar;
    public javax.swing.JButton btnsalir;
    public javax.swing.JComboBox comboEquipo;
    public javax.swing.JComboBox comboEstado;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel labelestado;
    public javax.swing.JLabel labeltextdescripcion;
    public javax.swing.JLabel labeltextid;
    public javax.swing.JPanel panelCodigoMarcas;
    public javax.swing.JTable tablaCodigoMarcas;
    public javax.swing.JTextField textidequipo;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtdescuento2;
    public javax.swing.JTextField txtdescuento3;
    public javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
