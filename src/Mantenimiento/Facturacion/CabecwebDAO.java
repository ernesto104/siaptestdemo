/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento.Facturacion;

import Entidades.Cabecweb;
import Mantenimiento.GenericDAO;
import java.util.List;

/**
 *
 * @author fabrica2
 */
public class CabecwebDAO extends GenericDAO<Cabecweb> {

    public List<Cabecweb> obtenerListaEstado(String estado) {
        List<Cabecweb> lista = null;
        try {
            lista = getHibernateTemplate().createQuery("from Cabecweb where estado = '" + estado + "'").list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }

//        if (estado.equals("a") || estado.equals("p") || estado.equals("r")) {
//            
//        } else {
//            return null;
//        }
        return lista;
    }
}
