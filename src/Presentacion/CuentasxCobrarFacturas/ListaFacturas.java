package Presentacion.CuentasxCobrarFacturas;

/**
 *
 * @author Keily Mendiolaza
 */
public class ListaFacturas {
    public String empresa;
    public String titulo;
    
    public String tipDoc;
    public String numDoc;
    public String nroSerie;
    
    public String fecha;
    public String ruc;
    public String cliente;
    
    public String facturado;
    public String pagado;
    public String saldo;
    
    public String banco;
    
    public ListaFacturas(String tipDoc, String numDoc, String nroSerie, 
                         String fecha, String ruc, String cliente, 
                         String facturado, String pagado, String saldo,
                         String banco) {
        this.tipDoc = tipDoc;
        this.numDoc = numDoc;
        this.nroSerie = nroSerie;
        
        this.fecha = fecha;
        this.ruc = ruc;
        this.cliente = cliente;
        
        this.facturado = facturado;
        this.pagado = pagado;
        this.saldo = saldo;
        
        this.banco = banco;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}