package Presentacion;

/**
 *
 * @author Administrator
 */
public class Entidad implements Comparable<Entidad> {
    private String campoSup;
    private String campoInf;

    public Entidad(String campoInf, String campoSup) {
        this.campoInf = campoInf;
        this.campoSup = campoSup;
    }
    
    public String getCampoSup() {
        return campoSup;
    }

    public void setCampoSup(String campoSup) {
        this.campoSup = campoSup;
    }

    public String getCampoInf() {
        return campoInf;
    }

    public void setCampoInf(String campoInf) {
        this.campoInf = campoInf;
    }

    @Override
    public int compareTo(Entidad o) {
//        String a=new String(String.valueOf(this.getEdad())+this.getNombre());
//        String b=new String(String.valueOf(o.getEdad())+o.getNombre());
//        return a.compareTo(b);
        
        // Ordenar solo por 1 filtro "campoInf"
//        String a = campoInf;
//        String b = o.campoInf;
//        return a.compareTo(b);
        
        // Ordenar por 2 filtros "campoInf" y "campoSup"
        String a = campoInf + campoSup;
        String b = o.campoInf + o.campoSup;
        return a.compareTo(b);
    }
}