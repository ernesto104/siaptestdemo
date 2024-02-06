/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Accesos;
import Entidades.Programas;
import Entidades.Roles;
import Mantenimiento.AccesosDAO;
import Mantenimiento.ProgramasDAO;
import Mantenimiento.RolesDAO;
import java.util.List;

/**
 *
 * @author CRS
 */
public class Servicio_Accesos {
    public Servicio_Accesos(){
        
    }
    public List Lista_Roles(){
        return new RolesDAO().Obtener_Lista_Objetos();
    }
    public List Lista_Programas(){
        return new ProgramasDAO().Obtener_Lista_Objetos();
    }/*
    public boolean ActualizarAccesos(List Lista_roles){
        return new AccesosDAO().GuardarAccesos(Lista_roles);
    }*/
    /*
    public boolean ActualizarAccesos(List programas,
                                     Roles rol){
        return new AccesosDAO().GuardarAccesos(programas, rol);
    }*/
    public boolean ActualizarAccesos(List<Programas> programasEliminados,
                                     List<Programas> programasNuevos,
                                     Roles rol){
        //return new AccesosDAO().GuardarAccesos(programas, rol);
        return new AccesosDAO().GuardarAccesos(programasEliminados, programasNuevos, rol);
    }
    
    public List<Accesos> listarAccesos(int idRol) {
        return new AccesosDAO().listarAccesos(idRol);
    }
}
