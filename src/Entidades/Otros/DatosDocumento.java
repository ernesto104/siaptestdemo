package Entidades.Otros;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DatosDocumento {
    
    // N° Identificación de Factura o boleta para exportar archivo con único nombre en Excel
    private String nroSerie;
    private String nroDocumento;
    private String rutaProgramas;
    private String rutaTemplate;
    private String nombreExcelDocumento; // Para factura o boleta
    
    private String totalIgv;
    private String nombreEmpresa;
    private String rucEmpresa;
    private String direccionEmpresa;
    private String ubigeo;
    private String fecha;
    private String enviado;
    private String pedido;
    private String orden;

    private String marca;
    private String observaciones;  //antes llamado placa
    private String modelo;

    private String ordenTransporte;
    private String siniestro;
    private String fechaEmision;
    private String dia;
    private String mes;
    private String año;

    private String numeroGuia;
    private String total;
    private String cantidadEnLetras;
    private String valorventa;
    
    private String moneda;
    private String vendedor;
    private String puntoventa;
    
    private String glosa;
    private String condicion;

    public DatosDocumento() {
        
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
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

    public String getRutaTemplate() {
        return rutaTemplate;
    }

    public void setRutaTemplate(String rutaTemplate) {
        this.rutaTemplate = rutaTemplate;
    }

    public String getNombreExcelDocumento() {
        return nombreExcelDocumento;
    }

    public void setNombreExcelDocumento(String nombreExcelDocumento) {
        this.nombreExcelDocumento = nombreExcelDocumento;
    }

    public String getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(String totalIgv) {
        this.totalIgv = totalIgv;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getOrdenTransporte() {
        return ordenTransporte;
    }

    public void setOrdenTransporte(String ordenTransporte) {
        this.ordenTransporte = ordenTransporte;
    }

    public String getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(String siniestro) {
        this.siniestro = siniestro;
    }

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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCantidadEnLetras() {
        return cantidadEnLetras;
    }

    public void setCantidadEnLetras(String cantidadEnLetras) {
        this.cantidadEnLetras = cantidadEnLetras;
    }

    public String getValorventa() {
        return valorventa;
    }

    public void setValorventa(String valorventa) {
        this.valorventa = valorventa;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getPuntoventa() {
        return puntoventa;
    }

    public void setPuntoventa(String puntoventa) {
        this.puntoventa = puntoventa;
    }
    
    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}