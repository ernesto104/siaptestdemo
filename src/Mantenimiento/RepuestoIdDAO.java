
package Mantenimiento;

import Entidades.RepuestosId;

/**
 *
 * @author Lesly Aguilar
 */
public class RepuestoIdDAO extends GenericDAO<RepuestosId>{
    
    public RepuestosId obtenerRepuestoID(int idRepuesto) {
        RepuestosId repuestoID = new RepuestosId(idRepuesto);
        return repuestoID;
    }
     
}
