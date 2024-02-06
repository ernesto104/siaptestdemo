package Presentacion.InventarioValorizado;

/**
 *
 * @author Keily Mendiolaza
 */
public class InventarioAlmacen {

    public String empresa;
    public String valorizacion;
    public String t1;
    public String t2;
    public String t3;
    public String t4;
    public String t5;
    public String nombre;
//    public String linea;
//    public String item;
    public String codigo;
    public String codigoSec;
    public String descripcion;
    public String stock;
//    public String precio;
    public String total;
    public String totalneto;
    public String aplicacion;
    public String descrModelo;
    public String descrLista;
    public String costo;
    public String idLinea;
    
    public String costoUnitario;
    public String precioVenta;
    public String desclista2;
    public String desclista;    
    
//    public InventarioAlmacen(String linea, String codigo, String codigosec, String descripcion, String stock, String precio, String total) {
//        this.linea = linea;
//        this.codigo = codigo;
//        this.codigosec = codigosec;
//        this.descripcion = descripcion;
//        this.stock = stock;
//        this.precio = precio;
//        this.total = total;
//    }
    
//    public InventarioAlmacen(String linea, String codigo, String descripcion, String aplicacion, String stock, String precio, String total) {
    
    // En el reporte Jasper las variables Field deben tener el mismo nombre que las variables de esta clase enviadas por constructor.
    public InventarioAlmacen(
//                             String item, 
                             String codigo, String descripcion, String descrModelo, 
                             String descrLista, String stock, String costo, String total, String idLinea,
                             String costoUnitario, String precioVenta, String codigoSec, String desclista2, String desclista) {
//        this.linea = linea;
//        this.item = item;
        this.codigo = codigo;
        this.codigoSec = codigoSec;
        this.descripcion = descripcion;
        this.desclista = desclista;
        this.descrModelo = descrModelo;
        this.descrLista = descrLista;
        this.stock = stock;
        this.costo = costo;
        this.total = total;
        this.idLinea = idLinea;
        
        this.costoUnitario = costoUnitario;
        this.precioVenta = precioVenta;
        this.desclista2 = desclista2;
//        this.aplicacion = aplicacion;
    }

 //   InventarioAlmacen(String codigo, String descripcion, String descrModelo, String descrLista, String stock, String costo, String total, String idLinea, String costoUnit, String precioVta, String codigoSec) {
  //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  //  }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(String valorizacion) {
        this.valorizacion = valorizacion;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

//    public String getLinea() {
//        return linea;
//    }
//
//    public void setLinea(String linea) {
//        this.linea = linea;
//    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public String getAplicacion() {
//        return aplicacion;
//    }
//
//    public void setAplicacion(String aplicacion) {
//        this.aplicacion = aplicacion;
//    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

//    public String getPrecio() {
//        return precio;
//    }
//
//    public void setPrecio(String precio) {
//        this.precio = precio;
//    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalneto() {
        return totalneto;
    }

    public void setTotalneto(String totalneto) {
        this.totalneto = totalneto;
    }

//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }

    public String getDescrModelo() {
        return descrModelo;
    }

    public void setDescrModelo(String descrModelo) {
        this.descrModelo = descrModelo;
    }

    public String getDescrLista() {
        return descrLista;
    }

    public void setDescrLista(String descrLista) {
        this.descrLista = descrLista;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

    public String getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(String costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(String codigoSec) {
        this.codigoSec = codigoSec;
    }
    
    public String getDesclista2() {
        return desclista2;
    }

    public void setDesclista2(String desclista2) {
        this.desclista2 = desclista2;
    }

    public String getDesclista() {
        return desclista;
    }

    public void setDesclista(String desclista) {
        this.desclista = desclista;
    }
    

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }
    
    
}