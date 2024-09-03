package Mantenimiento;

import Entidades.Control;
import Entidades.Detallees;
import Entidades.Detallenota;
import Entidades.Otros.Constante;
import static Entidades.Otros.Constante.A;
import static Entidades.Otros.Constante.B;
import static Entidades.Otros.Constante.C;
import static Entidades.Otros.Constante.D;
import static Entidades.Otros.Constante.DOLAR;
import static Entidades.Otros.Constante.EE;
import static Entidades.Otros.Constante.F;
import static Entidades.Otros.Constante.FILA_INI_DET_BOL;
import static Entidades.Otros.Constante.FILA_INI_DET_FACT;
import static Entidades.Otros.Constante.FILA_INI_DET_GR;
import static Entidades.Otros.Constante.FILA_INI_DET_IXA;
import static Entidades.Otros.Constante.FILA_INI_DET_IXCL;
import static Entidades.Otros.Constante.FILA_INI_DET_IXR;
import static Entidades.Otros.Constante.FILA_INI_DET_LETRA;
import static Entidades.Otros.Constante.FILA_INI_DET_LETRAS;
import static Entidades.Otros.Constante.FILA_INI_DET_NC;
import static Entidades.Otros.Constante.FILA_INI_DET_PROF;
import static Entidades.Otros.Constante.FILA_INI_DET_SK;
import static Entidades.Otros.Constante.FILA_INI_DET_SXR;
import static Entidades.Otros.Constante.G;
import static Entidades.Otros.Constante.H;
import static Entidades.Otros.Constante.I;
import static Entidades.Otros.Constante.ING_IMPORTACION;
import static Entidades.Otros.Constante.ING_REGULARIZACION;
import static Entidades.Otros.Constante.J;
import static Entidades.Otros.Constante.K;
import static Entidades.Otros.Constante.L;
import static Entidades.Otros.Constante.M;
import static Entidades.Otros.Constante.MAX_CARACTERES_DESCRIPCION;
import static Entidades.Otros.Constante.MAX_CARACTERES_VENDEDOR_FACT;
import static Entidades.Otros.Constante.MAX_REP_DET;
import static Entidades.Otros.Constante.N;
import static Entidades.Otros.Constante.NC_DEVOLUCION;
import static Entidades.Otros.Constante.ND_MOD_UNICA;
import static Entidades.Otros.Constante.O;
import static Entidades.Otros.Constante.P;
import static Entidades.Otros.Constante.Q;
import static Entidades.Otros.Constante.R;
import static Entidades.Otros.Constante.S;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;
import static Entidades.Otros.Constante.SOL;
import static Entidades.Otros.Constante.TT;
import static Entidades.Otros.Constante.USUARIO_LUCY;
import Entidades.Otros.DatosDocumento;
import Entidades.Otros.DatosGR;
import Entidades.Otros.DatosNC;
import Entidades.Otros.DatosND;
import Entidades.Otros.DatosProforma;
import Entidades.Otros.Fecha;
import static Entidades.Otros.Fecha.getFechaActual;
import Entidades.Otros.Monetario;
import Entidades.Usuarios;
import Presentacion.CuentasxCobrarLetras.Letra;
import Presentacion.Anulacion.DetalleAnulacion;
import Presentacion.CuentasxCobrarFacturas.ListaFacturas;
import Presentacion.CuentasxCobrarLetras.ListaLetras;
import Presentacion.IngresoSalidas.DetalleIngSal;
import Servicios.Util;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.tabla;
import static com.lowagie.text.pdf.PdfName.T;
import static groovy.xml.Entity.times;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import javax.swing.JFrame;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.Border;
import jxl.format.CellFormat;
import jxl.format.PageOrientation;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableImage;
import static org.hsqldb.Library.year;

public class ExcelDAO {

    private JFrame it;
    JTable table;
    private CellFormat fNroDocumento;    
//  private int U;
    
    // Modalidad de Exportación 2
    JTable tableLeft;
    JTable tableRight;
    
    public ExcelDAO(JTable table, JFrame IU) {
        this.table = table;
        this.it = IU;
    }

    public void exportar(int opcion) {
        if ( table.getRowCount() > 0 ) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);

                        System.out.println("File : " + file);
                        System.out.println("Opcion : " + opcion);
                        
                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿ desea reemplazarlo ?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                switch ( opcion ) {
                                    case 1: // Exportar ""
                                        guardarclientprov(file);
                                        break;
                                    case 2: // Exportar "Cuenta Corriente Facturación"
                                        guardarCuentaCorrienteFact(file);
                                        break;
                                }       
                                
                            } else {
                                exportar(opcion);
                            }
                        } else {
                            guardarclientprov(file);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla esta vacia", "Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarCuentaCorrienteFact(File file) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Facturación-Ctas.Ctes.", 0);

        s.addCell(new Label(1, 0, "Número", new WritableCellFormat(titleFont)));
        s.addCell(new Label(2, 0, "Documento", new WritableCellFormat(titleFont)));
        s.addCell(new Label(3, 0, "Nro. Serie", new WritableCellFormat(titleFont)));
        s.addCell(new Label(4, 0, "Fecha", new WritableCellFormat(titleFont)));
        s.addCell(new Label(5, 0, "RUC", new WritableCellFormat(titleFont)));
        s.addCell(new Label(6, 0, "Nombre Cliente", new WritableCellFormat(titleFont)));
        s.addCell(new Label(7, 0, "Moneda", new WritableCellFormat(titleFont)));
        s.addCell(new Label(8, 0, "Facturado", new WritableCellFormat(titleFont)));
        s.addCell(new Label(9, 0, "Pagado", new WritableCellFormat(titleFont)));
        s.addCell(new Label(10, 0, "Saldo", new WritableCellFormat(titleFont)));
        s.addCell(new Label(11, 0, "Banco", new WritableCellFormat(titleFont)));

        for (int i = 1; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {                
                Object objeto = table.getValueAt(j, i);
                s.addCell(new Label(i, j + 1, String.valueOf(objeto)));
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }

    public void guardarclientprov(File file) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Transportistas", 0);

        s.addCell(new Label(1, 0, "Nro. de RUC", new WritableCellFormat(titleFont)));
        s.addCell(new Label(2, 0, "Nombre clientes/proveedor", new WritableCellFormat(titleFont)));
        s.addCell(new Label(3, 0, "Telefono(s)", new WritableCellFormat(titleFont)));
        s.addCell(new Label(4, 0, "Direccion del cliente", new WritableCellFormat(titleFont)));
        s.addCell(new Label(5, 0, "Plaza", new WritableCellFormat(titleFont)));
        s.addCell(new Label(6, 0, "Contacto", new WritableCellFormat(titleFont)));

        for (int i = 1; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {                
                Object objeto = table.getValueAt(j, i);
                s.addCell(new Label(i, j + 1, String.valueOf(objeto)));
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con exito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public ExcelDAO(JTable tableLeft, JTable tableRight, JFrame IU) {
        this.tableLeft = tableLeft;
        this.tableRight = tableRight;
        this.it = IU;
    }
    
    public void exportar2Tables(int anio) {
        if ( tableLeft.getRowCount() > 0 && tableRight.getRowCount() > 0) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);

                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿ desea reemplazarlo ?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                guardarFlujoComparativoVentas(file, anio);
                                
                            } else {
                                exportar2Tables(anio);
                            }
                        } else {
                            guardarFlujoComparativoVentas(file, anio);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla esta vacia", "Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarFlujoComparativoVentas(File file, int anio) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        
        WritableCellFormat formatoTitulo = new WritableCellFormat(titleFont, NumberFormats.TEXT);
        formatoTitulo.setAlignment(Alignment.CENTRE);
        
        
        NumberFormat totalesFont = new NumberFormat("###,###,###.00",NumberFormat.COMPLEX_FORMAT );
        WritableCellFormat totalesFontFormat = new WritableCellFormat(totalesFont);
//        WritableFont totalesFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
//        WritableCellFormat formatoTotales = new WritableCellFormat(totalesFont, NumberFormats.TEXT);
        totalesFontFormat.setAlignment(Alignment.RIGHT);
        
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Comparativo Ventas", 0);
                
        s.mergeCells(0, 0, 5, 1); //A1 - B7
        s.addCell(new Label(0, 0, "FLUJO COMPARATIVO DE VENTAS", new WritableCellFormat(formatoTitulo)));
        
        int filaInicio = 3;
        s.mergeCells(1, filaInicio, 2, filaInicio);
        s.setColumnView(1,18);
        s.addCell(new Label(1, filaInicio, "AÑO " + (anio-1), new WritableCellFormat(formatoTitulo)));
        s.addCell(new Label(1, filaInicio + 1, "TOTAL SOLES", new WritableCellFormat(formatoTitulo)));
        s.setColumnView(2,18);
        s.addCell(new Label(2, filaInicio + 1, "TOTAL DOLARES", new WritableCellFormat(formatoTitulo)));
        
        s.setColumnView(3, 6);
        
        s.mergeCells(4, filaInicio, 5, filaInicio);
        s.setColumnView(4,18);
        s.addCell(new Label(4, filaInicio, "AÑO " + anio, new WritableCellFormat(formatoTitulo)));
        s.addCell(new Label(4, filaInicio + 1, "TOTAL SOLES", new WritableCellFormat(formatoTitulo)));
        s.setColumnView(5,18);
        s.addCell(new Label(5, filaInicio + 1, "TOTAL DOLARES", new WritableCellFormat(formatoTitulo)));
        
        s.setColumnView(0,11);
        s.addCell(new Label(0, filaInicio + 2, "Enero"));
        s.addCell(new Label(0, filaInicio + 3, "Febrero"));
        s.addCell(new Label(0, filaInicio + 4, "Marzo"));
        s.addCell(new Label(0, filaInicio + 5, "Abril"));
        s.addCell(new Label(0, filaInicio + 6, "Mayo"));
        s.addCell(new Label(0, filaInicio + 7, "Junio"));
        s.addCell(new Label(0, filaInicio + 8, "Julio"));
        s.addCell(new Label(0, filaInicio + 9, "Agosto"));
        s.addCell(new Label(0, filaInicio + 10, "Septiembre"));
        s.addCell(new Label(0, filaInicio + 11, "Octubre"));
        s.addCell(new Label(0, filaInicio + 12, "Noviembre"));
        s.addCell(new Label(0, filaInicio + 13, "Diciembre"));
                
        for (int i = 1; i < tableLeft.getColumnCount(); i++) {
            for (int j = 0; j < tableLeft.getRowCount(); j++) {                
                Object objeto = tableLeft.getValueAt(j, i);
                //s.addCell(new Label(i, 4+(j+1), String.valueOf(objeto), new WritableCellFormat(formatoTotales)));
                double numero = Double.parseDouble(String.valueOf(objeto));
                System.out.println("Valor de numero : " + numero);
                s.addCell(new jxl.write.Number(i, 4+(j+1), numero, totalesFontFormat));
            }
        }
        
        for (int i = 0; i < tableRight.getColumnCount(); i++) {
            for (int j = 0; j < tableRight.getRowCount(); j++) {                
                Object objeto = tableRight.getValueAt(j, i);
                //s.addCell(new Label(i+4, 4+(j+1), String.valueOf(objeto), new WritableCellFormat(formatoTotales)));
                double numero = Double.parseDouble(String.valueOf(objeto));
                s.addCell(new jxl.write.Number(i+4, 4+(j+1), numero, totalesFontFormat));
            }
        }
        
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Reporte exportado con éxito", "Exportar Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    public void guardar(File file) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Transportistas", 0);

        for (int i = 0; i < table.getColumnCount(); i++) {
            s.addCell(new Label(i, 0, table.getColumnName(i), new WritableCellFormat(titleFont)));
        }

        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                Object objeto = table.getValueAt(j, i);
                s.addCell(new Label(i, j + 1, String.valueOf(objeto)));
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con exito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Generalizando exportación:
    public void exportarExcel(String nombreHoja, List nombresCabecera, int opcion) {
//        System.out.println("exportar Excel.... :D");
        if ( table.getRowCount() > 0 ) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);

                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿ desea reemplazarlo ?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                guardarExcel(file, nombreHoja, nombresCabecera);
                            } else {
                                exportar(opcion);
                            }
                        } else {
                            guardarExcel(file, nombreHoja, nombresCabecera);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla esta vacia", "Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarExcel(File file, String nombreHoja, List nombresCabecera) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet(nombreHoja, 0);

        for ( int i = 0; i < nombresCabecera.size(); i++ ) {
            s.addCell(new Label(i, 0, String.valueOf(nombresCabecera.get(i)), new WritableCellFormat(titleFont)));
        }
//      for (int i = 1; i < table.getColumnCount(); i++) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {                
                Object objeto = table.getValueAt(j, i);
                s.addCell(new Label(i, j + 1, String.valueOf(objeto)));
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con exito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void exportarPlanillaExcel(String nombreHoja, List nombresCabecera, int opcion) {
        if (table.getRowCount() > 0) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            switch (seleccion) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);

                        if (file.exists()) {
                            if (JOptionPane.showConfirmDialog(it, "Archivo existente ¿Desea reemplazarlo?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION) {
                                guardarPlanillaExcel(file, nombreHoja, nombresCabecera);
                            } else {
                                exportar(opcion);
                            }
                        } else {
                            guardarPlanillaExcel(file, nombreHoja, nombresCabecera);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla esta vacia", "Vacia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void guardarPlanillaExcel(File file, String nombreHoja, List nombresCabecera) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet(nombreHoja, 0);

        for ( int i = 0; i < nombresCabecera.size(); i++ ) {
            s.addCell(new Label(i, 0, String.valueOf(nombresCabecera.get(i)), new WritableCellFormat(titleFont)));
        }
        
        for (int filaTable = 0; filaTable < table.getRowCount(); filaTable++) {
            //FECHA, ID DOC., NUMERO, RAZON SOCIAL, RUC, MONEDA, TOTAL, INAFECTO, GLOSA, CUENTA OPCIONAL
            String fecha = String.valueOf(table.getValueAt(filaTable, 4));
            String letraDoc = String.valueOf(table.getValueAt(filaTable, 1));
            String idDoc = convertirCodigoSUNAT(letraDoc);
            String serie = String.valueOf(table.getValueAt(filaTable, 2));
            String numero = String.valueOf(table.getValueAt(filaTable, 3));
            String numDocumento = formatearDocumento(serie, numero);
            String razonSocial = String.valueOf(table.getValueAt(filaTable, 5));
            String ruc = String.valueOf(table.getValueAt(filaTable, 9));
            String moneda = String.valueOf(table.getValueAt(filaTable, 10));
            if ( DOLAR.equals(moneda) ) {
                moneda = "D";
            } else if ( SOL.equals(moneda) ) {
                moneda = "S";
            }
            String total = String.valueOf(table.getValueAt(filaTable, 11));
            String inafecto = "";
            String glosa = "";
            String cuentaOpcional = "";
            String columnas[] = {fecha, idDoc, numDocumento, razonSocial, 
                                 ruc, moneda, total, inafecto, glosa, cuentaOpcional};
            int numColumnas = columnas.length;
            for ( int colTable = 0; colTable < numColumnas; colTable++ ) {
                s.addCell(new Label(colTable, filaTable + 1, columnas[colTable]));
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String convertirCodigoSUNAT(String letraDoc) {
        String idDoc = "";
        switch ( letraDoc ) {
            case "F" :
                idDoc = "01";
                break;
            case "B" :
                idDoc = "03";
                break;
            case "NC" :
                idDoc = "07";
                break;
            case "ND" :
                idDoc = "08";
                break;
        }
        return idDoc;
    }
    
    public static String formatearNroDocNC(String serie, String numero) {
        return formatearCeros(serie, 3) + "-" + formatearCeros(numero, 8);
    }
    
    private static String formatearDocumento(String serie, String numero) {
//        return formatearCeros(serie, 3) + "-" + formatearCeros(numero, 8);
        return formatearCeros(serie, 4) + "-" + formatearCeros(numero, 8);
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    public void exportarProformasFacturadas() {
         
       if ( table.getRowCount() > 0 ) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);

                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿desea reemplazarlo?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                guardarProformasFacturadas(file);
                            } else {
                                exportarProformasFacturadas();
                            }
                        } else {
                            guardarProformasFacturadas(file);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 0. PROFORMAS FACTURADAS
    public void guardarProformasFacturadas(File file) throws FileNotFoundException, IOException, WriteException {
        
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Proforma Facturada", 0);

        s.addCell(new Label(0, 0, "Nº Proforma", new WritableCellFormat(titleFont)));
        s.addCell(new Label(1, 0, "Cliente", new WritableCellFormat(titleFont)));
        s.addCell(new Label(2, 0, "Fecha Proforma", new WritableCellFormat(titleFont)));
        s.addCell(new Label(3, 0, "Tipo Moneda", new WritableCellFormat(titleFont)));
        s.addCell(new Label(4, 0, "Total", new WritableCellFormat(titleFont)));
        
        for ( int i = 0; i < table.getColumnCount(); i++ ) {    
            int fila = 0;
            for ( int j = 0; j < table.getRowCount(); j++ ) {
                
                Object celdaSeleccionar = table.getValueAt(j, 6);
                Object objeto = table.getValueAt(j, i);
                int columna = i;
                if ( j == 0 ) {
                    fila = j;
                }
                if ( celdaSeleccionar != null && columna != 5 && columna != 6 ) { // Es true
                    
                    if ( String.valueOf(objeto).equals("null") ) {
                        s.addCell(new Label(columna, fila + 1, ""));

                    } else {
                        s.addCell(new Label(columna, fila + 1, String.valueOf(objeto)));
                    }
                    fila++;
                } else {
                }
            }
        }
        w.write();
        w.close();
        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // PROFORMA
    public void exportarCabecDetProforma(DatosProforma proforma) throws FileNotFoundException, BiffException {
       
       if ( table.getRowCount() > 0 ) {
            try {
                String nroSerie = proforma.getNroSerie();
                String nroDoc = proforma.getNroDocumento();
//                String ruta = proforma.getRutaProgramas() + "\\Proforma_" + nroSerie + "_" + nroDoc;
//                File file = new File(ruta);
//                exportarCabDetProforma(file, proforma);
                
                // Con formato de proforma
                
                System.out.println("SI VA A IMPRIMIR LA PROFORMA...");
                
                String ruta = proforma.getRutaProgramas() + "\\Proforma_" + nroSerie + "_" + nroDoc;
                File file = new File(ruta.replace("/", "\\"));

                String rutaLibroMargen0 = proforma.getRutaProgramas() + "\\ZLibroProf.xls";
                String rutaTemplate = proforma.getRutaProgramas() + "\\Proforma_" + nroSerie + "_" + nroDoc + ".xls";

                rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
                rutaTemplate = rutaTemplate.replace("/", "\\");

                exportarCabDetProforma(file, proforma, rutaLibroMargen0, rutaTemplate);

            } catch (WriteException e) {
                System.out.println("Excepcion:" + e.getMessage());
                JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                
            } catch (IOException ex) {
                System.out.println("Excepcion:" + ex.getMessage());
                JOptionPane.showMessageDialog(it, "Error al guardar proforma excel, configure la Ruta de Programas \n"
                                            + "a '" + proforma.getRutaProgramas() + "'", "Exportación Automática de Proforma", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 2. PROFORMA
    public void exportarCabDetProforma(File file, DatosProforma proforma,
                                       String rutaLibroMargen0, String rutaTemplate) 
            throws FileNotFoundException, IOException, WriteException, BiffException {
        
//        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
//        WritableWorkbook w = Workbook.createWorkbook(out);
//        WritableSheet s = w.createSheet("Proforma", 0);
        
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet hoja = w.getSheet(0); // sheet
        ajusteAnchoColumnasExcel(hoja);
        
        hoja.setColumnView(0, 5);
        hoja.setColumnView(1, 13);
        hoja.setColumnView(2, 20);
        hoja.setColumnView(3, 48);
        hoja.setColumnView(4, 9);
        hoja.setColumnView(5, 11);
        hoja.setColumnView(6, 18);
        hoja.setColumnView(7, 12);
        hoja.setColumnView(8, 12);
        hoja.setColumnView(9, 18);
        // Cabecera de Proforma
        
        // Datos de Empresa - SIAR
        //Añadir imágenes (nota fotos aquí jxl temporal sólo admite el formato PNG) 
        //0,1, respectivamente, en nombre de X, y 2, el número total de ancho y 5 representantes de la celda 
//        String rutaReportes = RUTA_LOGO_SIAR;
//        System.out.println("rutaReportes::>" + rutaReportes);
        
//        String rutaReportes = new ControlDAO().Obtener_Objeto(1).getRutaprogramas() + NAME_LOGO; // Antes
//        String rutaReportes = new ControlDAO().Obtener_Objeto(1).getRutalogoproforma();
        //    \\\\LEDIS\\ExcelsProforma\\logoExcelProforma.png
//        System.out.println("rutaReportes:" + rutaReportes);
//        File archivo = new File(rutaReportes);
//        System.out.println("archivo:" + archivo);
//        File archivo = new File("C:\\Imagenes\\logoOrbitParts");
//        File archivo = new File("C:\\Imagenes\\log_OrbitParts.png");
//        hoja.addImage(new WritableImage(0, 0, 3, 4, archivo));
        
        WritableFont ruc = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD);
        WritableCellFormat fRuc = new WritableCellFormat(ruc);
        fRuc.setAlignment(Alignment.LEFT);
        WritableFont numero = new WritableFont(WritableFont.ARIAL, 18, WritableFont.BOLD);
        WritableCellFormat fnumero = new WritableCellFormat(numero);
        fRuc.setAlignment(Alignment.LEFT);

        hoja.addCell(new Label(A, 4, "R.U.C. " + proforma.getRuc(), fRuc));
        hoja.addCell(new Label(F, 4, "NRO. DE PROFORMA :  " + proforma.getNroDocumento(), fnumero));
        
        WritableFont titleFontCab = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat fTituloIzq = new WritableCellFormat(titleFontCab);
        fTituloIzq.setAlignment(Alignment.LEFT);
        
        hoja.addCell(new Label(A, 5, proforma.getDireccion1(), fTituloIzq));
        hoja.addCell(new Label(A, 6, proforma.getDireccion2(), fTituloIzq));
        
        Date fechahoy = new Date();
        String xxx = proforma.getFecha();
        
        getFechaActual();
        
        hoja.addCell(new Label(A, 7, "TELEFONO : " + proforma.getTelefono(), fTituloIzq));
        hoja.addCell(new Label(F, 7, "FECHA DE EMISION :  " + getFechaActual(), fTituloIzq));                
        
        // Datos de Cliente
        hoja.addCell(new Label(A, 9, "EMPRESA", fTituloIzq));
        hoja.addCell(new Label(C, 9, proforma.getCliente(), fTituloIzq));
        
        hoja.addCell(new Label(A, 10, "ATENCIÓN", fTituloIzq));
        hoja.addCell(new Label(C, 10, proforma.getAtencion(), fTituloIzq));
        
        hoja.addCell(new Label(A, 11, "TELÉFONO", fTituloIzq));
        hoja.addCell(new Label(C, 11, proforma.getTelefonoFax(), fTituloIzq));
        
        
        hoja.addCell(new Label(F, 9, "VENDEDOR", fTituloIzq));
        hoja.addCell(new Label(G, 9, proforma.getVendedor(), fTituloIzq));
        
        hoja.addCell(new Label(F, 10, "PUNTO DE VENTA", fTituloIzq));
        hoja.addCell(new Label(G, 10, proforma.getPuntoventa(), fTituloIzq));
        
        hoja.addCell(new Label(F, 11, "E-MAIL", fTituloIzq));
        hoja.addCell(new Label(G, 11, proforma.getEmail(), fTituloIzq));
        
        hoja.addCell(new Label(F, 12, "REFERENCIA", fTituloIzq));
        hoja.addCell(new Label(G, 12, proforma.getReferencia(), fTituloIzq));
        
        // Detalle de Proforma
        WritableCellFormat fCabecera = new WritableCellFormat(titleFontCab);
        fCabecera.setAlignment(Alignment.CENTRE);
        fCabecera.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        hoja.addCell(new Label(0, FILA_INI_DET_PROF, "ITEM", fCabecera)); // N° Proforma
        hoja.addCell(new Label(1, FILA_INI_DET_PROF, "C.EMP.", fCabecera)); // N° Empresa
        hoja.addCell(new Label(2, FILA_INI_DET_PROF, "N DE SERIE", fCabecera)); // Cliente
        hoja.addCell(new Label(3, FILA_INI_DET_PROF, "DESCRIPCIÓN", fCabecera)); // Fecha Proforma
        hoja.addCell(new Label(4, FILA_INI_DET_PROF, "CANT.", fCabecera)); // Cantidad       
        hoja.addCell(new Label(5, FILA_INI_DET_PROF, "P.LISTA", fCabecera)); // Precio Lista
        hoja.addCell(new Label(6, FILA_INI_DET_PROF, "DESCUENTO", fCabecera));
        hoja.addCell(new Label(7, FILA_INI_DET_PROF, "P.U.NETO", fCabecera));
        hoja.addCell(new Label(8, FILA_INI_DET_PROF, "P.TOTAL", fCabecera));
//      hoja.addCell(new Label(8, FILA_INI_DET_PROF, "DISPONIBILIDAD", fCabecera));
        
        WritableFont titleNormal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableCellFormat fDetLetras = new WritableCellFormat(titleNormal);
        fDetLetras.setAlignment(Alignment.CENTRE);
        fDetLetras.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        WritableCellFormat fDetDer = new WritableCellFormat(titleNormal);
        fDetDer.setAlignment(Alignment.RIGHT);
        fDetDer.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        WritableCellFormat fDetIzq = new WritableCellFormat(titleNormal);
        fDetIzq.setAlignment(Alignment.LEFT);
        fDetIzq.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        int fila = FILA_INI_DET_PROF;
        
        for ( int j = 0; j < table.getRowCount(); j++ ) {
            fila++;

//           String codRepuesto = d.getRepuestos().getCodigoseg();            
            
            agregarFilaAExcel(j, 0, fila, 0, hoja, fDetLetras); // ITEM
            agregarFilaAExcel(j, 19, fila, 1, hoja, fDetIzq); // CÓDIGO EMPRESA  --> enrealidad es codigo segundo de repuesto y se encuentra ahora en colum 19 (antes 16)
            agregarFilaAExcel(j, 2, fila, 2, hoja, fDetIzq); // CÓDIGO

//          Concatenar descripcion + aplicacion
            String D1 = (String) table.getValueAt(j, 3);   //equipo (empieza de la colum 3 porque 2da colum de tabla Facturacion se oculto)
            String D2 = (String) table.getValueAt(j, 4);   //marca
            String D3 = (String) table.getValueAt(j, 5);    // modelo
            String D4 = (String) table.getValueAt(j, 6);    // descripcion
            String D7 = (String) table.getValueAt(j, 7);  //  colum Aplicacion oculto, opcional si se llegara a utilizar
            String DX = D1 + " " +D2 + " " + " "+ D3 + " "+ D4;
            agregarFilaAExcel(DX, fila, 3, hoja, fDetIzq); // se concatenan Descripcion concatenada
            
            agregarFilaAExcel(j, 8, fila, 4, hoja, fDetLetras); // CANT.
            
            double vvu = agregarVVUnitAExcel(j, 9, fila, 5, hoja, fDetDer); // P.Lista
    
            String descuentos = getDescuentosExcel(j);  
            
            System.out.println("Descuentos : " + descuentos);

            
            System.out.println(" j, 17" + table.getValueAt(j, 17));  //P.TOTAL   P.U.NETO = (j,14 / j,7)-> se le aumentara 3 por colum Equipo Marca y Modelo agregados (j,17 / j,10)

            int cant = (int) table.getValueAt(j, 11);
            Object objeto = table.getValueAt(j, 17);
            double vvNeto = new Util().Redondear2Decimales(Double.parseDouble(String.valueOf(objeto)) / cant);
            double vvTot = new Util().Redondear2Decimales(Double.parseDouble(String.valueOf(objeto)));

            System.out.println("Si lo hizo - P.U.Total : " + vvNeto);
                    
            double vv = agregarVVAExcel(vvNeto, j, fila, 7, hoja, fDetDer); // P.U.NETO
            
            agregarVVconDsctoAExcel(vvTot, j, fila, 8, hoja, fDetDer); // P.TOTAL
                        
            agregarFilaAExcel(descuentos, fila, 6, hoja, fDetIzq); // se concatenan DSCTOS.
//          agregarFilaAExcel(j, 15, fila, 8, hoja, fDetIzq); // DISPONIBILIDAD
        }
        fila = fila + 2;
        
        // Pie del Excel
        hoja.addCell(new Label(A, fila, "LOS PRECIOS ESTAN SUJETOS A VARIACIÓN SIN PREVIO AVISO Y EN " + proforma.getMoneda(), fTituloIzq));
        hoja.addCell(new Label(H, fila, "TOTAL", fCabecera));
        hoja.addCell(new Label(I, fila, proforma.getTotal(), fDetDer));
     

        fila++;
        hoja.addCell(new Label(A, fila, "LOS PRECIOS YA INCLUYEN EL I.G.V. " + proforma.getMarca(), fTituloIzq));
//        hoja.addCell(new Label(G, fila, "I.G.V. 18%", fCabecera));
//        hoja.addCell(new Label(H, fila, proforma.getIgv(), fDetDer));

        fila++;
        hoja.addCell(new Label(A, fila, "TODOS NUESTROS PRODUCTOS SON IMPORTADOS " + proforma.getMarca(), fTituloIzq));

        
        fila++;
//        hoja.addCell(new Label(G, fila, "TOTAL", fCabecera));
//        hoja.addCell(new Label(H, fila, proforma.getTotal(), fDetDer));
        
        // Datos del Vehículo
//        fila++;
//        hoja.addCell(new Label(A, fila, "MARCA:" + proforma.getMarca(), fTituloIzq));
//        hoja.addCell(new Label(C, fila, "PLACA:" + proforma.getPlaca(), fTituloIzq));
        
//        fila++;
//        hoja.addCell(new Label(A, fila, "MODELO:" + proforma.getModelo(), fTituloIzq));
        
//        fila++;
//        hoja.addCell(new Label(A, fila, "SERIE:" + proforma.getSerie(), fTituloIzq));
        
//        fila++;
//        hoja.addCell(new Label(A, fila, "MOTOR:" + proforma.getMotor(), fTituloIzq));
        
        w.write();
        w.close();
        workbook.close();
//        out.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Proforma exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void exportarCabecDetFactura(DatosDocumento documento, ArrayList<Detallees> detalle, boolean esFactura, tabla tablaFacturacion, Usuarios usuario) 
                                        throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        if ( table.getRowCount() > 0 ) {
            String nroSerie = documento.getNroSerie();
            String nroDoc = documento.getNroDocumento();
            String rutaProgramas = documento.getRutaProgramas();
            
            // Configuración de la plantilla Excel ZLibroFac para una impresora LX-350 (Excel - Tamaño de hoja OFICIO)
            String nameDoc = (esFactura == true ? "Factura" : "Boleta");
            String ruta = rutaProgramas + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc;
            File file = new File(ruta.replace("/", "\\"));
            
            String nombreLibro = (esFactura == true ? "ZLibroFac" : "ZLibroBol"); // Factura y boleta tamaño OFICIO
            String rutaLibroMargen0 = rutaProgramas + "\\" + nombreLibro + ".xls";
            String rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc + ".xls";
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
            rutaTemplate = rutaTemplate.replace("/", "\\");
            
            // Configuraciónn de la plantilla Excel ZLibroFac para una impresora LX-300 (Excel - Tamaño de hoja CARTA)
            if ( esFactura ) { // Para Factura
                String nameDocCarta = "FacturaC";
                String rutaCarta = rutaProgramas + "\\" + nameDocCarta + "_" + nroSerie + "_" + nroDoc;
                File fileCarta = new File(rutaCarta.replace("/", "\\"));

                String nombreLibroCarta = "ZLibroFacCarta"; // Factura Tamaño CARTA
                String rutaLibroMargen0Carta = rutaProgramas + "\\" + nombreLibroCarta + ".xls";
                String rutaTemplateCarta = rutaProgramas + "\\" + nameDoc + "C_" + nroSerie + "_" + nroDoc + ".xls";
                rutaLibroMargen0Carta = rutaLibroMargen0Carta.replace("/", "\\");
                rutaTemplateCarta = rutaTemplateCarta.replace("/", "\\");
                
                exportarCabDetFactura(file, documento, detalle, rutaLibroMargen0, rutaTemplate, tablaFacturacion, null);
                
                exportarCabDetFactura(fileCarta, documento, detalle, rutaLibroMargen0Carta, rutaTemplateCarta, tablaFacturacion, USUARIO_LUCY);
            }
            
            if ( !esFactura ) {
                exportarCabDetBoleta(file, documento, detalle, rutaLibroMargen0, rutaTemplate, tablaFacturacion);
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void ajusteAnchoColumnasExcel(WritableSheet hoja) { // Total = 10
        hoja.setColumnView(0, 10);  // A
        hoja.setColumnView(1, 3); // B
        hoja.setColumnView(2, 3); // C
        hoja.setColumnView(3, 1); // D
        hoja.setColumnView(4, 1); // E
        hoja.setColumnView(5, 14); // F
        hoja.setColumnView(6, 5); // G
        hoja.setColumnView(7, 12); // H
        hoja.setColumnView(8, 9); // I
        hoja.setColumnView(9, 12); // J
        hoja.setColumnView(10, 10); // K
        hoja.setColumnView(11, 14); // L
    }
    
    // 3. BOLETA
    public void exportarCabDetBoleta(File file, DatosDocumento documento, ArrayList<Detallees> det,
                                     String rutaLibroMargen0, String rutaTemplate, tabla tablaFacturacion) 
                                     throws FileNotFoundException, IOException, WriteException, BiffException, ParseException {
        
// Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet hoja = w.getSheet(0); // sheet
        //ajusteAnchoColumnasExcel(hoja);
        
        //Solo cambiaran tamaño de estas celdas para Boleta
        /*hoja.setColumnView(0, 5);  // A Cantidad
        hoja.setColumnView(2, 10);  // C N Serie
        hoja.setColumnView(3, 8);  // D N Serie
        hoja.setColumnView(10, 15); // K (para mas espacio para la descripcion
        hoja.setColumnView(11, 10); // L Precio Unitario
        hoja.setColumnView(12, 10); // L Precio Unitario Total*/
        
// Declaración de Fuentes y formatos de letra para celdas(derecha, izquierda)
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Normal = new WritableCellFormat(font10Normal);
        der10Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat der10NormalSinBorde = new WritableCellFormat(font10Normal);
        der10NormalSinBorde.setAlignment(Alignment.RIGHT);
        der10NormalSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE); // THIN
        
        WritableCellFormat cent10NormalSinborde = new WritableCellFormat(font10Normal);
        cent10NormalSinborde.setAlignment(Alignment.CENTRE);
        cent10NormalSinborde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        int tamanyo = 10;
        WritableFont font11ArialNarrow = new WritableFont(WritableFont.createFont("Arial Narrow"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq11AN = new WritableCellFormat(font11ArialNarrow);
        izq11AN.setAlignment(Alignment.LEFT);
        
        // 1. CABECERA DE BOLETA
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = documento.getFechaEmision();
        Date dateEmision = formatter.parse(dateInString);
        System.out.println(dateEmision);

        String dia2Cifras = Fecha.getDia(dateEmision);
        String mes2Cifras = Fecha.getMes(dateEmision);
        String año4cifras = Fecha.getAnio(dateEmision);
        String añoUltCifra = Fecha.getAnioUltimaCifra(dateEmision);

        // Asignar fecha de emisión en Boleta de Venta
        hoja.addCell(new Label(C, 5, dateInString, cent10NormalSinborde));  //antes en D
        //hoja.addCell(new Label(D, 5, mes2Cifras, cent10NormalSinborde));   //antes en F
        //hoja.addCell(new Label(F, 5, "" + año4cifras, cent10NormalSinborde));  //antes en H
        
        //Numero de boleta
        hoja.addCell(new Label(L, 6, "N de Boleta: "+documento.getNroSerie()+"-"+documento.getNroDocumento(), izq10Normal));
        //El punto de Venta
        hoja.addCell(new Label(L, 7, documento.getPuntoventa(), izq10Normal));
        
        //vendedor
        hoja.addCell(new Label(L, 8, "Vendedor: "+documento.getVendedor(), izq10Normal));
        
        hoja.addCell(new Label(C, 7, documento.getNombreEmpresa(), izq10Normal));  //antes en D
        hoja.addCell(new Label(C, 9, documento.getDireccionEmpresa(), izq10Normal));  //antes en D
        
        
        hoja.addCell(new Label(A, 11, "Cantidad", izq10Normal));
        hoja.addCell(new Label(C, 11, "N de Serie", izq10Normal));  // aca antes era Codigo Sec
        hoja.addCell(new Label(D, 11, "Descripción", izq10Normal));        
        hoja.addCell(new Label(L, 11, "Precio unitario", izq10Normal));        
        hoja.addCell(new Label(M, 11, "Importe", izq10Normal));        
        // 2. DETALLE DE BOLETA
        int fila = FILA_INI_DET_BOL; // Conteo de filas empieza desde 0 (antes fila 8 excel)
        Util util = new Util();
        int contFilas = 0; // si una descripcion ocupa mas de 1 linea
//        hoja.addMergedRegion
        for ( Detallees d : det ) {
            fila++; 
            fila = fila + contFilas;
            contFilas = 0;
            
            String cantidad = String.valueOf(d.getCantentregada());
            String unitario = formatearMonetario(String.valueOf(d.getPreciolista()), 2);
            
            int cantentregada = d.getCantentregada();
            
            String cadenaDsctos = "";
            double d1 = d.getDescuento1() / 100.0;
            cadenaDsctos += (d1 == 0.00 ? "0.0%" : (d1 * 100) + "%");
            
            double d2 = d.getDescuento2() / 100.0;
            cadenaDsctos += (d2 == 0.00 ?  "+0.0%" : "+" + (d2 * 100) + "%");
            
//            double d3 = d.getDescuento3() / 100.0;
//            cadenaDsctos += (d3 == 0.00 ? "+0.0%" : "+" + (d3 * 100) + "%");
            
//            double d4 = d.getDescuento4() / 100.0;
//            cadenaDsctos += (d4 == 0.00 ? "" : " " + d4 + "%");
            
            double PrecioLista = d.getPreciolista();
            
            double Precio = (((PrecioLista * (1 - d1)) * (1 - d2))); // * (1 - d3)); // * (1 - d4);
            double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;

            if ( d1 != 0 || d2 != 0 ) { //|| d3 != 0 ) { // || d4 != 0 ) {
                String totalConDsctos = String.valueOf(util.Redondear2Decimales(Total/cantentregada));
                unitario = formatearMonetario(totalConDsctos, 2);
            }
            
            String unitString = String.valueOf(util.Redondear2Decimales( Double.parseDouble(cantidad) * Double.parseDouble(unitario) ));
            String unitarioTotal = util.formatearMonetario(unitString, 2);
        
            String nserie = d.getRepuestos().getCodrepuesto();
            
            String modelo = ( d.getRepuestos().getDescrmodelo() == null ? "" : d.getRepuestos().getDescrmodelo());
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloEquip = d.getRepuestos().getModelos().getDescripcion();
            String descripcion = d.getRepuestos().getDescripcion(); // + " " + modelo; // ¿SE DEBE AGREGAR EL MODELO A LA DESCRIPCION EN BOLETA Y FACTURA?
            descripcion =   equipo+" "+ marca+" " +modeloEquip+" "+ descripcion; // Se utilizara variable de abajo para descripcion modificables
            String descrelementfact = d.getRepuestos().getDescrfactura();
            //descripcion = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION); // + cadenaDsctos;
            String[] descripcionPartes = agregarSaltoLineaCaracteres(descrelementfact, MAX_CARACTERES_DESCRIPCION);       
            if ( cantentregada > 0 ) {
                hoja.addCell(new Label(A, fila, cantidad, der10Normal)); // CANTIDAD
                //System.out.print(descripcionPartes[1]);
                hoja.addCell(new Label(C, fila, nserie, izq11AN));
//                hoja.addCell(new Label(C, fila, idRepuesto, fTituloIzq)); // CÓDIGO
                //////////////////////
                if(descrelementfact.length() > MAX_CARACTERES_DESCRIPCION && descripcionPartes.length > 1) {  // si DESCRIPCION ES LARGA y tien salto de linea
                    int tamañoIndexInterno=descripcionPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                    hoja.addCell(new Label(D, filaDescripcion, descripcionPartes[i], izq11AN));
                    //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                    i++;
                    filaDescripcion++;
                    tamañoIndexInterno--;
                    }
                    contFilas =i-1;
                } else {
                    hoja.addCell(new Label(D, fila, descrelementfact, izq11AN)); // DESCRIPCIÓN
                }
                //////////////////////////////
                //hoja.addCell(new Label(J, fila, cadenaDsctos, izq10Normal)); // DESCUENTOS  / Se comentara porque no es necesario para negocio de laptops
                
//                hoja.addCell(new Label(M, fila, util.formatearComaMillar(unitario), der10NormalSinBorde)); // P. UNITARIO
                hoja.addCell(new Label(L, fila, util.formatearComaMillar(String.valueOf(PrecioLista)), der10NormalSinBorde)); // P. UNITARIO --> (Actualizado a Precio de Lista)
                hoja.addCell(new Label(M, fila, util.formatearComaMillar(unitarioTotal), der10NormalSinBorde)); // IMPORTE
            }
        } 
        fila = 33;
        String glosa = documento.getGlosa();
        hoja.addCell(new Label(M, fila, glosa + " " + util.formatearComaMillar(documento.getTotal()), der10NormalSinBorde)); // TOTAL DEL IMPORTE
        
        
        int fila1 = 29;
        String obs = documento.getObservaciones();
        hoja.addCell(new Label(C, fila1, obs, izq11AN)); // Las observaciones para Boleta
        
        
        w.write();
        w.close();
        workbook.close();
        String Path = file.getAbsolutePath();
        
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    // 4. FACTURA EXCEL
    public void exportarCabDetFactura(File file, DatosDocumento factura, ArrayList<Detallees> det, 
                                      String rutaLibroMargen0, String rutaTemplate, tabla tablaFacturacion
                                      , String usuario)
                                      throws FileNotFoundException, IOException, WriteException, BiffException, FontFormatException {
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        System.out.println("rutaTemplate:" + rutaTemplate);
        WritableSheet s = w.getSheet(0); // sheet
        
        int tamanyo = 10;
        WritableFont font11Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
//        WritableFont font11Normal = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
//        WritableCellFormat izq10NormalSinborde = new WritableCellFormat(font10Normal);
//        izq10NormalSinborde.setAlignment(Alignment.LEFT);
        
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq9Normal = new WritableCellFormat(font9Normal);
        izq9Normal.setAlignment(Alignment.LEFT);
        
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq8Normal = new WritableCellFormat(font8Normal);
        izq8Normal.setAlignment(Alignment.LEFT);
        
//        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ARIALN.ttf"));
        WritableFont font11ArialNarrow = new WritableFont(WritableFont.createFont("Arial Narrow"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq11AN = new WritableCellFormat(font11ArialNarrow);
        izq11AN.setAlignment(Alignment.LEFT);
        
        WritableFont font11V = new WritableFont(WritableFont.createFont("Verdana"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat der11V = new WritableCellFormat(font11V);
        der11V.setAlignment(Alignment.RIGHT);
                
        WritableFont font10ArialNarrow = new WritableFont(WritableFont.createFont("Arial Narrow"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq10AN = new WritableCellFormat(font10ArialNarrow);
        izq10AN.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq11V = new WritableCellFormat(font11V);
        izq11V.setAlignment(Alignment.LEFT);
        
        WritableCellFormat cen11V = new WritableCellFormat(font11V);
        cen11V.setAlignment(Alignment.CENTRE);
        
        WritableCellFormat izq11Normal = new WritableCellFormat(font11Normal);
        izq11Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der11Normal = new WritableCellFormat(font11ArialNarrow);
        der11Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10NormalSinborde = new WritableCellFormat(font10Normal);
        cen10NormalSinborde.setAlignment(Alignment.CENTRE);
        cen10NormalSinborde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat cen10NormalBordeDelgado = new WritableCellFormat(font10Normal);
        cen10NormalBordeDelgado.setAlignment(Alignment.CENTRE);
        cen10NormalBordeDelgado.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        WritableCellFormat cent8Normal = new WritableCellFormat(font8Normal);
        cent8Normal.setAlignment(Alignment.CENTRE);
        
// I. CABECERA DE LA FACTURA
        // 1. Lima,....... de .................. del 20....
        String fe[] = factura.getFechaEmision().split("/");
        String dia = fe[0];
        String mesLetras = Fecha.getMesLetras(Integer.parseInt(fe[1]));
//        String año2DigUlt = fe[2].substring(2, 4);
        String año4Dig = fe[2].substring(0,4);
        
        String fechaCompleta = "Lima, " + dia + " de " + mesLetras + " del " + año4Dig;
        s.addCell(new Label(C, 9, fechaCompleta, izq10Normal));
        
//        String fechaSeparador = factura.getFechaEmision();
//        s.addCell(new Label(C, 9, fechaSeparador, cen10NormalSinborde));
        
//        s.addCell(new Label(C, 9, dia, cen10NormalSinborde));
//        s.addCell(new Label(EE, 9, mesLetras + "                      " + año2DigUlt, izq10Normal));
//        s.addCell(new Label(F, 9, año2DigUlt, izq10Normal));
//        s.addCell(new Label(D, 16, factura.getFechaEmision(), cen10NormalSinborde)); // FECHA formato dd/MM/AAAA
        
        // 2. Señor(es)
        s.addCell(new Label(D, 10,"Empresa: "+ factura.getNombreEmpresa(), izq10Normal));
        
        
        //2-4. N° de Factura
        
        s.addCell(new Label(S, 9,"N Factura: "+factura.getNroSerie()+" - "+factura.getNroDocumento(), izq10Normal));
        //2-5. Punto de Venta
        
        s.addCell(new Label(S, 10,factura.getPuntoventa(), izq10Normal));
        // 3. Dirección
        String dirEmpresa = factura.getDireccionEmpresa();
//        s.addCell(new Label(EE, 12, dirEmpresa, izq10Normal));
//        System.out.println("Direccion de empresa:" + dirEmpresa);
//        System.out.println("longitud:" + dirEmpresa.length());
        
//        if ( dirEmpresa.length() > 52 ) {
//            String direccionLinea1 = dirEmpresa.substring(0, 53);
//            String direccionLinea2 = dirEmpresa.substring(53, dirEmpresa.length()) + ".";
//            System.out.println("direccion1:" + direccionLinea1);
//            System.out.println("direccion2:" + direccionLinea2);
        s.addCell(new Label(D, 11,"Dir Empresa: "+ dirEmpresa, izq10Normal)); // Dirección Línea 1
//            s.addCell(new Label(EE, 13, direccionLinea2, izq10Normal)); // Dirección Línea 2
//        String ubigeoLinea2 = factura.getUbigeo();
//        s.addCell(new Label(EE, 13, ubigeoLinea2, izq10Normal)); // Dirección Línea 2
            
//        } else {
////            System.out.println("direccion1:" + dirEmpresa);
//            String direccionLinea1 = dirEmpresa.substring(0, dirEmpresa.length());
//            s.addCell(new Label(EE, 12, direccionLinea1, izq10Normal)); // Dirección Línea 1
//        }
        //3.5 Vendedor
         s.addCell(new Label(S, 11,"Vendedor: "+ factura.getVendedor(), izq10Normal));
        
        // 4. R.U.C.
        s.addCell(new Label(D, 12,"RUC: "+ factura.getRucEmpresa(), izq10Normal));
        
        // 5. Guía de Remisión
        s.addCell(new Label(S, 12,"N° Guia: "+ factura.getNumeroGuia(), izq10Normal));
        
        // Condición (Contado o Crédito)
//        s.addCell(new Label(G, 16, factura.getCondicion().toUpperCase(), cen10NormalSinborde));
        
        // Vendedor
//        String vendedor = truncarCaracteres(factura.getVendedor(), MAX_CARACTERES_VENDEDOR_FACT);
//        s.addCell(new Label(H, 16, vendedor, cent8Normal));
//        s.addCell(new Label(H, 16, vendedor, cen10NormalSinborde));
        
        
        s.addCell(new Label(A, 13, "Nro.Serie", izq11Normal));
        s.addCell(new Label(B, 13, "Equipo Marca Modelo", izq11Normal));  // aca antes era Codigo Sec
        s.addCell(new Label(G, 13, "Descripción", izq11Normal));        
        s.addCell(new Label(P, 13, "Cantidad", izq11Normal));        
        s.addCell(new Label(S, 13, "Precio", izq11Normal));        
        s.addCell(new Label(Constante.U, 13, "Total", der11Normal));        
        
// II. DETALLE DE LA FACTURA
        int fila = FILA_INI_DET_FACT;
        Util util = new Util();
        int igv = new ControlDAO().Obtener_Objeto(1).getImpuestoigv();
        
        int filaTblFact = 0;
//        System.out.println(" fila:" + fila);
        boolean conAnotacion = true;
        int contFilas = 0;
        for ( Detallees d : det ) {
            /*fila = fila + contFilas;
            contFilas = 0;*/
            
            String cantidad = String.valueOf(d.getCantentregada());
            String modelo = d.getRepuestos().getDescrmodelo();
            if ( modelo == null ) {
                modelo = "";
            }
            String unitario = formatearMonetario(String.valueOf(d.getPreciolista()), 2);

            int cantentregada = d.getCantentregada();
            String cadenaDsctos = "";
            
            double d1 = d.getDescuento1() / 100.0;
            String des1String = String.valueOf(d1 * 100);
            int d1Int = Integer.parseInt(des1String.substring(0, des1String.length() - 2));
//            cadenaDsctos += (d1 == 0.00 ? "0.0%" : (d1 * 100) + "%");
            cadenaDsctos += (d1 == 0.00 ? "0%" : d1Int + "%");
            
            double d2 = d.getDescuento2() / 100.0;
            String des2String = String.valueOf(d2 * 100);
            int d2Int = Integer.parseInt(des2String.substring(0, des2String.length() - 2));
//            cadenaDsctos += (d2 == 0.00 ? " + 0.0%" : " + " + (d2 * 100) + "%");
            cadenaDsctos += (d2 == 0.00 ? " + 0%" : " + " + d2Int + "%");
//            cadenaDsctos += (d2 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d2 * 100))) + "%");
            
            double d3 = d.getDescuento3() / 100.0;
            String des3String = String.valueOf(d3 * 100);
            int d3Int = Integer.parseInt(des3String.substring(0, des3String.length() - 2));
            cadenaDsctos += (d3 == 0.00 ? " + 0%" : " + " + d3Int + "%");
//            cadenaDsctos += (d3 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d3 * 100))) + "%");
            
            double d4 = d.getDescuento4() / 100.0;
            String des4String = String.valueOf(d4 * 100);
            int d4Int = Integer.parseInt(des4String.substring(0, des4String.length() - 2));
            cadenaDsctos += (d4 == 0.00 ? " + 0%" : " + " + d4Int + "%");
//            cadenaDsctos += (d4 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d4 * 100))) + "%");

            double PrecioLista = d.getPreciolista();
            double Precio = ((((PrecioLista * (1 - d1)) * (1 - d2)) * (1 - d3)) * (1 - d4));
            double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;

            if ( d1 != 0 || d2 != 0 || d3 != 0 || d4 != 0 ) {
                String totalConDsctos = String.valueOf(util.Redondear2Decimales(Total/cantentregada));
                unitario = formatearMonetario(totalConDsctos, 2);
            }
//            String idRepuesto = d.getRepuestos().getCodrepuesto();
//            double factorIGV = ( 1.0 + Double.parseDouble(String.valueOf(igv))/100 );

//            String vuString = String.valueOf(util.Redondear2Decimales(Double.parseDouble(unitario)/factorIGV));
//            String vu = util.formatearMonetario(vuString, 2);

//            String vutString = String.valueOf(util.Redondear2Decimales( Double.parseDouble(cantidad) * Double.parseDouble(vu) ));
//            String vut = util.formatearMonetario(vutString, 2);
        
            String descripcion = d.getRepuestos().getDescripcion(); // + " " + modelo;
            
            String descrModelo = d.getRepuestos().getDescrmodelo();
            if ( d.getRepuestos().getDescrmodelo() == null ) {
                descrModelo = "";
            }
            
            String nroParte = d.getRepuestos().getCodrepuesto();
            String codRepuesto = d.getRepuestos().getCodigoseg();
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloProduc = d.getRepuestos().getModelos().getDescripcion(); 
            String descrBase = equipo +" "+marca + " "+modeloProduc;
            
            System.out.println("codRepuesto:" + codRepuesto);
//            System.out.println("Codigoseg : " + codigoseg);
//            descripcion = truncarCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION);
                    
            String subTotal = String.valueOf(tablaFacturacion.getValueAt(filaTblFact, 17));
//            System.out.println("subTotal:::" + subTotal);
            
            if ( cantentregada > 0 ) {
                
                // 0. Nro. de Parte del Repuesto
                s.addCell(new Label(A, fila, nroParte, izq11V));
                // 1. Código de Repuesto --> ahora descripcion base +Descripcion y campo DescrModelo
                descripcion = descrBase+" "+descripcion + " " + descrModelo; // Se utilizara variable de abajo para probar modificables
                String descrelementfact = d.getRepuestos().getDescrfactura();
                String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descrelementfact, MAX_CARACTERES_DESCRIPCION)/* + " " + aplicacion*/;
                
                if(descrelementfact.length() > MAX_CARACTERES_DESCRIPCION && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(B, filaDescripcion, descripcionPorPartes[i], izq11AN));
                        //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    contFilas =i-1;
                } else {s.addCell(new Label(B, fila, descrelementfact, izq10Normal));  }// DESCRIPCIÓN
                //s.addCell(new Label(B, fila, descripcion, izq11AN));
                
                
                //s.addCell(new Label(G, fila, descripcion, izq11AN));
                
                // 3. Cantidad
                s.addCell(new Label(P, fila, cantidad, der11V));
                
                // 4. Anotación (descrLista)
                // Si el repuesto tiene "anotacion1", la siguiente fila = fila + 2, sino fila = fila + 1
                String anotacion1 = d.getRepuestos().getDesclista();
//                System.out.println("fila:" + fila);
//                System.out.println("anotacion1:" + anotacion1);
                
                /*Comentar DescLista porque no se sabe si seva a utilizar en Facturacion
                if ( d.getRepuestos().getDesclista() == null ) {
                    conAnotacion = false;
                    
                } else {
                    conAnotacion = true;
                    s.addCell(new Label(G, (fila + 1), anotacion1, izq11AN));
                }*/
                
                // 5. Descuentos
//                s.addCell(new Label(I, fila, cadenaDsctos, izq10AN));
//                s.addCell(new Label(I, fila, cadenaDsctos, izq11AN));
                
                // 6. Precio Unitario (actualizado a Precio de Lista)
//                s.addCell(new Label(J, fila, util.formatearComaMillar(vu), der10Normal));
//                s.addCell(new Label(L, fila, util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(PrecioLista))), der11V));
                double st = Double.parseDouble(subTotal);
                int cant = d.getCantentregada();
                double importe = (double) st/cant;
//                System.out.println("st:" + st);
//                System.out.println("cant:" + cant);
//                System.out.println("importe:" + importe);
                
                String imp = util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(importe)));
                s.addCell(new Label(S, fila, formatearMonetario(imp, 2), der11V));
//                s.addCell(new Label(L, fila, util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(PrecioLista))), der11V));
                
                // 7. IMPORTE --Valor de Venta (Sub-Total)
//                s.addCell(new Label(P, fila, util.formatearComaMillar(vut), der11V));
                String subT = util.formatearComaMillar(subTotal);
                s.addCell(new Label(Constante.U, fila, formatearMonetario(subT, 2), der11V));
            }
            filaTblFact++;
            
            if ( conAnotacion ) {
                fila = fila + 2;
                
            } else {
                fila = fila + 1;
            }
        }
        
        //39 - 14(ubicacion de fila donde empieza la lista de Detalles) = 25
        int filaSon = 39;
        int filaIGV = 43;
        if ( usuario != null ) {
            if ( USUARIO_LUCY.equals(usuario) ) {
                filaSon = 37;
                filaIGV = 42;
            }
        } else {
            System.out.println(">> usuario:" + usuario);
        }
        System.out.println(">> filaSon:" + filaSon);
            // SON:
            s.addCell(new Label(C, filaSon, "    " + factura.getCantidadEnLetras(), izq10Normal));
        
            // III. Pie del Excel
            String glosa = factura.getGlosa();
        
            // Total del Valor de Venta o SUB-TOTAL
            s.addCell(new Label(TT, filaSon + 2, glosa + " " + util.formatearComaMillar(factura.getValorventa()), der11V));
        
            // I.G.V. %
//            s.addCell(new Label(L, 60, igv + "%", izq10Normal)); // %IGV
            String IGVTexto = "IGV: "+String.valueOf(igv)+"%";
            s.addCell(new Label(Q, filaIGV, IGVTexto, der11V)); // %IGV
            s.addCell(new Label(TT, filaIGV, glosa + " " + util.formatearComaMillar(factura.getTotalIgv()), der11V)); // I.G.V.
        
            // Las Observaciones opcionales
            String obs = factura.getObservaciones();
            
            
            s.addCell(new Label(C, filaIGV, String.valueOf(obs), der11V));
            // TOTAL
            s.addCell(new Label(TT, filaSon + 7, glosa + " " + util.formatearComaMillar(factura.getTotal()), der11V));
            
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
//        System.out.println("Path:" + Path);
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Factura exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void exportarCabecDetSK(DatosDocumento documento, ArrayList<Detallees> detalle, tabla tablaFacturacion) 
                                   throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        if ( table.getRowCount() > 0 ) {
            
            System.out.println("Ya entro a imprimir .....SK");
            String nroSerie = documento.getNroSerie();
            String nroDoc = documento.getNroDocumento();
            String nameDoc = "SK";
            
            String ruta = documento.getRutaProgramas() + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc;
            File file = new File(ruta.replace("/", "\\"));
            
            String nombreLibro = "ZLibroSK";
            String rutaLibroMargen0 = documento.getRutaProgramas() + "\\" + nombreLibro + ".xls";
            String rutaTemplate = documento.getRutaProgramas() + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc + ".xls";
            
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
            rutaTemplate = rutaTemplate.replace("/", "\\");
            
            exportarCabDetSK(file, documento, detalle, rutaLibroMargen0, rutaTemplate, tablaFacturacion);
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 5.SALIDAS VARIAS(SK) EXCEL
    public void exportarCabDetSK(File file, DatosDocumento sk, ArrayList<Detallees> det, 
                                 String rutaLibroMargen0, String rutaTemplate, tabla tablaFacturacion)
                                 throws FileNotFoundException, IOException, WriteException, BiffException, FontFormatException {
        
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        int tamanyo = 10;
        WritableFont font11Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
        WritableFont font10Italic = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD, true);
        WritableCellFormat izq10Italic = new WritableCellFormat(font10Italic);
        izq10Italic.setAlignment(Alignment.LEFT);
        
        WritableCellFormat cen10Italic = new WritableCellFormat(font10Italic);
        cen10Italic.setAlignment(Alignment.CENTRE);
        
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq9Normal = new WritableCellFormat(font9Normal);
        izq9Normal.setAlignment(Alignment.LEFT);
        
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq8Normal = new WritableCellFormat(font8Normal);
        izq8Normal.setAlignment(Alignment.LEFT);
        
//        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ARIALN.ttf"));
        WritableFont font11ArialNarrow = new WritableFont(WritableFont.createFont("Arial Narrow"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq11AN = new WritableCellFormat(font11ArialNarrow);
        izq11AN.setAlignment(Alignment.LEFT);
        
        WritableFont font11V = new WritableFont(WritableFont.createFont("Verdana"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat der11V = new WritableCellFormat(font11V);
        der11V.setAlignment(Alignment.RIGHT);
                
        WritableFont font10ArialNarrow = new WritableFont(WritableFont.createFont("Arial Narrow"), tamanyo, WritableFont.NO_BOLD);
        WritableCellFormat izq10AN = new WritableCellFormat(font10ArialNarrow);
        izq10AN.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq11V = new WritableCellFormat(font11V);
        izq11V.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq11Normal = new WritableCellFormat(font11Normal);
        izq11Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der11Normal = new WritableCellFormat(font11ArialNarrow);
        der11Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10NormalSinborde = new WritableCellFormat(font10Normal);
        cen10NormalSinborde.setAlignment(Alignment.CENTRE);
        cen10NormalSinborde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat cen10NormalBordeDelgado = new WritableCellFormat(font10Normal);
        cen10NormalBordeDelgado.setAlignment(Alignment.CENTRE);
        cen10NormalBordeDelgado.setBorder(Border.ALL, BorderLineStyle.THIN);
        
        WritableCellFormat cent8Normal = new WritableCellFormat(font8Normal);
        cent8Normal.setAlignment(Alignment.CENTRE);
        
// I. CABECERA DE LA SK
        // 0. N° SK
        String nroSerie = sk.getNroSerie();
        String nroDoc = sk.getNroDocumento();
        String numSK = formatearNroDocNC(nroSerie, nroDoc);
        s.addCell(new Label(G, 4, "N° " + numSK, cen10NormalSinborde));
        
        // 1.(Factura) Lima,....... de .................. del 20....
        String fe[] = sk.getFechaEmision().split("/");
        String dia = fe[0];
        String mesLetras = Fecha.getMesLetras(Integer.parseInt(fe[1]));
        
//        String año2DigUlt = fe[2].substring(2, 4);
//        s.addCell(new Label(C, 9, dia, cen10NormalSinborde));
//        s.addCell(new Label(EE, 9, mesLetras + "                      " + año2DigUlt, izq10Normal));
        
        // 1.(SK) Lima,....... de ...................................
        String año4Dig = fe[2].substring(0, 4);
        s.addCell(new Label(B, 7, "Lima,", izq10Italic));
        s.addCell(new Label(C, 7, dia, cen10NormalSinborde));
        s.addCell(new Label(D, 7, "de", cen10Italic));
        s.addCell(new Label(EE, 7, mesLetras + "  del  " + año4Dig, izq10Normal));
        
//        s.addCell(new Label(F, 9, año2DigUlt, izq10Normal));
//        s.addCell(new Label(D, 16, factura.getFechaEmision(), cen10NormalSinborde)); // FECHA formato dd/MM/AAAA
        
        // 2. Señor(es)
        s.addCell(new Label(B, 8, "Señor(es):", izq10Italic));
        s.addCell(new Label(D, 8, sk.getNombreEmpresa(), izq10Normal));
        
        // 3. Dirección
        s.addCell(new Label(B, 8, "Dirección:", izq10Italic));
        String dirEmpresa = sk.getDireccionEmpresa();
//        s.addCell(new Label(EE, 12, dirEmpresa, izq10Normal));
//        System.out.println("Direccion de empresa:" + dirEmpresa);
//        System.out.println("longitud:" + dirEmpresa.length());
        
//        if ( dirEmpresa.length() > 52 ) {
//            String direccionLinea1 = dirEmpresa.substring(0, 53);
//            String direccionLinea2 = dirEmpresa.substring(53, dirEmpresa.length()) + ".";
//            System.out.println("direccion1:" + direccionLinea1);
//            System.out.println("direccion2:" + direccionLinea2);
        s.addCell(new Label(D, 10, dirEmpresa, izq10Normal)); // Dirección Línea 1
//            s.addCell(new Label(EE, 13, direccionLinea2, izq10Normal)); // Dirección Línea 2
//        String ubigeoLinea2 = factura.getUbigeo();
//        s.addCell(new Label(EE, 13, ubigeoLinea2, izq10Normal)); // Dirección Línea 2
            
//        } else {
////            System.out.println("direccion1:" + dirEmpresa);
//            String direccionLinea1 = dirEmpresa.substring(0, dirEmpresa.length());
//            s.addCell(new Label(EE, 12, direccionLinea1, izq10Normal)); // Dirección Línea 1
//        }
        
        // 4. R.U.C.
        s.addCell(new Label(B, 11, "R.U.C.", izq10Italic));
        s.addCell(new Label(D, 11, sk.getRucEmpresa(), izq10Normal));
        
        // 5. Guía de Remisión
        s.addCell(new Label(L, 11, sk.getNumeroGuia(), izq10Normal));
        
        // Condición (Contado o Crédito)
//        s.addCell(new Label(G, 16, factura.getCondicion().toUpperCase(), cen10NormalSinborde));
        
        // Vendedor
//        String vendedor = truncarCaracteres(factura.getVendedor(), MAX_CARACTERES_VENDEDOR_FACT);
//        s.addCell(new Label(H, 16, vendedor, cent8Normal));
//        s.addCell(new Label(H, 16, vendedor, cen10NormalSinborde));
        
// II. DETALLE DE LA SK
        int fila = FILA_INI_DET_SK;
        Util util = new Util();
        int igv = new ControlDAO().Obtener_Objeto(1).getImpuestoigv();
        
        int filaTblFact = 0;
//        System.out.println(" fila:" + fila);
        boolean conAnotacion = true;
        
        for ( Detallees d : det ) {
            
            String cantidad = String.valueOf(d.getCantentregada());
            String modelo = d.getRepuestos().getDescrmodelo();
            if ( modelo == null ) {
                modelo = "";
            }
            String unitario = formatearMonetario(String.valueOf(d.getPreciolista()), 2);

            int cantentregada = d.getCantentregada();
            String cadenaDsctos = "";
            
            double d1 = d.getDescuento1() / 100.0;
            String des1String = String.valueOf(d1 * 100);
            int d1Int = Integer.parseInt(des1String.substring(0, des1String.length() - 2));
//            cadenaDsctos += (d1 == 0.00 ? "0.0%" : (d1 * 100) + "%");
            cadenaDsctos += (d1 == 0.00 ? "0%" : d1Int + "%");
            
            double d2 = d.getDescuento2() / 100.0;
            String des2String = String.valueOf(d2 * 100);
            int d2Int = Integer.parseInt(des2String.substring(0, des2String.length() - 2));
//            cadenaDsctos += (d2 == 0.00 ? " + 0.0%" : " + " + (d2 * 100) + "%");
            cadenaDsctos += (d2 == 0.00 ? " + 0%" : " + " + d2Int + "%");
//            cadenaDsctos += (d2 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d2 * 100))) + "%");
            
            double d3 = d.getDescuento3() / 100.0;
            String des3String = String.valueOf(d3 * 100);
            int d3Int = Integer.parseInt(des3String.substring(0, des3String.length() - 2));
            cadenaDsctos += (d3 == 0.00 ? " + 0%" : " + " + d3Int + "%");
//            cadenaDsctos += (d3 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d3 * 100))) + "%");
            
            double d4 = d.getDescuento4() / 100.0;
            String des4String = String.valueOf(d4 * 100);
            int d4Int = Integer.parseInt(des4String.substring(0, des4String.length() - 2));
            cadenaDsctos += (d4 == 0.00 ? " + 0%" : " + " + d4Int + "%");
//            cadenaDsctos += (d4 == 0.00 ? " + 0%" : " + " + String.valueOf(Integer.parseInt(String.valueOf(d4 * 100))) + "%");

            double PrecioLista = d.getPreciolista();
            double Precio = ((((PrecioLista * (1 - d1)) * (1 - d2)) * (1 - d3)) * (1 - d4));
            double Total = Math.round((Precio * cantentregada) * 100.0) / 100.0;

            if ( d1 != 0 || d2 != 0 || d3 != 0 || d4 != 0 ) {
                String totalConDsctos = String.valueOf(util.Redondear2Decimales(Total/cantentregada));
                unitario = formatearMonetario(totalConDsctos, 2);
            }
//            String idRepuesto = d.getRepuestos().getCodrepuesto();
//            double factorIGV = ( 1.0 + Double.parseDouble(String.valueOf(igv))/100 );

//            String vuString = String.valueOf(util.Redondear2Decimales(Double.parseDouble(unitario)/factorIGV));
//            String vu = util.formatearMonetario(vuString, 2);

//            String vutString = String.valueOf(util.Redondear2Decimales( Double.parseDouble(cantidad) * Double.parseDouble(vu) ));
//            String vut = util.formatearMonetario(vutString, 2);
            
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloEquip = d.getRepuestos().getModelos().getDescripcion();
            String descripcion = d.getRepuestos().getDescripcion(); // + " " + modelo;
            
            String descrModelo = d.getRepuestos().getDescrmodelo();
            if ( d.getRepuestos().getDescrmodelo() == null ) {
                descrModelo = "";
            }
            
            String codRepuesto = d.getRepuestos().getCodrepuesto();
//            System.out.println("codRepuesto:" + codRepuesto);
//            descripcion = truncarCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION);
                    
            String subTotal = String.valueOf(tablaFacturacion.getValueAt(filaTblFact, 17));  //SubTotal es Total por producto
//            System.out.println("subTotal:::" + subTotal);
            
            if ( cantentregada > 0 ) {
                // 1. Cantidad
                s.addCell(new Label(B, fila, cantidad, der11V));
                
                // 2. Código de Repuesto
//                s.addCell(new Label(D, fila, codRepuesto, izq11V)); // J
                
                // Código de Repuesto (N° Parte)                
//                s.addCell(new Label(B, fila, idRepuesto, izq11AN));
                
                // 3. (Modelo - Descripción)
//                descripcion = descripcion + " " + descrModelo; //  + " " + codRepuesto;
                descripcion = equipo+" " + marca+ " "+modeloEquip+ " "+ descripcion + " " + descrModelo; //  + " " + codRepuesto;
                s.addCell(new Label(D, fila, descripcion, izq11AN));
                
                //////////////////////////////////////////////////////////////////////////
                // 4. Anotación (descrLista)
                // Si el repuesto tiene "anotacion1", la siguiente fila = fila + 2, sino fila = fila + 1
                String anotacion1 = d.getRepuestos().getDesclista();
//                System.out.println("fila:" + fila);
//                System.out.println("anotacion1:" + anotacion1);
                
                if ( d.getRepuestos().getDesclista() == null ) {
                    conAnotacion = false;
                    
                } else {
//                    System.out.println("con anotacion...");
                    conAnotacion = true;
                    s.addCell(new Label(F, (fila + 1), anotacion1, izq11AN));
                }
                
                // 5. Descuentos
//                s.addCell(new Label(I, fila, cadenaDsctos, izq10AN));
//                s.addCell(new Label(I, fila, cadenaDsctos, izq11AN));
                
                // 6. Precio Unitario (actualizado a Precio de Lista)
//                s.addCell(new Label(J, fila, util.formatearComaMillar(vu), der10Normal));
//                s.addCell(new Label(L, fila, util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(PrecioLista))), der11V));
                double st = Double.parseDouble(subTotal);
                int cant = d.getCantentregada();
                double importe = (double) st/cant;
//                System.out.println("st:" + st);
//                System.out.println("cant:" + cant);
//                System.out.println("importe:" + importe);
                
                String imp = util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(importe)));
                s.addCell(new Label(L, fila, formatearMonetario(imp, 2), der11V));
//                s.addCell(new Label(L, fila, util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(PrecioLista))), der11V));
                
                // 5. IMPORTE --Valor de Venta (Sub-Total)
//                s.addCell(new Label(P, fila, util.formatearComaMillar(vut), der11V));
                String subT = util.formatearComaMillar(subTotal);
                s.addCell(new Label(N, fila, formatearMonetario(subT, 2), der11V));
            }
            filaTblFact++;
            
            if ( conAnotacion ) {
                fila = fila + 2;
                
            } else {
                fila = fila + 1;
            }
//            System.out.println("fila NUEVA:" + fila);
        }
        
        // SON:
        s.addCell(new Label(C, 43, "    " + sk.getCantidadEnLetras(), izq10Normal));
        
// III. Pie del Excel
        String glosa = sk.getGlosa();
        
        // Total del Valor de Venta o SUB-TOTAL
//        s.addCell(new Label(N, 45, glosa + " " + util.formatearComaMillar(sk.getValorventa()), der11V));
        
        // I.G.V. %
//        s.addCell(new Label(L, 60, igv + "%", izq10Normal)); // %IGV
//        s.addCell(new Label(L, 47, String.valueOf(igv), der11V)); // %IGV
        
//        s.addCell(new Label(K, 49, String.valueOf(igv), der11V)); // %IGV
//        s.addCell(new Label(N, 49, glosa + " " + util.formatearComaMillar(sk.getTotalIgv()), der11V)); // I.G.V.
        
        // TOTAL
        s.addCell(new Label(N, 45, glosa + " " + util.formatearComaMillar(sk.getTotal()), der11V));        
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Factura exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // 6. y 7. INGRESOS/SALIDAS POR REGULARIZACION - EXCEL
    public void exportarCabecDetIngSalXReg(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista, int tipo) 
                                           throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        if ( table.getRowCount() > 0 ) {
            String nroSerie = String.valueOf(parametros.get("nroserie"));
            String nroDoc = String.valueOf(parametros.get("nroDoc"));
            String rutaProgramas = String.valueOf(parametros.get("rutaProgramas"));
            
            String nameDoc = (tipo == ING_REGULARIZACION ? "IngXReg" : (tipo == SAL_REGULARIZACION ? "SalXReg" : "IngxImp"));
            
            String ruta = rutaProgramas + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc;
            File file = new File(ruta.replace("/", "\\"));
            
            String nombreLibro = (tipo == ING_REGULARIZACION ? "ZLibroIXR" : (tipo == SAL_REGULARIZACION ? "ZLibroSXR" : "ZLibroIXI")); // Ingreso por Regularización & Salida por Regularización & Ingreso por Importacion
            String rutaLibroMargen0 = rutaProgramas + "\\" + nombreLibro + ".xls";
            
            String rutaTemplate = "";
            
            if ( tipo == ING_REGULARIZACION ) {
//                rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroSerie + "_" + nroDoc + ".xls";
                rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroDoc + ".xls";
                
            } else if ( tipo == SAL_REGULARIZACION ) {
                rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroDoc + ".xls";

            } else if ( tipo == ING_IMPORTACION ) {
                rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroDoc + ".xls";
            }
            
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
            rutaTemplate = rutaTemplate.replace("/", "\\");
            exportarCabDetIngYSalXReg(file, parametros, lista, rutaLibroMargen0, rutaTemplate, tipo);
                
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 6. y 7. INGRESOS Y SALIDAS X REGULARIZACION (X AJUSTE) EXCEL
    public void exportarCabDetIngYSalXReg(File file, Map<String, Object> parametros, ArrayList<DetalleIngSal> lista,
                                          String rutaLibroMargen0, String rutaTemplate, int tipo)
                                          throws FileNotFoundException, IOException, WriteException, BiffException, FontFormatException {
        
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        // Para título del excel
        WritableFont font20NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 20, WritableFont.BOLD);
        WritableCellFormat cen20NormalCalibri = new WritableCellFormat(font20NegritaCalibri);
        cen20NormalCalibri.setAlignment(Alignment.CENTRE);
        
        // Para etiquetas
        WritableFont font10NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.BOLD);
        WritableFont font10Calibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        izq10NegritaCalibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        der10NegritaCalibri.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        cen10NegritaCalibri.setAlignment(Alignment.CENTRE);
        
        // Para valores
        WritableCellFormat izq10Calibri = new WritableCellFormat(font10Calibri);
        izq10Calibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Calibri = new WritableCellFormat(font10Calibri);
        der10Calibri.setAlignment(Alignment.RIGHT);
        
        // Para línea que separa el último Detalle del Total
        WritableCellFormat izqFinDetalle = new WritableCellFormat(izq10Calibri);
        WritableCellFormat derFinDetalle = new WritableCellFormat(der10Calibri);
        
        izqFinDetalle.setAlignment(Alignment.LEFT);
        izqFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
        derFinDetalle.setAlignment(Alignment.RIGHT);
        derFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
// I. CABECERA DE LA SALIDA POR REGULARIZACIÓN (O POR AJUSTE)
        
        int fila = 0;
        // 1. Título
        if ( tipo == ING_REGULARIZACION ) {
            s.addCell(new Label(A, 1, "INGRESOS AL ALMACÉN", cen20NormalCalibri));
            fila = FILA_INI_DET_SXR;
            
        } else if ( tipo == SAL_REGULARIZACION ) {
            s.addCell(new Label(A, 1, "SALIDA DEL ALMACÉN", cen20NormalCalibri));
            fila = FILA_INI_DET_IXR;
        } else if ( tipo == ING_IMPORTACION ) {
            s.addCell(new Label(A, 1, "INGRESO POR IMPORTACION", cen20NormalCalibri));
            fila = FILA_INI_DET_IXR;
        }
        int fila_ini = fila;
        
        // 2. Empresa
        s.addCell(new Label(B, 2, "Empresa", izq10NegritaCalibri));
        String empresa = String.valueOf(parametros.get("empresa"));
        s.addCell(new Label(D, 2, empresa, izq10Calibri));
        
        // 3. Tipo de Transacción
        s.addCell(new Label(B, 3, "Tipo de Transacción", izq10NegritaCalibri));
        String tipoTra = String.valueOf(parametros.get("transaccion"));
        s.addCell(new Label(D, 3, tipoTra, izq10Calibri));
        
        // 4. Documento
        s.addCell(new Label(B, 4, "Documento", izq10NegritaCalibri));
        String documento = String.valueOf(parametros.get("nroDoc"));
        s.addCell(new Label(D, 4, documento, izq10Calibri));
        
        // 5. Motivo
        s.addCell(new Label(B, 5, "Motivo", izq10NegritaCalibri));
        String motivo = String.valueOf(parametros.get("motivo"));
        s.addCell(new Label(D, 5, motivo, izq10Calibri));
        
        // 6. Fecha y Hora   
//        s.addCell(new Label(F, 6, "Fecha y Hora", der10NegritaCalibri));
//        s.addCell(new Label(G, 6, fechayHora, izq10Calibri));
        
        s.addCell(new Label(F, 6, "Fecha y Hora", izq10NegritaCalibri));
        s.addCell(new Label(H, 6, getFechayHora(), der10Calibri));
        
// II. DETALLE DE LA SALIDA POR REGULARIZACIÓN(O POR AJUSTE)
        
//        System.out.println(" fila:" + fila);
        Util util = new Util();
//        int filaTblFact = 0;
//        boolean conAnotacion = true;
        
        // Cabecera de Salidas del Almacén
        s.addCell(new Label(B, fila, "Nro. de Parte", izq10NegritaCalibri));
//        s.addCell(new Label(C, fila, "Cod. Secundario", izq10NegritaCalibri));
        s.addCell(new Label(D, fila, "Descripción", cen10NegritaCalibri));
        s.addCell(new Label(F, fila, "Cantidad", der10NegritaCalibri));
        s.addCell(new Label(G, fila, "Precio Lista", der10NegritaCalibri));
        s.addCell(new Label(H, fila, "Total", der10NegritaCalibri));
        
        int numDetalles = lista.size();
//        System.out.println("numDetalles:" + numDetalles);
        
        fila++;
        for ( DetalleIngSal d : lista ) {
            // 1. N° de Parte
            String nroParte = d.getNroParte();
            s.addCell(new Label(B, fila, nroParte, izq10Calibri));
            
            // 2. Cod. Secundario
//            String codSecundario = d.getCodSec();
//            s.addCell(new Label(C, fila, codSecundario, izq10Calibri));
            
            // 3. Descripción
            String equipo = d.getEquipo();
            String marca = d.getMarca();
            String modelo_de_marca = d.getModelo();
            String descripcion = d.getDescripcion();
            descripcion = equipo+" " + marca +" "+modelo_de_marca + " "+ d.getDescripcion();
//            System.out.println("descripcion:" + descripcion);
            String modelo = d.getDescrModelo();
            
//            System.out.println("modelo:" + modelo);
            String descripcion_modelo = descripcion + " " + modelo;
            //s.addCell(new Label(D, fila, descripcion_modelo, izq10Calibri));
            
            String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion_modelo, 50)/* + " " + aplicacion*/;
            System.out.print("Campos:"+ equipo+" " + marca + " "+modelo_de_marca );    
            if(descripcion_modelo.length() > 50 && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(D, filaDescripcion, descripcionPorPartes[i], izq10Calibri));
                        //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    //contFilas =i-1;
           } else {s.addCell(new Label(D, fila, descripcion_modelo, izq10Calibri));  }// DESCRIPCIÓN
            
            // 4. Cantidad
            String cantidad = d.getCantidad();
            s.addCell(new Label(F, fila, cantidad, der10Calibri));
            
            // 5. Precio Lista
            String precioLista = d.getPrecioLista();
            s.addCell(new Label(G, fila, precioLista, der10Calibri));
            
            // 6. Total
            String total = d.getTotal();
            s.addCell(new Label(H, fila, total, der10Calibri));
            
//            String descrModelo = d.getRepuestos().getDescrmodelo();
//            if ( d.getRepuestos().getDescrmodelo() == null ) {
//                descrModelo = "";
//            }
//            descripcion = descripcion + " " + descrModelo;
//            String anotacion1 = d.getRepuestos().getDesclista();
//            if ( d.getRepuestos().getDesclista() == null ) {
//                conAnotacion = false;
//            } else {
//                conAnotacion = true;
//                s.addCell(new Label(F, (fila + 1), anotacion1, izq11AN));
//            }
            
//            double st = Double.parseDouble(subTotal);
//            int cant = d.getCantentregada();
//            double importe = (double) st/cant;
//
//            String imp = util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(importe)));
//            s.addCell(new Label(L, fila, formatearMonetario(imp, 2), der11V));
//
//            // 5. IMPORTE --Valor de Venta (Sub-Total)
//            String subT = util.formatearComaMillar(subTotal);
//            s.addCell(new Label(N, fila, formatearMonetario(subT, 2), der11V));
//            
//            if ( conAnotacion ) {
//                fila = fila + 2;
//                
//            } else {
//                fila = fila + 1;
//            }
//            System.out.println("fila-8  :" + (fila - 8));
//            System.out.println("numDetalles-1   :" + (numDetalles));
            if ( fila - fila_ini == numDetalles ) {
                s.addCell(new Label(B, fila, nroParte, izqFinDetalle));
//                s.addCell(new Label(C, fila, codSecundario, izqFinDetalle));
                s.addCell(new Label(C, fila, "", izqFinDetalle));
                /*if(descripcion_modelo.length() > 50 && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(D, filaDescripcion, descripcionPorPartes[i], izqFinDetalle));
                        //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    //contFilas =i-1;
                   } else {s.addCell(new Label(D, fila, descripcion_modelo, izqFinDetalle));  }// DESCRIPCIÓN*/
                s.addCell(new Label(D, fila, descripcion_modelo, izqFinDetalle));
                s.addCell(new Label(EE, fila, "", izqFinDetalle));
                s.addCell(new Label(F, fila, cantidad, derFinDetalle));
                s.addCell(new Label(G, fila, precioLista, derFinDetalle));
                s.addCell(new Label(H, fila, total, derFinDetalle));
            }
            fila++;
        }
        
// III. Pie del Excel
        String glosa = String.valueOf(parametros.get("glosa"));
        String total = String.valueOf(parametros.get("total"));
        
        // TOTAL
        s.addCell(new Label(G, fila, "TOTAL", der10NegritaCalibri));
        s.addCell(new Label(H, fila, glosa + " " + util.formatearComaMillar(total), der10NegritaCalibri));
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Factura exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void exportarCabecDetIngXCompraLocal(Map<String, Object> parametros, ArrayList<DetalleIngSal> lista) 
                                                throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        if ( table.getRowCount() > 0 ) {
//            String nroSerie = String.valueOf(parametros.get("nroserie"));
//            String nroDoc = String.valueOf(parametros.get("nroDoc"));
            
            String nroSerieProv = String.valueOf(parametros.get("nroSerieProv"));
            String nroDocProv = String.valueOf(parametros.get("nroDocProv"));
            String rutaProgramas = String.valueOf(parametros.get("rutaProgramas"));
            
            String nameDoc = "IngXCompraLocal";
            String ruta = rutaProgramas + "\\" + nameDoc + "_" + nroSerieProv + "_" + nroDocProv;
            File file = new File(ruta.replace("/", "\\"));
            
            String nombreLibro = "ZLibroIXCL"; // Ingreso por Compra Local
            String rutaLibroMargen0 = rutaProgramas + "\\" + nombreLibro + ".xls";
            
            String rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroSerieProv + "_" + nroDocProv + ".xls";
//            rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroDoc + ".xls";
            
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
            rutaTemplate = rutaTemplate.replace("/", "\\");
            exportarCabDetIngXCompraLocal(file, parametros, lista, rutaLibroMargen0, rutaTemplate);
                
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 8.INGRESOS POR COMPRA LOCAL - EXCEL
    public void exportarCabDetIngXCompraLocal(File file, Map<String, Object> parametros, ArrayList<DetalleIngSal> lista,
                                              String rutaLibroMargen0, String rutaTemplate)
                                              throws FileNotFoundException, IOException, WriteException, BiffException, FontFormatException {
        
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        // Para título del excel
        WritableFont font20NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 20, WritableFont.BOLD);
        WritableCellFormat cen20NormalCalibri = new WritableCellFormat(font20NegritaCalibri);
        cen20NormalCalibri.setAlignment(Alignment.CENTRE);
        
        // Para etiquetas
        WritableFont font10NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.BOLD);
        WritableFont font10Calibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        izq10NegritaCalibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        der10NegritaCalibri.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        cen10NegritaCalibri.setAlignment(Alignment.CENTRE);
        
        // Para valores
        WritableCellFormat izq10Calibri = new WritableCellFormat(font10Calibri);
        izq10Calibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Calibri = new WritableCellFormat(font10Calibri);
        der10Calibri.setAlignment(Alignment.RIGHT);
        
        // Para línea que separa el último Detalle del Total
        WritableCellFormat izqFinDetalle = new WritableCellFormat(izq10Calibri);
        WritableCellFormat derFinDetalle = new WritableCellFormat(der10Calibri);
        
        izqFinDetalle.setAlignment(Alignment.LEFT);
        izqFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
        derFinDetalle.setAlignment(Alignment.RIGHT);
        derFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
// I. CABECERA DEL INGRESO POR COMPRA LOCAL (O POR AJUSTE)
        
        int fila = 0;
        // 1. Título
        s.addCell(new Label(A, 1, "INGRESOS AL ALMACÉN", cen20NormalCalibri));
        fila = FILA_INI_DET_IXCL;
        
        // 2. Empresa
        s.addCell(new Label(B, 2, "Empresa", izq10NegritaCalibri));
        String empresa = String.valueOf(parametros.get("empresa"));
        s.addCell(new Label(D, 2, empresa, izq10Calibri));
        
        // 3. Tipo de Transacción
        s.addCell(new Label(B, 3, "Tipo de Transacción", izq10NegritaCalibri));
        String tipoTra = String.valueOf(parametros.get("transaccion"));
        s.addCell(new Label(D, 3, tipoTra, izq10Calibri));
        
        // 4. Documento
        s.addCell(new Label(B, 4, "Documento", izq10NegritaCalibri));
        String nroSerie = String.valueOf(parametros.get("nroserie"));
        String nroDoc = String.valueOf(parametros.get("nroDoc"));
        String numFact = nroSerie + "-" + nroDoc;
        s.addCell(new Label(D, 4, numFact, izq10Calibri));
        
        s.addCell(new Label(B, 5, "Proveedor", izq10NegritaCalibri));
        // 5.1. N° Serie (Proveedor)
        String serieProveedor = String.valueOf(parametros.get("nroSerieProv"));
        
        // 5.2. N° Documento (Proveedor)
        String nroDocProveedor = String.valueOf(parametros.get("nroDocProv"));
        
        // 5.3. Nombre (Proveedor)
        String nombreProveedor = "- " + String.valueOf(parametros.get("proveedor"));
//        s.addCell(new Label(EE, 5, nombreProveedor, izq10Calibri));
        
        String nroFactProveedor = serieProveedor + "-" + nroDocProveedor + "     " + nombreProveedor;
        s.addCell(new Label(D, 5, nroFactProveedor, izq10Calibri));
        
        // 6. Motivo
        s.addCell(new Label(B, 6, "Motivo", izq10NegritaCalibri));
        String motivo = String.valueOf(parametros.get("motivo"));
        s.addCell(new Label(D, 6, motivo, izq10Calibri));
        
        // 7. Fecha y Hora        
        Date fechaHora = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(fechaHora);
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fechaHora);
        String fechayHora = fecha + " - " + hora;
        s.addCell(new Label(F, 7, "Fecha y Hora", izq10NegritaCalibri));
        s.addCell(new Label(H, 7, fechayHora, der10Calibri));
        
// II. DETALLE DEL INGRESO POR COMPRA LOCAL
        
//        System.out.println(" fila:" + fila);
        Util util = new Util();
//        int filaTblFact = 0;
//        boolean conAnotacion = true;
        
        // Cabecera de Salidas del Almacén
        s.addCell(new Label(B, fila, "Nro. de Parte", izq10NegritaCalibri));
//        s.addCell(new Label(C, fila, "Cod. Secundario", izq10NegritaCalibri));
        s.addCell(new Label(D, fila, "Descripción", cen10NegritaCalibri));
        s.addCell(new Label(F, fila, "Cantidad", der10NegritaCalibri));
        s.addCell(new Label(G, fila, "Precio Lista", der10NegritaCalibri));
        s.addCell(new Label(H, fila, "Total", der10NegritaCalibri));
        
        int numDetalles = lista.size();
//        System.out.println("numDetalles:" + numDetalles);
        
        fila++;
        for ( DetalleIngSal d : lista ) {
            // 1. N° de Parte
            String nroParte = d.getNroParte();
            s.addCell(new Label(B, fila, nroParte, izq10Calibri));
            
            // 2. Cod. Secundario
//            String codSecundario = d.getCodSec();
//            s.addCell(new Label(C, fila, codSecundario, izq10Calibri));
            
            // 3. Descripción
            String equipo = d.getEquipo();
            String marca = d.getMarca();
            String modelo_de_marca = d.getModelo();
            String descripcion = d.getDescripcion();
            String descripcion_completa= equipo +' ' +marca+' ' + modelo_de_marca +' ' +descripcion;
            System.out.println("descripcion completa:" + equipo +' ' +marca+' ' + modelo_de_marca +' ' +descripcion);
            String modelo = d.getDescrModelo();
            System.out.println("Descr modelo:" + modelo);
            //String descripcion_modelo = descripcion + " " + modelo;
            String descripcion_modelo = descripcion_completa + " " + modelo;
            //s.addCell(new Label(D, fila, descripcion_modelo, izq10Calibri));
            
            String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion_modelo, 50)/* + " " + aplicacion*/;
                
                if(descripcion_modelo.length() > 50 && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(D, filaDescripcion, descripcionPorPartes[i], izq10Calibri));
                        //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    //contFilas =i-1;
                } else {s.addCell(new Label(D, fila, descripcion_modelo, izq10Calibri));  }// DESCRIPCIÓN
            
            
            // 4. Cantidad
            String cantidad = d.getCantidad();
            s.addCell(new Label(F, fila, cantidad, der10Calibri));
            
            // 5. Precio Lista
            String precioLista = d.getPrecioLista();
            s.addCell(new Label(G, fila, precioLista, der10Calibri));
            
            // 6. Total
            String total = d.getTotal();
            s.addCell(new Label(H, fila, total, der10Calibri));
            
//            String descrModelo = d.getRepuestos().getDescrmodelo();
//            if ( d.getRepuestos().getDescrmodelo() == null ) {
//                descrModelo = "";
//            }
//            descripcion = descripcion + " " + descrModelo;
//            String anotacion1 = d.getRepuestos().getDesclista();
//            if ( d.getRepuestos().getDesclista() == null ) {
//                conAnotacion = false;
//            } else {
//                conAnotacion = true;
//                s.addCell(new Label(F, (fila + 1), anotacion1, izq11AN));
//            }
            
//            double st = Double.parseDouble(subTotal);
//            int cant = d.getCantentregada();
//            double importe = (double) st/cant;
//
//            String imp = util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(importe)));
//            s.addCell(new Label(L, fila, formatearMonetario(imp, 2), der11V));
//
//            // 5. IMPORTE --Valor de Venta (Sub-Total)
//            String subT = util.formatearComaMillar(subTotal);
//            s.addCell(new Label(N, fila, formatearMonetario(subT, 2), der11V));
//            
//            if ( conAnotacion ) {
//                fila = fila + 2;
//                
//            } else {
//                fila = fila + 1;
//            }
//            System.out.println("fila-8  :" + (fila - 9));
//            System.out.println("numDetalles   :" + numDetalles);
            if ( fila - 9 == numDetalles ) {
                s.addCell(new Label(B, fila, nroParte, izqFinDetalle));
//                s.addCell(new Label(C, fila, codSecundario, izqFinDetalle));
                s.addCell(new Label(C, fila, "", izqFinDetalle));
                //String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion_modelo, 50)/* + " " + aplicacion*/;
                
                if(descripcion_modelo.length() > 50 && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(D, filaDescripcion, descripcionPorPartes[i], izqFinDetalle));
                        //hoja.addCell(new Label(D, fila+1, descripcionPartes[1], izq11AN));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    //contFilas =i-1;
                } else {s.addCell(new Label(D, fila, descripcion_modelo, izqFinDetalle));  }// DESCRIPCIÓN
                //s.addCell(new Label(D, fila, descripcion_modelo, izqFinDetalle));
                s.addCell(new Label(EE, fila, "", izqFinDetalle));
                s.addCell(new Label(F, fila, cantidad, derFinDetalle));
                s.addCell(new Label(G, fila, precioLista, derFinDetalle));
                s.addCell(new Label(H, fila, total, derFinDetalle));
            }
            fila++;
        }
        
// III. Pie del Excel
        String glosa = String.valueOf(parametros.get("glosa"));
        String total = String.valueOf(parametros.get("total"));
        
        // TOTAL
        s.addCell(new Label(G, fila, "TOTAL", der10NegritaCalibri));
        s.addCell(new Label(H, fila, glosa + " " + util.formatearComaMillar(total), der10NegritaCalibri));
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Factura exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void exportarCabecDetIngXAnulacion(Map<String, Object> parametros, ArrayList<DetalleAnulacion> lista) 
                                              throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        System.out.println("expoooort ing. x anulacion");
        System.out.println("N°:" + table.getRowCount());
        System.out.println("it:" + it);
        if ( table.getRowCount() > 0 ) {
//            String nroSerie = String.valueOf(parametros.get("nroserie"));
//            String nroDoc = String.valueOf(parametros.get("nroDoc"));
            
            String nroIngXAnulacion = String.valueOf(parametros.get("nroIngreso"));
            String rutaProgramas = String.valueOf(parametros.get("rutaProgramas"));
            
            String nameDoc = "IngXAnulacion";
            String ruta = rutaProgramas + "\\" + nameDoc + "_" + nroIngXAnulacion;
            File file = new File(ruta.replace("/", "\\"));
            
            String nombreLibro = "ZLibroIXA"; // Ingreso por Anulación
            String rutaLibroMargen0 = rutaProgramas + "\\" + nombreLibro + ".xls";
            
            String rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroIngXAnulacion + ".xls";
//            rutaTemplate = rutaProgramas + "\\" + nameDoc + "_" + nroDoc + ".xls";
            
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");
            rutaTemplate = rutaTemplate.replace("/", "\\");
            exportarCabDetIngXAnulacion(file, parametros, lista, rutaLibroMargen0, rutaTemplate);
                
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 9.INGRESOS POR ANULACIÓN - EXCEL
    public void exportarCabDetIngXAnulacion(File file, Map<String, Object> parametros, ArrayList<DetalleAnulacion> lista,
                                            String rutaLibroMargen0, String rutaTemplate)
                                            throws FileNotFoundException, IOException, WriteException, BiffException, FontFormatException {
        
        System.out.println("exportar INGRESO POR ANULACION");
        // Uso de plantilla Libro1.xls para margen izquierdo y derecho = 0
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        // Para título del excel
        WritableFont font20NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 20, WritableFont.BOLD);
        WritableCellFormat cen20NormalCalibri = new WritableCellFormat(font20NegritaCalibri);
        cen20NormalCalibri.setAlignment(Alignment.CENTRE);
        
        // Para etiquetas
        WritableFont font10NegritaCalibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.BOLD);
        WritableFont font10Calibri = new WritableFont(WritableFont.createFont("Calibri"), 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        izq10NegritaCalibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        der10NegritaCalibri.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10NegritaCalibri = new WritableCellFormat(font10NegritaCalibri);
        cen10NegritaCalibri.setAlignment(Alignment.CENTRE);
        
        // Para valores
        WritableCellFormat izq10Calibri = new WritableCellFormat(font10Calibri);
        izq10Calibri.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Calibri = new WritableCellFormat(font10Calibri);
        der10Calibri.setAlignment(Alignment.RIGHT);
        
        // Para línea que separa el último Detalle del Total
        WritableCellFormat izqFinDetalle = new WritableCellFormat(izq10Calibri);
        WritableCellFormat derFinDetalle = new WritableCellFormat(der10Calibri);
        
        izqFinDetalle.setAlignment(Alignment.LEFT);
        izqFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
        derFinDetalle.setAlignment(Alignment.RIGHT);
        derFinDetalle.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
        
// I. CABECERA DEL INGRESO POR ANULACIÓN
        
        int fila = 0;
        // 1. Título
        s.addCell(new Label(A, 1, "INGRESOS AL ALMACÉN", cen20NormalCalibri));
        fila = FILA_INI_DET_IXA;
        
        // 2. Empresa
        s.addCell(new Label(B, 2, "Empresa", izq10NegritaCalibri));
        String empresa = String.valueOf(parametros.get("empresa"));
        s.addCell(new Label(D, 2, empresa, izq10Calibri));
        
        // 3. Tipo de Documento
        String tipoDoc = String.valueOf(parametros.get("tipoDoc"));
        String nroDoc = String.valueOf(parametros.get("nroDoc"));
        String tipoDocumento = tipoDoc + "-" + nroDoc;
        s.addCell(new Label(B, 3, "Tipo de Documento", izq10NegritaCalibri));
        s.addCell(new Label(D, 3, tipoDocumento, izq10Calibri));
        
        // 4. Tipo de Transacción
        s.addCell(new Label(B, 4, "Tipo de Transacción", izq10NegritaCalibri));
        String tipoTra = String.valueOf(parametros.get("transaccion"));
        s.addCell(new Label(D, 4, tipoTra, izq10Calibri));
        
        // 5. Documento
        s.addCell(new Label(B, 5, "Documento", izq10NegritaCalibri));
//        String nroSerie = String.valueOf(parametros.get("nroserie"));
//        String numFact = nroSerie + "-" + nroDoc;
//        s.addCell(new Label(D, 5, numFact, izq10Calibri));
        String nroIngreso = String.valueOf(parametros.get("nroIngreso"));
        s.addCell(new Label(D, 5, nroIngreso, izq10Calibri));
        
        // 6. Motivo
        s.addCell(new Label(B, 6, "Motivo", izq10NegritaCalibri));
        String motivo = String.valueOf(parametros.get("motivo"));
        s.addCell(new Label(D, 6, motivo, izq10Calibri));
        
        // 7. Fecha de emisión
        String fechaEmision = String.valueOf(parametros.get("femision"));
        s.addCell(new Label(B, 7, "Fecha de Emisión", izq10NegritaCalibri));
        s.addCell(new Label(D, 7, fechaEmision, izq10Calibri));
        
        // 8. Fecha y Hora
        Date fechaHora = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(fechaHora);
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fechaHora);
        String fechayHora = fecha + " - " + hora;
        System.out.println("fecha y hora XD:" + fechayHora);
        s.addCell(new Label(F, 8, "Fecha y Hora", izq10NegritaCalibri));
        s.addCell(new Label(H, 8, fechayHora, der10Calibri));
        
// II. DETALLE DEL INGRESO POR ANULACIÓN
        
//        System.out.println(" fila:" + fila);
        Util util = new Util();
//        int filaTblFact = 0;
//        boolean conAnotacion = true;
//        
        // Cabecera de Ingreso por Anulación
        s.addCell(new Label(B, fila, "Nro. de Parte", izq10NegritaCalibri));
//        s.addCell(new Label(C, fila, "Cod. Secundario", izq10NegritaCalibri));
        s.addCell(new Label(D, fila, "Descripción", cen10NegritaCalibri));
        s.addCell(new Label(F, fila, "Cantidad", der10NegritaCalibri));
        s.addCell(new Label(G, fila, "P.U.", der10NegritaCalibri));
        s.addCell(new Label(H, fila, "Total", der10NegritaCalibri));
        
        int numDetalles = lista.size();
        fila++;
        
        for ( DetalleAnulacion da : lista ) {
            // 1. N° de Parte
            String nroParte = da.getNroParte();
            s.addCell(new Label(B, fila, nroParte, izq10Calibri));
            
            // 2. Cod. Secundario
//            String codSecundario = d.getCodSec();
//            s.addCell(new Label(C, fila, codSecundario, izq10Calibri));
            
            // 3. Descripción
            String descripcion = da.getDescripcion();
            System.out.println("descripcion:" + descripcion);
            String modelo = da.getModelo();
            System.out.println("modelo:" + modelo);
            String descripcion_modelo = descripcion + " " + modelo;
            s.addCell(new Label(D, fila, descripcion_modelo, izq10Calibri));
            
            // 4. Cantidad
            String cantidad = da.getCantidad();
            s.addCell(new Label(F, fila, cantidad, der10Calibri));
            
            // 5. Precio Unitario
            String precioUnit = da.getPrecioLista();
            s.addCell(new Label(G, fila, precioUnit, der10Calibri));
            
            // 6. Total
            String total = da.getTotal();
            s.addCell(new Label(H, fila, total, der10Calibri));
            ///////////////////////////////////////////////////////////////////
            
//            String descrModelo = d.getRepuestos().getDescrmodelo();
//            if ( d.getRepuestos().getDescrmodelo() == null ) {
//                descrModelo = "";
//            }
//            descripcion = descripcion + " " + descrModelo;
//            String anotacion1 = d.getRepuestos().getDesclista();
//            if ( d.getRepuestos().getDesclista() == null ) {
//                conAnotacion = false;
//            } else {
//                conAnotacion = true;
//                s.addCell(new Label(F, (fila + 1), anotacion1, izq11AN));
//            }
            
//            double st = Double.parseDouble(subTotal);
//            int cant = d.getCantentregada();
//            double importe = (double) st/cant;
//
//            String imp = util.formatearComaMillar(String.valueOf(util.Redondear2Decimales(importe)));
//            s.addCell(new Label(L, fila, formatearMonetario(imp, 2), der11V));
//
//            // 5. IMPORTE --Valor de Venta (Sub-Total)
//            String subT = util.formatearComaMillar(subTotal);
//            s.addCell(new Label(N, fila, formatearMonetario(subT, 2), der11V));
//            
//            if ( conAnotacion ) {
//                fila = fila + 2;
//                
//            } else {
//                fila = fila + 1;
//            }
            ///////////////////////////////////////////////////////////////////
            
//            System.out.println("fila-8  :" + (fila - 9));
//            System.out.println("numDetalles   :" + numDetalles);
            if ( fila - FILA_INI_DET_IXA == numDetalles ) {
                s.addCell(new Label(B, fila, nroParte, izqFinDetalle));
//                s.addCell(new Label(C, fila, codSecundario, izqFinDetalle));
                s.addCell(new Label(C, fila, "", izqFinDetalle));
                s.addCell(new Label(D, fila, descripcion_modelo, izqFinDetalle));
                s.addCell(new Label(EE, fila, "", izqFinDetalle));
                s.addCell(new Label(F, fila, cantidad, derFinDetalle));
                s.addCell(new Label(G, fila, precioUnit, derFinDetalle));
                s.addCell(new Label(H, fila, total, derFinDetalle));
            }
            fila++;
        }
        
// III. Pie del Excel
        String glosa = String.valueOf(parametros.get("glosa"));
        String total = String.valueOf(parametros.get("total"));
        
        // TOTAL
        s.addCell(new Label(G, fila, "TOTAL", der10Calibri));
        s.addCell(new Label(H, fila, glosa + " " + util.formatearComaMillar(total), der10Calibri));
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
//        System.out.println("Factura exportada en Excel correctamente XD.");
//        JOptionPane.showMessageDialog(it, "Tabla exportada con éxito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private int agregarLineasVaciasExcelFact(ArrayList<Detallees> detalle, WritableSheet s, WritableCellFormat formato, int fila) throws WriteException {
        int numRepDet = detalle.size();
        int numLineasExcelVacias = 10;
        if ( numRepDet > 0 ) {
            numLineasExcelVacias = MAX_REP_DET - numRepDet;
        }
        for ( int i = 0; i < numLineasExcelVacias; i++ ) {
            fila++;
            s.addCell(new Label(A, fila, "", formato));
        }
        return fila;
    }
    
    private int agregarLineasVaciasExcelNC(ArrayList<Detallenota> detalle, WritableSheet s, WritableCellFormat formato, int fila) throws WriteException {
        int numRepDet = detalle.size();
        int numLineasExcelVacias = MAX_REP_DET;
        if ( numRepDet > 0 ) {
            numLineasExcelVacias = MAX_REP_DET - numRepDet;
        }
        for ( int i = 0; i < numLineasExcelVacias; i++ ) {
            fila++;
            s.addCell(new Label(A, fila, "", formato));
        }
        return fila;
    }
        
    //cambia de truncarCaracteres a agregar saltos de Linea
    private String[] agregarSaltoLineaCaracteres(String descripcion, int maxCaracteres) {

        
        /*if ( descripcion.length() > maxCaracteres ) {
            descripcion = descripcion.substring(0, maxCaracteres);
        }*/
        StringBuilder sb = new StringBuilder(descripcion);

        int i = 0;
        while ((i = sb.indexOf(" ", i + maxCaracteres)) != -1) {
            sb.replace(i, i + 1, " \n");
        }
        
        String[] descripcionPartes = sb.toString().split("\n");
        System.out.print(descripcionPartes);
        return descripcionPartes;
    }
    
    private double obtenerDscto(int filaTabla, int columnaTabla) {
        double dscto = 0.00;
        String d = String.valueOf(table.getValueAt(filaTabla, columnaTabla)).trim();
        
        if ( !"".equals(d) ) {
            dscto = Double.parseDouble(d);
        }
        return dscto;
    }
    
    private void agregarVVconDsctoAExcel(double vv, int filaTabla, int filaExcel, int columnaExcel, WritableSheet s, WritableCellFormat fDetalle) {
        
        double dscto1 = obtenerDscto(filaTabla, 13);
        double dscto2 = obtenerDscto(filaTabla, 14);
        double dscto3 = obtenerDscto(filaTabla, 15);
        double dscto4 = obtenerDscto(filaTabla, 16);
        
        double valorVentaDscto = ( ( 100.00 - dscto1 ) / 100.00 ) * 
                                 ( ( 100.00 - dscto2 ) / 100.00 ) *
                                 ( ( 100.00 - dscto3 ) / 100.00 ) * 
                                 ( ( 100.00 - dscto4 ) / 100.00 ) * vv;
        
        double vvDscto = new Util().Redondear2Decimales(Double.parseDouble(String.valueOf(valorVentaDscto)));
//        String vvd = formatearMonetario(String.valueOf(vvDscto), 2);
        String vvd = formatearMonetario(String.valueOf(vv), 2);
        
        try {
            if ( vvd.equals("null") ) {
                s.addCell(new Label(columnaExcel, filaExcel, "", fDetalle));

            } else {
                s.addCell(new Label(columnaExcel, filaExcel, vvd, fDetalle));
            }
        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
    }
    
    private String concatenaDescuentos(int fila, int columna, String descuentos) {
        String dscto = String.valueOf(table.getValueAt(fila, columna)).trim();
        
        if ( !"".equals(dscto) ) {
            if ( columna == 10 ) {
                descuentos = dscto;
            } else {
                descuentos = descuentos + "+" + dscto;
            }
        }
        return descuentos;
    }
    
    private String getDescuentosExcel(int fila) {
        String descuentos = concatenaDescuentos(fila, 13, "");
        descuentos = concatenaDescuentos(fila, 14, descuentos);
        descuentos = concatenaDescuentos(fila, 15, descuentos);
//      descuentos = concatenaDescuentos(fila, 13, descuentos);
        return descuentos;
    }
    
    private double agregarVVUnitAExcel(int filaTabla, int columnaTabla, int filaExcel, int columnaExcel, WritableSheet s, WritableCellFormat fDetalle) {
        
        double igv = new Servicio_Documentos().getIGV();
//      double factor = ( 100.00 + igv ) / 100.00;
        double factor = ( 100.00 ) / 100.00;
            
        Object objeto = table.getValueAt(filaTabla, columnaTabla);
        double vvUnit = new Util().Redondear2Decimales(Double.parseDouble(String.valueOf(objeto)) / factor);
        String vvu = formatearMonetario(String.valueOf(vvUnit), 2);        
        try {
            if ( vvu.equals("null") ) {
                s.addCell(new Label(columnaExcel, filaExcel, "", fDetalle));

            } else {
                s.addCell(new Label(columnaExcel, filaExcel, vvu, fDetalle));
            }
        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
        return Double.parseDouble(vvu);
    }
    
    private double agregarVVAExcel( double valorVentaUnitario, int filaTabla, int filaExcel, int columnaExcel, WritableSheet s, WritableCellFormat fDetalle ) {
        
        int cantidad = Integer.parseInt(String.valueOf(table.getValueAt(filaTabla, 8)));
        double valorVenta = new Util().Redondear2Decimales(valorVentaUnitario * cantidad);
        String vv = formatearMonetario(String.valueOf(valorVentaUnitario), 2);
        
        try {
            if ( vv.equals("null") ) {
                s.addCell(new Label(columnaExcel, filaExcel, "", fDetalle));

            } else {
                s.addCell(new Label(columnaExcel, filaExcel, vv, fDetalle));
            }
        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
        agregarFilaAExcel(vv, filaExcel, 6, s, fDetalle);
        return Double.parseDouble(vv);
    }
    
    private void agregarFilaAExcel(int filaTabla, int columnaTabla, int filaExcel, int columnaExcel, 
                                   WritableSheet s, WritableCellFormat fDetalle) {
        
        Object objeto = table.getValueAt(filaTabla, columnaTabla);
        try {
            if ( String.valueOf(objeto).equals("null") ) {
                s.addCell(new Label(columnaExcel, filaExcel, "", fDetalle));

            } else {
                s.addCell(new Label(columnaExcel, filaExcel, String.valueOf(objeto), fDetalle));
            }
        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
    }
    
    private void agregarFilaAExcel(String valorTabla, int filaExcel, int columnaExcel, WritableSheet s, WritableCellFormat fDetalle) {
        
        try {
            if ( valorTabla.equals("null") ) {
                s.addCell(new Label(columnaExcel, filaExcel, "", fDetalle));

            } else {
                s.addCell(new Label(columnaExcel, filaExcel, valorTabla, fDetalle));
            }
        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
        }
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
    
    // GUÍA DE REMISIÓN
    public void exportarCabecDetGuiaRemision(DatosGR guia, ArrayList<Detallees> detalle)
            throws FileNotFoundException, BiffException {
        
        if ( table.getRowCount() > 0 ) {
            try {
                String nroSerie = guia.getNroSerie();
                String nroDoc = guia.getNroDocumento();
                
                int numeroSerie = Integer.parseInt(nroSerie);
                String ruta = guia.getRutaProgramas() + "\\GuiaRemision" + numeroSerie + "_" + nroDoc;
                ruta = ruta.replace("/", "\\");

                File file = new File(ruta);
                String rutaLibroMargen0 = guia.getRutaProgramas() + "\\ZLibroGR.xls";
                rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

//                System.out.println("N° Serie(GR):" + numeroSerie);
                String rutaProgramas = guia.getRutaProgramas();
                String rutaTemplate = rutaProgramas + "\\GuiaRemision_" + numeroSerie + "_" + nroDoc + ".xls";
                rutaTemplate = rutaTemplate.replace("/", "\\");
                // Para cualquier otro usuario
                exportarCabDetGuiaRemision(file, guia, detalle, rutaLibroMargen0, rutaTemplate, null);
                
                String nameDocCarta = "GuiaRemisionC";
                String rutaCarta = rutaProgramas + "\\" + nameDocCarta + "_" + numeroSerie + "_" + nroDoc;
                File fileCarta = new File(rutaCarta.replace("/", "\\"));
                
                String nombreLibroCarta = "ZLibroGRCarta"; // Factura Tamaño CARTA
                String rutaLibroMargen0Carta = rutaProgramas + "\\" + nombreLibroCarta + ".xls";
                String rutaTemplateCarta = rutaProgramas + "\\GuiaRemisionC_" + numeroSerie + "_" + nroDoc + ".xls";
                rutaLibroMargen0Carta = rutaLibroMargen0Carta.replace("/", "\\");
                rutaTemplateCarta = rutaTemplateCarta.replace("/", "\\");
                // Para el usuario Lucy
                exportarCabDetGuiaRemision(fileCarta, guia, detalle, rutaLibroMargen0Carta, rutaTemplateCarta, USUARIO_LUCY);
                
            } catch (WriteException e) {
                System.out.println("Excepcion:" + e.getMessage());
                JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                
            } catch (IOException ex) {
                System.out.println("Excepcion:" + ex.getMessage());
                JOptionPane.showMessageDialog(it, "Error al guardar guía remisión excel, configure la Ruta de Programas \n"
                                            + "a '" + guia.getRutaProgramas() + "'", 
                                            "Exportación Automática de Guía de Remisión", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 5. GUIA DE REMISION EXCEL
    public void exportarCabDetGuiaRemision(File file, DatosGR guia, ArrayList<Detallees> det, 
                                           String rutaLibroMargen0, String rutaTemplate,
                                           String usuario) 
            throws FileNotFoundException, IOException, WriteException, BiffException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0));
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook);
        System.out.println("rutaTemplate:" + rutaTemplate);
        WritableSheet s = w.getSheet(0);
//        s.setPageSetup(PageOrientation.PORTRAIT, 0.00, 0.00);
        
//        int tamanyo = 11;
        WritableFont font12Normal = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableFont font11Normal = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
        WritableFont font7Normal = new WritableFont(WritableFont.ARIAL, 7, WritableFont.NO_BOLD);
        
        WritableFont font10Verdana = new WritableFont(WritableFont.createFont("Verdana"), 12, WritableFont.NO_BOLD);
        
        WritableCellFormat der11Normal = new WritableCellFormat(font11Normal);
        der11Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat izq12Normal = new WritableCellFormat(font12Normal);
        izq12Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq11Normal = new WritableCellFormat(font11Normal);
        izq11Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq10Verdana = new WritableCellFormat(font10Verdana);
        izq10Verdana.setAlignment(Alignment.LEFT);
        
        WritableCellFormat cent10Verdana = new WritableCellFormat(font10Verdana);
        cent10Verdana.setAlignment(Alignment.CENTRE);
        
        WritableCellFormat izq9Normal = new WritableCellFormat(font9Normal);
        izq9Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq8Normal = new WritableCellFormat(font8Normal);
        izq8Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat izq7Normal = new WritableCellFormat(font7Normal);
        izq7Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Normal = new WritableCellFormat(font10Normal);
        der10Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cent11Normal = new WritableCellFormat(font11Normal);
        cent11Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat der9Normal = new WritableCellFormat(font9Normal);
        der9Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cent10Normal = new WritableCellFormat(font10Normal);
        cent10Normal.setAlignment(Alignment.CENTRE);
        cent10Normal.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat cent9Normal = new WritableCellFormat(font9Normal);
        cent9Normal.setAlignment(Alignment.CENTRE);
        cent9Normal.setBorder(Border.ALL, BorderLineStyle.NONE);
        
// 1. Cabecera de Guía de Remisión
        
        // Fecha de Emisión de la Guía de Remisión
        String nroDia = guia.getDia();
        String nroMes = getNroMes2Digitos(guia.getMesLetras());
        String nroAño = guia.getAño();
        String fechaEmisionGR = nroDia + "/" + nroMes + "/" + nroAño;
//        s.addCell(new Label(I, 7, guia.getDia(), izq10Normal)); // dia
//        s.addCell(new Label(J, 7, guia.getMesLetras(), izq10Normal)); // Mes (letras)
//        s.addCell(new Label(K, 7, guia.getAño(), der10Normal)); // año (4 digitos)
//        s.addCell(new Label(EE, 10, fechaEmisionGR, izq10Normal)); // formato 05/09/2016
//        s.addCell(new Label(EE, 10, fechaEmisionGR, der10Normal)); // formato 05/09/2016
        
        // Fecha de Inicio de Traslado (El cliente consideró que fuese la misma fecha que la de Emisión)
        String fechaIniTraslado = fechaEmisionGR;
//        s.addCell(new Label(I, 10, fechaIniTraslado, izq10Normal)); // formato 05/09/2016
        s.addCell(new Label(H, 11, fechaIniTraslado, izq10Normal)); // formato 05/09/2016
        
        // Dirección del Punto de partida (Generalmente coincide con la dirección de la Empresa que adquirió el SIAR)
        String dirPartida = guia.getDireccionEmpresaPartida();
        
        if ( dirPartida.length() > 43 ) {
            String dirLinea1 = dirPartida.substring(0, 44);
            String dirLinea2 = dirPartida.substring(44, dirPartida.length()) + ".";
            s.addCell(new Label(F, 9, dirLinea1, izq9Normal)); // DIRECCION del punto de partida (línea 1)
            s.addCell(new Label(D, 10, "    " + dirLinea2, izq9Normal)); // DIRECCION del punto de partida (línea 2)
            
        } else {
            String dirLinea1 = dirPartida.substring(0, dirPartida.length());
            s.addCell(new Label(F, 9, dirLinea1, izq9Normal)); // DIRECCION del punto de partida (línea 1)
        }
        
        // Ubigeo del Punto de Partida (Generalmente coincide con Ubigeo de la Empresa que adquirió el SIAR)
//        s.addCell(new Label(EE, 15, "  " + guia.getDistPartida(), izq9Normal)); // DISTRITO de partida
//        s.addCell(new Label(H, 15, guia.getProvPartida(), izq9Normal)); // PROVINCIA de partida
//        s.addCell(new Label(K, 15, guia.getDptoPartida(), izq9Normal)); // DEPARTAMENTO de partida
        
        // Dirección del Domicilio de Llegada (Generalmente coincide con la dirección de la Empresa cliente de DLN)
        String dirLlegada = guia.getDireccionEmpresaLlegada();
        
        if ( dirLlegada.length() > 42 ) {
            String dirLinea1 = dirLlegada.substring(0, 43);
            String dirLinea2 = dirLlegada.substring(43, dirLlegada.length()) + ".";
            s.addCell(new Label(R, 9, "  " + dirLinea1, izq9Normal)); // DIRECCION del domicilio de llegada (línea 1)
            s.addCell(new Label(O, 10, dirLinea2, izq9Normal)); // DIRECCION del domicilio de llegada (línea 2)
            
        } else {
            String dirLinea1 = dirLlegada.substring(0, dirLlegada.length());
            s.addCell(new Label(R, 9, "  " + dirLinea1, izq9Normal)); // DIRECCION del domicilio de llegada (línea 1)
        }
        
        // Ubigeo del Domicilio de Llegada (Generalmente coincide con el ubigeo de la Empresa cliente de DLN)
//        s.addCell(new Label(O, 15, guia.getDistLlegada(), izq9Normal)); // DISTRITO de llegada
//        s.addCell(new Label(N, 15, "   " + guia.getDistLlegada(), izq9Normal)); // DISTRITO de llegada
//        s.addCell(new Label(R, 15, "     " + guia.getProvLlegada(), cent9Normal)); // PROVINCIA de llegada
//        s.addCell(new Label(TT, 15, "   " + guia.getDptoLlegada(), izq9Normal)); // DEPARTAMENTO de llegada
        
        // Destinatario (Cliente)
        s.addCell(new Label(TT, 11, "       " + guia.getNombreEmpresa(), izq10Normal)); // NOMBRE O RAZON SOCIAL (Cliente)
        s.addCell(new Label(P, 13, guia.getRucEmpresa(), izq11Normal)); // R.U.C. (Cliente)
//        s.addCell(new Label(J, 13, "   " + guia.getNroFactura(), izq11Normal)); // Comprobante de Pago N° (N° de factura relacionada a la GR)
//        s.addCell(new Label(B, 13, guia.getDireccionEmpresa(), izq10Normal));    // DIRECCION (Cliente)
        
        
// 2. Detalle de Guía de Remisión
        int fila = FILA_INI_DET_GR;
        boolean conAnotacion = true;
        
        for ( Detallees d : det ) {
//            fila++;
            String cantidad = String.valueOf(d.getCantentregada());
            String unidadMedida =  (d.getRepuestos().getUnidadmedida() == null ? "" : d.getRepuestos().getUnidadmedida());
//            String modelo = ( d.getRepuestos().getDescrmodelo() == null ? "" : d.getRepuestos().getDescrmodelo() );
//            String descripcion = d.getRepuestos().getDescripcion() + " " + modelo;
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloProduc = d.getRepuestos().getModelos().getDescripcion();
            String descripcion = d.getRepuestos().getDescripcion();
            String descrModelo = d.getRepuestos().getDescrmodelo();
            if ( d.getRepuestos().getDescrmodelo() == null ) {
                descrModelo = "";
            }
//          String codRepuesto = d.getRepuestos().getCodrepuesto();
            String codRepuesto = d.getRepuestos().getCodigoseg();
//            String idRepuesto = d.getRepuestos().getCodrepuesto();
//            String codigoSecundario = ( d.getRepuestos().getCodigoseg() == null ? "" : d.getRepuestos().getCodigoseg() );
        
            // 1. COD REPUESTO
            s.addCell(new Label(D, fila, codRepuesto, izq10Verdana)); // O
            
            // 2. DESCRIPCION - MODELO
//            s.addCell(new Label(B, fila, idRepuesto, izq10Normal)); // CÓDIGO
//            descripcion = truncarCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION) + " " + descrModelo;
            descripcion = equipo+" "+marca+" "+modeloProduc+" "+descripcion + " " + descrModelo;
            s.addCell(new Label(H, fila, descripcion, izq12Normal));
            
            // 3. ANOTACIÓN 1
            // Si el repuesto tiene "anotacion1", la siguiente fila = fila + 2, sino fila = fila + 1
            String anotacion1 = d.getRepuestos().getDesclista();
//            System.out.println("fila:" + fila);
//            System.out.println("anotacion1:" + anotacion1);
            
            // 4. CANTIDAD
            s.addCell(new Label(S, fila, cantidad, cent10Verdana));
            
            if ( d.getRepuestos().getDesclista() == null ) {
                conAnotacion = false;

            } else {
                conAnotacion = true;
                s.addCell(new Label(H, (fila + 1), anotacion1, izq12Normal));
            }
            
            if ( conAnotacion ) {
                fila = fila + 2;
                
            } else {
                fila = fila + 1;
            }
            
            // 5. UNIDAD DE MEDIDA (UND)
//            s.addCell(new Label(S, fila, unidadMedida, cent10Normal));
//            System.out.println("fila NUEVA:" + fila);
        }
//        fila = agregarLineasVaciasExcelFact(det, s, izq10Normal, fila);
//        fila++;

// 3. UNIDAD DE TRANSPORTE Y CONDUCTOR
        // Marca y N° de placa
        s.addCell(new Label(H, 15, "  " + guia.getMarca() + " " + guia.getPlaca(), izq11Normal));
        
        // N° de Constancia de Inscripción
        s.addCell(new Label(I, 16, guia.getConstanciaInscripcion(), izq11Normal));
        
        // N° de Licencia de Conducir
        s.addCell(new Label(I, 17, guia.getLicenciaConducir(), izq11Normal));
        
// 4. EMPRESA DE TRANSPORTES
        String nombreTrans = guia.getNombreTransportista();
        nombreTrans = ( nombreTrans.length() > 27 ? nombreTrans.substring(0, 28) + "." : nombreTrans );
        
        // Nombre o Razón Social de la empresa de Transportes
        s.addCell(new Label(R, 15, nombreTrans, izq11Normal));
//        s.addCell(new Label(D, 10, guia.getDireccionTransportista(), izq10Normal)); // DIRECCION (Transportista)
        
        // Número de RUC de la empresa de Transportes
        s.addCell(new Label(R, 17, guia.getRucTransportista(), izq11Normal));

        int filaNroFactura = 49;
        if ( USUARIO_LUCY.equals(usuario) ) {
            filaNroFactura = 44;
        }
// 5. Pie de la Guía de Remisión (última línea de GR)
        // TIPO Y N° DE COMPROBANTE DE PAGO (Comprobante de Pago N° (N° de factura relacionada a la GR))
//        s.addCell(new Label(I, 56, "   " + guia.getNroFactura(), izq11Normal));
        s.addCell(new Label(EE, filaNroFactura, "   " + guia.getNroFactura(), izq11Normal));
        
        // ORDEN DE COMPRA
//        s.addCell(new Label(TT, 55, guia.getOrdenTransporte(), izq11Normal));
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    private String getNroMes(String mes) {
        String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String numMes = "-1";
        for ( int i = 0; i < Meses.length; i++ ) {
            if ( mes.equals(Meses[i]) ) {
                numMes = "" + (i + 1);
                break;
            }
        }
        return numMes;
    }
    
    private String getNroMes2Digitos(String mes) {
        String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String numMes = "-1";
        for ( int i = 0; i < Meses.length; i++ ) {
            if ( mes.equals(Meses[i]) ) {
                if ( i < 9 ) {
                    numMes = "0" + (i + 1);
                } else {
                    numMes = "" + (i + 1);
                }
                break;
            }
        }
        return numMes;
    }
    
    // 6.NOTA DE CRÉDITO
    public void exportarCabecDetNotaCredito(DatosNC nota, ArrayList<Detallenota> detalle)
                                            throws FileNotFoundException, BiffException, ParseException {
        if ( table.getRowCount() > 0 ) {
            try {
                String nroSerie = nota.getNroSerie();
                String nroDoc = nota.getNroDocumento();
                
                String ruta = nota.getRutaProgramas() + "\\NotaCredito_" + nroSerie + "_" + nroDoc;
                ruta = ruta.replace("/", "\\");

                File file = new File(ruta);
                String rutaLibroMargen0 = nota.getRutaProgramas() + "\\ZLibroNC.xls";
                rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

                String rutaTemplate = nota.getRutaProgramas() + "\\NotaCredito_" + nroSerie + "_" + nroDoc + ".xls";
                rutaTemplate = rutaTemplate.replace("/", "\\");
                
//                String ruta = nota.getRutaProgramas() + "\\NotaCredito_" + nroSerie + "_" + nroDoc;
//                File file = new File(ruta);
                exportarCabDetNotaCredito(file, nota, detalle, rutaLibroMargen0, rutaTemplate);
                    
            } catch (WriteException e) {
                System.out.println("Excepcion:" + e.getMessage());
                JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                
            } catch (IOException ex) {
                System.out.println("Excepcion:" + ex.getMessage());
                JOptionPane.showMessageDialog(it, "Error al guardar nota de crédito excel, configure la Ruta de Programas \n"
                                            + "a '" + nota.getRutaProgramas() + "'", 
                                            "Exportación Automática de Nota de Crédito", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 6. NOTA DE CREDITO EXCEL
    public void exportarCabDetNotaCredito(File file, DatosNC nota, ArrayList<Detallenota> det,
                                          String rutaLibroMargen0, String rutaTemplate) 
                                          throws FileNotFoundException, IOException, WriteException, BiffException, ParseException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
//        s.setPageSetup(PageOrientation.PORTRAIT, 0.00, 0.00);
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Normal = new WritableCellFormat(font10Normal);
        der10Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10Normal = new WritableCellFormat(font10Normal);
        cen10Normal.setAlignment(Alignment.CENTRE);
        cen10Normal.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        // 1. Fecha de Emisión Del Comprobante de pago que modifica
// Asignar FECHA DE EMISION de la Nota de Crédito "La Molina '08' de 'Agosto' del 201'6'"
        String fechaEmision = nota.getFechaEmision(); // formato 04/09/2016
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateEmision = formatter.parse(fechaEmision);
        String dia2Digitos = Fecha.getDia(dateEmision);
        String mes2Digitos = Fecha.getMesNumeros(dateEmision);
//        String mesLetras = Fecha.getMes(dateEmision);
        String año4Digitos = Fecha.getAnio(dateEmision);
//        String año1Digito = Fecha.getAnioUltimaCifra(dateEmision);
//        s.addCell(new Label(M, 14, dia2Digitos, der10Normal));
//        s.addCell(new Label(P, 14, mesLetras, izq10Normal));
//        s.addCell(new Label(S, 14, año1Digito, izq10Normal));
//        s.addCell(new Label(D, 7, dia2Digitos + " " + mesLetras + ", " + año4Digitos));
        String fecEmision = dia2Digitos + "/" + mes2Digitos + "/" + año4Digitos;
        
        // 2. Cliente
        s.addCell(new Label(D, 8, nota.getClienteNombre(), izq10Normal)); // Señor(es)
//        s.addCell(new Label(D, 12, nota.getClienteDireccion(), izq10Normal)); // Dirección
        
        // 3. Dirección
        s.addCell(new Label(D, 9, nota.getClienteDireccion(), izq10Normal));
        
        // 4. R.U.C.
        s.addCell(new Label(D, 10, nota.getClienteRUC(), izq10Normal));
        
        // 5. Condición de Pago
        s.addCell(new Label(D, 11, nota.getCondicionPago(), izq10Normal));
        
// Datos Comprobante (Nota Crédito)
        
        // 6. Fecha de Emisión
        // Fecha de IMPRESION "La Molina, '20' de 'Julio' de '2016'"
        String fechaImpresion = nota.getFechaNC();
        Date dateImpresion = formatter.parse(fechaImpresion);
        String dia2Dig = Fecha.getDia(dateImpresion);
        String mes2Dig = Fecha.getMesNumeros(dateImpresion);
//        String mesLetPrint = Fecha.getMes(dateImpresion);
//        String año1DigPrint = Fecha.getAnio(dateImpresion);
        String año4Dig = Fecha.getAnio(dateImpresion);
//        s.addCell(new Label(G, 29, dia2Dig, cen10Normal));          // DIA en que se cancela NC
//        s.addCell(new Label(J, 27, mesLetPrint, izq10Normal));    // MES en que se cancela NC
//        s.addCell(new Label(I, 27, mes2Dig, izq10Normal));          // MES en que se cancela NC
//        s.addCell(new Label(I, 29, mesLetPrint, izq10Normal));          // MES en que se cancela NC
//        s.addCell(new Label(M, 27, "   " + año1DigPrint, izq10Normal));  // AÑO en que se cancela NC
//        s.addCell(new Label(K, 29, año4Dig, izq10Normal));          // AÑO en que se cancela NC
        String fechaPrintCompleta = dia2Dig + "/" + mes2Dig + "/" + año4Dig;
        
        // Anterior incorrecto
//        s.addCell(new Label(D, 7, fecEmision));
//        s.addCell(new Label(K, 11, fechaPrintCompleta, cen10Normal));
        
        // Correcto:
        s.addCell(new Label(D, 7, fechaPrintCompleta));
        s.addCell(new Label(K, 11, fecEmision, cen10Normal));
        
        // 7. N° de Factura
//        String tipoDocumento = nota.getTipoDocumento().toUpperCase();
        int modalidadNC = nota.getModalidadNC();
        
        if ( modalidadNC == NC_DEVOLUCION || modalidadNC == ND_MOD_UNICA ) {
            String nroDocumento = nota.getNroFactura();
//        s.addCell(new Label(O, 9, tipoDocumento, izq10Normal)); //Denominaciòn (Documento)
            s.addCell(new Label(O, 11, nroDocumento, der10Normal)); //Denominaciòn (N° de factura relacionado a NC)
        }
        
// Detalle de Nota de Crédito
        Util util = new Util();
        int fila = FILA_INI_DET_NC;
        int contFilas = 0;
        
        for ( Detallenota d : det ) {
            fila++;
            fila = fila + contFilas;
            contFilas = 0;
            
            String cantidad = String.valueOf(d.getCantidad());
            String modelo = ( d.getRepuestos().getDescrmodelo() == null ? "" : d.getRepuestos().getDescrmodelo());
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloEquip = d.getRepuestos().getModelos().getDescripcion();
            String descripcion = d.getDescripcion();
            descripcion = equipo+" "+marca+" "+modeloEquip+" "+descripcion;
            String importe = String.valueOf(d.getValor());
            String pu = String.valueOf(util.Redondear2Decimales(Double.parseDouble(importe)/Integer.parseInt(cantidad)));
            String precUnitario = formatearMonetario(pu, 2);
//            System.out.println("importe:" + importe);
//            System.out.println("pu:" + pu);
//            System.out.println("precioUnitario:" + precUnitario);

            if ( d.getCantidad() > 0 ) {
//                System.out.println("cantidad > 0");
//                if ( d.getRepuestos() != null ) {
                    String aplicacion = d.getRepuestos().getAplicacion();
                    if ( aplicacion == null ) {
                        aplicacion = "";
                    }
                    String codRepuesto = d.getRepuestos().getCodrepuesto();
//                }
                s.addCell(new Label(B, fila, codRepuesto, izq10Normal)); // CODIGO
                s.addCell(new Label(EE, fila, cantidad, izq10Normal)); // CANTIDAD
//                s.addCell(new Label(C, fila, idRepuesto, fDetIzq)); // CÓDIGO
                
                descripcion = descripcion+ " " + aplicacion; 
                String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION)/* + " " + aplicacion*/;
                if(descripcion.length() > MAX_CARACTERES_DESCRIPCION && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(D, filaDescripcion, descripcionPorPartes[i], izq10Normal));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    contFilas =i-1;
                } else {s.addCell(new Label(B, fila, descripcion, izq10Normal));  }// DESCRIPCIÓN
                //s.addCell(new Label(EE, fila, descripcion, izq10Normal)); // DESCRIPCIÓN
                
                
                /*descripcion = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION) + " " + aplicacion;
                s.addCell(new Label(F, fila, descripcion, izq10Normal)); // DESCRIPCIÓN*/
                
                
                
                s.addCell(new Label(L, fila, util.formatearComaMillar(Monetario.formatearMonetario(precUnitario, 2)), der10Normal)); // PRECIO UNITARIO
                s.addCell(new Label(O, fila, util.formatearComaMillar(Monetario.formatearMonetario(importe, 2)), der10Normal)); // IMPORTE
                
            } else {
//                System.out.println("cantidad <= 0");
                String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION)/* + " " + aplicacion*/;
                if(descripcion.length() > MAX_CARACTERES_DESCRIPCION && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(F, filaDescripcion, descripcionPorPartes[i], izq10Normal));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    contFilas =i-1;
                } else {s.addCell(new Label(F, fila, descripcion, izq10Normal));  }// DESCRIPCIÓN
                //descripcion = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION);
                
                
                //s.addCell(new Label(F, fila, descripcion, izq10Normal)); // DESCRIPCIÓN
                s.addCell(new Label(O, fila, util.formatearComaMillar(Monetario.formatearMonetario(importe, 2)), der10Normal)); // IMPORTE
            }
        }
        
        // MOTIVOS DE LA MODIFICACIÓN
        s.addCell(new Label(F, 23, nota.getConcepto().toUpperCase(), izq10Normal));
        
//        fila = agregarLineasVaciasExcelNC(det, s, izq10Normal, fila);
        // ___________________
//        s.addCell(new Label(C, fila, nota.getTotalEnLetras(), izq10Normal)); // SON:
        
        // SUB TOTAL
        s.addCell(new Label(G, 24, nota.getMoneda() + " " + util.formatearComaMillar(nota.getSubtotal()), izq10Normal)); // SUB-TOTAL
        
        // I.G.V.
//        s.addCell(new Label(M, 25, nota.getImpuesto() + "%", der10Normal)); // I.G.V. ___%
        s.addCell(new Label(J, 24, "       " + nota.getImpuesto() + "%", izq10Normal)); // I.G.V. ___%
        s.addCell(new Label(K, 24, nota.getMoneda() + " " + util.formatearComaMillar(Monetario.formatearMonetario(nota.getIgv(),2)), izq10Normal)); // IGV (MONTO)
        
        // TOTAL FACTURA
        s.addCell(new Label(O, 24, nota.getMoneda() + " " + util.formatearComaMillar(Monetario.formatearMonetario(nota.getTotal(), 2)), der10Normal)); // TOTAL
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    // 7. NOTA DE DÉBITO
    public void exportarCabecDetNotaDebito(DatosND nota, ArrayList<Detallenota> detalle)
                                           throws FileNotFoundException, BiffException, ParseException {
        if ( table.getRowCount() > 0 ) {
            try {
                String nroSerieND = nota.getNroSerie();
                String nroDocND = nota.getNumeroND();
                
                String ruta = nota.getRutaProgramas() + "\\NotaDebito_" + nroSerieND + "_" + nroDocND;
                ruta = ruta.replace("/", "\\");

                File file = new File(ruta);
                String rutaLibroMargen0 = nota.getRutaProgramas() + "\\ZLibroND.xls";
                rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

                String rutaTemplate = nota.getRutaProgramas() + "\\NotaDebito_" + nroSerieND + "_" + nroDocND + ".xls";
                rutaTemplate = rutaTemplate.replace("/", "\\");
                
//                String ruta = nota.getRutaProgramas() + "\\NotaCredito_" + nroSerie + "_" + nroDoc;
//                File file = new File(ruta);
                exportarCabDetNotaDebito(file, nota, detalle, rutaLibroMargen0, rutaTemplate);
                    
            } catch ( WriteException e ) {
//                System.out.println("Excepcion:" + e.getMessage());
                JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                
            } catch ( IOException ex ) {
//                System.out.println("Excepcion:" + ex.getMessage());
                JOptionPane.showMessageDialog(it, "Error al guardar nota de débito excel, configure la Ruta de Programas \n"
                                            + "a '" + nota.getRutaProgramas() + "'", 
                                            "Exportación Automática de Nota de Débito", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(it, "La Tabla está vacía", "Vacía", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 7. NOTA DE DÉBITO EXCEL
    public void exportarCabDetNotaDebito(File file, DatosND nota, ArrayList<Detallenota> det,
                                         String rutaLibroMargen0, String rutaTemplate) 
                                         throws FileNotFoundException, IOException, WriteException, BiffException, ParseException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
//        s.setPageSetup(PageOrientation.PORTRAIT, 0.00, 0.00);
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        
        WritableCellFormat izq10Normal = new WritableCellFormat(font10Normal);
        izq10Normal.setAlignment(Alignment.LEFT);
        
        WritableCellFormat der10Normal = new WritableCellFormat(font10Normal);
        der10Normal.setAlignment(Alignment.RIGHT);
        
        WritableCellFormat cen10Normal = new WritableCellFormat(font10Normal);
        cen10Normal.setAlignment(Alignment.CENTRE);
        cen10Normal.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        // Cliente
        s.addCell(new Label(D, 9, nota.getClienteNombre(), izq10Normal)); // Señor(es)
//        s.addCell(new Label(D, 12, nota.getClienteDireccion(), izq10Normal)); // Dirección
        s.addCell(new Label(D, 10, nota.getClienteRUC(), izq10Normal)); // R.U.C
        
        // Datos Comprobante (Nota Débito)
//        String tipoDoc = nota.getTipoDocumento().toUpperCase();
//        String nroDocFactura = nota.getNroDocFactura();
//        s.addCell(new Label(L, 9, tipoDoc, izq10Normal)); //Denominaciòn (Documento)
//        s.addCell(new Label(L, 10, nroDocFactura, izq10Normal)); //Denominaciòn (N° de factura relacionado a ND)
        
// Fecha de Emisión Del Comprobante de pago que modifica
// Asignar FECHA DE EMISION de la Nota de Débito "La Molina '08' de 'Agosto' del 201'6'"
        String fechaEmision = nota.getFechaEmision(); // formato 04/09/2016
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateEmision = formatter.parse(fechaEmision);
        String dia2Digitos = Fecha.getDia(dateEmision);
        String mes2Digitos = Fecha.getMesNumeros(dateEmision);
//        String mesLetras = Fecha.getMes(dateEmision);
//        String mesNumeros = Fecha.getMesNumeros(dateEmision);
        String año4Digitos = Fecha.getAnio(dateEmision);
//        String año1Digito = Fecha.getAnioUltimaCifra(dateEmision);
//        s.addCell(new Label(M, 14, dia2Digitos, der10Normal));
//        s.addCell(new Label(P, 14, mesLetras, izq10Normal));
//        s.addCell(new Label(S, 14, año1Digito, izq10Normal));
//        s.addCell(new Label(EE, 10, dia2Digitos + " " + mesLetras + ", " + año4Digitos));
        s.addCell(new Label(EE, 11, dia2Digitos + "/" + mes2Digitos + "/" + año4Digitos));
//        s.addCell(new Label(EE, 11, nota.getConcepto().toUpperCase(), izq10Normal));
        
        // Detalle de Nota de Débito
        Util util = new Util();
        int fila = FILA_INI_DET_NC;
        int contFila = 0;
        
        for ( Detallenota d : det ) {
            fila++;
            fila = fila +contFila;
            contFila =0; 
            String cantidad = String.valueOf(d.getCantidad());
            String modelo = ( d.getRepuestos().getDescrmodelo() == null ? "" : d.getRepuestos().getDescrmodelo());
            String equipo = d.getRepuestos().getEquipos().getDescripcion();
            String marca = d.getRepuestos().getMarcas().getDescripcion();
            String modeloEquip = d.getRepuestos().getModelos().getDescripcion();
            String descripcion = d.getDescripcion();
            descripcion = equipo +" "+marca+" "+modeloEquip+" "+descripcion;
                    
//            System.out.println("cantidad:" + cantidad);
//            System.out.println("descripcion:" + descripcion);
            
            String importe = String.valueOf(d.getValor());
            String pu = String.valueOf(util.Redondear2Decimales(Double.parseDouble(importe)/Integer.parseInt(cantidad)));
            String precUnitario = formatearMonetario(pu, 2);
//            System.out.println("importe:" + importe);
//            System.out.println("pu:" + pu);
//            System.out.println("precioUnitario:" + precUnitario);

            if ( d.getCantidad() > 0 ) {
//                System.out.println("cantidad > 0");
//                if ( d.getRepuestos() != null ) {
                    String aplicacion = d.getRepuestos().getAplicacion();
                    if ( aplicacion == null ) {
                        aplicacion = "";
                    }
                    String codRepuesto = d.getRepuestos().getCodrepuesto();
//                }
                s.addCell(new Label(B, fila, cantidad, der10Normal)); // CANTIDAD
                s.addCell(new Label(D, fila, codRepuesto, izq10Normal)); // CODIGO
//                s.addCell(new Label(C, fila, idRepuesto, fDetIzq)); // CÓDIGO
                
                
                descripcion = descripcion+" "+aplicacion;
                String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION)/* + " " + aplicacion*/;
                if(descripcion.length() > MAX_CARACTERES_DESCRIPCION && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(EE, filaDescripcion, descripcionPorPartes[i], izq10Normal));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    contFila =i-1;
                } else {s.addCell(new Label(EE, fila, descripcion, izq10Normal));  }// DESCRIPCIÓN
                //s.addCell(new Label(EE, fila, descripcion, izq10Normal)); // DESCRIPCIÓN
                
                
                s.addCell(new Label(M, fila, util.formatearComaMillar(Monetario.formatearMonetario(precUnitario, 2)), der10Normal)); // PRECIO UNITARIO
                s.addCell(new Label(O, fila, util.formatearComaMillar(Monetario.formatearMonetario(importe, 2)), der10Normal)); // IMPORTE
                
            } else {
//                System.out.println("cantidad <= 0");
                String[] descripcionPorPartes = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION)/* + " " + aplicacion*/;
                if(descripcion.length() > MAX_CARACTERES_DESCRIPCION && descripcionPorPartes.length > 1) { // si despcripcion es grande y si tiene saltos de linea
                    int tamañoIndexInterno=descripcionPorPartes.length;
                    int filaDescripcion = fila;
                    int i = 0;
                    while(tamañoIndexInterno > 0){
                        s.addCell(new Label(EE, filaDescripcion, descripcionPorPartes[i], izq10Normal));
                        i++;
                        filaDescripcion++;
                        tamañoIndexInterno--;
                    }
                    contFila =i-1;
                } else {s.addCell(new Label(EE, fila, descripcion, izq10Normal));  }// DESCRIPCIÓN
                //descripcion = agregarSaltoLineaCaracteres(descripcion, MAX_CARACTERES_DESCRIPCION);
                //s.addCell(new Label(EE, fila, descripcion, izq10Normal)); // DESCRIPCIÓN
                s.addCell(new Label(O, fila, util.formatearComaMillar(Monetario.formatearMonetario(importe, 2)), der10Normal)); // IMPORTE
            }
        }
//        fila = agregarLineasVaciasExcelNC(det, s, izq10Normal, fila);
        // ___________________
//        s.addCell(new Label(C, fila, nota.getTotalEnLetras(), izq10Normal)); // SON:
        s.addCell(new Label(O, 24, nota.getMoneda() + " " + util.formatearComaMillar(nota.getSubtotal()), der10Normal)); // SUB-TOTAL
        
// Fecha de IMPRESION "La Molina, '20' de 'Julio' de '2016'"
//        String fechaImpresion = nota.getFechaEmision();
//        Date dateImpresion = formatter.parse(fechaImpresion);
//        String dia2Dig = Fecha.getDia(dateImpresion);
//        
//        String mesLetPrint = Fecha.getMes(dateImpresion);
//        String año1DigPrint = Fecha.getAnio(dateImpresion);
//        String año4Dig = Fecha.getAnio(dateImpresion);
//        s.addCell(new Label(EE, 26, dia2Dig, der10Normal));  // DIA en que se cancela ND
//        s.addCell(new Label(J, 27, mesLetPrint, izq10Normal));   // MES en que se cancela ND
        
//        String mes2Dig = Fecha.getMesNumeros(dateImpresion);
//        s.addCell(new Label(I, 25, mes2Dig, izq10Normal));   // MES en que se cancela ND
//        String mesLetra = Fecha.getMes(dateImpresion);
//        s.addCell(new Label(F, 26, mesLetra, der10Normal));   // MES en que se cancela ND
//        s.addCell(new Label(I, 25, mes2Dig, izq10Normal));   // MES en que se cancela ND
//        s.addCell(new Label(M, 27, "   " + año1DigPrint, izq10Normal));  // AÑO en que se cancela ND
//        s.addCell(new Label(I, 26, año4Dig, izq10Normal));  // AÑO en que se cancela ND
        
//        s.addCell(new Label(M, 25, nota.getImpuesto() + "%", der10Normal)); // I.G.V. ___%
        s.addCell(new Label(L, 25, nota.getImpuesto() + "", cen10Normal)); // I.G.V. ___%
        s.addCell(new Label(O, 25, nota.getMoneda() + " " + util.formatearComaMillar(Monetario.formatearMonetario(nota.getIgv(),2)), der10Normal)); // IGV (MONTO)
        s.addCell(new Label(O, 26, nota.getMoneda() + " " + util.formatearComaMillar(Monetario.formatearMonetario(nota.getTotal(), 2)), der10Normal)); // TOTAL
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if (!Path.endsWith(".xls")) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    // 8. UNA LETRA
    public String exportarCabecDetLetra(ArrayList<Letra> lista, Map<String, Object> parametros, Control control) 
                                        throws FileNotFoundException, BiffException, IOException, WriteException {
        String nombreExcelLetra = "";
        String rutaProgramas = control.getRutaprogramas();
        try {
            String nroDoc = lista.get(0).getNumLetra();
            String ruta = rutaProgramas + "\\Letra_" + nroDoc; // ruta = rutaProgramas (Y:/ = unidad compartida en red creada)
            ruta = ruta.replace("/", "\\");

            File file = new File(ruta);
            String rutaLibroMargen0 = rutaProgramas + "\\ZLibroLet.xls";
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

            nombreExcelLetra = "Letra_" + nroDoc + ".xls";
            String rutaTemplate = rutaProgramas + "\\" + nombreExcelLetra;
            rutaTemplate = rutaTemplate.replace("/", "\\");
            exportarCabDetLetra(file, parametros, lista, rutaLibroMargen0, rutaTemplate);

        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
            JOptionPane.showMessageDialog(it, "Error al exportar letra", "Letra en excel", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            System.out.println("Excepcion:" + ex.getMessage());
            JOptionPane.showMessageDialog(it, "Error al guardar letra excel, configure la Ruta de Programas \n a '" + rutaProgramas + "'", 
                                              "Exportación Automática de Letra", JOptionPane.ERROR_MESSAGE);
        }
        return nombreExcelLetra;
    }
    
    // 8. UNA LETRA EXCEL
    public void exportarCabDetLetra(File file, Map<String, Object> parametros, ArrayList<Letra> lista,
                                          String rutaLibroMargen0, String rutaTemplate) 
                                          throws FileNotFoundException, IOException, WriteException, BiffException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        
        WritableCellFormat norm9IzqSinBorde = new WritableCellFormat(font9Normal);
        norm9IzqSinBorde.setAlignment(Alignment.LEFT);
        norm9IzqSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10CentSinBorde = new WritableCellFormat(font10Normal);
        norm10CentSinBorde.setAlignment(Alignment.CENTRE);
        norm10CentSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9CentSinBorde = new WritableCellFormat(font9Normal);
        norm9CentSinBorde.setAlignment(Alignment.CENTRE);
        norm9CentSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9DerSinBorde = new WritableCellFormat(font9Normal);
        norm9DerSinBorde.setAlignment(Alignment.RIGHT);
        norm9DerSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
//        WritableCellFormat norm10IzqSinBorde = new WritableCellFormat(font10Normal);
//        norm10IzqSinBorde.setAlignment(Alignment.LEFT);
//        norm10IzqSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10DerSinBorde = new WritableCellFormat(font10Normal);
        norm10DerSinBorde.setAlignment(Alignment.RIGHT);
        norm10DerSinBorde.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        int fila = FILA_INI_DET_LETRA;
        for ( Letra let : lista ) {
            fila++;
            String numLetra = let.getNumLetra();
            String letra_facbol_linea1 = let.getLetra_facbol();
            String letra_facbol_linea2 = let.getLetra_facbol2();
            String lugar_giro = let.getLugar_giro();
            
            String fechaGiro = let.getFechaGiro();
            String fechaVencimiento = let.getFechaVencimiento();
            
            String importe = let.getImporte();
            String importeEnTexto = let.getImporteEnTexto();
            String cliente = let.getCliente();
            String domicilio = let.getDomicilio();
            String localidad = let.getLocalidad();
            String ruc = let.getRuc();
            String telefijo = let.getTelefijo();

            s.addCell(new Label(D, fila, numLetra, norm10CentSinBorde)); // N° LETRA
//            s.addCell(new Label(F, fila, "                " + letra_facbol_linea1, norm9CentSinBorde)); // LETRA FACTURA_BOLETA (REFERENCIA DEL GIRADOR)
            s.addCell(new Label(F, fila, letra_facbol_linea1, norm9CentSinBorde)); // LETRA FACTURA_BOLETA (REFERENCIA DEL GIRADOR)
            s.addCell(new Label(G, fila, lugar_giro, norm9DerSinBorde)); // LUGAR DE GIRO
            s.addCell(new Label(L, fila, importe, norm10DerSinBorde)); // MONEDA E IMPORTE
            
            fila++;
//            s.addCell(new Label(F, fila, letra_facbol_linea2, norm9CentSinBorde)); // LETRA FACTURA_BOLETA2 (REFERENCIA DEL GIRADOR) --> Colocar facbol1/facbol2/fb3/fb4
            s.addCell(new Label(F, fila, letra_facbol_linea2, norm9CentSinBorde)); // LETRA FACTURA_BOLETA2 (REFERENCIA DEL GIRADOR) --> Colocar facbol1/facbol2/fb3/fb4
            // facbo5/fb6/fb7/fb8
//            System.out.println("fechaGiro:" + fechaGiro);
//            System.out.println("fechaVencimiento:" + fechaVencimiento);
//            System.out.println("fila: " + fila);
            s.addCell(new Label(I, fila, fechaGiro, norm9CentSinBorde)); // FECHA DE GIRO
            s.addCell(new Label(K, fila, fechaVencimiento, norm9CentSinBorde)); // FECHA DE VENCIMIENTO
            
            fila = fila + 2;
            s.addCell(new Label(B, fila, importeEnTexto, norm9IzqSinBorde)); // CANTIDAD DELETREADA
            
            fila = fila + 2;
            // DATOS DEL CLIENTE QUE PAGARÁ 1 LETRA
            int min = 35;
            if ( cliente.length() > min ) {
                String cliLinea1 = cliente.substring(0, min + 1);
                String cliLinea2 = cliente.substring(min + 1, cliente.length()) + ".";
                s.addCell(new Label(A, fila, "                                                        " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
                s.addCell(new Label(A, fila + 1, "                                                        " + cliLinea2, norm9IzqSinBorde)); // cliente (línea 2)

            } else {
                String cliLinea1 = cliente.substring(0, cliente.length());
                s.addCell(new Label(A, fila, "                                                        " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
            }
            
            fila = fila + 2;
            s.addCell(new Label(C,  fila, "   " + ruc, norm9IzqSinBorde)); // RUC DEL CLIENTE
            s.addCell(new Label(G,  fila, telefijo, norm9IzqSinBorde)); // TELEFONO DEL CLIENTE
            fila++;
            s.addCell(new Label(I,  fila, localidad, norm9CentSinBorde)); // LOCALIDAD DE CLIENTE
            int minDom = 35;
            
            if ( domicilio.length() > minDom ) {
                String domLinea1 = domicilio.substring(0, minDom + 1);
                String domLinea2 = domicilio.substring(minDom + 1, domicilio.length()) + ".";
                s.addCell(new Label(D, fila, domLinea1, norm9IzqSinBorde)); // DOMICILIO DE LA EMPRESA CLIENTE (línea 1)
                s.addCell(new Label(D, fila + 1, domLinea2, norm9IzqSinBorde)); // DOMICILIO DE LA EMPRESA CLIENTE (línea 2)

            } else {
                String domLinea1 = domicilio.substring(0, domicilio.length());
                s.addCell(new Label(D, fila, domLinea1, norm9IzqSinBorde)); // DOMICILIO DE LA EMPRESA CLIENTE (línea 1)
            }
//            fila++;
        }
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    // 9. IMPRIMIR REPORTE DE LETRAS(3 opciones: 1= Toda la cuenta corriente, 2= un cliente, 3= seleccionados)
    public String exportarCabecDetLetras(ArrayList<ListaLetras> lista, Map<String, Object> parametros, Control control, int opcionReporte) 
                                         throws FileNotFoundException, BiffException, IOException, WriteException {
        String nombreExcelLetras = "";
        String rutaProgramas = control.getRutaprogramas();
        try {
            String nroDoc = lista.get(0).getNumLetra();
            String fechaYHora = getStringFechayHora();
            String ruta = rutaProgramas + "\\RL_" + nroDoc + "_" + fechaYHora; // ruta = rutaProgramas (Y:/ = unidad compartida en red creada)
//            String ruta = rutaProgramas + "\\RL_" + nroDoc; // ruta = rutaProgramas (Y:/ = unidad compartida en red creada)
            ruta = ruta.replace("/", "\\");

            File file = new File(ruta);
            String rutaLibroMargen0 = rutaProgramas + "\\ZLibroREPLET.xls";
            rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

            nombreExcelLetras = "RL_" + nroDoc + "_" + fechaYHora + ".xls";
//            nombreExcelLetras = "RL_" + nroDoc + ".xls";
            String rutaTemplate = rutaProgramas + "\\" + nombreExcelLetras;
            rutaTemplate = rutaTemplate.replace("/", "\\");
            System.out.println("rutaTemplate::" + rutaTemplate);
            exportarCabDetLetras(file, parametros, lista, rutaLibroMargen0, rutaTemplate);

        } catch (WriteException e) {
            System.out.println("Excepcion:" + e.getMessage());
            JOptionPane.showMessageDialog(it, "Error al exportar reporte de letras", "Reporte de Letras en excel", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            System.out.println("Excepcion:" + ex.getMessage());
            JOptionPane.showMessageDialog(it, "Error al guardar letra excel, configure la Ruta de Programas \n a '" + rutaProgramas + "'", 
                                          "Exportación Automática de Reporte de Letras", JOptionPane.ERROR_MESSAGE);
        }
        return nombreExcelLetras;
    }
    
    // 9. REPORTE DE LETRAS EXCEL
    public void exportarCabDetLetras(File file, Map<String, Object> parametros, ArrayList<ListaLetras> lista, String rutaLibroMargen0, String rutaTemplate) 
                                     throws FileNotFoundException, IOException, WriteException, BiffException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaTemplate), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
        
        WritableFont font16Negrita = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        WritableFont font10Negrita = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableFont font14Negrita = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
        
        WritableCellFormat norm9Izq = new WritableCellFormat(font9Normal);
        norm9Izq.setAlignment(Alignment.LEFT);
        norm9Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Izq = new WritableCellFormat(font8Normal);
        norm8Izq.setAlignment(Alignment.LEFT);
        norm8Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Der = new WritableCellFormat(font8Normal);
        norm8Der.setAlignment(Alignment.RIGHT);
        norm8Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Cent = new WritableCellFormat(font10Normal);
        norm9Cent.setAlignment(Alignment.CENTRE);
        norm9Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Der = new WritableCellFormat(font10Normal);
        norm9Der.setAlignment(Alignment.RIGHT);
        norm9Der.setBorder(Border.ALL, BorderLineStyle.NONE);
                
        WritableCellFormat norm10Izq = new WritableCellFormat(font10Normal);
        norm10Izq.setAlignment(Alignment.LEFT);
        norm10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Cent = new WritableCellFormat(font10Normal);
        norm10Cent.setAlignment(Alignment.CENTRE);
        norm10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Der = new WritableCellFormat(font10Normal);
        norm10Der.setAlignment(Alignment.RIGHT);
        norm10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Izq = new WritableCellFormat(font10Negrita);
        negrita10Izq.setAlignment(Alignment.LEFT);
        negrita10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Cent = new WritableCellFormat(font10Negrita);
        negrita10Cent.setAlignment(Alignment.CENTRE);
        negrita10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Der = new WritableCellFormat(font10Negrita);
        negrita10Der.setAlignment(Alignment.RIGHT);
        negrita10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita16Cent = new WritableCellFormat(font16Negrita);
        negrita16Cent.setAlignment(Alignment.CENTRE);
        negrita16Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita14Izq = new WritableCellFormat(font14Negrita);
        negrita14Izq.setAlignment(Alignment.LEFT);
        negrita14Izq.setVerticalAlignment(VerticalAlignment.CENTRE);
        negrita14Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        int LIM_BLOQUE = 65493; // = 65536 - 43 (Para plantilla Excel "ZLibroREPLET.xls" en versión Excel 97-2003 -> Máximo de filas = 65536)
        int indice_lista = 0;
        ListaLetras letRep;
        boolean finalDetalle = false;
        int numPagina = 0;
        
        for ( int indice_bloque = 0; indice_bloque < LIM_BLOQUE && finalDetalle == false; indice_bloque = indice_bloque + 48 ) { // 52(ULTIMO)
    // I. CABECERA DE EXCEL
            // 1. Empresa
            String empresa = String.valueOf(parametros.get("empresa"));
            s.addCell(new Label(B, (indice_bloque + 1), empresa, negrita10Izq)); // 1 --> Equivale a Fila 2 en Excel

            // 2. Título del Excel
            String titulo = String.valueOf(parametros.get("titulo"));
            s.addCell(new Label(B, (indice_bloque + 3), titulo, negrita14Izq)); // 3 --> Equivale a Fila 4 en Excel

            // 3. Fecha y Hora del Reporte
            s.addCell(new Label(P, (indice_bloque + 5), getFechayHora(), negrita10Der)); // 5 --> Equivale a Fila 6 en Excel

    // II. DETALLE DE EXCEL
            // 1. DETALLE EXCEL: Nombres de columnas (1° CABECERA = fila, 2° CABECERA = fila + 1)
            int fila = FILA_INI_DET_LETRAS + indice_bloque;// 7, 49, 91 (1° Cabecera de columnas del Detalle - Serie de 42 en 42)
            s.addCell(new Label(B, fila, "N° letra", negrita10Cent));

            s.addCell(new Label(C, fila, "Fecha", negrita10Cent));
            s.addCell(new Label(C, fila + 1, "Emisión", negrita10Cent));

            s.addCell(new Label(D, fila, "Fecha", negrita10Cent));
            s.addCell(new Label(D, fila + 1, "Venc.", negrita10Cent));

            s.addCell(new Label(F, fila, "Cliente", negrita10Der));
            s.addCell(new Label(H, fila, "Emitido", negrita10Cent));
            s.addCell(new Label(I, fila, "Saldo", negrita10Cent));
            s.addCell(new Label(J, fila, "Ren.", negrita10Cent));

            s.addCell(new Label(K, fila, "N° Letra", negrita10Cent));
            s.addCell(new Label(K, fila + 1, "Banco", negrita10Cent));
            
            s.addCell(new Label(L, fila, "Banco", negrita10Cent));

            s.addCell(new Label(M, fila, "Días", negrita10Cent));
            s.addCell(new Label(M, fila + 1, "Venc.", negrita10Cent));

            s.addCell(new Label(N, fila, "Factura de Referencia", negrita10Izq));
//            s.addCell(new Label(N, fila + 1, "Ref.", negrita10Cent));

            fila++;
            
            // 2. DETALLE EXCEL: Items por filas (10-41, 52-83, 94-125) => Máximo de 32 items en detalle
            int num_items_det = 0;
            
//            System.out.println("indice_lista:" + indice_lista);
            if ( indice_lista < lista.size() && finalDetalle == false ) {
                letRep = lista.get(indice_lista);
            } else {
//                letRep = null;
                break;
            }
            
            while ( letRep != null ) {
//            for ( ListaLetras letRep : lista ) {
                fila++;

                if ( num_items_det < 31) {
                    // 1° N° Letra
                    String numLetra = letRep.getNumLetra();
                    s.addCell(new Label(B, fila, numLetra, norm10Cent));

                    // 2° Fecha de emisión
                    String fechaEmision = letRep.getFechaEmision();
                    s.addCell(new Label(C, fila, fechaEmision, norm10Cent));

                    // 3° Fecha de vencimiento
                    String fechaVence = letRep.getFechaVence();
                    s.addCell(new Label(D, fila, fechaVence, norm10Cent));

                    // 4° Cliente
                    String cliente = letRep.getCliente();
                    s.addCell(new Label(EE, fila, cliente, norm10Izq));

                    // 5° Importe emitido
                    String emitido = letRep.getEmitido();
                    s.addCell(new Label(H, fila, emitido, norm10Der));

                    // 6° Saldo restante
                    String saldo = letRep.getSaldo();
                    s.addCell(new Label(I, fila, saldo, norm10Der));

                    // 7° N° de renovación
                    String numRenovacion = letRep.getNumRenovacion();
                    s.addCell(new Label(J, fila, numRenovacion, norm10Cent));

                    // 8° N° de letra Banco
                    String numLetraBanco = letRep.getNumLetraBanco();
                    if ( "null".equals(numLetraBanco) ) {
                        numLetraBanco = "";
                    }
                    s.addCell(new Label(K, fila, numLetraBanco, norm10Cent));
                    
                    // 9° Banco (de la letra, anterior a la column 8°) 
                    String bancoLetra = letRep.getBancoLetra();
                    if ( "null".equals(bancoLetra) ) {
                        bancoLetra = "";
                    }
                    s.addCell(new Label(L, fila, bancoLetra, norm10Cent));
                    
                    // 10° Días de vencimiento
                    String diasVence = letRep.getDiasVencido();
                    s.addCell(new Label(M, fila, diasVence, norm10Cent));

                    // 11° N° de factura de referencia
                    String factRef = letRep.getFacturaRef();
                    s.addCell(new Label(N, fila, factRef, norm10Izq));

        //            fila++;

        //            int min = 35;
        //            if ( cliente.length() > min ) {
        //                String cliLinea1 = cliente.substring(0, min + 1);
        //                String cliLinea2 = cliente.substring(min + 1, cliente.length()) + ".";
        //                s.addCell(new Label(EE, fila, "    " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
        //                s.addCell(new Label(C, fila + 1, "    " + cliLinea2, norm9IzqSinBorde)); // cliente (línea 2)
        //
        //            } else {
        //                String cliLinea1 = cliente.substring(0, cliente.length());
        //                s.addCell(new Label(EE, fila, "   " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
        //            }
                } else {
                    break;
                }
                indice_lista++;
                
                if ( indice_lista < lista.size() ) {
                    letRep = lista.get(indice_lista);
                    
                } else {
                    finalDetalle = true; // terminó de revisar toda la lista
                    break;
                }
                num_items_det++;
            }
            
            System.out.println("N° fila(último item-detalle) : " + fila);
            System.out.println("ÚLTIMO Indice de la lista:" + indice_lista);
            System.out.println("TAMAÑO DE LISTA:" + lista.size());
            
       // TOTALES (EMITIDO Y SALDO EN SOLES Y DOLARES)
            if ( indice_lista == lista.size() ) {
                
                String totEmisionSoles = String.valueOf(parametros.get("totEmisionSoles"));
                String totSaldoSoles = String.valueOf(parametros.get("totSaldoSoles"));
                String totEmisionDolar = String.valueOf(parametros.get("totEmisionDolar"));
                String totSaldoDolar = String.valueOf(parametros.get("totSaldoDolar"));

                int primeraFilaTotal = fila + 2;
                int segundaFilaTotal = fila + 3;
                s.addCell(new Label(B, primeraFilaTotal, "TOTAL EMISIÓN EN SOLES S/.", norm10Izq));
                s.addCell(new Label(B, segundaFilaTotal, "TOTAL EMISIÓN EN DOLARES US $", norm10Izq));
                s.addCell(new Label(F, primeraFilaTotal, totEmisionSoles, norm10Der));
                s.addCell(new Label(F, segundaFilaTotal, totEmisionDolar, norm10Der));

                s.addCell(new Label(J, primeraFilaTotal, "TOTAL SALDO POR PAGAR EN SOLES S/.", norm10Izq));
                s.addCell(new Label(J, segundaFilaTotal, "TOTAL SALDO POR PAGAR EN DOLARES US $", norm10Izq));
                s.addCell(new Label(O, primeraFilaTotal, totSaldoSoles, norm10Der));
                s.addCell(new Label(O, segundaFilaTotal, totSaldoDolar, norm10Der));
            }
            
       // III. PIE DE PÁGINA DE EXCEL
//            N° de página por cada hoja Excel
            String num_pagina = "Página N°" + (numPagina + 1);
            System.out.println("numPagina + 1:" + (numPagina + 1));
            s.addCell(new Label(P, indice_bloque + 43, num_pagina, norm8Der)); // Antes 40, Ahora 43
            numPagina++;
        }
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
    }
    
    private String getFechayHora() {
        Date fechaHora = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(fechaHora);
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fechaHora);
        String fechayHora = fecha + " - " + hora;
        return fechayHora;
    }
    
    private static String getStringFechayHora() {
        Date fechaHora = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formatoFecha.format(fechaHora);
        String[] fechaArray = fecha.split("/");
        String fechaString = fechaArray[2] + "_" + fechaArray[1] + "_" + fechaArray[0];
        
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String hora = formatoHora.format(fechaHora);
        String[] horaArray = hora.split(":");
        String horaString = horaArray[0] + "_" + horaArray[1] + "_" + horaArray[2];
        
        String fechayHora = fechaString + "_" + horaString;
        return fechayHora;
    }
    
    // 10. EXPORTAR REPORTE DE CTA. CTE. FACTURAS (Sólo los seleccionados, ya existe otro botón "EXPORTAR TODA LA CTA. CTE - Parte superior formulario")
    public void exportarCtaCteFacturasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList<ListaFacturas> lista) 
                                                    throws FileNotFoundException, BiffException {
        if ( lista.size() > 0 )  {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String rutaExport = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(rutaExport);
                        
                        String rutaProgramas = control.getRutaprogramas();
                        String rutaLibroMargen0 = rutaProgramas + "\\ZLibroREPFACT.xls";
                        rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿desea reemplazarlo?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                exportarCtaCteFacturasSeleccionadas(file, nombreHoja, parametros, lista, rutaLibroMargen0, rutaExport);
                            }
                        } else {
                            exportarCtaCteFacturasSeleccionadas(file, nombreHoja, parametros, lista, rutaLibroMargen0, rutaExport);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar Cta.Cte.Facturas", JOptionPane.ERROR_MESSAGE);
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar Cta.Cte.Facturas", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "Ningún item seleccionado", "Vacío", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 10. EXPORTAR REPORTE DE CTA. CTE. FACTURAS (Sólo los seleccionados, ya existe otro botón "EXPORTAR TODA LA CTA. CTE - Parte superior formulario")
    public void exportarCtaCteFacturasSeleccionadas(File file, String nombreHoja, Map<String, Object> parametros, ArrayList<ListaFacturas> lista,
                                                    String rutaLibroMargen0, String rutaExport)
                                                    throws FileNotFoundException, IOException, WriteException, BiffException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaExport), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
        
        WritableFont font16Negrita = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        WritableFont font10Negrita = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableFont font14Negrita = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
        
        WritableCellFormat norm9Izq = new WritableCellFormat(font9Normal);
        norm9Izq.setAlignment(Alignment.LEFT);
        norm9Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Izq = new WritableCellFormat(font8Normal);
        norm8Izq.setAlignment(Alignment.LEFT);
        norm8Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Der = new WritableCellFormat(font8Normal);
        norm8Der.setAlignment(Alignment.RIGHT);
        norm8Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Cent = new WritableCellFormat(font10Normal);
        norm9Cent.setAlignment(Alignment.CENTRE);
        norm9Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Der = new WritableCellFormat(font10Normal);
        norm9Der.setAlignment(Alignment.RIGHT);
        norm9Der.setBorder(Border.ALL, BorderLineStyle.NONE);
                
        WritableCellFormat norm10Izq = new WritableCellFormat(font10Normal);
        norm10Izq.setAlignment(Alignment.LEFT);
        norm10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Cent = new WritableCellFormat(font10Normal);
        norm10Cent.setAlignment(Alignment.CENTRE);
        norm10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Der = new WritableCellFormat(font10Normal);
        norm10Der.setAlignment(Alignment.RIGHT);
        norm10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Izq = new WritableCellFormat(font10Negrita);
        negrita10Izq.setAlignment(Alignment.LEFT);
        negrita10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Cent = new WritableCellFormat(font10Negrita);
        negrita10Cent.setAlignment(Alignment.CENTRE);
        negrita10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Der = new WritableCellFormat(font10Negrita);
        negrita10Der.setAlignment(Alignment.RIGHT);
        negrita10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita16Cent = new WritableCellFormat(font16Negrita);
        negrita16Cent.setAlignment(Alignment.CENTRE);
        negrita16Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita14Izq = new WritableCellFormat(font14Negrita);
        negrita14Izq.setAlignment(Alignment.LEFT);
        negrita14Izq.setVerticalAlignment(VerticalAlignment.CENTRE);
        negrita14Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        int LIM_BLOQUE = 65493; // = 65536 - 43 (Para plantilla Excel "ZLibroREPLET.xls" en versión Excel 97-2003 -> Máximo de filas = 65536)
        int indice_lista = 0;
        ListaFacturas factRep;
        boolean finalDetalle = false;
        int numPagina = 0;
        
        for ( int indice_bloque = 0; indice_bloque < LIM_BLOQUE && finalDetalle == false; indice_bloque = indice_bloque + 48 ) { // 52(ULTIMO)
    // I. CABECERA DE EXCEL
            // 1. Empresa
            String empresa = String.valueOf(parametros.get("empresa"));
            s.addCell(new Label(B, (indice_bloque + 1), empresa, negrita10Izq)); // 1 --> Equivale a Fila 2 en Excel

            // 2. Título del Excel
            String titulo = String.valueOf(parametros.get("titulo"));
            s.addCell(new Label(B, (indice_bloque + 3), titulo, negrita14Izq)); // 3 --> Equivale a Fila 4 en Excel

            // 3. Fecha y Hora del Reporte
            s.addCell(new Label(Q, (indice_bloque + 5), getFechayHora(), negrita10Der)); // 5 --> Equivale a Fila 6 en Excel

    // II. DETALLE DE EXCEL
            // 1. DETALLE EXCEL: Nombres de columnas (1° CABECERA = fila, 2° CABECERA = fila + 1)
            int fila = FILA_INI_DET_LETRAS + indice_bloque;// 7, 49, 91 (1° Cabecera de columnas del Detalle - Serie de 42 en 42)
            s.addCell(new Label(B, fila, "Tipo", negrita10Cent));
            s.addCell(new Label(B, (fila + 1), "Doc", negrita10Cent));

            s.addCell(new Label(C, fila, "Documento", negrita10Cent));
            s.addCell(new Label(D, fila, "Fecha", negrita10Der));
            s.addCell(new Label(EE, fila, "RUC", negrita10Cent));
            s.addCell(new Label(F, fila, "Nombre Cliente", negrita10Izq));
            s.addCell(new Label(K, fila, "Facturado", negrita10Izq));
            s.addCell(new Label(M, fila, "Pagado", negrita10Izq));
            s.addCell(new Label(O, fila, "Saldo", negrita10Izq));
            s.addCell(new Label(P, fila, "Banco", negrita10Izq));

            fila++;
            
            // 2. DETALLE EXCEL: Items por filas (10-41, 52-83, 94-125) => Máximo de 32 items en detalle
            int num_items_det = 0;
//            System.out.println("detalle EXCEL:::" + lista.size());
//            System.out.println("indice_lista:" + indice_lista);
            
            if ( indice_lista < lista.size() && finalDetalle == false ) {
                factRep = lista.get(indice_lista);
                
            } else {
                break;
            }
            
            while ( factRep != null ) {
                fila++;

                if ( num_items_det < 31) {
                    // 1° Tipo Doc
                    String tipoDoc = factRep.getTipDoc();
                    s.addCell(new Label(B, fila, tipoDoc, norm10Cent));

                    // 2° Documento
                    String documento = factRep.getNumDoc();
                    s.addCell(new Label(C, fila, documento, norm10Cent));

                    // 3° Fecha
                    String fecha = factRep.getFecha();
                    s.addCell(new Label(D, fila, fecha, norm10Izq));

                    // 4° RUC
                    String ruc = factRep.getRuc();
                    s.addCell(new Label(EE, fila, ruc, norm10Der));

                    // 5° Nombre Cliente
                    String cliente = factRep.getCliente();
                    s.addCell(new Label(F, fila, cliente, norm10Izq));

                    // 6° Facturado
                    String facturado = factRep.getFacturado();
                    s.addCell(new Label(K, fila, facturado, norm10Der));

                    // 7° Pagado
                    String pagado = factRep.getPagado();
                    s.addCell(new Label(M, fila, pagado, norm10Der));
                    
                    // 8° Saldo
                    String saldo = factRep.getSaldo();
                    s.addCell(new Label(O, fila, saldo, norm10Der));
                    
                    // 9° Banco
                    String banco = factRep.getBanco();
                    if ( "null".equals(banco) ) {
                        banco = "";
                    }
                    s.addCell(new Label(P, fila, banco, norm10Der));
                    
                } else {
                    break;
                }
                indice_lista++;
                
                if ( indice_lista < lista.size() ) {
                    factRep = lista.get(indice_lista);
                    
                } else {
                    finalDetalle = true; // terminó de revisar toda la lista
                    break;
                }
                num_items_det++;
            }
            
//            System.out.println("N° fila(último item-detalle) : " + fila);
//            System.out.println("ÚLTIMO Indice de la lista:" + indice_lista);
//            System.out.println("TAMAÑO DE LISTA:" + lista.size());
            
       // TOTALES (EMITIDO Y SALDO EN SOLES Y DOLARES)
            if ( indice_lista == lista.size() ) {
                
                String totFacturadoSoles = String.valueOf(parametros.get("totFactSoles"));
                String totSaldoSoles = String.valueOf(parametros.get("totSaldoSoles"));
                String totFacturadoDolar = String.valueOf(parametros.get("totFactDolar"));
                String totSaldoDolar = String.valueOf(parametros.get("totSaldoDolar"));

                int primeraFilaTotal = fila + 2;
                int segundaFilaTotal = fila + 3;
                s.addCell(new Label(B, primeraFilaTotal, "TOTAL FACTURADO EN SOLES S/.", norm10Izq));
                s.addCell(new Label(B, segundaFilaTotal, "TOTAL SALDO EN SOLES S/.", norm10Izq));
                s.addCell(new Label(F, primeraFilaTotal, totFacturadoSoles, norm10Der));
                s.addCell(new Label(F, segundaFilaTotal, totSaldoSoles, norm10Der));

                s.addCell(new Label(J, primeraFilaTotal, "TOTAL FACTURADO EN DÓLARES US $", norm10Izq));
                s.addCell(new Label(J, segundaFilaTotal, "TOTAL SALDO EN DOLARES US $", norm10Izq));
                s.addCell(new Label(P, primeraFilaTotal, totFacturadoDolar, norm10Der));
                s.addCell(new Label(P, segundaFilaTotal, totSaldoDolar, norm10Der));
            }
            
       // III. PIE DE PÁGINA DE EXCEL
//            N° de página por cada hoja Excel
            String num_pagina = "Página N°" + (numPagina + 1);
//            System.out.println("numPagina + 1:" + (numPagina + 1));
            s.addCell(new Label(P, indice_bloque + 43, num_pagina, norm8Der)); // Antes 40, Ahora 43
            numPagina++;
        }
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Cta.Cte. Facturas exportada con éxito", "Exportar Cta.Cte.Facturas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // 11. EXPORTAR REPORTE DE CTA. CTE. LETRAS (Sólo los seleccionados)
    public void exportarCtaCteLetrasSeleccionadas(String nombreHoja, Control control, Map<String, Object> parametros, ArrayList<ListaLetras> lista) 
                                                  throws FileNotFoundException, BiffException {
        if ( lista.size() > 0 )  {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            
            switch ( seleccion ) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String rutaExport = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(rutaExport);
                        
                        String rutaProgramas = control.getRutaprogramas();
                        String rutaLibroMargen0 = rutaProgramas + "\\ZLibroREPLET.xls";
                        rutaLibroMargen0 = rutaLibroMargen0.replace("/", "\\");

                        if ( file.exists() ) {
                            if ( JOptionPane.showConfirmDialog(it, "Archivo existente ¿desea reemplazarlo?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION ) {
                                exportarCtaCteLetrasSeleccionadas(file, nombreHoja, parametros, lista, rutaLibroMargen0, rutaExport);
                            }
                        } else {
                            exportarCtaCteLetrasSeleccionadas(file, nombreHoja, parametros, lista, rutaLibroMargen0, rutaExport);
                        }
                    } catch (WriteException e) {
                        JOptionPane.showMessageDialog(it, "Error al exportar", "Exportar Cta.Cte.Letras", JOptionPane.ERROR_MESSAGE);
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(it, "Error al guardar", "Exportar Cta.Cte.Letras", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(it, "Ningún item seleccionado", "Vacío", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // 11. EXPORTAR REPORTE DE CTA. CTE. LETRAS (Sólo los seleccionados)
    public void exportarCtaCteLetrasSeleccionadas(File file, String nombreHoja, Map<String, Object> parametros, ArrayList<ListaLetras> lista,
                                                  String rutaLibroMargen0, String rutaExport)
                                                  throws FileNotFoundException, IOException, WriteException, BiffException {
        
        Workbook workbook = Workbook.getWorkbook(new File(rutaLibroMargen0)); // ORIGEN
        WritableWorkbook w = Workbook.createWorkbook(new File(rutaExport), workbook); // workbook DESTINO
        WritableSheet s = w.getSheet(0); // sheet
        
        WritableFont font10Normal = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableFont font9Normal = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        WritableFont font8Normal = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
        
        WritableFont font16Negrita = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        WritableFont font10Negrita = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableFont font14Negrita = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
        
        WritableCellFormat norm9Izq = new WritableCellFormat(font9Normal);
        norm9Izq.setAlignment(Alignment.LEFT);
        norm9Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Izq = new WritableCellFormat(font8Normal);
        norm8Izq.setAlignment(Alignment.LEFT);
        norm8Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm8Der = new WritableCellFormat(font8Normal);
        norm8Der.setAlignment(Alignment.RIGHT);
        norm8Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Cent = new WritableCellFormat(font10Normal);
        norm9Cent.setAlignment(Alignment.CENTRE);
        norm9Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm9Der = new WritableCellFormat(font10Normal);
        norm9Der.setAlignment(Alignment.RIGHT);
        norm9Der.setBorder(Border.ALL, BorderLineStyle.NONE);
                
        WritableCellFormat norm10Izq = new WritableCellFormat(font10Normal);
        norm10Izq.setAlignment(Alignment.LEFT);
        norm10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Cent = new WritableCellFormat(font10Normal);
        norm10Cent.setAlignment(Alignment.CENTRE);
        norm10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat norm10Der = new WritableCellFormat(font10Normal);
        norm10Der.setAlignment(Alignment.RIGHT);
        norm10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Izq = new WritableCellFormat(font10Negrita);
        negrita10Izq.setAlignment(Alignment.LEFT);
        negrita10Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Cent = new WritableCellFormat(font10Negrita);
        negrita10Cent.setAlignment(Alignment.CENTRE);
        negrita10Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita10Der = new WritableCellFormat(font10Negrita);
        negrita10Der.setAlignment(Alignment.RIGHT);
        negrita10Der.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita16Cent = new WritableCellFormat(font16Negrita);
        negrita16Cent.setAlignment(Alignment.CENTRE);
        negrita16Cent.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        WritableCellFormat negrita14Izq = new WritableCellFormat(font14Negrita);
        negrita14Izq.setAlignment(Alignment.LEFT);
        negrita14Izq.setVerticalAlignment(VerticalAlignment.CENTRE);
        negrita14Izq.setBorder(Border.ALL, BorderLineStyle.NONE);
        
        int LIM_BLOQUE = 65493; // = 65536 - 43 (Para plantilla Excel "ZLibroREPLET.xls" en versión Excel 97-2003 -> Máximo de filas = 65536)
        int indice_lista = 0;
        ListaLetras letRep;
        boolean finalDetalle = false;
        int numPagina = 0;
        
        for ( int indice_bloque = 0; indice_bloque < LIM_BLOQUE && finalDetalle == false; indice_bloque = indice_bloque + 48 ) { // 52(ULTIMO)
    // I. CABECERA DE EXCEL
            // 1. Empresa
            String empresa = String.valueOf(parametros.get("empresa"));
            s.addCell(new Label(B, (indice_bloque + 1), empresa, negrita10Izq)); // 1 --> Equivale a Fila 2 en Excel

            // 2. Título del Excel
            String titulo = String.valueOf(parametros.get("titulo"));
            s.addCell(new Label(B, (indice_bloque + 3), titulo, negrita14Izq)); // 3 --> Equivale a Fila 4 en Excel

            // 3. Fecha y Hora del Reporte
            s.addCell(new Label(P, (indice_bloque + 5), getFechayHora(), negrita10Der)); // 5 --> Equivale a Fila 6 en Excel

    // II. DETALLE DE EXCEL
            // 1. DETALLE EXCEL: Nombres de columnas (1° CABECERA = fila, 2° CABECERA = fila + 1)
            int fila = FILA_INI_DET_LETRAS + indice_bloque;// 7, 49, 91 (1° Cabecera de columnas del Detalle - Serie de 42 en 42)
            s.addCell(new Label(B, fila, "N° letra", negrita10Cent));

            s.addCell(new Label(C, fila, "Fecha", negrita10Cent));
            s.addCell(new Label(C, fila + 1, "Emisión", negrita10Cent));

            s.addCell(new Label(D, fila, "Fecha", negrita10Cent));
            s.addCell(new Label(D, fila + 1, "Venc.", negrita10Cent));

            s.addCell(new Label(F, fila, "Cliente", negrita10Der));
            s.addCell(new Label(H, fila, "Emitido", negrita10Cent));
            s.addCell(new Label(I, fila, "Saldo", negrita10Cent));
            s.addCell(new Label(J, fila, "Ren.", negrita10Cent));

            s.addCell(new Label(K, fila, "N° Letra", negrita10Cent));
            s.addCell(new Label(K, fila + 1, "Banco", negrita10Cent));
            
            s.addCell(new Label(L, fila, "Banco", negrita10Cent));

            s.addCell(new Label(M, fila, "Días", negrita10Cent));
            s.addCell(new Label(M, fila + 1, "Venc.", negrita10Cent));

            s.addCell(new Label(N, fila, "Factura de Referencia", negrita10Izq));
//            s.addCell(new Label(N, fila + 1, "Ref.", negrita10Cent));

            fila++;
            
            // 2. DETALLE EXCEL: Items por filas (10-41, 52-83, 94-125) => Máximo de 32 items en detalle
            int num_items_det = 0;
            
//            System.out.println("indice_lista:" + indice_lista);
            if ( indice_lista < lista.size() && finalDetalle == false ) {
                letRep = lista.get(indice_lista);
            } else {
//                letRep = null;
                break;
            }
            
            while ( letRep != null ) {
//            for ( ListaLetras letRep : lista ) {
                fila++;

                if ( num_items_det < 31) {
                    // 1° N° Letra
                    String numLetra = letRep.getNumLetra();
                    s.addCell(new Label(B, fila, numLetra, norm10Cent));

                    // 2° Fecha de emisión
                    String fechaEmision = letRep.getFechaEmision();
                    s.addCell(new Label(C, fila, fechaEmision, norm10Cent));

                    // 3° Fecha de vencimiento
                    String fechaVence = letRep.getFechaVence();
                    s.addCell(new Label(D, fila, fechaVence, norm10Cent));

                    // 4° Cliente
                    String cliente = letRep.getCliente();
                    s.addCell(new Label(EE, fila, cliente, norm10Izq));

                    // 5° Importe emitido
                    String emitido = letRep.getEmitido();
                    s.addCell(new Label(H, fila, emitido, norm10Der));

                    // 6° Saldo restante
                    String saldo = letRep.getSaldo();
                    s.addCell(new Label(I, fila, saldo, norm10Der));

                    // 7° N° de renovación
                    String numRenovacion = letRep.getNumRenovacion();
                    s.addCell(new Label(J, fila, numRenovacion, norm10Cent));

                    // 8° N° de letra Banco
                    String numLetraBanco = letRep.getNumLetraBanco();
                    if ( "null".equals(numLetraBanco) ) {
                        numLetraBanco = "";
                    }
                    s.addCell(new Label(K, fila, numLetraBanco, norm10Cent));
                    
                    // 9° Banco (de la letra, anterior a la column 8°) 
                    String bancoLetra = letRep.getBancoLetra();
                    if ( "null".equals(bancoLetra) ) {
                        bancoLetra = "";
                    }
                    s.addCell(new Label(L, fila, bancoLetra, norm10Cent));
                    
                    // 10° Días de vencimiento
                    String diasVence = letRep.getDiasVencido();
                    s.addCell(new Label(M, fila, diasVence, norm10Cent));

                    // 11° N° de factura de referencia
                    String factRef = letRep.getFacturaRef();
                    s.addCell(new Label(N, fila, factRef, norm10Izq));

        //            fila++;

        //            int min = 35;
        //            if ( cliente.length() > min ) {
        //                String cliLinea1 = cliente.substring(0, min + 1);
        //                String cliLinea2 = cliente.substring(min + 1, cliente.length()) + ".";
        //                s.addCell(new Label(EE, fila, "    " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
        //                s.addCell(new Label(C, fila + 1, "    " + cliLinea2, norm9IzqSinBorde)); // cliente (línea 2)
        //
        //            } else {
        //                String cliLinea1 = cliente.substring(0, cliente.length());
        //                s.addCell(new Label(EE, fila, "   " + cliLinea1, norm9IzqSinBorde)); // cliente (línea 1)
        //            }
                } else {
                    break;
                }
                indice_lista++;
                
                if ( indice_lista < lista.size() ) {
                    letRep = lista.get(indice_lista);
                    
                } else {
                    finalDetalle = true; // terminó de revisar toda la lista
                    break;
                }
                num_items_det++;
            }
            
//            System.out.println("N° fila(último item-detalle) : " + fila);
//            System.out.println("ÚLTIMO Indice de la lista:" + indice_lista);
//            System.out.println("TAMAÑO DE LISTA:" + lista.size());
            
       // TOTALES (EMITIDO Y SALDO EN SOLES Y DOLARES)
            if ( indice_lista == lista.size() ) {
                
                String totEmisionSoles = String.valueOf(parametros.get("totEmisionSoles"));
                String totSaldoSoles = String.valueOf(parametros.get("totSaldoSoles"));
                String totEmisionDolar = String.valueOf(parametros.get("totEmisionDolar"));
                String totSaldoDolar = String.valueOf(parametros.get("totSaldoDolar"));

                int primeraFilaTotal = fila + 2;
                int segundaFilaTotal = fila + 3;
                s.addCell(new Label(B, primeraFilaTotal, "TOTAL EMISIÓN EN SOLES S/.", norm10Izq));
                s.addCell(new Label(B, segundaFilaTotal, "TOTAL EMISIÓN EN DOLARES US $", norm10Izq));
                s.addCell(new Label(F, primeraFilaTotal, totEmisionSoles, norm10Der));
                s.addCell(new Label(F, segundaFilaTotal, totEmisionDolar, norm10Der));

                s.addCell(new Label(J, primeraFilaTotal, "TOTAL SALDO POR PAGAR EN SOLES S/.", norm10Izq));
                s.addCell(new Label(J, segundaFilaTotal, "TOTAL SALDO POR PAGAR EN DOLARES US $", norm10Izq));
                s.addCell(new Label(O, primeraFilaTotal, totSaldoSoles, norm10Der));
                s.addCell(new Label(O, segundaFilaTotal, totSaldoDolar, norm10Der));
            }
            
       // III. PIE DE PÁGINA DE EXCEL
//            N° de página por cada hoja Excel
            String num_pagina = "Página N°" + (numPagina + 1);
//            System.out.println("numPagina + 1:" + (numPagina + 1));
            s.addCell(new Label(P, indice_bloque + 43, num_pagina, norm8Der)); // Antes 40, Ahora 43
            numPagina++;
        }
        
        w.write();
        w.close();
        workbook.close();

        String Path = file.getAbsolutePath();
        if ( !Path.endsWith(".xls") ) {
            File temp = new File(Path + ".xls");
            file.renameTo(temp);
        }
        JOptionPane.showMessageDialog(it, "Cta.Cte. Letras exportada con éxito", "Exportar Cta.Cte.Letras", JOptionPane.INFORMATION_MESSAGE);
    }
}