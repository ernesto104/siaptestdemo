package Presentacion.ResumenGeneralCliente;

/**
 *
 * @author CRS
 */
public class Detalle_FBSK {
    String tipo;
    String nroSerie;
    String nroDoc;
    String fecha;
    String facturado;
    String pagado;
    String pendiente;

    public Detalle_FBSK() {
    }

    public Detalle_FBSK(String tipo, String nroSerie, String nroDoc, String fecha, String facturado, String pagado, String pendiente) {
        this.tipo = tipo;
        this.nroSerie = nroSerie;
        this.nroDoc = nroDoc;
        this.fecha = fecha;
        this.facturado = facturado;
        this.pagado = pagado;
        this.pendiente = pendiente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
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

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }
    
    
}
