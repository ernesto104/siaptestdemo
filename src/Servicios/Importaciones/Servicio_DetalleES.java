package Servicios.Importaciones;

import Entidades.Cabecsugerido;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Detallees;
import Entidades.Detallesugerido;
import Entidades.Repuestos;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import Mantenimiento.Importaciones.DetalleEsDAO;
import Mantenimiento.Importaciones.RepuestosDAO;
import Mantenimiento.Importaciones.CabecEsDAO;
import Mantenimiento.Importaciones.DetalleSugDAO;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Presentacion.Importaciones.IU_Sugerido;
import Presentacion.Importaciones.frmiBuscarSugerido;
import Servicios.HibernateUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.hibernate.Session;

public class Servicio_DetalleES {
    IU_Sugerido iuSugerido;
    RepuestosDAO repdao;
    CabecEsDAO cabESdao;
    DetalleEsDAO detESdao;
    Servicio_Control sc;
    Servicio_Repuestos sr;
    Servicio_DetSugerido sds;
    Object[] row;
    int numRepuestos;
    IU_ActualizarSugerido iuActSugerido;
    
    public Servicio_DetalleES(){
        detESdao=new DetalleEsDAO();
        sr=new Servicio_Repuestos();
        row = new Object[9];
    }
    
    public Servicio_DetalleES(frmiBuscarSugerido buscar,IU_Sugerido iusug){
        iuSugerido=iusug;
        row = new Object[9];
    }
    
    public Servicio_DetalleES(IU_Sugerido iusug){
        iuSugerido=iusug;
        sr=new Servicio_Repuestos();
        repdao=new RepuestosDAO();
        detESdao=new DetalleEsDAO();
        row = new Object[10];
        sds=new Servicio_DetSugerido(iusug);
    }
    
    public Servicio_DetalleES(IU_ActualizarSugerido ias){
        iuActSugerido=ias;
        sr=new Servicio_Repuestos();
        repdao=new RepuestosDAO();
        detESdao=new DetalleEsDAO();
        row = new Object[11];
        sds=new Servicio_DetSugerido(ias);
    }
    
    public List getDetalleSugerido(int idsugerido){
        DetalleSugDAO detSugDao=new DetalleSugDAO();
        return detSugDao.getDetallesDeCabSugerido(idsugerido);
    }
    
    private String getDiasDelMes(int mes){
        String dias="";
        switch(mes){
            case 1: dias="31";break;
            case 2: dias="29";break;
            case 3: dias="31";break;
            case 4: dias="30";break;
            case 5: dias="31";break;
            case 6: dias="30";break;
            case 7: dias="31";break;
            case 8: dias="31";break;
            case 9: dias="30";break;
            case 10: dias="31";break;
            case 11: dias="30";break;
            case 12: dias="31";break;
        }
        return dias;
    }
    
    private Date[] calcularFechaInicio(int[] meses, int añoActual){
        Date sqlfechas[]=new Date[2];
        Date fechas[]=new Date[2];
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha="";
        try {
            fecha=añoActual+"-"+meses[1]+"-"+getDiasDelMes(meses[1]);
            fechas[1] = formatoFecha.parse(fecha);
            sqlfechas[1] = new java.sql.Date(fechas[1].getTime());
            if(meses[0]<meses[1]){
                fecha=añoActual+"-"+meses[0]+"-"+"01";
            }else if(meses[1]<meses[0]){
                fecha=(añoActual-1)+"-"+meses[0]+"-"+"01";
            }
            fechas[0] = formatoFecha.parse(fecha);
            sqlfechas[0] = new java.sql.Date(fechas[0].getTime());
        }catch(Exception ex) {
            System.out.print("Error de conversión de fecha :" + ex.getMessage());
        }
        return sqlfechas;
    }
    
    public String convertString(Object obj){
        String valor="";
        if(! (String.valueOf(obj).equals("null")) ){
                valor=String.valueOf(obj);
        }
        return valor;
    }
    
    public int convertInteger(Object obj){
        int num=0;
        if(obj!=null){
            num=Integer.parseInt(String.valueOf(obj));
        }
        return num;
    }
    
    public double convertDouble(Object obj){
        double num=0.00;
        if(obj!=null){
            num=Double.parseDouble(String.valueOf(obj));
        }
        return num;
    }
    
    public ArrayList cargaTablaASugerido(JTable tbRepuestos,IU_Sugerido sugerido){
        DefaultTableModel table = (DefaultTableModel) tbRepuestos.getModel();
        return cargarASugerido(table,sugerido);
    }
    
    public ArrayList cargarASugerido(DefaultTableModel table,IU_Sugerido iusug){
        int añoHoy=getAñoActual();
        sc=new Servicio_Control();
        Servicio_DetSugerido sds=new Servicio_DetSugerido();
        int mesDemanda=sc.getControlUnico().getMesdemanda();
        iusug.txtMesesDemanda.setText(String.valueOf(mesDemanda));
        int mesProyeccion=sc.getControlUnico().getMesproyeccion();
        iusug.txt_mesProy.setText(String.valueOf(mesProyeccion));
        int proy=sc.getControlUnico().getMesproyeccion();
        int[] meses=sc.getMesIniYFin(mesDemanda);
        //System.out.println("MesI:"+meses[0]+"-MesF:"+meses[1]);
        int idrepuesto,idlinea;
        double dp,demProm,stock;
        int cantPendienteSug;
        Date[] fechas=new Date[2];
        fechas=calcularFechaInicio(meses, añoHoy);
        /*System.out.println("fechaIn:"+fechas[0]);
        System.out.println("fechaFin:"+fechas[1]);*/
        List listaRep=sr.getRepuestosASugerido();
        System.out.println("NumReg:"+listaRep.size());
        Iterator iteRep = listaRep.iterator();
        double fobTot=0.00;
        int i=0;
        ArrayList tablaRAM= new ArrayList();
        
        //Registro cabecsugerido
        Cabecsugerido cs=new Cabecsugerido();
        int idSugerido=Integer.parseInt(iusug.tx_id.getText());
        cs.setIdsugerido(idSugerido);
        cs.setImportadores(null);
        Servicio_CabSugerido scs=new Servicio_CabSugerido();
        cs=scs.getCabSugerido(idSugerido);
        if(cs==null){
            cs=new Cabecsugerido();
            cs.setIdsugerido(idSugerido);
            cs.setImportadores(null);
            if(!scs.registrarCabecSugerido(cs)){
                System.out.println("Problema al registrar cabecSugerido");
            }else{
                System.out.println("Cabecera sugerido registrada con idSug:"+idSugerido);
            }
        }else{
            System.out.println("El mismo sugerido");
        }
        iusug.tx_id.setText(String.valueOf(cs.getIdsugerido()));
        int contDetSug=0;
        boolean registro=true;
        while(iteRep.hasNext()){
            SugeridoRAM sugTabla=new SugeridoRAM();
            Object [] objRep = (Object []) iteRep.next();
            row[0]=objRep[0];
            for(int j=0;j<4;j++){
                row[j]=convertString(objRep[j]);
            }
            row[4]=0;
            int stockFis=convertInteger(objRep[4]);
            row[5]=getComaMillar(stockFis);
            idrepuesto=Integer.parseInt(String.valueOf(objRep[6]));
            idlinea=Integer.parseInt(String.valueOf(objRep[7]));
            
            dp=getCantEntregadaXRepuesto(fechas[0],fechas[1],mesDemanda,idrepuesto,idlinea); 
            long demP=0;
            if(dp!=-1){
                demP=Math.round(dp);
                row[6]=demP;
            }else{
                row[6]=0;
            }
            cantPendienteSug=sds.getCantPedidaPendienteSugerido(idrepuesto);
            if(cantPendienteSug==-1){
                cantPendienteSug=0;
            }
            demProm=Double.parseDouble(String.valueOf(demP));

           
            stock=Double.parseDouble(String.valueOf(stockFis));
            long sugerido=0;
            if(cantPendienteSug!=-1){//Sugerido
                sugerido=Math.round( (demProm - (stock +  cantPendienteSug)) * proy );
                row[7]=getComaMillar(sugerido);
            }else{
                row[7]=0;
            }
            int canPed=0;
            if(Integer.parseInt(String.valueOf(sugerido))>0){
                // Modificado solo para Siar MyD
                //canPed=Integer.parseInt(String.valueOf(sugerido));
                row[4]=getComaMillar(canPed); // cantidadPedida = 0
            }
            row[8]=convertDouble(objRep[5]);;
            double fobTotC=Double.parseDouble(String.valueOf(canPed))*Double.parseDouble(String.valueOf(row[8]));
            row[9]=fobTotC;
            fobTot+=Double.parseDouble(String.valueOf(row[9]));
            table.addRow(row);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[8])), i,8,iusug);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[9])), i,9,iusug);
            i++;
            String numParte=String.valueOf(row[0]);
            sugTabla.setNumParte(numParte);
            sugTabla.setCodSecundario(String.valueOf(row[1]));
            sugTabla.setDescripcion(String.valueOf(row[2]));
            sugTabla.setAplicacion(String.valueOf(row[3]));
            int cantPedida=Integer.parseInt(String.valueOf(canPed));
            sugTabla.setCantPedida(cantPedida);
            sugTabla.setStockFisico(Integer.parseInt(String.valueOf(stockFis)));
            int demanda=Integer.parseInt(String.valueOf(demP));
            sugTabla.setDemanda(demanda);
            
            // SugeridoRAM.setCantPedida(row[4]) 	--> cantPedida
            // SugeridoRAM.setStockSugerido(row[7]) 	--> sugerido
            int stockSugerido=Integer.parseInt(String.valueOf(sugerido));
            sugTabla.setStockSugerido(stockSugerido);
            double fob=Double.parseDouble(String.valueOf(row[8]));
            sugTabla.setFob(fob);
            sugTabla.setIdrepuesto(idrepuesto);
            sugTabla.setIdlinea(Integer.parseInt(String.valueOf(objRep[7])));
            tablaRAM.add(sugTabla);

            if(sugTabla.getCantPedida()!=0 && objRep[5]!=null){
                /*System.out.println("Nº Parte:"+sugTabla.getNumParte());
                System.out.println("Cant.Pedida:"+sugTabla.getCantPedida());
                System.out.println("Fob:"+sugTabla.getFob());*/
                //REGISTRAR DETALLESUGERIDO
                Detallesugerido ds=new Detallesugerido();
                ds=new Detallesugerido();
                int idDetSugerido=sds.nextId();
                ds.setIddetallesugerido(idDetSugerido);
                Repuestos rep=new Repuestos();
                rep=sr.getRepuesto(numParte);
                ds.setRepuestos(rep);//idrepuesto, idlinea
                ds.setCantpedida(cantPedida);
                ds.setCantpendiente(ds.getCantpedida()-cantPedida);
                ds.setCantentregada(cantPedida);
                ds.setValorfob(fob);
                //ds.setValorcosto();
                ds.setStocksugerido(stockSugerido);
                ds.setDemandaprom(demanda);
                ds.setCabecsugerido(cs);//idsugerido 
                if(!sds.registrarDetalleSugerido(ds)){
                        System.out.println("Error al registrar detallesugerido");
                }
            }
        }
        if(contDetSug==0){
            System.out.println("No registrar ni cabecSugerido ni detalleSugerido pq no existen con FOB<>0 y cantPed <>0");
        }else{
            //Registro de Cabecsugerido
            if(scs.getCabSugerido(idSugerido)==null){
                System.out.println("Registrando cabecera nueva");
                if(!scs.registrarCabecSugerido(cs)){
                    System.out.println("Problema al registrar cabecSugerido");
                }else{
                    System.out.println("Registrando cabecSugerido con idSug:"+cs.getIdsugerido());
                }
            }
            //Registro todos los Detallesugerido
            for(i=0;i<tablaRAM.size();i++){
                SugeridoRAM sug=(SugeridoRAM) tablaRAM.get(i);
                if(sug.getCantPedida()!=0 && sug.getFob()!=0.00){
                    sr=new Servicio_Repuestos();
                    Detallesugerido ds=new Detallesugerido();

                    int idDetSugerido=sds.nextId();
                    ds.setIddetallesugerido(idDetSugerido);

                    Repuestos rep=new Repuestos();
                    rep=sr.getRepuesto(sug.getNumParte());
                    ds.setRepuestos(rep);//idrepuesto, idlinea

                    ds.setCabecsugerido(cs);//idsugerido
                    ds.setCantpedida(sug.getCantPedida());
                    ds.setCantentregada(sug.getCantPedida());
                    ds.setValorfob(sug.getFob());
                    //ds.setValorcosto();
                    ds.setStocksugerido(sug.getStockSugerido());
                    ds.setCantpendiente(0);
                    ds.setDemandaprom(sug.getDemanda());
                    if(!sds.registrarDetalleSugerido(ds)){
                        registro=false;
                        System.out.println("Registrar en detallesugerido con cantPedida="+sug.getCantPedida()
                                           +" y fob="+sug.getFob()+" diferentes de 0");
                        break;
                    }
                    contDetSug++;
                }
            }
            System.out.println("No eliminar cabecSugerido");
        }
        iusug.txt_Tot_Fob.setText(String.valueOf(fobTot));
        formatearDecimalDerechaEnTextField(iusug.txt_Tot_Fob,fobTot);
        return tablaRAM;
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
    
    public ArrayList cargaTablaRAMBF(IU_Sugerido sugerido){
        return cargarSugeridoARAMBF(sugerido);
    }
    
    public ArrayList cargaTablaRAMBF(IU_ActualizarSugerido actsugerido){
        return cargarSugeridoARAMBF(actsugerido);
    }
    
    public ArrayList cargarSugeridoARAMBF(IU_Sugerido iusug){
        int añoHoy=getAñoActual();
        sc=new Servicio_Control();
        Servicio_DetSugerido sds=new Servicio_DetSugerido();
        int mesDemanda=sc.getControlUnico().getMesdemanda();
        int proy=sc.getControlUnico().getMesproyeccion();
        int[] meses=sc.getMesIniYFin(mesDemanda);
        int idrepuesto,idlinea;
        double dp,demProm,stock;
        int cantPendienteSug;
        Date[] fechas=new Date[2];
        fechas=calcularFechaInicio(meses, añoHoy);
        List listaRep=sr.getRepuestosASugerido();
        Iterator iteRep = listaRep.iterator();
        ArrayList tablaRAM= new ArrayList();
        while(iteRep.hasNext()){
            SugeridoRAM sugTabla=new SugeridoRAM();
            Object [] objRep = (Object []) iteRep.next();
            row[0]=objRep[0];
            for(int j=0;j<4;j++){
                row[j]=convertString(objRep[j]);
            }
            row[4]=0;
            row[5]=convertInteger(objRep[4]);
            idrepuesto=Integer.parseInt(String.valueOf(objRep[6]));
            idlinea=Integer.parseInt(String.valueOf(objRep[7]));
            dp=getCantEntregadaXRepuesto(fechas[0],fechas[1],mesDemanda,idrepuesto,idlinea); 
            if(dp!=-1){
                row[6]=Math.round(dp);
            }else{
                row[6]=0;
            }
            cantPendienteSug=sds.getCantPedidaPendienteSugerido(idrepuesto);
            if(cantPendienteSug==-1){
                cantPendienteSug=0;
            }
            demProm=Double.parseDouble(String.valueOf(row[6]));
            stock= Double.parseDouble(String.valueOf(row[5]));
            if(cantPendienteSug!=-1){//Sugerido
                double sugerido=(demProm - (stock +  cantPendienteSug)) * proy;
                row[7]=Math.round(sugerido);
            }else{
                row[7]=0;
            }
            row[8]=convertDouble(objRep[5]);
            sugTabla.setNumParte(String.valueOf(row[0]));
            sugTabla.setCodSecundario(String.valueOf(row[1]));
            sugTabla.setDescripcion(String.valueOf(row[2]));
            sugTabla.setAplicacion(String.valueOf(row[3]));
            sugTabla.setCantPedida(Integer.parseInt(String.valueOf(row[4])));
            sugTabla.setStockFisico(Integer.parseInt(String.valueOf(row[5])));
            sugTabla.setDemanda(Integer.parseInt(String.valueOf(row[6])));
            sugTabla.setStockSugerido(Integer.parseInt(String.valueOf(row[7])));
            sugTabla.setFob(Double.parseDouble(String.valueOf(row[8])));
            sugTabla.setIdrepuesto(idrepuesto);
            sugTabla.setIdlinea(Integer.parseInt(String.valueOf(objRep[7])));
            tablaRAM.add(sugTabla);
        }
        return tablaRAM;
    }
    
    public ArrayList cargarSugeridoARAMBF(IU_ActualizarSugerido iuactsug){
        int añoHoy=getAñoActual();
        sc=new Servicio_Control();
        Servicio_DetSugerido sds=new Servicio_DetSugerido();
        int mesDemanda=sc.getControlUnico().getMesdemanda();
        int proy=sc.getControlUnico().getMesproyeccion();
        int[] meses=sc.getMesIniYFin(mesDemanda);
        int idrepuesto,idlinea;
        double ce,demProm,stock;
        int cantPendienteSug;
        Date[] fechas=new Date[2];
        fechas=calcularFechaInicio(meses, añoHoy);
        List listaRep=sr.getRepuestosASugerido();
        Iterator iteRep = listaRep.iterator();
        ArrayList<SugeridoRAM> tablaRAMBF= new ArrayList<SugeridoRAM>();
        while(iteRep.hasNext()){
            SugeridoRAM sugTabla=new SugeridoRAM();
            Object [] objRep = (Object []) iteRep.next();
            for(int j=0;j<4;j++){
                row[j]=convertString(objRep[j]);
            }
            row[4]=0;
            row[7]=convertInteger(objRep[4]);//stock fisico
            idrepuesto=Integer.parseInt(String.valueOf(objRep[6]));
            idlinea=Integer.parseInt(String.valueOf(objRep[7]));
            ce=getCantEntregadaXRepuesto(fechas[0],fechas[1],mesDemanda,idrepuesto,idlinea); 
            if(ce==0.0){
                row[8]=0;
            }else{
                row[8]=Math.round(ce);
            }
            cantPendienteSug=sds.getCantPedidaPendienteSugerido(idrepuesto);
            if(cantPendienteSug==-1){
                cantPendienteSug=0;
            }
            demProm=Double.parseDouble(String.valueOf(row[8]));
            stock= Double.parseDouble(String.valueOf(row[7]));
            if(cantPendienteSug!=-1){//Sugerido
                double sugerido=(demProm - (stock +  cantPendienteSug)) * proy;
                row[9]=Math.round(sugerido);
            }else{
                row[9]=0;
            }
            row[10]=convertDouble(objRep[5]);//fob
            sugTabla.setNumParte(String.valueOf(row[0]));
            sugTabla.setCodSecundario(String.valueOf(row[1]));
            sugTabla.setDescripcion(String.valueOf(row[2]));
            sugTabla.setAplicacion(String.valueOf(row[3]));
            int cantPedida=Integer.parseInt(String.valueOf(row[4]));
            row[5]=0;
            int cantEntregada=Integer.parseInt(String.valueOf(row[5]));
            sugTabla.setCantPedida(cantPedida);
            sugTabla.setCantEntregada(cantEntregada);
            sugTabla.setCantPendiente(cantPedida-cantEntregada);
            
            sugTabla.setStockFisico(Integer.parseInt(String.valueOf(row[7])));
            sugTabla.setDemanda(Integer.parseInt(String.valueOf(row[8])));
            sugTabla.setStockSugerido(Integer.parseInt(String.valueOf(row[9])));
            sugTabla.setFob(Double.parseDouble(String.valueOf(row[10])));
            sugTabla.setIdrepuesto(idrepuesto);
            sugTabla.setIdlinea(Integer.parseInt(String.valueOf(objRep[7])));
            tablaRAMBF.add(sugTabla);
        }
        return tablaRAMBF;
    }
    
    public void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
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
    
    private void formatearColumnaDerecha(double valor, int fila, int columna,IU_Sugerido iusug){
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
        iusug.tb_sugerido.setValueAt(moneyCad,fila,columna);
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna,IU_ActualizarSugerido iuactsug){
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
        iuactsug.tb_sugerido.setValueAt(moneyCad,fila,columna);
    }
    
    public int getAñoActual(){
        Calendar ahoraCal = Calendar.getInstance();
        int año=ahoraCal.get(Calendar.YEAR);
        return año;
    }
    
    public double getCantEntregadaXRepuesto(Date fechaIni, Date fechaFin, int mesdemanda,int idrepuesto,int idlinea){
        DetalleEsDAO detESdao=new DetalleEsDAO();
        return detESdao.getCantPedidaXRepuesto(fechaIni, fechaFin, mesdemanda, idrepuesto,idlinea);
    }
    
    public List getCantEntregadaXAño(int idRepuesto, int idLinea, Date fi, Date ff) {
        return detESdao.getCantPedidaXAño(idRepuesto,idLinea,fi,ff);
    }
    
    public int getNumIngresoNext(){
        Object obj=detESdao.getNumIngresoNext();
        if(obj==null){
            return 1;
        }else{
            return Integer.parseInt(String.valueOf(obj))+1;
        }
    }
    
    public boolean registrarDetallees(Detallees des){
        boolean registro=false;
        try{
            createAndStoreDetallees(des);
            registro=true;
        }catch(ExceptionInInitializerError ex){
            System.out.println("Error al registrar detalle de nota de ingreso (detalleES) en la BD:"+ex);
        }
        HibernateUtil.getSessionFactory().close();
        return registro;
    }
    
    private void createAndStoreDetallees(Detallees des) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(des);
            session.getTransaction().commit();
        }catch(Throwable ex) {
            System.err.println ("Creación inicial SessionFactory falló." + ex);
            throw new ExceptionInInitializerError (ex);
        }
    }
    
    public boolean registrarListaDetallees(List<Detallees> listaDetalles){
        int i = 1;
        
        boolean registro = false;
        try {
            createAndStoreListaDetallees(listaDetalles);
            registro = true;
            
        } catch ( ExceptionInInitializerError ex ) {
            System.out.println("Exception(1-registrarListaDetallees):" + ex);
            
        } catch ( Exception e ) {
            System.out.println("Exception(2-registrarListaDetallees): " + e.getMessage());
            
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
        return registro;
    }
    
    private void createAndStoreListaDetallees(List<Detallees> listaDet) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            for ( Detallees det : listaDet ) {
                Detallees d = (Detallees) session.merge(det);
                session.save(d);
            }
//            for ( Detallees d : listaDet ) {
//                session.save(d);
//                int idDet = d.getIddetalles();
//                System.out.println("salvado N° " + contador + " con idDet : " + idDet);
//                contador++;
//            }
            session.getTransaction().commit();
            
        } catch ( Exception ex ) {
            System.err.println ("Exception(createAndStoreListaDetallees):Creación inicial SessionFactory falló." + ex.getMessage());
            throw new ExceptionInInitializerError (ex);
            
        } finally {
            session.close();
        }
    }
    
    public boolean seRegistro(int idsugerido, int nroingreso){
        boolean registro=false;
        int num=detESdao.contarDetallesNotaIngreso(idsugerido,nroingreso);
        if(num>0){  registro=true;  }
        return registro;
    }
    
    public boolean seRegistro(int idsugerido){
        boolean registro=false;
        int num=detESdao.contarDetallesNotaIngreso(idsugerido);
        if(num>0){  registro=true;  }
        return registro;
    }
    
    public int contarDetallesConNI(int idsugerido){
        return detESdao.contarDetallesNotaIngreso(idsugerido);
    }
    
    public String getMotivo(int idSugerido){
        return detESdao.getMotivo(idSugerido);
    }
    
    public Detallees getDetallees(int idsugerido){
        Detallees det=new Detallees();
        det=detESdao.getDetallees(idsugerido);
        return det;
    }
}