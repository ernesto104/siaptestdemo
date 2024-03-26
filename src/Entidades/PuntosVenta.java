/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author User
 */
public class PuntosVenta implements java.io.Serializable{

    
    
    int idpuntosventa;
    String descripcion;
    String estado;
    private Set<Cabeces> cabeceses = new HashSet<>(0);
    private Set<Cabecproformas> cabecproformases = new HashSet<>(0);
    private Set<Cabecsalvar> cabecsalvars = new HashSet<>(0);

    
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
    
    public Set<Cabeces> getCabeceses() {
        return cabeceses;
    }

    public void setCabeceses(Set<Cabeces> cabeceses) {
        this.cabeceses = cabeceses;
    }

    public Set<Cabecproformas> getCabecproformases() {
        return cabecproformases;
    }

    public void setCabecproformases(Set<Cabecproformas> cabecproformases) {
        this.cabecproformases = cabecproformases;
    }

    public Set<Cabecsalvar> getCabecsalvars() {
        return cabecsalvars;
    }

    public void setCabecsalvars(Set<Cabecsalvar> cabecsalvars) {
        this.cabecsalvars = cabecsalvars;
    }

}
