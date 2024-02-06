package Mantenimiento;

import Entidades.Cabecproformas;
import Servicios.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CabecproformasDAO extends GenericDAO<Cabecproformas> {
    
    public List filtrarCabecproformas(int idCliente) {
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        
        try {
            
            if ( idCliente == 0 ){
                lista = sesion.createQuery(
                          " from Cabecproformas cp "
                        + " order by cp.fecha desc ")
                        .list();
            } else {
                lista = sesion.createQuery(
                          " from Cabecproformas cp "
                        + " where cp.clientes.idcliente = :idCliente "
                        + " order by cp.fecha desc ")
                        .setParameter("idCliente", idCliente)
                        .list();
            }
            
        } catch (HibernateException e) {
            return null;
        }
        return lista;
    }
    
    public List filtrarCabecproformas(String estadoProforma) {
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        
        try {
            if ( "TODOS".equals(estadoProforma) ) {
                lista = sesion.createQuery(
                          " from Cabecproformas cp "
                        + " order by cp.fecha desc")
                        .list();
            } else {
                lista = sesion.createQuery(
                          " from Cabecproformas cp "
                        + " where cp.estado = :estadoProforma "
                        + " order by cp.fecha desc")
                        .setParameter("estadoProforma", estadoProforma)
                        .list();
            }
        } catch (HibernateException e) {
            return null;
        }
        return lista;
    }
    
    public List filtrarCabecproformas(Date desde, Date hasta) {
        
        return getHibernateTemplate().createQuery(
                  " from Cabecproformas cp "
                + " where cp.fecha between :desde and :hasta "
                + " order by cp.fecha desc")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .list();
    }
    
    public List filtrarCabecproformas(int idCliente, Date desde, Date hasta) {
        return getHibernateTemplate().createQuery(
                  " from Cabecproformas cp "
                + " where cp.clientes.idcliente = :idCliente "
                + " and cp.fecha between :desde and :hasta "
                + " order by cp.fecha desc")
                .setParameter("idCliente", idCliente)
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .list();
    }
    
    public List filtrarCabecproformas(String estadoProforma, Date desde, Date hasta) {
        return getHibernateTemplate().createQuery(
                  " from Cabecproformas cp "
                + " where cp.estado = :estadoProforma "
                + " and cp.fecha between :desde and :hasta "
                + " order by cp.fecha desc")
                .setParameter("estadoProforma", estadoProforma)
                .setParameter("desde", desde)
                .setParameter("hasta", hasta)
                .list();
    }
    
    public boolean actualizarCabecProforma(Cabecproformas cp) {

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();

        try {
            Cabecproformas cabecProforma = new Cabecproformas();
            cabecProforma = (Cabecproformas) sesion.merge(cp);
            sesion.update(cabecProforma);
            tx.commit();
            return true;

        } catch (HibernateException e) {
            e.printStackTrace();
            return false;

        } finally {
            if ( sesion != null ) {
                sesion.close();
            }
        }
    }
}
