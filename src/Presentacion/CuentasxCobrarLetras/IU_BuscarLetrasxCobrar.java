package Presentacion.CuentasxCobrarLetras;

import static Entidades.Otros.Constante.SELECCIONAR;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Servicio_CobrarFacturas;
import Servicios.CuentasxCobrar.Servicio_CobrarLetras;
import Servicios.Servicio_Cliente;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keily
 * @author modified : Ledis Rivera (2016)
 */
public class IU_BuscarLetrasxCobrar extends javax.swing.JFrame {

    DefaultTableModel m;
    FREP043 lc;
    List listaFacturas, cantidadpagos;
    Servicio_CobrarFacturas sc;
    Servicio_Cliente scl;
    BigDecimal num;
    BigDecimal cont_Emi_C;
    BigDecimal cont_Emi_D;
    BigDecimal cont_Sal_C;
    BigDecimal cont_Sal_D;
    
    MENU001 me;
    private Object date;

    public IU_BuscarLetrasxCobrar(FREP043 lc, MENU001 me) {
        initComponents();
        setLocationRelativeTo(null);
        
        me = new MENU001(lc.getUsuario().getNombre(), lc.getRolSeleccionado(), 0, 0);
        this.me = me;
        
        scl = new Servicio_Cliente(null);
        cont_Emi_C = new BigDecimal(0);
        cont_Emi_D = new BigDecimal(0);
        cont_Sal_C = new BigDecimal(0);
        cont_Sal_D = new BigDecimal(0);
        num = new BigDecimal(100);

        sc = new Servicio_CobrarFacturas();
        m = (DefaultTableModel) lc.tablaLetras.getModel();
        this.lc = lc;
        iniciarCombos();
    }

    public void repetirproceso(List li1) {
        m.getDataVector().removeAllElements();
        m.fireTableDataChanged();
        int tam = li1.size();
        
        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[5].equals("US$") ) {
                    cont_Emi_C = cont_Emi_C.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                    
                } else {
                    cont_Emi_D = cont_Emi_D.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
//                Object[] fila = {a[0], a[1], a[2], false, a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], false};
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
                m.addRow(fila);
            }
            lc.txtemisionS.setText(cont_Emi_C.toString());
            lc.txtsaldopagarS.setText(cont_Sal_C.toString());
            lc.txtemisionD.setText(cont_Emi_D.toString());
            lc.txtsaldopagarD.setText(cont_Sal_D.toString());

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }
        dispose();
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
    
    public void iniciarCombos() {
        Servicio_Cliente scli = new Servicio_Cliente(null);
        
        for ( Object cli : scli.Consulta_Nombre_Cliente() ) {
            if ( cli != null ) {
                comboCliente.addItem(cli.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbCliente = new javax.swing.JRadioButton();
        rbRuc = new javax.swing.JRadioButton();
        rbFactura = new javax.swing.JRadioButton();
        rbLetra = new javax.swing.JRadioButton();
        rbFechaV = new javax.swing.JRadioButton();
        comboCliente = new javax.swing.JComboBox();
        txtFactura = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        txtNLetra = new javax.swing.JTextField();
        txtFechaVencimiento = new com.toedter.calendar.JDateChooser();
        rbMonto = new javax.swing.JRadioButton();
        txtMonto = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        buttonGroup1.add(rbCliente);
        rbCliente.setText("Por Cliente");
        rbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbClienteMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbRuc);
        rbRuc.setText("Por RUC");
        rbRuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbRucMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbFactura);
        rbFactura.setText("Factura");
        rbFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbFacturaMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbLetra);
        rbLetra.setText("Nº de Letra");
        rbLetra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbLetraMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbFechaV);
        rbFechaV.setText("Fecha de Vencimiento");
        rbFechaV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbFechaVMouseReleased(evt);
            }
        });
        rbFechaV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFechaVActionPerformed(evt);
            }
        });

        comboCliente.setEnabled(false);

        txtFactura.setEnabled(false);

        txtRuc.setEnabled(false);

        txtNLetra.setEnabled(false);

        txtFechaVencimiento.setEnabled(false);

        buttonGroup1.add(rbMonto);
        rbMonto.setText("Monto");
        rbMonto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbMontoMouseReleased(evt);
            }
        });

        txtMonto.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbRuc)
                    .addComponent(rbCliente)
                    .addComponent(rbFactura)
                    .addComponent(rbLetra)
                    .addComponent(rbFechaV)
                    .addComponent(rbMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtFactura, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNLetra, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFechaVencimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addComponent(txtMonto, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCliente)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbRuc)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFactura)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLetra)
                    .addComponent(txtNLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbFechaV)
                    .addComponent(txtFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(botonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String convertirfecha(Date date) {        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(date);
        return fecha;
    }

    private void rbFechaVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFechaVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbFechaVActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        Servicio_CobrarLetras cl = new Servicio_CobrarLetras();
        if ( rbCliente.isSelected() ) { // 1. POR CLIENTE
            String cliente = comboCliente.getSelectedItem().toString();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_Cliente(cliente);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_Cliente(cliente);
                repetirproceso(li1);
            }
            lc.t1.setText("Cliente : ");
            lc.t2.setText(comboCliente.getSelectedItem().toString());
            lc.t3.setText("");
            lc.t4.setText("");
        }
        
        if ( rbRuc.isSelected() ) { // 2. POR RUC
            String ruc = txtRuc.getText();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_RUC(ruc);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_RUC(ruc);
                repetirproceso(li1);
            }
            lc.t1.setText("RUC : ");
            lc.t2.setText(txtRuc.getText());
            lc.t3.setText("");
            lc.t4.setText("");
        }
        
        if ( rbFactura.isSelected() ) { // 3. POR FACTURA
            String factura = txtFactura.getText();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_factura(factura);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_factura(factura);
                repetirproceso(li1);
            }
            lc.t1.setText("Factura : ");
            lc.t2.setText(txtFactura.getText());
            lc.t3.setText("");
            lc.t4.setText("");
        }
        
        if ( rbLetra.isSelected() ) { // 4. POR N° DE LETRA
            String letra = txtNLetra.getText();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_nletra(letra);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_nletra(letra);
                repetirproceso(li1);
            }
            lc.t1.setText("Nº Letra : ");
            lc.t2.setText(txtNLetra.getText());
            lc.t3.setText("");
            lc.t4.setText("");
        }
        
        if ( rbFechaV.isSelected() ) { // 5. POR FECHA DE VENCIMIENTO
            Date fechav = txtFechaVencimiento.getDate();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_fechaVencimiento(fechav);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_fechaVencimiento(fechav);
                repetirproceso(li1);
            }
            lc.t1.setText("F.Vencimiento : ");
            lc.t2.setText(convertirfecha(txtFechaVencimiento.getDate()));
            lc.t3.setText("");
            lc.t4.setText("");
        }
        
        if ( rbMonto.isSelected() ) { // 6. POR MONTO
            String monto = txtMonto.getText();
            
            if ( lc.textoLetras.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = cl.Listar_Cuentas_General_monto(monto);
                repetirproceso(li1);
                
            } else {
                List li1 = cl.Listar_Cuentas_xCobrar_monto(monto);
                repetirproceso(li1);
            }
            lc.t1.setText("Monto : ");
            lc.t2.setText(txtMonto.getText());
            lc.t3.setText("");
            lc.t4.setText("");
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void rbClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbClienteMouseReleased
        comboCliente.setEnabled(true);
        txtRuc.setEnabled(false);
        txtFactura.setEnabled(false);
        txtNLetra.setEnabled(false);
        txtFechaVencimiento.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbClienteMouseReleased

    private void rbRucMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbRucMouseReleased
        comboCliente.setEnabled(false);
        txtRuc.setEnabled(true);
        txtFactura.setEnabled(false);
        txtNLetra.setEnabled(false);
        txtFechaVencimiento.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbRucMouseReleased

    private void rbFacturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFacturaMouseReleased
        comboCliente.setEnabled(false);
        txtRuc.setEnabled(false);
        txtFactura.setEnabled(true);
        txtNLetra.setEnabled(false);
        txtFechaVencimiento.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbFacturaMouseReleased

    private void rbLetraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLetraMouseReleased
        comboCliente.setEnabled(false);
        txtRuc.setEnabled(false);
        txtFactura.setEnabled(false);
        txtNLetra.setEnabled(true);
        txtFechaVencimiento.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbLetraMouseReleased

    private void rbFechaVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFechaVMouseReleased
        comboCliente.setEnabled(false);
        txtRuc.setEnabled(false);
        txtFactura.setEnabled(false);
        txtNLetra.setEnabled(false);
        txtFechaVencimiento.setEnabled(true);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbFechaVMouseReleased

    private void rbMontoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMontoMouseReleased
        comboCliente.setEnabled(false);
        txtRuc.setEnabled(false);
        txtFactura.setEnabled(false);
        txtNLetra.setEnabled(false);
        txtFechaVencimiento.setEnabled(false);
        txtMonto.setEnabled(true);
    }//GEN-LAST:event_rbMontoMouseReleased

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
       dispose();
    }//GEN-LAST:event_botonSalirActionPerformed
    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(IU_FiltrarCuentasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarCuentasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarCuentasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(IU_FiltrarCuentasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new IU_BuscarLetrasxCobrar().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rbCliente;
    private javax.swing.JRadioButton rbFactura;
    private javax.swing.JRadioButton rbFechaV;
    private javax.swing.JRadioButton rbLetra;
    private javax.swing.JRadioButton rbMonto;
    private javax.swing.JRadioButton rbRuc;
    private javax.swing.JTextField txtFactura;
    private com.toedter.calendar.JDateChooser txtFechaVencimiento;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNLetra;
    private javax.swing.JTextField txtRuc;
    // End of variables declaration//GEN-END:variables
}