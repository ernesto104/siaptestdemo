/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.facturacion;

import Entidades.Cabeces;
import Entidades.Cabecproformas;
import Entidades.Detallees;
import Entidades.Detalleproformas;
import Entidades.Sistema;
import Mantenimiento.ControlDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Servicios.Constantes;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Util;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maverick225
 */
public class Servicio_Impresion implements Constantes {

    double total;
    String enviado, pedido, guia, ptoPartida;
    private int MaxLineas;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};
    private String[] Condicion = {"CONTADO", "CREDITO", "CANJE"};

    public Servicio_Impresion(int MaxLineas) {
        this.MaxLineas = MaxLineas;
    }

    private void imprime(String marca, String placa, String modelo, String ordenTransporte,
                         int op, Cabeces c, ArrayList<Detallees> det, Date fechaEmision, String siniestro) {
        
        ArrayList lista = llenaReporte(op, det, null, null, null, null); // direccionTransportista, telefono1Transportista, telefono2Transportista
        Map<String, Object> parametros = llenaParametros(op, c, null, fechaEmision, siniestro, 
                                                         marca, placa, modelo, ordenTransporte); // Se agregaron: placa y modelo
        imprime(op, lista, parametros);
    }
    
    //Para guia de remision
    private void imprime(int op, Cabeces c, ArrayList<Detallees> det, Date fechaEmision, String direcTransp, String telf1Transp, String telf2Transp) {
        ArrayList lista = llenaReporte(op, det, null, direcTransp, telf1Transp, telf2Transp);
        Map<String, Object> parametros = llenaParametros(op, c, null, fechaEmision, null, 
                null, null, null, null); // Se agregaron: marca, placa, modelo y ordenTransporte
        imprime(op, lista, parametros);
    }

    private void imprime(Cabecproformas c, ArrayList<Detalleproformas> detp, Date fechaEmision) {
        ArrayList lista = llenaReporte(3, null, detp, null, null, null); // direccionTransportista, telefono1Transportista, telefono2Transportista
        Map<String, Object> parametros = llenaParametros(3, null, c, fechaEmision, null,
                null, null, null, null); // Se agregaron: marca, placa, modelo y ordenTransporte
        imprime(3, lista, parametros);
    }

    public void imprimeFactura(Cabeces c, ArrayList<Detallees> det, String env, String ped, 
            String nroguia, Date fechaEmision, String siniestro,
            String marca, String placa, String modelo, String ordenTransporte) {
        enviado = env;
        pedido = ped;
        guia = nroguia;
        imprime(marca, placa, modelo, ordenTransporte, 0, c, det, fechaEmision, siniestro);
    }

    public void imprimeBoleta(Cabeces c, ArrayList<Detallees> det, Date fechaEmision) {
        imprime(1, c, det, fechaEmision, null, null, null); // Se agregaron al final: placa y modelo
    }

    public void imprimeGuiaRemisiono(Cabeces c, ArrayList<Detallees> det, String PtoPartida, Date fechaEmision,
                                     String direcTransp, String telf1Transp, String telf2Transp,
                                     String numPedido) { // Impresión por Jasper (1° Versión M&D)
        ptoPartida = PtoPartida;
        pedido = numPedido;
        System.out.println("numPedido(imprimeGuiaRemisiono):" + pedido);
        imprime(2, c, det, fechaEmision, direcTransp, telf1Transp, telf2Transp);
    }

    public void imprimeProforma(Cabecproformas cp, ArrayList<Detalleproformas> detp, Date fechaEmision) {
        imprime(cp, detp, fechaEmision);
    }

    private ArrayList llenaReporte(int op, ArrayList<Detallees> det, ArrayList<Detalleproformas> detP,
                                   String direccionTransp, String telf1Transp, String telf2Transp) {
        ArrayList lista = new ArrayList();
        switch (op) {
            case 0: // Factura
                int i = 0;
                for (Detallees d : det) {
                    String cantidad = String.valueOf(d.getCantentregada());
                    
                    String modelo = d.getRepuestos().getDescrmodelo();
                    if ( modelo == null ) {
                        modelo = "";
                    }
                    String descripcion = d.getRepuestos().getDescripcion() + " " + modelo;
                    String unitario = formatearMonetario(String.valueOf(d.getRepuestos().getPreciolista()), 2);

                    int cantentregada = d.getCantentregada();
                    double d1 = d.getDescuento1() / 100.0;
                    double d2 = d.getDescuento2() / 100.0;
                    double d3 = d.getDescuento3() / 100.0;
                    double d4 = d.getDescuento4() / 100.0;

                    double Precio = d.getRepuestos().getPreciolista();
                    Precio = ((((Precio * (1 - d1)) * (1 - d2))) * (1 - d3)) * (1 - d4);

                    double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;

//                    String valorVenta = formatearMonetario(String.valueOf(Total), 2);
                    
                    if ( d1 != 0 || d2 != 0 || d3 != 0 || d4 != 0 ) {
                        String totalConDsctos = String.valueOf(new Util().Redondear2Decimales(Total/cantentregada));
                        unitario = formatearMonetario(totalConDsctos, 2);
                    }
                    String idRepuesto = d.getRepuestos().getCodrepuesto();
                    String codigoSecundario = d.getRepuestos().getCodigoseg();
                    String descuentos = "";
                    if (d.getDescuento1() > 0) {
                        descuentos += (int) (double) d.getDescuento1() + "%";
                    }
                    if (d.getDescuento2() > 0) {
                        descuentos += (int) (double) d.getDescuento2() + "%";
                    }
                    if (d.getDescuento3() > 0) {
                        descuentos += (int) (double) d.getDescuento3() + "%";
                    }
                    if (d.getDescuento4() > 0) {
                        descuentos += (int) (double) d.getDescuento4() + "%";
                    }
                    if ( codigoSecundario == null ) {
                        codigoSecundario = "";
                    }
                    int igv = new ControlDAO().Obtener_Objeto(1).getImpuestoigv();
                    double factorIGV = ( 1.0 + Double.parseDouble(String.valueOf(igv))/100 );
                    
                    Util util = new Util();
                    String vuString = String.valueOf(util.Redondear2Decimales(Double.parseDouble(unitario)/factorIGV));
                    String vu = util.formatearMonetario(vuString, 2);
                    
                    String vutString = String.valueOf(util.Redondear2Decimales( Double.parseDouble(cantidad) * Double.parseDouble(vu) ));
                    String vut = util.formatearMonetario(vutString, 2);
                    
                    // Le faltaban "anotaciones" para "factura2.jrxml"
                    String anotaciones = "";
                    if (d.getRepuestos().getDesclista() != null) {
                        anotaciones = d.getRepuestos().getDesclista();
                    }
                    
                    if (cantentregada > 0) {
                        // unitario (3er parametro), valor venta total (4to parametro)
                        lista.add(new FilaFactura(cantidad, descripcion, vu, vut, idRepuesto, codigoSecundario, descuentos, anotaciones));
                        i++;
                    }
                }
                break;
            case 1: // Boleta
                int a = 0;
                for (Detallees d : det) {
                    String cantidad = String.valueOf(d.getCantentregada());
                    String codigo = String.valueOf(d.getRepuestos().getCodrepuesto());
                    String descripcion = String.valueOf(d.getRepuestos().getDescripcion());

                    int cantentregada = d.getCantentregada();
                    double d1 = d.getDescuento1() / 100.0;
                    double d2 = d.getDescuento2() / 100.0;
                    double d3 = d.getDescuento3() / 100.0;
                    double d4 = d.getDescuento4() / 100.0;

                    double Precio = d.getRepuestos().getPreciolista();
                    Precio = ((((Precio * (1 - d1)) * (1 - d2))) * (1 - d3)) * (1 - d4);

                    double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;
                    
                    String importe = formatearMonetario(String.valueOf(Total), 2);
                    if (cantentregada > 0) {
                        lista.add(new FilaBoleta(cantidad, codigo,descripcion, importe));
                        a++;
                    }
                }
                for (int j = a; j < MaxLineas; j++) {
                    lista.add(new FilaBoleta("", "", "", ""));
                }
                break;
            case 2: // Guia de Remision
                int ab = 0;
                int cont = 1;
                for (Detallees d : det) {
                    String item = d.getRepuestos().getCodrepuesto();
                    String descripcionModelo = d.getRepuestos().getDescrmodelo();
                    if ( descripcionModelo == null ) {
                        descripcionModelo = "";
                    }
                    String descripcion = d.getRepuestos().getDescripcion() + " " + descripcionModelo;
                    String cantidad = String.valueOf(d.getCantentregada());
                    String unidad = d.getRepuestos().getUnidadmedida();
                    String pesoTotal = "";
                    if (d.getCantentregada() > 0) {
                        lista.add(new FilaGuiaRemision(item, descripcion, cantidad, unidad, pesoTotal));
                        ab++;
                    }
                }
                // Agregando Direccion del Transportista en la siguiente linea del detalle de repuestos de la Guia de Remision.
                lista.add(new FilaGuiaRemision("", "", "", "", ""));
                lista.add(new FilaGuiaRemision("Direcc. Transp: ", direccionTransp, "", "", ""));
                
                // Agregando Telefono1 y Telefono2 del Transportista en la siguiente linea del detalle de repuesots de la Guia de Remision.
//                System.out.println("telefono1:" + telf1Transp);
//                System.out.println("telefono2:" + telf2Transp);
                String telefonos = "";
                
                if ( telf1Transp != null && telf2Transp != null ) {
                    if ( "".equals(telf2Transp) ) {
                        telefonos += telf1Transp;
                    } else {
                        telefonos += telf1Transp + ", " + telf2Transp;
                    }
                    
                } else if ( telf1Transp == null ) {
                    telefonos += telf2Transp;
                    
                } else if ( telf2Transp == null ) {
                    telefonos += telf1Transp;
                }
                lista.add(new FilaGuiaRemision("Telefo. Transp.:", telefonos, "", "", ""));
                
                for (int j = ab; j < MaxLineas - 2; j++) {
                    lista.add(new FilaGuiaRemision("", "", "", "", ""));
                }
                break;
            case 3: // Proforma
                int con = 1;
                int ac = 0;
                for (Detalleproformas d : detP) {
                    String descripcion = d.getRepuestos().getDescripcion();
                    String cantidad = String.valueOf(d.getCantidad());
                    String idRepuesto = d.getRepuestos().getCodrepuesto();

                    int cantentregada = d.getCantidad();
                    double d1 = d.getDescuento1() / 100.0;
                    double d2 = d.getDescuento2() / 100.0;
                    double d3 = d.getDescuento3() / 100.0;
                    double d4 = d.getDescuento4() / 100.0;

                    double Precio = d.getRepuestos().getPreciolista();
                    Precio = ((((Precio * (1 - d1)) * (1 - d2))) * (1 - d3)) * (1 - d4);

                    double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;
                    total = Total;
                    String importe = formatearMonetario(String.valueOf(Total), 2);
                    lista.add(new FilaProforma(String.valueOf(con++), idRepuesto, descripcion, cantidad, importe));
                    ac++;
                }
                for (int j = ac; j < MaxLineas; j++) {
                    lista.add(new FilaProforma("", "", "", "", ""));
                }
                break;
            default:
                lista = null;
        }
        return lista;
    }
    
    private static String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        int longitud = valorMonetario.length();
        int decimales = longitud - indicePunto;
        int diferenciaCeros = numeroDecimales - decimales;
        for ( int i = 0; i < diferenciaCeros; i++ ) {
            valorMonetario = valorMonetario + "0";
        }
        return valorMonetario;
    }

    private Map llenaParametros(int op, Cabeces c, Cabecproformas cp, Date fechaEmision, String siniestro,
                                String marca, String placa, String modelo, String ordenTransporte) {
        
        Map<String, Object> parametros = new HashMap();
        parametros.clear();
        
        switch ( op ) {
            case 0: // FACTURAS
                Calendar cal = new GregorianCalendar();
                String glosa = c.getMoneda().equals(SOLES) ? GLOSA_SOLES : GLOSA_DOLARES;
                cal.setTime(cal.getTime());
                parametros.put("totalIgv", glosa + " " + String.valueOf(c.getIgv()));
                parametros.put("nombreEmpresa", c.getClientes().getNombre());
                parametros.put("rucEmpresa", c.getClientes().getRuc());
                parametros.put("direccionEmpresa", c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion());
                parametros.put("fecha", df.format(c.getTipocambio().getFecha()));
                parametros.put("enviado", enviado); // New
                parametros.put("pedido", pedido); // New
                parametros.put("orden", c.getId().getNrodocumento()); // New
                
                // Inicio - Para Impresión por jasper (1° Versión de M&D)
                parametros.put("condiciones", Condicion[Integer.parseInt(c.getTipoperacion()) - 1]);
                parametros.put("igv", String.valueOf(new ControlDAO().Obtener_Objeto(1).getImpuestoigv()));
                String vendedor = "";
                if ( c.getVendedores() != null ) {
                    vendedor = "Vend:" + c.getVendedores().getNombre();
                }
                parametros.put("vendedor", vendedor);
                // Fin - Para Impresión por jasper (1° Versión de M&D)
                
                // New Parameters
                if ( cp == null ) {
                    System.out.println("cabecProforma null");
                    parametros.put("marca", marca);
                    parametros.put("placa", "PLACA: " + placa);
                    parametros.put("modelo", modelo);
                    
                } else {
                    System.out.println("cabecProforma NO es null");
                    parametros.put("marca", cp.getMarca());
                    parametros.put("placa", "PLACA: " + cp.getPlaca());
                    parametros.put("modelo", cp.getModelo());
                }
                
                if ( !"null".equals(ordenTransporte) ) {
                    parametros.put("ordenTransporte", "O/T: N° " + ordenTransporte);
                }
                
                if ( !"null".equals(siniestro) ) {
                    parametros.put("siniestro", "SINIESTRO: " + siniestro);
                }
                
                String fechaCadena, dia, mesLetras, año2Digitos, año4Digitos;
                if ( fechaEmision == null ) {
                    dia =getDia(c.getTipocambio().getFecha());
                    String mes = getMes(c.getTipocambio().getFecha());
                    String año = getAnio(c.getTipocambio().getFecha());
                    fechaCadena = dia + "/" + mes + "/" + año;
                    
                    mesLetras = Meses[Integer.parseInt(getMes(c.getTipocambio().getFecha()))-1];
                    año2Digitos = año.substring(2);
                    
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fechaCadena = formateador.format(fechaEmision);
                    
                    // VERIFICARLO
                    dia = getDia(fechaEmision);
                    mesLetras = Meses[Integer.parseInt(getMes(fechaEmision))-1];
                    año2Digitos = getAnio(fechaEmision).substring(2);
                }
//                System.out.println("fechaCadena:" + fechaCadena);
                
                parametros.put("fechaEmision", fechaCadena);
                
                // Datos de fecha de emision en pie de pagina
                parametros.put("dia", dia);
                parametros.put("mes", mesLetras);
                parametros.put("año", año2Digitos);
//                System.out.println("fecha inferior: " + dia + " de " + mesLetras + " del 20" + año2Digitos);

                Sistema sistema = new SistemaDAO().obtener_por_nombre("GUIA DE REMISION");
                String numSerieGuia = String.valueOf(sistema.getSerie());
                String numeroGuiaRemision = "";
                if ( !"null".equals(guia) ) {
                    numeroGuiaRemision = formatearGuiaRemision(numSerieGuia, guia);
                }
//                System.out.println("numeroGuiaRemision:" + numeroGuiaRemision);
                parametros.put("numeroGuia", numeroGuiaRemision);
                
                parametros.put("total", glosa + " " + formatearMonetario(String.valueOf(c.getTotal()), 2));
                parametros.put("cantidadEnLetras", "SON: " + Billete.BilleteX(c.getTotal(), c.getMoneda().equals("2") ? " DOLARES AMERICANOS" : " NUEVOS SOLES"));
                parametros.put("valorventa", glosa + " " + String.valueOf(c.getValorventa()));
                break;
            case 1: // BOLETA
                String glos = c.getMoneda().equals(SOLES) ? GLOSA_SOLES : GLOSA_DOLARES;
                parametros.put("empresaNombre", c.getClientes().getNombre());
                parametros.put("empresaDireccion", c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion());
                parametros.put("empresaIdentidad", c.getClientes().getRuc());
                
                parametros.put("dia", getDia(c.getTipocambio().getFecha()));
                parametros.put("mes", getMes(c.getTipocambio().getFecha()));
                parametros.put("año", getAnio(c.getTipocambio().getFecha()));
                parametros.put("cdia", getDia(c.getTipocambio().getFecha()));
                parametros.put("cmes", Meses[Integer.parseInt(getMes(c.getTipocambio().getFecha()))]);
                parametros.put("caño", getAnio(c.getTipocambio().getFecha()).substring(2, 4));
                parametros.put("total", glos + " " + c.getTotal());
                break;
            case 2: // GUIA DE REMISION
                parametros.put("puntoPartida", ptoPartida);
                parametros.put("pedido", pedido); // New
                fechaCadena = "";
                
                if ( fechaEmision == null ) {
                    dia = getDia(c.getTipocambio().getFecha());
                    String mes = getMes(c.getTipocambio().getFecha());
                    año4Digitos = getAnio(c.getTipocambio().getFecha());
                    fechaCadena = dia + "/" + mes + "/" + año4Digitos;
                    mesLetras = Meses[Integer.parseInt(getMes(c.getTipocambio().getFecha()))-1];
                    
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fechaCadena = formateador.format(fechaEmision);
                    
                    // VERIFICARLO
                    dia = getDia(fechaEmision);
                    mesLetras = Meses[Integer.parseInt(getMes(fechaEmision))-1];
                    año4Digitos = getAnio(fechaEmision);
                }
//                parametros.put("fechaInicio", fechaCadena);
                parametros.put("costoMinimo", "");
                parametros.put("puntoLlegada", c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion());
                parametros.put("razonSocial", c.getClientes().getNombre());
                parametros.put("ruc", c.getClientes().getRuc());
                parametros.put("marca", "");
                parametros.put("constancia", "");
                parametros.put("licencia", "");
                parametros.put("empresaRazon", c.getTransportistas().getNombre());
                parametros.put("empresaRuc", c.getTransportistas().getRuc());
                
                sistema = new SistemaDAO().obtener_por_nombre("FACTURA");
                String numSerieFactura = String.valueOf(sistema.getSerie());
                String comprobante = formatearFactura(numSerieFactura, c.getId().getNrodocumento());
                parametros.put("comprobante", comprobante);
                
                // New Parameters
                if ( c == null ) {
                    parametros.put("marca", marca);
                    parametros.put("placa", "PLACA: " + placa);
                    parametros.put("ordenTransporte", "O/T: N° " + ordenTransporte);
                    parametros.put("siniestro", "SINIESTRO: " + siniestro);
                } else {
                    parametros.put("marca", c.getMarca());
                    parametros.put("placa", "PLACA: " + c.getPlaca());
                    parametros.put("ordenTransporte", "O/T: N° " + c.getOrdenTransportista());
                    parametros.put("siniestro", "SINIESTRO: " + c.getSiniestro());
                }
                
                // Datos de fecha de emision en pie de pagina
                parametros.put("dia", dia);
                parametros.put("mes", mesLetras);
                parametros.put("anio", año4Digitos);
                
                break;
            case 3: // PROFORMA
                parametros.put("numeroProforma", cp.getCodigocabproforma());
                parametros.put("cliente", cp.getClientes().getNombre());
                parametros.put("fecha", df.format(cp.getFecha()));
                parametros.put("total", GLOSA_DOLARES + "  " + total);
                break;
        }
        return parametros;
    }
    
    private static String formatearGuiaRemision(String numSerie, String numCorrelativo) {
        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, 8);
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

    private static PrintService buscaImpresora() {
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService impresora = null;
        if (printService.length > 0) {
            impresora = (PrintService) JOptionPane.showInputDialog(null, "Elija impresora:",
                    "Imprimir Reporte", JOptionPane.QUESTION_MESSAGE, null, printService, printService);
        }
        return impresora;
//        return printService.length>0?printService[0]:null;
    }

    public static void imprime(int op, ArrayList lista, Map<String, Object> parametros) {
        PrintService impresora = buscaImpresora();
        if (impresora != null) {
            String plantilla = null;
            switch (op) {
                case 0:
                    plantilla = "plantillas\\factura2.jasper";
                    break;
                case 1:
                    plantilla = "plantillas\\boleta.jasper";
                    break;
                case 2:
                    plantilla = "plantillas\\guiaremision.jasper";
                    break;
                case 3:
                    plantilla = "plantillas\\proforma.jasper";
                    break;
                case 9:
                    plantilla = "plantillas\\comisiones.jasper";
                break;                    
            }
            try {

                /**
                 * **************************************************************
                 */
                JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(plantilla));
                JasperPrint documento = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));

                //JasperExportManager.exportReportToPdf(documento);
             //   JasperViewer.viewReport(documento, false);

                JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, documento);
                jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora);
                jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                jrprintServiceExporter.exportReport();
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void exporta(int op, ArrayList lista, Map<String, Object> parametros, String ruta) {

//        System.out.println(" OP : " + op);
//        System.out.println("ArrayList : " + ArrayList);
//        System.out.println(" Ruta : " + ruta);
        
        String plantilla = null;
        switch (op) {
            case 0:
                plantilla = "\\consultasReportes\\ConsultaClientes.jasper";
                break;
            case 1:
                plantilla = "\\consultasReportes\\consultarRepuestos.jasper";
                break;
            case 2:
                plantilla = "\\listaPrecios\\listaPrecios.jasper";
                break;
            case 3:
                plantilla = "\\consultasReportes\\stockMinimos.jasper";
                break;
            case 4:
//                plantilla = "plantillas\\ingresoSalidasPorAjustes.jasper"; // Con columna de cód. secundario vacía
                plantilla = "plantillas\\ingresoSalidasPorAjustesSinCS.jasper"; // Sin columna de cód. sec., más ancho para descripcion
                break;
            case 5:
                plantilla = "\\plantillas\\resumenCliente_General.jasper";
                break;
            case 6:
                plantilla = "\\plantillas\\resumenCliente_Detalle_facbol.jasper";
                break;
            case 7:
                plantilla = "\\plantillas\\planillas.jasper";
                break;
            case 8:
                plantilla = "\\plantillas\\resumenCliente_Detalle_letras.jasper";
                break;
            case 9:
                plantilla ="\\plantillas\\comisiones.jasper";
                break;
            case 15:
                plantilla = "\\plantillas\\sk.jasper";
                break;
            case 16:
                plantilla = "\\plantillas\\HistorialMov.jasper";
                break;    
            case 17:
                plantilla = "plantillas\\comisionesImpresion.jasper";
                break;
            case 18:
                plantilla = "\\plantillas\\HistorialMovSinFecha.jasper";
                break;
            // Impresiones de Importaciones
            case 19:
                plantilla = "\\OrdenCompra.jasper";
                break;
            // Impresiones de anulaciones
            case 20:
                plantilla = "plantillas\\ingresoAnulacion.jasper";
                break;
            case 21:
                plantilla = "\\plantillas\\Impresion_ProformasFact.jasper";
                break;
            // Impresiones de Resoluciones
            case 22:
                plantilla = "plantillas\\solicitudResolucion.jasper";
                break;
            case 23:
                plantilla = "plantillas\\estadoResoluciones.jasper";
                break;
            case 24:
                plantilla = "plantillas\\detalleResolucion.jasper";
                break;
            case 25:
                plantilla = "plantillas\\detalleResolucion2.jasper";
                break;
            case 26:
                plantilla = "plantillas\\consultaResoluciones.jasper";
                break;
            case 27:
                plantilla = "plantillas\\detalleResolucion3.jasper";
                break;
            case 28:
                plantilla = "\\plantillas\\ingresoCompraLocal.jasper";
                break;
        }
        try {
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            plantilla = rutaReportes + plantilla;
            
            System.out.println("Plantilla : " + plantilla);
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(plantilla));
//            System.out.println("lista:" + lista);
//            System.out.println("N° elementos en lista:" + lista.size());
//            System.out.println("parametros:" + parametros);
            JasperPrint documento = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(lista));

            //JasperExportManager.exportReportToPdfFile(documento, ruta);
//            JasperViewer.viewReport(documento, false);
            JasperViewer.viewReport(documento, false, Locale.getDefault());

        } catch (Exception e) {
            System.out.println("Excepcion(exporta-plantilla):" + e.getMessage());
            e.printStackTrace();
        }

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
}