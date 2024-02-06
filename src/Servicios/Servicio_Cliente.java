package Servicios;

import Entidades.Clientes;
import Mantenimiento.ClienteDAO;
import Mantenimiento.SucursalesDAO;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Cliente {

    ClienteDAO clienteDAO;
    JTable tblClientes;
    DefaultTableModel modelo;
    int ultimo_id;

    public Servicio_Cliente() {
    }

    public int nextId() {
        return (int) clienteDAO.nextId();
    }

    public int getUltimo_id() {
        return ultimo_id;
    }

    public Clientes getxNombre(String nombre) {
        return clienteDAO.getxNombre(nombre);
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }

    public List getList() {
        return clienteDAO.Obtener_Lista_Objetos();
    }

    public Servicio_Cliente(JTable tblClientes) {
        clienteDAO = new ClienteDAO();
        this.tblClientes = tblClientes;
    }

    public int Listar_clientes(DefaultTableModel modelo) {

        this.modelo = modelo;
        List<Clientes> listaClientes = clienteDAO.Obtener_Lista_Objetos_OrderNombre();

        for (int i = 0; i < listaClientes.size(); i++) {

            if (i == (listaClientes.size() - 1)) {
                ultimo_id = listaClientes.get(i).getIdcliente();
            }
            String b = listaClientes.get(i).getNombre();
            String c = listaClientes.get(i).getDireccion();
            String e = listaClientes.get(i).getRuc();
            
            String b1 = listaClientes.get(i).getContacto();
            b1 = "null".equals(b1) ? "" : b1;
            
            String d = listaClientes.get(i).getPlaza();
            d = "null".equals(d) ? "" : d;
            
            
            String f = listaClientes.get(i).getTelefonofijo();
            f = "null".equals(f) ? "" : f;
            
            String g = listaClientes.get(i).getTelefonocel();
            g = "null".equals(g) ? "" : g;
            
            String h = String.valueOf(listaClientes.get(i).getIdcliente());
            Object[] clientes = {b, b1, c, d, e, f, g, h};
            modelo.addRow(clientes);
        }

        return listaClientes.size();

    }

//    public Clientes obtener_Cliente(int id) {
//        return clienteDAO.Obtener_Objeto(id);
//    }
    
    public Clientes obtener_Cliente(int id) {
        return clienteDAO.Obtener_Objeto(id);
    }

    public boolean insertar_Cliente(Clientes cliente) {
        return clienteDAO.Agregar_Objeto(cliente);
    }

    public boolean actualizar_Cliente(Clientes cliente) {
        return clienteDAO.Modificar_Objeto(cliente);
    }

    public List get_ListaClientes() {
        return clienteDAO.Consulta_clientes();
    }

    public List get_ListaClientes_2() {
        return clienteDAO.Consulta_clientes_2();
    }
    
    public List get_ListaClientes_3() {
        return clienteDAO.Consulta_clientes_3();
    }    

    public boolean eliminar_Cliente(Clientes cliente) {
        clienteDAO.Eliminar_Objeto(cliente);
        return true;
    }

    public List Listar_Clientes_Order_Asc_Nombre() {
//        ClienteDAO clienteDAO = new ClienteDAO();
        return new ClienteDAO().getListaOrderNombre();

    }

    public Clientes Obtener_Cliente_Por_Nombre(String nombre) {
        clienteDAO = new ClienteDAO();
        Clientes client = new Clientes();
        client = clienteDAO.obtenerClientePorNombre(nombre);

        return client;
    }

    public List getSucursal_Cliente(int cliente_id) {
        return new SucursalesDAO().getSucursal_Cliente(cliente_id);
    }

    public List getCliente_Nombre(String nombre) {
        return clienteDAO.Consulta_nombre(nombre);
    }

    public List getCliente_RUC(String RUC) {
        return clienteDAO.Consulta_RUC(RUC);
    }

    public List getCliente_Plaza(String plaza) {
        return clienteDAO.Consulta_Plaza(plaza);
    }
    
    public List getCliente_Nombre_Filtro(String nombre) {
        return clienteDAO.Consulta_nombre_Filtro(nombre);
    }

    public List getCliente_RUC_Filtro(String RUC) {
        return clienteDAO.Consulta_RUC_Filtro(RUC);
    }

    public List getCliente_Plaza_Filtro(String plaza) {
        return clienteDAO.Consulta_Plaza_Filtro(plaza);
    }

    public List Listar_clientes() {
        return clienteDAO.Obtener_Lista_Objetos();
    }

    public void ListarxNombre(DefaultTableModel table, String nombre) {
        ClienteDAO c = new ClienteDAO();
        llenarTabla(c.getLista_nombre(nombre), table);
    }
    
    public void ListarxPlaza(DefaultTableModel table, String plaza) {
        ClienteDAO c = new ClienteDAO();
        llenarTabla(c.getLista_plaza(plaza), table);
    } 
    
    private void llenarTabla(List lista, DefaultTableModel table) {
        Iterator ite = lista.iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[8];
            Clientes temp = (Clientes) ite.next();
            row[0] = temp.getNombre();
            row[1] = temp.getContacto();
            row[2] = temp.getDireccion();
            row[3] = temp.getPlaza();
            row[4] = temp.getRuc();
            row[5] = temp.getTelefonofijo();
            row[6] = temp.getTelefonocel();
            row[7] = temp.getIdcliente();
            table.addRow(row);
        }
    }

    public List Consulta_plaza() {
        return clienteDAO.Consulta_plaza();
    }

    public Clientes getCliente_x_RUC(String ruc) {
        return clienteDAO.getxRUC(ruc);
    }

    public List Consulta_Nombre_Cliente() {
        return clienteDAO.Consulta_Nombre_Cliente();
    }
    
    public boolean Modificar_Cliente(Clientes c) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            Clientes cliente = (Clientes) sesion.merge(c);
            sesion.update(cliente);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            System.out.println("error:" + e.getMessage());
            tx.rollback();
            return false;
        }
    }
    
    public Clientes Obtener_Cliente_Por_IdCliente(int idCliente) {
        clienteDAO = new ClienteDAO();
        Clientes client = new Clientes();
        client = clienteDAO.Obtener_Cliente_Por_IdCliente(idCliente);
        return client;
    }

}
