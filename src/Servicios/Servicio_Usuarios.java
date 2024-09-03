/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Usuarios;
import Mantenimiento.UsuarioDAO;
import Presentacion.FREP015;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Servicio_Usuarios {
    
    DefaultTableModel modeloUsuarios;
    UsuarioDAO uDAO;
    FREP015 it;

    public Servicio_Usuarios() {
        uDAO = new UsuarioDAO();
    }

    public Servicio_Usuarios(FREP015 it) {
        uDAO = new UsuarioDAO();
        this.it = it;
    }

    public Long getSize() {
        return uDAO.Tamaño_Lista();
    }

    public Usuarios getUsuarios(int id) {
        Usuarios estratificacion;
        estratificacion = uDAO.Obtener_Objeto(id);
        return estratificacion;
    }

    public boolean addUsuarios(Usuarios t) {
        return uDAO.Agregar_Objeto(t);

    }

    public void listar() {
        DefaultTableModel table = (DefaultTableModel)it.tb_usuario.getModel();
        Iterator ite = uDAO.Obtener_Lista_Objetos().iterator();
        SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
        while (ite.hasNext()) {
            Object[] row = new Object[4];
            Usuarios temp = (Usuarios) ite.next();
            row[0] = temp.getNombre();
            row[1] = temp.getRoles().getNombre();
            row[2] = sdt.format(temp.getFechaExp());
            row[3] = temp.getEstado()==1?"Alta":"Baja";
            table.addRow(row);
        }
    }    
    
    
    public List getList() {
        return uDAO.Obtener_Lista_Objetos();
    }
    
    public boolean actualizarUsuarios(Usuarios t) {
        return uDAO.Modificar_Objeto(t);
    }

    public boolean borrarUsuarios(Usuarios t) {
        return uDAO.Eliminar_Objeto(t);
    }

    public int nextId() {
        return (int) uDAO.nextId();
    }

    public Usuarios getUsuario_Nombre(String nombre){
        return uDAO.Obtener_Objeto_por_nombre(nombre);
    }
   
    public void listar_Usuarios(JTable tablaUsuarios) {
        uDAO = new UsuarioDAO();
        ArrayList<Usuarios> usuarios = uDAO.listarUsuariosHabilitados();
        modeloUsuarios = (DefaultTableModel) tablaUsuarios.getModel();
        for (int i = 0; i < usuarios.size(); i++) {
            
            int id = usuarios.get(i).getIdusuario();
            String nombreUsuario = usuarios.get(i).getNombre();
            String RolUsuario = usuarios.get(i).getRoles().getNombre();
            Object[] lineas = {id, nombreUsuario, RolUsuario};
            modeloUsuarios.addRow(lineas);
            
        }
    }
    
    
//    public void listar_Usuarios(JTable tablaUsuarios) {
//        uDAO = new UsuarioDAO();
//        ArrayList<Usuarios> usuarios = uDAO.listarUsuariosHabilitados();
//        modeloUsuarios = (DefaultTableModel) tablaUsuarios.getModel();
//        for (int i = 0; i < uDAO.lista_Tamaño_Usuarios(); i++) {
//            
//            int id = uDAO.listar_Usuarios().get(i).getIdusuario();
//            String nombreUsuario = uDAO.listar_Usuarios().get(i).getNombre();
//            String RolUsuario = uDAO.listar_Usuarios().get(i).getRoles().getNombre();
//            Object[] lineas = {id, nombreUsuario, RolUsuario};
//            modeloUsuarios.addRow(lineas);
//            
//        }
//    }
}
