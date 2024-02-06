package Presentacion.CuentasxCobrarSalidas;

import Entidades.Cabeces;
import Entidades.Pagos;
import Entidades.Usuarios;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Servicio_CobrarSalidasVarias;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class FREP070 extends javax.swing.JFrame {

    boolean seleccion = false;
    String nombre;
    int filaseleccionada;
    List<Cabeces> listaFacturas;
    List<Pagos> cantidadpagos;
    DefaultTableModel model;
    MENU001 m;
    IU_1Cliente clientes;
    IU_FiltrarCuentasxCobrar filt;
    BigDecimal num;
    Usuarios usuario;
    BigDecimal cont_Fac_C;
    BigDecimal cont_Sal_C;
    BigDecimal cont_Fac_D;
    BigDecimal cont_Sal_D;
    //
    BigDecimal cont_Fac_SG;
    BigDecimal cont_Sal_SG;
    BigDecimal cont_Fac_DG;
    BigDecimal cont_Sal_DG;
    
    String rolElegido;

    public FREP070(Usuarios usuario, String rol, boolean btnCCSK) {
        initComponents();
        setLocationRelativeTo(null);
        cont_Fac_C = new BigDecimal(0.00);
        cont_Sal_C = new BigDecimal(0.00);
        cont_Fac_D = new BigDecimal(0.00);
        cont_Sal_D = new BigDecimal(0.00);

        cont_Fac_SG = new BigDecimal(0.00);
        cont_Sal_SG = new BigDecimal(0.00);
        cont_Fac_DG = new BigDecimal(0.00);
        cont_Sal_DG = new BigDecimal(0.00);

        num = new BigDecimal(100);
        model = (DefaultTableModel) tablaFactura.getModel();
        iniciarTabla(tablaFactura);
        this.usuario = usuario;
        
        rolElegido = rol;
        botonPagar.setEnabled(btnCCSK);
//        m = new MENU001(usuario.getNombre(), rol, 0, 0);
    }

    public String fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = sdf.format(ahora);
        return fecha;
    }

    public double totalFacturado() {
        int filas = this.tablaFactura.getRowCount();
        double cont = 0;
        for (int i = 0; i < filas; i++) {
            if (filas == 0) {
                JOptionPane.showMessageDialog(null, "Este cliente no dispone de cuentas por cobrar", "No existen registros", 0);
            }
            if (filas == 1) {
                cont = (double) tablaFactura.getValueAt(i, 6);
                return cont;
            }
            if (filas > 1) {
                if (i != filas - 1) {
                    if (i == 0) {
                        cont = Math.round((double) tablaFactura.getValueAt(i, 6) * 100.0) / 100.0;
                    }
                    cont = cont + Math.round((double) tablaFactura.getValueAt(i + 1, 6) * 100.0) / 100.0;
                }
            }
        }
        return cont;
    }

    public double totalSaldo() {
        int filas = this.tablaFactura.getRowCount();
        double cont = 0;
        for (int i = 0; i < filas; i++) {
            if (filas == 0) {
                JOptionPane.showMessageDialog(null, "Este cliente no dispone de cuentas por cobrar", "No existe registros", 0);
            }
            if (filas == 1) {
                cont = (double) tablaFactura.getValueAt(i, 8);
                return cont;
            }
            if (filas > 1) {
                if (i != filas - 1) {
                    if (i == 0) {
                        cont = Math.rint((double) tablaFactura.getValueAt(i, 8) * 100) / 100;
                    }
                    cont = cont + Math.rint((double) tablaFactura.getValueAt(i + 1, 8) * 100) / 100;
                }
            }
        }
        return cont;
    }

    public DefaultTableModel iniciarTabla(JTable tabla) {
        TextoFacturas.setText("SALIDAS VARIAS PENDIENTES POR COBRAR AL " + fechaSistema());
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        Servicio_CobrarSalidasVarias sc = new Servicio_CobrarSalidasVarias();
        List li1 = sc.Listar_Cuentas_x_Cobrar();
        int tam = li1.size();
        if (tam != 0) {
            for (int i = 0; i < tam; i++) {
                Object[] a = (Object[]) li1.get(i);
                cont_Fac_D = cont_Fac_D.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                Object[] fila = {a[0], a[1], a[2], a[3], a[8], a[4], "US$", a[5], a[6], a[7], false};
                modelo.addRow(fila);
            }
            txtTotalFacturado.setText(cont_Fac_C.toString());
            txtTotalSaldo.setText(cont_Sal_C.toString());
            txtTotFacDolar.setText(cont_Fac_D.toString());
            txttotalSaldoDolar.setText(cont_Sal_D.toString());

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }
        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCobrarSal = new javax.swing.JPanel();
        TextoFacturas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        botonPagar = new javax.swing.JButton();
        botGeneral = new javax.swing.JButton();
        botonPendientes = new javax.swing.JButton();
        btn_1cliente = new javax.swing.JButton();
        boton_Filtrar = new javax.swing.JButton();
        botonImprimir = new javax.swing.JButton();
        boton_seleccionartodo = new javax.swing.JButton();
        boton_restaurar = new javax.swing.JButton();
        botonDetalle = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        t1 = new javax.swing.JLabel();
        t2 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        txtTotalFacturado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalSaldo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTotFacDolar = new javax.swing.JLabel();
        txttotalSaldoDolar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextoFacturas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TOTAL FACTURADO EN SOLES : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("TOTAL SALDO EN SOLES : ");

        botonPagar.setText("Pagar");
        botonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPagarActionPerformed(evt);
            }
        });

        botGeneral.setText("General");
        botGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botGeneralActionPerformed(evt);
            }
        });

        botonPendientes.setText("Pendientes");
        botonPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPendientesActionPerformed(evt);
            }
        });

        btn_1cliente.setText("1 Cliente");
        btn_1cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1clienteActionPerformed(evt);
            }
        });

        boton_Filtrar.setText("Filtrar");
        boton_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_FiltrarActionPerformed(evt);
            }
        });

        botonImprimir.setText("Imprimir");
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        boton_seleccionartodo.setText("Seleccionar Todo");
        boton_seleccionartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_seleccionartodoActionPerformed(evt);
            }
        });

        boton_restaurar.setText("Restaurar");
        boton_restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_restaurarActionPerformed(evt);
            }
        });

        botonDetalle.setText("Detalle");
        botonDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetalleActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        t1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        t3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        t4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTotalFacturado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TOTAL FACTURADO EN DOLARES : ");

        txtTotalSaldo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("TOTAL SALDO EN DOLARES : ");

        txtTotFacDolar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txttotalSaldoDolar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Documento", "Nro. Serie", "Fecha", "RUC", "Nombre Cliente", "Moneda", "Facturado", "Pagado", "Saldo", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaFactura.getTableHeader().setReorderingAllowed(false);
        tablaFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaFacturaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaFactura);
        if (tablaFactura.getColumnModel().getColumnCount() > 0) {
            tablaFactura.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelCobrarSalLayout = new javax.swing.GroupLayout(panelCobrarSal);
        panelCobrarSal.setLayout(panelCobrarSalLayout);
        panelCobrarSalLayout.setHorizontalGroup(
            panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarSalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalFacturado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotFacDolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttotalSaldoDolar, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addComponent(TextoFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCobrarSalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addComponent(botonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_1cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton_seleccionartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(boton_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        panelCobrarSalLayout.setVerticalGroup(
            panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCobrarSalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextoFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalFacturado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1)
                        .addComponent(txtTotFacDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCobrarSalLayout.createSequentialGroup()
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelCobrarSalLayout.createSequentialGroup()
                                .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTotalSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(panelCobrarSalLayout.createSequentialGroup()
                                .addComponent(txttotalSaldoDolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)))
                        .addGroup(panelCobrarSalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_1cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_Filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_seleccionartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCobrarSal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCobrarSal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPagarActionPerformed
        if (!seleccion) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente! ", "Pagar Cuenta", JOptionPane.ERROR_MESSAGE);
        } else {
            IU_PagarSalidasVarxCobrar pf = new IU_PagarSalidasVarxCobrar(model, filaseleccionada, m, usuario, tablaFactura,
                                                                         rolElegido, botonPagar.isEnabled());
            pf.setVisible(true);
        }

    }//GEN-LAST:event_botonPagarActionPerformed

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        IU_ImprimirCuentasxCobrarSK isk= new IU_ImprimirCuentasxCobrarSK(model, filaseleccionada, this);
        isk.setVisible(true);
    }//GEN-LAST:event_botonImprimirActionPerformed

    private void boton_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_FiltrarActionPerformed
        tablaFactura.clearSelection();
        filt = new IU_FiltrarCuentasxCobrar(this, m);
        filt.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                filt = null;
            }
        });
        filt.setVisible(true);
    }//GEN-LAST:event_boton_FiltrarActionPerformed

    private void botonDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetalleActionPerformed

        IU_DetalleCuentasxCobrar dc = new IU_DetalleCuentasxCobrar(model, filaseleccionada);
        dc.setVisible(true);
    }//GEN-LAST:event_botonDetalleActionPerformed

    private void boton_seleccionartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_seleccionartodoActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(true, i, 10);
        }
    }//GEN-LAST:event_boton_seleccionartodoActionPerformed

    private void botGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botGeneralActionPerformed
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
        TextoFacturas.setText("TODAS LAS SALIDAS VARIAS CANCELADAS Y POR COBRAR AL " + fechaSistema());

        Servicio_CobrarSalidasVarias sc = new Servicio_CobrarSalidasVarias();

        List li1 = sc.Listar_Cuentas_General();
        int tam = li1.size();

        if (tam != 0) {
            for (int i = 0; i < tam; i++) {
                Object[] a = (Object[]) li1.get(i);
                cont_Fac_D = cont_Fac_D.add(BigDecimal.valueOf(Double.parseDouble(a[5].toString())));
                cont_Sal_D = cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                Object[] fila = {a[0], a[1], a[2], a[3], a[8], a[4], "US$", a[5], a[6], a[7], false};
                modelo.addRow(fila);
            }

            txtTotalFacturado.setText(cont_Fac_DG.toString());
            txtTotalSaldo.setText(cont_Sal_DG.toString());
            txtTotFacDolar.setText(cont_Fac_SG.toString());
            txttotalSaldoDolar.setText(cont_Sal_SG.toString());

        } else {
            JOptionPane.showMessageDialog(null, "No existe registros");
        }


    }//GEN-LAST:event_botGeneralActionPerformed

    private void boton_restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_restaurarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tablaFactura.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(false, i, 10);
        }
    }//GEN-LAST:event_boton_restaurarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPendientesActionPerformed
        limpiar();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        iniciarTabla(tablaFactura);
    }//GEN-LAST:event_botonPendientesActionPerformed

    private void btn_1clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1clienteActionPerformed
        tablaFactura.clearSelection();
        if (clientes == null) {
            clientes = new IU_1Cliente(this);
            clientes.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    clientes = null;
                }
            });
        } else {
            clientes.setVisible(true);
        }
    }//GEN-LAST:event_btn_1clienteActionPerformed

    private void tablaFacturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaFacturaMouseReleased
        filaseleccionada = tablaFactura.getSelectedRow();
        seleccion = true;
    }//GEN-LAST:event_tablaFacturaMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TextoFacturas;
    public javax.swing.JButton botGeneral;
    public javax.swing.JButton botonDetalle;
    public javax.swing.JButton botonImprimir;
    public javax.swing.JButton botonPagar;
    public javax.swing.JButton botonPendientes;
    public javax.swing.JButton botonSalir;
    public javax.swing.JButton boton_Filtrar;
    public javax.swing.JButton boton_restaurar;
    public javax.swing.JButton boton_seleccionartodo;
    public javax.swing.JButton btn_1cliente;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelCobrarSal;
    public javax.swing.JLabel t1;
    public javax.swing.JLabel t2;
    public javax.swing.JLabel t3;
    public javax.swing.JLabel t4;
    public javax.swing.JTable tablaFactura;
    public javax.swing.JLabel txtTotFacDolar;
    public javax.swing.JLabel txtTotalFacturado;
    public javax.swing.JLabel txtTotalSaldo;
    public javax.swing.JLabel txttotalSaldoDolar;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        cont_Fac_C = new BigDecimal(0.00);
        cont_Sal_C = new BigDecimal(0.00);
        cont_Fac_D = new BigDecimal(0.00);
        cont_Sal_D = new BigDecimal(0.00);

        cont_Fac_SG = new BigDecimal(0.00);
        cont_Sal_SG = new BigDecimal(0.00);
        cont_Fac_DG = new BigDecimal(0.00);
        cont_Sal_DG = new BigDecimal(0.00);

    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRolElegido() {
        return rolElegido;
    }

    public void setRolElegido(String rolElegido) {
        this.rolElegido = rolElegido;
    }
    
}
