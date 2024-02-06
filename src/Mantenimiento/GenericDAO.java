package Mantenimiento;

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
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    @SuppressWarnings("unchecked")
    public T Obtener_Objeto(int id) {
        try { 
            T returnValue = (T) getHibernateTemplate().load(domainClass, id);
            session.getTransaction().commit();
            return returnValue;
        } catch ( Exception e ) {
            System.out.println("Excepcion:" + e.getMessage());
            return null;
        }
    }

    public boolean Modificar_Objeto(T t) {
        try {
            getHibernateTemplate().update(t);
            session.getTransaction().commit();
            return true;
            
        } catch (HibernateException e) {     
            System.out.println("error(GenericDAO-ModificarObjeto):" + e.getMessage() + " -> Clase de t:" + t.getClass() + ", t:" + t);
            session.getTransaction().rollback();
            return false;   
        }
//        finally {
//            session.close();
//        }
    }
    
    public boolean Modificar_Objeto_Merge(T t) {
        try {
//            getHibernateTemplate().update(t);
            getHibernateTemplate().merge(t);
            session.getTransaction().commit();
            return true;
            
        } catch (HibernateException e) {     
            System.out.println("error(GenericDAO-ModificarObjetoMerge):" + e.getMessage() + " -> Clase de t:" + t.getClass() + ", t:" + t);
            session.getTransaction().rollback();
            return false;   
        }
    }

    public boolean Agregar_Objeto(T t) {
        try {
            getHibernateTemplate().save(t);
            session.getTransaction().commit();
            return true;
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.out.println("Error(Agregar_Objeto):" + e.getMessage());
            return false;
        }
    }

    public boolean Eliminar_Objeto(T t) {
        try {
            getHibernateTemplate().delete(t);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> Obtener_Lista_Objetos() {
        List<T> returnList = getHibernateTemplate().createQuery(
                "from " + domainClass.getName() + " x ").list();
        session.getTransaction().commit();
        return returnList;
    }

    public void Eliminar_Objeto_Por_ID(int id) {
        Object obj = this.Obtener_Objeto(id);
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

    //protected Session getHibernateTemplate() {
    public Session getHibernateTemplate() {
        if ( session == null || !session.isOpen() ) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        session.beginTransaction();
        return session;
    }

    private void handleException(HibernateException he) throws HibernateException {
        session.getTransaction().rollback();
        he.printStackTrace();
    }
}