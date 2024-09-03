package Presentacion.PlanillaLetras;

import Entidades.Cabeces;
import Entidades.Clientes;
import Entidades.Control;
import static Entidades.Otros.Constante.COLUMNA_CABECES;
import static Entidades.Otros.Constante.COLUMNA_MONEDA;
import static Entidades.Otros.Constante.COLUMNA_MONTO_TOTAL;
import static Entidades.Otros.Constante.COLUMNA_RUC;
import static Entidades.Otros.Constante.COLUMNA_VENDEDOR;
/*import static Entidades.Otros.Constante.COL_CTA_CTE;
import static Entidades.Otros.Constante.COL_EN_PLANILLA;
import static Entidades.Otros.Constante.COL_FECHA_PLANILLA;
import static Entidades.Otros.Constante.COL_PLAZA;
import static Entidades.Otros.Constante.COL_RUC_CLIENTE;
import static Entidades.Otros.Constante.COL_TELEFONO;*/
import static Entidades.Otros.Constante.*;
import static Entidades.Otros.Constante.TODOS;
import static Entidades.Otros.Constante.VALIDO;
import Entidades.Usuarios;
import Mantenimiento.CabecesDAO;
import Mantenimiento.ControlDAO;
import Presentacion.MENU001;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;
import Servicios.EmisionPlanillas.Servicio_PlanillasLetras;
import Servicios.Servicio_Cabeces;
import Servicios.TipoMensaje;
import Servicios.Util;
import Servicios.facturacion.Servicio_Impresion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;
import Servicios.facturacion.Servicio_Documentos;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ledis Rivera Changra
 */
public class IU_PlanillaLetras extends javax.swing.JFrame {

//    private Servicio_GestionarPlanillas servicioGP;
    private Servicio_PlanillasLetras spl;
    private List<Cabeces> lstLetras;
    Date inicio;
    Date fin;
    CabecesDAO cabdao;
    
    IU_FiltrarPlanillaLetras filt;
    MENU001 menu;
    Usuarios usuario;
    int filaseleccionada;
    Util util;
    boolean seleccion = false;
    
    BigDecimal cont_Let_D;
    BigDecimal cont_Let_S;
    Servicio_Documentos sf;
    ControlDAO controlDao;
    Control control;
    
//    List<Cabeces> letras;

    public IU_PlanillaLetras(Usuarios usuario, String rol, Date desde, Date hasta) {
        this();
        cabdao = new CabecesDAO();
        inicio = desde;
        fin = hasta;
        spl = new Servicio_PlanillasLetras(this, inicio, fin);
        
//        letras = new ArrayList<Cabeces>();
//        letras = spl.getLstLetras();
        
        setLocationRelativeTo(null);
        setVisible(true);
        SetFechas();
        sf = new Servicio_Documentos();
        util = new Util();
        
        ListarSoloClientes();
        tx_nroDoc.setDocument(new Validar_Mayusculas(tx_nroDoc, 20));
        setAnchoColumnas();
        alinearColumnaDerecha();
        lb_titulo.setText("Planilla de Letras");
//        ocultarColumnas();
//        iniciarTabla();
        this.usuario = usuario;
        menu = new MENU001(usuario.getNombre(), rol, 0, 0);
        control = new Control();
        controlDao = new ControlDAO();
        control = controlDao.obtener_objeto();
        
        ocultarColumnas();
    }
    
    private void ocultarColumnas() {
        //ocultarColumna(COL_EN_PLANILLA);
        //ocultarColumna(COL_FECHA_PLANILLA);
        //ocultarColumna(COL_RUC_CLIENTE);
        //ocultarColumna(COL_TELEFONO);
        //ocultarColumna(COL_CTA_CTE);
        //ocultarColumna(COL_PLAZA);
    }
    
    private void ocultarColumna(int columna) {
        tab_Planillas.getColumnModel().getColumn(columna).setMaxWidth(0);
        tab_Planillas.getColumnModel().getColumn(columna).setMinWidth(0);
        tab_Planillas.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tab_Planillas.getColumnModel().getColumn(7).setCellRenderer(tcr); // Importe
    }
    
    private void ListarSoloClientes() {
        Iterator<Clientes> it = sf.getSoloClientes().iterator();
        cbClientes.addItem(TODOS);
        
        while ( it.hasNext() ) {
            Clientes c = it.next();
            String clientePlaza = c.getNombre();
//            System.out.println("cliente plaza:" + clientePlaza);
            cbClientes.addItem(clientePlaza);
        }
    }
    
//    private void iniciarTabla() {
//        DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();
//        Servicio_Cabeces sc = new Servicio_Cabeces();
//        List<Cabeces> lst = sc.obtenerCabLetrasPlanilla();
//        int tam = lst.size();
//        
//        if ( tam != 0 ) {
//            for ( int i = 0; i < tam; i++ ) {
//                Cabeces cab = (Cabeces) lst.get(i);
//                String tipoDoc = cab.getId().getTipodoc();
//                String nroSerie = cab.getId().getNrorserie();
//                String nroDoc = cab.getId().getNrodocumento();
//                String cliente = cab.getClientes().getNombre();
//                
//                Date fechaEmision = cab.getTipocambio().getFecha();
//                
//                String moneda = cab.getMoneda();
//                double importe =cab.getTotal();
//                Date vence = cab.getFechavencimiento();
//                String enPlanilla = cab.getLetraPlanilla();
//                Date fechaPlanilla = cab.getFechaLetPla();
////                if ( a[10].equals("US$") ) {
//                if ( "US$".equals(moneda) ) {
//                    cont_Let_D = cont_Let_D.add(BigDecimal.valueOf(importe));
//                    
//                } else {
////                    cont_Let_S = cont_Let_S.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
//                }
////                String facturado = formatearMonetario(String.valueOf(a[5]), 2);
////                String pagado = formatearMonetario(String.valueOf(a[6]), 2);
////                String saldo = formatearMonetario(String.valueOf(a[7]), 2);
//                Object[] fila = {(i + 1), tipoDoc, nroSerie, nroDoc, cliente, 
//                                 fechaEmision, moneda, importe, vence, false, 
//                                 enPlanilla, fechaPlanilla};
//                modelo.addRow(fila);
//            }
//            String valorInicial = "0.00";
//            txt_S_Total.setText(valorInicial);
//            txt_D_Total.setText(valorInicial);
//        } else {
//            JOptionPane.showMessageDialog(null, "No existe registros");
//        }
//    }
    
//    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
//        int indicePunto = valorMonetario.indexOf(".") + 1;
//        
//        if ( indicePunto == 0 ) {
//            valorMonetario = valorMonetario + ".00";
//            
//        } else {
//            int longitud = valorMonetario.length();
//            int decimales = longitud - indicePunto;
//            int diferenciaCeros = numeroDecimales - decimales;
//            for ( int i = 0; i < diferenciaCeros; i++ ) {
//                valorMonetario = valorMonetario + "0";
//            }
//        }
//        return valorMonetario;
//    }
    
    public IU_PlanillaLetras() {
        initComponents();
    }
    
//    private void ocultarColumnas() {
//        ocultarColumna(COLUMNA_CABECES);
//        ocultarColumna(COLUMNA_RUC);
//        ocultarColumna(COLUMNA_MONEDA);
//        ocultarColumna(COLUMNA_MONTO_TOTAL);
//        ocultarColumna(COLUMNA_VENDEDOR);
//    }

    private void SetFechas() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        lb_inicio.setText(sd.format(inicio));
        lb_fin.setText(sd.format(fin));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_Planillas = new javax.swing.JTable();
        pnl_soles = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_S_Total = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_Salir = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        btnSeleccionarTodo = new javax.swing.JButton();
        btnRestaurarTodo = new javax.swing.JButton();
        lb_titulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tx_nroDoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        bt_buscar = new javax.swing.JButton();
        cbClientes = new javax.swing.JComboBox();
        pnl_dolares = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_D_Total = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lb_inicio = new javax.swing.JLabel();
        lb_fin = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

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

        tab_Planillas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "TIPO DOC.", "N° SERIE", "N° DOC.", "CLIENTE", "EMISIÓN", "MONEDA", "IMPORTE", "VENCE", "IMPRIMIR", "EN PLANILLA", "FECHA PLANILLA", "RUCCLIENTE", "TELEFONO", "CTACTE", "PLAZA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_Planillas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tab_Planillas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab_PlanillasMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tab_Planillas);

        pnl_soles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL SOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jLabel2.setText("Importe Total S/.");

        txt_S_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_Total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_Total.setEnabled(false);

        javax.swing.GroupLayout pnl_solesLayout = new javax.swing.GroupLayout(pnl_soles);
        pnl_soles.setLayout(pnl_solesLayout);
        pnl_solesLayout.setHorizontalGroup(
            pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_solesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_S_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        pnl_solesLayout.setVerticalGroup(
            pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_solesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_S_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        btn_Imprimir.setText("Imprimir");
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });

        btnSeleccionarTodo.setText("Seleccionar Todo");
        btnSeleccionarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarTodoActionPerformed(evt);
            }
        });

        btnRestaurarTodo.setText("Restaurar Todo");
        btnRestaurarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSeleccionarTodo)
                .addGap(31, 31, 31)
                .addComponent(btnRestaurarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestaurarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSeleccionarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        lb_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("BUSQUEDA"));

        jLabel1.setText("Nro Documento");

        tx_nroDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_nroDocKeyTyped(evt);
            }
        });

        jLabel8.setText("Cliente");

        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 419, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(261, 261, 261)))
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pnl_dolares.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL DOLARES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jLabel9.setText("Importe Total US$");

        txt_D_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_Total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_Total.setEnabled(false);

        javax.swing.GroupLayout pnl_dolaresLayout = new javax.swing.GroupLayout(pnl_dolares);
        pnl_dolares.setLayout(pnl_dolaresLayout);
        pnl_dolaresLayout.setHorizontalGroup(
            pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_D_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pnl_dolaresLayout.setVerticalGroup(
            pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_D_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHAS"));

        lb_inicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lb_fin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lb_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(lb_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnl_soles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnl_dolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(lb_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lb_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl_soles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_dolares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private String validarLetrasSeleccionadas() {
        // Letras seleccionadas deben tener el mismo cliente y el mismo tipo de moneda
        String  mensaje = VALIDO;
        DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();
        
        String moneda = String.valueOf(tab_Planillas.getValueAt(0, 6));
        String idCliente = String.valueOf(tab_Planillas.getValueAt(0, 12));
        
        for ( int i = 1; i < modelo.getRowCount(); i++ ) {
            
            boolean seleccionado = (boolean) modelo.getValueAt(i, 9);
//            System.out.println("seleccionado : " + seleccionado);
            
            String monedaSig = String.valueOf(tab_Planillas.getValueAt(i, 6));
            String idClienteSig = String.valueOf(tab_Planillas.getValueAt(i, 12));

            if ( !moneda.equals(monedaSig) ) {
                mensaje = "Seleccione letras del mismo tipo de moneda";
                break;
            }
            if ( !idCliente.equals(idClienteSig) ) {
                mensaje = "Seleccione letras del mismo cliente";
                break;
            }
        }
        return mensaje;
    }
    
    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed
        int filaSelect = tab_Planillas.getSelectedRow();
        
        if ( filaSelect == -1 ) {
            JOptionPane.showMessageDialog(null, "Seleccione letra(s)", "Validación de selección de letras", JOptionPane.WARNING_MESSAGE);
            
        } else if ( tab_Planillas.getSelectedRowCount() > 15 ) {
            JOptionPane.showMessageDialog(null, "Seleccione máximo " + MAX_ITEM_PLAN_LET + " letra(s) para imprimir", 
                                          "Validación de selección de letras", JOptionPane.WARNING_MESSAGE);
            
        } else {
            String mensaje = validarLetrasSeleccionadas();
            
            if ( VALIDO.equals(mensaje) ) {
                String rsEmpresa = control.getNombrempresa();
                String telEmpresa = control.getTelefonos();
                String ctaCte = ""; // control.getCtaCte();
                
                IU_FiltrarPlanillaLetras iuFPL = new IU_FiltrarPlanillaLetras(this, menu, inicio, fin, 
                                                                              rsEmpresa, telEmpresa, ctaCte, 
                                                                              txt_S_Total.getText(), 
                                                                              txt_D_Total.getText());
                iuFPL.setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, mensaje, "Validación de selección de letras", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ImprimirActionPerformed
    
    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        spl.buscarPor();
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void tx_nroDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_nroDocKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_nroDocKeyTyped

    private void btnSeleccionarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarTodoActionPerformed
        boolean seleccionado = (boolean) tab_Planillas.getValueAt(0, 9);
        
        if ( seleccionado == false ) {// ¿1ra fila no esta selecionada?
            DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();

            double totalFacturadoDolares = Double.parseDouble(txt_D_Total.getText());
            double totalFacturadoSoles = Double.parseDouble(txt_S_Total.getText());

            for ( int i = 0; i < modelo.getRowCount(); i++ ) {
                modelo.setValueAt(true, i, 9);
                String moneda = String.valueOf(tab_Planillas.getValueAt(filaseleccionada, 6));
                
                if ( "US$".equals(moneda) ) { // En dolares
                    double facturadoDolar = Double.parseDouble(String.valueOf(tab_Planillas.getValueAt(i, 7)));
                    totalFacturadoDolares += facturadoDolar;

                } else {
                    double facturadoSol = Double.parseDouble(String.valueOf(tab_Planillas.getValueAt(i, 7)));
                    totalFacturadoSoles += facturadoSol;
                }
            }
            txt_D_Total.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoDolares)), 2));
            txt_S_Total.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoSoles)), 2));
        }
    }//GEN-LAST:event_btnSeleccionarTodoActionPerformed

    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    private void btnRestaurarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarTodoActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();
        
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
            modelo.setValueAt(false, i, 9);
        }
        String valorInicial = "0.00";
        txt_D_Total.setText(valorInicial);
        txt_S_Total.setText(valorInicial);
    }//GEN-LAST:event_btnRestaurarTodoActionPerformed

    private void tab_PlanillasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_PlanillasMouseReleased
        int columnaSeleccionada = tab_Planillas.getSelectedColumn();
        
        if ( columnaSeleccionada == 9 ) {
            filaseleccionada = tab_Planillas.getSelectedRow();
            seleccion = true;
            String moneda = String.valueOf(tab_Planillas.getValueAt(filaseleccionada, 6));
            
            if ( "US$".equals(moneda) ) { // En dolares
                double totalLetrasDolares = Double.parseDouble(txt_D_Total.getText());
                double letrasDolar = Double.parseDouble(String.valueOf(tab_Planillas.getValueAt(filaseleccionada, 7)));
                boolean seleccionado = (boolean) tab_Planillas.getValueAt(filaseleccionada, 9);

                if ( seleccionado ) {
                    totalLetrasDolares += letrasDolar;

                } else {
                    totalLetrasDolares -= letrasDolar;
                }
                txt_D_Total.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalLetrasDolares)), 2));

            } else { // En soles
                double totalLetrasSoles = Double.parseDouble(txt_S_Total.getText());
                double letrasSol = Double.parseDouble(String.valueOf(tab_Planillas.getValueAt(filaseleccionada, 7)));

                boolean seleccionado = (boolean) tab_Planillas.getValueAt(filaseleccionada, 9);

                if ( seleccionado ) {
                    totalLetrasSoles += letrasSol;

                } else {
                    totalLetrasSoles -= letrasSol;
                }
                txt_S_Total.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalLetrasSoles)), 2));
            }
        }
    }//GEN-LAST:event_tab_PlanillasMouseReleased

//    private ArrayList SetLista() {
//        DefaultTableModel dtm = (DefaultTableModel) tab_Planillas.getModel();
//        ArrayList<FilaPlanilla> lista = new ArrayList<>();
//        for ( int i = 0; i < tab_Planillas.getRowCount(); i++ ) {
//            String tip = String.valueOf(dtm.getValueAt(i, 1));
//            String numDoc = String.valueOf(dtm.getValueAt(i, 3));
//            String fecha = String.valueOf(dtm.getValueAt(i, 4));
//            
//            Cabeces c =  ((Cabeces) (dtm.getValueAt(i, 8)));
//            String ruc = "";
//            if ( c != null && c.getClientes()!= null ) {
//                System.out.println("d"+i);
//                 ruc = ((Cabeces) (dtm.getValueAt(i, 8))).getClientes().getRuc();
//            }
//            String cliente = String.valueOf(dtm.getValueAt(i, 5));
//            String total = String.valueOf(dtm.getValueAt(i, 6));
//            System.out.println("total:" + total);
//            lista.add(new FilaPlanilla(tip, numDoc, fecha, ruc, cliente, total));
//        }
//        return lista;
//    }

    private Map SetParametros() {
        Map<String, Object> mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("titulo", lb_titulo.getText());
        mapa.put("empresa", empresa);
        mapa.put("desde", lb_inicio.getText());
        mapa.put("hasta", lb_fin.getText());
        mapa.put("tsoles", txt_S_Total.getText());
        mapa.put("tdolares", txt_D_Total.getText());
        return mapa;
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tab_Planillas.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tab_Planillas.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tab_Planillas.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (6 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (25 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 8:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 9:
                    anchoColumna = (5 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton btnRestaurarTodo;
    private javax.swing.JButton btnSeleccionarTodo;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.JButton btn_Salir;
    public javax.swing.JComboBox cbClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_fin;
    private javax.swing.JLabel lb_inicio;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JPanel pnl_dolares;
    private javax.swing.JPanel pnl_soles;
    public javax.swing.JTable tab_Planillas;
    public javax.swing.JTextField tx_nroDoc;
    public javax.swing.JTextField txt_D_Total;
    public javax.swing.JTextField txt_S_Total;
    // End of variables declaration//GEN-END:variables
}