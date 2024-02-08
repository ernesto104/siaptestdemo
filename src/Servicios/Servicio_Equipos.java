/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Clientes;
import Entidades.Equipos;
import Entidades.Roles;
import Entidades.Transportistas;
import Entidades.Usuarios;
import Mantenimiento.EquipoDAO;
import Mantenimiento.UsuarioDAO;
import Presentacion.FREP003;
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
public class Servicio_Equipos {
    
    
    DefaultTableModel modelo;
    EquipoDAO equipoDao;    
    FREP003 it;
    int ultimo_id;
    public Servicio_Equipos(FREP003 it){
        equipoDao = new EquipoDAO();
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
    
    public void actualizarEquipos(Equipos l) {
        System.out.println("mira"+equipoDao.Modificar_Objeto(l));
        equipoDao.Modificar_Objeto(l);
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
    
    public void eliminarEquipos(Equipos l) {
        equipoDao.Eliminar_Objeto(l);        
    }
    
    public Equipos getEquipos(int id) {
        Equipos equipos;
        //transportista = transportistaDao.getTransportista(id);
        equipos= equipoDao.Obtener_Objeto(id);
        return equipos;
    }
    
    public List getList(){
        return equipoDao.Obtener_Lista_Objetos();
    }
    
    public int nextId() {        
        return (int) equipoDao .nextId();

    }
    
   public Equipos getEquipos_por_codigo(int codigo) {
        Equipos equipos = new Equipos();
        equipos = equipoDao.Obtener_Objeto_por_codigo(codigo);        
        return equipos;
    }
   
    public boolean addEquipos(Equipos l) {
        return equipoDao.Agregar_Objeto(l);
        
    }
    
    public boolean borrarEquipos(Equipos l) {
        return equipoDao.Eliminar_Objeto(l);
    }

    public Equipos buscarEquiposx_Nombre(String nombre){
        return equipoDao.Obtener_Objeto_por_nombre(nombre);
    }
    
    
      public void Listar_equipos() {

       DefaultTableModel table = (DefaultTableModel) it.tablaCodigoEquipos.getModel();
        Iterator ite = equipoDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[6];
            Equipos lin = (Equipos) ite.next();
            row[0] = lin.getIdequipo();
            row[1] = lin.getDescripcion();
            row[2] = lin.getEstado();
            row[3] = lin.getDescuento2();
            row[4] = lin.getDescuento3();
            row[5] = lin.getDescuento4();
            table.addRow(row);
        }
      
      }
    
    
    
}
