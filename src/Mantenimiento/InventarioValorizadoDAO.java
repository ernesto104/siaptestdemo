package Mantenimiento;

import Entidades.Detallees;
import static Entidades.Otros.Constante.TIT_IVG;
import Entidades.Repuestos;
import Servicios.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Keily Mendiolaza
 */
public class InventarioValorizadoDAO extends GenericDAO<Detallees> {
    
    public List listar_Inventario_aUnaFecha(String valorizacion, String fecha) {

        System.out.println("Entro a una fecha....");
        String desde = fecha;

        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String actual = sdf.format(hoy);
//        fecha = "2016-01-01";        
        //fecha = actual;
        System.out.println("Fecha Desde : " + desde);
        System.out.println("Fecha Hasta : " + actual);
        String cad = "";
        if (valorizacion.equals("Precio Lista")) {
            cad = "preciolista";
        }
//        if ( valorizacion.equals("Costo Promedio") ) {
        if (valorizacion.equals("Costo Unitario")) {
            cad = "costopromedio";
        }
        if (valorizacion.equals("Ultimo Costo")) {
            cad = "pcostoultimo";
        }
        // (**) Valga la redundancia con "preciolista" no se mostrará este valor en reporte, sólo es para tener una consulta correcta en sintaxis.
        if (valorizacion.equals(TIT_IVG)) {
            cad = "preciolista";
        }

        System.out.println("Estoy a una Fecha.....");
        String consulta
                = //                "select r.idlinea,r.codrepuesto,r.codigoseg,r.descripcion,r.aplicacion, "
                "select r.codrepuesto, r.descripcion, r.descrmodelo, r.desclista, "
                + "r.stock, r." + cad + ", (r.stock * r." + cad + ") as total,"
                //+ "substr(o.codigooperacion,1,1), "
//                + "d.tipotra,"
                + "d.idoperacion,"                
                + "d.cantentregada, " // 8
                + "d.fecha, "
                + "r.idlinea, "
                + "r.costopromedio, "
                + "r.preciolista, "
                + "r.codigoseg, " // 13
                + "r.aplicacion, "
                + "r.desclista2 "
                + "r.foxultimo "
                + "r.marca "                
                + "from repuestos r left join detallees d on r.idrepuesto = d.idrepuesto "
                + "left join operaciones o on d.idoperacion = o.idoperacion "
                + "where (d.fecha between date_add('" + desde + "', interval 1 day) and '" + actual + "') "
                + "order by r.descripcion, r.descrmodelo, d.fecha";
//                + "order by r.codrepuesto, d.fecha";        
        System.out.println("consulta:" + consulta);
        return getHibernateTemplate().createSQLQuery(consulta).list();
    }
    
    public List listar_CodigosInventarioSinRepetir(String valorizacion, String fecha) {
        
        System.out.println("Entro a SIN REPETIR.....");
        String desde = fecha;
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String actual = sdf.format(hoy);
        
        String cad = "";
        if (valorizacion.equals("Precio Lista")) {
            cad = "preciolista";
        }
        if (valorizacion.equals("Costo Unitario")) {
            cad = "costopromedio";
        }
        if (valorizacion.equals("Ultimo Costo")) {
            cad = "pcostoultimo";
        }
        if (valorizacion.equals(TIT_IVG)) {
            cad = "preciolista";
        }

        String consulta
                = "select distinct r.codrepuesto "
                + "from repuestos r "
                + "left join detallees d on r.idrepuesto = d.idrepuesto "
                + "left join operaciones o on d.idoperacion = o.idoperacion "
                + "where (d.fecha between date_add('" + desde + "', interval 1 day) and '" + actual + "') "
                + "order by r.descripcion, r.descrmodelo, d.fecha";
//                + "order by r.codrepuesto";
        System.out.println("consulta::" + consulta);
        
        return getHibernateTemplate().createSQLQuery(consulta).list();
    }

    public List getlistaRepuestos() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Repuestos> lista;
        try {
            lista = sesion.createQuery("from Repuestos r order by r.idlinea").list();
        } catch (HibernateException e) {
            return null;
        }
        return lista;
    }

    public List listar_Inventario_alaFecha_x_Linea(String valorizacion, String fecha) {

        System.out.println("Entro a la fecha....XXXXXXXXXXXXXXXXXXXx");
        Date hoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String actual = sdf.format(hoy);

        String cad = "";
        if (valorizacion.equals("Precio Lista")) {
            cad = "preciolista";
        }
        if (valorizacion.equals("Costo Promedio")) {
            cad = "costopromedio";
        }
        if (valorizacion.equals("Ultimo Costo")) {
            cad = "pcostoultimo";
        }

        // (**) Valga la redundancia con "preciolista" no se mostrará este valor en reporte, sólo es para tener una consulta correcta en sintaxis.
        if (valorizacion.equals(TIT_IVG)) {
            cad = "preciolista";
        }
        System.out.println("cadena: " + cad);

        return getHibernateTemplate().createSQLQuery("select r.idlinea,r.codrepuesto,r.codigoseg,r.descripcion,r.aplicacion, "
                + "r.stock,r." + cad + ",(r.stock * r." + cad + ") as total,substr(o.codigooperacion,1,1), d.cantentregada "
                + "from repuestos r left join detallees d on r.idrepuesto = d.idrepuesto "
                + "left join operaciones o on d.idoperacion = o.idoperacion "
                + "where (d.fecha between '" + fecha + "' and '" + actual + "') "
                + " order by r.descripcion, r.descrmodelo asc asc").list();
    }
}
