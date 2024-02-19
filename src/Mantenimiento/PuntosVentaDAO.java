
package Mantenimiento;

import Entidades.Equipos;
import Entidades.PuntosVenta;
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
public class PuntosVentaDAO extends GenericDAO<PuntosVenta> {

    private Session sesion;
    private Transaction tx;

    public int nextId() {
        PuntosVenta l = null;
        try {
            l = (PuntosVenta) getHibernateTemplate().createQuery("from PuntosVenta l order by l.idpuntosventa desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return l.getIdpuntosventa()+ 1;
        }
    }

    public void agregar(int id, String descripcion, double descuento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PuntosVenta puntosventa = new PuntosVenta();
        puntosventa.setIdpuntosventa(id);
        puntosventa.setDescripcion(descripcion);
        try {
            session.beginTransaction();
            session.save(puntosventa);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void modificar(int id, String descripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PuntosVenta puntosventa = new PuntosVenta();
        puntosventa.setIdpuntosventa(id);
        puntosventa.setDescripcion(descripcion);
        //equipos.setDescuento1(descuento);
        try {
            session.beginTransaction();
            session.update(puntosventa);
            session.getTransaction().commit();           
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void eliminar(int id, String descripcion, double descuento) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PuntosVenta puntosventa = new PuntosVenta();
        puntosventa.setIdpuntosventa(id);
        puntosventa.setDescripcion(descripcion);
        //puntosventa.setDescuento1(descuento);
        try {
            session.beginTransaction();
            session.delete(puntosventa);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public PuntosVenta Obtener_Objeto_por_codigo(int codigo) {
        PuntosVenta l = new PuntosVenta();
        try {
            l = (PuntosVenta) getHibernateTemplate().createQuery("from PuntosVenta where idpuntosventa='" + codigo + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return l;
    }

    public PuntosVenta Obtener_Objeto_por_nombre(String nombre) {
        PuntosVenta li = new PuntosVenta();
        li = (PuntosVenta) getHibernateTemplate().createQuery("from PuntosVenta where descripcion='" + nombre + "'").uniqueResult();       
        return li;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }

    public List<PuntosVenta> Obtener_Lista_Objetos_OrderNombre() {
        List<PuntosVenta> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Equipos order by idlinea ASC").list();
            lista = sesion.createQuery("from PuntosVenta order by descripcion ASC").list();
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
