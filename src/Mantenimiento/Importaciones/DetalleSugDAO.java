package Mantenimiento.Importaciones;
import Entidades.Detallesugerido;
import Entidades.Repuestos;
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
public class DetalleSugDAO extends GenericDAO<Detallesugerido>{
    public int nextId() {
        Detallesugerido ds=null;
        try {
            ds=(Detallesugerido) getHibernateTemplate().createQuery("from Detallesugerido ds order by ds.iddetallesugerido desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } 
        if(Tamaño_Lista()==0)
            return 1;
        else{
            return ds.getIddetallesugerido()+1;
        }
    }
    public double cantPendienteDetalleSugXRepuesto(Repuestos r){
        double cantPendiente=0.00;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detallesugerido as ds "
                + "where ds.repuestos.id.idrepuesto="+r.getId().getIdrepuesto()+" and "
                +" ds.repuestos.id.idlinea="+r.getId().getIdequipo()+" and "
                +" ds.cabecsugerido.estado like '*'"
        );
        ArrayList lista = (ArrayList) q.list();
        Iterator it = lista.iterator();
        while (it.hasNext()){
            Detallesugerido det=new Detallesugerido();
            det=(Detallesugerido)it.next();
            cantPendiente=cantPendiente+det.getCantpendiente();
        }
        return cantPendiente;
    }
    public List<Detallesugerido> getDetallesSug(int idCabSugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List listaDetSug = session.createQuery("from Detallesugerido as ds where "
        + "ds.cabecsugerido.idsugerido = "+idCabSugerido+" order by ds.repuestos.codrepuesto asc").list();
        return listaDetSug;
    }
    public Detallesugerido getIdDetalleSug(int idDetSugerido){
        Detallesugerido detSug=new Detallesugerido();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detallesugerido as ds where ds.iddetallesugerido="+ idDetSugerido);
        ArrayList lista = (ArrayList) q.list();
        if(lista.size()==0){
            detSug=null;
        }else{
            detSug=(Detallesugerido) lista.iterator().next();
        }
        return detSug;
    }
    public Detallesugerido getDetalleSug(int idsugerido, int idrepuesto, int idlinea){
        Detallesugerido detSug=new Detallesugerido();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detallesugerido as ds where ds.cabecsugerido.idsugerido="
        + idsugerido+" and ds.repuestos.id.idrepuesto="+idrepuesto+" and ds.repuestos.id.idlinea="+idlinea);
        ArrayList lista = (ArrayList) q.list();
        if(lista.size()==0){
            detSug=null;
        }else{
            detSug=(Detallesugerido) lista.iterator().next();
        }
        return detSug;
    }
    public List getDetallesDeCabSugerido(int idsugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList=null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //ACTUALIZA PARA LEER
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace(); 
        }
        joinList = session.createQuery("select ds.repuestos.id.idlinea,ds.repuestos.codrepuesto,"
                + "ds.repuestos.codigoseg,ds.repuestos.descripcion,ds.cantentregada,ds.repuestos.costopromedio,"
                + "ds.valorfob from Detallesugerido as ds "
                + "where ds.cabecsugerido.idsugerido="+idsugerido
                + " order by ds.repuestos.id.idlinea asc, ds.repuestos.codrepuesto asc").list();
        session.close(); 
        return joinList;
    }
    public List getDetallesDeCabSugeridoProforma(int idsugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList=null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //ACTUALIZA PARA LEER
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace(); 
        }
        joinList = session.createQuery("select lin.idlinea, r.codrepuesto,"
                + "r.codigoseg,r.descripcion,ds.cantentregada from Detallesugerido as ds "
                + "inner join ds.repuestos as r "
                + "inner join ds.cabecsugerido as cs "
                + "inner join ds.repuestos.lineas as lin "
                + "where r.id.idrepuesto=ds.repuestos.id.idrepuesto and "
                + "r.id.idlinea=ds.repuestos.id.idlinea and "
                + "cs.idsugerido=ds.cabecsugerido.idsugerido and "
                + "r.lineas.idlinea=ds.repuestos.id.idlinea and "
                + "ds.cabecsugerido.idsugerido="+idsugerido
                + " order by r.id.idlinea asc, r.codrepuesto asc"
                ).list();
        session.close(); 
        return joinList;
    }
    public List getDetallesDeCabPedido(int idsugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList=null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //ACTUALIZA PARA LEER
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace(); 
        }
        joinList = session.createQuery("select r.partidarancel,r.id.idlinea,r.codrepuesto,r.descripcion,"
                + "ds.cantentregada,ds.valorfob from Detallesugerido as ds "
                + "inner join ds.repuestos as r "
                + "inner join ds.cabecsugerido as cs "
                + "where r.id.idrepuesto=ds.repuestos.id.idrepuesto and "
                + "r.id.idlinea=ds.repuestos.id.idlinea and "
                + "cs.idsugerido=ds.cabecsugerido.idsugerido and "
                + "ds.cabecsugerido.idsugerido="+idsugerido
                + " order by r.partidarancel asc,r.codrepuesto asc"
                ).list();
        return joinList;
    }
    public List getDetalleSugXIdSug(int idsugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select ds.repuestos.id.idrepuesto, ds.cantpedida "
        + "from Detallesugerido as ds where ds.cabecsugerido.idsugerido="+idsugerido).list();
        return joinList;
    }
    public int getCantPedidaPendienteSugerido(int idsugerido) {    
        List returnList = getHibernateTemplate().createSQLQuery(
        "select ds.cantpedida from detallesugerido as ds inner join cabecsugerido as cs "+
        "on cs.idsugerido=ds.idsugerido where cs.fechapedido is null and cs.estado like '*' "+
        "and ds.idrepuesto="+idsugerido).list();
        session.getTransaction().commit();
        Iterator iteDetSug=returnList.iterator();
        int cantPdte=-1;
        if(iteDetSug.hasNext()){
            cantPdte=Integer.parseInt(String.valueOf(iteDetSug.next()));
        }
        return cantPdte;
    }
    public List getDetalleSugParaValorizar(int idsugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList=null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //ACTUALIZA PARA LEER
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace(); 
        }
        joinList = session.createQuery("select r.id.idrepuesto, r.codrepuesto,"
                + "ds.cantentregada, ds.valorfob,r.id.idlinea,ds.iddetallesugerido from Detallesugerido "
                + "as ds inner join ds.repuestos as r where "
                + "ds.repuestos.id.idrepuesto=r.id.idrepuesto "
                + "and ds.repuestos.id.idlinea=r.id.idlinea and idsugerido="
                +idsugerido+ " order by  r.codrepuesto asc,r.id.idlinea asc,r.descripcion asc").list();
        return joinList;
    }
    public List getATablaNotaIngreso(int idSugerido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.codrepuesto,lin.idlinea, r.descripcion, "
                + "r.costopromedio,ds.cantentregada,r.id.idrepuesto,ds.valorfob,r.id.idlinea from "
                + "Detallesugerido as ds inner join ds.repuestos as r "
                + "inner join ds.repuestos.lineas as lin "
                + "where r.id.idrepuesto=ds.repuestos.id.idrepuesto "
                + "and r.id.idlinea=ds.repuestos.id.idlinea "
                + "and r.lineas.idlinea=r.id.idlinea "
                + "and ds.cabecsugerido.idsugerido="+idSugerido
                + " order by r.codrepuesto asc,r.id.idlinea asc,r.descripcion asc").list();
        return joinList;
    }
    public List<Detallesugerido> getDetalleSugerido(int idSugerido){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("from Detallesugerido as ds where "
                + "ds.cabecsugerido.idsugerido="+idSugerido).list();
        return joinList;
    }
    public boolean updateDetSugerido(Detallesugerido detSugAnt){
        boolean valor=false;
        SessionFactory factory;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Fallo al crear el objeto sessionFactory en update de DetalleSugDAO" + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            int iddetallesugerido=detSugAnt.getIddetallesugerido();
            Detallesugerido detSugNew = (Detallesugerido)session.get(Detallesugerido.class, iddetallesugerido);
            detSugNew=detSugAnt;
            detSugNew.setCantpedida(detSugAnt.getCantpedida());
            detSugNew.setCantentregada(detSugAnt.getCantentregada());
            detSugNew.setCantpendiente(detSugAnt.getCantpendiente());
            detSugNew.setValorfob(detSugAnt.getValorfob());
            mostrar(detSugNew);
            session.merge(detSugNew);
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
    
    public boolean updateListaDetSugerido(List<Detallesugerido> lstDetSug) {
        boolean valor = false;
        SessionFactory factory;
        
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch ( Throwable ex ) { 
            System.err.println("Fallo al crear el objeto sessionFactory en update de DetalleSugDAO" + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            int i = 0;
            
            for ( Detallesugerido ds : lstDetSug ) {
                int iddetallesugerido = ds.getIddetallesugerido();
                Detallesugerido detSugNew = (Detallesugerido)session.get(Detallesugerido.class, iddetallesugerido);
                detSugNew = ds;
                detSugNew.setCantpedida(ds.getCantpedida());
                detSugNew.setCantentregada(ds.getCantentregada());
                detSugNew.setCantpendiente(ds.getCantpendiente());
                detSugNew.setValorfob(ds.getValorfob());
//                mostrar(detSugNew);
                session.merge(detSugNew);
//                System.out.println("i:" + (i+1));
//                i++;
            }
            tx.commit();
            valor = true;
            System.out.println("updateListaDetSugerido OK");
            
        } catch ( HibernateException e ) {
            if ( tx != null ) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return valor;
    }
    
    public void mostrar(Detallesugerido ds){
        System.out.println("IdDetSug-IdRep-IdLin-IdSug-CantPed-CantEnt-FOB-StockSug-CantPdte-Demanda");
        System.out.println(ds.getIddetallesugerido()+"-"+ds.getRepuestos().getId()
        .getIdrepuesto()+"-"+ds.getRepuestos().getId().getIdequipo()+"-"+ds.getCabecsugerido()
        .getIdsugerido()+"-"+ds.getCantpedida()+"-"+ds.getCantentregada()+"-"+
        ds.getValorfob()+"-"+ds.getStocksugerido()+"-"+ds.getCantpendiente()+"-"+
        ds.getDemandaprom());
    }
    public boolean deleteDetSug(int idDetalleSugerido){
        boolean val=false;
        SessionFactory factory;
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Fallo al crear el objeto sessionFactory en delete de detSugeridoDAO" + ex);
            throw new ExceptionInInitializerError(ex); 
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Detallesugerido detsug = (Detallesugerido)session.get(Detallesugerido.class, idDetalleSugerido); 
            session.delete(detsug); 
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
}