package Mantenimiento;

import Entidades.Vendedores;
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
public class VendedorDAO extends GenericDAO<Vendedores> {
private Session sesion;
    private Transaction tx;
    
    public int nextId() {
        Vendedores t = null;
        try {
            t = (Vendedores) getHibernateTemplate().createQuery("from Vendedores t order by t.idvendedor desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return t.getIdvendedor() + 1;
        }
    }
    
    
    public Vendedores Obtener_Vendedor_PorNombre(String nombre) {
        Vendedores vendedor = new Vendedores();
        try {
            iniciaOperacion();
            vendedor = (Vendedores) sesion.createQuery("from Vendedores where nombre like '" + nombre + "'").uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return vendedor;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        //   System.out.println("Conectando ...");
    }
    
    public List<Vendedores> Obtener_Lista_Objetos_OrderNombre() {
        List<Vendedores> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Vendedores order by nombre ASC").list();
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
