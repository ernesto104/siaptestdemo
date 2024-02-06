package Entidades;
// Generated 17/09/2013 02:56:16 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cabecweb generated by hbm2java
 */
public class Cabecweb  implements java.io.Serializable {


     private Integer idCabweb;
     private Usuarios usuarios;
     private Clientes clientes;
     private Date fecha;
     private Double total;
     private String tipoperacion;
     private String estado;
     private String tipodocumento;
     private Date hora;
     private String observaciones;
     private String solicitante;
     private Set pedidowebs = new HashSet(0);

    public Cabecweb() {
    }

    public Cabecweb(Usuarios usuarios, Clientes clientes, Date fecha, Double total, String tipoperacion, String estado, String tipodocumento, Date hora, String observaciones, String solicitante, Set pedidowebs) {
       this.usuarios = usuarios;
       this.clientes = clientes;
       this.fecha = fecha;
       this.total = total;
       this.tipoperacion = tipoperacion;
       this.estado = estado;
       this.tipodocumento = tipodocumento;
       this.hora = hora;
       this.observaciones = observaciones;
       this.solicitante = solicitante;
       this.pedidowebs = pedidowebs;
    }
   
    public Integer getIdCabweb() {
        return this.idCabweb;
    }
    
    public void setIdCabweb(Integer idCabweb) {
        this.idCabweb = idCabweb;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getTipoperacion() {
        return this.tipoperacion;
    }
    
    public void setTipoperacion(String tipoperacion) {
        this.tipoperacion = tipoperacion;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipodocumento() {
        return this.tipodocumento;
    }
    
    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getSolicitante() {
        return this.solicitante;
    }
    
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    public Set getPedidowebs() {
        return this.pedidowebs;
    }
    
    public void setPedidowebs(Set pedidowebs) {
        this.pedidowebs = pedidowebs;
    }




}

