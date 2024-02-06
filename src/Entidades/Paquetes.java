package Entidades;
// Generated 17/09/2013 02:56:16 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;


public class Paquetes  implements java.io.Serializable {


     private Integer idpaquete;
     private String descripcion;
     private Set paquetesRepuestoses = new HashSet(0);

    public Paquetes() {
        
    }

    public Paquetes(String descripcion, Set paquetesRepuestoses) {
       this.descripcion = descripcion;
       this.paquetesRepuestoses = paquetesRepuestoses;
    }
   
    public Integer getIdpaquete() {
        return this.idpaquete;
    }
    
    public void setIdpaquete(Integer idpaquete) {
        this.idpaquete = idpaquete;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getPaquetesRepuestoses() {
        return this.paquetesRepuestoses;
    }
    
    public void setPaquetesRepuestoses(Set paquetesRepuestoses) {
        this.paquetesRepuestoses = paquetesRepuestoses;
    }




}


