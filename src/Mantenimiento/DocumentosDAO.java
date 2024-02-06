package Mantenimiento;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecproformas;
import Entidades.Cabecsalvar;
import Entidades.Cabecweb;
import Entidades.Detallees;
import Entidades.Detallenota;
import Entidades.Detalleproformas;
import Entidades.Repuestos;
import Entidades.Sistema;
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
public class DocumentosDAO extends GenericDAO<Cabecproformas> {

    public Cabeces obtenerCabecFacturaPorDocumento(String numeroDocumento, String tipoDocumento) {
        CabecesId cabecEsId = new CabecesId();
        Cabeces cabecEs = new Cabeces();

        CabecesIdDAO cabecEsIdDAO = new CabecesIdDAO();
        CabecesDAO cabecEsDAO = new CabecesDAO();

        cabecEsId = cabecEsIdDAO.obtenerCabeceraID(tipoDocumento, numeroDocumento);
        cabecEs = cabecEsDAO.obtenerCabeceraPorDocumento(cabecEsId);

        return cabecEs;
    }
    
    public Cabeces obtenerCabecFacturaPorDocumento(String tipoTransaccion, String tipoDocumento, String numeroSerie, String numeroDocumento) {
        CabecesId cabecEsId = new CabecesId();
        Cabeces cabecEs = new Cabeces();

        CabecesIdDAO cabecEsIdDAO = new CabecesIdDAO();
        CabecesDAO cabecEsDAO = new CabecesDAO();

        cabecEsId = cabecEsIdDAO.obtenerCabeceraID(tipoTransaccion, tipoDocumento, numeroSerie, numeroDocumento);
        cabecEs = cabecEsDAO.obtenerCabeceraPorDocumentoX4Claves(cabecEsId);

        return cabecEs;
    }

    public boolean GuardarFactura(Object cabFactura, ArrayList<Detallees> dt) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabFactura);
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);
                Repuestos r = dtll.getRepuestos();
                r.setStock(r.getStock() - dtll.getCantentregada());
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
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

    public boolean GuardarFactura(Cabeces cab, ArrayList<Detallees> dt) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cab);
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);
                Repuestos r = dtll.getRepuestos();
                r.setStock(r.getStock() - dtll.getCantentregada());
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
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

    public boolean GuardarFacturaSV(Cabeces cab, ArrayList<Cabecsalvar> listaSalvar) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();;
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cab);
            for ( Cabecsalvar c : listaSalvar ) {
                sesion.update(sesion.merge(c));
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

    public boolean GuardarNotas(Cabeces cab, ArrayList<Detallenota> dt, Sistema sistema, boolean actualizarSistema) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();;
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.merge(cab);
            for ( Detallenota dtll : dt ) {
                sesion.merge(dtll);

                //No devolución (2° Tipo de Nota de Crédito: DESCUENTO ESPECIAL)
                if ( dtll.getCantidad() == 0 ) {
                    
                } else {//devolución (1° Tipo de Nota de Crédito: DEVOLUCIÓN)

                    System.out.println("Esta linea esta actualizando el total y no el parcial : " + dtll.getCantidad());
                    
                    Repuestos r = dtll.getRepuestos();                    
                    r.setStock(r.getStock() + dtll.getCantidad());
                    Repuestos rt = (Repuestos) sesion.merge(r);
                    sesion.update(rt);
                    sesion.flush();
                    sesion.clear();
                }
            }
            
            // NUEVO N° DE DOCUMENTO(ÚLTIMO NÚMERO) A ACTUALIZAR EN SISTEMA
            if ( actualizarSistema ) {
                sesion.merge(sistema);
                System.out.println("ACTUALIZAR SISTEMA con N° documento : " + sistema.getUltimonumero());
                
            } else {
                System.out.println("NO actualizar sistema, pues ultimoN° es:" + sistema.getUltimonumero());
            }
//            sesion.merge(sistema);
            tx.commit();
            
        } catch ( HibernateException e ) {
            tx.rollback();
            System.out.println("Error(DocumentoDAO):" + e.getMessage());
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }

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
            
        } catch ( HibernateException e ) {
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
            lista = sesion.createQuery("from Cabecproformas cp where cp.clientes.empresa like '" + cliente + "%'").list();
            
        } catch ( HibernateException e ) {
            return null;
        }
        return lista;
    }

    public List getlistaProformas() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cabecproformas> lista;
        try {
            lista = sesion.createQuery("from Cabecproformas").list();
            
        } catch ( HibernateException e ) {
            return null;
        }
        return lista;
    }

    public List getxCabecProforma(Cabecproformas cabec) {
        return getHibernateTemplate().createQuery("from Detalleproformas d where d.cabecproformas.idcabproforma = "
                + cabec.getIdcabproforma()).list();
    }

    public boolean AnularDoc_ConCabec(Cabeces cab, ArrayList<Detallees> dt, boolean movimient, boolean aument) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            if ( cab != null ) {
                Cabeces c = (Cabeces) sesion.merge(cab);
                sesion.update(c);
            }
            for ( Detallees dtll : dt ) {
                sesion.save(dtll);  

                if ( movimient == true ) {
                    Repuestos r = dtll.getRepuestos();
                    
                    if ( aument == true ) {
                        r.setStock(r.getStock() + dtll.getCantentregada());
                        Repuestos rt = (Repuestos) sesion.merge(r);
                        sesion.update(rt);

                    } else {            
                        r.setStock(r.getStock() - dtll.getCantentregada());
                        Repuestos rt = (Repuestos) sesion.merge(r);
                        sesion.update(rt);
                    }
                    sesion.flush();
                    sesion.clear();
                }
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

    public List getxCabec(Cabecproformas cabec) {
        return getHibernateTemplate().createQuery("from Detalleproformas d where d.cabecproformas.idcabproforma = "
                + cabec.getIdcabproforma()).list();
    }

    public List getxCabW(Cabecweb id) {
        return getHibernateTemplate().createQuery("from Pedidoweb p where p.cabecweb.idCabweb = "
                + id.getIdCabweb()).list();
    }

    public List getxCabGuia(Cabeces guia) {
        return getHibernateTemplate().createQuery("from Cabeces c where c.nroguia = " + guia.getNroguia()).list();
    }

    public List<Cabecsalvar> listarPorClienteNoProces(int idCliente) {
        try {
            List<Cabecsalvar> lista = getHibernateTemplate().createQuery(
                    "from Cabecsalvar where idcliente = " + idCliente
                    + "and (tipotra is null)"
                    + "and (tipodoc is null)"
                    + "and (nrorserie is null)"
                    + "and (nrodocumento is null)").list();
            return lista;
            
        } catch ( HibernateException e ) {
            return null;
        }
    }

    public boolean GuardarLetras(Cabeces cabFactura, ArrayList<Detallees> det, ArrayList<Cabeces> cabLetra) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cabFactura);
            for ( Detallees dtll : det ) {
                System.out.println(dtll.getCabeces().getId().getNrodocumento());
                sesion.save(dtll);
                Repuestos r = dtll.getRepuestos();
                r.setStock(r.getStock() - dtll.getCantentregada());
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }
            
            for ( Cabeces cabL : cabLetra ) {
                sesion.save(cabL);
                sesion.flush();
                sesion.clear();
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

    public boolean Modificar_Objeto_P(Cabecproformas cp) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        try {
            Cabecproformas cpr = (Cabecproformas) sesion.merge(cp);
            sesion.update(cpr);
            tx.commit();
            return true;
            
        } catch ( HibernateException e ) {
            return false;
        }
    }
}