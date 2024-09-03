/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import Entidades.TipoServicioTecnico;
import Mantenimiento.TipoServicioTecnicoDAO;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Servicio_TipoServicioTecnico {
    
    
    TipoServicioTecnicoDAO tipoServicioTecnicoDAO;

    public Servicio_TipoServicioTecnico() {
        this.tipoServicioTecnicoDAO = new TipoServicioTecnicoDAO();
    }
    
    
    public boolean addRepuestos(TipoServicioTecnico tpt) {
        //System.out.println(maestrosDAO.Agregar_Objeto(r));
        return tipoServicioTecnicoDAO.Agregar_Objeto(tpt);

    }
    
    
    public int listarTipoServicioTecnico(DefaultTableModel modelo) {
        
        List listaTipoServicioTecnico = tipoServicioTecnicoDAO.Obtener_TiposServicioTecnico();
    
        Iterator it = listaTipoServicioTecnico.iterator();
        
        while(it.hasNext()) {
            Object[] result = (Object[]) it.next();
            Object[] row = new Object[6];
            
            row[0] = result[1];
            row[1] = result[2];
            row[2] = result[3];
            row[3] = result[4];
            
            modelo.addRow(row);
        }
            return listaTipoServicioTecnico.size();
    }
    
}
