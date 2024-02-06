/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Operaciones;
import Mantenimiento.OperacionesDAO;
import Presentacion.FREP004;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class Servicio_Operaciones {

   private OperacionesDAO dao;
    private JTable tab_Operaciones;
    private DefaultTableModel dftm_Operaciones;
    private DefaultTableCellRenderer dtcr;
    DefaultTableModel modelo;
    
    public Servicio_Operaciones() {
        dao = new OperacionesDAO();
        
    }
    
    

    public Servicio_Operaciones(JTable tab) {
        dao = new OperacionesDAO();
        this.tab_Operaciones = tab;
    }

    public JTable getTab_Operaciones() {
        return tab_Operaciones;
    }

    public void setTab_Operaciones(JTable tab_Operaciones) {
        this.tab_Operaciones = tab_Operaciones;
    }

    public DefaultTableModel getDftm_Operaciones() {
        return dftm_Operaciones;
    }

    public void setDftm_Operaciones(DefaultTableModel dftm_Operaciones) {
        this.dftm_Operaciones = dftm_Operaciones;
    }

    private void listarOperaciones() {
        dftm_Operaciones.setRowCount(0);
        List<Operaciones> lista = dao.Obtener_Lista_Objetos();
        for (int i = 0; i < lista.size(); i++) {
            Operaciones operacion = lista.get(i);
            Object[] objeto = {operacion.getIdoperacion(),
                operacion.getDescripcion(),
                operacion.getVerstock(),
                operacion.getCostos(),
                operacion.getTransaccion()};
            dftm_Operaciones.addRow(objeto);
        }
        for (int i = 0; i < tab_Operaciones.getColumnCount(); i++) {
            tab_Operaciones.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        tab_Operaciones.setModel(dftm_Operaciones);
    }

    public Operaciones obtenerOperacion(int id) {
        return dao.Obtener_Objeto(id);
    }

    public boolean insertarOperacion(Operaciones operacion) {
        boolean aux = dao.Agregar_Objeto(operacion);
      //  listarOperaciones();
        return aux;
    }

    public boolean modificarOperacion(Operaciones operacion) {
        boolean aux = dao.Modificar_Objeto(operacion);
        //listarOperaciones();
        return aux;
    }

    public boolean eliminarOperacion(Operaciones operacion) {
        dao = new OperacionesDAO();
        boolean aux = dao.Eliminar_Objeto(operacion);
        //listarOperaciones();
        return aux;
    }

    public int obtenerNextId() {
        return dao.nextId();
    }

    public Operaciones getOperaciones_por_nombre(String codigo) {
        Operaciones op = new Operaciones();
        op = dao.Obtener_Objeto_por_nombre(codigo);
        return op;
    }
    
    
    public int Listar_Operaciones(DefaultTableModel modelo) {
        this.modelo = modelo;
        List<Operaciones> listaOperaciones = dao.Obtener_Lista_Objetos_OrderNombre();
  //      System.out.println("Hay " + listaClientes.size() + " contactos en la base de datos");

        for (int i = 0; i < listaOperaciones.size(); i++) {

            String b = listaOperaciones.get(i).getCodigooperacion();
            String b1 = listaOperaciones.get(i).getDescripcion();
            String c = listaOperaciones.get(i).getVerstock();
            String d = listaOperaciones.get(i).getCostos();
            String e = listaOperaciones.get(i).getTransaccion();
            Object[] lineas = {b, b1, c, d, e};
            modelo.addRow(lineas);
        }

        return listaOperaciones.size();
    }

    public Operaciones obtener_Operacion_Codigo(String codigo) {
        Operaciones operaciones;
        operaciones = dao.Obtener_Operacion_Codigo(codigo);
        return operaciones;
    }
}
