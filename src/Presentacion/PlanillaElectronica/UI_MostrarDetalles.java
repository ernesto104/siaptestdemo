package Presentacion.PlanillaElectronica;

import Presentacion.EmisionPlanillas.*;
import Entidades.Cabeces;
import Entidades.Cabecsalvar;
import Servicios.EmisionPlanillas.Servicio_MostrarDetalles;
import Servicios.Servicio_Sistema;
import java.text.SimpleDateFormat;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author maverick225
 */
public class UI_MostrarDetalles extends javax.swing.JFrame {

    Cabeces c;
    Cabecsalvar cs;
    private Servicio_MostrarDetalles servicioMostrar;

    public UI_MostrarDetalles(Object o) {
        this();
        if (o instanceof Cabeces) {
            c = (Cabeces) o;
        } else {
            cs = (Cabecsalvar) o;
        }
        servicioMostrar = new Servicio_MostrarDetalles(o, tab_Detalles);
        setDetallesDocumentos();
        setAnchoColumnas();
    }

    public UI_MostrarDetalles() {
        initComponents();
    }

    private void setDetallesDocumentos() {
        if (c != null) {
            lb_tipoDoc.setText(new Servicio_Sistema().Obtener_Sistema_TipoDoc(c.getId().getTipodoc()).getDescripcion());
            lb_nroDoc.setText(c.getId().getNrodocumento());
            lb_cliente.setText(c.getClientes()==null?"ANULADO":c.getClientes().getNombre());
            lb_fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(c.getTipocambio().getFecha()));
            lb_total.setText(c.getMoneda()==null ?"":(c.getMoneda().equals("1") ? "S/" : "$") + "  " + c.getTotal());
            lb_usuario.setText(c.getUsuarios()==null?"":c.getUsuarios().getNombre());
            lb_vendedor.setText(c.getVendedores()==null?"":c.getVendedores().getNombre());
        } else {
            lb_tipoDoc.setText("SALIDA VARIA");
            lb_nroDoc.setText(cs.getCodigosalida());
            lb_cliente.setText(cs.getClientes().getNombre());
            lb_fecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(cs.getFecha()));
            lb_total.setText("$  " + String.valueOf(cs.getTotal()));
            lb_usuario.setText(cs.getUsuarios()==null?"":cs.getUsuarios().getNombre());
            lb_vendedor.setText(cs.getVendedores()==null?"":cs.getVendedores().getNombre());
        }

    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tab_Detalles.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tab_Detalles.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tab_Detalles.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (10 * ancho) / 100;
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
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_Detalles = new javax.swing.JTable();
        btn_Movimientos = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_cliente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lb_fecha = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lb_nroDoc = new javax.swing.JLabel();
        lb_tipoDoc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb_usuario = new javax.swing.JLabel();
        lb_vendedor = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Total:");

        tab_Detalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LINEA", "NRO PARTE", "DESCRIPCION", "CANTIDAD", "DSCTO 1", "DSCTO 2", "DSCTO 3", "DSCTO 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab_Detalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tab_Detalles);
        if (tab_Detalles.getColumnModel().getColumnCount() > 0) {
            tab_Detalles.getColumnModel().getColumn(2).setResizable(false);
            tab_Detalles.getColumnModel().getColumn(6).setResizable(false);
        }

        btn_Movimientos.setText("Movimientos");
        btn_Movimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MovimientosActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DETALLES DEL DOCUMENTO");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cliente / Proveedor");

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Fecha");

        lb_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nro Documento");

        lb_fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Tipo Documento");

        lb_nroDoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_tipoDoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Usuario");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Vendedor");

        lb_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_vendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btn_Movimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_tipoDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(lb_nroDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lb_tipoDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lb_nroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lb_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lb_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Movimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_MovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MovimientosActionPerformed
        servicioMostrar.mostrarMovimientos();
    }//GEN-LAST:event_btn_MovimientosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Movimientos;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_cliente;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_nroDoc;
    private javax.swing.JLabel lb_tipoDoc;
    private javax.swing.JLabel lb_total;
    private javax.swing.JLabel lb_usuario;
    private javax.swing.JLabel lb_vendedor;
    private javax.swing.JTable tab_Detalles;
    // End of variables declaration//GEN-END:variables
}
