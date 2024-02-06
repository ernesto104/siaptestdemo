/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Comision;

import Entidades.Cabeces;
import Entidades.Vendedores;
import Presentacion.Comisiones.FREP034;
import Servicios.Util;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CRS
 */
public class Tabla_Comisiones extends AbstractTableModel {

    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<Fila_Tabla_comision> lista;
    ArrayList<Cabeces> seleccionados;
    FREP034 comisiones;
    double TotalComision;
    double TotalValorVenta;
    Util util;
    String[] columnNames = {"Item", "Tipo", "Nro", "Fecha", "Vendedor", "Cliente/Proveedor", "%",
        "Valor Venta ($)", "Comision", "Pagado", "Referencia", "Seleccionado"};
    boolean[] habilitado = {false, false, false, false, false, false, false, false, false, false, false, true};

    public Tabla_Comisiones() {
        lista = new ArrayList<>();
        seleccionados = new ArrayList<>();
        util = new Util();
    }

    public Tabla_Comisiones(FREP034 g) {
        this();
        comisiones = g;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return habilitado[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fila_Tabla_comision fila = lista.get(rowIndex);
        Cabeces c = fila.getC();
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return TipoDoc(c.getId().getTipodoc());
            case 2:
                return c.getId().getNrodocumento();
            case 3:
                return sd.format(c.getTipocambio().getFecha());
            case 4:
                return c.getVendedores() != null ? c.getVendedores().getIdvendedor() : " ";
            case 5:
                if (c.getClientes() != null) {
                    return c.getClientes().getNombre();
                }

            case 6:
                if (c.getVendedores() != null && c.getClientes() != null) {
                    return c.getVendedores() != null
                            ? (("LIMA".equals(c.getClientes().getPlaza())
//                            ? ((c.getClientes().getPlaza().equals("LIMA")
                            ? c.getVendedores().getPorcentajelima() : c.getVendedores().getPorcentajeprov())) : " ";
                }


            case 7:
                return util.DosDecimales(c.getValorventa());
            case 8:
                if (c.getVendedores() != null && c.getClientes() != null) {
                    Vendedores v = c.getVendedores();
                    if (v != null) {
                        if ("LIMA".equals(c.getClientes().getPlaza())) {
                            return util.DosDecimales(util.Redondear2Decimales(v.getPorcentajelima() * c.getValorventa() / 100.0));
                        } else {
                            return util.DosDecimales(util.Redondear2Decimales(v.getPorcentajeprov() * c.getValorventa() / 100.0));
                        }
                    }
                }


            case 9:
                return fila.getPagado();
            case 10:
                return fila.getC().getNcFacbol();
            case 11:
                return fila.isSeleccionado();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 11) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Fila_Tabla_comision fila = lista.get(rowIndex);
        Cabeces c = fila.getC();
        switch (columnIndex) {
            case 9:
                fila.setPagado(String.valueOf(aValue));
                break;
            case 11:
                if (!fila.getPagado().equals("*")) {
                    fila.setSeleccionado((boolean) aValue);
                    if ((boolean) aValue == true) {
                        seleccionados.add(c);
                    } else {
                        seleccionados.remove(c);
                    }
                    comisiones.lb_Apagar.setText(util.DosDecimales(CalcularPagos()));
                } else {
                    JOptionPane.showMessageDialog(null, "COMISION YA CANCELADA");
                }
                break;
        }
        lista.set(rowIndex, fila);
        fireTableDataChanged();
    }

    private Double CalcularPagos() {
        double pago = 0.0;
        for (Cabeces c : seleccionados) {
            double porcentaje;
            if (c.getClientes().getPlaza().equals("LIMA")) {
                porcentaje = c.getVendedores().getPorcentajelima();
            } else {
                porcentaje = c.getVendedores().getPorcentajeprov();
            }
            if ("03".equals(c.getId().getTipodoc())) {
                pago -= (c.getValorventa() * porcentaje / 100.0);
            } else {
                pago += (c.getValorventa() * porcentaje / 100.0);
            }

        }
        return util.Redondear2Decimales(pago);
    }

    private void Agregar(Cabeces c) {
        String pago;
        double ValorVenta = c.getValorventa() == null ? 0.0 : c.getValorventa();
        double porcentaje = 0;
        if (c.getVendedores() != null) {
            if (c.getClientes() != null) {
                if ("LIMA".equals(c.getClientes().getPlaza())) {
                    porcentaje = c.getVendedores().getPorcentajelima();
                } else {
                    porcentaje = c.getVendedores().getPorcentajeprov();
                }
            }
        } else {
            porcentaje = 0;
        }
        double comision = porcentaje * ValorVenta / 100.0;
        if ((c.getComision() != null && c.getComision().equals("") || c.getComision() == null)) {
            pago = "";
        } else {
            pago = "*";
        }
        TotalComision += comision;
        TotalValorVenta += ValorVenta;
        boolean contiene = seleccionados.contains(c);
        lista.add(new Fila_Tabla_comision(c, pago, contiene));
    }

    public void Agregar(List resultado) {
        lista.clear();
        TotalComision = TotalValorVenta = 0;
        Iterator it = resultado.iterator();
        while (it.hasNext()) {
            Agregar((Cabeces) it.next());
        }
        fireTableDataChanged();
        TotalValorVenta = (util.Redondear2Decimales(TotalValorVenta));
        TotalComision = (util.Redondear2Decimales(TotalComision));
        BigDecimal valorVenta = new BigDecimal(String.valueOf(TotalValorVenta));
        BigDecimal comision = new BigDecimal(String.valueOf(TotalComision));


        comisiones.lb_valorVenta.setText(util.DosDecimales(valorVenta.doubleValue()));
        comisiones.lb_comisiones.setText(util.DosDecimales(comision.doubleValue()));
    }

    public Cabeces GetCabecera(int row) {
        return lista.isEmpty() ? null : lista.get(row).getC();
    }

    public ArrayList<Cabeces> getSeleccionados() {
        return seleccionados;
    }

    public ArrayList<Fila_Tabla_comision> getFilas() {
        return lista;
    }

    public void BorrarSeleccion() {
        seleccionados.clear();
        comisiones.lb_Apagar.setText("$ 0.00");
    }

    private String TipoDoc(String tipo) {
        switch (tipo) {
            case "01":
                return "F";
            case "02":
                return "B";
            case "03":
                return "NC";
            case "04":
                return "ND";
            case "05":
            case "06":
            case "07":
            case "08":
            case "09":
            case "10":
            case "11":
            case "12":
                return tipo;
            default:
                return "";
        }
    }
}
