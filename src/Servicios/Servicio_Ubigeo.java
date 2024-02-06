package Servicios;

import Entidades.Ubigeo;
import Mantenimiento.UbigeoDAO;
import java.util.List;

/**
 *
 * @author Ledis Rivera
 */
public class Servicio_Ubigeo {
    
    private UbigeoDAO ubigeoDAO;
    
    public Servicio_Ubigeo() {
        ubigeoDAO = new UbigeoDAO();
    }
    
    public List Listar_Departamentos_Order_Asc_Nombre() {
        return ubigeoDAO.Listar_Departamentos();
    }
    
    public List Listar_Provincias_Order_Asc_Nombre(String departamento) {
        return ubigeoDAO.Listar_Provincias(departamento);
    }
    
    public List Listar_Distritos_Order_Asc_Nombre(String departamento, String provincia) {
        return ubigeoDAO.Listar_Distritos(departamento, provincia);
    }
    
    public Ubigeo buscarUbigeo(String departamento, String provincia, String distrito) {
        return ubigeoDAO.buscarUbigeo(departamento, provincia, distrito);
    }
}