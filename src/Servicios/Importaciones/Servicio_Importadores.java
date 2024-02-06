package Servicios.Importaciones;
import Entidades.Importadores;
import Mantenimiento.Importaciones.GenericDAO;
import Mantenimiento.Importaciones.ImportadoresDAO;
import java.util.List;
import javax.swing.JComboBox;
public class Servicio_Importadores extends GenericDAO<Importadores>{
    ImportadoresDAO impDao;
    public Servicio_Importadores(){
        impDao=new ImportadoresDAO();
    }
    public void listarImportadores(JComboBox cboimp){
        List listaImp=impDao.getImportadores();
        cboimp.addItem("");
        for (int i = 0; i < listaImp.size(); i++) {
            cboimp.addItem(listaImp.get(i));
        }
    }
    public int listarImportadores(JComboBox cboimp, String nombreImp){
        int indice=-1;
        List listaImp=impDao.getImportadores();
        //cboimp.addItem("");
        for (int i = 0; i < listaImp.size(); i++) {
            cboimp.addItem(listaImp.get(i));
            if(listaImp.get(i).equals(nombreImp)){
                indice=i;
            }
        }
        return indice;
    }
    public boolean registrarImportador(Importadores imp) {
        if(this.getImportador(imp.getNombre())!=null){
            return false;
        }else{
            return impDao.Agregar_Objeto(imp);
        }
    }
    public Importadores getImportador(String nombre){
        return impDao.getIdImportador(nombre);
    }
}