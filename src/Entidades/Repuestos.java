package Entidades;
// Generated 17/09/2013 02:56:16 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Repuestos generated by hbm2java
 */
public class Repuestos  implements java.io.Serializable {


     private RepuestosId id;
     private Estratificacion estratificacion;
     private Equipos equipos;
     private String codrepuesto;
     private String codigoseg;
     private Integer nroalternativo;
     private String descripcion;
     private Double preciolista;
     private Double costopromedio;
     private String descrmodelo;
     private String desclista;
     private String desclista2;
     private Double pcostoultimo;
     private Date fechapcosto;
     private String aplicacion;
     private String idrepuestoant;
     private String marca;
     private String unidadmedida;
     private String partidarancel;
     private Double fobultimo;
     private Integer stock;
     private String ubicalmacen;
     private String unidadventa;
     private Double pcostotemporal;
     private String filtro1;
     private String filtro2;
     private String filtro3;
     private String filtro4;
     private Integer inventario;
     private Integer stockminimo;
     private Double margenutil;
     private Date fecharegistro;
     
     private String imagen;
     
     private Set paquetesRepuestoses = new HashSet(0);
     private Set pedidowebs = new HashSet(0);
     private Set detalleproformases = new HashSet(0);
     private Set detallenotas = new HashSet(0);
     private Set detallesugeridos = new HashSet(0);
     private Set detalleeses = new HashSet(0);
     private String usuario;

    public Repuestos() {
    }

	
    public Repuestos(RepuestosId id, Equipos equipos, String codrepuesto) {
        this.id = id;
        this.equipos = equipos;
        this.codrepuesto = codrepuesto;
    }
    public Repuestos(RepuestosId id, Estratificacion estratificacion, Equipos equipos, String codrepuesto, String codigoseg, Integer nroalternativo, String descripcion, Double preciolista, Double costopromedio, String descrmodelo, String desclista, String desclista2, Double pcostoultimo, Date fechapcosto, String aplicacion, String idrepuestoant, String marca, String unidadmedida, String partidarancel, Double fobultimo, Integer stock, String ubicalmacen, String unidadventa, Double pcostotemporal, String filtro1, String filtro2, String filtro3, String filtro4, Integer inventario, Integer stockminimo, Double margenutil, Date fecharegistro, Set paquetesRepuestoses, Set pedidowebs, Set detalleproformases, Set detallenotas, Set detallesugeridos, Set detalleeses,
                     String imagen, String usuario) {
       this.id = id;
       this.estratificacion = estratificacion;
       this.equipos = equipos;
       this.codrepuesto = codrepuesto;
       this.codigoseg = codigoseg;
       this.nroalternativo = nroalternativo;
       this.descripcion = descripcion;
       this.preciolista = preciolista;
       this.costopromedio = costopromedio;
       this.descrmodelo = descrmodelo;
       this.desclista = desclista;
       this.desclista2 = desclista2;
       this.pcostoultimo = pcostoultimo;
       this.fechapcosto = fechapcosto;
       this.aplicacion = aplicacion;
       this.idrepuestoant = idrepuestoant;
       this.marca = marca;
       this.unidadmedida = unidadmedida;
       this.partidarancel = partidarancel;
       this.fobultimo = fobultimo;
       this.stock = stock;
       this.ubicalmacen = ubicalmacen;
       this.unidadventa = unidadventa;
       this.pcostotemporal = pcostotemporal;
       this.filtro1 = filtro1;
       this.filtro2 = filtro2;
       this.filtro3 = filtro3;
       this.filtro4 = filtro4;
       this.inventario = inventario;
       this.stockminimo = stockminimo;
       this.margenutil = margenutil;
       this.fecharegistro = fecharegistro;
       this.paquetesRepuestoses = paquetesRepuestoses;
       this.pedidowebs = pedidowebs;
       this.detalleproformases = detalleproformases;
       this.detallenotas = detallenotas;
       this.detallesugeridos = detallesugeridos;
       this.detalleeses = detalleeses;
       
       this.imagen = imagen;
       this.usuario = usuario;
    }
   
    public RepuestosId getId() {
        return this.id;
    }
    
    public void setId(RepuestosId id) {
        this.id = id;
    }
    public Estratificacion getEstratificacion() {
        return this.estratificacion;
    }
    
    public void setEstratificacion(Estratificacion estratificacion) {
        this.estratificacion = estratificacion;
    }
    public Equipos getEquipos() {
        return this.equipos;
    }
    
    public void setEquipos(Equipos equipos) {
        this.equipos = equipos;
    }
    public String getCodrepuesto() {
        return this.codrepuesto;
    }
    
    public void setCodrepuesto(String codrepuesto) {
        this.codrepuesto = codrepuesto;
    }
    public String getCodigoseg() {
        return this.codigoseg;
    }
    
    public void setCodigoseg(String codigoseg) {
        this.codigoseg = codigoseg;
    }
    public Integer getNroalternativo() {
        return this.nroalternativo;
    }
    
    public void setNroalternativo(Integer nroalternativo) {
        this.nroalternativo = nroalternativo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Double getPreciolista() {
        return this.preciolista;
    }
    
    public void setPreciolista(Double preciolista) {
        this.preciolista = preciolista;
    }
    public Double getCostopromedio() {
        return this.costopromedio;
    }
    
    public void setCostopromedio(Double costopromedio) {
        this.costopromedio = costopromedio;
    }
    public String getDescrmodelo() {
        return this.descrmodelo;
    }
    
    public void setDescrmodelo(String descrmodelo) {
        this.descrmodelo = descrmodelo;
    }
    public String getDesclista() {
        return this.desclista;
    }
    
    public void setDesclista(String desclista) {
        this.desclista = desclista;
    }

    public String getDesclista2() {
        return desclista2;
    }

    public void setDesclista2(String desclista2) {
        this.desclista2 = desclista2;
    }
    
    public Double getPcostoultimo() {
        return this.pcostoultimo;
    }
    
    public void setPcostoultimo(Double pcostoultimo) {
        this.pcostoultimo = pcostoultimo;
    }
    public Date getFechapcosto() {
        return this.fechapcosto;
    }
    
    public void setFechapcosto(Date fechapcosto) {
        this.fechapcosto = fechapcosto;
    }
    public String getAplicacion() {
        return this.aplicacion;
    }
    
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }
    public String getIdrepuestoant() {
        return this.idrepuestoant;
    }
    
    public void setIdrepuestoant(String idrepuestoant) {
        this.idrepuestoant = idrepuestoant;
    }
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getUnidadmedida() {
        return this.unidadmedida;
    }
    
    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }
    public String getPartidarancel() {
        return this.partidarancel;
    }
    
    public void setPartidarancel(String partidarancel) {
        this.partidarancel = partidarancel;
    }
    public Double getFobultimo() {
        return this.fobultimo;
    }
    
    public void setFobultimo(Double fobultimo) {
        this.fobultimo = fobultimo;
    }
    public Integer getStock() {
        return this.stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getUbicalmacen() {
        return this.ubicalmacen;
    }
    
    public void setUbicalmacen(String ubicalmacen) {
        this.ubicalmacen = ubicalmacen;
    }
    public String getUnidadventa() {
        return this.unidadventa;
    }
    
    public void setUnidadventa(String unidadventa) {
        this.unidadventa = unidadventa;
    }
    public Double getPcostotemporal() {
        return this.pcostotemporal;
    }
    
    public void setPcostotemporal(Double pcostotemporal) {
        this.pcostotemporal = pcostotemporal;
    }
    public String getFiltro1() {
        return this.filtro1;
    }
    
    public void setFiltro1(String filtro1) {
        this.filtro1 = filtro1;
    }
    public String getFiltro2() {
        return this.filtro2;
    }
    
    public void setFiltro2(String filtro2) {
        this.filtro2 = filtro2;
    }
    public String getFiltro3() {
        return this.filtro3;
    }
    
    public void setFiltro3(String filtro3) {
        this.filtro3 = filtro3;
    }
    public String getFiltro4() {
        return this.filtro4;
    }
    
    public void setFiltro4(String filtro4) {
        this.filtro4 = filtro4;
    }
    public Integer getInventario() {
        return this.inventario;
    }
    
    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }
    public Integer getStockminimo() {
        return this.stockminimo;
    }
    
    public void setStockminimo(Integer stockminimo) {
        this.stockminimo = stockminimo;
    }
    public Double getMargenutil() {
        return this.margenutil;
    }
    
    public void setMargenutil(Double margenutil) {
        this.margenutil = margenutil;
    }
    public Date getFecharegistro() {
        return this.fecharegistro;
    }
    
    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    public Set getPaquetesRepuestoses() {
        return this.paquetesRepuestoses;
    }
    
    public void setPaquetesRepuestoses(Set paquetesRepuestoses) {
        this.paquetesRepuestoses = paquetesRepuestoses;
    }
    public Set getPedidowebs() {
        return this.pedidowebs;
    }
    
    public void setPedidowebs(Set pedidowebs) {
        this.pedidowebs = pedidowebs;
    }
    public Set getDetalleproformases() {
        return this.detalleproformases;
    }
    
    public void setDetalleproformases(Set detalleproformases) {
        this.detalleproformases = detalleproformases;
    }
    public Set getDetallenotas() {
        return this.detallenotas;
    }
    
    public void setDetallenotas(Set detallenotas) {
        this.detallenotas = detallenotas;
    }
    public Set getDetallesugeridos() {
        return this.detallesugeridos;
    }
    
    public void setDetallesugeridos(Set detallesugeridos) {
        this.detallesugeridos = detallesugeridos;
    }
    public Set getDetalleeses() {
        return this.detalleeses;
    }
    
    public void setDetalleeses(Set detalleeses) {
        this.detalleeses = detalleeses;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }    
}