package Presentacion.CuentasxCobrarFacturas;

import Entidades.Cabeces;
import Entidades.Control;
import Entidades.Importaciones.Ordenacion;
import Entidades.Pagos;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Servicio_CobrarFacturas;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Control;
import Servicios.Servicio_Excel;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Keily Mendiolaza
 * @Modificado por: Ledis Rivera Ch.
 */
public class FREP042 extends javax.swing.JFrame {

    boolean seleccion = false;
    String nombre;
    int filaseleccionada;
    List<Cabeces> listaFacturas;
    List<Pagos> cantidadpagos;
    DefaultTableModel model;
    MENU001 m;
    IU_1Cliente clientes;
    IU_FiltrarCuentasxCobrar filt;
    BigDecimal num;
    Usuarios usuario;
    BigDecimal cont_Fac_C;
    BigDecimal cont_Sal_C;
    BigDecimal cont_Fac_D;
    BigDecimal cont_Sal_D;
    //
    BigDecimal cont_Fac_SG;
    BigDecimal cont_Sal_SG;
    BigDecimal cont_Fac_DG;
    BigDecimal cont_Sal_DG;
    
    String rolElegido;
    Util util;
    int clienteId;  //CAMBIO DE CLIENTE ID


    public FREP042(Usuarios usuario, String rol, boolean btnCCF) {
        initComponents();
        Ordenacion ord=new Ordenacion();
        ord.eventoTabla(this);
        
        setLocationRelativeTo(null);
        btnExportar.setVisible(false);
        cont_Fac_C = new BigDecimal(0.00);
        cont_Sal_C = new BigDecimal(0.00);
        cont_Fac_D = new BigDecimal(0.00);
        cont_Sal_D = new BigDecimal(0.00);

        cont_Fac_SG = new BigDecimal(0.00);
        cont_Sal_SG = new BigDecimal(0.00);
        cont_Fac_DG = new BigDecimal(0.00);
        cont_Sal_DG = new BigDecimal(0.00);

        num = new BigDecimal(100);
        model = (DefaultTableModel) tablaFactura.getModel();
        clienteId = 0; //CAMBIO DE CLIENTE ID
        iniciarTabla(tablaFactura, clienteId); //CAMBIO DE CLIENTE ID
        
        this.usuario = new Usuarios();
        this.usuario = usuario;
        seleccion=false;
        
        rolElegido = rol;
        botonPagar.setEnabled(btnCCF);
        m = new MENU001(usuario.getNombre(), rol, 0, 0);
//        m = new MENU001("", rol, 0, 0);
        util = new Util();
        alinearDerecha();      
    }
    
    private void alinearDerecha() {
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaFactura.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tablaFactura.getColumnModel().getColumn(8).setCellRenderer(tcr);
        tablaFactura.getColumnModel().getColumn(9).setCellRenderer(tcr);
        
      //  tablaFactura.getColumnModel().getColumn(9).
        System.out.println("Column 9  " + tablaFactura.getColumnModel().getColumn(9));
        
// Oculta -serie y Banco
        tablaFactura.getColumnModel().getColumn(2).setMaxWidth(0);   //Oculta serie
        tablaFactura.getColumnModel().getColumn(2).setMinWidth(0);   //Oculta serie
        tablaFactura.getColumnModel().getColumn(2).setPreferredWidth(0);   //Oculta serie
        
        tablaFactura.getColumnModel().getColumn(10).setMaxWidth(0);   //Oculta Banco
        tablaFactura.getColumnModel().getColumn(10).setMinWidth(0);   //Oculta Banco
        tablaFactura.getColumnModel().getColumn(10).setPreferredWidth(0);   //Oculta Banco

// Tamaño de columna Moneda        
        tablaFactura.getColumnModel().getColumn(6).setMaxWidth(40);   //Ancho Moneda
        tablaFactura.getColumnModel().getColumn(6).setMinWidth(40);   //Ancho Moneda
        tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(40);   //Ancho Moneda
    }

    public String fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = sdf.format(ahora);
        return fecha;
    }

    public double totalFacturado() {
        int filas = this.tablaFactura.getRowCount();
        double cont = 0;
        
        for ( int i = 0; i < filas; i++ ) {

            if ( filas == 0 ) {
                JOptionPane.showMessageDialog(null, "Este cliente no dispone de cuentas por cobrar", "No existe registros", 0);
            }
            
            if ( filas == 1 ) {
                cont = (double) tablaFactura.getValueAt(i, 6);
                return cont;
            }
            
            if ( filas > 1 ) {
                if ( i != filas - 1 ) {
                    if ( i == 0 ) {
                        cont = Math.round((double) tablaFactura.getValueAt(i, 6) * 100.0) / 100.0;
                    }
                    cont = cont + Math.round((double) tablaFactura.getValueAt(i + 1, 6) * 100.0) / 100.0;
                }
            }
        }
        return cont;
    }

    public double totalSaldo() {
        int filas = this.tablaFactura.getRowCount();
        double cont = 0;
        
        for ( int i = 0; i < filas; i++ ) {
            if ( filas == 0 ) {
                JOptionPane.showMessageDialog(null, "Este cliente no dispone de cuentas por cobrar", "No existe registros", 0);
            }
            
            if ( filas == 1 ) {
                cont = (double) tablaFactura.getValueAt(i, 8);
                return cont;
            }
            
            if ( filas > 1 ) {
                if ( i != filas - 1 ) {
                    if ( i == 0 ) {
                        cont = Math.rint((double) tablaFactura.getValueAt(i, 8) * 100) / 100;
                    }
                    cont = cont + Math.rint((double) tablaFactura.getValueAt(i + 1, 8) * 100) / 100;
                }
            }
        }
        return cont;
    }

    public DefaultTableModel iniciarTabla(JTable tabla, int clienteID) {  //CAMBIO DE CLIENTE ID
        TextoFacturas.setText("FACTURAS PENDIENTES POR COBRAR AL " + fechaSistema());
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        Servicio_CobrarFacturas sc = new Servicio_CobrarFacturas(); 
        //CAMBIO DE CLIENTE ID ////////////////
        clienteId = clienteID;
        System.out.print("ClienteId: "+clienteId+"\n");
        List li1 = new ArrayList();
        if (clienteId != 0) { 
            li1 = sc.Listar_Cuentas_General_Cliente(clienteId);
        } else { 
            li1 = sc.Listar_Cuentas_x_Cobrar();
        }
        //CAMBIO DE CLIENTE ID ////////////////
//El objeto es lil, el campo clientes es el 4 , empezando desde 
//        DefaultTableModel.
//        Servicio_CobrarFacturas.
//                TableRowSorter<> sorter = new TableRowSorter<>(modelo);
//        List<SortKey> sortKeys = new ArrayList<>();
//        sortkeys.add(lil);
//        sortkeys.add(new  li1(4,SortOrder.ASCENDING));
//        sorter.setSortKeys(sortKeys);
//        System.out.println("Que tiene el objeto : " + lil);
//        
        
        
        int tam = li1.size();
        
        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[10].equals("US$") ) {
                    cont_Fac_D = cont_Fac_D.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                    cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));                                      
                    
                } else {
                    cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                    cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
                
                String estadoFactura = String.valueOf(a[11]);
//                System.out.println("Estado Factura(PENDIENTE):" + estadoFactura);
//                String facturado = "0.00", pagado = "0.00", saldo = "0.00";
                
                if ( !"ANULADO".equals(estadoFactura) ) {
                    String facturado = formatearMonetario(String.valueOf(a[5]), 2);
                    String pagado = formatearMonetario(String.valueOf(a[6]), 2);
                    String saldo = formatearMonetario(String.valueOf(a[7]), 2);
                    
                    Object[] fila = {a[0], a[1], a[2], a[3], a[9], a[4], a[10], facturado, pagado, saldo, a[8], false};
//                    Object[] fila = {a[0], a[1], a[3], a[9], a[4], a[10], facturado, pagado, saldo, false};                    
                    modelo.addRow(fila);
                }
            }
            String valorInicial = "0.00";
            txtTotalFacturado.setText(valorInicial);
            txtTotalSaldo.setText(valorInicial);
            txtTotFacDolar.setText(valorInicial);
            txttotalSaldoDolar.setText(valorInicial);
//            txtTotalFacturado.setText(cont_Fac_C.toString());
//            txtTotalSaldo.setText(cont_Sal_C.toString());
//            txtTotFacDolar.setText(cont_Fac_D.toString());
//            txttotalSaldoDolar.setText(cont_Sal_D.toString());

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }
        return modelo;
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        int longitud = 0;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
        } else {
            longitud = valorMonetario.length();
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

        panelCobrarFac = new javax.swing.JPanel();
        TextoFacturas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonPagar = new javax.swing.JButton();
        botGeneral = new javax.swing.JButton();
        botonPendientes = new javax.swing.JButton();
        btn_1cliente = new javax.swing.JButton();
        boton_Filtrar = new javax.swing.JButton();
        botonImprimir = new javax.swing.JButton();
        boton_seleccionartodo = new javax.swing.JButton();
        boton_restaurar = new javax.swing.JButton();
        botonDetalle = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        t1 = new javax.swing.JLabel();
        t2 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        txtTotalFacturado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalSaldo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotFacDolar = new javax.swing.JLabel();
        txttotalSaldoDolar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        btnExportarTodo = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextoFacturas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL FACTURADO EN SOLES : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL SALDO EN SOLES : ");

        botonPagar.setText("Pagar");
        botonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPagarActionPerformed(evt);
            }
        });

        botGeneral.setText("General");
        botGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botGeneralActionPerformed(evt);
            }
        });

        botonPendientes.setText("Pendientes");
        botonPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPendientesActionPerformed(evt);
            }
        });

        btn_1cliente.setText("1 Cliente");
        btn_1cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1clienteActionPerformed(evt);
            }
        });

        boton_Filtrar.setText("Filtrar");
        boton_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_FiltrarActionPerformed(evt);
            }
        });

        botonImprimir.setText("Imprimir");
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        boton_seleccionartodo.setText("Seleccionar Todo");
        boton_seleccionartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_seleccionartodoActionPerformed(evt);
            }
        });

        boton_restaurar.setText("Restaurar");
        boton_restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_restaurarActionPerformed(evt);
            }
        });

        botonDetalle.setText("Detalle");
        botonDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetalleActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        t1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        t3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTotalFacturado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTotalFacturado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TOTAL FACTURADO EN DOLARES : ");

        txtTotalSaldo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTotalSaldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL SALDO EN DOLARES : ");

        txtTotFacDolar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTotFacDolar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txttotalSaldoDolar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txttotalSaldoDolar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Documento", "Serie", "Fecha", "RUC", "Nombre de Cliente ▲▼", "Mon", "Facturado", "Pagado", "Saldo", "Banco", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaFacturaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFactura);
        if (tablaFactura.getColumnModel().getColumnCount() > 0) {
            tablaFactura.getColumnModel().getColumn(3).setResizable(false);
            tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(230);
        }

        btnExportarTodo.setText("Exportar Todo");
        btnExportarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarTodoActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCobrarFacLayout = new javax.swing.GroupLayout(panelCobrarFac);
        panelCobrarFac.setLayout(panelCobrarFacLayout);
        panelCobrarFacLayout.setHorizontalGroup(
            panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCobrarFacLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarFacLayout.createSequentialGroup()
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                .addComponent(botonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_1cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_seleccionartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotalFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(58, 58, 58)
                                        .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))))
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(txtTotFacDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarFacLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txttotalSaldoDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarFacLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextoFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCobrarFacLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarFacLayout.createSequentialGroup()
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExportarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        panelCobrarFacLayout.setVerticalGroup(
            panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCobrarFacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                        .addComponent(btnExportarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtTotalFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCobrarFacLayout.createSequentialGroup()
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtTotFacDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txttotalSaldoDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelCobrarFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_1cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_seleccionartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCobrarFac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCobrarFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPagarActionPerformed
        if ( !seleccion ) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente! ", "Pagar Cuenta", JOptionPane.ERROR_MESSAGE);
            
        } else {
            IU_PagarFacturasxCobrar pf = new IU_PagarFacturasxCobrar(model, filaseleccionada, m, usuario, tablaFactura, rolElegido, botonPagar.isEnabled(), this.clienteId);  //CAMBIO DE CLIENTE ID //
            System.out.print("ClienteId en btn Pagar: ¨"+this.clienteId+"\n");
            pf.setVisible(true);
        }
    }//GEN-LAST:event_botonPagarActionPerformed

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        IU_ImprimirCuentasxCobrar ic = new IU_ImprimirCuentasxCobrar(model, filaseleccionada, this);
        ic.setVisible(true);
    }//GEN-LAST:event_botonImprimirActionPerformed

    private void boton_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_FiltrarActionPerformed
        tablaFactura.clearSelection();
        filt = new IU_FiltrarCuentasxCobrar(this, m);
        
        filt.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                filt = null;
            }
        });
        filt.setVisible(true);
    }//GEN-LAST:event_boton_FiltrarActionPerformed

    private void botonDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetalleActionPerformed
        IU_DetalleCuentasxCobrar dc = new IU_DetalleCuentasxCobrar(model, filaseleccionada);
        dc.setVisible(true);
    }//GEN-LAST:event_botonDetalleActionPerformed

    private void boton_seleccionartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_seleccionartodoActionPerformed
        boolean seleccionado = (boolean) tablaFactura.getValueAt(0, 11); 
        
        if ( seleccionado == false ) {// ¿1ra fila no esta selecionada?
                        
            DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();

            double totalFacturadoDolares = Double.parseDouble(txtTotFacDolar.getText());
            double totalSaldoDolares = Double.parseDouble(txttotalSaldoDolar.getText());

            double totalFacturadoSoles = Double.parseDouble(txtTotalFacturado.getText());
            double totalSaldoSoles = Double.parseDouble(txtTotalSaldo.getText());

            for ( int i = 0; i < modelo.getRowCount(); i++ ) {

                modelo.setValueAt(true, i, 11);
                String moneda = String.valueOf(tablaFactura.getValueAt(i, 6));
                String tipodoc = String.valueOf(tablaFactura.getValueAt(i, 0));
            
                if ( "US$".equals(moneda) ) { // En dolares
                    
                    String cadena = String.valueOf(tablaFactura.getValueAt(i, 7));
                    cadena = cadena.replace(",", "");
                    double facturadoDolar = Double.parseDouble(cadena);
                    
                    if ("03".equals(tipodoc) ){  // NC Resta
                        totalFacturadoDolares -= facturadoDolar;
                    } else {
                        totalFacturadoDolares += facturadoDolar;
                    }                    

                    cadena = String.valueOf(tablaFactura.getValueAt(i, 9));
                    cadena = cadena.replace(",", "");
                    double saldoDolar = Double.parseDouble(cadena);
                    
                    if (("03".equals(tipodoc) )){  // NC Resta
                        totalSaldoDolares -= saldoDolar;
                    } else {
                        totalSaldoDolares += saldoDolar;
                    }

                } else {
                    String cadena = String.valueOf(tablaFactura.getValueAt(i, 7));
                    cadena = cadena.replace(",", "");                    
                    double facturadoSol = Double.parseDouble(cadena);
                    totalFacturadoSoles += facturadoSol;

                    cadena = String.valueOf(tablaFactura.getValueAt(i, 9));
                    cadena = cadena.replace(",", "");                                        
                    double saldoSol = Double.parseDouble(cadena);
                    totalSaldoSoles += saldoSol;
                }
            }

            txtTotFacDolar.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoDolares)), 2));
            txttotalSaldoDolar.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoDolares)), 2));

            txtTotalFacturado.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoSoles)), 2));
            txtTotalSaldo.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoSoles)), 2));
        }
    }//GEN-LAST:event_boton_seleccionartodoActionPerformed

    private static String formatearCeros(String numero, int cantidadDigitos) {
      
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    private void botGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botGeneralActionPerformed
        seleccion = false;
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
        TextoFacturas.setText("TODAS LAS FACTURAS CANCELADAS Y POR COBRAR AL " + fechaSistema());

        Servicio_CobrarFacturas sc = new Servicio_CobrarFacturas();

        List li1 = sc.Listar_Cuentas_General();
        clienteId = 0; //CAMBIO DE CLIENTE ID ///
        int tam = li1.size();

        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[10].equals("US$") ) {
                    cont_Fac_DG = cont_Fac_DG.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                    cont_Sal_DG = cont_Sal_DG.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                    
                } else {
                    cont_Fac_SG = cont_Fac_SG.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                    cont_Sal_SG = cont_Sal_SG.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
                
                String estado = String.valueOf(a[11]);
//                System.out.println("estado(General):" + estado);
                String columnaCliente = String.valueOf(a[4]);
                String facturado = "0.00", pagado = "0.00", saldo = "0.00";
                
                if ( "ANULADO".equals(estado) ) {
                    columnaCliente = "(ANULADO) " + a[4];
                    
                } else {
                    facturado = formatearMonetario(String.valueOf(a[5]), 2);
                    pagado = formatearMonetario(String.valueOf(a[6]), 2);
                    saldo = formatearMonetario(String.valueOf(a[7]), 2);
                }
                  Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, a[8], false};
//                Object[] fila = {a[0], a[1], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, false};                
//                Object[] fila = {a[0], a[1], a[2], a[3], a[9], a[4], a[10], a[5], a[6], a[7], a[8], false};
                modelo.addRow(fila);
            }
            String valorInicial = "0.00";
            txtTotalFacturado.setText(valorInicial);
            txtTotalSaldo.setText(valorInicial);
            txtTotFacDolar.setText(valorInicial);
            txttotalSaldoDolar.setText(valorInicial);

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }
    }//GEN-LAST:event_botGeneralActionPerformed

    private void boton_restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_restaurarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
        
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
            modelo.setValueAt(false, i, 11);
        }
        String valorInicial = "0.00";
        txtTotFacDolar.setText(valorInicial);
        txttotalSaldoDolar.setText(valorInicial);
        
        txtTotalFacturado.setText(valorInicial);
        txtTotalSaldo.setText(valorInicial);
    }//GEN-LAST:event_boton_restaurarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPendientesActionPerformed
        seleccion = false;
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        clienteId = 0; //CAMBIO DE CLIENTE ID //
        iniciarTabla(tablaFactura, clienteId); //CAMBIO DE CLIENTE ID //
    }//GEN-LAST:event_botonPendientesActionPerformed

    private void btn_1clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1clienteActionPerformed
        tablaFactura.clearSelection();
        
        if ( clientes == null ) {
            clientes = new IU_1Cliente(this);
            
            clientes.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    clientes = null;
                }
            });
            clientes.setVisible(true);
            
        } else {
            clientes.setVisible(true);
        }
    }//GEN-LAST:event_btn_1clienteActionPerformed

    private void tablaFacturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturaMouseReleased
        int columnaSeleccionada = tablaFactura.getSelectedColumn();
        
        if ( columnaSeleccionada == 11 ) {
            filaseleccionada = tablaFactura.getSelectedRow();
            seleccion = true;
            String moneda = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 6));
            
            if ( "US$".equals(moneda) ) { // En dolares
                               
                String cadena = String.valueOf(txtTotFacDolar.getText());
                cadena = cadena.replace(",", "");
                double totalFacturadoDolares = Double.parseDouble(cadena);                

                cadena = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 7));
                cadena = cadena.replace(",", "");
                double facturadoDolar = Double.parseDouble(cadena);                
                
                cadena = String.valueOf(txttotalSaldoDolar.getText());
                cadena = cadena.replace(",", "");                
                double totalSaldoDolares = Double.parseDouble(cadena);
                
                cadena = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 9));
                cadena = cadena.replace(",", "");                                
                double saldoDolar = Double.parseDouble(cadena);

                boolean seleccionado = (boolean) tablaFactura.getValueAt(filaseleccionada, 11);

                String tipodoc = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 0));
                
                if ( seleccionado ) {
                    
                    if ("03".equals(tipodoc) ){  // NC Resta
                        totalFacturadoDolares -= facturadoDolar;
                        totalSaldoDolares -= saldoDolar;
                    } else {
                        totalFacturadoDolares += facturadoDolar;
                        totalSaldoDolares += saldoDolar;
                    }                                        
                  
                } else {

                    if ("03".equals(tipodoc) ){  // NC Resta                    
                        totalFacturadoDolares += facturadoDolar;
                        totalSaldoDolares += saldoDolar;
                    } else {
                        totalFacturadoDolares -= facturadoDolar;
                        totalSaldoDolares -= saldoDolar;
                    }                                                            

                }
                txtTotFacDolar.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoDolares)), 2));
                txttotalSaldoDolar.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoDolares)), 2));

            } else { // En soles

                
//                double totalFacturadoSoles = Double.parseDouble(txtTotalFacturado.getText());
//                double facturadoSol = Double.parseDouble(String.valueOf(tablaFactura.getValueAt(filaseleccionada, 7)));

//                double totalSaldoSoles = Double.parseDouble(txtTotalSaldo.getText());
//                double saldoSol = Double.parseDouble(String.valueOf(tablaFactura.getValueAt(filaseleccionada, 9)));

                
                String cadena = String.valueOf(txtTotalFacturado.getText());
                cadena = cadena.replace(",", "");
                double totalFacturadoSoles = Double.parseDouble(cadena);                

                cadena = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 7));
                cadena = cadena.replace(",", "");
                double facturadoSol = Double.parseDouble(cadena);                
                
                cadena = String.valueOf(txtTotalSaldo.getText());
                cadena = cadena.replace(",", "");                
                double totalSaldoSoles = Double.parseDouble(cadena);
                
                cadena = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 9));
                cadena = cadena.replace(",", "");                                
                double saldoSol = Double.parseDouble(cadena);                
                
                boolean seleccionado = (boolean) tablaFactura.getValueAt(filaseleccionada, 11);
                String tipodoc = String.valueOf(tablaFactura.getValueAt(filaseleccionada, 0));
                
                if ( seleccionado ) {
                    
                    if ("03".equals(tipodoc) ){  // NC Resta
                        totalFacturadoSoles -= facturadoSol;
                        totalSaldoSoles -= saldoSol;
                    } else {
                        totalFacturadoSoles += facturadoSol;
                        totalSaldoSoles += saldoSol;
                    }                                                            

                } else {
                    
                    if ("03".equals(tipodoc) ){  // NC Resta                    
                        totalFacturadoSoles += facturadoSol;
                        totalSaldoSoles += saldoSol;
                    } else {
                        totalFacturadoSoles -= facturadoSol;
                        totalSaldoSoles -= saldoSol;
                    }                                                                                
                    
                }
                txtTotalFacturado.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalFacturadoSoles)), 2));
                txtTotalSaldo.setText(formatearCeros(String.valueOf(util.Redondear2Decimales(totalSaldoSoles)), 2));
            }
        }
    }//GEN-LAST:event_tablaFacturaMouseReleased

    private void btnExportarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarTodoActionPerformed
        Servicio_Excel servicio_Excel = new Servicio_Excel(tablaFactura, this);
        String nombre = "Cuenta Corriente Facturación";
        List<String> lstCabCCFact = new ArrayList<String>(Arrays.asList(
             "Número", "Documento", "Nro. Serie", "Fecha", "RUC", 
             "Nombre Cliente", "Moneda", "Facturado", "Pagado", "Saldo", "Banco"
        ));
        servicio_Excel.exportarExcel(nombre, lstCabCCFact, 2);
    }//GEN-LAST:event_btnExportarTodoActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        ArrayList lista = SetListaSeleccionados();
        Map<String, Object> parametros = SetParametros();
        
        try {
            Control control = new ControlDAO().Obtener_Objeto(1);
//            String rutaBDCtaCteFactura = control.getRutaprogramas();
//            String rutaExcel = rutaBDCtaCteFactura.replace("/", "\\");
            
            // adaptar exportacion de excel como LETRAS, no facturas
            generarExcelCtaCteFacturasSeleccionadas("Cta.Cte.Facturas", control, parametros, lista);
//            nombreExcelCtaCteFacturas = rutaExcel + "\\" + nombreExcelCtaCteFacturas;
//            new ImpresionExcel().imprimirEliminarExcel(nombreExcelCtaCteFacturas);
//            Sv_Impresion.exporta(0, lista, parametros, "reportes/Impresion_Factura.pdf");
            
        } catch ( Exception ex ) {
            Logger.getLogger(FREP042.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    // Genera excel para SOLO exportar REPORTE DE FACTURAS
    private void generarExcelCtaCteFacturasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList lista)
                                                         throws FileNotFoundException, BiffException, IOException, WriteException {
        new Servicio_Excel(tablaFactura, this).exportarExcelCabDetCtaCteFacturasSeleccionadas(nombreHoja, control, parametros, lista);
    }
    
    private ArrayList SetListaSeleccionados() {
        ArrayList<ListaFacturas> lista = new ArrayList<>();
        
        for ( int i = 0; i < model.getRowCount(); i++ ) {
            
            if ( (boolean)model.getValueAt(i, 11) == true ) {
                String tipDoc = tipoDoc(String.valueOf(model.getValueAt(i, 0)));
                String numDoc = String.valueOf(model.getValueAt(i, 1));
                
                String nroSerie = String.valueOf(model.getValueAt(i, 2));
                
                String fecha = String.valueOf(model.getValueAt(i, 3));
                String ruc = String.valueOf(model.getValueAt(i, 4));
                String cliente = String.valueOf(model.getValueAt(i, 5));
                String facturado = String.valueOf(model.getValueAt(i, 6)) + " " + String.valueOf(model.getValueAt(i, 7));
                
                String pagado = String.valueOf(model.getValueAt(i, 6)) + " " + String.valueOf(model.getValueAt(i, 8));
                
                String saldo = String.valueOf(model.getValueAt(i, 6)) + " " + String.valueOf(model.getValueAt(i, 9));
                String banco = String.valueOf(model.getValueAt(i, 10));

                lista.add(new ListaFacturas(tipDoc, numDoc, nroSerie, fecha, ruc, cliente, facturado, pagado, saldo, banco));
            }
        }
        return lista;
    }
    
    private Map SetParametros() {
        Servicio_Control sc = new Servicio_Control();
        Map<String, Object> mapa = new HashMap<>();
        String titulo = TextoFacturas.getText();
        String empresa = sc.listar_control().get(0).getNombrempresa();        
        mapa.put("titulo", titulo);
        mapa.put("empresa",empresa);
        
        String totFactSoles = txtTotalFacturado.getText();
        String totSaldoSoles = txtTotalSaldo.getText();
        String totFactDolares = txtTotFacDolar.getText();
        String totSaldoDolares = txttotalSaldoDolar.getText();
        
        mapa.put("totFactSoles", totFactSoles);
        mapa.put("totSaldoSoles", totSaldoSoles);
        mapa.put("totFactDolar", totFactDolares);
        mapa.put("totSaldoDolar", totSaldoDolares);
//        System.out.println("titulo:" + mapa.get("titulo"));
//        System.out.println("empresa:" + mapa.get("empresa"));
//        System.out.println("totFactSoles:" + mapa.get("totFactSoles"));
//        System.out.println("totSaldoSoles:" + mapa.get("totSaldoSoles"));    
//        System.out.println("totFactDolar:" + mapa.get("totFactDolar"));
//        System.out.println("totSaldoDolar:" + mapa.get("totSaldoDolar"));
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
                tipo = "Entrada no valida";
                break;
        }
        return tipo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TextoFacturas;
    public javax.swing.JButton botGeneral;
    public javax.swing.JButton botonDetalle;
    public javax.swing.JButton botonImprimir;
    public javax.swing.JButton botonPagar;
    public javax.swing.JButton botonPendientes;
    public javax.swing.JButton botonSalir;
    public javax.swing.JButton boton_Filtrar;
    public javax.swing.JButton boton_restaurar;
    public javax.swing.JButton boton_seleccionartodo;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnExportarTodo;
    public javax.swing.JButton btn_1cliente;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelCobrarFac;
    public javax.swing.JLabel t1;
    public javax.swing.JLabel t2;
    public javax.swing.JLabel t3;
    public javax.swing.JLabel t4;
    public javax.swing.JTable tablaFactura;
    public javax.swing.JLabel txtTotFacDolar;
    public javax.swing.JLabel txtTotalFacturado;
    public javax.swing.JLabel txtTotalSaldo;
    public javax.swing.JLabel txttotalSaldoDolar;
    // End of variables declaration//GEN-END:variables
    public javax.swing.JTable tb_consulta;
    
    private void limpiar() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        cont_Fac_C = new BigDecimal(0.00);
        cont_Sal_C = new BigDecimal(0.00);
        cont_Fac_D = new BigDecimal(0.00);
        cont_Sal_D = new BigDecimal(0.00);

        cont_Fac_SG = new BigDecimal(0.00);
        cont_Sal_SG = new BigDecimal(0.00);
        cont_Fac_DG = new BigDecimal(0.00);
        cont_Sal_DG = new BigDecimal(0.00);

    }
    
    public String getRolElegido() {
        return rolElegido;
    }

    public void setRolElegido(String rolElegido) {
        this.rolElegido = rolElegido;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    private String substr(String valorMonetario, int j, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private FREP042() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private FREP042(String valorMonetario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void tx_busqActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       
    
}