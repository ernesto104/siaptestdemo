package Entidades.Otros;

/**
 *
 * @author Ledis Rivera Ch.
 */
public class DatosND {
    
    private String fechaEmision;
    private String dia;
    private String mesLetras;
    private String año1DigUlt;
//    private String año2DigUlt;
//    private String año4DigUlt;
    
    private String clienteNombre;
    private String clienteDireccion;
    private String clienteRUC;
    
    private String pedido;
    private String tipoCambio;
    
    // N° de Referencia de la Factura(a la que se relaciona la Nota de Débito)
    private String tipoDocumento;
    private String nroDocFactura;
    
    private String rutaProgramas;
    private String nombreExcelNotaDebito;
    
    // N° de la Nota de Débito
    private String nroSerie;
    private String numeroND;
    
    private String moneda;
    private String subtotal;
    private String igv;
    private String impuesto;
    private String total;
    
    private String totalEnLetras;
    private String concepto;
    
    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMesLetras() {
        return mesLetras;
    }

    public void setMesLetras(String mesLetras) {
        this.mesLetras = mesLetras;
    }

    public String getAño1DigUlt() {
        return año1DigUlt;
    }

    public void setAño1DigUlt(String año1DigUlt) {
        this.año1DigUlt = año1DigUlt;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getClienteRUC() {
        return clienteRUC;
    }

    public void setClienteRUC(String clienteRUC) {
        this.clienteRUC = clienteRUC;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getNroDocFactura() {
        return nroDocFactura;
    }

    public void setNroDocFactura(String nroDocFactura) {
        this.nroDocFactura = nroDocFactura;
    }

    public String getRutaProgramas() {
        return rutaProgramas;
    }

    public void setRutaProgramas(String rutaProgramas) {
        this.rutaProgramas = rutaProgramas;
    }
    
    public String getNombreExcelNotaDebito() {
        return nombreExcelNotaDebito;
    }

    public void setNombreExcelNotaDebito(String nombreExcelNotaDebito) {
        this.nombreExcelNotaDebito = nombreExcelNotaDebito;
    }

    public String getNumeroND() {
        return numeroND;
    }

    public void setNumeroND(String numeroND) {
        this.numeroND = numeroND;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalEnLetras() {
        return totalEnLetras;
    }

    public void setTotalEnLetras(String totalEnLetras) {
        this.totalEnLetras = totalEnLetras;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}