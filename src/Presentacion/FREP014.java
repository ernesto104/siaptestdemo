package Presentacion;

import Entidades.Operaciones;
import Entidades.Sistema;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Operaciones;
import Servicios.Servicio_Sistema;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily I.
 */
public class FREP014 extends javax.swing.JFrame {

    private int fila;
    private boolean seleccionada;
    private DefaultTableModel table;
    private Servicio_Sistema sis;
    TipoMensaje tm;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    private Servicio_Excel se;
    JTextField[] componentes;

    public FREP014() {
        initComponents();
        setLocationRelativeTo(null);
        tm = new TipoMensaje();
        sis = new Servicio_Sistema(this);
        table = (DefaultTableModel) tablaSistema.getModel();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        se = new Servicio_Excel(tablaSistema, this);
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, botonSalir);
        sis.listar();
        txtID.setText(String.valueOf(sis.nextId()));
        asignarEvento();

    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayComponente() {
        componentes = new JTextField[6];
        componentes[0] = txtTipoDocumento;
        componentes[1] = txtDescripcion;
        componentes[2] = txtOperacion;
        componentes[3] = txtserie;
        componentes[4] = txtUltimoNumero;
        componentes[5] = txtTransaccion;

    }

    private void crearArrayNumMax() {
        numMaximo.add(2);
        numMaximo.add(25);
        numMaximo.add(11);
        numMaximo.add(3);
        numMaximo.add(8);
        numMaximo.add(1);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("I");
        tipoDato.add("I");
        tipoDato.add("S");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        panelSistema = new javax.swing.JPanel();
        ID = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtTipoDocumento = new javax.swing.JTextField();
        txtUltimoNumero = new javax.swing.JTextField();
        txtTransaccion = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtOperacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSistema = new javax.swing.JTable();
        botonAgregar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonExportar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ID.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("ID Transacción");

        jLabel2.setText("Tipo Documento");

        jLabel3.setText("Ultimo Número");

        jLabel4.setText("Transacción");

        jLabel5.setText("Descripción");

        txtID.setEnabled(false);

        jLabel6.setText("Código de Operación ");

        jLabel7.setText("Serie");

        jLabel9.setText("( * )");

        jLabel10.setText("( * )");

        javax.swing.GroupLayout IDLayout = new javax.swing.GroupLayout(ID);
        ID.setLayout(IDLayout);
        IDLayout.setHorizontalGroup(
            IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IDLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(IDLayout.createSequentialGroup()
                        .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(IDLayout.createSequentialGroup()
                        .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(IDLayout.createSequentialGroup()
                                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4))
                                .addGap(46, 46, 46)))
                        .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(IDLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(IDLayout.createSequentialGroup()
                                        .addComponent(txtOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9))
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(IDLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(IDLayout.createSequentialGroup()
                                        .addComponent(txtUltimoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        IDLayout.setVerticalGroup(
            IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUltimoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaSistema.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Doc.", "Descripción", "Cod. Operación", "Serie", "Ult. Num", "Transacción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaSistema.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaSistema.getTableHeader().setReorderingAllowed(false);
        tablaSistema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSistemaMouseClicked(evt);
            }
        });
        tablaSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaSistemaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSistema);
        if (tablaSistema.getColumnModel().getColumnCount() > 0) {
            tablaSistema.getColumnModel().getColumn(0).setResizable(false);
            tablaSistema.getColumnModel().getColumn(0).setPreferredWidth(70);
            tablaSistema.getColumnModel().getColumn(1).setResizable(false);
            tablaSistema.getColumnModel().getColumn(1).setPreferredWidth(140);
            tablaSistema.getColumnModel().getColumn(2).setResizable(false);
            tablaSistema.getColumnModel().getColumn(2).setPreferredWidth(100);
            tablaSistema.getColumnModel().getColumn(3).setResizable(false);
            tablaSistema.getColumnModel().getColumn(3).setPreferredWidth(50);
            tablaSistema.getColumnModel().getColumn(4).setResizable(false);
            tablaSistema.getColumnModel().getColumn(4).setPreferredWidth(60);
            tablaSistema.getColumnModel().getColumn(5).setResizable(false);
        }

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonExportar.setText("Exportar");
        botonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("SISTEMA");

        javax.swing.GroupLayout panelSistemaLayout = new javax.swing.GroupLayout(panelSistema);
        panelSistema.setLayout(panelSistemaLayout);
        panelSistemaLayout.setHorizontalGroup(
            panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSistemaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSistemaLayout.createSequentialGroup()
                        .addComponent(botonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSistemaLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 166, Short.MAX_VALUE))
                            .addGroup(panelSistemaLayout.createSequentialGroup()
                                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(botonExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(panelSistemaLayout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSistemaLayout.setVerticalGroup(
            panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSistemaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String validarEntradas() {

        String mensaje = "";

        if (txtOperacion.getText().equals("")) {
            mensaje = mensaje + "Ingrese el número de Operacion";
        }
        if (txtUltimoNumero.getText().equals("")) {
            mensaje = mensaje + "\n Ingrese el último número";
        }
        
        return mensaje;
    }

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed

        if (seleccionada == false) {
            String validacion = validarEntradas();

            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    String id = txtID.getText();
                    String idop = txtOperacion.getText();
                    String serie = txtserie.getText();
                    String tipodoc = txtTipoDocumento.getText();
                    String ultnum = txtUltimoNumero.getText();
                    String trans = txtTransaccion.getText();
                    String desc = txtDescripcion.getText();

                    Servicio_Operaciones op = new Servicio_Operaciones();
                    Operaciones oper = op.obtener_Operacion_Codigo(idop);
                    oper.setCodigooperacion(idop);

                    Sistema sist = new Sistema();
                    sist.setIdtransaccion(Integer.parseInt(id));
                    sist.setOperaciones(oper);
                    sist.setSerie(Integer.parseInt(serie));                    
                    sist.setTipodoc(tipodoc);
                    sist.setUltimonumero(Integer.parseInt(ultnum));
                    sist.setTransaccion(trans);
                    sist.setDescripcion(desc);

                    if (sis.addSistema(sist)) {
                        JOptionPane.showMessageDialog(null, "Operacion exitosa");
                        Object[] row = {tipodoc, desc, idop, serie, ultnum, trans};
                        table.addRow(row);                        
                        clean();                        
                        txtID.setText(String.valueOf(sis.nextId()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "ESTE REGISTRO YA EXISTE", "Error al agregar", 0);
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void tablaSistemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSistemaMouseClicked
        fila = tablaSistema.getSelectedRow();
        System.out.println("fila"+fila);
        String tipodoc = tablaSistema.getValueAt(fila, 0).toString();
        String descrip = tablaSistema.getValueAt(fila, 1).toString();
        String idop = tablaSistema.getValueAt(fila, 2).toString();
        String serie = tablaSistema.getValueAt(fila, 3).toString();
        String ultnum = tablaSistema.getValueAt(fila, 4).toString();
        String tran = tablaSistema.getValueAt(fila, 5).toString();

        int id = sis.getSistemas_por_descripcion(descrip).getIdtransaccion();

        txtID.setText(String.valueOf(id));
        txtTipoDocumento.setText(tipodoc);
        txtDescripcion.setText(descrip);
        txtOperacion.setText(idop);
        txtserie.setText(serie);
        txtUltimoNumero.setText(ultnum);
        txtTransaccion.setText(tran);
        seleccionada = true;
    }//GEN-LAST:event_tablaSistemaMouseClicked

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        
        if (seleccionada==true){
            if (JOptionPane.showConfirmDialog(this, "¿ Desea continuar con la Operacion ?", "Confirmacion", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                if (sis.borrarSistemas(sis.getSistemas_por_nombre(txtID.getText()))){
                    JOptionPane.showMessageDialog(null, "Operacion exitosa");
                    table.removeRow(fila);                    
                    tablaSistema.clearSelection();
                    seleccionada = false;
                    clean();
                    txtID.setText(String.valueOf(sis.nextId()));
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operacion");
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_SISTEMA);
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        String validacion = validarEntradas();
        if (seleccionada==true) {
            if (!validacion.equals("")){
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            }else {

                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    
                        String id = txtID.getText();
                        String idop = txtOperacion.getText();
                        String serie = txtserie.getText();
                        String tipodoc = txtTipoDocumento.getText();
                        String ultnum = txtUltimoNumero.getText();
                        String trans = txtTransaccion.getText();
                        String desc = txtDescripcion.getText();

                        
                        Servicio_Operaciones op = new Servicio_Operaciones();
                        Operaciones oper = op.obtener_Operacion_Codigo(idop);    
                        
                        Servicio_Sistema ss = new Servicio_Sistema();
                        Sistema sist = ss.getSistemas_por_nombre(id);
                        
                        sist.setIdtransaccion(Integer.parseInt(id));                        
                        sist.setTipodoc(tipodoc);                        
                        sist.setOperaciones(oper);                        
                        sist.setSerie(Integer.parseInt(serie));
                        sist.setDescripcion(desc);
                        sist.setUltimonumero(Integer.parseInt(ultnum));
                        sist.setTransaccion(trans);                       

                        if (actualizarRepuesto(sist)) {
                            JOptionPane.showMessageDialog(null, "Operacion exitosa");
                            DefaultTableModel mod = (DefaultTableModel) tablaSistema.getModel();
                            mod.setValueAt(tipodoc, fila, 0);
                            mod.setValueAt(desc, fila, 1);
                            mod.setValueAt(idop, fila, 2);
                            mod.setValueAt(serie, fila, 3);
                            mod.setValueAt(ultnum, fila, 4);
                            mod.setValueAt(trans, fila, 5);                              
                        } else {
                            JOptionPane.showMessageDialog(null, "Error en la actualización");
                        }                    
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_SISTEMA);
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    public boolean actualizarRepuesto(Sistema sist) {
        Session session = HibernateUtil.getSessionFactory().openSession();        
        try {
            session.beginTransaction();
            session.update(sist);
            session.getTransaction().commit();            
            return true;
        } catch (HibernateException e) {
            session.beginTransaction().rollback();            
            return false;
        }
        
    }
    
    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        clean();
        tablaSistema.clearSelection();
        txtID.setText(String.valueOf(sis.nextId()));
        seleccionada = false;
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarActionPerformed
        se.Exportar_Excel(1);
    }//GEN-LAST:event_botonExportarActionPerformed

    private void tablaSistemaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaSistemaKeyReleased
        fila = tablaSistema.getSelectedRow();
        String tipodoc = tablaSistema.getValueAt(fila, 0).toString();
        String descrip = tablaSistema.getValueAt(fila, 1).toString();
        String idop = tablaSistema.getValueAt(fila, 2).toString();
        String serie = tablaSistema.getValueAt(fila, 3).toString();
        String ultnum = tablaSistema.getValueAt(fila, 4).toString();
        String tran = tablaSistema.getValueAt(fila, 5).toString();

        int id = sis.getSistemas_por_descripcion(descrip).getIdtransaccion();

        txtID.setText(String.valueOf(id));
        txtTipoDocumento.setText(tipodoc);
        txtDescripcion.setText(descrip);
        txtOperacion.setText(idop);
        txtserie.setText(serie);
        txtUltimoNumero.setText(ultnum);
        txtTransaccion.setText(tran);
        seleccionada = true;
    }//GEN-LAST:event_tablaSistemaKeyReleased

    private void clean() {
        for (JTextField j : componentes) {
            j.setText("");
        }
        tablaSistema.clearSelection();
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[6];
        row[0] = txtOperacion.getText();
        row[1] = txtserie.getText();
        row[2] = txtTipoDocumento.getText();
        row[3] = txtUltimoNumero.getText();
        row[4] = txtTransaccion.getText();
        row[5] = txtDescripcion.getText();

        table.removeRow(fila);
        table.insertRow(fila, row);
        tablaSistema.clearSelection();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel ID;
    public javax.swing.JButton botonAgregar;
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonExportar;
    public javax.swing.JButton botonLimpiar;
    public javax.swing.JButton botonModificar;
    public javax.swing.JButton botonSalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JPanel panelSistema;
    public javax.swing.JTable tablaSistema;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtID;
    public javax.swing.JTextField txtOperacion;
    public javax.swing.JTextField txtTipoDocumento;
    public javax.swing.JTextField txtTransaccion;
    public javax.swing.JTextField txtUltimoNumero;
    public javax.swing.JTextField txtserie;
    // End of variables declaration//GEN-END:variables
}
