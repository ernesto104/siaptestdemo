/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Control;
import Mantenimiento.ControlDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Iterator;
/**
 *
 * @author fabrica2
 */
public class Servicio_Control {
    
    private ControlDAO dao;
   
    public Servicio_Control() {
        
    }
    
    public Control getControlUnico(){
        dao = new ControlDAO();
        Iterator ite = dao.Obtener_Lista_Objetos().iterator();
        ite.hasNext();
        Control tempCon = (Control) ite.next();
        return tempCon;
    }
    
    public Control getControl() {
        Control ctr = new Control();
        ctr = dao.obtener_objeto();
        return ctr;
    }
    
    public ArrayList<Control> listar_control() {   
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
    //        System.out.println("session:" + session);
    //        boolean conectado = session.isConnected();
    //        System.out.println("conectado:" + conectado);
            Query q = (Query) session.createQuery("from Control");

    //        System.out.println("q:" + q);
            ArrayList lista = (ArrayList) q.list();
    //        System.out.println("lista:" + lista);
            return lista;
            
        } catch ( Exception ex ) {
            JOptionPane.showMessageDialog(null,"No existe conexión a la Base de Datos, arranque MySQL","Error de Conexión a BD", 2);
//            System.out.println("Error:D ..>" + ex.getMessage());
            return null;
        }
    }
    
    public Integer obtenerIGV() {
        return dao.obtenerImpuesto();
    }
    
}
