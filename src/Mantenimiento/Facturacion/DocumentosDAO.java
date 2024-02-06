package Mantenimiento.Facturacion;

import Entidades.Cabeces;
import Entidades.Cabecproformas;
import Entidades.Cabecsalvar;
import Entidades.Cabecweb;
import Entidades.Detallees;
import Entidades.Detalleproformas;
import Entidades.Pagos;
import Entidades.Pedidoweb;
import Entidades.Repuestos;
import Mantenimiento.GenericDAO;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrador
 */
public class DocumentosDAO extends GenericDAO<Cabecproformas> { //   

//    public boolean Guardar_ActualizarDoc(Cabeces cabFactura, ArrayList<Detallees> dt, Pagos pago, List<Cabecproformas> cabec_actualizar) {
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
//        try {
//            if ( cabFactura.getTipocambio() != null ) {
//                System.out.println("fecha(cabFactura): " + cabFactura.getTipocambio().getFecha());
//            }
//            
//            sesion.save(cabFactura);
//            System.out.println("guardada la Factura...");
//            
//            // Actualizar a cada una de las proformas seleccionadas su cabecera
//            if ( cabec_actualizar != null ) {
//                for ( Cabecproformas cp : cabec_actualizar ) {
//                    if ( cabec_actualizar != null ) {
//                        Object cabec_act = sesion.merge(cp);
//                        sesion.update(cabec_act);
//                    }
//                    sesion.flush();
//                    sesion.clear();
//                }
//            }
//            // Actualiza a una proforma seleccionada su cabecera
//            // if (cabec_actualizar != null) {
//            //    Object cabec_act = sesion.merge(cabec_actualizar);
//            //    sesion.update(cabec_act);
//            // }
////            System.out.println("idpago:" + pago.getIdpago());
////            System.out.println("fecha:" + pago.getFecha());
////            System.out.println("importe:" + pago.getImporte());
////            System.out.println("forma:" + pago.getForma());
////            System.out.println("idsalida:" + pago.getIdsalida());
//            if ( pago != null ) {
//                sesion.saveOrUpdate(pago);
//                System.out.println("guarda pago de Factura...");
//            }
////            if ( pago != null ) {
////                Pagos p = (Pagos) sesion.merge(pago);
////                sesion.save(p);
////                System.out.println("guarda pago de Factura...");
////            }
//            for (Detallees dtll : dt) {
//                sesion.save(dtll);
//                if ( cabec_actualizar == null || cabec_actualizar instanceof Cabecproformas ) {
//                    Repuestos r = dtll.getRepuestos();
//                    r.setStock(r.getStock() - dtll.getCantentregada());
//                    Repuestos rt = (Repuestos) sesion.merge(r);
//                    sesion.update(rt);
//                }
//                sesion.flush();
//                sesion.clear();
//            }
//            tx.commit();
//        } catch (HibernateException e) {
//            tx.rollback();
//            e.printStackTrace();
//            System.out.println("Excepcion(Guardar_ActualizarDoc):" + e.getMessage());
//            return false;
//            
//        } finally {
//            sesion.close();
//        }
//        return true;
//    }
    /**
     * *************** Metodo para guardar factura/boleta y ademas actualizar
     * proforma o SK
     */
//    public boolean Guardar_ActualizarDoc(Object cabFactura, ArrayList<Detallees> dt, Pagos pago, Object cabec_actualizar) {
    public boolean Guardar_ActualizarDoc(Object cabFactura, ArrayList<Detallees> dt, Pagos pago, List<Cabecproformas> cabec_actualizar) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabFactura);
            
            // Actualizar a cada una de las proformas seleccionadas su cabecera
            if ( cabec_actualizar != null ) {
                for ( Cabecproformas cp : cabec_actualizar ) {
                    if ( cabec_actualizar != null ) {
                        Object cabec_act = sesion.merge(cp);
                        sesion.update(cabec_act);
                    }
                    sesion.flush();
                    sesion.clear();
                }
            }
            // Actualiza a una proforma seleccionada su cabecera
            // if (cabec_actualizar != null) {
            //    Object cabec_act = sesion.merge(cabec_actualizar);
            //    sesion.update(cabec_act);
            // }
            if ( pago != null ) {
//                System.out.println("cabeces:" + pago.getCabeces());
//                System.out.println("id:" + pago.getCabeces().getId());
                System.out.println("tipotra:" + pago.getCabeces().getId().getTipotra());
                System.out.println("tipodoc:" + pago.getCabeces().getId().getTipodoc());
                System.out.println("nroserie:" + pago.getCabeces().getId().getNrorserie());
                System.out.println("nrodocumento:" + pago.getCabeces().getId().getNrodocumento());
                
                Pagos p = (Pagos) sesion.merge(pago);
                sesion.save(p);
//                System.out.println("guarda pago de SK...");
            }
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);
                if ( cabec_actualizar == null || cabec_actualizar instanceof Cabecproformas ) {
                    Repuestos r = dtll.getRepuestos();
                    r.setStock(r.getStock() - dtll.getCantentregada());
                    Repuestos rt = (Repuestos) sesion.merge(r);
                    sesion.update(rt);
                }
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    
    // Guardar SK
    public boolean Guardar_SK(Cabecsalvar cabSK, ArrayList<Detallees> dt, Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
//            // 1° Guardar nuevo registro Cabecsalvar (SK).
            sesion.save(cabSK);
            System.out.println("guarda SK...");
            
            // 2° Guardar pago por SK
            if ( pago != null ) {
                Pagos p = (Pagos) sesion.merge(pago);
                sesion.save(p);
                System.out.println("guarda pago de SK...");
            }
            
            // 3° Guardar detalle de SK
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);
                
                // 4° Actualizar stock para el detalle de SK
                Repuestos r = dtll.getRepuestos();
                System.out.println("idRep:" + r.getIdrepuestoant());
                System.out.println("Repuesto:" + r.getCodrepuesto());
                r.setStock(r.getStock() - dtll.getCantentregada());
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                    
                sesion.flush();
                sesion.clear();
            }
            System.out.println("guarda detalle y actualiza stock de SK...");
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            System.out.println("Excepcion(Guardar_SK):" + e.getMessage());
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean ActualizarDoc(Cabeces cabFactura, ArrayList<Detallees> dt, Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            // 1° Guardar nuevo registro Cabeces (Nueva Factura).
            Cabeces nuevoCabecesGR = (Cabeces) sesion.merge(cabFactura);
            sesion.save(nuevoCabecesGR);
            
            // 2° Guardar pago generado por factura.
            if ( pago != null ) {
                Pagos p = (Pagos) sesion.merge(pago);
                sesion.save(p);
            }

            // 3° Crear nuevos Detallees de Factura generada a partir de GR (NUEVOS Detallees para Factura).
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
            
        } catch ( HibernateException e ) {
            System.out.println("Excepcion(DocumentosDAO-ActualizarDoc):" + e.getMessage());
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean EliminarFacturaFicticia(Cabeces cabFictGR, List<Detallees> lstDetFictGR) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
            
        try {
            // 1° Eliminar Detallees de Factura Ficticia encontrado para "cabFicticiaGR" ("tipotra", "tipodoc", "nrorserie", "nrodocumento").
            for( int i = 0; i < lstDetFictGR.size(); i++ ) {
                Detallees detFictGR = lstDetFictGR.get(i);
                Detallees d = (Detallees) sesion.merge(detFictGR);
                
                sesion.delete(d);
                sesion.flush();
                sesion.clear();
            }
            
            // 2° Eliminar Cabeces de Factura Ficticia con "cabFicticiaGR".
            Cabeces c = (Cabeces) sesion.merge(cabFictGR);
            sesion.delete(c);
            tx.commit();
            
        } catch( Exception ex ) {
            System.out.println("Excepcion : " + ex.getMessage());
            tx.rollback();
            ex.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean GenerarGRXConsignacion(Cabeces cabFactFicticia, ArrayList<Detallees> lstDet) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
            
        try {
            // 1° Guardar Cabeces (Factura Ficticia para GR x consignación).
            Cabeces cabGR = (Cabeces) sesion.merge(cabFactFicticia);
            sesion.save(cabGR);

            // 2° Guardar Detallees de Factura Ficticia.
            for ( Detallees dtll : lstDet ) {
                sesion.save(dtll);
                
                // 3° Actualizar Stock (Salida por generaciónn de GR x consignación).
                Repuestos r = dtll.getRepuestos();
                System.out.println(">>> idRep : " + r.getId());
                System.out.println(">>> Repuesto : " + r.getCodrepuesto());
                System.out.println(">>> Stock(anterior) : " + r.getStock());
                System.out.println(">>> N° guía(detalle) : " + dtll.getNroguia());
                
                r.setStock(r.getStock() - dtll.getCantentregada());
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
            
        } catch ( Exception ex ) {
            System.out.println("Excepcion : " + ex.getMessage());
            tx.rollback();
            ex.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    
    /**
     * ****************************************
     */
    public boolean GuardarProforma(Cabecproformas cabp, ArrayList<Detalleproformas> dt) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabp);
            for ( Detalleproformas dtll : dt ) {
                sesion.save(dtll);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public List getProformaxCliente(String cliente) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        try {
            lista = sesion.createQuery("from Cabecproformas cp "
//                    + " where cp.cabeces.id.nrodocumento is null and  "
                    + " where cp.clientes.nombre like '%" + cliente + "%'").list();
        } catch (HibernateException e) {
            return null;
        }
        return lista;

    }

    public List getlista() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        try {
            lista = sesion.createQuery("from Cabecproformas").list();
        } catch (HibernateException e) {
            return null;
        }
        return lista;
    }

    public List getxCabec(Cabecproformas cabec) {
        return getHibernateTemplate().createQuery("from Detalleproformas d where d.cabecproformas.idcabproforma = "
                + cabec.getIdcabproforma()).list();
    }
    /*
     * Metodo para Guardar Factura/Boleta pagada por letras, tambien actualiza proforma o SK
     */

    public boolean GuardarLetras(Cabeces cabFactura, ArrayList<Detallees> det, ArrayList<Cabeces> cabLetra, Pagos pago,
            Object cabec_actualizar) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabFactura);
            sesion.save(pago);
            if (cabec_actualizar != null) {
                Object cabec_act = sesion.merge(cabec_actualizar);
                sesion.update(cabec_act);
            }
            for (Detallees dtll : det) {
                sesion.save(dtll);
                if (cabec_actualizar == null || cabec_actualizar instanceof Cabecproformas) {
                    Repuestos r = dtll.getRepuestos();
                    r.setStock(r.getStock() - dtll.getCantentregada());
                    Repuestos rt = (Repuestos) sesion.merge(r);
                    sesion.update(rt);
                }
                sesion.flush();
                sesion.clear();
            }
            for (Cabeces cabL : cabLetra) {
                sesion.save(cabL);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public List getxCabW(Cabecweb id) {
//        return getHibernateTemplate().createQuery("from Pedidoweb p where p.cabecweb.idCabweb = "
//        + id.getIdCabweb()).list();
        return getHibernateTemplate().createSQLQuery("select * from Pedidoweb where id_Cabweb = "
                + id.getIdCabweb()).addEntity(Pedidoweb.class).list();
    }
    
    public List<Detallees> getxCabGuia(Cabeces guia) {
        return getHibernateTemplate().createQuery("from Detallees det where det.nroguia = " + guia.getNroguia()).list();
    }
    
    public List<Detallees> getxCabGuia(int nroGuia) {
        return getHibernateTemplate().createQuery("from Detallees det where det.nroguia = " + nroGuia).list();
    }


    public List listarPorClienteNoProces(int idCliente) {
        try {
            List lista = getHibernateTemplate().createSQLQuery("select c.* from cabecsalvar as c left join pagos as p"
                    + " on c.idsalida = p.idsalida "
                    + " where "
                    + " c.tipotra is null and c.tipodoc is null and c.nrorserie is null and c.nrodocumento is null"
                    + " and p.idsalida is null and c.idcliente = " + idCliente).list();
            return lista;
        } catch (HibernateException e) {
            return null;
        }
    }

    public Cabecsalvar getCabecSalvar(int id) {
        return (Cabecsalvar) getHibernateTemplate().createQuery("from Cabecsalvar where idsalida = " + id).uniqueResult();
    }

    public Cabecsalvar getCabecSalvarCodSal(int id) {
        return (Cabecsalvar) getHibernateTemplate().createQuery("from Cabecsalvar where codigosalida = " + id).uniqueResult();
    }
    
    public Cabecproformas getCabecProforma(String codigoCabProforma) {
        return (Cabecproformas) getHibernateTemplate().createQuery(
                "from Cabecproformas where codigocabproforma = '" + codigoCabProforma + "'").uniqueResult();
    }
    
    public Cabecproformas getIdCabecProforma(int idCabProforma) {
        return (Cabecproformas) getHibernateTemplate().createQuery(
                "from Cabecproformas where idcabproforma = " + idCabProforma).uniqueResult();
    }
    
    public Cabecweb getCabecWeb(int id) {
        return (Cabecweb) getHibernateTemplate().createQuery("from Cabecweb where idCabweb = " + id).uniqueResult();
    }

    public boolean Actualizar_CabecSalvar(Cabecsalvar c, Cabeces cabFactura, List<Detallees> detalles) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabFactura);
            Cabecsalvar cbs = (Cabecsalvar) sesion.merge(c);
            cbs.setCabeces(cabFactura);
            sesion.update(cbs);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean Actualizar_CabecSK(Cabecsalvar c) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.beginTransaction();
            sesion.update(c);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean GuardarLetras_Canje(ArrayList<Cabeces> letras, ArrayList<Pagos> pagos) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (Pagos p : pagos) {
                sesion.save(p);
                sesion.flush();
                sesion.clear();
            }
            for (Cabeces cabL : letras) {
                sesion.save(cabL);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    
    // Para Canje de letras por nuevas letras (las primeras letras se convierten en protestadas)
    public boolean GuardarLetras_Canje(ArrayList<Cabeces> letras, ArrayList<Pagos> pagos, List<Cabeces> lstCabeces) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for ( Pagos p : pagos ) {
                sesion.save(p);
                sesion.flush();
                sesion.clear();
            }
//            System.out.println("N° letras como resultado del canje: " + letras.size());
            for ( Cabeces cabL : letras ) {
                sesion.save(cabL);
                sesion.flush();
                sesion.clear();
            }
            
            // Actualizar estado de "Protesto" = "PR" en letras originales (que fueron canjeadas por nuevas letras durante canje)
//            System.out.println("N° letras seleccionadas originalmente para canje: " + lstCabeces.size());
            for ( Cabeces cab : lstCabeces ) {
                cab.setEstado("PROTESTADA");
                Cabeces cabAct = (Cabeces) sesion.merge(cab);
                sesion.update(cabAct);
                
//                Pagos pagoProtesto = new Pagos();
//                pagoProtesto.setCabeces(cabAct);
                
//                Date hoy = new Date();
////                System.out.println("hoy::" + hoy);
//                pagoProtesto.setFecha(hoy);
//                pagoProtesto.setImporte(cabAct.getTotal());
//                pagoProtesto.setForma(null); // C, E
//                sesion.save(pagoProtesto);
//                sesion.flush();
//                sesion.clear();
            }
            tx.commit();
            
        } catch ( HibernateException e ) {
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean GuardarLetras_Varias(ArrayList<Cabeces> letras) {

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for (Cabeces cabL : letras) {
                sesion.save(cabL);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }

    public boolean ActualizarSK(Cabecsalvar cs) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            Cabecsalvar cabsalv = (Cabecsalvar) ses.merge(cs);
            ses.update(cabsalv);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            ses.close();
        }
    }
    
    public boolean ActualizarSK_Canje(Cabecsalvar sal, Pagos pago) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            //for (Pagos p : pagos) {
            Cabecsalvar cabsalv = (Cabecsalvar) sesion.merge(sal);

            sesion.update(cabsalv);
            sesion.flush();
            sesion.clear();
            //}
            //for (Cabeces cabL : letras) {
            sesion.save(pago);
            sesion.flush();
            sesion.clear();
            // }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public List getProformaxNumFactura(String numeroFactura) {
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        
        String filtro = "";
        if ( numeroFactura.length() != 0 ) {
            filtro = " where cp.codigocabproforma like '%" + numeroFactura + "%'";
//            filtro = " where cp.nrofactura like '%" + numeroFactura + "%'";
        }
        
        try {
            lista = sesion.createQuery(" from Cabecproformas cp " + filtro).list();
            
        } catch (HibernateException e) {
            return null;
        }
        return lista;

    }
}
