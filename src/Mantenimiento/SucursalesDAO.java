/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Entidades.Sucursales;
import java.util.List;

/**
 *
 * @author CRS
 */
public class SucursalesDAO extends GenericDAO<Sucursales>{
    public Sucursales getSucursal(int idSuc,int idCliente){
        return (Sucursales) getHibernateTemplate().createQuery("from Sucursales s where s.idsucursal = "
                +idSuc+" and s.clientes.idcliente="+idCliente).uniqueResult();
    }
    public List getSucursal_Cliente(int cliente_id) {
        return getHibernateTemplate().createQuery("from Sucursales s where s.clientes.idcliente = "+cliente_id).list();
    }
}
