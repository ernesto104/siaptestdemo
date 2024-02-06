package Servicios.facturacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maverick225
 */
public class FilaFactura {

    private String cantidad;
    private String descripcion;
    private String unitario;
    private String valorVenta;
    private String idRepuesto;
    private String codigoSecundario;
    private String descuentos;
    
    private String anotaciones;

    public FilaFactura(String cantidad, String descripcion, String unitario, String valorVenta, String idRepuesto, String codigoSecundario, String descuentos
            , String anotaciones) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.unitario = unitario;
        this.valorVenta = valorVenta;
        this.idRepuesto = idRepuesto;
        this.codigoSecundario = codigoSecundario;
        this.descuentos = descuentos;
        this.anotaciones = anotaciones;
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

    public String getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(String idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getCodigoSecundario() {
        return codigoSecundario;
    }

    public void setCodigoSecundario(String codigoSecundario) {
        this.codigoSecundario = codigoSecundario;
    }

    public String getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(String descuentos) {
        this.descuentos = descuentos;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }
    
}
