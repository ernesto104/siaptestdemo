package Mantenimiento;

import Entidades.Cabecsalvar;
import Entidades.Clientes;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import Servicios.HibernateUtil;
import java.util.ArrayList;
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
public class RepuestosDAO extends GenericDAO<Repuestos> {

    private Session sesion;
    private Transaction tx;

    public Repuestos obtenerRepuesto(int id) {
        Repuestos repuesto = new Repuestos();
        RepuestosId repuestosId = new RepuestosId(id);

        try {
            iniciaOperacion();
            repuesto = (Repuestos) sesion.createQuery("from Repuestos where idrepuesto=" + repuestosId.getIdrepuesto()).uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

        return repuesto;
    }

    public Repuestos obtenerRepuestoNombre(String nombre) {
        Repuestos repuesto = new Repuestos();

        try {
            iniciaOperacion();
            repuesto = (Repuestos) sesion.createQuery("from Repuestos where codrepuesto='" + nombre + "'").uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                //    sesion.close();
            }
        }

        return repuesto;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public List getListaPorMarca(String marca) {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos where marca LIKE '%" + marca + "%' order by descripcion ").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                //  sesion.close();
            }
        }
        return lista;
    }

    public List<Repuestos> getListaPorGrupo(String grup) {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos where descripcion LIKE '%" + grup + "%' order by descripcion").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                //  sesion.close();
            }
        }        
        return lista;
    }

    public List<Repuestos> getListaPorIDLinea(int idL) {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos where idLinea=" + idL + " order by descripcion").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                //  sesion.close();
            }
        }
        System.out.println("eeee: " + lista.size());
        return lista;
    }

    public List listarPorClienteCabecsalvar(Clientes cl, Cabecsalvar cs) {
        List returnList = getHibernateTemplate().createSQLQuery(
                "select * from Repuestos r, Detallees d, Cabecsalvar c where"
                + "(r.idrepuesto = d.idrepuesto) and "
                + "(c.idsalida = d.idsalida)").list();
        session.close();
        return returnList;
//        + "(c.TIPOTRA = d.TIPOTRA) and (c.TIPODOC = d.TIPODOC) and "
//                + "(c.NRORSERIE = d.NRORSERIE) and (c.NRODOCUMENTO = d.NRODOCUMENTO)"
    }

    public ArrayList<Repuestos> buscarXNombre(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Repuestos as r"
                + " where r.codrepuesto like '" + name + "%' order by r.codrepuesto asc");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public List<Repuestos> getListaOrderCodRep() {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos ORDER BY CODREPUESTO ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }

    public List<Repuestos> getListaOrderDescrip() {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos ORDER BY Descripcion ASC, descrmodelo asc").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }

    public List<Repuestos> getListaOrderIdLinea() {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos ORDER BY idLinea,codrepuesto ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }

    public List<Repuestos> getListaOrderUbicAlm() {
        List<Repuestos> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Repuestos ORDER BY ubicAlmacen,codrepuesto ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }

    public List getLista_marca(String nombre) {
        return getHibernateTemplate().createQuery("from Repuestos t where t.codrepuesto LIKE '" + nombre + "%' order by t.codrepuesto ASC").list();
    }

    public List<Repuestos> getLista_todos_orderdescrip() {
        return getHibernateTemplate().createQuery("from Repuestos order by descripcion, descrmodelo asc").list();
    }
    
    public List getLista_descripcion(String nombre) {
        return getHibernateTemplate().createQuery("from Repuestos t where t.descripcion LIKE '%" + nombre + "%' order by t.codrepuesto ASC").list();
    }

    public List getLista_descrimodelo(String nombre) {
        return getHibernateTemplate().createQuery("from Repuestos t where t.descrmodelo LIKE '%" + nombre + "%' order by t.codrepuesto ASC").list();
    }

    
    public int totalrepuestosxsize(){
        return getHibernateTemplate().createQuery("from Repuestos").list().size();
    }
    
}
