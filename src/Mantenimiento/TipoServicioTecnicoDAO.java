/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mantenimiento;

import Entidades.TipoServicioTecnico;
import java.util.List;

/**
 *
 * @author User
 */
public class TipoServicioTecnicoDAO extends GenericDAO<TipoServicioTecnico> {
    
  public List Obtener_TiposServicioTecnico() {
      
      List list = null;
      
      try {
          list = getHibernateTemplate().createQuery(
           
            " select tst.id, tst.descripcion, tst.facturable, tst.estado"
                    + " from TipoServicioTecnico tst")
            .list();
          
      } catch (Exception e) {
          System.err.println("Error TipoServicioTecnicoDAO.Obtener_TiposServicioTecnico function:" + e.getMessage());
      } finally {
          session.close();
      }
      
      return list;
      
            
    }  
    
}
