
package Mantenimiento;

import Entidades.CabecesId;


/**
 *
 * @author Lesly Aguilar
 */
public class CabecesIdDAO extends GenericDAO<CabecesId>{

    public CabecesId obtenerCabeceraID(String tipoDocumento, String numeroDocumento) {
        return new CabecesId(tipoDocumento,numeroDocumento);
    }
    
    public CabecesId obtenerCabeceraID(String tipoTransaccion, String tipoDocumento, String numeroSerie, String numeroDocumento) {
        return new CabecesId(tipoTransaccion, tipoDocumento, numeroSerie, numeroDocumento);
    }
    
}