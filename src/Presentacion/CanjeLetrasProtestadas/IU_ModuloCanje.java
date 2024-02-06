package Presentacion.CanjeLetrasProtestadas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import static Entidades.Otros.Constante.DOLAR_STANDARD;
import static Entidades.Otros.Constante.SOL;
import Entidades.Otros.NroLetras;
import Entidades.Otros.PagoCabec;
import Entidades.Pagos;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Mantenimiento.CabecesDAO;
import Servicios.Editor_Celdas;
import Servicios.Servicio_TipoCambio;
import Servicios.Util;
import Servicios.facturacion.Editor;
import Servicios.facturacion.Render_Letra;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.Tabla_letras;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ledis Rivera Changra
 */
public class IU_ModuloCanje extends javax.swing.JFrame {

    Tabla_letras modelo;
    Editor edit;
    Render_Letra rend;
    Date fechaInicio;
    double total;
    Clientes cliente;
    ArrayList<PagoCabec> listaPagosCabec;
    Servicio_Documentos serv_doc;
    Sistema sisLet;
    String FacBol_Referencia;
    Tipocambio t;
    Util util;
    Editor_Celdas editor;
    JTextField edit_field;
    
    private String gastosAdm;
    private String montoLetras;
    private String sumaTotal;
    
    private List<Cabeces> lstCabOrig;
    private CabecesDAO cabDao;
    
    public IU_ModuloCanje(Clientes c, String moneda) {
//        System.out.println("Constructor 1");
        serv_doc = new Servicio_Documentos();
        cabDao = new CabecesDAO();
        cliente = c;
        util = new Util();
        modelo = new Tabla_letras(total, Calendar.getInstance().getTime(), util, this);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        edit = new Editor();
        rend = new Render_Letra();
        tb_letras.setDefaultEditor(JDateChooser.class, edit);
        tb_letras.setDefaultRenderer(JDateChooser.class, rend);
        
        lblMonedaM.setText(moneda);
        lblMonedaGA.setText(moneda);
        lblMonedaTotal.setText(moneda);
        
        gastosAdm = util.DosDecimales(0.00);
        montoLetras = util.DosDecimales(Double.parseDouble(sumaTotal));
        sumaTotal = montoLetras;
        
        txtMontoLetras.setText(montoLetras);
        txtGastosAdm.setText(gastosAdm);
        
        SetCliente();
        tb_letras.setCellSelectionEnabled(true);
        SetEditor();
        
        alinearColumnaDerecha();
    }
    
    public IU_ModuloCanje(Clientes c, ArrayList<PagoCabec> lista, double total, String moneda) {
//        System.out.println("Constructor 2");
        serv_doc = new Servicio_Documentos();
        cabDao = new CabecesDAO();
        t = serv_doc.getTipoCambio(new GregorianCalendar().getTime());
        listaPagosCabec = lista;
        cliente = c;
        this.total = total;
        util = new Util();
        modelo = new Tabla_letras(total, Calendar.getInstance().getTime(), util, this);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        edit = new Editor();
        rend = new Render_Letra();
        tb_letras.setDefaultEditor(JDateChooser.class, edit);
        tb_letras.setDefaultRenderer(JDateChooser.class, rend);
        
        lblMonedaM.setText(moneda);
        lblMonedaGA.setText(moneda);
        lblMonedaGA.setText(moneda);
        
        gastosAdm = util.DosDecimales(0.00);
        montoLetras = util.DosDecimales(total);
        sumaTotal = montoLetras;
        
        txtMontoLetras.setText(montoLetras);
        txtGastosAdm.setText(gastosAdm);
        
        SetCliente();
        tb_letras.setCellSelectionEnabled(true);
        SetEditor();
        
        alinearColumnaDerecha();
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_letras.getColumnModel().getColumn(1).setCellRenderer(tcr); // Importe
    }
    
    private void SetEditor(){
        edit_field = new JTextField();
        editor = new Editor_Celdas(edit_field);
        editor.setClickCountToStart(2);
        
        for ( int i = 0; i < modelo.getColumnCount()-1; i++ ) {
            tb_letras.getColumnModel().getColumn(i).setCellEditor(editor);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_ruc = new javax.swing.JTextField();
        tx_nombre = new javax.swing.JTextField();
        tx_direccion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lb_total = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dt_fecha = new JDateChooser(Calendar.getInstance().getTime());
        tx_letras = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMontoLetras = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGastosAdm = new javax.swing.JTextField();
        bt_calcular = new javax.swing.JButton();
        lblMonedaM = new javax.swing.JLabel();
        lblMonedaGA = new javax.swing.JLabel();
        lblMonedaTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_letras = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTotImporte = new javax.swing.JTextField();
        bt_canjear = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        btnCancelarCanje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));

        jLabel1.setText("Nro RUC");

        jLabel2.setText("Nombre");

        jLabel3.setText("Direccion");

        tx_ruc.setEnabled(false);

        tx_nombre.setEnabled(false);

        tx_direccion.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones de Canje"));

        lb_total.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel5.setText("Fecha de Emision");

        jLabel6.setText("Cantidad Letras");

        dt_fecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dt_fechaPropertyChange(evt);
            }
        });

        tx_letras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_letrasKeyTyped(evt);
            }
        });

        jLabel4.setText("Monto(Letras)");

        txtMontoLetras.setEnabled(false);

        jLabel7.setText("Gastos Admin.");

        txtGastosAdm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGastosAdmKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGastosAdmKeyTyped(evt);
            }
        });

        bt_calcular.setText("Calcular Monto");
        bt_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_calcularActionPerformed(evt);
            }
        });

        lblMonedaM.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMonedaM.setText("US $");

        lblMonedaGA.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMonedaGA.setText("US $");

        lblMonedaTotal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblMonedaTotal.setText("US $");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Total : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(44, 44, 44)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_letras, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMonedaM)
                                .addComponent(lblMonedaGA))
                            .addGap(5, 5, 5)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMontoLetras)
                                .addComponent(txtGastosAdm, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(bt_calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblMonedaTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtMontoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMonedaM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGastosAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonedaGA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_letras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_total, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(lblMonedaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha Vcto"));

        tb_letras.setModel(modelo);
        jScrollPane1.setViewportView(tb_letras);

        jLabel8.setText("Total Importe:");

        txtTotImporte.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotImporte.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        bt_canjear.setText("Realizar Canje");
        bt_canjear.setEnabled(false);
        bt_canjear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_canjearActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        btnCancelarCanje.setText("Cancelar Canje");
        btnCancelarCanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCanjeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(bt_canjear, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCancelarCanje, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_canjear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelarCanje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void calcularMonto() {
        try {
            gastosAdm = txtGastosAdm.getText();
            double sumTot = Double.parseDouble(gastosAdm) + Double.parseDouble(montoLetras);
//            System.out.println("sumTot:" + sumTot);
            sumaTotal = util.DosDecimales(sumTot);
            
//            System.out.println("montoLetras:" + montoLetras);
//            System.out.println("gastosAdm:" + gastosAdm);
//            System.out.println("sumaTotal:" + sumaTotal);
            
            if ( montoLetras.equals(sumaTotal) ) {
                JOptionPane.showMessageDialog(null,
                                              "Ingrese una cantidad mayor que 0 para Gastos Administrativos.",
                                              "ADVERTENCIA",
                                              JOptionPane.WARNING_MESSAGE);
            } else {
                lb_total.setText(sumaTotal);
                txtTotImporte.setText(sumaTotal);
                
                BorrarTabla();
//                modelo.asignarLetras(Integer.parseInt(tx_letras.getText()));
                modelo.asignarLetras(Integer.parseInt(tx_letras.getText()), sumaTotal);
                bt_calcular.setEnabled(false);
                bt_canjear.setEnabled(true);
            }
        } catch ( NumberFormatException e ) {
            System.out.println("Excepcion:" + e.getMessage());
            JOptionPane.showMessageDialog(null,
                                          "Ingrese un número entero para la Cantidad de Letras.",
                                          "ADVERTENCIA",
                                          JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void bt_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_calcularActionPerformed
        calcularMonto();
    }//GEN-LAST:event_bt_calcularActionPerformed

    private void tx_letrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_letrasKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            bt_calcular.doClick();
        }
    }//GEN-LAST:event_tx_letrasKeyTyped

    private void dt_fechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dt_fechaPropertyChange
        if ( evt.getPropertyName().compareTo("date") == 0 ) {
            modelo.setFechaInicio(dt_fecha.getDate());
        }
    }//GEN-LAST:event_dt_fechaPropertyChange

    private void bt_canjearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_canjearActionPerformed
        tb_letras.clearSelection();
        sisLet = serv_doc.getSis("Numero de Letra");
        
        if ( validarImporteRealEditado() ) {
            if ( modelo.Valida() ) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la Operación?", "Confirmación",
                                                           JOptionPane.YES_NO_OPTION);

                if ( opcion == JOptionPane.YES_OPTION ) {
                    ArrayList<NroLetras> letras_detalle = modelo.getLt();
                    ArrayList<Pagos> pagos = SetPagos();
                    ArrayList<Cabeces> cabec_letras = SetCabeces(letras_detalle);

    //                System.out.println("N° lista de pagos cabec:" + listaPagosCabec.size());
                    lstCabOrig = buscarCabeces(listaPagosCabec);
    //                System.out.println("N° lstCabOrig:" + lstCabOrig);
    //                System.out.println("OK :D");

                    if ( serv_doc.GuardarLetras_Canje(cabec_letras, pagos, lstCabOrig) ) {
                        serv_doc.actualizarSis(sisLet);
                        JOptionPane.showMessageDialog(null, "Guardado con Éxito", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar", "Guardar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos Vacíos o Inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El 'Total Importe' editado debe ser igual al 'Total'.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_canjearActionPerformed

    private boolean validarImporteRealEditado () {
        return txtTotImporte.getText().equals(lb_total.getText());
    }
    
    private List<Cabeces> buscarCabeces(List listaLetOrig) {
        List<Cabeces> lstCabeces = new ArrayList<Cabeces>();
        
        Iterator it = listaLetOrig.iterator();
        while ( it.hasNext() ) {
            PagoCabec objCab = (PagoCabec) it.next();
            CabecesId cabId = objCab.getCabec().getId();
            String tipoTra = cabId.getTipotra();
            String tipoDoc = cabId.getTipodoc();
            String nroSerie = cabId.getNrorserie();
            String nroDoc = cabId.getNrodocumento();
            Cabeces cab = cabDao.obtenerCabecera(tipoTra, tipoDoc, nroSerie, nroDoc);
            lstCabeces.add(cab);
        }
        return lstCabeces;
    }
    
    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if ( opcion == JOptionPane.YES_OPTION ) {
            dispose();
        }
    }//GEN-LAST:event_bt_salirActionPerformed

    private void txtGastosAdmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosAdmKeyTyped
        char caracter = evt.getKeyChar();
        if ( ((caracter < '0') || (caracter > '9')) && (caracter != '\b') && caracter!='.' && (caracter!='-') ) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtGastosAdmKeyTyped

    private void txtGastosAdmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGastosAdmKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            calcularMonto();
        }
    }//GEN-LAST:event_txtGastosAdmKeyPressed

    private void btnCancelarCanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCanjeActionPerformed
        gastosAdm = util.DosDecimales(0.00);
        montoLetras = util.DosDecimales(Double.parseDouble(sumaTotal));
        sumaTotal = montoLetras;
        
        txtMontoLetras.setText(montoLetras);
        txtGastosAdm.setText(gastosAdm);
        
        limpiarTabla();
        
        bt_calcular.setEnabled(true);
        bt_canjear.setEnabled(false);
        tx_letras.setText("");
        txtTotImporte.setText("");
    }//GEN-LAST:event_btnCancelarCanjeActionPerformed
    
    public void limpiarTabla(){
        try {
            
//            DefaultTableModel modelo = (DefaultTableModel) tb_letras.getModel();
            int filas = tb_letras.getRowCount();
//            System.out.println("filas:" + filas);
            for ( int i = 0; filas > i; i++ ) {
                modelo.eliminar(i); //removeRow(0);
            }
        } catch ( Exception e ) {
            System.out.println("Excepcion::" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void SetCliente() {
        tx_nombre.setText(cliente.getNombre());
        tx_direccion.setText(cliente.getDireccion());
        tx_ruc.setText(cliente.getRuc());
    }

    private void BorrarTabla() {
        int numRows = modelo.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            modelo.eliminar(0);
        }
    }
    
    private ArrayList<Cabeces> SetCabeces(ArrayList<NroLetras> letras){
        ArrayList<Cabeces> lista = new ArrayList<>();
        int numero = sisLet.getUltimonumero()+1;
        int serie = sisLet.getSerie();
        int i = 0;
        t = serv_doc.getTipoCambio(dt_fecha.getDate());
        
        try {
            System.out.println(t);
            
        } catch ( Exception e ) {
            t = new Tipocambio();
            t.setFecha(dt_fecha.getDate());
            new Servicio_TipoCambio().Insertar(t);
        }
        
        for ( NroLetras letra : letras ) {
            Cabeces cab = new Cabeces();
            cab.setTipoperacion("3");
            cab.setClientes(cliente);
            cab.setTipocambio(t);
            cab.setTipocambio_1(serv_doc.getTipoCambio(Calendar.getInstance().getTime()).getValorventa());
            cab.setId(new CabecesId("V", "06", String.valueOf(serie),String.valueOf(numero)));
            cab.setTotal(letra.getImporte());
            
            if ( DOLAR_STANDARD.equals(lblMonedaM.getText()) ) {
                cab.setMoneda("2"); //Dolares
                
            } else if ( SOL.equals(lblMonedaM.getText()) ){
                cab.setMoneda("1"); // Soles
            }
            
            JDateChooser chooser = (JDateChooser) modelo.getValueAt(i++, 3);
            cab.setFechavencimiento(chooser.getDate());
            cab.setLetraFacbol(FacBol_Referencia);
            lista.add(cab);
            numero++;
        }
        sisLet.setUltimonumero(numero - 1);
        return lista;
    }
    
    private ArrayList<Pagos> SetPagos(){
        FacBol_Referencia = "";
        ArrayList<Pagos> nuevo = new ArrayList<>();
//        int max_doc_referencia = 8;
        
        System.out.println("N° listaPagosCabec(documentos seleccionados):" + listaPagosCabec.size());
        for ( int i = 0; i < listaPagosCabec.size(); i++ ) {
            PagoCabec pc = listaPagosCabec.get(i);
            
//            if ( i < max_doc_referencia ) {
                FacBol_Referencia = FacBol_Referencia + pc.getCabec().getId().getNrodocumento();
            
                if ( i < listaPagosCabec.size() - 1 )
                    FacBol_Referencia = FacBol_Referencia + "/";
//            }
            Pagos pago = new Pagos();
            pago.setCabeces(pc.getCabec());
            pago.setFecha(t.getFecha());//El dia de hoy
            pago.setImporte(pc.getImportePendiente());
            pago.setForma("E");
            nuevo.add(pago);
        }
//        System.out.println("FacBol_Referencia::" + FacBol_Referencia);
//        System.out.println("Longitud MAXIMA:" + FacBol_Referencia.length());
        return nuevo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_calcular;
    private javax.swing.JButton bt_canjear;
    private javax.swing.JButton bt_salir;
    private javax.swing.JButton btnCancelarCanje;
    private com.toedter.calendar.JDateChooser dt_fecha;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_total;
    private javax.swing.JLabel lblMonedaGA;
    private javax.swing.JLabel lblMonedaM;
    private javax.swing.JLabel lblMonedaTotal;
    protected javax.swing.JTable tb_letras;
    private javax.swing.JTextField tx_direccion;
    private javax.swing.JTextField tx_letras;
    private javax.swing.JTextField tx_nombre;
    private javax.swing.JTextField tx_ruc;
    private javax.swing.JTextField txtGastosAdm;
    private javax.swing.JTextField txtMontoLetras;
    public javax.swing.JTextField txtTotImporte;
    // End of variables declaration//GEN-END:variables
}