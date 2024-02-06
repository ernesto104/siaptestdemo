package Presentacion.RepuestosSegunEstratificacion;

/**
 *
 * @author CRS
 */
public class ConsultarRepuestos {
    String nroParte;
    String descripcion;
    String stock;
    String precioLista;
    String marca;

    public ConsultarRepuestos(String nroParte, String descripcion, String stock, String precioLista, String marca) {
        this.nroParte = nroParte;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioLista = precioLista;
        this.marca = marca;
    }

    
    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(String precioLista) {
        this.precioLista = precioLista;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
