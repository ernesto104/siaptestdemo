package Presentacion.Inventario;

/**
 *
 * @author Lesly Aguilar
 */
import Entidades.Control;
import Entidades.Repuestos;
import Mantenimiento.ControlDAO;
import Mantenimiento.RepuestosDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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

    public void mostrarReporteParametros(String archivo, String orden, String valorizacion, String date, int todo) throws SQLException {
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

            Control control;
            ControlDAO controlDAO = new ControlDAO();
            control = controlDAO.Obtener_Objeto(1);

            JasperReport report = JasperCompileManager.compileReport(archivo);
            HashMap parametros = new HashMap();
            parametros.put("NOMBRE_EMPRESA", control.getNombrempresa());
            parametros.put("TIPO_VALORIZACION", valorizacion);
            parametros.put("ORDEN_PRESENTACION", orden);
            parametros.put("FECHA_EJERCICIO", date);

            String query;

            if (orden.equals("Código de Fabricación")) {
                query = "SELECT repuestos.`IDREPUESTO` AS repuestos_IDREPUESTO, repuestos.`CODREPUESTO` AS repuestos_CODREPUESTO,"
                        + "repuestos.`IDLINEA` AS repuestos_IDLINEA, repuestos.`DESCRIPCION` AS repuestos_DESCRIPCION,"
                        + "repuestos.`UBICALMACEN` AS repuestos_UBICALMACEN, repuestos.`INVENTARIO` AS repuestos_INVENTARIO FROM "
                        + "`repuestos` repuestos ORDER BY repuestos.`IDLINEA`,repuestos.`CODREPUESTO` ASC";
            } else {
                if (orden.equals("Nro. Parte")) {
                    query = "SELECT repuestos.`IDREPUESTO` AS repuestos_IDREPUESTO, repuestos.`CODREPUESTO` AS repuestos_CODREPUESTO,"
                            + "repuestos.`IDLINEA` AS repuestos_IDLINEA, repuestos.`DESCRIPCION` AS repuestos_DESCRIPCION,"
                            + "repuestos.`UBICALMACEN` AS repuestos_UBICALMACEN, repuestos.`INVENTARIO` AS repuestos_INVENTARIO FROM "
                            + "`repuestos` repuestos ORDER BY repuestos.`CODREPUESTO` ASC";
                } else {
                    if (orden.equals("Descripción")) {
                        query = "SELECT repuestos.`IDREPUESTO` AS repuestos_IDREPUESTO, repuestos.`CODREPUESTO` AS repuestos_CODREPUESTO,"
                                + "repuestos.`IDLINEA` AS repuestos_IDLINEA, repuestos.`DESCRIPCION` AS repuestos_DESCRIPCION,"
                                + "repuestos.`UBICALMACEN` AS repuestos_UBICALMACEN, repuestos.`INVENTARIO` AS repuestos_INVENTARIO FROM "
                                + "`repuestos` repuestos ORDER BY repuestos.`DESCRIPCION` ASC";
                    } else {
                        query = "SELECT repuestos.`IDREPUESTO` AS repuestos_IDREPUESTO, repuestos.`CODREPUESTO` AS repuestos_CODREPUESTO,"
                                + "repuestos.`IDLINEA` AS repuestos_IDLINEA, repuestos.`DESCRIPCION` AS repuestos_DESCRIPCION,"
                                + "repuestos.`UBICALMACEN` AS repuestos_UBICALMACEN, repuestos.`INVENTARIO` AS repuestos_INVENTARIO FROM "
                                + "`repuestos` repuestos ORDER BY repuestos.`UBICALMACEN`,repuestos.`CODREPUESTO` ASC";
                    }
                }
            }

            Date fechaSelec = CambiarStringToDate(date);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(query);
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);

            List<Repuestos> listaRepuestos;
            RepuestosDAO rDAO = new RepuestosDAO();
            listaRepuestos = rDAO.getListaOrderCodRep();
            ArrayList lista = new ArrayList();
            double sobranteTotal = 0;
            double faltanteTotal = 0;
            for (int i = 0; i < listaRepuestos.size(); i++) {
                int idLinea = listaRepuestos.get(i).getEquipos().getIdequipo();
                String nroParte;
                nroParte = listaRepuestos.get(i).getCodrepuesto();
                String descripcion = listaRepuestos.get(i).getDescripcion();
                double costo = 0;
                if (valorizacion.equals("Precio Lista")) {
                    costo = listaRepuestos.get(i).getPreciolista();
                } else {
                    if (valorizacion.equals("Costo Promedio")) {
                        if (listaRepuestos.get(i).getCostopromedio() == null) {
                            costo = 0;
                        } else {
                            costo = listaRepuestos.get(i).getCostopromedio();
                        }
                    } else {
                        if (listaRepuestos.get(i).getPcostoultimo() == null) {
                            costo = 0;
                        } else {
                            costo = listaRepuestos.get(i).getPcostoultimo();
                        }
                    }
                }
                int sistema = listaRepuestos.get(i).getStock();
                int inventario;
                if (listaRepuestos.get(i).getInventario() == null) {
                    inventario = 0;
                } else {
                    inventario = listaRepuestos.get(i).getInventario();
                }

                int diferencia = inventario - sistema;

                //  System.out.println("diferencia:"+diferencia);
                double sobrante = 0;
                double faltante = 0;
                if (diferencia > 0) {
                    sobrante = diferencia * costo;
                    if (sobrante < 0) {
                        sobrante = sobrante * -1;
                    }
                    sobrante = Redondear(sobrante);
                    if(todo==1  || todo==0){
                        lista.add(new Diferencias(idLinea, nroParte, descripcion, costo, sistema, inventario, diferencia, sobrante, faltante));
                    }
                } else {
                    faltante = diferencia * costo;
                    if (faltante < 0) {
                        faltante = faltante * -1;
                    }
                    faltante = Redondear(faltante);
                    if(todo==1){
                        lista.add(new Diferencias(idLinea, nroParte, descripcion, costo, sistema, inventario, diferencia, sobrante, faltante));
                    }
                }
                sobranteTotal = sobranteTotal + sobrante;
                faltanteTotal = faltanteTotal + faltante;

                

            }
            double diferenciaTotal = 0;
            if (sobranteTotal > faltanteTotal) {
                diferenciaTotal = sobranteTotal - faltanteTotal;
                diferenciaTotal = Redondear(diferenciaTotal);
                parametros.put("FALTANTE_TOTAL", diferenciaTotal + "");
                parametros.put("SOBRANTE_TOTAL", "");
            } else {
                diferenciaTotal = faltanteTotal - sobranteTotal;
                diferenciaTotal = Redondear(diferenciaTotal);
                parametros.put("SOBRANTE_TOTAL", diferenciaTotal + "");
                parametros.put("FALTANTE_TOTAL", "");
            }

            JasperPrint print;
            print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lista));
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