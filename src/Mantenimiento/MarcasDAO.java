
package Mantenimiento;

import Entidades.Equipos;
import Entidades.Marcas;
import Servicios.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Keimy Mendiomaza
 */
public class MarcasDAO extends GenericDAO<Marcas> {

    private Session sesion;
    private Transaction tx;

    public int nextId() {
        Marcas m = null;
        try {
            m = (Marcas) getHibernateTemplate().createQuery("from Marcas m order by m.idmarca desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return m.getIdmarca() + 1;
        }
    }
    //no se utiliza
    public void agregar(int id, String descripcion, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Marcas marcas = new Marcas();
        marcas.setIdmarca(id);
        marcas.setDescripcion(descripcion);
        marcas.setEstado(estado);
        try {
            session.beginTransaction();
            session.save(marcas);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void modificar(int id, String descripcion, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Marcas marcas = new Marcas();
        marcas.setIdmarca(id);
        marcas.setDescripcion(descripcion);
        marcas.setEstado(estado);
        try {
            session.beginTransaction();
            session.update(marcas);
            session.getTransaction().commit();           
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void eliminar(int id, String descripcion, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Marcas marcas = new Marcas();
        marcas.setIdmarca(id);
        marcas.setDescripcion(descripcion);
        marcas.setEstado(estado);
        try {
            session.beginTransaction();
            session.delete(marcas);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public Marcas Obtener_Objeto_por_codigo(int codigo) {
        Marcas m = new Marcas();
        try {
            m = (Marcas) getHibernateTemplate().createQuery("from Marcas where idmarca='" + codigo + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return m;
    }

    public Marcas Obtener_Objeto_por_nombre(String nombre) {
        Marcas marc = new Marcas ();
        marc = (Marcas) getHibernateTemplate().createQuery("from Marcas where descripcion='" + nombre + "'").uniqueResult();       
        return marc;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }

    public List<Marcas> Obtener_Lista_Objetos_OrderNombre() {
        List<Marcas> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Equipos order by idlinea ASC").list();
            lista = sesion.createQuery("from Marcas order by descripcion ASC").list();
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
