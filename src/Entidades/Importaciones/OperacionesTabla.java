package Entidades.Importaciones;

import Entidades.Otros.RepuestoDemanda;
import Mantenimiento.Importaciones.RepuestosDAO;
import Presentacion.Importaciones.FREP027;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

public class OperacionesTabla {
    
    static private FREP027 maestro;
    private Servicio_Repuestos sr;
    private RepuestosDAO repDao;
    
    private Date fechaInicio;
    private Date fechaFin;
    
    private int vectorMesXPosicion[];
    
    public OperacionesTabla(FREP027 m) {
        sr = new Servicio_Repuestos();
        maestro = m;
        fechaInicio = m.getFechaInicio();
        fechaFin = m.getFechaFin();

        vectorMesXPosicion = m.getVectorMesXPosicion();
    }
    
//    public int insertarRepuestosATabla(String valor, int opc) {
//        int num = 0;
//        limpiarTabla();
//        List<Repuestos> listadoRep = new ArrayList<Repuestos>();
//        
//        switch ( opc ) {
//            case 1: listadoRep = sr.buscarRepuestosXNombre(valor); break;
//            case 3: listadoRep = sr.buscarRepuestosXDescripcion(valor); break;
//            case 4: listadoRep = sr.buscarRepuestosXMarca(valor); break;
//            case 5: listadoRep = sr.buscarRepuestosXStock(valor); break;
//            case 6:
//                repDao = new RepuestosDAO();
//                listadoRep = repDao.getRepuestosAscNumParte(); break;
//        }
//        agregarRepuestoATabla(listadoRep);
//        num = listadoRep.size();
//        return num;
//    }
    
    public int insertarRepuestosATabla(String valor, int opc) {
        //System.out.println("(insertarRepuestosATabla)opc:" + opc);
        int num = 0;
        limpiarTabla();
        //List<Repuestos> listadoRep = new ArrayList<Repuestos>();
        List<RepuestoDemanda> lstRepDem = new ArrayList<RepuestoDemanda>();
        
        System.out.println("Fecha Inicio : " + fechaInicio);
        System.out.println("Fecha Fin : " + fechaFin);
        System.out.println("Hasta aqui....");
        switch ( opc ) {
            case 1:
                //listadoRep = sr.buscarRepuestosXNombre(valor); break;
                lstRepDem = sr.buscarDemandaAnualRepuestosXNombre(fechaInicio, fechaFin, valor); 
                break;
            case 3: 
                //lstRepDem = sr.buscarRepuestosXDescripcion(valor);
                lstRepDem = sr.buscarDemandaAnualRepuestosXDescripcion(fechaInicio, fechaFin, valor);
                break;
            case 4:
                //lstRepDem = sr.buscarRepuestosXMarca(valor);
                lstRepDem = sr.buscarDemandaAnualRepuestosXMarca(fechaInicio, fechaFin, valor);
                break;
            case 5:
                //lstRepDem = sr.buscarRepuestosXStock(valor);
                lstRepDem = sr.buscarDemandaAnualRepuestosXStock(fechaInicio, fechaFin, valor);
                break;
            case 6:
                repDao = new RepuestosDAO();
                //lstRepDem = repDao.getRepuestosAscNumParte();
                lstRepDem = repDao.getDemandaAnualRepuestosAscNumParte(fechaInicio, fechaFin);
                break;
        }
        agregarRepuestoATabla(lstRepDem);
        
        num = lstRepDem.size();
        return num;
    }
    
    private boolean noEsRepetidoIdRepuesto(String idRep, String anteriorIdRep, int indiceRegistros) {
        if ( indiceRegistros == 0 ) {
            return false;
            
        } else {
            if ( idRep.equals(anteriorIdRep) ) {
                return false;
                
            } else {
                return true;
            }
        }
    }
    
    private void agregarRepuestoATabla(List<RepuestoDemanda> repuestos) {
        Iterator it = repuestos.iterator();
        int indiceTabla = 0;
        String[] demandaMeses = new String[12];
        for ( int i = 0; i < 12; i++ ) {
            demandaMeses[i] = "0";
        }
        int total = 0;
        String anteriorIdRep = "";
        int indiceRegistros = 0;
        RepuestoDemanda repu;
        RepuestoDemanda repuAnterior = null;
        int numReg = repuestos.size();
        
        while ( it.hasNext() ) {
            repu = (RepuestoDemanda)it.next();
            String idRep = repu.getIdRepuesto();
            
            if ( noEsRepetidoIdRepuesto(idRep, anteriorIdRep, indiceRegistros) ) {
                // 4° Obtener el "Promedio Mensual" y el "Total Anual" (Asignar a JTable el "Repuesto Anterior" (repuAnterior) completo de "demandaMeses")
                repuAnterior.setDemandaTotal(getComaMillar(String.valueOf(total)));
                double prom = Double.parseDouble(String.valueOf(total)) / 12.0;
                Object promedio = Math.round(prom);
                repuAnterior.setDemandaPromedio(getComaMillar(String.valueOf(promedio)));
                repuAnterior.setDemandaMeses(demandaMeses);
                agregarFilaATabla(repuAnterior, indiceTabla);
                indiceTabla++;
                
                total = 0;
                demandaMeses = new String[12];
                for ( int i = 0; i < 12; i++ ) {
                    demandaMeses[i] = "0";
                }
            }
            
            // Agregar el Repuesto actual ("repu") que se comparó con el último repuAnterior.
            // 3° Obtener cantidad de salidas o DEMANDA por cada uno de los últimos 12 meses.
            for ( int pos = 0; pos < 12; pos++ ) {
                int temp = Integer.parseInt(repu.getMes()); // 8

                if ( temp == vectorMesXPosicion[pos] ) {
                    String demandaString = repu.getDemanda(); // 10
                    int demanda = Integer.parseInt(demandaString);
                    demandaMeses[pos] = demandaString;
                    total += demanda;
                    break;
                }
            }
            repuAnterior = repu;
            anteriorIdRep = idRep;
            indiceRegistros++;
            
            if ( indiceRegistros == numReg ) {
                // Agregar el último item de Repuestos al JTable.
                repuAnterior.setDemandaTotal(getComaMillar(String.valueOf(total)));
                double prom = Double.parseDouble(String.valueOf(total)) / 12.0;
                Object promedio = Math.round(prom);
                repuAnterior.setDemandaPromedio(getComaMillar(String.valueOf(promedio)));
                repuAnterior.setDemandaMeses(demandaMeses);
                agregarFilaATabla(repuAnterior, indiceTabla);
                indiceTabla++;
                
                total = 0;
                demandaMeses = new String[12];
                for ( int i = 0; i < 12; i++ ) {
                    demandaMeses[i] = "0";
                }
            }
        }
        // System.out.println("numReg: " + numReg);
        //System.out.println("indiceTabla:" + indiceTabla);
    }
    
    //private void agregarFilaATabla(Repuestos r,int fila){
    private void agregarFilaATabla(RepuestoDemanda r,int fila){
        DefaultTableModel temp = (DefaultTableModel) maestro.tbRepuestos.getModel();
        //String codSec = r.getCodigoseg();
        String codSec = r.getCodSecundario();
        //if ( r.getCodigoseg() == null ) { codSec = "";  }
        if ( r.getCodSecundario()== null ) { codSec = "";  }
        Object nuevo[] = new Object[21];
        nuevo[0] = r.getNumeroRepuesto();
        nuevo[1] = codSec;
        nuevo[2] = r.getDescripcion();
        nuevo[3] = r.getDescrmodelo();
        nuevo[4] = getComaMillar(setInt(r.getStock()));
        //setDouble(r.getPreciolista()), 
        nuevo[5] = setDouble(r.getFobultimo());
        nuevo[6] = r.getMarca();
        
        // Agregando la demanda dinámica de los últimos 12 meses.
        String[] dm = r.getDemandaMeses();
        for ( int i = 0; i < 12; i++ ) {
            nuevo[i+7] = dm[i];
        }
        nuevo[19] = r.getDemandaTotal();
        nuevo[20] = r.getDemandaPromedio();
        
        temp.addRow(nuevo);
        formatearColumnaDerecha(Double.parseDouble(String.valueOf(nuevo[5])), fila, 5);
    }
    
    private int setInt(Object obj) {
        int val = 0;
        if ( obj != null ) { val = Integer.parseInt(String.valueOf(obj)); }
        return val;
    }
    
    private double setDouble(Object obj) {
        double val = 0;
        if ( obj != null ) { val = Double.parseDouble(String.valueOf(obj)); }
        return val;
    }
    
    private String getComaMillar(Object valor) {
//        System.out.println("getComaMillar...");
        String cantStr = String.valueOf(valor);
        int cantInt = Integer.parseInt(cantStr);
        int cifMillar,cifResto;
        
        if ( cantStr.length() > 3 && Integer.parseInt(cantStr) > 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            if ( cifResto == 0 && cifMillar != 0 ) {
//                cantStr=cifMillar+",000";
                cantStr = cifMillar + "000";
                
            } else if ( cifResto / 100 != 0 ) {
//                cantStr=cifMillar+","+cifResto;
                cantStr = cifMillar + "" + cifResto;
                
            } else if ( cifResto / 10 != 0 ) {
//                cantStr=cifMillar+",0"+cifResto;
                cantStr = cifMillar+"0"+cifResto;
                
            } else {
//                cantStr=cifMillar+",00"+cifResto;
                cantStr = cifMillar + "00" + cifResto;
            }
        } else if ( cantStr.length() > 4 && Integer.parseInt(cantStr) < 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            if ( cifResto == 0 && cifMillar != 0 ) {
//                cantStr=cifMillar+",000";
                cantStr = cifMillar + "000";
                
            } else if ( cifResto / 100 != 0 ) {
//                cantStr=cifMillar+","+Math.abs(cifResto);
                cantStr = cifMillar + "" + Math.abs(cifResto);
                
            } else if ( cifResto / 10 != 0 ) {
//                cantStr=cifMillar+",0"+Math.abs(cifResto);
                cantStr = cifMillar + "0" + Math.abs(cifResto);
                
            } else {
//                cantStr=cifMillar+",00"+Math.abs(cifResto);
                cantStr = cifMillar + "00" + Math.abs(cifResto);
            }
        }
        return cantStr;
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna) {
//        System.out.println("formatearColumnaDerecha...");
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        maestro.tbRepuestos.setValueAt(moneyCad, fila, columna);
    }
    
    public void limpiarTabla(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) maestro.tbRepuestos.getModel();
            int filas = maestro.tbRepuestos.getRowCount();
            for ( int i = 0; filas > i; i++ ) {
                modelo.removeRow(0);
            }
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
}