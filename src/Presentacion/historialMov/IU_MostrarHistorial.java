package Presentacion.historialMov;

import Entidades.Detallees;
import static Entidades.Otros.Constante.VISUALIZAR_POR_FECHA;
import static Entidades.Otros.Constante.VISUALIZAR_TODO;
import Entidades.Repuestos;
import Mantenimiento.ControlDAO;
import Servicios.HistorialMov.Servicio_Historial;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Util;
import Servicios.facturacion.Servicio_Impresion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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

public class IU_MostrarHistorial extends javax.swing.JFrame {

    Date fechaInicio;
    Date fechaFin;
    Repuestos repuesto;
    Servicio_Historial sh;
    List<Detallees> lista;
    DefaultTableModel dtm;
    int totalIngreso;
    int totalSalida;
    Util util;

    public IU_MostrarHistorial(Date Inicio, Date Fin, Repuestos r) {
        initComponents();
        repuesto = r;
        fechaInicio = Inicio;
        fechaFin = Fin;
        SetFechas();
        InitOtherComponents();
        Obtener_Lista(VISUALIZAR_POR_FECHA); // Obtener movimientos entre fechas
        Listar();
    }

    public IU_MostrarHistorial(Repuestos r) {
        initComponents();
        repuesto = r;
        InitOtherComponents();
        Obtener_Lista(VISUALIZAR_TODO);
        Listar();
    }

    private void InitOtherComponents() {
        util = new Util();
        dtm = (DefaultTableModel) tb_historial.getModel();
        sh = new Servicio_Historial();
        setLocationRelativeTo(null);
        setVisible(true);
        SetRepuesto();
        setAnchoColumnas();
        lb_hoy.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
        
        // Ajustando a 2 decimales
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_historial.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_historial.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_historial.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_historial.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (40 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_historial = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_ingresos = new javax.swing.JTextField();
        tx_salidas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_nroParte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx_descripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tx_ubicAlmacen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tx_StockActual = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tx_desde = new javax.swing.JTextField();
        tx_hasta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lb_hoy = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bt_imprimir = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Historia de Movimiento de Repuesto"));

        tb_historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Fecha Mov.", "Tipo Doc", "Ingresos", "Salidas", "Total", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_historial);

        jLabel2.setText("Total Ingresos");

        jLabel3.setText("Total Salidas");

        tx_ingresos.setEnabled(false);

        tx_salidas.setEnabled(false);

        jLabel4.setText("Nro Parte");

        tx_nroParte.setEnabled(false);

        jLabel5.setText("Descripcion");

        tx_descripcion.setEnabled(false);

        jLabel6.setText("Ubicacion Almacen");

        tx_ubicAlmacen.setEnabled(false);

        jLabel7.setText("Stock Actual");

        tx_StockActual.setEnabled(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha"));

        tx_desde.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_desde.setEnabled(false);

        tx_hasta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_hasta.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(tx_desde, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(tx_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tx_desde)
                .addComponent(tx_hasta)
                .addComponent(jLabel1))
        );

        lb_hoy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setText(" AL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_ubicAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(tx_StockActual, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_hoy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 64, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_ubicAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tx_StockActual)
                        .addComponent(lb_hoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_salidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("HISTORIAL DE MOVIMIENTO DE REPUESTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(347, 347, 347)
                .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        HashMap parametros = SetParametros();
        ArrayList listado = SetLista();
        
        Servicio_Control sc = new Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();
        String url1 = rutaReportes + "\\plantillas\\HistorialMov.jrxml";
        String url2 = rutaReportes + "\\plantillas\\HistorialMov.jasper";
//        String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//        Servicio_Impresion.exporta(16, lista,parametros,"plantillas/HistorialMov.pdf");
        try {
            convertPedidoToPDF(url1, url2, parametros, listado);

        } catch ( Exception ex ) {
            System.out.println("Error(mostrarHistorial):" + ex.getMessage());
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

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
    
    private ArrayList SetLista() {
        ArrayList<HistorialMovimiento> listah = new ArrayList<>();
        
        for ( int i = 0; i < tb_historial.getRowCount(); i++ ) {
            String nroDoc = String.valueOf(dtm.getValueAt(i, 0));
            String fechaMov = String.valueOf(dtm.getValueAt(i, 1));
            String tipoDoc = String.valueOf(dtm.getValueAt(i, 2));
            String ingresos = String.valueOf(dtm.getValueAt(i, 3));
            String salidas = String.valueOf(dtm.getValueAt(i, 4));
            String tot =String.valueOf(dtm.getValueAt(i, 5));
//            System.out.println("tot:" + tot);
            String total = formatearMonetario(tot, 2);
//            System.out.println("total:" + total);
            String detalle = String.valueOf(dtm.getValueAt(i, 6));
            listah.add(new HistorialMovimiento(nroDoc, fechaMov, tipoDoc, ingresos, salidas, total,  detalle));
        }
        return listah;
    }

    private HashMap SetParametros() {
        HashMap mapa = new HashMap<>();
        mapa.put("fechadesde", tx_desde.getText());
        mapa.put("fechahasta", tx_hasta.getText());
        mapa.put("nroparte", tx_nroParte.getText());
        mapa.put("descripcion", tx_descripcion.getText());
        mapa.put("ubicacion", tx_ubicAlmacen.getText());
        mapa.put("stock", tx_StockActual.getText());
        mapa.put("stockal", lb_hoy.getText());
        mapa.put("totingresos", tx_ingresos.getText());
        mapa.put("totsalidas", tx_salidas.getText());

        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        return mapa;
    }

    private void SetFechas() {
        tx_desde.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio));
        
        if ( fechaFin != null ) {
            tx_hasta.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaFin));
            
        } else {
            fechaFin = GregorianCalendar.getInstance().getTime();
            tx_hasta.setText(new SimpleDateFormat("dd/MM/yyyy").format(fechaFin));
        }
    }

    private void SetRepuesto() {
//        System.out.println("repuesto:" + repuesto);
        tx_nroParte.setText(repuesto.getCodrepuesto());
        tx_descripcion.setText(repuesto.getDescripcion());
        tx_ubicAlmacen.setText(repuesto.getUbicalmacen());
        tx_StockActual.setText(String.valueOf(repuesto.getStock()));
    }

    private void Obtener_Lista(int i) {
        if ( i == VISUALIZAR_TODO ) {
            lista = sh.getLista(repuesto.getId().getIdrepuesto());
            
        } else { // con fechas
            lista = sh.getListaxFechas(repuesto.getId().getIdrepuesto(), fechaInicio, fechaFin);
        }
    }

    private void Listar() {
        totalIngreso = totalSalida = 0;
        int Stock_a = repuesto.getStock();
        String TipoOp;
        
        for ( Detallees d : lista ) {
            Object[] rows = new Object[7];
            
//            rows[0] = ("IC".equals(d.getOperaciones().getCodigooperacion()) ? d.getNroingreso() : d.getCabeces().getId().getNrodocumento());
            
//          rows[0] = d.getCabeces() == null ? d.getNroingreso() : d.getCabeces().getId().getNrodocumento();
            rows[0] = d.getCabeces() == null ? d.getNroingreso() : ( d.getNroingreso() == null ? d.getCabeces().getId().getNrodocumento() : d.getNroingreso());
            rows[1] = d.getFecha();
            
            TipoOp = d.getOperaciones().getCodigooperacion();
            
            if ( TipoOp.toCharArray()[0] == 'S' ) {
                rows[3] = 0;
                rows[4] = d.getCantentregada();

                if ( fechaFin == null || d.getFecha().compareTo(fechaFin) <= 0 ) {
                    totalSalida += d.getCantentregada();
                }
                Stock_a += d.getCantentregada();
            } else {
                rows[3] = d.getCantentregada();
                rows[4] = 0;

                if ( fechaFin == null || d.getFecha().compareTo(fechaFin) <= 0 ) {
                    totalIngreso += d.getCantentregada();
                }
                Stock_a -= d.getCantentregada();
            }   
                
            if ( d.getCabeces() == null ) {
                rows[2] = d.getOperaciones().getDescripcion();
                
            } else {
                if ( d.getNroingreso() == null ) {
                    rows[2] = sh.getTipoOperacion(d.getCabeces().getId().getTipodoc());
                } else {
                    rows[2] = "INGRESO POR ANULACIÓN";
                }
            }
            
            String precioLista = String.valueOf(util.Redondear2Decimales(d.getCantentregada() * d.getPreciolista()));
//            System.out.println("precioLista:" + precioLista);
            rows[5] = formatearMonetario(precioLista, 2);
//            System.out.println("row[5]:" + rows[5]);
            
            if ( d.getClientes() == null ) {
                rows[6] = d.getMotivo();
            } else {
                rows[6] = d.getClientes().getNombre();
            }
            dtm.addRow(rows);
        }
        tx_ingresos.setText(String.valueOf(totalIngreso));
        tx_salidas.setText(String.valueOf(totalSalida));
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
        return valorMonetario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_salir;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_hoy;
    private javax.swing.JTable tb_historial;
    private javax.swing.JTextField tx_StockActual;
    private javax.swing.JTextField tx_descripcion;
    private javax.swing.JTextField tx_desde;
    private javax.swing.JTextField tx_hasta;
    private javax.swing.JTextField tx_ingresos;
    private javax.swing.JTextField tx_nroParte;
    private javax.swing.JTextField tx_salidas;
    private javax.swing.JTextField tx_ubicAlmacen;
    // End of variables declaration//GEN-END:variables
}
