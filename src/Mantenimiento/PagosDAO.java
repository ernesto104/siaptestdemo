package Mantenimiento;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Pagos;
import Servicios.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Keily Mendiolaza
 */
public class PagosDAO extends GenericDAO<Pagos> {

    public List<Pagos> listarPagosxCabecera(CabecesId cabecesID) {
        return getHibernateTemplate().createQuery("from Pagos where cabeces.id.tipotra = 'V' and cabeces.id.tipodoc = '" + cabecesID.getTipodoc() + "'"
                + " and cabeces.id.nrorserie = '" + cabecesID.getNrorserie() + "' and cabeces.id.nrodocumento = '" + cabecesID.getNrodocumento() + "'").list();
    }

    public List<Pagos> listarPagosxCabeceSal(int idSal) {
        return getHibernateTemplate().createQuery("from Pagos where idsalida =  '" + idSal + "' order by idpago desc").list();
    }

//    public Object obtenerPago() {
    public String obtenerPago() {
        String idPago = "0";
        Query q = (Query) getHibernateTemplate().createQuery("select max(idpago) from Pagos");
        ArrayList lista = (ArrayList) q.list();
        if (lista != null) {
            Iterator it = lista.iterator();
            if (it.hasNext()) {
                idPago = String.valueOf(it.next());
            }
        }
        System.out.println("igual (1):" + ("null".equals(idPago)));
        System.out.println("igual (2):" + (null == idPago) );
        
        if ( "null".equals(idPago) ) {
            System.out.println("es null");
            idPago = "0";
        }
        System.out.println("idPago(pagosDAO):" + idPago);
        return idPago;

    }
    
    public boolean ActualizarCab(Cabeces cab) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
                Cabeces c = (Cabeces) sesion.merge(cab);
                sesion.update(c);
                sesion.flush();
                sesion.clear();            
                tx.commit();
                
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            System.out.println("Excepcion :" + e.getMessage());
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean GuardarPagos(Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {                        
                sesion.save(pago); 
                sesion.flush();
                sesion.clear();            
                tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean modificarPagos(Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(pago);
            sesion.flush();
            sesion.clear();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean modificar(Pagos pago) {
        Session sesion = getHibernateTemplate();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.update(pago);
            sesion.flush();
            sesion.clear();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean eliminarPagos(Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.delete(pago);
            sesion.flush();
            sesion.clear();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
}