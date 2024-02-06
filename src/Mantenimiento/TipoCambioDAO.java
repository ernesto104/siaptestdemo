/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Entidades.Equipos;
import Entidades.Tipocambio;
import Servicios.HibernateUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fabrica2
 */
public class TipoCambioDAO extends GenericDAO<Tipocambio> {

     private Session sesion;
    private Transaction tx;
    
    
    public Tipocambio Obtener_Objeto(Date fecha){
        Tipocambio returnValue = (Tipocambio) getHibernateTemplate().load(domainClass, fecha);
        return returnValue;
    }
    
    public Tipocambio getTipoCambio(Date fecha) {
//        System.out.println("fechaHoy:" + fechaHoy);        
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fec = formateador.format(fecha);
//        System.out.println("fec:" + fec);
        
        Tipocambio t = null;
        try {
            t = (Tipocambio) getHibernateTemplate().createQuery("from Tipocambio t where t.fecha like '" + fec + "'").iterate().next();
        } catch( Exception e ) {
            System.out.println("Excepcion(getTipoCambio):" + e.getMessage());
        }
        return t;
    }
    
    public Tipocambio getTipoCambioHoy() {
        Date dateHoy = new Date();
//        System.out.println("fechaHoy:" + fechaHoy);        
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fechaHoy = formateador.format(dateHoy);
        System.out.println("fechaHoy:" + fechaHoy);
        
        Tipocambio t = null;
        try {
            t = (Tipocambio) getHibernateTemplate().createQuery("from Tipocambio t where t.fecha like '" + fechaHoy + "'").iterate().next();
        } catch(Exception e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
        return t;
    }

    public Date nextId() {
        //Devuelte NULL si la fecha pedida es igual a la fecha actual
        Tipocambio t = null;
        try {
            t = (Tipocambio) getHibernateTemplate().createQuery("from Tipocambio t order by t.fecha desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return new Date();
        } else {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date());
            if (!comparaFechas(t.getFecha(), new Date())) {
                return new Date();
            } else {
                return null;
            }
        }
    }

    private boolean comparaFechas(Date f1, Date f2) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(f1).equals(df.format(f2));
    }
    
    public ArrayList<Tipocambio> listar_Cambio() {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from TipoCambio");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
    public int listacambiotamaño(){
        int tamaño = listar_Cambio().size();
        return tamaño;
    }
    
    public ArrayList<Tipocambio> devolverfecha(String fecha) {         
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from TipoCambio where fecha='"+fecha+"'").uniqueResult();
        ArrayList lista = (ArrayList) q.list();         
        return lista;    
    }
    
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public List<Tipocambio> Obtener_Lista_Objetos_OrderNombre() {
        List<Tipocambio> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Tipocambio order by fecha DESC").list();
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