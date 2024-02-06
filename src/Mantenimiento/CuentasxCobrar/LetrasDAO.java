package Mantenimiento.CuentasxCobrar;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Pagos;
import Entidades.Vendedores;
import Mantenimiento.GenericDAO;
import Servicios.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Keily Mendiolaza
 * @author modified : Ledis Rivera (2016)
 */
public class LetrasDAO extends GenericDAO<Cabeces> {

    public List Listar_Cuentas_x_Cobrar_Letras() {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.nrodocumento,"
                + " c.nrorenovacion,"
                + " c.fecha,"
                + " c.fechavencimiento,"
                + " if ( c.moneda = 1, 'S/.', 'US$' ),"
                + " c.total,"
                + " if ( c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe) ),"
                + " datediff(now(), c.fechavencimiento),"
                + " c.letra_nrobanco,"
                + " c.letra_facbol,"
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ( (c.total - if(sum(p.importe) is null, 0.0, sum(p.importe)))!= 0.0 ) "
                + " order by c.fecha desc, c.nrorserie desc, cast(c.nrodocumento AS UNSIGNED ) DESC ").list();
    }

    public List Listar_Cuentas_xCobrar_Cliente(String cliente) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if ( c.moneda = 1, 'S/.', 'US$'), "
                + " c.total, "
                + " if ( c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe) ), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.idcliente in ( "
                          + " select idcliente from Clientes "
                          + " where nombre = '" + cliente + "' ) "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) "
                + " having ( (c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_xCobrar_RUC(String ruc) {
        return getHibernateTemplate().createSQLQuery(
                  " select ( select nombre from Clientes "
                        + " where idcliente = c.idcliente ), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if ( c.moneda = 1,'S/.','US$' ), "
                + " c.total, "
                + " if ( c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe) ), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.idcliente in ( "
                          + " select idcliente "
                          + " from Clientes "
                          + " where ruc = '" + ruc + "' ) "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ( (c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0 )"
                + " order by c.fecha desc ").list();
    }

    public List Listar_Cuentas_xCobrar_factura(String factura) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' and c.letra_facbol like '%" + factura + "%' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) order by c.fecha desc").list();
    }

    public List Listar_Cuentas_xCobrar_nletra(String nletra) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' and c.nrodocumento = '" + nletra + "' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) order by c.fecha desc").list();
    }

    public List Listar_Cuentas_xCobrar_fechaVencimiento(Date fechavenc) {
        
        System.out.println("Fecha de vencimiento : " + fechavenc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(fechavenc);       
        
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.fechavencimiento = '" + fecha + "' "
//                + " and c.fechavencimiento = '2023-12-20' "                          
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) order by c.fecha desc").list();
    }

    public List Listar_Cuentas_xCobrar_monto(String monto) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, " 
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.total = '" + monto + "' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " having ((c.total - if(sum(p.importe) is null, 0.0,sum(p.importe)))!= 0.0) order by c.fecha desc").list();
    }

    //LETRAS EN GENERAL
    public List Listar_Cuentas_General_Letras() {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente),"
                + " c.nrodocumento,"
                + " c.nrorenovacion,"
                + " c.fecha,"
                + " c.fechavencimiento,"
                + " if(c.moneda = 1,'S/.','US$'),"
                + " c.total,"
                + " if(c.total - sum(p.importe) is null,c.total, c.total - sum(p.importe)),"
                + " datediff(now(), c.fechavencimiento),"
                + " c.letra_nrobanco,"
                + " c.letra_facbol,"
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_Cliente(String cliente) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.idcliente in (select idcliente from Clientes where nombre = '" + cliente + "') "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra),(c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_RUC(String ruc) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.idcliente in (select idcliente from Clientes where ruc = '" + ruc + "')"
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_factura(String factura) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' and c.letra_facbol like '%" + factura + "%' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_nletra(String nletra) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' and c.nrodocumento = '" + nletra + "' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_fechaVencimiento(Date fechavenc) {

        System.out.println("Fecha de vencimiento : " + fechavenc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(fechavenc);       
                
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' "
                + " and c.fechavencimiento = '" + fecha + "' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public List Listar_Cuentas_General_monto(String monto) {
        return getHibernateTemplate().createSQLQuery(
                  " select (select nombre from Clientes where idcliente = c.idcliente), "
                + " c.nrodocumento, "
                + " c.nrorenovacion, "
                + " c.fecha, "
                + " c.fechavencimiento, "
                + " if(c.moneda = 1,'S/.','US$'), "
                + " c.total, "
                + " if(c.total - sum(p.importe) is null, c.total, c.total - sum(p.importe)), "
                + " datediff(now(), c.fechavencimiento), "
                + " c.letra_nrobanco, "
                + " c.letra_facbol, "
                + " c.estado, "
                + " c.letra_banco, "
                + " c.letra_tipo "
                          
                + " from cabeces c "
                + " left join pagos p "
                + " on c.nrodocumento = p.nrodocumento "
                + " and c.tipotra = p.tipotra "
                + " and c.nrorserie = p.nroserie "
                + " and c.tipodoc = p.tipodoc "
                + " where c.tipodoc = '06' and c.total = '" + monto + "' "
                + " group by (c.nrodocumento), (c.tipodoc), (c.tipotra), (c.nrorserie) "
                + " order by c.fecha desc").list();
    }

    public boolean AnularLetra(Cabeces c) {
              
        System.out.println("entra para anular");
        
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        try {
            
            ses.update(c);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            ses.close();
        }
        return true;
    }

    public Cabeces Buscar_Cuenta_Letra(String numero) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces c where c.id.tipotra = 'V' and c.id.tipodoc = '06' and c.id.nrodocumento = '" + numero + "'").uniqueResult();
    }

    public Cabeces obtenerCabecera_Id(CabecesId id) {
        return (Cabeces) getHibernateTemplate().createQuery("from Cabeces c where c.id.tipotra = 'V' and c.id.tipodoc = '06' and c.id.nrodocumento = '" + id.getNrodocumento() + "'").uniqueResult();
    }

    public List<Pagos> listarPagosxLetras(CabecesId cabecesID) {
        return getHibernateTemplate().createQuery("from Pagos where cabeces.id.tipotra = 'V' and cabeces.id.tipodoc = '06' and cabeces.id.nrodocumento = '" + cabecesID.getNrodocumento() + "'").list();
    }

    public List Listar_Cuentas_xCobrar_fechaVencimiento(String fecha222) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
