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
public class TipoServicioTecnico {
    
        private int id;
        private String tipo;
        private String descripcion;
        private String facturable;
        private String estado;

    public TipoServicioTecnico() {
    }

        
        
    public TipoServicioTecnico(int id, String tipo, String descripcion, String facturable, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.facturable = facturable;
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
        
        

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFacturable() {
        return facturable;
    }

    public void setFacturable(String facturable) {
        this.facturable = facturable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
        
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
}
