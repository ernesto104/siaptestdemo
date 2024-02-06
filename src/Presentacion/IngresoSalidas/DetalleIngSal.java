package Presentacion.IngresoSalidas;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DetalleIngSal {
    String nroParte;
    String codSec;
    String descripcion;
    String cantidad;
    String precioLista;
    String total;
    String modelo;

    public DetalleIngSal(String nroParte, String codSec, String descripcion, String cantidad, String precioLista, String total, String modelo) {
        this.nroParte = nroParte;
        this.codSec = codSec;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioLista = precioLista;
        this.total = total;
        this.modelo = modelo;
    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public String getCodSec() {
        return codSec;
    }

    public void setCodSec(String codSec) {
        this.codSec = codSec;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(String precioLista) {
        this.precioLista = precioLista;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}