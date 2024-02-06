/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Servicios.facturacion.Billete;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        generarTxt();
        
//        String leyenda = getLeyenda("336.00", "US$");
//        System.out.println("leyenda:" + leyenda);
        
//        String ejemplo1 = formatearNroSerie("FC", "1");
//        System.out.println("ejemplo1:" + ejemplo1);
        
//        String ejemplo2 = formatearNroSerie("F", "1");
//        System.out.println("ejemplo2:" + ejemplo2);
        String nns = "F001";
        String primeraLetrDoc = nns.substring(0,1);
        System.out.println("primeraLetrDoc:" + primeraLetrDoc);
    }
    
    private static String formatearNroSerie(String prefijo, String nroSerie) {
        return prefijo + formatearNroSer(prefijo, nroSerie);
        // String nroDoc = iuf.tx_doc.getText(); // N° Factura
        //guia.setNroFactura(tipDoc + formatearNroSerie(nroSerie) + "-" + nroDoc);
    }
    
    private static String formatearNroSer(String prefijo, String serie) {
        String nroSerie = "";
        int largo = 3 - serie.length();
        
        if (prefijo.length() == 2) {
            largo = 2 - serie.length();
        }
        
        for ( int i = 0; i < largo; i++ ) {
            nroSerie = nroSerie + "0";
        }
        nroSerie = nroSerie + serie;
        return nroSerie;
    }
    
    // Anterior (para Nro serie siempre a 3 digitos)
//    private static String formatearNroSerie(String serie) {
//        String nroSerie = "";
//        int largo = 3 - serie.length();;
//        for ( int i = 0; i < largo; i++ ) {
//            nroSerie = nroSerie + "0";
//        }
//        nroSerie = nroSerie + serie;
//        return nroSerie;
//    }
    
    private static String getLeyenda(String montoPagar, String moneda) {
        String glosa = "PEN";
//        if ( "S/.".equals(moneda) ) {
//            glosa = "PEN";
//        } else if ( "US$".equals(moneda) ) {
//            glosa = "USD";
//        }
        double mp = Double.parseDouble(montoPagar);
        //return Billete.BilleteX(mp, "USD".equals(glosa) ? " DÓLARES AMERICANOS" : " SOLES");
        return Billete.BilleteX(mp);
    }
    
    private static void generarTxt() {
        String nombreArchivo = "archivo";
        String ruta = "X:\\txtDocElectr\\" + nombreArchivo + ".txt";
        File archivo = new File(ruta);
        BufferedWriter bw = null;
        
        try {
            if ( archivo.exists() ) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("El fichero de texto ya estaba creado.");
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("Acabo de crear el fichero de texto.");
            }
            bw.flush();
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void prueba1(){
//        System.out.println("PRECIO:" + formatearMonetario("89.5", 2));
//        separa();
//        ejemploRedondeo();
//        ejemploOrdenar2Filtros();
        String fechaYHora = getStringFechayHora();
        System.out.println("fechaYHora:" + fechaYHora);
    }
    
    private static String getStringFechayHora() {
        Date fechaHora = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(fechaHora);
        String[] fechaArray = fecha.split("/");
        String fechaString = fechaArray[2] + "_" + fechaArray[1] + "_" + fechaArray[0];
        
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fechaHora);
        String[] horaArray = hora.split(":");
        String horaString = horaArray[0] + "_" + horaArray[1] + "_" + horaArray[2];
        
        String fechayHora = fechaString + "_" + horaString;
        return fechayHora;
    }
    
    private static void ejemploOrdenar2Filtros() {
//        Object[] e1 = {"CULATA DE MOTOR", "MITSUBISHI 4D56T 16V"};
//        Object[] e2 = {"EJE LEVAS", "MITSUBISHI 4D56U 16v"};
//        Object[] e3 = {"EJE LEVAS", "MITSUBISHI 4D56U 16v"};
//        Object[] e4 = {"CULATA DE MOTOR", "NISSAN SD23"};
//        Object[] e5 = {"CULATA DE MOTOR", "NISSAN Z24-4"};
//        Object[] e6 = {"CULATA DE MOTOR", "SUZUKI G16B"};
//        Object[] e7 = {"CULATA DE MOTOR", "TOYOTA 2L NEW (MOD)"};
//        Object[] e8 = {"CULATA DE MOTOR", "SUZUKI F10A CHINO"};
//        Object[] e9 = {"CULATA DE MOTOR", "SUZUKI F8CV"};
//        Object[] e10 = {"BRAZO BIELA", "NISSAN TD25/TD27/TD42"};
//        Object[] e11 = {"EJE LEVAS", "TOYOTA 2E"};
//        Object[] e12 = {"CULATA DE MOTOR", "HYUNDAI D4CB 16V"};
//        Object[] e13 = {"EJE LEVAS", "MITSUBISHI 4D34"};
//        Object[] e14 = {"CULATA DE MOTOR", "ISUZU 4ZD1"};
//        Object[] e15 = {"CULATA DE MOTOR", "CHEVROLET B10B12"};
//        Object[] e16 = {"CIGÜEÑAL", "NISSAN TD27"};
//        Object[] e17 = {"CIGÜEÑAL", "SUZUKI G16B"};
//        Object[] e18 = {"CIGÜEÑAL", "TOYOTA 2L(ANT)"};
//        Object[] e19 = {"CIGÜEÑAL", "TOYOTA 2L(NEW)"};
//        Object[] e20 = {"CULATA DE MOTOR", "NISSAN GA16 16V"};
//        Object[] e21 = {"CULATA DE MOTOR", "NISSAN YD22"};
//        Object[] e22 = {"EJE LEVAS", "HYUNDAI D4BH"};
//        Object[] e23 = {"CIGÜEÑAL", "MITSUBISHI 4D31 4D32"};
//        Object[] e24 = {"CULATA DE MOTOR", "MITSUBISHI 4M40-T"};
//        Object[] e25 = {"CULATA DE MOTOR", "MADA R2"};
        
        Entidad e1 = new Entidad("CULATA DE MOTOR", "MITSUBISHI 4D56T 16V");
        Entidad e2 = new Entidad("EJE LEVAS", "MITSUBISHI 4D56U 16v");
        Entidad e3 = new Entidad("EJE LEVAS", "MITSUBISHI 4D56U 16v");
        Entidad e4 = new Entidad("CULATA DE MOTOR", "NISSAN SD23");
        Entidad e5 = new Entidad("CULATA DE MOTOR", "NISSAN Z24-4");
        Entidad e6 = new Entidad("CULATA DE MOTOR", "SUZUKI G16B");
        Entidad e7 = new Entidad("CULATA DE MOTOR", "TOYOTA 2L NEW (MOD)");
        Entidad e8 = new Entidad("CULATA DE MOTOR", "SUZUKI F10A CHINO");
        Entidad e9 = new Entidad("CULATA DE MOTOR", "SUZUKI F8CV");
        Entidad e10 = new Entidad("BRAZO BIELA", "NISSAN TD25/TD27/TD42");
        Entidad e11 = new Entidad("EJE LEVAS", "TOYOTA 2E");
        Entidad e12 = new Entidad("CULATA DE MOTOR", "HYUNDAI D4CB 16V");
        Entidad e13 = new Entidad("EJE LEVAS", "MITSUBISHI 4D34");
        Entidad e14 = new Entidad("CULATA DE MOTOR", "ISUZU 4ZD1");
        Entidad e15 = new Entidad("CULATA DE MOTOR", "CHEVROLET B10B12");
        Entidad e16 = new Entidad("CIGÜEÑAL", "NISSAN TD27");
        Entidad e17 = new Entidad("CIGÜEÑAL", "SUZUKI G16B");
        Entidad e18 = new Entidad("CIGÜEÑAL", "TOYOTA 2L(ANT)");
        Entidad e19 = new Entidad("CIGÜEÑAL", "TOYOTA 2L(NEW)");
        Entidad e20 = new Entidad("CULATA DE MOTOR", "NISSAN GA16 16V");
        Entidad e21 = new Entidad("CULATA DE MOTOR", "NISSAN YD22");
        Entidad e22 = new Entidad("EJE LEVAS", "HYUNDAI D4BH");
        Entidad e23 = new Entidad("CIGÜEÑAL", "MITSUBISHI 4D31 4D32");
        Entidad e24 = new Entidad("CULATA DE MOTOR", "MITSUBISHI 4M40-T");
        Entidad e25 = new Entidad("CULATA DE MOTOR", "MADA R2");
        
//        List<Object[]> lista = new ArrayList<Object[]>();
        List<Entidad> lista = new ArrayList<Entidad>();
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);
        lista.add(e5);
        lista.add(e6);
        lista.add(e7);
        lista.add(e8);
        lista.add(e9);
        lista.add(e10);
        lista.add(e11);
        lista.add(e12);
        lista.add(e13);
        lista.add(e14);
        lista.add(e15);
        lista.add(e16);
        lista.add(e17);
        lista.add(e18);
        lista.add(e19);
        lista.add(e20);
        lista.add(e21);
        lista.add(e22);
        lista.add(e23);
        lista.add(e24);
        lista.add(e25);
        
//        lista = ordenarPor1erFiltro(0, lista);
        
        Collections.sort(lista);
        mostrarListaEntReordenada(lista);
    }
    
    private static List ordenarPor1erFiltro(int posicionFiltro, List<Object[]> lista) {
        for ( int i = 0; i < lista.size() - 1; i++ ) {
            for ( int j = i; j < lista.size(); j++ ) {
                Object[] eIni = (Object[])lista.get(i);
                Object[] eSig = (Object[])lista.get(j);
                
                String menor = String.valueOf(eIni[posicionFiltro]);
                String mayor = String.valueOf(eSig[posicionFiltro]);
                System.out.println("menor:" + menor);
                System.out.println("mayor:" + mayor);
                
                if ( menor.compareTo(mayor)  >=  0 ) {
                    menor = mayor;
                    System.out.println("NUEVO menor:" + menor);
                    lista.remove(i);
                    lista.remove(j);
                    lista.add(i, eIni);
                    lista.add(j, eSig);
                }
                
                mostrarListaReordenada(lista);
            }
        }
        return lista;
    }
    
    private static void mostrarListaEntReordenada(List<Entidad> lista) {
        System.out.println("Ini--------------------------------");
        for ( int i = 0; i < lista.size(); i++ ) {
            Entidad e = (Entidad)lista.get(i);
            System.out.println(e.getCampoInf() + "--" + e.getCampoSup());
        }
        System.out.println("Fin--------------------------------");
    }
    
    private static void mostrarListaReordenada(List<Object[]> lista) {
        System.out.println("Ini--------------------------------");
        for ( int i = 0; i < lista.size(); i++ ) {
            Object[] obj = (Object[])lista.get(i);
            System.out.println(obj[0] + "--" + obj[1]);
        }
        System.out.println("Fin--------------------------------");
    }
    
    private static void ejemploRedondeo() {
        String valor = "1460.600";
        String mon = String.valueOf(Redondear2Decimales(Double.parseDouble(valor)));
        System.out.println("mon:" + mon);
        String val = formatearMonetario2(mon, 2);
        System.out.println("val:" + val);
    }
    
    public static double Redondear2Decimales(double valor){
        return Math.round(valor*100.0)/100.0;
    }
    
    private static void separa() {
        String fg[] = new String[3];
        String fechaGiro = "2016-10-03";
        fg = fechaGiro.split("-");
        fechaGiro = fg[2] + "/" + fg[1] + "/" + fg[0];
        System.out.println("fecha giro:" + fechaGiro);
    }
    
    private static String formatearMonetario2(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }
        return valorMonetario;
    }
    
    private static String formatearMonetario(String valorMonetario, int numeroDecimales) {
        
        int indicePunto = valorMonetario.indexOf(".") + 1;
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }
        return valorMonetario;
    }
    
    public static String formatearComaMillar(String monto) {
        int indicePunto = monto.indexOf(".");
        String parteEntera = monto.substring(0, indicePunto);
        String parteDecimal = monto.substring(indicePunto, monto.length());
        int lengthPartInt = parteEntera.length();
        
        if ( lengthPartInt > 3 ) {
            int posComaMillar = lengthPartInt - 3;
            String millarDer = parteEntera.substring(posComaMillar, lengthPartInt);
            String millarIzq = parteEntera.substring(0, posComaMillar);            
            monto = millarIzq + "," + millarDer + parteDecimal;
        }
//        System.out.println("monto:::" + monto);
        return monto;
    }
}
