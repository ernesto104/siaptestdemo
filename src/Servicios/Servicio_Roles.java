/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Roles;
import Mantenimiento.RolesDAO;
import Presentacion.FREP017;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Servicio_Roles {

    RolesDAO rDAO;
    FREP017 it;

    public Servicio_Roles(FREP017 it) {
        rDAO = new RolesDAO();
        this.it = it;
    }

    public Long getSize() {
        return rDAO.Tamaño_Lista();
    }

    public Roles getRol(int id) {
        Roles rol;
        rol = rDAO.Obtener_Objeto(id);
        return rol;
    }

    public boolean addRol(Roles t) {
        return rDAO.Agregar_Objeto(t);

    }

    public List getList() {
        return rDAO.Obtener_Lista_Objetos();
    }

    public void listar() {
        DefaultTableModel table = (DefaultTableModel) it.tb_roles.getModel();
        Iterator ite = rDAO.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[2];
            Roles temp = (Roles) ite.next();
            row[0] = temp.getCodigorol();
            row[1] = temp.getNombre();
            table.addRow(row);
        }
    }

    public Roles getRol_por_codigo(String codigo) {
        return rDAO.Obtener_Rol_codigo(codigo);
    }

    public boolean actualizarRol(Roles r) {
        return rDAO.Modificar_Objeto(r);
    }

    public boolean borrarRol(Roles r) {
        return rDAO.Eliminar_Objeto(r);
    }

    public Roles getRol_por_nombre(String nombre) {
        Roles rol = new Roles();
        rol = rDAO.Obtener_Objeto_por_nombre(nombre);
        return rol;
    }

    
}
