package Servicios;

import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Detallees;
import static Entidades.Otros.Constante.NOTA_ING_AJUSTES;
import static Entidades.Otros.Constante.NOTA_ING_COMPRA_LOCAL;
import Entidades.Otros.Monetario;
import Mantenimiento.DetallesDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_DetalleEs {
    
    JTable tblDetalleEs;
    DefaultTableModel modelo;
    DetallesDAO detalleEsDAO;
    List<Detallees> listaDetalleEs;
    
    public Servicio_DetalleEs(JTable tblDetalleEs) {
        detalleEsDAO = new DetallesDAO();
        this.tblDetalleEs = tblDetalleEs;
    }

    public Servicio_DetalleEs() {
        detalleEsDAO = new DetallesDAO();
    }
    
    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }
    
    public int Listar_DetalleEs(DefaultTableModel modelo, CabecesId cabecesId) {
        this.modelo = modelo;
        List<Detallees> listaDetalleEs = detalleEsDAO.getListaDetalleEs(cabecesId);

        for ( int i = 0; i < listaDetalleEs.size(); i++ ) {
            int a = listaDetalleEs.get(i).getRepuestos().getEquipos().getIdequipo();
            String b = "" + listaDetalleEs.get(i).getRepuestos().getCodrepuesto();
            String c = listaDetalleEs.get(i).getRepuestos().getDescripcion();
            int d = listaDetalleEs.get(i).getCantentregada();
            Double e = listaDetalleEs.get(i).getDescuento1();
            Double f = listaDetalleEs.get(i).getDescuento2();
            
            Double f1 = listaDetalleEs.get(i).getDescuento3();
            Double f2 = listaDetalleEs.get(i).getDescuento4();
            Double g = ( 1 - e/100 ) * ( 1 - f/100 ) * ( 1 - f1/100 ) * ( 1 - f2/100 ) * listaDetalleEs.get(i).getPreciolista() * d;
            String valorTotal = Monetario.formatearMonetario(g, 2);
            
            Object[] lineas = {a, b, c, d, e, f, valorTotal};
            modelo.addRow(lineas);
        }
        return listaDetalleEs.size();
    }
    
    public ArrayList<Detallees> Listar_DetalleEs_PorCabec(CabecesId cabecesId) {        
        List<Detallees> listaDetalleEs = detalleEsDAO.getListaDetalleEs(cabecesId);
        return (ArrayList<Detallees>) listaDetalleEs;
    }
    
    public int Listar_DetalleEs_Anulacion(DefaultTableModel modelo, CabecesId cabecesId) {
        this.modelo = modelo;
        listaDetalleEs = detalleEsDAO.getListaDetalleEs(cabecesId);       

        for ( int i = 0; i < listaDetalleEs.size(); i++ ) {
            int l = listaDetalleEs.get(i).getRepuestos().getId().getIdlinea();
            String cr = listaDetalleEs.get(i).getRepuestos().getCodrepuesto();
            
            // Inicio de  Agregado
            String d = listaDetalleEs.get(i).getRepuestos().getDescripcion();
            // Fin de Agregado
            
            int c = listaDetalleEs.get(i).getCantentregada();
            Double pl = listaDetalleEs.get(i).getPreciolista();
            
            
            Double e = listaDetalleEs.get(i).getDescuento1();
            Double f = listaDetalleEs.get(i).getDescuento2();
            Double f1 = listaDetalleEs.get(i).getDescuento3();
            Double f2 = listaDetalleEs.get(i).getDescuento4();
            Double t = ( 1 - e/100 ) * ( 1 - f/100 ) * ( 1 - f1/100 ) * ( 1 - f2/100 ) * listaDetalleEs.get(i).getPreciolista() * c;
//            Double t = listaDetalleEs.get(i).getCantentregada() * listaDetalleEs.get(i).getPreciolista();
            t = new Util().Redondear2Decimales(t);
            
            String m = listaDetalleEs.get(i).getRepuestos().getDescrmodelo();
            
//            Object[] lineas = {a,b,d,g};
//            Object[] lineas = {l, cr, d, c, pl, t, m};
            
            String plString = formatearMonetario(String.valueOf(pl), 2);
            String tString = formatearMonetario(String.valueOf(t), 2);
            Object[] lineas = {l, cr, d, c, plString, tString, m};
            modelo.addRow(lineas);
        }
        return listaDetalleEs.size();
    }
    
    public int Listar_DetalleEsReimpres(DefaultTableModel modelo, CabecesId cabecesId) {
        this.modelo = modelo;
        listaDetalleEs = detalleEsDAO.getListaDetalleEs(cabecesId);       

        for ( int i = 0; i < listaDetalleEs.size(); i++ ) {
            int a = listaDetalleEs.get(i).getRepuestos().getId().getIdlinea();
            String b = "" + listaDetalleEs.get(i).getRepuestos().getCodrepuesto();
            String c = listaDetalleEs.get(i).getRepuestos().getDescripcion();
            int d = listaDetalleEs.get(i).getCantentregada();
            
            Double h = listaDetalleEs.get(i).getPreciolista();
            String h1 = formatearMonetario(String.valueOf(h), 2);

            Double e = listaDetalleEs.get(i).getDescuento1();
            String e1 = formatearMonetario(String.valueOf(e), 2);
            
            Double f = listaDetalleEs.get(i).getDescuento2();
            String fa = formatearMonetario(String.valueOf(f), 2);
            
            Double f1 = listaDetalleEs.get(i).getDescuento3();
            String f1a = formatearMonetario(String.valueOf(f1), 2);
            
            Double f2 = listaDetalleEs.get(i).getDescuento4();
            String f2a = formatearMonetario(String.valueOf(f2), 2);
            
            Double g = ( 1 - e/100 ) * ( 1 - f/100 ) * ( 1 - f1/100 ) * ( 1 - f2/100 ) * listaDetalleEs.get(i).getPreciolista() * d;
//            g = Redondear(g);
//            String g1 = formatearMonetario(String.valueOf(g), 2);
            String g1 = Monetario.formatearMonetario(g, 2);
            
            Object[] lineas = {a,b,c,d,
                               h1,
                               e1,fa,f1a,f2a,g1};
            modelo.addRow(lineas);
        }
        return listaDetalleEs.size();
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
    
    public int Listar_Detalle_Ingreso(int nro, DefaultTableModel modelo, String tipo){
        this.modelo = modelo;
        List<Detallees> listaDetalleEs = null;
//        listaDetalleEs = detalleEsDAO.getListaDetalleEs_Ingreso(nro);
        
        switch ( tipo ) {
            case NOTA_ING_COMPRA_LOCAL:
                listaDetalleEs = detalleEsDAO.getListaDetalleEs_IngCompraLocal(nro);
                break;
            case NOTA_ING_AJUSTES:
                listaDetalleEs = detalleEsDAO.getListaDetalleEs_IngAjuste(nro);
                break;
        }
        
        for ( int i = 0; i < listaDetalleEs.size(); i++ ) {
//            System.out.println("idre: "+listaDetalleEs.get(i).getRepuestos().getId().getIdrepuesto());
            int linea = listaDetalleEs.get(i).getRepuestos().getId().getIdlinea();
            String codRep = listaDetalleEs.get(i).getRepuestos().getCodrepuesto();
            int cantEnt = listaDetalleEs.get(i).getCantentregada();
            String descripcion = listaDetalleEs.get(i).getRepuestos().getDescripcion();
            
            Double dscto1 = listaDetalleEs.get(i).getDescuento1();
            Double dscto2 = listaDetalleEs.get(i).getDescuento2();
            Double dscto3 = listaDetalleEs.get(i).getDescuento3();
            Double dscto4 = listaDetalleEs.get(i).getDescuento4();
            Double g = ( 1 - dscto1/100 ) * ( 1 - dscto2/100 ) * ( 1 - dscto3/100 ) * ( 1 - dscto4/100 ) 
                        * listaDetalleEs.get(i).getPreciolista() * cantEnt;
//            Double g= listaDetalleEs.get(i).getPreciolista()*d;
//            g = Redondear(g);
            String tot = Monetario.formatearMonetario(g, 2);
            String codSec = listaDetalleEs.get(i).getCodsecundario();
            
//            String cs = listaDetalleEs.get(i).getCodsecundario();
//            String precioLista = String.valueOf(listaDetalleEs.get(i).getPreciolista());
            String precioLista = Monetario.formatearMonetario(Double.parseDouble(tot) / cantEnt, 2);
//            System.out.println("precioLista:::" + precioLista);
            String mod = listaDetalleEs.get(i).getRepuestos().getDescrmodelo();
//            cs = "null".equals(cs) ? "" : cs;
            
//            Object[] lineas = {a, b, d, c, g, cs, pl};
//            Object[] lineas = {l, cr, d, c, pl, t, m};
            Object[] lineas = {linea, codRep, cantEnt, descripcion, tot, codSec, precioLista, mod};
            modelo.addRow(lineas);
        }
        return listaDetalleEs.size();
    }
    
    public int Listar_Detalle_Salida(int nro, DefaultTableModel modelo){
        this.modelo = modelo;
        List<Detallees> listaDetalleEs = detalleEsDAO.getListaDetalleEs_Salida(nro);
        
        for ( int i = 0; i < listaDetalleEs.size(); i++ ) {
            int l = listaDetalleEs.get(i).getRepuestos().getId().getIdlinea();
            String cr = listaDetalleEs.get(i).getRepuestos().getCodrepuesto();
            int c = listaDetalleEs.get(i).getCantentregada();
            String d = listaDetalleEs.get(i).getRepuestos().getDescripcion();
            
            Double e = listaDetalleEs.get(i).getDescuento1();
            Double f = listaDetalleEs.get(i).getDescuento2();
            Double f1 = listaDetalleEs.get(i).getDescuento3();
            Double f2 = listaDetalleEs.get(i).getDescuento4();
            Double g = ( 1 - e/100 ) * ( 1 - f/100 ) * ( 1 - f1/100 ) * ( 1 - f2/100 ) * listaDetalleEs.get(i).getPreciolista() * c;
//            Double g= listaDetalleEs.get(i).getPreciolista()*d;
//            g = Redondear(g);
            String t = Monetario.formatearMonetario(g, 2);
            
//            String cs = listaDetalleEs.get(i).getCodsecundario();
//            System.out.println("cs:" + cs);
//            System.out.println("igual1?" + (cs == null));
//            System.out.println("igual2?" + "null".equals(cs));
//            cs = "null".equals(cs) ? "" : cs;
//            cs = cs == null ? "" : cs;
            String pl = String.valueOf(listaDetalleEs.get(i).getPreciolista());
            String m = listaDetalleEs.get(i).getRepuestos().getAplicacion();
            
//            Object[] lineas = {a, b, d, c, g, cs, pl
            Object[] lineas = {l, cr, d, c, pl, t, m};
            modelo.addRow(lineas);
        }
        return listaDetalleEs.size();
    }

    public List<Detallees> getListaDetalleEs() {
        return listaDetalleEs;
    }
    
    public ArrayList<Detallees> listarPorSK(Cabecsalvar cab) {
        List<Detallees> listaDetalleEs = detalleEsDAO.listarporSK(cab);
        return (ArrayList<Detallees>) listaDetalleEs;
    }   
}