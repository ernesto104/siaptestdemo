package Servicios;

import Entidades.Transportistas;
import java.util.List;
import Mantenimiento.TransportistaDAO;
import Presentacion.FREP005;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Transportista {

    TransportistaDAO transportistaDao;
    FREP005 it;

    public Servicio_Transportista() {
    }

    public List<Transportistas> Listar_Tranportistas() {
        return new TransportistaDAO().getTransportistasOrdenadosXNombre();
    }

    public Servicio_Transportista(FREP005 it) {
        transportistaDao = new TransportistaDAO();
        this.it = it;
    }

    public DefaultListModel Listar_transportista(DefaultListModel model) {
        List<Transportistas> listaTransportistas = transportistaDao.Obtener_Lista_Objetos();

        for (int i = 0; i < listaTransportistas.size(); i++) {
            model.addElement(listaTransportistas.get(i).getNombre());
        }
        return model;
    }

    public Transportistas Obtener_Transportista_Por_Nombre(String nombre) {
        Transportistas transp = new Transportistas();
        transportistaDao = new TransportistaDAO();
        transp = transportistaDao.obtenerTransportistaPorNombre(nombre);

        return transp;
    }
    public Transportistas Obtener_Transportista_Por_Ruc(String ruc){
        transportistaDao = new TransportistaDAO();
        return transportistaDao.obtenerTransportistaPorRuc(ruc);
    }

    public Long getSize() {
        return transportistaDao.Tamaño_Lista();
    }

    public void ListarxNombre(String nombre) {
        DefaultTableModel table = (DefaultTableModel) it.tb_transp.getModel();
        llenarTabla(transportistaDao.getLista_nombre(nombre), table);
    }

    private void llenarTabla(List lista, DefaultTableModel table) {
        Iterator ite = lista.iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[5];
            Transportistas temp = (Transportistas) ite.next();
            row[0] = temp.getNombre();
            row[1] = temp.getRuc();
            row[2] = temp.getDireccion();
            row[3] = temp.getTelefono();
            row[4] = temp.getTelefono2();
            table.addRow(row);
        }
    }

    public Transportistas getTransportista(int id) {
        Transportistas transportista;
        transportista = transportistaDao.Obtener_Objeto(id);
        return transportista;
    }

    public boolean addTransportista(Transportistas t) {
        return transportistaDao.Agregar_Objeto(t);
    }
    public boolean RucRepetido(String Ruc){
        int ruc = transportistaDao.RucRepetido(Ruc);
        if(ruc > 0)return true;
        return false;
    }
    public void listar() {
        DefaultTableModel table = (DefaultTableModel) it.tb_transp.getModel();
        llenarTabla(transportistaDao.GetTransportistas(), table);
    }

    public boolean actualizarTransportista(Transportistas t) {
        return transportistaDao.Modificar_Objeto(t);
    }

    public boolean borrarTransportista(Transportistas t) {
        return transportistaDao.Eliminar_Objeto(t);
    }

    public List Listar_Transportistas_Order_Asc_Nombre() {
        TransportistaDAO transportistaDAO = new TransportistaDAO();
        return transportistaDAO.getListaOrderNombre();
    }
  
}