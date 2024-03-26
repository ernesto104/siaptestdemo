package Servicios;

import Entidades.Cabeces;
import Entidades.Cabecsalvar;
import Entidades.Control;
import Entidades.Detallees;
import Entidades.Detallenota;
import static Entidades.Otros.Constante.ING_IMPORTACION;
import static Entidades.Otros.Constante.ING_POR_ANULACION;
import static Entidades.Otros.Constante.ING_POR_COMPRA_LOCAL;
import static Entidades.Otros.Constante.ING_REGULARIZACION;
import static Entidades.Otros.Constante.NUM_CARACT_DIR_TRUNCA;
import static Entidades.Otros.Constante.PRINT_LX_300;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;
import static Entidades.Otros.Constante.USUARIO_LUCY;
import Entidades.Otros.DatosDocumento;
import Entidades.Otros.DatosGR;
import Entidades.Otros.DatosNC;
import Entidades.Otros.DatosND;
import Entidades.Otros.DatosProforma;
import static Entidades.Otros.Fecha.getAnio;
import static Entidades.Otros.Fecha.getDia;
import static Entidades.Otros.Fecha.getMes;
import static Entidades.Otros.Fecha.getMesFactura;
import static Entidades.Otros.Fecha.getMeses;
import Entidades.Sistema;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Mantenimiento.ExcelDAO;
import Presentacion.Anulacion.DetalleAnulacion;
import Presentacion.IngresoSalidas.DetalleIngSal;
import static Servicios.Constantes.GLOSA_DOLARES;
import static Servicios.Constantes.GLOSA_SOLES;
import static Servicios.Constantes.SOLES;
import Servicios.facturacion.Billete;
import Servicios.facturacion.tabla;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTable;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Excel {
    
    ExcelDAO excelDAO;
    
    
    
    public Servicio_Excel(JTable table, JFrame IU){
        excelDAO = new ExcelDAO(table,IU);
    }
    
    public Servicio_Excel(JTable tableLeft, JTable tableRight, JFrame IU){
        excelDAO = new ExcelDAO(tableLeft,tableRight, IU);
    }
    
    public void Exportar_Excel(int opcion){
        excelDAO.exportar(opcion);
    }
    
    public void Exportar_Excel_2_Tables(int anio){
        excelDAO.exportar2Tables(anio);
    }
    
    public void guardar(File file) throws FileNotFoundException, IOException, WriteException{
        excelDAO.guardar(file);
    }
    
    public void exportarExcel(String nombreHoja, List nombresCabecera, int opcion) {
        System.out.println("Estoy en exportar Exce...");
        System.out.println("nombreHoja: " + nombreHoja);
        System.out.println("nombreCabecera: " + nombresCabecera);
        System.out.println("opcion : " + opcion);
        excelDAO.exportarExcel(nombreHoja, nombresCabecera, opcion);
    }
    
    public void exportarPlanillaExcel(String nombreHoja, List nombresCabecera, int opcion) {
        excelDAO.exportarPlanillaExcel(nombreHoja, nombresCabecera, opcion);
    }
    
    public void Exportar_Excel_PF() {
        excelDAO.exportarProformasFacturadas();
    }
    
    public void Exportar_Excel_CabecDet_Proforma(DatosProforma proforma) throws FileNotFoundException, BiffException {
        excelDAO.exportarCabecDetProforma(proforma);
    }
    
    public void Exportar_Excel_CabecDet_Factura(DatosDocumento documento, ArrayList<Detallees> detalle, boolean esFactura, tabla tablaFacturacion, Usuarios usuario) 
            throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        excelDAO.exportarCabecDetFactura(documento, detalle, esFactura, tablaFacturacion, usuario);
    }
    
    public void Exportar_Excel_CabecDet_GR(DatosGR guia, ArrayList<Detallees> detalle) 
            throws FileNotFoundException, BiffException {
        excelDAO.exportarCabecDetGuiaRemision(guia, detalle);
    }
    
    public void Exportar_Excel_CabecDet_NC(DatosNC nota, ArrayList<Detallenota> detalle) 
            throws FileNotFoundException, BiffException, ParseException {
        excelDAO.exportarCabecDetNotaCredito(nota, detalle);
    }
    
    public void Exportar_Excel_CabecDet_ND(DatosND nota, ArrayList<Detallenota> detalle) 
            throws FileNotFoundException, BiffException, ParseException {
        excelDAO.exportarCabecDetNotaDebito(nota, detalle);
    }
    
    public String Exportar_Excel_CabecDet_Letra(ArrayList lista, Map<String, Object> parametros, Control control) 
                                                throws FileNotFoundException, BiffException, IOException, WriteException {
        return excelDAO.exportarCabecDetLetra(lista, parametros, control);
    }
    
    public String Exportar_Excel_CabecDet_Letras(ArrayList lista, Map<String, Object> parametros, Control control, int opcionReporte) 
                                                 throws FileNotFoundException, BiffException, IOException, WriteException {
        return excelDAO.exportarCabecDetLetras(lista, parametros, control, opcionReporte);
    }
    
    public void exportarExcelCabDetCtaCteFacturasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList lista)
                                                               throws FileNotFoundException, BiffException, IOException, WriteException {
        excelDAO.exportarCtaCteFacturasSeleccionadas(nombreHoja, control, parametros, lista);
    }
    
    public void exportarExcelCabDetCtaCteLetrasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList lista)
                                                             throws FileNotFoundException, BiffException, IOException, WriteException {
        excelDAO.exportarCtaCteLetrasSeleccionadas(nombreHoja, control, parametros, lista);
    }
    
    public void Exportar_Excel_CabecDet_SK(DatosDocumento documento, ArrayList<Detallees> detalle, tabla tablaFacturacion) 
            throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        excelDAO.exportarCabecDetSK(documento, detalle, tablaFacturacion);
    }
    
    public void Exportar_Excel_CabecDet_IngSalXReg(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista, int tipo) 
            throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        excelDAO.exportarCabecDetIngSalXReg(parametros, lista, tipo);
    }
    
    public void Exportar_Excel_CabecDet_IngXCompraLocal(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista) 
            throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        excelDAO.exportarCabecDetIngXCompraLocal(parametros, lista);
    }
    
    // Servicios para generar documentos excel en background para luego imprimir.
    // Genera excel para PROFORMA o FACTURA
    public String generarExcelDocumento(Cabeces c, ArrayList<Detallees> det, String env, String ped, String nroguia, Date fechaEmision, String siniestro, String marca, 
                                        String placa, String modelo, String ordenTransporte, boolean esFactura, String nroSerie, String nroDoc, String vendedor,
                                        String puntoventa ,String condicion, String tipoCambio, tabla tablaFacturacion, Usuarios usuario)
                                        throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        DatosDocumento documento = new DatosDocumento();
        documento = llenarDatosDocumento(documento, c, env, ped, nroguia, fechaEmision, siniestro, marca, placa, modelo, ordenTransporte, esFactura, nroSerie, nroDoc,
                                         vendedor,puntoventa , condicion, tipoCambio, usuario);
        Exportar_Excel_CabecDet_Factura(documento, det, esFactura, tablaFacturacion, usuario);
        return documento.getNombreExcelDocumento();
    }
    
    private DatosDocumento llenarDatosDocumento(DatosDocumento documento, Cabeces c, String enviado, String pedido, String nroguia, Date fechaEmision, String siniestro, 
                                                String marca, String placa, String modelo, String ordenTransporte, boolean esFactura, String nroSerie, String nroDoc,
                                                String vendedor, String puntoventa, String condicion, String tipoCambio, Usuarios usuario) {
        String nombreExcelDoc = "\\";
        if ( esFactura ) {
            System.out.println("tipo impresora:" + usuario.getImpresora());
            if ( USUARIO_LUCY.equals(usuario.getNombre()) ) {
                nombreExcelDoc += "FacturaC_" + nroSerie + "_" + nroDoc + ".xls";
            } else {
                nombreExcelDoc += "Factura_" + nroSerie + "_" + nroDoc + ".xls";
            }
//            if ( PRINT_LX_300.equals(usuario.getImpresora()) ) {
//                nombreExcelDoc += "FacturaC_" + nroSerie + "_" + nroDoc + ".xls";
//            } else {
//                nombreExcelDoc += "Factura_" + nroSerie + "_" + nroDoc + ".xls";
//            }
            
        } else {
            nombreExcelDoc += "Boleta_" + nroSerie + "_" + nroDoc + ".xls";
        }
        
        documento.setNroSerie(nroSerie);
        documento.setNroDocumento(nroDoc);
        documento.setNombreExcelDocumento(nombreExcelDoc);
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        documento.setRutaProgramas(control.getRutaprogramas());
        documento.setRutaTemplate(control.getRutareportes());
        
        String glosa = c.getMoneda().equals(SOLES) ? GLOSA_SOLES : GLOSA_DOLARES;
        documento.setGlosa(glosa);
        
        String totalIgv = String.valueOf(c.getIgv());
//        documento.setTotalIgv(totalIgv);
        documento.setTotalIgv(formatearMonetario(totalIgv, 2));
        
        String nombreEmpresa = c.getClientes().getNombre();
        documento.setNombreEmpresa(nombreEmpresa);
        documento.setRucEmpresa(c.getClientes().getRuc());
        
        String distrito = c.getClientes().getUbigeo().getDistrito();
        String provincia = c.getClientes().getUbigeo().getProvincia();
        String departamento = c.getClientes().getUbigeo().getDepartamento();
        
        String direccion = c.getSucursales() == null ? c.getClientes().getDireccion() : c.getSucursales().getDireccion();
//        documento.setDireccionEmpresa(truncarDireccion(direccion));
        documento.setDireccionEmpresa(direccion);
        
        String ubigeo = "(" + distrito + " " + provincia + " " + departamento +")";
        documento.setUbigeo(ubigeo);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        documento.setFecha(df.format(c.getTipocambio().getFecha()));
        documento.setEnviado(enviado);
        documento.setPedido(pedido);
        documento.setOrden(c.getId().getNrodocumento());
        documento.setVendedor(vendedor);
        documento.setPuntoventa(puntoventa);
        
        if ( esFactura ) {
            documento.setMarca(marca);
            documento.setObservaciones("OBSERVACIONES: " + placa);
            documento.setModelo(modelo);
        
            if ( !"null".equals(ordenTransporte) ) {
                documento.setOrdenTransporte("O/T: N° " + ordenTransporte);
            }
            if ( !"null".equals(siniestro) ) {
                documento.setSiniestro("SINIESTRO: " + siniestro);
            }
            
        } else { // Para Boleta
            documento.setMarca(null);
            documento.setObservaciones("OBSERVACIONES: " + placa);
            //documento.setObservaciones(null);
            documento.setModelo(null);
            documento.setSiniestro(null);
        }
        documento.setCondicion(condicion);
        
        String fechaCadena, dia, mesLetras, año2Digitos, año4Digitos;
        if ( fechaEmision == null ) {
            dia = getDia(c.getTipocambio().getFecha());
            String mes = getMesFactura(c.getTipocambio().getFecha());
            String año = getAnio(c.getTipocambio().getFecha());
            fechaCadena = dia + "/" + mes + "/" + año;

            mesLetras = getMeses(Integer.parseInt(getMes(c.getTipocambio().getFecha()))-1);
            año2Digitos = año.substring(2);

        } else {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fechaCadena = formateador.format(fechaEmision);

            dia = getDia(fechaEmision);
            mesLetras = getMeses(Integer.parseInt(getMesFactura(fechaEmision))-1);
            año2Digitos = getAnio(fechaEmision).substring(2);
        }
        documento.setFechaEmision(fechaCadena);
        documento.setDia(dia);
        documento.setMes(mesLetras);
        documento.setAño(año2Digitos);
        
        if ( esFactura ) {
            Sistema sistema = new SistemaDAO().obtener_por_nombre("GUIA DE REMISION");
            String numSerieGuia = String.valueOf(sistema.getSerie());
            String numeroGuiaRemision = "";
            if ( !"null".equals(nroguia) ) {
                numeroGuiaRemision = formatearGuiaRemision(numSerieGuia, nroguia, 6);
                documento.setNumeroGuia(numeroGuiaRemision);
            }
        } else { // Para Boleta
            documento.setNumeroGuia(null);
        }
        
        documento.setTotal(formatearMonetario(String.valueOf(c.getTotal()), 2));
        documento.setCantidadEnLetras(Billete.BilleteX(c.getTotal(), c.getMoneda().equals("2") ? " DOLARES AMERICANOS" : " SOLES")); // SON;
        documento.setValorventa(formatearMonetario(String.valueOf(c.getValorventa()), 2));
//        documento.setValorventa(String.valueOf(c.getValorventa()));
        documento.setMoneda(getMonedaEnLetras(tipoCambio));
        
        return documento;
    }
    
    // Generar SK
    public String generarExcelDocumento(Cabecsalvar cs, ArrayList<Detallees> det, String env, String ped, String nroguia, Date fechaEmision, String siniestro, String marca, 
                                        String placa, String modelo, String ordenTransporte, String nroSerie, String nroDoc, String vendedor,
                                        String condicion, String tipoCambio, tabla tablaFacturacion)
                                        throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        DatosDocumento documento = new DatosDocumento();
        documento = llenarDatosDocumentoSK(documento, cs, env, ped, nroguia, fechaEmision, siniestro, marca, placa, modelo, ordenTransporte, nroSerie, nroDoc,
                                           vendedor, condicion, tipoCambio);
        Exportar_Excel_CabecDet_SK(documento, det, tablaFacturacion);
        return documento.getNombreExcelDocumento();
    }
    
    // Llenar datos de la SK
    private DatosDocumento llenarDatosDocumentoSK(DatosDocumento documento, Cabecsalvar cs, String enviado, String pedido, String nroguia, Date fechaEmision, String siniestro, 
                                                String marca, String placa, String modelo, String ordenTransporte, String nroSerie, String nroDoc,
                                                String vendedor, String condicion, String tipoCambio) {
        String nombreExcelDoc = "\\";
        nombreExcelDoc += "SK_" + nroSerie + "_" + nroDoc + ".xls";
        System.out.println("nombreExcelDoc:" + nombreExcelDoc);
        
        documento.setNroSerie(nroSerie);
        documento.setNroDocumento(nroDoc);
        documento.setNombreExcelDocumento(nombreExcelDoc);
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        documento.setRutaProgramas(control.getRutaprogramas());
        documento.setRutaTemplate(control.getRutareportes());
        
        String glosa = cs.getMoneda().equals(SOLES) ? GLOSA_SOLES : GLOSA_DOLARES;
        documento.setGlosa(glosa);
        
        String totalIgv = String.valueOf(cs.getIgv());
//        documento.setTotalIgv(totalIgv);
        documento.setTotalIgv(formatearMonetario(totalIgv, 2));
        
        String nombreEmpresa = cs.getClientes().getNombre();
        documento.setNombreEmpresa(nombreEmpresa);
        documento.setRucEmpresa(cs.getClientes().getRuc());
        
        String distrito = cs.getClientes().getUbigeo().getDistrito();
        String provincia = cs.getClientes().getUbigeo().getProvincia();
        String departamento = cs.getClientes().getUbigeo().getDepartamento();
        
//        String direccion = cs.getSucursales() == null ? cs.getClientes().getDireccion() : cs.getSucursales().getDireccion();
        String direccion = cs.getClientes().getDireccion();
        
//        documento.setDireccionEmpresa(truncarDireccion(direccion));
        documento.setDireccionEmpresa(direccion);
        
        String ubigeo = "(" + distrito + " " + provincia + " " + departamento +")";
        documento.setUbigeo(ubigeo);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        documento.setFecha(df.format(cs.getTipocambio().getFecha()));
        documento.setFecha(df.format(cs.getFecha()));
        documento.setEnviado(enviado);
        documento.setPedido(pedido);
        
//        documento.setOrden(cs.getId().getNrodocumento());
        documento.setOrden(cs.getCabeces().getId().getNrodocumento());
        
        documento.setVendedor(vendedor);
        
//        if ( esFactura ) {
//            documento.setMarca(marca);
//            documento.setPlaca("PLACA: " + placa);
//            documento.setModelo(modelo);
//        
//            if ( !"null".equals(ordenTransporte) ) {
//                documento.setOrdenTransporte("O/T: N° " + ordenTransporte);
//            }
//            if ( !"null".equals(siniestro) ) {
//                documento.setSiniestro("SINIESTRO: " + siniestro);
//            }
//            
//        } else { // Para Boleta
            documento.setMarca(null);
            documento.setObservaciones(null);
            documento.setModelo(null);
            documento.setSiniestro(null);
//        }
        documento.setCondicion(condicion);
        
        String fechaCadena, dia, mesLetras, año2Digitos, año4Digitos;
        if ( fechaEmision == null ) {
//            dia = getDia(cs.getTipocambio().getFecha());
            dia = getDia(cs.getFecha());
//            String mes = getMesFactura(cs.getTipocambio().getFecha());
            String mes = getMesFactura(cs.getFecha());
//            String año = getAnio(cs.getTipocambio().getFecha());
            String año = getAnio(cs.getFecha());
            fechaCadena = dia + "/" + mes + "/" + año;

//            mesLetras = getMeses(Integer.parseInt(getMes(cs.getTipocambio().getFecha()))-1);
            mesLetras = getMeses(Integer.parseInt(getMes(cs.getFecha()))-1);
            año2Digitos = año.substring(2);

        } else {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fechaCadena = formateador.format(fechaEmision);

            dia = getDia(fechaEmision);
            mesLetras = getMeses(Integer.parseInt(getMesFactura(fechaEmision))-1);
            año2Digitos = getAnio(fechaEmision).substring(2);
        }
        documento.setFechaEmision(fechaCadena);
        documento.setDia(dia);
        documento.setMes(mesLetras);
        documento.setAño(año2Digitos);
        
        // ¿Una SK puede generar GR? --> Preguntar a Xavier
//        if ( esFactura ) {
//            Sistema sistema = new SistemaDAO().obtener_por_nombre("GUIA DE REMISION");
//            String numSerieGuia = String.valueOf(sistema.getSerie());
//            String numeroGuiaRemision = "";
//            if ( !"null".equals(nroguia) ) {
//                numeroGuiaRemision = formatearGuiaRemision(numSerieGuia, nroguia, 6);
//                documento.setNumeroGuia(numeroGuiaRemision);
//            }
//        } else { // Para Boleta
//            documento.setNumeroGuia(null);
//        }
        
        documento.setTotal(formatearMonetario(String.valueOf(cs.getTotal()), 2));
        documento.setCantidadEnLetras(Billete.BilleteX(cs.getTotal(), cs.getMoneda().equals("2") ? " DOLARES AMERICANOS" : " SOLES")); // SON;
        documento.setValorventa(formatearMonetario(String.valueOf(cs.getValorventa()), 2));
//        documento.setValorventa(String.valueOf(c.getValorventa()));
        documento.setMoneda(getMonedaEnLetras(tipoCambio));
        
        return documento;
    }
    
    private String getMonedaEnLetras(String tipoCambio) {
        String monedaEnLetras = tipoCambio;
        String[] mon = monedaEnLetras.split(":");
        monedaEnLetras = mon[1].toUpperCase().trim();
        return monedaEnLetras;
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
    
    private String truncarDireccion(String direccion) {
        if ( !"null".equals(direccion) ) {
            if ( direccion.length() > NUM_CARACT_DIR_TRUNCA ) {
                direccion = direccion.substring(0, NUM_CARACT_DIR_TRUNCA);
            }
        }
        return direccion;
    }
    
    private static String formatearGuiaRemision(String numSerie, String numCorrelativo, int numDigitos) {
        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, numDigitos);
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    // Generar Ingresos/Salidas por Regularización (x Ajuste)
    public String generarExcelDocumento(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista, int tipo)
                                        throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        String nombreDoc = nombrarDocRegularizacion(parametros, tipo);
        System.out.println("NombreDoc : " + nombreDoc);
        Exportar_Excel_CabecDet_IngSalXReg(parametros, lista, tipo);
        return nombreDoc;
    }
    
    private String nombrarDocRegularizacion(Map<String, Object> parametros, int tipoRegularizacion) {

        String nombreExcelDoc = "\\";
        String nroSerie = String.valueOf(parametros.get("nroserie"));
        String nroDoc = String.valueOf(parametros.get("nroDoc"));
        
        System.out.println("tipoRegularizacion : " + tipoRegularizacion);
        if ( ING_REGULARIZACION == tipoRegularizacion ) {
//            System.out.println("INGRESO POR REGULARIZACION");
//            nombreExcelDoc += "IngXReg_" + nroSerie + "_" + nroDoc + ".xls";
            nombreExcelDoc += "IngXReg_" + nroDoc + ".xls";
            
        } else if ( SAL_REGULARIZACION == tipoRegularizacion ) {
//            System.out.println("SALIDA POR REGULARIZACION");
//            nombreExcelDoc += "SalXReg_" + nroSerie + "_" + nroDoc + ".xls";
            nombreExcelDoc += "SalXReg_" + nroDoc + ".xls";
        } else if ( ING_IMPORTACION == tipoRegularizacion ) {
//            System.out.println("INGRESO POR IMPORTACION");
//            nombreExcelDoc += "InglXImp_" + nroSerie + "_" + nroDoc + ".xls";
            nombreExcelDoc += "IngXImp_" + nroDoc + ".xls";
        }
        
        System.out.println("nombreExcelDoc" +  nombreExcelDoc);
        return nombreExcelDoc;
    }
    
    // Generar Ingreso por Compra Local
    public String generarExcelDocumento(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista)
                                        throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        String nombreDoc = nombrarDocumento(parametros, ING_POR_COMPRA_LOCAL);
        Exportar_Excel_CabecDet_IngXCompraLocal(parametros, lista);
        return nombreDoc;
    }
    
    // Generar Ingreso por Anulación
    public String generarExcelIngXAnulacion(Map<String, Object> parametros, ArrayList<DetalleAnulacion> lista)
                                            throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        String nombreDoc = nombrarDocumento(parametros, ING_POR_ANULACION);
        Exportar_Excel_CabecDet_IngXAnulacion(parametros, lista);
        return nombreDoc;
    }
    
    public void Exportar_Excel_CabecDet_IngXAnulacion(Map<String, Object> parametros, ArrayList<DetalleAnulacion> lista) 
                                                      throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        excelDAO.exportarCabecDetIngXAnulacion(parametros, lista);
    }
    
    private String nombrarDocumento(Map<String, Object> parametros, int tipoIngreso) {
        String nombreExcelDoc = "\\";
        
        if ( ING_POR_COMPRA_LOCAL  == tipoIngreso )  {
//            String nroSerie = String.valueOf(parametros.get("nroserie"));
//            String nroDoc = String.valueOf(parametros.get("nroDoc"));
//            System.out.println("nroSerie:" + nroSerie);
//            System.out.println("nroDoc:"+ nroDoc);
            String nroSerieProv = String.valueOf(parametros.get("nroSerieProv"));
            String nroDocProv = String.valueOf(parametros.get("nroDocProv"));
//            System.out.println("nroSerieProveedor:" + nroSerieProv);
//            System.out.println("nroDocProveedor:" + nroDocProv);
//
//            System.out.println("INGRESO POR COMPRA LOCAL");
//            nombreExcelDoc += "IngXReg_" + nroSerie + "_" + nroDoc + ".xls";
            nombreExcelDoc += "IngXCompraLocal_" + nroSerieProv + "_" + nroDocProv + ".xls";
//            nombreExcelDoc += "IngXReg_" + nroDoc + ".xls";
            
        } else if ( ING_POR_ANULACION == tipoIngreso ) {
            String nroIngreso = String.valueOf(parametros.get("nroIngreso"));
            nombreExcelDoc += "IngXAnulacion_" + nroIngreso + ".xls";
        }
        return nombreExcelDoc;
    }


}