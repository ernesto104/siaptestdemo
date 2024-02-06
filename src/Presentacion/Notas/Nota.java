
package Presentacion.Notas;

/**
 *
 * @author Lesly Aguilar
 */
public class Nota{
    
    String nroParte;
    int cantidad;
    String descripcion;
//    double valorVenta;
    String valorVenta;
    
    int cantInicial;
    double vvInicial;

//    public Nota(String nroParte, int cantidad, String descripcion, double valorVenta) {
    public Nota(String nroParte, int cantidad, String descripcion, String valorVenta
                , int cantInicial, double vvInicial) {
        this.nroParte = nroParte;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.valorVenta = valorVenta;
        
        this.cantInicial = cantInicial;
        this.vvInicial = vvInicial;
    }

//    public Nota(String descripcion, double valorVenta) {
    public Nota(String descripcion, String valorVenta) {
        this.descripcion = descripcion;
        this.valorVenta = valorVenta;
    }
    
    Nota() {
    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    public double getValorVenta() {
//        return valorVenta;
//    }
//
//    public void setValorVenta(double valorVenta) {
//        this.valorVenta = valorVenta;
//    }
    public String getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(String valorVenta) {
        this.valorVenta = valorVenta;
    }

    public int getCantInicial() {
        return cantInicial;
    }

    public void setCantInicial(int cantInicial) {
        this.cantInicial = cantInicial;
    }

    public double getVvInicial() {
        return vvInicial;
    }

    public void setVvInicial(double vvInicial) {
        this.vvInicial = vvInicial;
    }
}