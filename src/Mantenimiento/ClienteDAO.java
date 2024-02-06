package Mantenimiento;

import Entidades.Clientes;
import Servicios.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */
public class ClienteDAO extends GenericDAO<Clientes> {

    private Session sesion;
    private Transaction tx;

    public List getListaOrderNombre() {
        List<Clientes> lista = new LinkedList();
        try {
            iniciaOperacion();
//            System.out.println("C.nombre : " + c.nombre);
            String query = "select C.nombre "
                        + " from Clientes as C "
                        + " order by C.nombre ASC";
            lista = sesion.createQuery(query).list();
            tx.commit();

        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-getListaOrderNombre()) : " + e.getMessage());
            lista = null;
            
        } finally {
            if ( sesion != null ) {
                System.out.println("1.cerrando sesion de getListaOrderNombre()");
                sesion.close();
            }
        }
        return lista;
    }

    public List Consulta_clientes() {
//        List lst = new ArrayList();
//        try {
//            String query = "select c.ruc, "
//                        + " c.nombre, "
//                        + " c.plaza, "
//                        + " c.direccion, "
//                        + " c.telefonofijo, "
//                        + " c.telefonofijo2 "
//                        + " from Clientes c "
//                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_clientes()):" + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("2.cerrando sesion de Consulta_clientes()");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery("select c.ruc,c.nombre,c.plaza,c.direccion,c.telefonofijo, c.telefonofijo2 from Clientes c order by c.nombre asc").list();
    }

    public List Consulta_clientes_2() {
//        List lst = new ArrayList();
        String query = "select c.ruc,"
                    + " c.nombre, "
                    + " c.telefonofijo, "
                    + " c.telefonofijo2, "
                    + " c.direccion, "
                    + " c.plaza, "
                    + " c.contacto, "
                    + " c.idcliente "
                    + " from Clientes c "
                    + " order by c.nombre asc";
//        try {
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_clientes_2()):" + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("3.cerrando sesion de Consulta_clientes_2()");
//                sesion.close();
//            }
//        }
        return getHibernateTemplate().createQuery(query).list();
//        return lst;
    }

    public List Consulta_clientes_3() {
        System.out.println("Llegue aqui");
  //    return getHibernateTemplate().createQuery("select c.ruc,c.nombre,c.plaza,c.direccion,c.telefonofijo, c.telefonofijo2 from Clientes c like '%" + nombre + "%'  order by c.nombre asc").list();      
        //String nombre = 'HERRERA';
        return getHibernateTemplate().createQuery("select c.ruc,c.nombre,c.plaza,c.direccion,c.telefonofijo, c.telefonofijo2 from Clientes c  where c.nombre like '%\" + nombre + \"%'    order by c.nombre asc").list();
        
    }
    
    
    private void iniciaOperacion() throws HibernateException {
//        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
//            
//        } catch ( Exception e ) {
//            System.err.println("Error : " + e.getMessage());
//            
//        } finally {
//            if ( sesion != null ) {
//                sesion.close();
//            }
//        }
    }

    public Clientes obtenerClientePorNombre(String nombre) {
        Clientes cliente = new Clientes();
        try {
            iniciaOperacion();
            String query = "from Clientes where nombre='" + nombre + "'";
            cliente = (Clientes) sesion.createQuery(query).uniqueResult();
            tx.commit();

        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-obtenerClientePorNombre(nombre=" + nombre + ")) : " + e.getMessage());
            cliente = null;
            
        } finally {
            if ( sesion != null ) {
                System.out.println("4.cerrando sesion en obtenerClientePorNombre(nombre=" + nombre + ")");
//                sesion.close();
            }
        }
        return cliente;
    }
    
    public Clientes Obtener_Cliente_Por_IdCliente(int idCliente) {
        Clientes clientes = new Clientes();
        try {
            iniciaOperacion();

            clientes = (Clientes) sesion.createQuery("from Clientes where idCliente=" + idCliente).uniqueResult();            
            tx.commit();

        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-Obtener_Cliente_Por_IdCliente(idCliente=" + idCliente + ")) : " + e.getMessage());
//            cliente = null;
            
        } finally {
            if ( sesion != null ) {
                System.out.println("Valor Id Cliente : " + idCliente);
                System.out.println("5.cerrando sesion en Obtener_Cliente_Por_IdCliente(idCliente=" + idCliente + ")");
//                sesion.close();
            }
        }
        return clientes;
    }

    public Clientes getxNombre(String nombre) {
//        Clientes cliente = new Clientes();
//        try {
//            String query = "from Clientes c where c.nombre='" + nombre + "'";
//            cliente = (Clientes) sesion.createQuery(query).list().iterator().next();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-getxNombre(nombre=" + nombre + ")) : " + ex.getMessage());
//            cliente = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("6.cerrando sesion en getxNombre(nombre=" + nombre + ")");
//                sesion.close();
//            }
//        }
        return (Clientes) getHibernateTemplate().createQuery("from Clientes c where c.nombre='" + nombre + "'").list().iterator().next();
//        return cliente;
    }

    public Clientes getxRUC(String ruc) {
//        Clientes cliente = new Clientes();
//        try {
            String query = "from Clientes c "
                        + " where c.ruc = '" + ruc + "'";
//            cliente = (Clientes) sesion.createQuery(query).uniqueResult();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-getxRUC(ruc=" + ruc + ")) : " + ex.getMessage());
//            cliente = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("7.cerrando sesion en getxRUC(ruc=" + ruc + ")");
//                sesion.close();
//            }
//        }
        return (Clientes) getHibernateTemplate().createQuery(query).uniqueResult();
//        return cliente;
    }

    public int nextId() {
        Clientes cliente = null;
        try {
            String query = "from Clientes e "
                        + " order by e.idcliente desc";
//            cliente = (Clientes) sesion.createQuery(query).iterate().next();
            cliente = (Clientes) getHibernateTemplate().createQuery(query).iterate().next();
            session.getTransaction().commit();
            
        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-nextId()) : " + e.getMessage());
            
        } finally {
            if ( session != null ) {
                System.out.println("8.cerrando sesion en nextId()");
//                session.close();
            }
        }
        if ( Tamaño_Lista() == 0 ) {
//        if ( Tamanyo_Lista() == 0 ) {
            return 1;
            
        } else {
            return cliente.getIdcliente() + 1;
        }
    }
    
    public Long Tamanyo_Lista() {
        Long count = 0L;
        try {
            String query = "select count(*) from Clientes";
            count = (Long) sesion.createQuery(query).uniqueResult();
            
        } catch ( Exception ex ) {
            System.out.println("Error(ClienteDAO-Tamanyo_Lista()) : " + ex.getMessage());
            
        } finally {
            if ( sesion != null ) {
                System.out.println("9.cerrando sesion en Tamanyo_Lista()");
                sesion.close();
            }
        }
        return count.longValue();
    }

    public List getLista_nombre(String nombre) {
//        List lst = new ArrayList();
//        try {
            String query = "from Clientes t "
                        + " where t.nombre like '%" + nombre + "%' "
                        + " order by t.nombre ASC";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-getLista_nombre(nombre=" + nombre + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("10.cerrando sesion en getLista_nombre(nombre=" + nombre + ")");
//                sesion.close();
//            }
//        }
        return getHibernateTemplate().createQuery(query).list();
//        return lst;
    }
    public List getLista_plaza(String plaza) {
        return getHibernateTemplate().createQuery("from Clientes t where t.plaza like '%" + plaza + "%' order by t.plaza ASC").list();
    }

    public List obtenerProveedores() {
//        List lst = new ArrayList();
//        try {
            String query = "from Clientes c "
                        + " where c.tipo = 'P' "
                        + " or c.tipo ='A' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-obtenerProveedores()) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("11.cerrando sesion en obtenerProveedores()");
//                sesion.close();
//            }
//        }
        return getHibernateTemplate().createQuery(query).list();
//        return lst;
    }

    public List Consulta_nombre(String nombre) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.plaza, "
                        + " c.direccion, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.contacto "
                        + " from Clientes c"
                        + " where c.nombre like '%" + nombre + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_nombre(nombre=" + nombre + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("12.cerrando sesion en Consulta_nombre(nombre=" + nombre + ")");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }
    
    public List Consulta_RUC(String RUC) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.plaza, "
                        + " c.direccion, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.contacto "
                        + " from Clientes c "
                        + " where c.ruc like '%" + RUC + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_RUC(RUC=" + RUC + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("13.cerrando sesion en Consulta_RUC(RUC=" + RUC + ")");
//                sesion.close();
//            }
//        }
//        return lst;
          return getHibernateTemplate().createQuery(query).list();
    }
    
    public List Consulta_Plaza(String plaza) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.plaza, "
                        + " c.direccion, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.contacto "
                        + " from Clientes c "
                        + " where c.plaza like '%" + plaza + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_Plaza(plaza=" + plaza + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("14.cerrando sesion en Consulta_Plaza(plaza=" + plaza + ")");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }

    public List Consulta_plaza() {
//        List lst = new ArrayList();
//        try {
            String query = "select distinct c.plaza "
                        + " from Clientes c "
                        + " where c.plaza is not null "
                        + " order by c.plaza";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_plaza) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("15.cerrando sesion en Consulta_plaza()");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }

    public List Consulta_Nombre_Cliente() {
//        List lst = new ArrayList();
//        try {
            String query = "select distinct c.nombre "
                        + " from Clientes c "
                        + " order by c.nombre";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_Nombre_Cliente) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("16.cerrando sesion en Consulta_Nombre_Cliente()");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }
    
    public List Consulta_nombre_Filtro(String nombre) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.direccion, "
                        + " c.plaza, "
                        + " c.contacto, "
                        + " c.idcliente "
                        + " from Clientes c"
                        + " where c.nombre like '%" + nombre + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_nombre_Filtro(nombre=" + nombre + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("cerrando sesion en Consulta_nombre_Filtro");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }
    
    public List Consulta_RUC_Filtro(String RUC) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.direccion, "
                        + " c.plaza, "
                        + " c.contacto, "
                        + " c.idcliente "
                        + " from Clientes c "
                        + " where c.ruc like '%" + RUC + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_RUC_Filtro(RUC=" + RUC + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("cerrando sesion en Consulta_RUC_Filtro");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }
    
    public List Consulta_Plaza_Filtro(String plaza) {
//        List lst = new ArrayList();
//        try {
            String query = "select c.ruc, "
                        + " c.nombre, "
                        + " c.telefonofijo, "
                        + " c.telefonofijo2, "
                        + " c.direccion, "
                        + " c.plaza, "
                        + " c.contacto, "
                        + " c.idcliente "
                        + " from Clientes c "
                        + " where c.plaza like '%" + plaza + "%' "
                        + " order by c.nombre asc";
//            lst = sesion.createQuery(query).list();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-Consulta_Plaza_Filtro(plaza=" + plaza + ")) : " + ex.getMessage());
//            lst = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("cerrando sesion en Consulta_Plaza_Filtro");
//                sesion.close();
//            }
//        }
//        return lst;
        return getHibernateTemplate().createQuery(query).list();
    }

    public List<Clientes> Obtener_Lista_Objetos_OrderNombre() {
        List<Clientes> lista = new LinkedList();
        try {
            iniciaOperacion();
            String query = "from Clientes order by nombre ASC";
            lista = sesion.createQuery(query).list();
            tx.commit();

        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-Obtener_Lista_Objetos_OrderNombre()) : " + e.getMessage());
//            lista = null;
            
        } finally {
            if ( sesion != null ) {
                System.out.println("cerrando sesion en Obtener_Lista_Objetos_OrderNombre");
                sesion.close();
            }
        }
        return lista;
    }
    
    public List<Clientes> ObtenerSoloClientes() { // Sin proveedores
        List<Clientes> lista = new LinkedList();
        try {
            iniciaOperacion();
            System.out.println("Voy a ejecutar el query de clientes....");
            String query = "from Clientes "
                        + " where tipo like 'C' "
                        + " or tipo like 'A' "
                        + " order by nombre ASC";
            lista = sesion.createQuery(query).list();
            System.out.println("Si hizo el query.... clientes....");
            tx.commit();

        } catch ( Exception e ) {
            System.err.println("Error(ClienteDAO-ObtenerSoloClientes()) : " + e.getMessage());
//            lista = null;
            
        } finally {
            System.out.println("Sesion " + sesion);
            if ( sesion != null ) {
                System.out.println("cerrar sesion en ObtenerSoloClientes...");
                sesion.close();
            }
        }
        return lista;
    }
    
    public Clientes getxNombrePlaza(String nombre, String plaza) {
//        Clientes cliente = new Clientes();
//        try {
//            iniciaOperacion();
            String query = "from Clientes c "
                        + " where c.nombre='" + nombre + "' "
                        + " and c.plaza = '" + plaza + "'";
//            cliente = (Clientes) sesion.createQuery(query).list().iterator().next();
//            tx.commit();

//        } catch ( Exception e ) {
//            System.err.println("Error(ClienteDAO-getxNombrePlaza(nombre=" + nombre + ", plaza=" + plaza + ")) : " + e.getMessage());
//            cliente = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("cerrar sesion en ObtenerSoloClientes...");
//                sesion.close();
//            }
//        }
//        return cliente;
        return (Clientes) getHibernateTemplate().createQuery(query).list().iterator().next();
    }
    
    public Clientes getxNombreDireccion(String nombre, String direccion) {
//        Clientes cliente = new Clientes();
//        try {
            String query = "from Clientes c "
                        + " where c.nombre = '" + nombre + "' "
                        + " and c.direccion = '" + direccion + "'";
//            cliente = (Clientes) sesion.createQuery(query).list().iterator().next();
//            
//        } catch ( Exception ex ) {
//            System.out.println("Error(ClienteDAO-getxNombreDireccion(nombre=" + nombre 
//                            + ",direccion=" + direccion+ ")) : " + ex.getMessage());
//            cliente = null;
//            
//        } finally {
//            if ( sesion != null ) {
//                System.out.println("cerrar sesion getxNombreDireccion...");
//                sesion.close();
//            }
//        }
//        return cliente;
        return (Clientes) getHibernateTemplate().createQuery(query).list().iterator().next();
    }
    
}