package Presentacion.ConsultarClientes;

import Entidades.Clientes;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Excel;
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

public class FREP038 extends javax.swing.JFrame {

    Servicio_Cliente consulta;
    DefaultTableModel dtm;
    Servicio_Excel se;
    Usuarios usu;

    public FREP038(Usuarios u) {
        usu = u;
        consulta = new Servicio_Cliente(null);
        initComponents();
        setLocationRelativeTo(null);
        dtm = (DefaultTableModel) tb_consulta.getModel();
        setAnchoColumnas();
        se = new Servicio_Excel(tb_consulta, this);
        Listar_Clientes(consulta.get_ListaClientes_2().iterator());
        ocultandoColumnaIdCliente();
    }
    
    private void ocultandoColumnaIdCliente() {
        tb_consulta.getColumnModel().getColumn(7).setMaxWidth(0);
        tb_consulta.getColumnModel().getColumn(7).setMinWidth(0);
        tb_consulta.getColumnModel().getColumn(7).setPreferredWidth(0);
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
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (27 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (27 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (9 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (10 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelConCli = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cb_tipo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        tx_busq = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_consulta = new javax.swing.JTable();
        tx_resultado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bt_datos = new javax.swing.JButton();
        bt_filtrar = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_exportar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        cb_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Ruc", "Plaza" }));

        jLabel1.setText("Tipo de Busqueda");

        tx_busq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_busqActionPerformed(evt);
            }
        });
        tx_busq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_busqKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(tx_busq, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tx_busq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        tb_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "RUC", "Nombre", "Telefono(s)", "Direccion", "Plaza", "Contacto", "idCliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        if (tb_consulta.getColumnModel().getColumnCount() > 0) {
            tb_consulta.getColumnModel().getColumn(7).setResizable(false);
        }

        tx_resultado.setEnabled(false);

        jLabel2.setText("Total Representantes");

        bt_datos.setText("Datos");
        bt_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_datosActionPerformed(evt);
            }
        });

        bt_filtrar.setText("Filtrar");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_exportar.setText("Exporta (Excel)");
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

        javax.swing.GroupLayout panelConCliLayout = new javax.swing.GroupLayout(panelConCli);
        panelConCli.setLayout(panelConCliLayout);
        panelConCliLayout.setHorizontalGroup(
            panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConCliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConCliLayout.createSequentialGroup()
                        .addComponent(bt_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 201, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelConCliLayout.setVerticalGroup(
            panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConCliLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(tx_resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelConCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelConCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelConCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_datosActionPerformed
        int fila = tb_consulta.getSelectedRow();
        if (fila >= 0) {
            int idCliente = Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 7)));
            Clientes c = consulta.obtener_Cliente(idCliente);
            IU_DatosCliente datos = new IU_DatosCliente(c);
        }
    }//GEN-LAST:event_bt_datosActionPerformed

    private void tx_busqKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_busqKeyTyped
        if (evt.getKeyChar() == 10) {
            bt_filtrar.doClick();
        }
    }//GEN-LAST:event_tx_busqKeyTyped

    private void bt_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportarActionPerformed
        se.Exportar_Excel(1);
    }//GEN-LAST:event_bt_exportarActionPerformed

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed
        BorrarTabla();
        switch (cb_tipo.getSelectedIndex()) {
            case 0:
                Listar_Clientes(consulta.getCliente_Nombre_Filtro(tx_busq.getText()).iterator());
                break;
            case 1:
                Listar_Clientes(consulta.getCliente_RUC_Filtro(tx_busq.getText()).iterator());
                break;
            case 2:
                Listar_Clientes(consulta.getCliente_Plaza_Filtro(tx_busq.getText()).iterator());
        }
    }//GEN-LAST:event_bt_filtrarActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la Operacion?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        
        if ( opcion == JOptionPane.YES_OPTION ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            String url1 = rutaReportes + "\\plantillas\\ConsultaClientes.jrxml";
            String url2 = rutaReportes + "\\plantillas\\ConsultaClientes.jasper";
            
            try {
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(ConsultaClientes):" + ex.getMessage());
            }
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            Servicio_Impresion.exporta(0, lista, parametros, "plantillas/Consulta_Clientes.pdf");
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void tx_busqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_busqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_busqActionPerformed
    
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
        ArrayList<ConsultaClientes> lista = new ArrayList<>();
        for (int i = 0; i < tb_consulta.getRowCount(); i++) {
            String item = String.valueOf(i + 1);
            String ruc = String.valueOf(dtm.getValueAt(i, 1));
            String nombre = String.valueOf(dtm.getValueAt(i, 2));
            
            String telefono = String.valueOf(dtm.getValueAt(i, 3));
            
            String direccion = String.valueOf(dtm.getValueAt(i, 4));
            String plaza = String.valueOf(dtm.getValueAt(i, 5));
            String contacto = String.valueOf(dtm.getValueAt(i, 6));
            String idCliente = String.valueOf(dtm.getValueAt(i, 7));

            lista.add(new ConsultaClientes(item, ruc, nombre, telefono, direccion, plaza, contacto, idCliente));
        }
        return lista;
    }

    private HashMap SetParametros() {
        HashMap mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        mapa.put("total", tx_resultado.getText());
        mapa.put("usuario", usu.getNombre());
        return mapa;
    }

    private void BorrarTabla() {
        int numRows = dtm.getRowCount();
        for (int i = 0; i < numRows; i++) {
            dtm.removeRow(0);
        }
    }

    private void Listar_Clientes(Iterator it) {
        int i = 1;
        while (it.hasNext()) {
            Object[] res = (Object[]) it.next();
            String telefonos = "";
            String telefono1 = String.valueOf(res[2]);
            String telefono2 = String.valueOf(res[3]);
            
            if ( res[2] != null && res[3] != null) {
                if ( !("".equals(telefono1) && "".equals(telefono2)) ) {
                    telefonos = res[2] + ", " + res[3];
                }
                
            } else if ( res[2] != null ){
                telefonos = (res[2] == null) ? "" : String.valueOf(res[2]);
                
            } else if ( res[3] != null ){
                telefonos = (res[3] == null) ? "" : String.valueOf(res[3]);
            }
            Object[] row = {i++, res[0], res[1], telefonos, res[4], res[5], res[6], res[7]};
            dtm.addRow(row);
        }
        tx_resultado.setText(String.valueOf(i - 1));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_datos;
    public javax.swing.JButton bt_exportar;
    public javax.swing.JButton bt_filtrar;
    public javax.swing.JButton bt_imprimir;
    public javax.swing.JButton bt_salir;
    public javax.swing.JComboBox cb_tipo;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelConCli;
    public javax.swing.JTable tb_consulta;
    public javax.swing.JTextField tx_busq;
    public javax.swing.JTextField tx_resultado;
    // End of variables declaration//GEN-END:variables
}
