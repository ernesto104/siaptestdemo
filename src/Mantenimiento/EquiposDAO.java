
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
public class EquiposDAO extends GenericDAO<Equipos> {

    private Session sesion;
    private Transaction tx;

    public int nextId() {
        Equipos l = null;
        try {
            l = (Equipos) getHibernateTemplate().createQuery("from Equipos l order by l.idequipo desc").iterate().next();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return l.getIdequipo() + 1;
        }
    }

    public void agregar(int id, String descripcion, double descuento) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session1.beginTransaction();
            session1.save(equipos);
            session1.getTransaction().commit();            
        } catch (HibernateException e) {
            session1.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void modificar(int id, String descripcion, double descuento) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session1.beginTransaction();
            session1.update(equipos);
            session1.getTransaction().commit();           
        } catch (HibernateException e) {
            session1.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void eliminar(int id, String descripcion, double descuento) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        Equipos equipos = new Equipos();
        equipos.setIdequipo(id);
        equipos.setDescripcion(descripcion);
        equipos.setDescuento1(descuento);
        try {
            session1.beginTransaction();
            session1.delete(equipos);
            session1.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public Equipos Obtener_Objeto_por_codigo(int codigo) {
        Equipos l = new Equipos();
        try {
            iniciaOperacion();
            l = (Equipos) getHibernateTemplate().createQuery("from Equipos where idequipo='" + codigo + "'").uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            if(session  !=null) {
            session.close();}
        }
        return l;
    }
    
    public String Obtener_NombreObjeto_por_codigo(int codigo) {
        String l = null;
        try {
            iniciaOperacion();
            l = (String) getHibernateTemplate().createQuery("select descripcion from Equipos where idequipo='" + codigo + "'").uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return l;
    }

    public Equipos Obtener_Objeto_por_nombre(String nombre) {
        Equipos li = new Equipos();
        
        try{
            iniciaOperacion();  
            li = (Equipos) getHibernateTemplate().createQuery("from Equipos where descripcion like '" + nombre + "'").uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
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
