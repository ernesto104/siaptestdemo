package Presentacion.RepuestosSegunEstratificacion;

import Entidades.Estratificacion;
import Entidades.Repuestos;
import Mantenimiento.ControlDAO;
import Presentacion.Facturacion.IU_Demanda;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Servicio_Estratificacion;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Maestros;
import Servicios.facturacion.Servicio_Impresion;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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

public class FREP039 extends javax.swing.JFrame {

    DefaultTableModel dtm;
    Servicio_Excel se;
    Servicio_Maestros sm;

    public FREP039() {
        initComponents();
        dtm = (DefaultTableModel) tb_consulta.getModel();
        se = new Servicio_Excel(tb_consulta, this);
        sm = new Servicio_Maestros(null);        
        ListarEstratificacion();
        setAnchoColumnas();
        tx_busqueda.setDocument(new Validar_Mayusculas(tx_busqueda, 30));
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_consulta.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_consulta.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_consulta.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (17 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (17 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (13 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    private ArrayList SetLista(){
       ArrayList<ConsultarRepuestos> lista = new ArrayList<>();
       for(int i=0;i<tb_consulta.getRowCount();i++){
           String nroParte = String.valueOf(dtm.getValueAt(i, 2));
           String descripcion = String.valueOf(dtm.getValueAt(i, 3));
           String stock = String.valueOf(dtm.getValueAt(i, 4));
           String precio = String.valueOf(dtm.getValueAt(i, 5));
           String marca = String.valueOf(dtm.getValueAt(i, 6));
           lista.add(new ConsultarRepuestos(nroParte, descripcion, stock, precio, marca));
       }
       return lista;
   }
   private HashMap SetParametros(){
       HashMap mapa = new HashMap<>();
       mapa.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(GregorianCalendar.getInstance().getTime()));
       String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
       mapa.put("empresa", empresa);
       
       String cod = new Servicio_Estratificacion().getEstratificacion_por_nombre(
               String.valueOf(cb_estrat.getSelectedItem())).getCodigoestratificacion();
       String descrip = new Servicio_Estratificacion().getEstratificacion_por_nombre(
               String.valueOf(cb_estrat.getSelectedItem())).getDescripcion();
       
       int desde = new Servicio_Estratificacion().getEstratificacion_por_nombre(
               String.valueOf(cb_estrat.getSelectedItem())).getVentasdesde();
       
       int hasta =  new Servicio_Estratificacion().getEstratificacion_por_nombre(
               String.valueOf(cb_estrat.getSelectedItem())).getVentashasta();
       
       String estratificacion = cod + " - " + descrip + "    (Desde : "+ desde +  "  -  Hasta : " + hasta + ")"; 
       
       mapa.put("estratificacion",estratificacion);
       mapa.put("totalRep", lb_totalRep.getText());
       return mapa;
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRepEstra = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_estrat = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        bt_datos = new javax.swing.JButton();
        bt_demanda = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_exportar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cb_tipoBusqueda = new javax.swing.JComboBox();
        tx_busqueda = new javax.swing.JTextField();
        lb_totalRep = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel1.setText("Estratificacion");

        cb_estrat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_estratItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cb_estrat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_estrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tb_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Linea", "Nro Parte", "Descripcion", "Stock", "Precio Lista", "Marca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_consulta.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_consulta);

        jLabel2.setText("Total Repuestos");

        bt_datos.setText("Datos");
        bt_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_datosActionPerformed(evt);
            }
        });

        bt_demanda.setText("Demanda");
        bt_demanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_demandaActionPerformed(evt);
            }
        });

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_exportar.setText("Exportar");
        bt_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exportarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        jLabel3.setText("Por");

        cb_tipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numero Parte", "Descripcion" }));

        tx_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_busquedaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cb_tipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_tipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lb_totalRep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REPUESTOS SEGUN ESTRATIFICACION");

        javax.swing.GroupLayout panelRepEstraLayout = new javax.swing.GroupLayout(panelRepEstra);
        panelRepEstra.setLayout(panelRepEstraLayout);
        panelRepEstraLayout.setHorizontalGroup(
            panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepEstraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelRepEstraLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addGroup(panelRepEstraLayout.createSequentialGroup()
                        .addComponent(bt_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_demanda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_totalRep, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(panelRepEstraLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRepEstraLayout.setVerticalGroup(
            panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepEstraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_totalRep, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRepEstraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_demanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRepEstraLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRepEstra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRepEstra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_datosActionPerformed
        int fila = tb_consulta.getSelectedRow();
        if (fila >= 0) {
            Repuestos r = sm.GetRepuesto_Codigo(String.valueOf(dtm.getValueAt(fila, 2)));
            IU_DatosRepuesto Datos_Rep = new IU_DatosRepuesto(r,0);// 
        }
    }//GEN-LAST:event_bt_datosActionPerformed

    private void bt_demandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_demandaActionPerformed
        int fila = tb_consulta.getSelectedRow();
        Repuestos r = sm.GetRepuesto_Codigo(String.valueOf(dtm.getValueAt(fila, 2)));
        IU_Demanda d = new IU_Demanda(r);
    }//GEN-LAST:event_bt_demandaActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la Operacion?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        
        if ( opcion == JOptionPane.YES_OPTION ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            String url1 = rutaReportes + "\\plantillas\\consultarRepuestos.jrxml";
            String url2 = rutaReportes + "\\plantillas\\consultarRepuestos.jasper";
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            Servicio_Impresion.exporta(1, lista,parametros,"plantillas/Consulta_Repuestos.pdf");
            try {
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(IU_ResumenGeneral):" + ex.getMessage());
            }
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
    
    private void bt_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportarActionPerformed
        se.Exportar_Excel(1);
    }//GEN-LAST:event_bt_exportarActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void cb_estratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_estratItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            BorrarTabla();
            ListarRepuestos(sm.Consultar_Repuestos(String.valueOf(cb_estrat.getSelectedItem()), cb_tipoBusqueda.getSelectedIndex(),
                    tx_busqueda.getText()).iterator());
        }
    }//GEN-LAST:event_cb_estratItemStateChanged

    private void tx_busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_busquedaKeyTyped
        if (evt.getKeyChar() == 10) { // Enter
            BorrarTabla();
            ListarRepuestos(sm.Consultar_Repuestos(String.valueOf(cb_estrat.getSelectedItem()), cb_tipoBusqueda.getSelectedIndex(),
                    tx_busqueda.getText()).iterator());
        }
    }//GEN-LAST:event_tx_busquedaKeyTyped
  
    private void BorrarTabla() {
        int numRows = dtm.getRowCount();
        for (int i = 0; i < numRows; i++) {
            dtm.removeRow(0);
        }
    }
    
    private void ListarRepuestos(Iterator it) {
        int i = 1;
        while (it.hasNext()) {
            Object[] result = (Object[]) it.next();
            Object[] row = {i++, result[0],
                result[1],
                result[2],
                result[3],
                result[4],
                result[5]};
            dtm.addRow(row);
        }
        lb_totalRep.setText(String.valueOf(i - 1));
    }

    private void ListarEstratificacion() {
        ArrayList<Estratificacion> lista = (ArrayList) new Servicio_Estratificacion(null).ListarEstratificacion();
        for (Estratificacion estr : lista) {
            cb_estrat.addItem(estr.getCodigoestratificacion());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_datos;
    public javax.swing.JButton bt_demanda;
    public javax.swing.JButton bt_exportar;
    public javax.swing.JButton bt_imprimir;
    public javax.swing.JButton bt_salir;
    public javax.swing.JComboBox cb_estrat;
    public javax.swing.JComboBox cb_tipoBusqueda;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lb_totalRep;
    public javax.swing.JPanel panelRepEstra;
    public javax.swing.JTable tb_consulta;
    public javax.swing.JTextField tx_busqueda;
    // End of variables declaration//GEN-END:variables
}
