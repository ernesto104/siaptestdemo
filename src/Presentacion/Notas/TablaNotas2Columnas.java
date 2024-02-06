package Presentacion.Notas;

import Entidades.Control;
import Entidades.Tipocambio;
import Mantenimiento.ControlDAO;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public final class TablaNotas2Columnas extends AbstractTableModel {

    String[] columnNames = {"Descripción", "Precio de venta (incl. IGV)"};
    boolean[] habilitado = {true, true};
    private ArrayList<Nota> lN;
    ControlDAO controlDAO;
    int nroLinea;
    IU_Emisión_Notas_Credito_Desc_Especial iu;
    IU_Emisión_Notas_Debito iuDeb;
    double subTotal;
    Tipocambio t;
    int igv;

    public TablaNotas2Columnas(IU_Emisión_Notas_Credito_Desc_Especial iu) {
        this.iu = iu;
        this.iuDeb = null;
        igv = 0;
        iniciar();
    }

    public TablaNotas2Columnas(IU_Emisión_Notas_Debito iu) {
        this.iuDeb = iu;
        this.iu = null;
        iniciar();
    }

    public void iniciar() {
        lN = new ArrayList<>();
        controlDAO = new ControlDAO();
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        nroLinea = control.getNrolineanc();
        igv = control.getImpuestoigv();
    }

    public void SetEditable(boolean bool) {
        habilitado[0] = bool;
        habilitado[1] = bool;
    }

    @Override
    public int getRowCount() {
        return nroLinea;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return getlN().get(rowIndex).getDescripcion();
            case 1:
                Nota n = getlN().get(rowIndex);
                String vv = getlN().get(rowIndex).getValorVenta();
                if ( vv == null ) {
                    return "";
                } else {
//                System.out.println("transformar a double:" + Double.parseDouble(getlN().get(rowIndex).getValorVenta()));
//                System.out.println("son iguales: " + (Double.parseDouble(getlN().get(rowIndex).getValorVenta()) == 0.0));
                    if (Double.parseDouble(getlN().get(rowIndex).getValorVenta()) == 0.0) {
                        return "";
                    } else {
                        return "" + getlN().get(rowIndex).getValorVenta();
                    }
                }
        }
        return 0;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return habilitado[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        System.out.println("setValueAt...");
        Nota nota = getlN().get(rowIndex);

        switch (columnIndex) {
            case 0:
                String valor = String.valueOf(aValue);
                String descripcion = valor;
                nota.setDescripcion(descripcion);
                break;
            case 1:
                String a = String.valueOf(aValue);
                Double m;
                if (a.equalsIgnoreCase("")) {
                    m = 0.0;
                } else {
                    m = Double.valueOf(a);
                }
                nota.setValorVenta(String.valueOf(m));
//                nota.setValorVenta(m); // ULTIMO CAMBIO
                break;
        }
        fireTableDataChanged();
        if (iu == null) {
            CalcularTotalesDebito();
        } else {
            CalcularTotales();
        }
    }

    public ArrayList<Nota> getlN() {
        return lN;
    }

    public void setlN(ArrayList<Nota> lN) {
        this.lN = lN;
    }

    public Tipocambio getT() {
        return this.t;
    }

    public void setT(Tipocambio t) {
        this.t = t;
    }

    public void eliminar(int i) {
        getlN().remove(0);
        fireTableDataChanged();
    }

    public void asignarFilas(int num) {

        for (int i = 0; i < num; i++) {
            Nota nuevaFila = new Nota();
            getlN().add(nuevaFila);
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public void asignarFilasDebito(int num) {
        for (int i = 0; i < num; i++) {
            Nota nuevaFila = new Nota();
            getlN().add(nuevaFila);
        }
        fireTableDataChanged();
        CalcularTotalesDebito();

    }

    public double TotalBruto() {
        double totalBruto = 0.0;
        for ( Nota d : getlN() ) {
            String vv = d.getValorVenta();
            if ( vv != null ) {
                totalBruto += Double.parseDouble(vv);
            }
        }
        return totalBruto;
    }

    public void CalcularTotales() {
        subTotal = TotalBruto();
        double igv = subTotal * this.igv / (100 + this.igv);
        igv = Redondear(igv);
        double total = subTotal - igv;
        iu.txtSubTotal.setText(total + "");
        iu.txtIGV.setText("" + igv);
        iu.txtTotal.setText("" + subTotal);
        
//        System.out.println("SubTotal:" + total);
//        System.out.println("IGV:" + igv);
//        System.out.println("Total:" + subTotal);
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }
    
    public String RedondearConCeros(double numero, int cerosDecimales) {
        String cantidad = String.valueOf(Math.rint(numero * 100) / 100);
        return formatearMonetario(cantidad, cerosDecimales);
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }
        return valorMonetario;
    }

    private void CalcularTotalesDebito() {
        subTotal = TotalBruto();
        double igv = subTotal * this.igv / (100 + this.igv);
        igv = Redondear(igv);
        double total = subTotal - igv;
        iuDeb.txtSubTotal.setText(total + "");
        iuDeb.txtIGV.setText("" + igv);
        iuDeb.txtTotal.setText("" + subTotal);

    }

    public void cambiarDolares() {
        if (!lN.isEmpty()) {
            int size = getlN().size();
            for (int i = 0; i < size; i++) {
                Nota df = getlN().get(i);
                setValueAt(ADolares(Double.parseDouble(df.getValorVenta()), getT()), i, 1);
//                setValueAt(ADolares(df.getValorVenta(), getT()), i, 1); // ULTIMO CAMBIO
            }
        }
    }

    private double ADolares(double precio, Tipocambio t) {
        return Math.round((precio / t.getValorventa()) * 100.0) / 100.0;
    }

    void cambiarSoles() {
        System.out.println("ntra a cambio de sole");
        if (!lN.isEmpty()) {
            int size = getlN().size();
            for (int i = 0; i < size; i++) {
                Nota df = getlN().get(i);
                setValueAt(Asoles(Double.parseDouble(df.getValorVenta()), getT()), i, 1);
//                setValueAt(Asoles(df.getValorVenta(), getT()), i, 1); // ULTIMO CAMBIO
                System.out.println("soles:" + Asoles(Double.parseDouble(df.getValorVenta()), getT()));
//                System.out.println("soles:" + Asoles(df.getValorVenta(), getT())); ULTIMO CAMBIO
            }
        }
    }

    private double Asoles(double precio, Tipocambio t) {
        return Math.round((precio * t.getValorventa()) * 100.0) / 100.0;
    }
}
