package Mantenimiento.IngresoSalidas;

import Entidades.Cabeces;
import Entidades.Detallees;
import Entidades.Repuestos;
import Servicios.HibernateUtil;
import Servicios.Util;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author CRS
 */
public class IngresoSalidaDAO {

    public boolean guardarCompra(ArrayList<Detallees> detalles, Cabeces cab) {
        Util util = new Util();
        double costoPromedio;
//        double ultimoCosto;
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            sesion.save(cab);
            //if(pago!=null) sesion.save(pago);
            for ( Detallees dtll : detalles ) {
                sesion.save(dtll);
                Repuestos r = dtll.getRepuestos();
                
                int stock = r.getStock();
                double _CostoPromedio = r.getCostopromedio();
                int cantidad = dtll.getCantentregada();
                double _Precio = dtll.getPreciolista();
                
//                System.out.println("Stock : "+stock+"\nCosto Promedio : "+_CostoPromedio+"\ncantidad : "+cantidad+
//                        "\nPrecio : "+_Precio);
                costoPromedio = ( stock * _CostoPromedio + cantidad * _Precio ) / ( stock + cantidad );
                costoPromedio = util.Redondear2Decimales(costoPromedio);
                
                r.setCostopromedio(costoPromedio);
                r.setPcostoultimo(_Precio);
                r.setStock(r.getStock() + dtll.getCantentregada());
                
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
            
        } catch ( HibernateException e ) {
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    public boolean guardarIngresoSalida(ArrayList<Detallees> detalles,int tipo){ // 2 : ingreso   ,   3  : salida
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            //if(pago!=null) sesion.save(pago);
            for ( Detallees dtll : detalles ) {
                sesion.save(dtll);
                Repuestos r = dtll.getRepuestos();
                
                if( tipo == 2 )
                    r.setStock(r.getStock() + dtll.getCantentregada());
                else
                    r.setStock(r.getStock() - dtll.getCantentregada());
                
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }
            tx.commit();
            
        } catch (HibernateException e) {
            System.out.println("Excepcion(guardarIngresoSalida):" + e.getMessage());
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }
    
    public boolean guardarIngresoPorNCDevolucion(ArrayList<Detallees> detalles, int tipo/*, List<Detallenota> detallesNota*/){ // 2 : ingreso   ,   3  : salida
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        try {
            for ( Detallees dtll : detalles ) {
                dtll.setCabeces(null);
                sesion.save(dtll);
                
                Repuestos r = dtll.getRepuestos();
                
                if( tipo == 2 )
                   System.out.println("CanEntregada " + dtll.getCantentregada() );
                   // Comente esta linea siguiente, parece que en otra parte del programa vuelve a Adicionar al Stock
                   //r.setStock(r.getStock() + dtll.getCantentregada());
                
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                
                sesion.flush();
                sesion.clear();
            }/*
            for (Detallenota detNota : detallesNota) {
                Repuestos r = detNota.getRepuestos();
                if( tipo == 2 )
                    r.setStock(r.getStock() + detNota.getCantidad());
                
                Repuestos rt = (Repuestos) sesion.merge(r);
                sesion.update(rt);
                sesion.flush();
                sesion.clear();
            }*/
            tx.commit();
            
        } catch ( HibernateException e ) {
            System.out.println("Excepcion(guardarIngresoSalida):" + e.getMessage());
            tx.rollback();
            e.printStackTrace();
            return false;
            
        } finally {
            sesion.close();
        }
        return true;
    }    
    
}
