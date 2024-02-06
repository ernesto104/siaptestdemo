/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.HistorialMov;

import Mantenimiento.DetallesDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CRS
 */
public class Servicio_Historial {
    DetallesDAO detalles ;
    public Servicio_Historial(){
        detalles = new DetallesDAO();
    }
    public List getListaxFechas(int idRep , Date inicio, Date fin){
        return detalles.getDetallesxFecha(idRep, inicio, fin);
    }
    public List getLista(int idRep){
        return detalles.getDetalles(idRep);
    }
    public String getTipoOperacion(String tipo){
        return new SistemaDAO().obtener_por_Codigo(tipo);
    }
}
