package Servicios;

import Entidades.Vendedores;
import Mantenimiento.VendedorDAO;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Vendedor {

    VendedorDAO vendedorDAO;
    JTable tblVendedor;
    DefaultTableModel modelo;
    int ultimo_id;

    public Servicio_Vendedor() {
        vendedorDAO = new VendedorDAO();
    }
    
    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }

    public List getList() {
        return vendedorDAO.Obtener_Lista_Objetos();
    }

    public Servicio_Vendedor(JTable tblVendedores) {
        vendedorDAO = new VendedorDAO();
        this.tblVendedor = tblVendedores;
    }

  public int Listar_vendedores(DefaultTableModel modelo) {

        this.modelo = modelo;
        List<Vendedores> listaVendedores = vendedorDAO.Obtener_Lista_Objetos_OrderNombre();
        
        for (int i = 0; i < listaVendedores.size(); i++) {

            if (i == (listaVendedores.size() - 1)) {
                ultimo_id = listaVendedores.get(i).getIdvendedor();
            }

            String b = listaVendedores.get(i).getNombre();
            String c = listaVendedores.get(i).getDocumento();
            String d = listaVendedores.get(i).getDireccion();
            String e = listaVendedores.get(i).getTelefono();
            String f = listaVendedores.get(i).getCelular();
            String g = listaVendedores.get(i).getEmail();
            Double h;
            Double m;
            if (listaVendedores.get(i).getPorcentajelima() < 1) {
                h = (listaVendedores.get(i).getPorcentajelima() * 100);
            } else {
                h = (listaVendedores.get(i).getPorcentajelima());
            }
            if (listaVendedores.get(i).getPorcentajeprov() < 0) {
                m = (listaVendedores.get(i).getPorcentajeprov() * 100);
            } else {
                m = (listaVendedores.get(i).getPorcentajeprov());
            }
            int n = listaVendedores.get(i).getIdvendedor();

            Object[] vendedores = {b, c, d, e, f, g, h, m, n};
            modelo.addRow(vendedores);
        }

        return listaVendedores.size();

    }

    public Vendedores obtener_Vendedor(int id) {
        return vendedorDAO.Obtener_Objeto(id);
    }

    public boolean insertar_Vendedor(Vendedores vendedor) {
        return vendedorDAO.Agregar_Objeto(vendedor);
    }

    public boolean actualizar_Vendedor(Vendedores vendedor) {
        return vendedorDAO.Modificar_Objeto_Merge(vendedor);
    }

    public boolean eliminar_Vendedor(Vendedores vendedor) {
        return vendedorDAO.Eliminar_Objeto(vendedor);
    }

    public int nextID() {
        return (int) vendedorDAO.nextId();
    }

    public List<Vendedores> Listar_vendedores() {
        return vendedorDAO.Obtener_Lista_Objetos();
    }
    
    public Vendedores obtener_Vendedor_Nombre(String nombVe) {
        return vendedorDAO.Obtener_Vendedor_PorNombre(nombVe);
    }
    
    public List<Vendedores> Obtener_Lista_Objetos_OrderNombre(){
        return vendedorDAO.Obtener_Lista_Objetos_OrderNombre();
    }
    
}
