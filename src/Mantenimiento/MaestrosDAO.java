package Mantenimiento;

import Entidades.Repuestos;
import Entidades.RepuestosId;
import java.util.List;

import org.hibernate.Session;
import Servicios.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *System.out.println("Entro a una fecha....");
 * @author Keily Mendiolaza
 */
public class MaestrosDAO extends GenericDAO<Repuestos> {
    
    String GGG = "Hola....";
    public int nextId() {
        
        Repuestos re = null;
        try {
            re = (Repuestos) getHibernateTemplate().createQuery("from Repuestos e order by e.id.idrepuesto desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if ( Tamaño_Lista() == 0 ) {
            return 1;
        } else {
            return re.getId().getIdrepuesto() + 1;
        }
    }

    public Repuestos GetRepuesto_idrepuesto(int id) {
        
        Repuestos re = null;
        
        try {
            re = (Repuestos) getHibernateTemplate().createQuery(
                "from Repuestos r join fetch r.equipos join fetch r.marcas join fetch r.modelos join fetch r.estratificacion where r.id.idrepuesto = '" + id + "'").uniqueResult();
            /*re = (Repuestos) getHibernateTemplate().createQuery(
                "select DISTINCT r from Repuestos r join fetch r.marcas where r in '" + re + "'").uniqueResult();
            re = (Repuestos) getHibernateTemplate().createQuery(
                "select DISTINCT r from Repuestos r join fetch r.modelos where r in '" + re + "'").uniqueResult();*/
            session.getTransaction().commit();
            } catch (Exception e) {
                System.err.println("Error : " + e.getMessage());
            } finally {
                session.close();
            }
        //System.out.print(re.getEquipos().getDescripcion());
        return re;
    }
    
    public Repuestos GetRepuesto_Codigo(String codigo) {
        return (Repuestos) getHibernateTemplate().createQuery("from Repuestos r where r.codrepuesto like '" + codigo + "'").uniqueResult();
    }

//    public Repuestos GetRepuesto_Codigo(String codigo) {
//        Repuestos repuesto = new Repuestos();
//        try {
//    //        String query = "from Repuestos r where r.codrepuesto like '" + codigo + "'";
//            String query = "from Repuestos r where r.codrepuesto = '" + codigo + "'";
//    //        String query = "select * from Repuestos r where r.codrepuesto like '" + codigo + "'";
//            System.out.println("query:" + query);
//            repuesto = (Repuestos) getHibernateTemplate().createQuery(query).uniqueResult();
//    //        repuesto = (Repuestos) getHibernateTemplate().createSQLQuery(query).uniqueResult();
//            System.out.println("repuesto::" + repuesto);
////            System.out.println("repuesto.getCodRepuesto:" + repuesto.getCodrepuesto());
//            
//        } catch ( Exception e ) {
//            System.out.println("Exception:" + e.getMessage());
//        }
//        return repuesto;
//    }
    
    public Repuestos GetRepuesto_CodigoSinNull(String codigo) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = sesion.beginTransaction();
        Repuestos repuestos = new Repuestos();
        Repuestos r = null;
        
        try {
            repuestos = (Repuestos) sesion.createQuery(
                    "from Repuestos r where r.codrepuesto = '" + codigo + "'").uniqueResult();
            r = (Repuestos) sesion.merge(repuestos);
            
        } catch ( Exception e ) {
            System.err.println("Error : " + e.getMessage());
            
        } finally {
            sesion.close();
        }
        return r;
    }

    public Repuestos Obtener_Objeto_por_nombre(String nombre) {
        Repuestos re = new Repuestos();
        try {
            re = (Repuestos) getHibernateTemplate().createQuery("from Repuestos where nombre='" + nombre + "'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return re;
    }

    public Repuestos Obtener_Objeto(RepuestosId id) {
        Repuestos returnValue = (Repuestos) getHibernateTemplate().load(domainClass, id);
        session.getTransaction().commit();
        return returnValue;
    }

//    public List Repuestos_conStock() {
//        return getHibernateTemplate().createQuery("from Repuestos r where r.stock > 0").list();
//    }
    
    // Método optimizado
    public List conStock() {
        return getHibernateTemplate().createSQLQuery(
//            " select idlinea, codrepuesto, descripcion, descrmodelo, preciolista, stock, unidadventa, costopromedio " +
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg  " +
            " from Repuestos where stock > 0")
            .list();
    }

    ////////////////
    public List Getx_NroParte(String nroparte) {
//        System.out.println("nroParte::::" + nroparte);
//            " select idlinea, codrepuesto, descripcion, descrmodelo, preciolista, stock, unidadventa, costopromedio " +
        String query = " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg " +
            " from Repuestos where codrepuesto like '" + nroparte + "%'";
//        System.out.println("query:" + query);
        List lst = getHibernateTemplate().createSQLQuery(query).list();
//            "from Repuestos r where r.codrepuesto like '" + nroparte + "%'").list();
//        System.out.println("lista:" + lst);
//        if ( lst != null ) { 
//            System.out.println("Longitud de lista:" + lst.size());
//        }
        return lst;
    }
    
    public List Getx_NroParte2(String nroparte) {
        return getHibernateTemplate().createSQLQuery(
            " select codrepuesto, codigoseg, descripcion, aplicacion, preciolista, stock, marca " +
            " from Repuestos where codrepuesto like '%" + nroparte + "%'").list();
//            "from Repuestos r where r.codrepuesto like '" + nroparte + "%'").list();
    }
    
    public List xNroParteMaestro(String nroparte) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos r where codrepuesto like '%" + nroparte + "%'").list();
    }
    public List xNroParteMaestro2(String nroparte) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, 0, 0, preciolista " +
            " from Repuestos where codrepuesto like '%" + nroparte + "%'").list();
    }
    
    public List xEquipoMaestro(String equipo) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos r where r.id.idequipo like '%" + equipo + "%'").list();
    }
    
    public List xMarcaMaestro(String marca) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos r where r.id.idmarca like '%" + marca + "%'").list();
    }
    
    
    public List xModeloDeEquipoMaestro(String modelo) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos r where r.id.idmodelo like '%" + modelo + "%'").list();
    }
    
    public List Getx_CodSec(String codSec) {
      return getHibernateTemplate().createSQLQuery(
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg " +
            " from Repuestos where codigoseg like '%" + codSec + "%'").list();
//        return getHibernateTemplate().createQuery("from Repuestos r where r.codigoseg like '%" + codSec + "%'").list();
    }

    public List Getx_Descripcion(String descripcion) {
        return getHibernateTemplate().createSQLQuery(
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg " +
            " from Repuestos where descripcion like '%" + descripcion + "%'").list();
//            "from Repuestos r where r.descripcion like '%" + descripcion + "%'").list();
    }
    
    public List Getx_Descripcion2(String descripcion) {
        return getHibernateTemplate().createSQLQuery(
            " select codrepuesto, codigoseg, descripcion, aplicacion, preciolista, stock, marca " +
            " from Repuestos where descripcion like '%" + descripcion + "%'").list();
//            "from Repuestos r where r.descripcion like '%" + descripcion + "%'").list();
    }
    
    public List xDescripcionMaestro(String descripcion) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos r where descripcion like '%" + descripcion + "%'").list();
    }
    public List xDescripcionMaestro2(String descripcion) {
        return getHibernateTemplate().createQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, r.equipos.descripcion, marca, r.modelos.descripcion, codrepuesto,  descripcion, stock, 0, 0, preciolista " +
            " from Repuestos r where descripcion like '%" + descripcion + "%'").list();
    }
    
    public List Getx_Modelo(String modelo) {
        return getHibernateTemplate().createSQLQuery(
//            " select idlinea, codrepuesto, descripcion, descrmodelo, preciolista, stock, unidadventa, costopromedio " +
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg  " +
            " from Repuestos where descrmodelo like '%" + modelo + "%'").list();
//            "from Repuestos r where r.descrmodelo like '%" + modelo + "%'").list();
    }
    
    public List xModeloMaestro(String modelo) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, fobultimo, costopromedio, pcostoultimo, preciolista " +
            " from Repuestos where descrmodelo like '%" + modelo + "%'").list();
    }

    public List Getx_Cod_Secundario(String codseg) {
        return getHibernateTemplate().createQuery("from Repuestos r where r.codigoseg like '%" + codseg + "%'").list();
    }
    
    public List Getx_Cod_PrecLista(String plista) {
        return getHibernateTemplate().createSQLQuery(
//            " select idlinea, codrepuesto, descripcion, descrmodelo, preciolista, stock, unidadventa, costopromedio " +  
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa " +  
            " from Repuestos where preciolista like '" + plista + "'").list();
//            " from Repuestos r where r.preciolista = " + plista).list();
    }
    
    public List xCodPListaMaestro(String plista) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select codrepuesto, descripcion, descrmodelo, desclista, stock, preciolista, costopromedio, pcostoultimo, fobultimo " +
            " from Repuestos where preciolista like '%" + plista + "'").list();
    }
    
    public List xAplicacion1(String aplicacion1) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos where descrmodelo like '%" + aplicacion1 + "%'").list();
    }
    public List xAplicacion2(String aplicacion1) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select r.id.idrepuesto, codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, 0, 0, preciolista " +
            " from Repuestos where descrmodelo like '%" + aplicacion1 + "%'").list();
    }
    
    public List xCodigosec(String codigosec) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, fobultimo, pcostoultimo, preciolista " +
            " from Repuestos where codigoseg like '%" + codigosec + "%'").list();
    }
    public List xCodigosec2(String codigosec) {
        return getHibernateTemplate().createSQLQuery(
            //" select codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal " +
            " select codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, 0, 0, preciolista " +
            " from Repuestos where codigoseg like '%" + codigosec + "%'").list();
    }    
    
    
    public List excluirListaItems(String listaCodRepuestos) {
        return getHibernateTemplate().createSQLQuery(
                "select r.codrepuesto, r.descripcion, r.descrmodelo, r.desclista, r.stock, r.preciolista, (r.stock * r.preciolista) as total, " +
                "null, r.idlinea, r.costopromedio, r.preciolista, r.codigoseg " +
                "from repuestos r " +
                "where r.codrepuesto not in (" + listaCodRepuestos + ") " +
                "order by r.codrepuesto").list();
    }
    
    
    public List GetxId(int id) {
        return getHibernateTemplate().createQuery("from Repuestos r where r.id.idrepuesto like '" + id + "%'").list();
    }

    public List GetxDesc(String desc) {
        return getHibernateTemplate().createQuery("from Repuestos r where r.descripcion like '" + desc + "%'").list();
    }

    public List GetxModel(String model) {
        return getHibernateTemplate().createQuery("from Repuestos r where r.descrmodelo like '" + model + "%'").list();
    }

    public List Consulta_Repuestos_NroParte(String cod_estr, String busqueda) {
        return getHibernateTemplate().createQuery("select r.equipos.descripcion,r.codrepuesto,r.descripcion,r.stock,"
                + "r.preciolista,r.marca from Repuestos r where r.codrepuesto like '%" + busqueda + "%'"
                + " and r.estratificacion.codigoestratificacion = '" + cod_estr + "'").list();
    }

    public List Consulta_Repuestos_Descripcion(String cod_estr, String busqueda) {
        return getHibernateTemplate().createQuery("select r.equipos.descripcion,r.codrepuesto,r.descripcion,r.stock,"
                + "r.preciolista,r.marca from Repuestos r where r.descripcion like '%" + busqueda + "%'"
                + " and r.estratificacion.codigoestratificacion = '" + cod_estr + "'").list();
    }

    ////////////////
    public List GetRep_Stock_Min() {
        return getHibernateTemplate().createQuery("select r.codrepuesto,r.codigoseg,"
                + "r.descripcion, r.descrmodelo, r.marca, r.stock,r.stockminimo from Repuestos r where r.stock <= r.stockminimo and r.stockminimo > 0").list();
    }

    /**
     * ******************* para CUS Lista de Precios ***************
     */
    public List GetRep_ListaOpciones(String Consulta) {
        return getHibernateTemplate().createQuery(Consulta).list();
    }

    public List Filtrar_Repuestos_por(String nroparte, String descripcion, String marca) {
        return getHibernateTemplate().createQuery(" from Repuestos r where r.codrepuesto like '%" + nroparte + "%' "
                + " and r.descripcion like '%" + descripcion + "%' "
                + " and r.marca like '%" + marca + "%'").list();
    }
    
    public List repuestosOrdenadosPorLinea() {
//        System.out.println("repuestosOrdenadosPorLinea...");
        return getHibernateTemplate().createQuery(" from Repuestos  order by descripcion asc , descrmodelo asc "
                                                  + ", marca asc").list();
//        return getHibernateTemplate().createQuery("from Repuestos r order by r.lineas.idlinea asc "
//                                                  + ", r.descrmodelo asc").list();
    }
    
    public List Obtener_Repuestos() {
            return getHibernateTemplate().createQuery(
            //" select idrepuesto, codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal from repuestos ")
            " select r.id.idrepuesto, r.codrepuesto, r.codigoseg, r.descripcion, r.descrmodelo, r.desclista, r.stock, r.marca, r.fobultimo, r.pcostoultimo, r.preciolista,"
                    + " r.equipos.descripcion, r.modelos.descripcion from Repuestos r")
            .list();
    }
    
    
    public List Obtener_Repuestos2() {
            return getHibernateTemplate().createQuery(
            //" select idrepuesto, codrepuesto, codigoseg, descripcion, stock, preciolista, costopromedio, pcostoultimo, pcostotemporal from repuestos ")
            " select r.id.idrepuesto, codrepuesto, codigoseg, descripcion, descrmodelo, desclista, stock, marca, 0, 0, preciolista, "
                    + "r.equipos.descripcion, r.modelos.descripcion from Repuestos r")
            .list();
    }    
    
    public List Get_Lista_Repuestos() {
        System.out.println("Get_Lista_Repuestos....");
        return getHibernateTemplate().createSQLQuery(
//            " select idlinea, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa " +
            " select idequipo, codrepuesto, descripcion, descrmodelo, preciolista, costopromedio, stock, unidadventa, codigoseg " +
            " from repuestos ")
            .list();
    }
    
    public List Get_Lista_Repuestos2() {
        return getHibernateTemplate().createSQLQuery(
            " select codrepuesto, codigoseg, descripcion, aplicacion, preciolista, stock, marca " +
            " from repuestos ")
            .list();
    }
    
    public List listar_RepuestosExceptuandoListaRepuestosMovidos(String lstCodigosRepuestos) {
        String consulta
                = "select r.codrepuesto, r.descripcion, r.descrmodelo, r.desclista, r.stock, "
                + "r.preciolista, (r.stock * r.preciolista) as total, "
                + "'', '', '', r.idlinea, r.costopromedio, "
                + "r.preciolista, r.codigoseg, r.aplicacion, r.desclista2 "
                + "from repuestos r " 
                + "where r.codrepuesto not in (" + lstCodigosRepuestos + ") "
                // Formato de "lstCodigosRepuestos": '04111-55030', '04111-72010', '45503-29435 LG', '76155-2', '76184', '76398' "
                + "order by r.codrepuesto";
        
        System.out.println("consulta:" + consulta);
        return getHibernateTemplate().createSQLQuery(consulta).list();
    }
}