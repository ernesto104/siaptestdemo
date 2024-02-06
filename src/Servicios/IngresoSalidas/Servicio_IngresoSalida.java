/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.IngresoSalidas;

import Entidades.Cabeces;
import Entidades.Clientes;
import Entidades.Detallees;
import Entidades.Operaciones;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Mantenimiento.ClienteDAO;
import Mantenimiento.ControlDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Mantenimiento.IngresoSalidas.IngresoSalidaDAO;
import Mantenimiento.OperacionesDAO;
import Mantenimiento.TipoCambioDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CRS
 */
public class Servicio_IngresoSalida {
    ClienteDAO cl ;
    IngresoSalidaDAO dao ;
    OperacionesDAO opDAO;
    SistemaDAO sisDAO;
    
    public Servicio_IngresoSalida(){
        cl = new ClienteDAO();
        dao = new IngresoSalidaDAO();
        sisDAO = new SistemaDAO();
    }
    public List ObtenerProveedor(){
        return cl.obtenerProveedores();
    }
    public Clientes GetCliente(int id){
        return cl.Obtener_Objeto(id);
    }
    public int getMaxLineas(){
        return new ControlDAO().Obtener_Objeto(1).getNrolineafac();
    }
    
    public Tipocambio getTipoCambio(Date f){
        return new TipoCambioDAO().Obtener_Objeto(f);
    }
    public boolean guardarCompraLocal(ArrayList<Detallees> detalles, Cabeces cab) {
        return dao.guardarCompra(detalles,cab);
    }
    public boolean guardarIngreso(ArrayList<Detallees> detalles){
        return dao.guardarIngresoSalida(detalles, 2);
    }
    
    public boolean guardarIngresoPorNCDevolucion(ArrayList<Detallees> detalles/*, List<Detallenota> detallesNota*/) {
        return dao.guardarIngresoPorNCDevolucion(detalles, 2/*, detallesNota*/);
    }
    public boolean guardarSalida(ArrayList<Detallees> detalles){
        return dao.guardarIngresoSalida(detalles, 3);
    }
    public Operaciones getOperacion(String nombre){
        return new OperacionesDAO().getOperacion(nombre);
    }
    public Sistema getSis(String nombre){
        return sisDAO.obtener_por_nombre(nombre);
    }
    public boolean actualizarSistema(Sistema t){
        return sisDAO.Modificar_Objeto(t);
    }
    
    public boolean actualizaSistema(Sistema t) {
        return sisDAO.actualizar(t);
    }
}
