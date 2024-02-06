/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Programas;
import Mantenimiento.ProgramasDAO;
import Presentacion.FREP016;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class Servicio_Programas {

    private ProgramasDAO dao;
    private JTable tab_Programas;
    private DefaultTableModel dftm_Programas;
    private DefaultTableCellRenderer dtcr;
    public FREP016 ip;
    
    public Servicio_Programas(FREP016 it){
        dao = new ProgramasDAO();
        this.ip=it;    
    }
    
    public Servicio_Programas() {
        dao = new ProgramasDAO();
    }

    public JTable getTab_Programas() {
        return tab_Programas;
    }

    public void setTab_Programas(JTable tab_Programas) {
        this.tab_Programas = tab_Programas;
    }

    public DefaultTableModel getDftm_Programas() {
        return dftm_Programas;
    }

    public void setDftm_Programas(DefaultTableModel dftm_Programas) {
        this.dftm_Programas = dftm_Programas;
    }

    public void listarProgramas() {
        dftm_Programas.setRowCount(0);
        List<Programas> lista = dao.Obtener_Lista_Objetos();
        for (int i = 0; i < lista.size(); i++) {
            Programas programa = lista.get(i);
            Object[] objeto = {programa.getIdprograma(), programa.getCodprograma(), programa.getDescripcion()};
            dftm_Programas.addRow(objeto);
        }
        for (int i = 0; i < tab_Programas.getColumnCount(); i++) {
            tab_Programas.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
        tab_Programas.setModel(dftm_Programas);
    }    
    
    public void Listar_Programas() {

       DefaultTableModel table = (DefaultTableModel) ip.tablaProgramas.getModel();
       Iterator ite = dao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[2];
            Programas pro = (Programas) ite.next();            
            row[0] = pro.getCodprograma();
            row[1] = pro.getDescripcion();
            table.addRow(row);
        }      
      }
    
    public int nextId() {        
        return (int) dao.nextId();
    }
    
    public boolean addProgramas(Programas p) {
        return dao.Agregar_Objeto(p);        
    }
    
    public boolean borrarProgramas(Programas p) {
        return dao.Eliminar_Objeto(p);
    }
    
    public Programas getProgramas(int id) {
        Programas pr;        
        pr = dao.Obtener_Objeto(id);
        return pr;
    }
    
    public Programas getProgramas_codigo(String cod) {
        return dao.getPrograma_cod(cod);
    }
    
    public Programas obtenerPrograma(int id) {
        return dao.Obtener_Objeto(id);
    }

    public boolean insertarPrograma(Programas programa) {
        boolean aux = false;
        if (dao.getPrograma_cod(programa.getCodprograma()) == null) {
            aux = dao.Agregar_Objeto(programa);
            listarProgramas();
        }
        return aux;
    }

    public boolean modificarPrograma(Programas programa) {
        boolean aux = dao.Modificar_Objeto(programa);
        listarProgramas();
        return aux;
    }

    public boolean eliminarPrograma(Programas programa) {
        boolean aux = dao.Eliminar_Objeto(programa);
        listarProgramas();
        return aux;
    }

    public int obtenerNextId() {
        return dao.nextId();
    }
    
    public List<Programas> listarProgramas(int idRol) {
        return new ProgramasDAO().listarProgramas(idRol);
    }
}
