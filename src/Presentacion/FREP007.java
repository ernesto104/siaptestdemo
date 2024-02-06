package Presentacion;

import Entidades.Importadores;
import Mantenimiento.Navegacion;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Importador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP007 extends javax.swing.JFrame {

    Servicio_Importador servicio_Importador;
    Servicio_Excel servicio_Excel;
    private DefaultTableModel modelo;
    int cant_import;
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    int filaseleccionadaImportador;
    private JTableHeader header;
    private JPopupMenu renamePopup;
    private JTextField text;
    private TableColumn column;

    public FREP007() {
        initComponents();
        this.setLocationRelativeTo(null);

        modelo = (DefaultTableModel) tblImportadores.getModel();
        ((JLabel) tblImportadores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        servicio_Importador = new Servicio_Importador(tblImportadores);
        servicio_Excel = new Servicio_Excel(tblImportadores, this);
        Listar_Importadores();

        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnAgregar);
        asignarEvento();
        SetParametrosTableHeader();

    }

    private void Listar_Importadores() {
        cant_import = servicio_Importador.Listar_importadores(modelo);
        cant_import++;


    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayComponente() {
        componentes = new JTextField[7];
        componentes[0] = txtNombre;
        componentes[1] = txtDireccion;
        componentes[2] = txtContacto;
        componentes[3] = txtEmail;
        componentes[4] = txtPais;
        componentes[5] = txtTelefono1;
        componentes[6] = txtTelefono2;

    }

    private void crearArrayNumMax() {
        numMaximo.add(40); //nombre
        numMaximo.add(40); //direccion
        numMaximo.add(40); //contacto
        numMaximo.add(40); //email
        numMaximo.add(20); //pais
        numMaximo.add(20); //telefon1
        numMaximo.add(20); //telefon2
        numMaximo.add(20); //celular
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //empresa
        tipoDato.add("S"); //direccion
        tipoDato.add("S"); //contacto
        tipoDato.add("S"); //email
        tipoDato.add("S"); //pais
        tipoDato.add("S"); //telefon1
        tipoDato.add("S"); //telefon2
        tipoDato.add("S"); //celular
    }

    public void Limpiar_campos() {
        txtNombre.setText("");
        txtContacto.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtPais.setText("");
        txtTelefono1.setText("");
        txtTelefono2.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImportadores = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImportadores = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Dirección:");

        jLabel4.setText("Contacto:");

        jLabel5.setText("E-mail:");

        txtNombre.setNextFocusableComponent(txtDireccion);

        txtDireccion.setNextFocusableComponent(txtContacto);

        txtContacto.setNextFocusableComponent(txtEmail);

        txtEmail.setNextFocusableComponent(txtPais);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setText("Pais:");

        jLabel7.setText("Teléfono 1:");

        jLabel8.setText("Teléfono2:");

        txtPais.setNextFocusableComponent(txtTelefono1);

        txtTelefono1.setNextFocusableComponent(txtTelefono2);

        jLabel10.setText("(*)");

        jLabel11.setText("(*)");

        jLabel12.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(283, 283, 283))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(42, 42, 42)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10))
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("IMPORTADORES");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblImportadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre (Buscar)", "Contacto", "Pais", "E-mail", "Teléfono 1", "Teléfono 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImportadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblImportadores.getTableHeader().setReorderingAllowed(false);
        tblImportadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblImportadoresMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImportadoresMouseClicked(evt);
            }
        });
        tblImportadores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblImportadoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblImportadores);
        if (tblImportadores.getColumnModel().getColumnCount() > 0) {
            tblImportadores.getColumnModel().getColumn(0).setMinWidth(260);
            tblImportadores.getColumnModel().getColumn(0).setMaxWidth(260);
            tblImportadores.getColumnModel().getColumn(1).setMinWidth(280);
            tblImportadores.getColumnModel().getColumn(1).setMaxWidth(280);
            tblImportadores.getColumnModel().getColumn(2).setMinWidth(150);
            tblImportadores.getColumnModel().getColumn(2).setMaxWidth(150);
            tblImportadores.getColumnModel().getColumn(4).setMinWidth(100);
            tblImportadores.getColumnModel().getColumn(4).setMaxWidth(100);
            tblImportadores.getColumnModel().getColumn(5).setMinWidth(100);
            tblImportadores.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImportadoresLayout = new javax.swing.GroupLayout(panelImportadores);
        panelImportadores.setLayout(panelImportadoresLayout);
        panelImportadoresLayout.setHorizontalGroup(
            panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImportadoresLayout.createSequentialGroup()
                .addGroup(panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelImportadoresLayout.createSequentialGroup()
                        .addGroup(panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImportadoresLayout.createSequentialGroup()
                                .addGap(391, 391, 391)
                                .addComponent(jLabel1))
                            .addGroup(panelImportadoresLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelImportadoresLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImportadoresLayout.setVerticalGroup(
            panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImportadoresLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImportadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImportadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        String mensaje = "";
        Importadores importadores = new Importadores();
        if (txtNombre.getText().equals("")) {

            mensaje = mensaje + "Debe ingresar el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Debe ingresar la dirección";
        }
        if (txtPais.getText().equals("")) {
            mensaje = mensaje + "\n" + "Debe ingresar el pais";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea añadir importador?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                servicio_Importador.setUltimo_id(servicio_Importador.getUltimo_id() + 1);

                importadores.setContacto(txtContacto.getText());
                importadores.setDireccion(txtDireccion.getText());
                importadores.setEmail(txtEmail.getText());
                importadores.setNombre(txtNombre.getText());
                importadores.setPais(txtPais.getText());
                importadores.setTelefono(txtTelefono1.getText());
                importadores.setTelefono2(txtTelefono2.getText());

                boolean verific = false;

                verific = servicio_Importador.insertar_Importador(importadores);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Insertó un nuevo importador");
                    DefaultTableModel modelo = (DefaultTableModel) tblImportadores.getModel();
                    Object[] lineas = {importadores.getNombre(), importadores.getContacto(), importadores.getPais(),
                        importadores.getEmail(), importadores.getTelefono(), importadores.getTelefono2()};
                    modelo.addRow(lineas);
                    Limpiar_campos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error en la inserción");
                }
            } else {
                Limpiar_campos();
            }
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar_campos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblImportadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImportadoresMouseClicked
    }//GEN-LAST:event_tblImportadoresMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

      
        filaseleccionadaImportador = tblImportadores.getSelectedRow();
        String mensaje = "";
        if (txtNombre.getText().equals("")) {

            mensaje = mensaje + "Debe ingresar el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Debe ingresar la dirección";
        }
        if (txtPais.getText().equals("")) {
            mensaje = mensaje + "\n" + "Debe ingresar el pais";
        }
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String NombreImportador = (String) tblImportadores.getValueAt(filaseleccionadaImportador, 0);

            Importadores importador = new Importadores();
            importador = servicio_Importador.Obtener_Importador_Por_Nombre(NombreImportador);
            //    importador.setCelular(NombreImportador);

            Importadores importad = new Importadores();
            importad.setIdimportador(importador.getIdimportador());
            importad.setContacto(txtContacto.getText());
            importad.setDireccion(txtDireccion.getText());
            importad.setEmail(txtEmail.getText());
            importad.setNombre(txtNombre.getText());
            importad.setPais(txtPais.getText());
            importad.setTelefono(txtTelefono1.getText());
            importad.setTelefono2(txtTelefono2.getText());

            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea actualizar importador?", "CONFIRMACIÓN", 0);
            if (verif == 0) {

                boolean verific;

                verific = servicio_Importador.actualizar_Importador(importad);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Actualización de cliente");
                    modelo.setValueAt(importad.getNombre(), filaseleccionadaImportador, 0);
                    modelo.setValueAt(importad.getContacto(), filaseleccionadaImportador, 1);
                    modelo.setValueAt(importad.getPais(), filaseleccionadaImportador, 2);
                    modelo.setValueAt(importad.getEmail(), filaseleccionadaImportador, 3);
                    modelo.setValueAt(importad.getTelefono(), filaseleccionadaImportador, 4);
                    modelo.setValueAt(importad.getTelefono2(), filaseleccionadaImportador, 5);
                    //    Listar_Clientes();
                    Limpiar_campos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la actualización");
                }
            } else {
                Limpiar_campos();
            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (!txtNombre.getText().equals("")) {
            filaseleccionadaImportador = tblImportadores.getSelectedRow();

            String NombreImportador = (String) tblImportadores.getValueAt(filaseleccionadaImportador, 0);

            Importadores importador = new Importadores();
            importador = servicio_Importador.Obtener_Importador_Por_Nombre(NombreImportador);

            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea eliminar importador?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                boolean verific = false;
                //  Limpiar_Tabla();
                verific = servicio_Importador.eliminar_Importador(importador);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Eliminó un importador");
                    Limpiar_campos();
                    modelo.removeRow(filaseleccionadaImportador);
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la eliminación");
                }


            } else {
                Limpiar_campos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a un importador antes de eliminar");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        servicio_Excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnExportarActionPerformed

    private void tblImportadoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblImportadoresKeyReleased
        String NombreImportador = (String) tblImportadores.getValueAt(tblImportadores.getSelectedRow(), 0);

        Importadores importador;
        importador = servicio_Importador.Obtener_Importador_Por_Nombre(NombreImportador);

        txtNombre.setText(importador.getNombre());
        txtDireccion.setText(importador.getDireccion());
        txtContacto.setText(importador.getContacto());
        txtEmail.setText(importador.getEmail());
        txtPais.setText(importador.getPais());
        txtTelefono1.setText(importador.getTelefono());
        txtTelefono2.setText(importador.getTelefono2());
    }//GEN-LAST:event_tblImportadoresKeyReleased

    private void tblImportadoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImportadoresMouseReleased
        
        String NombreImportador = (String) tblImportadores.getValueAt(tblImportadores.getSelectedRow(), 0);

        Importadores importador;
        importador = servicio_Importador.Obtener_Importador_Por_Nombre(NombreImportador);

        txtNombre.setText(importador.getNombre());
        txtDireccion.setText(importador.getDireccion());
        txtContacto.setText(importador.getContacto());
        txtEmail.setText(importador.getEmail());
        txtPais.setText(importador.getPais());
        txtTelefono1.setText(importador.getTelefono());
        txtTelefono2.setText(importador.getTelefono2());
    }//GEN-LAST:event_tblImportadoresMouseReleased

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel1;
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
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelImportadores;
    public javax.swing.JTable tblImportadores;
    public javax.swing.JTextField txtContacto;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPais;
    public javax.swing.JTextField txtTelefono1;
    public javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables

    private void SetParametrosTableHeader() {
        header = tblImportadores.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
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
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= 'a' && c <= 'z') {
                    e.setKeyChar((char) (c - 32));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxNombre();
            }

            private void BuscarxNombre() {
                String nombre = text.getText();
                if (text.getText().equals("")) {
                    text.setText("Nombre (Buscar)");
                }
                column.setHeaderValue(text.getText());
                renamePopup.setVisible(false);
                header.repaint();
                BorrarTabla();
                servicio_Importador.ListarxNombre(modelo, nombre);
            }
        });

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);
    }

    private void BorrarTabla() {
        int numRows = modelo.getRowCount();
        for (int i = 0; i < numRows; i++) {
            modelo.removeRow(0);
        }
    }
}
