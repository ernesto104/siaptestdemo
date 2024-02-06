package Entidades.Importaciones;
public class FormatoNotaIngreso {
    private String numItem;
    private String codRepuesto;
    private String numLinea;
    private String descripcion;
    private String precioCosto;
    private String cantidad;
    private String costoTotal;
    public FormatoNotaIngreso(String numItem, String codRepuesto, String numLinea, String descripcion, String precioCosto, String cantidad, String costoTotal) {
        this.numItem = numItem;
        this.codRepuesto=codRepuesto;
        this.numLinea = numLinea;
        this.descripcion = descripcion;
        this.precioCosto = precioCosto;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }
    public FormatoNotaIngreso() {
    }
    public String getNumItem() {
        return numItem;
    }
    public void setNumItem(String numItem) {
        this.numItem = numItem;
    }
    public String getCodRepuesto(){
        return codRepuesto;
    }
    public void setCodRepuesto(String codRepuesto){
        this.codRepuesto=codRepuesto;
    }
    public String getNumLinea() {
        return numLinea;
    }
    public void setNumLinea(String numLinea) {
        this.numLinea = numLinea;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPrecioCosto() {
        return precioCosto;
    }
    public void setPrecioCosto(String precioCosto) {
        this.precioCosto = precioCosto;
    }
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public String getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }
}