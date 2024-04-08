package Presentacion.IngresoSalidas;

/**
 *
 * @author Ledis Rivera Changra
 */
public class DetalleIngSal {
    String nroParte;
    String codSec;
    String descripcion;
    String cantidad;
    String precioLista;
    String total;
    String descrModelo;
    String equipo;
    String marca;
    String modelo;

    //nuevo constructor con Equipo, Marca y Modelo
    public DetalleIngSal(String nroParte, String codSec, String descripcion, String cantidad, String precioLista, String total, String descrModelo, String equipo, String marca, String modelo) {
        this.nroParte = nroParte;
        this.codSec = codSec;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioLista = precioLista;
        this.total = total;
        this.descrModelo = descrModelo;
        this.equipo = equipo;
        this.marca = marca;
        this.modelo = modelo;
    }
    
    public DetalleIngSal(String nroParte, String codSec, String descripcion, String cantidad, String precioLista, String total, String descrModelo) {
        this.nroParte = nroParte;
        this.codSec = codSec;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioLista = precioLista;
        this.total = total;
        this.descrModelo = descrModelo;
    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public String getCodSec() {
        return codSec;
    }

    public void setCodSec(String codSec) {
        this.codSec = codSec;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(String precioLista) {
        this.precioLista = precioLista;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDescrModelo() {
        return descrModelo;
    }

    public void setDescrModelo(String descrModelo) {
        this.descrModelo = descrModelo;
    }
    
    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}