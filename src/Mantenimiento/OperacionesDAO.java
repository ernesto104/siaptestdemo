
package Mantenimiento;

import Entidades.Operaciones;
import Servicios.HibernateUtil;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class OperacionesDAO extends GenericDAO<Operaciones> {

    private Session sesion;
    private Transaction tx;
    
    public void actualizarTabla(JTable tabla) {
        ((DefaultTableModel) tabla.getModel()).setRowCount(0);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Operaciones> lista = Obtener_Lista_Objetos();
        for (int i = 0; i < lista.size(); i++) {
            Operaciones op = lista.get(i);
            Object[] tupla = {op.getIdoperacion(), op.getDescripcion(),
                op.getVerstock(), op.getCostos(), op.getTransaccion()};
            ((DefaultTableModel) tabla.getModel()).addRow(tupla);
        }
        DefaultTableCellRenderer def = new DefaultTableCellRenderer();
        def.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(def);
        }
    }
    public Operaciones getOperacion(String nombre){
        return (Operaciones) getHibernateTemplate().createQuery("from Operaciones o where o.codigooperacion = '"+nombre+"'").uniqueResult();
    }

    public int nextId() {
        Operaciones t = null;
        try {
            t = (Operaciones) getHibernateTemplate().createQuery("from Operaciones t order by t.idoperacion desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return t.getIdoperacion() + 1;
        }
    }
    
     private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();        
    }
    
    
        public List<Operaciones> Obtener_Lista_Objetos_OrderNombre() {
        List<Operaciones> lista = new LinkedList();
        try {
            iniciaOperacion();
            lista = sesion.createQuery("from Operaciones order by codigooperacion ASC").list();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return lista;
    }
    
    
        public Operaciones Obtener_Objeto_por_nombre(String nombre) {
        Operaciones op = new Operaciones();
        try {
            op = (Operaciones) getHibernateTemplate().createQuery("from Operaciones where idoperacion='"+nombre+"'").uniqueResult();
        } catch (Exception e) {
            System.err.println("Error : " + e.getLocalizedMessage());
        }finally{
            session.close();
        }
        return op;
    
    }
        
        
     public Operaciones Obtener_Operacion_Codigo(String codigo) {
        Operaciones operaciones = new Operaciones();
        try {
            iniciaOperacion();
            operaciones = (Operaciones) sesion.createQuery("from Operaciones where codigooperacion='" + codigo + "'").uniqueResult();
            tx.commit();

        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return operaciones;
    }
    
}
