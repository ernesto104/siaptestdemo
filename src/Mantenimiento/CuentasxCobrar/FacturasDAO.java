
package Mantenimiento.CuentasxCobrar;

import Entidades.Cabeces;
import Mantenimiento.GenericDAO;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Keily Mendiolaza
 */
public class FacturasDAO extends GenericDAO<Cabeces> {

        //NUevas cuentas
    public List Listar_Cuentas_x_Cobrar() {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc, "
                + " c.nrodocumento, "
                + " c.nrorserie, "
                + " c.fecha, "
                + "(select nombre from Clientes where idcliente = c.idcliente), "
                + " c.total, "
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)), "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " c.idbanco, "
                + " (select ruc from Clientes where idcliente = c.idcliente), "
                + " if(c.moneda = 1,'S/.','US$') "
                          
                + ", c.estado "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc in ('01','02','03','04') "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) "
                + " order by c.fecha desc, c.nrorserie desc, c.nrodocumento desc ").list();
    }
    
    public List Listar_Cuentas_General(){
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0,sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado "
                          
                + " from cabeces c"
                + " left join pagos p"
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc"
                + " where c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie)"
                + " order by c.fecha desc").list();
    }
    
    public List Listar_Cuentas_xCobrar_Plaza(String plaza) {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado"
                          
                + " from cabeces c"
                + " left join pagos p"
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc "
                + " where c.idcliente in (select idcliente from Clientes where plaza = '" + plaza + "')"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0) "
                + " order by c.idcliente, c.fecha desc").list();
    }
    
    public List Listar_Cuentas_General_Plaza(String plaza) {
         return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado "
                          
                + " from cabeces c"
                + " left join pagos p"
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc "
                + " where c.idcliente in (select idcliente from Clientes where plaza = '" + plaza + "')"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }
    
     public List Listar_Cuentas_xCobrar_Vendedor(String vendedor) {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$')"
                + " c.estado "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc"
                + " where c.idvendedor in (select idvendedor from Vendedores where nombre = '" + vendedor + "')"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                + " having ((c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0)"
                + " order by c.fecha desc").list();
    }
    
    public List Listar_Cuentas_General_Vendedor(String vendedor) {
         return getHibernateTemplate().createSQLQuery(
                   " select c.tipodoc,"
                 + " c.nrodocumento,"
                 + " c.nrorserie,"
                 + " c.fecha,"
                 + " (select nombre from Clientes where idcliente = c.idcliente),"
                 + " c.total,"
                 + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                 + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                 + " c.idbanco,"
                 + " (select ruc from Clientes where idcliente = c.idcliente),"
                 + " if(c.moneda = 1,'S/.','US$'),"
                 + " c.estado "
                           
                 + " from cabeces c"
                 + " left join pagos p"
                 + " on c.nrodocumento = p.nrodocumento"
                 + " and c.tipotra = p.tipotra"
                 + " and c.nrorserie = p.nroserie"
                 + " and c.tipodoc = p.tipodoc"
                 + " where c.idvendedor in (select c.idvendedor from Vendedores where nombre = '" + vendedor + "')"
                 + " and c.tipodoc in ('01','02','03','04')"
                 + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                 + " order by c.fecha desc").list();
    }
    
    public List Listar_Cuentas_xCobrar_Factura(String fac) {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado " 
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc"
                + " where c.nrodocumento = '" + fac + "'"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0)"
                + " order by c.fecha desc").list();
    }
    
    public List Listar_Cuentas_General_Factura(String fac) {
         return getHibernateTemplate().createSQLQuery(
                   " select c.tipodoc,"
                 + " c.nrodocumento,"
                 + " c.nrorserie,"
                 + " c.fecha,"
                 + " (select nombre from Clientes"
                 + " where idcliente = c.idcliente),"
                 + " c.total,"
                 + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                 + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                 + " c.idbanco,"
                 + " (select ruc from Clientes where idcliente = c.idcliente),"
                 + " if(c.moneda = 1,'S/.','US$'),"
                 + " c.estado "
                           
                 + " from cabeces c"
                 + " left join pagos p"
                 + " on c.nrodocumento = p.nrodocumento"
                 + " and c.tipotra = p.tipotra"
                 + " and c.nrorserie = p.nroserie"
                 + " and c.tipodoc = p.tipodoc where c.nrodocumento = '" + fac + "'"
                 + " and c.tipodoc in ('01','02','03','04')"
                 + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                 + " order by c.fecha desc").list();
    }
    
//    public List Listar_Cuentas_xCobrar_Fecha(String fecha) {
    public List Listar_Cuentas_xCobrar_Fecha(Date fecha) {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + "if(c.moneda = 1,'S/.','US$'),"
                + " c.estado "
                          
                + " from cabeces c"
                + " left join pagos p"
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
//                + "c.tipodoc = p.tipodoc where c.fecha = '"+fecha+"' and c.tipodoc in ('01','02','03','04') group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) "
                + " and c.tipodoc = p.tipodoc"
                + " where date(c.fecha) = date(:fechsk)"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                + " having ((c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0)"
                + " order by c.fecha desc")
                .setParameter("fechsk", fecha).list();
//                .list();
    }
    
//    public List Listar_Cuentas_General_Fecha(String fecha) {
    public List Listar_Cuentas_General_Fecha(Date fecha) {
         return getHibernateTemplate().createSQLQuery(
                   " select c.tipodoc,"
                 + " c.nrodocumento,"
                 + " c.nrorserie,"
                 + " c.fecha,"
                 + " (select nombre from Clientes where idcliente = c.idcliente),"
                 + " c.total,"
                 + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                 + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                 + " c.idbanco,"
                 + " (select ruc from Clientes where idcliente = c.idcliente),"
                 + " if(c.moneda = 1,'S/.','US$'),"
                 + " c.estado "
                           
                 + " from cabeces c"
                 + " left join pagos p"
                 + " on c.nrodocumento = p.nrodocumento"
                 + " and c.tipotra = p.tipotra"
                 + " and c.nrorserie = p.nroserie"
                 + " and c.tipodoc = p.tipodoc"
                 + " where date(c.fecha) = date(:fechsk)"
                 + " and c.tipodoc in ('01','02','03','04')"
                 + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                 + " order by c.fecha desc")
                 .setParameter("fechsk", fecha).list();
//                + "c.tipodoc = p.tipodoc where c.fecha = '"+fecha+"' and c.tipodoc in ('01','02','03','04') group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) order by c.fecha desc").list();
    }
    
    
        public List Listar_Cuentas_xCobrar_Monto(String monto) {
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                + " c.idbanco,"
                + " (select ruc from Clientes where idcliente = c.idcliente),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado "     
                
                + " from cabeces c"
                + " left join pagos p"
                + " on c.nrodocumento = p.nrodocumento"
                + " and c.tipotra = p.tipotra"
                + " and c.nrorserie = p.nroserie"
                + " and c.tipodoc = p.tipodoc"
                + " where c.total = '" + monto + "'"
                + " and c.tipodoc in ('01','02','03','04')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                + " having ((c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0)"
                + " order by c.fecha desc")
                .list();
    }
    
    public List Listar_Cuentas_General_Monto(String monto) {
         return getHibernateTemplate().createSQLQuery(
                   " select c.tipodoc,"
                 + " c.nrodocumento,"
                 + " c.nrorserie,"
                 + " c.fecha,"
                 + " (select nombre from Clientes where idcliente = c.idcliente),"
                 + " c.total,"
                 + " if(sum(p.importe) is null, 0.0, sum(p.importe)),"
                 + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)),"
                 + " c.idbanco,"
                 + " (select ruc from Clientes where idcliente = c.idcliente),"
                 + " if(c.moneda = 1,'S/.','US$'),"
                 + " c.estado "          
                           
                 + " from cabeces c "
                 + " left join pagos p "
                 + " on c.nrodocumento = p.nrodocumento "
                 + " and c.tipotra = p.tipotra "
                 + " and c.nrorserie = p.nroserie "
                 + " and c.tipodoc = p.tipodoc "
                 + " where c.total = '" + monto + "' "
                 + " and c.tipodoc in ('01','02','03','04')"
                 + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie)"
                 + " order by c.fecha desc")
                 .list();
    }
    
     
     public List Listar_Cuentas_xCobrar_Cliente(int id) {
         System.out.println("id:" + id);
        return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc, "
                          + "c.nrodocumento, "
                          + "c.nrorserie, "
                          + "c.fecha, "
                          + "(select nombre from Clientes where idcliente = c.idcliente), "
                          + "c.total, "
                          + " if ( sum(p.importe) is null, 0.0, sum(p.importe) ), "
                          + " if ( c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe) ), "
                          + " c.idbanco, "
                          + "(select ruc from Clientes where idcliente = c.idcliente), "
                          + " if ( c.moneda = 1, 'S/.', 'US$') "
                          + ", c.estado "
                          
                          + " from cabeces c "
                          + " left join pagos p "
                          + " on c.nrodocumento = p.nrodocumento "
                          + " and c.tipotra = p.tipotra "
                          + " and c.nrorserie = p.nroserie "
                          + " and c.tipodoc = p.tipodoc "
                          + " where c.idcliente = '" + id + "' "
                          + " and c.tipodoc in ('01','02','03','04') "
                          + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                          + " having ( (c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0 ) "
                          + " order by c.fecha desc, c.nrorserie desc, c.nrodocumento desc ").list();
    }
    
    public List Listar_Cuentas_General_Cliente(int id) { // Cta. Cte. de facturas x 1 cliente
         return getHibernateTemplate().createSQLQuery(
                  " select c.tipodoc,"
                + " c.nrodocumento,"
                + " c.nrorserie,"
                + " c.fecha,"
                + " (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.total,"
                + " if ( sum(p.importe) is null, 0.0, sum(p.importe) ),"
                + " if ( c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe) ),"
                + " c.idbanco,"
                + " ( select ruc from Clientes where idcliente = c.idcliente ),"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.estado "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.idcliente = " + id + " "
                + " and c.tipodoc in ('01','02','03','04') "
//                + " and c.estado != 'ANULADO'  "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) "                          
                + " order by c.fecha desc, c.nrorserie desc, c.nrodocumento desc ").list();
    }
    
    public List consultarRepuesto(Cabeces guia) {
        return getHibernateTemplate().createQuery("from Deta c where c.nroguia = " + guia.getNroguia()).list();
    }
    
}
