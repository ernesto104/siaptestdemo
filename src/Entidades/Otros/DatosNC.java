package Entidades.Otros;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DatosNC {
    
    private int modalidadNC;
    
// N° Identificación de Factura para exportar archivo con único nombre en Excel
    private String nroSerie;
    private String nroDocumento;
    private String rutaProgramas;
    private String nombreExcelNotaCredito;
    private String numeroNC;

    private String fechaEmision;
    private String clienteNombre;
    private String clienteRUC;
    private String clienteDireccion;
    
    private String fechaNC;
    
    private String moneda;
    private String subtotal;
    
    private String impuesto;
    private String igv;
    
    private String totalEnLetras;
    private String total;
    
    private String dia;
    private String mesLetras;
    private String año2DigUlt;
    private String año4DigUlt;
    
    private String tipoDocumento;
    private String nroFactura;
    
    private String concepto;
    
    private String condicionPago;

    public int getModalidadNC() {
        return modalidadNC;
    }

    public void setModalidadNC(int modalidadNC) {
        this.modalidadNC = modalidadNC;
    }
    
    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }
    
    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getRutaProgramas() {
        return rutaProgramas;
    }

    public void setRutaProgramas(String rutaProgramas) {
        this.rutaProgramas = rutaProgramas;
    }

    public String getNombreExcelNotaCredito() {
        return nombreExcelNotaCredito;
    }

    public void setNombreExcelNotaCredito(String nombreExcelNotaCredito) {
        this.nombreExcelNotaCredito = nombreExcelNotaCredito;
    }

    public String getNumeroNC() {
        return numeroNC;
    }

    public void setNumeroNC(String numeroNC) {
        this.numeroNC = numeroNC;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteRUC() {
        return clienteRUC;
    }

    public void setClienteRUC(String clienteRUC) {
        this.clienteRUC = clienteRUC;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getFechaNC() {
        return fechaNC;
    }

    public void setFechaNC(String fechaNC) {
        this.fechaNC = fechaNC;
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

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getTotalEnLetras() {
        return totalEnLetras;
    }

    public void setTotalEnLetras(String totalEnLetras) {
        this.totalEnLetras = totalEnLetras;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getAño2DigUlt() {
        return año2DigUlt;
    }

    public void setAño2DigUlt(String año2DigUlt) {
        this.año2DigUlt = año2DigUlt;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getAño4DigUlt() {
        return año4DigUlt;
    }

    public void setAño4DigUlt(String año4DigUlt) {
        this.año4DigUlt = año4DigUlt;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }
}