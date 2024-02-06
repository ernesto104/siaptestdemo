
package Servicios.CuentasxCobrar;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author keily
 */
public class Sv_Impresion {

    public static void exporta(int op, ArrayList lista, Map<String, Object> parametros, String ruta) {

        String rep = null;
        switch (op) {
            case 0:
                rep = "reportes\\Impresion_Factura.jasper";
                break;
            case 1:
                rep = "reportes\\Inventario_Valorizado.jasper";
                break;                
            case 2:
                rep = "plantillas\\letra.jasper";
                break;
            case 3:
                rep = "reportes\\Inventario_Valorizado_Resumen.jasper";
                break;
            case 4:
                rep="reportes\\Impresion_SK.jasper";
                break;
            case 5:
                rep = "plantillas\\variasLetras.jasper";
                break;
                
            case 6:
                rep = "reportes\\Inventario_Valorizado_General.jasper";
                break;
        }
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(rep));
            JasperPrint documento = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));
//            JasperExportManager.exportReportToPdfFile(documento, ruta);
            JasperViewer.viewReport(documento, false);

        } catch (Exception e) {
            System.out.println("Excepcion:" + e.getMessage());
            e.printStackTrace();
        }

    }
}
