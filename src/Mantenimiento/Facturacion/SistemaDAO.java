/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento.Facturacion;

import Entidades.Sistema;
import Mantenimiento.GenericDAO;
import Servicios.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CRS
 */
public class SistemaDAO extends GenericDAO<Sistema>{
    public Sistema obtener_por_nombre(String nombre){
//        getHibernateTemplate().beginTransaction();
//        return (Sistema) session.createQuery("from Sistema s where s.descripcion = '" + nombre.toUpperCase() + "'").uniqueResult();
        return (Sistema) getHibernateTemplate().createQuery("from Sistema s where s.descripcion = '" + nombre.toUpperCase() + "'").uniqueResult();
    }
    public String obtener_por_Codigo(String id){
        String s = (String) getHibernateTemplate().createQuery("select s.descripcion from Sistema s where s.tipodoc = '"+id+"'").uniqueResult();
        return s;
    }
    
    public Sistema getActualNroSerie(String tipoDoc) {
        return (Sistema) getHibernateTemplate().createQuery("from Sistema where tipodoc = '" + tipoDoc + "'").uniqueResult();
    }
    
    public boolean actualizar(Sistema s) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Sistema sis = (Sistema) sesion.merge(s);
            sesion.update(sis);
            sesion.flush();
            sesion.clear();
            
        } catch ( Exception ex ) {
            System.out.println("Excepcion(actualizar):" + ex.getMessage());
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
}
