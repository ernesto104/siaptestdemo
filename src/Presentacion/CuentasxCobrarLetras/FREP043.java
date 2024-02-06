package Presentacion.CuentasxCobrarLetras;

import Entidades.Control;
import static Entidades.Otros.Constante.BANCO_LETRA;
import static Entidades.Otros.Constante.SELECCIONAR;
import static Entidades.Otros.Constante.TIPO_LETRA;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Presentacion.CuentasxCobrarFacturas.ListaFacturas;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Servicio_CobrarLetras;
import Servicios.Servicio_Control;
import Servicios.Servicio_Excel;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Keily Mendiolaza
 * @author modified : Ledis Rivera (2016-2017)
 */
public class FREP043 extends javax.swing.JFrame {

    MENU001 m;
    BigDecimal cont_Emi_G;
    BigDecimal cont_Emi_C;
    BigDecimal cont_Emi_D;
    BigDecimal cont_Sal_D;
    BigDecimal cont_Sal_G;
    BigDecimal cont_Sal_C;
    int filaseleccionada;
    DefaultTableModel model;
    boolean seleccion = false;
    Usuarios usuario;
    IU_BuscarLetrasxCobrar filt;
    
    String rolSeleccionado;
    Util util;

    public FREP043(Usuarios usuario, String rol, boolean btnCCL){
        initComponents();
        setLocationRelativeTo(null);
        cont_Emi_G = new BigDecimal(0.0);
        cont_Emi_C = new BigDecimal(0.0);
        cont_Emi_D = new BigDecimal(0.0);
        cont_Sal_D = new BigDecimal(0.0);

        cont_Sal_G = new BigDecimal(0.0);
        cont_Sal_C = new BigDecimal(0.0);
        model = (DefaultTableModel) tablaLetras.getModel();
        this.usuario = usuario;
        fechaSistema();
        iniciarTabla(tablaLetras);
        
        m = new MENU001("", rol, 0, 0);
        rolSeleccionado = rol;
        btnPagar.setEnabled(btnCCL);
        util = new Util();
        alinearDerecha();
        
//        ocultarColumna(BANCO_LETRA);
        ocultarColumna(TIPO_LETRA);
    }
    
    private void ocultarColumna(int columna) {
        tablaLetras.getColumnModel().getColumn(columna).setMaxWidth(0);
        tablaLetras.getColumnModel().getColumn(columna).setMinWidth(0);
        tablaLetras.getColumnModel().getColumn(columna).setPreferredWidth(0);

        tablaLetras.getColumnModel().getColumn(12).setMaxWidth(180);    // tamaño de Fact.Referencia
        tablaLetras.getColumnModel().getColumn(12).setMinWidth(180);    // tamaño de Fact.Referencia
        tablaLetras.getColumnModel().getColumn(12).setPreferredWidth(180);   // tamaño de Fact.Referencia

        tablaLetras.getColumnModel().getColumn(11).setMaxWidth(60);    // tamaño de Banco
        tablaLetras.getColumnModel().getColumn(11).setMinWidth(60);    // tamaño de Banco
        tablaLetras.getColumnModel().getColumn(11).setPreferredWidth(60);   // tamaño de Banco
        
        tablaLetras.getColumnModel().getColumn(9).setMaxWidth(70);    // tamaño de dias vencido
        tablaLetras.getColumnModel().getColumn(9).setMinWidth(70);    // tamaño de dias vencido 
        tablaLetras.getColumnModel().getColumn(9).setPreferredWidth(70);   // tamaño de dias vencido        
        
        tablaLetras.getColumnModel().getColumn(6).setMaxWidth(50);    // tamaño de moneda
        tablaLetras.getColumnModel().getColumn(6).setMinWidth(50);    // tamaño de moneda
        tablaLetras.getColumnModel().getColumn(6).setPreferredWidth(50);   // tamaño de moneda

        tablaLetras.getColumnModel().getColumn(3).setMaxWidth(40);    // tamaño de anulado
        tablaLetras.getColumnModel().getColumn(3).setMinWidth(40);    // tamaño de anulado
        tablaLetras.getColumnModel().getColumn(3).setPreferredWidth(40);   // tamaño de anulado
        
        tablaLetras.getColumnModel().getColumn(2).setMaxWidth(40);    // tamaño de anulado
        tablaLetras.getColumnModel().getColumn(2).setMinWidth(40);    // tamaño de anulado
        tablaLetras.getColumnModel().getColumn(2).setPreferredWidth(40);   // tamaño de anulado        
    }
    
    private void alinearDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaLetras.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tablaLetras.getColumnModel().getColumn(8).setCellRenderer(tcr);
        tablaLetras.getColumnModel().getColumn(9).setCellRenderer(tcr);
    }

    public String fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = sdf.format(ahora);
        return fecha;
    }

    public DefaultTableModel iniciarTabla(JTable tabla) {
        textoLetras.setText("LETRAS PENDIENTES DE COBRANZA AL " + fechaSistema());
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        Servicio_CobrarLetras sc = new Servicio_CobrarLetras();
        List li1 = sc.Listar_Cuentas_x_Cobrar_Letras();
        int tam = li1.size();
        
        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[5].equals("US$") ) {
                    cont_Emi_D = cont_Emi_D.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                    
                } else {
                    cont_Emi_C = cont_Emi_C.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
                String valorEmision = formatearMonetario(String.valueOf(a[6]), 2);
                String saldoXPagar = formatearMonetario(String.valueOf(a[7]), 2);

                String renovacion = "";
                if ( a[2] != null ) {
                    if ( "0".equals(String.valueOf(a[2])) ) {
                        renovacion = "";
                    } else {
                        renovacion = String.valueOf(a[2]);
                    }
                }
//                System.out.println("RENOVACION:::" + renovacion);
                
//                System.out.println("a[12] =" + a[12]);
                String bancoLetra = String.valueOf(a[12]);
                if ( a[12] == null ) {
//                    bancoLetra = SELECCIONAR; // DEBERIA SER VACÍO
                    bancoLetra = "";
                }
                
                String tipoLetra = String.valueOf(a[13]);
                if ( a[13] == null ) {
                    tipoLetra = "LI";
                }
                
                Object[] fila = {a[0], a[1], renovacion, a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], bancoLetra, a[10], false, tipoLetra};
//                Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], a[10], false};
//                Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], false};
                modelo.addRow(fila);
            }
            String valorInicial = "0.00";
            txtemisionS.setText(valorInicial);
            txtsaldopagarS.setText(valorInicial);
            txtemisionD.setText(valorInicial);
            txtsaldopagarD.setText(valorInicial);

        } else {
            JOptionPane.showMessageDialog(null, "No existen registros");
        }
        return modelo;
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }

// decNum.toLocaleString('hi-IN')        
       //Localidad: Inglaterra  (###,###.##)
        String cadena= valorMonetario;
        Locale locale = new Locale ("en", "UK");
        NumberFormat objNF = NumberFormat.getInstance (locale);
        cadena= objNF.format(Double.parseDouble(cadena));
        valorMonetario = cadena;        
        
        return valorMonetario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCCLetras = new javax.swing.JPanel();
        textoLetras = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLetras = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtemisionS = new javax.swing.JTextField();
        txtsaldopagarS = new javax.swing.JTextField();
        txtemisionD = new javax.swing.JTextField();
        txtsaldopagarD = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();
        btnPendientes = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        btnRenovar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnRestaurar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        t2 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textoLetras.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        tablaLetras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Nº Letra", "Reno.", "Anul.", "Fec. Emisión", "Fec. Vencimiento", "Moneda", "Valor de Emisión", "Saldo x Pagar", "Dias Vencido", "Let. Banc", "Banco", "Fact. Ref", "Seleccionar", "tipoLetra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLetras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaLetrasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLetras);

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel3.setText("TOTAL EMISION EN SOLES S/.");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel4.setText("TOTAL EMISION EN DOLARES US$");

        txtemisionS.setEditable(false);
        txtemisionS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtsaldopagarS.setEditable(false);
        txtsaldopagarS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtemisionD.setEditable(false);
        txtemisionD.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtsaldopagarD.setEditable(false);
        txtsaldopagarD.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        btnGeneral.setText("General");
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        btnPendientes.setText("Pendientes");
        btnPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendientesActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnRenovar.setText("Renov/Refina");
        btnRenovar.setMaximumSize(new java.awt.Dimension(85, 23));
        btnRenovar.setMinimumSize(new java.awt.Dimension(85, 23));
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });

        btnAnular.setText("Anular Letra");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnDetalle.setText("Seleccionar Todo");
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel2.setText("TOTAL SALDO POR PAGAR EN SOLES S/.");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        jLabel5.setText("TOTAL SALDO POR PAGAR EN DOLARES US$ ");

        t1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        t3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCCLetrasLayout = new javax.swing.GroupLayout(panelCCLetras);
        panelCCLetras.setLayout(panelCCLetrasLayout);
        panelCCLetrasLayout.setHorizontalGroup(
            panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCCLetrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCCLetrasLayout.createSequentialGroup()
                        .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCCLetrasLayout.createSequentialGroup()
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCCLetrasLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(239, 239, 239)
                        .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelCCLetrasLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(10, 10, 10)
                            .addComponent(txtemisionD, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(342, 342, 342)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtsaldopagarD))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCCLetrasLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(40, 40, 40)
                            .addComponent(txtemisionS, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(342, 342, 342)
                            .addComponent(jLabel2)
                            .addGap(44, 44, 44)
                            .addComponent(txtsaldopagarS, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCCLetrasLayout.createSequentialGroup()
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPendientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRenovar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestaurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCCLetrasLayout.setVerticalGroup(
            panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCCLetrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCCLetrasLayout.createSequentialGroup()
                        .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(309, 309, 309))
                    .addGroup(panelCCLetrasLayout.createSequentialGroup()
                        .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(t1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                .addComponent(t2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(8, 8, 8)
                .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtsaldopagarS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtemisionS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtemisionD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCCLetrasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtsaldopagarD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPagar)
                    .addComponent(btnSalir)
                    .addGroup(panelCCLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDetalle)
                        .addComponent(btnAnular)
                        .addComponent(btnRenovar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReporte)
                        .addComponent(btnImprimir)
                        .addComponent(btnPendientes)
                        .addComponent(btnGeneral)
                        .addComponent(btnBuscar)
                        .addComponent(btnRestaurar)
                        .addComponent(btnExportar)))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCCLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCCLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaLetras.getModel();
        
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
            modelo.setValueAt(false, i, 13);
        }
        String valorInicial = "0.00";
        txtemisionD.setText(valorInicial);
        txtsaldopagarD.setText(valorInicial);
        txtemisionS.setText(valorInicial);
        txtsaldopagarS.setText(valorInicial);
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        boolean seleccionado = (boolean) tablaLetras.getValueAt(filaseleccionada, 13);
        
        if ( seleccionado == false ) { // ¿1ra fila no esta seleccionada?
            DefaultTableModel modelo = (DefaultTableModel) tablaLetras.getModel();

            double totalEmisionDolares = Double.parseDouble(txtemisionD.getText());
            double totalSaldoDolares = Double.parseDouble(txtsaldopagarD.getText());

            double totalEmisionSoles = Double.parseDouble(txtemisionS.getText());
            double totalSaldoSoles = Double.parseDouble(txtsaldopagarS.getText());

            for ( int i = 0; i < modelo.getRowCount(); i++ ) {
                modelo.setValueAt(true, i, 13);
                String moneda = String.valueOf(tablaLetras.getValueAt(filaseleccionada, 6));
                
                if ( "US$".equals(moneda) ) { // En dolares
                    String cadena = String.valueOf(tablaLetras.getValueAt(i, 7));
                    cadena = cadena.replace(",", "");
                    double emisionDolar = Double.parseDouble(cadena);
                    totalEmisionDolares += emisionDolar;

                    cadena = String.valueOf(tablaLetras.getValueAt(i, 8));
                    cadena = cadena.replace(",", "");
                    double saldoDolar = Double.parseDouble(cadena);
                    totalSaldoDolares += saldoDolar;

                } else {
                    String cadena = String.valueOf(tablaLetras.getValueAt(i, 7));
                    cadena = cadena.replace(",", "");                                        
                    double emisionSol = Double.parseDouble(cadena);
                    totalEmisionSoles += emisionSol;

                    cadena = String.valueOf(tablaLetras.getValueAt(i, 8));
                    cadena = cadena.replace(",", "");                                        
                    double saldoSol = Double.parseDouble(cadena);
                    totalSaldoSoles += saldoSol;
                }
            }

            txtemisionD.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalEmisionDolares)),2));
            txtsaldopagarD.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoDolares)),2));

            txtemisionS.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalEmisionSoles)),2));
            txtsaldopagarS.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoSoles)),2));
        }
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        if ( !seleccion ) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente! ", "Pagar Cuenta", JOptionPane.ERROR_MESSAGE);
            
        } else {
            IU_AnularLetra pf = new IU_AnularLetra(this,model, filaseleccionada,usuario,tablaLetras);
            pf.setVisible(true);
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        //        dispose();
        //        IU_ImprimirCuentasxCobrar ic = new IU_ImprimirCuentasxCobrar();
        //        ic.setVisible(true);
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        dispose();
        IU_ImprimirLetras fc = new IU_ImprimirLetras(model, filaseleccionada, this);
        fc.setVisible(true);
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendientesActionPerformed
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        iniciarTabla(tablaLetras);
    }//GEN-LAST:event_btnPendientesActionPerformed

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        DefaultTableModel modelo = (DefaultTableModel) tablaLetras.getModel();
        textoLetras.setText("TODAS LAS LETRAS CANCELADAS Y POR COBRAR AL " + fechaSistema());

        Servicio_CobrarLetras sc = new Servicio_CobrarLetras();
        List li1 = sc.Listar_Cuentas_General_Letras();
        int tam = li1.size();

        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[5].equals("US$") ) {
                    cont_Emi_D = cont_Emi_D.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));

                } else {
                    cont_Emi_C = cont_Emi_C.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
                String valorEmision = formatearMonetario(String.valueOf(a[6]), 2);
                String saldoXPagar = formatearMonetario(String.valueOf(a[7]), 2);
                
                String renovacion = "";
                
                if ( a[2] != null ) {
                    
                    if ( "0".equals(String.valueOf(a[2])) ) {
                        renovacion = "";
                        
                    } else {
                        renovacion = String.valueOf(a[2]);
                    }
                }
                
//                System.out.println("a[12] =" + a[12]);
                String bancoLetra = String.valueOf(a[12]);
                if ( a[12] == null ) {
                    bancoLetra = SELECCIONAR; // DEBERIA SER VACÍO
                    bancoLetra = "";
                }
                
                String tipoLetra = String.valueOf(a[13]);
                if ( a[13] == null ) {
                    tipoLetra = "LI";
                }
//                System.out.println("tipoLetra(btn General):" + tipoLetra);
                Object[] fila = {a[0], a[1], renovacion, a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], bancoLetra, a[10], false, tipoLetra};
//                Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], a[10], false};
//                Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], false};
                modelo.addRow(fila);
            }

            String valorInicial = "0.00";
            txtemisionS.setText(valorInicial);
            txtsaldopagarS.setText(valorInicial);
            txtemisionD.setText(valorInicial);
            txtsaldopagarD.setText(valorInicial);

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        if ( !seleccion ) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente! ", "Pagar Cuenta", JOptionPane.ERROR_MESSAGE);
            
        } else {
            IU_PagarLetrasxCobrar pf = new IU_PagarLetrasxCobrar(model, filaseleccionada, m, usuario, tablaLetras,
                rolSeleccionado, btnPagar.isEnabled(), this);
            pf.setVisible(true);
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void tablaLetrasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLetrasMouseReleased
        int columnaSelecionada = tablaLetras.getSelectedColumn();

        if ( columnaSelecionada == 13 ) {
            filaseleccionada = tablaLetras.getSelectedRow();
            seleccion = true;
            String moneda = String.valueOf(tablaLetras.getValueAt(filaseleccionada, 6));
            
            if ( "US$".equals(moneda) ) { // En dolares
                double totalEmisionDolares = Double.parseDouble(txtemisionD.getText());
                double emisionDolar = Double.parseDouble(String.valueOf(tablaLetras.getValueAt(filaseleccionada, 7)));

                double totalSaldoDolares = Double.parseDouble(txtsaldopagarD.getText());
                double saldoDolar = Double.parseDouble(String.valueOf(tablaLetras.getValueAt(filaseleccionada, 8)));

                boolean seleccionado = (boolean) tablaLetras.getValueAt(filaseleccionada, 13);

                if ( seleccionado ) {
                    totalEmisionDolares += emisionDolar;
                    totalSaldoDolares += saldoDolar;

                } else {
                    totalEmisionDolares -= emisionDolar;
                    totalSaldoDolares -= saldoDolar;
                }
                txtemisionD.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalEmisionDolares)), 2));
                txtsaldopagarD.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoDolares)), 2));

            } else { // En soles
                double totalEmisionSoles = Double.parseDouble(txtemisionS.getText());
                double emisionSol = Double.parseDouble(String.valueOf(tablaLetras.getValueAt(filaseleccionada, 7)));

                double totalSaldoSoles = Double.parseDouble(txtsaldopagarS.getText());
                double saldoSol = Double.parseDouble(String.valueOf(tablaLetras.getValueAt(filaseleccionada, 8)));

                boolean seleccionado = (boolean) tablaLetras.getValueAt(filaseleccionada, 13);

                if ( seleccionado ) {
                    totalEmisionSoles += emisionSol;
                    totalSaldoSoles += saldoSol;

                } else {
                    totalEmisionSoles -= emisionSol;
                    totalSaldoSoles -= saldoSol;
                }
                txtemisionS.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalEmisionSoles)), 2));
                txtsaldopagarS.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoSoles)), 2));
            }
        }
    }//GEN-LAST:event_tablaLetrasMouseReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        tablaLetras.clearSelection();
        filt = new IU_BuscarLetrasxCobrar(this, m);
        filt.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                filt = null;
            }
        });
        filt.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        if ( !seleccion ) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente! ", "Renovar Cuenta", JOptionPane.ERROR_MESSAGE);
            
        } else {
            if(String.valueOf(model.getValueAt(filaseleccionada, 2)).equals("")){
                IU_GenerarLetraRenovada gl = new IU_GenerarLetraRenovada(model, filaseleccionada, m, usuario, tablaLetras,
                rolSeleccionado, this);
                gl.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Ya renovada ", "Ya renovada", JOptionPane.ERROR_MESSAGE);
            }

        }        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRenovarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        try {
            ArrayList lista = SetListaSeleccionados();
            Map<String, Object> parametros = SetParametros();
            Control control = new ControlDAO().Obtener_Objeto(1);
//        String nombreExcelLetras = generarExcelLetras(lista, parametros, control, OPC_SELECCIONADOS);
            generarExcelCtaCteLetrasSeleccionadas("Cta.Cte.Letras", control, parametros, lista);
            
        } catch ( Exception ex ) {
            Logger.getLogger(FREP043.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    // Genera excel para SOLO exportar REPORTE DE CTA. CTE. DE LETRAS
    private void generarExcelCtaCteLetrasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList lista)
                                                       throws FileNotFoundException, BiffException, IOException, WriteException {
        new Servicio_Excel(tablaLetras, this).exportarExcelCabDetCtaCteLetrasSeleccionadas(nombreHoja, control, parametros, lista);
    }
    
    private ArrayList SetListaSeleccionados() {
        ArrayList<ListaLetras> lista = new ArrayList<>();
        
        for ( int i = 0; i < model.getRowCount(); i++ ) {
            
            if ( (boolean) model.getValueAt(i, 13) == true ) {
                String cliente = String.valueOf(model.getValueAt(i, 0));
                String numLetra = String.valueOf(model.getValueAt(i, 1));
                String fechaEmision = String.valueOf(model.getValueAt(i, 4));
                String fechaVence = String.valueOf(model.getValueAt(i, 5));
                String emitido = String.valueOf(model.getValueAt(i, 6)) + " " + String.valueOf(model.getValueAt(i, 7));
                String saldo = String.valueOf(model.getValueAt(i, 6)) + " " + String.valueOf(model.getValueAt(i, 8));
                String diasVencido = String.valueOf(model.getValueAt(i, 9));
                String facturaRef = String.valueOf(model.getValueAt(i, 12));
                String numRenovacion = String.valueOf(model.getValueAt(i, 2));

                String numLetraBanco = String.valueOf(model.getValueAt(i, 10));
                String bancoLetra = String.valueOf(model.getValueAt(i, 11));

                lista.add(new ListaLetras(cliente, numLetra, fechaEmision, fechaVence, emitido, saldo, diasVencido, facturaRef,
                                          numRenovacion, numLetraBanco, bancoLetra));
            }
//            double totalEmitido = Double.parseDouble(String.valueOf(model.getValueAt(i, 7)));
//            double totalSaldo = Double.parseDouble(String.valueOf(model.getValueAt(i, 8)));
//            
//            if ( "US$".equals(model.getValueAt(i, 6)) ) {
//                totalEmitidoDolares += totalEmitido;
//                totalSaldoDolares += totalSaldo;
//                
//            } else {
//                totalEmitidoSoles += totalEmitido;
//                totalSaldoSoles += totalSaldo;
//            }
        }
        return lista;
    }
    
    private Map SetParametros() {
        Servicio_Control sc = new Servicio_Control();
        Map<String, Object> mapa = new HashMap<>();
        String titulo = textoLetras.getText();
        String empresa = sc.listar_control().get(0).getNombrempresa();
        mapa.put("titulo", titulo);
        mapa.put("empresa", empresa);
        
        mapa.put("totEmisionSoles", txtemisionS.getText());
        mapa.put("totSaldoSoles", txtsaldopagarS.getText());
        mapa.put("totEmisionDolar", txtemisionD.getText());
        mapa.put("totSaldoDolar", txtsaldopagarD.getText());
        
        return mapa;
    }
    
    public String tipoDoc(String doc) {
        String tipo = "";
        
        switch ( doc ) {
            case "01":
                tipo = "F";
                break;
                
            case "02":
                tipo = "B";
                break;
                
            case "03":
                tipo = "NC";
                break;
                
            case "04":
                tipo = "ND";
                break;
                
            default:
                tipo = "Entrada no válida";
                break;
        }
        return tipo;
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    //    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FREP042.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FREP042.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FREP042.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FREP042.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new FREP043().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnular;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnDetalle;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnGeneral;
    public javax.swing.JButton btnImprimir;
    public javax.swing.JButton btnPagar;
    public javax.swing.JButton btnPendientes;
    public javax.swing.JButton btnRenovar;
    public javax.swing.JButton btnReporte;
    public javax.swing.JButton btnRestaurar;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelCCLetras;
    public javax.swing.JLabel t1;
    public javax.swing.JLabel t2;
    public javax.swing.JLabel t3;
    public javax.swing.JLabel t4;
    public javax.swing.JTable tablaLetras;
    public javax.swing.JLabel textoLetras;
    public javax.swing.JTextField txtemisionD;
    public javax.swing.JTextField txtemisionS;
    public javax.swing.JTextField txtsaldopagarD;
    public javax.swing.JTextField txtsaldopagarS;
    // End of variables declaration//GEN-END:variables

    void limpiar() {
//        t1.setText("");
//        t2.setText("");
//        t3.setText("");
//        t4.setText("");
        cont_Emi_D = new BigDecimal(0);
        cont_Emi_C = new BigDecimal(0);
        cont_Sal_D = new BigDecimal(0);
        cont_Sal_C = new BigDecimal(0);
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }   
}