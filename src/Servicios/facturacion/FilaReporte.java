package Servicios.facturacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maverick225
 */
public class FilaReporte {

    private String cantidad;
    private String descripcion;
    private String unitario;
    private String valorventa;

    public FilaReporte(String cantidad, String descripcion, String unitario, String valorventa) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.unitario = unitario;
        this.valorventa = valorventa;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnitario() {
        return unitario;
    }

    public void setUnitario(String unitario) {
        this.unitario = unitario;
    }

    public String getValorventa() {
        return valorventa;
    }

    public void setValorventa(String valorventa) {
        this.valorventa = valorventa;
    }
    
    
}
