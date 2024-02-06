
package Mantenimiento;

import Entidades.Equipos;
import Servicios.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Keily Mendiolaza
 */
public class EquipoDAO extends GenericDAO<Equipos> {

    private Session sesion;
    private Transaction tx;

    public int nextId() {
        Equipos l = null;
        try {
            l = (Equipos) getHibernateTemplate().createQuery("from Equipos l order by l.idequipo desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return l.getIdequipo() + 1;
        }
    }

    public void agregar(int id, String descripcion, double descuento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session.beginTransaction();
            session.save(equipos);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void modificar(int id, String descripcion, double descuento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session.beginTransaction();
            session.update(equipos);
            session.getTransaction().commit();           
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void eliminar(int id, String descripcion, double descuento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session.beginTransaction();
            session.delete(equipos);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public Equipos Obtener_Objeto_por_codigo(int codigo) {
        Equipos l = new Equipos();
        try {
            l = (Equipos) getHibernateTemplate().createQuery("from Equipos where idequipo='" + codigo + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return l;
    }

    public Equipos Obtener_Objeto_por_nombre(String nombre) {
        Equipos li = new Equipos();
        li = (Equipos) getHibernateTemplate().createQuery("from Equipos where descripcion='" + nombre + "'").uniqueResult();       
        return li;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }

    public List<Equipos> Obtener_Lista_Objetos_OrderNombre() {
        List<Equipos> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Equipos order by idlinea ASC").list();
            lista = sesion.createQuery("from Equipos order by descripcion ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;

    }
}
