package Entidades.Importaciones;
public class FormatoImpSugerido {
    private String numItem;
    private String codLinea;
    private String numParte;
    private String codSec;
    private String descripcion;
    private String cantPedida;
    private String precioCosto;
    private String costoTotal;
    private String fob;
    private String fobTotal;
    public FormatoImpSugerido() {
    }
    public FormatoImpSugerido(String numItem, String codLinea, String numParte, String codSec, String descripcion, String cantPedida, String precioCosto, String costoTotal, String fob, String fobTotal) {
        this.numItem = numItem;
        this.codLinea = codLinea;
        this.numParte = numParte;
        this.codSec = codSec;
        this.descripcion = descripcion;
        this.cantPedida = cantPedida;
        this.precioCosto = precioCosto;
        this.costoTotal = costoTotal;
        this.fob = fob;
        this.fobTotal = fobTotal;
    }
    public String getNumItem() {
        return numItem;
    }
    public void setNumItem(String numItem) {
        this.numItem = numItem;
    }
    public String getCodLinea() {
        return codLinea;
    }
    public void setCodLinea(String codLinea) {
        this.codLinea = codLinea;
    }
    public String getNumParte() {
        return numParte;
    }
    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }
    public String getCodSec() {
        return codSec;
    }
    public void setCodSec(String codSec) {
        this.codSec = codSec;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCantPedida() {
        return cantPedida;
    }
    public void setCantPedida(String cantPedida) {
        this.cantPedida = cantPedida;
    }
    public String getPrecioCosto() {
        return precioCosto;
    }
    public void setPrecioCosto(String precioCosto) {
        this.precioCosto = precioCosto;
    }
    public String getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }
    public String getFob() {
        return fob;
    }
    public void setFob(String fob) {
        this.fob = fob;
    }
    public String getFobTotal() {
        return fobTotal;
    }
    public void setFobTotal(String fobTotal) {
        this.fobTotal = fobTotal;
    }
}