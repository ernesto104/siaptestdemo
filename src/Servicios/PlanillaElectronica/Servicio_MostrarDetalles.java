/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.PlanillaElectronica;

import Servicios.EmisionPlanillas.*;
import Entidades.Cabeces;
import Entidades.Cabecsalvar;
import Entidades.Detallees;
import Mantenimiento.DetallesDAO;
import Presentacion.EmisionPlanillas.UI_MostrarMovimientos;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 */
public class Servicio_MostrarDetalles {
    
    private JTable tab_Detalles;
    private DefaultTableModel dftm_Detalles;
    private DefaultTableCellRenderer dtcr_Detalles;
    private DetallesDAO det;
    private List<Detallees> listaDetalles;
    HashMap<String,String> documentos;
    Cabeces c ;
    Cabecsalvar cs;
    
    public Servicio_MostrarDetalles(Object o, JTable tab) {
        tab_Detalles = tab;
        det = new DetallesDAO();
        if(o instanceof Cabeces){
//            System.out.println("111");
            c = (Cabeces)o;
            listaDetalles = det.listarPorCabeces(c);
        }else{
//            System.out.println("222");
            cs = (Cabecsalvar)o;
            listaDetalles = det.listarporSK(cs);
        }
        
        
        dftm_Detalles = (DefaultTableModel) tab.getModel();
        listaDetalles();
    }

    
    private void listaDetalles() {
        for (int i = 0; i < listaDetalles.size(); i++) {
            Detallees d = listaDetalles.get(i);
            Object[] fila = {d.getRepuestos().getEquipos().getIdequipo(), d.getRepuestos().getCodrepuesto(),d.getRepuestos().getDescripcion(), d.getCantentregada(),
                d.getDescuento1(), d.getDescuento2(), d.getDescuento3(), d.getDescuento4()};
            dftm_Detalles.addRow(fila);
        }
    }
    
    public void mostrarMovimientos() {
        int fila = tab_Detalles.getSelectedRow();
        if (fila>=0) {
            UI_MostrarMovimientos ui = new UI_MostrarMovimientos(listaDetalles.get(fila),documentos);
            ui.setLocationRelativeTo(null);
            ui.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione al menos un repuesto");
        }
    }

    public void setDocumentos(HashMap<String, String> documentos) {
        this.documentos = documentos;
    }
    
}
