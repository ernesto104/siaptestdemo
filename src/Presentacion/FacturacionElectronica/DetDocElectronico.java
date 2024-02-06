package Presentacion.FacturacionElectronica;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DetDocElectronico {
    
    // 1. "ID_ITEM" = Nº de item (como el orden que figura en el documento impreso)
    private String id_item;
    
    // 2. "COD_PROD_SERV_ITEM" = codigo del producto
    private String cod_prod_serv_item;
    
    // 3. "DESRIP_ITEM" = descripcion del item
    private String desrip_item;
    
    // 4. "COD_UNIDAD_MEDIDA_ITEM"
    private String cod_unidad_medida_item = "NIU";
    
    // 5. "INDICADOR_PS_ITEM"
    private String indicador_ps_item = "P";
    
    // 6. "INDICADOR_TRANS_GRAT"
    private String indicador_trans_grat = "0";
    
    // 7. "INDICADOR_AFECTACION_ITEM"
    private String indicador_afectacion_item = "10";
    
    
    // 8. "VALOR_VENTA_UNITARIA"
    private String valor_venta_unitaria;
    
    // 9. "PRECIO_VENTA_UNITARIO_ITEM"
    private String precio_venta_unitario_item;
    
    // 10. "CANTIDAD_ITEM" (Formato : ##.##)
    private String cantidad_item;
    
    // 11. "DESCUENTO_ITEM" (Formato: ##.##)
    private String descuento_item = "0.00"; // PREGUNTAR A JAVIER
    
    // 12. "VALOR_ITEM"
    private String valor_item;
    
    // 13. "IGV_TOTAL_ITEM"
    private String igv_total_item;
    
    // 14. "TOTAL_ITEM"
    private String total_item;

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getCod_prod_serv_item() {
        return cod_prod_serv_item;
    }

    public void setCod_prod_serv_item(String cod_prod_serv_item) {
        this.cod_prod_serv_item = cod_prod_serv_item;
    }

    public String getDesrip_item() {
        return desrip_item;
    }

    public void setDesrip_item(String desrip_item) {
        this.desrip_item = desrip_item;
    }

    public String getCod_unidad_medida_item() {
        return cod_unidad_medida_item;
    }

    public void setCod_unidad_medida_item(String cod_unidad_medida_item) {
        this.cod_unidad_medida_item = cod_unidad_medida_item;
    }

    public String getIndicador_ps_item() {
        return indicador_ps_item;
    }

    public void setIndicador_ps_item(String indicador_ps_item) {
        this.indicador_ps_item = indicador_ps_item;
    }

    public String getIndicador_trans_grat() {
        return indicador_trans_grat;
    }

    public void setIndicador_trans_grat(String indicador_trans_grat) {
        this.indicador_trans_grat = indicador_trans_grat;
    }

    public String getIndicador_afectacion_item() {
        return indicador_afectacion_item;
    }

    public void setIndicador_afectacion_item(String indicador_afectacion_item) {
        this.indicador_afectacion_item = indicador_afectacion_item;
    }

    public String getValor_venta_unitaria() {
        return valor_venta_unitaria;
    }

    public void setValor_venta_unitaria(String valor_venta_unitaria) {
        this.valor_venta_unitaria = valor_venta_unitaria;
    }

    public String getPrecio_venta_unitario_item() {
        return precio_venta_unitario_item;
    }

    public void setPrecio_venta_unitario_item(String precio_venta_unitario_item) {
        this.precio_venta_unitario_item = precio_venta_unitario_item;
    }

    public String getCantidad_item() {
        return cantidad_item;
    }

    public void setCantidad_item(String cantidad_item) {
        this.cantidad_item = cantidad_item;
    }

    public String getDescuento_item() {
        return descuento_item;
    }

    public void setDescuento_item(String descuento_item) {
        this.descuento_item = descuento_item;
    }

    public String getValor_item() {
        return valor_item;
    }

    public void setValor_item(String valor_item) {
        this.valor_item = valor_item;
    }

    public String getIgv_total_item() {
        return igv_total_item;
    }

    public void setIgv_total_item(String igv_total_item) {
        this.igv_total_item = igv_total_item;
    }

    public String getTotal_item() {
        return total_item;
    }

    public void setTotal_item(String total_item) {
        this.total_item = total_item;
    }
}