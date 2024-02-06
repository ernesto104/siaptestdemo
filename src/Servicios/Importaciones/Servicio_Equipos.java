package Servicios.Importaciones;
import Entidades.Equipos;
import Mantenimiento.Importaciones.GenericDAO;
import Mantenimiento.Importaciones.EquiposDAO;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import org.hibernate.Query;
import org.hibernate.Session;
public class Servicio_Equipos extends GenericDAO<Equipos>  {
    private EquiposDAO equiposDao;
    public Servicio_Equipos() {
        equiposDao=new EquiposDAO();
    }
    public void listarEquipos(JComboBox cbolineas){
        List<Equipos> lineas = equiposDao.Obtener_Lista_Objetos();
        for (int i = 0; i < lineas.size(); i++) {
            cbolineas.addItem(lineas.get(i).getDescripcion());
        }
    }
    public ArrayList<Equipos> listar_Equipos() {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Equipos");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
}