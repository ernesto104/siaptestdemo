package Mantenimiento;

import Entidades.PaquetesRepuestos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author usuario
 */
public class PaqueteRepuestoDAO extends GenericDAO<PaquetesRepuestos> {

    public List Obtener_Repuestos(int id) {
        return getHibernateTemplate().createQuery("from PaquetesRepuestos pr where pr.idpaqrepuestos = '" + id + "'").list();
    }

    public PaquetesRepuestos getPaq_Rep(int idpaq, String idRep) {
        return (PaquetesRepuestos) getHibernateTemplate().createQuery("from PaquetesRepuestos pr where pr.paquetes.idpaquete = '" + idpaq + "' "
                + "and pr.repuestos.codrepuesto= '" + idRep + "'").uniqueResult();
    }

    public boolean Eliminar_por_Paquete(int id_Paquete) {
        Session ses = getHibernateTemplate();
        try {            
            ses.getTransaction().begin();
            ses.createQuery("delete from PaquetesRepuestos pr where pr.paquetes.idpaquete = :id_paquete").setInteger("id_paquete", id_Paquete).executeUpdate();
            ses.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }
}
