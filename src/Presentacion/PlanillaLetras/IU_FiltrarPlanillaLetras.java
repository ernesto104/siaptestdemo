package Presentacion.PlanillaLetras;

import Entidades.Bancos;
import Entidades.Cabeces;
import Entidades.Control;
import static Entidades.Otros.Constante.CERO;
import static Entidades.Otros.Constante.DOLAR_US;
import static Entidades.Otros.Constante.SELECCIONAR;
import static Entidades.Otros.Constante.SOL;
import Entidades.Otros.DetPlanillaLetra;
import Entidades.Otros.Fecha;
import Entidades.Otros.ImpresionExcel;
import Entidades.Otros.Monetario;
import Mantenimiento.BancoDAO;
import Mantenimiento.CabecesDAO;
import Mantenimiento.ControlDAO;
import Presentacion.MENU001;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Servicios.Servicio_Documentos;
import Servicios.Servicio_Excel;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Ledis Rivera Ch.
 */
public class IU_FiltrarPlanillaLetras extends javax.swing.JFrame {

    private Date desde;
    private Date hasta;
    private JTable tablaLetrasSelect;
    private String rsEmpresa;
    private String telEmpresa;
    private String ctaCte;
    
    private ControlDAO controlDao;
    private Control control;
    private BancoDAO bancoDao;
    private List<Bancos> lstBancos;
    
    private double total;
    private String moneda;
    private String totalS;
    private String totalD;

    private List lstNroDocLetras;
    private List<Cabeces> lstPlanillaLetras;
    
    public IU_FiltrarPlanillaLetras(IU_PlanillaLetras uiPL, MENU001 me, Date desde, Date hasta,
                                    String rsEmpresa, String telEmpresa, String ctaCte,
                                    String totalS, String totalD) {
        initComponents();
        this.desde = desde;
        this.hasta = hasta;
        this.rsEmpresa = rsEmpresa;
        this.telEmpresa = telEmpresa;
        this.ctaCte = ctaCte;
        this.totalS = totalS;
        this.totalD = totalD;
        
//        System.out.println("totalS:" + totalS);
//        System.out.println("totalD:" + totalD);
        
        asignarTitulo();
        lstPlanillaLetras = new ArrayList<Cabeces>();
        tablaLetrasSelect = uiPL.tab_Planillas;
        
        controlDao = new ControlDAO();
        control = controlDao.obtener_objeto();
        
        setFechaActual();
        setInformacionCliente();
        setMoneda();
        setMonedaPorDefecto();
        setTablaPlanilla();
        
        bancoDao = new BancoDAO();
        lstBancos = new ArrayList<Bancos>();
        listarBancos();
        alinearColumnaDerecha();
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblImpresion.getColumnModel().getColumn(7).setCellRenderer(tcr); // Importe
    }
    
    private void listarBancos() {
        lstBancos = bancoDao.listar_Bancos();
        cbBanco.addItem(SELECCIONAR);
        
        for ( Bancos b : lstBancos ) {
            cbBanco.addItem(b.getNombre());
        }
    }
    
    private void setMonedaPorDefecto() {
        if ( SOL.equals(moneda) ) {
            rbSoles.setSelected(true);
            
        } else if ( DOLAR_US.equals(moneda) ) {
            rbDolares.setSelected(true);
        }
    }
    
    private void setFechaActual() {
//        Date fechaHoy = Fecha.getDateActual();
//        dcFechaImpresion.setDate(fechaHoy);
        dcFechaImpresion.setDate(new GregorianCalendar().getTime());
    }
    
    private void setMoneda() {
        moneda = SOL;
//        System.out.println("¿CERO.equals(totalS)? " + CERO.equals(totalS));
        if ( CERO.equals(totalS) ) {
            moneda = DOLAR_US;
        }
//        System.out.println("moneda:" + moneda);
        lblMonedaTotal.setText(moneda);
    }
    
    private void setInformacionCliente() {
        txtrsEmpresa.setText(control.getNombrempresa());
        txtTelEmpresa.setText(control.getTelefonos());
    }
    
    private void setTablaPlanilla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaLetrasSelect.getModel();
        int fila = 0;
        
        lstNroDocLetras = new ArrayList();
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
            boolean seleccionado = (boolean) modelo.getValueAt(i, 9);
            
            if ( seleccionado ) {
                // Item
                tblImpresion.setValueAt((fila + 1), fila, 0); 
                
                // N° Letra
                String nroLetra = String.valueOf(tablaLetrasSelect.getValueAt(i, 3));
                tblImpresion.setValueAt(nroLetra, fila, 1);
                
                // Nombre o Razón Social
                String cliente = String.valueOf(tablaLetrasSelect.getValueAt(i, 4));
                tblImpresion.setValueAt(cliente, fila, 2);
                
                // RUC o DNI
                String ruc = String.valueOf(tablaLetrasSelect.getValueAt(i, 12));
                tblImpresion.setValueAt(ruc, fila, 3);
                
                // Aval
//                tblImpresion.setValueAt(aval, fila, 4);
                
                // Lugar de Pago
                String plaza = String.valueOf(tablaLetrasSelect.getValueAt(i, 15));
                tblImpresion.setValueAt(plaza, fila, 5);
                
                // Vencimiento
                String fechaVence = String.valueOf(tablaLetrasSelect.getValueAt(i, 8));
                tblImpresion.setValueAt(fechaVence, fila, 6);
                
                // Importe
                double importe = Double.parseDouble(String.valueOf(tablaLetrasSelect.getValueAt(i, 7)));
                tblImpresion.setValueAt(importe, fila, 7);
                
                total += importe;
                fila++;
                
                lstNroDocLetras.add(nroLetra);
            }
        }
//        mostrarLstNroDocLetras();
        txtTotal.setText(moneda + " " + Monetario.formatearTotal(total));
        txtCantDocReci.setText(String.valueOf(fila));
    }
    
//    private void mostrarLstNroDocLetras() {
//        for ( Object obj : lstNroDocLetras ) {
//            String nrodoc = String.valueOf(obj);
//            System.out.println("nrodoc:" + nrodoc);
//        }
//    }
    
    private void asignarTitulo() {
        String sDesde = Fecha.dateToString(desde);
        String sHasta = Fecha.dateToString(hasta);
        lblTitulo.setText("PLANILLA LETRAS PARA BANCOS DEL " + sDesde + " AL " + sHasta);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMoneda = new javax.swing.ButtonGroup();
        bgProtestar = new javax.swing.ButtonGroup();
        bgCobrarInteres = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtrsEmpresa = new javax.swing.JTextField();
        txtTelEmpresa = new javax.swing.JTextField();
        txtCtaCte = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbDolares = new javax.swing.JRadioButton();
        rbSoles = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        chbCoGarantia = new javax.swing.JCheckBox();
        chbFactoring = new javax.swing.JCheckBox();
        chbAcreencias = new javax.swing.JCheckBox();
        chbDescuento = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        chbCoLibre = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rbSiProtestar = new javax.swing.JRadioButton();
        rbNoProtestar = new javax.swing.JRadioButton();
        rbSiCobrarInt = new javax.swing.JRadioButton();
        rbNoCobrarInt = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblImpresion = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        cbBanco = new javax.swing.JComboBox();
        dcFechaImpresion = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCantDocReci = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblMonedaTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lblTitulo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fecha de Impresión:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "INFORMACIÓN DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel14.setText("Nombre o Razón Social:");

        jLabel15.setText("Teléfono:");

        jLabel16.setText("N° Cuenta Corriente (a):");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Tipo de Moneda:");

        bgMoneda.add(rbDolares);
        rbDolares.setText("Dólares");
        rbDolares.setEnabled(false);

        bgMoneda.add(rbSoles);
        rbSoles.setText("Soles");
        rbSoles.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrsEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel16))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCtaCte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(rbDolares)
                        .addGap(33, 33, 33)
                        .addComponent(rbSoles)
                        .addGap(16, 16, 16)))
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtrsEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(rbDolares)
                        .addComponent(rbSoles))
                    .addComponent(txtTelEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)), "PRODUCTO / MODALIDAD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        chbCoGarantia.setText("Cobranza Garantía (b)");

        chbFactoring.setText("Factoring (b)");

        chbAcreencias.setText("Acreencias en Cesión (Facturas)");

        chbDescuento.setText("Descuento (b) (d)");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)), "(c)Solo aplica para Cobranza Libre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        chbCoLibre.setText("Cobranza Libre (c)");

        jLabel8.setText("Tasa (C)");

        jLabel9.setText("Protestar (c)");

        jLabel10.setText("Cobrar Intereses (c)");

        bgProtestar.add(rbSiProtestar);
        rbSiProtestar.setText("Sí");

        bgProtestar.add(rbNoProtestar);
        rbNoProtestar.setText("No");

        bgCobrarInteres.add(rbSiCobrarInt);
        rbSiCobrarInt.setText("Sí");

        bgCobrarInteres.add(rbNoCobrarInt);
        rbNoCobrarInt.setText("No");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chbCoLibre)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(rbSiCobrarInt))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbSiProtestar)))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbNoProtestar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbNoCobrarInt, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(36, 36, 36))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbCoLibre)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rbSiProtestar)
                    .addComponent(rbNoProtestar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbNoCobrarInt)
                        .addComponent(rbSiCobrarInt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbCoGarantia)
                    .addComponent(chbDescuento))
                .addGap(68, 68, 68)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbAcreencias)
                    .addComponent(chbFactoring))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbDescuento)
                            .addComponent(chbFactoring))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbCoGarantia)
                            .addComponent(chbAcreencias)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("<html>(a)Colocar código de Cta. Cte. en mismo tipo de moneda que la Planilla de Facturas o Letras.<br/> (b) Instrucciones de Cobro de Intereses y Protestos Obligatorios de valorados.<br/>(c) Sólo aplica para Cobranza Libre.<br />(d) En caso de facturas vencidas, el cobro de principal, intereses y comisiones, se cargará automáticamente al Cedente.<br />(f) Marcar con X en el caso de tener aval.</html>");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("   DATOS DEL ACEPTANTE");

        tblImpresion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "N° Letra o Factura", "Nombre o Razón Social", "RUC o DNI", "Aval(f)", "Lugar de Pago", "Vencimiento", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblImpresion);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Banco:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Sectorista/Fun.Neg:");

        cbBanco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBancoItemStateChanged(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cantidad de doc. recibidos");

        txtCantDocReci.setEditable(false);
        txtCantDocReci.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblMonedaTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMonedaTotal.setText("S/.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(dcFechaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel5))
                                            .addGap(31, 31, 31)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(cbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(120, 120, 120)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtCantDocReci, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(37, 37, 37)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lblMonedaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16))))))
                .addGap(0, 498, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(dcFechaImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantDocReci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonedaTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if ( opcion == JOptionPane.YES_OPTION ) {
            try {
                Map<String, Object> parametros = SetParametros();
                ArrayList lista = SetLista();
                System.out.println("Imprimir...");

                Servicio_Documentos sd = new Servicio_Documentos();
//                lstPlanillaLetras = new CabecesDAO().obtenerPlanillaLetras(lstNroDocLetras);
                //lstPlanillaLetras = sd.obtenerPlanillaLetras(lstNroDocLetras);
                //sd.actualizarLetras(lstPlanillaLetras);
                
                imprimirConExcel(parametros, lista);
                
            } catch ( Exception ex ) {
                Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void imprimirConExcel(Map<String, Object> parametros, ArrayList lista) 
                                  throws BiffException, WriteException, IOException {
        
        //String nombreExcelPlanLetras = generarExcelPlanillaLetras(lista, parametros, control);
//        Control control = new ControlDAO().Obtener_Objeto(1);
        try {
            String rutaBDPlanLetra = control.getRutaprogramas();
            String rutaExcel = rutaBDPlanLetra.replace("/", "\\");
            //nombreExcelPlanLetras = rutaExcel + "\\" + nombreExcelPlanLetras;
            //new ImpresionExcel().imprimirEliminarExcel(nombreExcelPlanLetras);
            
        } catch ( Exception ex ) {
            Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Genera excel para imprimir REPORTE DE LETRAS
    /*private String generarExcelPlanillaLetras(ArrayList lista, Map<String, Object> parametros, Control control)
                                              throws FileNotFoundException, BiffException, IOException, WriteException {
        
        String nombreExcelLetras = new Servicio_Excel(null, null).Exportar_Excel_Planilla_Letras(lista, parametros, control);
        return nombreExcelLetras;
    }*/
    
    private void cbBancoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBancoItemStateChanged
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            String bancoSelect = String.valueOf(cbBanco.getSelectedItem());
            txtFuncionario.setText("");

            if ( !SELECCIONAR.equals(bancoSelect) ) {
                for ( Bancos b : lstBancos ) {
                    if ( bancoSelect.equals(b.getNombre()) ) {
                        txtFuncionario.setText(b.getFuncionario());
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_cbBancoItemStateChanged

    private ArrayList SetLista() {
        ArrayList<DetPlanillaLetra> lista = new ArrayList<>();
        
        for ( int i = 0; i < tblImpresion.getRowCount(); i++ ) {
            String item = String.valueOf(i + 1);
            String numLetOFact = String.valueOf(tblImpresion.getValueAt(i, 1));
            String cliente = String.valueOf(tblImpresion.getValueAt(i, 2));
            String ruc = String.valueOf(tblImpresion.getValueAt(i, 3));
            String aval = null;
            String lugarPago = String.valueOf(tblImpresion.getValueAt(i, 5)); // PLAZA???
            String vencimiento = String.valueOf(tblImpresion.getValueAt(i, 6));
            String importe = String.valueOf(tblImpresion.getValueAt(i, 7));
            
            lista.add(new DetPlanillaLetra(item, numLetOFact, cliente, ruc,
                                           aval,
                                           lugarPago, vencimiento, importe));
        }
        return lista;
    }

    private Map SetParametros() {
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("fecha", Fecha.getFechaActual());
        
        String funcionario = txtFuncionario.getText();
        mapa.put("funcionario", funcionario);
        
        mapa.put("empresa", rsEmpresa);
        mapa.put("telefono", telEmpresa);
        mapa.put("ctaCte", ctaCte);
        
        // Selección de Moneda (1° Línea - Información del Cliente):
        String monedaSoles = rbSoles.isSelected() ? "Si" : "No";
        String monedaDolares = rbDolares.isSelected() ? "Si" : "No";
        mapa.put("monedaSoles", monedaSoles);
        mapa.put("monedaDolares", monedaDolares);
        
        // Selección de descuento (1° Línea - Producto/Modalidad):
        String descuento = chbDescuento.isSelected() ? "Si" : "No";
        mapa.put("descuento", descuento);
        
        // Selección de factoring (1° Línea - Producto/Modalidad)
        String factoring = chbFactoring.isSelected() ? "Si" : "No";
        mapa.put("factoring", factoring);
        
        // Selecciónn de Cobranza Libre (1° Línea - Producto/Modalidad)
        String cobranzaLibre = chbCoLibre.isSelected() ? "Si" : "No";
        mapa.put("cobranzaLibre", cobranzaLibre);
        
        
        // Selección de Cobranza Garantía (2° Línea - Producto/Modalidad)
        String cobranzaGarantia = chbCoGarantia.isSelected() ? "Si" : "No";
        mapa.put("cobranzaGarantia", cobranzaGarantia);
        
        // Selección de Acreencias en Cesión (Facturas) (2° Línea - Producto/Modalidad)
        String acreenciasCesion = chbAcreencias.isSelected() ? "Si" : "No";
        mapa.put("acreenciasCesion", acreenciasCesion);
        
        // Selección de Protestar(c) Si/No (2° Línea - Producto/Modalidad)
//        String protestarSi = rbSiProtestar.isSelected() ? "Si" : "No";
//        String protestarNo = rbNoProtestar.isSelected() ? "Si" : "No";
//        mapa.put("protestarSi", protestarSi);
//        mapa.put("protestarNo", protestarNo);
        
        String protestarSiNo = rbSiProtestar.isSelected() ? "Si" : (rbNoProtestar.isSelected() ? "No" : "");
        mapa.put("protestar", protestarSiNo);
        
        // Selección de Cobrar Intereses(c) Si/No (3° Línea - Producto/Modalidad)
//        String cobrarInteresesSi = rbSiCobrarInt.isSelected() ? "Si" : "No";
//        String cobrarInteresesNo = rbNoCobrarInt.isSelected() ? "Si" : "No";
//        mapa.put("cobrarInteresesSi", cobrarInteresesSi);
//        mapa.put("cobrarInteresesNo", cobrarInteresesNo);
        String cobrarIntereses = rbSiCobrarInt.isSelected() ? "Si" : (rbNoCobrarInt.isSelected() ? "No" : "");
        mapa.put("cobrarIntereses", cobrarIntereses);
        
        String banco = String.valueOf(cbBanco.getSelectedItem());
        mapa.put("banco", banco);
        
        String cantidad = txtCantDocReci.getText();
        mapa.put("cantidadDocRec", cantidad);
        
        String moneda = lblMonedaTotal.getText();
        mapa.put("moneda", moneda);
        
        String total = txtTotal.getText();
        mapa.put("total", total);
        
        return mapa;
    }
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarPlanillaLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new IU_FiltrarPlanillaLetras().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCobrarInteres;
    private javax.swing.ButtonGroup bgMoneda;
    private javax.swing.ButtonGroup bgProtestar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbBanco;
    private javax.swing.JCheckBox chbAcreencias;
    private javax.swing.JCheckBox chbCoGarantia;
    private javax.swing.JCheckBox chbCoLibre;
    private javax.swing.JCheckBox chbDescuento;
    private javax.swing.JCheckBox chbFactoring;
    private com.toedter.calendar.JDateChooser dcFechaImpresion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMonedaTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rbDolares;
    private javax.swing.JRadioButton rbNoCobrarInt;
    private javax.swing.JRadioButton rbNoProtestar;
    private javax.swing.JRadioButton rbSiCobrarInt;
    private javax.swing.JRadioButton rbSiProtestar;
    private javax.swing.JRadioButton rbSoles;
    private javax.swing.JTable tblImpresion;
    private javax.swing.JTextField txtCantDocReci;
    private javax.swing.JTextField txtCtaCte;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JTextField txtTelEmpresa;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtrsEmpresa;
    // End of variables declaration//GEN-END:variables
}