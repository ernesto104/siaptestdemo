package Presentacion.CuentasxCobrarFacturas;

import Entidades.Vendedores;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Servicio_CobrarFacturas;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Vendedor;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_FiltrarCuentasxCobrar extends javax.swing.JFrame {

    DefaultTableModel m;
    FREP042 lc;
    List listaFacturas, cantidadpagos;
    Servicio_CobrarFacturas sc;
    Servicio_Cliente scl;
    BigDecimal num;
    BigDecimal cont_Fac_G;
    BigDecimal cont_Fac_C;
    BigDecimal cont_Sal_G;
    BigDecimal cont_Sal_C;
    
    MENU001 me;

    public IU_FiltrarCuentasxCobrar(FREP042 lc, MENU001 me) {
        initComponents();
        setLocationRelativeTo(null);
        
        me = new MENU001(lc.getUsuario().getNombre(), lc.getRolElegido(), 0, 0);
        this.me = me;
        
        scl = new Servicio_Cliente(null);
        cont_Fac_G = new BigDecimal(0);
        cont_Fac_C = new BigDecimal(0);
        cont_Sal_G = new BigDecimal(0);
        cont_Sal_C = new BigDecimal(0);
        num = new BigDecimal(100);

        sc = new Servicio_CobrarFacturas();
        m = (DefaultTableModel) lc.tablaFactura.getModel();
        this.lc = lc;
        iniciarCombos();
    }

    public void iniciarCombos() {
        Servicio_Cliente sc = new Servicio_Cliente(null);
        Servicio_Vendedor sv = new Servicio_Vendedor(null);

        for ( Object cli : sc.Consulta_plaza() ) {
            if ( cli != null ) {
                comboPlaza.addItem(cli.toString());
            }
        }

        for ( Vendedores vend : sv.Obtener_Lista_Objetos_OrderNombre() ) {
            comboVendedor.addItem(vend.getNombre());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbPlaza = new javax.swing.JRadioButton();
        rbVend = new javax.swing.JRadioButton();
        rbFact = new javax.swing.JRadioButton();
        rbFecha = new javax.swing.JRadioButton();
        rbMonto = new javax.swing.JRadioButton();
        comboPlaza = new javax.swing.JComboBox();
        comboVendedor = new javax.swing.JComboBox();
        txt_Factura = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        txtMonto = new javax.swing.JTextField();
        botBuscar = new javax.swing.JButton();
        botSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        buttonGroup1.add(rbPlaza);
        rbPlaza.setText("1 Plaza");
        rbPlaza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbPlazaMouseReleased(evt);
            }
        });
        rbPlaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlazaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbVend);
        rbVend.setText("1 Vendedor");
        rbVend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbVendMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbFact);
        rbFact.setText("Factura");
        rbFact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbFactMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbFecha);
        rbFecha.setText("Fecha");
        rbFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbFechaMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbMonto);
        rbMonto.setText("Monto");
        rbMonto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbMontoMouseReleased(evt);
            }
        });

        comboPlaza.setEnabled(false);
        comboPlaza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboPlazaMouseReleased(evt);
            }
        });
        comboPlaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboPlazaKeyTyped(evt);
            }
        });

        comboVendedor.setEnabled(false);
        comboVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboVendedorMouseReleased(evt);
            }
        });
        comboVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboVendedorActionPerformed(evt);
            }
        });
        comboVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboVendedorKeyTyped(evt);
            }
        });

        txt_Factura.setEnabled(false);
        txt_Factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_FacturaKeyTyped(evt);
            }
        });

        txtFecha.setEnabled(false);
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtFechaMouseReleased(evt);
            }
        });
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaKeyTyped(evt);
            }
        });

        txtMonto.setEnabled(false);
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbVend)
                    .addComponent(rbPlaza)
                    .addComponent(rbFact)
                    .addComponent(rbFecha)
                    .addComponent(rbMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboPlaza, 0, 206, Short.MAX_VALUE)
                    .addComponent(comboVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbPlaza)
                            .addComponent(comboPlaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbVend)
                            .addComponent(comboVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFact)
                            .addComponent(txt_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbFecha))
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        botBuscar.setText("Buscar");
        botBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarActionPerformed(evt);
            }
        });

        botSalir.setText("Salir");
        botSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(botBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(botSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(botBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        botBuscar.getAccessibleContext().setAccessibleName("");
        botSalir.getAccessibleContext().setAccessibleName("");

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

    private void botSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSalirActionPerformed
        dispose();
    }//GEN-LAST:event_botSalirActionPerformed

    private void rbPlazaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbPlazaMouseReleased
        comboPlaza.setEnabled(true);
        comboVendedor.setEnabled(false);
        txt_Factura.setEnabled(false);
        txtFecha.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbPlazaMouseReleased

    private void rbVendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbVendMouseReleased
        comboPlaza.setEnabled(false);
        comboVendedor.setEnabled(true);
        txt_Factura.setEnabled(false);
        txtFecha.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbVendMouseReleased

    private void rbFactMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFactMouseReleased
        comboPlaza.setEnabled(false);
        comboVendedor.setEnabled(false);
        txt_Factura.setEnabled(true);
        txtFecha.setEnabled(false);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbFactMouseReleased

    private void rbFechaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFechaMouseReleased
        comboPlaza.setEnabled(false);
        comboVendedor.setEnabled(false);
        txt_Factura.setEnabled(false);
        txtFecha.setEnabled(true);
        txtMonto.setEnabled(false);
    }//GEN-LAST:event_rbFechaMouseReleased

    private void rbMontoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMontoMouseReleased
        comboPlaza.setEnabled(false);
        comboVendedor.setEnabled(false);
        txt_Factura.setEnabled(false);
        txtFecha.setEnabled(false);
        txtMonto.setEnabled(true);
    }//GEN-LAST:event_rbMontoMouseReleased

    private void botBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarActionPerformed
        Servicio_CobrarFacturas sca = new Servicio_CobrarFacturas();

        if ( rbPlaza.isSelected() ) {
            String plaza = comboPlaza.getSelectedItem().toString();
            
            if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = sca.Listar_Cuentas_General_Plaza(plaza);
                repetirproceso(li1);
                
            } else {
                List li1 = sca.Listar_Cuentas_xCobrar_Plaza(plaza);
                repetirproceso(li1);
            }
            lc.t1.setText("Plaza : ");
            lc.t2.setText(comboPlaza.getSelectedItem().toString());
            lc.t3.setText("");
            lc.t4.setText("");
        }

        if ( rbVend.isSelected() ) {
            String ven = comboVendedor.getSelectedItem().toString();
            
            if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = sca.Listar_Cuentas_General_Vendedor(ven);
                repetirproceso(li1);
                
            } else {
                List li1 = sca.Listar_Cuentas_xCobrar_Vendedor(ven);
                repetirproceso(li1);
            }
            lc.t1.setText("Vendedor : ");
            lc.t2.setText(comboVendedor.getSelectedItem().toString());
            lc.t3.setText("");
            lc.t4.setText("");
        }

        if ( rbFact.isSelected() ) {
            String fac = txt_Factura.getText();
            
            if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = sca.Listar_Cuentas_General_Factura(fac);
                repetirproceso(li1);
                
            } else {
                List li1 = sca.Listar_Cuentas_xCobrar_Factura(fac);
                repetirproceso(li1);
            }
            lc.t1.setText("");
            lc.t2.setText("");
            lc.t3.setText("");
            lc.t4.setText("");
        }

        if ( rbFecha.isSelected() ) {
            //String fecha = txtFecha.getDate().toString();
            if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = sca.Listar_Cuentas_General_Fecha(txtFecha.getDate());
                repetirproceso(li1);
                
            } else {
                List li1 = sca.Listar_Cuentas_xCobrar_Fecha(txtFecha.getDate());
                repetirproceso(li1);
            }
            lc.t1.setText("");
            lc.t2.setText("");
            lc.t3.setText("");
            lc.t4.setText("");

        }

        if ( rbMonto.isSelected() ) {
            String monto = txtMonto.getText();
            
            if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                List li1 = sca.Listar_Cuentas_General_Monto(monto);
                repetirproceso(li1);
                
            } else {
                List li1 = sca.Listar_Cuentas_xCobrar_Monto(monto);
                repetirproceso(li1);
            }
            lc.t1.setText("");
            lc.t2.setText("");
            lc.t3.setText("");
            lc.t4.setText("");
        }
    }//GEN-LAST:event_botBuscarActionPerformed

    private void comboPlazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboPlazaKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            botBuscar.doClick();
        }
    }//GEN-LAST:event_comboPlazaKeyTyped

    private void comboVendedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboVendedorKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            botBuscar.doClick();
        }
    }//GEN-LAST:event_comboVendedorKeyTyped

    private void txt_FacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_FacturaKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            botBuscar.doClick();
        }
    }//GEN-LAST:event_txt_FacturaKeyTyped

    private void txtFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            botBuscar.doClick();
        }
    }//GEN-LAST:event_txtFechaKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            botBuscar.doClick();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void comboPlazaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboPlazaMouseReleased
    }//GEN-LAST:event_comboPlazaMouseReleased

    private void comboVendedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboVendedorMouseReleased
    }//GEN-LAST:event_comboVendedorMouseReleased

    private void txtFechaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMouseReleased
    }//GEN-LAST:event_txtFechaMouseReleased

    private void comboVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboVendedorActionPerformed

    private void rbPlazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPlazaActionPerformed

    public void repetirproceso(List li1) {
        m.getDataVector().removeAllElements();
        m.fireTableDataChanged();
        int tam = li1.size();
        
        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                String nom = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getNombre();
                String ruc = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getRuc();
                
                String facturado = "0.00", pagado = "0.00", saldo = "0.00";
                String estado = String.valueOf(a[11]);
                String columnaCliente = String.valueOf(a[4]);
                if ( "ANULADO".equals(estado) ) {
                    columnaCliente = "(ANULADO) " + a[4];
                } else {
                    facturado = formatearMonetario(String.valueOf(a[5]), 2);
                    pagado = formatearMonetario(String.valueOf(a[6]), 2);
                    saldo = formatearMonetario(String.valueOf(a[7]), 2);
//                }
                
//                Object[] fila = {a[0], a[1], a[2], a[3], ruc, nom, a[10],a[5], a[6], a[7], a[8], false};
                Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, a[8], false};
                m.addRow(fila);
                }
            }
            lc.txtTotalFacturado.setText(cont_Fac_C.toString());
            lc.txtTotalSaldo.setText(cont_Sal_C.toString());
            MathContext mc1 = new MathContext(cont_Fac_C.precision(), RoundingMode.HALF_UP);
            MathContext mc2 = new MathContext(cont_Sal_C.precision(), RoundingMode.HALF_UP);

            BigDecimal x = BigDecimal.valueOf(Double.parseDouble(me.txtValorVenta.getText()));
            BigDecimal a = cont_Fac_C.divide(x, mc1);
            BigDecimal b = cont_Sal_C.divide(x, mc2);

            lc.txtTotFacDolar.setText(a.toString());
            lc.txttotalSaldoDolar.setText(b.toString());

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
    
    public String tipoDoc(String doc) {
        String tipo = "";
        switch ( doc ) {
            case "01":
                tipo = "F";
                break;
            case "02":
                tipo = "B";
                break;
            case "03":
                tipo = "NC";
                break;
            case "04":
                tipo = "ND";
                break;
            default:
                tipo = "Entrada no valida";
                break;
        }
        return tipo;
    }

    public String fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = sdf.format(ahora);
        return fecha;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botBuscar;
    private javax.swing.JButton botSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboPlaza;
    private javax.swing.JComboBox comboVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rbFact;
    private javax.swing.JRadioButton rbFecha;
    private javax.swing.JRadioButton rbMonto;
    private javax.swing.JRadioButton rbPlaza;
    private javax.swing.JRadioButton rbVend;
    public com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txt_Factura;
    // End of variables declaration//GEN-END:variables
}