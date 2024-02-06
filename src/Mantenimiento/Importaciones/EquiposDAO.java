package Mantenimiento.Importaciones;
import Entidades.Equipos;
public class EquiposDAO extends GenericDAO<Equipos> {
    public int nextId() {
        Equipos l=null;
        try {
            l=(Equipos) getHibernateTemplate().createQuery(
            "from Equipos li order by li.idequipo desc").iterate().next();
            session.getTransaction().commit();
        }catch(NullPointerException e){
            System.out.println("Error 1:"+e.getMessage());
        }catch (Exception e) {
            return 1;
        } 
        if(Tamaño_Lista()==0)
            return 1;
        else{
            return l.getIdequipo()+1;
        }
    }
    public Equipos obtenerEquipo(String descripcion) {
        Equipos l=(Equipos) getHibernateTemplate().createQuery(
                "from Equipo li where li.descripcion like '"+descripcion+"'")
                .list().iterator().next();
        return l;
    }
}