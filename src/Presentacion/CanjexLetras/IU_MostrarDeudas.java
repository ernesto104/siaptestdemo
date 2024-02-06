package Presentacion.CanjexLetras;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import static Entidades.Otros.Constante.DOLAR_STANDARD;
import static Entidades.Otros.Constante.SOL;
import Entidades.Otros.PagoCabec;
import Servicios.Servicio_Cabeces;
import Servicios.Util;
import Servicios.facturacion.Servicio_Documentos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class IU_MostrarDeudas extends javax.swing.JFrame {

    Clientes cliente;
    Servicio_Cabeces cabeces;
    DefaultTableModel dtm;
    List listaCabeces;
    double total;
    Util util;
    
    public IU_MostrarDeudas(Clientes c) {
        initComponents();
        util = new Util();
        cabeces = new Servicio_Cabeces();
        dtm = (DefaultTableModel) tb_deudas.getModel();
        cliente = c;
        
        setAnchoColumnas();
        SetCliente();
        ListarDeudas();
        setLocationRelativeTo(null);
        setVisible(true);
        lb_monto.setText("0.00");
        lblMoneda.setText(DOLAR_STANDARD);
        
        alinearColumnaDerecha();
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_deudas.getColumnModel().getColumn(4).setCellRenderer(tcr); // Pendiente
        tb_deudas.getColumnModel().getColumn(7).setCellRenderer(tcr); // Facturado
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_deudas.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_deudas.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_deudas.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            
            switch (i) {
                case 0:
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 6:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 7:
                    anchoColumna = (10 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    private void ListarDeudas() {
        Servicio_Documentos temporal = new Servicio_Documentos();
        listaCabeces = cabeces.ObtenerDeudas(cliente.getIdcliente());
        Iterator it = listaCabeces.iterator();
        
        while ( it.hasNext() ) {
            Object[] resul = (Object[]) it.next();
            
            if ( resul[6] == null ) {
                resul[6] = 0.0;
            }
            double pendiente = (double) resul[4] - (double) resul[6];
            pendiente = util.Redondear2Decimales(pendiente );
            
            int mon = Integer.parseInt(String.valueOf(resul[9]));
            String moneda = (mon == 1 ? SOL : DOLAR_STANDARD);
            
            Object[] row = {false,
                            TipoDoc(String.valueOf(resul[1])),
                            resul[3],
                            cliente.getNombre(),
                            util.DosDecimales(pendiente),
                            resul[8] == null ? cliente.getPlaza() : temporal.getSucursal((int) resul[8], cliente.getIdcliente()),
                            moneda,
                            util.DosDecimales((double)resul[4]),
                            resul[7] == null ? "" : resul[7]
            };
            dtm.addRow(row);
        }
    }

    private void SetCliente() {
        tx_nombre.setText(cliente.getNombre());
        tx_direccion.setText(cliente.getDireccion());
        tx_ruc.setText(cliente.getRuc());
    }

    private void CalcularMonto() {
        double monto = 0.0;
        
        for ( int i = 0; i < dtm.getRowCount(); i++ ) {
            if ( (boolean)dtm.getValueAt(i, 0) == true ) {
                String tipoDocumento = String.valueOf(dtm.getValueAt(i, 1));
                
                if ( "NOTA DE CREDITO".equals(tipoDocumento) ) {
//                if ( dtm.getValueAt(i, 1).equals("03") ) { 
                    monto -= Double.parseDouble(String.valueOf(dtm.getValueAt(i, 7))); 
                    // Antes 6, cuando no había Columna "moneda"
                } else {
                    monto += Double.parseDouble(String.valueOf(dtm.getValueAt(i, 4)));
                }
            }
        }
        monto = util.Redondear2Decimales(monto);
        BigDecimal monto_total = new BigDecimal(String.valueOf(monto));
        lb_monto.setText(util.DosDecimales(monto_total.doubleValue()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tx_ruc = new javax.swing.JTextField();
        tx_nombre = new javax.swing.JTextField();
        tx_direccion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_deudas = new javax.swing.JTable();
        lb_total = new javax.swing.JLabel();
        ch_seleccionar = new javax.swing.JCheckBox();
        lb_monto = new javax.swing.JLabel();
        lblMoneda = new javax.swing.JLabel();
        bt_continuar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));

        jLabel1.setText("Nro RUC");

        jLabel2.setText("Nombre");

        jLabel3.setText("Direccion");

        tx_ruc.setEnabled(false);

        tx_nombre.setEnabled(false);

        tx_direccion.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(396, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Deudas"));

        tb_deudas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionar", "Tipo", "Nro Doc", "Nombre Cliente", "Pendiente", "Plaza", "Moneda", "Facturado", "Doc Referencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_deudas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_deudasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_deudas);

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_total.setText("TOTAL :");

        ch_seleccionar.setText("Seleccionar Todo");
        ch_seleccionar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ch_seleccionarItemStateChanged(evt);
            }
        });

        lb_monto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lblMoneda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMoneda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMoneda.setText("US $");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ch_seleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ch_seleccionar)
                        .addComponent(lb_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMoneda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bt_continuar.setText("Continuar");
        bt_continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_continuarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmación",JOptionPane.YES_NO_OPTION);
        if ( opcion == JOptionPane.YES_OPTION ) {
            dispose();
        }
    }//GEN-LAST:event_bt_salirActionPerformed

    private void tb_deudasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_deudasMouseReleased
        if ( tb_deudas.columnAtPoint(evt.getPoint()) == 0 ) {
            if ( validarTodo1Moneda() ) {
                CalcularMonto();
            } else {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, "Seleccionar todos los documentos de la misma Moneda","ERROR", 
                                      JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tb_deudasMouseReleased

    private boolean validarTodo1Moneda() {
        DefaultTableModel modelo = (DefaultTableModel) tb_deudas.getModel();
        int numFilaSelect = tb_deudas.getSelectedRow();
        String moneda = String.valueOf(modelo.getValueAt(numFilaSelect, 6));
        int cantFilasSelect = contarSeleccionados();
        
        if ( cantFilasSelect == 1 ) {
            if ( DOLAR_STANDARD.equals(moneda) ) {
                lblMoneda.setText(DOLAR_STANDARD);
            } else {
                lblMoneda.setText(SOL);
            }
            return true;
            
        } else if ( cantFilasSelect > 1 ) {
            if ( moneda.equals(lblMoneda.getText()) ) {
                return true;
                
            } else {
                modelo.setValueAt(false, numFilaSelect, 0);
                return false;
            }
        } else {
           return true;
        }
    }
    
    private int contarSeleccionados() {
        DefaultTableModel modelo = (DefaultTableModel) tb_deudas.getModel();
        int numFilas = modelo.getRowCount();
        int seleccionados = 0;
        for ( int fila = 0; fila < numFilas; fila++ ) {
            boolean seleccionado =  (boolean) modelo.getValueAt(fila, 0);
            if ( seleccionado )  {
                seleccionados++;
            }
        }
        return seleccionados;
    }
    
    private void bt_continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_continuarActionPerformed
        String Total = lb_monto.getText();
        
        if ( Double.parseDouble(Total) > 0.0 ) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
            
            if ( opcion == JOptionPane.YES_OPTION ) {
                listaCabeces = Obtener_Seleccionados();
                IU_ModuloCanje canje = new IU_ModuloCanje(cliente, (ArrayList<PagoCabec>) listaCabeces, 
                                                        Double.parseDouble(lb_monto.getText()), lblMoneda.getText());
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_bt_continuarActionPerformed

    private void ch_seleccionarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ch_seleccionarItemStateChanged
        if ( ch_seleccionar.isSelected() ) {
            for ( int i = 0; i < dtm.getRowCount(); i++ ) {
                dtm.setValueAt(true, i, 0);
            }
        } else {
            for ( int i = 0; i < dtm.getRowCount(); i++ ) {
                dtm.setValueAt(false, i, 0);
            }
        }
        CalcularMonto();
    }//GEN-LAST:event_ch_seleccionarItemStateChanged
    
    private ArrayList<PagoCabec> Obtener_Seleccionados() {
        ArrayList<PagoCabec> nueva = new ArrayList<>();
        
        for ( int i = 0; i < dtm.getRowCount(); i++ ) {
            Object[] fila = (Object[]) listaCabeces.get(i);
//            System.out.println("fil[6]:" + fila[6]);
            double pendiente = (double) fila[4] - (double) fila[6];
            pendiente = util.Redondear2Decimales(pendiente );
            
            if ( (boolean)dtm.getValueAt(i, 0) == true ) {
                String tipotra = String.valueOf(fila[0]);
                String tipodoc = String.valueOf(fila[1]);
                String nroserie = String.valueOf(fila[2]);
                String nrodoc = String.valueOf(fila[3]);
                
                Cabeces c = cabeces.obtenerCabecera_id(new CabecesId(tipotra, tipodoc, nroserie, nrodoc));
                PagoCabec letra = new PagoCabec();
                letra.setCabec(c);
                letra.setImportePendiente(pendiente);
                nueva.add(letra);
            }
        }
        return nueva;
    }
    
    private String TipoDoc(String tipo){
        switch ( tipo ) {
            case "01":
                return "FACTURA";
            case "02":
                return "BOLETA";
            case "03":
                return "NOTA DE CREDITO";
            case "04":
                return "NOTA DE DEBITO";
        }
        return "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_continuar;
    private javax.swing.JButton bt_salir;
    private javax.swing.JCheckBox ch_seleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_monto;
    private javax.swing.JLabel lb_total;
    public javax.swing.JLabel lblMoneda;
    private javax.swing.JTable tb_deudas;
    private javax.swing.JTextField tx_direccion;
    private javax.swing.JTextField tx_nombre;
    private javax.swing.JTextField tx_ruc;
    // End of variables declaration//GEN-END:variables
}