package Presentacion.ResumenGeneralCliente;

/**
 *
 * @author usuario
 */
public class Detalle_L {
    String nroLetra;
    String nroBanco;
    String fechaEmision;
    String fechaVcto;
    String dias;
    String importe;
    String referencia;

    public Detalle_L(String nroLetra, String nroBanco, String fechaEmision, String fechaVcto, String dias, String importe, String referencia) {
        this.nroLetra = nroLetra;
        this.nroBanco = nroBanco;
        this.fechaEmision = fechaEmision;
        this.fechaVcto = fechaVcto;
        this.dias = dias;
        this.importe = importe;
        this.referencia = referencia;
    }

    public String getNroLetra() {
        return nroLetra;
    }

    public void setNroLetra(String nroLetra) {
        this.nroLetra = nroLetra;
    }

    public String getNroBanco() {
        return nroBanco;
    }

    public void setNroBanco(String nroBanco) {
        this.nroBanco = nroBanco;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVcto() {
        return fechaVcto;
    }

    public void setFechaVcto(String fechaVcto) {
        this.fechaVcto = fechaVcto;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    
}
