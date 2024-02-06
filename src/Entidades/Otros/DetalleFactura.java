/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Otros;

import Entidades.Detallees;

/**
 *
 * @author CRS
 */
public class DetalleFactura {
    
    private Detallees D; // Detalle en moneda por defecto en BD(Puede ser en dólares o soles, según se defina en BD para Repuestos y Detallees)
    private double Total;
    private int item;
    private double PrecioRepuesto;
    
    private double PrecioReferencial;
    private String modelo;

    /**
     * @return the D
     */
    public Detallees getD() {
        return D;
    }

    /**
     * @param D the D to set
     */
    public void setD(Detallees D) {
        this.D = D;
    }

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * @return the PrecioRepuesto
     */
    public double getPrecioRepuesto() {
        return PrecioRepuesto;
    }

    /**
     * @param PrecioRepuesto the PrecioRepuesto to set
     */
    public void setPrecioRepuesto(double PrecioRepuesto) {
        this.PrecioRepuesto = PrecioRepuesto;
    }

    public double getPrecioReferencial() {
        return PrecioReferencial;
    }

    public void setPrecioReferencial(double PrecioReferencial) {
        this.PrecioReferencial = PrecioReferencial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
