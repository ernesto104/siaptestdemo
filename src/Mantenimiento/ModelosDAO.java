
package Mantenimiento;

import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Modelos;
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
public class ModelosDAO extends GenericDAO<Modelos> {

    private Session sesion;
    private Transaction tx;

    public int nextId() {
        Modelos mod = null;
        try {
            mod = (Modelos) getHibernateTemplate().createQuery("from Modelos m order by m.idmodelo desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return mod.getIdmodelo() + 1;
        }
    }
    //no se utiliza
    public void agregar(int id, String descripcion, String estado,Equipos equipo ,Marcas marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Modelos modelos = new Modelos();
        modelos.setIdmodelo(id);
        modelos.setEquipo(equipo);
        modelos.setMarca(marca);
        modelos.setDescripcion(descripcion);
        modelos.setEstado(estado);
        try {
            session.beginTransaction();
            session.save(modelos);
            session.getTransaction().commit();            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
        }

    }

    public void modificar(int id, String descripcion, String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Modelos modelos = new Modelos();
        modelos.setIdmodelo(id);
        modelos.setDescripcion(descripcion);
        modelos.setEstado(estado);
        try {
            session.beginTransaction();
            session.update(modelos);
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

    public Modelos Obtener_Objeto_por_codigo(int codigo) {
        Modelos mod = new Modelos();
        try {
            mod = (Modelos) getHibernateTemplate().createQuery("from Modelos where idmodelo='" + codigo + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return mod;
    }

    public Modelos Obtener_Objeto_por_nombre(String nombre) {
        Modelos mod = new Modelos();
        mod = (Modelos) getHibernateTemplate().createQuery("from Modelos where descripcion='" + nombre + "'").uniqueResult();       
        return mod;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }

    public List<Modelos> Obtener_Lista_Objetos_OrderNombre() {
        List<Modelos> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Equipos order by idlinea ASC").list();
            lista = sesion.createQuery("from Modelos order by descripcion ASC").list();
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
    
    
    public List<Modelos> Obtener_Lista_Objetos_OrderMarcas(Marcas marca) {
        List<Modelos> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Equipos order by idlinea ASC").list();
            lista = getHibernateTemplate().createQuery("from Modelos where marca.idmarca like '"+marca.getIdmarca()+"' order by descripcion ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;

    }
}
