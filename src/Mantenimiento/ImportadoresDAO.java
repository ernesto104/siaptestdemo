
package Mantenimiento;

import Entidades.Importadores;
import Servicios.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */

public class ImportadoresDAO extends GenericDAO<Importadores> {
    
    private Session sesion;
    private Transaction tx;
    
    public Importadores obtener_Importador_Por_Nombre(String nombreT) {
        Importadores importador = new Importadores();
        try {
            iniciaOperacion();
            importador = (Importadores) sesion.createQuery("from Importadores where nombre='"+nombreT+"'").uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return importador;
    }
    
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }
    
    public List getLista_nombre(String nombre) {
        return getHibernateTemplate().createQuery("from Importadores t where t.nombre like '%" + nombre + "%' order by t.nombre ASC").list();
    }
    
      public List<Importadores> Obtener_Lista_Objetos_OrderNombre() {
        List<Importadores> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Importadores order by nombre ASC").list();
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
