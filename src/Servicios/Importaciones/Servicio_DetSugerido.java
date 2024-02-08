package Servicios.Importaciones;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Detallesugerido;
import Entidades.Repuestos;
import Mantenimiento.Importaciones.DetalleSugDAO;
import Presentacion.Importaciones.IU_ImpPedido;
import Presentacion.Importaciones.IU_ImpSugerido;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Presentacion.Importaciones.IU_Sugerido;
import Presentacion.Importaciones.IU_GeneracionNotaIngreso;
import Presentacion.Importaciones.IU_ImpSugeridoProforma;
import Servicios.HibernateUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
public class Servicio_DetSugerido {
    DetalleSugDAO detSugDao;
    Servicio_Repuestos sr;
    Object[] row;
    IU_ImpSugerido iuImprSug;
    IU_ImpSugeridoProforma iuImprSugProf;
    IU_ImpPedido iuImprPed;
    IU_GeneracionNotaIngreso iuNotaIngreso;
    IU_Sugerido iuSugerido;
    IU_ActualizarSugerido iuActSugerido;
    public Servicio_DetSugerido(){
        detSugDao=new DetalleSugDAO();
    }
    public Servicio_DetSugerido(IU_GeneracionNotaIngreso iuNotaIng){
        detSugDao=new DetalleSugDAO();
        iuNotaIngreso=iuNotaIng;
        row=new Object[5];
    }
    public Servicio_DetSugerido(IU_Sugerido iusug){
        detSugDao=new DetalleSugDAO();
        sr=new Servicio_Repuestos();
        row = new Object[9];
        iuSugerido=iusug;
    }
    public Servicio_DetSugerido(IU_ActualizarSugerido iuactsug){
        detSugDao=new DetalleSugDAO();
        sr=new Servicio_Repuestos();
        row = new Object[12];
        iuActSugerido=iuactsug;
    }
    public Servicio_DetSugerido(IU_ImpSugerido iuimprsug){
        detSugDao=new DetalleSugDAO();
        iuImprSug=iuimprsug;
        row = new Object[9];
    }
    public Servicio_DetSugerido(IU_ImpSugeridoProforma iuimprsugprof){
        detSugDao=new DetalleSugDAO();
        iuImprSugProf=iuimprsugprof;
        row = new Object[5];
    }
    public Servicio_DetSugerido(IU_ImpPedido iuimprped){
        detSugDao=new DetalleSugDAO();
        iuImprPed=iuimprped;
        row = new Object[8];
    }
    public int nextId(){
        return detSugDao.nextId();
    }
    public void eliminarDetallesSugerido(int idCabSug){
        DetalleSugDAO dsDao=new DetalleSugDAO();
        Iterator ite = dsDao.getDetallesSug(idCabSug).iterator();
        while (ite.hasNext()) {
            Detallesugerido detsug=(Detallesugerido) ite.next();
            dsDao.Eliminar_Objeto(detsug);
        }
    }
    public boolean eliminarDetalleSugerido(int idsugerido,int idrepuesto,int idlinea){
        DetalleSugDAO dsDao=new DetalleSugDAO();
        Detallesugerido detsug= dsDao.getDetalleSug(idsugerido,idrepuesto,idlinea);
        return dsDao.Eliminar_Objeto(detsug);
    }
    private ArrayList cargarRepuestos(DefaultTableModel table, int idCabSug){
        DetalleSugDAO dsDao=new DetalleSugDAO();
        Iterator ite=dsDao.getDetallesSug(idCabSug).iterator();
        double fobTot=0.00,totFob=0.00;
        int fila=0;
        Repuestos rep=new Repuestos();
        ArrayList<SugeridoRAM> tablaRAM= new ArrayList<SugeridoRAM>();
        while (ite.hasNext()) {
            SugeridoRAM sugTabla=new SugeridoRAM();
            Detallesugerido detsug=(Detallesugerido) ite.next();
            rep=detsug.getRepuestos();
            if(rep!=null){
                row[0]=rep.getCodrepuesto();
                row[1]=rep.getCodigoseg();
                row[2]=rep.getDescripcion();
                row[3]=rep.getAplicacion();
                int canPed=detsug.getCantpedida();
                row[4]=getComaMillar(canPed);
                int canEnt=detsug.getCantentregada();
                row[5]=getComaMillar(canEnt);
                row[6]=detsug.getCantpendiente();
                int stockFis=0;
                if(rep.getStock()!=null){
                    stockFis=rep.getStock();
                }
                row[7]=getComaMillar(stockFis);
                int dem=detsug.getDemandaprom();
                row[8]=getComaMillar(dem);
                int sug=detsug.getStocksugerido();
                row[9]=getComaMillar(sug);
                row[10]=detsug.getValorfob();
                if(row[4]==null){   row[4]=0;   }
                if(row[5]==null){   row[5]=0;   }
                if(row[6]==null){   row[6]=0;   }
                if(row[10]==null){   row[10]=0;   }
                totFob=Double.parseDouble(String.valueOf(canEnt))*Double.parseDouble(String.valueOf(row[10]));
                row[11]=totFob;
                fobTot+=totFob;
                table.addRow(row);
                formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[10])),fila,10);
                formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[11])),fila,11);
                fila++;
                sugTabla.setNumParte(String.valueOf(row[0]));
                sugTabla.setCodSecundario(String.valueOf(row[1]));
                sugTabla.setDescripcion(String.valueOf(row[2]));
                sugTabla.setAplicacion(String.valueOf(row[3]));
                sugTabla.setCantPedida(Integer.parseInt(String.valueOf(canPed)));
                sugTabla.setCantEntregada(Integer.parseInt(String.valueOf(canEnt)));
                sugTabla.setCantPendiente(Integer.parseInt(String.valueOf(row[6])));
                sugTabla.setStockFisico(Integer.parseInt(String.valueOf(stockFis)));
                sugTabla.setDemanda(Integer.parseInt(String.valueOf(dem)));
                sugTabla.setStockSugerido(Integer.parseInt(String.valueOf(sug)));
                sugTabla.setFob(Double.parseDouble(String.valueOf(row[10])));
                int idrepuesto=rep.getId().getIdrepuesto();
                sugTabla.setIdrepuesto(idrepuesto);
                int linea=rep.getId().getIdequipo();
                sugTabla.setIdlinea(linea);
                tablaRAM.add(sugTabla);
            }
        }
        //ACTUALIZAR TABLARAMFB CON CONTENIDO DE TABLARAM AL CARGAR JTABLE
        SugeridoRAM sugRam=new SugeridoRAM();
        ArrayList<SugeridoRAM> tablaRAMFB=new ArrayList<SugeridoRAM>();
        tablaRAMFB=this.iuActSugerido.getTablaRAMBuscarFiltrar();
        for(int j=0;j<tablaRAM.size();j++){
            for(int k=0;k<this.iuActSugerido.getTablaRAMBuscarFiltrar().size();k++){
                if(tablaRAM.get(j).getIdrepuesto()==tablaRAMFB.get(k).getIdrepuesto() &&
                   tablaRAM.get(j).getIdlinea()==tablaRAMFB.get(k).getIdlinea()){
                    sugRam=tablaRAMFB.get(k);
                    sugRam.setCantPedida(tablaRAM.get(j).getCantPedida());
                    sugRam.setCantEntregada(tablaRAM.get(j).getCantEntregada());
                    sugRam.setCantPendiente(tablaRAM.get(j).getCantPendiente());
                    sugRam.setStockFisico(tablaRAM.get(j).getStockFisico());
                    sugRam.setDemanda(tablaRAM.get(j).getDemanda());
                    sugRam.setStockSugerido(tablaRAM.get(j).getStockSugerido());
                    sugRam.setFob(tablaRAM.get(j).getFob());
                    tablaRAMFB.set(k,sugRam);
                    break;
                }
            }
        }
        formatearDecimalDerechaEnTextField(iuActSugerido.txt_Tot_Fob,fobTot);
        return tablaRAM;
    }
    private void formatearColumnaDerecha(double valor, int fila, int columna){
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
        iuActSugerido.tb_sugerido.setValueAt(moneyCad,fila,columna);
    }
    public ArrayList cargaTabla(int idCabSug){
        DefaultTableModel table = new DefaultTableModel();
        table=(DefaultTableModel) iuActSugerido.tb_sugerido.getModel();
        return cargarRepuestos(table,idCabSug);
    }
    private void cargarAImpSugerido(DefaultTableModel table,int idCabSug){
        List lista=getRepuestosAImpSugerido(idCabSug,1);
        Iterator ite = lista.iterator();
        double totalImporte=0.00;
        double totalFob=0.00;
        int j=0;
        alinearColumnasDerecha();
        while(ite.hasNext()){
            Object objDetSug[]= (Object[]) ite.next();
                        for(int i=0;i<4;i++){
                if(objDetSug[i]==null){
                    row[i]="";
                }else{
                    row[i]=objDetSug[i];
                }
            }
            if(objDetSug[4]==null){    row[4]=0;    }
            else{                   row[4]=getComaMillar(objDetSug[4]);    }
            if(objDetSug[5]==null){    row[5]=0;    }
            else{                   row[5]=objDetSug[5];    }
            //costo total= Cantidad pedida * Precio costo
            row[6] = Integer.parseInt(String.valueOf(objDetSug[4])) * Double.parseDouble(String.valueOf(row[5]));
            if(objDetSug[6]==null){    row[7]=0;   }
            else{                   row[7]=objDetSug[6];   }
            
            if(objDetSug[6]==null){        row[8]=0;   }
            else{//FOB Total=Cantidad pedida * fobultimo
                row[8] = Integer.parseInt(String.valueOf(objDetSug[4]))* Double.parseDouble(String.valueOf(objDetSug[6]));
            }
            totalImporte+=Double.parseDouble(String.valueOf(row[6]));
            totalFob+=Double.parseDouble(String.valueOf(row[8]));
            table.addRow(row);
            formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(row[5])),j,5);
            formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(row[6])),j,6);
            formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(row[7])),j,7);
            formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(row[8])),j,8);
            j++;
        }
        formatearDecimalDerechaEnTextField(iuImprSug.txtTotalCosto,totalImporte);
        formatearDecimalDerechaEnTextField(iuImprSug.txtTotalFOB,totalFob);
    }
    private String getComaMillar(Object valor){
        String cantStr=String.valueOf(valor);
        int cantInt=Integer.parseInt(cantStr);
        int cifMillar,cifResto;
        if(cantStr.length()>3 && Integer.parseInt(cantStr)>0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+cifResto;
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+cifResto;
            }else{
                cantStr=cifMillar+",00"+cifResto;
            }
        }else if(cantStr.length()>4 && Integer.parseInt(cantStr)<0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+Math.abs(cifResto);
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+Math.abs(cifResto);
            }else{
                cantStr=cifMillar+",00"+Math.abs(cifResto);
            }
        }
        return cantStr;
    }
    private void formatearColumnaDerechaSugerido(double valor, int fila, int columna){
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
        iuImprSug.tbResultados.setValueAt(moneyCad,fila,columna);
    }
    private void cargarAImpSugeridoProforma(DefaultTableModel table,int idCabSug){
        List lista=getRepuestosAImpSugerido(idCabSug,3);
        Iterator ite = lista.iterator();
        int j=0;
        while(ite.hasNext()){
            Object [] objRep = (Object []) ite.next();
            for(int i=0;i<5;i++){
                row[i]=objRep[i];
                if(objRep[4]==null){    row[4]=0;    }
                else{                   row[4]=getComaMillar(objRep[4]);    }
            }
            table.addRow(row);
            alinearColumnasDerechaProforma();
            j++;
        }
    }
    private void alinearColumnasDerechaProforma(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        iuImprSugProf.tbResultados.getColumnModel().getColumn(4).setCellRenderer(tcr);
    }
    private void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        //Obtener indice de . decimal en cadena moneyCad
        int indice=moneyCad.indexOf(".");
        if(indice==-1){//No existe . decimal
            moneyCad=moneyCad+".00";
        }else if(moneyCad.length()-indice==2){//2,301.9 (penultima ubicacion de . decimal)
            moneyCad=moneyCad+"0";
        }
        txt.setText(moneyCad);
    }
    private void alinearColumnasDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        iuImprSug.tbResultados.getColumnModel().getColumn(5).setCellRenderer(tcr);
        iuImprSug.tbResultados.getColumnModel().getColumn(6).setCellRenderer(tcr);
        iuImprSug.tbResultados.getColumnModel().getColumn(7).setCellRenderer(tcr);
    }
    public void cargaTablaAImprimirSugerido(int idCabSug, int opc){
        DefaultTableModel table = new DefaultTableModel();
        if(opc==1){
            table=(DefaultTableModel) iuImprSug.tbResultados.getModel();
            cargarAImpSugerido(table,idCabSug);
        }else if(opc==2){
            table=(DefaultTableModel) iuImprPed.tbResultados.getModel();
            cargarAImpPedido(table,idCabSug);
        }else if(opc==3){
            table=(DefaultTableModel) iuImprSugProf.tbResultados.getModel();
            cargarAImpSugeridoProforma(table,idCabSug);
        }
    }
    public DefaultTableModel getTabla(){
        return (DefaultTableModel) iuImprPed.tbResultados.getModel();
    }
    public List getRepuestosAImpSugerido(int idCabSug, int opc) {
        if(opc==1){
            return detSugDao.getDetallesDeCabSugerido(idCabSug);
        }else if(opc==2){
            return detSugDao.getDetallesDeCabPedido(idCabSug);
        }else if(opc==3){
            return detSugDao.getDetallesDeCabSugeridoProforma(idCabSug);
        }else{
            return null;
        }
    }
    private String setString(Object obj){
        String cad="";
        if(obj!=null){    cad=String.valueOf(obj);    }
        return cad;
    }
    private int setInteger(Object obj){
        int num=0;
        if(obj!=null){    num=Integer.parseInt(String.valueOf(obj));    }
        return num;
    }
    private double setDouble(Object obj){
        double num=0.0;
        if(obj!=null){    num=Double.parseDouble(String.valueOf(obj));    }
        return num;
    }
    private void cargarAImpPedido(DefaultTableModel table, int idCabSug) {
        List lista=getRepuestosAImpSugerido(idCabSug,2);
        Iterator ite = lista.iterator();
        double totalFob=0.00;
        int j=0;
        while(ite.hasNext()){
            Object [] objRep = (Object []) ite.next();
            for(int i=0;i<4;i++){
                row[i]=setString(objRep[i]);
            }
            row[4]=getComaMillar(objRep[4]);
            row[5]=setDouble(objRep[5]);
            //FOB Total=Cantidad pedida * fobultimo
            row[6] = Integer.parseInt(String.valueOf(objRep[4]))*Double.parseDouble(String.valueOf(row[5]));
            totalFob+=Double.parseDouble(String.valueOf(row[6]));
            table.addRow(row);
            formatearColumnaDerechaPedido(Double.parseDouble(String.valueOf(row[5])),j,5);
            formatearColumnaDerechaPedido(Double.parseDouble(String.valueOf(row[6])),j,6);
            j++;
        }
        formatearDecimalDerechaEnTextField(iuImprPed.txtTotalFob,totalFob);
    }
    private void formatearColumnaDerechaPedido(double valor, int fila, int columna){
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
        iuImprPed.tbResultados.setValueAt(moneyCad,fila,columna);
    }
    public List cargarATablaValorizacion(int idsugerido){
        return detSugDao.getDetalleSugXIdSug(idsugerido);
    }
    public int getCantPedidaPendienteSugerido(int idsugerido){
        return detSugDao.getCantPedidaPendienteSugerido(idsugerido);
    }
    public boolean registrarDetalleSugerido(Detallesugerido ds) {
        boolean registro=false;
        try{
            createAndStoreRepuesto(ds);
            registro=true;
        }catch(ExceptionInInitializerError ex){
            System.out.println("Error al registrar cabecera de sugerido en la BD:"+ex);
        }
        HibernateUtil.getSessionFactory().close();
        return registro;
    }
    private void createAndStoreRepuesto(Detallesugerido ds) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ds);
            session.getTransaction().commit();
        }catch(Throwable ex) {
            System.err.println ("creación inicial SessionFactory falló." + ex);
            throw new ExceptionInInitializerError (ex);
        }
    }
    public List getDetalleSugParaValorizar(int idsugerido){
        return detSugDao.getDetalleSugParaValorizar(idsugerido);
    }
    public List<Detallesugerido> getDetalleSugerido(int idsugerido){
        return detSugDao.getDetalleSugerido(idsugerido);
    }
    public boolean actualizarDetSugerido(Detallesugerido det){
        return detSugDao.updateDetSugerido(det);
    }
    
    public boolean actualizarListaDetSugerido(List<Detallesugerido> lstDetSug) {
        return detSugDao.updateListaDetSugerido(lstDetSug);
    }
    
    public Detallesugerido buscaDetSugerido(int idDetSugerido){
        return detSugDao.getIdDetalleSug(idDetSugerido);
    }
    public Detallesugerido buscaDetSugerido(int idSugerido,int idRepuesto,int idLinea){
        return detSugDao.getDetalleSug(idSugerido,idRepuesto,idLinea);
    }
    public boolean eliminarDetSugerido(int idSugerido){
        boolean val=true;
        List<Detallesugerido> listaDetSug=new ArrayList<Detallesugerido>();
        listaDetSug=getDetalleSugerido(idSugerido);
        int numReg=listaDetSug.size();
        System.out.println("Nº de registros a eliminar:"+numReg);
        for(int i=0;i<numReg;i++){
            if(!detSugDao.deleteDetSug(listaDetSug.get(i).getIddetallesugerido())){//idSugerido
                val=false;
                break;
            }
        }
        return val;
    }
}