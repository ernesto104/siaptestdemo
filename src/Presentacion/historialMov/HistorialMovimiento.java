/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion.historialMov;

/**
 *
 * @author PC-14
 */
public class HistorialMovimiento {
    
    String nroDoc;
    String fechaMov;
    String tipoDoc;
    String ingresos;
    String salidas;
    String total;
    String detalle;

    public HistorialMovimiento(String nroDoc, String fechaMov, String tipoDoc, String ingresos, String salidas, String total, String detalle) {
        this.nroDoc = nroDoc;
        this.fechaMov = fechaMov;
        this.tipoDoc = tipoDoc;
        this.ingresos = ingresos;
        this.salidas = salidas;
        this.total = total;
        this.detalle = detalle;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(String fechaMov) {
        this.fechaMov = fechaMov;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getIngresos() {
        return ingresos;
    }

    public void setIngresos(String ingresos) {
        this.ingresos = ingresos;
    }

    public String getSalidas() {
        return salidas;
    }

    public void setSalidas(String salidas) {
        this.salidas = salidas;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

  
    
    
    
    
}
