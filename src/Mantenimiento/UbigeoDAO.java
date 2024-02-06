package Mantenimiento;

import Entidades.Ubigeo;
import java.util.List;

/**
 *
 * @author Ledis Rivera
 */
public class UbigeoDAO extends GenericDAO<Ubigeo> {
    public List Listar_Departamentos() {
        return getHibernateTemplate().createSQLQuery("select distinct(departamento) from Ubigeo order by departamento ASC").list();
    }
    
    public List Listar_Provincias(String departamento) {
        return getHibernateTemplate().createSQLQuery("select distinct(provincia) from Ubigeo where departamento like '" + departamento + "' order by provincia ASC").list();
    }
    
    public List Listar_Distritos(String departamento, String provincia) {
        return getHibernateTemplate().createSQLQuery("select distinct(distrito) from Ubigeo where departamento like '" + departamento + "' " +
                                                     "and provincia like '" + provincia + "' order by distrito ASC").list();
    }
    
    public Ubigeo buscarUbigeo(String departamento, String provincia, String distrito) {
        Ubigeo ubigeo = new Ubigeo();
        try {
            ubigeo = (Ubigeo) getHibernateTemplate().createQuery("from Ubigeo where departamento like '" + departamento + "' " +
                                                                 "and provincia like '" + provincia + "' " +
                                                                 "and distrito like '" + distrito + "' " +
                                                                 "order by distrito ASC").uniqueResult();
        } catch ( Exception ex ) {
            System.out.println("Error:" + ex.getMessage());
        }
        return ubigeo;
    }
}
