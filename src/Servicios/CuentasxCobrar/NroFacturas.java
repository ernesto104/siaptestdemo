package Servicios.CuentasxCobrar;

import Servicios.Servicio_Banco;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author keily
 */
public class NroFacturas {

    private int numero;
    
    private int idFactura;
    
    private double importe;
    private JDateChooser fechaPago;
    private String modo;
    Servicio_Banco sb = new Servicio_Banco(null);
    List listabancos = sb.listar_Bancos();
    JComboBox banco = new JComboBox(listabancos.toArray());
    private String nrocheque;
    private String rutaImagen;

    public NroFacturas() {
        fechaPago = new JDateChooser();
    }
   
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
   
    public JDateChooser getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {        
         this.fechaPago.setDate(fechaPago);    
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public JComboBox getBanco() {
        return banco;
    }

    public void setBanco(Object banco) {
        this.banco.setSelectedItem(banco);
    }

    public String getNrocheque() {
        return nrocheque;
    }

    public void setNrocheque(String nrocheque) {
        this.nrocheque = nrocheque;
    }
    
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setImporte(String letra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
