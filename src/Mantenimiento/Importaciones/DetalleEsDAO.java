package Mantenimiento.Importaciones;

import Entidades.Cabeces;
import Entidades.Clientes;
import Entidades.Detallees;
import Entidades.Repuestos;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import Servicios.HibernateUtil;

public class DetalleEsDAO extends GenericDAO<Detallees> {

    public int nextId() {
        Detallees ds = null;
        try {
            ds = (Detallees) getHibernateTemplate().createQuery("from Detallees r order by r.iddetalles desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return ds.getIddetalles() + 1;
        }
    }

    public ArrayList<Detallees> buscarDetalleXDoc(Cabeces c) {
        String tipotra = c.getId().getTipotra();
        String tipodoc = c.getId().getTipodoc();
        String nroserie = c.getId().getNrorserie();
        String nrodoc = c.getId().getNrodocumento();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detallees as d "
                + "where d.cabeces.id.tipotra =" + tipotra + " and "
                + " d.cabece.id.tipodoc=" + tipodoc + " and "
                + "d.cabece.id.nrorserie =" + nroserie + " and "
                + "d.cabece.id.nrodocumento=" + nrodoc + " and "
                + "d.operaciones.idoperacion='S%'"
        );
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public List<Detallees> getDetalleESXRepuestos(Repuestos r) {
        List<Detallees> returnList = getHibernateTemplate().createQuery(
                "from Detallees as d where d.repuestos.id.idrepuesto=" + r.getId().getIdrepuesto()
                + " and d.repuestos.id.idlinea=" + r.getId().getIdequipo()
        ).list();
        session.getTransaction().commit();
        return returnList;
    }

    public List<Detallees> getDetalleESXRepuestosXCliente(Repuestos r, Clientes cli) {
        List<Detallees> returnList = getHibernateTemplate().createQuery(
                "from Detallees as d where d.repuestos.id.idrepuesto=" + r.getId().getIdrepuesto()
                + " and d.repuestos.id.idlinea=" + r.getId().getIdequipo()+ " "
                        + "and d.clientes.idcliente = "+cli.getIdcliente()+ " order by d.fecha desc").list();
        session.getTransaction().commit();
        return returnList;
    }

    public double getCantPedidaXRepuesto(Date fechaIni, Date fechaFin, int mesdemanda, int idrepuesto, int idlinea) {
        List returnList = getHibernateTemplate().createSQLQuery(
                "select sum(des.cantpedida)" + "from Detallees as des "
                + "inner join cabeces as ces on des.tipotra=ces.tipotra and des.tipodoc=ces.tipodoc and "
                + "des.nrorserie=ces.nrorserie and des.nrodocumento=ces.nrodocumento "
                + "inner join operaciones as o on des.idoperacion=o.idoperacion "
                + "where o.codigooperacion like 'S%' and des.idrepuesto=" + idrepuesto + " and des.idlinea=" + idlinea + " and"
                + " ces.fecha>='" + fechaIni + "' and ces.fecha<= '" + fechaFin + "' "
                + "group by des.idrepuesto, des.idlinea").list();
        session.getTransaction().commit();
        Iterator iteDet = returnList.iterator();
        double demandaProm = 0;
        if (iteDet.hasNext()) {
            demandaProm = Double.parseDouble(String.valueOf(iteDet.next()));
        }
                
        return demandaProm;
    }

    public List getCantPedidaXAño(int idRepuesto, int idLinea, Date fi, Date ff) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String query = "select month(ces.tipocambio.fecha), "
                + "year(ces.tipocambio.fecha),"
                + "sum(des.cantpedida) from Detallees as des "
                + "inner join des.cabeces as ces "
                + "inner join des.operaciones as op "
                + "where ces.id.tipotra=des.cabeces.id.tipotra "
                + "and ces.id.tipodoc=des.cabeces.id.tipodoc "
                + "and ces.id.nrorserie=des.cabeces.id.nrorserie "
                + "and ces.id.nrodocumento=des.cabeces.id.nrodocumento "
                + "and des.repuestos.id.idrepuesto=" + idRepuesto
                + "and des.repuestos.id.idlinea=" + idLinea
                + "and ces.tipocambio.fecha>='" + fi + "' "
                + "and ces.tipocambio.fecha<='" + ff + "' "
                + "and des.operaciones.idoperacion=op.idoperacion "
                + "and op.codigooperacion like 'S%' "
                + "group by month(ces.tipocambio.fecha), year(ces.tipocambio.fecha) "
                + "order by ces.tipocambio.fecha";
        System.out.println("query(getCantPedidaXAño):" + query);
        List joinList = session.createQuery(query).list();
        
        return joinList;
    }

    public Object getNumIngresoNext() {
        Object obj = new Object();
        obj = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select max(nroingreso) from Detallees");
        ArrayList lista = (ArrayList) q.list();
        if (lista != null) {
            Iterator it = lista.iterator();
            if (it.hasNext()) {
                return it.next();
            }
        }
        return obj;
    }

    public int contarDetallesNotaIngreso(int idsugerido, int nroingreso) {
        int contador;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select count(iddetalles) from Detallees "
                + "where cabecsugerido.idsugerido=" + idsugerido + " and nroingreso=" + nroingreso);
        ArrayList lista = (ArrayList) q.list();
        if (lista.size() == 0) {
            contador = 0;
        } else {
            Object cuenta = lista.iterator().next();
            contador = Integer.parseInt(String.valueOf(cuenta));
        }
        return contador;
    }

    public int contarDetallesNotaIngreso(int idsugerido) {
        int contador;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select count(iddetalles) from "
                + "Detallees where cabecsugerido.idsugerido=" + idsugerido);
        ArrayList lista = (ArrayList) q.list();
        if (lista.size() == 0) {
            contador = 0;
        } else {
            Object cuenta = lista.iterator().next();
            contador = Integer.parseInt(String.valueOf(cuenta));
        }
        return contador;
    }

    public String getMotivo(int idsugerido) {
        String motivo;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("select distinct(motivo) from Detallees where idsugerido=" + idsugerido);
        ArrayList lista = (ArrayList) q.list();
        if (lista.size() == 0) {
            motivo = "";
        } else {
            Object obj = lista.iterator().next();
            motivo = String.valueOf(obj);
        }
        return motivo;
    }

    public Detallees getDetallees(int idsugerido) {
        Detallees det = new Detallees();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detallees as des where "
                + "des.cabecsugerido.idsugerido=" + idsugerido);
        ArrayList lista = (ArrayList) q.list();
        if (lista.size() == 0) {
            det = null;
        } else {
            det = (Detallees) lista.iterator().next();
        }
        return det;
    }
}
