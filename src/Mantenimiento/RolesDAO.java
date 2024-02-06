/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Entidades.Roles;

/**
 *
 * @author Cristian
 */
public class RolesDAO extends GenericDAO<Roles> {

    public Roles Obtener_Objeto_por_nombre(String nombre) {
        Roles r = new Roles();
        r = (Roles) getHibernateTemplate().createQuery("from Roles where nombre='" + nombre + "'").uniqueResult();
        return r;

    }

    public Roles Obtener_Rol_codigo(String codigo) {
        return (Roles) getHibernateTemplate().createQuery("from Roles where codigorol='" + codigo + "'").uniqueResult();
    }
}
