package Presentacion.CuentasxCobrarSalidas;

import Presentacion.CuentasxCobrarFacturas.*;
import Entidades.Clientes;
import Mantenimiento.TipoCambioDAO;
import Servicios.CuentasxCobrar.Servicio_CobrarFacturas;
import Servicios.CuentasxCobrar.Servicio_CobrarSalidasVarias;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_1Cliente extends javax.swing.JFrame {

    protected Servicio_Cliente sc;
    DefaultListModel modelo;
    DefaultTableModel mod;
    List listaFacturas;
    List cantidadpagos;
    FREP070 lc;
    BigDecimal num;
    BigDecimal cero;
    BigDecimal cont_Fac_G;
    BigDecimal cont_Fac_C;
    BigDecimal cont_Sal_G;
    BigDecimal cont_Sal_C;

    public IU_1Cliente(FREP070 lc) {
        initComponents();
        this.lc = lc;
        mod = (DefaultTableModel) lc.tablaFactura.getModel();
        cont_Fac_G = new BigDecimal(0);
        cont_Fac_C = new BigDecimal(0);
        cont_Sal_G = new BigDecimal(0);
        cont_Sal_C = new BigDecimal(0);
        num = new BigDecimal(100);
        cero = new BigDecimal(0);
        sc = new Servicio_Cliente(null);
        setLocationRelativeTo(null);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();

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

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        boton_Salir.setText("Salir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
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
                                .addGap(54, 54, 54)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boton_Salir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (lc != null) {
            Clientes clie = null;
            if (listaClientes.getSelectedIndex() >= 0) {
                mod.getDataVector().removeAllElements();
                mod.fireTableDataChanged();
                System.out.println("señec" + listaClientes.getSelectedValue());
                Clientes cli = sc.getxNombre(String.valueOf(listaClientes.getSelectedValue()));
                clie = cli;
                System.out.println("cli" + cli.getNombre());
                Servicio_CobrarSalidasVarias scab = new Servicio_CobrarSalidasVarias();
                Servicio_Cliente scl = new Servicio_Cliente(null);
                System.out.println("entrooa clientes sk");
                if (lc.TextoFacturas.getText().split(" ")[0].equals("TODAS")) {

                    lc.TextoFacturas.setText("TODAS LAS SALIDAS VARIAS CANCELADAS Y POR COBRAR AL " + fechaSistema());
                    lc.t1.setText("Cliente : ");
                    lc.t2.setText(clie.getNombre());
                    lc.t3.setText("RUC : ");
                    lc.t4.setText(clie.getRuc());

                    List list = scab.Listar_Cuentas_General_Cliente(clie.getIdcliente());
                    int tam = list.size();
                    System.out.println("entrooa clientes sk");
                    if (tam != 0) {
/*
                        for (int i = 0; i < tam; i++) {
                            Object[] a = (Object[]) list.get(i);
                            cont_Fac_G = cont_Fac_G.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                            cont_Sal_G = cont_Sal_G.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));

                            String nom = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getNombre();
                            String ruc = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getRuc();
                            Object[] fila = {a[0], a[1], a[2], a[3], ruc, nom, a[5], a[6], a[7], a[8], false};
                            mod.addRow(fila);
                        }
*/
                        for (int i = 0; i < tam; i++) {
                            Object[] a = (Object[]) list.get(i);
                            cont_Fac_G = cont_Fac_G.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                            cont_Sal_G = cont_Sal_G.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                            Object[] fila = {a[0], a[1], a[2], a[3], a[8], a[4], "US$", a[5], a[6], a[7], false};
                            mod.addRow(fila);
                        }

                        TipoCambioDAO dao = new TipoCambioDAO();

                        lc.txtTotalFacturado.setText(cont_Fac_G.toString());
                        lc.txtTotalSaldo.setText(cont_Sal_G.toString());
                        MathContext mc1 = new MathContext(cont_Fac_G.precision(), RoundingMode.HALF_UP);
                        MathContext mc2 = new MathContext(cont_Sal_G.precision(), RoundingMode.HALF_UP);

                        BigDecimal x = BigDecimal.valueOf(dao.Obtener_Objeto(fechasistema()).getValorventa());
                        BigDecimal a = cont_Fac_G.divide(x, mc1);
                        BigDecimal b = cont_Sal_G.divide(x, mc2);

                        lc.txtTotFacDolar.setText(a.toString());
                        lc.txttotalSaldoDolar.setText(b.toString());

                        lc.seleccion = false;

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe registros");
                        lc.txtTotFacDolar.setText(cero.toString());
                        lc.txttotalSaldoDolar.setText(cero.toString());

                    }

                } else {
                    lc.TextoFacturas.setText("SALIDAS VARIAS PENDIENTES POR COBRAR AL " + fechaSistema());
                    lc.t1.setText("Cliente : ");
                    lc.t2.setText(clie.getNombre());
                    lc.t3.setText("RUC : ");
                    lc.t4.setText(clie.getRuc());
                    List list = scab.Listar_Cuentas_xCobrar_Cliente(clie.getIdcliente());
                    int tam = list.size();
                    for (int i = 0; i < tam; i++) {
                        Object[] a = (Object[]) list.get(i);
                        cont_Fac_C = cont_Fac_C.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                        cont_Sal_C = cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                        String nom = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getNombre();
                        String ruc = scl.Obtener_Cliente_Por_Nombre(a[4].toString()).getRuc();
                        Object[] fila = {a[0], a[1], a[2], a[3], ruc, nom, "US$", a[5], a[6], a[7], false};
                        mod.addRow(fila);
                    }

                    TipoCambioDAO dao = new TipoCambioDAO();

                    lc.txtTotalFacturado.setText(cont_Fac_C.toString());
                    lc.txtTotalSaldo.setText(cont_Sal_C.toString());
                    MathContext mc1 = new MathContext(cont_Fac_C.precision(), RoundingMode.HALF_UP);
                    MathContext mc2 = new MathContext(cont_Sal_C.precision(), RoundingMode.HALF_UP);

                    BigDecimal x = BigDecimal.valueOf(dao.Obtener_Objeto(fechasistema()).getValorventa());
                    BigDecimal a = cont_Fac_C.divide(x, mc1);
                    BigDecimal b = cont_Sal_C.divide(x, mc2);

                    lc.txtTotFacDolar.setText(a.toString());
                    lc.txttotalSaldoDolar.setText(b.toString());
                    lc.seleccion = false;

                }

            } else {
                JOptionPane.showMessageDialog(null, "Necesita seleccionar un cliente!", "Buscar Cliente", 2);

            }

        }

        dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    public Date fechasistema() {
        Date ahora = new Date();
        return ahora;

    }

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_boton_SalirActionPerformed

    private void listaClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClientesMouseReleased
        jButton1.doClick();
    }//GEN-LAST:event_listaClientesMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_Salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaClientes;
    // End of variables declaration//GEN-END:variables
}
