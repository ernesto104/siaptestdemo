
package Mantenimiento;

import Entidades.Roles;
import Entidades.Usuarios;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrador
 */
public class UsuarioDAO extends GenericDAO<Usuarios> {

    public int nextId() {
        Usuarios us = null;
        try {
            us = (Usuarios) getHibernateTemplate().createQuery("from Usuarios e order by e.idusuario desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return us.getIdusuario() + 1;
        }
    }

    public Usuarios Obtener_Objeto_por_nombre(String nombre) {
        Usuarios us = new Usuarios();
        us = (Usuarios) getHibernateTemplate().createQuery("from Usuarios where nombre='" + nombre + "'").uniqueResult();        
        return us;
    }

    public ArrayList<Usuarios> listar_Usuarios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Usuarios");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    
    /*   */
    
    public ArrayList<Usuarios> listarUsuariosHabilitados(){
        ArrayList lista = (ArrayList) getHibernateTemplate().createQuery("from Usuarios where estado= 1").list();
        return lista;
    }
    
    /*   */

    public int lista_Tamaño_Usuarios() {
        int tamaño = listar_Usuarios().size();
        return tamaño;
    }

    public ArrayList<Roles> buscarRolUsuario(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Roles where idrol = " + id);
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public ArrayList<Usuarios> buscarIDUsuario(int id) {
        System.out.println("entro");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Usuarios where idusuario = " + id);
        ArrayList lista = (ArrayList) q.list();

        return lista;
    }

    public String nombreRolUsuario(int id) {
        String nombreRolUsuario = String.valueOf(buscarRolUsuario(id).get(0).getIdrol());
        return nombreRolUsuario;
    }
}
