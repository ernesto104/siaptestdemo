package Presentacion.listaPrecios;

/**
 *
 * @author CRS
 */
public class ListaPrecio {
    
//    private String linea;
//    private String item; // N°
    private String nroParte;
    private String codigoSeg;
    private String descripcion;
    
    private String descrModelo;     // Aplicación 1 + Aplicación 2 + Anotación 1 + Anotación 2
    //private String descrModelo;     // Aplicación 1 + Aplicación 2
    //private String descrLista;      // Anotacion 1 + Anotacion 2
    
//    private String anotaciones;
//    private String stock;
//    private String costo;
    private String precio;
    private String marca;

    public ListaPrecio(
//            String item, 
            String nroParte, String codigoSeg, String descripcion, String descrModelo, /*String descrLista,*/
                       /*String stock, String costo,*/ String precio,
                       String marca) {
//        this.item = item;
        this.nroParte = nroParte;
        this.codigoSeg = codigoSeg;
        this.descripcion = descripcion;
        this.descrModelo = descrModelo;
        //this.descrLista = descrLista;
//        this.stock = stock;
//        this.costo = costo;
//        this.marca = marca;
        this.precio = precio;
        this.marca = marca;
    }

//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
    
//    public String getLinea() {
//        return linea;
//    }
//
//    public void setLinea(String linea) {
//        this.linea = linea;
//    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public String getCodigoSeg() {
        return codigoSeg;
    }

    public void setCodigoSeg(String codigoSeg) {
        this.codigoSeg = codigoSeg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescrModelo() {
        return descrModelo;
    }

    public void setDescrModelo(String descrModelo) {
        this.descrModelo = descrModelo;
    }

//    public String getDescrLista() {
//        return descrLista;
//    }
//
//    public void setDescrLista(String descrLista) {
//        this.descrLista = descrLista;
//    }

//    public String getAnotaciones() {
//        return anotaciones;
//    }
//
//    public void setAnotaciones(String anotaciones) {
//        this.anotaciones = anotaciones;
//    }
//    public String getStock() {
//        return stock;
//    }
//
//    public void setStock(String stock) {
//        this.stock = stock;
//    }
//
//    public String getCosto() {
//        return costo;
//    }
//
//    public void setCosto(String costo) {
//        this.costo = costo;
//    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}