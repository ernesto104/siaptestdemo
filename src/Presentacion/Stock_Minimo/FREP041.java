package Presentacion.Stock_Minimo;

import Mantenimiento.ControlDAO;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Servicio_Maestros;
import Servicios.facturacion.Servicio_Impresion;
import java.util.ArrayList;
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

public class FREP041 extends javax.swing.JFrame {

    Servicio_Maestros servicio;

    public FREP041() {
        initComponents();
        setLocationRelativeTo(null);
        servicio = new Servicio_Maestros(null);
        ListarRep();
        setAnchoColumnas();
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_stockMin.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_stockMin.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_stockMin.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);

            switch (i) {
                case 0:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (40 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (15 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelStockMinimo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_stockMin = new javax.swing.JTable();
        bt_imprimir = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_stockMin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Parte", "Código Secundario", "Descripcion", "Desc.Modelo", "Marca", "Stock", "Stock Minimo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_stockMin);
        if (tb_stockMin.getColumnModel().getColumnCount() > 0) {
            tb_stockMin.getColumnModel().getColumn(3).setResizable(false);
            tb_stockMin.getColumnModel().getColumn(4).setResizable(false);
        }

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Stock Mínimos");

        javax.swing.GroupLayout panelStockMinimoLayout = new javax.swing.GroupLayout(panelStockMinimo);
        panelStockMinimo.setLayout(panelStockMinimoLayout);
        panelStockMinimoLayout.setHorizontalGroup(
            panelStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockMinimoLayout.createSequentialGroup()
                .addGroup(panelStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelStockMinimoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(panelStockMinimoLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelStockMinimoLayout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        panelStockMinimoLayout.setVerticalGroup(
            panelStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStockMinimoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStockMinimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStockMinimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if ( opcion == JOptionPane.YES_OPTION ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            
            String url1 = rutaReportes + "\\plantillas\\stockMinimos.jrxml";
            String url2 = rutaReportes + "\\plantillas\\stockMinimos.jasper";
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            Servicio_Impresion.exporta(3, lista, parametros, "plantillas/Stock_Minimos.pdf");
            try {
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(IU_ResumenGeneral):" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

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
        DefaultTableModel dtm = (DefaultTableModel) tb_stockMin.getModel();
        ArrayList<StockMinimo> lista = new ArrayList<>();
        for (int i = 0; i < tb_stockMin.getRowCount(); i++) {
            String nroParte = String.valueOf(dtm.getValueAt(i, 0));
            String codSec = String.valueOf(dtm.getValueAt(i, 1));
            String descripcion = String.valueOf(dtm.getValueAt(i, 2));
            String descrmodelo = String.valueOf(dtm.getValueAt(i, 3));
            String marca = String.valueOf(dtm.getValueAt(i, 4));
            String stock = String.valueOf(dtm.getValueAt(i, 5));
            String stockMinimo = String.valueOf(dtm.getValueAt(i, 6));
            lista.add(new StockMinimo(nroParte, codSec, descripcion, descrmodelo, marca, stock, stockMinimo));
        }
        return lista;
    }

    private HashMap SetParametros() {
        HashMap mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        mapa.put("total", String.valueOf(tb_stockMin.getRowCount()));
        return mapa;
    }

    private void ListarRep() {
        DefaultTableModel dtm = (DefaultTableModel) tb_stockMin.getModel();
        ArrayList<Object> lista = (ArrayList) servicio.GetRepuestos_StockMin();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            Object[] result = (Object[]) it.next();
            Object[] fila = {result[0],
                result[1],
                result[2],
                result[3],
                result[4],
                result[5],
                result[6]};
            dtm.addRow(fila);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_imprimir;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelStockMinimo;
    public javax.swing.JTable tb_stockMin;
    // End of variables declaration//GEN-END:variables
}
