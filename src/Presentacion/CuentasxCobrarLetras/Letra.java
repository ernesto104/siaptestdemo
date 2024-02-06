package Presentacion.CuentasxCobrarLetras;

/**
 *
 * @author Keily Mendiolaza
 */
public class Letra {
    
    // Para Impresión por Excel (2° Versión de C&S)
    public String empresa;
    public String titulo;
    public String numLetra;
    public String letra_facbol;
    public String letra_facbol2;
    public String lugar_giro;
    
    public String fechaGiro;  //Para Impresión por Excel (2° Versión de C&S)
    public String fechaVencimiento;   //Para Impresión por Excel (2° Versión de C&S)
    
    public String importe;
    public String cliente;
    public String domicilio;
    
    public String localidad;  //Para Impresión por Excel (2° Versión de C&S)
    
    public String ruc;
    public String telefijo;
    public String importeEnTexto;
    
    // Para Impresión de Jasper (Versión 1° de M&D)
    public String fecha;

    // Para Impresión de Excel (Versión 2° de C&S)
    public Letra(String numLetra, String letra_facbol, String letra_facbol2,
                 String lugar_giro, String fechaGiro, String fechaVencimiento,
                 String importe, String cliente,
                 String domicilio, String localidad, String ruc, String telefijo,
                 String importeEnTexto,
                 String empresa) {
    
//     Para Impresión por Jasper (1° Versión de M&D)
//    public Letra(String numLetra, String letra_facbol, String letra_facbol2,
//                 String lugar_giro, String fecha, 
//                 String importe, String cliente,
//                 String domicilio, String ruc, String telefijo,
//                 String importeEnTexto,
//                 String empresa) {

        this.numLetra = numLetra;
        this.letra_facbol = letra_facbol;
        this.letra_facbol2 = letra_facbol2;
        this.lugar_giro = lugar_giro;
        
        this.fechaGiro = fechaGiro;
        this.fechaVencimiento = fechaVencimiento;
        
        this.importe = importe;
        
        this.cliente = cliente;
        this.domicilio = domicilio;
//        this.localidad = localidad;
        this.ruc = ruc;
        this.telefijo = telefijo;
        
        this.importeEnTexto = importeEnTexto;
        this.empresa = empresa;
        
        // Para Impresión de Jasper (Versión 1° de M&D)
//        this.fecha = fecha;
    }

    public String getNumLetra() {
        return numLetra;
    }

    public void setNumLetra(String numLetra) {
        this.numLetra = numLetra;
    }

    public String getLetra_facbol() {
        return letra_facbol;
    }

    public void setLetra_facbol(String letra_facbol) {
        this.letra_facbol = letra_facbol;
    }

    public String getLetra_facbol2() {
        return letra_facbol2;
    }

    public void setLetra_facbol2(String letra_facbol2) {
        this.letra_facbol2 = letra_facbol2;
    }
    
    public String getLugar_giro() {
        return lugar_giro;
    }

    public void setLugar_giro(String lugar_giro) {
        this.lugar_giro = lugar_giro;
    }

    public String getFechaGiro() {
        return fechaGiro;
    }

    public void setFechaGiro(String fechaGiro) {
        this.fechaGiro = fechaGiro;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefijo() {
        return telefijo;
    }

    public void setTelefijo(String telefijo) {
        this.telefijo = telefijo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getImporteEnTexto() {
        return importeEnTexto;
    }

    public void setImporteEnTexto(String importeEnTexto) {
        this.importeEnTexto = importeEnTexto;
    }
    
    // Para Impresión de Jasper (Versión 1° de M&D)
//    public String getFecha() {
//        return fecha;
//    }
//
//    public void setFecha(String fecha) {
//        this.fecha = fecha;
//    }
}