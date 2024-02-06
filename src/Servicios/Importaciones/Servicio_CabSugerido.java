package Servicios.Importaciones;

import Presentacion.Importaciones.IU_Sugerido;
import Entidades.Cabecsugerido;
import Entidades.Importadores;
import Mantenimiento.Importaciones.CabSugDAO;
import Mantenimiento.Importaciones.ImportadoresDAO;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Servicios.HibernateUtil;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Servicio_CabSugerido {
    CabSugDAO CabSugDao;
    IU_Sugerido iuSugerido;
    IU_ActualizarSugerido iuActSugerido;
    
    public Servicio_CabSugerido() {
        CabSugDao=new CabSugDAO();
    }
    
    public Servicio_CabSugerido(IU_Sugerido iusug) {
        CabSugDao = new CabSugDAO();
        iuSugerido = iusug;
    }
    
    public Servicio_CabSugerido(IU_ActualizarSugerido ias) {
        CabSugDao = new CabSugDAO();
        iuActSugerido = ias;
    }
    
    public DefaultListModel Listar_Banco(DefaultListModel model) {
        List<Cabecsugerido> listaCabSug = CabSugDao.Obtener_Lista_Objetos();
        for ( int i = 0; i < listaCabSug.size(); i++ ) {
            model.addElement(listaCabSug.get(i).getIdsugerido());
        }
        return model;
    }
    
    public long getSize() {
        return CabSugDao.Tamaño_Lista();
    }
    
    public int cuentaOrdenesCompra(){
        int contador = CabSugDao.contarOrdenesCompra();
        return contador;
    }
    
    public Cabecsugerido getCabSugerido(int id){
        return CabSugDao.getCabSugerido(id);
    }
    
    public boolean addBanco(Cabecsugerido t) {
        return CabSugDao.Agregar_Objeto(t);
    }
    
    public void listar() {
        DefaultTableModel table = (DefaultTableModel) iuSugerido.tb_sugerido.getModel();
        Iterator ite = CabSugDao.Obtener_Lista_Objetos().iterator();
        while ( ite.hasNext() ) {
            Object[] row = new Object[5];
            Cabecsugerido temp = (Cabecsugerido) ite.next();
            row[0] = (double) temp.getIdsugerido();
            table.addRow(row);
        }
    }
    
    public int nextId(){
        return CabSugDao.nextId();
    }
    
    public boolean actualizarCabSug(int idCabSug,String numFacImp,String numGuia,Importadores imp, Date fechaEmbarque, double factorCosto){
        return CabSugDao.updateCabSug(idCabSug, numFacImp, numGuia, imp, fechaEmbarque, factorCosto);
    }
    
    public boolean borrarCabSug(Cabecsugerido cs){
        return CabSugDao.Eliminar_Objeto(cs);
    }
    
    public boolean eliminarCabSugerido(int idCabSug){
        boolean valor = false;
        Cabecsugerido cs = new Cabecsugerido();
        CabSugDAO csDao = new CabSugDAO();
        cs = csDao.getCabSug(idCabSug);
        
        if ( csDao.Eliminar_Objeto(cs) ) {
            valor = true;
        }
        return valor;
    }
    
    public int contarSugeridos(){
        return CabSugDao.contarSugeridos();
    }
    
    public int contarSugeridosYOrdenesCompra(){
        return CabSugDao.contarSugeridosYOrdenesCompra();
    }
    
    public Date getFechaIngresoStock(int idCabSug){
        CabSugDAO csd = new CabSugDAO();
        Date fecha = csd.getFechaIngresoStock(idCabSug);
        return fecha;
    }
    
    public int listarOrdenesPedidos(JComboBox cboPedOficial) {
        int numRegistros = 0;
        List<Cabecsugerido> cabSug = CabSugDao.cargarOrdenesPedidos();
        
        if ( cabSug.size() != 0 ) {
            numRegistros = cabSug.size();
            
            for ( int i = 0; i < numRegistros; i++ ) {
                cboPedOficial.addItem(cabSug.get(i).getIdsugerido());
            }
        }
        return numRegistros;
    }
    
    public double getFobTotal(int idCabSug){
        CabSugDAO csDao = new CabSugDAO();
        double fobTot = csDao.getTotalFob(idCabSug);
        return fobTot;
    }
    
    public boolean registrarCabecSugerido(Cabecsugerido cs) {
        boolean registro = false;
        try {
            createAndStoreRepuesto(cs);
            registro = true;
        }catch(ExceptionInInitializerError ex){
            System.out.println("Error al registrar cabecera de sugerido en la BD:"+ex);
        }
        HibernateUtil.getSessionFactory().close();
        return registro;
    }
    
    private void createAndStoreRepuesto(Cabecsugerido cs) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cs);
            session.getTransaction().commit();
            
        } catch(Throwable ex) {
            System.err.println ("creación inicial SessionFactory falló." + ex);
            throw new ExceptionInInitializerError (ex);
        }
    }
    
    public String verificarNroFacturaImportacion(int idsugerido){
        CabSugDAO csDao = new CabSugDAO();
        return csDao.getNroFactImportacion(idsugerido);
    }
    
    public String getActualNumImpresion(int idcabsug) {
        String val = CabSugDao.getNumImpresion(idcabsug);
        if ( val.equals("null") ) {
            return "0";
            
        } else {
            return val;
        }
    }
    
    public boolean actualizarNroGuia(int idsugerido,int nro){
        boolean val = false;
        if ( true ) {
            val = true;
        }
        return val;
    }
    
    public boolean actualizaEstadoTerminado(int idsugerido){
      boolean valor = false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      CabSugDAO anter = new CabSugDAO();
      Cabecsugerido cabAnt = new Cabecsugerido();
      cabAnt = anter.getCabSug(idsugerido);
      
      try {
         tx = session.beginTransaction();
         Cabecsugerido newcs = (Cabecsugerido)session.get(Cabecsugerido.class, idsugerido); 
         
         Importadores imp = new Importadores();
         imp = cabAnt.getImportadores();
         newcs.setImportadores(imp);
         
         newcs.setNrofacimportacion(cabAnt.getNrofacimportacion());
         newcs.setFechaingresostock(cabAnt.getFechaingresostock());
         newcs.setNroguia(cabAnt.getNroguia());
         newcs.setFechapedido(cabAnt.getFechapedido());
         newcs.setFechaembarque(cabAnt.getFechaembarque());
         newcs.setEstado(null);//terminado
         session.update(newcs);
         tx.commit();
         valor = true;
         
      } catch (HibernateException e) {
         if ( tx != null ) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return valor;
    }
    
    public boolean actualizaFechaPedido(int idsugerido,String nameImportador){
      boolean valor = false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      CabSugDAO anter = new CabSugDAO();
      Cabecsugerido cabAnt = new Cabecsugerido();
      cabAnt = anter.getCabSug(idsugerido);
      
      try {
         tx = session.beginTransaction();
         Cabecsugerido newcs = (Cabecsugerido)session.get(Cabecsugerido.class, idsugerido); 
         newcs.setEstado("*");//pendiente
         newcs.setFechaembarque(cabAnt.getFechaembarque());
         newcs.setFechaingresostock(cabAnt.getFechaingresostock());
         newcs.setFechapedido(new Date());
         
         ImportadoresDAO impDao = new ImportadoresDAO();
         Importadores imp = new Importadores();
         imp = impDao.getIdImportador(nameImportador);
         newcs.setImportadores(imp);
         newcs.setNrofacimportacion(cabAnt.getNrofacimportacion());

         session.update(newcs);
         tx.commit();
         valor = true;
         
      } catch (HibernateException e) {
         if ( tx!=null ) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return valor;
    }
    
    public boolean esSugerido(int idCabSug){
        if ( CabSugDao.esSugerido(idCabSug) != null ) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean esPedido(int idCabSug){
        if ( CabSugDao.esPedido(idCabSug) != null ) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean esOrdenCompra(int idCabSug){
        if ( CabSugDao.esOrdenCompra(idCabSug) != null ) {
            return true;
        } else {
            return false;
        }
    }
    
    public int listarCabSugerido(JComboBox cboIdSugerido){
        int numRegistros=0;
        List<Cabecsugerido> cabSug = CabSugDao.cargarCabSug();
        if ( cabSug.size() != 0 ) {
            numRegistros = cabSug.size();
            for ( int i = 0; i < numRegistros; i++ ) {
                cboIdSugerido.addItem(cabSug.get(i).getIdsugerido());
            }
        }
        return numRegistros;
    }
    
    public int listarCabSugeridosParaActualizar(JComboBox cboCabSug) {
        int numRegistros = 0;
        List<Cabecsugerido> cabSug = CabSugDao.listarCabSugeridosParaActualizar();
        
        if ( cabSug.size() != 0 ) {
            numRegistros = cabSug.size();
            
            for (int i = 0; i < numRegistros; i++ ) {
                cboCabSug.addItem(cabSug.get(i).getIdsugerido());
            }
        }
        return numRegistros;
    }
    
    public int listarCabSugeridosOrdenesCompra(JComboBox cboCabSug) {
        int numRegistros = 0;
        List<Cabecsugerido> cabSug = CabSugDao.listarCabSugeridosOrdenesCompra();
        
        if ( cabSug.size() != 0 ) {
            numRegistros = cabSug.size();
            
            for (int i = 0; i < numRegistros; i++ ) {
                cboCabSug.addItem(cabSug.get(i).getIdsugerido());
            }
        }
        return numRegistros;
    }
    
    public boolean actualizarFechaIngresoStock(Cabecsugerido cs) {
      boolean valor = false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         Cabecsugerido newCabSug = (Cabecsugerido)session.get(Cabecsugerido.class, cs.getIdsugerido()); 
         newCabSug = cs;
         newCabSug.setFechaingresostock(new Date());
         session.merge(newCabSug);
         tx.commit();
         valor = true;
         
      } catch (HibernateException e) {
         if ( tx != null ) tx.rollback();
         e.printStackTrace();
         
      } finally {
         session.close(); 
      }
      return valor;
    }
}