/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cabecweb;
import Entidades.Detallees;
import Entidades.Pedidoweb;
import Mantenimiento.DetallesDAO;
import Mantenimiento.Facturacion.CabecwebDAO;
import Servicios.facturacion.Servicio_Documentos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class Servicio_PedidosWeb {

    private CabecwebDAO dao;
    private JTable tab_Pedidos;
    private DefaultTableModel dftm_Pedidos;
    private DefaultTableCellRenderer dtcr;
    private DateFormat df;
    private List<Cabecweb> lista;
    private List list;
    private int idItem;
    private DetallesDAO detDAO;
    private Servicio_Documentos servicioDoc;

    public Servicio_PedidosWeb(JTable tabla) {
        
        servicioDoc = new Servicio_Documentos();
        detDAO = new DetallesDAO();
        dao = new CabecwebDAO();
        df = new SimpleDateFormat("dd/MM/yyyy");
        tab_Pedidos = tabla;
        dftm_Pedidos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        String[] tituloColumna = {"ID", "ID CLIENTE", "FECHA", "ID USUARIO", "TOTAL", "ESTADO"};
        dftm_Pedidos.setColumnCount(tituloColumna.length);
        dftm_Pedidos.setRowCount(0);
        dftm_Pedidos.setColumnIdentifiers(tituloColumna);
        tab_Pedidos.setModel(dftm_Pedidos);
        ((JLabel) tab_Pedidos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tab_Pedidos.getSelectionModel().setValueIsAdjusting(false);
        tab_Pedidos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tab_Pedidos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                idItem = tab_Pedidos.getSelectedRow();
            }
        });
        lista = dao.Obtener_Lista_Objetos();
        listarPedidos();
    }

    public void atenderPedido() {
//        Cabecweb cabecweb = lista.remove(idItem);
//        Facturar(cabecweb);
//        listarPedidos();
    }

    public void RecharPedido() {
//        if (String.valueOf(tab_Pedidos.getValueAt(idItem, 5)).equals("PENDIENTE")) {
//            if (JOptionPane.showConfirmDialog(null, "Desea rechazar?", "Rechazar Pedido Web",
//                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                Cabecweb cabecweb = lista.get(idItem);
//                cabecweb.setEstado("r");
//                dao.Modificar_Objeto(cabecweb);
//                listarPedidos();
//            }
//        }
    }

    private String convierte(String estado) {
        switch (estado) {
            case "a":
                return "ACEPTADO";
            case "p":
                return "PENDIENTE";
            case "r":
                return "RECHAZADO";
            default:
                return null;
        }
    }

    private void listarPedidos() {
        dftm_Pedidos.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Cabecweb cabecweb = lista.get(i);
            Object[] objeto = {cabecweb.getIdCabweb(),
                cabecweb.getClientes().getIdcliente(),
                df.format(cabecweb.getFecha()),
                cabecweb.getUsuarios().getIdusuario(),
                cabecweb.getTotal(),
                convierte(cabecweb.getEstado())};
            dftm_Pedidos.addRow(objeto);
        }
        for (int i = 0; i < tab_Pedidos.getColumnCount(); i++) {
            tab_Pedidos.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

//    public ArrayList<Pedidoweb> getLista() {
//        ArrayList<Pedidoweb> listapedidos = new ArrayList();
//        if (idItem != null) {
//            for (int i = 0; i < idItem.length; i++) {
//                Object[] row = (Object[]) list.get(idItem[i]);
//                listapedidos.addAll(detDAO.listarPedidoWebxCliente((int) row[0]));
//            }
//            return listapedidos;
//        } else {
//            return null;
//        }
//    }

    public Cabecweb getCabecWeb(int id) {
        return servicioDoc.getCabecWeb(id);
    }

}
