package Presentacion;

import static Entidades.Otros.Constante.COLUMNA_ID_VENDEDOR;
import Entidades.Vendedores;
import Mantenimiento.Navegacion;
import Presentacion.Facturacion.IU_Facturacion;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Vendedor;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP006 extends javax.swing.JFrame {

    Servicio_Vendedor servicioVendedor;
    Servicio_Excel servicio_Excel;
    int cant_vended;
    
    // Para validacion de tipo y N° de caracteres de cada componente en la Interfaz de Usuario
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    
    private DefaultTableModel modelo;
    int filaseleccionadaVendedor;
    
    IU_Facturacion iuf;

    public FREP006() {
        initComponents();
        this.setLocationRelativeTo(null);

        modelo = (DefaultTableModel) tblVendedores.getModel();
        ((JLabel) tblVendedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        servicioVendedor = new Servicio_Vendedor(tblVendedores);
        servicio_Excel = new Servicio_Excel(tblVendedores, this);
        Listar_Vendedores();

        // Configurando validacion de tipo y cantidad de caracteres a escribir
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        System.out.println("componentes:" + componentes);
        System.out.println("numMaximo:" + numMaximo);
        System.out.println("tipoDato:" + tipoDato);
        System.out.println("btnAgregar:" + btnAgregar);
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnAgregar);

        asignarEvento();
        ocultarColumnaIdVendedor();
    }
    
//    public FREP006(IU_Facturacion iuf) { // Registro de un nuevo vendedor, desde Facturación
//        initComponents();
//        this.iuf = iuf;
//        this.setLocationRelativeTo(null);
//
//        modelo = (DefaultTableModel) tblVendedores.getModel();
//        ((JLabel) tblVendedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
//
//        servicioVendedor = new Servicio_Vendedor(tblVendedores);
//        servicio_Excel = new Servicio_Excel(tblVendedores, this);
//        Listar_Vendedores();
//
//        crearArrayComponente();
//        numMaximo = new ArrayList<>();
//        crearArrayNumMax();
//        tipoDato = new ArrayList<>();
//        crearArrayTipoDato();
//        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnAgregar);
//
//        asignarEvento();
//        ocultarColumnaIdVendedor();
//    }
    
    private void ocultarColumnaIdVendedor() {
        tblVendedores.getColumnModel().getColumn(COLUMNA_ID_VENDEDOR).setMaxWidth(0);
        tblVendedores.getColumnModel().getColumn(COLUMNA_ID_VENDEDOR).setMinWidth(0);
        tblVendedores.getColumnModel().getColumn(COLUMNA_ID_VENDEDOR).setPreferredWidth(0);
    }

    private void Listar_Vendedores() {
        cant_vended = servicioVendedor.Listar_vendedores(modelo);
        cant_vended++;
    }

    private void crearArrayComponente() {
        componentes = new JTextField[7];
        componentes[0] = txtNombre;
        componentes[1] = txtDireccion;
        //      componentes[2] = txtEmail;
        componentes[2] = txtDNI;
        componentes[3] = txtTelefono;
        componentes[4] = txtCelular;
        componentes[5] = txtPorcentajeLima;
        componentes[6] = txtPorcentajeProv;
    }

    private void crearArrayNumMax() {
        numMaximo.add(40); //nombre
        numMaximo.add(40); //direccion
        //    numMaximo.add(40); //email
        numMaximo.add(11); //dni
        numMaximo.add(20); //telefono
        numMaximo.add(20); //celular
        numMaximo.add(5); //porcentajeLima
        numMaximo.add(5); //porcentajeProv
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //nombre
        tipoDato.add("S"); //direccion
        //      tipoDato.add("S"); //email
        tipoDato.add("I"); //dni
        tipoDato.add("S"); //telefono
        tipoDato.add("S"); //celular
        tipoDato.add("D"); //porcentajeLima
        tipoDato.add("D"); //porcentajeProv
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void Limpiar_Campos() {
        txtCelular.setText("");
        txtDNI.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtNombre.setText("");
        txtPorcentajeLima.setText("");
        txtPorcentajeProv.setText("");
        txtTelefono.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVendedores = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtPorcentajeLima = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtPorcentajeProv = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendedores = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS"));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Dirección:");

        jLabel4.setText("E-mail:");

        jLabel5.setText("DNI/RUC:");

        txtNombre.setNextFocusableComponent(txtDireccion);

        txtDireccion.setNextFocusableComponent(txtEmail);

        txtEmail.setNextFocusableComponent(txtDNI);

        txtDNI.setNextFocusableComponent(txtTelefono);

        jLabel6.setText("Teléfono:");

        jLabel7.setText("Celular:");

        jLabel8.setText("Porcentaje Lima:");

        jLabel9.setText("Porcentaje Provincia:");

        txtTelefono.setNextFocusableComponent(txtCelular);

        txtPorcentajeLima.setNextFocusableComponent(txtPorcentajeProv);

        txtCelular.setNextFocusableComponent(txtPorcentajeLima);

        txtPorcentajeProv.setNextFocusableComponent(btnAgregar);

        jLabel10.setText("(*)");

        jLabel13.setText("(*)");

        jLabel14.setText("(*)");

        jLabel15.setText("(*)");

        jLabel11.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(31, 31, 31))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 35, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                            .addComponent(txtDireccion)
                            .addComponent(txtNombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel10))
                    .addComponent(jLabel11))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPorcentajeLima, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPorcentajeProv, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(1, 1, 1)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPorcentajeProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel5)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPorcentajeLima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("VENDEDORES");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "DNI", "Dirección", "Teléfono", "Celular", "E-mail", "% Lima", "% Prov", "idVendedor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVendedores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblVendedores.getTableHeader().setReorderingAllowed(false);
        tblVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendedoresMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblVendedoresMouseReleased(evt);
            }
        });
        tblVendedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblVendedoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblVendedores);
        if (tblVendedores.getColumnModel().getColumnCount() > 0) {
            tblVendedores.getColumnModel().getColumn(1).setMinWidth(100);
            tblVendedores.getColumnModel().getColumn(1).setMaxWidth(100);
            tblVendedores.getColumnModel().getColumn(3).setMinWidth(100);
            tblVendedores.getColumnModel().getColumn(3).setMaxWidth(100);
            tblVendedores.getColumnModel().getColumn(4).setMinWidth(100);
            tblVendedores.getColumnModel().getColumn(4).setMaxWidth(100);
            tblVendedores.getColumnModel().getColumn(6).setMinWidth(70);
            tblVendedores.getColumnModel().getColumn(6).setMaxWidth(70);
            tblVendedores.getColumnModel().getColumn(7).setMinWidth(70);
            tblVendedores.getColumnModel().getColumn(7).setMaxWidth(70);
            tblVendedores.getColumnModel().getColumn(8).setResizable(false);
            tblVendedores.getColumnModel().getColumn(8).setPreferredWidth(0);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
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

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVendedoresLayout = new javax.swing.GroupLayout(panelVendedores);
        panelVendedores.setLayout(panelVendedoresLayout);
        panelVendedoresLayout.setHorizontalGroup(
            panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVendedoresLayout.createSequentialGroup()
                .addGroup(panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelVendedoresLayout.createSequentialGroup()
                        .addGroup(panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelVendedoresLayout.createSequentialGroup()
                                .addGap(399, 399, 399)
                                .addComponent(jLabel1))
                            .addGroup(panelVendedoresLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelVendedoresLayout.setVerticalGroup(
            panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVendedoresLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendedoresMouseClicked
    }//GEN-LAST:event_tblVendedoresMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        String mensaje = "";
        if (txtNombre.getText().equals("")) {
            mensaje = mensaje + "Ingrese el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese la dirección";
        }
        if (txtDNI.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese el DNI/RUC";
        } else {
            if (txtDNI.getText().length() != 11 && txtDNI.getText().length() != 8) {
                mensaje = mensaje + "\n" + "El DNI/RUC debe tener 8 u 11 dígitos";
            }
        }
        if ((txtPorcentajeLima.getText().equals("")) || (Double.parseDouble(txtPorcentajeLima.getText()) == 0)) {
            mensaje = mensaje + "\n" + "Ingrese el porcentaje de Lima";
        }
        if ((txtPorcentajeProv.getText().equals("")) || (Double.parseDouble(txtPorcentajeProv.getText()) == 0)) {
            mensaje = mensaje + "\n" + "Ingrese el porcentaje de Provincia";
        }

        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea añadir vendedor?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                Vendedores vendedor = new Vendedores();

                vendedor.setCelular(txtCelular.getText());
                vendedor.setDireccion(txtDireccion.getText());
                vendedor.setDocumento(txtDNI.getText());
                vendedor.setEmail(txtEmail.getText());
                vendedor.setNombre(txtNombre.getText());
                Double dd = Double.parseDouble(txtPorcentajeLima.getText());
                vendedor.setPorcentajelima(dd);
                dd = Double.parseDouble(txtPorcentajeProv.getText());
                vendedor.setPorcentajeprov(dd);
                vendedor.setTelefono(txtTelefono.getText());

                boolean verific = false;

                verific = servicioVendedor.insertar_Vendedor(vendedor);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Insertó un nuevo vendedor");
                    DefaultTableModel modelo = (DefaultTableModel) tblVendedores.getModel();
                    Object[] lineas = {vendedor.getNombre(), vendedor.getDocumento(),
                        vendedor.getDireccion(), vendedor.getTelefono(), vendedor.getCelular(), vendedor.getEmail(),
                        vendedor.getPorcentajelima(), vendedor.getPorcentajeprov(), vendedor.getIdvendedor()
                    };
                    modelo.addRow(lineas);
                    Limpiar_Campos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error en la inserción");
                }
            } else {
                Limpiar_Campos();
            }
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        filaseleccionadaVendedor = tblVendedores.getSelectedRow();

        int verif;
        String mensaje = "";
        if (txtNombre.getText().equals("")) {
            mensaje = mensaje + "Ingrese el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese la dirección";
        }
        if (txtDNI.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese el DNI/RUC";
        }
        if ((txtPorcentajeLima.getText().equals("")) || (Double.parseDouble(txtPorcentajeLima.getText()) == 0)) {
            mensaje = mensaje + "\n" + "Ingrese el procentaje de Lima";
        }
        if ((txtPorcentajeProv.getText().equals("")) || (Double.parseDouble(txtPorcentajeProv.getText()) == 0)) {
            mensaje = mensaje + "\n" + "Ingrese el procentaje de Provincia";
        }

        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            verif = JOptionPane.showConfirmDialog(null, "¿Desea actualizar vendedor?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                String nombVe = (String) tblVendedores.getValueAt(filaseleccionadaVendedor, 0);

                Vendedores vendedor;
                vendedor = servicioVendedor.obtener_Vendedor_Nombre(nombVe);
                System.out.println("idVend:"+vendedor.getIdvendedor());
                vendedor.setCelular(txtCelular.getText());
                vendedor.setDireccion(txtDireccion.getText());
                vendedor.setDocumento(txtDNI.getText());
                vendedor.setEmail(txtEmail.getText());
                vendedor.setNombre(txtNombre.getText());
                vendedor.setPorcentajelima(Double.parseDouble(txtPorcentajeLima.getText()));
                vendedor.setPorcentajeprov(Double.parseDouble(txtPorcentajeProv.getText()));
                vendedor.setTelefono(txtTelefono.getText());

                boolean verific;

                verific = servicioVendedor.actualizar_Vendedor(vendedor);
                System.out.println("modifica:"+verific);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Actualización de vendedor");
                    modelo.setValueAt(vendedor.getNombre(), filaseleccionadaVendedor, 0);
                    modelo.setValueAt(vendedor.getDocumento(), filaseleccionadaVendedor, 1);
                    modelo.setValueAt(vendedor.getDireccion(), filaseleccionadaVendedor, 2);
                    modelo.setValueAt(vendedor.getTelefono(), filaseleccionadaVendedor, 3);
                    modelo.setValueAt(vendedor.getCelular(), filaseleccionadaVendedor, 4);
                    modelo.setValueAt(vendedor.getEmail(), filaseleccionadaVendedor, 5);
                    modelo.setValueAt(vendedor.getPorcentajelima(), filaseleccionadaVendedor, 6);
                    modelo.setValueAt(vendedor.getPorcentajeprov(), filaseleccionadaVendedor, 7);
                    Limpiar_Campos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error en la actualización");
                }
            } else {
                Limpiar_Campos();
            }
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        servicio_Excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar_Campos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
//        cb_vend.removeAllItems();
//                ListarVendedores();
//        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblVendedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVendedoresKeyReleased
        String nombVe = (String) tblVendedores.getValueAt(tblVendedores.getSelectedRow(), 0);

        Vendedores vendedor;
        vendedor = servicioVendedor.obtener_Vendedor_Nombre(nombVe);

        txtCelular.setText(vendedor.getCelular());
        txtDNI.setText(vendedor.getDocumento());
        txtDireccion.setText(vendedor.getDireccion());
        txtEmail.setText(vendedor.getEmail());
        txtNombre.setText(vendedor.getNombre());
        txtPorcentajeLima.setText("" + vendedor.getPorcentajelima());
        txtPorcentajeProv.setText("" + vendedor.getPorcentajeprov());

        txtTelefono.setText(vendedor.getTelefono());
    }//GEN-LAST:event_tblVendedoresKeyReleased

    private void tblVendedoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendedoresMouseReleased
        Limpiar_Campos();
        DefaultTableModel modelo = (DefaultTableModel) tblVendedores.getModel();
        String nombVe = (String) tblVendedores.getValueAt(tblVendedores.getSelectedRow(), 0);

        Vendedores vendedor;
        vendedor = servicioVendedor.obtener_Vendedor_Nombre(nombVe);
        System.out.print("\n"+servicioVendedor.obtener_Vendedor(35).getNombre()+" + "+servicioVendedor.obtener_Vendedor(35).getIdvendedor());
        Limpiar_Campos();
        txtCelular.setText(vendedor.getCelular());
        txtDNI.setText(vendedor.getDocumento());
        txtDireccion.setText(vendedor.getDireccion());
        txtEmail.setText(vendedor.getEmail());
        txtNombre.setText(vendedor.getNombre());

        txtPorcentajeLima.setText("" + vendedor.getPorcentajelima());
        txtPorcentajeProv.setText("" + vendedor.getPorcentajeprov());

        txtTelefono.setText(vendedor.getTelefono());

    }//GEN-LAST:event_tblVendedoresMouseReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        String valor = String.valueOf(tblVendedores.getValueAt(tblVendedores.getSelectedRow(), 8));
        int idVendedor = Integer.parseInt(String.valueOf(tblVendedores.getValueAt(tblVendedores.getSelectedRow(), 8)));
        Vendedores vendedor = servicioVendedor.obtener_Vendedor(idVendedor);

        filaseleccionadaVendedor = tblVendedores.getSelectedRow();
        if ( filaseleccionadaVendedor != -1 ) {
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea eliminar vendedor?", "CONFIRMACIÓN", 0);

            if ( verif == 0 ) {
                boolean verific = false;
                verific = servicioVendedor.eliminar_Vendedor(vendedor);

                if ( verific == true ) {
                    JOptionPane.showMessageDialog(null, "Eliminó un vendedor");
                    Limpiar_Campos();
                    modelo.removeRow(filaseleccionadaVendedor);

                } else {
                    JOptionPane.showMessageDialog(null, "Error en la eliminación del vendedor");
                }


            } else {
                Limpiar_Campos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a un vendedor antes de eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelVendedores;
    public javax.swing.JTable tblVendedores;
    public javax.swing.JTextField txtCelular;
    public javax.swing.JTextField txtDNI;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPorcentajeLima;
    public javax.swing.JTextField txtPorcentajeProv;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
