package Presentacion.Comisiones;

/**
 *
 * @author usuario
 */
public class FilaComision {
    String tipoDoc;
    String nroDoc;
    String fecha;
    String cV;
    String cliente_Proveedor;
    String venta;
    String comision;

    public FilaComision(String tipoDoc, String nroDoc, String fecha, String cV, String cliente_Proveedor, String venta, String comision) {
        this.tipoDoc = tipoDoc;
        this.nroDoc = nroDoc;
        this.fecha = fecha;
        this.cV = cV;
        this.cliente_Proveedor = cliente_Proveedor;
        this.venta = venta;
        this.comision = comision;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getcV() {
        return cV;
    }

    public void setcV(String cV) {
        this.cV = cV;
    }

    public String getCliente_Proveedor() {
        return cliente_Proveedor;
    }

    public void setCliente_Proveedor(String cliente_Proveedor) {
        this.cliente_Proveedor = cliente_Proveedor;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }
    
    
}
