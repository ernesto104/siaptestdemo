/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Clientes;
import Entidades.Equipos;
import Entidades.PuntosVenta;
import Entidades.Roles;
import Entidades.Transportistas;
import Entidades.Usuarios;
import Mantenimiento.EquiposDAO;
import Mantenimiento.PuntosVentaDAO;
import Mantenimiento.UsuarioDAO;
import Presentacion.FREP003;
import Presentacion.FREP0057;
import Presentacion.FREP015;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 *
 * @author usuario
 */
public class Servicio_Puntos_Venta {
    
    
    DefaultTableModel modelo;
    PuntosVentaDAO puntosventaDao;    
    FREP0057 it;
    int ultimo_id;
    public Servicio_Puntos_Venta(FREP0057 it){
        puntosventaDao = new PuntosVentaDAO();
        this.it=it;    
    }

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }
    
    
    public ArrayList<PuntosVenta> listar_PuntosVenta() {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from PuntosVenta");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
    public int listalineastamaño(){
        int tamaño = listar_PuntosVenta().size();
        return tamaño;
    }
    
    public void actualizarPuntosVenta(PuntosVenta l) {
        System.out.println("mira"+puntosventaDao.Modificar_Objeto(l));
        puntosventaDao.Modificar_Objeto(l);
    }
    
    
    public int valor_Codigo_Linea(){
        int codigo;
        if(listalineastamaño()==0){
            System.out.println("entro");
            return codigo=0;
        }else
        codigo = listar_PuntosVenta().get(listalineastamaño()-1).getIdpuntosventa();
        return codigo;        
    }
    
    public void eliminarPuntosVenta(PuntosVenta l) {
        puntosventaDao.Eliminar_Objeto(l);        
    }
    
    public PuntosVenta getPuntosVenta(int id) {
        PuntosVenta puntosventa;
        //transportista = transportistaDao.getTransportista(id);
        puntosventa= puntosventaDao.Obtener_Objeto(id);
        return puntosventa;
    }
    
    public List getList(){
        return puntosventaDao.Obtener_Lista_Objetos();
    }
    
    public int nextId() {        
        return (int) puntosventaDao .nextId();

    }
    
   public PuntosVenta getPuntosVenta_por_codigo(int codigo) {
        PuntosVenta puntosventa = new PuntosVenta();
        puntosventa = puntosventaDao.Obtener_Objeto_por_codigo(codigo);        
        return puntosventa;
    }
   
    public boolean addPuntosVenta(PuntosVenta l) {
        return puntosventaDao.Agregar_Objeto(l);
        
    }
    
    public boolean borrarPuntosVenta(PuntosVenta l) {
        return puntosventaDao.Eliminar_Objeto(l);
    }

    public PuntosVenta buscarPuntosVentax_Nombre(String nombre){
        return puntosventaDao.Obtener_Objeto_por_nombre(nombre);
    }
    
    
      public void Listar_PuntosVenta() {

       DefaultTableModel table = (DefaultTableModel) it.tablaCodigoPuntosVenta.getModel();
        Iterator ite = puntosventaDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[3];
            PuntosVenta lin = (PuntosVenta) ite.next();
            row[0] = lin.getIdpuntosventa();
            row[1] = lin.getDescripcion();
            row[2] = lin.getEstado();
            //row[3] = lin.getDescuento2();
            //row[4] = lin.getDescuento3();
            //row[5] = lin.getDescuento4();
            table.addRow(row);
        }
      
      }
    
    
    
}
