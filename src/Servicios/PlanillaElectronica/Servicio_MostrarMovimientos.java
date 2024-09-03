/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.PlanillaElectronica;

import Servicios.EmisionPlanillas.*;
import Entidades.Cabeces;
import Entidades.Detallees;
import Servicios.HistorialMov.Servicio_Historial;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 */
public class Servicio_MostrarMovimientos {

    private Servicio_Historial historial;
    private JTable tab_Movimientos;
    private DefaultTableModel dftm_Movimientos;
    private List<Detallees> listaMovimientos;
    private Detallees det;

    public Servicio_MostrarMovimientos(Detallees d, JTable tab) {
        historial = new Servicio_Historial();
        det = d;
        listaMovimientos = historial.getLista(d.getRepuestos().getId().getIdrepuesto());
        tab_Movimientos = tab;
        dftm_Movimientos = (DefaultTableModel) tab.getModel();
        listaTabla();
    }

    private void listaTabla() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaMovimientos.size(); i++) {
            Detallees detalle = listaMovimientos.get(i);
            Cabeces c = detalle.getCabeces();
            Object[] fila = {
                i + 1,
                c == null ? detalle.getOperaciones().getDescripcion() : TipoDoc(c.getId().getTipodoc()),
                c == null ? detalle.getNroingreso() : c.getId().getNrodocumento(),
                detalle.getCantentregada(),
                detalle.getPreciolista(),
                dt.format(detalle.getFecha())
            };
            dftm_Movimientos.addRow(fila);
        }
    }

    public String TipoDoc(String tipo) {
        switch (tipo) {
            case "01":
                return "FACTURA";
            case "02":
                return "BOLETA";
            case "03":
                return "NOTA DE CREDITO";
            case "04":
                return "NOTA DE DEBITO";
            case "05":
                return "GUIA DE REMISION";
            case "06":
                return "LETRA";
            case "07":
                return "PROFORMA";
            case "08":
                return "INGRESO POR  COMPRA";
            case "09":
                return "INGRESO POR IMPORTACION";
            case "10":
                return "INGRESO POR DEVOLUCION";
            case "11":
                return "INGRESOS VARIOS";
            case "12":
                return "SALIDAS VARIAS";
            default : 
                return "";
        }
    }
}
