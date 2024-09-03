package Entidades.Otros;

import static Entidades.Otros.Constante.DOLAR_STANDARD;
import static Entidades.Otros.Constante.SOL;

/**
 *
 * @author Ledis Rivera Changra
 */
public class Monetario {
    
    // Cuando el valor tiene varias cifras decimales (aún sin truncar a 2 decimales), este método redondea a 2 decimales y luego da formato completando a 2 cifras decimales con 0
    public static String formatearMonetario(double valor, int numeroDecimales) {
//        System.out.println("valor(1°):" + valor);
        
        // 1° Se redondea a máximo 2 decimales
        valor = Math.rint(valor * 100) / 100;
//        System.out.println("valor(2°):" + valor);
        String valorMonetario = String.valueOf(valor);
        
        // 2° Luego se ajusta formato a 2 ceros decimales
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
//        System.out.println("valorMonetario(3°):" + valorMonetario);
        return valorMonetario;
    }
    
    // Previo "valorMonetario" redondeado a 2 decimales ingresa como IN
    public static String formatearMonetario(String valorMonetario, int numeroDecimales) {
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
    
    public static String formatearTotal(double total) {
        return formatearCeros(String.valueOf(redondear2Decimales(total)), 2);
    }
    
    public static String asignarMoneda(int tipoMoneda) { // tipoMoneda = 0(Dolar), 1(Soles)
        String moneda = DOLAR_STANDARD;
        if ( tipoMoneda == 1 ) {
            moneda = SOL;
        }
        return moneda;
    }
    
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    public static double redondear2Decimales(double valor){
        return Math.round(valor*100.0)/100.0;
    }
}
