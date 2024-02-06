/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Clientes;
import Entidades.Detallees;
import Entidades.Repuestos;
import Entidades.Vendedores;
import Mantenimiento.ControlDAO;
import Mantenimiento.DetallesDAO;
import com.toedter.calendar.JDateChooser;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class Servicio_FacturarSV {

    private Clientes cliente;
    private Vendedores vendedor;
    private ControlDAO daoControl;
    private DetallesDAO daoDetalles;
    private Servicio_Documentos servicioDoc;
    private JDateChooser fecha;
    private JTable tab_Salvar;
    private JTable tab_Detalle;
    private JTextField txt_TotalBruto;
    private JTextField txt_TotalDesc;
    private JTextField txt_IGV;
    private JTextField txt_Total;
    private JButton btn_Inserta;
    private JButton btn_Extrae;
    private DefaultTableModel dftm_Salvar;
    private DefaultTableModel dftm_Detalle;
    private DefaultTableCellRenderer dtcr;
    private List<Detallees> listaDetalles;
    private List<Cabecsalvar> listaSalvar;
    private DecimalFormat df;
    private boolean[] mostrarSalvar;
    private int[] salvarSelec;
    private int[] detallesSelec;
    private int colCantidad = 6;
    private int colPrecio = 7;
    private int colDescInicio = 8;
    private int colDescFin = 11;
    private int colTotal = 12;
    private double igv = 18;
    private double total;

    public Servicio_FacturarSV(
            JTable tSalvar, JTable tRepuestos,
            JTextField txTotalBruto, JTextField txTotalDesc,
            JTextField txIGV, JTextField txTotal,
            JButton inserta, JButton extrae, JDateChooser fecha) {
        this.fecha = fecha;
        btn_Inserta = inserta;
        btn_Inserta.setEnabled(false);
        btn_Extrae = extrae;
        btn_Extrae.setEnabled(false);
        txt_TotalBruto = txTotalBruto;
        txt_TotalDesc = txTotalDesc;
        txt_IGV = txIGV;
        txt_Total = txTotal;
        habilitaCampos(false);
        tab_Salvar = tSalvar;
        dftm_Salvar = new DefaultTableModel();
        tab_Detalle = tRepuestos;
        dftm_Detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if ((column >= 0 && column <= 5) || (column == 12)) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        
        seteaTabCabecsalvar();
        seteaTabDetalles();
        seteaServicios();
    }
    public JTable getTabla() {
        return tab_Salvar;
    }

    private void seteaServicios() {
        df = new DecimalFormat("00.00");
        daoDetalles = new DetallesDAO();
        daoControl = new ControlDAO();
        servicioDoc = new Servicio_Documentos();
    }

    public boolean hayDetalles() {
        if (dftm_Detalle.getRowCount() != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void insertaSV() {
        for (int i = 0; i < salvarSelec.length; i++) {
            int pos = (Integer) tab_Salvar.getValueAt(salvarSelec[i], 0);
            int idSalida = (Integer) tab_Salvar.getValueAt(salvarSelec[i], 1);
            List<Detallees> lista = daoDetalles.listarPorCabecsalvarCliente(idSalida, cliente.getIdcliente());
            if (!lista.isEmpty()) {
                listaDetalles.addAll(lista);
                mostrarSalvar[pos] = false;
            }
        }
        if (listaDetalles != null) {
            habilitaCampos(true);
            txt_IGV.setText(String.valueOf(igv));
            listarSalvar();
            listarDetalles();
        }
    }

    public void extraeSV() {
        ArrayList<Integer> idSalidas = new ArrayList();
        for (int i = 0; i < detallesSelec.length; i++) {
            Integer id = (Integer) tab_Detalle.getValueAt(detallesSelec[i], 1);
            if (!idSalidas.contains(id)) {
                idSalidas.add(id);
            }
        }
        ArrayList<Detallees> detallesRemover = new ArrayList();
        for (Integer id : idSalidas) {
            for (Detallees s : listaDetalles) {
                if (s.getCabecsalvar().getIdsalida().compareTo(id) == 0) {
                    detallesRemover.add(s);
                }
            }
            for (Cabecsalvar c : listaSalvar) {
                if (c.getIdsalida().compareTo(id) == 0) {
                    mostrarSalvar[listaSalvar.indexOf(c)] = true;
                }
            }
        }
        if (listaDetalles.isEmpty()) {
            limpiaCampos();
            habilitaCampos(false);
        } else {
        }
        listaDetalles.removeAll(detallesRemover);
        listarDetalles();
        listarSalvar();
    }

    private void limpiaCampos() {
        txt_Total.setText("");
        txt_TotalBruto.setText("");
        txt_TotalDesc.setText("");
        txt_IGV.setText("");
    }

    private void habilitaCampos(boolean opcion) {
        txt_Total.setEnabled(opcion);
        txt_TotalBruto.setEnabled(opcion);
        txt_TotalDesc.setEnabled(opcion);
        txt_IGV.setEnabled(opcion);
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
        listaDetalles = new ArrayList();
        if (cliente != null) {
            total = 0;
            listaSalvar = servicioDoc.getListarPorClientesNoProces(cliente.getIdcliente());
            setearMostrarSalvar(listaSalvar.size());
            listarSalvar();
            dftm_Detalle.setRowCount(0);
            listaDetalles.clear();
        } else {
            dftm_Salvar.setRowCount(0);
        }
    }

    public void setVendedor(Vendedores vendedor) {
        this.vendedor = vendedor;
    }

    private void seteaTabCabecsalvar() {
        String[] tituloColumna = {"ITEM", "IDSALIDA", "VALORVENTA", "TOTAL"};
        dftm_Salvar.setColumnCount(tituloColumna.length);
        dftm_Salvar.setRowCount(0);
        dftm_Salvar.setColumnIdentifiers(tituloColumna);
        tab_Salvar.setModel(dftm_Salvar);
        ((JLabel) tab_Salvar.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tab_Salvar.getSelectionModel().setValueIsAdjusting(false);
        tab_Salvar.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tab_Salvar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                salvarSelec = tab_Salvar.getSelectedRows();
                if (salvarSelec.length != 0) {
                    btn_Inserta.setEnabled(true);
                } else {
                    btn_Inserta.setEnabled(false);
                }
            }
        });
    }

    private void seteaTabDetalles() {
        String[] tituloColumna = {"ITEM", "IDSALIDA", "IDREPUESTO", "DESCRIPCION",
            "PRECIOLISTA", "CANTPEDIDA", "CANTENTREGADA", "PRECIO", "DESCUENTO1",
            "DESCUENTO2", "DESCUENTO3", "DESCUENTO4", "TOTAL"};
        dftm_Detalle.setColumnCount(tituloColumna.length);
        dftm_Detalle.setRowCount(0);
        dftm_Detalle.setColumnIdentifiers(tituloColumna);
        tab_Detalle.setModel(dftm_Detalle);
        ((JLabel) tab_Salvar.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tab_Detalle.getSelectionModel().setValueIsAdjusting(false);
        tab_Detalle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                detallesSelec = tab_Detalle.getSelectedRows();
                if (detallesSelec.length != 0) {
                    btn_Extrae.setEnabled(true);
                } else {
                    btn_Extrae.setEnabled(false);
                }
            }
        });
        dftm_Detalle.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int columna = e.getColumn();
                if (columna >= 6 && columna <= 11) {
                    refrescaValores();
                }
            }
        });
    }

    private void refrescaValores() {
        calculaTotal();
        calculaTotalDescuento();
        calculaTotalIGV();
    }

    private double valorCelda(int fila, int columna) {
        try {
            return Double.parseDouble(String.valueOf(tab_Detalle.getValueAt(fila, columna)));
        } catch (Exception e) {
            return 0;
        }
    }

    private double calculaTotalFila(int fila) {
        double cantidad = valorCelda(fila, colCantidad);
        double precio = valorCelda(fila, colPrecio);
        double tot = cantidad * precio * (100 - calculaDescuentoTotalFila(fila)) / 100;
        tab_Detalle.setValueAt(redondear(tot), fila, colTotal);
        return tot;
    }

    private double calculaDescuentoTotalFila(int fila) {
        double tot = 100;
        for (int col = colDescInicio; col <= colDescFin; col++) {
            tot *= (100 - valorCelda(fila, col)) / 100;
        }
        return 100 - tot;
    }

    private double calculaTotalBruto() {
        double tot = 0;
        for (int fila = 0; fila < tab_Detalle.getRowCount(); fila++) {
            tot += valorCelda(fila, colPrecio) * valorCelda(fila, colCantidad);
        }
        txt_TotalBruto.setText(String.valueOf(redondear(tot)));
        return tot;
    }

    private void calculaTotal() {
        double tot = 0;
        for (int fila = 0; fila < tab_Detalle.getRowCount(); fila++) {
            tot += calculaTotalFila(fila);
        }
        txt_Total.setText(String.valueOf(redondear(tot)));
        total = tot;
    }

    private void calculaTotalIGV() {
        txt_IGV.setText(String.valueOf(redondear(total * 118 / 100)));
    }

    private void calculaTotalDescuento() {
        txt_TotalDesc.setText(String.valueOf(redondear(calculaTotalBruto() - total)));

    }

    private double redondear(double valor) {
        return Math.rint(valor * 100) / 100;
    }

    private void listarSalvar() {
        dftm_Salvar.setRowCount(0);
        for (int i = 0; i < listaSalvar.size(); i++) {
            if (mostrarSalvar[i]) {
                Cabecsalvar c = listaSalvar.get(i);
                Object[] objeto = {i, c.getIdsalida(), c.getValorventa(), c.getTotal()};
                dftm_Salvar.addRow(objeto);
            }
        }
        for (int i = 0; i < tab_Salvar.getColumnCount(); i++) {
            tab_Salvar.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    private void listarDetalles() {
        dftm_Detalle.setRowCount(0);
        for (int i = 0; i < listaDetalles.size(); i++) {
            Detallees detalle = listaDetalles.get(i);
            Repuestos repuesto = detalle.getRepuestos();
            Object[] objeto = {i, detalle.getCabecsalvar().getIdsalida(), repuesto.getId(),
                repuesto.getDescripcion(), repuesto.getPreciolista(), detalle.getCantpedida(), detalle.getCantentregada(),
                "", "", "", "", ""};
            dftm_Detalle.addRow(objeto);
        }
        for (int i = 0; i < tab_Detalle.getColumnCount(); i++) {
            tab_Detalle.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void limpiarSV() {
        tab_Salvar.clearSelection();
    }

    public void limpiarDetalles() {
        tab_Detalle.clearSelection();
    }

    public boolean generarFactura(String transaccion, String documento,
            String nserie, String ndocumento, String operacion) {
        if (transaccion != null && documento != null && nserie != null
                && ndocumento != null && cliente != null && vendedor != null
                && !listaDetalles.isEmpty()) {
            Cabeces cab = new Cabeces();
            CabecesId id = new CabecesId(transaccion, documento,
                    nserie, ndocumento);
            cab.setId(id);
            cab.setVendedores(vendedor);
            cab.setClientes(cliente);
            cab.setIgv(igv);
            cab.setTotal(total);
            cab.setTipoperacion(operacion);
            return servicioDoc.guardarFacturaSV(cab, modificarCabecsalvar(cab));
        } else {
            return false;
        }
    }

    private ArrayList<Cabecsalvar> modificarCabecsalvar(Cabeces c) {
        ArrayList<Cabecsalvar> listaModificar = new ArrayList();
        int j = mostrarSalvar.length;
        for (int i = 0; i < mostrarSalvar.length; i++) {
            if (!mostrarSalvar[i]) {
                Cabecsalvar cab = listaSalvar.get(i);
                cab.setCabeces(c);
                cab.setFecha(fecha.getDate());
                listaModificar.add(cab);
                cab.setFecha(null);
                j--;
            }
        }
        setearMostrarSalvar(j);
        listaDetalles.clear();
        listaSalvar.removeAll(listaModificar);
        listarDetalles();
        listarSalvar();
        return listaModificar;
    }

    private void setearMostrarSalvar(int i) {
        mostrarSalvar = new boolean[i];
        for (int j = 0; j < mostrarSalvar.length; j++) {
            mostrarSalvar[j] = true;
        }
    }
}