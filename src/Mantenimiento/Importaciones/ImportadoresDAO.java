package Mantenimiento.Importaciones;
import Entidades.Importadores;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Servicios.HibernateUtil;
public class ImportadoresDAO extends GenericDAO<Importadores> {
    
    public List getImportadores(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List joinList = session.createQuery("select i.nombre from Importadores as i order by i.nombre asc").list();
        return joinList;
    }
    public Importadores getIdImportador(String name){
        Importadores imp=new Importadores();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Importadores as imp where imp.nombre like '"+name+"'");
        ArrayList lista = (ArrayList) q.list();
        if(lista.size()==0){
            imp=null;
        }else{
            imp=(Importadores) lista.iterator().next();
        }
        return imp;
    }
}