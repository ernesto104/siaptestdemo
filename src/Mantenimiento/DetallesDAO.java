package Mantenimiento;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Detallees;
import Entidades.Pedidoweb;
import Servicios.HibernateUtil;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */
public class DetallesDAO extends GenericDAO<Detallees> {

    private Session sesion;
    private Transaction tx;

    public List getListaDetalleEs(CabecesId cabecesId) {

        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Detallees where TIPOTRA='" + cabecesId.getTipotra() + "' AND "
                    + "TIPODOC='" + cabecesId.getTipodoc() + "' AND NRORSERIE='" + cabecesId.getNrorserie() + "' AND NRODOCUMENTO='" + cabecesId.getNrodocumento() + "'").list();

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
            }
        }
        return lista;
    }

    public List getListaDetalleEs_IdRepuesto(int idRepuesto) {
        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Detallees where idrepuesto=" + idRepuesto).list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                // sesion.close();
            }
        }
        return lista;
    }

    public List getDetalles(int idRep) {
        return getHibernateTemplate().createQuery("from Detallees d where d.repuestos.id.idrepuesto = " + idRep
                + " order by d.fecha desc").list();
    }

    public List getDetallesxFecha(int idRep, Date inicio, Date fin) {
        Query q = getHibernateTemplate().createQuery("from Detallees d where d.repuestos.id.idrepuesto = " + idRep
                + " and d.fecha between :_inicio and :_fin order by d.fecha ");
        q.setParameter("_inicio", inicio);
        q.setParameter("_fin", fin);
        return q.list();

    }

    public List getListaDetalleEs_Ingreso(int nroIngreso) {
        System.out.println("nroIngreso():" + nroIngreso);
        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
            String query = "from Detallees where nroingreso = " + nroIngreso + " AND "
                    + "(idoperacion = 1 OR  idoperacion = 2 OR idoperacion = 3 OR idoperacion = 7 OR idoperacion = 9)";
//                    + "(idoperacion = 2 OR  idoperacion = 4 OR idoperacion = 5 OR idoperacion = 6)"
//            System.out.println("query:::" + query);
            lista = sesion.createQuery(query).list();

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
            }
        }
        return lista;
    }
    
    public List getListaDetalleEs_IngCompraLocal(int nroDocumento) {
        System.out.println("nroDocumento:" + nroDocumento);
        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
            String query = "from Detallees where cabeces.id.nrodocumento = " + nroDocumento 
                    + " AND idoperacion = 1";
//            System.out.println("query:::" + query);
            lista = sesion.createQuery(query).list();

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
            }
        }
        return lista;
    }

    public List getListaDetalleEs_IngAjuste(int nroIngreso) {
        System.out.println("nroIngreso():" + nroIngreso);
        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
            String query = "from Detallees where nroingreso = " + nroIngreso 
                    + " AND idoperacion = 2";
//            System.out.println("query:::" + query);
            lista = sesion.createQuery(query).list();

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
            }
        }
        return lista;
    }

    public List getListaDetalleEs_Salida(int nroIngreso) {
        System.out.println("nro:" + nroIngreso);
        List<Detallees> lista = new LinkedList();
        try {
            iniciaOperacion();
//            lista = sesion.createQuery("from Detallees where nroingreso = " + nroIngreso + " AND idoperacion = 7").list();
            lista = sesion.createQuery("from Detallees where nroingreso = " + nroIngreso + " AND idoperacion = 10").list();
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                // sesion.close();
            }
        }
        return lista;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public List<Detallees> listarPorCabecsalvarCliente(int idSalida, int idCliente) {
        List<Detallees> lista = getHibernateTemplate().createQuery(
                "from Detallees where idsalida = " + idSalida + "and idcliente = " + idCliente).list();
        session.getTransaction().commit();
        return lista;
    }

    public List<Pedidoweb> listarPedidoWebxCliente(int idcabecweb) {
        List<Pedidoweb> lista = getHibernateTemplate().createQuery(
                "from Pedidoweb p where p.cabecweb.idCabweb= " + idcabecweb).list();
        session.getTransaction().commit();
        return lista;
    }

    public double demandaMensualRepuesto(String CodRepuesto, int mes, int año) {
        List<Object> lista = getHibernateTemplate().createSQLQuery(
                "Select d.cantentregada from Detallees d, Cabeces c,Repuestos r"
                + " where (d.idrepuesto = r.idrepuesto ) "
                + "and (r.codrepuesto = '" + CodRepuesto + "') "
                + "and (d.tipotra = c.tipotra) "
                + "and (d.tipodoc = c.tipodoc) "
                + "and (d.nrorserie = c.nrorserie) "
                + "and (d.nrodocumento = c.nrodocumento) "
                + "and (month(c.fecha) = " + mes + ") "
                + "and (year(c.fecha) =  " + año + ")").list();
        session.getTransaction().commit();
        double suma = 0.0;
        for (Object ob : lista) {
            suma += Double.parseDouble(String.valueOf(ob));
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    public List<Detallees> listarPorCabeces(Cabeces c) {
//        System.out.println("obtener detalles...");
//        System.out.println("c.Nro Doc::" + c.getId().getNrodocumento());
        return getHibernateTemplate().createQuery("from Detallees where cabeces = :c")
                .setParameter("c", c).list();
    }

    public List<Detallees> listarporSK(Cabecsalvar cs) {
        List<Detallees> lista = getHibernateTemplate().createQuery("from Detallees where idsalida = " + cs.getIdsalida()).list();
        session.getTransaction().commit();
        return lista;

    }
    
    public Date obtenerFechaMasAntiguaMovimiento() {
        Date fecha = new Date();
        try {
            fecha = (Date) getHibernateTemplate().createSQLQuery("select min(fecha) from Detallees").uniqueResult();
//            System.out.println("fecha:" + fecha);
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return fecha;
    }

}
