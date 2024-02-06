/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento.Facturacion;

import Entidades.Cabecproformas;
import Entidades.Detalleproformas;
import Mantenimiento.GenericDAO;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CRS
 */
public class ProformaDAO extends GenericDAO<Cabecproformas>{
     public boolean GuardarProforma(Cabecproformas cabp, ArrayList<Detalleproformas> dt) {
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {            
            sesion.save(cabp);
            for (Detalleproformas dtll : dt) {
                sesion.save(dtll);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            return false;
        }finally{
            sesion.close();
        }
        return true;
    }
     public List getProformaxCliente(String cliente){
         Session sesion=HibernateUtil.getSessionFactory().openSession();
         List<Cabecproformas> lista ;
         try{
             lista=sesion.createQuery("from Cabecproformas cp where cp.clientes.empresa like '"+cliente+"%'").list();             
         }catch(HibernateException e){
             return null;
         }
         return lista;
         
     }

     public List getlista(){
         Session sesion=HibernateUtil.getSessionFactory().openSession();
         List<Cabecproformas> lista ;
         try{
             lista=sesion.createQuery("from Cabecproformas").list();             
         }catch(HibernateException e){
             return null;
         }
         return lista;
     }
     public List getxCabec(Cabecproformas cabec){
         return getHibernateTemplate().createQuery("from Detalleproformas d where d.cabecproformas.idcabproforma = "+
                                                cabec.getIdcabproforma()).list();
     }
     
     public boolean actualizarProforma(Cabecproformas cabecproformas) {
         
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            Cabecproformas proforma = new Cabecproformas();
            proforma = (Cabecproformas) sesion.merge(cabecproformas);
            sesion.update(proforma);
            sesion.flush();
            tx.commit();
            return true;
            
        } catch( HibernateException e ) {
            tx.rollback();
            return false;
            
        } finally {
            if ( sesion != null ) {
                sesion.close();
            }
        }
     }
     
//     public Cabecproformas obtenerProforma(int idProforma) {
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Cabecproformas cabProf = new Cabecproformas();
//        try {
//            cabProf = (Cabecproformas) sesion.createQuery("from Cabecproformas where idcabproforma = " + idProforma).uniqueResult();
//        } catch (Exception e) {
//            cabProf = null;
//            System.err.println("Error : " + e.getMessage());
//        } finally {
//            sesion.close();
//        }
//        return cabProf;
//    }
}