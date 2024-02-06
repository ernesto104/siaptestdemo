package Presentacion.CuentasxCobrarFacturas;

import Entidades.Clientes;
import Entidades.Usuarios;
import Mantenimiento.TipoCambioDAO;
import Servicios.CuentasxCobrar.Servicio_CobrarFacturas;
import Servicios.Servicio_Cliente;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_1Cliente extends javax.swing.JFrame {
    Servicio_Cliente consulta;    
    protected Servicio_Cliente sc;
    
    DefaultListModel modelo;
    DefaultTableModel mod;
    DefaultTableModel dtm;    

    List listaFacturas;
    List cantidadpagos;
    FREP042 lc;
    BigDecimal num;
    BigDecimal cero;
    BigDecimal cont_Fac_G;
    BigDecimal cont_Fac_C;
    BigDecimal cont_Sal_G;
    BigDecimal cont_Sal_C;

    public IU_1Cliente(FREP042 lc) {
        this.lc = lc;
        mod = (DefaultTableModel) lc.tablaFactura.getModel();
        cont_Fac_G = new BigDecimal(0);
        cont_Fac_C = new BigDecimal(0);
        cont_Sal_G = new BigDecimal(0);
        cont_Sal_C = new BigDecimal(0);
        num = new BigDecimal(100);
        cero = new BigDecimal(0);
        sc = new Servicio_Cliente(null);
        initComponents();
        setLocationRelativeTo(null);
        //dtm = (DefaultTableModel) tb_consulta.getModel();
        Listar_Clientes(sc.get_ListaClientes().iterator());
    }

    private void Listar_Clientes(Iterator it) {
        modelo = new DefaultListModel();
        while (it.hasNext()) {
            Object[] res = (Object[]) it.next();
            modelo.addElement(res[1]);
        }
        listaClientes.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_numDoc1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList();
        btnBuscar = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tx_busq = new javax.swing.JTextField();

        txt_numDoc1.setEditable(false);
        txt_numDoc1.setBackground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Seleccione un Cliente:");

        listaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaClientesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(listaClientes);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        boton_Salir.setText("Salir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre de Cliente (Filtro) : ");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_busq, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tx_busq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = sdf.format(ahora);
        return fecha;
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if ( lc != null ) {
            Clientes clie = null;
            
            if ( listaClientes.getSelectedIndex() >= 0 ) {
                mod.getDataVector().removeAllElements();
                mod.fireTableDataChanged();
                Clientes cli = sc.getxNombre(String.valueOf(listaClientes.getSelectedValue()));
                clie = cli;
                Servicio_CobrarFacturas scab = new Servicio_CobrarFacturas();
                Servicio_Cliente scl = new Servicio_Cliente(null);

                if ( lc.TextoFacturas.getText().split(" ")[0].equals("TODAS") ) {
                    System.out.println("todas las facturas canceladas y por cobrar...(filtro x 1 cliente)");
                    lc.TextoFacturas.setText("TODAS LAS FACTURAS CANCELADAS Y POR COBRAR AL " + fechaSistema());
                    lc.t1.setText("Cliente : ");
                    lc.t2.setText(clie.getNombre());
                    lc.t3.setText("RUC : ");
                    lc.t4.setText(clie.getRuc());

                    System.out.println("idCliente:" + clie.getIdcliente());
                    List list = scab.Listar_Cuentas_General_Cliente(clie.getIdcliente());
                    
                    int tam = list.size();
                    System.out.println("tamaño de lista:" + tam);

                    if ( tam != 0 ) {
                        for ( int i = 0; i < tam; i++ ) {
                            Object[] a = (Object[]) list.get(i);
                            cont_Fac_G = cont_Fac_G.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                            cont_Sal_G = cont_Sal_G.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//                            String nom = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getNombre();
//                            String ruc = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getRuc();
//                            Object[] fila = {a[0], a[1], a[2], a[3], ruc, nom, a[5], a[6], a[7], a[8],false};
                            
                            String facturado = "0.00", pagado = "0.00", saldo = "0.00";
                            String estado = String.valueOf(a[11]);
                            String columnaCliente = String.valueOf(a[4]);
                            System.out.println("CLIENTE:" + columnaCliente);
                            System.out.println("estado:" + estado);
                            
                            if ( "ANULADO".equals(estado) ) {
                                columnaCliente = "(ANULADO) " + a[4];
                                
                            } else {
                                facturado = formatearMonetario(String.valueOf(a[5]), 2);
                                pagado = formatearMonetario(String.valueOf(a[6]), 2);
                                saldo = formatearMonetario(String.valueOf(a[7]), 2);
                            }
//                            Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], a[5], a[6], a[7], a[8], false};
                            Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, a[8], false};
                            mod.addRow(fila);
                        }

                        TipoCambioDAO dao = new TipoCambioDAO();

//                        lc.txtTotalFacturado.setText(cont_Fac_G.toString());
//                        lc.txtTotalSaldo.setText(cont_Sal_G.toString());
                        lc.txtTotalFacturado.setText("0.00");
                        lc.txtTotalSaldo.setText("0.00");
                        MathContext mc1 = new MathContext(cont_Fac_G.precision(), RoundingMode.HALF_UP);
                        MathContext mc2 = new MathContext(cont_Sal_G.precision(), RoundingMode.HALF_UP);

                        BigDecimal x = BigDecimal.valueOf(dao.Obtener_Objeto(fechasistema()).getValorventa());
                        BigDecimal a = cont_Fac_G.divide(x, mc1);
                        BigDecimal b = cont_Sal_G.divide(x, mc2);

//                        lc.txtTotFacDolar.setText(a.toString());
//                        lc.txttotalSaldoDolar.setText(b.toString());
                        lc.txtTotFacDolar.setText("0.00");
                        lc.txttotalSaldoDolar.setText("0.00");
                        lc.seleccion = false;
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe registros");
//                        lc.txtTotFacDolar.setText(cero.toString());
//                        lc.txttotalSaldoDolar.setText(cero.toString());
                        lc.txtTotFacDolar.setText("0.00");
                        lc.txttotalSaldoDolar.setText("0.00");
                    }
                } else {
                    System.out.println("facturas pendientes por cobrar...(filtro x 1 cliente)");
                    lc.TextoFacturas.setText("FACTURAS PENDIENTES POR COBRAR AL " + fechaSistema());
                    lc.t1.setText("Cliente : ");
                    lc.t2.setText(clie.getNombre());
                    lc.t3.setText("RUC : ");
                    lc.t4.setText(clie.getRuc());
                    System.out.println("ELSE...");
                    System.out.println("idCliente:" + clie.getIdcliente());
                    List list = scab.Listar_Cuentas_xCobrar_Cliente(clie.getIdcliente());
                    int tam = list.size();
                    System.out.println("tamaño de lista:" + tam);
                    
                    for ( int i = 0; i < tam; i++ ) {
                        Object[] a = (Object[]) list.get(i);
                        cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                        cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
//                        String nom = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getNombre();
//                        String ruc = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getRuc();
//                        Object[] fila = {a[0], a[1], a[2], a[3], ruc, nom, a[5], a[6], a[7], a[8],false};
                        
                        String facturado = "0.00", pagado = "0.00", saldo = "0.00";
                        String estadoFactura = String.valueOf(a[11]);
                        String columnaCliente = String.valueOf(a[4]);
//                        System.out.println("estadoFactura:" + );
                
                        if ( "ANULADO".equals(estadoFactura) ) {
                            columnaCliente = "(ANULADO) " + a[4];
                            
                        } else {
                            facturado = formatearMonetario(String.valueOf(a[5]), 2);
                            pagado = formatearMonetario(String.valueOf(a[6]), 2);
                            saldo = formatearMonetario(String.valueOf(a[7]), 2);
//                      }     JSP lo corrigio para exceptuar los anulados
                        Object[] fila = {a[0], a[1], a[2], a[3], a[9], columnaCliente, a[10], facturado, pagado, saldo, a[8], false};
//                        Object[] fila = {a[0], a[1], a[2], a[3], a[9], a[4], a[10], a[5], a[6], a[7], a[8], false};
                        mod.addRow(fila);
                        }
                    }

                    TipoCambioDAO dao = new TipoCambioDAO();
//                    lc.txtTotalFacturado.setText(cont_Fac_C.toString());
//                    lc.txtTotalSaldo.setText(cont_Sal_C.toString());
                    lc.txtTotalFacturado.setText("0.00");
                    lc.txtTotalSaldo.setText("0.00");
                    MathContext mc1 = new MathContext(cont_Fac_C.precision(), RoundingMode.HALF_UP);
                    MathContext mc2 = new MathContext(cont_Sal_C.precision(), RoundingMode.HALF_UP);

                    BigDecimal x = BigDecimal.valueOf(dao.Obtener_Objeto(fechasistema()).getValorventa());
                    BigDecimal a = cont_Fac_C.divide(x, mc1);
                    BigDecimal b = cont_Sal_C.divide(x, mc2);

                    lc.txtTotFacDolar.setText("0.00");
                    lc.txttotalSaldoDolar.setText("0.00");
//                    lc.txtTotFacDolar.setText(a.toString());
//                    lc.txttotalSaldoDolar.setText(b.toString());
                    lc.seleccion = false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Necesita seleccionar un cliente!", "Buscar Cliente", 2);
            }
        }
        dispose();
    }//GEN-LAST:event_btnBuscarActionPerformed

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
    
    public Date fechasistema() {
        Date ahora = new Date();
        return ahora;

    }

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_boton_SalirActionPerformed

    private void listaClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClientesMouseReleased
//        jButton1.doClick();
        
    }//GEN-LAST:event_listaClientesMouseReleased

    private void tx_busqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_busqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_busqActionPerformed

    private void tx_busqKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_busqKeyTyped
        if (evt.getKeyChar() == 10) {
           String cadena = tx_busq.getText(); 
           Listar_Clientes(sc.getCliente_Nombre_Filtro(cadena).iterator());
        }
    }//GEN-LAST:event_tx_busqKeyTyped
    
    public javax.swing.JButton bt_filtrar;    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Salir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaClientes;
    private javax.swing.JTextField tx_busq;
    private javax.swing.JTextField txt_numDoc1;
    // End of variables declaration//GEN-END:variables
}