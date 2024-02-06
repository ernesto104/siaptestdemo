package Entidades.Importaciones;
public class FormatoValorizacion {
    private String glosa;
    private String monto;
    public FormatoValorizacion(String glosa, String monto) {
        this.glosa = glosa;
        this.monto = monto;
    }
    public FormatoValorizacion() {
    }
    public String getGlosa() {
        return glosa;
    }
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
}