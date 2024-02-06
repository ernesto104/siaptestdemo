
package Servicios;

import Entidades.Cabeces;
import Entidades.Detallenota;
import Mantenimiento.DetalleNotaDAO;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */

public class Servicio_DetalleNota {
    
    DefaultTableModel modelo;
    DetalleNotaDAO detalleNotaDao;
    JTable tblDetalle;
    int ultimo_id;
    List<Detallenota> listaDetalle;

    public Servicio_DetalleNota() {
        detalleNotaDao = new DetalleNotaDAO();
    }
    
    public Servicio_DetalleNota(JTable tblDetalle) {
        detalleNotaDao = new DetalleNotaDAO();
        this.tblDetalle = tblDetalle;
    }
    

    public int Listar_Detalle_Por_Documento(DefaultTableModel modelo, String numeroDocumento) { 
        this.modelo=modelo;
        List<Detallenota> listaDetalles = detalleNotaDao.getListaDetallePorDocumento(numeroDocumento);
        for (int i = 0; i < listaDetalles.size(); i++) {
            if(i==(listaDetalles.size()-1)){
                //ultimo_id = listaDetalles.get(i).getIddetnotacred();
                ultimo_id = listaDetalles.get(i).getIddetnota();
            }
        }
        return listaDetalles.size();
    }
    
    // Nota de credito por descuento y nota de debito
    public int Listar_DetalleNota_Sin_CabecID(DefaultTableModel modelo, Cabeces cabeces) {
      
        this.modelo=modelo;
        List<Detallenota> listaDetalleNota = detalleNotaDao.getListaDetallePorTipoDocumento(cabeces.getId().getNrodocumento(),cabeces.getId().getTipodoc());
      //  System.out.println("Hay " + listaDetalleEs.size() + " contactos en la base de datos");
        this.listaDetalle = listaDetalleNota;
        for (int i = 0; i < listaDetalleNota.size(); i++) {
            String f= listaDetalleNota.get(i).getDescripcion();
            Double g= listaDetalleNota.get(i).getValor();
            Object[] lineas = {f,g};
            modelo.addRow(lineas);
        }
        return listaDetalleNota.size();
    }
    
    
    // Nota de credito por devolucion
    public int Listar_DetalleNota_Con_CabecID(DefaultTableModel modelo, Cabeces cabeces) {
      
        this.modelo=modelo;
        List<Detallenota> listaDetalleNota = detalleNotaDao.getListaDetallePorTipoDocumento(cabeces.getId().getNrodocumento(),cabeces.getId().getTipodoc());
        this.listaDetalle = listaDetalleNota;
        for (int i = 0; i < listaDetalleNota.size(); i++) {
            int a = listaDetalleNota.get(i).getRepuestos().getId().getIdlinea();
            String b = ""+listaDetalleNota.get(i).getRepuestos().getCodrepuesto();
           // String b = "";
            int c = listaDetalleNota.get(i).getCantidad();
            String f= listaDetalleNota.get(i).getDescripcion();
            Double g= listaDetalleNota.get(i).getValor()*c;
            g = Redondear(g);
            Object[] lineas = {a,b,c,f,g};
            modelo.addRow(lineas);
        }
        return listaDetalleNota.size();
    }
    
    public boolean insertar_DetalleNota(Detallenota d){
        return detalleNotaDao.Agregar_Objeto(d);
    
    }
    
    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public List<Detallenota> getListaDetalle() {
        return listaDetalle;
    }
    
    
        
    
}
