/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.facturacion;

import Entidades.Otros.NroLetras;
import Servicios.Util;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import static Entidades.Otros.Constante.TIPO_IU_CANJE_LETRAS;
import static Entidades.Otros.Constante.TIPO_IU_CANJE_DEUDAS;

/**
 *
 * @author Administrador
 */
public class Tabla_letras extends AbstractTableModel {

    String[] columnNames = {"Nro Letra", "Importe", "Dias Vcto.", "Fecha de Vcto."};
//    boolean[] habilitado = {false, false, true, true};
    boolean[] habilitado = {false, true, true, true};
    private ArrayList<NroLetras> lt;
    private final double totalventa;
    Calendar hoy;
    int Milisegundos_dia = 24 * 60 * 60 * 1000;
    int dias_extra;
    Date fechaInicio;
    Util util;
    
    Presentacion.CanjexLetras.IU_ModuloCanje iuModCan;
    Presentacion.CanjeLetrasProtestadas.IU_ModuloCanje iuModCanCliente;
    String tipoIUCanje;

    public Tabla_letras(double totalventa, Date fechaInicio, Util u) {
        this.totalventa = totalventa;
        this.fechaInicio = fechaInicio;
        lt = new ArrayList<>();
        hoy = Calendar.getInstance();
        util = u;
    }
    
    public Tabla_letras(double totalventa, Date fechaInicio,Util u, Presentacion.CanjexLetras.IU_ModuloCanje iuModCan) {
        this.iuModCan = iuModCan;
        tipoIUCanje = "L";
        System.out.println("Tabla_letras");
        
        this.totalventa = totalventa;
        this.fechaInicio = fechaInicio;
        lt = new ArrayList<>();
        hoy = Calendar.getInstance();
        util = u;
    }

    public Tabla_letras(double totalventa, Date fechaInicio,Util u, Presentacion.CanjeLetrasProtestadas.IU_ModuloCanje iuModCanCliente) {
        this.iuModCanCliente = iuModCanCliente;
        tipoIUCanje = "D";
        System.out.println("Tabla_Deudas");
        
        this.totalventa = totalventa;
        this.fechaInicio = fechaInicio;
        lt = new ArrayList<>();
        hoy = Calendar.getInstance();
        util = u;
    }
    
    public void SetImporteEditable(boolean bool) {
        habilitado[1] = bool;
    }

    @Override
    public int getRowCount() {
        return getLt().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NroLetras letra = getLt().get(rowIndex);
        Object retorno = null;
        switch (columnIndex) {
            case 0:
                retorno = rowIndex + 1;
                break;
            case 1:
//                System.out.println("letra.getImporte:" + letra.getImporte());
                retorno = util.DosDecimales(letra.getImporte());
                break;
            case 2:
                retorno = letra.getDiasVcto();
                break;
            case 3:
                retorno = letra.getFechaVec();
                break;
        }
        return retorno;
    }
    
    private void actualizarTotalImporte() {
        double totImp = 0.00;
//        System.out.println("N° elementos de lista:" + lt.size());
        if ( !lt.isEmpty() ) {
            for ( int i = 0; i < lt.size(); i++ ) {
                double importe = Double.parseDouble(String.valueOf(getValueAt(i, 1)));
                totImp = totImp + importe;
            }
        }
//        System.out.println("totImp:::" + totImp);
        String impTot = util.DosDecimales(totImp);
//        System.out.println("impTot:" + impTot);
//        System.out.println("tipoIUCanje:" + tipoIUCanje);
        
        if ( TIPO_IU_CANJE_LETRAS.equals(tipoIUCanje) ) {
//            System.out.println("CANJE LETRAS");
            iuModCan.txtTotImporte.setText(impTot);
            
        } else if ( TIPO_IU_CANJE_DEUDAS.equals(tipoIUCanje) ) {
//            System.out.println("CANJE DEUDAS");
            iuModCanCliente.txtTotImporte.setText(impTot);
        }
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
        switch (columnIndex) {
            case 0:
            case 2:
                return Integer.class;
            case 1:
                return String.class;
            case 3:
                return JDateChooser.class;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            NroLetras letra = getLt().get(rowIndex);
            switch (columnIndex) {
                case 1:
                    double importe = Double.parseDouble(String.valueOf(aValue));
                    letra.setImporte(importe);
                    getLt().set(rowIndex, letra);
                    break;
                case 2:
                    int i = Integer.parseInt(String.valueOf(aValue));
                    Calendar hoy_c = (Calendar) hoy.clone();
                    hoy_c.add(Calendar.DATE, i);
                    letra.setDiasVcto(i);
                    letra.setFechaVec(hoy_c.getTime());
                    getLt().set(rowIndex, letra);

                    break;
                case 3:
                    int d = 0;
                    long dias = 0;
                    Date venc = letra.getFechaVec().getDate();
                    Date h = hoy.getTime();
                    if (venc != null) {
                        dias = (venc.getTime() - h.getTime()) / Milisegundos_dia;
                    }
                    d = (int) (dias);
                    if (d <= 0) {
                        JOptionPane.showMessageDialog(null, "Fecha Inválida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    letra.setDiasVcto(d);
                    getLt().set(rowIndex, letra);
                    break;

            }
        } catch (NumberFormatException e) {

        }
        fireTableDataChanged();
        actualizarTotalImporte();
    }

    public void asignarLetras(int num) {
        double numero = Double.parseDouble(String.valueOf(num));
        double impxLetra = util.Redondear2Decimales((totalventa / numero));
        
        for ( int i = 0; i < num; i++ ) {
            NroLetras nuevaletra = new NroLetras();
            nuevaletra.setNro(i);
            nuevaletra.setImporte(impxLetra);
            
            if ( i == num - 1 ) {
                nuevaletra.setImporte(util.Redondear2Decimales((nuevaletra.getImporte() + Diferencia(impxLetra, num))));
            }
            getLt().add(nuevaletra);
        }
        fireTableDataChanged();
    }
    
    public void asignarLetras(int num, String sumaTotal) {
        double sumTot = Double.parseDouble(sumaTotal);
        double numero = Double.parseDouble(String.valueOf(num));
        double impxLetra = util.Redondear2Decimales((sumTot / numero));
        
        for ( int i = 0; i < num; i++ ) {
            NroLetras nuevaletra = new NroLetras();
            nuevaletra.setNro(i);
            nuevaletra.setImporte(impxLetra);
            
            if ( i == num - 1 ) {
                nuevaletra.setImporte(util.Redondear2Decimales((nuevaletra.getImporte() + DiferenciaGastosAdm(impxLetra, num, sumTot))));
            }
            getLt().add(nuevaletra);
        }
        fireTableDataChanged();
    }

    public void agregar(NroLetras nueva) {
        getLt().add(nueva);
        fireTableDataChanged();
    }

    private double Diferencia(double impxLetra, int num) {
        double dif = 0.0;
        for (int i = 0; i < num; i++) {
            dif += impxLetra;
        }
        dif = totalventa - dif;
        return dif;
    }

    private double DiferenciaGastosAdm(double impxLetra, int num, double sumTot) {
        double dif = 0.0;
        for ( int i = 0; i < num; i++ ) {
            dif += impxLetra;
        }
        dif = sumTot - dif;
        return dif;
    }
    
    public void eliminar(int i) {
        getLt().remove(0);
        fireTableDataChanged();
    }

    public boolean Valida() {
        if (getLt().isEmpty()) {
            return false;
        }
        int dias = 0, dias_ant;
        for (int i = 0; i < getLt().size(); i++) {
            NroLetras nl = getLt().get(i);
            if (nl.getDiasVcto() == 0 || nl.getFechaVec().getDate() == null) {
                return false;
            }
            if (habilitado[1] == true && nl.getImporte() == 0.0) {
                return false;
            }

            if (i > 0) {
                dias_ant = dias;
                dias = nl.getDiasVcto();
                if (dias <= dias_ant) {
                    return false;
                }
            }
            dias = nl.getDiasVcto();
        }
        return true;
    }

    public ArrayList<NroLetras> getLt() {
        return lt;
    }

    public void setLt(ArrayList<NroLetras> lt) {
        this.lt = lt;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        Calendar in = Calendar.getInstance(); // Fecha anterior
        Calendar fin = Calendar.getInstance(); // nueva fecha

        int dia, mes, anio;
        dia = hoy.get(Calendar.DAY_OF_MONTH);
        mes = hoy.get(Calendar.MONTH);
        anio = hoy.get(Calendar.YEAR);
        in.set(anio, mes, dia);

        dia = util.getDia(fechaInicio);
        mes = util.getMes(fechaInicio);
        anio = util.getAnio(fechaInicio);
        fin.set(anio, mes, dia);

        dias_extra = (int) ((fin.getTimeInMillis() - in.getTimeInMillis()) / Milisegundos_dia);

        hoy.add(Calendar.DATE, dias_extra);
        CalcularNuevasFechas();
    }

    private void CalcularNuevasFechas() {
        if (!lt.isEmpty()) {
            for (int i = 0; i < lt.size(); i++) {
                if ((int) getValueAt(i, 2) != 0) {
                    setValueAt((int) getValueAt(i, 2), i, 2);
                }
            }
        }
    }

    public double getTotal() {
        double total = 0.0;
        for (NroLetras letra : lt) {
            total += letra.getImporte();
        }
        return util.Redondear2Decimales(total);
    }
}