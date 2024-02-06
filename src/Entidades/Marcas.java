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
public class Marcas implements java.io.Serializable {
 
    private int idmarca;
    private Equipos equipo;
    private String descripcion;
    private String estado;
    
    
     public Marcas() {
    }
     
     
     public Marcas(int idmarca, Equipos equipo, String descripcion, String estado) {
        this.idmarca = idmarca;
        this.equipo = equipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
     
    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public Equipos getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipos equipo) {
        this.equipo = equipo;
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
