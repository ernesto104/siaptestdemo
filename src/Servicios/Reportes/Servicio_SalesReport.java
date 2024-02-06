package Servicios.Reportes;

import Entidades.SalesReport;
import Mantenimiento.Reportes.SalesReportDAO;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ledis
 */
public class Servicio_SalesReport {
    
    SalesReportDAO salesReportDAO;
    
    JTable tblIzquierda;
    DefaultTableModel modeloIzquierda;
    
    JTable tblDerecha;
    DefaultTableModel modeloDerecha;
    
    int anio;
    
    public Servicio_SalesReport(JTable tblIzquierda, JTable tblDerecha, int anio) {
        salesReportDAO = new SalesReportDAO();
        this.tblIzquierda = tblIzquierda;
        this.tblDerecha = tblDerecha;
        this.anio = anio;
    }
    
    public int listarVentas(DefaultTableModel modeloIzquierda, 
                            DefaultTableModel modeloDerecha) {

        this.modeloIzquierda = modeloIzquierda;
        this.modeloDerecha = modeloDerecha;
        
        List ventas = salesReportDAO.Obtener_Lista_Ventas_Con_SP(anio);

        for (int i = 0; i < ventas.size(); i++) {
            SalesReport sales = (SalesReport) ventas.get(i);
            
            String a = getMes(sales.getMon());
            String b = String.valueOf(sales.getTotSolAnt());
            String c = String.valueOf(sales.getTotDolAnt());
            
            Object[] reporteAnt = {a, b, c};
            modeloIzquierda.addRow(reporteAnt);
        }
        
        for (int i = 0; i < ventas.size(); i++) {
            SalesReport sales = (SalesReport) ventas.get(i);
            
            String d = String.valueOf(sales.getTotSolSel());
            String e = String.valueOf(sales.getTotDolSel());
            
            Object[] reporteSel = {d, e};
            modeloDerecha.addRow(reporteSel);
        }
        return ventas.size();
    }
    
    private String getMes(int mes) {
        switch(mes){
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return null;
        }
    }
}
