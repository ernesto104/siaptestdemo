package Servicios.EmisionPlanillas;

import Entidades.Cabeces;
import Entidades.Cabecsalvar;
import Presentacion.EmisionPlanillas.UI_FiltrarPlanillas;
import Presentacion.EmisionPlanillas.UI_GestionarPlanillas;
import Presentacion.EmisionPlanillas.UI_MostrarDetalles;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Excel;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maverick225
 * @author modified : Ledis Rivera Ch.
 */
public class Servicio_GestionarPlanillas {

    private UI_GestionarPlanillas iu;
    private Servicio_Cabeces servicio;
    public JTable tab_Planillas;
    private DefaultTableModel dftm_Planillas;
    private List ListaCab;
    private ArrayList resul_busqueda, listafiltrada;
    public boolean listaCabec;
    public boolean listaCabecSalvar;
    int tipo;
    String rol;
    String rolfinal;
    HashMap<String, String> documentos;
    Date inicio, fin;
    double total_Soles;
    double total_Soles_Facturas;
    double total_Soles_Boletas;
    double total_Soles_NotaCredito;
    double total_Soles_NotaDebito;
    double total_Dolares;
    double total_Dolares_Facturas;
    double total_Dolares_Boletas;
    double total_Dolares_NotaCredito;
    double total_Dolares_NotaDebito;
    double total_Dolares_Costos;
    double total_Soles_Costos;    
    double total_Dolares_Valorventa;
    double total_Soles_Valorventa;
    double costo_sol;

    boolean Cabec;
    Util util;

    public Servicio_GestionarPlanillas(UI_GestionarPlanillas iu, Date inicio, Date fin, int tipo) {
        /*
         * TIPO = 0 :  VENTAS
         * TIPO = 1 : COMPRAS
         * TIPO = 2 : SK
         */
        util = new Util();
        resul_busqueda = new ArrayList();
        listafiltrada = new ArrayList();
        this.iu = iu;
        servicio = new Servicio_Cabeces();
        tab_Planillas = iu.tab_Planillas;
        
        inicializarTabla(iu, inicio, fin, tipo);
    }
    
    public void inicializarTabla(UI_GestionarPlanillas iu, Date inicio, Date fin, int tipo) {
//        System.out.println("inicializarTabla..");
        listaCabec = listaCabecSalvar = false;
        dftm_Planillas = (DefaultTableModel) iu.tab_Planillas.getModel();
        this.inicio = inicio;
        this.fin = fin;
        this.tipo = tipo;

        ListaCab = Buscar_Cab();
        listaTabla(ListaCab);
    }

    public void listaTabla(List listaC) {
//        System.out.println("listaTabla..");
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        Iterator it = listaC.iterator();
//        double costo_sol =0.00;
        
        if ( !listaC.isEmpty() ) {
            IniciarVariables();
            Object o = listaC.get(0);
            
            if ( o instanceof Cabeces ) {
                Cabec = true;
                
                while ( it.hasNext() ) {
                    Cabeces c = (Cabeces) it.next();
                    Object[] row = new Object[16]; // 13
                    row[0] = i++;
                    row[1] = TipoDoc(c.getId().getTipodoc());
                    row[2] = c.getId().getNrorserie();
                    row[3] = c.getId().getNrodocumento();
                    row[4] = sd.format(c.getTipocambio().getFecha());
                    
                    String estado = c.getEstado();
//                    System.out.println("ESTADO:" + estado);
                    
                    if ( "ANULADO".equals(estado) ) {
                        if ( c.getClientes() == null ) {
                            row[5] = "(ANULADO)";
                            row[11] = 0.00;
                            
                        } else {
                            row[5] = "(ANULADO) - " + c.getClientes().getNombre();
                            row[11] = 0.00;
                        }
                    } else {
                        row[5] = c.getClientes().getNombre();
                        row[11] = c.getTotal();
                    }
//                    row[5] = "ANULADO".equals(estado) ? "(ANULADO) " + c.getClientes().getNombre() : c.getClientes().getNombre();
//                    row[5] = c.getClientes() == null ? "ANULADO" : c.getClientes().getNombre();
                    if ( c.getMoneda() == null ) {
                        row[6] = "$ 00.00";
                    } else {
                        if ( "ANULADO".equals(estado) ) {
                            row[6] = c.getMoneda().equals("1") ? "S/. 0.00" : "$ 0.00";
                            SumarSegunTipo_Moneda(0.00, c.getId().getTipodoc(), c.getMoneda());
                        } else {
                            row[6] = c.getMoneda().equals("1") ? "S/. " + c.getTotal() : "$ " + c.getTotal();
                            SumarSegunTipo_Moneda(c.getTotal(), c.getId().getTipodoc(), c.getMoneda());

                            
                            if ( c.getCostototal() == null ) {
                                row[14] = "$ 00.00";
 // Javier Salas (Costos NC)

//                                                          
                            } else {
                                
                                if ( "03".equals(c.getId().getTipodoc()) ) {
                                    row[14] = c.getTotal() * 0.41;  
                                }                                                                                                  
                                
                            costo_sol = c.getCostototal() * c.getTipocambio_1();
                            row[14] = c.getMoneda().equals("1") ? "S/. " + c.getCostototal() : "$ " + c.getCostototal();                            
                            Double Cnc = c.getCostototal();
                            
                            SumarCostos_SegunTipo_Moneda(c.getCostototal(), c.getId().getTipodoc(), c.getMoneda(), c.getTipocambio_1(), Cnc);   
                            }
                            
                            row[15] = c.getMoneda().equals("1") ? "S/. " + c.getValorventa() : "$ " + c.getValorventa();                            
                            SumarValorventa_SegunTipo_Moneda(c.getValorventa(), c.getId().getTipodoc(), c.getMoneda());   
                          
                        }                       
                    }
                    row[7] = c.getLetraFacbol() == null ? (c.getNcFacbol() == null ? "" : c.getNcFacbol()) : c.getLetraFacbol();
                    row[8] = c;
                    if ( c.getClientes() == null ) {
                        row[9] = "";
                        
                    } else {
                        row[9] = c.getClientes().getRuc();
                    }
                    row[10] = "1".equals(c.getMoneda()) ? "S/." : "$";
//                    row[11] = "ANULADO".equals(estado) ? 0.00 : c.getTotal();
                    if ( c.getVendedores() == null ) {
                        row[12] = "EDUARDO DIAZ MORENO";
                    } else {
                        row[12] = c.getVendedores().getNombre();
                    }
                    row[13] = estado;
//                    row[14] = c.getCostototal();
                    dftm_Planillas.addRow(row);
                }
                
            } else {
                Cabec = false;
                while (it.hasNext()) {
                    Cabecsalvar cs = (Cabecsalvar) it.next();
                    Object[] row = new Object[9];
                    row[0] = i++;
                    row[1] = "SK";
                    
                    if ( !listaCabecSalvar ){
                        row[2] = cs.getCabeces().getId().getNrorserie();
                    } else {
                        row[2] = "";
                    }
                    row[3] = cs.getCodigosalida();
                    row[4] = sd.format(cs.getFecha());
                    row[5] = cs.getClientes().getNombre();
                    row[6] = "$ " + cs.getTotal();
                    SumarSegunTipo_Moneda(cs.getTotal(), "SALIDAS VARIAS", "2");
                    row[7] = "";
                    row[8] = cs.getCabeces() == null ? null : cs.getCabeces();
                    dftm_Planillas.addRow(row);
                }
            }
            Redondear();
            seteaTotales();
        }
    }

    public void muestraDetalles() {
        int fila = tab_Planillas.getSelectedRow();
        
        if ( fila >= 0 ) {
            String tipoDoc = String.valueOf(dftm_Planillas.getValueAt(fila, 1));
            String nroDoc = String.valueOf(dftm_Planillas.getValueAt(fila, 3));
            int index = Buscar_en_Lista(tipoDoc, nroDoc);
            Object c = ListaCab.get(index);
            UI_MostrarDetalles ui = new UI_MostrarDetalles(c);
            ui.setLocationRelativeTo(null);
            ui.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione al menos un item");
        }
    }

    private int Buscar_en_Lista(String tipo, String nro) {
        int i = 0;
        
        for ( Object c : ListaCab ) {
            
            if ( Cabec ) {
                Cabeces cabe = (Cabeces) c;
                
                if ( tipo.equals(TipoDoc(cabe.getId().getTipodoc())) && nro.equals(cabe.getId().getNrodocumento()) ) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    private void IniciarVariables() {
        total_Dolares = 0;
        total_Dolares_Facturas = 0;
        total_Dolares_Boletas = 0;
        total_Dolares_NotaCredito = 0;
        total_Dolares_NotaDebito = 0;
        total_Dolares_Costos = 0;
        total_Dolares_Valorventa = 0;        

        total_Soles = 0;
        total_Soles_Facturas = 0;
        total_Soles_Boletas = 0;
        total_Soles_NotaCredito = 0;
        total_Soles_NotaDebito = 0;
        total_Soles_Costos = 0; 
        total_Soles_Valorventa = 0;        
    }

    public void seteaTotales() {
        
        BigDecimal t_Soles = new BigDecimal(String.valueOf(total_Soles));
        BigDecimal t_Soles_f = new BigDecimal(String.valueOf(total_Soles_Facturas));
        BigDecimal t_Soles_b = new BigDecimal(String.valueOf(total_Soles_Boletas));
        BigDecimal t_Soles_nc = new BigDecimal(String.valueOf(total_Soles_NotaCredito));
        BigDecimal t_Soles_nd = new BigDecimal(String.valueOf(total_Soles_NotaDebito));
        BigDecimal t_Soles_costo = new BigDecimal(String.valueOf(total_Soles_Costos));        
        BigDecimal t_Soles_Valor_venta = new BigDecimal(String.valueOf(total_Soles_Valorventa));                

        //Aqui falta convertir costos a soles
        
        double t_utilidad_Soles = (total_Soles_Valorventa - total_Soles_Costos);
        
        iu.txt_S_Total.setText(util.DosDecimales(t_Soles.doubleValue()));
        iu.txt_S_TotalFacturas.setText(util.DosDecimales(t_Soles_f.doubleValue()));
        iu.txt_S_TotalBoletas.setText(util.DosDecimales(t_Soles_b.doubleValue()));
        iu.txt_S_TotalCredito.setText(util.DosDecimales(t_Soles_nc.doubleValue()));
        iu.txt_S_TotalDebito.setText(util.DosDecimales(t_Soles_nd.doubleValue()));
        iu.txt_S_TotalCosto.setText(util.DosDecimales(t_Soles_costo.doubleValue()));
        iu.txt_S_TotalUtilidad.setText(util.DosDecimales(t_utilidad_Soles));


        BigDecimal t_Dolares = new BigDecimal(String.valueOf(total_Dolares));
        BigDecimal t_Dolares_f = new BigDecimal(String.valueOf(total_Dolares_Facturas));
        BigDecimal t_Dolares_b = new BigDecimal(String.valueOf(total_Dolares_Boletas));
        BigDecimal t_Dolares_nc = new BigDecimal(String.valueOf(total_Dolares_NotaCredito));
        BigDecimal t_Dolares_nd = new BigDecimal(String.valueOf(total_Dolares_NotaDebito));
        BigDecimal t_Dolares_costo = new BigDecimal(String.valueOf(total_Dolares_Costos));        
        BigDecimal t_Dolares_Valor_venta = new BigDecimal(String.valueOf(total_Dolares_Valorventa));                
        double t_utilidad_Dolares = (total_Dolares_Valorventa - total_Dolares_Costos);
        
        iu.txt_D_Total.setText(util.DosDecimales(t_Dolares.doubleValue()));
        iu.txt_D_TotalFacturas.setText(util.DosDecimales(t_Dolares_f.doubleValue()));
        iu.txt_D_TotalBoletas.setText(util.DosDecimales(t_Dolares_b.doubleValue()));
        iu.txt_D_TotalCredito.setText(util.DosDecimales(t_Dolares_nc.doubleValue()));
        iu.txt_D_TotalDebito.setText(util.DosDecimales(t_Dolares_nd.doubleValue()));
        iu.txt_D_TotalCosto.setText(util.DosDecimales(t_Dolares_costo.doubleValue()));        
        iu.txt_D_TotalUtilidad.setText(util.DosDecimales(t_utilidad_Dolares));        
    }

    public List Buscar_Cab() {
        System.out.println("Buscar_Cab..");
        List<Cabeces> lista = null;
        List<Cabecsalvar> lista_sal = null;
        
        switch ( tipo ) {
            case 1:
                lista = servicio.obtenerPlanillaVentas(inicio, fin);
                listaCabec = true;
                break;
                
            case 2:
                lista = servicio.obtenerPlanillaCompras(inicio, fin);
                listaCabec = true;
                break;
                
            case 3:
                lista_sal = servicio.obtenerPlanillaVarias(inicio, fin);
                listaCabecSalvar = true;
                break;
        }
        return lista == null ? lista_sal : lista;
    }

    private void SumarSegunTipo_Moneda(double Monto, String tipoDoc, String Moneda) {
        if ( Moneda.equals("1") ) {
            if ( tipoDoc.equals("03") ) {
                total_Soles -= Monto;
                
            } else {
                total_Soles += Monto;
            }
            
        } else {
            if ( tipoDoc.equals("03") ) {
                total_Dolares -= Monto;
                
            } else {
                total_Dolares += Monto;
            }
        }

        switch ( tipoDoc ) {
            case "01": // factura
                if ( Moneda.equals("1") ) {
                    total_Soles_Facturas += Monto;
                    
                } else {
                    total_Dolares_Facturas += Monto;
                }
                break;
                
            case "02": // boleta
                if ( Moneda.equals("1") ) {
                    total_Soles_Boletas += Monto;
                    
                } else {
                    total_Dolares_Boletas += Monto;
                }
                break;
                
            case "03": // nota de credito
                if ( Moneda.equals("1") ) {
                    total_Soles_NotaCredito += Monto;
                    
                } else {
                    total_Dolares_NotaCredito += Monto;
                }
                break;
                
            case "04": // nota de debito
                if ( Moneda.equals("1") ) {
                    total_Soles_NotaDebito += Monto;
                    
                } else {
                    total_Dolares_NotaDebito += Monto;
                }
                break;
        }
    }

///////
    private void SumarCostos_SegunTipo_Moneda(double MontoC, String tipoDoc, String Moneda, double tipcambio, double costoNC) {

        switch ( tipoDoc ) {
            case "01": // factura
                if ( Moneda.equals("1") ) {
                    MontoC = MontoC * tipcambio;
                    total_Soles_Costos += MontoC;
                } else {
                    total_Dolares_Costos += MontoC;
                }
                break;
                
            case "02": // boleta
                if ( Moneda.equals("1") ) {
                    MontoC = MontoC * tipcambio;                    
                    total_Soles_Costos += MontoC;
                } else {
                    total_Dolares_Costos += MontoC;
                }
                break;
                
            case "03": // Nota de Credito

//                                    row[14] = c.getTotal() * 0.41;
//                                    c.getCostototal() = row[14];                
                System.out.println("Si entra aqui Costo total}" );
                System.out.println("cost NC : " + costoNC);
                if ( Moneda.equals("1") ) {
                    costoNC = costoNC * tipcambio;
                    total_Soles_Costos -= costoNC;
                } else {
                    total_Dolares_Costos -= costoNC;
                }
                break;                
              
        }
    }

    private void SumarValorventa_SegunTipo_Moneda(double MontoV, String tipoDoc, String Moneda) {

        switch ( tipoDoc ) {
            case "01": // factura
                if ( Moneda.equals("1") ) {
                    total_Soles_Valorventa += MontoV;
                } else {
                    total_Dolares_Valorventa += MontoV;
                }
                break;
                
            case "02": // boleta
                if ( Moneda.equals("1") ) {
                    total_Soles_Valorventa += MontoV;
                } else {
                    total_Dolares_Valorventa += MontoV;
                }
                break;

            case "03": // Nota de Credito
                
                System.out.println("Nota de Credito Valores de Venta NC" + MontoV );                
                if ( Moneda.equals("1") ) {
                    total_Soles_Valorventa -= MontoV;
                } else {
                    total_Dolares_Valorventa -= MontoV;
                }
                break;
                
                
        }
    }
    
//////    
    
    public void exportaExcel(String titulo) {
        if ( JOptionPane.showConfirmDialog(null, "¿Desea confirmar la operación?") == JOptionPane.YES_OPTION ) {
            Servicio_Excel excel = new Servicio_Excel(tab_Planillas, null);
            List listaCabecera = new ArrayList();
            listaCabecera.add("FECHA");
            listaCabecera.add("ID DOC.");
            listaCabecera.add("NUMERO");
            listaCabecera.add("RAZON SOCIAL");
            listaCabecera.add("RUC");
            listaCabecera.add("MONEDA");
            listaCabecera.add("TOTAL");
            listaCabecera.add("INAFECTO");
            listaCabecera.add("GLOSA");
            listaCabecera.add("CUENTA OPCIONAL");
            excel.exportarPlanillaExcel(titulo, listaCabecera, 1);
        }
    }

    public void Filtrar_planillas() {
        final UI_FiltrarPlanillas filtrar = new UI_FiltrarPlanillas(this);
        
        filtrar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if ( filtrar.aceptar ) {
                    FiltrarPor( filtrar.rb_Facturas.isSelected(),
                                filtrar.rb_Boletas.isSelected(),
                                filtrar.rb_NotasCredito.isSelected(),
                                filtrar.rb_NotasDebito.isSelected(),
                                filtrar.rb_Clientes.isSelected());
//                                filtrar.rb_Vendedor.isSelected());
                }
            }
        });
    }

    private void BorrarTabla() {
        int numRows = dftm_Planillas.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            dftm_Planillas.removeRow(0);
        }
    }

    private void Redondear() {
        total_Dolares = util.Redondear2Decimales(total_Dolares);
        total_Dolares_Facturas = util.Redondear2Decimales(total_Dolares_Facturas);
        total_Dolares_Boletas = util.Redondear2Decimales(total_Dolares_Boletas);
        total_Dolares_NotaCredito = util.Redondear2Decimales(total_Dolares_NotaCredito);
        total_Dolares_NotaDebito = util.Redondear2Decimales(total_Dolares_NotaDebito);
        total_Dolares_Costos = util.Redondear2Decimales(total_Dolares_Costos);        
        total_Dolares_Valorventa = util.Redondear2Decimales(total_Dolares_Valorventa);                

        total_Soles = util.Redondear2Decimales(total_Soles);
        total_Soles_Facturas = util.Redondear2Decimales(total_Soles_Facturas);
        total_Soles_Boletas = util.Redondear2Decimales(total_Soles_Boletas);
        total_Soles_NotaCredito = util.Redondear2Decimales(total_Soles_NotaCredito);
        total_Soles_NotaDebito = util.Redondear2Decimales(total_Soles_NotaDebito);
        total_Soles_Costos = util.Redondear2Decimales(total_Soles_Costos);       
        total_Soles_Valorventa = util.Redondear2Decimales(total_Soles_Valorventa);
    }

    public Object getCabecera(int fila) {
        return ListaCab.get(fila);
    }

    public JTable getTable() {
        return tab_Planillas;
    }

    public void buscarPor() {
        String bsq_nroDoc = iu.tx_nroDoc.getText();
        String bsq_cliente = iu.tx_cliente.getText();
        String bsq_vendedor = iu.tx_vendedor.getText();
        resul_busqueda.clear();
        List actual;
        
        if ( listafiltrada.isEmpty() ) {
            actual = ListaCab;
            
        } else {
            actual = listafiltrada;
        }
        
        for ( int i = 0; i < actual.size(); i++ ) {
            String nroDocumento, cliente, vendedor;

            if ( listaCabec ) {
                nroDocumento = ((Cabeces) actual.get(i)).getId().getNrodocumento();
                cliente = ((Cabeces) actual.get(i)).getClientes().getNombre();

                String tipox = ((Cabeces) actual.get(i)).getId().getTipodoc();
                                
                if ( "04".equals(tipox) ){
                    System.out.println("Si entro a ND : " + i);                    
                    vendedor = "EDUARDO DIAZ MORENO";
                } else {
                System.out.println("Valor i " + i);                    
                vendedor = ((Cabeces) actual.get(i)).getVendedores().getNombre();                
                }
                                
                
            } else {
                nroDocumento = ((Cabecsalvar) actual.get(i)).getCabeces().getId().getNrodocumento();
                cliente = ((Cabecsalvar) actual.get(i)).getClientes().getNombre();
                vendedor = ((Cabecsalvar) actual.get(i)).getVendedores().getNombre();                
            }

            boolean doc, cli, ven;
            doc = cli = ven = false;

            if ( nroDocumento.indexOf(bsq_nroDoc) != -1 ) {
                doc = true;
            }
            if ( cliente.indexOf(bsq_cliente) != -1 ) {
                cli = true;
            }

            if ( vendedor.indexOf(bsq_vendedor) != -1 ) {
                ven = true;
            }
            
            if ( cli && doc && ven) {
                resul_busqueda.add(actual.get(i));
            }
        }
        BorrarTabla();
        listaTabla(resul_busqueda);
    }

    private void FiltrarPor(boolean b_factura, boolean b_boleta, boolean b_notaCredito, boolean b_notaDebito,
                            boolean b_cliente) {

        System.out.println("voy a seleccionar vendedor");
        
        if ( !b_boleta && !b_cliente && !b_factura && !b_notaCredito && !b_notaDebito) {
            return;
        }

        if ( b_cliente ) {
            limpiarFiltro();
            
        } else {
            iu.tx_cliente.setText("");
            iu.tx_nroDoc.setText("");
            resul_busqueda.clear();
            listafiltrada.clear();
            
            for ( int i = 0; i < ListaCab.size(); i++ ) {
                if ( b_factura && ((Cabeces) ListaCab.get(i)).getId().getTipodoc().equals("01") ) {
                    listafiltrada.add(ListaCab.get(i));
                }
                if ( b_boleta && ((Cabeces) ListaCab.get(i)).getId().getTipodoc().equals("02") ) {
                    listafiltrada.add(ListaCab.get(i));
                }
                if ( b_notaCredito && ((Cabeces) ListaCab.get(i)).getId().getTipodoc().equals("03") ) {
                    listafiltrada.add(ListaCab.get(i));
                }
                if ( b_notaDebito && ((Cabeces) ListaCab.get(i)).getId().getTipodoc().equals("04") ) {
                    listafiltrada.add(ListaCab.get(i));
                }
                
//                ((Cabeces) ListaCab.get(i)).getId().
                
            }
            BorrarTabla();
            listaTabla(listafiltrada);
        }
    }

    public void limpiarFiltro() {
        BorrarTabla();
        resul_busqueda.clear();
        listafiltrada.clear();
        listaTabla(ListaCab);
    }

    private String TipoDoc(String tipo) {
        switch ( tipo ) {
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

    private Object set(Double costototal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}