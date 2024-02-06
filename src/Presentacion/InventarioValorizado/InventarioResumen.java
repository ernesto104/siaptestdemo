package Presentacion.InventarioValorizado;

/**
 *
 * @author Ledis Rivera
 */
public class InventarioResumen {

    public String empresa;
    public String valorizacion;
    public String t1;
    public String t2;
    public String t3;
    public String t4;
    public String t5;
    
    public String numLinea;
    public String linea;
    public String total;
    public String totalneto;

    public InventarioResumen(String numLinea, String linea, String total) {
        this.numLinea = numLinea;
        this.linea = linea;
        this.total = total;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

    public String getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(String numLinea) {
        this.numLinea = numLinea;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalneto() {
        return totalneto;
    }

    public void setTotalneto(String totalneto) {
        this.totalneto = totalneto;
    }
}
