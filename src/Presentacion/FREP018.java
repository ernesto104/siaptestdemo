package Presentacion;

import Entidades.Programas;
import Entidades.Roles;
import Servicios.Servicio_Accesos;
import Servicios.Servicio_Programas;
import Servicios.TipoMensaje;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CRS
 */
public class FREP018 extends javax.swing.JFrame {

    Servicio_Accesos servicio_accesos;
    DefaultTableModel dtm_listaprog;
    DefaultTableModel dtm_progasig;
    List<Programas> lista_programas;
    List<Roles> lista_Roles;
    
    public FREP018() {      
        servicio_accesos = new Servicio_Accesos();
        initComponents();
        lista_programas = servicio_accesos.Lista_Programas();
        dtm_listaprog = (DefaultTableModel) tb_listaProg.getModel();
        dtm_progasig = (DefaultTableModel) tb_ProgAsig.getModel();
        ListarRoles();

    }
    final public void ListarRoles() {
        List<Roles> nueva_Lista = servicio_accesos.Lista_Roles();
        if(lista_Roles==null){
            lista_Roles = nueva_Lista;
        }else{
            for(Roles r : nueva_Lista){
                if(!EstaEn(lista_Roles, r)){
                    lista_Roles.add(r);
                }
            }
        }
        cb_roles.removeAllItems();
        for(Roles rol : lista_Roles){
            cb_roles.addItem(rol);
        }
        cb_roles.setSelectedIndex(0);
        ListarProgramas_Rol((Roles) cb_roles.getSelectedItem());
    }

    public void ListarProgramas_Rol(Roles Rol) {
        
        List<Programas> Programas_ = new Servicio_Programas().listarProgramas(Rol.getIdrol());
        
        BorrarTabla(dtm_progasig);
        BorrarTabla(dtm_listaprog);
        
        for (Programas p : Programas_) {
            AgregarPrograma(p, dtm_progasig);
        }
        for (Programas p : lista_programas) {
            if (!EstaEn(Programas_,  p)) {
                AgregarPrograma( p, dtm_listaprog);
            }
        }
    }
    
    public boolean EstaEn(List<Programas> Programas_, Programas pr) {
        for (Object p : Programas_) {
            if (((Programas) p).getCodprograma().equals(pr.getCodprograma())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean EstaEn(List<Roles> N_Roles,Roles r){
        for(Roles rol : N_Roles){
            if(rol.getCodigorol().equals(r.getCodigorol()))
                return true;
        }
        return false;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAcceso = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_roles = new javax.swing.JComboBox();
        bt_addRol = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listaProg = new javax.swing.JTable();
        bt_addPrograma = new javax.swing.JButton();
        bt_AgregarTodo = new javax.swing.JButton();
        bt_Agregar = new javax.swing.JButton();
        bt_quitar = new javax.swing.JButton();
        bt_quitartodo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_ProgAsig = new javax.swing.JTable();
        bt_aceptar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ROL");

        cb_roles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_rolesItemStateChanged(evt);
            }
        });

        bt_addRol.setText("AGREGAR ROL");
        bt_addRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addRolActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("GESTIONAR ACCESOS"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("LISTADO DE PROGRAMAS"));

        tb_listaProg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "DESC", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_listaProg.getTableHeader().setReorderingAllowed(false);
        tb_listaProg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_listaProgMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_listaProg);
        if (tb_listaProg.getColumnModel().getColumnCount() > 0) {
            tb_listaProg.getColumnModel().getColumn(0).setPreferredWidth(5);
            tb_listaProg.getColumnModel().getColumn(1).setPreferredWidth(300);
            tb_listaProg.getColumnModel().getColumn(2).setMinWidth(0);
            tb_listaProg.getColumnModel().getColumn(2).setPreferredWidth(0);
            tb_listaProg.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        bt_addPrograma.setText("ADICIONAR PROGRAMA");
        bt_addPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addProgramaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(bt_addPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bt_AgregarTodo.setText("AGREGAR TODO");
        bt_AgregarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AgregarTodoActionPerformed(evt);
            }
        });

        bt_Agregar.setText("AGREGAR ");
        bt_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AgregarActionPerformed(evt);
            }
        });

        bt_quitar.setText("QUITAR");
        bt_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_quitarActionPerformed(evt);
            }
        });

        bt_quitartodo.setText("QUITAR TODO");
        bt_quitartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_quitartodoActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("PROGRAMAS ASIGNADOS"));

        tb_ProgAsig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD", "DESC", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_ProgAsig.getTableHeader().setReorderingAllowed(false);
        tb_ProgAsig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_ProgAsigMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tb_ProgAsig);
        if (tb_ProgAsig.getColumnModel().getColumnCount() > 0) {
            tb_ProgAsig.getColumnModel().getColumn(0).setPreferredWidth(5);
            tb_ProgAsig.getColumnModel().getColumn(1).setPreferredWidth(300);
            tb_ProgAsig.getColumnModel().getColumn(2).setMinWidth(0);
            tb_ProgAsig.getColumnModel().getColumn(2).setPreferredWidth(0);
            tb_ProgAsig.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_AgregarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_quitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_quitartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(bt_AgregarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_quitartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_aceptar.setText("ACEPTAR");
        bt_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aceptarActionPerformed(evt);
            }
        });

        bt_salir.setText("SALIR");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACCESO AL SISTEMA");

        javax.swing.GroupLayout panelAccesoLayout = new javax.swing.GroupLayout(panelAcceso);
        panelAcceso.setLayout(panelAccesoLayout);
        panelAccesoLayout.setHorizontalGroup(
            panelAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccesoLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(bt_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAccesoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cb_roles, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(bt_addRol, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAccesoLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelAccesoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAccesoLayout.setVerticalGroup(
            panelAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccesoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panelAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_addRol, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(panelAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addRolActionPerformed
        FREP017 nuevo_Rol = new FREP017();
        nuevo_Rol.setLocationRelativeTo(null);
        nuevo_Rol.setVisible(true);
        nuevo_Rol.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt) {
                ListarRoles();
            }
        });
    }//GEN-LAST:event_bt_addRolActionPerformed

    private void cb_rolesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_rolesItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            ListarProgramas_Rol((Roles) cb_roles.getSelectedItem());
        }
    }//GEN-LAST:event_cb_rolesItemStateChanged

    private void bt_AgregarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AgregarTodoActionPerformed
        Set<Programas> contiene = ((Roles)cb_roles.getSelectedItem()).getProgramases();
        for(int i=0; i<dtm_listaprog.getRowCount();i++){
            contiene.add((Programas) dtm_listaprog.getValueAt(i, 2));            
        }
        BorrarTabla(dtm_listaprog);
        BorrarTabla(dtm_progasig);
        
        for(Programas p : contiene){
            AgregarPrograma(p, dtm_progasig);
        }
    }//GEN-LAST:event_bt_AgregarTodoActionPerformed

    private void bt_quitartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_quitartodoActionPerformed
        Set<Programas> contiene = ((Roles)cb_roles.getSelectedItem()).getProgramases();
        contiene.clear();
        BorrarTabla(dtm_listaprog);
        BorrarTabla(dtm_progasig);
        for(Programas p : lista_programas){
            AgregarPrograma(p, dtm_listaprog);
        }
        
    }//GEN-LAST:event_bt_quitartodoActionPerformed

    private void bt_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AgregarActionPerformed
        TipoMensaje tm = new TipoMensaje();
        int[] filas = tb_listaProg.getSelectedRows();       
        Set<Programas> contiene = ((Roles)cb_roles.getSelectedItem()).getProgramases();
        if(filas.length>0){
            for(int i : filas){
                contiene.add((Programas) dtm_listaprog.getValueAt(filas[0], 2));
                AgregarPrograma( (Programas) dtm_listaprog.getValueAt(filas[0], 2), dtm_progasig);
                dtm_listaprog.removeRow(filas[0]);
            }
        }else{
            tm.Error("SELECCIONE AL MENOS UN PROGRAMA");
        }
    }//GEN-LAST:event_bt_AgregarActionPerformed

    private void bt_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_quitarActionPerformed
        TipoMensaje tm = new TipoMensaje();
        int[] filas = tb_ProgAsig.getSelectedRows();       
        Set<Programas> contiene = ((Roles)cb_roles.getSelectedItem()).getProgramases();
        if(filas.length>0){
            for(int i : filas){
                AgregarPrograma( (Programas) dtm_progasig.getValueAt(filas[0], 2), dtm_listaprog);
                contiene.remove((Programas) dtm_progasig.getValueAt(filas[0], 2));                
                dtm_progasig.removeRow(filas[0]);
                
            }
        }else{
            tm.Error("SELECCIONE AL MENOS UN PROGRAMA");
        }
    }//GEN-LAST:event_bt_quitarActionPerformed

    private void bt_addProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addProgramaActionPerformed
        FREP016 nuevo_Programa = new FREP016();
        nuevo_Programa.setLocationRelativeTo(null);
        nuevo_Programa.setVisible(true);
        nuevo_Programa.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt) {
                lista_programas = servicio_accesos.Lista_Programas();
                ListarProgramas_Rol((Roles)cb_roles.getSelectedItem());
            }
        });
    }//GEN-LAST:event_bt_addProgramaActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed

            dispose();

    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aceptarActionPerformed
        TipoMensaje tm = new TipoMensaje();
        int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
        if(opcion == tm.SI){
            //if(servicio_accesos.ActualizarAccesos(lista_Roles)){
            
            Roles rol = (Roles) cb_roles.getSelectedItem();
            List<Programas> iniciales = new Servicio_Programas().listarProgramas(rol.getIdrol());
            int actuales = dtm_progasig.getRowCount();
            System.out.println("Nro actuales:" + actuales);
            List<Programas> programasEliminados = new ArrayList<>();
            List<Programas> programasNuevos = new ArrayList<>();
            Programas prog = null;
            boolean modificado = true;

            for (int i=0; i<iniciales.size(); i++) {
                modificado = true;
                for (int j=0; j<actuales; j++) {
                    prog = (Programas)dtm_progasig.getValueAt(j, 2);
                    if (iniciales.get(i).getIdprograma() == prog.getIdprograma()) {
                        modificado = false;
                        break;
                    }
                }
                if (modificado) {
                    programasEliminados.add(iniciales.get(i)); // ELIMINAR ACCESO AL PROGRAMA
                }
            }
            System.out.println("eliminados:");
            int i = 0, j = 0;
            for (Programas pr : programasEliminados) {
                System.out.println((i+1) + ":" + pr.getIdprograma());
                i++;
            }

            System.out.println("nuevos:");
            System.out.println("actuales:" + actuales);
            System.out.println("iniciales.size():" + iniciales.size());
            for (i=0; i<actuales; i++) {
                prog = (Programas)dtm_progasig.getValueAt(i, 2);
                modificado = true;
                for (j=0; j<iniciales.size(); j++) {
                    System.out.println("prog.getIdprograma():" + prog.getIdprograma());
                    System.out.println("iniciales.get(j).getIdprograma():" + iniciales.get(j).getIdprograma());
                    
                    if (iniciales.get(j).getIdprograma() == prog.getIdprograma()) {
                        modificado = false;
                        break;
                    }
                }
                if (modificado) {
                    System.out.println("se agrega..." + prog.getIdprograma());
                    programasNuevos.add(prog); // NUEVO ACCESO AL PROGRAMA
                }
            }
            i = 0;
            System.out.println("programasNuevos:" + programasNuevos);
            System.out.println("Se van agregar:");
            for (Programas pr : programasNuevos) {
                System.out.println((i+1) + ":" + pr.getIdprograma());
                i++;
            }
            
            //if(servicio_accesos.ActualizarAccesos(lista_programas, (Roles) cb_roles.getSelectedItem())){
            if(servicio_accesos.ActualizarAccesos(programasEliminados, programasNuevos, (Roles) cb_roles.getSelectedItem())){
                tm.Mensaje("OPERACIÓN EXITOSA");
                dispose();
            }else{
                tm.Error("OCURRIÓ UN ERROR AL GUARDAR");
            }            
        }
        /*
        TipoMensaje tm = new TipoMensaje();
        int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
        if(opcion == tm.SI){
            //if(servicio_accesos.ActualizarAccesos(lista_Roles)){
            
            
            
            if(servicio_accesos.ActualizarAccesos(lista_programas, (Roles) cb_roles.getSelectedItem())){
                tm.Mensaje("OPERACIÓN EXITOSA");
                dispose();
            }else{
                tm.Error("OCURRIÓ UN ERROR AL GUARDAR");
            }            
        }
        */
    }//GEN-LAST:event_bt_aceptarActionPerformed

    private void tb_listaProgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listaProgMouseReleased
        tb_ProgAsig.clearSelection();
    }//GEN-LAST:event_tb_listaProgMouseReleased

    private void tb_ProgAsigMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ProgAsigMouseReleased
        tb_listaProg.clearSelection();
    }//GEN-LAST:event_tb_ProgAsigMouseReleased
    private void BorrarTabla(DefaultTableModel modelo) {
        int numRows = modelo.getRowCount();
        for (int i = 0; i < numRows; i++) {
            modelo.removeRow(0);
        }
    }

    private void AgregarPrograma(Programas p, DefaultTableModel modelo) {
        Object[] fila = {p.getCodprograma(), p.getDescripcion(),p};
        modelo.addRow(fila);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_Agregar;
    public javax.swing.JButton bt_AgregarTodo;
    public javax.swing.JButton bt_aceptar;
    public javax.swing.JButton bt_addPrograma;
    public javax.swing.JButton bt_addRol;
    public javax.swing.JButton bt_quitar;
    public javax.swing.JButton bt_quitartodo;
    public javax.swing.JButton bt_salir;
    public javax.swing.JComboBox cb_roles;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel panelAcceso;
    public javax.swing.JTable tb_ProgAsig;
    public javax.swing.JTable tb_listaProg;
    // End of variables declaration//GEN-END:variables
}
