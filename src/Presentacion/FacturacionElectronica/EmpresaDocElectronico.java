package Presentacion.FacturacionElectronica;

/**
 *
 * @author Ledis Rivera Changra
 * @date Viernes 20/07/2018 (22:00 p.m.)
 */
public class EmpresaDocElectronico {
    private String ruc_emisor;
    private String tip_doc_emisor;
    private String apamno_razon_social_emisor;
    private String ubigeo_emisor;
    private String direccion_emisor;
    private String urbanizacion_emisor; // = "-";
    private String departamento_emisor;
    private String provincia_emisor;
    private String distrito_emisor;
    private String codigo_pais_emisor; // = "PE";
    private String nombre_comercial_emisor; // = "-";
    
    private String rutaDocElectronicos;

    public EmpresaDocElectronico() {
        urbanizacion_emisor = "-";
        codigo_pais_emisor = "PE";
        nombre_comercial_emisor = "-";
    }

    public String getRuc_emisor() {
        return ruc_emisor;
    }

    public void setRuc_emisor(String ruc_emisor) {
        this.ruc_emisor = ruc_emisor;
    }

    public String getTip_doc_emisor() {
        return tip_doc_emisor;
    }

    public void setTip_doc_emisor(String tip_doc_emisor) {
        this.tip_doc_emisor = tip_doc_emisor;
    }

    public String getApamno_razon_social_emisor() {
        return apamno_razon_social_emisor;
    }

    public void setApamno_razon_social_emisor(String apamno_razon_social_emisor) {
        this.apamno_razon_social_emisor = apamno_razon_social_emisor;
    }

    public String getUbigeo_emisor() {
        return ubigeo_emisor;
    }

    public void setUbigeo_emisor(String ubigeo_emisor) {
        this.ubigeo_emisor = ubigeo_emisor;
    }

    public String getDireccion_emisor() {
        return direccion_emisor;
    }

    public void setDireccion_emisor(String direccion_emisor) {
        this.direccion_emisor = direccion_emisor;
    }

    public String getUrbanizacion_emisor() {
        return urbanizacion_emisor;
    }

    public void setUrbanizacion_emisor(String urbanizacion_emisor) {
        this.urbanizacion_emisor = urbanizacion_emisor;
    }

    public String getDepartamento_emisor() {
        return departamento_emisor;
    }

    public void setDepartamento_emisor(String departamento_emisor) {
        this.departamento_emisor = departamento_emisor;
    }

    public String getProvincia_emisor() {
        return provincia_emisor;
    }

    public void setProvincia_emisor(String provincia_emisor) {
        this.provincia_emisor = provincia_emisor;
    }

    public String getDistrito_emisor() {
        return distrito_emisor;
    }

    public void setDistrito_emisor(String distrito_emisor) {
        this.distrito_emisor = distrito_emisor;
    }

    public String getCodigo_pais_emisor() {
        return codigo_pais_emisor;
    }

    public void setCodigo_pais_emisor(String codigo_pais_emisor) {
        this.codigo_pais_emisor = codigo_pais_emisor;
    }

    public String getNombre_comercial_emisor() {
        return nombre_comercial_emisor;
    }

    public void setNombre_comercial_emisor(String nombre_comercial_emisor) {
        this.nombre_comercial_emisor = nombre_comercial_emisor;
    }

    public String getRutaDocElectronicos() {
        return rutaDocElectronicos;
    }

    public void setRutaDocElectronicos(String rutaDocElectronicos) {
        this.rutaDocElectronicos = rutaDocElectronicos;
    }
}