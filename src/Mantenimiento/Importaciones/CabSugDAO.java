package Mantenimiento.Importaciones;

import Entidades.Cabecsugerido;
import Entidades.Importadores;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import Servicios.HibernateUtil;

public class CabSugDAO extends GenericDAO<Cabecsugerido>{
    
     public int nextId() {
        Cabecsugerido cs = null;
        try {
            cs = (Cabecsugerido) getHibernateTemplate().createQuery(
                    "from Cabecsugerido cs order by cs.idsugerido desc").iterate().next();
            session.getTransaction().commit();
            
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } 
        
        if ( Tamaño_Lista() == 0 )
            return 1;
        else
            return cs.getIdsugerido() + 1;
    }
     
    public double getTotalFob(int idCabSug){
        double fobTot = 0.00;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery(
                "select sum(ds.valorfob*ds.cantentregada) from Detallesugerido as ds " +
                "inner join ds.cabecsugerido as cs " +
                "where cs.idsugerido = ds.cabecsugerido.idsugerido " +
                "and cs.idsugerido = " + idCabSug + " and " +
                "cs.fechapedido is not null and " +
                "cs.importadores.idimportador is not null group by ds.cabecsugerido.idsugerido");
        ArrayList lista = (ArrayList) q.list();
        
        if ( lista.size() == 0 ) {
            fobTot = -1;
        } else {
            Object tot = lista.iterator().next();
            fobTot = Double.parseDouble(String.valueOf(tot));
        }
        return fobTot;
    }
    
    public Cabecsugerido getCabSugerido(int idCabSugerido){
        Cabecsugerido cs = new Cabecsugerido();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery(
                "from Cabecsugerido as cs where cs.idsugerido = " + idCabSugerido);
        ArrayList lista = (ArrayList) q.list();
        
        if ( lista.size() == 0 ) {
            cs = null;
        } else {
            cs = (Cabecsugerido) lista.iterator().next();
        }
        return cs;
    }
    
    public Cabecsugerido getCabSug(int idCabSugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cabecsugerido cs = (Cabecsugerido) session.createQuery("from "
                + "Cabecsugerido as cs where cs.idsugerido = " + idCabSugerido).iterate().next();
        return cs;
    }
    
    public ArrayList<Cabecsugerido> cargarCabSug() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido as cs where cs.fechapedido "
                + "is null and cs.fechaingresostock is null and cs.importadores.idimportador "
                + "is null and cs.fechaembarque is null");
//                + "order by cs.idsugerido desc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public ArrayList<Cabecsugerido> cargarOrdenesPedidos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido as cs where cs.fechapedido is not null "
                + "and cs.importadores.idimportador is not null "
                + "order by cs.idsugerido desc");
                //+ "cs.fechaingresostock is null ");
        ArrayList lista = (ArrayList) q.list();
        System.out.println("Num lista:"+lista.size());
        return lista;
    }
    
    public int contarOrdenesCompra() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select count(cs.idsugerido) from Cabecsugerido cs "
                + "where cs.fechapedido is not null and cs.importadores.idimportador is not null");///* and cs.fechaingresostock is null*/
        ArrayList lista = (ArrayList) q.list();
        int contador = Integer.parseInt(String.valueOf(lista.iterator().next()));
        return contador;
    }
    
    public ArrayList<Cabecsugerido> listarCabSugeridosParaActualizar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido cs "
                + "where cs.fechaingresostock is null and  cs.nrofacimportacion is null and cs.fechaingresostock is null "
                + "and cs.nroguia is null and cs.fechaembarque is null "
                
                + "and cs.estado is null " 
                
                + "order by cs.idsugerido desc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public ArrayList<Cabecsugerido> listarCabSugeridosOrdenesCompra() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido cs "
                + "where cs.fechaingresostock is null and  cs.nrofacimportacion is null and cs.fechaingresostock is null "
                + "and cs.nroguia is null and cs.fechaembarque is null "
                + "order by cs.idsugerido desc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public int contarSugeridos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select count(cs.idsugerido) from Cabecsugerido cs "
                + "where cs.fechapedido is null and cs.fechaingresostock is null and (cs.estado <> '*' "
                + "or cs.estado is null) and cs.importadores.idimportador is null and cs.fechaembarque "
                + "is null and cs.nrofacimportacion is null and cs.nroguia is null");
        ArrayList lista = (ArrayList) q.list();
        int contador = Integer.parseInt(String.valueOf(lista.iterator().next()));
        return contador;
    }
    
    public int contarSugeridosYOrdenesCompra(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select count(cs.idsugerido) from Cabecsugerido cs "
                + "where cs.fechaingresostock is null and cs.nrofacimportacion is null and cs.fechaingresostock is null "
                + "and cs.nroguia is null and cs.fechaembarque is null");
        ArrayList lista = (ArrayList) q.list();
        int contador = Integer.parseInt(String.valueOf(lista.iterator().next()));
        return contador;
    }
    
    public Date getFechaIngresoStock(int idCabSug) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select fechaingresostock from Cabecsugerido "
                                            + "cs where cs.idsugerido = "+ idCabSug);
        ArrayList lista = (ArrayList) q.list();
        Date fecha = new Date(String.valueOf(lista.iterator().next()));
        return fecha;
    }
    
    public String getNroFactImportacion(int idsugerido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select cs.nrofacimportacion from Cabecsugerido cs "
                + "where cs.nrofacimportacion is not null and cs.fechaingresostock is null and "
                + "cs.idsugerido = " + idsugerido);
        ArrayList lista = (ArrayList) q.list();
        
        if ( lista.size() == 0 ) {
            return null;
        } else {
            String nrofacturaImp = String.valueOf(lista.iterator().next());
            return nrofacturaImp;
        }
    }
    
    public String getNumImpresion(int idcabsug) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select cs.nroguia from Cabecsugerido cs "
                + "where cs.idsugerido = " + idcabsug);
        ArrayList lista = (ArrayList) q.list();
        
        if ( lista.size() == 0 ) {
            return null;
        } else {
            String nroguia = String.valueOf(lista.iterator().next());
            return nroguia;
        }
    }
    
    public Cabecsugerido esSugerido(int idCabSug){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido cs "
                + "where cs.fechapedido is null and cs.fechaingresostock is null and (cs.estado <> '*' "
                + "or cs.estado is null) and cs.importadores.idimportador is null and cs.fechaembarque "
                + "is null and cs.nrofacimportacion is null and cs.nroguia is null and cs.idsugerido = "
                + idCabSug);
        ArrayList<Cabecsugerido> lista = (ArrayList) q.list();
        Cabecsugerido cs = new Cabecsugerido();
        cs = null;
        
        if ( lista.size() != 0 ) {
            cs = lista.iterator().next();
        }
        return cs;
    }
    
    public Cabecsugerido esPedido(int idCabSug){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabecsugerido cs where cs.fechapedido is not null "
                + "and cs.fechaingresostock is null and cs.estado = '*' and cs.importadores.idimportador is not null "
                + "and cs.nrofacimportacion is null and cs.fechaingresostock is null "
                + "and cs.nroguia is null and cs.fechaembarque is null and cs.idsugerido = " + idCabSug);
        ArrayList<Cabecsugerido> lista = (ArrayList) q.list();
        Cabecsugerido cs = new Cabecsugerido();
        cs = null;
        if ( lista.size() != 0 ) {
            cs = lista.iterator().next();
        }        
        return cs;
    }
    
    public Cabecsugerido esOrdenCompra(int idCabSug){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Cabecsugerido cs = (Cabecsugerido) session.createQuery("select count(cs.idsugerido) from "
                + "Cabecsugerido cs where cs.fechapedido is not null and cs.fechaingresostock is null "
                + "and (cs.estado <> '*' or cs.estado is null) and cs.importadores.idimportador "
                + "is not null and cs.nrofacimportacion is null and cs.fechaingresostock is null "
                + "and cs.nroguia is null and cs.fechaembarque is null and cs.idsugerido = " + idCabSug)
                .iterate().next();
        return cs;
    }
    
    public boolean updateCabSug(int idCabSug,String numFacImp,String numGuia,Importadores imp,Date fechaEmbarque, double factorCosto){
      SessionFactory factory;
      boolean valor = false;
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
         
      } catch (Throwable ex) { 
         System.err.println("Fallo al crear el objeto sessionFactory en update de TramitesDAO" + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      Session session = factory.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         Cabecsugerido cs = (Cabecsugerido)session.get(Cabecsugerido.class, idCabSug); 
         cs.setNrofacimportacion(numFacImp);
         cs.setNroguia(numGuia);
         cs.setImportadores(imp);
         cs.setFechaembarque(fechaEmbarque);
         System.out.println("fc:" + factorCosto);
         cs.setFactorCosto(factorCosto);
         
         session.update(cs); 
         tx.commit();
         valor = true;
         
      } catch (HibernateException e) {
         if ( tx != null ) tx.rollback();
         e.printStackTrace(); 
         
      } finally {
         session.close(); 
      }
      return valor;
    }
}