package Presentacion.FacturacionElectronica;

import static Entidades.Otros.Constante.COLUMNA_ESTADO_TXT;
import Entidades.Sistema;
import Mantenimiento.ControlDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Presentacion.NewMain;
import Servicios.TipoMensaje;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Servicios.Servicio_Cabeces;

/**
 *
 * @author Ledis Rivera Changra
 * @date Viernes 20/07/2018 (20:24 p.m.)
 */
public class UI_Generar_DocElectronicos extends javax.swing.JFrame {

    private TipoMensaje tm;
    
    private DefaultTableModel modelo;
    private Servicio_Cabeces servicioCab;
    private Date fechaInicio;
    private Date fechaFin;
    
    private String fecha_inicio;
    private String fecha_fin;
    
    private List<CabDocElectronico> lstTodos;
    
    private Sistema sistNC;
    private Sistema sistND;
    private Sistema sistFact;
    private Sistema sistBol;
    
//    private BigDecimal cont_Fac_C;
//    private BigDecimal cont_Fac_D;
    private Servicio_Cabeces sc;
    
    public UI_Generar_DocElectronicos(Date inicio, Date fin) {
        sc = new Servicio_Cabeces();
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        tm = new TipoMensaje();
//        obtenerTipDocSunat();
        
        modelo = (DefaultTableModel) tablaDocElect.getModel();
        servicioCab = new Servicio_Cabeces();
        
        fechaInicio = inicio;
        fechaFin = fin;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        fecha_inicio = sdf.format(inicio);
        fecha_fin = sdf.format(fin);
        
        setFechas();
        
        String valorInicial = "0.00";
        txtTotDocElectDolar.setText(valorInicial);
        txtTotDocElectrSoles.setText(valorInicial);
        
        lstTodos = servicioCab.ObtenerDocumentosElectronicos(this, fecha_inicio, fecha_fin);
        cargarDocElectronicos(lstTodos);
        ocultarEstadoTxt();
        alinearColumnaDerecha();
        //ajustarAnchoColumnas();
    }
    
    private void obtenerTipDocSunat() {
        sistNC = new SistemaDAO().getActualNroSerie("03");
        System.out.println("NC:" + sistNC.getCodDocElect());
        
        sistND = new SistemaDAO().getActualNroSerie("04");
        System.out.println("ND:" + sistND.getCodDocElect());

        sistFact = new SistemaDAO().getActualNroSerie("01");
        System.out.println("Fact:" + sistFact.getCodDocElect());
        
        sistBol = new SistemaDAO().getActualNroSerie("02");
        System.out.println("Bol:" + sistBol.getCodDocElect());
    }
    
    private void ocultarEstadoTxt() {
        tablaDocElect.getColumnModel().getColumn(COLUMNA_ESTADO_TXT).setMaxWidth(0);
        tablaDocElect.getColumnModel().getColumn(COLUMNA_ESTADO_TXT).setMinWidth(0);
        tablaDocElect.getColumnModel().getColumn(COLUMNA_ESTADO_TXT).setPreferredWidth(0);
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaDocElect.getColumnModel().getColumn(8).setCellRenderer(tcr);  // Valor Venta
        tablaDocElect.getColumnModel().getColumn(9).setCellRenderer(tcr);  // IGV
        tablaDocElect.getColumnModel().getColumn(10).setCellRenderer(tcr); // Total
    }
    
    public final void ajustarAnchoColumnas() {
        JViewport scroll = (JViewport) tablaDocElect.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tablaDocElect.getColumnModel();
        TableColumn columnaTabla;

        for (int i = 0; i < tablaDocElect.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0: // N°
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 1: // Documento
                    anchoColumna = (21 * ancho) / 100;
                    break;
                case 2: // Nro. Serie
                    anchoColumna = (19 * ancho) / 100;
                    break;
                case 3: // Nro. Doc
                    anchoColumna = (19 * ancho) / 100;
                    break;
                case 4: // Fecha Emisión
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 5: // RUC
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 6: // Cliente
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 7: // Moneda
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 8: // Valor Venta
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 9: // IGV
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 10: // Total
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 11: // Fecha Envío
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 12: // Días Vencidos
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 13: // EstadoTxt
                    anchoColumna = (7 * ancho) / 100;
                    break;    
                case 14: // TipDocumentoElectronico
                    anchoColumna = (7 * ancho) / 100;
                    break;
            }
            columnaTabla.setMaxWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumna);
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    
    private void setFechas() {
        dc_FechaDesde.setDate(fechaInicio);
        dc_FechaHasta.setDate(fechaFin);
        
        dc_FechaDesde.setEnabled(false);
        dc_FechaHasta.setEnabled(false);
    }
    
    private String convertirTipDocBDSiar_Sunat(String tipDoc) {
        System.out.println("convertir:" + tipDoc);
        if ( "01".equals(tipDoc) ) {
            tipDoc = sistFact.getCodDocElect();
            System.out.println("resultado01(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "02".equals(tipDoc) ) {
            tipDoc = sistBol.getCodDocElect();
            System.out.println("resultado02(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "03".equals(tipDoc) ) {
            tipDoc = sistNC.getCodDocElect();
            System.out.println("resultado03(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "04".equals(tipDoc) ) {
            tipDoc = sistND.getCodDocElect();
            System.out.println("resultado04(after convertion):" + tipDoc);
            return tipDoc;
            
        } else {
            return null;
        }
//        return tipDoc;
    }
    
    private void cargarDocElectronicos(List<CabDocElectronico> lstTodos) {
        if ( lstTodos != null ) {
            double totalTotDocElectrDolares = Double.parseDouble(txtTotDocElectDolar.getText());
            double totalTotDocElectrSoles = Double.parseDouble(txtTotDocElectrSoles.getText());
            
            Iterator it = lstTodos.iterator();
            int nro = 0;

            while ( it.hasNext() ) {
                CabDocElectronico c = (CabDocElectronico) it.next();
                Object[] row = new Object[15]; // 14

                // Columna #1: Nº
                row[0] = nro + 1;

                // Columna #2: Documento
//                String tipDoc = convertirTipDocBDSiar_Sunat(c.getTip_doc());
//                System.out.println("c.getTip_doc(BD):" + c.getTip_doc());
//                System.out.println("tipDoc(Sunat):" + tipDoc);
//                row[1] = tipDoc;
                row[1] = c.getTip_doc();

                // Columna #3: Nro. Serie
                row[2] = c.getNro_serie();

                // Columna #4: Nro. Doc
                row[3] = c.getNro_doc();
                //System.out.println("Nº doc:" + c.getNro_doc());

                // Columna #5: Fecha Emisión
                row[4] = c.getFec_ed();

                // Columna #6: RUC
                row[5] = c.getNro_doc_adquiriente();

                // Columna #7: Cliente
                row[6] = c.getApamno_razon_social_adquiriente();

                // Columna #8: Moneda
                row[7] = c.getMoneda();

                // Columna #9: Valor Venta
                row[8] = c.getTotal_operaciones_grav();

                // Columna #10: IGV
                row[9] = c.getMonto_total_igv();

                // Columna #11: Total
                row[10] = c.getMonto_pagar();

                // Columna #12: Fecha Envío
                row[11] = ( "null".equals(c.getFechaEnvio()) ? "" : c.getFechaEnvio() );

                // Columna #13: Días Vencidos
                row[12] = c.getDiasVencidosGenTxt();

                // Columna #14: Estado de generacion del txt en BD (Cabeces).
                // 1 = Generado, 0 = No Generado
                row[13] = c.getEstadoTxtGenerado();
                
                row[14] = c.getTipo_doc_electronico();

                modelo.addRow(row);

                String moneda = c.getMoneda();

                if ( "US$".equals(moneda) ) { // En dolares
                    //cont_Fac_D = cont_Fac_D.add(BigDecimal.valueOf(Double.parseDouble(String.valueOf(tablaDocElect.getValueAt(nro, 10)))));
                    double totalDolar = Double.parseDouble(String.valueOf(tablaDocElect.getValueAt(nro, 10)));
                    totalTotDocElectrDolares += totalDolar;

                } else {
                    //cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(String.valueOf(tablaDocElect.getValueAt(nro, 10)))));
                    double totalSol = Double.parseDouble(String.valueOf(tablaDocElect.getValueAt(nro, 10)));
                    totalTotDocElectrSoles += totalSol;
                }
                nro++;
                System.out.println("-------------------------");
            }
            txtTotDocElectrSoles.setText(String.valueOf(totalTotDocElectrSoles));
            txtTotDocElectDolar.setText(String.valueOf(totalTotDocElectrDolares));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDocElect = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dc_FechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dc_FechaHasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtTotDocElectDolar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();
        botonPendientes = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotDocElectrSoles = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tablaDocElect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Documento", "Nro. Serie", "Nro. Doc", "Fecha Emisión", "RUC", "Cliente", "Moneda", "Valor Venta", "IGV", "Total", "Fecha Envío", "Días Vencidos", "EstadoTxt", "TipDocElect"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDocElect.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaDocElect);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLANILLA DE DOCUMENTOS ELECTRONICOS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHAS"));

        jLabel4.setText("Desde");

        jLabel5.setText("Hasta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dc_FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dc_FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dc_FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(dc_FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(50, 50, 50))
        );

        txtTotDocElectDolar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTotDocElectDolar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL EN DOLARES : ");

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        btnGeneral.setText("General");
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        botonPendientes.setText("Pendientes");
        botonPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPendientesActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL EN SOLES : ");

        txtTotDocElectrSoles.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTotDocElectrSoles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(39, 39, 39)
                        .addComponent(txtTotDocElectrSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(txtTotDocElectDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 648, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtTotDocElectDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotDocElectrSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed

//        seleccion = false;
//        limpiar();
//        model.getDataVector().removeAllElements();
//        model.fireTableDataChanged();
//        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
//        TextoFacturas.setText("TODAS LAS FACTURAS CANCELADAS Y POR COBRAR AL " + fechaSistema());
//
//        Servicio_CobrarFacturas sc = new Servicio_CobrarFacturas();
//
//        List li1 = sc.Listar_Cuentas_General();
//        int tam = li1.size();
//
//        if ( tam != 0 ) {
//            for ( int i = 0; i < tam; i++ ) {
//                Object[] a = (Object[]) li1.get(i);
//
//                if ( a[10].equals("US$") ) {
//                    cont_Fac_DG = cont_Fac_DG.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
//                    cont_Sal_DG = cont_Sal_DG.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//
//                } else {
//                    cont_Fac_SG = cont_Fac_SG.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
//                    cont_Sal_SG = cont_Sal_SG.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//                }
//
//                String estado = String.valueOf(a[11]);
//                //                System.out.println("estado(General):" + estado);
//                String columnaCliente = String.valueOf(a[4]);
//                String facturado = "0.00", pagado = "0.00", saldo = "0.00";
//
//                if ( "ANULADO".equals(estado) ) {
//                    columnaCliente = "(ANULADO) " + a[4];
//
//                } else {
//                    facturado = formatearMonetario(String.valueOf(a[5]), 2);
//                    pagado = formatearMonetario(String.valueOf(a[6]), 2);
//                    saldo = formatearMonetario(String.valueOf(a[7]), 2);
//                }
//                Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, a[8], false};
//                //                Object[] fila = {a[0], a[1], a[2], a[3], a[9], a[4], a[10], a[5], a[6], a[7], a[8], false};
//                modelo.addRow(fila);
//            }
//            String valorInicial = "0.00";
//            txtTotalFacturado.setText(valorInicial);
//            txtTotFacDolar.setText(valorInicial);
//
//        } else {
//            JOptionPane.showMessageDialog(null, "No existe registros");
//        }
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void botonPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPendientesActionPerformed
        limpiar();
        modelo.getDataVector().removeAllElements();
        modelo.fireTableDataChanged();
        
        lstTodos = servicioCab.ObtenerDocumentosElectronicos(this, fecha_inicio, fecha_fin);
        cargarDocElectronicos(lstTodos);
        //iniciarTabla(tablaDocElect);
    }//GEN-LAST:event_botonPendientesActionPerformed

//    public DefaultTableModel iniciarTabla(JTable tabla) {
//        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
//        lstTodos = servicioCab.ObtenerDocumentosElectronicos(this, fecha_inicio, fecha_fin);
//        int tam = lstTodos.size();
//        
//        if ( tam != 0 ) {
//            for ( int i = 0; i < tam; i++ ) {
//                Object[] rowData = new Object[14];
//                CabDocElectronico cde = (CabDocElectronico) lstTodos.get(i);
//                
//                if ( a[10].equals("US$") ) {
//                    cont_Fac_D = cont_Fac_D.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
//                    cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//                    
//                } else {
//                    cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
//                    cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//                }
//                
//                String estadoFactura = String.valueOf(a[11]);
//                
//                if ( !"ANULADO".equals(estadoFactura) ) {
//                    String facturado = formatearMonetario(String.valueOf(a[5]), 2);
//                    String pagado = formatearMonetario(String.valueOf(a[6]), 2);
//                    String saldo = formatearMonetario(String.valueOf(a[7]), 2);
//                    
//                    
//                    modelo.addRow(rowData);
//                }
//            }
//            String valorInicial = "0.00";
//            txtTotalFacturado.setText(valorInicial);
//            txtTotalSaldo.setText(valorInicial);
//            txtTotFacDolar.setText(valorInicial);
//            txttotalSaldoDolar.setText(valorInicial);
//
//        } else {
//            JOptionPane.showMessageDialog(null, "No existen registros");
//        }
//        return modelo;
//    }
    
    private void limpiar() {
        txtTotDocElectrSoles.setText("0.00");
        txtTotDocElectDolar.setText("0.00");
    }
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        generarTxtDocElectr();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void generarTxtDocElectr() {
        try {
            BufferedWriter bw = null;
            EmpresaDocElectronico ede = new ControlDAO().obtenerEmpresaDocElectronico();
            String rutaDocElectr = ede.getRutaDocElectronicos();
            //String ruta = "X:\\txtDocElectr\\archivo.txt";
            
            String ruc_emisor                   = ede.getRuc_emisor();
            String tip_doc_emisor               = ede.getTip_doc_emisor();
            String apamno_razon_social_emisor   = ede.getApamno_razon_social_emisor();
            String ubigeo_emisor                = ede.getUbigeo_emisor();
            String direccion_emisor             = ede.getDireccion_emisor();
            String departamento_emisor          = ede.getDepartamento_emisor();
            String provincia_emisor             = ede.getProvincia_emisor();
            String distrito_emisor              = ede.getDistrito_emisor();
            
            for ( CabDocElectronico cde : lstTodos ) {
                StringBuilder cadena = new StringBuilder();
                
                // 1. Datos de la empresa (MyD)
                String cadTxt =  "ACTION:Registrar~FEC_ED:" + cde.getFec_ed() +
                        "|RUC_EMISOR:" + ruc_emisor +
                        "|TIP_DOC_EMISOR:" + tip_doc_emisor + 
                        "|APAMNO_RAZON_SOCIAL_EMISOR:" + apamno_razon_social_emisor +
                        "|UBIGEO_EMISOR:" + ubigeo_emisor + 
                        "|DIRECCION_EMISOR:" + direccion_emisor +
                        "|URBANIZACION_EMISOR:" + ede.getUrbanizacion_emisor() +
                        "|DEPARTAMENTO_EMISOR:" + departamento_emisor +
                        "|PROVINCIA_EMISOR:" + provincia_emisor +
                        "|DISTRITO_EMISOR:" + distrito_emisor +
                        "|CODIGO_PAIS_EMISOR:" + ede.getCodigo_pais_emisor() +
                        "|NOMBRE_COMERCIAL_EMISOR:" + ede.getNombre_comercial_emisor();
                
                // 2. Datos de la Cabecera (del documento electrónico)
                String glosaMoneda = ("US$".equals(cde.getMoneda()) ? "USD" : "PEN");
                String correo = (!"".equals(cde.getCorreo_cliente()) ? cde.getCorreo_cliente() : "-");
                
                String nns = cde.getNro_serie();
                //String nns = convertir(cde.getNro_serie());
                System.out.println("(GUID)nns:" + nns);
                String newNroSerie = "|NRO_SERIE:" + nns;
                
                String primeraLetrDoc = nns.substring(0,1);
                
                String guia_remision  = cde.getGuia_remision();
                String direccion_cliente = cde.getDireccion_cliente();
                String gr = "";
                
                if ( "F".equals(primeraLetrDoc) ) {
                    gr = "|IA_49:" + guia_remision;
                }
                
//                cadTxt = cadTxt + "|TIP_DOC:" + cde.getTip_doc() +
                cadTxt = cadTxt + "|TIP_DOC:" + cde.getTipo_doc_electronico() +
                        newNroSerie +
                        "|NRO_DOC:" + cde.getNro_doc() +
                        "|NRO_DOC_ADQUIRIENTE:" + cde.getNro_doc_adquiriente() +
                        "|TIP_DOC_ADQUIRIENTE:" + cde.getTip_doc_adquiriente() +
                        "|APAMNO_RAZON_SOCIAL_ADQUIRIENTE:" + cde.getApamno_razon_social_adquiriente() +
                        "|MONEDA:" + glosaMoneda +
                        "|TOTAL_OPERACIONES_GRAV:" + cde.getTotal_operaciones_grav() +
                        "|TOTAL_OPERACIONES_INF:" + cde.getTotal_operaciones_inf() +
                        "|TOTAL_OPERACIONES_EXONERADAS:" + cde.getTotal_operaciones_exoneradas() +
                        "|TOTAL_OPERACIONES_EXPORTACION:" + cde.getTotal_operaciones_exportacion() +
                        "|MONTO_TOTAL_OPERACIONES_GRAT:" + cde.getMonto_total_operaciones_grat() +
                        "|MONTO_DESCUENTOS_GLOBALES:" + cde.getMonto_descuentos_globales() +
                        "|MONTO_TOTAL_IGV:" + cde.getMonto_total_igv() +
                        "|MONTO_PAGAR:" + cde.getMonto_pagar() +
                        "|MONTO_PERCEPCION:" + cde.getMonto_percepcion() +
                        "|MONTO_TOTAL_PERCEP:" + cde.getMonto_total_percep() +
                        "|LEYENDA:" + cde.getLeyenda() +
                        "|CORREO_CLIENTE:" + correo +
                        "|IA_50:" + direccion_cliente +
                        gr;
                
                // 3. Datos del Detalle (del documento electronico)
                List<DetDocElectronico> lstDDE = cde.getLstDetDocElect();
                if ( lstDDE != null ) {
                    // Solo agregar este simbolo para el 1er item (detalle)
                    
                    int i = 0;
                    
                    for ( DetDocElectronico dde : lstDDE ) {
                        if ( i == 0 ) {
                            cadTxt = cadTxt + "~";
                            
                        } else {
                            cadTxt = cadTxt + "^";
                        }
                        cadTxt = cadTxt + "ID_ITEM:" + dde.getId_item() +
                                "|COD_PROD_SERV_ITEM:" + dde.getCod_prod_serv_item() +
                                "|DESRIP_ITEM:" + dde.getDesrip_item() +
                                "|COD_UNIDAD_MEDIDA_ITEM:" + dde.getCod_unidad_medida_item() +
                                "|INDICADOR_PS_ITEM:" + dde.getIndicador_ps_item() +
                                "|INDICADOR_TRANS_GRAT:" + dde.getIndicador_trans_grat() +
                                "|INDICADOR_AFECTACION_ITEM:" + dde.getIndicador_afectacion_item() +
                                
                                "|VALOR_VENTA_UNITARIA:" + dde.getValor_venta_unitaria() +
                                "|PRECIO_VENTA_UNITARIO_ITEM:" + dde.getPrecio_venta_unitario_item() +
                                "|CANTIDAD_ITEM:" + dde.getCantidad_item() +
                                "|DESCUENTO_ITEM:" + dde.getDescuento_item() +
                                "|VALOR_ITEM:" + dde.getValor_item() +
                                "|IGV_TOTAL_ITEM:" + dde.getIgv_total_item() +
                                "|TOTAL_ITEM:" + dde.getTotal_item();
                        i++;
                    }
                    cadena.append(cadTxt);
                    System.out.println("cadTxt:" + cadTxt);
                    
                    /* Formato del nombre del Txt:
                    [RUC_EMPRESA]+ “-”+ [TIPO_DOCUMENTO]+ “-”+ [NUMERO_SERIE] + “-”+ [NUMERO_DOC]
                    */
                    
                    String prefijo = cde.getTip_doc();
                    System.out.println("prefijo(BD):" + prefijo);
                    //convertirTipDocBDSiar_Sunat()
                    
                    String nroSer = cde.getNro_serie();
                    String prefijo_NroSerie = formatearNroSerie(prefijo, nroSer);
                    System.out.println("tip_doc:" + cde.getTip_doc());
                    System.out.println("prefijo_NroSerie:" + prefijo_NroSerie);
                    System.out.println("cde.getNro_doc:" + cde.getNro_doc());
                    String nuevoNroSerie= prefijo_NroSerie.substring(2, prefijo_NroSerie.length());
                    System.out.println("(LED)nuevoNroSerie::" + nuevoNroSerie);
                    System.out.println("(LEDIS)getTipo_doc_electronico:"+ cde.getTipo_doc_electronico());
                    
                    // MODIF #1
                    String nombreTxt = ruc_emisor + "-"
//                            + cde.getTip_doc() + "-"
                            + cde.getTipo_doc_electronico() + "-"
                            + nuevoNroSerie + "-"
                            + cde.getNro_doc();
                    
                    System.out.println("(LED)nombreTxt:" + nombreTxt);
                    String rutaCompleta = rutaDocElectr + "\\" + nombreTxt + ".txt";
                    System.out.println("rutaCompleta:" + rutaCompleta);
                    File archivo = new File(rutaCompleta);
                    
                    // 4. Crear archivo txt (para Facturacion Electronica)
                    try {
                        if ( !archivo.exists() ) {
                            bw = new BufferedWriter(new FileWriter(archivo));
                            String txt = cadena.toString();
                            bw.write(txt);
                            bw.flush();
                            bw.close();
                        }
                        
                    } catch ( IOException ex ) {
                        System.out.println("Excepcion(escribir sobre archivo):" + ex.getMessage());
                        Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            System.out.println("lstTodos:" + lstTodos);
            System.out.println("lstTodos != null :" + (lstTodos != null));
            System.out.println("FIN...");        
            // Actualiza el estado de haber generado la factura electrónica:
            /*
            if ( sc.ActualizarTodasCabecera(lstTodos) ) {
                System.out.println("Exito...");
                tm.Mensaje(tm.EXITO_GENERAR_TXT);
                
            } else {
                System.out.println("Error al actualizar Base de Datos.");
                tm.Error("ERROR, NO SE PUDO ACTUALIZAR FECHAS EN QUE SE GENERARON TXT DE FACTURACION ELECTRONICA");
            }
            */
            
        } catch (Exception ex) {
            Logger.getLogger(UI_Generar_DocElectronicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    private String convertir(String nro_serie) {
        String prefijo = nro_serie.substring(0, 2);
        System.out.println("(LED)prefijo::" + prefijo);
        if ( "FC".equals(prefijo) ) {
            return "NC" + nro_serie.substring(2, 4);
            
        } else if ( "FD".equals(prefijo) ) {
            return "ND" + nro_serie.substring(2, 4);
            
        } else {
            return nro_serie;
        }
    }*/
    
    private String formatearNroSerie(String prefijo, String nroSerie) {
        return prefijo + formatearNroSer(prefijo, nroSerie);
        // String nroDoc = iuf.tx_doc.getText(); // N° Factura
        //guia.setNroFactura(tipDoc + formatearNroSerie(nroSerie) + "-" + nroDoc);
    }
    
    private String formatearNroSer(String prefijo, String serie) {
        String nroSerie = "";
        int largo = 3 - serie.length();
        
        if ( prefijo.length() == 2 ) {
            largo = 2 - serie.length();
        }
        
        for ( int i = 0; i < largo; i++ ) {
            nroSerie = nroSerie + "0";
        }
        nroSerie = nroSerie + serie;
        return nroSerie;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonPendientes;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnGeneral;
    private com.toedter.calendar.JDateChooser dc_FechaDesde;
    private com.toedter.calendar.JDateChooser dc_FechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDocElect;
    public javax.swing.JLabel txtTotDocElectDolar;
    public javax.swing.JLabel txtTotDocElectrSoles;
    // End of variables declaration//GEN-END:variables
}