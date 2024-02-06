package Mantenimiento.Importaciones;
import Servicios.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
public abstract class GenericDAO<T> {
    public Class<T> domainClass = getDomainClass();
    protected Session session;
    protected Class getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }
    @SuppressWarnings("unchecked")
    public T Obtener_Objeto(int id) {
        T returnValue = (T) getHibernateTemplate().load(domainClass, id);
        session.getTransaction().commit();
        return returnValue;
    }
    public boolean Modificar_Objeto(T t) {
        try {
            getHibernateTemplate().update(t);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            handleException(e);
            return false;
        }
    }
    public boolean Agregar_Objeto(T t) {
        try {
            getHibernateTemplate().save(t);
            //getHibernateTemplate().merge(t);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            handleException(e);
            return false;
        }
    }
    public boolean Eliminar_Objeto(T t)  {
        try {
            getHibernateTemplate().delete(t);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            handleException(e);
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public List<T> Obtener_Lista_Objetos() {
        List<T> returnList = getHibernateTemplate().createQuery(
                "from " + domainClass.getName() + " x").list();
        session.getTransaction().commit();
        return returnList;
    }
    public void Eliminar_Objeto_Por_ID(int id) {
        Object obj = this.Obtener_Objeto(id);
        System.out.println("obj:"+obj);
        getHibernateTemplate().delete(obj);
    }
    public int Eliminar_Todo(boolean isSure) {
        int countDeleted = getHibernateTemplate().createQuery(
                "delete " + domainClass.getName()).executeUpdate();
        if (isSure) {
            session.getTransaction().commit();
        } else {
            session.getTransaction().rollback();
        }
        return countDeleted;
    }
    public Long Tamaño_Lista() {
        Long count = (Long) getHibernateTemplate().createQuery(
                "select count(*) from " + domainClass.getName() + " x")
                .uniqueResult();
        session.getTransaction().commit();
        return count.longValue();
    }
    public List<T> findByExample(T exampleObject) {
        Example example = Example.create(exampleObject).excludeZeroes()
                .enableLike().ignoreCase();
        List<T> list = getHibernateTemplate().createCriteria(domainClass).add(
                example).list();
        session.getTransaction().commit();
        return list;
    }
    protected Session getHibernateTemplate() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
    public void handleException(HibernateException he) throws HibernateException {
        session.getTransaction().rollback();
        he.printStackTrace();
    }
}