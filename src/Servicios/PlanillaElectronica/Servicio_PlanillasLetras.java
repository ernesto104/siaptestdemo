package Servicios.PlanillaElectronica;

import Servicios.EmisionPlanillas.*;
import Entidades.Cabeces;
import static Entidades.Otros.Constante.DOLAR_US;
import static Entidades.Otros.Constante.SOL;
import Presentacion.PlanillaLetras.IU_PlanillaLetras;
import Servicios.Servicio_Cabeces;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import Servicios.Util;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Ledis Rivera Changra
 */
public class Servicio_PlanillasLetras {

    private IU_PlanillaLetras iuPL;
    private Servicio_Cabeces servicio;
    private DefaultTableModel dftm_Planillas;
    public JTable tab_Planillas;
    Date inicio, fin;
    
    double total_Dolares;
    double total_Soles;
    boolean cabec;
    Util util;
    
    private ArrayList resul_busqueda, listafiltrada;
    private List<Cabeces> lstLetras;
    
    public Servicio_PlanillasLetras(IU_PlanillaLetras iu, Date inicio, Date fin) {
//        System.out.println("Servicio_PlanillasLetras...");
        dftm_Planillas = (DefaultTableModel) iu.tab_Planillas.getModel();
        resul_busqueda = new ArrayList();
        this.iuPL = iu;
        tab_Planillas = iu.tab_Planillas;
        this.inicio = inicio;
        this.fin = fin;
        util = new Util();
        servicio = new Servicio_Cabeces();
        
        lstLetras = servicio.obtenerPlanillaLetras(inicio, fin);
        listarLetras(lstLetras);
    }

    public List<Cabeces> getLstLetras() {
        return lstLetras;
    }

    public void setLstLetras(List<Cabeces> lstLetras) {
        this.lstLetras = lstLetras;
    }
    
    public void listarLetras(List<Cabeces> lstLetras) {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
        Iterator it = lstLetras.iterator();

        if ( !lstLetras.isEmpty() ) {
            iniciarTotales();

            while ( it.hasNext() ) {
                Cabeces c = (Cabeces) it.next();
                Object[] row = new Object[16];
                row[0] = i++;
                row[1] = getTipoDoc(c.getId().getTipodoc());
                row[2] = c.getId().getNrorserie();
                row[3] = c.getId().getNrodocumento();
                row[4] = c.getClientes().getNombre();
                row[5] = sd.format(c.getTipocambio().getFecha()); // Fecha de EMISIÓN
                
//                System.out.println("c.getMoneda():" + c.getMoneda());
                if ( c.getMoneda().equals("1") ) {
                    row[6] = SOL;
                    
                } else if ( c.getMoneda().equals("2") ) {
                    row[6] = DOLAR_US;
                }
//                System.out.println("row[6]:" + row[6]);
                row[7] = c.getTotal(); // IMPORTE
                SumarSegunTipo_Moneda(c.getTotal(), c.getMoneda()); // IMPORTE
                row[8] = c.getFechavencimiento();   // Fecha que VENCE
                row[9] = false;                     // Inicializa selección de IMPRIMIR
                row[10] = c.getLetraPlanilla();      // EN PLANILLA
                row[11] = c.getFechaLetPla();       // FECHA DE PLANILLA
                row[12] = c.getClientes().getRuc(); // RUC del cliente
                row[13] = c.getClientes().getTelefonofijo(); // TELEFONO FIJO del cliente
                row[14] = ""; // N° CTA. CTE. del Cliente
                row[15] = c.getClientes().getPlaza(); // PLAZA del cliente
                dftm_Planillas.addRow(row);
            }
            Redondear();
        }
    }
    
    private String getTipoDoc(String tipoDoc) {
        return ( "06".equals(tipoDoc) ? "L" : "");
    }
    
    private void iniciarTotales() {
        total_Dolares = 0;
        total_Soles = 0;
        iuPL.txt_D_Total.setText(String.valueOf(total_Dolares));
        iuPL.txt_S_Total.setText(String.valueOf(total_Soles));
    }
    
    private void SumarSegunTipo_Moneda(double Monto, String Moneda) {
        if ( Moneda.equals("1") ) {
            total_Soles += Monto;

        } else {
            total_Dolares += Monto;
        }
    }
    
    private void Redondear() {
        total_Dolares = util.Redondear2Decimales(total_Dolares);
        total_Soles = util.Redondear2Decimales(total_Soles);
    }
    
    public void seteaTotales() {
        BigDecimal t_Soles = new BigDecimal(String.valueOf(total_Soles));
        iuPL.txt_S_Total.setText(util.DosDecimales(t_Soles.doubleValue()));

        BigDecimal t_Dolares = new BigDecimal(String.valueOf(total_Dolares));
        iuPL.txt_D_Total.setText(util.DosDecimales(t_Dolares.doubleValue()));
    }
    
    private boolean filtrosVacios(String bsq_nroDoc, String bsq_cliente) {
        boolean filtrosVacios = "".equals(bsq_nroDoc) && "".equals(bsq_cliente);
//        System.out.println("filtros vacios:" + filtrosVacios);
        return filtrosVacios;
    }
    
    public void buscarPor() {
        String bsq_nroDoc = iuPL.tx_nroDoc.getText();
        String bsq_cliente = String.valueOf(iuPL.cbClientes.getSelectedItem());
        resul_busqueda.clear();
        
        if ( filtrosVacios(bsq_nroDoc, bsq_cliente) ) {
            for ( Cabeces c : lstLetras ) {
                resul_busqueda.add(c);
            }
        } else {
            for ( int i = 0; i < lstLetras.size(); i++ ) {
                String nroDocumento, cliente;
                nroDocumento = ((Cabeces) lstLetras.get(i)).getId().getNrodocumento();
                cliente = ((Cabeces) lstLetras.get(i)).getClientes().getNombre();
                boolean doc, cli;
                doc = cli = false;
//                System.out.println("nroDocumento:" + nroDocumento);
//                System.out.println("bsq_nroDoc:" + bsq_nroDoc);
                
                if ( "".equals(bsq_nroDoc) && "".equals(bsq_cliente) ) {
                    resul_busqueda.add(lstLetras.get(i));

                } else {
                    if ( nroDocumento.indexOf(bsq_nroDoc) != -1 ) {
                        doc = true;
                    }
//                    System.out.println("cliente:" + cliente);
//                    System.out.println("bsq_cliente:" + bsq_cliente);
                    if ( bsq_cliente.equals(cliente) ) {
                        cli = true;
                    }
//                    System.out.println("cli:" + cli);
//                    System.out.println("dpc:" + doc);
                    if ( cli && doc ) {
                        resul_busqueda.add(lstLetras.get(i));
                    }
                }
            }
        }
//        System.out.println("resul_busqueda:" + resul_busqueda);
//        if ( !resul_busqueda.isEmpty() ) {
//            System.out.println("tamaño de resul_busqueda:" + resul_busqueda.size());
//            for( Object obj : resul_busqueda ) {
//                Cabeces c = (Cabeces) obj;
//                System.out.println("select:" + c.getId().getNrodocumento());
//            }
//        }
        BorrarTabla();
        listaTabla(resul_busqueda);
    }
    
    private void BorrarTabla() {
        int numRows = dftm_Planillas.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            dftm_Planillas.removeRow(0);
        }
    }
    
    public void listaTabla(List listaC) {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        int i = 1;
//        if ( !listaC.isEmpty() ) {
//            System.out.println("tamaño de lista:" + listaC.size());
//        }
        Iterator it = listaC.iterator();
        
        if ( !listaC.isEmpty() ) {
            IniciarVariables();
            Object o = listaC.get(0);
            
            while ( it.hasNext() ) {
                Cabeces c = (Cabeces) it.next();
                Object[] row = new Object[15];
                row[0] = i++;
//                row[1] = TipoDoc(c.getId().getTipodoc());
                row[1] = c.getId().getTipodoc();
                row[2] = c.getId().getNrorserie();
                row[3] = c.getId().getNrodocumento();
//                row[4] = sd.format(c.getTipocambio().getFecha());
//                row[4] = c.getClientes().getNombre();

                String estado = c.getEstado();

                if ( "ANULADO".equals(estado) ) {
                    row[4] = "(ANULADO) - " + c.getClientes().getNombre();
                    row[7] = 0.00;

                } else {
                    row[4] = c.getClientes().getNombre();
                    row[7] = c.getTotal();
                }
//                    row[5] = "ANULADO".equals(estado) ? "(ANULADO) " + c.getClientes().getNombre() : c.getClientes().getNombre();
//                    row[5] = c.getClientes() == null ? "ANULADO" : c.getClientes().getNombre();
                if ( c.getMoneda() == "1" ) {
                    row[6] = "S/.";

                } else {
//                    if ( "ANULADO".equals(estado) ) {
//                        row[6] = c.getMoneda().equals("1") ? "S/. 0.00" : "$ 0.00";
//                        SumarSegunTipo_Moneda(0.00, c.getId().getTipodoc(), c.getMoneda());
//
//                    } else {
//                        row[6] = c.getMoneda().equals("1") ? "S/. " + c.getTotal() : "$ " + c.getTotal();
//                        SumarSegunTipo_Moneda(c.getTotal(), c.getId().getTipodoc(), c.getMoneda());
//                    }
                    row[6] = "US$";
                }
                System.out.println("listaTabla(row[6]):" + row[6]);
//                row[7] = c.getLetraFacbol() == null ? (c.getNcFacbol() == null ? "" : c.getNcFacbol()) : c.getLetraFacbol();
                row[7] = c.getTotal();
                row[8] = c.getFechavencimiento();
                row[9] = false;
                row[10] = c.getLetraPlanilla();
                row[11] = c.getFechaLetPla();
                row[12] = c.getClientes().getRuc(); // RUC del cliente
                row[13] = c.getClientes().getTelefonofijo(); // TELEFONO FIJO del cliente
                row[14] = ""; // N° CTA. CTE. del Cliente
                dftm_Planillas.addRow(row);
            }
            Redondear();
            seteaTotales();
        }
    }
    
    private void IniciarVariables() {
        total_Dolares = 0;
        total_Soles = 0;
    }
    
    private void SumarSegunTipo_Moneda(double Monto, String tipoDoc, String Moneda) {
        if ( Moneda.equals("1") ) {
            if ( tipoDoc.equals("06") ) {
                total_Soles += Monto;
            }
            
        } else {
            if ( tipoDoc.equals("06") ) {
                total_Dolares += Monto;
            }
        }
    }
}