package Mantenimiento;

import Entidades.Control;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Lely Aguilar
 */
public class ControlSistemaDAO extends GenericDAO<Control>{
    
    private JFrame it;
    
    public ControlSistemaDAO(JFrame IU){
        this.it = IU;
    }
    
    private String obtenerRutaCarpeta(){
        String ruta;
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int respuesta = fc.showOpenDialog(it);
        if(respuesta== JFileChooser.APPROVE_OPTION){            
            ruta = fc.getSelectedFile().getAbsolutePath();
            return ruta;         
        }
        else{
            return null;
        }
    }
    
}
