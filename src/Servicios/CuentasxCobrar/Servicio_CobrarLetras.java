package Servicios.CuentasxCobrar;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Pagos;
import Mantenimiento.CuentasxCobrar.LetrasDAO;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Keily Mendiolaza
 */
public class Servicio_CobrarLetras {

    LetrasDAO dao;
    private Object date;

    public Servicio_CobrarLetras() {
        dao = new LetrasDAO();
    }

    public List Listar_Cuentas_x_Cobrar_Letras() {
        return dao.Listar_Cuentas_x_Cobrar_Letras();
    }

    public List Listar_Cuentas_xCobrar_Cliente(String cliente) {
        return dao.Listar_Cuentas_xCobrar_Cliente(cliente);
    }

    public List Listar_Cuentas_xCobrar_RUC(String ruc) {
        return dao.Listar_Cuentas_xCobrar_RUC(ruc);
    }

    public List Listar_Cuentas_xCobrar_factura(String factura) {
        return dao.Listar_Cuentas_xCobrar_factura(factura);
    }

    public List Listar_Cuentas_xCobrar_nletra(String nletra) {
        return dao.Listar_Cuentas_xCobrar_nletra(nletra);
    }

    public List Listar_Cuentas_xCobrar_fechaVencimiento(Date fechavenc) {

//        System.out.println("Fecha Vencimento : " + fechavenc);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String fecha222 = sdf.format(fechavenc);
//        System.out.println("Fecha 222 : " + fecha222);
//        return dao.Listar_Cuentas_xCobrar_fechaVencimiento(fecha222);        
        return dao.Listar_Cuentas_xCobrar_fechaVencimiento(fechavenc);
    }

    public List Listar_Cuentas_xCobrar_monto(String monto) {
        return dao.Listar_Cuentas_xCobrar_monto(monto);
    }

    public List<Cabeces> Listar_Cuentas_General_Letras() {
        return dao.Listar_Cuentas_General_Letras();
    }

    public List Listar_Cuentas_General_Cliente(String cliente) {
        return dao.Listar_Cuentas_General_Cliente(cliente);
    }

    public List Listar_Cuentas_General_RUC(String ruc) {
        return dao.Listar_Cuentas_General_RUC(ruc);
    }

    public List Listar_Cuentas_General_factura(String factura) {
        return dao.Listar_Cuentas_General_factura(factura);
    }

    public List Listar_Cuentas_General_nletra(String nletra) {
        return dao.Listar_Cuentas_General_nletra(nletra);
    }

    public List Listar_Cuentas_General_fechaVencimiento(Date fechavenc) {

        return dao.Listar_Cuentas_General_fechaVencimiento(fechavenc);
    }

    public List Listar_Cuentas_General_monto(String monto) {
        return dao.Listar_Cuentas_General_monto(monto);
    }

    public Cabeces Buscar_Cuenta_Letra(String numero) {
        return dao.Buscar_Cuenta_Letra(numero);
    }

    public List<Pagos> listarPagosxLetras(CabecesId cabecesID) {
        return dao.listarPagosxLetras(cabecesID);
    }

    public Cabeces obtenerCabecera_Id(CabecesId id) {
        return dao.obtenerCabecera_Id(id);
    }
    
    public boolean AnularLetra(Cabeces c) {
        return dao.AnularLetra(c);
    }

    public List Listar_Cuentas_General_fechaVencimiento(String fecha222) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List Listar_Cuentas_xCobrar_fechaVencimiento(String fecha222) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}