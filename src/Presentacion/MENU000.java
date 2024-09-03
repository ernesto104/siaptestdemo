package Presentacion;

import Entidades.Usuarios;
import Mantenimiento.UsuarioDAO;
import Servicios.HibernateUtil;
import Servicios.Servicio_Control;
import Servicios.Servicio_Usuarios;
import Servicios.TipoMensaje;
import Servicios.UserSession;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

public class MENU000 extends javax.swing.JFrame {

    public boolean seleccion;
    public String nombreUsuario;
    
    String usuario;
    String rol; 
    
    public String codrol;
    MENU001 iu;
    TipoMensaje tm;
    Servicio_Usuarios usuarios;
    UsuarioDAO usu;
    int filaSeleccionada;
    int idseleccionado;

    public MENU000() {

        initComponents();
        tm = new TipoMensaje();
        usuarios = new Servicio_Usuarios();
        usuarios.listar_Usuarios(tabla_Usuarios);    
        tabla_Usuarios.setRequestFocusEnabled(true);
        this.setLocationRelativeTo(null);
        String icono = "/Imagenes/kernel.png";
        setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        
//        tabla_Usuarios.getSelectionModel().setSelectionInterval(0, 0);
//        filaSeleccionada = 0; // 1
//        seleccion = true;
    }

    private String validarEntradas() {
        if (!seleccion) {
            return tm.NO_SELECCIONADO_USUARIO;
        }
        return tm.VALIDO;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Usuarios = new javax.swing.JTable();
        boton_Ingresar = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE INVENTARIOS Y ADMINISTRACION DE REPUESTOS");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(185, 209, 234));

        tabla_Usuarios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tabla_Usuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Rol del Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_Usuarios.setToolTipText("");
        tabla_Usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla_Usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_Usuarios.getTableHeader().setReorderingAllowed(false);
        tabla_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_UsuariosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabla_UsuariosMouseReleased(evt);
            }
        });
        tabla_Usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_UsuariosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabla_UsuariosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_Usuarios);
        if (tabla_Usuarios.getColumnModel().getColumnCount() > 0) {
            tabla_Usuarios.getColumnModel().getColumn(0).setResizable(false);
            tabla_Usuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabla_Usuarios.getColumnModel().getColumn(1).setPreferredWidth(500);
            tabla_Usuarios.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        boton_Ingresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        boton_Ingresar.setText("Ingresar");
        boton_Ingresar.setToolTipText("Ingresar al sistema");
        boton_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_IngresarActionPerformed(evt);
            }
        });

        boton_Salir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        boton_Salir.setText("Salir");
        boton_Salir.setToolTipText("Salir del Sistema");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carTrans.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(410, 245));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(boton_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_boton_SalirActionPerformed

    
    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public String fechaSistema() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ahora = new Date();
        return sdf.format(ahora);
    }

    private void boton_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_IngresarActionPerformed
        String validacion = validarEntradas();
        Date ahora = new Date();
        
        if (validacion.equals(tm.VALIDO)) {
            String rol = (String) tabla_Usuarios.getValueAt(tabla_Usuarios.getSelectedRow(), 2);
            String nombreusuario = (String) tabla_Usuarios.getValueAt(tabla_Usuarios.getSelectedRow(), 1);      
            usuario = tabla_Usuarios.getValueAt(tabla_Usuarios.getSelectedRow(), 1).toString();
            rol = tabla_Usuarios.getValueAt(tabla_Usuarios.getSelectedRow(), 2).toString();
            Servicio_Usuarios su = new Servicio_Usuarios();            
          
            if (su.getUsuario_Nombre(nombreusuario).getFechaExp().before(ahora)) {
                JOptionPane.showMessageDialog(null,"Su fecha ha expirado","Expiración de Clave", 2);
                
            }else{
                IU_VerificacionPassword password = new IU_VerificacionPassword(idseleccionado, rol, nombreusuario);
                UserSession.USER =nombreusuario;
                UserSession.ROL =rol;
                dispose();
                password.setVisible(true);
            }               

        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_USUARIO);
        }
    }//GEN-LAST:event_boton_IngresarActionPerformed

    private void tabla_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_UsuariosMouseClicked
        if (evt.getClickCount() == 2) {
//            System.out.println("filaSeleccionada:" + filaSeleccionada);
            boton_Ingresar.doClick();
        }
    }//GEN-LAST:event_tabla_UsuariosMouseClicked

    private void tabla_UsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_UsuariosMouseReleased
        filaSeleccionada = tabla_Usuarios.getSelectedRow();
        idseleccionado = (int) tabla_Usuarios.getValueAt(filaSeleccionada, 0);
        seleccion = true;
    }//GEN-LAST:event_tabla_UsuariosMouseReleased

    private void tabla_UsuariosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_UsuariosKeyTyped
        tabla_Usuarios.setRowSelectionInterval(filaSeleccionada,filaSeleccionada);
        String validacion = validarEntradas();
        Date ahora = new Date();
        
        if (validacion.equals(tm.VALIDO)) {
            String rol = (String) tabla_Usuarios.getValueAt(filaSeleccionada, 2);
            String nombreusuario = (String) tabla_Usuarios.getValueAt(filaSeleccionada, 1);      
            usuario = tabla_Usuarios.getValueAt(filaSeleccionada, 1).toString();
            rol = tabla_Usuarios.getValueAt(filaSeleccionada, 2).toString();
            Servicio_Usuarios su = new Servicio_Usuarios();            
          
            if (su.getUsuario_Nombre(nombreusuario).getFechaExp().before(ahora)) {
                JOptionPane.showMessageDialog(null,"Su fecha ha expirado","Expiración de Clave", 2);
                
            }else{
                idseleccionado = (int) tabla_Usuarios.getValueAt(filaSeleccionada, 0);
                IU_VerificacionPassword password = new IU_VerificacionPassword(idseleccionado, rol, nombreusuario);
                dispose();
                password.setVisible(true);
            }               

        } else {
            tm.manejarMensajes(tm.NO_SELECCIONADO_USUARIO);
        }
    }//GEN-LAST:event_tabla_UsuariosKeyTyped

    private void tabla_UsuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_UsuariosKeyPressed
        
    }//GEN-LAST:event_tabla_UsuariosKeyPressed

    public boolean validarContraseña(int id, String contraseña) {

        String clave = buscarIDUsuario(id).get(0).getClave().toUpperCase();
        if (clave.equals(contraseña)) {
            nombreUsuario = buscarIDUsuario(id).get(0).getNombre();
//            codrol = buscarIDUsuario(id).get(0).getRoles().getNombre();            
            codrol = buscarIDUsuario(id).get(0).getRoles().getNombre();                        
            MENU001 menu = new MENU001(nombreUsuario, codrol, 0, 0);
            menu.setVisible(true);
            dispose();
            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, "La contraseña es incorrecta", "Verificacion de Contraseña", 0);
        }
        return false;

    }

    public String obtenerNombre() {
        Servicio_Control sc = new Servicio_Control();
        return sc.listar_control().get(0).getNombrempresa();
    }

    public ArrayList<Usuarios> buscarIDUsuario(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Usuarios where idusuario = " + id);
        ArrayList lista = (ArrayList) q.list();

        return lista;
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MENU000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MENU000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MENU000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MENU000.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MENU000().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Ingresar;
    public javax.swing.JButton boton_Salir;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla_Usuarios;
    // End of variables declaration//GEN-END:variables

    public class EventoPresionarTecla extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            System.out.println("entraaaaaa a pressed...");
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("evento de tecla enter - AJA");
                boton_Ingresar.addActionListener(null);
            }
        }
        
        public void keyTyped(KeyEvent ke) {
            System.out.println("entraaaaaa a typed...");
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("evento de tecla enter - AJA2");
                boton_Ingresar.addActionListener(null);
            }
        }
    }
}
