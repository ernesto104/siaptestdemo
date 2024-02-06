package Presentacion.Notas;

import Entidades.Clientes;
import Entidades.Control;
import static Entidades.Otros.Constante.ND_MOD_UNICA;
import static Entidades.Otros.Constante.NOTA_DEBITO;
import Entidades.Otros.Fecha;
import Entidades.Otros.Monetario;
import Entidades.Tipocambio;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_TipoCambio;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Lesly Aguilar
 * @modificado por: Ledis Rivera Ch.
 */
public final class IU_Emisión_Notas_Debito extends javax.swing.JFrame {

    Servicio_Cliente servicioCliente;
    private DefaultListModel modelClient;
    List listaClient;
    Clientes clienteSelecc;
    String nombreClient;
    double totalNota = 0.0;
    boolean hayvalor;
    private DefaultTableModel modelo;
    ControlDAO controlDAO;
    int nroLineaNotaCred;
    TablaNotas2Columnas tabla;
    int igv;
    Usuarios usuario;
    double subTotal;
    double igvv;
    double total;
    private DateFormat df;
    Tipocambio tipoCambio;
    boolean bandera_moneda = true;

    public IU_Emisión_Notas_Debito(Usuarios u) throws ParseException {
        tabla = new TablaNotas2Columnas(this);
        initComponents();
        this.setLocationRelativeTo(null);
        usuario = u;
        Listar_Clientes();

        iniciar();
         
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
    }

    private void Listar_Clientes() {
        modelClient = new DefaultListModel();
        modelClient.clear();
        servicioCliente = new Servicio_Cliente();
        listaClient = servicioCliente.Listar_Clientes_Order_Asc_Nombre();
        
        for ( int i = 0; i < listaClient.size(); i++ ) {
            modelClient.addElement(listaClient.get(i));
        }
        listaCliente.setModel(modelClient);
    }
    
    public void cambiar() {
        if ( radBtnDolares.isSelected() ) {
            bandera_moneda  = true;
            txtlab1.setText("(US$):");
            txtlab2.setText("(US$):");
            txtlab3.setText("(US$):");
            
        } else {
            bandera_moneda  = false;
            txtlab1.setText("(S/.):");
            txtlab2.setText("(S/.):");
            txtlab3.setText("(S/.):");
        }
    }

    public void iniciar() throws ParseException {
        txtDireccion.setEditable(false);
        txtEmail.setEditable(false);
        txtNombre.setEditable(false);
        txtRUC.setEditable(false);
        txtSubTotal.setEditable(false);
        txtTelefono1.setEditable(false);
        txtTelefono2.setEditable(false);
        txtIGV.setEditable(false);
        txtTotal.setEditable(false);

        controlDAO = new ControlDAO(this);
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        nroLineaNotaCred = control.getNrolineanc();
        igv = control.getImpuestoigv();

        setAnchoColumnas();
        tblNotaDebito.setCellSelectionEnabled(true);
        //   BorrarTabla();
        tabla.asignarFilasDebito(nroLineaNotaCred);
        
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String FechaEmision = sdf.format(fecha);
        
//        System.out.println("fecha emision:"+FechaEmision);
        Servicio_TipoCambio s = new Servicio_TipoCambio();
        tipoCambio = new Tipocambio();
        df = new SimpleDateFormat("dd/MM/yyyy");
        tipoCambio = s.obtenerTipoCambio(df.parse(FechaEmision));
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblNotaDebito.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblNotaDebito.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tblNotaDebito.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            
            switch ( i ) {
                case 0:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                    
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public boolean TablaVacia() {
//        System.out.println("entroo");
        for ( int i = 0; i < tblNotaDebito.getRowCount(); i++ ) {
            for ( int j = 0; j < tblNotaDebito.getColumnCount(); j++ ) {
                if ( tblNotaDebito.getValueAt(i, j) != null ) {
//                    System.out.println("entra");
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelEmisionNotasDebito = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCliente = new javax.swing.JList();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNotaDebito = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        radBtnDolares = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        txtlab1 = new javax.swing.JLabel();
        txtlab2 = new javax.swing.JLabel();
        txtlab3 = new javax.swing.JLabel();
        btnGenerarNota = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel6.setText("Dirección:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel2.setText("Cliente:");

        listaCliente.setNextFocusableComponent(btnBuscar);
        listaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaClienteMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaClienteMouseClicked(evt);
            }
        });
        listaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                listaClienteKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(listaCliente);

        btnBuscar.setText("Aceptar");
        btnBuscar.setNextFocusableComponent(btnGenerarNota);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));

        jLabel3.setText("R.U.C:");

        jLabel4.setText("Dirección:");

        jLabel5.setText("Nombre:");

        jLabel8.setText("E-mail:");

        jLabel7.setText("Teléfono 1:");

        jLabel9.setText("Teléfono 2:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel7)
                            .addGap(21, 21, 21)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95)
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel5)
                            .addGap(24, 24, 24)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar Nota"));

        tblNotaDebito.setModel(tabla);
        tblNotaDebito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNotaDebitoMouseClicked(evt);
            }
        });
        tblNotaDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblNotaDebitoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblNotaDebito);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Sub-Total");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("IGV");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Total");

        jLabel13.setText("Tipo de Moneda: ");

        buttonGroup1.add(radBtnDolares);
        radBtnDolares.setSelected(true);
        radBtnDolares.setText("Dólares");
        radBtnDolares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radBtnDolaresMouseReleased(evt);
            }
        });
        radBtnDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBtnDolaresActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Soles");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseReleased(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        txtlab1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab1.setText("(US$):");

        txtlab2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab2.setText("(US$):");

        txtlab3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtlab3.setText("(US$):");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtlab3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtlab2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtlab1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(radBtnDolares)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(radBtnDolares)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlab1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlab2))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlab3)))
        );

        btnGenerarNota.setText("Generar Nota");
        btnGenerarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarNotaActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMISIÓN DE NOTAS DE DÉBITO ");

        javax.swing.GroupLayout panelEmisionNotasDebitoLayout = new javax.swing.GroupLayout(panelEmisionNotasDebito);
        panelEmisionNotasDebito.setLayout(panelEmisionNotasDebitoLayout);
        panelEmisionNotasDebitoLayout.setHorizontalGroup(
            panelEmisionNotasDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasDebitoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelEmisionNotasDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmisionNotasDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmisionNotasDebitoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEmisionNotasDebitoLayout.setVerticalGroup(
            panelEmisionNotasDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasDebitoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmisionNotasDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEmisionNotasDebito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEmisionNotasDebito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
        //   IU_Emisión_Notas iu = new IU_Emisión_Notas();
        //   iu.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void listaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClienteMouseClicked
    }//GEN-LAST:event_listaClienteMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        txtDireccion.setText(clienteSelecc.getDireccion());
        txtEmail.setText(clienteSelecc.getEmail());
        txtNombre.setText(clienteSelecc.getNombre());
        txtRUC.setText(clienteSelecc.getRuc());
        txtTelefono1.setText(clienteSelecc.getTelefonofijo());
        txtTelefono2.setText(clienteSelecc.getTelefonofijo2());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGenerarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarNotaActionPerformed
        if ( TablaVacia() == true ) {
            JOptionPane.showMessageDialog(null, "La tabla de Nota de Débito esta vacía", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            IU_Generar_Notas iu = null;
            try {
                String fechaEmision = Fecha.getFechaActual();
//                System.out.println("fecha Emision:" + fechaEmision);
                iu = new IU_Generar_Notas(usuario, NOTA_DEBITO, clienteSelecc, null, tabla, null, bandera_moneda, fechaEmision,
                                          "", "", "", ND_MOD_UNICA);
                // Revisar tipo de documento (en Nota de credito al imprimir), nroFactura
            } catch ( ParseException ex ) {
                Logger.getLogger(IU_Emisión_Notas_Debito.class.getName()).log(Level.SEVERE, null, ex);
            }
            iu.setVisible(true);
        }
    }//GEN-LAST:event_btnGenerarNotaActionPerformed

    private void listaClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClienteMouseReleased
        nombreClient = (String) listaCliente.getSelectedValue();
        clienteSelecc = servicioCliente.Obtener_Cliente_Por_Nombre(nombreClient);
    }//GEN-LAST:event_listaClienteMouseReleased

    private void listaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaClienteKeyTyped
        int cod = (int) evt.getKeyChar();
        
        if ( cod == 10 ) {
            nombreClient = (String) listaCliente.getSelectedValue();
            clienteSelecc = servicioCliente.Obtener_Cliente_Por_Nombre(nombreClient);
            txtDireccion.setText(clienteSelecc.getDireccion());
            txtEmail.setText(clienteSelecc.getEmail());
            txtNombre.setText(clienteSelecc.getNombre());
            txtRUC.setText(clienteSelecc.getRuc());
            txtTelefono1.setText(clienteSelecc.getTelefonofijo());
            txtTelefono2.setText(clienteSelecc.getTelefonofijo2());
        }
    }//GEN-LAST:event_listaClienteKeyTyped

    private void tblNotaDebitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNotaDebitoKeyTyped
        int cod = (int) evt.getKeyChar();
//        System.out.println("keychar: " + cod);
        //Para ENTER
    /*    if (cod == 10) {
         //  System.out.println("cantidddd " + tblNotaCredito.getValueAt(tblNotaCredito.getSelectedRow() - 1, 1));
         String cantid = (String) tblNotaDebito.getValueAt(tblNotaDebito.getSelectedRow() - 1, 1);
         //  System.out.println("cantid" + cantid);
         Double nuevaCantidad = Double.parseDouble(cantid);

         totalNota = totalNota + nuevaCantidad;
         txtSubTotal.setText("" + totalNota);
         }*/
        if ( cod >= 'a' && cod <= 'z' ) {
            evt.setKeyChar((char) (cod - 32));
        }
    }//GEN-LAST:event_tblNotaDebitoKeyTyped

    private void tblNotaDebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNotaDebitoMouseClicked

    }//GEN-LAST:event_tblNotaDebitoMouseClicked

    private void radBtnDolaresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radBtnDolaresMouseReleased
        if ( tabla != null ) {
            tabla.setT(tipoCambio);
            tabla.cambiarDolares();
            total = tabla.TotalBruto();
            igvv = tabla.Redondear(total * igv / (100 + igv));
            subTotal = total - igvv;
            
            String importSubTot = "" + new Util().Redondear2Decimales(subTotal);
            txtSubTotal.setText(importSubTot);
            
            txtIGV.setText("" + igvv);
            txtTotal.setText("" + total);
            
        } else {
//            System.out.println("entraaaaaaaaaa");
//            tabla4.setT(tipoCambio);
//            tabla4.cambiarDolares();
//            total = tabla4.TotalBruto();
//            igvv = tabla4.Redondear(total * igv / (100 + igv));
//            subTotal = total - igvv;
//            txtSubTotal1.setText("" + subTotal);
//            txtIGV1.setText("" + igvv);
//            txtTotal1.setText("" + total);
        }
    }//GEN-LAST:event_radBtnDolaresMouseReleased

    private void jRadioButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseReleased
        if ( tabla != null ) {
            tabla.setT(tipoCambio);
            tabla.cambiarSoles();
            total = tabla.TotalBruto();
            igvv = tabla.Redondear(total * igv / (100 + igv));
            subTotal = total - igvv;
            subTotal = tabla.Redondear(subTotal);
            txtSubTotal.setText("" + subTotal);
            txtIGV.setText("" + igvv);
            txtTotal.setText("" + total);
            
        } else {
//            System.out.println("entraaaaaaaaaa");
//            tabla4.setT(tipoCambio);
//            tabla4.cambiarSoles();
//            total = tabla4.TotalBruto();
//            igvv = tabla4.Redondear(total * igv / (100 + igv));
//            subTotal = total - igvv;
//            subTotal = tabla4.Redondear(subTotal);
//            txtSubTotal1.setText("" + subTotal);
//            txtIGV1.setText("" + igvv);
//            txtTotal1.setText("" + total);
        }
    }//GEN-LAST:event_jRadioButton2MouseReleased

    private void radBtnDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBtnDolaresActionPerformed
        cambiar();
    }//GEN-LAST:event_radBtnDolaresActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        cambiar();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarNota;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JList listaCliente;
    public javax.swing.JPanel panelEmisionNotasDebito;
    private javax.swing.JRadioButton radBtnDolares;
    private javax.swing.JTable tblNotaDebito;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRUC;
    public javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    public javax.swing.JTextField txtTotal;
    private javax.swing.JLabel txtlab1;
    private javax.swing.JLabel txtlab2;
    private javax.swing.JLabel txtlab3;
    // End of variables declaration//GEN-END:variables
}