package Mantenimiento;

import Entidades.Paquetes;
import Servicios.HibernateUtil;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class PaquetesDAO extends GenericDAO<Paquetes> {

    public void actualizarTabla(JTable tabla) {
        ((DefaultTableModel) tabla.getModel()).setRowCount(0);
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Paquetes> lista = Obtener_Lista_Objetos();
        for (int i = 0; i < lista.size(); i++) {
            Paquetes paquete = lista.get(i);
            Object[] tupla = {paquete.getIdpaquete(), paquete.getDescripcion()};
            ((DefaultTableModel) tabla.getModel()).addRow(tupla);
        }
        DefaultTableCellRenderer def = new DefaultTableCellRenderer();
        def.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(def);
        }
    }

    public int nextId() {
        Paquetes t = null;
        try {
            t = (Paquetes) getHibernateTemplate().createQuery("from Paquetes t order by t.idpaquete desc").iterate().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        if (Tamaño_Lista() == 0) {
            return 1;
        } else {
            return t.getIdpaquete() + 1;
        }
    }

    public List obtenerRepuestos_Paq(int id) {
        return getHibernateTemplate().createQuery("from PaquetesRepuestos p where p.paquetes.idpaquete='" + id + "'").list();
    }
}
