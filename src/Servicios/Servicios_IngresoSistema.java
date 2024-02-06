/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Mantenimiento.UsuarioDAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class Servicios_IngresoSistema {    
    
    DefaultTableModel modeloUsuarios;
    UsuarioDAO usuarioDAO;

    public void listar_Usuarios(JTable tablaUsuarios) {
        usuarioDAO = new UsuarioDAO();
        
        modeloUsuarios = (DefaultTableModel) tablaUsuarios.getModel();
        for (int i = 0; i < usuarioDAO.lista_Tamaño_Usuarios(); i++) {
            String nombreUsuario = usuarioDAO.listar_Usuarios().get(i).getNombre();
            int numRolUsuario = usuarioDAO.listar_Usuarios().get(i).getRoles().getIdrol();
            String nombreRolUsuario = usuarioDAO.nombreRolUsuario(numRolUsuario);  
            Object[] lineas = {nombreUsuario,nombreRolUsuario};
            modeloUsuarios.addRow(lineas);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
