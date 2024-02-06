/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Comision;

import Entidades.Cabeces;

/**
 *
 * @author CRS
 */
public class Fila_Tabla_comision {
    private Cabeces c;
    private String Pagado;
    private boolean seleccionado;

    public Fila_Tabla_comision(Cabeces c, String Pagado, boolean seleccionado) {
        this.c = c;
        this.Pagado = Pagado;
        this.seleccionado = seleccionado;
    }
    
    
    /**
     * @return the c
     */
    public Cabeces getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Cabeces c) {
        this.c = c;
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    /**
     * @return the Pagado
     */
    public String getPagado() {
        return Pagado;
    }

    /**
     * @param Pagado the Pagado to set
     */
    public void setPagado(String Pagado) {
        this.Pagado = Pagado;
    }
    
}
