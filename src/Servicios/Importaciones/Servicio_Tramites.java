package Servicios.Importaciones;
import Entidades.Tramites;
import Mantenimiento.Importaciones.TramitesDAO;
import Presentacion.Importaciones.IU_Valorizacion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class Servicio_Tramites {
    TramitesDAO traDao;
    IU_Valorizacion iuvaloriza;
    Object[] row;
    public Servicio_Tramites(IU_Valorizacion iuPrint){
        traDao=new TramitesDAO();
        iuvaloriza=iuPrint;
    }
    public Servicio_Tramites(){
        traDao=new TramitesDAO();
    }
    public boolean registrarTramite(Tramites tr){
        return traDao.Agregar_Objeto(tr);
    }
    public void cargaTramitesATabla(){
        DefaultTableModel table = new DefaultTableModel();
        table=(DefaultTableModel) iuvaloriza.tbImpuestos.getModel();
        cargarTramitesATabla(iuvaloriza.tbImpuestos);
    }
    public ArrayList<Tramites> getTramitesATabla(int idsugerido) {
        return traDao.getTramites(idsugerido);
    }
    
    public boolean cargarTramitesATabla(JTable tbImpuestos) {
        iuvaloriza.limpiarTabla();
        DefaultTableModel table = (DefaultTableModel) tbImpuestos.getModel();
        double totImp = 0.00;
        int idsugerido = Integer.parseInt(iuvaloriza.txtNumPedido.getText());
        List<Tramites> listaTramites = (List)getTramitesATabla(idsugerido);
        
//        if ( !listaTramites.isEmpty() ) {
//            System.out.println("tamaño de lista tramites:" + listaTramites.size());
//        }
        Iterator ite = listaTramites.iterator();
        int j = 0;
        
        while ( ite.hasNext() ) {
            Object[] fila = new Object[2];
            Tramites tempImp = (Tramites) ite.next();
            fila[0] = tempImp.getGlosa();
            fila[1] = tempImp.getImporte();
            
            if ( fila[1] == null ) {
                fila[1] = 0;
            }
            totImp += Double.parseDouble(String.valueOf(fila[1]));
            table.addRow(fila);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(fila[1])),j,1,tbImpuestos);
            j++;
        }
        totImp += Double.parseDouble(iuvaloriza.quitaComa(iuvaloriza.txtValorFobImp.getText()));
        iuvaloriza.txtTotalImportacion.setText(String.valueOf(totImp));
        formatearDecimalDerechaEnTextField(iuvaloriza.txtTotalImportacion,totImp);
        
        if ( listaTramites.isEmpty() )  {
            return false;
        } else {
            return true;
        }
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna, JTable tbImpuestos){
        String moneyCad="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        int indice=moneyCad.indexOf(".");
        if(indice==-1){
            moneyCad=moneyCad+".00";
        }else if(moneyCad.length()-indice==2){
            moneyCad=moneyCad+"0";
        }
        tbImpuestos.setValueAt(moneyCad,fila,columna);
    }
    private void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        int indice=moneyCad.indexOf(".");
        if(indice==-1){
            moneyCad=moneyCad+".00";
        }else if(moneyCad.length()-indice==2){
            moneyCad=moneyCad+"0";
        }
        txt.setText(moneyCad);
    }
    public void listarTramites(JComboBox cbotramites,int idsugerido){
        List<Tramites> tramites = traDao.getTramites(idsugerido);
        for (int i = 0; i < tramites.size(); i++) {
            cbotramites.addItem(tramites.get(i).getGlosa());
            //System.out.println("Tramites:"+tramites.get(i).getGlosa());
        }
    }
    public boolean actualizarImpuesto(int impuestoID, double importe, String glosa){
        return traDao.updateImpuesto(impuestoID,importe,glosa);
    }
    public int getIdTramiteXGlosa(int idSugerido,String glosa){
        return traDao.getIdTramite(idSugerido,glosa);
    }
    public boolean eliminarImpuesto(int impuestoID){
        return traDao.deleteImpuesto(impuestoID);
    }
    public boolean verificarGlosaRepetida(int idCabSug,String glosa) {
        boolean val=true;
        if(traDao.countGlosas(idCabSug,glosa)==0){
            val=false;
        }
        return val;
    }
    public double getImporteTotal(int idsugerido){
        return traDao.getImporteTotal(idsugerido);
    }
    public boolean actualizarImpuesto(Tramites tramiteNew) {
        return traDao.updateImpuesto(tramiteNew);
    }
}