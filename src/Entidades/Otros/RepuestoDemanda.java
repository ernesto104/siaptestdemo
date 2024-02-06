package Entidades.Otros;

/**
 *
 * @author Sidel
 */
public class RepuestoDemanda {
    private String idRepuesto;
    
    private String numeroRepuesto;
    private String codSecundario;
    private String descripcion;
    private String descrmodelo;
    private String stock;
    private String fobultimo;
    private String marca;
    
    private String mes;
    private String año;
    private String demanda;
    
    private String demandaMeses[];
    
    private String demandaTotal;
    private String demandaPromedio;

    public RepuestoDemanda() {
        demandaMeses = new String[12];
        for ( int i = 0; i < 12; i++ ) {
            demandaMeses[i] = "0";
        }
        demandaTotal = "0";
        demandaPromedio = "0.00";
    }

    public String[] getDemandaMeses() {
        return demandaMeses;
    }

    public void setDemandaMeses(String[] demandaMeses) {
        this.demandaMeses = demandaMeses;
    }
    
    public String getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(String idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getNumeroRepuesto() {
        return numeroRepuesto;
    }

    public void setNumeroRepuesto(String numeroRepuesto) {
        this.numeroRepuesto = numeroRepuesto;
    }

    public String getCodSecundario() {
        return codSecundario;
    }

    public void setCodSecundario(String codSecundario) {
        this.codSecundario = codSecundario;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getFobultimo() {
        return fobultimo;
    }

    public void setFobultimo(String fobultimo) {
        this.fobultimo = fobultimo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    public String getDemandaTotal() {
        return demandaTotal;
    }

    public void setDemandaTotal(String demandaTotal) {
        this.demandaTotal = demandaTotal;
    }

    public String getDemandaPromedio() {
        return demandaPromedio;
    }

    public void setDemandaPromedio(String demandaPromedio) {
        this.demandaPromedio = demandaPromedio;
    }

    public String getDemandaMeses(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDemandaMeses(String demandatxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDemanda(int d2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}