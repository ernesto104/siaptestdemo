package Mantenimiento;

import Entidades.Estratificacion;

/**
 *
 * @author fabrica2
 */
public class EstratificacionDAO extends GenericDAO<Estratificacion> {


    public Estratificacion Obtener_Objeto_por_nombre(String codigo) {
        Estratificacion est = new Estratificacion();
        est = (Estratificacion) getHibernateTemplate().createQuery("from Estratificacion where codigoestratificacion='" + codigo + "'").uniqueResult();
        return est;
    }
    
    public Estratificacion Obtener_Objeto_por_descripcion(String descripcion) {
        Estratificacion est = new Estratificacion();
        est = (Estratificacion) getHibernateTemplate().createQuery("from Estratificacion where descripcion='" + descripcion + "'").uniqueResult();
        return est;
    }
}
