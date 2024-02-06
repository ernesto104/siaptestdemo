package Servicios;

import Entidades.Sistema;
import Mantenimiento.SistemaDAO;
import Presentacion.FREP014;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 */
public class Servicio_Sistema {

    private SistemaDAO dao;
    FREP014 sis;

    public Servicio_Sistema(FREP014 sis) {
        dao = new SistemaDAO();
        this.sis = sis;
    }

    public Servicio_Sistema() {
        dao = new SistemaDAO();
    }

    public void listar() {
        DefaultTableModel table = (DefaultTableModel) sis.tablaSistema.getModel();
        Iterator ite = dao.listarSistemas().iterator();
        
        while ( ite.hasNext() ) {
            Object[] row = new Object[6];
            Sistema sist = (Sistema) ite.next();
            row[0] = sist.getTipodoc();
            row[1] = sist.getDescripcion();
            row[2] = sist.getOperaciones().getCodigooperacion();
            row[3] = sist.getSerie();
            row[4] = sist.getUltimonumero();
            row[5] = sist.getTransaccion();
            table.addRow(row);
        }
    }
    
    public Sistema Obtener_Sistema_TipoDoc(String TipoDoc){
        return dao.Obtener_Sistema_TipoDoc(TipoDoc);
    }   
    
    public Integer obtieneNumeroSerie() {
        return dao.obtieneNumeroSerie();
    }

    public Integer obtieneNumeroDocumento() {
        return dao.obtieneNumeroDocumento();
    }

    public void actualizar() {
        dao.actualizar();
    }

    public int nextId() {
        return (int) dao.nextId();
    }

    public boolean addSistema(Sistema s) {
        return dao.Agregar_Objeto(s);
    }

    public Sistema getSistemas(int id) {
        Sistema sistema;
        sistema = dao.Obtener_Objeto(id);
        return sistema;
    }

    public Sistema getSistemas_por_nombre(String id) {
        Sistema s = new Sistema();
        s = dao.Obtener_Objeto_por_nombre(id);
        return s;
    }
    
    public Sistema getSistemas_por_descripcion(String id) {
        Sistema s = new Sistema();
        s = dao.obtener_por_nombre(id);
        return s;
    }

    public boolean borrarSistemas(Sistema t) {
        return dao.Eliminar_Objeto(t);
    }

    public boolean actualizarSistemas(Sistema t) {        
        return dao.Modificar_Objeto(t);
    }
    
    public Sistema obtener_por_nombre(String nombre) {
        return dao.obtener_por_nombre(nombre);
    }
}