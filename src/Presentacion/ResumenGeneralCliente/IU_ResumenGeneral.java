package Presentacion.ResumenGeneralCliente;

import Entidades.Clientes;
import Mantenimiento.ControlDAO;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Control;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Impresion;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class IU_ResumenGeneral extends javax.swing.JFrame {

    Clientes cliente;
    Date FechaInicio;
    Date FechaFin;
    DefaultTableModel dtm;
    List Deudas_FacBol;
    List Deudas_Letras;
    List Deudas_SK;
    Servicio_Cabeces cab;
    double totalSoles;
    double totalDolares;

    public IU_ResumenGeneral(Clientes c, Date Inicio, Date Fin) {
        initComponents();
        cab = new Servicio_Cabeces();
        setLocationRelativeTo(null);
        setVisible(true);
        dtm = (DefaultTableModel) tb_resumen.getModel();
        cliente = c;
        FechaInicio = Inicio;
        FechaFin = Fin;
        SetCliente_Fechas();
        SetTotalDeudas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_nombre = new javax.swing.JLabel();
        lb_ruc = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_resumen = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lb_totalDolares = new javax.swing.JLabel();
        lb_totalSoles = new javax.swing.JLabel();
        bt_imprimir = new javax.swing.JButton();
        bt_detalle = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        lb_fechaIn = new javax.swing.JLabel();
        lb_fechaFin = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nro RUC :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre  :");

        lb_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_ruc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lb_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tb_resumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Por Cobrar", "Dolares $", "Soles S/."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_resumen.setRowHeight(50);
        tb_resumen.getTableHeader().setResizingAllowed(false);
        tb_resumen.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_resumen);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Total");

        lb_totalDolares.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_totalSoles.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(lb_totalDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(lb_totalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_totalDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_totalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_detalle.setText("Ver Detalle");
        bt_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_detalleActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        lb_fechaIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_fechaFin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText(" -");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_fechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(bt_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_fechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_detalleActionPerformed
        int fila = tb_resumen.getSelectedRow();
        if (fila >= 0){
            switch(fila){
                case 0:
                    IU_DetalleFacBol detFacBol = new IU_DetalleFacBol(Deudas_FacBol,FechaInicio,FechaFin,cliente);
                    break;
                case 1:
                    IU_DetalleLetras detLetras = new IU_DetalleLetras(Deudas_Letras, FechaInicio, FechaFin, cliente);
                    break;
                case 2:
                    IU_DetalleSK detSK = new IU_DetalleSK(Deudas_SK, FechaInicio, FechaFin, cliente);
                    break;
            }
        }
    }//GEN-LAST:event_bt_detalleActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        TipoMensaje tm = new TipoMensaje();
        
        if ( tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            String url1, url2;
            
            url1 = rutaReportes + "\\resumenGeneral\\resumenCliente_General.jrxml";
            url2 = rutaReportes + "\\resumenGeneral\\resumenCliente_General.jasper";
            
            try {
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(IU_ResumenGeneral):" + ex.getMessage());
            }
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            System.out.println("lista:" + lista);
//            System.out.println("parametros:" + parametros);
//            Servicio_Impresion.exporta(5, lista,parametros,"plantillas/ResumenGeneral.pdf");
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
    
    private ArrayList SetLista(){
       ArrayList<ResumenGeneral> lista = new ArrayList<>();
       for(int i=0;i<tb_resumen.getRowCount();i++){
           String tipoDoc = String.valueOf(dtm.getValueAt(i, 0));
           String dolares = String.valueOf(dtm.getValueAt(i, 1));
           String soles = String.valueOf(dtm.getValueAt(i, 2));
           System.out.println("tipoDoc:" + tipoDoc);
           System.out.println("dolares:" + dolares);
           System.out.println("soles:" + soles);
           System.out.println("---------------------");
           lista.add(new ResumenGeneral(tipoDoc, dolares, soles));           
       }
       return lista;
   }
   private HashMap SetParametros(){
       HashMap mapa = new HashMap<>();
       mapa.put("codigo", String.valueOf(cliente.getIdcliente()));
       mapa.put("cliente",cliente.getNombre());
       mapa.put("ruc", cliente.getRuc());
       mapa.put("desde", lb_fechaIn.getText());
       mapa.put("hasta", lb_fechaFin.getText());
       mapa.put("totalDolares", lb_totalDolares.getText());
       mapa.put("totalSoles",lb_totalSoles.getText());
       
       System.out.println("codigo:" + mapa.get("codigo"));
       System.out.println("cliente:" + mapa.get("cliente"));
       System.out.println("ruc:" + mapa.get("ruc"));
       System.out.println("desde:" + mapa.get("desde"));
       System.out.println("hasta:" + mapa.get("hasta"));
       System.out.println("totalDolares:" + mapa.get("totalDolares"));
       System.out.println("totalSoles:" + mapa.get("totalSoles"));
       return mapa;
   }
    
    private void SetCliente_Fechas() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lb_nombre.setText(cliente.getNombre());
        lb_ruc.setText(cliente.getRuc());
        lb_fechaIn.setText(formato.format(FechaInicio));
        lb_fechaFin.setText(formato.format(FechaFin));
    }

    private void SetTotalDeudas() {
        Deudas_FacBol = cab.ObtenerDeudas_FacBol(cliente.getIdcliente(), FechaInicio, FechaFin);
        Deudas_Letras = cab.ObtenerDeudas_Letras(cliente.getIdcliente(), FechaInicio, FechaFin);
        Deudas_SK = cab.ObtenerDeudas_SK(cliente.getIdcliente(), FechaInicio, FechaFin);
        
        SetTotal_FacBol();
        SetTotal_Letras();
        SetTotal_SK();
        SetSumaTotal();
    }

    private void SetTotal_FacBol() {
        double importe_dolares = 0.0;
        double importe_soles = 0.0;
        double pagado;
        Iterator it = Deudas_FacBol.iterator();
        while (it.hasNext()) {
            Object[] resul = (Object[]) it.next();

            if (String.valueOf(resul[5]).equals("2")) { // Dolares
                importe_dolares += Double.parseDouble(String.valueOf(resul[4]));
                if (resul[7] == null) {
                    pagado = 0.0;
                } else {
                    pagado = Double.parseDouble(String.valueOf(resul[7]));
                }
                importe_dolares -= pagado;
            } else {
                importe_soles += Double.parseDouble(String.valueOf(resul[4]));
                if (resul[7] == null) {
                    pagado = 0.0;
                } else {
                    pagado = Double.parseDouble(String.valueOf(resul[7]));
                }
                importe_soles -= pagado;
            }
        }
        totalSoles += importe_soles;
        totalDolares += importe_dolares;
        importe_dolares = Math.round(importe_dolares * 100.0) / 100.0;
        importe_soles = Math.round(importe_soles * 100.0) / 100.0;
        
        BigDecimal bd1 = new BigDecimal(String.valueOf(importe_dolares));
        BigDecimal bd2 = new BigDecimal(String.valueOf(importe_soles));
        
        Object[] row = {"FACTURAS / BOLETAS ", bd1, bd2};
        dtm.addRow(row);

    }

    private void SetTotal_Letras() {
        double importe_dolares = 0.0;
        double importe_soles = 0.0;
                
        Iterator it = Deudas_Letras.iterator();
        while (it.hasNext()) {
            Object[] resul = (Object[]) it.next();
            if (String.valueOf(resul[5]).equals("2")) { // Dolares
                importe_dolares += Double.parseDouble(String.valueOf(resul[4]));
            } else {
                importe_soles += Double.parseDouble(String.valueOf(resul[4]));
            }
        }        
        totalSoles += importe_soles;
        totalDolares += importe_dolares;
        importe_dolares = Math.round(importe_dolares * 100.0) / 100.0;
        importe_soles = Math.round(importe_soles * 100.0) / 100.0;
        
        BigDecimal bd1 = new BigDecimal(String.valueOf(importe_dolares));
        BigDecimal bd2 = new BigDecimal(String.valueOf(importe_soles));
        
        
        Object[] row = {"LETRAS ", bd1, bd2};
        dtm.addRow(row);
    }

    private void SetTotal_SK() {
        double importe_dolares = 0.0;
        double importe_soles = 0.0;
        double pagado;
        Iterator it = Deudas_SK.iterator();
        while (it.hasNext()) {
            Object[] resul = (Object[]) it.next();
            System.out.println(resul[2] + " - "+ resul[3]);
            importe_dolares += Double.parseDouble(String.valueOf(resul[2]));
            if (resul[3] == null) {
                pagado = 0.0;
            } else {
                pagado = Double.parseDouble(String.valueOf(resul[3]));
            }
            importe_dolares -= pagado;

        }
        totalDolares += importe_dolares;
        importe_dolares = Math.round(importe_dolares * 100.0) / 100.0;
        
        BigDecimal bd1 = new BigDecimal(String.valueOf(importe_dolares));
        BigDecimal bd2 = new BigDecimal(String.valueOf(importe_soles));
        
        Object[] row = {"SALIDAS VARIAS  ", bd1, bd2};
        dtm.addRow(row);
    }

    private void SetSumaTotal() {
        totalDolares=Math.round(totalDolares*100.0)/100.0;
        totalSoles=Math.round(totalSoles*100.0)/100.0;
        
        BigDecimal dolares = new BigDecimal(String.valueOf(totalDolares));
        BigDecimal soles = new BigDecimal(String.valueOf(totalSoles));
        
        lb_totalSoles.setText(" S/. " + soles);
        lb_totalDolares.setText(" $   " + dolares);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_detalle;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_fechaFin;
    private javax.swing.JLabel lb_fechaIn;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_ruc;
    private javax.swing.JLabel lb_totalDolares;
    private javax.swing.JLabel lb_totalSoles;
    private javax.swing.JTable tb_resumen;
    // End of variables declaration//GEN-END:variables
}
