package Presentacion.EmisionPlanillas;

import Entidades.Cabeces;
import static Entidades.Otros.Constante.COLUMNA_CABECES;
import static Entidades.Otros.Constante.COLUMNA_MONEDA;
import static Entidades.Otros.Constante.COLUMNA_MONTO_TOTAL;
import static Entidades.Otros.Constante.COLUMNA_RUC;
import static Entidades.Otros.Constante.COLUMNA_VENDEDOR;
import Mantenimiento.CabecesDAO;
import Mantenimiento.ControlDAO;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.EmisionPlanillas.Servicio_GestionarPlanillas;
import Servicios.Importaciones.Servicio_Control;
import Servicios.TipoMensaje;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import Entidades.Roles;
import Entidades.Usuarios;
import java.util.List;


/**
 *
 * @author maverick225
 * @author modified : Ledis Rivera Ch.
 */
public class UI_GestionarPlanillas extends javax.swing.JFrame {

    private Servicio_GestionarPlanillas servicioGP;
    Date inicio;
    Date fin;
    int tipo;
    String rolfinal;
    CabecesDAO cabdao;

    public UI_GestionarPlanillas(Date desde, Date hasta, int tipo, String rolfinal) {
        this();
        
        inicio = desde;
        cabdao = new CabecesDAO();
        fin = hasta;
        servicioGP = new Servicio_GestionarPlanillas(this, inicio, fin, tipo);
        setLocationRelativeTo(null);
        setVisible(true);
        SetFechas();
        
//      txt_D_TotalCosto.setVisible(false);
//      lb_usuario.setText(c.getUsuarios()==null?"":c.getUsuarios().getNombre());
        if ( "ADMINISTRADOR".equals(rolfinal)) {

        } else {
            txt_D_TotalCosto.setVisible(false);        
            txt_D_TotalUtilidad.setVisible(false);
            txt_S_TotalCosto.setVisible(false);        
            txt_S_TotalUtilidad.setVisible(false);
        }
        //      txt_D_TotalCosto.setVisible(false);
//      lb_usuario.setText(c.getUsuarios()==null?"":c.getUsuarios().getNombre());

        
        if ( tipo == 3 ) {
            pnl_soles.setVisible(false);
        }
        
        tx_cliente.setDocument(new Validar_Mayusculas(tx_cliente, 20));
        tx_nroDoc.setDocument(new Validar_Mayusculas(tx_nroDoc, 20));
        tx_vendedor.setDocument(new Validar_Mayusculas(tx_vendedor, 20));
        
        this.tipo = tipo;
        
        if ( tipo == 1 ) {
            lb_titulo.setText("Planilla de Ventas");
            
        } else if ( tipo == 2 ) {
            lb_titulo.setText("Planilla de Compras");
            
        } else {
            lb_titulo.setText("Planilla de Salidas Varias");
        }
        setAnchoColumnas();
        ocultarColumnas();
    }

    
    private void ocultarColumnas() {
        ocultarColumna(COLUMNA_CABECES);
        ocultarColumna(COLUMNA_RUC);
        ocultarColumna(COLUMNA_MONEDA);
        ocultarColumna(COLUMNA_MONTO_TOTAL);
//        ocultarColumna(COLUMNA_VENDEDOR);
    }
    
    private void ocultarColumna(int columna) {
        tab_Planillas.getColumnModel().getColumn(columna).setMaxWidth(0);
        tab_Planillas.getColumnModel().getColumn(columna).setMinWidth(0);
        tab_Planillas.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }
    
    public UI_GestionarPlanillas() {
        initComponents();
    }

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_S_TotalDebito = new javax.swing.JTextField();
        txt_S_TotalBoletas = new javax.swing.JTextField();
        txt_S_TotalCredito = new javax.swing.JTextField();
        txt_S_TotalFacturas = new javax.swing.JTextField();
        txt_S_Total = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_S_TotalCosto = new javax.swing.JTextField();
        txt_S_TotalUtilidad = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lb_titulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tx_nroDoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx_cliente = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tx_vendedor = new javax.swing.JTextField();
        bt_limpiar = new javax.swing.JButton();
        pnl_dolares = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_D_TotalDebito = new javax.swing.JTextField();
        txt_D_TotalBoletas = new javax.swing.JTextField();
        txt_D_TotalCredito = new javax.swing.JTextField();
        txt_D_TotalFacturas = new javax.swing.JTextField();
        txt_D_Total = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_D_TotalCosto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_D_TotalUtilidad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lb_inicio = new javax.swing.JLabel();
        lb_fin = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btn_MasDatos = new javax.swing.JButton();
        btn_IngresarAnulado = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        btn_Modificar = new javax.swing.JButton();
        btn_Excel = new javax.swing.JButton();
        btn_Filtrar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

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
                "ITEM", "TIPO DCMT", "NRO SERIE", "NRO DCMTO", "FECHA", "CLIENTE / PROVEEDOR", "TOTAL ", "REFERENCIA", "CABECES", "RUC", "MONEDA", "MONTOTOT", "VENDEDOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_Planillas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tab_Planillas.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tab_PlanillasComponentRemoved(evt);
            }
        });
        jScrollPane2.setViewportView(tab_Planillas);

        pnl_soles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL SOLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jLabel2.setText("Importe Total");

        jLabel3.setText("Total Facturas");

        jLabel4.setText("Total Boletas");

        jLabel5.setText("Total Notas de Crédito");

        jLabel6.setText("Total Notas de Débito");

        txt_S_TotalDebito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalDebito.setEnabled(false);

        txt_S_TotalBoletas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalBoletas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalBoletas.setEnabled(false);

        txt_S_TotalCredito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalCredito.setEnabled(false);

        txt_S_TotalFacturas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalFacturas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalFacturas.setEnabled(false);

        txt_S_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_Total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_Total.setEnabled(false);

        jLabel7.setText("Importe Tota Costo");

        jLabel17.setText("Total Utilidad Periodo");

        txt_S_TotalCosto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalCosto.setEnabled(false);

        txt_S_TotalUtilidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_S_TotalUtilidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_S_TotalUtilidad.setEnabled(false);

        javax.swing.GroupLayout pnl_solesLayout = new javax.swing.GroupLayout(pnl_soles);
        pnl_soles.setLayout(pnl_solesLayout);
        pnl_solesLayout.setHorizontalGroup(
            pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_solesLayout.createSequentialGroup()
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_solesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(pnl_solesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_S_TotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_S_TotalFacturas)
                        .addComponent(txt_S_TotalDebito)
                        .addComponent(txt_S_TotalCredito)
                        .addComponent(txt_S_TotalBoletas)
                        .addComponent(txt_S_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_S_TotalUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        pnl_solesLayout.setVerticalGroup(
            pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_solesLayout.createSequentialGroup()
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_S_TotalFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_S_TotalBoletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_S_TotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_S_TotalDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_S_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_S_TotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_S_TotalUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 702, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
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

        jLabel8.setText("Cliente/Proveedor");

        tx_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_clienteActionPerformed(evt);
            }
        });
        tx_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_clienteKeyTyped(evt);
            }
        });

        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        jLabel18.setText("Vendedor");

        tx_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_vendedorActionPerformed(evt);
            }
        });
        tx_vendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_vendedorKeyTyped(evt);
            }
        });

        bt_limpiar.setText("Limpiar Filtro");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tx_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(tx_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_dolares.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL DOLARES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jLabel9.setText("Importe Total");

        jLabel10.setText("Total Facturas");

        jLabel11.setText("Total Boletas");

        jLabel12.setText("Total Notas de Crédito");

        jLabel13.setText("Total Notas de Débito");

        txt_D_TotalDebito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalDebito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalDebito.setEnabled(false);

        txt_D_TotalBoletas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalBoletas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalBoletas.setEnabled(false);

        txt_D_TotalCredito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalCredito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalCredito.setEnabled(false);

        txt_D_TotalFacturas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalFacturas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalFacturas.setEnabled(false);

        txt_D_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_Total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_Total.setEnabled(false);
        txt_D_Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_D_TotalActionPerformed(evt);
            }
        });

        jLabel14.setText("Importe Total Costo");

        txt_D_TotalCosto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalCosto.setEnabled(false);
        txt_D_TotalCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_D_TotalCostoActionPerformed(evt);
            }
        });

        jLabel15.setText("Total Utilidad Periodo");

        txt_D_TotalUtilidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_D_TotalUtilidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_D_TotalUtilidad.setEnabled(false);

        javax.swing.GroupLayout pnl_dolaresLayout = new javax.swing.GroupLayout(pnl_dolares);
        pnl_dolares.setLayout(pnl_dolaresLayout);
        pnl_dolaresLayout.setHorizontalGroup(
            pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_dolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_dolaresLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnl_dolaresLayout.createSequentialGroup()
                        .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_D_TotalUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_D_TotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_D_TotalFacturas)
                        .addComponent(txt_D_TotalDebito)
                        .addComponent(txt_D_TotalCredito)
                        .addComponent(txt_D_TotalBoletas)
                        .addComponent(txt_D_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        pnl_dolaresLayout.setVerticalGroup(
            pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dolaresLayout.createSequentialGroup()
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_D_TotalFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_D_TotalBoletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_D_TotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_D_TotalDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_D_Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_D_TotalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_D_TotalUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
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

        btn_MasDatos.setText("Más datos");
        btn_MasDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MasDatosActionPerformed(evt);
            }
        });

        btn_IngresarAnulado.setText("Reg.Doc.Anulados");
        btn_IngresarAnulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarAnuladoActionPerformed(evt);
            }
        });

        btn_Imprimir.setText("Imprimir");
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });

        btn_Modificar.setText("Modificar");
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });

        btn_Excel.setText("Excel");
        btn_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExcelActionPerformed(evt);
            }
        });

        btn_Filtrar.setText("Filtrar");
        btn_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FiltrarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(lb_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_MasDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(btn_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_IngresarAnulado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_Imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(btn_Filtrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                .addComponent(pnl_soles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnl_dolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lb_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_dolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_soles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_MasDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_IngresarAnulado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcelActionPerformed
        servicioGP.exportaExcel(lb_titulo.getText());
    }//GEN-LAST:event_btn_ExcelActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_MasDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MasDatosActionPerformed
        servicioGP.muestraDetalles();
    }//GEN-LAST:event_btn_MasDatosActionPerformed

    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if ( opcion == JOptionPane.YES_OPTION ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            Servicio_Impresion.exporta(7, lista, parametros, "plantillas/Planillas.pdf");
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            String url1 = rutaReportes + "\\plantillas\\planillas.jrxml";
            String url2 = rutaReportes + "\\plantillas\\planillas.jasper";
            
            try {
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(GestionarPlanillas):" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_ImprimirActionPerformed

    public void convertPedidoToPDF(String url1, String url2,
                                   HashMap parametros,
                                   ArrayList listado) throws Exception{
        
        JasperCompileManager.compileReportToFile(url1,url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
                                                               parametros,
                                                               new JRBeanCollectionDataSource(listado));
        
        JasperViewer.viewReport(jasperPrint, false, Locale.getDefault());
    }
    
    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        int fila = tab_Planillas.getSelectedRow();
//        System.out.println("fila seleccionada:" + fila);
        if ( fila >= 0 ) {
            UI_ModificarPlanilla modificar = new UI_ModificarPlanilla(servicioGP.getCabecera(fila), servicioGP, 
                                                                      tab_Planillas, inicio, fin, tipo,
                                                                      this, fila);
            
        } else {
            new TipoMensaje().Error("ERROR, Seleccione una item");
        }
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FiltrarActionPerformed
        servicioGP.Filtrar_planillas();
    }//GEN-LAST:event_btn_FiltrarActionPerformed

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        servicioGP.buscarPor();
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void btn_IngresarAnuladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarAnuladoActionPerformed
        UI_IngresarAnulado anularDoc = new UI_IngresarAnulado(this, inicio, fin, tipo);
        anularDoc.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {         
            
            }
        });
    }//GEN-LAST:event_btn_IngresarAnuladoActionPerformed

    private void tx_nroDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_nroDocKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_nroDocKeyTyped

    private void tx_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_clienteKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_clienteKeyTyped
    
    private void txt_D_TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_TotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_TotalActionPerformed

    private void txt_D_TotalCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_D_TotalCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_D_TotalCostoActionPerformed

    private void tx_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_clienteActionPerformed

    private void tx_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_vendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_vendedorActionPerformed

    private void tx_vendedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_vendedorKeyTyped
        // TODO add your handling code here:
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }

    }//GEN-LAST:event_tx_vendedorKeyTyped

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        tx_vendedor.setText("");
        tx_cliente.setText("");
        tx_nroDoc.setText("");
//        BorrarTabla();
        SetLista();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tab_PlanillasComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tab_PlanillasComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tab_PlanillasComponentRemoved

    public void refrescarTablaPlanillas() {
        BorrarTabla();
        SetLista();
    }
    
    private void BorrarTabla() {
        System.out.println("borrar tabla");
        DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();
        int numRows = modelo.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            modelo.removeRow(0);
        }
    }
        
    private ArrayList SetLista() {
        System.out.println("setear lista");
        DefaultTableModel dtm = (DefaultTableModel) tab_Planillas.getModel();
        ArrayList<FilaPlanilla> lista = new ArrayList<>();
        
        for ( int i = 0; i < tab_Planillas.getRowCount(); i++ ) {
            String tip = String.valueOf(dtm.getValueAt(i, 1));
            String numDoc = String.valueOf(dtm.getValueAt(i, 3));
            String fecha = String.valueOf(dtm.getValueAt(i, 4));
            Cabeces c =  ((Cabeces) (dtm.getValueAt(i, 8)));
            String ruc = "";
            
            if ( c != null && c.getClientes() != null ) {
//                System.out.println("d" + i);
                 ruc = ((Cabeces) (dtm.getValueAt(i, 8))).getClientes().getRuc();
            }
            String cliente = String.valueOf(dtm.getValueAt(i, 5));
            String total = String.valueOf(dtm.getValueAt(i, 6));
            String vendedor = String.valueOf(dtm.getValueAt(i, 12));
//            System.out.println("total:" + total);
            lista.add(new FilaPlanilla(tip, numDoc, fecha, ruc, cliente, total, vendedor));
        }
        return lista;
    }

    private HashMap SetParametros() {
        HashMap mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("titulo", lb_titulo.getText());
        mapa.put("empresa", empresa);
        mapa.put("desde", lb_inicio.getText());
        mapa.put("hasta", lb_fin.getText());
        mapa.put("tsoles", txt_S_Total.getText());
        mapa.put("ts_facturas", txt_S_TotalFacturas.getText());
        mapa.put("ts_boletas", txt_S_TotalBoletas.getText());
        mapa.put("ts_nc", txt_S_TotalCredito.getText());
        mapa.put("ts_nd", txt_S_TotalDebito.getText());
        mapa.put("tdolares", txt_D_Total.getText());
        mapa.put("td_facturas", txt_D_TotalFacturas.getText());
        mapa.put("td_boletas", txt_D_TotalBoletas.getText());
        mapa.put("td_nc", txt_D_TotalCredito.getText());
        mapa.put("td_nd", txt_D_TotalDebito.getText());
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
            
            switch ( i ) {
                case 0: // ITEM
                    anchoColumna = (5 * ancho) / 100;
                    break;
                    
                case 1: // TIPO DOC.
                    anchoColumna = (10 * ancho) / 100; // 15
                    break;
                    
                case 2: // NRO SERIE
                    anchoColumna = (8 * ancho) / 100; // 10
                    break;
                    
                case 3: // NRO DOCUMENTO
                    anchoColumna = (10 * ancho) / 100;
                    break;
                    
                case 4: // FECHA
                    anchoColumna = (10 * ancho) / 100;
                    break;
                    
                case 5: // CLIENTE/PROVEEDOR
                    anchoColumna = (40 * ancho) / 100;
                    break;
                    
                case 6: // TOTAL
                    anchoColumna = (10 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JButton btn_Excel;
    private javax.swing.JButton btn_Filtrar;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.JButton btn_IngresarAnulado;
    private javax.swing.JButton btn_MasDatos;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_fin;
    private javax.swing.JLabel lb_inicio;
    private javax.swing.JLabel lb_titulo;
    private javax.swing.JPanel pnl_dolares;
    private javax.swing.JPanel pnl_soles;
    public javax.swing.JTable tab_Planillas;
    public javax.swing.JTextField tx_cliente;
    public javax.swing.JTextField tx_nroDoc;
    public javax.swing.JTextField tx_vendedor;
    public javax.swing.JTextField txt_D_Total;
    public javax.swing.JTextField txt_D_TotalBoletas;
    public javax.swing.JTextField txt_D_TotalCosto;
    public javax.swing.JTextField txt_D_TotalCredito;
    public javax.swing.JTextField txt_D_TotalDebito;
    public javax.swing.JTextField txt_D_TotalFacturas;
    public javax.swing.JTextField txt_D_TotalUtilidad;
    public javax.swing.JTextField txt_S_Total;
    public javax.swing.JTextField txt_S_TotalBoletas;
    public javax.swing.JTextField txt_S_TotalCosto;
    public javax.swing.JTextField txt_S_TotalCredito;
    public javax.swing.JTextField txt_S_TotalDebito;
    public javax.swing.JTextField txt_S_TotalFacturas;
    public javax.swing.JTextField txt_S_TotalUtilidad;
    // End of variables declaration//GEN-END:variables
}
