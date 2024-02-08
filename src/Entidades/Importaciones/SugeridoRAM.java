package Entidades.Importaciones;
import javax.swing.JTable;
import Servicios.Importaciones.Servicio_Repuestos;
public class SugeridoRAM {
    String numParte;
    String codSecundario;
    String descripcion;
    String aplicacion;
    Integer cantPedida;
    Integer cantEntregada;
    Integer cantPendiente;
    Integer stockFisico;
    Integer demanda;
    Integer stockSugerido;
    Double fob;
    Integer idrepuesto;
    Integer idlinea;
    Integer totDemanda;    
    int opcion;
    public int totdemanda;
//    private Integer totdemanda;
    public SugeridoRAM(){
    }
    public SugeridoRAM(JTable tb_sugerido,int fila,int opc) {
        numParte=String.valueOf(tb_sugerido.getValueAt(fila,0));
        codSecundario=String.valueOf(tb_sugerido.getValueAt(fila,1));
        descripcion=String.valueOf(tb_sugerido.getValueAt(fila,2));
        aplicacion=String.valueOf(tb_sugerido.getValueAt(fila,3));
        cantPedida=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,4)));
        stockFisico=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,5)));
        
        if(opc==1){
            demanda=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,6)));
            stockSugerido=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,7)));
            fob=Double.parseDouble(String.valueOf(tb_sugerido.getValueAt(fila,8)));
            Servicio_Repuestos sr=new Servicio_Repuestos();
            idrepuesto=sr.getRepuesto(numParte).getId().getIdrepuesto();
            idlinea=sr.getRepuesto(numParte).getId().getIdequipo();
        }else if(opc==2){
            cantEntregada=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,6)));
            cantPendiente=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,7)));
            demanda=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,8)));
            stockSugerido=Integer.parseInt(String.valueOf(tb_sugerido.getValueAt(fila,9)));
            fob=Double.parseDouble(String.valueOf(tb_sugerido.getValueAt(fila,10)));
            Servicio_Repuestos sr=new Servicio_Repuestos();
            idrepuesto=sr.getRepuesto(numParte).getId().getIdrepuesto();
            idlinea=sr.getRepuesto(numParte).getId().getIdequipo();
        }
        opcion=opc;
    }
    public Object[] getObjects(){
        Object[] obj=null;
        if(opcion==1){
            obj=new Object[12];
            obj[0]=numParte;
            obj[1]=codSecundario;
            obj[2]=descripcion;
            obj[3]=aplicacion;
            obj[4]=cantPedida;
            obj[5]=stockFisico;
            obj[6]=demanda;
            obj[7]=stockSugerido;
            obj[8]=fob;
            obj[9]=idrepuesto;
            obj[10]=idlinea;
        }else if(opcion==2){
            obj=new Object[14];
            obj[0]=numParte;
            obj[1]=codSecundario;
            obj[2]=descripcion;
            obj[3]=aplicacion;
            obj[4]=cantPedida;
            obj[5]=cantEntregada;
            obj[6]=cantPendiente;
            obj[7]=stockFisico;
            obj[8]=demanda;
            obj[9]=stockSugerido;
            obj[10]=fob;
            obj[11]=idrepuesto;
            obj[12]=idlinea;
        }
        return obj;
    }
    public String getNumParte() {
        return numParte;
    }
    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }
    public String getCodSecundario() {
        return codSecundario;
    }
    public void setCodSecundario(String codSecundario) {
        this.codSecundario = codSecundario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getAplicacion() {
        return aplicacion;
    }
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }
    public Integer getCantPedida() {
        return cantPedida;
    }
    public void setCantPedida(Integer cantPedida) {
        this.cantPedida = cantPedida;
    }
    public Integer getCantEntregada() {
        return cantEntregada;
    }
    public void setCantEntregada(Integer cantEntregada) {
        this.cantEntregada = cantEntregada;
    }
    public Integer getCantPendiente() {
        return cantPendiente;
    }
    public void setCantPendiente(Integer cantPendiente) {
        this.cantPendiente = cantPendiente;
    }
    public Integer getStockFisico() {
        return stockFisico;
    }
    public void setStockFisico(Integer stockFisico) {
        this.stockFisico = stockFisico;
    }
    public Integer getDemanda() {
        return demanda;
    }
    public void setDemanda(Integer demanda) {
        this.demanda = demanda;
    }
    public Integer getStockSugerido() {
        return stockSugerido;
    }
    public void setStockSugerido(Integer stockSugerido) {
        this.stockSugerido = stockSugerido;
    }
    public Double getFob() {
        return fob;
    }
    public void setFob(Double fob) {
        this.fob = fob;
    }
    public Integer getIdrepuesto() {
        return idrepuesto;
    }
    public void setIdrepuesto(Integer idrepuesto) {
        this.idrepuesto = idrepuesto;
    }
    public Integer getIdlinea() {
        return idlinea;
    }
    public void setIdlinea(Integer idlinea) {
        this.idlinea = idlinea;
    }
    
    public Integer getTotDemanda() {
        return totdemanda;
    }
    public void setTotDemanda(Integer totdemanda) {
        this.totdemanda = totdemanda;
    }
    
    
    public boolean esIgual(JTable tb_sugerido,int fila) {
        boolean val=true;
        Object[] objCols=null;
        if(opcion==1){
            objCols=new Object[12];
        }else if(opcion==2){
            objCols=new Object[14];
        }
        objCols=getObjects();
        for(int col=0;col<tb_sugerido.getColumnCount();col++){
            Object objJTable=tb_sugerido.getValueAt(fila,col);
            Object objSugRAM=objCols[col];
            if(!objJTable.equals(objSugRAM)){
                val=false;
                break;
            }
        }
        return val;
    }
    public void mostrar(){
        if(opcion==1){
            System.out.println(numParte+"->"+codSecundario+"->"+descripcion+"->"+aplicacion+"->"+cantPedida+
            "->"+stockFisico+"->"+demanda+"->"+stockSugerido+"->"+fob+"->"+idrepuesto+"->"+idlinea+"->"+totdemanda);
        }else if(opcion==2){
            System.out.println(numParte+"->"+codSecundario+"->"+descripcion+"->"+aplicacion+"->"+cantPedida+
            "->"+cantEntregada+"->"+cantPendiente+
            "->"+stockFisico+"->"+demanda+"->"+stockSugerido+"->"+fob+"->"+idrepuesto+"->"+idlinea+"->"+totdemanda);
        }
    }

    public void setTotdemanda(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}