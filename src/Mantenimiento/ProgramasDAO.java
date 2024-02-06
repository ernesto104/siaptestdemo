package Mantenimiento;

import Entidades.Programas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Keily Mendiolaza
 */
public class ProgramasDAO extends GenericDAO<Programas> {

    public int nextId() {
        Programas t = null;
        try {
            t = (Programas) getHibernateTemplate().createQuery("from Programas t order by t.idprograma desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return t.getIdprograma() + 1;
        }
    }
    
    public Programas getPrograma_cod(String cod){
        return (Programas)getHibernateTemplate().createQuery("from Programas p where p.codprograma='"+cod+"'").uniqueResult();
    }
    
    public List<Programas> listarProgramas() {
        List<Programas> programas = new ArrayList<Programas>();
        try {
            programas = getHibernateTemplate().createQuery(
                "from Programas p order by p.codprograma asc ").list();
        } catch ( Exception e ) {
            System.out.println("Excepcion: " + e.getMessage());
            programas = null;
        }
        return programas;
    }
    
//    public List<Programas> getProgramas(int idRol) {
//        return getHibernateTemplate().createQuery("from Programas p where p.codprograma='"+cod+"'").list();
//    }
    
    public List<Programas> listarProgramas(int idRol) {
        List<Programas> programas = new ArrayList<Programas>();
        try {
            programas = getHibernateTemplate().createSQLQuery(
                      " select p.* from Programas p "
                    + " inner join Accesos a on a.idprograma = p.idprograma "
                    + " where a.idrol = " + idRol
                    + " order by p.idprograma asc ").addEntity(Programas.class).list();
            
        } catch ( Exception e ) {
            System.out.println("Excepcion:" + e.getMessage());
            programas = null;
        }
        return programas;
    }
}
