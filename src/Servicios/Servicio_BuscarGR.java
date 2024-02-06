/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cabeces;
import Entidades.CabecesId;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class Servicio_BuscarGR {

    private JTable tab_GR;
    private DefaultTableModel dftm_GR;
    private Servicio_Cabeces servicio;
    private List<Cabeces> lista;
    private DateFormat df;
    private DefaultTableCellRenderer dtcr;
    private boolean[] mostrarItem;
    private int itemSelec;

    public Servicio_BuscarGR(JTable tab) {
        dftm_GR = new DefaultTableModel();
        tab_GR = tab;
        dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        df = new SimpleDateFormat("dd/MM/yyyy");
        servicio = new Servicio_Cabeces();
        lista = servicio.obtenerGuiasRemision();
//        System.out.println("lista:" + lista);
//        System.out.println("lista tamaño:" + lista.size());
        mostrarItem = new boolean[lista.size()];
        setMostrarItem();
        setTabla();
    }

    private void setMostrarItem() {
        for (int i = 0; i < mostrarItem.length; i++) {
            mostrarItem[i] = true;
        }
    }

    private void setTabla() {
        tab_GR.setModel(dftm_GR);
        String[] titulos = {"ITEM", "N° Guia", "Cliente", "Fecha"};
        dftm_GR.setColumnCount(titulos.length);
        dftm_GR.setRowCount(0);
        dftm_GR.setColumnIdentifiers(titulos);
        ((JLabel) tab_GR.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tab_GR.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    itemSelec = tab_GR.getSelectedRow();
                }
            }
        });
        listarGR();
    }

    private void listarGR() {
        dftm_GR.setRowCount(0);
        int i = 1;
        for (Cabeces c : lista) {
            if (mostrarItem[lista.indexOf(c)]) {
                CabecesId id = c.getId();
                Object[] tupla = {
                    i, 
                    c.getNroguia(),
                    c.getClientes().getNombre(),
                    df.format(c.getFechaemigr())
                };
                dftm_GR.addRow(tupla);
                i++;
            }
        }
        for (int j = 0; j < tab_GR.getColumnCount(); j++) {
            tab_GR.getColumnModel().getColumn(j).setCellRenderer(dtcr);
        }
    }

    public void filtraTabla(int opcion, Object valor) {
        switch (opcion) {
            case 0:
                Integer codigoGR = (Integer) valor;
                for (Cabeces c : lista) {
                    if (c.getNroguia().compareTo(codigoGR) != 0) {
                        mostrarItem[lista.indexOf(c)] = false;
                    }
                }
                break;
            case 1:
                String razonSocial = String.valueOf(valor);
                for (Cabeces c : lista) {
                    if (!c.getClientes().getNombre().equals(razonSocial)) {
                        mostrarItem[lista.indexOf(c)] = false;
                    }
                }
                break;
            case 2:
                String fecha = String.valueOf(valor);
                for (Cabeces c : lista) {
                    if (!df.format(c.getFechaemigr()).equals(fecha)) {
                        mostrarItem[lista.indexOf(c)] = false;
                    }
                }
                break;

        }
        listarGR();
        setMostrarItem();
    }

    public Cabeces procesar() {
        if (!lista.isEmpty()) {
            return lista.get(itemSelec);
        } else {
            return null;
        }
    }
}
