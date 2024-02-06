/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author CRS
 */
public class Util {
    DecimalFormat format ;
    DecimalFormatSymbols symbol;
    
    public Util(){
        symbol = new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        format= new DecimalFormat("0.00", symbol);
    }
    
    public String DosDecimales(double valor){
        return format.format(valor);
    }
    public double Redondear2Decimales(double valor){
        return Math.round(valor*100.0)/100.0;
    }
    
    public String formatearComaMillar(String monto) {
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
    
    public int getDia(Date date) {
        try {
            return Integer.parseInt(new SimpleDateFormat("dd").format(date));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getMes(Date date) {
        try {
            return Integer.parseInt(new SimpleDateFormat("MM").format(date)) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int getAnio(Date date) {
        try {
            return Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String formatearMonetario(String valorMonetario, int numeroDecimales) {
        
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
}
