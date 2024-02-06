package Presentacion.Notas;

import Entidades.Cabeces;
import Entidades.Control;
import Entidades.Detallenota;
import Mantenimiento.ControlDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/**
 *
 * @author Lesly Aguilar
 */
public class Impresion {

    private int MaxLineas;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre",
        "Diciembre"};
//    private double subTotal;
    private String subTotal;
    private double igv;
    private double total;
    private String nroDocumento;
    private Date fecha;
    ControlDAO controlDAO;
    private String fechaEmision;

    public Impresion(int MaxLineas, String st, double igv, double t, String nro, Date f, String fechaEmision) {
        this.subTotal = st;
        this.igv = igv;
        this.total = t;
        this.nroDocumento = nro;
        this.MaxLineas = MaxLineas;
        this.fecha = f;
        this.fechaEmision = fechaEmision;
    }

    public void imprime(int op, Cabeces c, ArrayList<Detallenota> det) {
        ArrayList lista = llenaReporte(op, det);
        HashMap parametros = llenaParametros(c, this.fecha);
        imprime(lista, parametros);
    }

    private ArrayList llenaReporte(int op, ArrayList<Detallenota> det) {
        ArrayList lista = new ArrayList();
        int i =0;
        switch (op) {
            //Nota de Debito
            case 0:
                System.out.println("Nota de Debito");
                i =0;
                 
                for ( Detallenota d : det ) {
                    String descripcion = d.getDescripcion();
                    String importe = String.valueOf(d.getValor());
                    System.out.println("repuesto:" + d.getRepuestos());
                    
                    String nroParte = "";
                    if ( d.getRepuestos() != null ) {
                        nroParte = d.getRepuestos().getCodrepuesto();
                    }
                    System.out.println("nroParte(ND):" + nroParte);
                    lista.add(new ImpresionNota("", descripcion, "", importe, nroParte));
                    i++;
                }
                for (int j = i; j < MaxLineas; j++) {
                    lista.add(new ImpresionNota("", "", "", "", ""));
                }
                break;
             
             //Nota de Crédito
             case 1:
                i = 0;
                for ( Detallenota d : det ) {
                    String descripcion = d.getDescripcion();
                    String importe = String.valueOf(d.getValor());
                    String cantidad = String.valueOf(d.getCantidad());
//                    String precUnitario = String.valueOf(d.getRepuestos().getPreciolista());
                    String precUnitario = formatearMonetario(String.valueOf(Double.parseDouble(importe)/Integer.parseInt(cantidad)), 2);
                    
                    String nroParte = "";
                    if ( d.getRepuestos() != null ) {
                        nroParte = d.getRepuestos().getCodrepuesto();
                    }
                    System.out.println("nroParte(NC):" + nroParte);
                    lista.add(new ImpresionNota(cantidad, descripcion, precUnitario, importe, nroParte));
                    i++;
                }
                for (int j = i; j < MaxLineas; j++) {
                    lista.add(new ImpresionNota("", "", "", "", ""));
                }
                break;  
            default:
                lista = null;
        }
        return lista;
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
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

    private HashMap llenaParametros(Cabeces c, Date fecha) {
        controlDAO = new ControlDAO();
        HashMap parametros = new HashMap();
        parametros.clear();
        parametros.put("CLIENTE_NOMBRE", c.getClientes().getNombre());
        parametros.put("CLIENTE_DIRECCION", c.getClientes().getDireccion());
        if(c.getMoneda().equals("2")){
            parametros.put("MONEDA", "US$");
            
        } else {
            parametros.put("MONEDA", "S/.");
        }
        
        parametros.put("SUB_TOTAL", getSubTotal());
        System.out.println("igv:" + igv);
        parametros.put("IGV", getIgv());
        parametros.put("TOTAL", getTotal());
        parametros.put("RUC", c.getClientes().getRuc());
        
        String numSerieNC = c.getId().getNrorserie();
        String nroDocNC = c.getId().getNrodocumento();
        String numDocNC = formatearFactura(numSerieNC, nroDocNC);
        parametros.put("NRO_DOCUMENTO", numDocNC);
        parametros.put("FECHA_EMISION", fechaEmision);
        
        String fechaNotaCredito = getDia(fecha) + "/" + 
                              getMes(fecha) + "/" +
                              getAnio(fecha);
        parametros.put("FECHA_NC", fechaNotaCredito);
        double subTotalDouble = Double.parseDouble(getSubTotal());
        Control control = new ControlDAO().Obtener_Lista_Objetos().get(0);
        int impuesto = control.getImpuestoigv();//(int) (getIgv()*100/subTotalDouble);
        parametros.put("IMPUESTO", impuesto + "%");
        return parametros;
    }
    
    private static String formatearFactura(String numSerie, String numCorrelativo) {
        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, 8);
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }

    private void imprime(ArrayList listado, HashMap parametros) {
        PrintService impresora = buscaImpresora();
        
        if ( impresora != null ) {
            Servicios.Importaciones.Servicio_Control sc = new Servicios.Importaciones.Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            
            String url1 = rutaReportes + "\\reimpresiones\\RepNotCredito.jrxml";
            String url2 = rutaReportes + "\\reimpresiones\\RepNotCredito.jasper";
            
//            String plantilla = null;
//            plantilla = "src/Presentacion/Notas/RepNotCredito.jrxml";
//            plantilla = "plantillas/RepNotCredito.jrxml";
            try {
                convertPedidoToPDF(url1, url2, parametros, listado, impresora);
                
            } catch ( Exception ex ) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void convertPedidoToPDF(String url1, String url2,
                                   HashMap parametros,
                                   ArrayList listado,
                                   PrintService impresora) throws Exception{
        
//        JasperCompileManager.compileReportToFile(url1,url2);
//        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
//        
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
//                                                               parametros,
//                                                               new JRBeanCollectionDataSource(listado));
//        
//        JasperViewer.viewReport(jasperPrint, false, Locale.getDefault());
        //////////////////////////////////////////
        
        JasperReport reporte = JasperCompileManager.compileReport(url1);
        JasperPrint documento = JasperFillManager.fillReport(reporte,
                                                             parametros,
                                                             new JRBeanCollectionDataSource(listado));
//                JasperExportManager.exportReportToPdf(documento);
//                JasperViewer.viewReport(documento, false);

        JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
        jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, documento);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora);
        jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, 
                                            Boolean.FALSE);
        jrprintServiceExporter.exportReport();
    }
    
    private PrintService buscaImpresora() {
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService impresora = null;
        if (printService.length > 0) {
            impresora = (PrintService) JOptionPane.showInputDialog(null, "Elija impresora:",
                    "Imprimir Nota", JOptionPane.QUESTION_MESSAGE, null, printService, printService);
        }
        return impresora;
    }

//    public double getSubTotal() {
//        return subTotal;
//    }
    
    public String getSubTotal() {
        return subTotal;
    }

    public double getIgv() {
        return igv;
    }

    public double getTotal() {
        return total;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    private String getDia(Date d) {
        return new SimpleDateFormat("dd").format(d);
    }

    private String getMes(Date d) {
        return new SimpleDateFormat("MM").format(d);
    }

    private String getAnio(Date d) {
        return new SimpleDateFormat("yyyy").format(d);
    }

    public Date getFecha() {
        return fecha;
    }
}
