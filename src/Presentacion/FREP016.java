
package Presentacion;

import Entidades.Programas;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Programas;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily I.
 */
public class FREP016 extends javax.swing.JFrame {

    private Servicio_Programas servicio;
    private Navegacion navegacion;
    private JTextField[] componentes;
    private ArrayList<Integer> numMaximo;
    private ArrayList<String> tipoDato;   
    private TipoMensaje tm;
    private DefaultTableModel mod;
    int fila;
    private boolean seleccionada;

    public FREP016() {
        initComponents();
        setLocationRelativeTo(null);
        servicio = new Servicio_Programas(this);
        mod = (DefaultTableModel) tablaProgramas.getModel();
        tm = new TipoMensaje();

        txtid.setEnabled(false);
        txtid.setText(String.valueOf(servicio.obtenerNextId()));
        Listar_Programas();
        //Navegacion
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btn_Agregar);
        asignarEvento();
        limpiar();
    }

    private void crearArrayTipoDato() {
        tipoDato = new ArrayList();
        tipoDato.add("I");
        tipoDato.add("S");
        tipoDato.add("S");
    }

    private void crearArrayComponente() {
        componentes = new JTextField[3];
        componentes[0] = txtid;
        componentes[1] = txtcodigo;
        componentes[2] = txtdescripcion;

    }

    private void crearArrayNumMax() {
        numMaximo = new ArrayList();
        numMaximo.add(11);
        numMaximo.add(50);
        numMaximo.add(70);
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }



    private void limpiar() {
        txtid.setText(String.valueOf(servicio.obtenerNextId()));
        txtcodigo.setText("");
        txtdescripcion.setText("");
    }

    
    private void Listar_Programas() {
        servicio.Listar_Programas();
    }

    private String validarEntradas() {
        String mensaje = "";
        if (txtcodigo.getText().equals("")) {
            mensaje = mensaje + "INGRESE CODIGO DEL PROGRAMA";
        }
        return mensaje;
    }

    private void clean() {
        for (JTextField j : componentes) {
            txtid.setText(String.valueOf(servicio.obtenerNextId()));
            j.setText("");
        }
        tablaProgramas.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProgramas = new javax.swing.JPanel();
        btn_Agregar = new javax.swing.JButton();
        btn_Modificar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Exportar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtid = new javax.swing.JTextField();
        txtcodigo = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProgramas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btn_Exportar.setText("Exportar");
        btn_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("PROGRAMAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS DEL PROGRAMA"));

        jLabel4.setText("Descripcion");

        jLabel3.setText("Codigo");

        jLabel2.setText("ID");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("( * )");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaProgramas.setAutoCreateRowSorter(true);
        tablaProgramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProgramas.getTableHeader().setReorderingAllowed(false);
        tablaProgramas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaProgramasMouseReleased(evt);
            }
        });
        tablaProgramas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProgramasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProgramas);
        tablaProgramas.getColumnModel().getColumn(0).setResizable(false);
        tablaProgramas.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaProgramas.getColumnModel().getColumn(1).setResizable(false);
        tablaProgramas.getColumnModel().getColumn(1).setPreferredWidth(230);

        javax.swing.GroupLayout panelProgramasLayout = new javax.swing.GroupLayout(panelProgramas);
        panelProgramas.setLayout(panelProgramasLayout);
        panelProgramasLayout.setHorizontalGroup(
            panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgramasLayout.createSequentialGroup()
                .addGroup(panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProgramasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelProgramasLayout.createSequentialGroup()
                                .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelProgramasLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelProgramasLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel5)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelProgramasLayout.setVerticalGroup(
            panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProgramasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed

        if (seleccionada == false) {
            String validacion = validarEntradas();
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {       
                    String id = txtid.getText();
                    String codigo = txtcodigo.getText();
                    String descripcion = txtdescripcion.getText();

                    Programas pr = new Programas(); 
                    pr.setIdprograma(Integer.parseInt(id));
                    pr.setCodprograma(codigo);
                    pr.setDescripcion(descripcion);

                    if (servicio.addProgramas(pr)) {
                        JOptionPane.showMessageDialog(null, "Operacion exitosa");
                        Object[] row = {codigo, descripcion};
                        mod.addRow(row);
                        clean();
                        txtid.setText(String.valueOf(servicio.nextId()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ESTE CODIGO YA SE ENCUENTRA REGISTRADO", "Error al agregar", 0);
        }

    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        String validacion = validarEntradas();
        if (seleccionada == true) {
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {

                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                if (verif == 0) {
                    int id = Integer.parseInt(txtid.getText());
                    String codigo = txtcodigo.getText();
                    String descripcion = txtdescripcion.getText();

                    Programas pr = new Programas();
                    pr.setIdprograma(id);
                    pr.setCodprograma(codigo);
                    pr.setDescripcion(descripcion);

                    if (actualizarProgramas(pr)) {
                        JOptionPane.showMessageDialog(null, "Operación exitosa");
                        DefaultTableModel m = (DefaultTableModel) tablaProgramas.getModel();                       
                        m.setValueAt(codigo, fila, 0);
                        m.setValueAt(descripcion, fila, 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_LINEAS);
        }


    }//GEN-LAST:event_btn_ModificarActionPerformed

    public boolean actualizarProgramas(Programas p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            return false;
        }

    }

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        if (seleccionada == true) {
            if (JOptionPane.showConfirmDialog(this, "¿ Desea continuar con la Operacion ?", "Confirmacion", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION) {
                if (servicio.borrarProgramas(servicio.getProgramas(Integer.parseInt(txtid.getText())))) {
                    JOptionPane.showMessageDialog(null, "Operacion exitosa");
                    mod.removeRow(fila);                    
                    tablaProgramas.clearSelection();
                    seleccionada = false;
                    clean();
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operacion");
                }
            }
        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_LINEAS);
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiar();
        tablaProgramas.clearSelection();
        seleccionada = false;
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportarActionPerformed
        Servicio_Excel servicio_excel;
        servicio_excel = new Servicio_Excel(tablaProgramas, this);
        servicio_excel.Exportar_Excel(1);
    }//GEN-LAST:event_btn_ExportarActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
      
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void tablaProgramasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProgramasMouseReleased
        fila = tablaProgramas.getSelectedRow();
        Servicio_Programas s = new Servicio_Programas(this);
        Programas p = s.getProgramas_codigo(tablaProgramas.getValueAt(fila, 0).toString());
        String codigo = tablaProgramas.getValueAt(fila, 0).toString();
        String desc = tablaProgramas.getValueAt(fila, 1).toString();        
        txtid.setText(String.valueOf(p.getIdprograma()));
        txtcodigo.setText(codigo);
        txtdescripcion.setText(desc);
        seleccionada = true;
    }//GEN-LAST:event_tablaProgramasMouseReleased

    private void tablaProgramasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProgramasKeyReleased
        fila = tablaProgramas.getSelectedRow();
        Servicio_Programas s = new Servicio_Programas(this);
        Programas p = s.getProgramas_codigo(tablaProgramas.getValueAt(fila, 0).toString());
        String codigo = tablaProgramas.getValueAt(fila, 0).toString();
        String desc = tablaProgramas.getValueAt(fila, 1).toString();        
        txtid.setText(String.valueOf(p.getIdprograma()));
        txtcodigo.setText(codigo);
        txtdescripcion.setText(desc);
        seleccionada = true;
    }//GEN-LAST:event_tablaProgramasKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_Agregar;
    public javax.swing.JButton btn_Eliminar;
    public javax.swing.JButton btn_Exportar;
    public javax.swing.JButton btn_Limpiar;
    public javax.swing.JButton btn_Modificar;
    public javax.swing.JButton btn_Salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel panelProgramas;
    public javax.swing.JTable tablaProgramas;
    public javax.swing.JTextField txtcodigo;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
