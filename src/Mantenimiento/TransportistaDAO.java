/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Entidades.Transportistas;
import java.util.List;

/**
 *
 * @author Cers
 */
public class TransportistaDAO extends GenericDAO<Transportistas>{

    
    public Transportistas obtenerTransportistaPorNombre(String nombreT) {
        return (Transportistas) getHibernateTemplate().createQuery("from Transportistas where nombre='"+nombreT+"'").uniqueResult();
    }
    
    public List getListaOrderNombre() {// Solo nombres
        return getHibernateTemplate().createQuery("select T.nombre from Transportistas AS T order by T.nombre ASC").list();      
    }
    
    public List GetTransportistas(){// Todo el registro
        return getHibernateTemplate().createQuery("from Transportistas t order by t.nombre asc").list();
    }
    
    public List getLista_nombre(String nombre){
        return getHibernateTemplate().createQuery("from Transportistas t where t.nombre like '%"+nombre+"%' order by t.nombre ASC").list();
    }
    
    public int RucRepetido(String ruc){
        return ((Long)getHibernateTemplate().createQuery("select count(*) from Transportistas t where t.ruc = :ruc").setString("ruc", ruc).uniqueResult()).intValue();
    }
    
    public Transportistas obtenerTransportistaPorRuc(String ruc){
        return (Transportistas) getHibernateTemplate().createQuery("from Transportistas where ruc=:ruc").setString("ruc", ruc).uniqueResult();
    }
    
    public List getTransportistasOrdenadosXNombre() {// Solo nombres
        return getHibernateTemplate().createQuery("from Transportistas AS T order by T.nombre ASC").list();      
    }
}