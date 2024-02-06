/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import Entidades.Sistema;
import java.util.List;

/**
 *
 * @author maverick225
 */
public class SistemaDAO extends GenericDAO<Sistema> {

    private Sistema sistema;

    public SistemaDAO() {
        sistema = Obtener_Objeto(12);
    }

    public Integer obtieneNumeroSerie() {

        return sistema.getSerie();
    }

    public Integer obtieneNumeroDocumento() {
        return sistema.getUltimonumero();
    }

    public int nextId() {
        Sistema sis = null;
        try {
            sis = (Sistema) getHibernateTemplate().createQuery("from Sistema e order by e.idtransaccion desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return sis.getIdtransaccion() + 1;
        }
    }

    public void actualizar() {
        sistema.setSerie(obtieneNumeroSerie() + 1);
        sistema.setUltimonumero(obtieneNumeroDocumento() + 1);
    }

    public Sistema obtener_por_nombre(String nombre) {
        getHibernateTemplate().beginTransaction();
        Sistema sis = (Sistema) session.createQuery("from Sistema s where s.descripcion = '" + nombre + "'").uniqueResult();
        return sis;
    }

    public Sistema Obtener_Objeto_por_nombre(String idsistema) {
        Sistema sis = new Sistema();
        try {
            sis = (Sistema) getHibernateTemplate().createQuery("from Sistema where idtransaccion='" + idsistema + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return sis;
    }

    /*  Emision de planillas  */
    public Sistema Obtener_Sistema_TipoDoc(String tipoDoc) {
        return (Sistema) getHibernateTemplate().createQuery("from Sistema where tipodoc = '" + tipoDoc + "'").uniqueResult();
    }

    public List<Sistema> listarSistemas() {
        return (List<Sistema>) getHibernateTemplate().createQuery("from Sistema order by tipodoc").list();
    }

}
