/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Otros;

import com.toedter.calendar.JDateChooser;
import java.util.Date;

/**
 *
 * @author CRS
 */
public class NroLetras {
    private int nro;
    private double importe;
    private int diasVcto;
    private JDateChooser fechaVec;
    private int nroRenovacion;
        
    public NroLetras(){
        fechaVec=new JDateChooser();
    }

    /**
     * @return the nro
     */
    public int getNro() {
        return nro;
    }

    /**
     * @param nro the nro to set
     */
    public void setNro(int nro) {
        this.nro = nro;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the diasVcto
     */
    public int getDiasVcto() {
        return diasVcto;
    }

    /**
     * @param diasVcto the diasVcto to set
     */
    public void setDiasVcto(int diasVcto) {
        this.diasVcto = diasVcto;
    }

    /**
     * @return the fechaVec
     */
    public JDateChooser getFechaVec() {
        return fechaVec;
    }

    /**
     * @param fechaVec the fechaVec to set
     */
    public void setFechaVec(Date fechaVec) {
        this.fechaVec.setDate(fechaVec);
    }

    public int getNroRenovacion() {
        return nroRenovacion;
    }

    public void setNroRenovacion(int nroRenovacion) {
        this.nroRenovacion = nroRenovacion;
    }

    
}
