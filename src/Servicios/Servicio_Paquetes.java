/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Paquetes;
import Entidades.PaquetesRepuestos;
import Mantenimiento.PaqueteRepuestoDAO;
import Mantenimiento.PaquetesDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author fabrica2
 */
public class Servicio_Paquetes {

    private PaquetesDAO dao;
    private PaqueteRepuestoDAO pr;
    public Servicio_Paquetes() {
        dao = new PaquetesDAO();
        pr = new PaqueteRepuestoDAO();
    }
    public Paquetes obtenerPaquete(int id) {
        return dao.Obtener_Objeto(id);
    }

    public boolean insertarPaquete(Paquetes paquete) {
        return dao.Agregar_Objeto(paquete);
     }

    public boolean modificarPaquete(Paquetes paquete) {
        return dao.Modificar_Objeto(paquete);
    }

    public boolean eliminarPaquete(Paquetes paquete) {
        
        return pr.Eliminar_por_Paquete(paquete.getIdpaquete()) && dao.Eliminar_Objeto(paquete);
    }

    public int obtenerNextId() {
        return dao.nextId();
    }

    public List GetList() {
        return dao.Obtener_Lista_Objetos();
    }

    public List obtenerRepuestos_Paq(int id) {
        List lista = new ArrayList();
        Servicio_Maestros sm = new Servicio_Maestros(null);

        Iterator it = dao.obtenerRepuestos_Paq(id).iterator();
        while (it.hasNext()) {
            PaquetesRepuestos pr = (PaquetesRepuestos) it.next();
            lista.add(pr);
        }
        return lista;
    }
    
    
    
    
    /***************  Pertenece a Paquetes_Repuestos **********************/
    
    public List get_List_PaqRep(){ 
        return pr.Obtener_Lista_Objetos();
    }
    public List get_List_PaqRep(int id){
        return pr.Obtener_Repuestos(id);
    }
    public PaquetesRepuestos getPaq_rep(int id){
        return pr.Obtener_Objeto(id);
    }
    public PaquetesRepuestos getPaq_rep(int idpaq,String idRep){
        return pr.getPaq_Rep(idpaq,idRep);
    }
    public boolean agregar_PR(PaquetesRepuestos paqrep){
        return pr.Agregar_Objeto(paqrep);
    }
    public boolean eliminar_PR(PaquetesRepuestos paqrep){
        return pr.Eliminar_Objeto(paqrep);
    }
    public boolean modificar_PR(PaquetesRepuestos paqrep){
        return pr.Modificar_Objeto(paqrep);
    }
}
