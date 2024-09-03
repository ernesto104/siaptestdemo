package Presentacion.PlanillaElectronica;

import Presentacion.EmisionPlanillas.*;

/**
 *
 * @author CRS
 */
public class FilaPlanilla {
    String tipo;
    String numDoc;
    String fecha;
    String ruc;
    String cliente;
    String total;

    public FilaPlanilla(String tipo, String numDoc, String fecha, String ruc, String cliente, String total) {
        this.tipo = tipo;
        this.numDoc = numDoc;
        this.fecha = fecha;
        this.ruc = ruc;
        this.cliente = cliente;
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
}
