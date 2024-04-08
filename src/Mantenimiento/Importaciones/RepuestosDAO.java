package Mantenimiento.Importaciones;

import Entidades.Otros.RepuestoDemanda;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Servicios.HibernateUtil;
import Servicios.Importaciones.Servicio_Repuestos;
import static java.lang.Integer.parseInt;
import static java.lang.System.console;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.table.TableModel;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class RepuestosDAO extends GenericDAO<Repuestos> {
    private Session sesion;
    private Transaction tx;
    private int k2;
    
    public int nextId() {
        
        Repuestos r=null;
        try {
            r=(Repuestos) getHibernateTemplate().createQuery("from Repuestos r order by r.id desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } 
        if(Tamaño_Lista()==0)
            return 1;
        else{
            return r.getId().getIdrepuesto()+1;
        }
    }
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    public Repuestos obtenerRepuesto(int id) {
        Repuestos repuesto = new Repuestos();
        RepuestosId repuestosId = new RepuestosId(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            iniciaOperacion();
            repuesto = (Repuestos) sesion.createQuery("from Repuestos where idrepuesto=" + repuestosId.getIdrepuesto()).uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

        return repuesto;
    }
    public Repuestos busca1Repuesto(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Repuestos rep = (Repuestos) session.createQuery(
                "from Repuestos as r where r.codrepuesto like '"+name+"'").iterate().next();
        return rep;
    }
    public ArrayList<Repuestos> buscarXNombre(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
        + " where r.codrepuesto like '"+name+"%' order by r.codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXNombre(Date fechaInicio, Date fechaFinal, String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        /*
        String query = "select rep.idrepuesto, "
                        + " rep.codrepuesto, "
                        + " rep.codigoseg, "
                        + " rep.descripcion, "
                        + " rep.aplicacion, "
                        + " rep.stock, "
                        + " rep.preciolista, "
                        + " rep.marca, "

                        + " month(ces.fecha), "
                        + " year(ces.fecha), "
                        + " sum(des.cantpedida) "
                        
                        + " from Detallees as des"
        
                        + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                        + " inner join cabeces as ces "
                                + " on ces.tipotra = des.tipotra "
                                + " and ces.tipodoc = des.tipodoc "
                                + " and ces.nrorserie = des.nrorserie "
                                + " and ces.nrodocumento = des.nrodocumento "
                        + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                        + " where  "
                                + " ces.fecha >='" + fechaInicio + "' "
                                + " and ces.fecha <= '" + fechaFinal + "' "
                                + " and op.codigooperacion like 'S%' "
                                + " and rep.codrepuesto like '" + name + "%' "
                        + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "
                        + " order by rep.codrepuesto asc, ces.fecha "; */
        String query = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(ces.fecha) as mes, "
                    + " year(ces.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                    + " inner join cabeces as ces "
                    + " on ces.tipotra = des.tipotra "
                    + " and ces.tipodoc = des.tipodoc "
                    + " and ces.nrorserie = des.nrorserie "
                    + " and ces.nrodocumento = des.nrodocumento "
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                
                    + " where "
                    + " ces.fecha >= '" + fechaInicio + "' "
                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " and op.codigooperacion like 'S%' "
                    + " and rep.codrepuesto like '" + name + "%' "
                    + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                
                    + " where des.idrepuesto is null "
                    + " and rep2.codrepuesto like '" + name + "%' "
                    
                    + " order by idrepuesto asc, mes asc, anio asc";

        //System.out.println("query(buscarDemandaAnualRepuestosXNombre):" + query);
        
        List lst = session.createSQLQuery(query).list();
        Iterator ite = lst.iterator();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock        = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca        = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rd = new RepuestoDemanda();
            rd.setIdRepuesto(idRepuesto);
            rd.setNumeroRepuesto(numRepuesto);
            rd.setCodSecundario(codSecun);
            rd.setDescripcion(descripcion);
            rd.setDescrmodelo(descrmodelo);
            rd.setStock(stock);
            rd.setFobultimo(fobultimo);
            rd.setMarca(marca);
            rd.setMes(mes);
            rd.setAño(año);
            rd.setDemanda(demanda);
            
            lstRepDem.add(rd);
        }
        return lstRepDem;
        //return getHibernateTemplate().createSQLQuery(query).list();

//        System.out.println("Entro a devoluciones....");        
    
    }
    
    public ArrayList<Repuestos> getRepuestos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    public ArrayList<Repuestos> getRepuestosAscNumParte() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos order by codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
    public List<RepuestoDemanda> getDemandaAnualRepuestosAscNumParte(Date fechaInicio, Date fechaFinal) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        /*String query = "select rep.idrepuesto, "
                        + " rep.codrepuesto, "
                        + " rep.codigoseg, "
                        + " rep.descripcion, "
                        + " rep.aplicacion, "
                        + " rep.stock, "
                        + " rep.preciolista, "
                        + " rep.marca, "

                        + " month(ces.fecha), "
                        + " year(ces.fecha), "
                        + " sum(des.cantpedida) "
                        
                        + " from Detallees as des"
        
                        + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                        + " inner join cabeces as ces "
                                + " on ces.tipotra = des.tipotra "
                                + " and ces.tipodoc = des.tipodoc "
                                + " and ces.nrorserie = des.nrorserie "
                                + " and ces.nrodocumento = des.nrodocumento "
                        + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                        + " where  "
                                + " ces.fecha >='" + fechaInicio + "' "
                                + " and ces.fecha <= '" + fechaFinal + "' "
                                + " and op.codigooperacion like 'S%' "
                        + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "
                        + " order by rep.codrepuesto asc, ces.fecha ";*/
        String query = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(ces.fecha) as mes, "
                    + " year(ces.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                    + " inner join cabeces as ces "
                    + " on ces.tipotra = des.tipotra "
                    + " and ces.tipodoc = des.tipodoc "
                    + " and ces.nrorserie = des.nrorserie "
                    + " and ces.nrodocumento = des.nrodocumento "
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                    + " where "
                    + " ces.fecha >= '" + fechaInicio + "' "
                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " and op.codigooperacion like 'S%' "
                    + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                
                    + " where des.idrepuesto is null "
                
//                    + " order by codrepuesto asc, mes asc, anio asc";        
                    + " order by idrepuesto asc, mes asc, anio asc";                

        // System.out.println("query(getDemandaAnualRepuestosAscNumParte):" + query);
        List lst = session.createSQLQuery(query).list();
        
        
/// Query de Devoluciones (JSP)
        String queryDev = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(des.fecha) as mes, "
                    + " year(des.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
//                    + " inner join cabeces as ces "
//                    + " on ces.tipotra = des.tipotra "
//                    + " and ces.tipodoc = des.tipodoc "
//                    + " and ces.nrorserie = des.nrorserie "
//                    + " and ces.nrodocumento = des.nrodocumento "
                
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                    + " where "
//                    + " ces.fecha >= '" + fechaInicio + "' "
//                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " des.fecha >= '" + fechaInicio + "' "
                    + " and des.fecha <= '" + fechaFinal + "' "

                + " and des.idoperacion like '9%' "                                
//                    + " and op.codigooperacion like 'D%' "                
//                    + " and des.motivo like '(DEV)%' "                                
                
                    + " group by rep.idrepuesto, month(des.fecha), year(des.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                    + " where des.idrepuesto is null "
                    + " and des.idoperacion like '9%' "                
//                    + " and op.codigooperacion like 'DV%' "                                
                
                    + " order by idrepuesto asc, mes asc, anio asc";                
        
        List lst2Devoluciones = session.createSQLQuery(queryDev).list();
        
        Iterator ite = lst.iterator();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock_       = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca        = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rd = new RepuestoDemanda();
            rd.setIdRepuesto(idRepuesto);
            rd.setNumeroRepuesto(numRepuesto);
            rd.setCodSecundario(codSecun);
            rd.setDescripcion(descripcion);
            rd.setDescrmodelo(descrmodelo);
            rd.setStock(stock_);
            rd.setFobultimo(fobultimo);
            rd.setMarca(marca);
            rd.setMes(mes);
            rd.setAño(año);
            rd.setDemanda(demanda);
            
            lstRepDem.add(rd);
        }
        
        Iterator iteDev = lst2Devoluciones.iterator();
        List<RepuestoDemanda> lstRepDev = new ArrayList<RepuestoDemanda>();
        
         while ( iteDev.hasNext() ) {
            Object[] obj = (Object[]) iteDev.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock_       = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca        = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rdDev = new RepuestoDemanda();
            rdDev.setIdRepuesto(idRepuesto);
            rdDev.setNumeroRepuesto(numRepuesto);
            rdDev.setCodSecundario(codSecun);
            rdDev.setDescripcion(descripcion);
            rdDev.setDescrmodelo(descrmodelo);
            rdDev.setStock(stock_);
            rdDev.setFobultimo(fobultimo);
            rdDev.setMarca(marca);
            rdDev.setMes(mes);
            rdDev.setAño(año);
            rdDev.setDemanda(demanda);
            
            lstRepDev.add(rdDev);
        }
        
// Verificando tamaño de las 2 query
         System.out.println("Comparo tamaño de listas " + lstRepDem.size() + " vs " + lstRepDev.size());
//         if( lstRepDem.size() == lstRepDev.size() ) {
            
          String bandera = "";
          for(int i=0; i < lstRepDev.size(); i++){
              
              bandera = "";
              for( int j=0; j < lstRepDem.size(); j++){
                  
                 if( lstRepDev.get(i).getNumeroRepuesto().equals(lstRepDem.get(j).getNumeroRepuesto())
                     && (lstRepDev.get(i).getMes().equals(lstRepDem.get(j).getMes())    
                     && (lstRepDev.get(i).getAño().equals(lstRepDem.get(j).getAño())                             
                         ) ) ){
                     
          //Acumula Devoluciones
                     bandera = "1";  // Lo encontró
                     System.out.println("Contenido de DEM repuesto : " + lstRepDem.get(j).getNumeroRepuesto());                           
                     System.out.println("Contenido de DEM Cantidad : " + lstRepDem.get(j).getDemanda());                                                
                     System.out.println("Contenido de DEM mes : " + lstRepDem.get(j).getMes());                           
                     System.out.println("Contenido de DEM año : " + lstRepDem.get(j).getAño());                           
                     
                     String a = lstRepDev.get(i).getDemanda();
                     int a1 = parseInt(a);
                     System.out.println("Integer value is Devolucion " + a1);
                           
                     String b = lstRepDem.get(j).getDemanda();
                     int b1 = parseInt(b);
                     System.out.println("Integer value is Demanda " + b1);

                     int d2 = (b1 - a1);
                     String demandatxt = String.valueOf(d2);
//                     String demandatxt = toString(d2);            
                     lstRepDem.get(j).setDemanda(demandatxt);                                              
                     RepuestoDemanda rd = new RepuestoDemanda();    
                     rd.setDemanda(demandatxt);                     
                     
                      }
                 
                 }                 
                 // bandera equals "" , no lo encontro y lo adiciona
                 if ( "".equals(bandera) ){
                        String idRepuesto   = lstRepDev.get(i).getIdRepuesto();
                        String numRepuesto  = lstRepDev.get(i).getNumeroRepuesto();
                        String codSecun     = lstRepDev.get(i).getCodSecundario();
                        String descripcion  = lstRepDev.get(i).getDescripcion();
                        String descrmodelo  = lstRepDev.get(i).getDescrmodelo();
                        String stock_       = lstRepDev.get(i).getStock();
                        String fobultimo    = lstRepDev.get(i).getFobultimo();
                        String marca        = lstRepDev.get(i).getMarca();
                        String mes          = lstRepDev.get(i).getMes();
                        String año          = lstRepDev.get(i).getAño();
                        
//                     String demandatxt = String.valueOf(d2);
                        String demanda      = lstRepDev.get(i).getDemanda();
                        int d2 = parseInt(demanda);
                        d2 = (0 - d2);
                        demanda      = String.valueOf(d2);
                        
                        RepuestoDemanda rdDem = new RepuestoDemanda();
                        rdDem.setIdRepuesto(idRepuesto);
                        rdDem.setNumeroRepuesto(numRepuesto);
                        rdDem.setCodSecundario(codSecun);
                        rdDem.setDescripcion(descripcion);
                        rdDem.setDescrmodelo(descrmodelo);
                        rdDem.setStock(stock_);
                        rdDem.setFobultimo(fobultimo);
                        rdDem.setMarca(marca);
                        rdDem.setMes(mes);
                        rdDem.setAño(año);
                        rdDem.setDemanda(demanda);
            
                        lstRepDem.add(rdDem);
                        
                        System.out.println("Adicionando a DEM");
                        System.out.println("Contenido de DEM repuesto : " + numRepuesto );                           
                        System.out.println("Contenido de DEM Cantidad : " + demanda);                                                
                        System.out.println("Contenido de DEM mes : " + mes );                           
                        System.out.println("Contenido de DEM año : " + año );                           

                 }
              
        }
 //       }
//        }
        return lstRepDem; 
}
    private String nullToString(Object obj) {
        return ( obj == null ) ? "" : String.valueOf(obj);
    }    


    public ArrayList<Repuestos> buscarXCodSecundario(String codSec) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
        + " where r.codigoseg like '"+codSec+"%' order by r.codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    public ArrayList<Repuestos> buscarXDescripcion(String desc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
        + " where r.descripcion like '"+desc+"%' order by r.codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXDescripcion(Date fechaInicio, Date fechaFinal, String desc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        /*
        String query = "select rep.idrepuesto, "
                        + " rep.codrepuesto, "
                        + " rep.codigoseg, "
                        + " rep.descripcion, "
                        + " rep.aplicacion, "
                        + " rep.stock, "
                        + " rep.preciolista, "
                        + " rep.marca, "

                        + " month(ces.fecha), "
                        + " year(ces.fecha), "
                        + " sum(des.cantpedida) "
                        
                        + " from Detallees as des"
        
                        + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                        + " inner join cabeces as ces "
                                + " on ces.tipotra = des.tipotra "
                                + " and ces.tipodoc = des.tipodoc "
                                + " and ces.nrorserie = des.nrorserie "
                                + " and ces.nrodocumento = des.nrodocumento "
                        + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                        + " where  "
                                + " ces.fecha >='" + fechaInicio + "' "
                                + " and ces.fecha <= '" + fechaFinal + "' "
                                + " and op.codigooperacion like 'S%' "
                                + " and r.descripcion like '" + desc + "%' "
                        + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "
                        + " order by rep.codrepuesto asc, ces.fecha ";*/

        String query = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(ces.fecha) as mes, "
                    + " year(ces.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                    + " inner join cabeces as ces "
                    + " on ces.tipotra = des.tipotra "
                    + " and ces.tipodoc = des.tipodoc "
                    + " and ces.nrorserie = des.nrorserie "
                    + " and ces.nrodocumento = des.nrodocumento "
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                
                    + " where "
                    + " ces.fecha >= '" + fechaInicio + "' "
                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " and op.codigooperacion like 'S%' "
                    + " and rep.descripcion like '" + desc + "%' "
                    + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                
                    + " where des.idrepuesto is null "
                    + " and rep2.descripcion like '" + desc + "%' "
                
                    + " order by idrepuesto asc, mes asc, anio asc";
        
        //System.out.println("query(buscarDemandaAnualRepuestosXDescripcion):" + query);
        
        List lst = session.createSQLQuery(query).list();
        Iterator ite = lst.iterator();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock        = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca        = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rd = new RepuestoDemanda();
            rd.setIdRepuesto(idRepuesto);
            rd.setNumeroRepuesto(numRepuesto);
            rd.setCodSecundario(codSecun);
            rd.setDescripcion(descripcion);
            rd.setDescrmodelo(descrmodelo);
            rd.setStock(stock);
            rd.setFobultimo(fobultimo);
            rd.setMarca(marca);
            rd.setMes(mes);
            rd.setAño(año);
            rd.setDemanda(demanda);
            
            lstRepDem.add(rd);
        }
        return lstRepDem;
    }
    
    public ArrayList<Repuestos> buscarXMarca(String marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
        + " where r.marca like '"+marca+"' order by r.id.idequipo asc, r.id.idrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXMarca(Date fechaInicio, Date fechaFinal, String marca) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        /*
        String query = "select rep.idrepuesto, "
                        + " rep.codrepuesto, "
                        + " rep.codigoseg, "
                        + " rep.descripcion, "
                        + " rep.aplicacion, "
                        + " rep.stock, "
                        + " rep.preciolista, "
                        + " rep.marca, "

                        + " month(ces.fecha), "
                        + " year(ces.fecha), "
                        + " sum(des.cantpedida) "
                        
                        + " from Detallees as des"
        
                        + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                        + " inner join cabeces as ces "
                                + " on ces.tipotra = des.tipotra "
                                + " and ces.tipodoc = des.tipodoc "
                                + " and ces.nrorserie = des.nrorserie "
                                + " and ces.nrodocumento = des.nrodocumento "
                        + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                        + " where  "
                                + " ces.fecha >='" + fechaInicio + "' "
                                + " and ces.fecha <= '" + fechaFinal + "' "
                                + " and op.codigooperacion like 'S%' "
                                + " and r.marca like '" + marca + "' "
                        + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "
                        + " order by rep.codrepuesto asc, ces.fecha ";*/
        
        String query = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(ces.fecha) as mes, "
                    + " year(ces.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                    + " inner join cabeces as ces "
                    + " on ces.tipotra = des.tipotra "
                    + " and ces.tipodoc = des.tipodoc "
                    + " and ces.nrorserie = des.nrorserie "
                    + " and ces.nrodocumento = des.nrodocumento "
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                
                    + " where "
                    + " ces.fecha >= '" + fechaInicio + "' "
                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " and op.codigooperacion like 'S%' "
                    + " and rep.marca like '" + marca + "' "
                    + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                
                    + " where des.idrepuesto is null "
                    + " and rep2.marca like '" + marca + "' "
                    
                    + " order by idrepuesto asc, mes asc, anio asc";

        //System.out.println("query(buscarDemandaAnualRepuestosXMarca):" + query);
        
        List lst = session.createSQLQuery(query).list();
        Iterator ite = lst.iterator();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock        = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca_       = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rd = new RepuestoDemanda();
            rd.setIdRepuesto(idRepuesto);
            rd.setNumeroRepuesto(numRepuesto);
            rd.setCodSecundario(codSecun);
            rd.setDescripcion(descripcion);
            rd.setDescrmodelo(descrmodelo);
            rd.setStock(stock);
            rd.setFobultimo(fobultimo);
            rd.setMarca(marca_);
            rd.setMes(mes);
            rd.setAño(año);
            rd.setDemanda(demanda);
            
            lstRepDem.add(rd);
        }
        return lstRepDem;
    }
    
    public ArrayList<Repuestos> buscarXStock(int stock) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
        + " where r.stock="+stock+" order by r.codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXStock(Date fechaInicio, Date fechaFinal, String stock) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        /*
        String query = "select rep.idrepuesto, "
                        + " rep.codrepuesto, "
                        + " rep.codigoseg, "
                        + " rep.descripcion, "
                        + " rep.aplicacion, "
                        + " rep.stock, "
                        + " rep.preciolista, "
                        + " rep.marca, "

                        + " month(ces.fecha), "
                        + " year(ces.fecha), "
                        + " sum(des.cantpedida) "
                        
                        + " from Detallees as des"
        
                        + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                        + " inner join cabeces as ces "
                                + " on ces.tipotra = des.tipotra "
                                + " and ces.tipodoc = des.tipodoc "
                                + " and ces.nrorserie = des.nrorserie "
                                + " and ces.nrodocumento = des.nrodocumento "
                        + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                        + " where  "
                                + " ces.fecha >='" + fechaInicio + "' "
                                + " and ces.fecha <= '" + fechaFinal + "' "
                                + " and op.codigooperacion like 'S%' "
                                + " and r.stock = " + stock
                        + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "
                        + " order by rep.codrepuesto asc, ces.fecha ";*/
        
        String query = " select rep.idrepuesto as idrepuesto, "
                    + " rep.codrepuesto, "
                    + " rep.codigoseg, "
                    + " rep.descripcion, "
                    + " rep.descrmodelo, "
                    + " rep.stock, "
                    + " rep.fobultimo, "
                    + " rep.marca, "

                    + " month(ces.fecha) as mes, "
                    + " year(ces.fecha) as anio, "
                    + " sum(des.cantpedida) "
                    + " from Detallees as des "
                
                    + " inner join Repuestos rep on rep.idrepuesto = des.idrepuesto "
                    + " inner join cabeces as ces "
                    + " on ces.tipotra = des.tipotra "
                    + " and ces.tipodoc = des.tipodoc "
                    + " and ces.nrorserie = des.nrorserie "
                    + " and ces.nrodocumento = des.nrodocumento "
                    + " inner join Operaciones as op on op.idoperacion = des.idoperacion "
                
                    + " where "
                    + " ces.fecha >= '" + fechaInicio + "' "
                    + " and ces.fecha <= '" + fechaFinal + "' "
                    + " and op.codigooperacion like 'S%' "
                    + " and rep.stock = " + stock
                    + " group by rep.idrepuesto, month(ces.fecha), year(ces.fecha) "

                    + " union "

                    + " select distinct rep2.idrepuesto as idrepuesto, "
                    + " rep2.codrepuesto, "
                    + " rep2.codigoseg, "
                    + " rep2.descripcion, "
                    + " rep2.descrmodelo, "
                    + " rep2.stock, "
                    + " rep2.fobultimo, "
                    + " rep2.marca, "
                    + " month(DATE_SUB(sysdate(), INTERVAL 1 month)) as mes, "
                    + " year(DATE_SUB(sysdate(), INTERVAL 1 month)) as anio, "
                    + " 0 "
                
                    + " from Repuestos as rep2 "
                    + " left join Detallees des on des.idrepuesto = rep2.idrepuesto "
                
                    + " where des.idrepuesto is null "
                    + " and rep2.stock = " + stock
                
                    + " order by idrepuesto asc, mes asc, anio asc";

        //System.out.println("query(buscarDemandaAnualRepuestosXStock):" + query);
        
        List lst = session.createSQLQuery(query).list();
        Iterator ite = lst.iterator();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String idRepuesto   = String.valueOf(obj[0]);
            String numRepuesto  = String.valueOf(obj[1]);
            String codSecun     = nullToString(obj[2]);
            String descripcion  = String.valueOf(obj[3]);
            String descrmodelo  = nullToString(obj[4]);
            String stock_       = String.valueOf(obj[5]);
            String fobultimo    = String.valueOf(obj[6]);
            String marca        = String.valueOf(obj[7]);
            String mes          = String.valueOf(obj[8]);
            String año          = String.valueOf(obj[9]);
            String demanda      = String.valueOf(obj[10]);
            
            RepuestoDemanda rd = new RepuestoDemanda();
            rd.setIdRepuesto(idRepuesto);
            rd.setNumeroRepuesto(numRepuesto);
            rd.setCodSecundario(codSecun);
            rd.setDescripcion(descripcion);
            rd.setDescrmodelo(descrmodelo);
            rd.setStock(stock_);
            rd.setFobultimo(fobultimo);
            rd.setMarca(marca);
            rd.setMes(mes);
            rd.setAño(año);
            rd.setDemanda(demanda);
            
            lstRepDem.add(rd);
        }
        return lstRepDem;
    }
            
    public List getRepuestosASugerido(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.codrepuesto, r.codigoseg, "
                + "r.descripcion, r.descrmodelo, r.stock, r.fobultimo,r.id.idrepuesto,r.id.idequipo "
                + "from Repuestos as r order by r.codrepuesto asc, r.descripcion asc").list();
        return joinList;
    } 
    public List getRepuestosAImpSugerido() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idlinea,r.codrepuesto,r.codigoseg, "
                + "r.descripcion,r.costopromedio,r.fobultimo from Repuestos as r order by r.codrepuesto asc").list();
        return joinList;
    }
    public List getRepuestosXNameIncompleto(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idrepuesto,r.id.idlinea from "
                + "Repuestos as r where r.codrepuesto like '"+name+"%' "
                + "order by r.codrepuesto asc").list();
        return joinList;
    }
    public List getRepuestosXCodSecIncompleto(String codSec) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idrepuesto,r.id.idlinea from Repuestos as r"
        + " where r.codigoseg like '"+codSec+"%' order by r.codrepuesto asc").list();
        return joinList;
    }
    public List getRepuestosXDescripcionIncompleto(String descripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idrepuesto,r.id.idlinea from Repuestos as r"
        + " where r.descripcion like '"+descripcion+"%' order by r.descripcion asc").list();
        return joinList;
    }
    public List getRepuestosXLinea(String linea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("from Repuestos as r"
        + " where r.id.idequipo like '"+linea+"' order by r.descripcion asc").list();
        return joinList;
    }
    public List repuestosXLinea(int idlinea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("from Repuestos as r"
        + " where r.id.idequipo like '"+idlinea+"' order by r.id.idequipo, r.id.idrepuesto asc").list();
        return joinList;
    }
    public List getRepuestosXNumSecundario(String numSecundario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idrepuesto,r.id.idequipo from "
                + "Repuestos as r where r.codigoseg like '"+numSecundario+"%' "
                + "order by r.codrepuesto asc").list();
        return joinList;
    }
    public List getRepuestosXDescricion(String descripcion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.id.idrepuesto,r.id.idequipo from "
                + "Repuestos as r where r.descripcion like '"+descripcion+"%' "
                + "order by r.codrepuesto asc").list();
        return joinList;
    }
    public Repuestos getRepuesto(int idlinea, int idrepuesto) {
        Repuestos rep=new Repuestos();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos where idrepuesto="+idrepuesto+ " and idlinea="+ idlinea);
        ArrayList lista = (ArrayList) q.list();
        if(lista.size()==0){
            rep=null;
        }else{
            rep=(Repuestos) lista.iterator().next();
        }
        return rep;
    }
    public boolean actualizarPrecios(TableModel tabla,double factor,boolean sinDec){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        Repuestos rAct=new Repuestos();
        Servicio_Repuestos sr=new Servicio_Repuestos();
        try {
            for(int i=0;i<tabla.getRowCount();i++){
                String precioLista=String.valueOf(tabla.getValueAt(i,3));
                tabla.setValueAt(precioLista,i,4);
                double precio=Double.parseDouble(precioLista);
                precio=precio*factor;
                if(sinDec){
                    precio=Math.round(precio);
                    precioLista=String.valueOf(precio);
                    precioLista+="0";
                    tabla.setValueAt(precioLista,i,3);
                }else{
                    precio=(double)Math.round(precio * 100) / 100;
                    precioLista=String.valueOf(precio);
                    tabla.setValueAt(precioLista,i,3);
                    formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(precioLista)),i,3,tabla);
                }
                rAct = sr.getRepuesto(Integer.parseInt(String.valueOf(tabla.getValueAt(i,1))),
                                      Integer.parseInt(String.valueOf(tabla.getValueAt(i,0))));
                rAct.setPreciolista(precio);
                Repuestos rt = (Repuestos) sesion.merge(rAct);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }
        }catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    public boolean restaurarPrecios(TableModel tabla){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        Repuestos rAct=new Repuestos();
        Servicio_Repuestos sr=new Servicio_Repuestos();
        try {
            for(int i=0;i<tabla.getRowCount();i++){
                String precioLista=String.valueOf(tabla.getValueAt(i,4));
                tabla.setValueAt(precioLista,i,3);
                tabla.setValueAt(0,i,4);
                formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(precioLista)),i,3,tabla);
                rAct = sr.getRepuesto(Integer.parseInt(String.valueOf(tabla.getValueAt(i,1))),
                                      Integer.parseInt(String.valueOf(tabla.getValueAt(i,0))));
                rAct.setPreciolista(Double.parseDouble(String.valueOf(tabla.getValueAt(i,3))));
                Repuestos rt = (Repuestos) sesion.merge(rAct);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }
        }catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    private void formatearColumnaDerechaSugerido(double valor, int fila, int columna,TableModel tabla){
        String price="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        price=formateador.format(valor);
        int indice=price.indexOf(".");
        if(indice==-1){
            price=price+".00";
        }else if(price.length()-indice==2){
            price=price+"0";
        }
        tabla.setValueAt(price,fila,columna);
    }
    public List obtenerMarcas(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select r.marca from Repuestos as r group by marca order by marca asc").list();
        return joinList;
    }

    private static class console {

        private static int log(int parseInt) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public console() {
        }
    }

   
}