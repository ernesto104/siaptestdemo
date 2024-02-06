package Entidades.Importaciones;
import Presentacion.ActualizarPrecios.IU_MantenimientoRepuestos;
import Presentacion.CuentasxCobrarFacturas.FREP042;
import Presentacion.Importaciones.FREP027;
import Presentacion.Importaciones.IU_ImpPedido;
import Presentacion.Importaciones.IU_ImpSugerido;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Presentacion.Importaciones.IU_ImpSugeridoProforma;
import Presentacion.Importaciones.IU_Sugerido;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
public class Ordenacion {

    IU_Sugerido iusug;
    IU_ImpSugerido iuPrintSug;
    IU_ImpSugeridoProforma iuPrintSugProf;
    IU_ImpPedido iuPrintPed;
    IU_MantenimientoRepuestos iuMantenimientoRep;
    private boolean ascIdRep=true;
    private boolean ascNumParte=true;
    private boolean ascNumParte2=true;
    private boolean ascDesc=true;
    private boolean ascDesc2=true;
    private boolean ascCodSec=true;
    private boolean ascCodLinea=true;
    private boolean ascPartAranc=true;
    private boolean ascIdLin=true;
    FREP027 iuMaestroRep;
    FREP042 iuCtaCorriente;
    IU_ImpPedido iuOrdenCompra;
    IU_ActualizarSugerido iuActSug;
    public void eventoTabla(IU_Sugerido iusugerido){
        iusug=iusugerido;
        iusug.tb_sugerido.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iusug.tb_sugerido.getModel().getRowCount()!=0){
                    int col = iusug.tb_sugerido.columnAtPoint(e.getPoint()); 
                    if(col==0){      ordenarTablaPorNumParte(iusug);
                    }else if(col==1){ordenarTablaPorCodSecundario(iusug);
                    }else if(col==2){ordenarTablaPorDescripcion(iusug); }
                }
            }
        });
    }
    public void eventoTabla(IU_ActualizarSugerido actSugerido){
        iuActSug=actSugerido;
        iuActSug.tb_sugerido.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iuActSug.tb_sugerido.getModel().getRowCount()!=0){
                    int col = iuActSug.tb_sugerido.columnAtPoint(e.getPoint()); 
                    if(col==0){      ordenarTablaPorNumParte(iuActSug);
                    }else if(col==1){ordenarTablaPorCodSecundario(iuActSug);
                    }else if(col==2){ordenarTablaPorDescripcion(iuActSug); }
                }
            }
        });
    }
    public void eventoTabla(IU_ImpSugerido iuprintsugerido){
        iuPrintSug=iuprintsugerido;
        iuPrintSug.tbResultados.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                 if(iuPrintSug.tbResultados.getModel().getRowCount()!=0){
                    int col = iuPrintSug.tbResultados.columnAtPoint(e.getPoint()); 
                    if(col==0){      ordenarTablaPorCodLinea(iuPrintSug);
                    }else if(col==1){ ordenarTablaPorNumParte(iuPrintSug);}
                }
            }
        });
    }
    public void eventoTabla(IU_ImpSugeridoProforma iuprintsugprof){
        iuPrintSugProf=iuprintsugprof;
        iuPrintSugProf.tbResultados.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                 if(iuPrintSugProf.tbResultados.getModel().getRowCount()!=0){
                    int col = iuPrintSugProf.tbResultados.columnAtPoint(e.getPoint()); 
                    if(col==0){      ordenarTablaPorCodLinea(iuPrintSugProf);
                    }else if(col==1){ ordenarTablaPorNumParte(iuPrintSugProf);}
                }
            }
        });
    }
    public void eventoTabla(IU_ImpPedido iuprintpedido){
        iuPrintPed=iuprintpedido;
        iuPrintPed.tbResultados.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iuPrintPed.tbResultados.getModel().getRowCount()!=0){
                    int col = iuPrintPed.tbResultados.columnAtPoint(e.getPoint()); 
                    if(col==2){      ordenarTablaPorPartArancYNumParte(iuPrintPed);
                    }else if(col==3){ordenarTablaPorPartArancYDescripcion(iuPrintPed); 
                    }
                }
            }
        });
    }
    public void eventoTabla(FREP027 iumaestrorep){
        iuMaestroRep=iumaestrorep;
        iuMaestroRep.tbRepuestos.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iuMaestroRep.tbRepuestos.getModel().getRowCount()!=0){
                    int col = iuMaestroRep.tbRepuestos.columnAtPoint(e.getPoint()); 
                    if(col==0){     ordenarTablaPorNumParte(iuMaestroRep); }
                    else if(col==1){ordenarTablaPorCodSecundario(iuMaestroRep);
                    }else if(col==2){ordenarTablaPorDescripcion(iuMaestroRep); }
                }
            }
        });
    }
    
    public void eventoTabla(FREP042 ctaCorriente){
        iuCtaCorriente=ctaCorriente;
        iuCtaCorriente.tablaFactura.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iuCtaCorriente.tablaFactura.getModel().getRowCount()!=0){
                    int col = iuCtaCorriente.tablaFactura.columnAtPoint(e.getPoint()); 
                    System.out.println("col select:" + col);
                    if(col==5){     ordenarTablaPorNombreCliente(iuCtaCorriente); }
                    /*else if(col==1){ordenarTablaPorCodSecundario(iuMaestroRep);
                    }else if(col==2){ordenarTablaPorDescripcion(iuMaestroRep); }   */
                }
            }
        });
    }
    
    public void ordenarTablaPorNombreCliente(FREP042 iuctacorriente){
        System.out.println("ordenando por nombre de cliente");
        iuCtaCorriente=iuctacorriente;
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iuCtaCorriente.tablaFactura.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                System.out.println("comparando...");
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(5));
                String d2 = String.valueOf(fila2.get(5));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iuCtaCorriente.tablaFactura.repaint();
    }
    
    public void eventoTabla(IU_MantenimientoRepuestos iuMantRep){
        iuMantenimientoRep=iuMantRep;
        iuMantenimientoRep.tbRepuestos.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { 
                if(iuMantenimientoRep.tbRepuestos.getModel().getRowCount()!=0){
                    int col = iuMantenimientoRep.tbRepuestos.columnAtPoint(e.getPoint()); 
                    if(col==0){     ordenarTablaPorIdRepuesto(iuMantenimientoRep); }
                    else if(col==1){ordenarTablaPorIdLinea(iuMantenimientoRep);
                    }else if(col==2){ordenarTablaPorDescripcion(iuMantenimientoRep); }
                }
            }
        });
    }
    public void ordenarTablaPorIdRepuesto(IU_MantenimientoRepuestos iuMantRep){
        ascIdRep=!ascIdRep;
        List<Object[]> lista = ((DefaultTableModel) iuMantRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String idRep1 = String.valueOf(fila1.get(0));
                String idRep2 = String.valueOf(fila2.get(0));
                if(ascIdRep){
                    if(Integer.parseInt(idRep1)>Integer.parseInt(idRep2)){
                        return 1;
                    }else if(Integer.parseInt(idRep1)==Integer.parseInt(idRep2)){
                        return 0;
                    }else{
                        return -1;
                    }
                }else{
                    if(Integer.parseInt(idRep1)<Integer.parseInt(idRep2)){
                        return 1;
                    }else if(Integer.parseInt(idRep1)==Integer.parseInt(idRep2)){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            }
        });
        iuMantRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorNumParte(IU_Sugerido iusug){
        ascNumParte=!ascNumParte;
        List<Object[]> lista = ((DefaultTableModel) iusug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(0));
                String np2 = String.valueOf(fila2.get(0));
                if(ascNumParte){
                    return np1.compareToIgnoreCase(np2);
                }else{
                    return np2.compareToIgnoreCase(np1);
                }
            }
        });
        iusug.tb_sugerido.repaint();
    }
    public void ordenarTablaPorNumParte(IU_ImpSugerido iuprintsug){
        ascNumParte=!ascNumParte;
        List<Object[]> lista = ((DefaultTableModel) iuPrintSug.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(1));
                String np2 = String.valueOf(fila2.get(1));
                if(ascNumParte){
                    return np1.compareToIgnoreCase(np2);
                }else{
                    return np2.compareToIgnoreCase(np1);
                }
            }
        });
        iuPrintSug.tbResultados.repaint();
    }
    public void ordenarTablaPorNumParte(IU_ActualizarSugerido iuActSug){
        ascNumParte=!ascNumParte;
        List<Object[]> lista = ((DefaultTableModel) iuActSug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(0));
                String np2 = String.valueOf(fila2.get(0));
                if(ascNumParte){
                    return np1.compareToIgnoreCase(np2);
                }else{
                    return np2.compareToIgnoreCase(np1);
                }
            }
        });
        iuActSug.tb_sugerido.repaint();
    }
    public void ordenarTablaPorPartArancYNumParte(IU_ImpPedido iuOrden){/*Doble filtro*/
        iuOrdenCompra=iuOrden;
        ascPartAranc=!ascPartAranc;
        final List<Object[]> lista = ((DefaultTableModel) iuOrdenCompra.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            int val;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(0));
                String np2 = String.valueOf(fila2.get(0));
                if(ascPartAranc){
                    val= np1.compareToIgnoreCase(np2);
                }else{
                    val= np2.compareToIgnoreCase(np1);
                }
                return val;
            }
        });
        ascNumParte2=!ascNumParte2;
        Collections.sort(lista, new Comparator() {
            int val2;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                
                String np1 = String.valueOf(fila1.get(0));//partida arancelaria
                String np2 = String.valueOf(fila2.get(0));//partida arancelaria
                
                String np3 = String.valueOf(fila1.get(2));//nº parte
                String np4 = String.valueOf(fila2.get(2));//nº parte
                if(np1.compareToIgnoreCase(np2)==0){
                    if(ascNumParte2){
                        val2= np3.compareToIgnoreCase(np4);
                    }else{
                        val2= np4.compareToIgnoreCase(np3);
                    }
                }else if(np1.compareToIgnoreCase(np2)<0){
                    val2= np2.compareToIgnoreCase(np1);
                }else if(np1.compareToIgnoreCase(np2)>0){
                    val2= np1.compareToIgnoreCase(np2);
                }
                return val2;
            }
        });
        iuOrdenCompra.tbResultados.repaint();
    }
    public void ordenarTablaPorPartArancYDescripcion(IU_ImpPedido iuOrden){/*Doble filtro*/
        iuOrdenCompra=iuOrden;
        ascPartAranc=!ascPartAranc;
        final List<Object[]> lista = ((DefaultTableModel) iuOrdenCompra.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            int val;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(0));
                String np2 = String.valueOf(fila2.get(0));
                if(ascPartAranc){
                    val= np1.compareToIgnoreCase(np2);
                }else{
                    val= np2.compareToIgnoreCase(np1);
                }
                return val;
            }
        });
        ascDesc2=!ascDesc2;
        Collections.sort(lista, new Comparator() {
            int val2;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                
                String np1 = String.valueOf(fila1.get(0));//partida arancelaria
                String np2 = String.valueOf(fila2.get(0));//partida arancelaria
                
                String np3 = String.valueOf(fila1.get(3));//descripcion
                String np4 = String.valueOf(fila2.get(3));//descripcion
                if(np1.compareToIgnoreCase(np2)==0){
                    if(ascDesc2){
                        val2= np3.compareToIgnoreCase(np4);
                    }else{
                        val2= np4.compareToIgnoreCase(np3);
                    }
                }else if(np1.compareToIgnoreCase(np2)<0){
                    val2= np2.compareToIgnoreCase(np1);
                }else if(np1.compareToIgnoreCase(np2)>0){
                    val2= np1.compareToIgnoreCase(np2);
                }
                return val2;
            }
        });
        iuOrdenCompra.tbResultados.repaint();
    }
    public void ordenarTablaPorNumParte(FREP027 iumaestrorep){/*Doble filtro*/
        iuMaestroRep=iumaestrorep;
        ascNumParte=!ascNumParte;
        final List<Object[]> lista = ((DefaultTableModel) iuMaestroRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            int val;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(0));
                String np2 = String.valueOf(fila2.get(0));
                if(ascNumParte){
                    val= np1.compareToIgnoreCase(np2);
                }else{
                    val= np2.compareToIgnoreCase(np1);
                }
                return val;
            }
        });
        ascDesc=!ascDesc;
        Collections.sort(lista, new Comparator() {
            int val2;
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                
                String np1 = String.valueOf(fila1.get(0));//n°parte
                String np2 = String.valueOf(fila2.get(0));//n°parte
                
                String np3 = String.valueOf(fila1.get(2));//descripcion
                String np4 = String.valueOf(fila2.get(2));//descripcion
                if(np1.compareToIgnoreCase(np2)==0){
                    if(ascDesc){
                        val2= np3.compareToIgnoreCase(np4);
                    }else{
                        val2= np4.compareToIgnoreCase(np3);
                    }
                }else if(np1.compareToIgnoreCase(np2)<0){
                    val2= np2.compareToIgnoreCase(np1);
                }else if(np1.compareToIgnoreCase(np2)>0){
                    val2= np1.compareToIgnoreCase(np2);
                }
                return val2;
            }
        });
        iuMaestroRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorCodSecundario(IU_Sugerido iusugerido){
        iusug=iusugerido;
        ascCodSec=!ascCodSec;
        List<Object[]> lista = ((DefaultTableModel) iusug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String cs1 = String.valueOf(fila1.get(1));
                String cs2 = String.valueOf(fila2.get(1));
                if(ascCodSec){
                    return cs1.compareToIgnoreCase(cs2);
                }else{
                    return cs2.compareToIgnoreCase(cs1);
                }
            }
        });
        iusug.tb_sugerido.repaint();
    }
    public void ordenarTablaPorCodSecundario(FREP027 iumaestrorep){
        iuMaestroRep=iumaestrorep;
        ascCodSec=!ascCodSec;
        List<Object[]> lista = ((DefaultTableModel) iuMaestroRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String cs1 = String.valueOf(fila1.get(1));
                String cs2 = String.valueOf(fila2.get(1));
                if(ascCodSec){
                    return cs1.compareToIgnoreCase(cs2);
                }else{
                    return cs2.compareToIgnoreCase(cs1);
                }
            }
        });
        iuMaestroRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorCodSecundario(IU_ActualizarSugerido iuactsug){
        iuActSug=iuactsug;
        ascCodSec=!ascCodSec;
        List<Object[]> lista = ((DefaultTableModel) iuActSug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String cs1 = String.valueOf(fila1.get(1));
                String cs2 = String.valueOf(fila2.get(1));
                if(ascCodSec){
                    return cs1.compareToIgnoreCase(cs2);
                }else{
                    return cs2.compareToIgnoreCase(cs1);
                }
            }
        });
        iuActSug.tb_sugerido.repaint();
    }
    public void ordenarTablaPorIdLinea(IU_MantenimientoRepuestos iuMantRep){
        ascIdLin=!ascIdLin;
        List<Object[]> lista = ((DefaultTableModel) iuMantRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String idLin1 = String.valueOf(fila1.get(1));
                String idLin2 = String.valueOf(fila2.get(1));
                if(ascIdLin){
                    return idLin1.compareToIgnoreCase(idLin2);
                }else{
                    return idLin2.compareToIgnoreCase(idLin1);
                }
            }
        });
        iuMantRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorDescripcion(IU_Sugerido iusugerido){
        iusug=iusugerido;
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iusug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(2));
                String d2 = String.valueOf(fila2.get(2));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iusug.tb_sugerido.repaint();
    }
     public void ordenarTablaPorDescripcion(IU_ActualizarSugerido iuactsug){
        iuActSug=iuactsug;
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iuActSug.tb_sugerido.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(2));
                String d2 = String.valueOf(fila2.get(2));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iuActSug.tb_sugerido.repaint();
    }
    public void ordenarTablaPorDescripcion(IU_ImpPedido iuimppedido){
        iuPrintPed=iuimppedido;
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iuPrintPed.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(3));
                String d2 = String.valueOf(fila2.get(3));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iuPrintPed.tbResultados.repaint();
    }
    public void ordenarTablaPorDescripcion(FREP027 iumaestrorep){
        iuMaestroRep=iumaestrorep;
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iuMaestroRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(2));
                String d2 = String.valueOf(fila2.get(2));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iuMaestroRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorDescripcion(IU_MantenimientoRepuestos iuMantRep){
        ascDesc=!ascDesc;
        List<Object[]> lista = ((DefaultTableModel) iuMantRep.tbRepuestos.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String d1 = String.valueOf(fila1.get(2));
                String d2 = String.valueOf(fila2.get(2));
                if(ascDesc){
                    return d1.compareToIgnoreCase(d2);
                }else{
                    return d2.compareToIgnoreCase(d1);
                }
            }
        });
        iuMantRep.tbRepuestos.repaint();
    }
    public void ordenarTablaPorCodLinea(IU_ImpSugerido iuprintsugerido){
        iuPrintSug=iuprintsugerido;
        ascCodLinea=!ascCodLinea;
        List<Object[]> lista = ((DefaultTableModel) iuPrintSug.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String cl1 = String.valueOf(fila1.get(0));
                String cl2 = String.valueOf(fila2.get(0));
                if(ascCodLinea){
                    return cl1.compareToIgnoreCase(cl2);
                }else{
                    return cl2.compareToIgnoreCase(cl1);
                }
            }
        });
        iuPrintSug.tbResultados.repaint();
    }
    public void ordenarTablaPorCodLinea(IU_ImpSugeridoProforma iuprintsugprof){
        iuPrintSugProf=iuprintsugprof;
        ascCodLinea=!ascCodLinea;
        List<Object[]> lista = ((DefaultTableModel) iuPrintSugProf.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String cl1 = String.valueOf(fila1.get(0));
                String cl2 = String.valueOf(fila2.get(0));
                if(ascCodLinea){
                    return cl1.compareToIgnoreCase(cl2);
                }else{
                    return cl2.compareToIgnoreCase(cl1);
                }
            }
        });
        iuPrintSugProf.tbResultados.repaint();
    }
    public void ordenarTablaPorNumParte(IU_ImpSugeridoProforma iuprintsugprof){
        ascNumParte=!ascNumParte;
        List<Object[]> lista = ((DefaultTableModel) iuPrintSugProf.tbResultados.getModel()).getDataVector();
        Collections.sort(lista, new Comparator() {
            public int compare(Object o1, Object o2) {
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                String np1 = String.valueOf(fila1.get(1));
                String np2 = String.valueOf(fila2.get(1));
                if(ascNumParte){
                    return np1.compareToIgnoreCase(np2);
                }else{
                    return np2.compareToIgnoreCase(np1);
                }
            }
        });
        iuPrintSugProf.tbResultados.repaint();
    }
}