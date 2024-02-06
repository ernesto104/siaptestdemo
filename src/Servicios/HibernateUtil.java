
package Servicios;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration c = new Configuration().configure();
            sessionFactory = new Configuration().configure().buildSessionFactory();
//            System.out.println("sessionFactory:" + sessionFactory);
//            System.out.println("sesion actual:" + sessionFactory.getCurrentSession() );
            
        } catch (ExceptionInInitializerError ex1) {
            System.out.println("Error de inicializacion...");
            
        } catch (Exception ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void beginViewTransaction() {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().setFlushMode(FlushMode.NEVER);
    }

    public static String showMode() {
        if (trasactionIsActive()) {
            return sessionFactory.getCurrentSession().getFlushMode().toString();
        }
        return "NOT-ACTIVE";
    }

    /**
     * Starts a read/write hibernate transaction (FlushMode.AUTO)
     *
     */
    public static void beginWriteTransaction() {
        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().setFlushMode(FlushMode.AUTO);
    }

    // Determines whether the current transaction is active
    public static boolean trasactionIsActive() {
        return sessionFactory.getCurrentSession().getTransaction().isActive();
    }

    // Commits the hibernate transaction (if active).
    public static void commitTransaction() throws HibernateException {
        if (trasactionIsActive()) {
            sessionFactory.getCurrentSession().getTransaction().commit();
        }
    }

    // Rolls back current hibernate transaction (if active).
    public static void rollbackTransaction() {
        if (trasactionIsActive()) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    // Get's the current transaction's hashCode
    public static long transactionHashcode() {
        return sessionFactory.getCurrentSession().getTransaction().hashCode();
    }
}