/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.facturacion;

/**
 *
 * @author CRS
 */
public class FilaProforma {
    String item;
    String nroParte;
    String descripcion;
    String cantidad;
    String total;

    public FilaProforma(String item, String nroParte, String descripcion, String cantidad, String total) {
        this.item = item;
        this.nroParte = nroParte;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    
    
}
