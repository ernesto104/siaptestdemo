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
public class Modelos implements java.io.Serializable {
    
    private int idmodelo;
    private Equipos equipo;
    private Marcas marca;
    private String descripcion;
    private String estado; 
    
    public Modelos() {
    }
    
    public Modelos(int idmodelo, Equipos equipo, Marcas marca, String descripcion, String estado) {
        this.idmodelo = idmodelo;
        this.equipo = equipo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    
    public int getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(int idmodelo) {
        this.idmodelo = idmodelo;
    }

    public Equipos getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipos equipo) {
        this.equipo = equipo;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
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
