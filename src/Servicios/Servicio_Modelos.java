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
    
    
    public Modelos buscarModelosx_EquipoNombrex_MarcaNombrex_Nombre(String equipoNombre, String marcaNombre, String nombre){
        return modeloDao.Obtener_Objeto_por_equipoNombre_por_marcaNombre_nombre(equipoNombre, marcaNombre, nombre);
    }
    
    public List buscarModelosx_Marca(Marcas marca){
        return modeloDao.Obtener_Lista_Objetos_OrderMarcas(marca);
    }
    
    public List buscarModelosx_CodigoMarca(int idMarca){
        return modeloDao.Obtener_Lista_Objetos_OrderCodigoMarcas(idMarca);
    }
    
    
    public void ListarModelosx_Marcas(Marcas marca){
        DefaultTableModel table = (DefaultTableModel) it.tablaCodigoModelos.getModel();
        Iterator ite =  modeloDao.Obtener_Lista_Objetos_OrderMarcas(marca).iterator();
        
        while (ite.hasNext()) {
            Object[] row = new Object[3];
            Modelos lin = (Modelos) ite.next();
            row[0] = lin.getIdmodelo();
            row[1] = lin.getDescripcion();
            row[2] = lin.getEstado();
            table.addRow(row);
        }
        
    }
    
      public void Listar_modelos() {

       DefaultTableModel table = (DefaultTableModel) it.tablaCodigoModelos.getModel();
        Iterator ite = modeloDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[3];
            Modelos mod = (Modelos) ite.next();
            row[0] = mod.getIdmodelo();
            row[1] = mod.getDescripcion();
            row[2] = mod.getEstado();
            table.addRow(row);
        }
      
      }
    
    
    
}
