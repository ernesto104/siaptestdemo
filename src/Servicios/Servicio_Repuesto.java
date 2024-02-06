package Servicios;

import Entidades.Repuestos;
import Mantenimiento.RepuestosDAO;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Repuesto {

    RepuestosDAO RepuestosDao;
    DefaultTableModel modelo;

    public Servicio_Repuesto() {
        RepuestosDao = new RepuestosDAO();
    }

    public void Modifica_Repuesto(Repuestos r) {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        repuestoDAO.Modificar_Objeto(r);
    }

    public int Totalrepuestos() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.totalrepuestosxsize();
    }

    private void llenarTabla(List lista, DefaultTableModel table) {
        Iterator ite = lista.iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[8];
            Repuestos temp = (Repuestos) ite.next();
            row[0] = temp.getEquipos().getIdequipo();
            row[1] = temp.getCodrepuesto();
            row[2] = temp.getCodigoseg();
            row[3] = temp.getDescripcion();
            row[4] = temp.getDescrmodelo();
            row[5] = temp.getStock();
            row[6] = temp.getPreciolista();
            row[7] = temp.getMarca();
            table.addRow(row);

        }
    }

    public void ListarxMarca(DefaultTableModel modelo, String nombre) {
        RepuestosDAO c = new RepuestosDAO();
        llenarTabla(c.getLista_marca(nombre), modelo);
    }

    public void ListarxDescripcion(DefaultTableModel modelo, String nombre) {
        RepuestosDAO c = new RepuestosDAO();
        llenarTabla(c.getLista_descripcion(nombre), modelo);
    }

    public void ListarxDescrmodelo(DefaultTableModel modelo, String nombre) {
        RepuestosDAO c = new RepuestosDAO();
        llenarTabla(c.getLista_descrimodelo(nombre), modelo);
    }
    
    
    public Repuestos obtenerRepuesto(int id) {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.obtenerRepuesto(id);
    }

    public Repuestos obtenerRepuestoPorNombre(String nombre) {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.obtenerRepuestoNombre(nombre);
    }

    public void getListaPorMarca(String marca, DefaultTableModel modelo) {
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> lista = rDAO.getListaPorMarca(marca);

        this.modelo = modelo;

        for (int i = 0; i < lista.size(); i++) {

            int idLinea = lista.get(i).getEquipos().getIdequipo();
            String nroParte = lista.get(i).getCodrepuesto();
            String codSeg = lista.get(i).getCodigoseg();
            String descripcion = lista.get(i).getDescripcion();
            String descrModelo = lista.get(i).getDescrmodelo();
            int stock = lista.get(i).getStock();
            double precioLista = lista.get(i).getPreciolista();
            marca = lista.get(i).getMarca();

            Object[] lineas = {idLinea, nroParte, codSeg, descripcion, descrModelo, stock, precioLista, marca};
            modelo.addRow(lineas);
        }

    }

    public void getListaPorGrupo(String grup, DefaultTableModel modelo) {
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> lista = rDAO.getListaPorGrupo(grup);

        this.modelo = modelo;

        for (int i = 0; i < lista.size(); i++) {

            int idLinea = lista.get(i).getEquipos().getIdequipo();
            String nroParte = lista.get(i).getCodrepuesto();
            String codSeg = lista.get(i).getCodigoseg();
            String descripcion = lista.get(i).getDescripcion();
            String descrModelo = lista.get(i).getDescrmodelo();
            int stock = lista.get(i).getStock();
            double precioLista = lista.get(i).getPreciolista();
            String marca = lista.get(i).getMarca();

            Object[] lineas = {idLinea, nroParte, codSeg, descripcion, descrModelo, stock, precioLista, marca};
            modelo.addRow(lineas);
//            System.out.println("aaaa");
        }
    }

    public void getListaPorIDLinea(int idL, DefaultTableModel modelo) {
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> lista = rDAO.getListaPorIDLinea(idL);

        this.modelo = modelo;

        for (int i = 0; i < lista.size(); i++) {

            int idLinea = lista.get(i).getEquipos().getIdequipo();
            String nroParte = lista.get(i).getCodrepuesto();
            String codSeg = lista.get(i).getCodigoseg();
            String descripcion = lista.get(i).getDescripcion();
            String descrModelo = lista.get(i).getDescrmodelo();
            int stock = lista.get(i).getStock();
            double precioLista = lista.get(i).getPreciolista();
            String marca = lista.get(i).getMarca();

            Object[] lineas = {idLinea, nroParte, codSeg, descripcion, descrModelo, stock, precioLista, marca};
            modelo.addRow(lineas);
//            System.out.println("aaaa");
        }
    }

    public void getListaTodos(DefaultTableModel modelo) {
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> lista = rDAO.getLista_todos_orderdescrip();

        this.modelo = modelo;

        for (int i = 0; i < lista.size(); i++) {

            int idLinea = lista.get(i).getEquipos().getIdequipo();
            String nroParte = lista.get(i).getCodrepuesto();
            String codSeg = lista.get(i).getCodigoseg();
            String descripcion = lista.get(i).getDescripcion();
            String descrModelo = lista.get(i).getDescrmodelo();
            int stock = lista.get(i).getStock();
            double precioLista = lista.get(i).getPreciolista();
            String marca = lista.get(i).getMarca();

            Object[] lineas = {idLinea, nroParte, codSeg, descripcion, descrModelo, stock, precioLista, marca};
            modelo.addRow(lineas);
//            System.out.println("aaaa");
        }
    }

    public void prepararTomaInventario() {
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> lista = rDAO.Obtener_Lista_Objetos();

        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("Modificando repuesto: " + (i + 1));
            Repuestos r = new Repuestos();
            r = lista.get(i);
            if (lista.get(i).getUbicalmacen() == null) {
                r.setUbicalmacen("");
            }
            r.setInventario(0);
            rDAO.Modificar_Objeto(r);
        }
    }

    public Repuestos getRepuesto(String name) {
        Repuestos rep;
        rep = RepuestosDao.buscarXNombre(name).get(0);
        return rep;
    }

    public List<Repuestos> getListaOrderCodRep() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.getListaOrderCodRep();
    }

    public List<Repuestos> getListaOrderDescrip() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.getListaOrderDescrip();
    }

    public List<Repuestos> getListaOrderIdLinea() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.getListaOrderIdLinea();
    }

    public List<Repuestos> getListaOrderUbicAlm() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.getListaOrderUbicAlm();
    }
}
