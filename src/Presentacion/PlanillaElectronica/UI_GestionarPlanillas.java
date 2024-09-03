package Presentacion.PlanillaElectronica;

import Entidades.Cabeces;
import Entidades.Control;
import static Entidades.Otros.Constante.COLUMNA_CABECES;
import static Entidades.Otros.Constante.COLUMNA_MONEDA;
import static Entidades.Otros.Constante.COLUMNA_MONTO_TOTAL;
import static Entidades.Otros.Constante.COLUMNA_RUC;
import static Entidades.Otros.Constante.COLUMNA_VENDEDOR;
import Mantenimiento.CabecesDAO;
import Mantenimiento.ControlDAO;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.PlanillaElectronica.*;
import Servicios.Importaciones.Servicio_Control;
import Servicios.TipoMensaje;
import Servicios.facturacion.Billete;
import Servicios.facturacion.Servicio_Impresion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import static org.hibernate.tool.hbm2x.StringUtils.substring;

/**
 *
 * @author maverick225
 * @author modified : Ledis Rivera Ch.
 */
public class UI_GestionarPlanillas extends javax.swing.JFrame {

    private Servicio_GestionarPlanillasElectronicas servicioGP;
    Date inicio;
    Date fin;
    int tipo;
    CabecesDAO cabdao;

    public UI_GestionarPlanillas(Date desde, Date hasta, int tipo) {
        this();
        inicio = desde;
        cabdao = new CabecesDAO();
        fin = hasta;
        servicioGP = new Servicio_GestionarPlanillasElectronicas(this, inicio, fin, tipo);
        setLocationRelativeTo(null);
        setVisible(true);
        SetFechas();
        
        if ( tipo == 3 ) {
            pnl_soles.setVisible(false);
        }
        
        tx_cliente.setDocument(new Validar_Mayusculas(tx_cliente, 20));
        tx_nroDoc.setDocument(new Validar_Mayusculas(tx_nroDoc, 20));
        this.tipo = tipo;
        
        if ( tipo == 1 ) {
            lb_titulo.setText("Planilla de Ventas");
            
        } else if ( tipo == 2 ) {
            lb_titulo.setText("Planilla de Compras");
            
        } else if (tipo == 5) {
            lb_titulo.setText("Planilla Electronica");
        }
        else {
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
        ocultarColumna(COLUMNA_VENDEDOR);
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
        jPanel3 = new javax.swing.JPanel();
        btn_Salir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lb_titulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tx_nroDoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx_cliente = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
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
                "ITEM", "TIPO DOCUMENTO", "NRO SERIE", "NRO DOCUMENTO", "FECHA", "CLIENTE / PROVEEDOR", "TOTAL ", "REFERENCIA", "CABECES", "RUC", "MONEDA", "MONTOTOT", "VENDEDOR", "ESTADO", "NROGUIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_Planillas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        javax.swing.GroupLayout pnl_solesLayout = new javax.swing.GroupLayout(pnl_soles);
        pnl_soles.setLayout(pnl_solesLayout);
        pnl_solesLayout.setHorizontalGroup(
            pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_solesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(pnl_solesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_S_TotalFacturas)
                    .addComponent(txt_S_TotalDebito)
                    .addComponent(txt_S_TotalCredito)
                    .addComponent(txt_S_TotalBoletas)
                    .addComponent(txt_S_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jLabel4)
                    .addComponent(txt_S_TotalBoletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 18, Short.MAX_VALUE))
        );

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jButton1.setText("Generar TXT");
        jButton1.setMaximumSize(new java.awt.Dimension(53, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(53, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(53, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 699, Short.MAX_VALUE)
                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addGap(13, 13, 13))
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tx_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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

        javax.swing.GroupLayout pnl_dolaresLayout = new javax.swing.GroupLayout(pnl_dolares);
        pnl_dolares.setLayout(pnl_dolaresLayout);
        pnl_dolaresLayout.setHorizontalGroup(
            pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_dolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(pnl_dolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_D_TotalFacturas)
                    .addComponent(txt_D_TotalDebito)
                    .addComponent(txt_D_TotalCredito)
                    .addComponent(txt_D_TotalBoletas)
                    .addComponent(txt_D_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_soles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnl_dolares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 110, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lb_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
    
    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        servicioGP.buscarPor();
    }//GEN-LAST:event_bt_buscarActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean txtOk = true;
        String dirMain = "";
        try {
            Control control = new ControlDAO().Obtener_Objeto(1);
            dirMain = control.getRutareportes() + "\\FactElect\\"; //"C:/Users/ADMIN/Documents/FactElect/";
            File directorio = new File(dirMain);
            if (!directorio.exists()) {
                directorio.mkdirs();
                System.out.println("Se creó la carpeta");
            }
            txtOk = true;
        } catch (Exception e) {
            txtOk = false;
            System.out.println("Error por generacion de directorio " + e);
        }
        //Si el directorio se creo correctamente, se empieza con la generacion de txt
        if (txtOk) {
            String textoMonto;
            String moneda;
            DefaultTableModel modelo = (DefaultTableModel) tab_Planillas.getModel();
            int numRows = modelo.getRowCount();
            for (int i = 0; i < numRows; i++) {
                modelo.getValueAt(i, 0);
                moneda = modelo.getValueAt(i, 10).toString().equals("$") ? " DOLARES AMERICANOS" : " SOLES";
                String montoPantalla = modelo.getValueAt(i, 6).toString();
                String[] separarMonto = montoPantalla.split(" ");
                textoMonto = Billete.BilleteX(Double.parseDouble(separarMonto[1]), moneda);
                String serie = modelo.getValueAt(i, 2).toString();
                String doc = modelo.getValueAt(i, 3).toString();
                String tipo = modelo.getValueAt(i, 1).toString();
                String tipoEnv = "";
                
                
                System.out.println("Nro.de Guia? 13 : " + modelo.getValueAt(i, 13).toString());                                
                System.out.println("Nro.de Guia? 14 : " + modelo.getValueAt(i, 14).toString());                                
                
                String guia = modelo.getValueAt(i, 14).toString();
                if (tipo.equals("F")) {
                    tipoEnv = "01";
                } else {
                    if (tipo.equals("B")) {
                        tipoEnv = "02";
                    } else {
                        if (tipo.equals("NC")) {
                            tipoEnv = "03";
                        } else {
                            tipoEnv = "04";
                        }
                    }
                }
                                
                //if ( cabeces.nroguia.equal(null)  ){
                System.out.println("Nro de Guia : " + guia);
                  //      }
                    
                String nombre = generarNombreComprobante(tipo, serie, doc,tipoEnv);
                
                if (tipoEnv.equals("01") || tipoEnv.equals("02")) {
                    txtOk = generarTxtFacturaBoleta(dirMain, tipoEnv, nombre, serie, doc, textoMonto);
                    
// Aqui rutina de generacion Guia de Remision 01/03/2024 JSP
//                  String guia = modelo.getValueAt(i, 14).toString();
                    if ("".equals(guia)) {
                    } else {
                      String docBack = doc;                        
                      String tipoEnvBack = "09";
                      tipo = "T";
                      tipoEnv = "01";
                      serie = "1";
                      docBack = guia.toString();
                      String nombreg = generarNombreComprobante(tipo, serie, docBack,tipoEnv);     
                      System.out.println("Nombre de Guia : " + nombreg);
                      txtOk = generarTxtGuia(dirMain, tipoEnv, nombreg, serie, doc, textoMonto, docBack);
                    }                            
                    
                    
                } else
                
                    {
                    System.out.println("dirMain: " + dirMain);
                    System.out.println("tipoEnv: " + tipoEnv);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Serie: " + serie);
                    System.out.println("Doc: " + doc);
                    System.out.println("tetoMonto: " + textoMonto);
                    
                    if (tipoEnv.equals("04")) {
                    nombre = "FND1-"+substring(nombre,5,13);
                    System.out.println("Nuevo Valor de nombre ND:" + nombre);
                    }
                    if (tipoEnv.equals("03")) {
                    System.out.println("Nuevo Valor de nombre NC:" + nombre);
                    }
                    
                    txtOk = generarTxtNc_Nd(dirMain, tipoEnv, nombre, serie, doc, textoMonto);
                    
                    System.out.println("dirMain:" + dirMain + " tipoEnv:" + tipoEnv + " Nombre:" + nombre + " Serie: " + serie + "Tipo Doc: " + doc + " TextoMonto: " + textoMonto );
                    
                }
            }
            
            if (txtOk) {
                JOptionPane.showMessageDialog(this, "Archivos generados correctamente", "Facturacion Electronica", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al generar los archivos", "Facturacion Electronica", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private boolean generarTxtFacturaBoleta(String dirMain, String tipoBDLocal, String nombre,
            String serie, String doc, String textoMonto) {
        
        
        boolean ok = true;
        String tipoSunat = "";
        String tipoDocumento = "";
        try {
             if(tipoBDLocal.equals("01")){
                tipoSunat = "01";
                tipoDocumento = "RUC";
            }else{
                if(tipoBDLocal.equals("02")){
                    tipoSunat = "03";
                    tipoDocumento = "DNI";
                }
            }
            
            List listaCabeces, listaCabeces2, listaCabeces3;
            String linea1 = "", linea2 = "";

            PrintWriter writer = new PrintWriter(dirMain + "20509788261-" + tipoSunat + "-" + nombre + ".txt", "UTF-8");

            listaCabeces = cabdao.ObtenerLinea1Txt(serie, doc, textoMonto,tipoDocumento,tipoBDLocal);
                    
            Iterator it = listaCabeces.iterator();
            while (it.hasNext()) {
                linea1 = (String) it.next();
            }
            
            writer.println(linea1);
//            writer.println(linea2);
   
            listaCabeces3 = cabdao.ObtenerLinea3Txt(serie, doc,tipoBDLocal);
            Iterator it3 = listaCabeces3.iterator();

            while (it3.hasNext()) {
                writer.println((String) it3.next());
            }
            writer.close();
            System.out.println("tipoBDLocal " + tipoBDLocal);
            //Cabeces cabeces = cabdao.ObtenerCabeceraConTxt(tipoBDLocal, doc);
            //cabeces.setTxtgen(1);
            //cabdao.ActualizarCabecera(cabeces);
            cabdao.ActualizarNota(Integer.parseInt(doc),Integer.parseInt(serie),tipoBDLocal);
        } catch (Exception e) {
            System.out.println("Error en generar txt de Factura o boleta "+e);
            ok = false;
        }

        return ok;
    }

//-------
    private boolean generarTxtGuia(String dirMain, String tipoBDLocal, String nombre,
            String serie, String doc, String textoMonto, String docBack) {
        
        
        boolean ok = true;
        String tipoSunat = "";
        String tipoDocumento = "";
        try {

            tipoSunat = "09";
            tipoDocumento = "RUC";

            
            List listaCabeces, listaCabeces2, listaCabeces3;
            String linea1 = "", linea2 = "";

            PrintWriter writer = new PrintWriter(dirMain + "20509788261-" + tipoSunat + "-" + nombre + ".txt", "UTF-8");

            listaCabeces = cabdao.ObtenerLinea1TxtG(serie, doc, textoMonto,tipoDocumento,tipoBDLocal,docBack);
                    
            Iterator it = listaCabeces.iterator();
            while (it.hasNext()) {
                linea1 = (String) it.next();
            }
            
            writer.println(linea1);
//            writer.println(linea2);
   
            listaCabeces3 = cabdao.ObtenerLinea3TxtG(serie, doc,tipoBDLocal);
            Iterator it3 = listaCabeces3.iterator();

            while (it3.hasNext()) {
                writer.println((String) it3.next());
            }
            writer.close();
            System.out.println("tipoBDLocal " + tipoBDLocal);
            //Cabeces cabeces = cabdao.ObtenerCabeceraConTxt(tipoBDLocal, doc);
            //cabeces.setTxtgen(1);
            //cabdao.ActualizarCabecera(cabeces);
            cabdao.ActualizarNota(Integer.parseInt(doc),Integer.parseInt(serie),tipoBDLocal);
        } catch (Exception e) {
            System.out.println("Error en generar txt de Guia Remision "+e);
            ok = false;
        }

        return ok;
    }
    
//-------
    
    private boolean generarTxtNc_Nd(String dirMain, String tipoBDLocal, String nombre,
            String serie, String doc, String textoMonto){
        boolean ok = true;
        String tipoSunat=""; 
        try{
            if(tipoBDLocal.equals("03")){
                tipoSunat = "07";
            }else{
                if(tipoBDLocal.equals("04")){
                    tipoSunat = "08";
                }
            }
            List listaCabeces, listaCabeces2, listaCabeces3;
            String linea1 = "", linea2 = "";

            PrintWriter writer = new PrintWriter(dirMain + "20509788261-" + tipoSunat + "-" + nombre + ".txt", "UTF-8");
            listaCabeces = cabdao.ObtenerLinea1TxtNC(serie, doc, tipoSunat, textoMonto,tipoBDLocal);
            Iterator it = listaCabeces.iterator();
            while (it.hasNext()) {
                linea1 = (String) it.next();
            }
//            listaCabeces2 = cabdao.ObtenerLinea2TxtNC(serie, doc, tipoBDLocal);
//            Iterator it2 = listaCabeces2.iterator();
//            while (it2.hasNext()) {
//                linea2 = (String) it2.next();
//            }
            
            writer.println(linea1);
//            writer.println(linea2);
            System.out.println("Linea 1 : " + linea1);
            listaCabeces3 = cabdao.ObtenerLinea3TxtNc(serie, doc, tipoBDLocal);
            Iterator it3 = listaCabeces3.iterator();
            
            while (it3.hasNext()) {
                writer.println((String) it3.next());
            }
            writer.close();
            cabdao.ActualizarNota(Integer.parseInt(doc),Integer.parseInt(serie),tipoBDLocal);
        }catch(Exception e){
            System.out.println("Error en generar el archivo "+e.getLocalizedMessage());
            ok = false;
        }
        return ok;
    }
    
    public String generarNombreComprobante(String tipo,String serie,String doc,String tipoBDLocal){
        String nombreComprobante = "";
        String serie1 = "0000";
        if(tipo.equals("F") || tipo.equals("B") || tipo.equals("T")){
            serie1 = serie1.substring(0,4-(tipo.length()+serie.length()));
            serie1 = tipo+serie1+serie;
            String doc1 = "00000000";
            doc1 = doc1.substring(0,8-doc.length());
            doc1 = doc1+doc;
            nombreComprobante = serie1+"-"+doc1;
        }else{
            nombreComprobante = cabdao.ObtenerNombreNC(serie, doc,tipoBDLocal).get(0).toString();
        }
        return nombreComprobante;
    }
    
    /*private List obtenerLinea3NC_ND(){
        
        return new List();
    } */
    
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
//            System.out.println("total:" + total);
            lista.add(new FilaPlanilla(tip, numDoc, fecha, ruc, cliente, total));
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
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    public javax.swing.JTextField txt_D_Total;
    public javax.swing.JTextField txt_D_TotalBoletas;
    public javax.swing.JTextField txt_D_TotalCredito;
    public javax.swing.JTextField txt_D_TotalDebito;
    public javax.swing.JTextField txt_D_TotalFacturas;
    public javax.swing.JTextField txt_S_Total;
    public javax.swing.JTextField txt_S_TotalBoletas;
    public javax.swing.JTextField txt_S_TotalCredito;
    public javax.swing.JTextField txt_S_TotalDebito;
    public javax.swing.JTextField txt_S_TotalFacturas;
    // End of variables declaration//GEN-END:variables
}