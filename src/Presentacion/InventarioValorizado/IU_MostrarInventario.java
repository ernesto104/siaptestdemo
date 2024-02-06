package Presentacion.InventarioValorizado;

import static Entidades.Otros.Constante.COLUMNA_IDLINEA;
import static Entidades.Otros.Constante.COLUMNA_PREVTA;
import static Entidades.Otros.Constante.COLUMNA_TOTAL;
import static Entidades.Otros.Constante.COLUMNA_COSTUNIT;
import static Entidades.Otros.Constante.COLUMNA_STOCK_IV;
import static Entidades.Otros.Constante.COLUMNA_VALORIZACION;
import static Entidades.Otros.Constante.TIT_IVG;
import Entidades.Repuestos;
import Mantenimiento.DetallesDAO;
import Servicios.CuentasxCobrar.Sv_Impresion;
import Servicios.Servicio_Control;
import Servicios.Servicio_Excel;
import Servicios.Servicio_InventarioRepuestos;
import Servicios.Servicio_Maestros;
import Servicios.Util;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Keily Mendiolaza
 */
public final class IU_MostrarInventario extends javax.swing.JFrame {

    private Servicio_InventarioRepuestos siv;
    private FREP036 iv;
    Servicio_Maestros sm;
    Servicio_Excel excel;    
    String TipoFecha;
    String fecha;
    String otro;
    String tipvalor;
    int filaseleccionada;
    DefaultTableModel modelo;
    String valorizacionSelect;
    
     public IU_MostrarInventario(String tipoFecha, String fechaseleccionada, String valorizacion, String otro) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.valorizacionSelect = valorizacion;
        
        siv = new Servicio_InventarioRepuestos(this);
        iv = new FREP036();
        sm = new Servicio_Maestros();
        TipoFecha = tipoFecha;
        fecha = fechaseleccionada;
        this.tipvalor = valorizacion;
        this.otro = otro;
        seleccionado();
        
        siv.formatoTabla(tablaRepuestos, valorizacion);
        
        ocultarColumnaIdLinea();
        ocultarSegunValorizacion(valorizacion);
        
        alinearDerecha();
    }
    
    private void ocultarSegunValorizacion(String valorizacion) {
        if ( TIT_IVG.equals(valorizacion) ) {
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_VALORIZACION).setMaxWidth(0); // 5
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_VALORIZACION).setMinWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_VALORIZACION).setPreferredWidth(0);
            
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_TOTAL).setMaxWidth(0); // 6
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_TOTAL).setMinWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_TOTAL).setPreferredWidth(0);
            
        } else {
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_COSTUNIT).setMaxWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_COSTUNIT).setMinWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_COSTUNIT).setPreferredWidth(0);
            
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_PREVTA).setMaxWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_PREVTA).setMinWidth(0);
            tablaRepuestos.getColumnModel().getColumn(COLUMNA_PREVTA).setPreferredWidth(0);
        }
    }
    
    private void ocultarColumnaIdLinea() {
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_IDLINEA).setMaxWidth(0); // 7
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_IDLINEA).setMinWidth(0);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_IDLINEA).setPreferredWidth(0);
        
        tablaRepuestos.getColumnModel().getColumn(13).setMaxWidth(0);    // Fob
        tablaRepuestos.getColumnModel().getColumn(13).setMinWidth(0);
        tablaRepuestos.getColumnModel().getColumn(13).setPreferredWidth(0);        
        
        tablaRepuestos.getColumnModel().getColumn(14).setMaxWidth(0);    // Marca
        tablaRepuestos.getColumnModel().getColumn(14).setMinWidth(0);
        tablaRepuestos.getColumnModel().getColumn(14).setPreferredWidth(0);            
    }
    
    private void alinearDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_STOCK_IV).setCellRenderer(tcr);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_VALORIZACION).setCellRenderer(tcr);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_TOTAL).setCellRenderer(tcr);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_COSTUNIT).setCellRenderer(tcr);
        tablaRepuestos.getColumnModel().getColumn(COLUMNA_PREVTA).setCellRenderer(tcr);
    }

    public String fechasistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(ahora);
    }

    public void seleccionado(){
        if ( TipoFecha.equals("A la fecha") ) {
            texto3.setText("FECHA : ");
            texto4.setText(fechasistema());
            txtvalorizacion.setText(tipvalor);
            BigDecimal sumatotal = siv.listarRepuestos(tipvalor);
            txtsumatotal.setText(String.valueOf(sumatotal));
        }
        if ( TipoFecha.equals("A una fecha") ) {
            txtvalorizacion.setText(tipvalor);
            //siv.listar_Inventario_alaFecha(tipvalor, fecha);

            BigDecimal sumatotal = siv.listar_Inventario_aUnaFecha(tipvalor, fecha);
            txtsumatotal.setText(String.valueOf(sumatotal));
            
            texto0.setText("FECHA: ");
            texto1.setText("DEL ");
            String fechaInicial = getFechaMasAntiguaMovimiento();
            texto2.setText(fechaInicial);
            texto3.setText(" AL ");
            texto4.setText(fechasistema());
            texto4.setText(otro);
        }
    }
    
    private String getFechaMasAntiguaMovimiento() {
        Date fecha = new Date();
        fecha = new DetallesDAO().obtenerFechaMasAntiguaMovimiento();
//        System.out.println("fecha:" + fecha);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString;
        
        if ( fecha == null ) {
            try {
//                String fechaInicial = "1990-01-01";
                String fechaInicial = "01/01/1990";
                Date dateFI = sdf.parse(fechaInicial);
//                fechaString = sdf.format(dateFI);
//                System.out.println("fechaString si es null: " + fechaString);
                fecha = dateFI;
//                System.out.println("CASO 1");
                
            } catch (ParseException ex) {
                Logger.getLogger(IU_MostrarInventario.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Excepcion:" + ex.getMessage());
            }
        }
//        System.out.println("fecha:::" + fecha);
//        else {
//            fechaString = sdf.format(fecha);
////            System.out.println("fechaString si tiene valor:" + fechaString);
//        }
        return sdf.format(fecha);
    }

    public Date fechaelegida() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fe = sdf.parse(TipoFecha);
        return fe;
    }

    public int calcularStock_Repuesto(String codop, int stock, int cantEnt) {
        int stockneto = stock;
        int cantent = cantEnt;
        char oper = codop.charAt(0);
        if ( oper == 'I' ) {
            stockneto = stockneto - cantent;
        }
        if ( oper == 'S' ) {
            stockneto = stockneto + cantent;
        }
        return stockneto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMostrarInventario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        texto3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRepuestos = new javax.swing.JTable();
        txtsumatotal = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        btnImprimirResum = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        texto4 = new javax.swing.JLabel();
        txtvalorizacion = new javax.swing.JLabel();
        texto2 = new javax.swing.JLabel();
        texto1 = new javax.swing.JLabel();
        texto0 = new javax.swing.JLabel();
        btImprimirDet = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btExcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setText("INVENTARIO VALORIZADO:");

        texto3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maestro de Repuestos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setText("S/.");

        tablaRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Nº de Parte", "Descripción", "Aplicación", "Anotación", "Stock", "Costo", "Total", "idLinea", "Costo Unitario", "Precio Venta", "Aplicacion 2", "Anotacion 2", "idlinea", "marca", "fobultimo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRepuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaRepuestos);
        if (tablaRepuestos.getColumnModel().getColumnCount() > 0) {
            tablaRepuestos.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        txtsumatotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsumatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsumatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnConsultar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConsultar.setText("Mas Datos");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnImprimirResum.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnImprimirResum.setText("Imprimir Resumen");
        btnImprimirResum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirResumActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        texto4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtvalorizacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        texto2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        texto1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        texto0.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        btImprimirDet.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btImprimirDet.setText("Imprimir Detallado");
        btImprimirDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirDetActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("INVENTARIO VALORIZADO");

        btExcel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btExcel.setText("Exportar Excel");
        btExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMostrarInventarioLayout = new javax.swing.GroupLayout(panelMostrarInventario);
        panelMostrarInventario.setLayout(panelMostrarInventarioLayout);
        panelMostrarInventarioLayout.setHorizontalGroup(
            panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                .addGroup(panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMostrarInventarioLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtvalorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(texto0, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(texto4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                .addGroup(panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnImprimirResum, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btImprimirDet)
                        .addGap(33, 33, 33)
                        .addComponent(btExcel)
                        .addGap(38, 38, 38)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMostrarInventarioLayout.setVerticalGroup(
            panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMostrarInventarioLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtvalorizacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMostrarInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btImprimirDet, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirResum, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMostrarInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMostrarInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed

        filaseleccionada = tablaRepuestos.getSelectedRow();

        if ( filaseleccionada >= 0 ) {
//            Repuestos rep = sm.GetRepuesto_Codigo((String) tablaRepuestos.getValueAt(filaseleccionada, 1));
            Repuestos rep = sm.GetRepuesto_Codigo((String) tablaRepuestos.getValueAt(filaseleccionada, 0));
            IU_MasDatos md = new IU_MasDatos(rep);
            md.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un repuesto", "Error al seleccionar", 2);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    
    
    private void btnImprimirResumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirResumActionPerformed
        dispose();
        IU_TablaR t = new IU_TablaR(TipoFecha, tipvalor,fecha);        
        modelo = (DefaultTableModel) tablaRepuestos.getModel();
        IU_Resumen imp = new IU_Resumen(modelo, texto0.getText(), texto1.getText(), 
                                        texto2.getText(), texto3.getText(), texto4.getText(),
                                        tipvalor, txtsumatotal.getText());
        imp.setVisible(true);
    }//GEN-LAST:event_btnImprimirResumActionPerformed

    private void btImprimirDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirDetActionPerformed
        HashMap parametros = SetParametros();
        ArrayList listado = SetLista();
        
        //String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
        Servicios.Importaciones.Servicio_Control sc = new Servicios.Importaciones.Servicio_Control();
        String rutaReportes = sc.getControlUnico().getRutareportes();
        String url1 = "", url2 = "";
        
        if ( TIT_IVG.equals(this.valorizacionSelect) ) {
            url1 = rutaReportes + "\\plantillas\\Inventario_Valorizado_General.jrxml";
            url2 = rutaReportes + "\\plantillas\\Inventario_Valorizado_General.jasper";
//            Sv_Impresion.exporta(6, lista, parametros, "reportes/Inventario_Valorizado_General.pdf");
            
        } else {
            url1 = rutaReportes + "\\plantillas\\Inventario_Valorizado.jrxml";
            url2 = rutaReportes + "\\plantillas\\Inventario_Valorizado.jasper";
//            Sv_Impresion.exporta(1, lista, parametros, "reportes/Inventario_Valorizado.pdf");
        }
         try {
            convertPedidoToPDF(url1, url2, parametros, listado);

         } catch ( Exception ex ) {
            System.out.println("Error(GestionarPlanillas):" + ex.getMessage());
        }
    }//GEN-LAST:event_btImprimirDetActionPerformed

    private void btExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcelActionPerformed
        // TODO add your handling code here:
        List listaCabecera = new ArrayList();
        listaCabecera.add("NRO PARTE");
//      listaCabecera.add("COD.SEC.");        
        listaCabecera.add("DESCRIPCIÓN");
        listaCabecera.add("APLICACIÓN"); // MODELO
        listaCabecera.add("ANOTACIÓN");
//        listaCabecera.add("MARCA");
        listaCabecera.add("STOCK");                
        
        
//        listaCabecera.add("PRECIO");
//        listaCabecera.add("P.TOTAL");                
//        listaCabecera.add("LINEA");                
//        listaCabecera.add("C.PROM");                
//        listaCabecera.add("TOTAL C");   
//-------------------
        if ( "Costo Unitario".equals(tipvalor) ) {
        listaCabecera.add("C.UNITARIO");
        listaCabecera.add("P.TOTAL");                
        }
        if ( "Ultimo Costo".equals(tipvalor) ) {
        listaCabecera.add("ULT.COSTO");
        listaCabecera.add("P.TOTAL");                
        }
        if ( "Precio Lista".equals(tipvalor) ) {
        listaCabecera.add("P.LISTA");
        listaCabecera.add("P.TOTAL");                
        }
        if ( "Costo Unitario + Precio Venta".equals(tipvalor) ) {
        listaCabecera.add("P.LISTA");
        listaCabecera.add("P.TOTAL");                
        }
//-------------------------------------------        
        listaCabecera.add("LINEA");           
//-------------------------------------
        if ( "Costo Unitario".equals(tipvalor) ) {
        listaCabecera.add("C.UNITARIO");
        listaCabecera.add("P.LISTA");                
        }
        if ( "Ultimo Costo".equals(tipvalor) ) {
        listaCabecera.add("C.UNITARIO");
        listaCabecera.add("P.LISTA");                
        }
        if ( "Precio Lista".equals(tipvalor) ) {
        listaCabecera.add("C.UNITARIO");
        listaCabecera.add("P.LISTA");                
        }
        if ( "Costo Unitario + Precio Venta".equals(tipvalor) ) {
        listaCabecera.add("C.UNITARIO");
        listaCabecera.add("P.LISTA");                
        }
        
//-------------------        
        
        listaCabecera.add("COD.SEC");                
        listaCabecera.add("ANOTACION 2");        
        listaCabecera.add("APLICACION 2");        
        listaCabecera.add("P.FOB");        
        listaCabecera.add("MARCA");        
        
        System.out.println("Estoy en exportar EXcel Inventario");
        excel = new Servicio_Excel(tablaRepuestos, this);
        excel.exportarExcel("Inventario Valorizado", listaCabecera, 1);
    }//GEN-LAST:event_btExcelActionPerformed
    
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
//        System.out.println("SetLista...");
        modelo = (DefaultTableModel) tablaRepuestos.getModel();
        ArrayList<InventarioAlmacen> lista = new ArrayList<>();
        
        // Lista temporal para ordenar por 2 filtros(descripcion y aplicacion) en el Browse
        List<InventarioBrowse> listaTmp = new ArrayList<InventarioBrowse>();
        
        for ( int i = 0; i < modelo.getRowCount(); i++ ) {
//            String linea = modelo.getValueAt(i, 0).toString();
//            String item = modelo.getValueAt(i, 0).toString();
            String codigo = modelo.getValueAt(i, 0).toString();
//            String codigosec = "";
//            if(modelo.getValueAt(i, 2)!=null){
//                codigosec = modelo.getValueAt(i, 2).toString();
//            }
            
//            String aplicacion = "";
//            if ( modelo.getValueAt(i, 4) != null ) {
//                aplicacion = modelo.getValueAt(i, 4).toString();
//            }
            
            String descripcion = modelo.getValueAt(i, 1).toString();
            String descrModelo = "";
            if ( modelo.getValueAt(i, 2) != null ) {
                descrModelo = modelo.getValueAt(i, 2).toString();
            }

            String descrLista = "";
            if ( modelo.getValueAt(i, 3) != null ) {
                descrLista = modelo.getValueAt(i, 3).toString();
            }
            
            String stock = modelo.getValueAt(i, 4).toString();
            String costo = modelo.getValueAt(i, 5).toString();
//            String precio = modelo.getValueAt(i, 7).toString();
            String total = modelo.getValueAt(i, 6).toString();
            
            String idLinea = modelo.getValueAt(i, 7).toString();
            
            String costoUnit = modelo.getValueAt(i, 8).toString();
            String precioVta = modelo.getValueAt(i, 9).toString();
            
            String codigoSec = "";
            if ( modelo.getValueAt(i, 10) != null ) {
                codigoSec = modelo.getValueAt(i, 10).toString();
            }
            
            String desclista2 = "";
            if ( modelo.getValueAt(i, 11) != null ) {
                desclista2 = modelo.getValueAt(i, 11).toString();
            }

            String desclista = "";
            if ( modelo.getValueAt(i, 12) != null ) {
                desclista = modelo.getValueAt(i, 12).toString();
            }

// Arreglo para imprimir en varias lineas - todo en descrmodelo            
            if ( !"".equals(desclista) && !" ".equals(desclista) ) {
                descrModelo = descrModelo + "<br/>" + desclista;
            }             
            if ( !"".equals(descrLista) && !" ".equals(descrLista) ) {
                descrModelo = descrModelo + "<br/>" + descrLista;
            }             
            if ( !"".equals(desclista2) && !" ".equals(desclista2) ) {
                descrModelo = descrModelo + "<br/>" + desclista2;
            }             
            
//            String aplicacion = descrModelo;
            
//            String aplicacion2 = modelo.getValueAt(i, 11).toString();
//            String anotacion2 = modelo.getValueAt(i, 12).toString();            
            
//            System.out.println("Aplicacion 2 : " + aplicacion2);
//            System.out.println("Anotacion 2 : " + anotacion2);            
            
//            System.out.println("Codigo : " + codigo);
//            System.out.println("Codigo Sec : " + codigoSec);
            
//            lista.add(new InventarioAlmacen(linea, codigo, codigosec, descripcion, stock, precio, total));
//            lista.add(new InventarioAlmacen(linea, codigo, descripcion, aplicacion, stock, precio, total));
            
            ////////////////////////////////////////////////
            // Guardando fila en una Entidad y ésta en una lista para luego ordenarla por 2 filtros (Descripcion y Modelo[Aplicación])
            // 1.Guardando en lista temporal (lista desordenada)
            InventarioBrowse ib = new InventarioBrowse(
//                                                       item, 
                                                       codigo, descripcion, descrModelo,
                                                       descrLista, stock, costo, total, idLinea,
                                                       costoUnit, precioVta, codigoSec, desclista2, desclista, "");
            listaTmp.add(ib);
            
//            lista.add(new InventarioAlmacen(item, codigo, descripcion, descrModelo,
//                                            descrLista, stock, costo, total, idLinea,
//                                            costoUnit, precioVta)); // JASPER
        }
        
        // 2. Ordenando lista temporal por 2 filtros (descripcion y aplicacion)
        //Collections.sort(listaTmp);
//        mostrarListaOrdenadax2Filtros(listaTmp);
        
        // 3. Transferir los elementos ordenados de la lista temporal "listaTmp" a la lista que se mostrará en el Browse:
        for ( InventarioBrowse ib : listaTmp ) {
//            String item = ib.getItem();
            
            String codigo = ib.getCodrepuesto();
            String descripcion = ib.getDescripcion();
            String aplicacion = ib.getAplicacion();
            String descrLista = ib.getAnotacion();
            String stock = ib.getStock();
            String costo = ib.getValorizacion();
            String total = ib.getTotal();
            String idLinea = ib.getIdlinea();
            String costoUnit = ib.getCostoUnit();
            String precioVta = ib.getPrecioVta();
            String codigoSec = ib.getCodigoSec();
            String desclista2 = ib.getDesclista2();
            String desclista = ib.getDesclista();
            String descrmodelo = ib.getDescrModelo();

            lista.add(new InventarioAlmacen(
//                                            item, 
                                            codigo, descripcion, descrmodelo,
                                            descrLista, stock, costo, total, idLinea,
                                            costoUnit, precioVta, codigoSec, desclista2, desclista)); // JASPER
        }
        return lista;
    }
    
    private void mostrarListaOrdenadax2Filtros(List<InventarioBrowse> lista) {
//        System.out.println("Lista ordenada x 2 filtros(descripcion y aplicacion)::");
        for ( int i = 0; i < lista.size(); i++ ) {
            InventarioBrowse ib = (InventarioBrowse) lista.get(i);
            System.out.println(ib.getDescripcion() + "--" + ib.getAplicacion());
        }
    }

    private HashMap SetParametros() {
        Servicio_Control sc = new Servicio_Control();
        HashMap mapa = new HashMap<>();
        String empresa = sc.listar_control().get(0).getNombrempresa();
        String valorizacion = txtvalorizacion.getText();
        String t1 = texto0.getText();
        String t2 = texto1.getText();
        String t3 = texto2.getText();
        String t4 = texto3.getText();
        String t5 = texto4.getText();
        String totalneto = txtsumatotal.getText();
        mapa.put("empresa", empresa);
        mapa.put("valorizacion",valorizacion);
        mapa.put("t1", t1);
        mapa.put("t2", t2);
        mapa.put("t3", t3);
        mapa.put("t4", t4);
        mapa.put("t5", t5);
        mapa.put("totalneto", totalneto);
        String nombre = "";
        
        if( tipvalor.equals("Precio Lista") ) {
            nombre = "PRECIO LISTA";
        }
        if( tipvalor.equals("Ultimo Costo") ) {
            nombre = "ULTIMO COSTO";
        }
        if( tipvalor.equals("Costo Unitario") ) {
            nombre = "COSTO UNITARIO";
        }
        if ( tipvalor.equals(TIT_IVG) ) {
            nombre = TIT_IVG;
        }
        mapa.put("nombre", nombre);
        return mapa;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btExcel;
    public javax.swing.JButton btImprimirDet;
    public javax.swing.JButton btnConsultar;
    public javax.swing.JButton btnImprimirResum;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel panelMostrarInventario;
    public javax.swing.JTable tablaRepuestos;
    public javax.swing.JLabel texto0;
    public javax.swing.JLabel texto1;
    public javax.swing.JLabel texto2;
    public javax.swing.JLabel texto3;
    public javax.swing.JLabel texto4;
    public javax.swing.JLabel txtsumatotal;
    public javax.swing.JLabel txtvalorizacion;
    // End of variables declaration//GEN-END:variables
}