
package Mantenimiento;

import Entidades.Detallenota;
import Servicios.HibernateUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */

public class DetalleNotaDAO extends GenericDAO<Detallenota>{
    
    private Session sesion;
    private Transaction tx;
    
    public List getListaDetallePorDocumento(String numeroDocumento) {
        List<Detallenota> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Detallenota where NRODOCUMENTO='"+numeroDocumento+"'").list();
            
            // Para corregir el error: org.hibernate.LazyInitializationException: could not initialize proxy - no Session
            if ( lista != null ) {
                System.out.println("N° elementos:" + lista.size());
                Iterator ite = lista.iterator();
//                int i = 0;
                
                while ( ite.hasNext() ) {
                    Detallenota detNot = (Detallenota) ite.next();
                    detNot.getRepuestos().getCodrepuesto();
//                    lista.get(i).getRepuestos().getCodrepuesto();
//                    i++;
                }
//                lista.get(0).getCantidad();
            }
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
    
    public List getListaDetallePorTipoDocumento(String numeroDocumento, String tipoDoc) {
        List<Detallenota> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Detallenota where NRODOCUMENTO='"+numeroDocumento+"' AND TIPODOC='"+tipoDoc+"'").list();
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
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }
    
}
