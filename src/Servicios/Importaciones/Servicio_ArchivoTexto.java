package Servicios.Importaciones;
import Mantenimiento.Importaciones.ArchivoTextoDAO;
import javax.swing.JFrame;
import javax.swing.JTable;
public class Servicio_ArchivoTexto {
    ArchivoTextoDAO txtDAO;
    public Servicio_ArchivoTexto(JTable table,JFrame IU,String importador){
        txtDAO = new ArchivoTextoDAO(table,IU,importador);
    }
    public void abrir(){
        txtDAO.abrir();
    }
    public void guardar(String cabecera){
        txtDAO.generaArchivoTexto(cabecera);
    }
}