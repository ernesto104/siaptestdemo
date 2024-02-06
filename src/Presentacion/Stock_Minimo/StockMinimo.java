package Presentacion.Stock_Minimo;

/**
 *
 * @author CRS
 */
public class StockMinimo {
    private String nroParte;
    private String codSec;
    private String descripcion;
    private String descrmodelo;
    private String marca;
    private String stock;
    private String stockMinimo;

    public StockMinimo(String nroParte, String codSec, String descripcion, String descrmodelo, String marca, String stock, String stockMinimo) {
        this.nroParte = nroParte;
        this.codSec = codSec;
        this.descripcion = descripcion;
        this.descrmodelo = descrmodelo;
        this.marca = marca;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
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

    public String getDescrmodelo() {
        return descrmodelo;
    }

    public void setDescrmodelo(String descrmodelo) {
        this.descrmodelo = descrmodelo;
    }

    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(String stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    
}
