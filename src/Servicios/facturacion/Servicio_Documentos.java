/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.facturacion;

import Entidades.Cabeces;
import Entidades.Cabecproformas;
import Entidades.Cabecsalvar;
import Entidades.Cabecweb;
import Entidades.Clientes;
import Entidades.Detalleproformas;
import Entidades.Detallees;
import Entidades.Operaciones;
import Entidades.Pagos;
import Entidades.Sistema;
import Entidades.Sucursales;
import Entidades.Tipocambio;
import Mantenimiento.CabecproformasDAO;
import Mantenimiento.ClienteDAO;
import Mantenimiento.ControlDAO;
import Mantenimiento.Facturacion.DocumentosDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Mantenimiento.OperacionesDAO;
import Mantenimiento.SucursalesDAO;
import Mantenimiento.TipoCambioDAO;
import Mantenimiento.VendedorDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Servicio_Documentos {

    DocumentosDAO DocDAO;
    SistemaDAO sisDAO;
    
    CabecproformasDAO CabecprofDAO;

    public Servicio_Documentos() {
        DocDAO = new DocumentosDAO();
        sisDAO = new SistemaDAO();
        
        CabecprofDAO = new CabecproformasDAO();
    }

    public List getListVend() {
        return new VendedorDAO().Obtener_Lista_Objetos();
    }

    public List getListCli() {
        return new ClienteDAO().Obtener_Lista_Objetos_OrderNombre();
    }
    
    public List getSoloClientes() {
        return new ClienteDAO().ObtenerSoloClientes();
    }

    public Sucursales getSucursal(int idSucursal, int idCliente) {
        return new SucursalesDAO().getSucursal(idSucursal, idCliente);
    }

    public int getMaxLineas(int tipo) {
        switch (tipo) {
            case 1:
                return new ControlDAO().Obtener_Objeto(1).getNrolineafac();
            case 2:
                return new ControlDAO().Obtener_Objeto(1).getNrolineabol();
            case 3:
                return new ControlDAO().Obtener_Objeto(1).getNrolineanc();
            case 4:
                return new ControlDAO().Obtener_Objeto(1).getNrolineand();
            default:
                return 0;
        }
    }

    public int getIGV() {
        return new ControlDAO().Obtener_Objeto(1).getImpuestoigv();
    }

    public Tipocambio getTipoCambio(Date f) {
        return new TipoCambioDAO().Obtener_Objeto(f);
    }

    public Clientes getClientexNombre(String nombre) {
        return new ClienteDAO().obtenerClientePorNombre(nombre);
    }

    public boolean guardarFactura(Cabeces cab, ArrayList<Detallees> dt, Pagos pago) {
        return DocDAO.Guardar_ActualizarDoc(cab, dt, pago, null);
    }
    
    public boolean actualizarFactura(Cabeces cab, ArrayList<Detallees> dt, Pagos pago) {
        return DocDAO.ActualizarDoc(cab, dt, pago);
    }
    
    public boolean guardarGRXConsignacion(Cabeces cab, ArrayList<Detallees> lstDet) {
        return DocDAO.GenerarGRXConsignacion(cab, lstDet);
    }
    
    public boolean eliminarFacturaFicticia(Cabeces cabFictGR, List<Detallees> detFictGR) {
        return DocDAO.EliminarFacturaFicticia(cabFictGR, detFictGR);
    }

//    public boolean guardar_actualizar_FacBol_Proforma(Cabeces cab, ArrayList<Detallees> dt, Pagos pago, Object cab_act) {
    public boolean guardar_actualizar_FacBol_Proforma(Cabeces cab, ArrayList<Detallees> dt, Pagos pago, List<Cabecproformas> cab_act) {
        return DocDAO.Guardar_ActualizarDoc(cab, dt, pago, cab_act);
    }

    public boolean GuardarProforma(Cabecproformas cp, ArrayList<Detalleproformas> dt) {
        return DocDAO.GuardarProforma(cp, dt);
    }

    public boolean GuardarSK(Cabecsalvar cabsal, ArrayList<Detallees> dt, Pagos pago) {//////Revisar
        System.out.println("1.codigo salida:" + cabsal.getCodigosalida());
        System.out.println("2.tipotrask:" + cabsal.getTipotrask());
        System.out.println("3.tipodocsk:" + cabsal.getTipodocsk());
        System.out.println("4.nroseriesk:" + cabsal.getNroseriesk());
        System.out.println("5.moneda:" + cabsal.getMoneda());
        System.out.println("6.idvendedor:" + cabsal.getVendedores().getIdvendedor());
        System.out.println("7.idcliente:" + cabsal.getClientes().getIdcliente());
        
        if ( cabsal.getSucursales() != null ) {
            System.out.println("8.idsucursal:" + cabsal.getSucursales().getIdsucursal());
        } else {
            System.out.println("8.idsucursal(nula):" + cabsal.getSucursales());
        }
        System.out.println("9.tipotra:" + cabsal.getCabeces().getId().getTipotra());
        System.out.println("10.tipodoc:" + cabsal.getCabeces().getId().getTipodoc());
        System.out.println("11.nrorserie:" + cabsal.getCabeces().getId().getNrorserie());
        System.out.println("12.nrodocumento:" + cabsal.getCabeces().getId().getNrodocumento());
        
        if ( cabsal.getUsuarios() != null ) {
            System.out.println("13.idusuario:" + cabsal.getUsuarios().getIdusuario());
        } else {
            System.out.println("13.idusuario(nulo):" + cabsal.getUsuarios());
        }
        System.out.println("14.fecha:" + cabsal.getFecha());
        System.out.println("15.valorventa:" + cabsal.getValorventa());
        System.out.println("16.igv:" + cabsal.getIgv());
        System.out.println("17.total:" + cabsal.getTotal());
        System.out.println("18.observacion:" + cabsal.getObservacion());
        
        if ( !dt.isEmpty() ) {
            System.out.println("N° elementos del detalle de SK:" + dt.size());
        }
        
        System.out.println("pago:" + pago);
        if ( pago != null ) {
            System.out.println("pago efectuado(importe):" + pago.getImporte());
        }
//        return DocDAO.Guardar_ActualizarDoc(cabsal, dt, null, null);
        return DocDAO.Guardar_SK(cabsal, dt, pago);
    }

    public List getDetalleProf(Cabecproformas c) {
        return DocDAO.getxCabec(c);
    }

    public List getxCliente(String cliente) {
        return DocDAO.getProformaxCliente(cliente);
    }
    
    public List filtrarCabecproformas(int idCliente) {
        return CabecprofDAO.filtrarCabecproformas(idCliente);
    }
    
    public List filtrarCabecproformas(String estadoProforma) {
        return CabecprofDAO.filtrarCabecproformas(estadoProforma);
    }
    
    public List filtrarCabecproformas(Date desde, Date hasta) {
        return CabecprofDAO.filtrarCabecproformas(desde, hasta);
    }
    
    public List filtrarCabecproformas(int idCliente, Date desde, Date hasta) {
        return CabecprofDAO.filtrarCabecproformas(idCliente, desde, hasta);
    }
    
    public List filtrarCabecproformas(String estadoProforma, Date desde, Date hasta) {
        return CabecprofDAO.filtrarCabecproformas(estadoProforma, desde, hasta);
    }

    public Cabecproformas GetxId(int id) {
        return DocDAO.Obtener_Objeto(id);
    }

    public Cabecproformas GetxCodigoCabProforma(String codCabProforma) {
        return DocDAO.getCabecProforma(codCabProforma);
    }
    
    public Cabecproformas GetxIdCabProforma(int idProforma) {
        return DocDAO.getIdCabecProforma(idProforma);
    }

    public boolean GuardarLetras_actualizarDoc(Cabeces cabFactura, ArrayList<Detallees> det, ArrayList<Cabeces> cabLetra, Pagos pago, Object cab_act) {
        return DocDAO.GuardarLetras(cabFactura, det, cabLetra, pago, cab_act);
    }

    public Sistema getSis(String nombre) {
//        System.out.println(nombre);
        return sisDAO.obtener_por_nombre(nombre);
    }

    public boolean actualizarSis(Sistema s) {
        return sisDAO.Modificar_Objeto(s);
    }
    
    public boolean actualizarSistema(Sistema s) {
        return sisDAO.Modificar_Objeto_Merge(s);
    }

    public List getDetalleWeb(Cabecweb cw) {
        return DocDAO.getxCabW(cw);
    }

    public List<Detallees> getDetalleGuia(Cabeces guia) {
        return DocDAO.getxCabGuia(guia);
    }
    
    public List<Detallees> getDetalleNroGuia(int nroGuia) {
        return DocDAO.getxCabGuia(nroGuia);
    }

    public List getListarPorClientesNoProces(int idCliente) {
        return DocDAO.listarPorClienteNoProces(idCliente);
    }

    public Operaciones getOperacion(String nombre) {
        return new OperacionesDAO().getOperacion(nombre);
    }

    public Cabecsalvar getCabecSalvar(int id) {
        return DocDAO.getCabecSalvar(id);
    }

    public Cabecsalvar getCabecSalvarCodSal(int id) {
        return DocDAO.getCabecSalvarCodSal(id);
    }
    
    public Cabecweb getCabecWeb(int id) {
        return DocDAO.getCabecWeb(id);
    }

    public boolean ActualizarCabecSalvar(Cabecsalvar c, Cabeces cab, List<Detallees> detalles) {
        return DocDAO.Actualizar_CabecSalvar(c, cab, detalles);
    }

    public List GetSucursales(int cliente_id) {
        return new SucursalesDAO().getSucursal_Cliente(cliente_id);
    }

    public boolean GuardarLetras_Canje(ArrayList<Cabeces> letras, ArrayList<Pagos> pagos) {
        return DocDAO.GuardarLetras_Canje(letras, pagos);
    }
    
    public boolean GuardarLetras_Canje(ArrayList<Cabeces> letras, ArrayList<Pagos> pagos, List<Cabeces> lstCabeces) {
        return DocDAO.GuardarLetras_Canje(letras, pagos, lstCabeces);
    }

    public boolean GuardarLetras_Varias(ArrayList<Cabeces> letras) {
        return DocDAO.GuardarLetras_Varias(letras);

    }

    public boolean Actualizar_SK(Cabecsalvar cs) {
        return DocDAO.ActualizarSK(null);
    }
    
    public boolean actualizarCabecProforma(Cabecproformas cabecProforma) {
        return CabecprofDAO.actualizarCabecProforma(cabecProforma);
    }
    
    public List getxNumeroFactura(String numeroFactura) {
        return DocDAO.getProformaxNumFactura(numeroFactura);
    }
    
    public List getVendedoresOrdenados() {
        return new VendedorDAO().Obtener_Lista_Objetos_OrderNombre();
    }
}
