
package Presentacion;

import Entidades.Paquetes;
import Entidades.PaquetesRepuestos;
import Entidades.Repuestos;
import Presentacion.Facturacion.IU_FMaestro;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Maestros;
import Servicios.Servicio_Paquetes;
import Servicios.TipoMensaje;
import java.util.List;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author usuario
 */
public class IU_Paquete_Repuestos extends javax.swing.JFrame {

    private Servicio_Paquetes servicio;
    private Servicio_Maestros sm;
    private Paquetes paquete;
    private List paquete_repuesto;
    DefaultTableModel dtm;
    TipoMensaje tm;
    RowSorter<TableModel> sorter;

    public IU_Paquete_Repuestos(Paquetes p, Servicio_Paquetes serv) {
        initComponents();
        tm = new TipoMensaje();
        servicio = serv;
        sm = new Servicio_Maestros();
        paquete = p;
        lb_paquete.setText(p.getDescripcion());
        dtm = (DefaultTableModel) tb_paq_rep.getModel();

        sorter = new TableRowSorter<TableModel>(dtm);

        tb_paq_rep.setRowSorter(sorter);


        initOtherComponents();
        setAnchoColumnas();
    }

    private void initOtherComponents() {
        Listar_Repuestos();
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_paq_rep.getTableHeader().getDefaultRenderer();
        tb_paq_rep.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tx_nroParte = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_cantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lb_paquete = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_paq_rep = new javax.swing.JTable();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_rep = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Repuestos en Paquete");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Repuesto"));

        jLabel3.setText("Nro Parte");

        jLabel4.setText("Cantidad");

        tx_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_cantidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Paquete");

        lb_paquete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tb_paq_rep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Parte", "Linea", "Descripcion", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_paq_rep.getTableHeader().setReorderingAllowed(false);
        tb_paq_rep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_paq_repMouseReleased(evt);
            }
        });
        tb_paq_rep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_paq_repKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_paq_rep);

        bt_modificar.setText("Modificar");
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });

        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        bt_rep.setText("Agregar");
        bt_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_repActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_paquete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_paquete, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = tb_paq_rep.getSelectedRow();
        if (fila >= 0) {
            int idpaq = paquete.getIdpaquete();
            String codRep = String.valueOf(tb_paq_rep.getValueAt(fila, 0));
            PaquetesRepuestos paq = (PaquetesRepuestos) servicio.getPaq_rep(idpaq,codRep);
            if (servicio.eliminar_PR(paq)) {
                dtm.removeRow(fila);
                clean();
            } else {
                tm.Error("ERROR AL REMOVER");
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        int fila = tb_paq_rep.getSelectedRow();
        if (fila >= 0) {
            PaquetesRepuestos paqrep = servicio.getPaq_rep(paquete.getIdpaquete(), String.valueOf(dtm.getValueAt(fila, 0)));
            try {
                int cantidad = Integer.parseInt(tx_cantidad.getText());
                paqrep.setCantidad(fila);
                if (servicio.modificar_PR(paqrep)) {
                    Object[] row = {paqrep.getRepuestos().getCodrepuesto(), paqrep.getRepuestos().getEquipos().getIdequipo(),
                        paqrep.getRepuestos().getDescripcion(), paqrep.getRepuestos().getPreciolista(), cantidad};
                    dtm.removeRow(fila);
                    dtm.insertRow(fila, row);
                    clean();
                } else {
                    tm.Error("ERROR AL MODIFICAR");
                }
            } catch (NumberFormatException e) {
                tm.Error("INGRESE UNA CANTIDAD CORRECTA");
            }

        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void tb_paq_repMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_paq_repMouseReleased
        Informacion_PaqRep();
    }//GEN-LAST:event_tb_paq_repMouseReleased

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        clean();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_paq_repKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_paq_repKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_PaqRep();
        }
    }//GEN-LAST:event_tb_paq_repKeyReleased

    private void bt_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_repActionPerformed
        IU_FMaestro maestro = new IU_FMaestro(this);
    }//GEN-LAST:event_bt_repActionPerformed

    private void tx_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_cantidadKeyTyped
        if (evt.getKeyChar() == 10) {
            bt_modificar.doClick();
        }
    }//GEN-LAST:event_tx_cantidadKeyTyped
    private void Informacion_PaqRep() {
        int fila = tb_paq_rep.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        if (fila >= 0) {
            PaquetesRepuestos paqrep = servicio.getPaq_rep(paquete.getIdpaquete(), String.valueOf(dtm.getValueAt(fila, 0)));
            tx_nroParte.setText(paqrep.getRepuestos().getCodrepuesto());
            tx_cantidad.setText(String.valueOf(paqrep.getCantidad()));
        }
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_paq_rep.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_paq_rep.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tb_paq_rep.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (45 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (10 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    private boolean esta_en(Repuestos rep) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (String.valueOf(dtm.getValueAt(i, 0)).equals(rep.getCodrepuesto())) {
                return true;
            }
        }
        return false;
    }

    private void Listar_Repuestos() {
        paquete_repuesto = servicio.obtenerRepuestos_Paq(paquete.getIdpaquete());
        for (Object o : paquete_repuesto) {
            PaquetesRepuestos r = (PaquetesRepuestos) o;
            Object[] row = {r.getRepuestos().getCodrepuesto(),
                String.valueOf(r.getRepuestos().getEquipos().getIdequipo()),
                r.getRepuestos().getDescripcion(),
                String.valueOf(r.getRepuestos().getPreciolista()),
                String.valueOf(r.getCantidad())};
            dtm.addRow(row);
        }
    }

    private void clean() {
        tx_cantidad.setText("");
        tx_nroParte.setText("");
        tb_paq_rep.clearSelection();
    }

    public boolean Agregar(Repuestos r) {

        if (esta_en(r)) {
            return false;
        }
        int cant = 1;
        if (servicio.agregar_PR(new PaquetesRepuestos(paquete, r, cant))) {
            Object[] row = {r.getCodrepuesto(), r.getEquipos().getIdequipo(), r.getDescripcion(), r.getPreciolista(),
                cant};
            dtm.addRow(row);
            clean();
        } else {

            tm.Error("ERROR AL AÑADIR");
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JButton bt_modificar;
    private javax.swing.JButton bt_rep;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_paquete;
    public javax.swing.JTable tb_paq_rep;
    private javax.swing.JTextField tx_cantidad;
    private javax.swing.JTextField tx_nroParte;
    // End of variables declaration//GEN-END:variables
}
