package Presentacion.FacturacionElectronica;

import java.util.List;

/**
 *
 * @author Ledis Rivera Changra
 */
public class CabDocElectronico {
    
    // 1. "FEC_ED" = Fecha del documento electronico
    // Ejemplo: 10/07/2018
    private String fec_ed;
    
    // 2. "TIP_DOC" = Tipo de documento electronico (SEGÚN TABLA SUNAT = Tabla SISTEMA en BD Siar)
    // Ejemplo: 01
    private String tip_doc;
    
    // 3. "NRO_SERIE"
    // Ejemplo: F001
    private String nro_serie;
    
    // 4. "NRO_DOC" = Nº de documento electronico = Nº de factura, boleta, NC, ND
    // Ejemplo: 0009925
    private String nro_doc;
    
    // 5. "NRO_DOC_ADQUIRIENTE" = RUC del cliente de MyD
    // Ejemplo: 20601441242
    private String nro_doc_adquiriente;
    
    // 6. "TIP_DOC_ADQUIRIENTE"
    // Ejemplo: 06
    private String tip_doc_adquiriente; // = "06";
    
    // 7. "APAMNO_RAZON_SOCIAL_ADQUIRIENTE" = Razon Social del Cliente
    // Ejemplo: GRUPO JOFRAN S.A.C.
    private String apamno_razon_social_adquiriente;
    
    // 8. "MONEDA"
    // Ejemplo: USD
    private String moneda;
    
    // 9. "TOTAL_OPERACIONES_GRAV" = Total - IGV
    private String total_operaciones_grav;
    
    // 10. "TOTAL_OPERACIONES_INF" = 0.00
    private String total_operaciones_inf; // = "0.00";
    
    // 11. "TOTAL_OPERACIONES_EXONERADAS" = 0.00
    private String total_operaciones_exoneradas; // = "0.00";
    
    // 12. "TOTAL_OPERACIONES_EXPORTACION"
    private String total_operaciones_exportacion; // = "0.00";
    
    // 13. "MONTO_TOTAL_OPERACIONES_GRAT"
    private String monto_total_operaciones_grat; // = "0.00";
    
    // 14. "MONTO_DESCUENTOS_GLOBALES"
    private String monto_descuentos_globales; // = "0.00";
    
    // 15. "MONTO_TOTAL_IGV"
    private String monto_total_igv;
    
    // 16. "MONTO_PAGAR"
    private String monto_pagar;
    
    // 17. "MONTO_PERCEPCION"
    private String monto_percepcion; // = "0.00";
    
    // 18. "MONTO_TOTAL_PERCEP"
    private String monto_total_percep; // = "0.00";
    
    // 19. "LEYENDA"
    private String leyenda;
    
    // 20. "CORREO_CLIENTE"
    private String correo_cliente; // = "";
    
    // 21. "TIPO_DOC_ELECTRONICO"
    private String tipo_doc_electronico;
    
    private String estadoTxtGenerado;
    private String fechaEnvio; // en que se generó el txt
    private String diasVencidosGenTxt;
    
    private List<DetDocElectronico> lstDetDocElect;
    
    /*********** Campos para obtener el nroSerie y el nroDocumento sin ceros y prefijo **********/
    /* Ejemplo:
    NRO_SERIE:F001      NRO_DOC:0008536
    */
    private String nro_serie_BD;
    private String nro_documento_BD;
    /* Ejemplo:
    NRO_SERIE:1         NRO_DOC:8536
    */
    
    private String guia_remision;
    private String direccion_cliente;

    public CabDocElectronico() {
        tip_doc_adquiriente = "06";
        total_operaciones_inf = "0.00";
        total_operaciones_exoneradas = "0.00";
        total_operaciones_exportacion = "0.00";
        monto_total_operaciones_grat = "0.00";
        monto_descuentos_globales = "0.00";
        monto_percepcion = "0.00";
        monto_total_percep = "0.00";
        correo_cliente = "";
    }

    public String getFec_ed() {
        return fec_ed;
    }

    public void setFec_ed(String fec_ed) {
        this.fec_ed = fec_ed;
    }

    public String getTip_doc() {
        return tip_doc;
    }

    public void setTip_doc(String tip_doc) {
        this.tip_doc = tip_doc;
    }

    public String getNro_serie() {
        return nro_serie;
    }

    public void setNro_serie(String nro_serie) {
        this.nro_serie = nro_serie;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public String getNro_doc_adquiriente() {
        return nro_doc_adquiriente;
    }

    public void setNro_doc_adquiriente(String nro_doc_adquiriente) {
        this.nro_doc_adquiriente = nro_doc_adquiriente;
    }

    public String getTip_doc_adquiriente() {
        return tip_doc_adquiriente;
    }

    public void setTip_doc_adquiriente(String tip_doc_adquiriente) {
        this.tip_doc_adquiriente = tip_doc_adquiriente;
    }

    public String getApamno_razon_social_adquiriente() {
        return apamno_razon_social_adquiriente;
    }

    public void setApamno_razon_social_adquiriente(String apamno_razon_social_adquiriente) {
        this.apamno_razon_social_adquiriente = apamno_razon_social_adquiriente;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTotal_operaciones_grav() {
        return total_operaciones_grav;
    }

    public void setTotal_operaciones_grav(String total_operaciones_grav) {
        this.total_operaciones_grav = total_operaciones_grav;
    }

    public String getTotal_operaciones_inf() {
        return total_operaciones_inf;
    }

    public void setTotal_operaciones_inf(String total_operaciones_inf) {
        this.total_operaciones_inf = total_operaciones_inf;
    }

    public String getTotal_operaciones_exoneradas() {
        return total_operaciones_exoneradas;
    }

    public void setTotal_operaciones_exoneradas(String total_operaciones_exoneradas) {
        this.total_operaciones_exoneradas = total_operaciones_exoneradas;
    }

    public String getTotal_operaciones_exportacion() {
        return total_operaciones_exportacion;
    }

    public void setTotal_operaciones_exportacion(String total_operaciones_exportacion) {
        this.total_operaciones_exportacion = total_operaciones_exportacion;
    }

    public String getMonto_total_operaciones_grat() {
        return monto_total_operaciones_grat;
    }

    public void setMonto_total_operaciones_grat(String monto_total_operaciones_grat) {
        this.monto_total_operaciones_grat = monto_total_operaciones_grat;
    }

    public String getMonto_descuentos_globales() {
        return monto_descuentos_globales;
    }

    public void setMonto_descuentos_globales(String monto_descuentos_globales) {
        this.monto_descuentos_globales = monto_descuentos_globales;
    }

    public String getMonto_total_igv() {
        return monto_total_igv;
    }

    public void setMonto_total_igv(String monto_total_igv) {
        this.monto_total_igv = monto_total_igv;
    }

    public String getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(String monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    public String getMonto_percepcion() {
        return monto_percepcion;
    }

    public void setMonto_percepcion(String monto_percepcion) {
        this.monto_percepcion = monto_percepcion;
    }

    public String getMonto_total_percep() {
        return monto_total_percep;
    }

    public void setMonto_total_percep(String monto_total_percep) {
        this.monto_total_percep = monto_total_percep;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getEstadoTxtGenerado() {
        return estadoTxtGenerado;
    }

    public void setEstadoTxtGenerado(String estadoTxtGenerado) {
        this.estadoTxtGenerado = estadoTxtGenerado;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getDiasVencidosGenTxt() {
        return diasVencidosGenTxt;
    }

    public void setDiasVencidosGenTxt(String diasVencidosGenTxt) {
        this.diasVencidosGenTxt = diasVencidosGenTxt;
    }

    public List<DetDocElectronico> getLstDetDocElect() {
        return lstDetDocElect;
    }

    public void setLstDetDocElect(List<DetDocElectronico> lstDetDocElect) {
        this.lstDetDocElect = lstDetDocElect;
    }

    public String getNro_serie_BD() {
        return nro_serie_BD;
    }

    public void setNro_serie_BD(String nro_serie_BD) {
        this.nro_serie_BD = nro_serie_BD;
    }

    public String getNro_documento_BD() {
        return nro_documento_BD;
    }

    public void setNro_documento_BD(String nro_documento_BD) {
        this.nro_documento_BD = nro_documento_BD;
    }

    public String getGuia_remision() {
        return guia_remision;
    }

    public void setGuia_remision(String guia_remision) {
        this.guia_remision = guia_remision;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }
    
    public String getTipo_doc_electronico() {
        return tipo_doc_electronico;
    }

    public void setTipo_doc_electronico(String tipo_doc_electronico) {
        this.tipo_doc_electronico = tipo_doc_electronico;
    }
}