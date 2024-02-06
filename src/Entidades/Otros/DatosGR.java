package Entidades.Otros;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DatosGR {
 
    private String nroSerie;
    private String nroDocumento;
    private String nombreExcelGR;
    private String rutaProgramas;
    
    private String nombreEmpresa;
    private String direccionEmpresa;
    private String rucEmpresa;
    
    private String nombreTransportista;
    private String direccionTransportista;
    private String rucTransportista;
    
    private String dia;
    private String mesLetras;
    private String año;
    
    // Datos de la Unidad de Transporte y Conductor
    // N° de Placa de la Unidad de Transporte
    private String placa; // Se tomó de la Aseguradora que requería la placa del vehículo para el que se solicitaba repuestos en Factura o Boleta
    private String ordenTransporte;
    
    // Marca de la Unidad de Transporte
    private String marca; // Se tomó de la Aseguradora que requería la placa del vehículo para el que se solicitaba repuestos en Factura o Boleta
    private String siniestro;
    
    // N° de Constancia de Inscripcion
    private String constanciaInscripcion; // Campo nuevo para asignar al respectivo documento de identificación del Conductor.
    
    // N° de Licencia de Conducir
    private String licenciaConducir; // Campo nuevo para asignar permiso de conducir.
    
    private String nroFactura;
    
    private String dptoPartida;
    private String provPartida;
    private String distPartida;
    private String direccionEmpresaPartida;
    
    private String dptoLlegada;
    private String provLlegada;
    private String distLlegada;
    private String direccionEmpresaLlegada;
    
    private String tipoDocumento; // tipo de documento del cual proviene

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

    public String getNombreExcelGR() {
        return nombreExcelGR;
    }

    public void setNombreExcelGR(String nombreExcelGR) {
        this.nombreExcelGR = nombreExcelGR;
    }

    public String getRutaProgramas() {
        return rutaProgramas;
    }

    public void setRutaProgramas(String rutaProgramas) {
        this.rutaProgramas = rutaProgramas;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getNombreTransportista() {
        return nombreTransportista;
    }

    public void setNombreTransportista(String nombreTransportista) {
        this.nombreTransportista = nombreTransportista;
    }

    public String getDireccionTransportista() {
        return direccionTransportista;
    }

    public void setDireccionTransportista(String direccionTransportista) {
        this.direccionTransportista = direccionTransportista;
    }

    public String getRucTransportista() {
        return rucTransportista;
    }

    public void setRucTransportista(String rucTransportista) {
        this.rucTransportista = rucTransportista;
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

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getOrdenTransporte() {
        return ordenTransporte;
    }

    public void setOrdenTransporte(String ordenTransporte) {
        this.ordenTransporte = ordenTransporte;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(String siniestro) {
        this.siniestro = siniestro;
    }

    public String getDptoPartida() {
        return dptoPartida;
    }

    public void setDptoPartida(String dptoPartida) {
        this.dptoPartida = dptoPartida;
    }

    public String getProvPartida() {
        return provPartida;
    }

    public void setProvPartida(String provPartida) {
        this.provPartida = provPartida;
    }

    public String getDistPartida() {
        return distPartida;
    }

    public void setDistPartida(String distPartida) {
        this.distPartida = distPartida;
    }

    public String getDptoLlegada() {
        return dptoLlegada;
    }

    public void setDptoLlegada(String dptoLlegada) {
        this.dptoLlegada = dptoLlegada;
    }

    public String getProvLlegada() {
        return provLlegada;
    }

    public void setProvLlegada(String provLlegada) {
        this.provLlegada = provLlegada;
    }

    public String getDistLlegada() {
        return distLlegada;
    }

    public void setDistLlegada(String distLlegada) {
        this.distLlegada = distLlegada;
    }

    public String getDireccionEmpresaLlegada() {
        return direccionEmpresaLlegada;
    }

    public void setDireccionEmpresaLlegada(String direccionEmpresaLlegada) {
        this.direccionEmpresaLlegada = direccionEmpresaLlegada;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getDireccionEmpresaPartida() {
        return direccionEmpresaPartida;
    }

    public void setDireccionEmpresaPartida(String direccionEmpresaPartida) {
        this.direccionEmpresaPartida = direccionEmpresaPartida;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getConstanciaInscripcion() {
        return constanciaInscripcion;
    }

    public void setConstanciaInscripcion(String constanciaInscripcion) {
        this.constanciaInscripcion = constanciaInscripcion;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }
}