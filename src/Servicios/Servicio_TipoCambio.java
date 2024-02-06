package Servicios;

import Entidades.Tipocambio;

import Mantenimiento.TipoCambioDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Keily I.
 */
public class Servicio_TipoCambio {

    private TipoCambioDAO dao;
    private JTable tablatipocambio;
    private DefaultTableModel dftm_TipoCambio;
    private DateFormat df;
    private DefaultTableCellRenderer dtcr;
    private DefaultTableModel modelo;

    public Servicio_TipoCambio(JTable tablatipocambio) {
        dao = new TipoCambioDAO();
        this.tablatipocambio = tablatipocambio;
    }

    public Servicio_TipoCambio() {
    }

    public void actualizarTipoCambio(Tipocambio c) {
        dao.Modificar_Objeto(c);
    }
   
    public DefaultTableModel getDftm_TipoCambio() {
        return dftm_TipoCambio;
    }

    public void setDftm_TipoCambio(DefaultTableModel dftm_TipoCambio) {
        this.dftm_TipoCambio = dftm_TipoCambio;
    }

    public boolean Insertar(Tipocambio tipocambio) {
        return dao.Agregar_Objeto(tipocambio);
    }

    public Tipocambio obtenerTipoCambio(Date fecha) {
        TipoCambioDAO t = new TipoCambioDAO();
        return t.Obtener_Objeto(fecha);
    }
    
    public Tipocambio getTipoCambio(Date fecha) {
        return new TipoCambioDAO().getTipoCambio(fecha);
    }

    public Date obtenerNextId() {
        return dao.nextId();
    }

    public ArrayList<Tipocambio> listar_TipoCambio() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Tipocambio");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public int tamaño_TipoCambio() {
        int tamaño = listar_TipoCambio().size();
        return tamaño;
    }

    public boolean addTipoCambio(Tipocambio t) {
        return dao.Agregar_Objeto(t);

    }

    public int Listar_TipoCambio(DefaultTableModel modelo) {        
        this.modelo = modelo;
        List<Tipocambio> listacambio = dao.Obtener_Lista_Objetos_OrderNombre();
        for (int i = 0; i < listacambio.size(); i++) {

            String a = convertirFecha(listacambio.get(i).getFecha());
            Double b = listacambio.get(i).getValorcompra();
            Double c = listacambio.get(i).getValorventa();
            Object[] lineas = {a, b, c};
            modelo.addRow(lineas);
        }

        return listacambio.size();

    }

    public String convertirFecha(Date fechaconvertir) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(fechaconvertir);

        return fecha;

    }

    public Date convertiraDate(String fechaconvertir) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = sdf.parse(fechaconvertir);

        return fecha;

    }

    public ArrayList<Tipocambio> buscarTipoCambioxFecha(String desde, String hasta) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Tipocambio where fecha between '" + desde + "' and '" + hasta + "' order by fecha DESC");
        ArrayList lista = (ArrayList) q.list();        
        return lista;

    }

    public boolean buscarFecha_TipoCambio(String fecha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Tipocambio where fecha = '" + fecha + "'");
        ArrayList lista = (ArrayList) q.list();
        if (lista.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
