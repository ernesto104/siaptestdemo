package Mantenimiento;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Pagos;
import Entidades.Vendedores;
import Presentacion.FacturacionElectronica.CabDocElectronico;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */
public class CabecesDAO extends GenericDAO<Cabeces> {
    
    private Session sesion;
    private Transaction tx;
    
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public CabecesId obtenercabecesID(String numdoc) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = sesion.beginTransaction();
        CabecesId cabecesID = new CabecesId();
        CabecesId c = null;
        try {
            cabecesID = (CabecesId) sesion.createQuery("from CabecesId where tipotra = 'V' and tipodoc='06'"
                    + " and nrodocumento=" + numdoc).uniqueResult();
            // tx.commit();
            c = (CabecesId) sesion.merge(cabecesID);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        return c;
    }

    public boolean agregarcabecera(Cabeces cab) {
        return (boolean) getHibernateTemplate().save(cab);
    }

    public boolean agregarcabDsalv(Cabeces cabsal) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            ses.save(cabsal);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            ses.close();
        }
    }

    public Cabeces obtenerCabeceraPorDocumento(CabecesId cabecesID) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
        Cabeces cabeces = new Cabeces();
        Cabeces c = null;
        try {
            cabeces = (Cabeces) sesion.createQuery("from Cabeces where tipodoc='" + cabecesID.getTipodoc() + 
                                                   "' AND nrodocumento=" + cabecesID.getNrodocumento()).uniqueResult();
            cabeces.getClientes();
            cabeces.getClientes().getNombre();
            // tx.commit();
            c = (Cabeces) sesion.merge(cabeces);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if ( sesion != null ) {
                sesion.close();
            }
        }
        return c;
    }
    
    public Cabeces obtenerCabeceraPorDocumentoX4Claves(CabecesId cabecesID) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = sesion.beginTransaction();
        Cabeces cabeces = new Cabeces();
        Cabeces c = null;
        try {
            cabeces = (Cabeces) sesion.createQuery("from Cabeces where tipodoc = '" + cabecesID.getTipodoc() + "' " +
                                                   " AND nrodocumento = " + cabecesID.getNrodocumento() +
                                                   " AND tipotra = '" + cabecesID.getTipotra() + "' " + 
                                                   " AND nrorserie = " + cabecesID.getNrorserie()).uniqueResult();
            // tx.commit();
            c = (Cabeces) sesion.merge(cabeces);
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            
        } finally {
            if ( sesion != null ) {
                sesion.close();
            }
        }
        return c;
    }

    public Cabeces obtenerCabeceraPorDocumento_cerrar(CabecesId cabecesID) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = sesion.beginTransaction();
        Cabeces cabeces = new Cabeces();
        Cabeces c = null;
        try {
            cabeces = (Cabeces) sesion.createQuery("from Cabeces where tipodoc='" + cabecesID.getTipodoc() + 
                                                   "' AND nrodocumento=" + cabecesID.getNrodocumento()).uniqueResult();
            // tx.commit();
            c = (Cabeces) sesion.merge(cabeces);
            
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
            
        } finally {
            if ( sesion != null ) {
                sesion.close();
            }
        }
        return c;
    }

    public boolean agregarcabcanulada(Cabeces cab) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(cab);
            session.getTransaction().commit();
            return true;
            
        } catch ( HibernateException e ) {
            session.beginTransaction().rollback();
            System.out.println("Error :" + e.getMessage());
            return false;
            
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }
    
    public boolean registrarFactAnulada(Cabeces cab) {
        Session ses = getHibernateTemplate();
        Transaction tx = ses.beginTransaction();
        try {
            ses.update(cab);
            tx.commit();
            return true;
            
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            if ( ses != null ) {
                ses.close();
            }
        }
    }

    public Cabeces obtenerCabecera(CabecesId cabecesID) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces where tipotra = 'V' and tipodoc = '" + cabecesID.getTipodoc() + "'"
                + " and nrorserie = '" + cabecesID.getNrorserie() + "' and nrodocumento = '" + cabecesID.getNrodocumento() + "'").uniqueResult();
    }

    public Cabeces obtenerCabecera_IC(CabecesId cabecesID) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces where tipotra = 'C' and tipodoc = '" + cabecesID.getTipodoc() + "'"
                + " and nrorserie = '" + cabecesID.getNrorserie() + "' and nrodocumento = '" + cabecesID.getNrodocumento() + "'").uniqueResult();
    }

    public Pagos obtenerClientePendiente(CabecesId cabecesID) {
        return (Pagos) getHibernateTemplate().createQuery("from Cabeces where cabeces.id.tipotra = 'V' and cabeces.id.tipodoc = '" + cabecesID.getTipodoc() + "'"
                + " and cabeces.id.nrorserie = '" + cabecesID.getNrorserie() + "' and cabeces.id.nrodocumento = '" + cabecesID.getNrodocumento() + "'").list();
    }

    public List<Cabeces> obtenerPlanillaCompras(Date desde, Date hasta) {
        return getHibernateTemplate().createQuery("from Cabeces where "
                + "(tipotra = 'C') and "
                + "tipocambio.fecha between :desde and :hasta "
//                + "order by tipocambio.fecha desc").
                + "order by nrorserie desc, nrodocumento desc")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta).list();
    }

    public List<Cabeces> obtenerPlanillaVentas(Date desde, Date hasta) {
        return getHibernateTemplate().createQuery("from Cabeces where (tipotra = 'V') and "
                + "tipocambio.fecha between :desde and :hasta"
                + " and id.tipodoc in ('01','02','03','04') "
                + "order by nrorserie desc, nrodocumento desc")
//                + "order by tipocambio.fecha desc")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta).list();
    }

    public List<Cabecsalvar> obtenerPlanillaVarias(Date desde, Date hasta) {
        System.out.println("desde:" + desde);
        System.out.println("hasta:" + hasta);
        List<Cabecsalvar> l = getHibernateTemplate().createQuery("from Cabecsalvar where "
                + "fecha between :desde and :hasta and "
                + "cabeces.id.tipotra is null and "
                + "cabeces.id.tipodoc is null and "
                + "cabeces.id.nrorserie is null and "
                + "cabeces.id.nrodocumento is null "
//                + "order by fecha desc")
                + "order by nrorserie desc, nrodocumento desc")
                .setParameter("desde", desde)
                .setParameter("hasta", hasta).list();
        return l;
    }

    //////  Comisiones
    public List Comisiones_Pendientes(String vendedor) {
//        return getHibernateTemplate().createQuery("select c , sum(p.importe) from Pagos p right join  p.cabeces c where ( c.comision is null or c.comision = ' ')  "
//                + "and c.id.tipodoc in ('01','02','03') group by (c.id) order by c.tipocambio.fecha desc").list();
        return getHibernateTemplate().createQuery("from Cabeces where ( comision <> '*' or comision is null) and "
                + "id.tipodoc in ('01','02','03') and vendedores.nombre = '" + vendedor + "' and (estado is null or estado <> 'ANULADO') "
                + "order by tipocambio.fecha desc").list();
    }

    public List Comisiones_Pagadas(String vendedor) {
        return getHibernateTemplate().createQuery("from Cabeces where ( comision = '*' ) and "
                + "id.tipodoc in ('01','02','03') and vendedores.nombre = '" + vendedor + "' and (estado is null or estado <> 'ANULADO' )"
                + "order by tipocambio.fecha desc").list();
    }

    public List Comisiones(String vendedor) {
        return getHibernateTemplate().createQuery("from Cabeces where "
                + "id.tipodoc in ('01','02','03') and vendedores.nombre = '" + vendedor + "' and (estado is null or estado <> 'ANULADO') "
                + "order by tipocambio.fecha desc").list();
    }

    public List Comisiones_Pendientes() {
        return getHibernateTemplate().createQuery("from Cabeces where (comision <> '*' or comision is null ) and "
                + "id.tipodoc in ('01','02','03') and (estado is null or estado <> 'ANULADO') "
                + "order by tipocambio.fecha desc").list();
    }

    public List Comisiones_Pagadas() {
        return getHibernateTemplate().createQuery("from Cabeces where (comision = '*'  ) and "
                + "id.tipodoc in ('01','02','03') and (estado is null or estado <> 'ANULADO' ) "
                + "order by tipocambio.fecha desc").list();
    }

    public List Comisiones() {
        return getHibernateTemplate().createQuery("from Cabeces where "
                + "id.tipodoc in ('01','02','03') and (estado is null or estado <> 'ANULADO'  )"
                + "order by tipocambio.fecha desc").list();
    }

    ///// Resumen general de clientes
    public List ObtenerDeudas_FacBol(int cliente_id, String fecha_inicio, String fecha_fin) {
        return getHibernateTemplate().createSQLQuery("select c.tipotra,c.tipodoc,c.nrorserie,c.nrodocumento,"
                + "c.total, c.moneda,c.fecha,sum(p.importe)"
                + " from cabeces as c left join pagos as p on "
                + " c.nrodocumento = p.nrodocumento and "
                + " c.tipotra = p.tipotra and "
                + " c.nrorserie = p.nroserie and "
                + " c.tipodoc = p.tipodoc "
                + " where "
                + " c.idcliente = " + cliente_id
//                + " and c.estado is null "
                + " and c.estado = '' "
                
                + " and c.fecha between '" + fecha_inicio + "' and '" + fecha_fin
                + "' and c.tipodoc in ('01','02','03','04')group by (c.nrodocumento),(c.tipodoc),"
                + "(c.tipotra),(c.nrorserie) having sum(p.importe) < c.total or "
                + " sum(p.importe) is null or sum(p.importe) = 0  ").list();
    }

    public List ObtenerDeudas_Letras(int cliente_id, String fecha_inicio, String fecha_fin) {
        return getHibernateTemplate().createSQLQuery("select c.tipotra,c.tipodoc,c.nrorserie,c.nrodocumento,"
                + " c.total, c.moneda ,c.fecha , c.fechavencimiento, c.nc_facbol, c.letra_facbol,c.idbanco,c.LETRA_NROBANCO "
                + " from cabeces as c left join pagos as p on "
                + " c.nrodocumento = p.nrodocumento and "
                + " c.tipotra = p.tipotra and "
                + " c.nrorserie = p.nroserie and "
                + " c.tipodoc = p.tipodoc "
                + " where "
                + " c.idcliente = " + cliente_id
//                + " and c.estado is null "
//                + " and c.estado = '' "
                + " and c.tipodoc = '06' "
                + " and c.fecha between '" + fecha_inicio + "' and '" + fecha_fin
                + "' and c.tipodoc in ('06')group by (c.nrodocumento),(c.tipodoc),"
                + "(c.tipotra),(c.nrorserie) having sum(p.importe) < c.total or "
                + " sum(p.importe) is null or sum(p.importe) = 0  ").list();
                
//              + " and c.fecha between '" + fecha_inicio + "' and '" + fecha_fin + "'").list();
    }

    public List ObtenerDeudas_SK(int cliente_id, String fecha_inicio, String fecha_fin) {
        return getHibernateTemplate().createSQLQuery("select cs.codigosalida, cs.idcliente , cs.total , sum(p.importe),cs.fecha"
                + " from cabecsalvar as cs left join pagos as p on "
//                + " cs.codigosalida  = p.nrodocumento and "
                + " cs.idsalida = p.idsalida "
                + " where "
                + " cs.idcliente = " + cliente_id
                + " and cs.fecha between '" + fecha_inicio + "' and '" + fecha_fin + "'"
                + " group by (cs.codigosalida), (cs.nroseriesk) having sum(p.importe) < cs.total or "
                + " sum(p.importe) is null or sum(p.importe) = 0 ").list();
//                + " cs.total>sum(p.importe) or sum(p.importe) is null or sum(p.importe) = 0").list();
    }

    public List<Cabeces> obtenerGuiasRemision() {
        List<Cabeces> returnList = getHibernateTemplate().createQuery(
                  " from Cabeces where nroguia is not null "
                + " and nrodocumento < 0").list();
        /*
        "from Cabeces where (nroguia is not null) "
                + "and (tipotra is null)"
                + "and (tipodoc is null)"
                + "and (nrorserie is null)"
                + "and (nrodocumento is null)"
        */
        
        return returnList;
    }

    public Cabeces obtenerCabecera_Id(CabecesId id) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces c where c.id.tipotra = '" + id.getTipotra()
                + "' and c.id.tipodoc = '" + id.getTipodoc()
                + "' and c.id.nrorserie = '" + id.getNrorserie()
                + "' and c.id.nrodocumento = '" + id.getNrodocumento() + "'").uniqueResult();
    }
    
    public Cabeces obtenerCabecera(String tipoTra, String tipoDoc, String nroSerie, String nroDocumento) {
        String query = "from Cabeces c where c.id.tipotra like '" + tipoTra + "' "
                + " and c.id.tipodoc = '" + tipoDoc + "' "
                + " and c.id.nrorserie = '" + nroSerie + "' "
                + " and c.id.nrodocumento = '" + nroDocumento + "'";
        //Session session = getHibernateTemplate();
        try {
            //List<Cabeces> returnList = session.createQuery(query).list();
            List<Cabeces> returnList = getHibernateTemplate().createQuery(query).list();
            if ( returnList.isEmpty() ) {
                return null;
                
            } else {
                return returnList.get(0);
            }
        } catch ( Exception ex ) {
            System.out.println("Excepcion:" + ex.getMessage());
            ex.printStackTrace();
            return null;
            
        } finally {
            //session.close();
            //getHibernateTemplate().close();
            System.out.println("No cierro sesion aun");
        }
    }

    public Cabeces obtenerCabecera_Idsinserie(CabecesId id) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces c where c.id.tipotra = '" + id.getTipotra()
                + "' and c.id.tipodoc = '" + id.getTipodoc()
                + "' and c.id.nrodocumento = '" + id.getNrodocumento() + "'").uniqueResult();
    }

    public List ObtenerDeudas(int cliente_id) {
        return getHibernateTemplate().createSQLQuery(
                  " SELECT cabeces.tipotra,"
                + " cabeces.tipodoc, "
                + " cabeces.nrorserie, "
                + " cabeces.nrodocumento, "
                + " cabeces.total, "
                + " cabeces.idcliente, "
                + " sum(pagos.importe), "
                + " cabeces.nc_facbol, "
                + " cabeces.idsucursal, "
                + " cabeces.moneda "
                          
                + " from cabeces left join pagos "
                + " on cabeces.nrodocumento = pagos.nrodocumento and "
                + " cabeces.tipotra = pagos.tipotra and "
                + " cabeces.nrorserie = pagos.nroserie and "
                + " cabeces.tipodoc = pagos.tipodoc "
                + " where "
                + " cabeces.idcliente = " + cliente_id
                          
                + " and cabeces.estado = '' "
//                + " and cabeces.estado is null "
                          
                + " and cabeces.tipodoc in ('01','02','03','04')group by (cabeces.nrodocumento),(cabeces.tipodoc), "
                + " (cabeces.tipotra),(cabeces.nrorserie) having sum(pagos.importe) < cabeces.total or "
                + " sum(pagos.importe) is null").list();
    }
    
    public List ObtenerLetrasPendientesCancelar(int cliente_id) {
        String query = " SELECT cabeces.tipotra, "
                + " cabeces.tipodoc, "
                + " cabeces.nrorserie, "
                + " cabeces.nrodocumento, "
                + " cabeces.total, "
                + " cabeces.idcliente, "
                + " SUM(pagos.importe), "
                + " cabeces.nc_facbol, "
                + " cabeces.idsucursal "
                          
                + " FROM cabeces "
                + " LEFT JOIN pagos ON cabeces.nrodocumento = pagos.nrodocumento "
                    + " AND cabeces.tipotra = pagos.tipotra "
                    + " AND cabeces.nrorserie = pagos.nroserie "
                    + " AND cabeces.tipodoc = pagos.tipodoc "
                          
                + " WHERE cabeces.idcliente = " + cliente_id
//                + " AND cabeces.estado = '' " // Sólo para los otros documentos (Factura, NC, etc) el estado al crearse el documento es vacío (no es NULL).
                + " AND cabeces.estado IS NULL " // Sólo para las letras(tipodoc = 06), el estado al crear una letra es NULL (no es vacío).
                          
                + " AND cabeces.tipodoc IN ('06') "
                + " GROUP BY  (cabeces.nrodocumento), "
                          + " (cabeces.tipodoc), "
                          + " (cabeces.tipotra), "
                          + " (cabeces.nrorserie) "
                + " HAVING SUM(pagos.importe) < cabeces.total "
                + " OR SUM(pagos.importe) IS NULL ";
//        System.out.println("query:" + query);
        return getHibernateTemplate().createSQLQuery(query).list();
    }
    
    public List ObtenerDeudasPendientesCancelar(int cliente_id) {
        String query = " SELECT cabeces.tipotra, "
                + " cabeces.tipodoc, "
                + " cabeces.nrorserie, "
                + " cabeces.nrodocumento, "
                + " cabeces.total, "
                + " cabeces.idcliente, "
                + " SUM(pagos.importe), "
                + " cabeces.nc_facbol, "
                + " cabeces.idsucursal, "
                + " cabeces.moneda "
                          
                + " FROM cabeces "
                + " LEFT JOIN pagos ON cabeces.nrodocumento = pagos.nrodocumento "
                    + " AND cabeces.tipotra = pagos.tipotra "
                    + " AND cabeces.nrorserie = pagos.nroserie "
                    + " AND cabeces.tipodoc = pagos.tipodoc "
                          
                + " WHERE cabeces.idcliente = " + cliente_id
//                + " AND cabeces.estado = '' " // Sólo para los otros documentos (Factura, NC, etc) el estado al crearse el documento es vacío (no es NULL).
//                + " AND cabeces.estado IS NULL " // Sólo para las letras(tipodoc = 06), el estado al crear una letra es NULL (no es vacío).
                + " AND ((cabeces.tipodoc IN ('06') AND cabeces.estado IS NULL) "
                + "      OR (cabeces.tipodoc IN ('01', '02', '03', '04') AND cabeces.estado = '') ) "
                          
//                + " AND cabeces.tipodoc IN ('06', '01', '02', '03', '04') "
                + " GROUP BY  (cabeces.nrodocumento), "
                          + " (cabeces.tipodoc), "
                          + " (cabeces.tipotra), "
                          + " (cabeces.nrorserie) "
                + " HAVING SUM(pagos.importe) < cabeces.total "
                + " OR SUM(pagos.importe) IS NULL ";
        System.out.println("query:" + query);
        return getHibernateTemplate().createSQLQuery(query).list();
    }

    public boolean PagarComisiones(ArrayList<Cabeces> seleccionados) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            for (Cabeces cab : seleccionados) {
                Cabeces c = (Cabeces) ses.merge(cab);
                ses.save(c);
            }
            tx.commit();
            return true;
            
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
            
        } finally {
            ses.close();
        }
    }

    public boolean ActualizarCab_Vendedor(Cabeces c, Vendedores v) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            Cabeces cab = (Cabeces) ses.merge(c);

            if (v != null) {
                Vendedores vend = (Vendedores) ses.merge(v);
                ses.update(vend);
                cab.setVendedores(vend);
            }
//            System.out.println("vendedor en dao:" + cab.getVendedores());
            ses.update(cab);
            tx.commit();
            return true;
            
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
            
        } finally {
            ses.close();
        }
    }

    public boolean ActualizarCabecera(Cabeces c) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            Cabeces cabeces = (Cabeces) ses.merge(c);
            ses.update(cabeces);
            tx.commit();
            return true;
            
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
            
        } finally {
            ses.close();
        }
    }
    
//    public boolean ActualizarTodasCabecera(List<Cabeces> lst) {
//        System.out.println("ActualizarTodasCabecera...");
//        //Session ses = getHibernateTemplate();
//        Session ses = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        try {
//            tx = ses.beginTransaction();
//            
//            System.out.println("entra al try");
//            int i = 0;
//            for ( Cabeces cab : lst ) {
//                System.out.println("for " + i);
//                i++;
////                Cabeces c = (Cabeces) ses.merge(cab);
//                
//                Cabeces newCab = (Cabeces) ses.get(Cabeces.class, cab.getId()); 
//                newCab = cab;
//                ses.merge(newCab);
//               
//                //ses.update(c);
//                ses.flush();
//                ses.clear();
//            }
//            
//            tx.commit();
//            System.out.println("comit...");
//            return true;
//            
//        } catch (HibernateException e) {
//            if ( tx != null ) {
//                tx.rollback();
//                System.out.println("Excepcion(ActualizarTodasCabecera):" + e.getMessage());
//                e.printStackTrace();
//            }
//            return false;
//            
//        } finally {
//            ses.close();
//        }
//    }
    
    public Cabeces obtenercabeces(String numdoc, String codigoTipoDocumento) {
        
        Cabeces cab = new Cabeces();
        try {
            cab = (Cabeces) getHibernateTemplate().createQuery("from Cabeces c where c.id.tipotra = 'V' " +
                " and c.id.tipodoc = '" + codigoTipoDocumento + "' " +
                " and c.id.nrodocumento = '" + numdoc + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        return cab;
    }
    
    public Cabeces obtenerCabecera(int nroguia) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces where nroguia = " + nroguia).uniqueResult();
    }
    
    public Cabeces obtenerCabeces(int idCabProforma) {
//        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces where idcabproforma = " + idCabProforma).uniqueResult();
//    }
    
//    public ArrayList<Repuestos> buscarXNombre(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Cabeces where idcabproforma = " + idCabProforma);
        ArrayList lista = (ArrayList) q.list();
        try {
            if ( lista != null ) {
                return (Cabeces) lista.get(0);

            } else {
                return null;
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
            
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }
    
    // Obtener registros de Cabeces y Detallees (Factura, Boleta, NC, ND):
    public List obtenerPlanillaDocElectronicos(String fecha_inicio, String fecha_fin) {
        Session ses = getHibernateTemplate();
        try {
            String query = " select "
                + " DATE_FORMAT(c.fecha, '%d/%m/%Y'), "
                //+ " c.fecha as FE, "
                + " c.tipodoc as TD, "
                //+ " getTipDocSunat(c.tipodoc) as TD, "
                + " CASE "
                + "     WHEN c.tipodoc = '01' THEN 'F' "
                + "     WHEN c.tipodoc = '02' THEN 'B' "
                + " END as PREF, "
                + " c.nrorserie as NS, "
                + " c.nrodocumento as ND, "
                + " cl.ruc as NDA, "
                + " '06' as TDA, "
                + " cl.nombre as ARSA, "
                + " if(c.moneda = 1,'S/.','US$') as M, "
                + " round(c.total/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2) as TOG, "
                + " c.total - round(c.total/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2) as MTI, "
                + " c.total as MP, "
                + " c.GEN_DOC_ELECT as 'SE GENERO DOC ELECT', "
                + " r.CODREPUESTO, "
                + " r.DESCRIPCION, "
                + " round((((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista)/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra),2) as VVU, "
                + " round(((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista,2) as PVUI, "
                + " d.cantpedida AS CI, "
                + " 0 as DESCUENTOS, "
                + " round((((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista) * d.cantpedida/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2) as VI, "
                + " (round(((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista,2) * d.cantpedida - "
                + " round((((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista) * d.cantpedida/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2) "
                + " ) as ITI, "
                + " round(((100 - d.DESCUENTO1)/100)*((100 - d.DESCUENTO2)/100)*((100 - d.DESCUENTO3)/100)*((100 - d.DESCUENTO4)/100)*d.preciolista,2) * d.cantpedida as TI, "
                + " c.fecha_envio, "
                + " DATEDIFF(DATE_ADD(c.fecha,  INTERVAL 7 DAY), SYSDATE()), "
                
                + " c.estado as 'ES_ANULADO', "
                          
                + " c.fecha as FE, "
                + " c.nroguia, "
                + " cl.direccion "
                    
                + " ,getTipDocSunat(c.tipodoc) as TDE "
                          
                + " from cabeces c "
                + " inner join detallees d "
                + " on c.nrodocumento = d.nrodocumento "
                + " and c.tipotra = d.tipotra "
                + " and c.nrorserie = d.nrorserie "
                + " and c.tipodoc = d.tipodoc "
                
                + " inner join repuestos r "
                + " on r.idrepuesto = d.idrepuesto "
                + " and r.idlinea = d.idlinea "
                
                + " inner join clientes cl "
                + " on cl.idcliente = c.idcliente "
                
                + " where "
                + " c.fecha between '" + fecha_inicio + "' and '" + fecha_fin + "' "
                + " and c.fecha_envio is null "
                
                + " UNION "
                + " ( "
                + " select "
                + " DATE_FORMAT(c.fecha, '%d/%m/%Y'), "
                
                //+ " getTipDocSunat(c.tipodoc) as TD, "
                + " c.tipodoc as TD, "
                + " CASE "
                + "     WHEN c.tipodoc = '03' THEN 'FC' "
                + "     WHEN c.tipodoc = '04' THEN 'FD' "
                + " END as PREF, "
                + " c.nrorserie as NS, "
                
                + " c.nrodocumento as ND, "
                + " cl.ruc as NDA, "
                + " '06' as TDA, "
                + " cl.nombre as ARSA, "
                + " if(c.moneda = 1,'S/.','US$') as M, "
                
                + " round(c.total/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra),2) as TOG, "
                + " c.total - round(c.total/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra),2) as MTI, "
                + " c.total as MP, "
                + " c.GEN_DOC_ELECT as 'SE GENERO DOC ELECT', "
                
                + " r.CODREPUESTO, "
                + " r.DESCRIPCION, "
                
                + " round(d.valor/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra),2) as VVU, "
                + " d.valor as PVUI, "
                + " d.cantidad AS CI, "
                + " 0 as DESCUENTOS, "
                
                + " round((round(d.valor,2) * d.cantidad)/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2) as VI, "
                + " (round(d.valor,2) * d.cantidad - "
                + " round((round(d.valor,2) * d.cantidad)/getFactorIGV(c.nrodocumento,'1', c.tipodoc, c.tipotra), 2)) "
                + "  as ITI, "
                
                + " round(d.valor,2) * d.cantidad as TI, "
                + " c.fecha_envio, "
                + " DATEDIFF(DATE_ADD(c.fecha,  INTERVAL 7 DAY), SYSDATE()) as MaxDiasEnvio, "
                
                + " c.estado as 'ES_ANULADO', "          
                + " c.fecha as FE, "
                    
                + " c.nroguia, "
                + " cl.direccion "
                    
                + " ,getTipDocSunat(c.tipodoc) as TDE "
                
                + " from cabeces c  "
                + " inner join detallenota d "
                + " on c.nrodocumento = d.nrodocumento "
                + " and c.tipotra = d.tipotra "
                + " and c.nrorserie = d.nrorserie "
                + " and c.tipodoc = d.tipodoc "
                
                + " left join repuestos r "
                + " on r.idrepuesto = d.idrepuesto "
                + " and r.idlinea = d.idlinea "
                
                + " inner join clientes cl "
                + " on cl.idcliente = c.idcliente "
                
                + " where "
                + " c.fecha between '" + fecha_inicio + "' and '" + fecha_fin + "' "
                + " and c.fecha_envio is null "
                + " ) "       
                //FILTROS PARA PRUEBAS:
                //+ " and (c.nrodocumento like '8536' "
                //+ " or c.nrodocumento like '8537') "

                + " ORDER BY FE desc, NS desc, ND desc ";
            System.out.println("query:" + query);
            
            List lst = ses.createSQLQuery(query).list();
            mostrarDatos(lst);
            return lst;
            
        } catch ( Exception ex ) {
            System.out.println("Excepcion(obtenerPlanillaDocElectronicos):" + ex.getMessage());
            return null;
            
        } finally {
            ses.close();
            System.out.println("Cerrando sesion(obtenerPlanillaDocElectronicos)");
        }
    }
    
    public void mostrarDatos(List lst) {
        System.out.println("INICIO-BD-return");
        Iterator ite = lst.iterator();
        int i = 1;
        while ( ite.hasNext() ) {
            Object[] obj = (Object[]) ite.next();
            String c1 = String.valueOf(obj[0]);
            String c2 = String.valueOf(obj[1]);
            String c3 = String.valueOf(obj[2]);
            String c4 = String.valueOf(obj[3]);
            String c5 = String.valueOf(obj[4]);
            
            String c6 = String.valueOf(obj[5]);
            String c7 = String.valueOf(obj[6]);
            String c8 = String.valueOf(obj[7]);
            String c9 = String.valueOf(obj[8]);
            String c10 = String.valueOf(obj[9]);
            
            String c11 = String.valueOf(obj[10]);
            String c12 = String.valueOf(obj[11]);
            String c13 = String.valueOf(obj[12]);
            String c14 = String.valueOf(obj[13]);
            String c15 = String.valueOf(obj[14]);
            
            String c16 = String.valueOf(obj[15]);
            String c17 = String.valueOf(obj[16]);
            String c18 = String.valueOf(obj[17]);
            String c19 = String.valueOf(obj[18]);
            String c20 = String.valueOf(obj[19]);
            
            String c21 = String.valueOf(obj[20]);
            String c22 = String.valueOf(obj[21]);
            String c23 = String.valueOf(obj[22]);
            String c24 = String.valueOf(obj[23]);
            String c25 = String.valueOf(obj[24]);
            String c26 = String.valueOf(obj[28]);
            
            System.out.println("Nº " + i);
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);
            System.out.println(c4);
            System.out.println(c5);
            
            System.out.println(c6);
            System.out.println(c7);
            System.out.println(c8);
            System.out.println(c9);
            System.out.println(c10);
            
            System.out.println(c11);
            System.out.println(c12);
            System.out.println(c13);
            System.out.println(c14);
            System.out.println(c15);
            
            System.out.println(c16);
            System.out.println(c17);
            System.out.println(c18);
            System.out.println(c19);
            System.out.println(c20);
            
            System.out.println(c21);
            System.out.println(c22);
            System.out.println(c23);
            System.out.println(c24);
            System.out.println(c25);
            System.out.println(c26);
            
            System.out.println("-----------------------------");
            i++;
        }
        System.out.println("FIN-BD-return");
    }
    
    public boolean ActualizarTodasCabecera(List<CabDocElectronico> listaCab) {
        boolean valor = false;
        
        Session ses = getHibernateTemplate();
        //Session ses = HibernateUtil.getSessionFactory().getCurrentSession();
//        System.out.println("getHibernateTemplate() :" + getHibernateTemplate());
//        Transaction tx = getHibernateTemplate().beginTransaction();
        //Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("ses:" + ses);
        Transaction tx = ses.beginTransaction();
        System.out.println("tx:" + tx);
        //Transaction tx = session.beginTransaction();
        

        try {
                
                System.out.println("tx:" + tx);
                System.out.println("listaCab:" + listaCab);
                System.out.println("listaCab.size():" + listaCab.size());

                for ( CabDocElectronico c : listaCab ) {
                    String tipoTra = "V";
                    String tipDoc = c.getTip_doc();
                    String nroSerie = c.getNro_serie_BD();
                    String nroDoc = c.getNro_documento_BD();
                    
                    Cabeces cab = obtenerCabecera(tipoTra, tipDoc, nroSerie, nroDoc);
                    System.out.println("cab:" + cab);
                    Date ahora = new Date();
                    cab.setFechaEnvio(ahora);
                    cab.setGenDocElect(3);
                    //Cabeces newCab = (Cabeces) session.merge(c);
                    //Cabeces newCab = (Cabeces) session.get(Cabeces.class, c.getId());
                    //System.out.println("newCab>>" + newCab);
                    String tt = cab.getId().getTipotra();
                    String td = cab.getId().getTipodoc();
                    String ns = cab.getId().getNrorserie();
                    String nd = cab.getId().getNrodocumento();
                    
                    System.out.println("tt:" + tt);
                    System.out.println("td:" + td);
                    System.out.println("ns:" + ns);
                    System.out.println("nd:" + nd);
                    
                    //newCab = c;
                    //session.merge(newCab);
                    
                    //Date ahora = new Date();
                    //newCab.setFechaEnvio(ahora);
                    
                    //Cabeces rt = (Cabeces) getHibernateTemplate().merge(c);
                    
                    //Cabeces rt = (Cabeces) ses.merge(c);
                    //ses.update(rt);
                    ses.update(cab);
                    
                    //session.update(c);
                    //getHibernateTemplate().update(rt);
                    
                    //newCab.setGenDocElect(1);
                    //session.update(newCab);
                    
//                    mostrarLista(newCab);
//                    session.flush();
//                    session.flush();
//                    session.clear();
                }
                tx.commit();
                valor = true;

        } catch ( HibernateException e ) {
            if ( tx != null ) {
                tx.rollback();
                e.printStackTrace();
            }
            System.out.println("Error(HibernateException-ActualizarTodasCabecera):" + e.getMessage());

        } finally {
           //getHibernateTemplate().close(); 
           ses.close();
        }
        return valor;
    }
    
    public List Listar_AniosReporteFlujoComparativo() {
        return getHibernateTemplate().createSQLQuery("select distinct year(fecha) from cabeces order by fecha desc").list();
    }
}
