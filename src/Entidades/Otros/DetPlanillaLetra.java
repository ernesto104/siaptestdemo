package Entidades.Otros;

/**
 *
 * @author Ledis Rivera Ch.
 */
public class DetPlanillaLetra {
    private String item;
    private String numLetOFact;
    private String cliente;
    private String ruc;
    
    private String aval;
    
    private String lugarPago;
    private String vencimiento;
    private String importe;

    public DetPlanillaLetra(String item, String numLetOFact, String cliente, String ruc, 
                            String aval,
                            String lugarPago, String vencimiento, String importe) {
        this.item = item;
        this.numLetOFact = numLetOFact;
        this.cliente = cliente;
        this.ruc = ruc;
        
        this.aval = aval;
        
        this.lugarPago = lugarPago;
        this.vencimiento = vencimiento;
        this.importe = importe;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNumLetOFact() {
        return numLetOFact;
    }

    public void setNumLetOFact(String numLetOFact) {
        this.numLetOFact = numLetOFact;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAval() {
        return aval;
    }

    public void setAval(String aval) {
        this.aval = aval;
    }

    public String getLugarPago() {
        return lugarPago;
    }

    public void setLugarPago(String lugarPago) {
        this.lugarPago = lugarPago;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }    
}
