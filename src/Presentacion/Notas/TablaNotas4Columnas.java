package Presentacion.Notas;

import Entidades.Otros.Monetario;
import Entidades.Tipocambio;
import Mantenimiento.ControlDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public final class TablaNotas4Columnas extends AbstractTableModel {

    String[] columnNames = {"Nro. Parte", "Cantidad", "Descripción", "Valor de Venta"
                            ,"CantInicial", "VVInicial"};
    boolean[] habilitado = {false, true, false, false
                            ,false, false};
    public ArrayList<Nota> lN;
    int nroLinea;
    IU_Emisión_Notas_Credito_Devolución iu;
    double subTotal;
    ControlDAO controlDAO;
    Tipocambio t;

    public TablaNotas4Columnas(IU_Emisión_Notas_Credito_Devolución iu) {
        this.iu = iu;
        lN = new ArrayList<>();
    }

    public Tipocambio getT() {
        return t;
    }

    public void setT(Tipocambio t) {
        this.t = t;
    }

    public void SetEditable(boolean bool) {
        habilitado[0] = bool;
        habilitado[1] = bool;
        habilitado[2] = bool;
        habilitado[3] = bool;
        
        habilitado[4] = bool;
        habilitado[5] = bool;
    }

    @Override
    public int getRowCount() {
        return getlN().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch ( columnIndex ) {
            case 0:
                return getlN().get(rowIndex).getNroParte();

            case 1:
                return getlN().get(rowIndex).getCantidad();

            case 2:
                return getlN().get(rowIndex).getDescripcion();

            case 3:
                return RedondearConCeros(Double.parseDouble(getlN().get(rowIndex).getValorVenta()), 2);
                
            case 4:
                return getlN().get(rowIndex).getCantInicial();
                
            case 5:
                return RedondearConCeros(getlN().get(rowIndex).getVvInicial(), 2);
        }
        return 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Nota nota = getlN().get(rowIndex);

        switch ( columnIndex ) {
            case 1:
                int cantidad = Integer.parseInt(String.valueOf(aValue)); // VALOR VARIABLE-DINÁMICO
                int cantAntes = nota.getCantInicial();
                double valorVentaAntes = nota.getVvInicial();
//                System.out.println("cantidad:" + cantidad);
//                System.out.println("cantAntes:" + cantAntes);
                
                if ( cantidad > cantAntes ) {
                    JOptionPane.showMessageDialog(null,
                                                  "La nueva cantidad debe ser menor o igual que la anterior", 
                                                  "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    double vTotal = (valorVentaAntes * cantidad) / cantAntes;
                    nota.setCantidad(cantidad);
                    nota.setValorVenta(String.valueOf(vTotal));
                }
                break;
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public ArrayList<Nota> getlN() {
        return lN;
    }

    public void setlN(ArrayList<Nota> lN) {
        this.lN = lN;
    }

    public void eliminar(int i) {
        getlN().remove(0);
        fireTableDataChanged();
        CalcularTotales();
    }

    public double TotalBruto() {
        double totalBruto = 0.0;
        for ( Nota d : getlN() ) {
            totalBruto += Double.parseDouble(d.getValorVenta());
//            totalBruto += d.getValorVenta(); // ULTIMO CAMBIO
        }
        return totalBruto;
    }

    public void CalcularTotales() {
        subTotal = TotalBruto();
        String totalNC = Monetario.formatearMonetario(String.valueOf(Redondear(subTotal)), 2);
        iu.txtTotalNotaCredito.setText(totalNC);
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
    
    public String RedondearConCerosDecimales(double numero, int cerosDecimales) {
        String cantidad = String.valueOf(Math.rint(numero * 100) / 100);
        return formatearCerosDecimales(cantidad, cerosDecimales);
    }
    
    private static String formatearCerosDecimales(String numero, int cantidadDigitos) {
        String formateado = numero;
        int posicionPunto = numero.indexOf(".");
        int longitudCifrasDecimales = numero.length() - posicionPunto - 1;
        
        if ( longitudCifrasDecimales != 2 ) {
            for ( int i = 0; i < longitudCifrasDecimales; i++ ) {
                formateado = formateado + "0";
            }
        }
        return formateado;
    }

//    public boolean agregar(int cantidad, String descripcion, String nroParte, double valorVenta) {
    public boolean agregar(int cantidad, String descripcion, String nroParte, String valorVenta) {
//        Nota nota = new Nota(nroParte, cantidad, descripcion, valorVenta);
        Nota nota = new Nota(nroParte, cantidad, descripcion, valorVenta, cantidad, Double.parseDouble(valorVenta));
        getlN().add(nota);
        fireTableDataChanged();
        //    fireTableDataChanged1();
        CalcularTotales();
        return true;
    }

    public IU_Emisión_Notas_Credito_Devolución getIu() {
        return iu;
    }
    
    public void cambiarDolares() {
        if ( !lN.isEmpty() ) {
            int size = getlN().size();
            
            for ( int i = 0; i < size; i++ ) {
                Nota df = getlN().get(i);
//                setValueAt(ADolares(df.getValorVenta(), getT()), i, 3); // ULTIMO CAMBIO
                setValueAt(ADolares(Double.parseDouble(df.getValorVenta()), getT()), i, 3);
            }
        }
    }
    
    private double ADolares(double precio, Tipocambio t) {
        return Math.round((precio / t.getValorventa()) * 100.0) / 100.0;
    }

    void cambiarSoles() {
        System.out.println("entra a camboop de soles");
        if (!getlN().isEmpty()) {
            int size = getlN().size();
            for (int i = 0; i < size; i++) {
                Nota df = getlN().get(i);
//                System.out.println("a soles:"+Asoles(df.getValorVenta(),getT())); // ULTIMO CAMBIO
                System.out.println("a soles:"+Asoles(Double.parseDouble(df.getValorVenta()),getT()));
                System.out.println("tipo cambio:"+getT().getValorventa());
//                setValueAt(Asoles(df.getValorVenta(), getT()), i, 3); // ULTIMO CAMBIO
                setValueAt(Asoles(Double.parseDouble(df.getValorVenta()), getT()), i, 3);
             //   System.out.println("asoleS: "+getValueAt(i, 3)); // ULTIMO CAMBIO
            }
        }
    }

    private double Asoles(double precio, Tipocambio t) {
        return Math.round((precio * t.getValorventa()) * 100.0) / 100.0;
    }
}