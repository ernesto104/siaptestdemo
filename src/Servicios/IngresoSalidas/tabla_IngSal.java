/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.IngresoSalidas;

import Entidades.Clientes;
import Entidades.Otros.DetalleFactura;
import Entidades.Detallees;
import Entidades.Repuestos;
import Entidades.Tipocambio;
import Presentacion.IngresoSalidas.IU_DetalleIngSal;
import Servicios.Util;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CRS
 */
public class tabla_IngSal extends AbstractTableModel {

    private IU_DetalleIngSal cont;
    int tipoOp;
    /*  1 :   compra local    :    compra en el interior de la ciudad, igual que factura. 
     *                             importe debe ser igual (+/-) 1 dolar, sol
     *  2 :   ingresos varios :    ingreso interno de la empresa, solo editable cantidad.  
     *                             Se actualiza la cantidad de los repuestos (+)
     *  3 :   salidas         :    similar ingresos varios , solo editable cantidad.
     *                             Se actualiza la cantidad de los repuestos (-)
                   
     * 2 y 3 son para regularizar existencias
     */

    private String Moneda; // solo interno en la tabla 
    private Tipocambio t;
    String[] columnNames = {"Item", "Nro Parte", "Cod. Secundario", "Descripcion", "Stock",
                            "Precio Costo", "Cantidad", "Dcto1", "Dcto2", "Dcto3", 
                            "Dcto4", "Total",
                            "Modelo"};
    boolean[] habilitado = {false, false, false, false, false, 
                            true, true, true, true, true,
                            true, false, false};
    private final ArrayList<DetalleFactura> detalles;
    private Util util ;

    public tabla_IngSal(int tipo) {
        tipoOp = tipo;
        detalles = new ArrayList<>();
        CeldasEditables();
        util = new Util();
    }

    public tabla_IngSal(int tipo, String importe) {
        tipoOp = tipo;
        detalles = new ArrayList<>();
        util = new Util();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return getDetalles().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetalleFactura detalle = getDetalles().get(rowIndex);
        Repuestos rp = detalle.getD().getRepuestos();
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return rp.getCodrepuesto(); // CAMBIO
            case 2:
                return rp.getCodigoseg();
            case 3:
                return rp.getDescripcion();
            case 4:
                return rp.getStock();
            case 5:
                return util.DosDecimales(detalle.getPrecioRepuesto());
            case 6:
                return detalle.getD().getCantpedida();
            case 7:
                return detalle.getD().getDescuento1() == 0.0 ? " " : util.DosDecimales(detalle.getD().getDescuento1());
            case 8:
                return detalle.getD().getDescuento2() == 0.0 ? " " : util.DosDecimales(detalle.getD().getDescuento2());
            case 9:
                return detalle.getD().getDescuento3() == 0.0 ? " " : util.DosDecimales(detalle.getD().getDescuento3());
            case 10:
                return detalle.getD().getDescuento4() == 0.0 ? " " : util.DosDecimales(detalle.getD().getDescuento4());
            case 11:
                return util.DosDecimales(detalle.getTotal());
            case 12:
                return rp.getDescrmodelo();
            default:
                return 0;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            DetalleFactura det = getDetalles().get(rowIndex);
            Detallees deta = det.getD();
            String valor = String.valueOf(aValue);
            double tot;
            switch (columnIndex) {
                case 5:
                    double cant = Double.parseDouble(valor);
                    det.setPrecioRepuesto(cant);
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                case 6:
                    int cantidad = Integer.parseInt(valor);
                    deta.setCantpedida(cantidad);
                    deta.setCantentregada(cantidad);
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                case 7:
                    deta.setDescuento1(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                case 8:
                    deta.setDescuento2(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                case 9:
                    deta.setDescuento3(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                case 10:
                    deta.setDescuento4(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(deta.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
            }

            det.setD(deta);
            getDetalles().set(rowIndex, det);
        } catch (NumberFormatException e) {
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return habilitado[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    private double PrecioConDcto(int row) {
        Detallees d = getDetalles().get(row).getD();
        double d1 = d.getDescuento1() / 100.0;
        double d2 = d.getDescuento2() / 100.0;
        double d3 = d.getDescuento3() / 100.0;
        double d4 = d.getDescuento4() / 100.0;

        double Precio = Double.parseDouble(String.valueOf(getValueAt(row, 5)));

        Precio = ((((Precio * (1 - d1)) * (1 - d2))) * (1 - d3)) * (1 - d4);
        return Precio;
    }

    public boolean AgregarRepuesto(Repuestos rep, Clientes cl) {
        if ( estaEn(rep) ) {
            return false;
        }
        DetalleFactura det = Detalle(rep, cl);
        getDetalles().add(det);
        fireTableDataChanged();
        CalcularTotales();
        return true;
    }

    private DetalleFactura Detalle(Repuestos rpt, Clientes cs) {
        Detallees d = new Detallees();
        double precRep = 0.00;
        if ( rpt.getCostopromedio() != null ) {
            precRep = rpt.getCostopromedio();
        }
//        System.out.println("precRep:" + precRep);
//        System.out.println("tipoOp:" + tipoOp);
//        if (tipoOp == 1) {
//            System.out.println("AA.costo ultimo");
//            precRep = rpt.getCostopromedio();
////            precRep = rpt.getPcostoultimo();
//        } else {
//            System.out.println("BB.precio de lista");
//            precRep = rpt.getPreciolista();
//        }
        if ( Moneda != null && Moneda.equals("S") ) {
            precRep = Asoles(precRep, t);
        }
        d.setRepuestos(rpt);
        d.setCantpedida(0);
        d.setCantentregada(0);
        d.setDescuento1(0.0);
        d.setDescuento2(0.0);
        d.setDescuento3(0.0);
        d.setDescuento4(0.0);
        d.setPreciolista(precRep);
        
        DetalleFactura det = new DetalleFactura();
        det.setD(d);
        det.setTotal(0.0);
        det.setItem(getDetalles().size() + 1);
        det.setPrecioRepuesto(precRep); // no se utiliza
        det.setModelo(rpt.getAplicacion());
        return det;
    }

    private void CalcularTotales() {
        double tot = Total();
        cont.lb_total.setText(util.DosDecimales(tot));
        if ( cont.lb_total.isVisible() ) {
            if ( tot > Double.parseDouble(cont.lb_total.getText()) ) {
                cont.lb_mensaje.setText("CANTIDAD EXCEDIDA");
            } else {
                cont.lb_mensaje.setText("");
            }
        }
    }

    private double Total() {
        double total = 0.0;
        for ( DetalleFactura d : getDetalles() ) {
            total += d.getTotal();
        }
        total = util.Redondear2Decimales(total);
        return total;
    }

    private boolean estaEn(Repuestos rp) {
//        System.out.println("estaEn?...");
        List<DetalleFactura> lstDetFac = getDetalles();
//        if ( !lstDetFac.isEmpty() ) {
//            System.out.println("tamaño de lstDetFac:" + lstDetFac.size());
//        }
        
        for ( DetalleFactura fac : getDetalles() ) {
//            System.out.println("idRep:" + rp.getId().getIdrepuesto());
//            System.out.println("fac.getD():" + fac.getD());
            if ( rp.getId().getIdrepuesto() == fac.getD().getRepuestos().getId().getIdrepuesto() ) {
                return true;
            }
        }
        return false;
    }

    public void borrar(int col) {
        getDetalles().remove(col);
        fireTableDataChanged();
        CalcularTotales();
    }

    public void aplicarDctos(double... n) {
        for ( int i = 0; i < getDetalles().size(); i++ ) {
            for ( int j = 0; j < n.length; j++ ) {
                setValueAt(n[j], i, j + 7);
            }
        }
        CalcularTotales();
    }

    public void cambiarADolares() {
        if ( !detalles.isEmpty() ) {
            int size = getDetalles().size();
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDetalles().get(i);
                setValueAt(ADolares(df.getPrecioRepuesto(), getT()), i, 5);
                setValueAt(ADolares(df.getD().getPreciolista(), getT()), i, 8);
            }
        }
    }

    public void cambiarASoles() {
        if ( !detalles.isEmpty() ) {
            int size = getDetalles().size();
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDetalles().get(i);
                setValueAt(Asoles(df.getPrecioRepuesto(), getT()), i, 5);
                setValueAt(Asoles(df.getD().getPreciolista(), getT()), i, 8);
            }
        }
    }

    private double Asoles(double precio, Tipocambio t) {
        return util.Redondear2Decimales((precio * t.getValorventa()));
    }

    private double ADolares(double precio, Tipocambio t) {
        return util.Redondear2Decimales((precio / t.getValorventa()));
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(IU_DetalleIngSal cont) {
        this.cont = cont;
    }

    /**
     * @return the Moneda
     */
    public String getMoneda() {
        return Moneda;
    }

    /**
     * @param Moneda the Moneda to set
     */
    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    /**
     * @return the t
     */
    public Tipocambio getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Tipocambio t) {
        this.t = t;
    }

    public final void CeldasEditables() {
        habilitado[7] = false;
        habilitado[8] = false;
        habilitado[9] = false;
        habilitado[10] = false;
    }

    /**
     * @return the detalles
     */
    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }
}