package Mantenimiento;

import Entidades.Bancos;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class BancoDAO extends GenericDAO<Bancos>{
    
    public int nextId() {
        Bancos t = null;
        try {
            t = (Bancos) getHibernateTemplate().createQuery("from Bancos t order by t.idbanco desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } 
        if ( Tamaño_Lista() == 0 )
            return 1;
        else
            return t.getIdbanco()+1;    
    }
    
    public Bancos getBanco_Nombre(String nombre){
        return (Bancos) getHibernateTemplate().createQuery("from Bancos b where b.nombre = '"+nombre+"'").uniqueResult();
    }
       
    public Bancos getBanco_Siglas(String siglas){
        return (Bancos) getHibernateTemplate().createQuery("from Bancos b where b.siglas = '"+siglas+"'").uniqueResult();
    }
    
    public List<Bancos> listar_Bancos(){
        return getHibernateTemplate().createQuery("select nombre from Bancos").list();
    }
    
    public List getBancoParaLetra() {
        return getHibernateTemplate().createQuery(
                  " select distinct c.banco from Bancos c "
                + " order by c.banco asc").list();
    }
}