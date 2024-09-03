/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Clientes;
import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Roles;
import Entidades.Transportistas;
import Entidades.Usuarios;
import Mantenimiento.EquiposDAO;
import Mantenimiento.MarcasDAO;
import Mantenimiento.UsuarioDAO;
import Presentacion.FREP003;
import Presentacion.FREP0055;
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
public class Servicio_Marcas {
    
    
    DefaultTableModel modelo;
    MarcasDAO marcaDao;    
    FREP0055 it;
    int ultimo_id;
    public Servicio_Marcas(FREP0055 it){
        marcaDao = new MarcasDAO();
        this.it=it;    
    }

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }
    
    
    public ArrayList<Equipos> listar_Equipos() {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Equipos");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
    public int listalineastamaño(){
        int tamaño = listar_Equipos().size();
        return tamaño;
    }
    
    public void actualizarEquipos(Marcas l) {
        System.out.println("mira"+marcaDao.Modificar_Objeto(l));
        marcaDao.Modificar_Objeto(l);
    }
    
    
    public int valor_Codigo_Linea(){
        int codigo;
        if(listalineastamaño()==0){
            System.out.println("entro");
            return codigo=0;
        }else
        codigo = listar_Equipos().get(listalineastamaño()-1).getIdequipo();
        return codigo;        
    }
    
    public void eliminarEquipos(Marcas l) {
        marcaDao.Eliminar_Objeto(l);        
    }
    
    public Marcas getMarcas(int id) {
        Marcas marcas;
        //transportista = transportistaDao.getTransportista(id);
        marcas= marcaDao.Obtener_Objeto(id);
        return marcas;
    }
    
    public List getList(){
        return marcaDao.Obtener_Lista_Objetos();
    }
    
    public int nextId() {        
        return (int) marcaDao .nextId();

    }
    
   public Marcas getMarcas_por_codigo(int codigo) {
        Marcas marcas = new Marcas();
        marcas = marcaDao.Obtener_Objeto_por_codigo(codigo);        
        return marcas;
    }
   
    public boolean addMarcas(Marcas l) {
        //if(marcaDao.)
        return marcaDao.Agregar_Objeto(l);   
    }
    
    public boolean borrarMarcas(Marcas l) {
        return marcaDao.Eliminar_Objeto(l);
    }

    public Marcas buscarMarcasx_Nombre(String nombre){
        return marcaDao.Obtener_Objeto_por_nombre(nombre);
    }
    
    public Marcas buscarMarcasx_Equipox_Nombre(String equipoNombre, String nombre){
        return marcaDao.Obtener_Objeto_por_equipoNombre_y_por_nombre(equipoNombre, nombre);
    }
    
    public List buscarMarcasx_Equipo(Equipos equipo){
        return marcaDao.Listar_Marcas_por_Equipo(equipo);
        
    }
    
    public List buscarMarcasx_CodigoEquipo(int idEquipo){
        return marcaDao.Listar_Marcas_por_CodigoEquipo(idEquipo);
        
    }
    
    
    public void ListarMarcasx_Equipo(Equipos equipo){
        //return marcaDao.Listar_Marcas_por_Equipo(equipo);
        
        DefaultTableModel table = (DefaultTableModel) it.tablaCodigoMarcas.getModel();
        Iterator ite = marcaDao.Listar_Marcas_por_Equipo(equipo).iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[3];
            Marcas lin = (Marcas) ite.next();
            row[0] = lin.getIdmarca();
            row[1] = lin.getDescripcion();
            row[2] = lin.getEstado();
            table.addRow(row);
        }
        
    }
    
      public void Listar_marcas() {

       DefaultTableModel table = (DefaultTableModel) it.tablaCodigoMarcas.getModel();
        Iterator ite = marcaDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[3];
            Marcas lin = (Marcas) ite.next();
            row[0] = lin.getIdmarca();
            row[1] = lin.getDescripcion();
            row[2] = lin.getEstado();
            table.addRow(row);
        }
      
      }
    
    
    
}
