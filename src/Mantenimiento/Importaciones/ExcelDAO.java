package Mantenimiento.Importaciones;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class ExcelDAO {
    private JFrame it;
    JTable table;
    public ExcelDAO(JTable table, JFrame IU) {
        this.table = table;
        this.it= IU;
    }
    public void exportar(String hojaTitle) {
        if (table.getRowCount() > 0) {
            JFileChooser fc = new JFileChooser();
            int seleccion = fc.showSaveDialog(it);
            switch (seleccion) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        String ruta = fc.getSelectedFile().getAbsolutePath();
                        File file = new File(ruta);
                        if (file.exists()) {
                            if (JOptionPane.showConfirmDialog(it, "Archivo existente ¿ desea reemplazarlo?", "Reemplazar", JOptionPane.YES_NO_OPTION)
                                    == JOptionPane.YES_OPTION) {
                                guardar(file,hojaTitle);
                            } else {
                                exportar(hojaTitle);
                            }
                        } else {
                            guardar(file,hojaTitle);
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
    public void guardar(File file,String hojaTitle) throws FileNotFoundException, IOException, WriteException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet(hojaTitle, 0);
        for (int i = 0; i < table.getColumnCount(); i++) {
            s.addCell(new Label(i, 0, table.getColumnName(i), new WritableCellFormat(titleFont)));
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                Object objeto = table.getValueAt(j, i);
                s.addCell(new Label(i, j + 1, String.valueOf(objeto)));
                //s.addCell(new Label(i, j + 1, "12,45"));
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
}