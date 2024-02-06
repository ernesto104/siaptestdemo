package Servicios;

import Entidades.Cabeces;
import Entidades.Cabecproformas;
import Entidades.Cabecsalvar;
import Entidades.Detalleproformas;
import Entidades.Detallees;
import Entidades.Detallenota;
import Entidades.Sistema;
import Mantenimiento.ControlDAO;
import Mantenimiento.DocumentosDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Servicio_Documentos {
    
    Servicios.Servicio_Vendedor sv;
    Servicios.Servicio_Cliente sc;
    Servicios.Servicio_TipoCambio stc;
    Servicios.Servicio_Maestros sm;
    ControlDAO cd ;
    DocumentosDAO DocDAO;
    
    SistemaDAO sisDAO;
    
    public Servicio_Documentos(){
        DocDAO = new DocumentosDAO();
        sv = new Servicio_Vendedor(null);
        sc = new Servicio_Cliente(null);
        cd = new ControlDAO();
        stc = new Servicio_TipoCambio();
        sm =new Servicio_Maestros(null);
        sisDAO = new SistemaDAO();
    }
    
    public boolean guardarFactura(Cabeces cab,ArrayList<Detallees> dt){
        return DocDAO.GuardarFactura(cab, dt);
    }

    public boolean GuardarProforma(Cabecproformas cp, ArrayList<Detalleproformas> dt) {
        return DocDAO.GuardarProforma(cp, dt);
    }
    
    public List getDetalleProf(Cabecproformas c){
        return DocDAO.getxCabecProforma(c);
    }
    
    public List getxCliente(String cliente){
        return DocDAO.getProformaxCliente(cliente);
    }
    
    public Cabecproformas GetxId(int id){
        return DocDAO.Obtener_Objeto(id);
    }

    public Cabeces obtenerCabeceraPorDocumento(String parseInt, String tipoDocumento) {
       return DocDAO.obtenerCabecFacturaPorDocumento(parseInt, tipoDocumento);
    }
    
    public Cabeces obtenerCabeceraPorDocumento(String tipoTransaccion, String tipoDocumento, String numeroSerie, String numeroDocumento) {
       return DocDAO.obtenerCabecFacturaPorDocumento(tipoTransaccion, tipoDocumento, numeroSerie, numeroDocumento);
    }
    
    public boolean GuardarNotas(Cabeces cab,ArrayList<Detallenota> dt, Sistema sistema, boolean actualizarSistema){
        return DocDAO.GuardarNotas(cab, dt, sistema, actualizarSistema);
    }
    
    public boolean anularFactura(Cabeces cab, ArrayList<Detallees> lista, boolean b, boolean m){
        DocumentosDAO d = new DocumentosDAO();
        d.AnularDoc_ConCabec(cab, lista, b, m);
        return true;
    }
    
    public List getListarPorClientesNoProces(int idCliente) {
        return DocDAO.listarPorClienteNoProces(idCliente);
    }
    
    public boolean guardarFacturaSV(Cabeces cab, ArrayList<Cabecsalvar> listaSalvar) {
        return DocDAO.GuardarFacturaSV(cab, listaSalvar);
    }
    
    public boolean actualizarSis(Sistema s) {
        System.out.println("actualizarSis(3)");
        return sisDAO.Modificar_Objeto(s);
    }
}