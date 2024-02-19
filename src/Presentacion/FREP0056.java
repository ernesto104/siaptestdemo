package Presentacion;

import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Modelos;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.Servicio_Equipos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Marcas;
import Servicios.Servicio_Modelos;
import Servicios.TipoMensaje;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily
 */
public class FREP0056 extends javax.swing.JFrame {

    int numeroequipos;
    Servicio_Equipos sequip;
    Servicio_Modelos smod;
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
    private DefaultComboBoxModel modelMarcCombo;
    int ultimo_id;
    int num_lin;

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }

    public FREP0056() {

        initComponents();
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) tablaCodigoModelos.getModel();
        table = (DefaultTableModel) tablaCodigoModelos.getModel();
        smod = new Servicio_Modelos(this);
        sequip = new Servicio_Equipos(null);
        tm = new TipoMensaje();

        txtid.setText(String.valueOf(smod.nextId()));
        Listar_Modelos();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        llenar_equipos();
        //llenar_marcas();
        
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnagregar);
        asignarEvento();

        // = new Servicio_Marcas(this);
        
        //para ocultar botones (se desplazo a otra vista el boton Eliminar)
        btneliminar.setVisible(false);
        jLabel5.setVisible(false);
        jLabel7.setVisible(false);
        txtdescuento2.setVisible(false);
        txtdescuento3.setVisible(false);
        
        btndesactivar.setVisible(false);
        
        //ocultar temporalmente fila de Marcas hasta se seleccione un Equipo
        /*labeltextidmarca.setVisible(false);
        textidmarca.setVisible(false);
        comboMarca.setVisible(false);
        jLabel11.setVisible(false);*/
        visibilidadElementosMarca(false);
        visibilidadElementosModelo(false);
    }

    private void visibilidadElementosMarca (boolean estado) {
        labeltextidmarca.setVisible(estado);
        textidmarca.setVisible(estado);
        comboMarca.setVisible(estado);
        jLabel11.setVisible(estado);
    }
    
    private void visibilidadElementosModelo (boolean estado) {
        labeltextid.setVisible(estado);
        txtid.setVisible(estado);
        labeltextdescripcion.setVisible(estado);
        txtdescripcion.setVisible(estado);
        jLabel3.setVisible(estado);
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

    }
    //generar contenido de tabla
    private void Listar_Modelos() {
        smod.Listar_modelos();
    }

    private String validarEntradas() {
        String mensaje = "ERROR";
        
        if (comboEquipo.getSelectedIndex() == 0) {
            mensaje += "\n- Seleccione un equipo encasillar el modelo";
        }
        
        if (comboMarca.getSelectedIndex() == 0) {
            mensaje += "\n- Seleccione una marca para encasillar el modelo";
        }
        
        
        if (txtdescripcion.getText().equals("")) {
            mensaje = mensaje + "\n- Ingrese descripción de Modelo";
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
   
   
   private void llenar_marcas() {
       Servicio_Marcas sm = new Servicio_Marcas(null);
       Iterator it = sm.getList().iterator();

       while (it.hasNext()) {
           Marcas marc = (Marcas) it.next();
           comboMarca.addItem(marc.getDescripcion());
       }
    }
   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btneliminar = new javax.swing.JButton();
        panelCodigoModelos = new javax.swing.JPanel();
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
        labeltextidmarca = new javax.swing.JLabel();
        textidmarca = new javax.swing.JTextField();
        comboMarca = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCodigoModelos = new javax.swing.JTable();
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        labeltextid.setText("Código de Modelo");

        labelestado.setText("Estado");

        txtid.setEnabled(false);

        labeltextdescripcion.setText("Descripcion");

        jLabel3.setText("( * )");

        jLabel5.setText("Modelo");

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

        labeltextidmarca.setText("Marca");

        textidmarca.setEnabled(false);

        comboMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        comboMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMarcaItemStateChanged(evt);
            }
        });

        jLabel11.setText("( * )");

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(labeltextidmarca))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(txtdescuento2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textidmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(textidequipo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addGap(42, 42, 42)
                .addComponent(txtdescuento3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textidequipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textidmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(labeltextidmarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeltextid)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeltextdescripcion)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdescuento3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelestado)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdescuento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Modelos");

        tablaCodigoModelos.setAutoCreateRowSorter(true);
        tablaCodigoModelos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tablaCodigoModelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Equipo", "Equipo", "Id Marca", "Marca", "Id Modelo", "Modelo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCodigoModelos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaCodigoModelos.getTableHeader().setReorderingAllowed(false);
        tablaCodigoModelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaCodigoModelosMouseReleased(evt);
            }
        });
        tablaCodigoModelos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaCodigoModelosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCodigoModelos);
        if (tablaCodigoModelos.getColumnModel().getColumnCount() > 0) {
            tablaCodigoModelos.getColumnModel().getColumn(0).setPreferredWidth(125);
            tablaCodigoModelos.getColumnModel().getColumn(1).setPreferredWidth(260);
            tablaCodigoModelos.getColumnModel().getColumn(2).setPreferredWidth(125);
            tablaCodigoModelos.getColumnModel().getColumn(3).setPreferredWidth(260);
            tablaCodigoModelos.getColumnModel().getColumn(4).setPreferredWidth(125);
            tablaCodigoModelos.getColumnModel().getColumn(5).setPreferredWidth(260);
            tablaCodigoModelos.getColumnModel().getColumn(6).setPreferredWidth(150);
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

        javax.swing.GroupLayout panelCodigoModelosLayout = new javax.swing.GroupLayout(panelCodigoModelos);
        panelCodigoModelos.setLayout(panelCodigoModelosLayout);
        panelCodigoModelosLayout.setHorizontalGroup(
            panelCodigoModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                .addGroup(panelCodigoModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelCodigoModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(btndesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCodigoModelosLayout.setVerticalGroup(
            panelCodigoModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoModelosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCodigoModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("");
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCodigoModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCodigoModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    
                    int marcaId = Integer.parseInt(textidmarca.getText());
                    String marcadescr = comboMarca.getSelectedItem().toString();
                    
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    String estado = (String) comboEstado.getSelectedItem();
                    //double descuento = validarDoble(txtdescuento);
                    //double descuento2 = validarDoble(txtdescuento2);
                    //double descuento3 = validarDoble(txtdescuento3);
                    //double descuento4 = validarDoble(txtdescuento4);
                    
                    
                    Servicio_Equipos sequip= new Servicio_Equipos(null);
                    Equipos equip = sequip.getEquipos_por_codigo(equipoId);
                    
                    Servicio_Marcas smarc = new Servicio_Marcas(null);
                    Marcas marca = smarc.getMarcas_por_codigo(marcaId);
                    
                    
                    Modelos mo = new Modelos();
                    mo.setEquipo(equip);
                    mo.setMarca(marca);
                    mo.setIdmodelo(id);
                    mo.setDescripcion(descripcion);
                    mo.setEstado(estado);
                    //li.setDescuento1(validarDoble(txtdescuento));
                    //li.setDescuento2(validarDoble(txtdescuento2));
                    //li.setDescuento3(validarDoble(txtdescuento3));
                   // ma.setDescuento4(validarDoble(txtdescuento4));

                    if (smod.addModelos(mo)) {
                        JOptionPane.showMessageDialog(null, "Operacion exitosa");
                        Object[] row = {equipoId, equipodescr, marcaId, marcadescr,id,/*descuento,*/ descripcion, estado};
                        table.addRow(row);
                        clean();
                        txtid.setText(String.valueOf(smod.nextId()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ESTE MODELO YA SE ENCUENTRA REGISTRADO\nUTILICE LA OPCION 'LIMPIAR' PARA COMENZAR A AGREGAR UN NUEVO MODELO", "Error al agregar", 0);
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
                if (smod.borrarModelos(smod.getModelos(Integer.parseInt(txtid.getText())))) {
                    JOptionPane.showMessageDialog(null, "Operacion exitosa");
                    table.removeRow(fila);
                    tablaCodigoModelos.clearSelection();
                    seleccionada = false;
                    clean();
                    txtid.setText(String.valueOf(smod.nextId()));
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operacion");
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_MODELO);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    public boolean actualizarModelos(Modelos l) {
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
                    
                    int marcaId = Integer.parseInt(textidmarca.getText());
                    String marcadescr = comboMarca.getSelectedItem().toString();
                    
                    int id = Integer.parseInt(txtid.getText());
                    String descripcion = txtdescripcion.getText();
                    //double descuento = validarDoble(txtdescuento);
                    String estado = (String) comboEstado.getSelectedItem();
                    //double descuento2 = validarDoble(txtdescuento2);
                   /* double descuento3 = validarDoble(txtdescuento3);
                    double descuento4 = validarDoble(txtdescuento4);*/

                    
                    Servicio_Equipos sequip= new Servicio_Equipos(null);
                    Equipos eq = sequip.getEquipos_por_codigo(equipoId);
                    
                    Servicio_Marcas smarc = new Servicio_Marcas(null);
                    Marcas marca = smarc.getMarcas_por_codigo(marcaId);

                    Modelos mo = new Modelos();                    
                    mo.setIdmodelo(id);
                    mo.setEquipo(eq);
                    mo.setMarca(marca);
                    mo.setDescripcion(descripcion);
                    mo.setEstado(estado);
                   // l.setDescuento1(descuento);
                    //m.setDescuento2(descuento2);
                    /*l.setDescuento3(descuento3);
                    l.setDescuento4(descuento4);*/

                    if (actualizarModelos(mo)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaCodigoModelos.getModel();
                        m.setValueAt(equipoId, fila, 0);
                        m.setValueAt(equipodescr, fila, 1);
                        m.setValueAt(marcaId, fila, 2);
                        m.setValueAt(marcadescr, fila, 3);

                        m.setValueAt(id, fila, 4);
                        //m.setValueAt(descuento, fila, 2);
                        m.setValueAt(descripcion, fila, 5);
                        m.setValueAt(estado, fila, 6);
                       /* m.setValueAt(descuento3, fila, 4);
                        m.setValueAt(descuento4, fila, 5);*/

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_MODELO);
        }


    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        limpiar();
        tablaCodigoModelos.clearSelection();
        seleccionada = false;
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        Servicio_Excel servicio_excel;
        servicio_excel = new Servicio_Excel(tablaCodigoModelos, this);
        servicio_excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnexportarActionPerformed

    //selecciona fila cuando libera tecla presionada
    private void tablaCodigoModelosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaCodigoModelosKeyReleased
        fila = tablaCodigoModelos.getSelectedRow();
        String equipoId = tablaCodigoModelos.getValueAt(fila, 0).toString();
        String equipoDescr = tablaCodigoModelos.getValueAt(fila, 1).toString();
        
        String marcaId = tablaCodigoModelos.getValueAt(fila, 2).toString();
        String marcaDescr = tablaCodigoModelos.getValueAt(fila, 3).toString();
        
        String id = tablaCodigoModelos.getValueAt(fila, 4).toString();
        String descripcion = tablaCodigoModelos.getValueAt(fila, 5).toString();
        String estado = (String) tablaCodigoModelos.getValueAt(fila, 6);
        /*double descuento2 = (double) tablaCodigoModelos.getValueAt(fila, 5);
        double descuento3 = (double) tablaCodigoModelos.getValueAt(fila, 6);
        double descuento4 = (double) tablaCodigoModelos.getValueAt(fila, 7);*/

        textidequipo.setText(equipoId);
        comboEquipo.setSelectedItem(String.valueOf(equipoDescr));
        
        textidmarca.setText(marcaId);
        comboMarca.setSelectedItem(marcaDescr);
        
        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        comboEstado.setSelectedItem(String.valueOf(estado));
       // txtdescuento.setText(String.valueOf(descuento));
        /*txtdescuento2.setText(String.valueOf(descuento2));
        txtdescuento3.setText(String.valueOf(descuento3));*/
        //txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoModelosKeyReleased

    //selecciona fila cuando el libera el click del mouse
    private void tablaCodigoModelosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCodigoModelosMouseReleased
        // TODO add your handling code here:
        //Para rellenar formulario al seleccionar 1 fila de tabla
        fila = tablaCodigoModelos.getSelectedRow();
        String equipoId = tablaCodigoModelos.getValueAt(fila, 0).toString();
        String equipoDescr = tablaCodigoModelos.getValueAt(fila, 1).toString();
        
        String marcaId = tablaCodigoModelos.getValueAt(fila, 2).toString();
        String marcaDescr = tablaCodigoModelos.getValueAt(fila, 3).toString();
        
        String id = tablaCodigoModelos.getValueAt(fila, 4).toString();
        String descripcion = tablaCodigoModelos.getValueAt(fila, 5).toString();
        String estado = (String) tablaCodigoModelos.getValueAt(fila, 6);
        //double descuento2 = (double) tablaCodigoEquipos.getValueAt(fila, 3);
       // double descuento3 = (double) tablaCodigoEquipos.getValueAt(fila, 4);
      //  double descuento4 = (double) tablaCodigoEquipos.getValueAt(fila, 5);

        
        textidequipo.setText(equipoId);
        comboEquipo.setSelectedItem(String.valueOf(equipoDescr));
        
        textidmarca.setText(marcaId);
        comboMarca.setSelectedItem(marcaDescr);
        
        txtid.setText(id);
        txtdescripcion.setText(descripcion);
        comboEstado.setSelectedItem(String.valueOf(estado));
       // txtdescuento2.setText(String.valueOf(descuento2));
       // txtdescuento3.setText(String.valueOf(descuento3));
        //txtdescuento4.setText(String.valueOf(descuento4));

        seleccionada = true;
    }//GEN-LAST:event_tablaCodigoModelosMouseReleased

    private void btndesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndesactivarActionPerformed
        // TODO add your handling code here:
        String validacion = validarEntradas();
        if (seleccionada == true) { 
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    //int id = Integer.parseInt(txtid.getText());
                    //String descripcion = txtdescripcion.getText();

                    
                    //probando acceso a data de fila de manera directa
                    fila = tablaCodigoModelos.getSelectedRow();
                    int idEquipo = (int) tablaCodigoModelos.getValueAt(fila, 0);
                    String descrEquipo = tablaCodigoModelos.getValueAt(fila, 1).toString();
                    
                    int idMarca = (int) tablaCodigoModelos.getValueAt(fila, 2);
                    String descrMarca = tablaCodigoModelos.getValueAt(fila, 3).toString();
                    
                    int id = (int) tablaCodigoModelos.getValueAt(fila, 4);
                    String descripcion = tablaCodigoModelos.getValueAt(fila, 5).toString();
                    String estado = (String) tablaCodigoModelos.getValueAt(fila, 6);
                    
                    Servicio_Equipos sequip = new Servicio_Equipos(null);
                    Equipos eq = sequip.getEquipos_por_codigo(idEquipo);
                    
                    Servicio_Marcas smarcas = new Servicio_Marcas(null);
                    Marcas marc = smarcas.getMarcas_por_codigo(idMarca);

                    if(estado.equals("Activado")){
                        estado="Desactivado";
                    }else{
                        estado="Activado";
                    }
                    
                    
                    Modelos mod = new Modelos();
                    mod.setIdmodelo(id);
                    mod.setEquipo(eq);
                    mod.setMarca(marc);
                    mod.setDescripcion(descripcion);
                    mod.setEstado(estado);


                    if (actualizarModelos(mod)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaCodigoModelos.getModel();
                        m.setValueAt(idEquipo, fila, 0);
                        m.setValueAt(descrEquipo, fila, 1);
                        m.setValueAt(idMarca, fila, 2);
                        m.setValueAt(descrMarca, fila, 3);
                        m.setValueAt(id, fila, 4);
                        m.setValueAt(descripcion, fila, 5);
                        m.setValueAt(estado, fila, 6);

                        
                        comboEstado.setSelectedItem(String.valueOf(estado));

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_LINEAS);
        }
    }//GEN-LAST:event_btndesactivarActionPerformed

    private void comboMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMarcaItemStateChanged
        // TODO add your handling code here:
        String marcDescr = String.valueOf(comboMarca.getSelectedItem());

        Servicio_Marcas servmarc = new Servicio_Marcas(null);
        Marcas marcaselec = servmarc.buscarMarcasx_Nombre(marcDescr);
        //Listar_Provincias(dpto);
        if(marcaselec == null){
            textidmarca.setText("");
            visibilidadElementosModelo(false);
        } else {
            textidmarca.setText(Integer.toString(marcaselec.getIdmarca()));
            visibilidadElementosModelo(true);
        }
    }//GEN-LAST:event_comboMarcaItemStateChanged

    private void comboEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEquipoItemStateChanged
        // TODO add your handling code here:

        String equipoDescr = String.valueOf(comboEquipo.getSelectedItem());

        Servicio_Equipos servequip = new Servicio_Equipos(null);
        Equipos equipselec = servequip.buscarEquiposx_Nombre(equipoDescr);
        //System.out.println(equipselec);
        textidmarca.setText("");
        //Listar_Provincias(dpto);
        if(equipselec == null){
            textidequipo.setText("");
            visibilidadElementosMarca(false);
            visibilidadElementosModelo(false);
            /*labeltextidmarca.setVisible(false);
            textidmarca.setVisible(false);
            comboMarca.setVisible(false);
            jLabel11.setVisible(false);*/
            //comboMarca.setSelectedItem("");
        } else {
            textidequipo.setText(Integer.toString(equipselec.getIdequipo()));
            llenar_marcasXequipo(equipselec);
            visibilidadElementosMarca(true);
            /*labeltextidmarca.setVisible(true);
            textidmarca.setVisible(true);
            comboMarca.setVisible(true);
            jLabel11.setVisible(true);  */
            comboMarca.setSelectedIndex(0);
        }

        /*
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            if ( comboEquipo.getSelectedIndex() != 0 ) {
                lb_venc.setVisible(true);
                dt_venc.setVisible(true);

            } else {
                lb_venc.setVisible(false);
                dt_venc.setVisible(false);
                dt_venc.setDate(null);
            }
        }*/

    }//GEN-LAST:event_comboEquipoItemStateChanged

    
    private void llenar_marcasXequipo(Equipos equipo) {
        
       modelMarcCombo = new DefaultComboBoxModel();
       Servicio_Marcas sm = new Servicio_Marcas(null);
       
       modelMarcCombo.addElement("Seleccione");
       List<Marcas> listaMarc = sm.buscarMarcasx_Equipo(equipo);
        for (int i = 0; i < listaMarc.size(); i++) {
            modelMarcCombo.addElement(listaMarc.get(i).getDescripcion());
        }
        
        comboMarca.setModel(modelMarcCombo); 
        
        
        
        
       /*Servicio_Marcas sm = new Servicio_Marcas(null);
       Iterator it = sm.getList().iterator();

       while (it.hasNext()) {
           Marcas marc = (Marcas) it.next();
           comboMarca.addItem(marc.getDescripcion());
       }*/
    }
    
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
        tablaCodigoModelos.clearSelection();
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
    public javax.swing.JComboBox comboMarca;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
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
    public javax.swing.JLabel labeltextidmarca;
    public javax.swing.JPanel panelCodigoModelos;
    public javax.swing.JTable tablaCodigoModelos;
    public javax.swing.JTextField textidequipo;
    public javax.swing.JTextField textidmarca;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtdescuento2;
    public javax.swing.JTextField txtdescuento3;
    public javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
