package Mantenimiento.Importaciones;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTable;
public class ArchivoTextoDAO {
    private JFrame it;
    JTable table;
    private String importador;
    private String cabecera;
    private String ln;
    private String cuerpo;
    private String ruta;
    public ArchivoTextoDAO(JTable table, JFrame IU, String imp){
        this.table=table;
        this.it=IU;
        importador=imp;
        ln = System.getProperty("line.separator");
    }
    public void generaArchivoTexto(String cab){
        cabecera=cab;
        boolean acceso=true;
        try{
            File outFile = new File("D:\\Black Jack\\Desktop\\Sistema\\Reportes\\archivoSalida.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            writer.write(cabecera);
            System.out.println("col:"+table.getColumnCount());
            System.out.println("fil:"+table.getRowCount());
            for (int fil = 0; fil < table.getRowCount(); fil++) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Object objeto = table.getValueAt(fil,col);
                    System.out.print(String.valueOf(objeto)+"-");
                    if(col==table.getColumnCount()-1){
                        writer.write(String.valueOf(objeto)+ln);
                    }else{
                        writer.write(String.valueOf(objeto)+"\t");
                    }
                }
            }
            //writer.newLine(); // Esto es un salto de linea
            writer.close();
        }catch(IOException e){
            System.err.println(e);
            acceso=false;
            //System.exit(1);
        }
        System.out.println("Acceso:"+acceso);
    }
    public void abrir() {
        
    }
    //public void guardar(File file,String hojaTitle) throws FileNotFoundException, IOException, WriteException{
}