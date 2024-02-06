package Presentacion;

import Entidades.Paquetes;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Paquetes;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
 * @author fabrica2
 */
public class FREP008 extends javax.swing.JFrame {

    private Servicio_Paquetes servicio;
    private JTextField[] componentes;
    private ArrayList<Integer> numMaximo;
    private ArrayList<String> tipoDato;
    private Navegacion navegacion;
    private DefaultTableModel dtm;
    private TipoMensaje tm;
    boolean modificar;
    RowSorter<TableModel> sorter;

    public FREP008() {
        initComponents();
        servicio = new Servicio_Paquetes();
        txt_Id.setEnabled(false);
        tm = new TipoMensaje();
        //Navegacion
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btn_Agregar);
        asignarEvento();
        dtm = (DefaultTableModel) tab_Paquetes.getModel();

        sorter = new TableRowSorter<TableModel>(dtm);

        tab_Paquetes.setRowSorter(sorter);


        setAnchoColumnas();
        limpiar();
        Listar_Paquetes();
        modificar = false;
        centrarCabeceras();
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tab_Paquetes.getTableHeader().getDefaultRenderer();
        tab_Paquetes.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayTipoDato() {
        tipoDato = new ArrayList();
        tipoDato.add("D");
        tipoDato.add("S");
    }

    private void crearArrayComponente() {
        componentes = new JTextField[2];
        componentes[0] = txt_Id;
        componentes[1] = txt_Descripcion;

    }

    private void crearArrayNumMax() {
        numMaximo = new ArrayList();
        numMaximo.add(3);
        numMaximo.add(40);
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void setear(Paquetes paquete) {
        paquete.setIdpaquete(Integer.valueOf(txt_Id.getText()));
        paquete.setDescripcion(txt_Descripcion.getText());
    }

    public void mostrar(Paquetes paquete) {
        txt_Id.setText(String.valueOf(paquete.getIdpaquete()));
        txt_Descripcion.setText(paquete.getDescripcion());
    }

    private void limpiar() {
        txt_Id.setText(String.valueOf(servicio.obtenerNextId()));
        txt_Descripcion.setText("");
        tab_Paquetes.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelPaquetes = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();
        txt_Descripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_Paquetes = new javax.swing.JTable();
        btn_Agregar = new javax.swing.JButton();
        btn_Modificar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setText("Datos de paquete");

        jLabel1.setText("ID");

        jLabel3.setText("Descripcion");

        tab_Paquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tab_Paquetes.getTableHeader().setReorderingAllowed(false);
        tab_Paquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab_PaquetesMouseReleased(evt);
            }
        });
        tab_Paquetes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tab_PaquetesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tab_Paquetes);

        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Modificar.setText("Modificar");
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("PAQUETES");

        jLabel6.setText("(*)");

        javax.swing.GroupLayout panelPaquetesLayout = new javax.swing.GroupLayout(panelPaquetes);
        panelPaquetes.setLayout(panelPaquetesLayout);
        panelPaquetesLayout.setHorizontalGroup(
            panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPaquetesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(213, 213, 213))
            .addGroup(panelPaquetesLayout.createSequentialGroup()
                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPaquetesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(panelPaquetesLayout.createSequentialGroup()
                                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelPaquetesLayout.createSequentialGroup()
                                        .addComponent(txt_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panelPaquetesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPaquetesLayout.createSequentialGroup()
                                .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPaquetesLayout.setVerticalGroup(
            panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPaquetesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        // TODO add your handling code here:
        String validacion = Validar();
        if (!validacion.equals(tm.VALIDO)) {
            tm.Error(validacion);
        } else {
            if (tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI) {
                Paquetes paquete = new Paquetes();
                setear(paquete);
                if (servicio.insertarPaquete(paquete)) {
                    JOptionPane.showMessageDialog(this, "Registro se agregó exitosamente");
                    Object[] row = {txt_Id.getText(), txt_Descripcion.getText()};
                    dtm.addRow(row);
                    limpiar();
                    txt_Descripcion.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Error en la base de datos");
                }
            }

        }
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        modificar = true;
        int fila = tab_Paquetes.getSelectedRow();
        if (fila >= 0) {
            String validacion = Validar();
            if (!validacion.equals(tm.VALIDO)) {
                tm.Error(validacion);
            } else {
                if (tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI) {
                    Paquetes paquete = servicio.obtenerPaquete(Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 0))));
                    paquete.setDescripcion(txt_Descripcion.getText());
                    if (servicio.modificarPaquete(paquete)) {
                        IU_Paquete_Repuestos pr = new IU_Paquete_Repuestos(paquete, servicio);
                        pr.setLocationRelativeTo(null);
                        pr.setVisible(true);
                    } else {
                        tm.Error(tm.ERROR_MODIFICAR);
                    }
                    limpiar();
                }
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
        modificar = false;
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        int fila = tab_Paquetes.getSelectedRow();
        if (fila >= 0) {
            if (tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI) {
                Paquetes paquete = servicio.obtenerPaquete(Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 0))));
                if (servicio.eliminarPaquete(paquete)) {
                    tm.Mensaje(tm.EXITO_ELIMINAR);
                    dtm.removeRow(fila);
                    limpiar();
                    tab_Paquetes.clearSelection();
                } else {
                    tm.Error(tm.ERROR_ELIMINAR);
                }

            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        // TODO add your handling code here:
        tab_Paquetes.getSelectionModel().clearSelection();
        limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
       
        this.dispose();

    }//GEN-LAST:event_btn_SalirActionPerformed

    private void tab_PaquetesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tab_PaquetesKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            Informacion_Paquetes();
        }
    }//GEN-LAST:event_tab_PaquetesKeyReleased

    private void tab_PaquetesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_PaquetesMouseReleased
        Informacion_Paquetes();
    }//GEN-LAST:event_tab_PaquetesMouseReleased

    private void Informacion_Paquetes() {
        int fila = tab_Paquetes.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        Paquetes p = servicio.obtenerPaquete(Integer.parseInt(String.valueOf(dtm.getValueAt(fila, 0))));
        txt_Id.setText(String.valueOf(p.getIdpaquete()));
        txt_Descripcion.setText(p.getDescripcion());
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tab_Paquetes.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tab_Paquetes.getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < tab_Paquetes.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (80 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void Listar_Paquetes() {
        Iterator ite = servicio.GetList().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[5];
            Paquetes temp = (Paquetes) ite.next();
            row[0] = String.valueOf(temp.getIdpaquete());
            row[1] = temp.getDescripcion();
            dtm.addRow(row);
        }
    }

    private String Validar() {
        String error = "ERROR";
        if (txt_Descripcion.getText().equals("")) {
            error += "\n- COMPLETE EL CAMPO DESCRIPCION";
        } else {
            if (modificar == false) {
                for (int i = 0; i < tab_Paquetes.getRowCount(); i++) {
                    if (String.valueOf(dtm.getValueAt(i, 1)).equals(txt_Descripcion.getText())) {
                        error += "\n- EL PAQUETE YA EXISTE, INGRESE OTRO";
                    }
                }
            }
        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_Agregar;
    public javax.swing.JButton btn_Eliminar;
    public javax.swing.JButton btn_Limpiar;
    public javax.swing.JButton btn_Modificar;
    public javax.swing.JButton btn_Salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelPaquetes;
    public javax.swing.JTable tab_Paquetes;
    public javax.swing.JTextField txt_Descripcion;
    public javax.swing.JTextField txt_Id;
    // End of variables declaration//GEN-END:variables
}
