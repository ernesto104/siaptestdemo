/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cabecsalvar;
import Entidades.Clientes;
import Entidades.Detallees;
import Mantenimiento.DetallesDAO;
import java.util.ArrayList;
import java.util.List;
import Servicios.facturacion.Servicio_Documentos;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 */
public class Servicio_Salvar {

    private JTable tab_Salvar;
    private DefaultTableModel dftm_Salvar;
    private int[] salvarSelec;
    private Clientes cliente;
    private Servicio_Documentos servicioDoc;
    private List lista;
    private DefaultTableCellRenderer dtcr;
    private DetallesDAO detDAO;

    public Servicio_Salvar(JTable sv) {
        tab_Salvar = sv;
        servicioDoc = new Servicio_Documentos();
        detDAO = new DetallesDAO();
        setTabSalvar();
    }

    public void setCliente(Clientes c) {
        cliente = c;
        dftm_Salvar.setRowCount(0);
        if (c != null) {
            lista = servicioDoc.getListarPorClientesNoProces(cliente.getIdcliente());
            listarSalvar();
        } else {
            lista = null;
        }
    }

    private void setTabSalvar() {
        dftm_Salvar = new DefaultTableModel();
        dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tab_Salvar.setModel(dftm_Salvar);
//        String[] tituloColumna = {"ITEM", "IDSALIDA","FECHA", "VALORVENTA", "TOTAL"}; 
        String[] tituloColumna = {"ITEM", "CODIGO SALIDA","FECHA", "VALOR VENTA", "TOTAL"}; 
        dftm_Salvar.setColumnCount(tituloColumna.length);
        dftm_Salvar.setRowCount(0);
        dftm_Salvar.setColumnIdentifiers(tituloColumna);
        ((JLabel) tab_Salvar.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tab_Salvar.getSelectionModel().setValueIsAdjusting(false);
        tab_Salvar.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tab_Salvar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                salvarSelec = tab_Salvar.getSelectedRows();
            }
        });
    }

    private void listarSalvar() {
        for (int i = 0; i < lista.size(); i++) {          
            Object[] row = (Object[]) lista.get(i);
            //Cabecsalvar c = listaSalvar.get(i);
            Object[] objeto = {i, row[1], row[9],row[10], row[12]};
            dftm_Salvar.addRow(objeto);
        }
        for (int i = 0; i < tab_Salvar.getColumnCount(); i++) {
            tab_Salvar.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public ArrayList<Detallees> getLista() {
        ArrayList<Detallees> listaRetorno = new ArrayList();
        if (salvarSelec != null) {
            for (int i = 0; i < salvarSelec.length; i++) {
                Object[] row = (Object[]) lista.get(salvarSelec[i]);
                
                listaRetorno.addAll(detDAO.listarPorCabecsalvarCliente((int)row[0], cliente.getIdcliente()));
            }
            return listaRetorno;
        } else {
            return null;
        }
    }

    public void limpiarTabla() {
        tab_Salvar.clearSelection();
    }
    
    public Cabecsalvar getCabecSalvar(int id){
        return servicioDoc.getCabecSalvar(id);
    }
}
