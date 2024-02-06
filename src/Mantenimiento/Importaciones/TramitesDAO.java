package Mantenimiento.Importaciones;
import Entidades.Equipos;
import Entidades.Tramites;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Servicios.HibernateUtil;
public class TramitesDAO extends GenericDAO<Tramites>  {
    public TramitesDAO() {
        
    }
    public int nextId() {
        Tramites t=null;
        try {
            t=(Tramites) getHibernateTemplate().createQuery("from Tramites t order by t.idtramite desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } 
        if(Tamaño_Lista()==0)
            return 1;
        else{
            return t.getIdtramite()+1;
        }
    }
    public int getIdTramite(int idsugerido,String glosa){
        int idTramite=-1;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select t.idtramite from "
                + "Tramites as t where t.glosa like '"+glosa+"'"
                + " and t.cabecsugerido.idsugerido="+idsugerido);
        ArrayList lista = (ArrayList) q.list();
        Iterator it = lista.iterator();
        if(it.hasNext()){
            idTramite=(int)it.next();
        }
        return idTramite;
    }
    public ArrayList<Tramites> getTramites(int idsugerido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Tramites as t where t.cabecsugerido.idsugerido="+idsugerido+" order by glosa asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    public Equipos obtenerEquipo(String descripcion) {
        Equipos l=(Equipos) getHibernateTemplate().createQuery(
                "from Equipos li where li.descripcion like '"+descripcion+"'")
                .list().iterator().next();
        return l;
    }
    public boolean updateImpuesto(Integer impuestoID, double importe, String glosa){
      SessionFactory factory;
      boolean valor=false;
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Fallo al crear el objeto sessionFactory en update de TramitesDAO" + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Tramites tramite = (Tramites)session.get(Tramites.class, impuestoID); 
         tramite.setGlosa(glosa);
         tramite.setImporte(importe);
         session.update(tramite); 
         tx.commit();
         valor=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return valor;
    }
    public boolean deleteImpuesto(int idImpuesto){
        boolean val=false;
        SessionFactory factory;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Fallo al crear el objeto sessionFactory en delete de TramitesDAO" + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Tramites tramite = (Tramites)session.get(Tramites.class, idImpuesto); 
            session.delete(tramite); 
            tx.commit();
            val=true;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
        return val;
    }
    public int countGlosas(int idCabSug,String glosa) {
        List returnList = getHibernateTemplate().createSQLQuery(
                "select count(t.idtramite) from Tramites as t where t.glosa like '"+glosa+"' and "
                + "t.idsugerido="+idCabSug).list();
        session.getTransaction().commit();
        Iterator iteDet=returnList.iterator();
        int count=-1;
        if(iteDet.hasNext()){
            count=Integer.parseInt(String.valueOf(iteDet.next()));
        }
        return count;
    }
    public double getImporteTotal(int idsugerido){
        List returnList = getHibernateTemplate().createSQLQuery(
                "select sum(importe) from tramites where idsugerido="+idsugerido).list();
        session.getTransaction().commit();
        Iterator iteDet=returnList.iterator();
        double suma=0.0;
        if(iteDet.hasNext()){
            suma=Double.parseDouble(String.valueOf(iteDet.next()));
        }
        return suma;
    }    
    public boolean updateImpuesto(Tramites tramiteAnt) {
      SessionFactory factory;
      boolean valor=false;
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Fallo al crear el objeto sessionFactory en update de TramitesDAO" + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         int idTramite=tramiteAnt.getIdtramite();
         Tramites tramiteNew = (Tramites)session.get(Tramites.class, idTramite); 
         tramiteNew.setGlosa(tramiteAnt.getGlosa());
         tramiteNew.setImporte(tramiteAnt.getImporte());
         tramiteNew.setCabecsugerido(null);
         session.update(tramiteNew);
         tx.commit();
         valor=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return valor;
    }
}