package Presentacion.CuentasxCobrarLetras;

/**
 *
 * @author Administrator
 */
public class ListaLetras {

    private String cliente;
    private String numLetra;
    private String fechaEmision;
    private String fechaVence;
    private String emitido;
    private String saldo;
    private String diasVencido;
    private String facturaRef;
    
    private String numRenovacion;
    private String numLetraBanco;
    private String bancoLetra;

    public ListaLetras(String cliente, String numLetra, String fechaEmision, String fechaVence, String emitido, String saldo, String diasVencido, String facturaRef,
                       String numRenovacion, String numLetraBanco, String bancoLetra) {
        this.cliente = cliente;
        this.numLetra = numLetra;
        this.fechaEmision = fechaEmision;
        this.fechaVence = fechaVence;
        this.emitido = emitido;
        this.saldo = saldo;
        this.diasVencido = diasVencido;
        this.facturaRef = facturaRef;
        this.numRenovacion = numRenovacion;
        this.numLetraBanco = numLetraBanco;
        this.bancoLetra = bancoLetra;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumLetra() {
        return numLetra;
    }

    public void setNumLetra(String numLetra) {
        this.numLetra = numLetra;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(String fechaVence) {
        this.fechaVence = fechaVence;
    }

    public String getEmitido() {
        return emitido;
    }

    public void setEmitido(String emitido) {
        this.emitido = emitido;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getDiasVencido() {
        return diasVencido;
    }

    public void setDiasVencido(String diasVencido) {
        this.diasVencido = diasVencido;
    }

    public String getFacturaRef() {
        return facturaRef;
    }

    public void setFacturaRef(String facturaRef) {
        this.facturaRef = facturaRef;
    }

    public String getNumRenovacion() {
        return numRenovacion;
    }

    public void setNumRenovacion(String numRenovacion) {
        this.numRenovacion = numRenovacion;
    }

    public String getNumLetraBanco() {
        return numLetraBanco;
    }

    public void setNumLetraBanco(String numLetraBanco) {
        this.numLetraBanco = numLetraBanco;
    }

    public String getBancoLetra() {
        return bancoLetra;
    }

    public void setBancoLetra(String bancoLetra) {
        this.bancoLetra = bancoLetra;
    }
}