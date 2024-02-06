package Mantenimiento;

import Entidades.Accesos;
import Entidades.Programas;
import Entidades.AccesosId;
import Entidades.Roles;
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
public class AccesosDAO extends GenericDAO<Accesos>{
    
    //public boolean GuardarAccesos(List programas, Roles rol){
    public boolean GuardarAccesos(List<Programas> programasEliminados, List<Programas> programasNuevos, Roles rol){
        //int nro = programas.size();
        //System.out.println("nro:" + nro);
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try{
            // Eliminando acceso a programas
            for(Programas p : programasEliminados){
                Accesos acceso = new Accesos();
                AccesosId ai = new AccesosId(rol.getIdrol(), p.getIdprograma());
                System.out.println("rol:" + rol.getNombre() + " - " + rol.getIdrol());
                System.out.println("programa:" + p.getDescripcion() + " - " + p.getIdprograma());
                acceso.setId(ai);
                ses.delete(acceso);
                System.out.println("eliminado acceso...");
            }
            
            // Agregando nuevos accesos
            for(Programas programa : programasNuevos){
                Accesos acceso = new Accesos();
                AccesosId ai = new AccesosId(rol.getIdrol(), programa.getIdprograma());
                acceso.setId(ai);
                ses.save(acceso);
            }
                
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Error:" + e.getMessage());
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            ses.close();
        }
        return true;
    }
    /*
    public boolean GuardarAccesos(List l_Roles){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try{
            for(Object r : l_Roles){
                Roles rol = (Roles)ses.merge(r);
                ses.update(rol);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            ses.close();
        }
        return true;
    }*/
    
    public List<Accesos> listarAccesos(int idRol) {
        List<Accesos> accesos = new ArrayList<Accesos>();
        try {
            accesos = getHibernateTemplate().createQuery("from Accesos a where a.roles.idrol = " + idRol).list();
            
        } catch ( Exception e ) {
            System.out.println("Excepcion:" + e.getMessage());
            accesos = null;
        }
        return accesos;
    }
}
