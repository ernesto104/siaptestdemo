package Servicios;

import Entidades.Repuestos;
import Mantenimiento.EquiposDAO;
import Mantenimiento.MaestrosDAO;
import Mantenimiento.ModelosDAO;
import Presentacion.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;

/**
 *
 * @author usuario
 */
public class Servicio_Maestros {

    MaestrosDAO maestrosDAO;
    EquiposDAO equiposDAO;
    ModelosDAO modelosDAO;
    
    FREP001 iu;
    DefaultTableModel modelo;
    int ultimo_id;
    private String roltemporal;
    private String rolxxx;

    public Servicio_Maestros() {

        maestrosDAO = new MaestrosDAO();

    }

    public Servicio_Maestros(FREP001 iu) {

        maestrosDAO = new MaestrosDAO();
        this.iu = iu;
    }

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }
    
    public List BuscarRepMaestro(int tipo, String busq) {
        switch (tipo) {
            case 0:
                return maestrosDAO.xNroParteMaestro(busq);
            case 1:
                return maestrosDAO.xDescripcionMaestro(busq);
            case 2:
                return maestrosDAO.xModeloMaestro(busq);
//            case 3:
//                return maestrosDAO.Getx_CodSec(busq);
            case 3:
                return maestrosDAO.xCodPListaMaestro(busq);
            case 4:
                return maestrosDAO.xAplicacion1(busq);
            case 5:
                return maestrosDAO.xCodigosec(busq);
            case 6:    
                return maestrosDAO.xNroParteMaestro2(busq);                                    
            case 7:
                return maestrosDAO.xCodigosec2(busq);                
            case 8:
                return maestrosDAO.xDescripcionMaestro2(busq);
            case 9:
                return maestrosDAO.xAplicacion2(busq);
            case 10:
                return maestrosDAO.xEquipoMaestro(busq);
            case 11:
                return maestrosDAO.xMarcaMaestro(busq);
            case 12:
                return maestrosDAO.xModeloDeEquipoMaestro(busq);
            case 13:
                return null;
            default:
                return null;
        }
    }

    public List BuscarRep_por_(int tipo, String busq) {
        switch (tipo) {
            case 0:
                return maestrosDAO.Getx_NroParte(busq);
            case 1:
                return maestrosDAO.Getx_Descripcion(busq);
            case 2:
                return maestrosDAO.Getx_DescrModelo(busq);
            case 3:
                return maestrosDAO.Getx_CodSec(busq);
            case 4:
                return maestrosDAO.Getx_Equipo(busq);
            case 5:
                return maestrosDAO.Getx_Marca(busq);
            case 6:
                return maestrosDAO.Getx_Modelo(busq);    
//            case 3:
//                return maestrosDAO.Getx_Cod_PrecLista(busq);                
            default:
                return null;
        }
    }
    
    public List BuscarRep_por_2(int tipo, String busq) {
        switch (tipo) {
            case 0:
                return maestrosDAO.Getx_NroParte2(busq);
            case 1:
                return maestrosDAO.Getx_CodSec(busq);
            case 2:
                return maestrosDAO.Getx_Descripcion2(busq);
            default:
                return null;
        }
    }

    public Repuestos getRepuesto_CodRep(String cod) {
//        System.out.println(">>getRepuesto_CodRep");
        return maestrosDAO.GetRepuesto_Codigo(cod);
        
        // Con esto se cae el Mantenimiento de Repuestos(org.hibernate.LazyInitializationException: could not initialize proxy - no Session)
        // return maestrosDAO.GetRepuesto_CodigoSinNull(cod);
    }

    public Repuestos GetRepuesto_Codigo(String codigo) {
//        System.out.println(">>GetRepuesto_Codigo");
        return maestrosDAO.GetRepuesto_Codigo(codigo);
    }

    public Long getSize() {
        return maestrosDAO.Tamaño_Lista();
    }

    public Repuestos getRepuestos(int id) {
        return maestrosDAO.Obtener_Objeto(id);
    }
    
    public Repuestos GetRepuesto_Id(int id) {
        return maestrosDAO.GetRepuesto_idrepuesto(id);
    }

//    public Repuestos getRepuestos(RepuestosId id) {
//        Repuestos repuestos;
//        repuestos = maestrosDAO.Obtener_Objeto(id);
//        return repuestos;
//    }

    public boolean addRepuestos(Repuestos r) {
        //System.out.println(maestrosDAO.Agregar_Objeto(r));
        return maestrosDAO.Agregar_Objeto(r);

    }

    public List GetRepuestos_StockMin() {
        return maestrosDAO.GetRep_Stock_Min();
    }

//    public int listarRepuestos(DefaultTableModel modelo) {
//        this.modelo = modelo;
//        List<Repuestos> listaRepuestos = maestrosDAO.Obtener_Lista_Objetos();
//                
//        for (int i = 0; i < listaRepuestos.size(); i++) {
//            if (i == (listaRepuestos.size() - 1)) {
//                ultimo_id = listaRepuestos.get(i).getId().getIdrepuesto();
//            }
//            int id = listaRepuestos.get(i).getId().getIdrepuesto();
//            
//            String codrep = listaRepuestos.get(i).getCodrepuesto();
//            String codseg = listaRepuestos.get(i).getCodigoseg();
//            String desc = listaRepuestos.get(i).getDescripcion();
//            int stock = listaRepuestos.get(i).getStock();
//            Double precl = listaRepuestos.get(i).getPreciolista();
//            Double costprom = listaRepuestos.get(i).getCostopromedio();
//            Double costult = listaRepuestos.get(i).getPcostoultimo();
//            Double pcosttemp = listaRepuestos.get(i).getPcostotemporal();
//            
//            Object[] lineas = {codrep, codseg, desc, stock, precl, costprom, costult, pcosttemp};
//            modelo.addRow(lineas);
//        }
//        return listaRepuestos.size();
//    }
    
    // Método optimizado
    public int listarRepuestos(DefaultTableModel modelo) {
        this.modelo = modelo;
        
        List listaRepuestos = (ArrayList) maestrosDAO.Obtener_Repuestos();
            
        Iterator it = listaRepuestos.iterator();
        
        while ( it.hasNext() ) {
            Object[] result = (Object[]) it.next();
            //System.out.print(result[11]);
            ultimo_id = Integer.parseInt(String.valueOf(result[0]));
            String codrep = String.valueOf(result[1]);
            String codseg = String.valueOf(result[2]);            
            String desc = String.valueOf(result[3]);
            String marca = String.valueOf(result[7]);
            String equipo = String.valueOf(result[11]);
            String modelodescr = String.valueOf(result[12]);
            /*int idequipo = (int) (result[11]);
            int idmodelo = (int)(result[12]);*/
            
            /*equiposDAO = new EquiposDAO();
            String equipo = equiposDAO.Obtener_NombreObjeto_por_codigo(idequipo);
            
            modelosDAO = new ModelosDAO();
            String modelodescr = modelosDAO.Obtener_NombreObjeto_por_codigo(idmodelo);  */       
            
            String aplicacion1 = "";
            //String codseg = "";
            if ( result[4] != null ) {
                //codseg = String.valueOf(result[2]);
                aplicacion1 = String.valueOf(result[4]);
            }
            
            String anotacion1 = "";
            if ( result[5] != null ) {
                anotacion1 = String.valueOf(result[5]);
            }
            
            int stock = Integer.parseInt(String.valueOf(result[6]));
            Double precl = Double.parseDouble(String.valueOf(result[10]));
            
//            Double costprom = ( result[6] == null ) ? 0.00 : Double.parseDouble(String.valueOf(result[6]));
//          String costprom = ( result[9] == null ) ? "" : String.valueOf(result[9]);
            
            Double costult = 0.00;
            if ( result[9] != null ) {
                costult = Double.parseDouble(String.valueOf(result[9]));
            }
            if ("ADMINISTRADOR".equals(roltemporal)){            
                costult = 0.00;
                result[9] = 0;
            }            
            
            Double fob = 0.00;
            if ( result[8] != null ) {
                fob = Double.parseDouble(String.valueOf(result[8]));
            }
            
            Object[] lineas = {ultimo_id,equipo, marca, modelodescr,codrep, desc,
 
                               stock, fob, costult, precl};

            modelo.addRow(lineas);
        }
        return listaRepuestos.size();
    }

// Esto es para usuarios No Administradores
    public int listarRepuestos2(DefaultTableModel modelo) {
        this.modelo = modelo;
        
        List<Object> listaRepuestos = (ArrayList) maestrosDAO.Obtener_Repuestos2();
            
        Iterator it = listaRepuestos.iterator();
        while ( it.hasNext() ) {
            Object[] result = (Object[]) it.next();
            ultimo_id = Integer.parseInt(String.valueOf(result[0]));
            String codrep = String.valueOf(result[1]);
            String codseg = String.valueOf(result[2]);            
            String desc = String.valueOf(result[3]);
            String marca = String.valueOf(result[7]);
            String equipo = String.valueOf(result[11]);
            String modelodescr = String.valueOf(result[12]);
            /*int idequipo = (int) (result[11]);
            int idmodelo = (int)(result[12]);
            
            equiposDAO = new EquiposDAO();
            String equipo = equiposDAO.Obtener_NombreObjeto_por_codigo(idequipo);
            
            modelosDAO = new ModelosDAO();
            String modelodescr = modelosDAO.Obtener_NombreObjeto_por_codigo(idmodelo);*/
            
            
            String aplicacion1 = "";
            //String codseg = "";
            if ( result[4] != null ) {
                //codseg = String.valueOf(result[2]);
                aplicacion1 = String.valueOf(result[4]);
            }
            
            String anotacion1 = "";
            if ( result[5] != null ) {
                anotacion1 = String.valueOf(result[5]);
            }
            
            int stock = Integer.parseInt(String.valueOf(result[6]));
            Double precl = Double.parseDouble(String.valueOf(result[10]));
            
//            Double costprom = ( result[6] == null ) ? 0.00 : Double.parseDouble(String.valueOf(result[6]));
//          String costprom = ( result[9] == null ) ? "" : String.valueOf(result[9]);
            
            Double costult = 0.00;
            if ( result[9] != null ) {
                costult = Double.parseDouble(String.valueOf(result[9]));
            }

            costult = 0.00;
            result[9] = 0;

            
            Double fob = 0.00;
            if ( result[8] != null ) {
                fob = Double.parseDouble(String.valueOf(result[8]));
            }
            fob = 0.00;
            result[8] = 0;
            
            Object[] lineas = {ultimo_id, equipo,marca, modelodescr,codrep, desc,
                               //anotacion1, aplicacion1, 
                               stock, 0, 0, precl};

            modelo.addRow(lineas);
        }
        return listaRepuestos.size();
    }    
    
    public boolean actualizarRepuestos(Repuestos r) {
        return maestrosDAO.Modificar_Objeto_Merge(r);
        //return maestrosDAO.Modificar_Objeto(r);
    }

    
//    public boolean actualizaRepuesto(Repuestos rep) {
//
//        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sesion.beginTransaction();
//        try {
//            getHibernateTemplate().update(rep);
//            sesion.flush();
//            sesion.clear();
//            tx.commit();
//        } catch (HibernateException e) {
//            tx.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            sesion.close();
//        }
//        return true;
//    }

    public Session getHibernateTemplate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        session.beginTransaction();
        return session;
    }

    public boolean borrarRepuestos(Repuestos r) {
        return maestrosDAO.Eliminar_Objeto(r);
    }

    public int nextId() {
        return (int) maestrosDAO.nextId();
    }

    public Repuestos getRepuestos_por_nombre(String nombre) {
        Repuestos re = new Repuestos();
        re = maestrosDAO.Obtener_Objeto_por_nombre(nombre);
        return re;
    }

//    public List getRepuestos_conStock() {
//        return maestrosDAO.Repuestos_conStock();
//    }
    
    // Método optimizado
    public List getRepuestos_conStock() {
        return maestrosDAO.conStock();
    }

//    public List getList() {
//        return maestrosDAO.Obtener_Lista_Objetos();
//    }
    
    // Método optimizado
    public List getList() {
        return maestrosDAO.Get_Lista_Repuestos();
    }
    
    public List getList2() {
        return maestrosDAO.Get_Lista_Repuestos2();
    }

    /**
     * ***************************************************************
     */
    public List getxId(int id) {
        return maestrosDAO.GetxId(id);
    }

    public List getxDesc(String desc) {
        return maestrosDAO.GetxDesc(desc);
    }

    public List getxModel(String desc) {
        return maestrosDAO.GetxModel(desc);
    }

    /**
     * ******
     */
    public List Consultar_Repuestos(String cod_estrtificacion, int tipo_busq, String busqueda) {
        switch (tipo_busq) {
            case 0:
                return maestrosDAO.Consulta_Repuestos_NroParte(cod_estrtificacion, busqueda);
            case 1:
                return maestrosDAO.Consulta_Repuestos_Descripcion(cod_estrtificacion, busqueda);
        }
        return null;
    }

    /**
     * ***** Para caso de Uso Listar Precios ************************
     */
    public List ListarRepuestos_Opciones(int tipo, String opcion, int stock) {
        String consulta = "from Repuestos r ";
        if (tipo != 4) {
            switch (tipo) {
                case 1:
                    consulta += " where r.lineas.descripcion = '" + opcion + "'";
                    break;
                case 2:
                    consulta += " where r.descripcion like '%" + opcion + "%'";
                    break;
                case 3:
                    consulta += " where r.marca like '%" + opcion + "%'";
                    break;
            }
        }
        if (stock == 1) {
            if (tipo == 4) {
                consulta += " where ";
            } else {
                consulta += " and ";
            }
            consulta += "r.stock > 0";
        }
        consulta += " order by r.descripcion asc, r.descrmodelo asc";
//        System.out.println("consulta:" + consulta);
        return maestrosDAO.GetRep_ListaOpciones(consulta);
    }

    public List FiltrarLista_Repuestos(int tipo, String opcion, int stock,
                                       String nroparte, String descripcion, String marca) {
        
        tipo = tipo +1;
        
        String consulta = "from Repuestos r where ";
        
        if ( tipo != 4 ) {
            switch (tipo) {
                case 1:
                    consulta += " r.lineas.descripcion = '" + opcion + "' and ";
                    break;
                case 2:
                    consulta += " r.descripcion like '%" + opcion + "%' and ";
                    break;
                case 3:
                    consulta += " r.descripcion like '%" + opcion + "%' and ";
                    break;
            }
        }
        if ( stock == 1 ) {
            if ( tipo == 4 ) {
                consulta += "  r.stock > 0 and ";
                
            } else {
                consulta += " and r.stock > 0 and ";
            }
        }
        consulta += " r.codrepuesto like '%" + nroparte + "%' "
                + " and r.descripcion like '%" + descripcion + "%' "
                + " and r.marca like '%" + marca + "%'";
        consulta += " order by r.descripcion asc, r.descrmodelo asc";

//        System.out.println("consulta:" + consulta);
        return maestrosDAO.GetRep_ListaOpciones(consulta);
    }
}
