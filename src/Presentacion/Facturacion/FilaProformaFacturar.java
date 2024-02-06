package Presentacion.Facturacion;

public class FilaProformaFacturar {
    
    String idCabProforma;
    
    String numeroProforma;
    String cliente;
    String fechaProforma;
    String tipoMoneda;
    String total;
    String estado;

    public FilaProformaFacturar(String idCabProforma,
                                String numeroProforma, String cliente, String fechaProforma, 
                                String tipoMoneda, String total, String estado) {
        
        this.idCabProforma = idCabProforma;
        
        this.numeroProforma = numeroProforma;
        this.cliente = cliente;
        this.fechaProforma = fechaProforma;
        this.tipoMoneda = tipoMoneda;
        this.total = total;
        this.estado = estado;
    }

    public String getIdCabProforma() {
        return idCabProforma;
    }

    public void setIdCabProforma(String idCabProforma) {
        this.idCabProforma = idCabProforma;
    }
    
    public String getNumeroProforma() {
        return numeroProforma;
    }

    public void setNumeroProforma(String numeroProforma) {
        this.numeroProforma = numeroProforma;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFechaProforma() {
        return fechaProforma;
    }

    public void setFechaProforma(String fechaProforma) {
        this.fechaProforma = fechaProforma;
    }
    
    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}