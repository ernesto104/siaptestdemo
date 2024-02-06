package Servicios.CuentasxCobrar;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author keily
 */
public class Tabla_CobrarFacturas extends AbstractTableModel {

    String[] nombreColumnas = {"Importe", "Fecha", " Modo", "Banco", "Nro. Cheque", "idPago", "rutaImagen"};
    boolean[] editFilas = {true, true, true, true, true, false, false};
    private ArrayList<NroFacturas> nf;

    public Tabla_CobrarFacturas() {
        nf = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return getNf().size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return nombreColumnas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editFilas[columnIndex];
    }

    public boolean[] getEditFilas() {
        return editFilas;
    }

    public void setEditFilas(boolean[] editFilas) {
        this.editFilas = editFilas;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Double.class;
            case 1:
                return JDateChooser.class;
            case 2:
                return String.class;
            case 3:
                return JComboBox.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NroFacturas factura = getNf().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return factura.getImporte();
            case 1:
                return factura.getFechaPago();
            case 2:
                return factura.getModo();
            case 3:
                return factura.getBanco().getSelectedItem().toString();
            case 4:
                return factura.getNrocheque();
            case 5:
                return factura.getIdFactura();
            case 6:
                return factura.getRutaImagen();
        }
        return 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NroFacturas factura = getNf().get(rowIndex);
        switch (columnIndex) {
            case 0:
                double importe = Double.parseDouble(String.valueOf(aValue));
                factura.setImporte(importe);
                getNf().set(rowIndex, factura);
                break;
            case 1:
                Date fecha = factura.getFechaPago().getDate();
                factura.setFechaPago(fecha);
                getNf().set(rowIndex, factura);
                break;
            case 2:
                factura.setModo(aValue.toString());
                getNf().set(rowIndex, factura);
                break;
            case 3:
                factura.setBanco(aValue.toString());
                getNf().set(rowIndex, factura);
                break;
            case 4:
                factura.setNrocheque(aValue.toString());
                getNf().set(rowIndex, factura);
                break;
            case 5:
                factura.getIdFactura();
                getNf().set(rowIndex, factura);
                break;
            case 6:
                factura.setRutaImagen(aValue.toString());
                getNf().set(rowIndex, factura);
                break;
        }
        fireTableDataChanged();
    }

    public void eliminar(int i) {
        getNf().remove(0);
        fireTableDataChanged();
    }

    public void eliminarXPago(int i) {
        getNf().remove(i);
        fireTableDataChanged();
    }

    public void asignarPagos(int num, int ini) {
        if ( ini == 0 ) {
            ini = 0;
            for ( int i = ini; i < (num + ini); i++ ) {
                NroFacturas nuevafactura = new NroFacturas();
                nuevafactura.setNumero(i);
                boolean[] editF = {true, true, true, true, true, false, false};
                setEditFilas(editF);
                getNf().add(ini, nuevafactura);
            }
        } else {
            for ( int i = ini; i < (num + ini); i++ ) {
                NroFacturas nuevafactura = new NroFacturas();
                nuevafactura.setNumero(i);
                boolean[] editF = {true, true, true, true, true, false, false};
                setEditFilas(editF);
                getNf().add(ini, nuevafactura);
            }
        }
        fireTableDataChanged();
    }

    public ArrayList<NroFacturas> getNf() {
        return nf;
    }

    public void setNf(ArrayList<NroFacturas> nf) {
        this.nf = nf;
    }
}