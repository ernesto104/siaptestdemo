package Mantenimiento;

import Entidades.Control;
import Presentacion.FacturacionElectronica.EmpresaDocElectronico;
import Servicios.HibernateUtil;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Lely Aguilar
 */
public class ControlDAO extends GenericDAO<Control> {

    private JFrame it;

    public ControlDAO(JFrame IU) {
        this.it = IU;
    }

    public ControlDAO() {
    }

    public String obtenerRutaCarpeta() {
        String ruta;

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int respuesta = fc.showOpenDialog(it);
        if (respuesta == JFileChooser.APPROVE_OPTION) {

            ruta = fc.getSelectedFile().getAbsolutePath();
            return ruta;
        } else {
            return null;
        }
    }
    
    public Control obtener_objeto() {
        getHibernateTemplate().beginTransaction();
        Control ctr = (Control) session.createQuery("from Control").list();
        return ctr;
    }

    public Integer obtenerImpuesto() {
        Control c = Obtener_Objeto(0);
        return c.getImpuestoigv();
    }
    
    public boolean actualizarControl(Control c){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = ses.beginTransaction();
        
        try{
            Control ctrl = (Control) ses.merge(c);
            ses.update(ctrl);
            tx.commit();
            return true;
        }catch(Exception e)
        {
            tx.rollback();
            return false;
        }
    }
    
    public EmpresaDocElectronico obtenerEmpresaDocElectronico() {
        Session ses = getHibernateTemplate();
        EmpresaDocElectronico ede = null;
        String query = "select "
                    + " c.ruc as RE, "
                    + " '06' AS TDE, "
                    + " c.NOMBREMPRESA as ARSE, "
                    + " c.COD_UBI_SUNAT as UE, "
                    + " c.DIR_EMI_SUNAT as DIRE, "
                    + " u.DEPARTAMENTO as DEPE, "
                    + " u.PROVINCIA as PE, "
                    + " u.DISTRITO as DISE, "
                    + " c.RUTADOCELECT "

                    + " from control c "
                    + " inner join ubigeo u "
                    + " on u.idubigeo = c.idubigeo";

        try {
            List lst = ses.createSQLQuery(query).list();

            if ( lst != null ) {
                Object obj[] = (Object[]) lst.get(0);

                String re = String.valueOf(obj[0]);
                String tde = String.valueOf(obj[1]);
                String arse = String.valueOf(obj[2]);
                String ue = String.valueOf(obj[3]);
                String dire = String.valueOf(obj[4]);
                String depe = String.valueOf(obj[5]);
                String pe = String.valueOf(obj[6]);
                String dise = String.valueOf(obj[7]);
                String rde = String.valueOf(obj[8]);

                ede = new EmpresaDocElectronico();
                ede.setRuc_emisor(re);
                ede.setTip_doc_emisor(tde);
                ede.setApamno_razon_social_emisor(arse);
                ede.setUbigeo_emisor(ue);
                ede.setDireccion_emisor(dire);
                ede.setDepartamento_emisor(depe);
                ede.setProvincia_emisor(pe);
                ede.setDistrito_emisor(dise);
                ede.setRutaDocElectronicos(rde);
            }
            return ede;
            
        } catch ( Exception ex ) {
            System.out.println("Excepcion(obtenerEmpresaDocElectronico):" + ex.getMessage());
            return null;
                    
        } finally {
            ses.close();
            System.out.println("cerrando sesion(obtenerEmpresaDocElectronico)");
        }
    }
}