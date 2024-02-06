package Servicios.Importaciones;
import Mantenimiento.Importaciones.ExcelDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTable;
import jxl.write.WriteException;
public class Servicio_Excel {
    ExcelDAO excelDAO;
    public Servicio_Excel(JTable table, JFrame IU){
        excelDAO = new ExcelDAO(table,IU);
    }
    public void Exportar_Excel(String hojaTitle){
        excelDAO.exportar(hojaTitle);
    }
    public void guardar(File file,String hojaTitle) throws FileNotFoundException, IOException, WriteException{
        excelDAO.guardar(file,hojaTitle);
    }
}