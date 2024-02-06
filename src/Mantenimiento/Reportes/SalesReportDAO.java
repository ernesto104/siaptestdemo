package Mantenimiento.Reportes;

import Entidades.SalesReport;
import Mantenimiento.GenericDAO;
import java.util.List;

/**
 *
 * @author Ledis
 */
public class SalesReportDAO extends GenericDAO<SalesReport> {
    
    public List Obtener_Lista_Ventas_Con_SP(int anio) {
        List lista = null;
        String consulta = "CALL getSalesReport2(:anyo)";
        lista = getHibernateTemplate().createSQLQuery(consulta)
                    .addEntity(SalesReport.class)
                    .setParameter("anyo", anio)
                    .list();
        return lista;
    }
}
