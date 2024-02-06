package Presentacion.InventarioValorizado;

/**
 *
 * @author Ledis Rivera Changra
 */
public class InventarioBrowse implements Comparable<InventarioBrowse>  {
    
//    private String item;
    private String codrepuesto;
    private String descripcion;
    private String descrmodelo;
    private String anotacion;
    private String stock;
    private String valorizacion;
    private String total;
    private String idlinea;
    private String costoUnit;
    private String precioVta;
    private String codigoSec;
    private String desclista;
    private String desclista2;
    private String aplicacion;
    
    public InventarioBrowse(
//                            String item, 
//                          String codrepuesto, String descripcion, String aplicacion, String anotacion, String stock, 
                            String codrepuesto, String descripcion, String descrmodelo, String anotacion, String stock, 
                            String valorizacion, 
                            String total, String idlinea, String costoUnit, String precioVta, String codigoSec, String desclista2, String aplicacion, String desclista) {
//        this.item = item;
        this.codrepuesto = codrepuesto;
        this.descripcion = descripcion;
        this.descrmodelo = descrmodelo;
        this.anotacion = anotacion;
        this.stock = stock;
        this.valorizacion = valorizacion;
        this.total = total;
        this.idlinea = idlinea;                      

        this.costoUnit = costoUnit;
        this.precioVta = precioVta;
        this.codigoSec = codigoSec;
        this.desclista = desclista;
        this.desclista2 = desclista2;  
        this.aplicacion = aplicacion;        
 //       this.desclista2 = desclista2;                
    } 

//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }

    public String getCodrepuesto() {
        return codrepuesto;
    }

    public void setCodrepuesto(String codrepuesto) {
        this.codrepuesto = codrepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(String idlinea) {
        this.idlinea = idlinea;
    }

    public String getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(String costoUnit) {
        this.costoUnit = costoUnit;
    }

    public String getPrecioVta() {
        return precioVta;
    }

    public void setPrecioVta(String precioVta) {
        this.precioVta = precioVta;
    }

    public String getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(String codigoSec) {
        this.codigoSec = codigoSec;
    }
    

    public String getDesclista() {
        return desclista;
    }

    public void setDesclista(String desclista) {
        this.desclista = desclista;
    }

    public String getDesclista2() {
        return desclista2;
    }

    public void setDesclista2(String desclista2) {
        this.desclista2 = desclista2;
    }
    
    public String getDescrModelo() {
        return descrmodelo;
    }

    public void setDescrModelo(String descrmodelo) {
        this.descrmodelo = descrmodelo;
    }
    
    @Override
    public int compareTo(InventarioBrowse o) {
        String a = descripcion + aplicacion;
        String b = o.descripcion + o.aplicacion;
        return a.compareTo(b);
    }
}