/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Control;
import Mantenimiento.ControlDAO;
import javax.swing.JFrame;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Control_Sistema {
    
    ControlDAO controlDAO;
    
    public Servicio_Control_Sistema(JFrame IU){
        controlDAO = new ControlDAO(IU);
    }
    
    public String obtener_Ruta_Carpeta(){
        return controlDAO.obtenerRutaCarpeta();
    }
    
    public Control obtener_Unico_Control(){
        Control c = new Control();
        c = controlDAO.Obtener_Objeto(1);
        return c;
    }
    
    public boolean modificar_Control(Control c){
        return controlDAO.actualizarControl(c);
    }
}
