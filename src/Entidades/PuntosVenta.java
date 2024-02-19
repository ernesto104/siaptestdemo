/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author User
 */
public class PuntosVenta implements java.io.Serializable{

    
    
    int idpuntosventa;
    String descripcion;
    String estado;
    
    public PuntosVenta() {
    }
   
    
    
    public PuntosVenta(int idpuntosventa, String descripcion, String estado) {
        this.idpuntosventa = idpuntosventa;
        this.descripcion = descripcion;
        this.estado = estado;
    }


    
    public int getIdpuntosventa() {
        return idpuntosventa;
    }

    public void setIdpuntosventa(int idpuntosventa) {
        this.idpuntosventa = idpuntosventa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
