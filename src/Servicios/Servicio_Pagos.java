package Servicios;


import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Pagos;
import Mantenimiento.PagosDAO;
import java.util.List;


/**
 *
 * @author Keily
 */
public class Servicio_Pagos {

    PagosDAO dao;

    public Servicio_Pagos() {
        dao = new PagosDAO();
    }

    public List<Pagos> listarPagosxCabecera(CabecesId cab) {
        return dao.listarPagosxCabecera(cab);
    }
    
    public List<Pagos> listarPagosxCabSalVar(int idSalida) {
        return dao.listarPagosxCabeceSal(idSalida);
    }

    public boolean ActualizaCab(Cabeces cab) {
        return dao.ActualizarCab(cab);
    }
    
    public boolean GuardarPagos(Pagos pago) {
        return dao.GuardarPagos(pago);
    }

    public boolean modificarPagos(Pagos pago) {
        return dao.modificarPagos(pago);
    }
    
    public boolean modificar(Pagos pago) {
        return dao.modificar(pago);
    }

    public boolean eliminarPagos(Pagos pago) {
        return dao.eliminarPagos(pago);
    }
    
    public String obtenerPago() {
        return dao.obtenerPago();
    }
   
}
