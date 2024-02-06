package Entidades.Otros;

import java.awt.Desktop;
import java.awt.print.PrinterJob;

/**
 *
 * @author Ledis Rivera Changra
 */
public class ImpresionExcel {
    
    public void imprimirDesdeExcel(String rutaDoc) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.printDialog();
        String impresora = job.getPrintService().getName();

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        java.io.File fichero = new java.io.File(rutaDoc);
//        System.out.println("rutaDoc >>: " + rutaDoc);
        
        if ( desktop.isSupported(Desktop.Action.PRINT) ) {
            try {
                try {
//                    System.out.println("PRINTER:" + impresora);
                    String cadena = "Rundll32 printui.dll,PrintUIEntry /y /n \"" + impresora + "\"";
//                    System.out.println("cadena:" + cadena);
                    
                    Process pr = Runtime.getRuntime().exec(cadena);
                } catch (Exception ex) {
                    System.out.println("Ha ocurrido un error al ejecutar el comando. Error: " + ex);
                }
                desktop.print(fichero);
                
            } catch (Exception e) {
                System.out.print("El sistema no permite imprimir usando la clase Desktop");
                e.printStackTrace();
            }
        } else {
            System.out.print("El sistema no permite imprimir usando la clase Desktop");
        }
    }
    
    // Útil para reportes, que no se reimprimirán con la misma fecha y hora, pues se pueden imprimir en cualquier momento.
    public void imprimirEliminarExcel(String rutaDoc) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.printDialog();
        String impresora = job.getPrintService().getName();

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        java.io.File fichero = new java.io.File(rutaDoc);
//        System.out.println("rutaDoc >>: " + rutaDoc);
        
        if ( desktop.isSupported(Desktop.Action.PRINT) ) {
            try {
                try {
//                    System.out.println("PRINTER:" + impresora);
                    String cadena = "Rundll32 printui.dll,PrintUIEntry /y /n \"" + impresora + "\"";
//                    System.out.println("cadena:" + cadena);
                    
                    Process pr = Runtime.getRuntime().exec(cadena);
                } catch (Exception ex) {
                    System.out.println("Ha ocurrido un error al ejecutar el comando. Error: " + ex);
                }
                // Imprimir excel
                desktop.print(fichero);
                
                // Eliminar excel, después de imprimirlo
                if ( fichero.delete() ) {
                    System.out.println("Fichero " + fichero + " eliminado...");
                } else {
                    System.out.println("No se pudo eliminar el fichero " + fichero);
                }
                
            } catch (Exception e) {
                System.out.print("El sistema no permite imprimir usando la clase Desktop");
                e.printStackTrace();
            }
        } else {
            System.out.print("El sistema no permite imprimir usando la clase Desktop");
        }
    }
}