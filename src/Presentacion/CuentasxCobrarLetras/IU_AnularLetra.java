/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.CuentasxCobrarLetras;

import Entidades.Cabeces;
import Entidades.CabecesId;
import static Entidades.Otros.Constante.SELECCIONAR;
import Entidades.Usuarios;
import Mantenimiento.CabecesDAO;
import Mantenimiento.CabecesIdDAO;
import Servicios.CuentasxCobrar.Servicio_CobrarLetras;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keily
 */
public class IU_AnularLetra extends javax.swing.JFrame {

    DefaultTableModel tabla;
    int filaseleccionada;
    JTable tt;
    private Usuarios usuario;
    BigDecimal cont_Emi_C;
    BigDecimal cont_Emi_D;
    BigDecimal cont_Sal_C;
    BigDecimal cont_Sal_D;
    FREP043 p;

    public IU_AnularLetra(FREP043 p,DefaultTableModel t, int fila, Usuarios usuario, JTable ta) {
        initComponents();
        tabla = t;
        filaseleccionada = fila;
        tt = ta;
        this.usuario = usuario;    
         this.p = p;
        cont_Emi_C = new BigDecimal(0.0);
        cont_Emi_D = new BigDecimal(0.0);
        cont_Sal_D = new BigDecimal(0.0);        
        cont_Sal_C = new BigDecimal(0.0);
       
        iniciarAnulacion();
        this.setLocationRelativeTo(null);
    }

    public void iniciarAnulacion() {
        txtnletra.setText(tabla.getValueAt(filaseleccionada, 1).toString());
        
        if ( tabla.getValueAt(filaseleccionada, 0) != null ) {
            txtcliente.setText(tabla.getValueAt(filaseleccionada, 0).toString());
        }
        String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
        txtimporte.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 8).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnletra = new javax.swing.JTextField();
        txtcliente = new javax.swing.JTextField();
        txtimporte = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("¿Esta usted seguro de Anular la Letra?");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nº de Letra: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Cliente: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Importe de Letra:");

        txtnletra.setEnabled(false);

        txtcliente.setEnabled(false);

        txtimporte.setEnabled(false);

        jButton1.setText("Anular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(71, 71, 71))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnletra, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnletra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(29, 29, 29))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Servicio_CobrarLetras cl = new Servicio_CobrarLetras();
        CabecesDAO da = new CabecesDAO();
        CabecesId id = new CabecesId();
        id.setTipotra("V");
        id.setTipodoc("06");
        id.setNrodocumento(tabla.getValueAt(filaseleccionada, 1).toString());
        Cabeces c = da.obtenerCabeceraPorDocumento_cerrar(id);
        c.setEstado("ANULADA");
        c.setUsuarios(usuario);
        c.setTotal(0.00);

        if ( cl.AnularLetra(c) ) {
            JOptionPane.showMessageDialog(null, "ANULACION DE LA LETRA", "La Letra ha sido anulada correctamente", JOptionPane.INFORMATION_MESSAGE);         

            p.textoLetras.setText("LETRAS PENDIENTES DE COBRANZA AL " + p.fechaSistema());
            DefaultTableModel modelo = (DefaultTableModel) p.tablaLetras.getModel();
            Servicio_CobrarLetras sc = new Servicio_CobrarLetras();
            List li1 = sc.Listar_Cuentas_x_Cobrar_Letras();
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
//                    Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], false};
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
//                        bancoLetra = SELECCIONAR; // DEBERIA SER VACÍO
                        bancoLetra = "";
                    }

                    String tipoLetra = String.valueOf(a[13]);
                    if ( a[13] == null ) {
                        tipoLetra = "LI";
                    }
                    
                    Object[] fila = {a[0], a[1], renovacion, a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], bancoLetra, a[10], false, tipoLetra};
                    modelo.addRow(fila);
                }
                p.txtemisionS.setText(cont_Emi_C.toString());
                p.txtsaldopagarS.setText(cont_Sal_C.toString());
                p.txtemisionD.setText(cont_Emi_D.toString());
                p.txtsaldopagarD.setText(cont_Sal_D.toString());

            } else {
                JOptionPane.showMessageDialog(null, "No existen registros");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ANULAR", "La Letra no ha sido anulada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(IU_AnularLetra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(IU_AnularLetra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(IU_AnularLetra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(IU_AnularLetra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new IU_AnularLetra().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtimporte;
    private javax.swing.JTextField txtnletra;
    // End of variables declaration//GEN-END:variables
}