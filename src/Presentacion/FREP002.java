package Presentacion;

import Entidades.Clientes;
import static Entidades.Otros.Constante.COLUMNA_ID_CLIENTE;
import Entidades.Transportistas;
import Entidades.Ubigeo;
import Mantenimiento.Navegacion;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Transportista;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Ubigeo;
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
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
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
public class FREP002 extends javax.swing.JFrame {

    Servicio_Cliente servicioCliente;
    Servicio_Excel servicio_Excel;
    Servicio_Transportista servicio_transportista;
    int cant_client;
    Date dia_Actual;
    Servicio_Transportista servicioTransportista;
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    private DefaultTableModel modelo;
    private DefaultComboBoxModel modelTranspCombo;
    private DefaultListModel modelTransp;
    List listaTrans;
    String nombreTransp;
    Transportistas transportistaSelecc;
    int filaTranspSelecc;
    int filaseleccionadaCliente;
    private JTableHeader header;
    private JTableHeader header2;    
    private JPopupMenu renamePopup;
    private JPopupMenu renamePopup2;    
    private JTextField text;
    private JTextField text2;    
    private TableColumn column;
    private TableColumn column2;    
    
    private DefaultComboBoxModel modelDptoCombo;
    private DefaultComboBoxModel modelProvCombo;
    private DefaultComboBoxModel modelDistCombo;
    private List listaDptos;
    private List listaProv;
    private List listaDist;
    private Servicio_Ubigeo servicioUbigeo;

    public FREP002() {
        initComponents();
        Listar_Transportistas();
        comboTransportista.setSelectedIndex(listaTrans.size());
        
        servicioUbigeo = new Servicio_Ubigeo();
        Listar_Departamentos();
        cbDepartamentos.setSelectedIndex(listaDptos.size());
        
        Limpiar_Campos();
        this.setLocationRelativeTo(null);

        modelo = (DefaultTableModel) tblClientes.getModel();
        ((JLabel) tblClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        servicioCliente = new Servicio_Cliente(tblClientes);
        servicio_Excel = new Servicio_Excel(tblClientes, this);
        Listar_Clientes();

        txtID.setEnabled(false);
        txtFecha.setEditable(false);
        passFieldClave.setText("");
        combBoxEstado.setSelectedIndex(1);
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(sdf.format(fecha));
        dia_Actual = CambiarStringToDate(sdf.format(fecha));

        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnAgregar);
        
        asignarEvento();
        SetParametrosTableHeader();
        SetParametrosTableHeader2();        
        ocultarColumnaIdCliente();
    }
    
    private void ocultarColumnaIdCliente() {
        tblClientes.getColumnModel().getColumn(COLUMNA_ID_CLIENTE).setMaxWidth(0);
        tblClientes.getColumnModel().getColumn(COLUMNA_ID_CLIENTE).setMinWidth(0);
        tblClientes.getColumnModel().getColumn(COLUMNA_ID_CLIENTE).setPreferredWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelClientes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCredito = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDesc1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        combBoxEstado = new javax.swing.JComboBox();
        chBoxVerStock = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        txtDesc2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDesc3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDesc4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCausa = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        txtTelefono2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combBoxTipoCliente = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        comboTransportista = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        passFieldClave = new javax.swing.JPasswordField();
        btnvisualizar = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        cbDepartamentos = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbProvincias = new javax.swing.JComboBox();
        cbDistritos = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTES");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos del Cliente"));

        jLabel2.setText("ID:");

        jLabel3.setText("Nombre:");

        jLabel7.setText("Fecha de Bloqueo:");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtEmpresa.setNextFocusableComponent(txtContacto);
        txtEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyTyped(evt);
            }
        });

        txtDireccion.setNextFocusableComponent(txtUbicacion);

        jLabel6.setText("Dirección:");

        jLabel8.setText("Plaza:");

        txtUbicacion.setNextFocusableComponent(txtEmail);

        jLabel9.setText("Contacto:");

        txtContacto.setNextFocusableComponent(txtDireccion);

        jLabel10.setText("Teléfono 1:");

        txtTelefono1.setNextFocusableComponent(txtTelefono2);

        jLabel11.setText("Celular:");

        txtCelular.setNextFocusableComponent(txtRUC);

        jLabel12.setText("RUC:");

        txtRUC.setNextFocusableComponent(txtCredito);

        jLabel13.setText("Causa:");

        jLabel14.setText("Crédito:");

        txtCredito.setNextFocusableComponent(txtDesc1);

        jLabel15.setText("Estado Web:");

        jLabel16.setText("Clave:");

        txtDesc1.setNextFocusableComponent(txtDesc2);
        txtDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                none(evt);
            }
        });

        jLabel18.setText("Desc. 1:");

        jLabel20.setText("Email:");

        combBoxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        combBoxEstado.setNextFocusableComponent(passFieldClave);

        chBoxVerStock.setText("Ver stock");
        chBoxVerStock.setNextFocusableComponent(combBoxTipoCliente);

        jLabel19.setText("Desc. 2:");

        txtDesc2.setNextFocusableComponent(txtDesc3);

        jLabel21.setText("Desc. 3:");

        txtDesc3.setNextFocusableComponent(txtDesc4);

        jLabel22.setText("Desc. 4:");

        txtDesc4.setNextFocusableComponent(chBoxVerStock);

        txtCausa.setColumns(20);
        txtCausa.setRows(5);
        jScrollPane3.setViewportView(txtCausa);

        jLabel23.setText("Teléfono 2:");

        txtTelefono2.setNextFocusableComponent(txtCelular);

        jLabel4.setText("Tipo:");

        combBoxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cliente", "Proveedor", "Ambos" }));
        combBoxTipoCliente.setNextFocusableComponent(combBoxEstado);

        jLabel5.setText("Transportista:");

        jLabel17.setText("(*)");

        jLabel24.setText("(*)");

        btnvisualizar.setText("Visualizar");
        btnvisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvisualizarActionPerformed(evt);
            }
        });

        jLabel25.setText("Departamento:");

        cbDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentosItemStateChanged(evt);
            }
        });
        cbDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDepartamentosActionPerformed(evt);
            }
        });

        jLabel27.setText("Provincia:");

        jLabel28.setText("Distrito:");

        cbProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciasItemStateChanged(evt);
            }
        });

        jLabel29.setText("(*)");

        jLabel30.setText("(*)");

        jLabel31.setText("(*)");

        jLabel32.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addGap(142, 142, 142))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17))
                                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel24))
                                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbProvincias, javax.swing.GroupLayout.Alignment.LEADING, 0, 262, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30))))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combBoxTipoCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDesc1)
                                    .addComponent(txtDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDesc3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDesc4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(chBoxVerStock)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtRUC)
                                            .addComponent(txtTelefono2)
                                            .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel32)))))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combBoxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(passFieldClave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnvisualizar))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(11, 11, 11)
                                .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel15)
                                    .addComponent(combBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(passFieldClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnvisualizar))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel5)
                                                    .addComponent(comboTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel25))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jLabel16)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel13)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbProvincias)
                                                .addComponent(jLabel30))
                                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(5, 5, 5)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDesc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtDesc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtDesc4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel32))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chBoxVerStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combBoxTipoCliente)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre (Buscar)", "Contacto", "Dirección", "Plaza", "RUC", "Teléfono", "Celular", "idCliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblClientesMouseReleased(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblClientesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(1).setMinWidth(140);
            tblClientes.getColumnModel().getColumn(1).setMaxWidth(140);
            tblClientes.getColumnModel().getColumn(3).setMinWidth(85);
            tblClientes.getColumnModel().getColumn(3).setMaxWidth(85);
            tblClientes.getColumnModel().getColumn(4).setMinWidth(90);
            tblClientes.getColumnModel().getColumn(4).setMaxWidth(90);
            tblClientes.getColumnModel().getColumn(5).setMinWidth(100);
            tblClientes.getColumnModel().getColumn(5).setMaxWidth(100);
            tblClientes.getColumnModel().getColumn(6).setMinWidth(100);
            tblClientes.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Listar_Clientes() {
        cant_client = servicioCliente.Listar_clientes(modelo);
        cant_client++;
        txtID.setText("" + (servicioCliente.getUltimo_id() + 1));
    }

    private void Listar_Transportistas() {
        //    modelTransp = new DefaultListModel();
        modelTranspCombo = new DefaultComboBoxModel();

        //   modelTransp.clear();

        servicioTransportista = new Servicio_Transportista();
        listaTrans = servicioTransportista.Listar_Transportistas_Order_Asc_Nombre();
        for (int i = 0; i < listaTrans.size(); i++) {
            //        modelTranspCombo.addElement(listaTrans.get(i));
            modelTranspCombo.addElement(listaTrans.get(i));

        }
        modelTranspCombo.addElement("");
        comboTransportista.setModel(modelTranspCombo);
        //   listTransportista.setModel(modelTransp);
    }
    
    private void Listar_Departamentos() {
        modelDptoCombo = new DefaultComboBoxModel();
        listaDptos = servicioUbigeo.Listar_Departamentos_Order_Asc_Nombre();
        for (int i = 0; i < listaDptos.size(); i++) {
            modelDptoCombo.addElement(listaDptos.get(i));
        }
        modelDptoCombo.addElement("");
        cbDepartamentos.setModel(modelDptoCombo);
    }
    
    private void Listar_Provincias(String departamento) {
        modelProvCombo = new DefaultComboBoxModel();
        listaProv = servicioUbigeo.Listar_Provincias_Order_Asc_Nombre(departamento);
        for (int i = 0; i < listaProv.size(); i++) {
            modelProvCombo.addElement(listaProv.get(i));
        }
        modelProvCombo.addElement("");
        cbProvincias.setModel(modelProvCombo);
    }

    private void Listar_Distritos(String departamento, String provincia) {
        modelDistCombo = new DefaultComboBoxModel();
        listaDist = servicioUbigeo.Listar_Distritos_Order_Asc_Nombre(departamento, provincia);
        for (int i = 0; i < listaDist.size(); i++) {
            modelDistCombo.addElement(listaDist.get(i));
        }
        modelDistCombo.addElement("");
        cbDistritos.setModel(modelDistCombo);
    }
    
    private void crearArrayComponente() {
        componentes = new JTextField[13];
        componentes[0] = txtEmpresa;
        componentes[1] = txtContacto;
        componentes[2] = txtDireccion;
        componentes[3] = txtUbicacion;
        //    componentes[4] = txtEmail;
        componentes[4] = txtTelefono1;
        componentes[5] = txtTelefono2;
        componentes[6] = txtCelular;
        componentes[7] = txtRUC;
        componentes[8] = txtCredito;
        componentes[9] = txtDesc1;
        componentes[10] = txtDesc2;
        componentes[11] = txtDesc3;
        componentes[12] = txtDesc4;
    }

    private void crearArrayNumMax() {
        numMaximo.add(50); //empresa
        numMaximo.add(40); //contacto
        numMaximo.add(80); //direccion
        numMaximo.add(50); //ubicacion
        numMaximo.add(20); //telefon1
        numMaximo.add(20); //telefon2
        numMaximo.add(13); //celular
        numMaximo.add(11); //ruc
        numMaximo.add(8); //credito
        numMaximo.add(4); //desc1
        numMaximo.add(4); //desc2
        numMaximo.add(4); //desc3
        numMaximo.add(4); //desc4
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //empresa
        tipoDato.add("S"); //contacto
        tipoDato.add("S"); //direccion
        tipoDato.add("S"); //ubicacion
        tipoDato.add("S"); //telefon1
        tipoDato.add("S"); //telefon2
        tipoDato.add("S"); //celular
        tipoDato.add("I"); //ruc
        tipoDato.add("D"); //credito
        tipoDato.add("D"); //desc1
        tipoDato.add("D"); //desc2
        tipoDato.add("D"); //desc3
        tipoDato.add("D"); //desc4
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void Limpiar_Campos() {
        txtEmpresa.setText("");
        txtDireccion.setText("");
        txtUbicacion.setText("");
        txtContacto.setText("");
        txtEmail.setText("");
        txtTelefono1.setText("");
        txtTelefono2.setText("");
        txtCelular.setText("");
        txtRUC.setText("");
        txtCausa.setText("");
        txtCredito.setText("");
        passFieldClave.setText("");
        txtDesc1.setText("");
        txtDesc2.setText("");
        txtDesc3.setText("");
        txtDesc4.setText("");
        chBoxVerStock.setSelected(false);
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(sdf.format(fecha));
        combBoxTipoCliente.setSelectedIndex(0);
        combBoxEstado.setSelectedIndex(1);
        comboTransportista.setSelectedIndex(listaTrans.size());
        
        if ( listaDptos != null ) {
            cbDepartamentos.setSelectedIndex(listaDptos.size());
        }
        if ( listaProv != null ) {
            cbProvincias.setSelectedIndex(listaProv.size());
        }
        if ( listaDist != null ) {
            cbDistritos.setSelectedIndex(listaDist.size());
        }
    }

    private Date CambiarStringToDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        strFecha = txtFecha.getText();
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        filaseleccionadaCliente = tblClientes.getSelectedRow();
        Clientes cliente = new Clientes();
        String mensaje = "";
        if (txtEmpresa.getText().equals("")) {
            mensaje = mensaje + "Ingrese el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese la dirección";
        }
        if (txtRUC.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese el RUC";
        }
//        if (txtUbicacion.getText().equals("")) {
//            mensaje = mensaje + "\n" + "Ingrese la plaza";
//        }
        if (combBoxEstado.getSelectedIndex() == 0) {
            cliente.setEstadoweb("A");
            if (passFieldClave.getText().equals("")) {
                mensaje = mensaje + "\n" + "Ingrese la clave web";
            }
        }
        
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
        String dist = String.valueOf(cbDistritos.getSelectedItem());
//        System.out.println("dpto(*):" + dpto);
//        System.out.println("prov(*):" + prov);
//        System.out.println("dist(*):" + dist);
        
        if ( "".equals(dpto) ) {
            mensaje = mensaje + "\nSeleccione un departamento";
        }
        if ( "".equals(prov) ) {
            mensaje = mensaje + "\nSeleccione una provincia";
        }
        if ( "".equals(dist) ) {
            mensaje = mensaje + "\nSeleccione un distrito";
        }
        
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
//            System.out.println("confirmar actualizar...");
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea actualizar cliente?", "CONFIRMACIÓN", 0);
//            System.out.println("verif:" + verif);
            if (verif == 0) {
                cliente.setFechabloqueo(dia_Actual);
                cliente.setIdcliente(Integer.parseInt(txtID.getText()));
                cliente.setNombre(txtEmpresa.getText());

                cliente.setDireccion(txtDireccion.getText());
//                System.out.println("cliente.setDir:" + cliente.getDireccion());
//                System.out.println("dir.getText:" + txtDireccion.getText());
//                System.out.println("ubic:" + txtUbicacion.getText());
                cliente.setPlaza(txtUbicacion.getText());
//                System.out.println("plaza:" + cliente.getPlaza());
                cliente.setContacto(txtContacto.getText());
                cliente.setEmail(txtEmail.getText());

                cliente.setTelefonofijo(txtTelefono1.getText());
                cliente.setTelefonofijo2(txtTelefono2.getText());
                cliente.setTelefonocel(txtCelular.getText());
                cliente.setRuc(txtRUC.getText());
                cliente.setCausabloqueo(txtCausa.getText());
                
                if (txtCredito.equals("")) {
                    cliente.setCredito(0.0);
                } else {
                    cliente.setCredito(validarDoble(txtCredito));
                }
                nombreTransp = (String) comboTransportista.getSelectedItem();
                if (nombreTransp.equals("")) {
                    transportistaSelecc = null;
                } else {
                    transportistaSelecc = servicioTransportista.Obtener_Transportista_Por_Nombre(nombreTransp);
                }
                cliente.setTransportistas(transportistaSelecc);
//                System.out.println("transp"+cliente.getTransportistas().getNombre());
                if (chBoxVerStock.isSelected()) {
                    cliente.setVerstock("S");
                } else {
                    cliente.setVerstock("N");
                }

                if (combBoxEstado.getSelectedIndex() == 0) {
                    cliente.setEstadoweb("A");
                } else {
                    cliente.setEstadoweb("D");
                }

                if (combBoxTipoCliente.getSelectedIndex() == 0) {
                    cliente.setTipo("C");
                } else if (combBoxTipoCliente.getSelectedIndex() == 1) {
                    cliente.setTipo("P");
                } else {
                    cliente.setTipo("A");
                }
                cliente.setClave(passFieldClave.getText());
                if (txtDesc1.equals("")) {
                    cliente.setDescuento1(0.0);
                } else {
                    cliente.setDescuento1(validarDoble(txtDesc1));
                }
                if (txtDesc2.equals("")) {
                    cliente.setDescuento2(0.0);
                } else {
                    cliente.setDescuento2(validarDoble(txtDesc1));
                }
                if (txtDesc3.equals("")) {
                    cliente.setDescuento3(0.0);
                } else {
                    cliente.setDescuento3(validarDoble(txtDesc1));
                }
                if (txtDesc4.equals("")) {
                    cliente.setDescuento4(0.0);
                } else {
                    cliente.setDescuento4(validarDoble(txtDesc1));
                }
                
                Ubigeo ubigeo = servicioUbigeo.buscarUbigeo(dpto, prov, dist);
//                System.out.println("ubigeo encontrado(modificar):" + ubigeo.getIdubigeo());
                cliente.setUbigeo(ubigeo);

                boolean verific = false;

//                verific = servicioCliente.actualizar_Cliente(cliente);
                verific = servicioCliente.Modificar_Cliente(cliente);
//                System.out.println("verific:" + verific);
                if ( verific == true ) {
                    JOptionPane.showMessageDialog(null, "Actualización de cliente");
                    modelo.setValueAt(cliente.getNombre(), filaseleccionadaCliente, 0);
                    modelo.setValueAt(cliente.getContacto(), filaseleccionadaCliente, 1);
                    modelo.setValueAt(cliente.getDireccion(), filaseleccionadaCliente, 2);
                    modelo.setValueAt(cliente.getPlaza(), filaseleccionadaCliente, 3);
                    modelo.setValueAt(cliente.getRuc(), filaseleccionadaCliente, 4);
                    modelo.setValueAt(cliente.getTelefonofijo(), filaseleccionadaCliente, 5);
                    modelo.setValueAt(cliente.getTelefonocel(), filaseleccionadaCliente, 6);
                    modelo.setValueAt(cliente.getIdcliente(), filaseleccionadaCliente, 7);
                    //    Listar_Clientes();
                    Limpiar_Campos();
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la actualización");
                }
            } else {
                Limpiar_Campos();
            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String mensaje = "";

        Clientes cliente = new Clientes();
        if (txtEmpresa.getText().equals("")) {
            mensaje = mensaje + "Ingrese el nombre";
        }
        if (txtDireccion.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese la dirección";
        }
        if (txtRUC.getText().equals("")) {
            mensaje = mensaje + "\n" + "Ingrese el RUC";
        } else if (txtRUC.getText().length() != 11) {
            mensaje = mensaje + "\n" + "El RUC debe tener 11 dígitos";
        }
 //        if (txtUbicacion.getText().equals("")) {
//            mensaje = mensaje + "\n" + "Ingrese la plaza";
//        }
        if (combBoxEstado.getSelectedIndex() == 0) {
            cliente.setEstadoweb("A");
            if (passFieldClave.getText().equals("")) {
                mensaje = mensaje + "\n" + "Ingrese la clave web";
            }
        }
        
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
        String dist = String.valueOf(cbDistritos.getSelectedItem());
        
        if ("".equals(dpto) ) {
            mensaje = mensaje + "\nSeleccione un departamento";
        }
        if ("".equals(prov) ) {
            mensaje = mensaje + "\nSeleccione una provincia";
        }
        if ("".equals(dist) ) {
            mensaje = mensaje + "\nSeleccione un distrito";
        }
        
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea añadir cliente?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                servicioCliente.setUltimo_id(servicioCliente.getUltimo_id() + 1);
                Date dia;
                dia = CambiarStringToDate(txtFecha.getText());
                cliente.setFechabloqueo(dia);
                cliente.setNombre(txtEmpresa.getText());
                cliente.setDireccion(txtDireccion.getText());
                cliente.setPlaza(txtUbicacion.getText());
                cliente.setContacto(txtContacto.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefonofijo(txtTelefono1.getText());
                cliente.setTelefonofijo2(txtTelefono2.getText());
                cliente.setTelefonocel(txtCelular.getText());
                cliente.setRuc(txtRUC.getText());
                cliente.setCausabloqueo(txtCausa.getText());
                cliente.setCredito(validarDoble(txtCredito));
                cliente.setTransportistas(transportistaSelecc);
                
//                Ubigeo ubigeo = new Ubigeo();
                Ubigeo ubigeo = servicioUbigeo.buscarUbigeo(dpto, prov, dist);
                System.out.println("ubigeo encontrado:" + ubigeo.getIdubigeo());
                cliente.setUbigeo(ubigeo);
                
                if (chBoxVerStock.isSelected()) {
                    cliente.setVerstock("S");
                } else {
                    cliente.setVerstock("N");
                }

                if (combBoxEstado.getSelectedIndex() == 0) {
                    cliente.setEstadoweb("A");
                    if (passFieldClave.getText().equals("")) {
                    }
                } else {
                    cliente.setEstadoweb("D");
                }
                if (combBoxTipoCliente.getSelectedIndex() == 0) {
                    cliente.setTipo("C");
                } else {
                    if (combBoxTipoCliente.getSelectedIndex() == 1) {
                        cliente.setTipo("P");
                    } else {
                        cliente.setTipo("A");
                    }
                }

                cliente.setClave(passFieldClave.getText());
                cliente.setDescuento1(validarDoble(txtDesc1));
                cliente.setDescuento2(validarDoble(txtDesc2));
                cliente.setDescuento3(validarDoble(txtDesc3));
                cliente.setDescuento4(validarDoble(txtDesc4));

                boolean verific = false;

                verific = servicioCliente.insertar_Cliente(cliente);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Insertó un nuevo cliente");
                    DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
                    Object[] clientes = {cliente.getNombre(), cliente.getContacto(), cliente.getDireccion(),
                        cliente.getPlaza(), 
//                        cliente.getEmail(), 
                        cliente.getRuc(), cliente.getTelefonofijo(),
                        cliente.getTelefonocel(), cliente.getIdcliente()};
                    modelo.addRow(clientes);
                    Limpiar_Campos();

                    //    servicioCliente.setUltimo_id(servicioCliente.getUltimo_id()+1);
                    txtID.setText("" + (servicioCliente.getUltimo_id() + 1));
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la inserción");
                }

            } else {
                Limpiar_Campos();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar_Campos();
        limpiarModelo();
//        tblClientes.setModel(new DefaultTableModel());
        servicioCliente.Listar_clientes(modelo);
        txtID.setText("" + (servicioCliente.getUltimo_id() + 1));
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void limpiarModelo(){
        for (int i = 0; i < tblClientes.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (!txtEmpresa.getText().equals("")) {
            DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
            
            int idCliente = Integer.parseInt(String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 7)));
            Clientes cliente = servicioCliente.obtener_Cliente(idCliente);

            filaseleccionadaCliente = tblClientes.getSelectedRow();
            int verif;
            verif = JOptionPane.showConfirmDialog(null, "¿Desea eliminar cliente?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                boolean verific = false;
                //  Limpiar_Tabla();
                verific = servicioCliente.eliminar_Cliente(cliente);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Eliminó un cliente");
                    Limpiar_Campos();
                    modelo.removeRow(filaseleccionadaCliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la eliminación del cliente");
                }

            } else {
                Limpiar_Campos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a un cliente antes de eliminar");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        servicio_Excel.Exportar_Excel(1);
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblClientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyTyped
    }//GEN-LAST:event_tblClientesKeyTyped

    private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased
        
        if ( tblClientes.getSelectedRow() != -1 ) {
            int idCliente = Integer.parseInt(String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 7)));
            Clientes cliente = servicioCliente.obtener_Cliente(idCliente);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (cliente.getFechabloqueo() != null) {
                txtFecha.setText(sdf.format(cliente.getFechabloqueo()));
            }
          
            txtID.setText(""+cliente.getIdcliente());
            txtEmpresa.setText(cliente.getNombre());
            txtDireccion.setText(cliente.getDireccion());
            txtUbicacion.setText(cliente.getPlaza());
            txtEmail.setText(cliente.getEmail());
            txtTelefono1.setText(cliente.getTelefonofijo());
            txtTelefono2.setText(cliente.getTelefonofijo2());
            txtCelular.setText(cliente.getTelefonocel());
            txtRUC.setText("" + cliente.getRuc());
            txtCausa.setText(cliente.getCausabloqueo());
            txtContacto.setText(cliente.getContacto());
            if (cliente.getCredito() == null) {
                txtCredito.setText("");
            } else {
                txtCredito.setText("" + cliente.getCredito());
            }

            passFieldClave.setText(cliente.getClave());

            if (cliente.getDescuento1() == null) {
                txtDesc1.setText("");
            } else {
                txtDesc1.setText("" + cliente.getDescuento1());
            }
            if (cliente.getDescuento2() == null) {
                txtDesc2.setText("");
            } else {
                txtDesc2.setText("" + cliente.getDescuento2());
            }
            if (cliente.getDescuento3() == null) {
                txtDesc3.setText("");
            } else {
                txtDesc3.setText("" + cliente.getDescuento3());
            }
            if (cliente.getDescuento4() == null) {
                txtDesc4.setText("");
            } else {
                txtDesc4.setText("" + cliente.getDescuento4());
            }

            if ( cliente.getTipo() != null ) {
                if (cliente.getTipo().equals("C")) {
                    combBoxTipoCliente.setSelectedIndex(0);
                } else {
                    if (cliente.getTipo().equals("P")) {
                        combBoxTipoCliente.setSelectedIndex(1);
                    } else {
                        combBoxTipoCliente.setSelectedIndex(2);
                    }
                }
            }

            if (cliente.getVerstock() != null) {
                if (cliente.getVerstock().equals("S")) {
                    chBoxVerStock.setSelected(true);
                } else {
                    chBoxVerStock.setSelected(false);
                }

            }

            if (cliente.getEstadoweb() != null) {
                if (cliente.getEstadoweb().equals("A")) {
                    combBoxEstado.setSelectedIndex(0);
                } else {
                    combBoxEstado.setSelectedIndex(1);
                }
            } else {
                combBoxEstado.setSelectedIndex(1);
            }

            if (cliente.getTransportistas() != null) {
                System.out.println("lista Trans:" + listaTrans.size());
                for (int i = 0; i < listaTrans.size(); i++) {
                    System.out.println("entrooo listaTrans:" + cliente.getTransportistas().getNombre());
                    if (cliente.getTransportistas().getNombre().equals(modelTranspCombo.getElementAt(i))) {
                        System.out.println("entrooo jlist" + modelTranspCombo.getElementAt(i));
                        comboTransportista.setSelectedIndex(i);
                    }
                }
            } else {
                System.out.println("no tiene");
                comboTransportista.setSelectedIndex(listaTrans.size());
            }
        } 
//        else {
//            System.out.println("No se ha elegido ninguna fila del browser");
//        }
    }//GEN-LAST:event_tblClientesKeyReleased

    private void tblClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseReleased
/*
        if ( tblClientes.getSelectedRow() != -1 ) {
            int idCliente = Integer.parseInt(String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 7)));
            Clientes cliente = servicioCliente.obtener_Cliente(idCliente);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (cliente.getFechabloqueo() != null) {
                txtFecha.setText(sdf.format(cliente.getFechabloqueo()));
            }
            txtID.setText("" + cliente.getIdcliente());
            txtEmpresa.setText(cliente.getNombre());
            txtDireccion.setText(cliente.getDireccion());
            System.out.println("cliente.getDir:"  + cliente.getDireccion());
            System.out.println("direccion:" + txtDireccion.getText());
            txtUbicacion.setText(cliente.getPlaza());
            System.out.println("plaza:" + cliente.getPlaza());
            System.out.println("ubicacion:" + txtUbicacion.getText());
            txtEmail.setText(cliente.getEmail());
            txtTelefono1.setText(cliente.getTelefonofijo());
            txtTelefono2.setText(cliente.getTelefonofijo2());
            txtCelular.setText(cliente.getTelefonocel());
            txtRUC.setText("" + cliente.getRuc());
            txtCausa.setText(cliente.getCausabloqueo());
            txtContacto.setText(cliente.getContacto());
            if (cliente.getCredito() == null) {
                txtCredito.setText("");
            } else {
                txtCredito.setText("" + cliente.getCredito());
            }

            passFieldClave.setText(cliente.getClave());

            if (cliente.getDescuento1() == null) {
                txtDesc1.setText("");
            } else {
                txtDesc1.setText("" + cliente.getDescuento1());
            }
            if (cliente.getDescuento2() == null) {
                txtDesc2.setText("");
            } else {
                txtDesc2.setText("" + cliente.getDescuento2());
            }
            if (cliente.getDescuento3() == null) {
                txtDesc3.setText("");
            } else {
                txtDesc3.setText("" + cliente.getDescuento3());
            }
            if (cliente.getDescuento4() == null) {
                txtDesc4.setText("");
            } else {
                txtDesc4.setText("" + cliente.getDescuento4());
            }

            if ( cliente.getTipo() != null ) {
                if (cliente.getTipo().equals("C")) {
                    System.out.println("clienteeee");
                    combBoxTipoCliente.setSelectedIndex(0);
                } else {
                    if (cliente.getTipo().equals("P")) {
                        System.out.println("proveeed");
                        combBoxTipoCliente.setSelectedIndex(1);
                    } else {
                        System.out.println("ambossss");
                        combBoxTipoCliente.setSelectedIndex(2);
                    }
                }
            }

            if (cliente.getVerstock() != null) {
                if (cliente.getVerstock().equals("S")) {
                    chBoxVerStock.setSelected(true);
                } else {
                    chBoxVerStock.setSelected(false);
                }
            }

            if (cliente.getEstadoweb() != null) {
                if (cliente.getEstadoweb().equals("A")) {
                    combBoxEstado.setSelectedIndex(0);
                } else {
                    combBoxEstado.setSelectedIndex(1);
                }
            } else {
                combBoxEstado.setSelectedIndex(1);
            }

            if (cliente.getTransportistas() != null) {
                System.out.println("lista Trans:" + listaTrans.size());
                System.out.println("entrooo listaTrans:" + cliente.getTransportistas().getNombre());
                for (int i = 0; i < listaTrans.size(); i++) {
                    if (cliente.getTransportistas().getNombre().equals(modelTranspCombo.getElementAt(i))) {
                        System.out.println("entrooo jlist" + modelTranspCombo.getElementAt(i));
                        comboTransportista.setSelectedIndex(i);
                    }
                }
            } else {
                comboTransportista.setSelectedIndex(listaTrans.size());
            }
        }*/
    }//GEN-LAST:event_tblClientesMouseReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
         if ( tblClientes.getSelectedRow() != -1 ) {
            int idCliente = Integer.parseInt(String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 7)));
            String nombreCliente = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
            String direccion = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 2));
            String ruc = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 4));
//            Clientes cliente = servicioCliente.obtener_Cliente(idCliente);
            Clientes cliente = servicioCliente.Obtener_Cliente_Por_IdCliente(idCliente);
//             System.out.println("idUbigeo(CLIENTE MODIFICADO):" + cliente.getUbigeo().getIdubigeo());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if (cliente.getFechabloqueo() != null) {
                txtFecha.setText(sdf.format(cliente.getFechabloqueo()));
            }
            txtID.setText("" + idCliente);
            txtEmpresa.setText(nombreCliente);
            txtDireccion.setText(direccion);
            txtEmail.setText(cliente.getEmail());
            txtTelefono1.setText(cliente.getTelefonofijo());
            txtTelefono2.setText(cliente.getTelefonofijo2());
            txtRUC.setText(ruc);
            txtCausa.setText(cliente.getCausabloqueo());
                
            String contacto = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 1));
            contacto = "null".equals(contacto) ? "" : contacto;
            txtContacto.setText(contacto);
            
            String plaza = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 3));
            plaza = "null".equals(plaza) ? "" : plaza;
            txtUbicacion.setText(plaza);
            
            String celular = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 6));
            celular = "null".equals(celular) ? "" : celular;
            txtCelular.setText(celular);
            
            if (cliente.getCredito() == null) {
                txtCredito.setText("");
            } else {
                txtCredito.setText("" + cliente.getCredito());
            }

            passFieldClave.setText(cliente.getClave());

            if (cliente.getDescuento1() == null) {
                txtDesc1.setText("");
            } else {
                txtDesc1.setText("" + cliente.getDescuento1());
            }
            if (cliente.getDescuento2() == null) {
                txtDesc2.setText("");
            } else {
                txtDesc2.setText("" + cliente.getDescuento2());
            }
            if (cliente.getDescuento3() == null) {
                txtDesc3.setText("");
            } else {
                txtDesc3.setText("" + cliente.getDescuento3());
            }
            if (cliente.getDescuento4() == null) {
                txtDesc4.setText("");
            } else {
                txtDesc4.setText("" + cliente.getDescuento4());
            }

            if ( cliente.getTipo() != null ) {
                if (cliente.getTipo().equals("C")) {
                    combBoxTipoCliente.setSelectedIndex(0);
                } else {
                    if (cliente.getTipo().equals("P")) {
                        combBoxTipoCliente.setSelectedIndex(1);
                    } else {
                        combBoxTipoCliente.setSelectedIndex(2);
                    }
                }
            }

            if (cliente.getVerstock() != null) {
                if (cliente.getVerstock().equals("S")) {
                    chBoxVerStock.setSelected(true);
                } else {
                    chBoxVerStock.setSelected(false);
                }
            }

            if (cliente.getEstadoweb() != null) {
                if (cliente.getEstadoweb().equals("A")) {
                    combBoxEstado.setSelectedIndex(0);
                } else {
                    combBoxEstado.setSelectedIndex(1);
                }
            } else {
                combBoxEstado.setSelectedIndex(1);
            }

            if (cliente.getTransportistas() != null) {
                for (int i = 0; i < listaTrans.size(); i++) {
                    if (cliente.getTransportistas().getNombre().equals(modelTranspCombo.getElementAt(i))) {
                        comboTransportista.setSelectedIndex(i);
                    }
                }
            } else {
                comboTransportista.setSelectedIndex(listaTrans.size());
            }
            
            Ubigeo ubigeo = cliente.getUbigeo();
            
            if (ubigeo != null )  {
                
                System.out.println("Ubigeo seleccionado : " + ubigeo);
                
                String dpto = ubigeo.getDepartamento();
                String prov = ubigeo.getProvincia();
                String dist = ubigeo.getDistrito();
                
                for (int i = 0; i < listaDptos.size(); i++) {
                    if (dpto.equals(modelDptoCombo.getElementAt(i))) {
                        cbDepartamentos.setSelectedIndex(i);
                    }
                }
                
                for (int i = 0; i < listaProv.size(); i++) {
                    if (prov.equals(modelProvCombo.getElementAt(i))) {
                        cbProvincias.setSelectedIndex(i);
                    }
                }
                
                for (int i = 0; i < listaDist.size(); i++) {
                    if (dist.equals(modelDistCombo.getElementAt(i))) {
                        cbDistritos.setSelectedIndex(i);
                    }
                }
            } else {
                cbDepartamentos.setSelectedIndex(listaDptos.size());
                cbProvincias.setSelectedIndex(listaProv.size());
                cbDistritos.setSelectedIndex(listaDist.size());
            }
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnvisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvisualizarActionPerformed
        System.out.println("sad"+passFieldClave.getText());
        JOptionPane.showMessageDialog(null, "La clave es : " + passFieldClave.getText(),"Clave",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnvisualizarActionPerformed

    private void none(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_none
        // TODO add your handling code here:
    }//GEN-LAST:event_none

    private void txtEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpresaKeyTyped

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void cbDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentosItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
//        System.out.println("departamento seleccionado:" + dpto);
        Listar_Provincias(dpto);
        cbProvincias.setSelectedItem("");
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbDepartamentosItemStateChanged

    private void cbProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciasItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
//        System.out.println("departamento seleccionado:" + dpto);
//        System.out.println("provincia seleccionada:" + prov);
        Listar_Distritos(dpto, prov);
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbProvinciasItemStateChanged

    private void cbDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDepartamentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDepartamentosActionPerformed

    private void comboTransportistaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  
    
    private Double validarDoble(JTextField txt) {
        if (txt.getText().equals("")) {
            return 0.0;
        } else {
            return Double.parseDouble(txt.getText());
        }
    }

    private int validarInt(JTextField txt) {
        if (txt.getText().equals("")) {
            return 0;
        } else {
            return Integer.parseInt(txt.getText());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnSalir;
    public javax.swing.JButton btnvisualizar;
    public javax.swing.JComboBox cbDepartamentos;
    public javax.swing.JComboBox cbDistritos;
    public javax.swing.JComboBox cbProvincias;
    public javax.swing.JCheckBox chBoxVerStock;
    public javax.swing.JComboBox combBoxEstado;
    public javax.swing.JComboBox combBoxTipoCliente;
    public javax.swing.JComboBox comboTransportista;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JPanel panelClientes;
    public javax.swing.JPasswordField passFieldClave;
    public javax.swing.JTable tblClientes;
    public javax.swing.JTextArea txtCausa;
    public javax.swing.JTextField txtCelular;
    public javax.swing.JTextField txtContacto;
    public javax.swing.JTextField txtCredito;
    public javax.swing.JTextField txtDesc1;
    public javax.swing.JTextField txtDesc2;
    public javax.swing.JTextField txtDesc3;
    public javax.swing.JTextField txtDesc4;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtEmpresa;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtID;
    public javax.swing.JTextField txtRUC;
    public javax.swing.JTextField txtTelefono1;
    public javax.swing.JTextField txtTelefono2;
    public javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables

    private void SetParametrosTableHeader() {
        header = tblClientes.getTableHeader();
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
                servicioCliente.ListarxNombre(modelo, nombre);
            }
        });

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);
    }

    
    private void SetParametrosTableHeader2() {
        header2 = tblClientes.getTableHeader();
        header2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    editColumnAt(event.getPoint());
                }
            }

            private void editColumnAt(Point point) {
                int columnIndex = header2.columnAtPoint(point);

                if (columnIndex == 3) {
                    column2 = header2.getColumnModel().getColumn(columnIndex);
                    Rectangle columnRectangle = header2.getHeaderRect(columnIndex);

                    text2.setText(column2.getHeaderValue().toString());
                    renamePopup2.setPreferredSize(
                            new Dimension(columnRectangle.width, columnRectangle.height - 1));
                    renamePopup2.show(header2, columnRectangle.x, 0);

                    text2.requestFocusInWindow();
                    text2.selectAll();
                }
            }
        });

        text2 = new JTextField();
        text2.setBorder(null);
        text2.addKeyListener(new KeyListener() {
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

        text2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxPlaza();
            }

            private void BuscarxPlaza() {
                String plaza = text2.getText();
                if (text2.getText().equals("")) {
                    text2.setText("Plaza (Buscar)");
                }
                column2.setHeaderValue(text2.getText());
                renamePopup2.setVisible(false);
                header2.repaint();
                BorrarTabla();
                servicioCliente.ListarxPlaza(modelo, plaza);
            }
        });

        renamePopup2 = new JPopupMenu();
        renamePopup2.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup2.add(text2);
    }    
    
    

    private void BorrarTabla() {
        int numRows = modelo.getRowCount();
        for (int i = 0; i < numRows; i++) {
            modelo.removeRow(0);
        }
    }
}
