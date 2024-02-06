package Presentacion.ResumenGeneralCliente;

/**
 *
 * @author usuario
 */
public class ResumenGeneral {
    String tipoDoc;
    String dolares;
    String soles;

    public ResumenGeneral(String tipoDoc, String dolares, String soles) {
        this.tipoDoc = tipoDoc;
        this.dolares = dolares;
        this.soles = soles;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDolares() {
        return dolares;
    }

    public void setDolares(String dolares) {
        this.dolares = dolares;
    }

    public String getSoles() {
        return soles;
    }

    public void setSoles(String soles) {
        this.soles = soles;
    }
    
    
}
