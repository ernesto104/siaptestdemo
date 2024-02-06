package Entidades.Importaciones;
public class FormatoOrdenCompra {
    private String numItem;
    private String partidaarancelaria;
    private String idLinea;
    private String numparte;
    private String descripcion;
    private String cantpedida;
    private String fob;
    private String fobtotal;
    public FormatoOrdenCompra(String numItem, String partidaarancelaria, String idLinea, String numparte, String descripcion, String cantpedida, String fob, String fobtotal) {
        this.numItem = numItem;
        this.partidaarancelaria = partidaarancelaria;
        this.idLinea=idLinea;
        this.numparte = numparte;
        this.descripcion = descripcion;
        this.cantpedida = cantpedida;
        this.fob = fob;
        this.fobtotal = fobtotal;
    }
    public FormatoOrdenCompra() {
    }
    public String getNumItem() {
        return numItem;
    }
    public void setNumItem(String numItem) {
        this.numItem = numItem;
    }
    public String getPartidaarancelaria() {
        return partidaarancelaria;
    }
    public void setPartidaarancelaria(String partidaarancelaria) {
        this.partidaarancelaria = partidaarancelaria;
    }
    public String getIdLinea() {
        return idLinea;
    }
    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }
    public String getNumparte() {
        return numparte;
    }
    public void setNumparte(String numparte) {
        this.numparte = numparte;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCantpedida() {
        return cantpedida;
    }
    public void setCantpedida(String cantpedida) {
        this.cantpedida = cantpedida;
    }
    public String getFob() {
        return fob;
    }
    public void setFob(String fob) {
        this.fob = fob;
    }
    public String getFobtotal() {
        return fobtotal;
    }
    public void setFobtotal(String fobtotal) {
        this.fobtotal = fobtotal;
    }
}