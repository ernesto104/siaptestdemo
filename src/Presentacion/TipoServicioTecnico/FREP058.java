/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.TipoServicioTecnico;

import Entidades.Roles;
import Entidades.Usuarios;
import Mantenimiento.Navegacion;
import Servicios.Servicio_TipoServicioTecnico;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class FREP058 extends javax.swing.JFrame {

    /**
     * Creates new form FREP058
     */
    Usuarios usuario;
    String valorVenta;
    
    boolean modificar;
    Servicio_TipoServicioTecnico stst;
    TipoMensaje tm;
    public JTextField[] componentes;
    DefaultTableModel modelo;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    
    public FREP058(Usuarios usuario, String valorVenta) {
        initComponents();
        this.usuario = usuario;
        this.valorVenta = valorVenta;
        modificar = false;

//        System.out.println("Roles : " + roles);
        Roles Querol = usuario.getRoles();
        System.out.println("Al comienzo Rol : " + Querol);
        
        tm = new TipoMensaje();
        modelo = (DefaultTableModel) tb_tipoSoporteTecnico.getModel();
        stst = new Servicio_TipoServicioTecnico();
        
        ListarTiposServicioTecnico();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_agregar);
        asignarEvento();
    }
    
    private void crearArrayComponente() {
        componentes = new JTextField[2];
        componentes[0] = tx_tipo;
        //componentes[1] = txtdescuento;
        componentes[1] = txtdescripcion;

    }
    
    private void crearArrayNumMax() {
        numMaximo.add(10);
        numMaximo.add(100);
    }
    
        private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
    }
    
      private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }
      
      
      private void ListarTiposServicioTecnico() {
          limpiarTabla();
          
          int cant_tipos= stst.listarTipoServicioTecnico(modelo);
          cant_tipos++;
      
      }
      
       private void limpiarTabla() {
//        tablaRepuestos.setModel(new DefaultTableModel());
       for (int i = 0; i < tb_tipoSoporteTecnico.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }

       
       private String validarEntradas() {
        String mensaje = "ERROR";
        boolean a = true;
        if (txtdescripcion.getText().equals("")) {
            mensaje = mensaje + "\n- Ingrese descripción de Equipo";
            a = false;
        }
        
        
        if (comboEstado.getSelectedIndex() == 0) {
            mensaje += "\n- Seleccione un estado para el equipo";
        }
       
        if (/*a && */!modificar){
            /*if (stst.buscarEquiposx_Nombre(txtdescripcion.getText())!= null ) {
                mensaje += "\n- YA EXISTE EQUIPO CON ESE NOMBRE";
            }*/
        }
       /* if (mensaje.equals("")) {
            return tm.VALIDO;
        } else {*/
            return mensaje;
        //}
   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelServicio_Tecnico = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tx_tipo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbFacturable = new javax.swing.JComboBox();
        comboEstado = new javax.swing.JComboBox();
        bt_agregar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bt_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tipoSoporteTecnico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Servicio Tecnico"));
        jPanel1.setToolTipText("");
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Tipo de Servicio");

        jLabel4.setText("Estado");

        jLabel2.setText("(*)");

        jLabel7.setText("(*)");

        jLabel9.setText("Descripcion");

        jLabel8.setText("(*)");

        jLabel10.setText("Facturable");

        jLabel11.setText("(*)");

        cbFacturable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "No Activo" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(tx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFacturable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbFacturable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85))))
        );

        bt_agregar.setText("Agregar Servicio");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        Modificar.setText("Modificar Servicio");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TIPOS DE SERVICIO TECNICO");

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        tb_tipoSoporteTecnico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"RG", "Reclamo de Garantia", "N", "Activo"},
                {"MA", "Soporte Tecnico", "S", "Activo"}
            },
            new String [] {
                "Tipo", "Descripcion", "Facturable", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_tipoSoporteTecnico.getTableHeader().setReorderingAllowed(false);
        tb_tipoSoporteTecnico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_tipoSoporteTecnicoMouseReleased(evt);
            }
        });
        tb_tipoSoporteTecnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_tipoSoporteTecnicoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_tipoSoporteTecnico);
        if (tb_tipoSoporteTecnico.getColumnModel().getColumnCount() > 0) {
            tb_tipoSoporteTecnico.getColumnModel().getColumn(0).setMaxWidth(50);
            tb_tipoSoporteTecnico.getColumnModel().getColumn(2).setMaxWidth(100);
            tb_tipoSoporteTecnico.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        javax.swing.GroupLayout panelServicio_TecnicoLayout = new javax.swing.GroupLayout(panelServicio_Tecnico);
        panelServicio_Tecnico.setLayout(panelServicio_TecnicoLayout);
        panelServicio_TecnicoLayout.setHorizontalGroup(
            panelServicio_TecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelServicio_TecnicoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(panelServicio_TecnicoLayout.createSequentialGroup()
                .addGroup(panelServicio_TecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelServicio_TecnicoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelServicio_TecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelServicio_TecnicoLayout.createSequentialGroup()
                                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelServicio_TecnicoLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelServicio_TecnicoLayout.setVerticalGroup(
            panelServicio_TecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServicio_TecnicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelServicio_TecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Datos de Tipo de Servicio Tecnico");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelServicio_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelServicio_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        /*IU_Facturacion f = new IU_Facturacion(valorVenta, FACTURA_NORMAL, null, null, usuario);
        //        f.setUsuario(usuario);
        System.out.println("Usuario : " + usuario);

        f.setLocationRelativeTo(null);
        f.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        /*IU_FProforma iup = new IU_FProforma(usuario, valorVenta);
        iup.setLocationRelativeTo(null);
        iup.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_ModificarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        /*IU_FSalidasVarias iu = new IU_FSalidasVarias(usuario);
        iu.setLocationRelativeTo(null);
        iu.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void tb_tipoSoporteTecnicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_tipoSoporteTecnicoMouseReleased
        //Informacion_Banco();
    }//GEN-LAST:event_tb_tipoSoporteTecnicoMouseReleased

    private void tb_tipoSoporteTecnicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_tipoSoporteTecnicoKeyReleased
        if ( evt.getKeyCode() == 38 || evt.getKeyCode() == 40 ) {
            //Informacion_Banco();
        }
    }//GEN-LAST:event_tb_tipoSoporteTecnicoKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FREP058.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FREP058.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FREP058.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FREP058.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FREP058(usuario, valorVenta).setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Modificar;
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_salir;
    private javax.swing.JComboBox cbFacturable;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelServicio_Tecnico;
    public javax.swing.JTable tb_tipoSoporteTecnico;
    public javax.swing.JTextField tx_tipo;
    public javax.swing.JTextField txtdescripcion;
    // End of variables declaration//GEN-END:variables
}