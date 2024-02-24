/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Clientes;
import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Modelos;
import Entidades.Roles;
import Entidades.Transportistas;
import Entidades.Usuarios;
import Mantenimiento.EquiposDAO;
import Mantenimiento.MarcasDAO;
import Mantenimiento.ModelosDAO;
import Mantenimiento.UsuarioDAO;
import Presentacion.FREP003;
import Presentacion.FREP0055;
import Presentacion.FREP0056;
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
public class Servicio_Modelos {
    
    
    DefaultTableModel modelo;
    ModelosDAO modeloDao;    
    FREP0056 it;
    int ultimo_id;
    public Servicio_Modelos(FREP0056 it){
        modeloDao = new ModelosDAO();
        this.it=it;    
    }

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }
    
    
    public ArrayList<Modelos> listar_Modelos() {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Modelos");
        ArrayList lista = (ArrayList) q.list();        
        return lista;      
    }
    
    public int listalineastamaño(){
        int tamaño = listar_Modelos().size();
        return tamaño;
    }
    
    public void actualizarModelos(Modelos mod) {
        System.out.println("mira"+modeloDao.Modificar_Objeto(mod));
        modeloDao.Modificar_Objeto(mod);
    }
    
    
    public int valor_Codigo_Linea(){
        int codigo;
        if(listalineastamaño()==0){
            System.out.println("entro");
            return codigo=0;
        }else
        codigo = listar_Modelos().get(listalineastamaño()-1).getIdmodelo();
        return codigo;        
    }
    
    public void eliminarModelos(Modelos mod) {
        modeloDao.Eliminar_Objeto(mod);        
    }
    
    public Modelos getModelos(int id) {
        Modelos modelos;
        //transportista = transportistaDao.getTransportista(id);
        modelos= modeloDao.Obtener_Objeto(id);
        return modelos;
    }
    
    public List getList(){
        return modeloDao.Obtener_Lista_Objetos();
    }
    
    public int nextId() {        
        return (int) modeloDao .nextId();

    }
    
   public Modelos getModelos_por_codigo(int codigo) {
        Modelos modelos = new Modelos();
        modelos = modeloDao.Obtener_Objeto_por_codigo(codigo);        
        return modelos;
    }
   
    public boolean addModelos(Modelos mod) {
        //if(modeloDao.)
        return modeloDao.Agregar_Objeto(mod);   
    }
    
    public boolean borrarModelos(Modelos l) {
        return modeloDao.Eliminar_Objeto(l);
    }

    public Modelos buscarModelosx_Nombre(String nombre){
        return modeloDao.Obtener_Objeto_por_nombre(nombre);
    }
    
    
    public void ListarModelosx_Marcas(Marcas marca){
        DefaultTableModel table = (DefaultTableModel) it.tablaCodigoModelos.getModel();
        Iterator ite =  modeloDao.Obtener_Lista_Objetos_OrderMarcas(marca).iterator();
        
        while (ite.hasNext()) {
            Object[] row = new Object[7];
            Modelos lin = (Modelos) ite.next();
            row[0] = lin.getEquipo().getIdequipo();
            row[1] = lin.getEquipo().getDescripcion();
            row[2] = lin.getMarca().getIdmarca();
            row[3] = lin.getMarca().getDescripcion();
            row[4] = lin.getIdmodelo();
            row[5] = lin.getDescripcion();
            row[6] = lin.getEstado();
            table.addRow(row);
        }
        
    }
    
      public void Listar_modelos() {

       DefaultTableModel table = (DefaultTableModel) it.tablaCodigoModelos.getModel();
        Iterator ite = modeloDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[7];
            Modelos mod = (Modelos) ite.next();
            row[0] = mod.getEquipo().getIdequipo();
            row[1] = mod.getEquipo().getDescripcion();
            row[2] = mod.getMarca().getIdmarca();
            row[3] = mod.getMarca().getDescripcion();
            row[4] = mod.getIdmodelo();
            row[5] = mod.getDescripcion();
            row[6] = mod.getEstado();
            table.addRow(row);
        }
      
      }
    
    
    
}
