package Servicios.Importaciones;
import Mantenimiento.Importaciones.RepuestosDAO;
import Presentacion.Importaciones.IU_Sugerido;
import Entidades.Repuestos;
import Entidades.Equipos;
import Entidades.Otros.RepuestoDemanda;
import Entidades.RepuestosId;
import Mantenimiento.Importaciones.DetalleSugDAO;
import Mantenimiento.Importaciones.EquiposDAO;
import Servicios.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class Servicio_Repuestos {
    RepuestosDAO RepuestosDao;
    IU_Sugerido iuSugerido;
    DetalleSugDAO DetalleSugDao;
    double totalFOB=0.00;
    Repuestos r;
    public Servicio_Repuestos() {
        RepuestosDao = new RepuestosDAO();
        DetalleSugDao = new DetalleSugDAO();
    }
    public Servicio_Repuestos(IU_Sugerido iusug) {
        RepuestosDao = new RepuestosDAO();
        iuSugerido = iusug;
        DetalleSugDao = new DetalleSugDAO();
        r=new Repuestos();
    }
    public Repuestos obtenerRepuesto(int id) {
        Mantenimiento.Importaciones.RepuestosDAO repuestoDAO = new Mantenimiento.Importaciones.RepuestosDAO();
        return repuestoDAO.obtenerRepuesto(id);
    }
    public long getSize() {
        return RepuestosDao.Tamaño_Lista();
    }
    public Repuestos getRepuesto(String name) {
        Repuestos rep=null;
        if(RepuestosDao.buscarXNombre(name).size()!=0){
            rep = RepuestosDao.buscarXNombre(name).get(0);
        }
        return rep;
    }
    public boolean addRepuesto(Repuestos t) {
        return RepuestosDao.Agregar_Objeto(t);
    }
    public void setTotalFOB(double fob){
        this.totalFOB=fob;
    }
    public double getTotalFOB(){
        return totalFOB;
    }
    public int nextId(){
        return RepuestosDao.nextId();
    }
    public boolean borrarBanco(Repuestos t){
        return RepuestosDao.Eliminar_Objeto(t);
    }
    public boolean registrarRepuesto(Repuestos r){
        boolean registro=false;
        try{
            createAndStoreRepuesto(r);
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Repuesto registrado con éxito",
            "EXITO", JOptionPane.INFORMATION_MESSAGE);
            registro=true;
        }catch(ExceptionInInitializerError ex){
            System.out.println("Error al registrar repuesto en la BD:"+ex);
        }
        HibernateUtil.getSessionFactory().close();
        return registro;
    }
    private void createAndStoreRepuesto(Repuestos r) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        }catch(Throwable ex) {
            System.err.println ("creación inicial SessionFactory falló." + ex);
            throw new ExceptionInInitializerError (ex);
        }
    }
    public Repuestos prepararNuevoRepuesto(String nombre,String linea,String descrip,String precLista){
        Repuestos rep=new Repuestos();
        EquiposDAO equipDao=new EquiposDAO();
        Equipos equip=new Equipos();
        equip=equipDao.obtenerEquipo(linea);
        RepuestosId ri=new RepuestosId();
        RepuestosDAO repDao=new RepuestosDAO();
        long idNext=repDao.Tamaño_Lista()+1;
        ri.setIdrepuesto(Integer.parseInt(String.valueOf(idNext+1)));
        ri.setIdequipo(equip.getIdequipo());
        rep.setId(ri);
        rep.setCodrepuesto(nombre);
        rep.setEquipos(equip);
        rep.setDescripcion(descrip);
        try{
            rep.setPreciolista(Double.parseDouble(precLista));
        }catch(java.lang.NumberFormatException ex){
            System.out.println("Error: No se puede convertir texto a número "+ex.getMessage());
        }
        return rep;
    }
    public List getRepuestos(){
        return RepuestosDao.Obtener_Lista_Objetos();
    }
    public void cargarRepuestos(JTable tbRepuestos) {
        DefaultTableModel table = (DefaultTableModel) tbRepuestos.getModel();
        Iterator ite = RepuestosDao.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] fila = new Object[9];
            Repuestos tempRep = (Repuestos) ite.next();
            fila[0]=tempRep.getCodrepuesto();
            fila[1]=tempRep.getCodigoseg();
            fila[2]=tempRep.getDescripcion();
            fila[3]=tempRep.getAplicacion();
            fila[4]=tempRep.getStock();
            fila[5]=tempRep.getPreciolista();
            fila[6]=tempRep.getMarca();
            table.addRow(fila);
        }
    }
    public List buscaRepuestosXNombreIncompleto(String name){
        return RepuestosDao.getRepuestosXNameIncompleto(name);
    }
    public List buscarRepuestosXCodSecIncompleto(String codSec) {
        return RepuestosDao.getRepuestosXCodSecIncompleto(codSec);
    }
    public List buscarRepuestosXDescripcionIncompleto(String descripcion){
        return RepuestosDao.getRepuestosXDescripcionIncompleto(descripcion);
    }
    public List buscarRepuestosXLineas(String linea){
        return RepuestosDao.getRepuestosXLinea(linea);
    }
    public List repuestosXLineas(int idlinea){
        return RepuestosDao.repuestosXLinea(idlinea);
    }
    public Repuestos buscaRepuestoXNombre(String name){
        return RepuestosDao.busca1Repuesto(name);
    }
    public ArrayList<Repuestos> buscarRepuestosXNombre(String name) {
        return RepuestosDao.buscarXNombre(name);
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXNombre(Date fechaInicio, Date fechaFinal, String name) {
        return RepuestosDao.buscarDemandaAnualRepuestosXNombre(fechaInicio, fechaFinal, name);
    }
    
    public ArrayList<Repuestos> buscarRepuestosXCodSecundario(String codSec) {
        return RepuestosDao.buscarXCodSecundario(codSec);
    }
    public ArrayList<Repuestos> buscarRepuestosXDescripcion(String desc) {
        return RepuestosDao.buscarXDescripcion(desc);
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXDescripcion(Date fechaInicio, Date fechaFinal, String desc) {
        return RepuestosDao.buscarDemandaAnualRepuestosXDescripcion(fechaInicio, fechaFinal, desc);
    }
    
    public ArrayList<Repuestos> buscarRepuestosXMarca(String valor) {
        return RepuestosDao.buscarXMarca(valor);
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXMarca(Date fechaInicio, Date fechaFinal, String marca) {
        return RepuestosDao.buscarDemandaAnualRepuestosXMarca(fechaInicio, fechaFinal, marca);
    }
    
    public ArrayList<Repuestos> buscarRepuestosXStock(String valor) {
        return RepuestosDao.buscarXStock(Integer.parseInt(valor));
    }
    
    public List<RepuestoDemanda> buscarDemandaAnualRepuestosXStock(Date fechaInicio, Date fechaFinal, String stock) {
        return RepuestosDao.buscarDemandaAnualRepuestosXStock(fechaInicio, fechaFinal, stock);
    }
    
    public List getRepuestosASugerido(){
        return RepuestosDao.getRepuestosASugerido();
    }
    public int getIdRepuesto(String numParte) {
        Iterator ite = RepuestosDao.buscarXNombre(numParte).iterator();
        Repuestos r=null;
        if(ite.hasNext()){
            r = (Repuestos) ite.next();
            return r.getId().getIdrepuesto();
        }else{
            return -1;
        }
    }
    public List buscaRepuestosXNumSecundario(String numSecundario) {
        return RepuestosDao.getRepuestosXNumSecundario(numSecundario);
    }
    public List buscaRepuestosXDescripcion(String descripcion) {
        return RepuestosDao.getRepuestosXDescricion(descripcion);
    }
    public Repuestos getRepuesto(int idlinea, int idrepuesto){
        return RepuestosDao.getRepuesto(idlinea,idrepuesto);
    }
    public boolean actualizarRepuestos(Repuestos r,double costNuevo,double costoUnit,double costoProm){
      boolean valor=false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Repuestos newRep=(Repuestos)session.get(Repuestos.class, r.getId()); 
         newRep=r;
         newRep.setPcostoultimo(costNuevo);
         newRep.setFobultimo(costoUnit);
         newRep.setCostopromedio(costoProm);
         session.merge(newRep);
         tx.commit();
         valor=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return valor;
    }
    
    public boolean actualizarListaRepuestos(List<Repuestos> listaRep){
        boolean valor = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
           tx = session.beginTransaction();
           for ( Repuestos r : listaRep ) {
               Repuestos newRep = (Repuestos)session.get(Repuestos.class, r.getId()); 
               newRep = r;
               session.merge(newRep);
           }
           tx.commit();
           valor = true;

        } catch ( HibernateException e ) {
           if ( tx != null ) tx.rollback();
           e.printStackTrace();

        } finally {
           session.close(); 
        }
        return valor;
    }
    
    public boolean actualizarStock(Repuestos rep, int newStock) {
      boolean valor=false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Repuestos newRep=(Repuestos)session.get(Repuestos.class, rep.getId()); 
         newRep=rep;
         newRep.setStock(newStock);
         session.merge(newRep);
         tx.commit();
         valor=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return valor;
    }
    
    public boolean actualizarListaStock(List<Repuestos> listaRep) {
        boolean valor = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
           tx = session.beginTransaction();
           
           for ( Repuestos rep : listaRep ) {
               Repuestos newRep = (Repuestos)session.get(Repuestos.class, rep.getId()); 
               newRep = rep;
//               newRep.setStock(newStock);
               session.merge(newRep);
           }
           tx.commit();
           valor = true;

        } catch ( HibernateException e ) {
           if (tx != null ) tx.rollback();
           e.printStackTrace(); 

        } finally {
           session.close(); 
        }
        return valor;
    }
    
    public boolean actualizarFOBUltimo(Repuestos rep, double newFobUltimo) {
      boolean valor=false;
      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Repuestos newRep=(Repuestos)session.get(Repuestos.class, rep.getId()); 
         newRep=rep;
         newRep.setFobultimo(newFobUltimo);
         session.merge(newRep);
         tx.commit();
         valor=true;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return valor;
    }
    public boolean actualizaPrecios(TableModel tabla,double factor,boolean sinDec){
        return RepuestosDao.actualizarPrecios(tabla,factor,sinDec);
    }
    public boolean restauraPrecios(TableModel tabla){
        return RepuestosDao.restaurarPrecios(tabla);
    }
    public List obtenerMarcas() {
        RepuestosDAO repuestoDAO = new RepuestosDAO();
        return repuestoDAO.obtenerMarcas();
    }
}