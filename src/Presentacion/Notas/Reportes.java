
package Presentacion.Notas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lesly Aguilar
 */
public class Reportes {
    
    public void mostrarReporte(String archivo) {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
//            conexion = DriverManager.getConnection("jdbc:mysql://cp06/siar_consorcio", "rootSIAR", "siar");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/siar_consorcio", "consorcio", "consorcio");
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            JasperReport report = JasperCompileManager.compileReport(archivo);
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), conexion);
            JasperViewer.viewReport(print, false, Locale.getDefault());
        } catch (JRException jRException) {
            System.out.println(jRException.getMessage());
        }
    }
    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    private Date CambiarStringToDate(String strFecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    
}
