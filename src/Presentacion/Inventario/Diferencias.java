
package Presentacion.Inventario;

/**
 *
 * @author Lesly Aguilar
 */

public class Diferencias {
    
    int idLinea;
    String nroParte;
    String descripcion;
    double costo;
    int sistema;
    int inventario;
    int diferencia;
    double sobrante;
    double faltante;

    public Diferencias(int idLinea, String nroParte, String descripcion, double costo, int sistema, int inventario, int diferencia, double sobrante, double faltante) {
        this.idLinea = idLinea;
        this.nroParte = nroParte;
        this.descripcion = descripcion;
        this.costo = costo;
        this.sistema = sistema;
        this.inventario = inventario;
        this.diferencia = diferencia;
        this.sobrante = sobrante;
        this.faltante = faltante;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public String getNroParte() {
        return nroParte;
    }

    public void setNroParte(String nroParte) {
        this.nroParte = nroParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getDiferencia() {
        return diferencia;
    }

    public double getSobrante() {
        return sobrante;
    }

    public void setSobrante(double sobrante) {
        this.sobrante = sobrante;
    }

    public double getFaltante() {
        return faltante;
    }

    public void setFaltante(double faltante) {
        this.faltante = faltante;
    }

 
}
