package Servicios.facturacion;

import Entidades.Cabeces;
import Entidades.Cabecproformas;
import Entidades.Cabecweb;
import Entidades.Clientes;
import Entidades.Otros.DetalleFactura;
import Entidades.Detalleproformas;
import Entidades.Detallees;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.SOL_COMBO;
import Entidades.PaquetesRepuestos;
import Entidades.Pedidoweb;
import Entidades.Repuestos;
import Entidades.Tipocambio;
import Presentacion.Facturacion.IU_Facturacion;
import Servicios.Servicio_TipoCambio;
import Servicios.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class tabla extends AbstractTableModel {

    String[] columnNames = {"Item", "Linea", "Nro Parte", 
//                            "Cod. Secundario", 
                            "Descripción", 
                            "Aplicación", // = Equipos
                            "Stock", "P. Lista",
                            "Pedida", "Entregada", "Precio", 
                            "Dcto1", "Dcto2", "Dcto3", "Dcto4",
                            "Total", 
                            "Disponibilidad",
                            "Cod.Sec"};
    boolean[] habilitado = {false, false, false, 
                            false, 
                            false, 
                            false, false, 
                            true, true, true, 
                            true, true, true, true, 
                            false, 
                            true,
                            false};
    private IU_Facturacion f;
    private final ArrayList<DetalleFactura> Detalle_Fac;
    private final ArrayList<Repuestos> repuestosSinStock;
    private ArrayList<Detallees> detsv;
    private int igv;
    
    private Tipocambio t;
    
    private double CostoTotal;
    private boolean Calculo_Proforma;
    Util util;
    
    private double valorVenta; // del Tipo de Cambio actual
    private int monedaControlRepuestos;

    public tabla(String valorVenta, int monedaControlRepuestos) {
        Detalle_Fac = new ArrayList<>();
        repuestosSinStock = new ArrayList<>();
        util = new Util();
        this.valorVenta = Double.parseDouble(valorVenta);
        this.monedaControlRepuestos = monedaControlRepuestos;
    }
    
    public tabla() {
        Detalle_Fac = new ArrayList<>();
        repuestosSinStock = new ArrayList<>();
        util = new Util();
//        t = new Tipocambio();
    }
    
    public tabla(IU_Facturacion f, Date fechaEmisionGR) {
        this.f = f;
//        igv = f.getIgv(); = 0
        Detalle_Fac = new ArrayList<>();
        repuestosSinStock = new ArrayList<>();
        util = new Util();
        t = new Servicio_TipoCambio().obtenerTipoCambio(fechaEmisionGR);
    }

    @Override
    public int getRowCount() {
        return getDt().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        System.out.println("(1)getValueAt...");
        Repuestos rp = getDt().get(rowIndex).getD().getRepuestos();
//        System.out.println("rp:" + rp);
//        System.out.println("repuesto:" + rp.getCodrepuesto());
        double pl = Double.parseDouble(util.DosDecimales(getDt().get(rowIndex).getD().getPreciolista()));
//        System.out.println("(1)getValueAt..pl:" + pl);
        double pr = Double.parseDouble(util.DosDecimales(getDt().get(rowIndex).getPrecioReferencial()));
//        System.out.println("(1)getValueAt..pr:" + pr);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return rp.getEquipos().getIdequipo();
            case 2:
                return rp.getCodrepuesto();
            case 3:
                return rp.getDescripcion();
            case 4:
                return rp.getDescrmodelo();
//            case 3:
//                return rp.getCodigoseg();
            case 5:
                return rp.getStock();
            case 6:
//                System.out.println("(getValueAt)case 6...Precio Lista" + util.DosDecimales(pl));
//                System.out.println("(getValueAt)case 6...Precio referencial:" + util.DosDecimales(pr));
//                return util.DosDecimales(pl);
                return util.DosDecimales(pr);
            case 7:
                if ( getDt().get(rowIndex).getD().getCantpedida() < 0 ) {
                    return 0;
                    
                } else {
                    return getDt().get(rowIndex).getD().getCantpedida();
                }
            case 8:
                if (getDt().get(rowIndex).getD().getCantentregada() < 0) {
                    return 0;
                } else {
                    return getDt().get(rowIndex).getD().getCantentregada();
                }
            case 9:
//                System.out.println("case 9...(getValueAt)" + util.DosDecimales(pl));
                return util.DosDecimales(pl);
            case 10:
                double d1 = getDt().get(rowIndex).getD().getDescuento1();
                return d1 == 0.0 ? "0.00" : util.DosDecimales(d1);
            case 11:
                double d2 = getDt().get(rowIndex).getD().getDescuento2();
                return d2 == 0.0 ? "0.00" : util.DosDecimales(d2);
            case 12:
                double d3 = getDt().get(rowIndex).getD().getDescuento3();
                return d3 == 0.0 ? "0.00" : util.DosDecimales(d3);
            case 13:
                double d4 = getDt().get(rowIndex).getD().getDescuento4();
                return d4 == 0.0 ? "0.00" : util.DosDecimales(d4);
            case 14:
//                System.out.println("pl:" + pl);
//                System.out.println("rowIndex:" + rowIndex);
//                System.out.println("cant.pedida:" + getDt().get(rowIndex).getD().getCantpedida());
                double total = pl * getDt().get(rowIndex).getD().getCantpedida();
//                System.out.println("total:" + total);
                
//                double dscto1 = getDt().get(rowIndex).getD().getDescuento1();
//                double dscto2 = getDt().get(rowIndex).getD().getDescuento2();
//                double totalConDsctos = pl * getDt().get(rowIndex).getD().getCantpedida() * (1 - dscto1/100) * (1 - dscto2/100);
//                System.out.println("totalConDsctos:" + totalConDsctos);
                
                if ( total < 0 ) {
                    return "0.00";
                } else {
                    double tot = getDt().get(rowIndex).getTotal();
//                    System.out.println("tot:" + tot);
                    return util.DosDecimales(tot);
                }
            case 15:
                String disponibilidad = getDt().get(rowIndex).getD().getDisponibilidad();
                return disponibilidad;

            case 16:
                 return rp.getCodigoseg();

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
//        System.out.println("(2)setValueAt...");
        try {
            DetalleFactura det = getDt().get(rowIndex);
            Detallees d = det.getD();
            double tot;
            String valor = String.valueOf(aValue);
            Object oldValue = getValueAt(rowIndex, columnIndex);
            
            switch (columnIndex) {
                case 6:
                    break;
                    
                case 7:
                    int cp = Integer.parseInt(valor);
                    d.setCantpedida(cp);
                    if ( (int) getValueAt(rowIndex, 5) >= cp ) {
                        d.setCantentregada(cp);
                    } else {
                        if ( d.getRepuestos().getStock() >= 0 ) {
                            d.setCantentregada(d.getRepuestos().getStock());
                        } else {
                            d.setCantentregada((int) oldValue);
                        }
                    }
                    if ( Calculo_Proforma ) {
                        tot = util.Redondear2Decimales(d.getCantpedida() * PrecioConDcto(rowIndex));
                    } else {
                        tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    }
                    det.setTotal(tot);
                    VerificarStockRepuestos();
                    break;
                    
                case 8:
                    int ce = Integer.parseInt(valor);
                    if ( (int) getValueAt(rowIndex, 7) >= ce ) {
                        if (Integer.parseInt(valor) <= d.getRepuestos().getStock()) {
                            d.setCantentregada(Integer.parseInt(valor));
                            tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                            det.setTotal(tot);
                            VerificarStockRepuestos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad entregada no puede ser mayor que la cantidad pedida", "Validacion", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 9:
//                    System.out.println("case 9...(setValueAt)");
                    double value = Double.parseDouble(valor);
//                    System.out.println("valor:" + value);
                    d.setPreciolista(value);
                    tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                    
                case 10:
                    d.setDescuento1(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                    
                case 11:
                    d.setDescuento2(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                    
                case 12:
                    d.setDescuento3(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                    
                case 13:
                    d.setDescuento4(Double.parseDouble(valor));
                    tot = util.Redondear2Decimales(d.getCantentregada() * PrecioConDcto(rowIndex));
                    det.setTotal(tot);
                    break;
                    
                case 15:
                    d.setDisponibilidad(valor.toUpperCase());
                    break;
                    
            }
            det.setD(d);
            getDt().set(rowIndex, det);
            
        } catch ( NumberFormatException e ) {
            System.out.println("Excepcion:" + e.getMessage());
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public boolean agregar(Repuestos rp, Clientes c) {
//        System.out.println("agregar....>>>");
        if ( estaEn(rp) ) {
            return false;
        }
        DetalleFactura det = Detalle(rp, c);
        getDt().add(det);
        fireTableDataChanged();
        CalcularTotales();
        return true;
    }

    public void agregarSV(Clientes cliente, ArrayList<Detallees> det) { // Agregar salidas varias
        detsv = det;
        CambiarCeldasEditables();
        
        for ( Detallees detalle : detsv ) {
            int indice = repuesto_registrado(detalle.getRepuestos());
            
            if ( indice >= 0 ) {
                int cantidad_entregada = detalle.getCantentregada();
                Detalle_Fac.get(indice).getD().setCantentregada(Detalle_Fac.get(indice).getD().getCantentregada() + cantidad_entregada);
                Detalle_Fac.get(indice).getD().setCantpedida(Detalle_Fac.get(indice).getD().getCantpedida() + cantidad_entregada);
                double tot = util.Redondear2Decimales(Detalle_Fac.get(indice).getD().getCantentregada() * PrecioConDcto(indice));
                Detalle_Fac.get(indice).setTotal(tot);
                fireTableDataChanged();
                
            } else {
                DetalleFactura df = DetalleCS(detalle, cliente);
                getDt().add(df);
                double tot = util.Redondear2Decimales(df.getD().getCantentregada() * PrecioConDcto(getDt().size() - 1));
                getDt().get(getDt().size() - 1).setTotal(tot);
            }

        }
        CalcularTotales();
    }

    public void agregarPR(ArrayList<PaquetesRepuestos> p, String moneda) { // Agregar de paquete repuestos
        for ( PaquetesRepuestos pr : p ) {
            DetalleFactura det = Detalle(pr.getRepuestos(), null);
            det.getD().setCantpedida(pr.getCantidad());
            
            if ( pr.getCantidad() > pr.getRepuestos().getStock() ) {
                det.getD().setCantentregada(pr.getRepuestos().getStock());
                repuestosSinStock.add(pr.getRepuestos());
                
            } else {
                det.getD().setCantentregada(pr.getCantidad());
            }
            getDt().add(det);
            double tot = util.Redondear2Decimales(det.getD().getCantentregada() * PrecioConDcto(getDt().size() - 1));
            getDt().get(getDt().size() - 1).setTotal(tot);
        }
        if ( repuestosSinStock.size() > 0 ) {
            f.lb_mensaje.setText("Stock Insuficiente");
            repuestosSinStock.clear();
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public void agregarDP(Cabecproformas cabp, ArrayList<Detalleproformas> detp) { // Agregar detalle proformas
//        System.out.println(">>>agregarDP XD");
        for ( Detalleproformas dp : detp ) {
            DetalleFactura dtf = DetalleP(cabp, dp);
            Detalle_Fac.add(dtf);
            double tot = util.Redondear2Decimales(dtf.getD().getCantentregada() * PrecioConDcto(Detalle_Fac.size() - 1));
            Detalle_Fac.get(Detalle_Fac.size() - 1).setTotal(tot);
        }
        if ( repuestosSinStock.size() > 0 ) {
            f.lb_mensaje.setText("STOCK INSUFICIENTE");
            repuestosSinStock.clear();
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public void agregarPW(Cabecweb cw, ArrayList<Pedidoweb> detalleCabec) { //Agregar PedidoWeb
        for ( Pedidoweb pw : detalleCabec ) {
            DetalleFactura dtf = DetallePW(cw, pw);
            getDt().add(dtf);
            double tot = util.Redondear2Decimales(dtf.getD().getCantentregada() * PrecioConDcto(getDt().size() - 1));
            getDt().get(getDt().size() - 1).setTotal(tot);
        }
        if ( repuestosSinStock.size() > 0 ) {
            f.lb_mensaje.setText("STOCK INSUFICIENTE");
            repuestosSinStock.clear();
        }
        fireTableDataChanged();
        CalcularTotales();
    }

    public void agregarGuia(Cabeces guia, List<Detallees> detalleGuia, int igv) { // agregar de guia de remision
//        System.out.println(">>>agregarGuia.... XD");
        
        for ( Detallees d : detalleGuia ) {
            DetalleFactura dtf = DetalleGuia(/*guia,*/d);
            getDt().add(dtf);
//            System.out.println("cant entregada:" + dtf.getD().getCantentregada());
//            System.out.println("size Detalle_Fac:" + Detalle_Fac.size());
//            System.out.println("Precio con Dscto:" + PrecioConDcto(Detalle_Fac.size() - 1));
            double tot = util.Redondear2Decimales(dtf.getD().getCantentregada() * PrecioConDcto(Detalle_Fac.size() - 1));
//            System.out.println("tot:" + tot);
            Detalle_Fac.get(Detalle_Fac.size() - 1).setTotal(tot);
        }
        if ( repuestosSinStock.size() > 0 ) {
            f.lb_mensaje.setText("STOCK INSUFICIENTE");
            repuestosSinStock.clear();
        }
        fireTableDataChanged();
        this.igv = igv;
        CalcularTotales();
    }
    
//    public void agregarDetFacturaAPartirGR(Cabeces cabgr, ArrayList<Detallees> detgr) {
//        System.out.println(">>>agregarDP XD");
//        for ( Detallees dp : detgr ) {
//            DetalleFactura dtf = DetalleP(cabgr, dp);
//            Detalle_Fac.add(dtf);
//            double tot = util.Redondear2Decimales(dtf.getD().getCantentregada() * PrecioConDcto(Detalle_Fac.size() - 1));
//            Detalle_Fac.get(Detalle_Fac.size() - 1).setTotal(tot);
//        }
//        if ( repuestosSinStock.size() > 0 ) {
//            f.lb_mensaje.setText("STOCK INSUFICIENTE");
//            repuestosSinStock.clear();
//        }
//        fireTableDataChanged();
//        CalcularTotales();
//    }

    private DetalleFactura DetalleCS(Detallees d, Clientes cliente) {
        DetalleFactura df = new DetalleFactura();
        df.setD(d);
        df.setTotal(0.0);
        return df;
    }

    private DetalleFactura Detalle(Repuestos rpt, Clientes cs) {
//        System.out.println("Crear DetalleFactura Detalle....>>>>");
        Detallees d = new Detallees();
        d.setRepuestos(rpt);
        d.setCantentregada(0);
        d.setCantpedida(0);

        if ( cs == null ) {
            d.setDescuento1(0.0);
            d.setDescuento2(0.0);
            d.setDescuento3(0.0);
            d.setDescuento4(0.0);
            
        } else {
            d.setDescuento1(cs.getDescuento1());
            d.setDescuento2(cs.getDescuento2());
            d.setDescuento3(cs.getDescuento3());
            d.setDescuento4(cs.getDescuento4());
        }

        d.setDisponibilidad("INMEDIATA");
        d.setUltimocosto(rpt.getPcostoultimo());
        // Obtener preciolista de Repuestos en BD (por defecto)
        double precRep = rpt.getPreciolista();
        double ultCos = rpt.getPcostoultimo();
        
        
       System.out.println("P.Costo Ultimo : " + rpt.getPcostoultimo());
        
// JSP 22.12.2023
        double precCosto = rpt.getPcostoultimo();
        d.setCostopromedio(precCosto);
        d.setUltimocosto(ultCos);
// fin JSP
        

        // Asignar preciolista para Detallees en BD
        int monedaCombo = f.cb_moneda.getSelectedIndex();
        if ( monedaCombo == DOLAR_COMBO ) {
            d.setPreciolista(precRep);
            
        } else if ( monedaCombo == SOL_COMBO ) {
            d.setPreciolista(ASoles(precRep));
        }
        
        // Asignar detalleFactura a Detallees en BD (d.getPreciolista es el preciolista de detallees en BD)
        DetalleFactura det = new DetalleFactura();
        det.setD(d);
        
        String monedaPorDefecto = (monedaControlRepuestos == DOLAR_COMBO ? "DOLARES" : "SOLES"); // 0 = Soles, 1 = Dolares
        
        det.setTotal(0.0);
        det.setItem(getDt().size() + 1);
        
        // Asignar precioRepuestos en DetalleFactura
        det.setPrecioRepuesto(d.getPreciolista());
        det.setPrecioReferencial(d.getPreciolista());
//        System.out.println("TOTALES seteados:::");
//        System.out.println("Total de DetalleFactura:" + det.getTotal());
        return det;
    }

    private DetalleFactura DetalleP(Cabecproformas cabp, Detalleproformas dp) {
//        System.out.println("DetalleP (Proforma)...");
        Detallees dtlle = new Detallees();
        double precRep = dp.getPreciolista();
        int cantPedida = dp.getCantidad();
        int stock = dp.getRepuestos().getStock();
        
        if ( cantPedida <= stock ) {
            dtlle.setCantentregada(cantPedida);
            
        } else {
            dtlle.setCantentregada(stock);
            repuestosSinStock.add(dp.getRepuestos());
        }
        // Objeto Detallees (Por defecto en BD)
        dtlle.setRepuestos(dp.getRepuestos());
        dtlle.setCantpedida(dp.getCantidad());
        dtlle.setClientes(cabp.getClientes());
        dtlle.setPreciolista(precRep);
        dtlle.setDescuento1(dp.getDescuento1());
        dtlle.setDescuento2(dp.getDescuento2());
        dtlle.setDescuento3(dp.getDescuento3());
        dtlle.setDescuento4(dp.getDescuento4());
        dtlle.setDisponibilidad(dp.getDisponibilidad());
        
        DetalleFactura dtf = new DetalleFactura();
        dtf.setD(dtlle);
        dtf.setPrecioRepuesto(precRep);
        return dtf;
    }

    private DetalleFactura DetallePW(Cabecweb cw, Pedidoweb pw) {
        Detallees dtlle = new Detallees();
        double precRep = pw.getPreciolista();
        
//        if ( f.cb_moneda.getSelectedIndex() == 1 ) {  asi estaba        
        if ( f.cb_moneda.getSelectedIndex() == 0 ) {
            precRep = ASoles(precRep);
        }
        dtlle.setClientes(cw.getClientes());
        dtlle.setRepuestos(pw.getRepuestos());
        dtlle.setCantpedida(pw.getCantpedida());
        dtlle.setPreciolista(precRep);
        int cantPedida = pw.getCantpedida();
        int Stock = pw.getRepuestos().getStock();
        
        if ( cantPedida <= Stock ) {
            dtlle.setCantentregada(cantPedida);
            
        } else {
            dtlle.setCantentregada(pw.getRepuestos().getStock());
            repuestosSinStock.add(pw.getRepuestos());
        }
        double dscto1 = pw.getDescuento1();
        double dscto2 = pw.getDescuento2();
        double dscto3 = pw.getDescuento3();
        double dscto4 = pw.getDescuento4();
        
        dtlle.setDescuento1(dscto1);
        dtlle.setDescuento2(dscto2);
        dtlle.setDescuento3(dscto3);
        dtlle.setDescuento4(dscto4);
        
        DetalleFactura dtf = new DetalleFactura();
        dtf.setD(dtlle);
        return dtf;
    }

    private DetalleFactura DetalleGuia(/*Cabeces guia, */Detallees deta) {
        DetalleFactura dtf = new DetalleFactura();
        dtf.setD(deta);
        return dtf;
    }

    public void borrar(int col) {
        getDt().remove(col);
        fireTableDataChanged();
        CalcularTotales();
        VerificarStockRepuestos();
    }

    public void aplicarDctos(double... n) {
        for ( int i = 0; i < getDt().size(); i++ ) {
            for ( int j = 0; j < n.length; j++ ) {
                setValueAt(n[j], i, j + 10);
            }
        }
        CalcularTotales();
    }

    public IU_Facturacion getF() {
        return f;
    }

    public void setF(IU_Facturacion f) {
        this.f = f;
    }

    private double TotalBruto() {
        
        double totalBruto = 0.0;
        double CostoTotal = 0.00;
        for ( DetalleFactura d : getDt() ) {
            if ( d.getD().getCantentregada() < 0 ) {
                totalBruto += 0.0;
                CostoTotal += 0.0;
                
            } else {
                double pl = 0.00;
                double cp = 0.00;
                pl = d.getD().getPreciolista();
                cp = d.getD().getRepuestos().getCostopromedio();
                totalBruto = util.Redondear2Decimales(totalBruto + d.getD().getCantentregada() * pl);
//              CostoTotal += d.getD().getCantentregada() * d.getD().getRepuestos().getCostopromedio();
                CostoTotal = util.Redondear2Decimales(CostoTotal + d.getD().getCantentregada() * cp);                
            }
        }
        totalBruto = util.Redondear2Decimales(totalBruto);
        CostoTotal = util.Redondear2Decimales(CostoTotal);        
        
        return totalBruto;
    }

//
    private double costoTotal() {
        
        double CostoTotal = 0.00;
        for ( DetalleFactura d : getDt() ) {
            if ( d.getD().getCantentregada() < 0 ) {
                CostoTotal += 0.0;
            } else {
                double cp = 0.00;
                cp = d.getD().getRepuestos().getCostopromedio();
                CostoTotal = util.Redondear2Decimales(CostoTotal + d.getD().getCantentregada() * cp);                
            }
        }
        CostoTotal = util.Redondear2Decimales(CostoTotal);        
        
        return CostoTotal;
    }
    
//    
    
    
    
    private double Total() {
        double total = 0.0;
        for ( DetalleFactura d : getDt() ) {
            if ( d.getTotal() < 0 ) {
                total += 0.0;
            } else {
                total += d.getTotal();
            }
        }
        total = util.Redondear2Decimales(total);
        return total;
    }

    private double TotalIGV(double total) {
        double IGV = Double.parseDouble(String.valueOf(igv));
//        System.out.println(">> total IGV: " + util.Redondear2Decimales((total / ((100.0 + IGV) / 100.0))));
        return util.Redondear2Decimales((total / ((100.0 + IGV) / 100.0)));
    }

    private double PrecioConDcto(int row) {
        Detallees d = getDt().get(row).getD();
        double d1 = d.getDescuento1() / 100.0;
        double d2 = d.getDescuento2() / 100.0;
        double d3 = d.getDescuento3() / 100.0;
        double d4 = d.getDescuento4() / 100.0;

        double Precio = d.getPreciolista();
//        System.out.println("f:" + f);
//        System.out.println("f.cb_moneda.getSel:" + f.cb_moneda.getSelectedItem());
        String monedaFact = String.valueOf(f.cb_moneda.getSelectedItem());
//        System.out.println("monedaFact:" + monedaFact);
        
        if ( f.provieneDe == 1 ) { // 2. Se factura una proforma
            String monProfBD = f.cabProforma.get(0).getMoneda();
            if ( "Dolares".equals(monedaFact) && "1".equals(monProfBD) ) {
                Precio = util.Redondear2Decimales(Precio / valorVenta);
            }
            
        } else { // 1. Se genera de forma directa proforma o factura
            if ( "Dolares".equals(monedaFact) ) {
                if ( monedaControlRepuestos == DOLAR_COMBO ) {
                    Precio = util.Redondear2Decimales(d.getPreciolista());   
                }
            }
        }
        Precio = ((((Precio * (1 - d1)) * (1 - d2))) * (1 - d3)) * (1 - d4);
        return Precio;
    }

    private boolean estaEn(Repuestos rp) {
        for ( DetalleFactura fac : getDt() ) {
            if ( rp.getId().getIdrepuesto() == fac.getD().getRepuestos().getId().getIdrepuesto() ) {
                return true;
            }
        }
        return false;
    }
    
    private void CalcularTotales() {
        double tb = TotalBruto();
        double ct = costoTotal();
        double tot = Total();
        double ValorVenta = TotalIGV(tot);
        double montoIGV = tot - ValorVenta;
//        System.out.println("----------------------------------");
//        System.out.println("totalBruto:" + tb);
//        System.out.println("tot:" + tot);
//        System.out.println("ValorVenta:" + ValorVenta);
//        System.out.println("igv:" + montoIGV);
//        System.out.println("----------------------------------");
        
        f.lb_TotalBruto.setText(util.DosDecimales(tb));
        f.lb_ValorVenta.setText(util.DosDecimales(ValorVenta));
        f.lb_IGV.setText(util.DosDecimales(util.Redondear2Decimales(montoIGV)));
        f.lb_Total.setText(util.DosDecimales(tot));
        


        f.lb_CostoTotal.setText(util.DosDecimales(ct));        
        System.out.println("Total Costo Factura : " + ct);        
    }

    public void cambiarADolares() {
        if ( !Detalle_Fac.isEmpty() ) {
            int size = getDt().size();
            
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDt().get(i);
                
//                System.out.println("lista de DetalleFactura::" + getDt());
//                System.out.println("df:" + df);
//                System.out.println("precio de lista::" + df.getD().getPreciolista());
                double precioSoles = Double.parseDouble(util.DosDecimales(df.getD().getPreciolista())); // Está en SOLES
//                System.out.println("precioSoles:" + precioSoles);
                double precioDolares = ADolares(precioSoles);
//                System.out.println("precioDolares:" + precioDolares);
                setValueAt(precioDolares, i, 9);
            }
        }
    }

    public void cambiarASoles() {
//        System.out.println("cambiarASoles(tabla.java)");
        if ( !Detalle_Fac.isEmpty() ) {
            int size = getDt().size();
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDt().get(i);
                double precioSoles = 0.00;
                
                
//                System.out.println("Tipo de Cambio     : " + Tipocambio);
                System.out.println("Tipo de Cambio (t) : " + t);                
                if ( monedaControlRepuestos == DOLAR_COMBO ) { // preciolista viene de Maestro Repuestos en dólares
                    if ( f.cb_moneda.getSelectedIndex() == DOLAR_COMBO ) {
                        precioSoles = Double.parseDouble(util.DosDecimales(ASoles(df.getD().getPreciolista()))); 

                    } else if ( f.cb_moneda.getSelectedIndex() == SOL_COMBO ) {
                        precioSoles = Double.parseDouble(util.DosDecimales(df.getD().getPreciolista()));
                    }
                    
                } else if ( monedaControlRepuestos == SOL_COMBO ) { // preciolista viene de Maestro Repuestos en soles
                    if ( f.cb_moneda.getSelectedIndex() == DOLAR_COMBO ) {

                        precioSoles = Double.parseDouble(util.DosDecimales(ADolares(df.getD().getPreciolista())));

                    } else if ( f.cb_moneda.getSelectedIndex() == SOL_COMBO ) {
                        precioSoles = Double.parseDouble(util.DosDecimales(df.getD().getPreciolista()));
                    }
                }
//                System.out.println("$->S/. precio en SOLES(tabla.java en setValueAt a 9):" + precioSoles);
                setValueAt(precioSoles, i, 9);
            }
        }
    }
    
    public void solesPorDefecto() {
        if ( !Detalle_Fac.isEmpty() ) {
            int size = getDt().size();
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDt().get(i);
                setValueAt(df.getPrecioRepuesto(), i, 6);
                setValueAt(df.getD().getPreciolista(), i, 9);
            }
        }
    }
    
    public void dolaresPorDefecto() {
        if ( !Detalle_Fac.isEmpty() ) {
            int size = getDt().size();
            for ( int i = 0; i < size; i++ ) {
                DetalleFactura df = getDt().get(i);
                setValueAt(df.getPrecioRepuesto(), i, 6);
                setValueAt(df.getD().getPreciolista(), i, 9);
            }
        }
    }

    private double ASoles(double precio) {
     return util.Redondear2Decimales((precio * t.getValorventa()));
//   return util.Redondear2Decimales((precio * 1));
        
    }

    private double ADolares(double precio) {
        return util.Redondear2Decimales((precio / t.getValorventa()));
    }

    public int getIgv() {
        return igv;
    }

    public void setIgv(int igv) {
        this.igv = igv;
    }

    public Tipocambio getT() {
        return t;
    }

    public void setT(Tipocambio t) {
        this.t = t;
    }

    public ArrayList<DetalleFactura> getDt() {
        return Detalle_Fac;
    }

    public Repuestos GetIdRep(int index) {
        return Detalle_Fac.get(index).getD().getRepuestos();
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    private void CambiarCeldasEditables() {
        for (int i = 0; i < habilitado.length; i++) {
            habilitado[i] = false;
        }
    }

    private void VerificarStockRepuestos() {
        for ( DetalleFactura detalle : Detalle_Fac ) {
            if ( detalle.getD().getRepuestos().getStock() < detalle.getD().getCantpedida() ) {
                f.lb_mensaje.setText("STOCK INSUFICIENTE");
                return;
            }
        }
        f.lb_mensaje.setText("");
    }

    private int repuesto_registrado(Repuestos r) {
        for ( int i = 0; i < Detalle_Fac.size(); i++ ) {
            if ( Detalle_Fac.get(i).getD().getRepuestos() == r ) {
                return i;
            }
        }
        return -1;
    }

    public void SetCalculoProforma(boolean calculo_Proforma) {
        this.Calculo_Proforma = calculo_Proforma;
        habilitado[8] = !Calculo_Proforma;
        SetTotales_Proforma();
    }

    private void SetTotales_Proforma() {
        for ( int i = 0; i < Detalle_Fac.size(); i++ ) {
            setValueAt(getValueAt(i, 7), i, 7);
            setValueAt(getValueAt(i, 15), i, 15);
        }
        fireTableDataChanged();
    }

}