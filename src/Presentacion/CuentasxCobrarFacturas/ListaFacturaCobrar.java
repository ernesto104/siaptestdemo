package Presentacion.CuentasxCobrarFacturas;

/**
 *
 * @author Ledis Rivera Ch.
 */
public class ListaFacturaCobrar {
    
    public String empresa;
    public String titulo;
    public String num;
    public String doc;
    public String fecha;
    public String ruc;
    public String cliente;
    public String facturado;    
    public String saldo;

    public ListaFacturaCobrar(String num, String doc, String fecha, String ruc, String cliente, String facturado,String saldo) {        
        this.num = num;
        this.doc = doc;
        this.fecha = fecha;
        this.ruc = ruc;
        this.cliente = cliente;
        this.facturado = facturado;        
        this.saldo = saldo;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
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

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}