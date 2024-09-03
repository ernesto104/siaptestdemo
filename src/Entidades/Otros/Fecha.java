package Entidades.Otros;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ledis Rivera Changra
 */
public class Fecha {
    
    private static String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    
    public static String getMeses(int indice) {
        return Meses[indice];
    }
    
    public static String getDia(Date d) {
        return new SimpleDateFormat("dd").format(d);
    }

    public static String getMes(Date d) {
        return Meses[Integer.parseInt(new SimpleDateFormat("MM").format(d)) - 1 ];
    }
    
    public static String getMesFactura(Date d) {
        return new SimpleDateFormat("MM").format(d);
    }

    public static String getAnio(Date d) {
        return new SimpleDateFormat("yyyy").format(d);
    }
    
    public static String getAnioUltimaCifra(Date d) {
        return new SimpleDateFormat("yyyy").format(d).substring(3,4);
    }
    
    public static String getMesLetras(int numero) {
        return Meses[numero - 1];
    }
    
    public static String getMesNumeros(Date d) {
        return new SimpleDateFormat("MM").format(d);
    }
    
    public static String getNroMes(String mes) {
        String numMes = "-1";
        for ( int i = 0; i < Meses.length; i++ ) {
            if ( mes.equals(Meses[i]) ) {
                numMes = "" + i;
                break;
            }
        }
        return numMes;
    }
    
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(ahora);
    }
    
    
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
